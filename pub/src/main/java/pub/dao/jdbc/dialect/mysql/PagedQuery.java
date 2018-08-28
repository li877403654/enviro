package pub.dao.jdbc.dialect.mysql;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import pub.dao.jdbc.JdbcTemplateQuery;
import pub.dao.query.PageSettings;
import pub.dao.query.PagedQueryResult;
import pub.dao.query.QuerySettings;
import pub.functions.StrFuncs;

import java.util.List;
import java.util.Map;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 12-2-15
 */
@SuppressWarnings("unchecked")
public class PagedQuery<T> extends JdbcTemplateQuery {

    private PageSettings settings;
    private PagedQueryResult result;
    private boolean quietLog;

    public PagedQuery() {
        settings = PageSettings.of(1, 10);
        result = new PagedQueryResult();
        quietLog = false;
    }

    public PagedQuery(QuerySettings settings) {
        this();
        applySettings(settings);
    }

    public static <T1> PagedQuery<T1> of(Class<T1> clazz) {
        PagedQuery<T1> query = new PagedQuery<T1>();
        query.setResultBeanClass(clazz);
        return query;
    }

    private int calculateRowCount() {
        long startTick = System.currentTimeMillis();

        String countSql = constructCountSql();
        int count = jdbcTemplate.queryForObject(countSql, params, Integer.class);

        if(!quietLog) {
            System.out.println("Count: " + (System.currentTimeMillis() - startTick));
        }
        return count;
    }

    public void execute() {

        int rowCount = calculateRowCount();
        result.setRowCount(rowCount);

        result.setPageSize(settings.getPageSize());
        int pageNo = settings.getPageNo();

        if (pageNo <= 0 || pageNo > result.getPageCount()) {
            pageNo = result.getPageCount();
        }

        result.setPageNo(pageNo);

        //
        long startTick = System.currentTimeMillis();

        String sql = getSql(true);
        sql = constructPagedSql(sql);
        int offset = settings.getPageSize() * (pageNo - 1);
        if(offset < 0) {
            offset = 0;
        }

        MapSqlParameterSource paramsSource = new MapSqlParameterSource(params);
        paramsSource.addValue("offset_", offset);
//        paramsSource.addValue("endRow", offset + settings.getPageSize() - 1);
        paramsSource.addValue("limit_", settings.getPageSize());

        RowMapper<T> rowMapper = createRowMapper(settings, resultBeanClass);
        List<T> list = jdbcTemplate.query(sql, paramsSource, rowMapper);

        if(!quietLog) {
            System.out.println("List: " + (System.currentTimeMillis() - startTick));
        }

        result.setRows(list);

        if(settings.isGenerateRowNo()) {
            generateRowNo();
        }

    }

    private void generateRowNo() {
        int rowNo = (result.getPageNo() - 1) * result.getPageSize() + 1;
        List<Map<String, Object>> rows = result.getRows();
        for(Map<String, Object> row: rows) {
            row.put("_rn", rowNo);
            ++rowNo;
        }
    }

    private String constructPagedSql(String listsql) {
        StringBuilder sql = new StringBuilder();
        if(outerSelect != null) {
            //throw new RuntimeException("Outer select is not supported!");
                sql.append("select ");
                sql.append(outerSelect).append(" from (");
                sql.append("select A.* from (");
                sql.append(listsql);
                sql.append(") A limit :limit_ offset :offset_");
                sql.append(") x");
        }
        else {
            sql.append(listsql);
            sql.append(" limit :limit_ offset :offset_");
        }
        return sql.toString();
    }

    private String constructCountSql() {
    	String strTabName = "x";//UUID.randomUUID().toString(); //Dr.Li
        StringBuilder sb = new StringBuilder();
        boolean subQueryCount = distinct || sql != null || groupBy != null;
        if (subQueryCount) {
            sb.append("select count(1) as ct from (");
            if (groupBy != null) {
                sb.append(getCountSqlOfGroup(false));
            } else {
                sb.append(getSql(false));
            }
            sb.append(") as ").append(strTabName);
        } else {
            sb.append("select count(1) as ct from ").append(from);
            if (StrFuncs.notEmpty(where)) {
                sb.append(" where ").append(where);
            }
            
        }
        return sb.toString();
    }

    @Override
    public PagedQueryResult<T> getResult() {
        return result;
    }

    @Override
    public QuerySettings getSettings() {
        return settings;
    }

    @Override
    public void applySettings(QuerySettings pSettings) {
        PageSettings settings = (PageSettings) pSettings;
        this.settings.apply(settings);
    }

    @Override
    public void setQuietLog() {
        quietLog = true;
    }
}
