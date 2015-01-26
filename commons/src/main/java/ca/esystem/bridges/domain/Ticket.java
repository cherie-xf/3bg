package ca.esystem.bridges.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry
 *
 */
public class Ticket extends BaseForm {
    private int          id;
    private String       title;
    private String       description;
    private int          user_id;
    private String       status_code;
    private String       type_code;
    private int          source_id;
    private int          processor_id;
    private String       phone;
    private String       email;
    private String       first_name;
    private String       last_name;
    private String       representative;
    private int          close_by;
    private Date         close_at;
    private List<String> statusCodeList = new ArrayList<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getProcessor_id() {
        return processor_id;
    }

    public void setProcessor_id(int processor_id) {
        this.processor_id = processor_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public int getClose_by() {
        return close_by;
    }

    public void setClose_by(int close_by) {
        this.close_by = close_by;
    }

    public Date getClose_at() {
        return close_at;
    }

    public void setClose_at(Date close_at) {
        this.close_at = close_at;
    }

    public List<String> getStatusCodeList() {
        return statusCodeList;
    }

    public void setStatusCodeList(List<String> statusCodeList) {
        this.statusCodeList = statusCodeList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
