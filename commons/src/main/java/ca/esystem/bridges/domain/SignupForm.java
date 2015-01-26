package ca.esystem.bridges.domain;

import java.io.Serializable;

public class SignupForm implements Serializable {
    private static final long serialVersionUID = 6374829852145448058L;
    
    private String email;
    private User user;
    private UserProfile userProfile;
    private Address address;
    private Contact contact;
    private Membership membership;
    private Business_Profile businessProfile;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SignupForm(){
        user = new User();
        userProfile = new UserProfile();
        address = new Address();
        contact = new Contact();
        contact.setType_code("30WC");
        membership = new Membership();
        businessProfile = new Business_Profile();
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserProfile getUserProfile() {
        return userProfile;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Membership getMembership() {
        return membership;
    }
    public void setMembership(Membership membership) {
        this.membership = membership;
    }
    public Business_Profile getBusinessProfile() {
        return businessProfile;
    }
    public void setBusinessProfile(Business_Profile businessProfile) {
        this.businessProfile = businessProfile;
    }
    
    
}
