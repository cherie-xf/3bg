package ca.esystem.bridges.domain;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Business_Profile extends BaseForm {
    private static final long       serialVersionUID = -3419666237727264989L;

    private String                  member_id;
    private String                  business_number;
    private String                  business_name;
    private String                  description;
    private String                  owner_name;
    private String                  phone;
    private String                  tax_number;
    private String                  wcb;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                    business_start;
    private String                  logo;
    private CommonsMultipartFile    logofile;
    private String                  support_doc;
    @JsonIgnore
    private CommonsMultipartFile    support_doc_file;
    private boolean                 agree_term;
    @JsonIgnore
    private int                     recommend_level_id;
    private boolean                 publication;
    private boolean                 is_person;
    private List<Business_Category> category_list;
    private String                  website;
    private String                  email;
    private String                  address;
    private String                  wechat;
    private String                  postcode;
    private boolean                 quick_respond;
    private String                  referal_info;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date                    contract_date;

    private String                  support_doc_name;                        // For display only
    private String                  categoryids;                             // For signup webflow
    private String                  categorynames;                           // For signup webflow

    public Business_Profile() {
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBusiness_number() {
        return business_number;
    }

    public void setBusiness_number(String business_number) {
        this.business_number = business_number;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public String getWcb() {
        return wcb;
    }

    public void setWcb(String wcb) {
        this.wcb = wcb;
    }

    public Date getBusiness_start() {
        return business_start;
    }

    public void setBusiness_start(Date business_start) {
        this.business_start = business_start;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CommonsMultipartFile getLogofile() {
        return logofile;
    }

    public void setLogofile(CommonsMultipartFile logofile) {
        this.logofile = logofile;
    }

    public String getSupport_doc() {
        return support_doc;
    }

    public void setSupport_doc(String support_doc) {
        this.support_doc = support_doc;
    }

    public CommonsMultipartFile getSupport_doc_file() {
        return support_doc_file;
    }

    public void setSupport_doc_file(CommonsMultipartFile support_doc_file) {
        this.support_doc_file = support_doc_file;
    }

    public boolean isAgree_term() {
        return agree_term;
    }

    public void setAgree_term(boolean agree_term) {
        this.agree_term = agree_term;
    }

    public int getRecommend_level_id() {
        return recommend_level_id;
    }

    public void setRecommend_level_id(int recommend_level_id) {
        this.recommend_level_id = recommend_level_id;
    }

    public boolean isPublication() {
        return publication;
    }

    public void setPublication(boolean publication) {
        this.publication = publication;
    }

    public boolean isIs_person() {
        return is_person;
    }

    public void setIs_person(boolean is_person) {
        this.is_person = is_person;
    }

    public List<Business_Category> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(List<Business_Category> category_list) {
        this.category_list = category_list;
    }

    public String getSupport_doc_name() {
        support_doc_name = null;
        if (support_doc != null) {
            String[] nameArray = support_doc.split("/");
            if (nameArray.length == 4)
                support_doc_name = nameArray[3];
        }
        return support_doc_name;
    }

    public void setSupport_doc_name(String support_doc_name) {
        this.support_doc_name = support_doc_name;
    }

    public String getCategoryids() {
        return categoryids;
    }

    public void setCategoryids(String categoryids) {
        this.categoryids = categoryids;
    }

    public String getCategorynames() {
        return categorynames;
    }

    public void setCategorynames(String categorynames) {
        this.categorynames = categorynames;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isQuick_respond() {
        return quick_respond;
    }

    public void setQuick_respond(boolean quick_respond) {
        this.quick_respond = quick_respond;
    }

    public String getReferal_info() {
        return referal_info;
    }

    public void setReferal_info(String referal_info) {
        this.referal_info = referal_info;
    }

    public Date getContract_date() {
        return contract_date;
    }

    public void setContract_date(Date contract_date) {
        this.contract_date = contract_date;
    }    
    
}
