package ca.esystem.bridges.domain;

import java.util.*;

import ca.esystem.framework.domain.BaseForm;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SysUser extends BaseForm {
    private int                  userid;
    private String               email;
    private String               username;
    private String               usertype;
    private Map                  usertypelist = new HashMap();
    private String               pass;
    private String               pass_confirmation;
    private String               verifycode;
    private String               firstname;
    private String               lastname;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                 birth;
    private String               year;
    private String               month;
    private String               date;
    private String               sex;
    private String               portrait;
    private String               phone;
    private String               cell;
    private String               address;
    private String               city;
    private String               province;
    private String               country;
    private String               postalcode;
    private String               qq;
    private String               wechat;
    private String               facebook;
    private String               twitter;
    private String               linkedin;
    private String               website;
    private int                  credit;
    private String               rank;
    private Map                  ranklist     = new HashMap();
    private int                  status       = -1;
    private String               role;
    private Map                  rolelist     = new HashMap();
    private String               tcpip;
    private Date                 lastlogin;
    private String               description;
    @JsonIgnore
    private CommonsMultipartFile upfile;
    private String               inputby;
    private Date                 inputtime;
    private String               modifiedby;
    private Date                 modifiedtime;

    /**
     * 0.Local 1.Facebook 2.Google
     */
    private int                  userfrom;

    public SysUser() {
        usertypelist.put("F", "Free User");
        usertypelist.put("M", "Member");
        usertypelist.put("V", "VIP");

        ranklist.put("0", "Iron");
        ranklist.put("1", "Bronze");
        ranklist.put("2", "Silver");
        ranklist.put("3", "Gold");
        ranklist.put("4", "Diamond");

        rolelist.put("U", "User");
        rolelist.put("A", "Administrator");
        rolelist.put("E", "Editor");

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass_confirmation() {
        return pass_confirmation;
    }

    public void setPass_confirmation(String pass_confirmation) {
        this.pass_confirmation = pass_confirmation;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTcpip() {
        return tcpip;
    }

    public void setTcpip(String tcpip) {
        this.tcpip = tcpip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map getUsertypelist() {
        return usertypelist;
    }

    public void setUsertypelist(Map usertypelist) {
        this.usertypelist = usertypelist;
    }

    public Map getRanklist() {
        return ranklist;
    }

    public void setRanklist(Map ranklist) {
        this.ranklist = ranklist;
    }

    public Map getRolelist() {
        return rolelist;
    }

    public void setRolelist(Map rolelist) {
        this.rolelist = rolelist;
    }

    public CommonsMultipartFile getUpfile() {
        return upfile;
    }

    public void setUpfile(CommonsMultipartFile upfile) {
        this.upfile = upfile;
    }

    public int getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(int userfrom) {
        this.userfrom = userfrom;
    }

    public String getInputby() {
        return inputby;
    }

    public void setInputby(String inputby) {
        this.inputby = inputby;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
}
