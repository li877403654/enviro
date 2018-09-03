/*
 * @(#) SysDictDao.java Aug 25, 2017
 * Copyright 2017 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.SysDict;
import pub.types.IdText;

import java.util.List;

/**
* @author zzl
* @version 1.0 Aug 25, 2017
*/
@Repository
@SuppressWarnings("unchecked")
public class SysDictDao extends MyBatisDao<SysDict> {

	public SysDictDao() {
		super(SysDict.class);
	}

	public List<IdText> list(String cat) {
		return selectList("list", cat);
	}
}
