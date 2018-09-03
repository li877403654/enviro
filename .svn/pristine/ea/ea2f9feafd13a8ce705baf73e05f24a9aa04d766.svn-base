package envir.sys.service;

import envir.sys.dao.SysFileDao;
import envir.sys.entity.SysFile;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pub.dao.GeneralDao;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zzl on 2015/11/6.
 */
@Service
@Transactional(readOnly = true)
public class SysFileService {

    public SysFile get(Integer id) {
        return sysFileDao.get(id);
    }


    public Serializable saveEntity(SysFile sysFile) {
        return sysFileDao.save(sysFile);
    }

    @Transactional
    public Integer savePcImage(String base64) {
        try {
            byte[] bytes = Base64.decodeBase64(getRealImgSrc(base64));
            return saveToSysFile(bytes, "ph.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRealImgSrc(String base) {
        if (base != null) {
            base = base.replace("data:image/jpeg;base64,", "");
            base = base.replace("data:image/png;base64,", "");
            base = base.replace("data:image/gif;base64,", "");
        } else {
            base = "";
        }
        return base;
    }

    public Integer saveToSysFile(byte[] PhotoFileContent, String photoFileName) {
        SysFile sysFile = new SysFile();
        sysFile.setRelTable("0");
        sysFile.setContent(PhotoFileContent);

        if (photoFileName == null || photoFileName.equals("")) {
            sysFile.setName("default");
        } else {
            sysFile.setName(photoFileName);
        }
        sysFile.setContentType("user profile");
        sysFile.setRelId(0);
        sysFile.setSize(PhotoFileContent.length);

        sysFile.setType(0);
        sysFile.setSeq(0);
        sysFile.setCreateTime(new Date());
        sysFileDao.save(sysFile);
        return sysFile.getId();
    }

    @Transactional
    public Integer saveAppImage(MultipartFile file) {
        SysFile sysFile = new SysFile();
        try {
            sysFile.setContent(file.getBytes());//?
            sysFile.setRelTable("0");
            sysFile.setName("default");
            sysFile.setContentType(file.getContentType());
            sysFile.setRelId(0);
            sysFile.setSize((int) file.getSize());
            sysFile.setType(0);
            sysFile.setSeq(0);
            sysFile.setCreateTime(new Date());
            sysFileDao.save(sysFile);
            return sysFile.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeSysFileById(Integer id) {
        sysFileDao.delete(id);
    }

    @Autowired
    private SysFileDao sysFileDao;

    @Autowired
    private GeneralDao generalDao;
}
