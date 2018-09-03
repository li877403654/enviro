/*
 * @(#) Msg.java Jun 20, 2018
 * Copyright  2018 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author zzl
 * @version 1.0 Jun 20, 2018
 */
@Entity
@Table(name = "t_msg")
public class Msg implements Serializable {

    public static final int BIZ_STATION = 1;
    public static final int BIZ_ASSIST = 2;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private Integer userId;

    // t_reminder.id
    private Integer remId;

    // 业务类型. 1:桃源客栈,2:爱心互助,3:xx
    private Integer biz;

    // 
    private Date time;

    // 
    private String content;

    //
    private Integer read;

    //
    private Integer pushMsgId;

    //ref data
    private String detail;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "rem_id")
    public Integer getRemId() {
        return remId;
    }

    @Column(name = "biz")
    public Integer getBiz() {
        return biz;
    }

    @Column(name = "`time`")
    public Date getTime() {
        return time;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "`read`")
    public Integer getRead() {
        return read;
    }

    @Column(name = "push_msg_id")
    public Integer getPushMsgId() {
        return pushMsgId;
    }

    public String getDetail() {
        return detail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRemId(Integer rem) {
        this.remId = remId;
    }

    public void setBiz(Integer biz) {
        this.biz = biz;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public void setPushMsgId(Integer pushMsgId) {
        this.pushMsgId = pushMsgId;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
