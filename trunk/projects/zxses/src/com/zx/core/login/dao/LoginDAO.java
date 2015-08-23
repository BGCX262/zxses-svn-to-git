package com.zx.core.login.dao;

import java.util.List;

import com.zx.core.base.dao.BaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;
import com.zx.core.model.SysMenu;
import com.zx.core.util.MD5Util;
import com.zx.core.vo.Loginer;

public class LoginDAO extends BaseDAO implements ILoginDAO {
	
	/**
	 * 登陆方法
	 * 
	 * @param username
	 * @param password
	 * @return List 
	 * @throws Exception
	 */
	public List<HrUser> findUser(String username, String password) throws Exception {
		// 以下是查询人员表：
		String[] values = new String[] { username,MD5Util.generatePassword(password) };
		String userHql = "from HrUser  user where user.account=? and user.password = ? and user.isEnable=1 and user.recordStatus=1 ";
		List<HrUser> userList = this.getHibernateTemplate().find(userHql, values);
		return userList;
	}

  
    /**
     * 查询top页面的菜单
     * @return
     * @throws Exception
     */
    public List<SysMenu> findRootMenu() throws Exception {
		String hql = " from SysMenu sm where sm.sysMenu is null and sm.isEnable=1 and sm.recordStatus=1 order by sm.recordOrder asc";
		return this.getHibernateTemplate().find(hql, null);
	}
    
    /***
     * 查询出当前登录用户所有根菜单
     * @param loginer
     * @return
     * @throws Exception
     */
    public List<SysMenu> findLongRootMenu(Loginer loginer)throws Exception{
    	String hql="select distinct rm.sysMenu from SysRoleMenu rm,HrUserRole ur where rm.sysMenu.isEnable=1 and rm.recordStatus=1 and " +
		"ur.recordStatus=1 and rm.sysMenu.recordStatus=1" +
		" and rm.sysRole.recordStatus=1  and rm.sysRole.id=ur.sysRole.id and ur.hrUser.account='"+loginer.getUserAccount()+"' and rm.sysMenu.sysMenu is null" +
				" order by rm.sysMenu.recordOrder asc";
    	return this.getHibernateTemplate().find(hql);
    }
    
    public List<SysMenu> findUserMenu(Loginer loginer) throws Exception {
		String hql = "from SysMenu sm where sm.isEnable=1 and sm.recordStatus=1  order by sm.recordOrder asc";
		if(!"administrator".equals(loginer.getUserType())){
			hql = "select distinct rm.sysMenu from SysRoleMenu rm,HrUserRole ur where rm.sysMenu.isEnable=1 and rm.recordStatus=1 and " +
					"ur.recordStatus=1 and rm.sysMenu.recordStatus=1" +
					" and rm.sysRole.recordStatus=1  and rm.sysRole.id=ur.sysRole.id and ur.hrUser.account='"+loginer.getUserAccount()+"'  order by rm.sysMenu.recordOrder asc";
		}
		return this.getHibernateTemplate().find(hql, null);
	}
    
    /**
	 * IDS查询菜单
	 * @param ids
	 * @return
	 * @throws Exception
	 * @Description:
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> findMenus(String ids) throws Exception {
		String hql = "from SysMenu sm where sm.id in (" + ids
				+ ") and sm.recordStatus=1";
		return this.find(hql, null);
	}
    /**
     * 查询最后登录信息
     * @param userAccount
     * @return
     * @throws Exception
     */
	public HrUserLog findLastLoginInfo(String userAccount) throws Exception {
		String hql = " from HrUserLog hu where hu.recordStatus=1 and hu.hrUser.account=? order by createTime desc";  
		HrUserLog sll = null;
		String[] values = new String[] { userAccount};
		List list = this.find(hql,values);
		if(list.size()>0){
			sll = (HrUserLog)list.get(0);
		}
		return sll;
	}
	/**
	 * 查询旧密码：
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String account, String oldpassword) throws Exception {
		String[] values = new String[] { account,MD5Util.generatePassword(oldpassword) };
		String userHql = "from HrUser  su where su.account=?  and su.password = ?";
		List<HrUser> userList = this.find(userHql, values);
		return userList;
	} 
    
}
