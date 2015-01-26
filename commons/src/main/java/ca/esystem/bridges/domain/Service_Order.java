package ca.esystem.bridges.domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry, Lei
 *
 */
public class Service_Order extends BaseForm {
    private static final long     serialVersionUID         = 4207879999784601263L;

    private Integer               order_id;
    private String                requirement;
    private String                status_id;
    private String                sub_status_id;
    private Date                  processed_at;

    // For status update
    private String                new_status_id;
    private String                new_sub_status_id;
    
    // Copied data from service_product and business_profile.
    private Integer               service_id;
    private String                service_title;
    private String                cover_img;
    private Boolean               face_negotiable;
    private float                 basic_price;
    private float                 unit_price;
    private String                unit_id;
    private int                   unit_quantity;
    private Boolean               tax_included;
    private float                 gst_rate;
    private float                 pst_rate;
    private String                warrant;
    private String                business_member_id;
    private String                business_name;

    // Copied data from user and address.
    private int                   user_id;
    private String                customer_member_id;
    private String                first_name;
    private String                last_name;
    private String                email;
    private String                cell;
    private String                address;
    private String                city;
    private String                postcode;
    private List<ServiceSchedule> serviceScheduleList      = new ArrayList<ServiceSchedule>();
    private Integer               selectedServiceTimeIndex = 3;                               // Indicate which item in serviceScheduleList is selected. 3 is
                                                                                               // selecting none

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                  orderDateTimeFrom;                                          // Query condition

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                  orderDateTimeTo;                                            // Query condition

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                  serviceDateFrom;                                            // Query condition

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                  serviceDateTo;                                              // Query condition

    private Boolean               isFinished;                                                 // Query condition

    // Below attributes are for display only.
    private String                status;                                                     // Display only, will not be saved.
    private String                orderDate;                                                  // Display only, will not be saved.
    private String                orderTime;                                                  // Display only, will not be saved.
    private String                priceBeforeTax;
    private String                priceTotal;
    private String                gstTax;                                                     // Display only, will not be saved.
    private String                pstTax;                                                     // Display only, will not be saved.
    private String                unit_name;                                                  // Display only, will not be saved.
    private String                basePrice;                                                  // Display only, will not be saved.
    private String                unitPrice;                                                  // Display only, will not be saved.

    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    private Date                  servicetime;                                                // Display only, will not be saved.
    private String                serviceDateStr;                                             // Display only, will not be saved.
    private String                serviceTimeStr;                                             // Display only, will not be saved.

    public Date getServicetime() {
        return servicetime;
    }

    public void setServicetime(Date servicetime) {
        this.servicetime = servicetime;
    }

    public String getServiceDateStr() {
        if (servicetime != null) {
            SimpleDateFormat formator = new SimpleDateFormat("MM/dd/yyyy");
            serviceDateStr = formator.format(servicetime);
        }
        return serviceDateStr;
    }

    public String getServiceTimeStr() {
        if (servicetime != null) {
            SimpleDateFormat formator = new SimpleDateFormat("h:mm a");
            serviceTimeStr = formator.format(servicetime);
        }
        return serviceTimeStr;
    }

    public Date getServiceDateFrom() {
        return serviceDateFrom;
    }

    public void setServiceDateFrom(Date serviceDateFrom) {
        this.serviceDateFrom = serviceDateFrom;
    }

    public Date getServiceDateTo() {
        return serviceDateTo;
    }

    public void setServiceDateTo(Date serviceDateTo) {
        this.serviceDateTo = serviceDateTo;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getBasePrice() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        basePrice = fmt.format(basic_price);
        return basePrice;
    }

    public String getUnitPrice() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        unitPrice = fmt.format(unit_price);
        return unitPrice;
    }

    public String getGstTax() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        gstTax = fmt.format((basic_price + unit_price * unit_quantity) * gst_rate);
        return gstTax;
    }

    public String getPstTax() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        pstTax = fmt.format((basic_price + unit_price * unit_quantity) * pst_rate);
        return pstTax;
    }

    public String getOrderDate() {
        if (getCreated_at() == null) return null;
        SimpleDateFormat formator = new SimpleDateFormat("MM/dd/yyyy");
        orderDate = formator.format(getCreated_at());

        return orderDate;
    }

    public String getOrderTime() {
        if (getCreated_at() == null) return null;        
        SimpleDateFormat formator = new SimpleDateFormat("h:mm a");
        orderTime = formator.format(getCreated_at());
        return orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getBusiness_member_id() {
        return business_member_id;
    }

    public void setBusiness_member_id(String business_member_id) {
        this.business_member_id = business_member_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCustomer_member_id() {
        return customer_member_id;
    }

    public void setCustomer_member_id(String customer_member_id) {
        this.customer_member_id = customer_member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getSub_status_id() {
        return sub_status_id;
    }

    public void setSub_status_id(String sub_status_id) {
        this.sub_status_id = sub_status_id;
    }

    public Date getProcessed_at() {
        return processed_at;
    }

    public void setProcessed_at(Date processed_at) {
        this.processed_at = processed_at;
    }

    public String getNew_status_id() {
        return new_status_id;
    }

    public void setNew_status_id(String new_status_id) {
        this.new_status_id = new_status_id;
    }

    public String getNew_sub_status_id() {
        return new_sub_status_id;
    }

    public void setNew_sub_status_id(String new_sub_status_id) {
        this.new_sub_status_id = new_sub_status_id;
    }

    public Date getOrderDateTimeFrom() {
        return orderDateTimeFrom;
    }

    public void setOrderDateTimeFrom(Date orderDateTimeFrom) {
        this.orderDateTimeFrom = orderDateTimeFrom;
    }

    public Date getOrderDateTimeTo() {
        if (orderDateTimeTo != null) {
            orderDateTimeTo = DateUtils.setHours(orderDateTimeTo, 23);
            orderDateTimeTo = DateUtils.setMinutes(orderDateTimeTo, 59);
            orderDateTimeTo = DateUtils.setSeconds(orderDateTimeTo, 59);
        }
        return orderDateTimeTo;
    }

    public void setOrderDateTimeTo(Date orderDateTimeTo) {
        this.orderDateTimeTo = orderDateTimeTo;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public Boolean getFace_negotiable() {
        return face_negotiable;
    }

    public void setFace_negotiable(Boolean face_negotiable) {
        this.face_negotiable = face_negotiable;
    }

    public float getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(float basic_price) {
        this.basic_price = basic_price;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public Boolean getTax_included() {
        return tax_included;
    }

    public void setTax_included(Boolean tax_included) {
        this.tax_included = tax_included;
    }

    public float getGst_rate() {
        return gst_rate;
    }

    public void setGst_rate(float gst_rate) {
        this.gst_rate = gst_rate;
    }

    public float getPst_rate() {
        return pst_rate;
    }

    public void setPst_rate(float pst_rate) {
        this.pst_rate = pst_rate;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public List<ServiceSchedule> getServiceScheduleList() {
        return serviceScheduleList;
    }

    public void setServiceScheduleList(List<ServiceSchedule> serviceScheduleList) {
        this.serviceScheduleList = serviceScheduleList;
    }

    public int getUnit_quantity() {
        return unit_quantity;
    }

    public void setUnit_quantity(int unit_quantity) {
        this.unit_quantity = unit_quantity;
    }

    public String getPriceBeforeTax() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        priceBeforeTax = fmt.format(basic_price + unit_price * unit_quantity);

        return priceBeforeTax;
    }

    public void setPriceBeforeTax(String priceBeforeTax) {
    }

    public String getPriceTotal() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        if (tax_included != null && tax_included) {
            priceTotal = fmt.format(basic_price + unit_price * unit_quantity);
        } else {
            double result = (basic_price + unit_price * unit_quantity) * (1 + gst_rate + pst_rate);
            priceTotal = fmt.format(result);
        }
        return priceTotal;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public Integer getSelectedServiceTimeIndex() {
        return selectedServiceTimeIndex;
    }

    public void setSelectedServiceTimeIndex(Integer selectedServiceTimeIndex) {
        this.selectedServiceTimeIndex = selectedServiceTimeIndex;
    }
}
