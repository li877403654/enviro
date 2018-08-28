/*
 * @(#) RelRoleEntry.java Sep 19, 2017
 * Copyright  2017 GPDI. All right reserved.
 */
package envir.sys.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zzl
 * @version 1.0 Sep 19, 2017
 */
@Entity
@Table(name = "rel_role_entry")
public class RelRoleEntry implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private Integer roleId;

    // 
    private Integer entryId;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }

    @Column(name = "entry_id")
    public Integer getEntryId() {
        return entryId;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

}
