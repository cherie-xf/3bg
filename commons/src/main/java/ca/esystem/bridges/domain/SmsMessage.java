package ca.esystem.bridges.domain;

import java.util.Date;

import ca.esystem.framework.domain.BaseForm;

public class SmsMessage extends BaseForm {

    private static final long serialVersionUID = 6231006029278405095L;
    
    private String  phone;
    private Integer message_code;
    private String  message_text;

    public SmsMessage() {
    }

    public SmsMessage(String phone, Integer message_code, String message_text, Date created_at) {
        this.phone = phone;
        this.message_code = message_code;
        this.message_text = message_text;
        super.setCreated_at(created_at);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getMessage_code() {
        return message_code;
    }

    public void setMessage_code(Integer message_code) {
        this.message_code = message_code;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }
}
