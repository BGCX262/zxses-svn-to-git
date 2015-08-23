package com.lcweb.commons;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JDBCTemplateImp extends JdbcDaoSupport implements JDBCTemplate {

	public List jdbcQuery(String sql, String[] params) {
	     
		return this.getJdbcTemplate().queryForList(sql, params);
	
	}
 

	public void executeSql(String sql) {
	   this.getJdbcTemplate().execute(sql);
		
	}
 


}
