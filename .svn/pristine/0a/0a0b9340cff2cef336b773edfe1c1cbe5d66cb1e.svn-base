package envir.sys.support;

import org.springframework.beans.factory.InitializingBean;
import pub.dao.sql.dialect.DialectManager;
import pub.dao.sql.dialect.MysqlDialect;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 2015/6/22
 */
public class SysPreInitializer implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        DialectManager.currentDialect = new MysqlDialect();
//        MyBatisDao.defaultValueGenerator = new IdentityGenerator();
    }

}
