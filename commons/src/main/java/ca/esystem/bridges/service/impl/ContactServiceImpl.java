package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.dao.ContactDao;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.bridges.service.ContactService;

@Service("ContactService")
public class ContactServiceImpl  implements ContactService{
    
    @Resource
    private ContactDao contactDao;
    
    public List queryList(Object condition) {

        return contactDao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {

        return contactDao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {

        return contactDao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {

        return contactDao.insert(obj);
    }

    public int update(Object obj) {

        return contactDao.update(obj);
    }

    public int archive(Object obj) {

        return contactDao.archive(obj);
    }

    public int delete(Object obj) {

        return contactDao.delete(obj);
    }

    public List queryContactListByUserAndType(int userId, String type) {
        Contact contact = new Contact();
        contact.setUser_id(userId);
        contact.setType_code(type);
        return contactDao.queryListByCondition(contact);
    }
    
    public List queryContactListByUserAndClass(int userId, ContactClass contactClass) {
        Contact contact = new Contact();
        contact.setUser_id(userId);
        contact.setType_class(contactClass.name());
        return contactDao.queryListByCondition(contact);
    }
}
