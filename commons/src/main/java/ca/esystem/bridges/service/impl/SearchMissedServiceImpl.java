package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.Search_MissedDao;
import ca.esystem.bridges.dao.TicketDao;
import ca.esystem.bridges.service.SearchMissedService;
import ca.esystem.framework.service.AbstractService;

@Service("SearchMissedService")
public class SearchMissedServiceImpl extends AbstractService implements SearchMissedService {
    @Resource
    private Search_MissedDao searchMissedDao;

    public List queryList(Object condition) {
        // TODO Auto-generated method stub
        return searchMissedDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        // TODO Auto-generated method stub
        return searchMissedDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        // TODO Auto-generated method stub
        return searchMissedDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        // TODO Auto-generated method stub
        return searchMissedDao.insert(obj);
    }

    public int update(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int archive(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return searchMissedDao.delete(obj);
    }

}
