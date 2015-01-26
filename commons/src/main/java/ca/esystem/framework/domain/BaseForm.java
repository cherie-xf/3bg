package ca.esystem.framework.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Define the common properties that are used in UI interaction and basic log information for each record
 * 
 * @author Larry
 *
 */
public abstract class BaseForm implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3271086218877022424L;
    @JsonIgnore
    private String            tcpip;
    private int               created_by;
    private Date              created_at;
    private int               modified_by;
    private Date              modified_at;
    @JsonIgnore
    private Boolean           selected;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonIgnore
    private Date              beginDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonIgnore
    private Date              endDate;
    @JsonIgnore
    private String            orderByClause;
    @JsonIgnore
    private Pagination        pagination       = new Pagination();
    @JsonIgnore
    private String            feedbackMessage;
    @JsonIgnore
    private String            operate;

    private Boolean           is_deleted       = false; //When deleting, set it to true.

    public String getTcpip() {
        return tcpip;
    }

    public void setTcpip(String tcpip) {
        this.tcpip = tcpip;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getModified_by() {
        return modified_by;
    }

    public void setModified_by(int modified_by) {
        this.modified_by = modified_by;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
