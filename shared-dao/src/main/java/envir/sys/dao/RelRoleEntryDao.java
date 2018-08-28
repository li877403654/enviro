/*
 * @(#) RelRoleEntryDao.java Sep 19, 2017
 * Copyright 2017 GPDI. All right reserved.
 */
package envir.sys.dao;

import envir.sys.entity.RelRoleEntry;
import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;

import java.util.List;

/**
* @author zzl
* @version 1.0 Sep 19, 2017
*/
@Repository
@SuppressWarnings("unchecked")
public class RelRoleEntryDao extends MyBatisDao<RelRoleEntry> {

	public RelRoleEntryDao() {
		super(RelRoleEntry.class);
	}

	public List<Integer> listRoleEntryIds(Integer id) {
		return selectList("listRoleEntryIds", id);
	}
}
