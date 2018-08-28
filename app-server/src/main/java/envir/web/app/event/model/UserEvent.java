package envir.web.app.event.model;

import java.io.Serializable;

/**
 * Created by zzl.
 * Date: 2018-07-15
 */
public class UserEvent implements Serializable {

    private Integer userId;
    private String eventName;
    private String eventData;

    public UserEvent() {
    }

    public UserEvent(Integer userId, String eventName) {
        this.userId = userId;
        this.eventName = eventName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
