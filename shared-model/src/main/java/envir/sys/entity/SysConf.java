/*
 * @(#) SysConf.java Jul 2, 2018
 * Copyright 2018 GPDI. All right reserved.
 */
package envir.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * @author zzl
 * @version 1.0 Jul 2, 2018
 */
@Entity
@Table(name = "sys_conf")
public class SysConf implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private String cat;

    // 
    private String name;

    // 
    private String label;

    // 1:字符串,2:整数,3:数值,4:日期,5:布尔
    private Integer valueType;

    // 
    private Integer valueLength;

    // 
    private String strValue;

    // 
    private Integer intValue;

    // 
    private Double numValue;

    // 
    private Date dateValue;

    // 
    private String textValue;

    // 
    private Integer boolValue;

    // 
    private String remark;

    // 
    private Date createTime;

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    @Column(name = "value_type")
    public Integer getValueType() {
        return valueType;
    }

    @Column(name = "value_length")
    public Integer getValueLength() {
        return valueLength;
    }

    @Column(name = "str_value")
    public String getStrValue() {
        return strValue;
    }

    @Column(name = "int_value")
    public Integer getIntValue() {
        return intValue;
    }

    @Column(name = "num_value")
    public Double getNumValue() {
        return numValue;
    }

    @Column(name = "date_value")
    public Date getDateValue() {
        return dateValue;
    }

    @Column(name = "text_value")
    public String getTextValue() {
        return textValue;
    }

    @Column(name = "bool_value")
    public Integer getBoolValue() {
        return boolValue;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public void setValueLength(Integer valueLength) {
        this.valueLength = valueLength;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public void setNumValue(Double numValue) {
        this.numValue = numValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public void setBoolValue(Integer boolValue) {
        this.boolValue = boolValue;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
