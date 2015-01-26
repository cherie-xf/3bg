package ca.esystem.bridges.http;

import java.io.Serializable;

public class SMSRequest implements Serializable {

    private static final long serialVersionUID = 4355957178274724289L;

    private String            appId;
    private String            body;
    private String            phoneNumber;
    private String            responsePattern;
    private Integer           sessionDuration;
    private String            callbackUrl;
    
    public SMSRequest() {

    }

    public SMSRequest(String appId, String body, String phoneNumber, Integer sessionDuration, String responsePattern, String callbackUrl) {
        super();
        this.appId = appId;
        this.body = body;
        this.phoneNumber = phoneNumber;
        this.sessionDuration = sessionDuration;
        this.responsePattern = responsePattern;
        this.callbackUrl = callbackUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getResponsePattern() {
        return responsePattern;
    }

    public void setResponsePattern(String responsePattern) {
        this.responsePattern = responsePattern;
    }

    public Integer getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(Integer sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

}
