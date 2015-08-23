package com.zx.core.login.dao;

import java.util.List;

import com.zx.core.base.dao.IBaseDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;
import com.zx.core.model.SysMenu;
import com.zx.core.vo.Loginer;

public interface ILoginDAO extends IBaseDAO {
	/**
	 * 登陆方法
	 * 
	 * @param username
	 * @param password
	 * @return List 
	 * @throws Exception
	 */
	public List<HrUser> findUser(String username, String password) throws Exception ;

    
    public List<SysMenu> findRootMenu() throws Exception ;
    
    /***
     * 查询出当前登录用户所有根菜单
     * @param loginer
     * @return
     * @throws Exception
     */
    public List<SysMenu> findLongRootMenu(Loginer loginer)throws Exception;
    
    public List<SysMenu> findUserMenu(Loginer loginer) throws Exception ;
    /**
	 * IDS查询菜单
	 * @param ids
	 * @return
	 * @throws Exception
	 * @Description:
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> findMenus(String ids) throws Exception ;
	/**
     * 查询最后登录信息
     * @param userAccount
     * @return
     * @throws Exception
     */
	public HrUserLog findLastLoginInfo(String userAccount) throws Exception ;
	/**
	 * 查询旧密码：
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String account, String oldpassword) throws Exception ;
    
}
