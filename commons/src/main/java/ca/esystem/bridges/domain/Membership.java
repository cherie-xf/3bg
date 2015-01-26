package ca.esystem.bridges.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import ca.esystem.framework.domain.BaseForm;

/**
 * Domain Object of Membership which is mapping to member table. Some info are stored in relative tables like user, business_profile, member_type and
 * recommend_level.
 * 
 * @author Lei Han
 *
 */
public class Membership extends BaseForm {
    private static final long serialVersionUID    = -3812858047022052460L;

    private String            member_id;
    private int               user_id;
    private String            type_code;
    private Long              credit;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date              valid_from;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date              valid_to;

    private Business_Profile  businessProfile;                            // When creating and updating, map it to form directly.

    private String            owner_name;                                 // Display only, will not be saved to DB.
    private String            phone;                                      // Display only, will not be saved to DB.
    private String            business_name;                              // Display only, will not be saved to DB, from business_profile table
    private String            first_name;                                 // Display only, will not be saved to DB, from user table.
    private String            last_name;                                  // Display only, will not be saved to DB, from user table.
    private String            firstLastName;                              // Display only, will not be saved to DB, composed with first name and last name.

    private Long              recommend_level_id;                         // Stored in recommend_level table.
    private Boolean           isRecommended;                              // Display only, will not be saved to DB.
    private String            type_name;                                  // Display only, will not be saved to DB, from member_type table.

    private boolean           searchRecommended   = false;                // Query condition, default to true to search recommended only.

    private String            subMemberType;                              // For display only. Control to show separate member type drop-down.
    private boolean           memberIDManualInput = false;                // Control if system auto generates member id.
    private int               status              = 0;                    // DEFAULT 0. 0.inactive 1.active 2.suspend.

    private String            memberTypeNotIs;                          // Query condition.

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Date getValid_from() {
        return valid_from;
    }

    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    public Date getValid_to() {
        return valid_to;
    }

    public void setValid_to(Date valid_to) {
        this.valid_to = valid_to;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirstLastName() {
        firstLastName = first_name + " " + last_name;
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public Long getRecommend_level_id() {
        return recommend_level_id;
    }

    public void setRecommend_level_id(Long recommend_level_id) {
        this.recommend_level_id = recommend_level_id;
    }

    public Boolean getIsRecommended() {
        if (recommend_level_id != null && recommend_level_id.longValue() == 0l) {
            isRecommended = false;
        } else {
            isRecommended = true;;
        }
        if (recommend_level_id == null)
            isRecommended = null;
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public boolean isSearchRecommended() {
        return searchRecommended;
    }

    public void setSearchRecommended(boolean searchRecommended) {
        this.searchRecommended = searchRecommended;
    }

    public Business_Profile getBusinessProfile() {
        return businessProfile;
    }

    public void setBusinessProfile(Business_Profile businessProfile) {
        this.businessProfile = businessProfile;
    }

    public String getSubMemberType() {
        if (type_code != null) {
            if (type_code.startsWith("B"))
                subMemberType = "B";
            if (type_code.startsWith("C"))
                subMemberType = "C";
            if (type_code.startsWith("S"))
                subMemberType = "S";
        }
        return subMemberType;
    }

    public void setSubMemberType(String subMemberType) {
        this.subMemberType = subMemberType;
    }

    public boolean isMemberIDManualInput() {
        return memberIDManualInput;
    }

    public void setMemberIDManualInput(boolean memberIDManualInput) {
        this.memberIDManualInput = memberIDManualInput;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemberTypeNotIs() {
        return memberTypeNotIs;
    }

    public void setMemberTypeNotIs(String memberTypeNotIs) {
        this.memberTypeNotIs = memberTypeNotIs;
    }


}
