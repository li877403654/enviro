/*
 * @(#) SysUserDao.java Aug 25, 2017
 * Copyright 2017 GPDI. All right reserved.
 */
package envir.sys.dao;

import envir.sys.entity.SysUser;
import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;

/**
* @author zzl
* @version 1.0 Aug 25, 2017
*/
@Repository
@SuppressWarnings("unchecked")
public class SysUserDao extends MyBatisDao<SysUser> {

	public SysUserDao() {
		super(SysUser.class);
	}

	public SysUser getByAccount(String account) {
		return selectOne("getByAccount", account);
	}

	public String getName(Integer id) {
		return selectOne("getName", id);
	}

	public String getPassword(Integer id) {
		return selectOne("getPassword", id);
	}

	public Integer getIdByAccount(String account) {
		return selectOne("getIdByAccount", account);
	}
}
