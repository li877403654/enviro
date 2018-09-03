/*
* @(#) FileDao.java Oct 31, 2015
* Copyright 2015 GPDI. All right reserved.
*/
package envir.sys.dao;

import envir.sys.entity.SysFile;
import pub.dao.Dao;

/**
* @author zzl
* @version 1.0 Oct 31, 2015
*/
public interface SysFileDao extends Dao<SysFile> {

    void updateRelInfo(Integer fileId, String relTable, Integer relId, Integer type);

    void delete(String relTable, Integer relId);

    boolean isVideo(Integer id);

}
