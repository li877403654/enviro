/*
 * @(#) SysConfDao.java Jul 2, 2018
 * Copyright 018 GPDI. All right reserved.
 */
package envir.sys.dao;

import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;
import envir.sys.entity.SysConf;

/**
 * @author zzl
 * @version 1.0 Jul 2, 2018
 */
@Repository
@SuppressWarnings("unchecked")
public class SysConfDao extends MyBatisDao<SysConf> {

    public SysConfDao() {
        super(SysConf.class);
    }

    public int getIntValue(String cat, String name, int defValue) {
        Integer value = selectOne("getIntValue", cat, name);
        return value == null ? defValue : value;
    }

    public Double getNumValue(String cat, String name, Double defValue) {
        Double value = selectOne("getNumValue", cat, name);
        return value == null ? defValue : value;
    }
}
