package pub.dao.jdbc;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zzl.
 * Date: 2017-08-20
 */
public class LowercaseColumnMapRowMapper extends ColumnMapRowMapper {

    private boolean metaInited = false;
    private int columnCount = 0;
    private String[] columnNames = null; //1 based

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        if(!metaInited) {
            initMeta(rs);
        }
        Map<String, Object> mapOfColValues = new HashMap<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            String key = columnNames[i];
            Object obj = getColumnValue(rs, i);
            mapOfColValues.put(key, obj);
        }
        return mapOfColValues;
    }

    private void initMeta(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();
        columnNames = new String[columnCount + 1];
        for (int i = 1; i <= columnCount; i++) {
            String key = JdbcUtils.lookupColumnName(rsmd, i);
            columnNames[i] = key.toLowerCase();
        }
        metaInited = true;
    }

}
