package com.agilefly.service.sysmodule;

import com.agilefly.bean.SysModule;
import com.agilefly.commons.QueryResult;
import com.agilefly.service.base.BaseDao;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:38:38 PM
 */
public interface ISysModuleService extends BaseDao<SysModule> {
	/**
	 * 添加模块信息，如果父模块的id为0，则添加顶级模块
	 * @param module 模块信息
	 * @param parentId 父模块的ID
	 */
	public void addSysModule(SysModule module, int parentId);
	
	public void delSysModule(int moduleId);
	
	public void updateSysModule(SysModule module, int parentId);
	
	/**
	 * 分页查询模块信息
	 * @param parentId
	 */
	public QueryResult<SysModule> searchSysModules(int parentId);
}
