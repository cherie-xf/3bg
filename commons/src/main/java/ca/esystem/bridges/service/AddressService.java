package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.framework.service.BasicService;

public interface AddressService extends BasicService {
    public List queryAddressList(int userId);

}
