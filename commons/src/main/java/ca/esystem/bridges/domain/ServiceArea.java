package ca.esystem.bridges.domain;

import java.io.Serializable;

/**
 * 
 * @author Lei
 *
 */
public class ServiceArea implements Serializable {
    private static final long serialVersionUID = -3743489523376064546L;

    private int               service_id;
    private String            city_code;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int i) {
        this.service_id = i;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
}
