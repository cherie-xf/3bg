package ca.esystem.bridges.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ca.esystem.bridges.domain.Role;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.service.UserService;

public class AuthenticationProvider implements UserDetailsService {

    private UserService userService;
    
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {

        List<Role> roles = new ArrayList<Role>();
        Role role = new Role("ROLE_USER");
        roles.add(role);
        User user = userService.queryUserByEmail(arg0);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by email");
        }
        
        //
        return new LoggedInUser(user, roles);
    }
}
