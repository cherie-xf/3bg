package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.BlogDao;
import ca.esystem.bridges.service.BlogService;
import ca.esystem.framework.service.AbstractService;

/**
 * ServiceImpl for Blog
 * 
 * @author cherie
 *
 */
@Service("BlogService")
public class BlogServiceImpl extends AbstractService implements BlogService {
    @Resource
    private BlogDao blogDao;

    public List queryList(Object condition) {
        // TODO Auto-generated method stub
        return blogDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        // TODO Auto-generated method stub
        return blogDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        // TODO Auto-generated method stub
        return blogDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        // TODO Auto-generated method stub
        return blogDao.insert(obj);
    }

    public int update(Object obj) {
        // TODO Auto-generated method stub
        return blogDao.update(obj);
    }

    public int archive(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return blogDao.delete(obj);
    }

    public int updateStatus(Object obj) {
        // TODO Auto-generated method stub
        return blogDao.updateStatus(obj);
    }

}
