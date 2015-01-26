package ca.esystem.bridges.domain;

import java.io.Serializable;

import ca.esystem.framework.domain.BaseForm;

public class UserInfo extends BaseForm implements Serializable {

    private static final long serialVersionUID = 637482543679448058L;

    private int               id;
    private String            email;
    private String            phone;
    private String            firstName;
    private String            lastName;
    private String            preferredName;

    public UserInfo() {

    }

    public UserInfo(String email, String phone, String firstName, String lastName, String preferredName) {
        super();
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredName = preferredName;
    }

    public UserInfo(int id, String email, String phone, String firstName, String lastName, String preferredName) {
        super();
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredName = preferredName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

}
