/*
 * @(#) SysRegionDao.java Jun 11, 2018
 * Copyright 2018 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.SysRegion;

/**
* @author zzl
* @version 1.0 Jun 11, 2018
*/
@Repository
@SuppressWarnings("unchecked")
public class SysRegionDao extends MyBatisDao<SysRegion> {

	public SysRegionDao() {
		super(SysRegion.class);
	}

	public String getName(Integer id) {
		return selectOne("getName", id);
	}
}
