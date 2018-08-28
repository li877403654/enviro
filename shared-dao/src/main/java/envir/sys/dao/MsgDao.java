/*
 * @(#) MsgDao.java Jun 20, 2018
 * Copyright 2018 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.Msg;

/**
* @author zzl
* @version 1.0 Jun 20, 2018
*/
@Repository
@SuppressWarnings("unchecked")
public class MsgDao extends MyBatisDao<Msg> {

	public MsgDao() {
		super(Msg.class);
	}

	public Integer getMaxId() {
		return selectOne("getMaxId");
	}
}
