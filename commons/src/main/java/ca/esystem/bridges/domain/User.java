package ca.esystem.bridges.domain;

import java.util.Date;

public class User extends UserInfo {

    private static final long serialVersionUID   = -6460537526820411598L;

    private String            password;
    private String            password_confirmation;
    private boolean           enabled            = true;
    private Date              accountExpired     = null;
    private boolean           accountLocked      = false;
    private boolean           credentialsExpired = false;
    private boolean           deleted            = false;
    private int               status             = 1;
    private Integer           user_type;
    private String            membertype         = "C";                  // only for signup form

    public User() {

    }

    public User(String email, String phone, String firstName, String lastName, String password) {
        super(-1, email, phone, firstName, lastName, null);
        this.password = password;
    }

    public User(User user) {
        super(user.getId(), user.getEmail(), user.getPhone(), user.getFirstName(), user.getLastName(), user.getPreferredName());
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.accountExpired = user.getAccountExpired();
        this.accountLocked = user.isAccountLocked();
        this.credentialsExpired = user.isCredentialsExpired();
        this.deleted = user.isDeleted();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Date accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Date getCreatedAt() {
        return super.getCreated_at();
    }

    public void setCreatedAt(Date createdAt) {
        super.setCreated_at(createdAt);
    }

    public int getCreatedBy() {
        return super.getCreated_by();
    }

    public void setCreatedBy(int createdBy) {
        super.setCreated_by(createdBy);
    }

    public Date getModifiedAt() {
        return super.getModified_at();
    }

    public void setModifiedAt(Date modifiedAt) {
        super.setModified_at(modifiedAt);
    }

    public int getModifiedBy() {
        return super.getModified_by();
    }

    public void setModifiedBy(int modifiedBy) {
        super.setModified_by(modifiedBy);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

}
