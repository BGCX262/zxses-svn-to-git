package com.zx.core.login.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.zx.core.base.action.BaseAction;
import com.zx.core.exception.ManageException;
import com.zx.core.login.service.ILoginService;
import com.zx.core.model.HrUser;
import com.zx.core.model.SysMenu;
import com.zx.core.util.DESUtil;
import com.zx.core.util.MD5Util;
import com.zx.core.vo.Loginer;

/**
 * 登陆action
 * 
 * @author zhoupk
 * @date Jul 6, 2010
 * @Version 1.0
 */
public class LoginAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private ILoginService loginService;
    
    private List<SysMenu> rootMenuList;

	private String username;

	private String password;
	
	private String agnewpassword;
	
	private String newpassword;

    private String url;
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysMenu> getRootMenuList() {
		return rootMenuList;
	}

	public void setRootMenuList(List<SysMenu> rootMenuList) {
		this.rootMenuList = rootMenuList;
	}

	/**
     * 登陆方法： action层不抛任何异常
     * 
     * @return
     */
	@SkipValidation
    public String login() {
    	HttpSession session = this.getRequest().getSession();
    	session.removeAttribute("loginer");
		session = this.getRequest().getSession(true);
		if ((getUsername() == null)|| "".equals(getUsername().trim()) ) {
			this.addActionMessage(getText("account.null"));
			return "loginfail";
		} 
		if ((getPassword() == null) || "".equals(getPassword().trim())) {
			this.addActionMessage(getText("password.null"));
			return "loginfail";
		}
		try {
			//拦截所有service层的异常
			loginer = loginService.login(getUsername(), getPassword(),this.getRequest().getRemoteAddr());
			loginer.setCurrentLoginIp(this.getRequest().getRemoteAddr());
		} catch (ManageException e) {
			logger.saveErrorLog(getText("passworderror"), e, getCurrUserInfo(getRequest()));
			//通过消息显示前台页面
			this.addActionMessage(getText("passworderror"));
			return "loginfail";
		}
		session.setAttribute("loginer", loginer);
		return "main";
    }
	@SkipValidation
    public String top(){
		try {
			loginer = (Loginer)this.getRequest().getSession().getAttribute("loginer");
			rootMenuList =  this.loginService.findLongRootMenu(loginer);
			if(rootMenuList.size()>0){
				this.getRequest().setAttribute("sysMenu", rootMenuList.get(0));
			}
			this.getRequest().setAttribute("rootMenuList", rootMenuList);
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage(getText("find.failure"));
		}
		return "top";	
	}
	@SkipValidation
    public String leftTree(){
		String menuid = this.getRequest().getParameter("menuid");
		try {
			loginer = (Loginer)this.getRequest().getSession().getAttribute("loginer");
			List list = this.loginService.findMenuById(menuid,this.loginer).getChildList();
			this.getRequest().setAttribute("menu", this.loginService.findMenuById(menuid,this.loginer));
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage(getText("find.failure"));
		}
		return "left";	
	}
    
    /**
	 * 设置导航条
	 * @return   
	 * @Description:
	 */
	@SkipValidation
	public String jumpURL(){
		try {
			SysMenu sysmenu = loginService.findMenu(Long.parseLong(this.getRequest().getParameter("menuid")));
			this.getRequest().getSession().setAttribute("navigationBar", sysmenu.getNavigationBar());
			this.setUrl(sysmenu.getUrl());
			
		} catch (ManageException e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage(getText("find.failure"));
		}
		return "tourl";
	}
	@SkipValidation
	public String logout() {
		getRequest().getSession().removeAttribute("loginer");
		getRequest().getSession().invalidate();
		return "login";
    }
	@SkipValidation
	public String updatepassword(){
		this.getRequest().getSession().setAttribute("navigationBar",getText("updatepassword"));
		return "updatepassword";
	}
	
	public String savePassword() {
		Loginer loginer = (Loginer) this.getRequest().getSession()
				.getAttribute("loginer");
		 
		if (newpassword != null && !"".equals(newpassword)
				&& agnewpassword != null && !"".equals(agnewpassword)) {
			if (!newpassword.equals(agnewpassword)) { 
				this.addFieldError("agnewpassword", getText("twopassword"));
				return "updatepassword";
			}
		}
		try{
			List<HrUser> list = new ArrayList<HrUser>();
			list = loginService.findHrUsers(loginer.getUserAccount(), password);
			if (list == null || list.size() == 0) {
				this.addFieldError("password", getText("password.error"));
				return "updatepassword";
			} else {
				HrUser hrUser = list.get(0);
				hrUser.setPassword(MD5Util.generatePassword(newpassword));
				DESUtil desPlus = new DESUtil(); 
				hrUser.setOriginalPassword(desPlus.encrypt(newpassword));
				loginService.update(hrUser);
			}
			addMessageInfo("update.success");
		}catch(ManageException e){
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage(getText("update.failure"));
		} catch (Exception e) {
			logger.saveErrorLog(e.getMessage(), e, getCurrUserInfo(getRequest()));
			this.addActionMessage(getText("update.failure"));
		}
		return "updatepassword";
	}
	
    public void setLoginService(ILoginService loginService) {
    	this.loginService = loginService;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgnewpassword() {
		return agnewpassword;
	}

	public void setAgnewpassword(String agnewpassword) {
		this.agnewpassword = agnewpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
}
