package ca.esystem.bridges.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ca.esystem.framework.dao.BasicAccessDao;

/**
 * DAO for the tables of member and business_profile.
 * 
 * @author Lei Han
 *
 */

@Repository
public interface MembershipDao extends BasicAccessDao {
    public int insertBusinessProfile(Object obj);

    public int updateBusinessProfile(Object obj);

    public int deleteBusinessProfile(Object obj);

    public Object queryBusinessProfileByCondition(Object obj);

    public int insertBusinessCategory(Object obj);

    public int deleteBusinessCategory(Object obj);

    public List<?> queryBusinessCategoryList(Object obj);
    
    public Object queryBusinessProfileByPhone(String phoneNumber);
    
    public List<?> querySimilarBusinessProfileListByMember(String memberId);
}