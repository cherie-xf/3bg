package ca.esystem.bridges.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ca.esystem.framework.domain.BaseForm;

/**
 * 
 * @author Larry
 *
 */

public class Media extends BaseForm {
    private int                  media_id;
    private String               thumb;
    private String               bigpic;
    private String               title;
    private String               ext_name;
    private String               description;
    @JsonIgnore
    private CommonsMultipartFile picfile;



    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getBigpic() {
        return bigpic;
    }

    public void setBigpic(String bigpic) {
        this.bigpic = bigpic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExt_name() {
        return ext_name;
    }

    public void setExt_name(String ext_name) {
        this.ext_name = ext_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommonsMultipartFile getPicfile() {
        return picfile;
    }

    public void setPicfile(CommonsMultipartFile picfile) {
        this.picfile = picfile;
    }

}
