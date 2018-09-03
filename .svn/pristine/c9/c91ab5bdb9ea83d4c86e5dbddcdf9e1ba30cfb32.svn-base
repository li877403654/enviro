/*
 * @(#) RelUserRole.java Sep 19, 2017
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
@Table(name = "rel_user_role")
public class RelUserRole implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 
    private Integer id;

    // 
    private Integer userId;

    // 
    private Integer roleId;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    @Column(name = "role_id")
    public Integer getRoleId() {
        return roleId;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
