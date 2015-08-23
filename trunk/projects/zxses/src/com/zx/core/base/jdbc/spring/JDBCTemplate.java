package com.zx.core.base.jdbc.spring;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface JDBCTemplate {

    /**
     * 
     * @param sql
     *            原始sql语句
     * @param params
     *            填入？的参数，没有则为null
     * @return 返回记录集合，每条记录为一个Map,每列取值用map.get("查询列名")
     * @Description:jdbc 查询语句，
     */
    public List find(String sql, String[] params) throws Exception;

    /**
     * 
     * @param sql
     * @Description:执行原始sql语句，用于更新，删除等没有返回值的语句
     */
    public void executeSql(String sql) throws Exception;

    /**
     * 
     * @param sql
     *            原始sql语句
     * @param startRow
     *            开始记录值
     * @param rowsCount
     *            查询多少记录
     * @return返回记录集合，每条记录为一个Map,每列取值用map.get("查询列名")
     * @throws DataAccessException
     * @Description:原始sql，分页查询
     */
    public List<Map> findByPage(String sql, int startRow, int rowsCount)
            throws DataAccessException;

    /**
     * 
     * @param sql
     *            原始sql语句
     * @return 记录条数
     * @throws Exception
     * @Description:查询表总记录条数，用于分页查询，查总记录
     */
    public Integer findTotalCount(String sql) throws Exception;

    public Long findForLong(String sql) throws Exception;
}
