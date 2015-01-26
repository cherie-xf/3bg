package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.ContactClass;
import ca.esystem.framework.service.BasicService;

public interface ContactService extends BasicService {
    public List queryContactListByUserAndType(int userId, String type);
    public List queryContactListByUserAndClass(int userId, ContactClass contactClass);
}
