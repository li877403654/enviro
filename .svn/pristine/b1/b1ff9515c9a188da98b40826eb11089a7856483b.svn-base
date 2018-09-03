/*
 * @(#) SysRegion.java Jun 11, 2018
 * Copyright  2018 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 省市县
 * @author zzl
 * @version 1.0 Jun 11, 2018
 */
@Entity
@Table(name = "sys_region")
public class SysRegion implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String code;

    // 
    private String name;

    // 
    private Integer parentId;

    // 
    private Integer level;

    // 
    private String idPath;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    @Column(name = "id_path")
    public String getIdPath() {
        return idPath;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }

}
