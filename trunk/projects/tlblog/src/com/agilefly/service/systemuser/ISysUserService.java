package com.agilefly.service.systemuser;

import com.agilefly.bean.SysUser;
import com.agilefly.service.base.BaseDao;

/**
 * @author boleyn_renlei
 * @date May 18, 2012 10:39:16 PM
 * 包含用户博客信息的操作
 */
public interface ISysUserService extends BaseDao<SysUser> {
	public SysUser findByUnamePass(String username,String password);
	
	public SysUser findByUnamePassType(String username, String password, String userType);
	
	/**
	 * 获取指定用户名的用户信息(包含博客信息)
	 * @param username
	 * @return
	 */
	public SysUser findByUname(String username);
	
	/**
	 * 检查用户是否已经存在
	 * @param username
	 * @return
	 */
	public boolean checkUnameExist(String username);
	
	/**
	 * 审批
	 */
	public int approve(String[] ids);
	
	/**
	 * 反审批
	 */
	public int unApprove(String[] ids);
}
