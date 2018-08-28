package pub.dao.mybatis;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pub.dao.DataSourceAware;
import pub.dao.GeneralDao;
import pub.dao.jdbc.JdbcTemplateAware;
import pub.functions.VarFuncs;
import pub.types.Executable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 12-2-15
 */
public class GeneralDaoImpl extends MyBatisDao<Object> implements GeneralDao {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public GeneralDaoImpl() {
        super(Object.class);
    }

    @Override
    public void execute(Executable executable) {
        if(executable instanceof SqlSessionAware) {
            ((SqlSessionAware)executable).setSqlSession(getSqlSession());
        }
        if(dataSource != null && executable instanceof DataSourceAware) {
            ((DataSourceAware) executable).setDataSource(dataSource);
        }
        else if(jdbcTemplate != null && executable instanceof JdbcTemplateAware) {
            ((JdbcTemplateAware)executable).setJdbcTemplate(jdbcTemplate);
        }
        executable.execute();
    }

    @Override
    public Date getSysDate() {
        return null;
    }

    @Override
    public void flushOperations() {
        //
    }

    @Override
    public void evict(List entities) {
        //
    }

    @Override
    public void evict(Object entity) {
        //
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T queryValue(Class<T> clazz, String sql, Object... args) {
        try {
            JdbcOperations jdbcOperations = jdbcTemplate.getJdbcOperations();
            if(clazz.getName().startsWith("java")) {
                if(clazz.equals(Map.class)) {
                    return (T) jdbcOperations.queryForMap(sql, args);
                }
                return jdbcOperations.queryForObject(sql, args, clazz);
            }
            else {
                RowMapper rowMapper = new BeanPropertyRowMapper(clazz);
                return (T) jdbcOperations.queryForObject(sql, args, rowMapper);
            }
        }
        catch (IncorrectResultSizeDataAccessException e) {
            if(e.getActualSize() == 0) {
                return null;
            }
            else {
                throw e;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> queryList(Class<T> clazz, String sql, Object... args) {
        if(clazz.getName().startsWith("java")) {
            return jdbcTemplate.getJdbcOperations().queryForList(sql, args, clazz);
        }
        else {
            RowMapper rowMapper = new BeanPropertyRowMapper(clazz);
            return jdbcTemplate.getJdbcOperations().query(sql, args, rowMapper);
        }
    }

    @Override
    public int execute(String sql, Object... args) {
        return jdbcTemplate.getJdbcOperations().update(sql, args);
    }

    @Override
    public int insert(String sql, Object... args) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.getJdbcOperations().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                for(int n = 0; n < args.length; n++) {
                    pstmt.setObject(n + 1, args[n]);
                }
                return pstmt;
            }
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public int getNextVal(String seqTable) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into " + seqTable + " values (null)";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});
                return pstmt;
            }
        };
        jdbcTemplate.getJdbcOperations().update(preparedStatementCreator, keyHolder);
        int id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public Integer getTempId() {
        return getNextVal("seq_temp_id");
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
