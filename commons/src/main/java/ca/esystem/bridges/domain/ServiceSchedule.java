package ca.esystem.bridges.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author Lei Han
 *
 */

public class ServiceSchedule implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8789112674705731670L;
    private int               id;
    private int               order_id;
    @DateTimeFormat(pattern = "MM/dd/yyyy h:mm a")
    private Date              servicetime;
    private Boolean           selected;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date              datePartial;                            // Binding from/to datepicker on form.
    @DateTimeFormat(pattern = "h:mm a")
    private Date              timePartial;                            // Binding from/to timepicker on form.

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getServicetime() {
        return servicetime;
    }

    public void setServicetime(Date servicetime) {
        this.servicetime = servicetime;
    }

    public Date getDatePartial() {
        if(datePartial == null && servicetime != null)
            datePartial = (Date) servicetime.clone();
        return datePartial;
    }

    public void setDatePartial(Date datePartial) {
        this.datePartial = datePartial;
    }

    public Date getTimePartial() {
        if(timePartial == null && servicetime != null)
            timePartial = (Date) servicetime.clone();
        return timePartial;
    }

    public void setTimePartial(Date timePartial) {
        this.timePartial = timePartial;
    }

    public Date getServiceTimeFromDateTimePartial() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");       
        Date date = null;
        try{
            String dateStr = dateFormat.format(datePartial);
            String timeStr = timeFormat.format(timePartial);
            String dateTimeStr = dateStr + " " + timeStr;            
            date = datetimeFormat.parse(dateTimeStr);
        }catch(Exception e){
            //do nothing.
        }
        return date;
    }
}
