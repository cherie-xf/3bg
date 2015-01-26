package ca.esystem.bridges.dao;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.User;
import ca.esystem.framework.dao.BasicAccessDao;

@Repository
public interface UserDao extends BasicAccessDao {
    public User queryUserByEmail(String email);
    public int changePassword(User user);
    public int updateUserStatus(User user);
}
