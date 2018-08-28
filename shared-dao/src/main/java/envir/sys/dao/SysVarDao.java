/*
 * @(#) SysVarDao.java Aug 25, 2017
 * Copyright 2017 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.SysVar;

/**
* @author zzl
* @version 1.0 Aug 25, 2017
*/
@Repository
@SuppressWarnings("unchecked")
public class SysVarDao extends MyBatisDao<SysVar> {

	public SysVarDao() {
		super(SysVar.class);
	}

	public String getValue(String name) {
		return selectOne("getValue", name);
	}

	public Integer getIdByName(String name) {
		return selectOne("getIdByName", name);
	}

	public SysVar getByName(String name) {
		return selectOne("getByName", name);
	}
}
