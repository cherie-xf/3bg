package ca.esystem.bridges.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ca.esystem.bridges.domain.Role;
import ca.esystem.bridges.domain.User;

public class LoggedInUser extends User implements UserDetails {

    private static final long      serialVersionUID = -2643190427863668155L;

    private List<GrantedAuthority> autorities;

    public LoggedInUser() {

    }

    public LoggedInUser(User user, List<Role> roles) {
        super(user);
        if (roles != null) {
            autorities = new ArrayList<GrantedAuthority>();
            for (Role role : roles) {
                GrantedAuthority auth = new SimpleGrantedAuthority(role.getRoleName());
                autorities.add(auth);
            }
        }
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorities;
    }

    public int getUserid(){
        return super.getId();
    }
    
    public String getEmail() {
        return super.getEmail();
    }
    
    public String getFirstName() {
        return super.getFirstName();
    }
    
    public String getLastName() {
        return super.getLastName();
    }
    
    public String getPhone() {
        return super.getPhone();
    }
    
    public String getPreferredName() {
        return super.getPreferredName();
    }
    
    public boolean isAccountNonExpired() {
        if (super.getAccountExpired() == null)
            return true;

        return super.getAccountExpired().after(new Date());
    }

    public boolean isAccountNonLocked() {
        return !super.isAccountLocked() && super.isEnabled() && !super.isDeleted();
    }

    public boolean isCredentialsNonExpired() {
        return !super.isCredentialsExpired();
    }
    
    public boolean isAdmin() {
        return true;
    }
    
    public boolean isStaff() {
        return true;
    }

    public String getUsername() {
        return super.getEmail();
    }
    
    public String getPassword() {
        return super.getPassword();
    }    
}
