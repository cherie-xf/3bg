package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the tables of initial business_profile.
 * 
 * @author Lei Han
 *
 */

@Repository
public interface InitBusinessInputDao extends BasicAccessDao {
    public int insertBusinessCategory(Object obj);

    public int deleteBusinessCategory(Object obj);

    public List<?> queryBusinessCategoryList(Object obj);
    
    public String getMaxMemberId();
}