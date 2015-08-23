package com.lcweb.struts.action;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.lcweb.base.util.ApplicationConfig;
import com.lcweb.base.util.EmailSender;
import com.lcweb.base.util.WebUtil;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.commons.CipherUtil;
import com.lcweb.service.front.FrontLoginService;
import com.lcweb.struts.form.FindPasswordForm;

public class FindPasswordAction extends DispatchAction {
	private FrontLoginService frontLoginService;
	
	public void setFrontLoginService(FrontLoginService frontLoginService) {
		this.frontLoginService = frontLoginService;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("frontIndex");
	}
	
	/**
	 * 找回密码---发送邮件
	 */
	public ActionForward getpassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FindPasswordForm user = (FindPasswordForm)form;
		FrontUser frontUser = (FrontUser)frontLoginService.findUserByAccount(user.getUserAccount());
		if(frontUser!=null){
			Template template = Velocity.getTemplate("mailContent.vm");
			VelocityContext context = new VelocityContext();
			context.put("username", frontUser.getUserAccount());
			String validateCode = CipherUtil.generatePassword(frontUser.getUserAccount()+ frontUser.getPassword());
			context.put("validateCode", validateCode);
			context.put("webPath", ApplicationConfig.get("email.webpath"));
			context.put("logo", ApplicationConfig.get("email.logo"));
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			writer.flush();
			String mailContent = writer.toString();//得到邮件内容
			try{
				EmailSender.send(frontUser.getEmail(), "塘朗小学--找回密码", mailContent, "text/html");
			}catch (Exception e) {
			}
			WebUtil.writeResponse(response, "emailSended");
		}else{
			WebUtil.writeResponse(response, "userAccountError");
		}
		return null;
	}
	/**
	 * 找回密码---显示密码修改界面
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FindPasswordForm formbean = (FindPasswordForm)form;
		FrontUser frontUser = frontLoginService.findUserByAccount(formbean.getUserAccount().trim());
		if(frontUser!=null){
			String validateCode = CipherUtil.generatePassword(frontUser.getUserAccount()+ frontUser.getPassword());
			if(validateCode.equals(formbean.getValidateCode())){
				return mapping.findForward("findPassword3");
			}else{
				return mapping.findForward("errorresult");
			}
		}else{
			request.setAttribute("message", "用户名有误");
			request.setAttribute("content", "用户帐号错误,如果您是在浏览器上输入的链接,请检查链接是否正确,建议重新");
			request.setAttribute("urltip", "找回密码");
			request.setAttribute("url", "client/index/content/login_reg/findPassword.jsp");
			return mapping.findForward("message");
		}		
	}
	/**
	 * 找回密码---密码修改
	 */
	public ActionForward changepassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FindPasswordForm formbean = (FindPasswordForm)form;
		FrontUser frontUser = frontLoginService.findUserByAccount(formbean.getUserAccount().trim());
		if(frontUser!=null){
			String validateCode = CipherUtil.generatePassword(frontUser.getUserAccount()+ frontUser.getPassword());
			if(validateCode.equals(formbean.getValidateCode())){
				if(formbean.getPassword() != null)
					frontUser.setPassword(CipherUtil.generatePassword(formbean.getPassword()));
				frontLoginService.updateObject(frontUser);
				request.setAttribute("message", "密码修改成功");
				request.setAttribute("content", "你的密码已成功修改,跳转到");
				request.setAttribute("urltip", "首页");
				request.setAttribute("url", "");
			}else{
				request.setAttribute("message", "来源不合法");
				request.setAttribute("content", "如果您是在浏览器上输入的链接,请检查链接是否正确,建议重新");
				request.setAttribute("urltip", "找回密码");
				request.setAttribute("url", "client/index/content/login_reg/findPassword.jsp");
			}
		}else{
			request.setAttribute("message", "用户名有误");
			request.setAttribute("content", "如果您是在浏览器上输入的链接,请检查链接是否正确,建议重新");
			request.setAttribute("urltip", "找回密码");
			request.setAttribute("url", "client/index/content/login_reg/findPassword.jsp");
		}		
		return mapping.findForward("message");
	}
	
	/**
	 * 找回密码---密码修改
	 */
	public ActionForward changePw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FindPasswordForm formbean = (FindPasswordForm)form;
		FrontUser frontUser = frontLoginService.findUserByAccount(formbean.getUserAccount().trim());
		if(frontUser!=null){
				if(formbean.getPassword() != null && frontUser.getPassword().equals(CipherUtil.generatePassword(formbean.getPassword()))){
					frontUser.setPassword(CipherUtil.generatePassword(formbean.getNewPassword()));
					frontLoginService.updateObject(frontUser);
					request.setAttribute("message", "密码修改成功");
					request.setAttribute("content", "你的密码已成功修改,跳转到");
					request.setAttribute("urltip", "首页");
					request.setAttribute("url", "");
				}else{
					request.setAttribute("message", "原始密码错误");
					request.setAttribute("content", "你输入的原始密码错误,重新");
					request.setAttribute("urltip", "修改");
					request.setAttribute("url", "client/index/content/login_reg/changePw.jsp");
				}
		}else{
			request.setAttribute("message", "用户名有误");
			request.setAttribute("content", "请到首页登录后修改密码,跳转到");
			request.setAttribute("urltip", "首页");
			request.setAttribute("url", "");
		}		
		return mapping.findForward("message");
	}
}
