package com.zx.core.base.jdbc.SQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import com.zx.core.base.jdbc.SQL.SQLCommandBuilder.SQLCommandType;

import common.Logger;

public class ExecuteSqlDb {

    protected final static Logger logger = Logger.getLogger(ExecuteSqlDb.class);
    private Connection conn = null; // 数据库连接对象
    private PreparedStatement ps = null; // 预编译的SQL命令执行对象
    private ResultSet rs = null; // 结果集对象

    /**
     * executeInsert 执行SQL添加数据命令的方法
     * @param tableName 要操作的特定数据库表名称
     * @param columnName  要操作的特定数据库列名称清单(Pascal命名法)
     * @param param SQL参数列表
     * @return true-成功/false-失败
     */
    public boolean executeInsert(String tableName, String[] columnName, Object[] param) {
	int rowCount = 0; // 保存执行SQL插入数据命令后受影响的行数
	try {

	    // 获取数据库连接对象
	    this.conn = DBConnection.getInstance().getConnect();
	    // 获取预编译SQL语句执行对象并根据参数自动构造SQL命令字串
	    this.ps = this.conn.prepareStatement(SQLCommandBuilder.getInstance().getSQLCommand(SQLCommandType.INSERT, tableName, columnName, param, null));

	    // 自动映射SQL参数
	    if (param != null && param.length > 0) {
		this.ps = SQLParamHelper.JavaParam2SQLParam(param, this.ps);
	    }

	    // 执行SQL更新命令并保存返回的受影响行数
	    rowCount = this.ps.executeUpdate();

	} catch (SQLException ex) {

	    System.err.println("异常信息：执行SQL添加命令时发生错误！\r\n" + ex.getMessage());

	} finally {

	    // 释放资源
	    this.releaseResource();

	}

	return rowCount > 0;

    }

    public List findByHql(String sqlstr, String beginrow, String endrow, Class<?> pojoClass) {
	List pojoSet = null;
	conn = DBConnection.getInstance().getConnect();
	try {
	    ps = conn.prepareStatement(sqlstr);
	    rs = ps.executeQuery();
	    ResultSetMetaData rsmd = rs.getMetaData();
	    while (rs.next()) {
		pojoClass = SQLParamHelper.SQLParam2JavaParam(rsmd, pojoClass, rs);
		pojoSet.add(pojoClass);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    releaseResource();
	}
	return null;
    }

    /**
     * releaseResource 释放所有数据库访问对象资源
     */
    private void releaseResource() {
	if (this.rs != null) {
	    try {
		this.rs.close();
	    } catch (SQLException ex) {
		System.err.println("异常信息：关闭结果集对象错误！\r\n" + ex.getMessage());
	    }
	}
	if (this.ps != null) {
	    try {
		this.ps.close();
	    } catch (SQLException ex) {
		System.err.println("异常信息：关闭SQL命令执行对象错误！\r\n" + ex.getMessage());
	    }
	}
	if (this.conn != null) {
	    try {
		if (!this.conn.isClosed()) {
		    this.conn.close();
		}
	    } catch (SQLException ex) {
		System.err.println("异常信息：关闭数据库连接错误！\r\n" + ex.getMessage());
	    }
	}
    }

}
