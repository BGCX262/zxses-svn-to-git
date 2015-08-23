package com.agilefly.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysConfig;
import com.agilefly.service.sysconfig.ISysConfigService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.form.SysConfigForm;

/**
 * 系统基本设置(首页新浪微博链接，qq在线链接)
 * @author Administrator
 *
 */
@Controller("/view/sysconfig")
public class SysConfigAction extends BaseAction {
	@Resource
	private ISysConfigService sysConfigService;
	
	/**
	 * 打开系统设置主界面
	 */
	@Permission(model="sysConfigManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取系统配置信息
		SysConfig sysConfig = sysConfigService.find("sysConfigManage");
		request.setAttribute("sysConfigInfo", sysConfig);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 修改系统设置信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysConfigManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysConfigForm scf = (SysConfigForm)form;
		
		SysConfig sysConfig = null;
		
		sysConfig = sysConfigService.find("sysConfigManage");
		
		BeanUtilEx.copyProperties(sysConfig, scf);
		
		sysConfigService.update(sysConfig);
		
		//修改系统设置信息，重新放入application中
		request.getSession().getServletContext().setAttribute("sysConfig_App", sysConfigService.find("sysConfigManage"));
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox("系统配置"));
		
		return unspecified(mapping, form, request,response);
	}
}
