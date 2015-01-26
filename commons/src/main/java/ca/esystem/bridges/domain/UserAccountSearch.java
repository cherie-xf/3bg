package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

public class UserAccountSearch extends BaseForm {

    private static final long serialVersionUID = 5673465085642167468L;

    private String            email;
    private String            phone;
    private String            name;
    private Integer           status;
    private Integer           user_type;

    public UserAccountSearch() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

}
