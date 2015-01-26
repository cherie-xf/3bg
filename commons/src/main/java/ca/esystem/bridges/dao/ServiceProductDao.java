package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.bridges.domain.ServiceProduct;
import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the table of service and related tables.
 * 
 * @author Lei Han
 *
 */

@Repository
public interface ServiceProductDao extends BasicAccessDao {
    public int insertServiceArea(Object obj);

    public int deleteServiceArea(Object obj);

    public List<?> queryServiceAreaList(Object obj);
    
    public List<ServiceProduct> queryServiceProductByUserId(ServiceProduct serviceProduct);
    
    public List<ServiceProduct> queryServiceProductForIndex(ServiceProduct serviceProduct);
}