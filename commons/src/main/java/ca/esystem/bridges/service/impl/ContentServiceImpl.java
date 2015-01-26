package ca.esystem.bridges.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ca.esystem.bridges.dao.ContentDao;
import ca.esystem.bridges.service.ContentService;
import ca.esystem.framework.service.AbstractService;

/**
 * ServiceImpl for Content
 * 
 * @author Larry
 *
 */
@Service("ContentService")
public class ContentServiceImpl extends AbstractService implements ContentService {
    @Resource
    private ContentDao contentDao;

    public List queryList(Object condition) {
        // TODO Auto-generated method stub
        return contentDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        // TODO Auto-generated method stub
        return contentDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        // TODO Auto-generated method stub
        return contentDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        // TODO Auto-generated method stub
        return contentDao.insert(obj);
    }

    public int update(Object obj) {
        // TODO Auto-generated method stub
        return contentDao.update(obj);
    }

    public int archive(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return contentDao.delete(obj);
    }

}
