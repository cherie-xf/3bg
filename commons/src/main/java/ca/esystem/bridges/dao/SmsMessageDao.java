package ca.esystem.bridges.dao;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.User;
import ca.esystem.framework.dao.BasicAccessDao;

@Repository
public interface SmsMessageDao extends BasicAccessDao {
    public Integer queryMinByCondition(Object condition);
    public Integer queryMaxByCondition(Object condition);
}
