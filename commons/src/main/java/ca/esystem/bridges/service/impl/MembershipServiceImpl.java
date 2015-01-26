package ca.esystem.bridges.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.esystem.bridges.service.MembershipService;
import ca.esystem.framework.service.AbstractService;
import ca.esystem.bridges.dao.MembershipDao;
import ca.esystem.bridges.domain.Business_Category;
import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.Membership;

/**
 * Implementation for MembershipService.
 * 
 * @author Lei Han
 *
 */
@Service("MembershipService")
@Transactional
public class MembershipServiceImpl extends AbstractService implements MembershipService {

    @Resource
    private MembershipDao dao;

    public List<?> queryList(Object condition) {
        return dao.queryListByCondition(condition);
    }

    public int queryCount(Object condition) {
        return dao.queryCountRowsByCondition(condition);
    }

    public Object queryOne(Object condition) {
        return dao.queryObjectByCondition(condition);
    }

    public Object add(Object obj) {
        return dao.insert(obj);
    }

    public int update(Object obj) {
        return dao.update(obj);
    }

    public int archive(Object obj) {
        return 0;
    }

    public int delete(Object obj) {
        return dao.delete(obj);
    }

    @Transactional
    public int insertMemberAndBusinessProfile(Membership member) {
        Business_Profile businessProfile = member.getBusinessProfile();
        
        if("B".equals(member.getSubMemberType()) && businessProfile != null){
            dao.insert(member);
            String bpMemberId = businessProfile.getMember_id();
            if(bpMemberId == null || bpMemberId.equals("")){
                businessProfile.setMember_id(member.getMember_id());
            }
            dao.insertBusinessProfile(businessProfile);
            
            List<Business_Category> newBusinessCategoryList = businessProfile.getCategory_list();
            if (newBusinessCategoryList != null) {
                for (Business_Category businessCategoryNew : newBusinessCategoryList) {
                    businessCategoryNew.setMember_id(member.getMember_id());
                    dao.insertBusinessCategory(businessCategoryNew);
                }
            }            
            return 1;
        }else{
            return dao.insert(member);
        }       
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public int updateMemberAndBusinessProfile(Membership member) {
        String member_id = member.getMember_id();
        Business_Profile businessProfile = member.getBusinessProfile();
        dao.update(member);

        if (businessProfile != null) {

            dao.updateBusinessProfile(businessProfile);

            Business_Category businessCategory = new Business_Category();
            businessCategory.setMember_id(member_id);

            List<Business_Category> oldBusinessCategoryList = (List<Business_Category>) dao.queryBusinessCategoryList(businessCategory);
            for (Business_Category businessCategoryOld : oldBusinessCategoryList) {
                dao.deleteBusinessCategory(businessCategoryOld);
            }

            List<Business_Category> newBusinessCategoryList = businessProfile.getCategory_list();
            if (newBusinessCategoryList != null) {
                for (Business_Category businessCategoryNew : newBusinessCategoryList) {
                    dao.insertBusinessCategory(businessCategoryNew);
                }
            }
        }

        return 1;
    }

    @Transactional
    public int deleteMemberAndBusinessProfile(Membership member) {
        String member_id = member.getMember_id();
        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(member_id);
        businessProfile = (Business_Profile) dao.queryBusinessProfileByCondition(businessProfile);
        if (businessProfile == null) {
            return dao.delete(member);
        } else {
            dao.delete(member);
            return dao.deleteBusinessProfile(businessProfile);
        }
    }

    @SuppressWarnings("unchecked")
    public Membership getMemberAndBusinessProfile(Membership member) {
        String member_id = member.getMember_id();
        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(member_id);

        Business_Profile businessProfileToreturn = (Business_Profile) dao.queryBusinessProfileByCondition(businessProfile);

        Membership memberToReturn = (Membership) dao.queryObjectByCondition(member);
        if (businessProfileToreturn != null) {
            memberToReturn.setBusinessProfile(businessProfileToreturn);
            Business_Category businessCategory = new Business_Category();
            businessCategory.setMember_id(member_id);
            List<Business_Category> businessCategoryList = (List<Business_Category>) dao.queryBusinessCategoryList(businessCategory);

            if (businessCategoryList != null && businessCategoryList.size() > 0) {
                businessProfileToreturn.setCategory_list(businessCategoryList);
            }
        }

        return memberToReturn;
    }
    
    public Business_Profile getBusinesssProfileByMember(String memberId) {
        Business_Profile businessProfile = new Business_Profile();
        businessProfile.setMember_id(memberId);
        
        return (Business_Profile) dao.queryBusinessProfileByCondition(businessProfile);
    }
    
    public Business_Profile getBusinesssProfileByPhone(String phoneNumber) {
        return (Business_Profile) dao.queryBusinessProfileByPhone(phoneNumber);       
    }
    
    public List<?> getSimilarBusinesssProfileListByMember(String memberId) {
        return dao.querySimilarBusinessProfileListByMember(memberId);
    }
}
