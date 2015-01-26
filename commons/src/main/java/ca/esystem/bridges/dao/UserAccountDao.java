package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.User;
import ca.esystem.bridges.domain.UserAccount;
import ca.esystem.framework.dao.BasicAccessDao;

@Repository
public interface UserAccountDao extends BasicAccessDao {
    public UserAccount queryUserByService(Integer serviceId);
    public UserAccount queryUserByPhone(String phoneNumber);    
    public List queryUserListByService(Integer serviceId);    
}
