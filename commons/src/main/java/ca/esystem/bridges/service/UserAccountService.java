package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.NewUserAccount;
import ca.esystem.bridges.domain.Signup;
import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.UserAccount;
import ca.esystem.bridges.domain.UserAccountBasic;
import ca.esystem.bridges.domain.UserAccountOther;
import ca.esystem.framework.service.BasicService;

/**
 * Transactional methods: insert*, add*, update*, delete*, remove*, do*, reSend*
 *
 */
public interface UserAccountService extends BasicService {
    public int addUser(Signup signup);
    public int addUserAccount(int operatorId, NewUserAccount newUserAccount);
    public UserAccountBasic getUserAccountBasic(int operatorId, int userId);
    public boolean updateUserAccountBasic(int operatorId, UserAccountBasic userAccountBasic);
    public UserAccountOther getUserAccountOther(int operatorId, int userId);
    public boolean updateUserAccountOther(int operatorId, UserAccountOther userAccountOther);
    public boolean deleteUserAccount(int operatorId, int userId);
    
    public List userHistoryList(Object condition);

    public int userHistoryCount(Object condition);

    public Object userHistoryOne(Object condition);

    public Object userHistoryAdd(Object obj); 
    
//    public UserAccount queryUserByService(Integer serviceId);
//    public UserAccount queryUserByPhone(String phoneNumber);
//    public List queryUserListByService(Integer serviceId);    
}
