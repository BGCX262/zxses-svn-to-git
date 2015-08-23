package com.zx.core.system.set.dao;   

import java.util.List;

import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.SysLog;
import com.zx.core.model.SysParameter;
  
public interface IConfigSetDAO extends IBaseDAO<SysLog> {
	/**   
	 * @return   
	 * @Description: 查询配置
	 * @param 
	 */
	public List<SysParameter> findSysParameters() throws Exception;
	
	/**
	 * @return
	 * @Description: 修改配置
	 * @param
	 */
	public void updateSysParameters(String code, String content) throws Exception;
}
