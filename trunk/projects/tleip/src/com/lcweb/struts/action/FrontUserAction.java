package com.lcweb.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.WebUtil;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.commons.CipherUtil;
import com.lcweb.service.front.FrontLoginService;
import com.lcweb.struts.form.FrontUserForm;

public class FrontUserAction extends DispatchAction {
	private FrontLoginService frontLoginService;
	
	public void setFrontLoginService(FrontLoginService frontLoginService) {
		this.frontLoginService = frontLoginService;
	}
	
	/**
	 * 解决首页静态化页面登录信息丢失 ajax请求
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkLoginStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontUser fuser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
		if(fuser != null){
			//WebUtil.writeResponse(response, "login");
			return new ActionForward("/client/index/content/login_reg/userInfo.jsp");
			//return null;
		}else{
			//WebUtil.writeResponse(response, "unLogin");
			//return null;
			return new ActionForward("/client/index/content/login_reg/userLogin.jsp");
		}
	}

	/**
	 * 用户登录验证
	 */
	public ActionForward frontLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userAccount = request.getParameter("userAccount");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String checkCode = request.getParameter("checkCode");
		
		//检查验证码
		Object randcode = request.getSession().getAttribute("frontUserRand");
		if(randcode == null){
			//页面过期，需要刷新
			WebUtil.writeResponse(response, "pageExpired");
			return null;
		}
		password = CipherUtil.generatePassword(password);
		FrontUser frontUser = frontLoginService.findUserByUnamePassType(userAccount, password, userType);
		if(frontUser != null){
			if (!randcode.toString().equals(checkCode)) {
				WebUtil.writeResponse(response, "codeFail");
				return null;
			}else {
				//记录没有逻辑删除
				if(frontUser.getRecordStatus() == 1){
					//检查用户是否启用(启用)
					if(frontUser.getAvailable() == 1){
						//检查用户是否审核通过
						if(frontUser.getApproveStatus() == 1){
							request.getSession().setAttribute("frontUserInfo",frontUser);
							return new ActionForward("/client/index/content/login_reg/userInfo.jsp");
						}else{
							WebUtil.writeResponse(response, "userDisable");
							return null;
						}
					}else{
						WebUtil.writeResponse(response, "userDisable");
						return null;
					}
					
				}else{
					WebUtil.writeResponse(response, "userDisable");
					return null;
				}
			}
		}else {
			WebUtil.writeResponse(response, "fail");
			return null;
		}
	}
	
	/**
	 * 用户注册
	 */
	public ActionForward userReg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontUserForm userForm = (FrontUserForm)form;
		FrontUser frontUser = new FrontUser();
		
		frontUser.setUserAccount(userForm.getUserAccount());
		frontUser.setUserName(userForm.getUserName());
		//MD5加密保存
		frontUser.setPassword(CipherUtil.generatePassword(userForm.getPassword()));
		frontUser.setSex(userForm.getSex());
		frontUser.setUserType(userForm.getUserType());
		frontUser.setBirthday(userForm.getBirthday());
		frontUser.setAddress(userForm.getAddress());
		frontUser.setPhone(userForm.getPhone());
		frontUser.setEmail(userForm.getEmail());
		frontUser.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:ss:mm").format(new Date()));
		//需要审批
		frontUser.setApproveStatus((short)0);
		//默认启用
		frontUser.setAvailable((short)1);
		//有效记录
		frontUser.setRecordStatus((short)1);
		frontLoginService.saveObject(frontUser);
		
		request.getSession().setAttribute("frontUserInfo",frontUser);
		
		ActionForward forward = new ActionForward("/");
		forward.setRedirect(true);
		return forward;
	}
	
	/**
	 * 用户注册 检查用户名是否使用
	 */
	public ActionForward checkUserAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontUserForm userForm = (FrontUserForm)form;
		FrontUser frontUser = null;
		frontUser = frontLoginService.findUserByAccount(userForm.getUserAccount());
		if(frontUser != null){
			WebUtil.writeResponse(response, "used");
		}else{
			WebUtil.writeResponse(response, "unused");
		}
		return null;
	}

	/**
	 * 用户注销
	 */
	public ActionForward loginOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("frontUserInfo");
		request.getSession().invalidate();
		ActionForward forward = new ActionForward("/");
		forward.setRedirect(true);
		return forward;
	}
	

	/**
	 * 跳转到修改个人信息
	 */
	public ActionForward toChangeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward("/client/index/content/login_reg/changeUserInf.jsp");
		return forward;
	}
	
	/**
	 * 修改个人信息
	 */
	public ActionForward changeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontUserForm userForm = (FrontUserForm)form;
		FrontUser frontUser = frontLoginService.findUserByAccount(userForm.getUserAccount());
		
		
		//frontUser.setUserAccount(userForm.getUserAccount());
		frontUser.setUserName(userForm.getUserName());
		frontUser.setSex(userForm.getSex());
		//不应该修改用户类型
		//frontUser.setUserType(userForm.getUserType());
		frontUser.setBirthday(userForm.getBirthday());
		frontUser.setAddress(userForm.getAddress());
		frontUser.setPhone(userForm.getPhone());
		frontUser.setEmail(userForm.getEmail());
		//修改时间
		frontUser.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:ss:mm").format(new Date()));
		frontLoginService.updateObject(frontUser);
		
		request.getSession().setAttribute("frontUserInfo",frontUser);
		
		ActionForward forward = new ActionForward("/");
		forward.setRedirect(true);
		return forward;
	}
}
