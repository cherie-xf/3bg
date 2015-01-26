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
public class ServiceOrderMngController extends AbstractController {
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

    @RequestMapping(value = "/serviceorder/manage", method = { RequestMethod.GET, RequestMethod.POST })
    public String searchServiceOrderList(@ModelAttribute("serviceOrderQuery") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {

        queryServiceOrderList(serviceOrderQuery, model);

        return "/serviceorder/manage";
    }
    
    @RequestMapping(value = "/serviceorder/vieworderrate", method = { RequestMethod.GET, RequestMethod.POST })
    public String viewOorderRate(@ModelAttribute("orderRate") Service_Order_Rate orderRate, Model model, HttpServletRequest request)
            throws Exception {
        
        orderRate = service.getOrderRate(orderRate);  
        
        if(orderRate == null){
            orderRate = new Service_Order_Rate();
        }
        
        orderRate.setOperate("view");
        
        model.addAttribute("orderRate", orderRate);

        return "/serviceorder/orderrateform";
    }

    @RequestMapping(value = "/serviceorder/create", method = RequestMethod.GET)
    public String openCreateServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        LoggedInUser user = getLoggedInUser();
        System.out.println("id=" + user.getUserid() + " email=" + user.getEmail());
        int user_id = user.getUserid();

        Service_Order serviceOrder = new Service_Order();
        serviceOrder.setCreated_by(user_id);
        serviceOrder.setOperate("add");
        
        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);

        Integer service_id = serviceOrderQuery.getService_id();

        if (service_id != null && service_id > 0) {
            serviceOrder.setService_id(service_id);
            serviceOrder = fillOrderWithProduct(serviceOrder, request);
        }

        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/form";
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
        serviceOrderToCopy.setModified_at(null); // not modified yet
        serviceOrderToCopy.setModified_by(0); // not modified yet
        serviceOrderToCopy.setStatus_id("10");// this is the initial value.        
        createTreeItemServcieSchedulList(serviceOrderToCopy);
        setSelectedServiceTimeIndex(serviceOrder);
        
        model.addAttribute("serviceOrder", serviceOrderToCopy);

        return "/serviceorder/form";
    }

    @RequestMapping(value = "/serviceorder/update", method = RequestMethod.GET)
    public String openUpdateServiceOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrderQuery, Model model, HttpServletRequest request)
            throws Exception {
        Service_Order serviceOrder = (Service_Order) service.queryOne(serviceOrderQuery);
        
        createTreeItemServcieSchedulList(serviceOrder);
        setSelectedServiceTimeIndex(serviceOrder);
        
        serviceOrder.setOperate("update");
        model.addAttribute("serviceOrder", serviceOrder);
        return "/serviceorder/form";
    }

    @RequestMapping(value = "/serviceorder/create", method = RequestMethod.POST)
    public String submitCreateOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        serviceOrder.setCreated_at(new Date());        
        setSeletetedServiceSchedule(serviceOrder);
        
        service.add(serviceOrder);
        
        String message = "创建Service Order成功。  <a class=\"btn btn-sm btn-success\" href=\"create.html\">继续创建</a>";

        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/serviceorder/copy", method = RequestMethod.POST)
    public String submitCopyOrderForm(@ModelAttribute("serviceOrder") Service_Order serviceOrder, Model model, HttpServletRequest request) throws Exception {
        serviceOrder.setCreated_at(new Date());
        setSeletetedServiceSchedule(serviceOrder);
        
        service.add(serviceOrder);       
        
        String message = "复制Service Order成功。";

        model.addAttribute("message", message);
        return "success";
    }

    @RequestMapping(value = "/serviceorder/update", method = RequestMethod.POST)
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
        return "success";
    }

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
        return "success";
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
        
        order = fillOrderWithProduct(order, request);       

        Gson gson = new Gson();
        String json = gson.toJson(order);
        return json;
    }

    private void queryServiceOrderList(Service_Order serviceOrderQuery, Model model) {
        int rownum = service.queryCount(serviceOrderQuery);
        serviceOrderQuery.getPagination().setRowCount(rownum);
        serviceOrderQuery.getPagination().setPageSize(10);
        int pagecount = serviceOrderQuery.getPagination().getPageCount();
        int currentpage = serviceOrderQuery.getPagination().getCurrentPage();
        List<?> pageNumList = getPageNumList(currentpage, pagecount);
        model.addAttribute("pageNumList", pageNumList);

        serviceOrderQuery.setOrderByClause("a.order_id desc"); // Latest created will be displayed first.
        List<?> serviceOrderList = service.queryList(serviceOrderQuery);
        model.addAttribute("serviceOrderList", serviceOrderList);
    }

    private Service_Order fillOrderWithProduct(Service_Order order, HttpServletRequest request) {

        ServiceProduct serviceProduct = new ServiceProduct();
        serviceProduct.setService_id(order.getService_id());

        serviceProduct = (ServiceProduct) serviceProductService.queryOne(serviceProduct);

        order.setBusiness_member_id(serviceProduct.getMember_id());
        order.setCover_img(serviceProduct.getCover_img());
        order.setService_title(serviceProduct.getService_title());
        order.setBasic_price(serviceProduct.getBasic_price());
        order.setUnit_price(serviceProduct.getUnit_price());
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

        TreeMap serviceUnitMap = (TreeMap) getAppScopeObj(request, "serviceUnitMap");
        if (serviceUnitMap != null) {
            order.setUnit_name((String) serviceUnitMap.get(serviceProduct.getUnit_id()));
        }
        return order;
    }
    
    private void setSelectedServiceTimeIndex(Service_Order order){
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
    
    private void setSeletetedServiceSchedule(Service_Order serviceOrder){
        Integer selectedServiceTimeIndex =  serviceOrder.getSelectedServiceTimeIndex();
        List<ServiceSchedule> list = serviceOrder.getServiceScheduleList();
        for(int i = 0; i< list.size(); i++){
            ServiceSchedule schedule = list.get(i);
            if(i == selectedServiceTimeIndex){
                schedule.setSelected(true);
            }else{
                schedule.setSelected(false);
            }
        }
    }
    
    //Business requirement is provide three service schedules for an order.
    private void createTreeItemServcieSchedulList(Service_Order serviceOrder) {
        List<ServiceSchedule> list = serviceOrder.getServiceScheduleList();
        if(list == null){
            list = new ArrayList<ServiceSchedule>();
        }
        int length = 3 - list.size();
        for(int i= 0; i < length; i++){
            ServiceSchedule schedule = new ServiceSchedule();
            list.add(schedule);
        }
        serviceOrder.setServiceScheduleList(list);      
    }
}
