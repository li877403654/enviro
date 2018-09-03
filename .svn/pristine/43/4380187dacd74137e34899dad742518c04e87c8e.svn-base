/*
 * @(#) SysUser.java Sep 18, 2017
 * Copyright  2017 GPDI. All right reserved.
 */
package envir.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author monta
 * @version 1.0 Sep 18, 2017
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    //
    private Integer projectId;

    // 登录昵称
    private String name;

    // 账号
    private String account;

    // 密码
    private String password;

    // 
    private Integer type;

    // 
    private Date createTime;

    // 头像
    private Integer avatarFileId;

    //ref data
    private List<Integer> roleIds;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "avatar_file_id")
    public Integer getAvatarFileId() {
        return avatarFileId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setAvatarFileId(Integer avatarFileId) {
        this.avatarFileId = avatarFileId;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
