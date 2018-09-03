/*
 * @(#) SysVar.java Aug 25, 2017
 * Copyright  2017 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author zzl
 * @version 1.0 Aug 25, 2017
 */
@Entity
@Table(name = "sys_var")
public class SysVar implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String name;

    // 
    private String value;

    //
    private String remark;


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

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
