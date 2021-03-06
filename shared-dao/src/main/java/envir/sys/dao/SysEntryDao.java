/*
 * @(#) SysEntryDao.java Sep 19, 2017
 * Copyright 2017 GPDI. All right reserved.
 */
package envir.sys.dao;

import envir.sys.entity.SysEntry;
import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;

/**
* @author zzl
* @version 1.0 Sep 19, 2017
*/
@Repository
@SuppressWarnings("unchecked")
public class SysEntryDao extends MyBatisDao<SysEntry> {

	public SysEntryDao() {
		super(SysEntry.class);
	}

}
