package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

/**
 * domain for user_history
 * 
 * @author cherie
 *
 */
public class User_History extends BaseForm {
    private int    history_id;
    private int    user_id;
    private String creater_name;
    private String content;

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreater_name() {
        return creater_name;
    }

    public void setCreater_name(String creater_name) {
        this.creater_name = creater_name;
    }
    

}
