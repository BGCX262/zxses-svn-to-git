package com.agilefly.service.sysprivilege;

import java.util.List;

import com.agilefly.bean.SysPrivilege;
import com.agilefly.service.base.BaseDao;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:38:49 PM
 */
public interface ISysPrivilegeService extends BaseDao<SysPrivilege> {
	/**
	 * 批量保存权限
	 * @param privileges
	 */
	public void batchSave(List<SysPrivilege> privileges);
}
