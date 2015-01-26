package ca.esystem.bridges.domain;

import java.util.Date;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry
 *
 */
public class Search_Missed extends BaseForm {
    private int    id;
    private String keyword;
    private Date   searched_at;
    private int    user_id;
    private String user_ip_address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getSearched_at() {
        return searched_at;
    }

    public void setSearched_at(Date searched_at) {
        this.searched_at = searched_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_ip_address() {
        return user_ip_address;
    }

    public void setUser_ip_address(String user_ip_address) {
        this.user_ip_address = user_ip_address;
    }

}
