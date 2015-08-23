package com.zx.core.base.jdbc.spring;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class JDBCTemplateImp extends JdbcTemplate implements JDBCTemplate {

    public List find(String sql, String[] params) throws Exception {
        return this.queryForList(sql, params);

    }

    public void executeSql(String sql) throws Exception {
        this.execute(sql);
    }

    public List<Map> findByPage(String sql, int startRow, int rowsCount)
            throws DataAccessException {
        startRow++;
        return findSP(sql, startRow, rowsCount, getColumnMapRowMapper());
    }

    public List<Map> findSP(String sql, int startRow, int rowsCount,
            RowMapper rowMapper) throws DataAccessException {
        return (List) query(sql, new SplitPageResultSetExtractor(rowMapper,
                startRow, rowsCount));
    }

    public Integer findTotalCount(String sql) throws Exception {
        Integer result = this.queryForInt(sql);
        return result;
    }

    public Long findForLong(String sql) throws Exception {
        return this.queryForLong(sql);
    }

}
