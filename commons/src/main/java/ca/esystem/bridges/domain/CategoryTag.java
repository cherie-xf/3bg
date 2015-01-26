package ca.esystem.bridges.domain;

import ca.esystem.framework.domain.BaseForm;

/**
 * Domain Object of Tag.
 * 
 * @author Lei Han
 *
 */
public class CategoryTag extends BaseForm {

    private static final long serialVersionUID = -2344150452873917064L;

    private Long              tag_id;
    private String            tag;
    private String            category_id;

    public Long getTag_id() {
        return tag_id;
    }

    public void setTag_id(Long tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

}
