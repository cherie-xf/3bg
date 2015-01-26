package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.UserDao;
import ca.esystem.bridges.dao.ContactDao;
import ca.esystem.bridges.dao.AddressDao;
import ca.esystem.bridges.domain.Signup;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    
    @Resource
    private AddressDao addressDao;
    
    @Resource
    private ContactDao contactDao;
    
    private BCryptPasswordEncoder encoder;
    
    public UserServiceImpl(){
        encoder = new BCryptPasswordEncoder();
    }
    
    public List queryList(Object condition) {

        return userDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return userDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {

        return userDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        User user = (User)obj;
        String encyPassword = encoder.encode(user.getPassword());
        user.setPassword(encyPassword);
        return userDao.insert(user);
    }

    public int update(Object obj) {
        return userDao.update(obj);
    }

    public int archive(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public int delete(Object obj) {
        return userDao.delete(obj);
    }

    public User queryUserByEmail(String email) {
        return userDao.queryUserByEmail(email);
    }
    
    public int addContact(Object obj){
        return contactDao.insert(obj);
    }
    public int updateContact(Object obj){
        return contactDao.update(obj);
    }
    
    public int deleteContact(Object obj){
        return contactDao.delete(obj);
    }
    
    public int addAddress(Object obj){
        return addressDao.insert(obj);
    }
    public int updateAddress(Object obj){
        return addressDao.update(obj);
    }
    public int deleteAddress(Object obj){
        return addressDao.delete(obj);
    }    
    
    public int updateUserStatus(Object obj){
    	return userDao.updateUserStatus((User)obj);
    }
    
    public int changePassword(User user){
    	return userDao.changePassword(user);
    }
}
