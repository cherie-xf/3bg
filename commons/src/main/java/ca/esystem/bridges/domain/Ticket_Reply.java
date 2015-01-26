package ca.esystem.bridges.domain;

import java.util.Date;

import ca.esystem.framework.domain.BaseForm;

public class Ticket_Reply extends BaseForm {
    private int    id;
    private String reply_msg;
    private int    reply_by;
    private String replier_name;
    private Date   reply_time;
    private int    ticket_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReply_msg() {
        return reply_msg;
    }

    public void setReply_msg(String reply_msg) {
        this.reply_msg = reply_msg;
    }

    public int getReply_by() {
        return reply_by;
    }

    public void setReply_by(int reply_by) {
        this.reply_by = reply_by;
    }

    public String getReplier_name() {
        return replier_name;
    }

    public void setReplier_name(String replier_name) {
        this.replier_name = replier_name;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Date getReply_time() {
        return reply_time;
    }

    public void setReply_time(Date reply_time) {
        this.reply_time = reply_time;
    }

}
