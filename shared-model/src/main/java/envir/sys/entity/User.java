/*
 * @(#) User.java Jul 8, 2018
 * Copyright 2018 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author Mr. xyb
 * @version 1.0 Jul 8, 2018
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //
    private String mobile;

    //
    private String password;

    //
    private String shareCode;

    //
    private Date registerTime;

    //
    private Integer stationId;

    //
    private Integer stewardId;

    //
    private String name;

    //
    private Integer avatarFileId;

    // 性别
    private Integer sex;

    // 生日
    private Date birthday;

    // 省份
    private String province;

    // 城市
    private String city;

    // 详细地址
    private String detailAddr;

    // 个性标签
    private String labelText;

    //
    private String locCoord; //lng,lat

    //
    private Date locTime;

    //
    private Integer locOn;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "share_code")
    public String getShareCode() {
        return shareCode;
    }

    @Column(name = "register_time")
    public Date getRegisterTime() {
        return registerTime;
    }

    @Column(name = "station_id")
    public Integer getStationId() {
        return stationId;
    }

    @Column(name = "steward_id")
    public Integer getStewardId() {
        return stewardId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "avatar_file_id")
    public Integer getAvatarFileId() {
        return avatarFileId;
    }

    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    @Column(name = "detail_addr")
    public String getDetailAddr() {
        return detailAddr;
    }

    @Column(name = "label_text")
    public String getLabelText() {
        return labelText;
    }

    @Column(name = "loc_coord")
    public String getLocCoord() {
        return locCoord;
    }

    @Column(name = "loc_time")
    public Date getLocTime() {
        return locTime;
    }

    @Column(name = "loc_on")
    public Integer getLocOn() {
        return locOn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public void setStewardId(Integer stewardId) {
        this.stewardId = stewardId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarFileId(Integer avatarFileId) {
        this.avatarFileId = avatarFileId;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public void setLocCoord(String locCoord) {
        this.locCoord = locCoord;
    }

    public void setLocTime(Date locTime) {
        this.locTime = locTime;
    }

    public void setLocOn(Integer locOn) {
        this.locOn = locOn;
    }
}
