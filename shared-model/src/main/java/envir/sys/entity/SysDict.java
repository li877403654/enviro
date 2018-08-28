/*
 * @(#) SysDict.java Aug 25, 2017
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
@Table(name = "sys_dict")
public class SysDict implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String cat;

    // 
    private Integer code;

    // 
    private String name;

    // 
    private String remark;

    // 
    private Integer seq;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "cat")
    public String getCat() {
        return cat;
    }

    @Column(name = "code")
    public Integer getCode() {
        return code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "seq")
    public Integer getSeq() {
        return seq;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}
