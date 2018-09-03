/*
 * @(#) PushMsg.java Jul 13, 2018
 * Copyright  2018 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author zzl
 * @version 1.0 Jul 13, 2018
 */
@Entity
@Table(name = "t_push_msg")
public class PushMsg implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String title;

    // 
    private String content;

    // 
    private String remark;

    // 
    private Date createTime;

    // 
    private Integer createUserId;

    // 
    private Date scheduleTime;

    // 
    private Integer pushed;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "create_user_id")
    public Integer getCreateUserId() {
        return createUserId;
    }

    @Column(name = "schedule_time")
    public Date getScheduleTime() {
        return scheduleTime;
    }

    @Column(name = "pushed")
    public Integer getPushed() {
        return pushed;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setPushed(Integer pushed) {
        this.pushed = pushed;
    }

}
