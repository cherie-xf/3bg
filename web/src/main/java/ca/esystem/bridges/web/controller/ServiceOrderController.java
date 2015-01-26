package ca.esystem.bridges.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ca.esystem.bridges.dao.MembershipDao;
import ca.esystem.bridges.domain.*;
import ca.esystem.bridges.security.LoggedInUser;
import ca.esystem.bridges.service.AddressService;
import ca.esystem.bridges.service.MembershipService;
import ca.esystem.bridges.service.ServiceOrderMngService;
import ca.esystem.bridges.service.ServiceProductMngService;
import ca.esystem.bridges.service.UserService;
import ca.esystem.framework.web.controller.AbstractController;

/**
 * The controller class for service order and related domain object requests.
 * 
 * @author Lei Han
 *
 */
@Controller()
public class ServiceOrderController extends AbstractController {
    private final Logger             logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "GlobalProperties")
    private Properties               globalproperties;

    @Resource(name = "ServiceOrderMngService")
    private ServiceOrderMngService   service;

    @Resource(name = "ServiceProductMngService")
    private ServiceProductMngService serviceProductService;

    @Resource
    private MembershipDao            membershipDao;

    @Resource(name = "UserService")
    private UserService              userService;

    @Resource(name = "AddressService")
    private AddressService           addressService;

    @Resource(name = "MembershipService")
    private MembershipService        membershipService;

    @RequestMapping(value = "/serviceorder/suppliermanage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchSupplierServiceOrderList(@ModelAttribute("serviceOrderQuery") ServiceOrderDoubleListSearchType serviceOrderQuery, Model model,
            HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();

        serviceOrderQuery.setLoginUserId(loginUserId);

        queryServiceOrderList(serviceOrderQuery, model, true);

        return "/serviceorder/suppliermanage";
    }

    @RequestMapping(value = "/serviceorder/buyermanage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchBuyerServiceOrderList(@ModelAttribute("serviceOrderQuery") ServiceOrderDoubleListSearchType serviceOrderQuery, Model model,
            HttpServletRequest request) throws Exception {
        
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();

        serviceOrderQuery.setLoginUserId(loginUserId);

        queryServiceOrderList(serviceOrderQuery, model, false);

        return "/serviceorder/buyermanage";
    }

    @RequestMapping(value = "/serviceorder/suppliercancel", method = { RequestMethod.GET, RequestMethod.POST })
    public String cancelSupplierServiceOrder(@ModelAttribute("serviceOrderQuery") ServiceOrderDoubleListSearchType serviceOrderQuery, Model model,
            HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();
        
        Integer order_id = serviceOrderQuery.getCancelOrderId();
        
        if(isBefor12Hours(order_id)){
            cancelOrder(order_id, loginUserId);
            serviceOrderQuery.setBackendMessage(String.format("成功取消订单ID为：%s的订单。", order_id));
        }else{
            serviceOrderQuery.setBackendMessage(String.format("当前时间距离选定订单服务时间不足12小时， 不能取消订单ID为：%s的订单。", order_id));
        }
        
        serviceOrderQuery.setLoginUserId(loginUserId);
        queryServiceOrderList(serviceOrderQuery, model, true);
        
        return "/serviceorder/suppliermanage";
    }
    
    @RequestMapping(value = "/serviceorder/bid", method = { RequestMethod.GET})
    public String openBidOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        Service_Order serviceOrder = (Service_Order) service.queryOne(serviceOrderQuery);

        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);

        serviceOrder.setOperate("update");
        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/bidform";
    }
    
    @RequestMapping(value = "/serviceorder/bid", method = { RequestMethod.POST })
    public String submitBidOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();

        serviceOrder.setModified_at(new Date());
        serviceOrder.setModified_by(user_id);
        
        serviceOrder.setStatus_id("20"); //set to bided.

        setSeletetedServiceSchedule(serviceOrder);

        service.update(serviceOrder);
        Integer order_id = serviceOrder.getOrder_id();
        String message = String.format("成功Bid订单ID为：%s的订单。", order_id);

        model.addAttribute("message", message);
        return "dialog/success";
    }

    @RequestMapping(value = "/serviceorder/buyercancel", method = { RequestMethod.GET, RequestMethod.POST })
    public String cancelBuyerServiceOrder(@ModelAttribute("serviceOrderQuery") ServiceOrderDoubleListSearchType serviceOrderQuery, Model model,
            HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();
        
        Integer order_id = serviceOrderQuery.getCancelOrderId();
        
        if(isBefor12Hours(order_id)){ 
            cancelOrder(order_id, loginUserId);
            serviceOrderQuery.setBackendMessage(String.format("成功取消订单ID为：%s的订单。", order_id));
        }else{
            serviceOrderQuery.setBackendMessage(String.format("当前时间距离选定订单服务时间不足12小时， 不能取消订单ID为：%s的订单。", order_id));
        }
        
        serviceOrderQuery.setLoginUserId(loginUserId);
        queryServiceOrderList(serviceOrderQuery, model, false);

        return "/serviceorder/buyermanage";
    }
    
    @RequestMapping(value = "/serviceorder/complete", method = { RequestMethod.GET})
    public String completeBuyerServiceOrder(@ModelAttribute("serviceOrderQuery") Service_Order order, Model model,
    		HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int loginUserId = user.getUserid();
        
        order.setModified_by(loginUserId);
        order.setModified_at(new Date());
        order.setStatus_id("40"); //Complete order.       
        service.updateOrderStatus(order);       
        
        String message = "完成订单操作成功。";

        model.addAttribute("message", message);
        return "dialog/success"; 
    }


    @RequestMapping(value = "/serviceorder/vieworderrate", method = { RequestMethod.GET})
    public String viewOrderRate(@ModelAttribute("orderRate") Service_Order_Rate orderRate, Model model, HttpServletRequest request) throws Exception {

        orderRate = service.getOrderRate(orderRate);

        if (orderRate == null) {
            orderRate = new Service_Order_Rate();
        }

        orderRate.setOperate("view");

        model.addAttribute("orderRate", orderRate);

        return "/serviceorder/orderrateform";
    }
    
    @RequestMapping(value = "/serviceorder/createorderrate", method = { RequestMethod.GET })
    public String openOrderRate(@ModelAttribute("orderRate") Service_Order_Rate orderRate, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();
        
    	orderRate.setOperate("add");
    	orderRate.setCreated_by(user_id);

        model.addAttribute("orderRate", orderRate);

        return "/serviceorder/orderrateform";
    }
    
    @RequestMapping(value = "/serviceorder/createorderrate", method = { RequestMethod.POST })
    public String submitOrderRate(@ModelAttribute("orderRate") Service_Order_Rate orderRate, Model model, HttpServletRequest request) throws Exception {

    	LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();
        
        orderRate.setCreated_at(new Date());
        orderRate.setRated_at(new Date());        
        service.insertOrderRate(orderRate);
        
        Service_Order order = new Service_Order();
        order.setModified_by(user_id);
        order.setModified_at(new Date());
        order.setOrder_id(orderRate.getOrder_id());
        order.setStatus_id("60");        
        service.updateOrderStatus(order);         

        String message = "创建评价成功。";

        model.addAttribute("message", message);
        return "dialog/success";       
    }

    @RequestMapping(value = "/serviceorder/create", method = RequestMethod.GET)
    public String openCreateServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();

        Service_Order serviceOrder = new Service_Order();
        serviceOrder.setCreated_by(user_id);
        serviceOrder.setUser_id(user_id);
        serviceOrder.setOperate("add");
        
        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);

        Integer service_id = serviceOrderQuery.getService_id();

        if (service_id != null && service_id > 0) {
            serviceOrder.setService_id(service_id);
            serviceOrder = fillOrderWithProduct(serviceOrder, request, true);
        }

        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/createform";
    }

    @RequestMapping(value = "/serviceorder/copy", method = RequestMethod.GET)
    public String openCopyServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request)
            throws Exception {
        LoggedInUser user = getLoggedInUser();
        int user_id = user.getUserid();

        Service_Order serviceOrderToCopy = (Service_Order) service.queryOne(serviceOrder);

        // When copying, reset below attributes to initial values.
        serviceOrderToCopy.setOperate("copy");
        serviceOrderToCopy.setOrder_id(null); // will be created by DB when submitting.
        serviceOrderToCopy.setCreated_by(user_id); // will be created by current login user.
        serviceOrderToCopy.setCreated_at(null);//will be created when submitting.
        serviceOrderToCopy.setModified_at(null); // not modified yet
        serviceOrderToCopy.setModified_by(0); // not modified yet
        serviceOrderToCopy.setStatus_id(null);
        createTreeItemServcieSchedulList(serviceOrderToCopy);
        setSelectedServiceTimeIndex(serviceOrder);

        model.addAttribute("serviceOrder", serviceOrderToCopy);

        return "/serviceorder/copyform";
    }

/*    @RequestMapping(value = "/serviceorder/update", method = RequestMethod.GET)
    public String openUpdateServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        Service_Order serviceOrder = (Service_Order) service.queryOne(serviceOrderQuery);

        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);

        serviceOrder.setOperate("update");
        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/form";
    }*/
    
    @RequestMapping(value = "/serviceorder/view", method = RequestMethod.GET)
    public String openViewServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        Service_Order serviceOrder = (Service_Order) service.queryOne(serviceOrderQuery);

        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);

        serviceOrder.setOperate("view");
        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/viewform";
    }

    @RequestMapping(value = "/serviceorder/create", method = RequestMethod.POST)
    public String submitCreateOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        serviceOrder.setCreated_at(new Date());
        serviceOrder.setStatus_id("10"); // a new order is pending status.
        setSeletetedServiceSchedule(serviceOrder);

        service.add(serviceOrder);

        String message = "创建订单成功。";

        model.addAttribute("message", message);
        return "dialog/success";
    }

    @RequestMapping(value = "/serviceorder/copy", method = RequestMethod.POST)
    public String submitCopyOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        serviceOrder.setCreated_at(new Date());
        serviceOrder.setStatus_id("10");// this is the initial value.
        setSeletetedServiceSchedule(serviceOrder);

        service.add(serviceOrder);

        String message = "复制订单成功。";

        model.addAttribute("message", message);
        return "dialog/success";
    }

/*    @RequestMapping(value = "/serviceorder/update", method = RequestMethod.POST)
    public String submitUpdateOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        serviceOrder.setModified_at(new Date());
        serviceOrder.setModified_by(user_id);

        setSeletetedServiceSchedule(serviceOrder);

        service.update(serviceOrder);
        Integer order_id = serviceOrder.getOrder_id();
        String message = "修改Service Product成功。  <a class=\"btn btn-sm btn-success\" href=\"update.html?order_id=" + order_id + "\">继续修改</a>";

        model.addAttribute("message", message);
        return "dialog/success";
    }*/

    @RequestMapping(value = "/serviceorder/delete/{order_id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable int order_id, Model model, HttpServletRequest request) throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Service_Order serviceOrder = new Service_Order();
        serviceOrder.setOrder_id(order_id);;
        serviceOrder.setModified_at(new Date());
        serviceOrder.setModified_by(user_id);
        serviceOrder.setIs_deleted(true);

        service.delete(serviceOrder);
        String message = "删除ServiceOrder成功";
        model.addAttribute("message", message);
        return "dialog/success";
    }

    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/serviceorder/getUserAndCMember", method = RequestMethod.GET)
    public @ResponseBody String getUserAndCMember(Model model, HttpServletRequest request) throws Exception {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = new User();
        user.setId(user_id);

        user = (User) userService.queryOne(user);

        Service_Order order = new Service_Order();
        order.setFirst_name(user.getFirstName());
        order.setLast_name(user.getLastName());
        order.setEmail(user.getEmail());
        order.setCell(user.getPhone());

        List<Address> addressList = addressService.queryAddressList(user_id);

        if (addressList.size() > 0) {
            Address addr = addressList.get(0);
            order.setAddress(addr.getAddress());
            order.setCity(addr.getCity_code());
            order.setPostcode(addr.getPostal_code());
        }

        Membership membership = new Membership();
        membership.setUser_id(user_id);
        membership.setSearchRecommended(false);
        membership.setType_code("C");

        List<Membership> memberList = membershipService.queryList(membership);
        if (memberList.size() > 0) {
            Membership member = memberList.get(0);
            order.setCustomer_member_id(member.getMember_id());
        }

        Gson gson = new Gson();
        String json = gson.toJson(order);
        return json;
    }

    @RequestMapping(value = "/serviceorder/getServiceProduct", method = RequestMethod.GET)
    public @ResponseBody String getServiceProduct(Model model, HttpServletRequest request) throws Exception {
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        int unit_quantity = Integer.parseInt(request.getParameter("unit_quantity"));

        Service_Order order = new Service_Order();
        order.setUnit_quantity(unit_quantity);
        order.setService_id(service_id);

        order = fillOrderWithProduct(order, request, true);

        Gson gson = new Gson();
        String json = gson.toJson(order);
        return json;
    }
    
    @RequestMapping(value = "/serviceorder/getServiceProductByPrices", method = RequestMethod.GET)
    public @ResponseBody String getServiceProductByPrices(Model model, HttpServletRequest request) throws Exception {
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        int unit_quantity = Integer.parseInt(request.getParameter("unit_quantity"));
        float basic_price = Float.parseFloat(request.getParameter("basic_price"));
        float unit_price = Float.parseFloat(request.getParameter("unit_price"));

        Service_Order order = new Service_Order();
        order.setBasic_price(basic_price);
        order.setUnit_price(unit_price);
        order.setService_id(service_id);
        order.setUnit_quantity(unit_quantity);

        order = fillOrderWithProduct(order, request, false);

        Gson gson = new Gson();
        String json = gson.toJson(order);
        return json;
    }

    private void queryServiceOrderList(ServiceOrderDoubleListSearchType serviceOrderQuery, Model model, boolean isQuerySupplier) {

        Service_Order unFinishedOrder = serviceOrderQuery.getUnFinishedOrder();
        unFinishedOrder.setOrderByClause("a.order_id desc"); // Latest created will be displayed first.
        unFinishedOrder.setIsFinished(false);
        unFinishedOrder.setOrder_id(serviceOrderQuery.getOrder_id());
        unFinishedOrder.setService_title(serviceOrderQuery.getService_title());
        unFinishedOrder.setUser_id(serviceOrderQuery.getLoginUserId());
        
        int rownum1 = service.queryCount(unFinishedOrder);
        unFinishedOrder.getPagination().setRowCount(rownum1);
        unFinishedOrder.getPagination().setPageSize(10);
        int pagecount1 = unFinishedOrder.getPagination().getPageCount();
        int currentpage1 = unFinishedOrder.getPagination().getCurrentPage();
        List<?> pageNumList1 = getPageNumList(currentpage1, pagecount1);
        model.addAttribute("pageNumList1", pageNumList1);

        List<?> serviceOrderList1;
        if(isQuerySupplier){
            serviceOrderList1 = service.querySupplierOrderListByUserId(unFinishedOrder);
        }else{
            serviceOrderList1 = service.queryList(unFinishedOrder);
        }            
        
        model.addAttribute("unFinishedOrderList", serviceOrderList1);

        Service_Order finishedOrder = serviceOrderQuery.getFinishedOrder();
        finishedOrder.setOrderByClause("a.order_id desc"); // Latest created will be displayed first.
        finishedOrder.setIsFinished(true);
        finishedOrder.setOrder_id(serviceOrderQuery.getOrder_id());
        finishedOrder.setService_title(serviceOrderQuery.getService_title());
        finishedOrder.setUser_id(serviceOrderQuery.getLoginUserId());
        
        int rownum2 = service.queryCount(finishedOrder);
        finishedOrder.getPagination().setRowCount(rownum2);
        finishedOrder.getPagination().setPageSize(10);
        int pagecount2 = finishedOrder.getPagination().getPageCount();
        int currentpage2 = finishedOrder.getPagination().getCurrentPage();
        List<?> pageNumList2 = getPageNumList(currentpage2, pagecount2);
        model.addAttribute("pageNumList2", pageNumList2);

        List<?> serviceOrderList2;
        if(isQuerySupplier){
            serviceOrderList2 = service.querySupplierOrderListByUserId(finishedOrder);
        }else{
            serviceOrderList2 = service.queryList(finishedOrder);
        }            
        model.addAttribute("finishedOrderList", serviceOrderList2);
    }

    private Service_Order fillOrderWithProduct(Service_Order order, HttpServletRequest request, boolean fillPrices) {

        ServiceProduct serviceProduct = new ServiceProduct();
        serviceProduct.setService_id(order.getService_id());

        serviceProduct = (ServiceProduct) serviceProductService.queryOne(serviceProduct);

        order.setBusiness_member_id(serviceProduct.getMember_id());
        order.setCover_img(serviceProduct.getCover_img());
        order.setService_title(serviceProduct.getService_title());
        if(fillPrices){
            order.setBasic_price(serviceProduct.getBasic_price());
            order.setUnit_price(serviceProduct.getUnit_price());
        }
        order.setUnit_id(serviceProduct.getUnit_id());
        order.setTax_included(serviceProduct.isTax_included());
        order.setGst_rate(serviceProduct.getGst_rate());
        order.setPst_rate(serviceProduct.getPst_rate());
        order.setWarrant(serviceProduct.getWarrant());

        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(serviceProduct.getMember_id());
        businessProfile = (Business_Profile) membershipDao.queryBusinessProfileByCondition(businessProfile);
        order.setBusiness_name(businessProfile.getBusiness_name());

        order.getPriceBeforeTax();
        order.getPriceTotal();
        order.getPstTax();
        order.getGstTax();
        order.getBasePrice();
        order.getUnitPrice();

        @SuppressWarnings("rawtypes")
		TreeMap serviceUnitMap = (TreeMap) getAppScopeObj(request, "serviceUnitMap");
        if (serviceUnitMap != null) {
            order.setUnit_name((String) serviceUnitMap.get(serviceProduct.getUnit_id()));
        }
        return order;
    }

    private void setSelectedServiceTimeIndex(Service_Order order) {
        List<ServiceSchedule> serviceScheduleList = order.getServiceScheduleList();
        if (serviceScheduleList != null) {
            int length = serviceScheduleList.size();
            for (int i = 0; i < length; i++) {
                if (serviceScheduleList.get(i).getSelected() != null && serviceScheduleList.get(i).getSelected() == true) {
                    order.setSelectedServiceTimeIndex(i);
                    break;
                }
            }
        }
    }

    private void setSeletetedServiceSchedule(Service_Order serviceOrder) {
        Integer selectedServiceTimeIndex = serviceOrder.getSelectedServiceTimeIndex();
        List<ServiceSchedule> list = serviceOrder.getServiceScheduleList();
        for (int i = 0; i < list.size(); i++) {
            ServiceSchedule schedule = list.get(i);
            if (i == selectedServiceTimeIndex) {
                schedule.setSelected(true);
            } else {
                schedule.setSelected(false);
            }
        }
    }

    // Business requirement is provide three service schedules for an order.
    private void createTreeItemServcieSchedulList(Service_Order serviceOrder) {
        List<ServiceSchedule> list = serviceOrder.getServiceScheduleList();
        if (list == null) {
            list = new ArrayList<ServiceSchedule>();
        }
        int length = 3 - list.size();
        for (int i = 0; i < length; i++) {
            ServiceSchedule schedule = new ServiceSchedule();
            list.add(schedule);
        }
        serviceOrder.setServiceScheduleList(list);
    }
    
    private void cancelOrder(Integer order_id, int loginUserId) {
        Service_Order order = new Service_Order();
        order.setOrder_id(order_id);
        order.setModified_by(loginUserId);
        order.setModified_at(new Date());
        order.setStatus_id("50"); //set cancel status.
        service.updateOrderStatus(order);        
    }
    
    private boolean isBefor12Hours(Integer order_id) {        
        Service_Order order = new Service_Order();
        order.setOrder_id(order_id);
        
        Service_Order orderRtn = (Service_Order) service.queryOne(order);
        
        if(orderRtn.getStatus_id().equals("10") || orderRtn.getStatus_id().equals("30")){
            return true;
        }else if(orderRtn.getStatus_id().equals("20")){
            List<ServiceSchedule> list = orderRtn.getServiceScheduleList();
            Date earliestServiceDate = new Date(0);
            Date current = new Date();
            for(ServiceSchedule schedule : list){
                Date serviceDate = schedule.getServicetime();                
                if(schedule.getSelected().equals(true)){                    
                    if((serviceDate.getTime() - current.getTime()) > 43200000){
                        return true;                    
                    }
                }
                if(earliestServiceDate.getTime() == 0 ||  earliestServiceDate.getTime() > serviceDate.getTime()){
                    earliestServiceDate = serviceDate;
                }                
            }
            
            if((earliestServiceDate.getTime() - current.getTime()) > 43200000){
                return true;                    
            }
        }     
                
        return false;
    }
}
