package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.ServiceProduct;
import ca.esystem.framework.service.BasicService;

/**
 * ServiceProduct Interface of ServiceProduct Management.
 * 
 * @author Lei Han
 *
 */
public interface ServiceProductMngService extends BasicService {    
    public List<ServiceProduct> queryServiceProductByUserId(ServiceProduct serviceProduct);
    public List<ServiceProduct> queryServiceProductForIndex(ServiceProduct serviceProduct);
}
