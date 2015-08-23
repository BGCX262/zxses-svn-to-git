package com.agilefly.web.action;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysType;
import com.agilefly.commons.SysConstant;
import com.agilefly.service.systype.ISysTypeService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.SysObj;
import com.agilefly.web.form.SysBlogTypeForm;

/**
 * @author boleyn_renlei
 * @date Jun 25, 2012 5:25:34 PM
 * 系统博客文章类型管理
 */
@Controller("/view/sysblogtype")
public class SysBlogTypeAction extends BaseAction{
	@Resource
	private ISysTypeService sysTypeService;
	
	public void setSysBlogTypeService(ISysTypeService sysTypeService) {
		this.sysTypeService = sysTypeService;
	}
	
	/**
	 * 打开系统文章类型主界面
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//按照用户身份类型排序获取文章类型列表，并传递到界面
		SysBlogTypeForm sbtf = (SysBlogTypeForm)form;
		
		//当用户选择不同用户身份过滤
		
		String whereHql = "o.typeSymbol=? ";
		Object[] params = new Object[]{SysConstant.ARTICLE_TYPE};
		
		//排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("extFirst", "asc");
		orderby.put("id", "asc");
		
		request.setAttribute("qs", sysTypeService.getScrollDataByThread(whereHql,params,orderby));
		
		return mapping.findForward("list");
	}
	
	/**
	 * 打开系统文章类型录入界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="add")
	public ActionForward addInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add_input");
	}
	
	/**
	 * 检查角色名是否使用
	 */
	/*public ActionForward checkRoleName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleForm srf = (SysRoleForm)form;
		boolean isExits = sysRoleService.isRoleNameExist(srf.getRoleName());
		if(isExits){
			WebUtils.writeResponse(response, "used");
		}else{
			WebUtils.writeResponse(response, "unused");
		}
		return null;
	}*/
	
	/**
	 * 添加系统文章类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="add")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysBlogTypeForm sbtf = (SysBlogTypeForm)form;
		SysType type = new SysType();
		
		BeanUtilEx.copyProperties(type, sbtf);
		//博客类型选项标识
		type.setTypeSymbol(SysConstant.ARTICLE_TYPE);
		//记录有效
		type.setAvailable((byte)1);
		
		sysTypeService.save(type);
		
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 打开系统文章类型管理修改界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="update")
	public ActionForward updateInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysTypeId = request.getParameter("sysTypeId");
		SysType type = sysTypeService.find(Integer.parseInt(sysTypeId));
		request.setAttribute("sysBlogTypeInfo", type);
		return mapping.findForward("update_input");
	}
	
	/**
	 * 修改系统文章类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysBlogTypeForm sbtf = (SysBlogTypeForm)form;
		
		SysType type = null;
		
		type = sysTypeService.find(sbtf.getId());
		
		String messageEntity = type.getTypeName();
		
		BeanUtilEx.copyProperties(type, sbtf);
		
		sysTypeService.update(type);
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox(messageEntity));
		return unspecified(mapping, form, request,response);
	}
	
	/**
	 * 删除系统文章类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysBlogTypeManage", privilegeValue="delete")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		sysTypeService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
}
