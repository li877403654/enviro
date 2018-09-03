package pub.dao.jdbc;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pub.functions.VarFuncs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zzl on 1/6/2016.
 */
public class DbUtils {

    public static <T> T queryValue(JdbcOperations jdbcOperations, Class<T> clazz, String sql, Object... args) {
        try {
            if(clazz.getName().startsWith("java")) {
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

    public static <T> List<T> queryList(JdbcOperations jdbcOperations, Class<T> clazz, String sql, Object... args) {
        if(clazz.getName().startsWith("java")) {
            return jdbcOperations.queryForList(sql, args, clazz);
        }
        else {
            RowMapper rowMapper = new BeanPropertyRowMapper(clazz);
            return jdbcOperations.query(sql, args, rowMapper);
        }
    }

    public static <T> T insert(JdbcOperations jdbcOperations, Class<T> clazz, String sql, Object... args) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"});
                for(int n = 0; n < args.length; n++) {
                    pstmt.setObject(n + 1, args[n]);
                }
                return pstmt;
            }
        }, keyHolder);
        return VarFuncs.to(keyHolder.getKey(), clazz);
    }

    public static int execute(JdbcOperations jdbcOperations, String sql, Object... args) {
        return jdbcOperations.update(sql, args);
    }
}
