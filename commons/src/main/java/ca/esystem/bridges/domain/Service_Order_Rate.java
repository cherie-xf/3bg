package ca.esystem.bridges.domain;

import java.util.Date;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry, Lei
 *
 */

public class Service_Order_Rate extends BaseForm {
    private static final long serialVersionUID = 2313242321195155761L;
    
    private int    id;
    private int    order_id;
    private float  attitude;
    private float  time;
    private float  price;
    private float  quality;
    private String comment;
    private Date   rated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public float getAttitude() {
        return attitude;
    }

    public void setAttitude(float attitude) {
        this.attitude = attitude;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRated_at() {
        return rated_at;
    }

    public void setRated_at(Date rated_at) {
        this.rated_at = rated_at;
    }
}
