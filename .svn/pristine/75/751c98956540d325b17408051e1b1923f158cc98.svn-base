package pub.dao.sql;

import pub.dao.query.Sort;
import pub.dao.sql.model.Column;
import pub.dao.sql.model.Columns;
import pub.functions.StrFuncs;
import pub.types.Pair;
import pub.types.SortableData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 12-4-28
 */
public class SqlUtils {

    public static String getOrderBy(Columns columns, SortableData sortableData, String defaultOrderBy) {
        Sort sort = new Sort();
        sort.setSort(sortableData.getSort());
        sort.setDir(sortableData.getDir());
        return getOrderBy(columns, sort, defaultOrderBy);
    }

    public static String getOrderBy(Columns columns, Sort sort, String defaultOrderBy) {
        String sortCol = sort.getColumn();
        if (sortCol == null || sortCol.length() == 0) {
            return defaultOrderBy;
        }
        String result = null;
        for (Column column : columns) {
            String name = column.getAlias();
            if (name == null || name.length() == 0) {
                name = column.getSource();
                int pos = name.indexOf('.');
                if (pos != -1) {
                    name = name.substring(pos + 1);
                }
            }
            if (name.equalsIgnoreCase(sortCol)) {
                result = column.getSource();
                break;
            }
        }
        if (result == null) {
            System.out.println("Warning: sort column not found! " + sort.getColumn());
            return defaultOrderBy;
        }
        if(result.indexOf(' ') != -1 && result.charAt(0) != '(') {
            result = '(' + result + ')';
        }
        if (sort.isDesc()) {
            result += " desc";
        }
        return result;
    }

    public static Pair<String, List<Object>> parseSqlWithParams(String sql) {
        return parseSqlWithParams(sql, true);
    }

    public static Pair<String, List<Object>> parseSqlWithParams(String sql, boolean markParamsUsed)
    {
        int pos1 = sql.indexOf(':');
        if(pos1 == -1) {
            return new Pair<>(sql, Collections.emptyList());
        }
        int sqlLen = sql.length();

        StringBuilder sb = new StringBuilder(sqlLen);
        sb.append(sql.substring(0, pos1));

        SqlParams sqlParams = SqlParams.get();
        List<Object> paramValues = new ArrayList<>();
        while(true) {
            int pos2 = sql.indexOf(":_p", pos1);
            if(pos2 == -1) {
                sb.append(sql.substring(pos1, sqlLen));
                break;
            }
            sb.append(sql.substring(pos1, pos2));
            pos2 += 3;
            int pos3 = pos2;
            while(pos3 < sqlLen && Character.isDigit(sql.charAt(pos3))) {
                ++pos3;
            }
            int sqlParamIndex = Integer.valueOf(sql.substring(pos2, pos3)) - 1;
            Object paramValue = sqlParams.params.get(sqlParamIndex);
            paramValues.add(paramValue);
            sb.append('?');
            pos1 = pos3;
        }
        String sql1 = sb.toString();
        if(markParamsUsed) {
            sqlParams.usedFlag = true;
        }
        return new Pair<>(sql1, paramValues);
    }

}
