package ca.esystem.bridges.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.OrderStatus;
import ca.esystem.bridges.domain.OrderSubStatus;
import ca.esystem.bridges.domain.ServiceSchedule;
import ca.esystem.bridges.domain.Service_Order;
import ca.esystem.bridges.domain.SmsMessage;
import ca.esystem.bridges.domain.UserAccount;
import ca.esystem.bridges.http.SMSRequest;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.OrderProcessingService;
import ca.esystem.bridges.service.ServiceOrderMngService;
import ca.esystem.bridges.service.SmsMessageService;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.sysio.impl.SMSClient;
import ca.esystem.framework.domain.Pagination;
import ca.esystem.framework.util.CommonUtil;

@Service("OrderProcessingService")
public class OrderProcessingServiceImpl implements OrderProcessingService {

    @Resource(name="GlobalProperties")
    private Properties globalproperties;    
    
    @Resource(name="UserAccountService")
    private UserAccountService userAccountService;
    
    @Resource(name="MembershipService")
    private MembershipService membershipService;
    
    @Resource(name="ServiceOrderMngService")
    private ServiceOrderMngService serviceOrderMngService;
    
    @Resource(name="SmsMessageService")
    private SmsMessageService smsMessageService;
    
    private String smsService;
    private String smsCallbackUrl;
    
    private static String MESSAGE_ASK_FOR_BID = "客户 %s 订购了您的 %s 服务，请选择您可以提供服务的时间， %s 回复 #{code}30: 表示上述时间都无法提供服务";
    private static String MESSAGE_INFORM_UNBID = "对不起，您选择的服务供应商在该时段无法服务，如果您愿意接受三桥服务为您推荐服务商, 回复#{code}11, 如果您愿意取消订单，回复 #{code}50";
    private static String MESSAGE_OFFER_FOR_BID = "客户 %s 希望订购您的 %s 服务，请选择您可以提供服务的时间，%s 回复 #{code}30: 表示上述时间都无法提供服务";
    private static String MESSAGE_ORDER_STATUS = "请查看订单状态";
    private static String MESSAGE_CANCELLED_ORDER = "取消订单";
    private static String MESSAGE_ORDER_CONFIRM = "确认服务订单成功";
    private static String MESSAGE_ORDER_ALREADY_ACCEPTED = "对不起，该订单已被其他供应商认领";
    
    private static Integer providerTimeLimit = 2; // hrs
    private static Integer customerTimeLimit = 48; // hrs    
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY年MM月DD日 HH时mm分");
    
    public boolean receiveOrderSMS(String phoneNumber, String orderId, String message) {
        //
        if (phoneNumber == null || orderId == null || message == null) return false;
        
        //
        String[] codes = parseMessage(message);
        orderId = orderId.replace("O", "");
        if (phoneNumber.length() > 10) phoneNumber = phoneNumber.substring(phoneNumber.length() - 10, phoneNumber.length());
        // Retrieve order
        Service_Order order = serviceOrderMngService.queryOrderById(Integer.valueOf(orderId));
        if (order == null) return false;
 
        //
        Date timestamp = CommonUtil.getUTCTime();
        Service_Order statusUpdate = new Service_Order();
        statusUpdate.setOrder_id(order.getOrder_id());
        statusUpdate.setStatus_id(order.getStatus_id());
        statusUpdate.setSub_status_id(order.getSub_status_id()); 
        
        //
        if (phoneNumber.equalsIgnoreCase(order.getCell())) {
            // SMS from customer
            
            // Process order
            if (OrderStatus.STATUS_UNBID.equalsIgnoreCase(order.getStatus_id()) &&
                    OrderSubStatus.STATUS_INFORM.equalsIgnoreCase(order.getSub_status_id())) {
                // Unbid order; customer can get recommended service provider or cancel order
                if ("11".equals(codes[1])) {
                    // Continue, 
                    statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                    statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_CONTINUE);
                    statusUpdate.setProcessed_at(timestamp);
                    serviceOrderMngService.updateStatus(statusUpdate);
                }
                else if ("50".equals(codes[1])) {
                    // Continue, 
                    statusUpdate.setNew_status_id(OrderStatus.STATUS_CANCELLED);
                    statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                    statusUpdate.setProcessed_at(timestamp);
                    serviceOrderMngService.updateStatus(statusUpdate);                    
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            // SMS not from customer
            
            // Retrieve service provider
            Business_Profile serviceProvider = null;
            if (order.getBusiness_member_id() != null) {
                serviceProvider = membershipService.getBusinesssProfileByMember(order.getBusiness_member_id());
            }
            
            // 
            if (serviceProvider != null && phoneNumber.equalsIgnoreCase(serviceProvider.getPhone())) {
                // Phone number is service provider's
                // Process order
                if (OrderStatus.STATUS_PENDING.equalsIgnoreCase(order.getStatus_id()) &&
                        OrderSubStatus.STATUS_ASK.equalsIgnoreCase(order.getSub_status_id())) {
                    if ("21".equals(codes[1]) || "22".equals(codes[1]) || "23".equals(codes[1])) {
                        // Bid, 
                        statusUpdate.setNew_status_id(OrderStatus.STATUS_BID);
                        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                        statusUpdate.setProcessed_at(timestamp);
                        serviceOrderMngService.updateStatus(statusUpdate);
                        
                        // Send customer confirmation
                        sendSmsMessage(order.getCell(), order.getOrder_id(), MESSAGE_ORDER_CONFIRM);
                        
                        // Send other member confirmation
                        sendSmsMessage(serviceProvider.getPhone(), order.getOrder_id(), MESSAGE_ORDER_CONFIRM);
                        
                    }
                    else if ("30".equals(codes[1])) {
                        // Unbid, 

                        // Send customer sms
                        boolean ret = sendAndSaveSmsMessage(order.getCell(), order.getOrder_id(), MESSAGE_INFORM_UNBID, customerTimeLimit);

                        //
                        if (ret) {
                            // Customer informed
                            statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                            statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_INFORM);
                            statusUpdate.setProcessed_at(timestamp);
                            serviceOrderMngService.updateStatus(statusUpdate);
                        }
                        else {
                            // Wait for processing
                            statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                            statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                            statusUpdate.setProcessed_at(timestamp);
                            serviceOrderMngService.updateStatus(statusUpdate);                           
                        }
                    }
                    else {
                        return false;
                    }                    
                }
                else {
                    return false;
                }
            }
            else {
                // Phone number is from other member's
                Business_Profile otherMember = membershipService.getBusinesssProfileByPhone(phoneNumber);
                if (otherMember == null) return false;
                
               // Process order
                if (OrderStatus.STATUS_UNBID.equalsIgnoreCase(order.getStatus_id()) &&
                        OrderSubStatus.STATUS_ASK.equalsIgnoreCase(order.getSub_status_id())) {
                    if ("21".equals(codes[1]) || "22".equals(codes[1]) || "23".equals(codes[1])) {
                        // Bid, 
                        statusUpdate.setNew_status_id(OrderStatus.STATUS_BID);
                        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                        statusUpdate.setProcessed_at(timestamp);
                        statusUpdate.setBusiness_member_id(otherMember.getMember_id());
                        serviceOrderMngService.updateStatus(statusUpdate);
                        
                        // Send customer confirmation
                        sendSmsMessage(order.getCell(), order.getOrder_id(), MESSAGE_ORDER_CONFIRM);
                        
                        // Send other member confirmation
                        sendSmsMessage(otherMember.getPhone(), order.getOrder_id(), MESSAGE_ORDER_CONFIRM);
                    }
                    else {
                        return false;
                    }
                }
                else if (OrderStatus.STATUS_BID.equalsIgnoreCase(order.getStatus_id())){
                    if ("21".equals(codes[1]) || "22".equals(codes[1]) || "23".equals(codes[1])) {
                        // Bid, 
                        
                        // Send other member confirmation
                        sendSmsMessage(otherMember.getPhone(), order.getOrder_id(), MESSAGE_ORDER_ALREADY_ACCEPTED);
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }               
            }
        }
        
        return true;
    }

    public boolean processPendingOrder() {
        
        // Unbidded, informed customer, but no action from customer
        Date beforeCustomerTimeLimit = CommonUtil.getUTCTimeHourBefore(customerTimeLimit);
        Service_Order cancelOrderSearch = new Service_Order();
        cancelOrderSearch.setStatus_id(OrderStatus.STATUS_UNBID);
        cancelOrderSearch.setSub_status_id(OrderSubStatus.STATUS_INFORM);
        cancelOrderSearch.setProcessed_at(beforeCustomerTimeLimit);
        Pagination orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        cancelOrderSearch.setPagination(orderPage);
        List cancelOrderList = serviceOrderMngService.queryListForProfessing(cancelOrderSearch);
          
        // Pending order list
        Date beforeProviderTimeLimit = CommonUtil.getUTCTimeHourBefore(providerTimeLimit);
        Service_Order pendingOrderSearch = new Service_Order();
        pendingOrderSearch.setStatus_id(OrderStatus.STATUS_PENDING);
        pendingOrderSearch.setSub_status_id(OrderSubStatus.STATUS_DEFAULT);        
        // Immediately
        //pendingOrderSearch.setProcessed_at(beforeProviderTimeLimit);
        orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        pendingOrderSearch.setPagination(orderPage);
        List pendingOrderList = serviceOrderMngService.queryListForProfessing(pendingOrderSearch);
        
        // Pending order, asked for bid, no reply from provider
        Service_Order unbiddingOrderSearch = new Service_Order();
        unbiddingOrderSearch.setStatus_id(OrderStatus.STATUS_PENDING);
        unbiddingOrderSearch.setSub_status_id(OrderSubStatus.STATUS_ASK);          
        unbiddingOrderSearch.setProcessed_at(beforeProviderTimeLimit);
        orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        unbiddingOrderSearch.setPagination(orderPage);
        List unbiddingOrderList = serviceOrderMngService.queryListForProfessing(unbiddingOrderSearch);       
        
        // Provider unbidded, Unbid order list
        Service_Order unbidOrderSearch = new Service_Order();
        unbidOrderSearch.setStatus_id(OrderStatus.STATUS_UNBID);
        unbidOrderSearch.setSub_status_id(OrderSubStatus.STATUS_DEFAULT);
        // Immediately
        //unbidOrderSearch.setProcessed_at(beforeProviderTimeLimit);
        orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        unbidOrderSearch.setPagination(orderPage);
        List unbidOrderList = serviceOrderMngService.queryListForProfessing(unbidOrderSearch);
        
        // Provider unbidded, customer chose to accept recommended other providers; Offer Unbid order list
        Service_Order offerOrderSearch = new Service_Order();
        offerOrderSearch.setStatus_id(OrderStatus.STATUS_UNBID);
        offerOrderSearch.setSub_status_id(OrderSubStatus.STATUS_CONTINUE);
        // Immediately
        //offerOrderSearch.setProcessed_at(beforeProviderTimeLimit);
        orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        offerOrderSearch.setPagination(orderPage);
        List offerOrderList = serviceOrderMngService.queryListForProfessing(offerOrderSearch);
        
        // Asked other providers to bid, no response; Cancelling Unbid order list
        Service_Order cancellingOrderSearch = new Service_Order();
        cancellingOrderSearch.setStatus_id(OrderStatus.STATUS_UNBID);
        cancellingOrderSearch.setSub_status_id(OrderSubStatus.STATUS_ASK);
        cancellingOrderSearch.setProcessed_at(beforeProviderTimeLimit);
        orderPage = new Pagination();
        orderPage.setSkip(0);
        orderPage.setPageSize(0);
        cancellingOrderSearch.setPagination(orderPage);
        List cancellingOrderList = serviceOrderMngService.queryListForProfessing(cancellingOrderSearch);
        
        // Process cancelled orders
        Date timestamp = CommonUtil.getUTCTime();       
        if (cancelOrderList != null) {
            for (Object obj : cancelOrderList) {
                Service_Order cancelOrder = (Service_Order)obj;
                boolean ret = sendSmsMessage(cancelOrder.getCell(), cancelOrder.getOrder_id(), MESSAGE_CANCELLED_ORDER);
                if (!ret) return false;
                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(cancelOrder.getOrder_id());
                statusUpdate.setStatus_id(cancelOrder.getStatus_id());
                statusUpdate.setSub_status_id(cancelOrder.getSub_status_id());
                statusUpdate.setNew_status_id(OrderStatus.STATUS_CANCELLED);
                statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                statusUpdate.setProcessed_at(timestamp);
                serviceOrderMngService.updateStatus(statusUpdate);                
            }
        }
        
        // Process pending orders
        if (pendingOrderList != null) {
            for (Object obj : pendingOrderList) {
                Service_Order pendingOrder = (Service_Order)obj;
                if (pendingOrder.getBusiness_member_id() == null) continue;
                Business_Profile member = membershipService.getBusinesssProfileByMember(pendingOrder.getBusiness_member_id());
                if (member == null) continue;
                //
                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(pendingOrder.getOrder_id());
                statusUpdate.setStatus_id(pendingOrder.getStatus_id());
                statusUpdate.setSub_status_id(pendingOrder.getSub_status_id());                
                if (OrderSubStatus.STATUS_DEFAULT.equalsIgnoreCase(pendingOrder.getSub_status_id())) {
                    // Pending, not ased for bid; ask for bid
                    String message = askForBidMessage(pendingOrder, MESSAGE_ASK_FOR_BID);
                    if (message == null) continue;
                    boolean ret = sendAndSaveSmsMessage(member.getPhone(), pendingOrder.getOrder_id(), message, providerTimeLimit);
                    if (!ret) return false;
                    //
                    statusUpdate.setNew_status_id(OrderStatus.STATUS_PENDING);
                    statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_ASK);
                    statusUpdate.setProcessed_at(timestamp);
                    serviceOrderMngService.updateStatus(statusUpdate);
                }
            }
        }
        
        // Process unbidding orders
        if (unbiddingOrderList != null) {
            for (Object obj : unbiddingOrderList) {
                Service_Order unbiddingOrder = (Service_Order)obj;
                if (unbiddingOrder.getBusiness_member_id() == null) continue;
                Business_Profile member = membershipService.getBusinesssProfileByMember(unbiddingOrder.getBusiness_member_id());
                if (member == null) continue;
                //
                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(unbiddingOrder.getOrder_id());
                statusUpdate.setStatus_id(unbiddingOrder.getStatus_id());
                statusUpdate.setSub_status_id(unbiddingOrder.getSub_status_id());                
                if (OrderSubStatus.STATUS_ASK.equalsIgnoreCase(unbiddingOrder.getSub_status_id())) {
                    // Asked for bid, no bid; set to ubbid and inform customer
                    
                    // Send customer sms
                    boolean ret = sendAndSaveSmsMessage(unbiddingOrder.getCell(), unbiddingOrder.getOrder_id(), MESSAGE_INFORM_UNBID, customerTimeLimit);
                    
                    if (ret) {
                        // Customer informed
                        statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_INFORM);
                        statusUpdate.setProcessed_at(timestamp);
                        serviceOrderMngService.updateStatus(statusUpdate);
                    }
                    else {
                        // Wait for processing
                        statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_DEFAULT);
                        statusUpdate.setProcessed_at(timestamp);
                        serviceOrderMngService.updateStatus(statusUpdate);                       
                    }
                    
                }
            }
        }
        
        // Process unbid orders
        if (unbidOrderList != null) {
            for (Object obj : unbidOrderList) {
                Service_Order unbidOrder = (Service_Order)obj;
                if (unbidOrder.getBusiness_member_id() == null) continue;
                Business_Profile member = membershipService.getBusinesssProfileByMember(unbidOrder.getBusiness_member_id());
                if (member == null) continue;

                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(unbidOrder.getOrder_id());
                statusUpdate.setStatus_id(unbidOrder.getStatus_id());
                statusUpdate.setSub_status_id(unbidOrder.getSub_status_id());
                if (OrderSubStatus.STATUS_DEFAULT.equalsIgnoreCase(unbidOrder.getSub_status_id())) {
                    // Unbid
                    // Send customer sms
                    boolean ret = sendAndSaveSmsMessage(unbidOrder.getCell(), unbidOrder.getOrder_id(), MESSAGE_INFORM_UNBID, customerTimeLimit);
                    if (!ret) return false;
                    
                    statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                    statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_INFORM);
                    statusUpdate.setProcessed_at(timestamp);
                    serviceOrderMngService.updateStatus(statusUpdate);
                }
            }
        }       
        
        // Process offers of unbid orders
        if (offerOrderList != null) {
            for (Object obj : offerOrderList) {
                Service_Order offerOrder = (Service_Order)obj;
 
                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(offerOrder.getOrder_id());
                statusUpdate.setStatus_id(offerOrder.getStatus_id());
                statusUpdate.setSub_status_id(offerOrder.getSub_status_id());
                if (OrderSubStatus.STATUS_CONTINUE.equalsIgnoreCase(offerOrder.getSub_status_id())) {
                    // Unbid, customer accept recommended providers
                    
                    //
                    if (offerOrder.getBusiness_member_id() == null) continue;
                    
                    //
                    boolean sent = false;
                    int count = 0;
                    List memberList = membershipService.getSimilarBusinesssProfileListByMember(offerOrder.getBusiness_member_id());
                    if (memberList != null) {
                        for (Object obj1: memberList) {
                            Business_Profile member = (Business_Profile)obj1;
                            String message = askForBidMessage(offerOrder, MESSAGE_OFFER_FOR_BID);
                            boolean ret = sendAndSaveSmsMessage(member.getPhone(), offerOrder.getOrder_id(), message, providerTimeLimit);
                            if (!ret) break;
                            sent = true;
                            count ++;
                            if (count >= 3) break;
                        }
                    }
                    
                    //
                    if (sent) {                              
                        statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_ASK);
                        statusUpdate.setProcessed_at(timestamp);
                        serviceOrderMngService.updateStatus(statusUpdate);
                    }
                }
            }
        }
        
        // Process canceling of unbid orders
        if (cancellingOrderList != null) {
            for (Object obj : cancellingOrderList) {
                Service_Order cancellingOrder = (Service_Order)obj;
                if (cancellingOrder.getBusiness_member_id() == null) continue;
                Business_Profile member = membershipService.getBusinesssProfileByMember(cancellingOrder.getBusiness_member_id());
                if (member == null) continue;

                Service_Order statusUpdate = new Service_Order();
                statusUpdate.setOrder_id(cancellingOrder.getOrder_id());
                statusUpdate.setStatus_id(cancellingOrder.getStatus_id());
                statusUpdate.setSub_status_id(cancellingOrder.getSub_status_id());
                if (OrderSubStatus.STATUS_ASK.equalsIgnoreCase(cancellingOrder.getSub_status_id())) {
                    // Unbid, offered to other members; no reply
                   
                    statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
                    statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_CANCELLING);
                    statusUpdate.setProcessed_at(timestamp);
                    serviceOrderMngService.updateStatus(statusUpdate);
                }
            }
        }
        return true;
    }

    public boolean sendAskForBidSMS(Integer operatorId, String memberId, Integer orderId) {

        // Retrieve order
        Service_Order order = serviceOrderMngService.queryOrderById(orderId);
        if (order == null) return false;
        
        // Retrieve service provider
        Business_Profile serviceProvider = null;
        if (order.getBusiness_member_id() != null) {
            serviceProvider = membershipService.getBusinesssProfileByMember(order.getBusiness_member_id());
        }
        if (serviceProvider == null) return false;
        
        // Send message
        String message = askForBidMessage(order, MESSAGE_ASK_FOR_BID);
        if (message == null) return false;
        boolean ret = sendAndSaveSmsMessage(serviceProvider.getPhone(), orderId, message, providerTimeLimit);
        if (!ret) return false;
        
        //
        Date timestamp = CommonUtil.getUTCTime();
        Service_Order statusUpdate = new Service_Order();
        statusUpdate.setOrder_id(order.getOrder_id());
        statusUpdate.setStatus_id(order.getStatus_id());
        statusUpdate.setSub_status_id(order.getSub_status_id());
        statusUpdate.setNew_status_id(OrderStatus.STATUS_PENDING);
        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_ASK);
        statusUpdate.setProcessed_at(timestamp);
        statusUpdate.setModified_by(operatorId);
        statusUpdate.setModified_at(timestamp);
        serviceOrderMngService.updateStatus(statusUpdate);
        
        //
        return true;        

    }

    public boolean sendOrderStatusSMS(String memberId, Integer orderId) {
        // Retrieve order
        Service_Order order = serviceOrderMngService.queryOrderById(orderId);
        if (order == null) return false;
        
        // Send message
        boolean ret = sendSmsMessage(order.getCell(), orderId, MESSAGE_ORDER_STATUS);
        if (!ret) return false;        
        
        //
        return true;
    }

    public boolean sendAskForBidSMS(Integer operatorId, Integer orderId) {
        // Retrieve order
        Service_Order order = serviceOrderMngService.queryOrderById(orderId);
        if (order == null) return false;
        if (order.getBusiness_member_id() == null) return false;        
        
        //
        boolean sent = false;
        int count = 0;
        List memberList = membershipService.getSimilarBusinesssProfileListByMember(order.getBusiness_member_id());
        if (memberList != null) {
            for (Object obj: memberList) {
                UserAccount member = (UserAccount)obj;
                String message = askForBidMessage(order, MESSAGE_OFFER_FOR_BID);
                if (message == null) continue;
                boolean ret = sendAndSaveSmsMessage(member.getPhone(), orderId, message, providerTimeLimit);
                if (!ret) break;
                sent = true;
                count ++;
                if (count >= 3) break;
            }
        }
        if (!sent) return false;
        
        //
        Date timestamp = CommonUtil.getUTCTime();
        Service_Order statusUpdate = new Service_Order();
        statusUpdate.setOrder_id(order.getOrder_id());
        statusUpdate.setStatus_id(order.getStatus_id());
        statusUpdate.setSub_status_id(order.getSub_status_id());
        statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_ASK);
        statusUpdate.setProcessed_at(timestamp);
        statusUpdate.setModified_by(operatorId);
        statusUpdate.setModified_at(timestamp);
        serviceOrderMngService.updateStatus(statusUpdate);
        
        //
        return true;
    }

    public boolean updateOrderStatus(Integer operatorId, Integer orderId, String status) {

        if (!(OrderStatus.STATUS_EVALUATED.equalsIgnoreCase(status) ||
                OrderStatus.STATUS_CANCELLED.equalsIgnoreCase(status) ||
                OrderStatus.STATUS_COMPLETED.equalsIgnoreCase(status))) return false;
        
        // Retrieve order
        Service_Order order = serviceOrderMngService.queryOrderById(orderId);
        if (order == null) return false;
        
        //
        Date timestamp = CommonUtil.getUTCTime();
        Service_Order statusUpdate = new Service_Order();
        statusUpdate.setOrder_id(order.getOrder_id());
        statusUpdate.setStatus_id(order.getStatus_id());
        statusUpdate.setSub_status_id(order.getSub_status_id());
        statusUpdate.setNew_status_id(OrderStatus.STATUS_UNBID);
        statusUpdate.setNew_sub_status_id(OrderSubStatus.STATUS_INFORM);
        statusUpdate.setProcessed_at(timestamp);
        statusUpdate.setModified_by(operatorId);
        statusUpdate.setModified_at(timestamp);
        serviceOrderMngService.updateStatus(statusUpdate);
        
        return true;
    }

    
    public boolean sendAndSaveSmsMessage(String phone, Integer orderId, String message, Integer session) {
        
        //
        Integer code = 1;
        Date beforeTimeLimit = CommonUtil.getUTCTimeMinuteBefore(session * 60 + 5);
        SmsMessage messageSearch = new SmsMessage();
        messageSearch.setPhone(phone);
        messageSearch.setCreated_at(beforeTimeLimit);
        
        //
        Date timestamp = CommonUtil.getUTCTime();

        //
        synchronized (OrderProcessingServiceImpl.class){
            Integer codeMin = smsMessageService.queryMin(messageSearch);
            if (codeMin != null && codeMin > 1) {
                code = codeMin - 1;
            }
            else {
                Integer codeMax = smsMessageService.queryMax(messageSearch);
                if (codeMax != null) code = codeMax + 1;
            }
            
            //
            message = message.replaceAll("\\#\\{code\\}", code.toString());
            SmsMessage smsMessage = new SmsMessage(phone, code, message, timestamp);
            smsMessageService.add(smsMessage);
        }
        
        //
        smsService = globalproperties.getProperty("SMSService");
        smsCallbackUrl = globalproperties.getProperty("SMSCallbackUrl");  

        SMSRequest request = new SMSRequest("O" + orderId, message, phone, session * 3600000, "^" + code + "..", smsCallbackUrl);
        boolean ret = SMSClient.sendSMS(smsService, request);
        if (!ret) return false;
        
        //
        return true;
    }
    
    public boolean sendSmsMessage(String phone, Integer orderId, String message) {
      smsService = globalproperties.getProperty("SMSService");
      smsCallbackUrl = globalproperties.getProperty("SMSCallbackUrl");  
        
      SMSRequest request = new SMSRequest("O" + orderId, message, phone, 0, null, null);
      boolean ret = SMSClient.sendSMS(smsService, request);
      
      return true;
    }
    
    private String[] parseMessage(String message) {

        //
        String code = null;
        String option = null;
        if (message.length() >= 2) {
            code = message.substring(0, message.length() - 2);
            option = message.substring(message.length() - 2, message.length());
        }
        
        //
        return new String[]{code, option};
    }
    
    private String askForBidMessage(Service_Order order, String format) {
        String name = String.format("%s %s", order.getLast_name(), order.getFirst_name());
        List<ServiceSchedule> scheduleList = serviceOrderMngService.queryScheduleListByOrder(order.getOrder_id());
        if (scheduleList == null || scheduleList.size() == 0) return null;
        int code = 21;
        String time = "";
        for (ServiceSchedule schedule: scheduleList) {
            time = time + "回复 #{code}" + code + ": 选 " + dateFormatter.format(schedule.getServicetime()) + ", ";
            code ++;
        }
        String message = String.format(format, name, order.getService_title(), time);
        
        return message;
    }
}
