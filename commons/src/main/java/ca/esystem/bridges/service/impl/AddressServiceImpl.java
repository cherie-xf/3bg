package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.AddressDao;
import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.service.AddressService;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;
    
    public List queryList(Object condition) {

        return addressDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return addressDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        return addressDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        return addressDao.insert(obj);
    }

    public int update(Object obj) {
        return addressDao.update(obj);
    }

    public int archive(Object obj) {
        return addressDao.archive(obj);
    }

    public int delete(Object obj) {
        return addressDao.delete(obj);
    }

    public List queryAddressList(int userId) {
        Address address = new Address();
        address.setUser_id(userId);
        
        return addressDao.queryListByCondition(address);
    }

}
