package com.zx.core.login.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zx.core.exception.ManageException;
import com.zx.core.login.dao.ILoginDAO;
import com.zx.core.model.HrUser;
import com.zx.core.model.HrUserLog;
import com.zx.core.model.HrUserLogType;
import com.zx.core.model.SysMenu;
import com.zx.core.util.DateUtil;
import com.zx.core.util.SysLoggerUtil;
import com.zx.core.vo.Loginer;
import com.zx.core.vo.SysMenuVO;

/**
 * 登陆service实现类：
 * 
 * @author zhoupk
 * @date Jul 6, 2010
 * @Version 1.0
 */
public class LoginService implements ILoginService {

	private ILoginDAO loginDAO;

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	/**
	 * 登陆方法
	 * 
	 * @param username
	 * @param password
	 * @return Loginer
	 * @throws Exception
	 */
	public Loginer login(String username, String password,String ip) throws ManageException {
		Loginer loginer = null;
		List<HrUser> userList = new ArrayList<HrUser>();
		try {
			userList = loginDAO.findUser(username, password);
			if (userList != null && userList.size() != 0) {
				//创建登录信息对象
				HrUser sysUser = (HrUser)userList.get(0);
				loginer = new Loginer();
				loginer.setId(sysUser.getId());
				loginer.setUserAccount(sysUser.getAccount());
				loginer.setUserName(sysUser.getName());
				loginer.setUserType(sysUser.getHrUserType().getCode());
				HrUserLog hrUserLog = this.loginDAO.findLastLoginInfo(sysUser.getAccount());
				if(hrUserLog!=null){
					loginer.setLastLoginIp(hrUserLog.getIp());
					loginer.setLastLoginTime(DateUtil.getInstance().dateTimeToStr(hrUserLog.getCreateTime()));
				}
				
				//写登录日志表
				HrUserLog hu = new HrUserLog();
				HrUserLogType hrUserLogType = new HrUserLogType();
				hrUserLogType.setId(1l);
				hrUserLogType.setCreateTime(new Date());
				hu.setHrUserLogType(hrUserLogType);
				hu.setHrUser(sysUser);
				hu.setContent(sysUser.getName()+"，成功登录。");
				hu.setIp(ip);
				hu.setRecordStatus(1);
				hu.setCreateTime(new Date());
				hu.setUpdateTime(new Date());
				this.loginDAO.save(hu);
			} 
		} catch (Exception e) {
			throw new ManageException("find.failure");
		}
		if (userList == null || userList.size() == 0) {
			//service层需要提示到页面的消息通过throw new ManageException实现
			throw new ManageException("passworderror");
		}
		return loginer;
	}
	
	public List<SysMenu> findRootMenu() throws ManageException {
		List<SysMenu> rootMenus = new ArrayList<SysMenu>();
		try {
			rootMenus = this.loginDAO.findRootMenu();
		} catch (Exception e) {
		    throw new ManageException("查询顶级菜单出错");
		}
		if(rootMenus.size()==0){
			throw new ManageException("顶级菜单没数据");
		}
		return rootMenus;
	}
	
	 /***
     * 查询出当前登录用户所有根菜单
     * @param loginer
     * @return
     * @throws Exception
     */
    public List<SysMenu> findLongRootMenu(Loginer loginer)throws ManageException{
    	List<SysMenu> rootMenus = new ArrayList<SysMenu>();
		try {
			if("administrator".equals(loginer.getUserType())){
				rootMenus = this.loginDAO.findRootMenu();
			}else{
				rootMenus = this.loginDAO.findLongRootMenu(loginer);
			}
		} catch (Exception e) {
		    throw new ManageException("查询顶级菜单出错");
		}
		if(rootMenus.size()==0){
			throw new ManageException("顶级菜单没数据");
		}
		return rootMenus;
    }
	
	public SysMenuVO findMenuById(String menuid,Loginer loginer) throws ManageException {
		SysMenuVO menuVO = new SysMenuVO();
		try {
			SysMenu sysMenu= (SysMenu)this.loginDAO.load(SysMenu.class, Long.parseLong(menuid));
			menuVO.setId(sysMenu.getId());
			menuVO.setCode(sysMenu.getCode());
			menuVO.setName(sysMenu.getName());
			menuVO.setUrl(sysMenu.getUrl());
			List<SysMenu> userMenuList = this.loginDAO.findUserMenu(loginer);
			for(SysMenu sm : userMenuList){
				if(sm.getSysMenu()!=null&&sm.getSysMenu().getId().equals(menuVO.getId())){
					SysMenuVO menuChildVO = new SysMenuVO();
					menuChildVO.setId(sm.getId());
					menuChildVO.setCode(sm.getCode());
					menuChildVO.setName(sm.getName());
					menuChildVO.setUrl(sm.getUrl());
					menuVO.getChildList().add(menuChildVO);
					for(SysMenu child:userMenuList){
						if(child.getSysMenu()!=null&&child.getSysMenu().getId().equals(menuChildVO.getId())){
							SysMenuVO menuChildVO2 = new SysMenuVO();
							menuChildVO2.setId(child.getId());
							menuChildVO2.setCode(child.getCode());
							menuChildVO2.setName(child.getName());
							menuChildVO2.setUrl(child.getUrl());
							menuChildVO.getChildList().add(menuChildVO2);
						}
					}
					
					/*************************************修改树形结构***************************************/
					if(menuChildVO.getChildList()!=null&&menuChildVO.getChildList().size()==1){
						SysMenuVO menuChildVO2 =(SysMenuVO) menuChildVO.getChildList().get(0);
						menuChildVO2.setImgSrc("leftnoline");
					}else{
						if(menuChildVO.getChildList()!=null&&menuChildVO.getChildList().size()>0){
							SysMenuVO menuChildVO2 =(SysMenuVO) menuChildVO.getChildList().get(0);
							menuChildVO2.setImgSrc("lefttopline");
							for(int i=1;i<menuChildVO.getChildList().size()-1;i++){
								SysMenuVO menuChildVO3 =(SysMenuVO) menuChildVO.getChildList().get(i);
								menuChildVO3.setImgSrc("leftcenterline");
							}
							SysMenuVO menuChildVO4 =(SysMenuVO) menuChildVO.getChildList().get(menuChildVO.getChildList().size()-1);
							menuChildVO4.setImgSrc("leftbottomline");
						}
					}
					
				}
			}
		} catch (Exception e) {
			throw new ManageException("查询左边菜单出错");
		}
		return menuVO;
	}
	
	/**
     * 查询菜单，封装成导航条需要的数据
     * @param id
     * @throws ManageException
     * @Description:
     */
    public SysMenu findMenu(Long id) throws ManageException {
	SysMenu sysMenu = new SysMenu();
	try {
	    sysMenu = (SysMenu) loginDAO.load(SysMenu.class, id);
	    String relational = sysMenu.getLevel();

	    String[] str = relational.split("_");
	    String ids = "";
	    if (str != null && str.length != 0) {
		for (int i = 0; i < str.length; i++) {
		    ids = ids + str[i] + ",";
		}
	    }
	    ids = ids.substring(0, ids.length() - 1);

	    List<SysMenu> list = loginDAO.findMenus(ids);
	    String bar = "首页->";
	    for (int i = 0; i < str.length; i++) {
			for (SysMenu menu : list) {
			    if (str[i].equals(menu.getId().toString())) {
				bar = bar + menu.getName() + "->";
				break;
			    }
			}
	    }
	    sysMenu.setNavigationBar(bar.substring(0, bar.length() - 2));
	} catch (Exception e) {
	    throw new ManageException("查询导航菜单出错");
	}
	return sysMenu;
    }
    
    /**
	 * 查询旧密码：
	 * @return
	 * @throws Exception
	 */
	public List<HrUser> findHrUsers(String userAccount, String oldpassword) throws ManageException {
		List<HrUser> userList = new ArrayList<HrUser>();
		try {
			userList = loginDAO.findHrUsers(userAccount, oldpassword); 
		} catch (Exception e) {
		    throw new ManageException("查询用户信息出错");
		}
		return userList;
	}

	/**
	 * 修改密码
	 * @param sysUser
	 * @throws ManageException   
	 * @Description:
	 */
	public void update(HrUser hrUser)throws ManageException {
		try {
			loginDAO.update(hrUser);
		} catch (Exception e) {
		    throw new ManageException("修改用户密码出错");
		}
	}
    
	
}
