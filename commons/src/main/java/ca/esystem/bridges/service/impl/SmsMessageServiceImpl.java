package ca.esystem.bridges.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.SmsMessageDao;
import ca.esystem.bridges.service.SmsMessageService;

@Service("SmsMessageService")
public class SmsMessageServiceImpl implements SmsMessageService {

    @Resource
    private SmsMessageDao dao;

    public Object add(Object obj) {
        return dao.insert(obj);
    }

    public Integer queryMin(Object obj) {
        return dao.queryMinByCondition(obj);
    }

    public Integer queryMax(Object obj) {
        return dao.queryMaxByCondition(obj);
    }
}
