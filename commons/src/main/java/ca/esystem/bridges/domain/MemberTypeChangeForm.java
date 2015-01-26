package ca.esystem.bridges.domain;

import java.io.Serializable;

/**
 * @author Lei
 *
 */
public class MemberTypeChangeForm implements Serializable {
    private static final long serialVersionUID = -986114270123401077L;

    private String            member_id;
    private String            currentTypeCode;
    private String            currentTypeName;
    private String            newTypeCode;
    private String            newTypeName;
    private String            changeReason;
    private String            subMemberType;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCurrentTypeCode() {
        return currentTypeCode;
    }

    public void setCurrentTypeCode(String currentTypeCode) {
        this.currentTypeCode = currentTypeCode;
    }

    public String getCurrentTypeName() {
        return currentTypeName;
    }

    public void setCurrentTypeName(String currentTypeName) {
        this.currentTypeName = currentTypeName;
    }

    public String getNewTypeCode() {
        return newTypeCode;
    }

    public void setNewTypeCode(String newTypeCode) {
        this.newTypeCode = newTypeCode;
    }

    public String getNewTypeName() {
        return newTypeName;
    }

    public void setNewTypeName(String newTypeName) {
        this.newTypeName = newTypeName;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getSubMemberType() {
        if (currentTypeCode != null) {
            if (currentTypeCode.startsWith("B"))
                subMemberType = "B";
            if (currentTypeCode.startsWith("C"))
                subMemberType = "C";
            if (currentTypeCode.startsWith("S"))
                subMemberType = "S";
        }
        return subMemberType;
    }

    public void setSubMemberType(String subMemberType) {
        this.subMemberType = subMemberType;
    }

}
