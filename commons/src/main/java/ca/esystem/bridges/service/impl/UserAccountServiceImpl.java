package ca.esystem.bridges.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.UserAccountDao;
import ca.esystem.bridges.dao.UserHistoryDao;
import ca.esystem.bridges.domain.NewUserAccount;
import ca.esystem.bridges.domain.Signup;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.UserAccount;
import ca.esystem.bridges.domain.UserAccountBasic;
import ca.esystem.bridges.domain.UserAccountOther;
import ca.esystem.bridges.domain.UserProfile;
import ca.esystem.bridges.exception.DuplicateDataException;
import ca.esystem.bridges.service.UserAccountService;
import ca.esystem.bridges.service.UserProfileService;
import ca.esystem.bridges.service.UserService;

@Service("UserAccountService")
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountDao     userAccountDao;

    @Resource(name = "UserService")
    private UserService        userService;

    @Resource(name = "UserProfileService")
    private UserProfileService userProfileService;
    
    @Resource
    private UserHistoryDao userHistoryDao;

    public int addUser(Signup signup) {
        User user = new User();
        user.setEmail(signup.getEmail());
        user.setPassword(signup.getPassword());
        user.setPhone("xxxx");
        user.setCreatedAt(new Date());
        userService.add(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(user.getId());
        userProfile.setCreatedAt(new Date());
        userProfileService.add(userProfile);

        return user.getId();
    }

    public int addUserAccount(int operatorId, NewUserAccount newUserAccount) {

        // Duplicate email addresses check
        User user = userService.queryUserByEmail(newUserAccount.getEmail());
        if (user != null) {
            throw new DuplicateDataException("Email has already been used.");
        }

        // Create a user record
        User newUser = new User(newUserAccount.getEmail(), newUserAccount.getPhone(), newUserAccount.getFirstName(), newUserAccount.getLastName(),
                newUserAccount.getPassword());
        newUser.setCreatedBy(operatorId);
        newUser.setCreatedAt(new Date());
        newUser.setUser_type(newUserAccount.getUser_type());
        userService.add(newUser);

        // Create a user profile record
        UserProfile newUserProfile = new UserProfile();
        newUserProfile.setUserId(newUser.getId());
        newUserProfile.setCreatedBy(operatorId);
        newUserProfile.setCreatedAt(new Date());
        userProfileService.add(newUserProfile);

        return newUser.getId();
    }

    public UserAccountBasic getUserAccountBasic(int operatorId, int userId) {

        // User
        User user = new User();
        user.setId(userId);
        user = (User) userService.queryOne(user);

        // User profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile = (UserProfile) userProfileService.queryOne(userProfile);

        return new UserAccountBasic(user, userProfile);
    }

    public boolean updateUserAccountBasic(int operatorId, UserAccountBasic userAccountBasic) {
        // User
        User user = new User();
        user.setId(userAccountBasic.getId());
        user = (User) userService.queryOne(user);
        userAccountBasic.copyToUser(user);
        user.setModifiedBy(operatorId);
        user.setModifiedAt(new Date());
        userService.update(user);

        // User profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userAccountBasic.getId());
        userProfile = (UserProfile) userProfileService.queryOne(userProfile);
        userAccountBasic.copyToUserProfile(userProfile);
        userProfile.setModifiedBy(operatorId);
        userProfile.setModifiedAt(new Date());
        userProfileService.update(userProfile);

        //
        return true;
    }

    public UserAccountOther getUserAccountOther(int operatorId, int userId) {

        // User profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile = (UserProfile) userProfileService.queryOne(userProfile);

        return new UserAccountOther(userProfile);
    }

    public boolean updateUserAccountOther(int operatorId, UserAccountOther userAccountOther) {

        // User profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userAccountOther.getId());
        userProfile = (UserProfile) userProfileService.queryOne(userProfile);
        userAccountOther.copyToUserProfile(userProfile);
        userProfile.setModifiedBy(operatorId);
        userProfile.setModifiedAt(new Date());
        userProfileService.update(userProfile);

        //
        return true;
    }

    public boolean deleteUserAccount(int operatorId, int userId) {
        // User
        User user = new User();
        
        user.setId(userId);
        user.setModifiedBy(operatorId);
        user.setModifiedAt(new Date());
        userService.delete(user);

        // User profile
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setModifiedBy(operatorId);
        userProfile.setModifiedAt(new Date());
        userProfileService.delete(userProfile);

        //
        return true;       
    }
    
    public List queryList(Object condition) {
        return userAccountDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return userAccountDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        throw new RuntimeException("Not implemented.");
    }

    public Object add(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public int update(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public int archive(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public int delete(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public List userHistoryList(Object condition) {
        // TODO Auto-generated method stub
        return userHistoryDao.queryListByCondition(condition);
    }

    public int userHistoryCount(Object condition) {
        // TODO Auto-generated method stub
        return userHistoryDao.queryCountRowsByCondition(condition);
    }

    public Object userHistoryOne(Object condition) {
        // TODO Auto-generated method stub
        return userHistoryDao.queryObjectByCondition(condition);
    }

    public Object userHistoryAdd(Object obj) {
        // TODO Auto-generated method stub
        return userHistoryDao.insert(obj);
    }

    public UserAccount queryUserByService(Integer serviceId) {
        return userAccountDao.queryUserByService(serviceId);
    }
   
    public UserAccount queryUserByPhone(String phoneNumber) {
        return userAccountDao.queryUserByPhone(phoneNumber);
    }

    public List queryUserListByService(Integer serviceId) {
        return userAccountDao.queryUserListByService(serviceId);
    }      
}
