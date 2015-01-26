package ca.esystem.bridges.domain;

import java.util.List;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry, Lei Han
 *
 */

public class ServiceProduct extends BaseForm {
    private static final long serialVersionUID = -6562424885450844196L;

    private Integer           service_id;
    private String            service_title;
    private String            cover_img;
    private String            category_id;
    private String            member_id;
    private boolean           face_negotiable  = false;                // Default to false.
    private float             basic_price;
    private float             unit_price;
    private String            unit_id;
    private boolean           tax_included     = false;                // Default to false.
    private float             gst_rate;
    private float             pst_rate;
    private String            description;
    private String            warrant;
    private String            status_id;
    private Integer           recommend_level_id;
    private String            meta_keywords;
    private String            meta_desc;
    private List<String>      serviceAreaList;

    /* below attributes will not be saved to DB, only for UI display. */
    private String            category_name;                           // For display only.
    private String            status;                                  // For display only.
    private String            recommend_level_name;                    // For display only.
    private String            gstRate          = "5.00";               // For display only. Default to current GST rate.
    private String            pstRate          = "7.00";               // For display only. Default to current BC PST rate.

    private int               user_id;                                 // Query condition

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public boolean isFace_negotiable() {
        return face_negotiable;
    }

    public void setFace_negotiable(boolean face_negotiable) {
        this.face_negotiable = face_negotiable;
    }

    public float getBasic_price() {
        return basic_price;
    }

    public void setBasic_price(float basic_price) {
        this.basic_price = basic_price;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public Integer getRecommend_level_id() {
        return recommend_level_id;
    }

    public void setRecommend_level_id(Integer recommend_level_id) {
        this.recommend_level_id = recommend_level_id;
    }

    public String getMeta_keywords() {
        return meta_keywords;
    }

    public void setMeta_keywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getMeta_desc() {
        return meta_desc;
    }

    public void setMeta_desc(String meta_desc) {
        this.meta_desc = meta_desc;
    }

    public boolean isTax_included() {
        return tax_included;
    }

    public void setTax_included(boolean tax_included) {
        this.tax_included = tax_included;
    }

    public float getGst_rate() {
        return gst_rate;
    }

    public void setGst_rate(float gst_rate) {
        this.gst_rate = gst_rate;
    }

    public float getPst_rate() {
        return pst_rate;
    }

    public void setPst_rate(float pst_rate) {
        this.pst_rate = pst_rate;
    }

    public List<String> getServiceAreaList() {
        return serviceAreaList;
    }

    public void setServiceAreaList(List<String> serviceAreaList) {
        this.serviceAreaList = serviceAreaList;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommend_level_name() {
        return recommend_level_name;
    }

    public void setRecommend_level_name(String recommend_level_name) {
        this.recommend_level_name = recommend_level_name;
    }

    public String getGstRate() {
        return gstRate;
    }

    public void setGstRate(String gstRate) {
        this.gstRate = gstRate;
    }

    public String getPstRate() {
        return pstRate;
    }

    public void setPstRate(String pstRate) {
        this.pstRate = pstRate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
