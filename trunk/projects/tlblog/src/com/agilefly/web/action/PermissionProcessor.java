package com.agilefly.web.action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.web.struts.DelegatingRequestProcessor;

import com.agilefly.bean.SysPrivilege;
import com.agilefly.bean.SysPrivilegeId;
import com.agilefly.bean.SysRole;
import com.agilefly.bean.SysUser;
import com.agilefly.commons.web.WebUtils;

/**
 * @author boleyn_renlei
 * @date May 20, 2012 8:25:52 PM
 * 集成spring DelegatingRequestProcessor 添加权限判断
 */
public class PermissionProcessor extends DelegatingRequestProcessor {

	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
		if(validate(request, action, mapping)){//校验用户是否有权限
			return super.processActionPerform(request, response, action, form, mapping);
		}
		
		request.setAttribute("message", "你没有权限执行该操作");
		request.setAttribute("urladdress", "view/right.jsp");
		//request.setAttribute("urladdress", request.getHeader("Referer"));
		return mapping.findForward("message");
	}
	/**
	 * 校验系统用户是否具有执行当前方法的权限
	 */
	private boolean validate(HttpServletRequest request, Action action, ActionMapping mapping) {
		Method currentMethod = getCurrentMethod(request, action, mapping);//得到当前执行的action方法
		if(currentMethod!=null && currentMethod.isAnnotationPresent(Permission.class)){
			Permission permission = currentMethod.getAnnotation(Permission.class);
			SysPrivilege privilege = new SysPrivilege(new SysPrivilegeId(permission.model(), permission.privilegeValue()));
			SysUser sysUser = WebUtils.getSysUser(request);
			for(SysRole sr : sysUser.getSysRoles()){
				if(sr.getSysPrivileges().contains(privilege)) return true;
			}
			return false;
		}
		return true;
	}
	/**
	 * 得到当前执行的方法
	 */
	private Method getCurrentMethod(HttpServletRequest request, Action action, ActionMapping mapping) {
		String methodName = "execute";
		if(DispatchAction.class.isAssignableFrom(action.getClass())){//判断DispatchAction是否是action的父类
			methodName = request.getParameter(mapping.getParameter());
			//如果没有指定具体的方法名(即执行默认的unspecified方法,反射无法获得该Method对象,在方法默认调用显示列表方法list()才能控制权限)
			if(methodName == null){
				methodName = "list";
			}
		}
		try {
			return  action.getClass().getMethod(methodName, ActionMapping.class, ActionForm.class,
				HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {}
		return null;
	}
}
