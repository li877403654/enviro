/*
 * @(#) File.java Oct 31, 2015
 * Copyright  2015 GPDI. All right reserved.
 */
package envir.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zzl
 * @version 1.0 Oct 31, 2015
 */
@Entity
@Table(name = "sys_file")
public class SysFile implements Serializable {

    public static final String TEMP_REL_TABLE = "temp";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final Integer TEMP_REL_ID = 0;
    public static final Integer TYPE_TEMP = -1;

    public static final Integer TYPE_RICHTEXT_IMAGE = 100;
    public static final Integer TYPE_ICON = 101;
    public static final Integer TYPE_SMALL_PIC = 102;
    public static final Integer TYPE_BIG_PIC = -103;

    // 
    private Integer id;

    // 
    private String relTable;

    // 
    private Integer relId;

    // 
    private Integer type;

    // 
    private Integer seq;

    // 
    private Integer size;

    // 
    private String name;

    // 
    private String contentType;

    //
    private byte[] content;

    // 
    private Date createTime;

    private Integer status;

    //
    private String md5;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "rel_table")
    public String getRelTable() {
        return relTable;
    }

    @Column(name = "rel_id")
    public Integer getRelId() {
        return relId;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    @Column(name = "seq")
    public Integer getSeq() {
        return seq;
    }

    @Column(name = "size")
    public Integer getSize() {
        return size;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public String getMd5() {
        return md5;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRelTable(String relTable) {
        this.relTable = relTable;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
