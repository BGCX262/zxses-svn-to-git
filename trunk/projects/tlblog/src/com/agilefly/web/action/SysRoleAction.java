package com.agilefly.web.action;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysPrivilege;
import com.agilefly.bean.SysPrivilegeId;
import com.agilefly.bean.SysRole;
import com.agilefly.commons.SysConstant;
import com.agilefly.commons.web.WebUtils;
import com.agilefly.service.sysmodule.ISysModuleService;
import com.agilefly.service.sysprivilege.ISysPrivilegeService;
import com.agilefly.service.sysrole.ISysRoleService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.form.SysRoleForm;

/**
 * @author boleyn_renlei
 * @date May 14, 2012 4:33:16 PM
 * 系统角色管理
 */
@Controller("/view/sysrole")
public class SysRoleAction extends BaseAction{
	@Resource
	private ISysRoleService sysRoleService;
	
	@Resource
	private ISysPrivilegeService sysPrivilegeService;
	
	@Resource
	private ISysModuleService sysModuleService;

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}
	
	public void setSysPrivilegeService(ISysPrivilegeService sysPrivilegeService) {
		this.sysPrivilegeService = sysPrivilegeService;
	}

	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}
	
	/**
	 * 打开系统角色管理主界面
	 */
	@Permission(model="sysRoleManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取角色列表，并传递到界面
		SysRoleForm srf = (SysRoleForm)form;
		
		request.setAttribute("qs", sysRoleService.getScrollDataByThread());
		
		return mapping.findForward("list");
	}
	
	/**
	 * 打开角色管理录入界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="add")
	public ActionForward addInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add_input");
	}
	
	/**
	 * 检查角色名是否使用
	 */
	public ActionForward checkRoleName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleForm srf = (SysRoleForm)form;
		boolean isExits = sysRoleService.isRoleNameExist(srf.getRoleName());
		if(isExits){
			WebUtils.writeResponse(response, "used");
		}else{
			WebUtils.writeResponse(response, "unused");
		}
		return null;
	}
	
	/**
	 * 添加角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="add")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleForm srf = (SysRoleForm)form;
		SysRole role = new SysRole();
		
		BeanUtilEx.copyProperties(role, srf);
		
		sysRoleService.save(role);
		
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 打开角色管理修改界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="update")
	public ActionForward updateInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysRoleId = request.getParameter("sysRoleId");
		SysRole role = sysRoleService.find(Integer.parseInt(sysRoleId));
		request.setAttribute("sysRoleInfo", role);
		return mapping.findForward("update_input");
	}
	
	/**
	 * 修改角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleForm srf = (SysRoleForm)form;
		
		SysRole role = null;
		
		role = sysRoleService.find(srf.getId());
		
		String messageEntity = role.getRoleName();
		
		BeanUtilEx.copyProperties(role, srf);
		
		sysRoleService.update(role);
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox(messageEntity));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 删除角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="delete")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		sysRoleService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
	
	/**
	 * 打开分配权限界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="assignPrivilege")
	public ActionForward assignPrivilegeInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysRoleId = request.getParameter("sysRoleId");
		SysRole role = sysRoleService.find(Integer.parseInt(sysRoleId));
		request.setAttribute("sysRoleInfo", role);
		
		//按照排序号排序
		LinkedHashMap<String, String> privOrderby = new LinkedHashMap<String, String>();
		privOrderby.put("orderNo", "asc");
		
		//获得所有权限操作
		request.setAttribute("sysPrivileges", sysPrivilegeService.findByCondition(privOrderby));
		
		//获得角色拥护的所有权限
		request.setAttribute("sysRolePrivileges", role.getSysPrivileges());
		
		//获得所有的一级模块
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderNo", "asc");
		
		request.setAttribute("sysModules", sysModuleService.getScrollData("o.parent is null", null, orderby).getResultlist());
		return mapping.findForward("assignPrivilege_input");
	}
	
	/**
	 * 分配权限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysRoleManage", privilegeValue="assignPrivilege")
	public ActionForward assignPrivilege(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleForm srf = (SysRoleForm)form;
		
		SysRole role = null;
		
		role = sysRoleService.find(srf.getId());
		
		//清除所有权限 重新添加权限
		role.getSysPrivileges().clear();
		
		for (SysPrivilegeId id : srf.getSysPrivileges()) {
			role.addSysPrivilege(new SysPrivilege(id));
		}
		
		String messageEntity = role.getRoleName();
		
		sysRoleService.update(role);
		
		request.setAttribute("showMsg", SysObj.createMassageBox("为["+messageEntity+"]分配权限成功！"));
		return unspecified(mapping, form, request,response);
	}
}
