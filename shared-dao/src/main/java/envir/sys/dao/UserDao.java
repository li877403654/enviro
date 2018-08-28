/*
 * @(#) UserDao.java Jun 5, 2018
 * Copyright 018 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.User;

/**
* @author zzl
* @version 1.0 Jun 5, 2018
*/
@Repository
@SuppressWarnings("unchecked")
public class UserDao extends MyBatisDao<User> {

	public UserDao() {
		super(User.class);
	}
}
