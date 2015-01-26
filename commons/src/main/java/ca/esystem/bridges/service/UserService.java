package ca.esystem.bridges.service;

import ca.esystem.bridges.domain.User;
import ca.esystem.framework.service.BasicService;

public interface UserService extends BasicService {
    public User queryUserByEmail(String email);
    
    public int addContact(Object obj);
    public int updateContact(Object obj);
    public int deleteContact(Object obj);
    
    public int addAddress(Object obj);
    public int updateAddress(Object obj);
    public int deleteAddress(Object obj);
    public int updateUserStatus(Object obj);
    public int changePassword(User user);
}