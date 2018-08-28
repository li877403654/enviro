package pub.dao.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzl on 1/6/2016.
 */
public class SqlParams {

    boolean usedFlag = false;
    List params = new ArrayList<Object>();

    private static ThreadLocal<SqlParams> _tls = new ThreadLocal<SqlParams>() {
        @Override
        protected SqlParams initialValue() {
            return new SqlParams();
        }
    };

    public static SqlParams get() {
        return _tls.get();
    }

}

