package ca.esystem.bridges.domain;

/**
 * domain for CustomerRepMng
 * author of Cherie
 */
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerRepInfo extends UserInfo {

    private String  member_id;
    private int     user_id;
    private Integer status;
    private Integer user_type;
    private String  type_code;
    private String  preferred_language;
    private String  name;
    private String  password;
    private String  password_confirmation;
    private Long    credit;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date    valid_from;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date    valid_to;
    private boolean memberIDManualInput = false;

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getPreferred_language() {
        return preferred_language;
    }

    public void setPreferred_language(String preferred_language) {
        this.preferred_language = preferred_language;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
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

    public boolean isMemberIDManualInput() {
        return memberIDManualInput;
    }

    public void setMemberIDManualInput(boolean memberIDManualInput) {
        this.memberIDManualInput = memberIDManualInput;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

}
