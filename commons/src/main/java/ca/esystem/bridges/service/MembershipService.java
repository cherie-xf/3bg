package ca.esystem.bridges.service;

import java.util.List;

import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.framework.service.BasicService;

/**
 * Service Interface of Membership and it's responding businiss_profile domain object.
 * 
 * @author Lei Han
 *
 */
public interface MembershipService extends BasicService {
    public int insertMemberAndBusinessProfile(Membership obj);
    public int updateMemberAndBusinessProfile(Membership obj);
    public int deleteMemberAndBusinessProfile(Membership obj);
    public Membership getMemberAndBusinessProfile(Membership obj);
    public Business_Profile getBusinesssProfileByMember(String memberId);
    public Business_Profile getBusinesssProfileByPhone(String phoneNumber);
    public List getSimilarBusinesssProfileListByMember(String memberId);        
}
