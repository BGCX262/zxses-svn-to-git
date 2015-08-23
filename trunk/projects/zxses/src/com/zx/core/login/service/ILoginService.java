package com.zx.core.login.service;

import java.util.List;

import com.zx.core.exception.ManageException;
import com.zx.core.model.HrUser;
import com.zx.core.model.SysMenu;
import com.zx.core.vo.Loginer;
import com.zx.core.vo.SysMenuVO;

public interface ILoginService {

	/**
	 * 登陆方法
	 * 
	 * @param username
	 * @param password
	 * @return Loginer
	 * @throws Exception
	 */
	public Loginer login(String username, String password,String ip) throws ManageException ;
    
    public List<SysMenu> findRootMenu() throws ManageException ;
    
    /***
     * 查询出当前登录用户所有根菜单
     * @param loginer
     * @return
     * @throws Exception
     */
    public List<SysMenu> findLongRootMenu(Loginer loginer)throws ManageException;
    
    public SysMenuVO findMenuById(String menuid,Loginer loginer) throws ManageException ;
    /**
     * 查询菜单，封装成导航条需要的数据
     * @param id
     * @throws ManageException
     * @Description:
     */
    public SysMenu findMenu(Long id) throws ManageException;
    /**
	 * 查询旧密码：
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String userAccount, String oldpassword) throws ManageException ;
	/**
	 * 修改密码
	 * @param sysUser
	 * @throws ManageException   
	 * @Description:
	 */
	public void update(HrUser hrUser)throws ManageException ;

}
