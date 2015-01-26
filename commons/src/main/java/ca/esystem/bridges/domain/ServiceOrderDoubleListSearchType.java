package ca.esystem.bridges.domain;

import java.io.Serializable;

/**
 * 
 * @author Lei Han
 *
 */
public class ServiceOrderDoubleListSearchType implements Serializable {

    private static final long serialVersionUID = 4337306286851981344L;

    private Integer           loginUserId;
    private Integer           order_id;
    private String            service_title;
    private Service_Order     unFinishedOrder  = new Service_Order();
    private Service_Order     finishedOrder    = new Service_Order();
    private String            backendMessage;
    private Integer           cancelOrderId;

    public String getBackendMessage() {
        return backendMessage;
    }

    public void setBackendMessage(String backendMessage) {
        this.backendMessage = backendMessage;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public Service_Order getUnFinishedOrder() {
        return unFinishedOrder;
    }

    public void setUnFinishedOrder(Service_Order unFinishedOrder) {
        this.unFinishedOrder = unFinishedOrder;
    }

    public Service_Order getFinishedOrder() {
        return finishedOrder;
    }

    public void setFinishedOrder(Service_Order finishedOrder) {
        this.finishedOrder = finishedOrder;
    }

    public Integer getCancelOrderId() {
        return cancelOrderId;
    }

    public void setCancelOrderId(Integer cancelOrderId) {
        this.cancelOrderId = cancelOrderId;
    }

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

}
