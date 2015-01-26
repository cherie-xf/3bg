package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry
 *
 */
public class Contact extends BaseForm {
    private int    id;
    private String type_code;
    private String type_class;
    private String contact_value;
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getType_class() {
        return type_class;
    }

    public void setType_class(String type_class) {
        this.type_class = type_class;
    }
    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getContact_value() {
        return contact_value;
    }

    public void setContact_value(String contact_value) {
        this.contact_value = contact_value;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
