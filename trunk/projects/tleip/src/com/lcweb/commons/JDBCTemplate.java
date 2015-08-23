package com.lcweb.commons;

import java.util.List;

public interface JDBCTemplate {
	
	public List jdbcQuery(String sql,String[]params);    
	
	public void executeSql(String sql);
}
