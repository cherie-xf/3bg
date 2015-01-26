package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

public class Signup extends BaseForm {

    private static final long serialVersionUID = -3363448384335125129L;
    
    private String email;
    private String password;
    private String verifyPassword;
    
    public Signup() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }    
}
