/*
 * @(#) FileDaoImpl.java Oct 31, 2015
 * Copyright 2015 GPDI. All right reserved.
 */
package envir.sys.daoimpl;

import envir.sys.dao.SysFileDao;
import envir.sys.entity.SysFile;
import org.springframework.stereotype.Repository;
import pub.dao.mybatis.MyBatisDao;

/**
* @author zzl
* @version 1.0 Oct 31, 2015
*/
@Repository
@SuppressWarnings("unchecked")
public class SysFileDaoImpl extends MyBatisDao<SysFile> implements SysFileDao {

	public SysFileDaoImpl() {
		super(SysFile.class);
	}

	@Override
	public void updateRelInfo(Integer fileId, String relTable, Integer relId, Integer type) {
		execute("updateRelInfo", fileId, relTable, relId, type);
	}

    @Override
    public void delete(String relTable, Integer relId) {
        execute("deleteByRelInfo", relTable, relId);
    }

    @Override
    public boolean isVideo(Integer id) {
	    String name = selectOne("getName", id);
        return name.endsWith(".mp4");
    }
}
