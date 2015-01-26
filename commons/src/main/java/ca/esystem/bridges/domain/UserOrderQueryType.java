package ca.esystem.bridges.domain;

import java.io.Serializable;

public class UserOrderQueryType implements Serializable {
    private static final long serialVersionUID = 7527779770353420098L;

    Integer                   id;
    Integer                   order_id;
    String                    service_title;
    Integer                   orderFinishedMonths;

    Service_Order             unFinishedOrder  = new Service_Order();

    Service_Order             finishedOrder    = new Service_Order();

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getOrderFinishedMonths() {
        return orderFinishedMonths;
    }

    public void setOrderFinishedMonths(Integer orderFinishedMonths) {
        this.orderFinishedMonths = orderFinishedMonths;
    }
}
