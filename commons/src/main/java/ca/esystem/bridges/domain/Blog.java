package ca.esystem.bridges.domain;

import java.util.ArrayList;
import java.util.List;

import ca.esystem.framework.domain.BaseForm;

/**
 * domain for Blog
 * 
 * @author cherie
 *
 */
public class Blog extends BaseForm {
    private int          blog_id;
    private String       content;
    private String       title;
    private String       meta_keyword;
    private String       meta_dec;
    private String       class_id;
    private String       blog_status;
    private String       phone;
    private String       email;
    private String       first_name;
    private String       last_name;
    private String       preferred_name;
    private String       salt;
    private List<String> statusCodeList = new ArrayList<String>();

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public String getMeta_dec() {
        return meta_dec;
    }

    public void setMeta_dec(String meta_dec) {
        this.meta_dec = meta_dec;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getBlog_status() {
        return blog_status;
    }

    public void setBlog_status(String blog_status) {
        this.blog_status = blog_status;
    }

    public List<String> getStatusCodeList() {
        return statusCodeList;
    }

    public void setStatusCodeList(List<String> statusCodeList) {
        this.statusCodeList = statusCodeList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPreferred_name() {
        return preferred_name;
    }

    public void setPreferred_name(String preferred_name) {
        this.preferred_name = preferred_name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
