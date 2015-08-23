package com.agilefly.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysModule;
import com.agilefly.service.sysmodule.ISysModuleService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.form.SysModuleForm;

/**
 * @author boleyn_renlei
 * @date May 16, 2012 1:30:30 PM
 * 系统模块管理
 */
@Controller("/view/sysmodule")
public class SysModuleAction extends BaseAction {
	@Resource
	private ISysModuleService sysModuleService;

	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}

	/**
	 * 打开模块管理列表界面 默认不指定mothod方法名 示例：view/sysmodule.do
	 * 默认列表显示方法调用 不可修改 或者新建一个单独的列表显示action继承Action
	 */
	@Permission(model="sysModuleManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取模块列表，并传递到界面
		SysModuleForm maf = (SysModuleForm)form;
		
		/*int offset = 0;
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch (Exception e) {
		}
		
		int pagesize = 10;
		
		int parent = maf.getParentId()
		request.setAttribute("qs", sysModuleService.getScrollData(offset, pagesize, maf.getParentId() == 0 ? "o.parent is null" : "o.parent.id = " + parentId, null));
		*/
		
		int parentId = maf.getParentId();
		request.setAttribute("qs", sysModuleService.getScrollDataByThread(parentId == 0 ? "o.parent is null" : "o.parent.id = " + parentId, null));
		
		
		
		int ppid = 0;
		
		if(maf.getParentId() != 0){
			SysModule module = sysModuleService.find(maf.getParentId());
			SysModule parent = module.getParent();
			if (parent != null) {
				ppid = parent.getId();
			}
		}
		
		request.setAttribute("ppid", ppid);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 打开模块管理录入界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysModuleManage", privilegeValue="add")
	public ActionForward addInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add_input");
	}
	
	/**
	 * 添加模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysModuleManage", privilegeValue="add")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysModuleForm smf = (SysModuleForm)form;
		SysModule module = new SysModule();
		
		BeanUtilEx.copyProperties(module, smf);
		
		sysModuleService.addSysModule(module, smf.getParentId());
		
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 打开模块管理修改界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysModuleManage", privilegeValue="update")
	public ActionForward updateInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysModuleId = request.getParameter("sysModuleId");
		SysModule module = sysModuleService.find(Integer.parseInt(sysModuleId));
		request.setAttribute("sysModuleInfo", module);
		return mapping.findForward("update_input");
	}
	
	/**
	 * 修改模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysModuleManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysModuleForm smf = (SysModuleForm)form;
		
		SysModule module = null;
		
		module = sysModuleService.find(smf.getId());
		
		String messageEntity = module.getName();
		
		BeanUtilEx.copyProperties(module, smf);
		
		sysModuleService.updateSysModule(module, smf.getParentId());
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox(messageEntity));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 删除模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysModuleManage", privilegeValue="delete")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		sysModuleService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
}
