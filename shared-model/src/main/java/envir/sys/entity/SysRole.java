/*
 * @(#) SysRole.java Sep 19, 2017
 * Copyright  2017 GPDI. All right reserved.
 */
package envir.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author zzl
 * @version 1.0 Sep 19, 2017
 */
@Entity
@Table(name = "sys_role")
public class SysRole implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String name;

    // 
    private String remark;

    // 0:禁用,1:启用
    private Integer enabled;

    // 
    private Date createTime;

    //ref data
    private Set<Integer> entryIds;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setEntryIds(Set<Integer> entryIds) {
        this.entryIds = entryIds;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Integer> getEntryIds() {
        return entryIds;
    }
}
