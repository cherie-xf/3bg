package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.UserDao;
import ca.esystem.bridges.dao.UserProfileDao;
import ca.esystem.bridges.service.UserProfileService;

@Service("UserProfileService")
public class UserProfileServiceImpl implements UserProfileService {

    @Resource
    private UserProfileDao userProfileDao;
    
    public List queryList(Object condition) {
        return userProfileDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return userProfileDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        return userProfileDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        return userProfileDao.insert(obj);
    }

    public int update(Object obj) {
        return userProfileDao.update(obj);
    }

    public int archive(Object obj) {
        throw new RuntimeException("Not implemented.");
    }

    public int delete(Object obj) {
        return userProfileDao.delete(obj);
    }

}
