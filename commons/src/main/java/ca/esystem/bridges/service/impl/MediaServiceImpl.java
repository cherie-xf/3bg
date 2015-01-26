package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.MediaDao;
import ca.esystem.bridges.service.MediaService;
import ca.esystem.framework.service.AbstractService;

/**
 * 
 * @author Larry
 *
 */
@Service("MediaService")
public class MediaServiceImpl extends AbstractService implements MediaService {
    
    @Resource
    private MediaDao mediaDao;
    
    public List queryList(Object condition) {
        // TODO Auto-generated method stub
        return mediaDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        // TODO Auto-generated method stub
        return mediaDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        // TODO Auto-generated method stub
        return mediaDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        // TODO Auto-generated method stub
        return mediaDao.insert(obj);
    }

    public int update(Object obj) {
        // TODO Auto-generated method stub
        return mediaDao.update(obj);
    }

    public int archive(Object obj) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(Object obj) {
        // TODO Auto-generated method stub
        return mediaDao.delete(obj);
    }

}
