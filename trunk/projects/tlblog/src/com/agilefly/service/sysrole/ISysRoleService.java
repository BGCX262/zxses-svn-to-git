package com.agilefly.service.sysrole;

import com.agilefly.bean.SysRole;
import com.agilefly.service.base.BaseDao;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:39:04 PM
 */
public interface ISysRoleService extends BaseDao<SysRole> {
	/**
	 * 检查角色名是否已存在
	 * @param roleName
	 * @return
	 */
	public boolean isRoleNameExist(String roleName);
}
