/*
 * @(#) SysEntry.java Sep 19, 2017
 * Copyright  2017 GPDI. All right reserved.
 */
package envir.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author zzl
 * @version 1.0 Sep 19, 2017
 */
@Entity
@Table(name = "sys_entry")
public class SysEntry implements Serializable {

    public static final int ROOT_ID = -1;
    public static final String TYPE = "sys_entry.type";
    public static final int TYPE_MODULE = 1;
    public static final int TYPE_MENU = 2;
    public static final int TYPE_ACTION = 3;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private Integer parentId;

    // 
    private String name;

    // 1:模块,2:菜单,3:按钮
    private Integer type;

    // 
    private String url;

    // 
    private String action;

    // 
    private String icon;

    // 1:启用,2:禁用
    private Integer enabled;

    // 
    private String remark;

    // 
    private Integer seq;

    //ref data
    private List<SysEntry> children;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    @Column(name = "action")
    public String getAction() {
        return action;
    }

    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    @Column(name = "enabled")
    public Integer getEnabled() {
        return enabled;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "seq")
    public Integer getSeq() {
        return seq;
    }

    public List<SysEntry> getChildren() {
        return children;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public void setChildren(List<SysEntry> children) {
        this.children = children;
    }
}
