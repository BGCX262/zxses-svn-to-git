package com.agilefly.web.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.agilefly.bean.SysRole;
import com.agilefly.bean.SysUser;
import com.agilefly.service.sysrole.ISysRoleService;
import com.agilefly.service.systemuser.ISysUserService;
import com.agilefly.utils.BeanUtilEx;
import com.agilefly.utils.CipherUtil;
import com.agilefly.utils.StringUtil;
import com.agilefly.utils.SysObj;
import com.agilefly.web.form.SysUserForm;

/**
 * @author boleyn_renlei
 * @date May 14, 2012 4:33:16 PM
 * 系统用户管理
 */
@Controller("/view/sysuser")
public class SysUserAction extends BaseAction{
	@Resource
	private ISysUserService sysUserService;
	
	@Resource
	private ISysRoleService sysRoleService;
	
	/**
	 * 打开系统用户管理主界面
	 */
	@Permission(model="sysUserManage", privilegeValue="view")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取用户列表，并传递到界面
		SysUserForm maf = (SysUserForm)form;
		
		
		//加入过滤条件
		String userTypeVal = request.getParameter("userType");
		String approveStatusVal = request.getParameter("approveStatus");
		
		StringBuilder query = new StringBuilder(" 1=1");
		
		if(userTypeVal != null){
			if("all".equals(userTypeVal)){
				
			}else{
				query.append(" and o.userType='" + userTypeVal + "'");
			}
		}
		
		if(approveStatusVal != null){
			if(-1 == Byte.parseByte(approveStatusVal)){
				
			}else{
				query.append(" and o.approveStatus=" + Byte.parseByte(approveStatusVal) + "");
			}
		}
		
		//int parentId = maf.getParentId();
		//request.setAttribute("qs", sysUserService.getScrollDataByThread(parentId == 0 ? "o.parent is null" : "o.parent.id = " + parentId, null));
		request.setAttribute("qs", sysUserService.getScrollDataByThread(query.toString()));
		request.setAttribute("userTypeValue", userTypeVal);
		request.setAttribute("approveStatusValue", approveStatusVal);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 打开用户管理录入界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="add")
	public ActionForward addInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add_input");
	}
	
	/**
	 * 添加用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="add")
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUserForm suf = (SysUserForm)form;
		SysUser user = new SysUser();
		
		BeanUtilEx.copyProperties(user, suf);
		
		//MD5加密保存
		user.setPassword(CipherUtil.generatePassword(suf.getPassword()));
		user.setUserLoginip(request.getRemoteAddr());
		user.setUserRegtime(new Date());
		user.setUserLogintime(new Date());
		
		//后台新增用户不需要审批
		user.setApproveStatus((byte)1);
		//默认启用
		user.setAvailable((byte)1);
		//有效记录
		user.setRecordStatus((byte)1);
		
		sysUserService.save(user);
		
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
		
		
		/*BrandForm formbean = (BrandForm) form;
		if(!BrandForm.validateImageFileType(formbean.getLogofile())){
			System.out.println(formbean.getLogofile().getContentType());
			request.setAttribute("message", "图片格式不正确");
			return mapping.findForward("message");
		}
		Brand brand = new Brand();
		brand.setName(formbean.getName());
		if(formbean.getLogofile()!=null && formbean.getLogofile().getFileSize()>0){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
			String logopathdir = "/images/brand/"+ dateformat.format(new Date());//构建图片保存的目录
			//得到图片保存目录的真实路径
			String logorealpathdir = request.getSession().getServletContext().getRealPath(logopathdir);
			File logosavedir = new File(logorealpathdir);
			if(!logosavedir.exists()) logosavedir.mkdirs();//如果目录不存在就创建
			String ext = formbean.getLogofile().getFileName().substring(formbean.getLogofile().getFileName().lastIndexOf('.'));
			String imagename = UUID.randomUUID().toString()+ ext;//构建文件名称
			FileOutputStream fileoutstream = new FileOutputStream(new File(logorealpathdir, imagename));
			fileoutstream.write(formbean.getLogofile().getFileData());
			fileoutstream.close();
			String logopath = logopathdir+"/"+imagename;
			brand.setLogopath(logopath);
		}		
		brandService.save(brand);
		request.setAttribute("message", "品牌添加成功");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.brand.list"));
		return mapping.findForward("message");*/
		
	}
	
	/**
	 * 打开用户管理修改界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="update")
	public ActionForward updateInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysUserId = request.getParameter("sysUserId");
		SysUser user = sysUserService.find(Integer.parseInt(sysUserId));
		request.setAttribute("sysUserInfo", user);
		return mapping.findForward("update_input");
	}
	
	/**
	 * 修改用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="update")
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUserForm suf = (SysUserForm)form;
		
		SysUser user = null;
		
		user = sysUserService.find(suf.getId());
		
		String messageEntity = user.getUsername();
		
		//只修改部分属性，以下方式会覆盖已有的属性值
		//BeanUtilEx.copyProperties(user, suf);
		//根据修改的属性值，一一对应修改
		user.setRealname(suf.getRealname());
		user.setGender(suf.getGender());
		user.setPhone(suf.getPhone());
		user.setUserEmail(suf.getUserEmail());
		
		sysUserService.update(user);
		
		request.setAttribute("showMsg", SysObj.createEditMassageBox(messageEntity));
		return unspecified(mapping, form, request,response);
		//return mapping.findForward("pub_add_success");
	}
	
	/**
	 * 删除用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="delete")
	public ActionForward del(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		sysUserService.delete(ids);
		//return mapping.findForward("pub_del_success");
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return unspecified(mapping, form, request,response);
	}
	
	/**
	 * 审批会员
	 * @throws Exception 
	 */
	public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		int effectCount = sysUserService.approve(ids);
		request.setAttribute("showMsg", SysObj.createApproveMassageBox(effectCount));
		return unspecified(mapping,form,request,response);
	}
	
	/**
	 * 反审批会员
	 * @throws Exception 
	 */
	public ActionForward unApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] ids = request.getParameterValues("check");
		int effectCount = sysUserService.unApprove(ids);
		request.setAttribute("showMsg", SysObj.createUnApproveMassageBox(effectCount));
		return unspecified(mapping,form,request,response);
	}
	
	/**
	 * 打开分配角色界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="assignRole")
	public ActionForward assignRoleInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sysUserId = request.getParameter("sysUserId");
		SysUser user = sysUserService.find(Integer.parseInt(sysUserId));
		request.setAttribute("sysUserInfo", user);
		//获得所有角色
		request.setAttribute("qs", sysRoleService.getScrollData());
		
		//获得用户拥护的所有角色
		request.setAttribute("sysUserRoles", user.getSysRoles());
		
		return mapping.findForward("assignRole_input");
	}
	
	/**
	 * 分配角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Permission(model="sysUserManage", privilegeValue="assignRole")
	public ActionForward assignRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUserForm suf = (SysUserForm)form;
		
		SysUser user = null;
		
		user = sysUserService.find(suf.getId());
		
		//清除所有角色 重新添加角色
		user.getSysRoles().clear();
		
		if(suf.getRoleIds()!=null){
			for(Integer id : suf.getRoleIds()){
				user.addSysRole(new SysRole(id));
			}
		}
		sysUserService.update(user);
		String messageEntity = user.getUsername();
		
		request.setAttribute("showMsg", SysObj.createMassageBox("为["+messageEntity+"]分配权限成功！"));
		return unspecified(mapping, form, request,response);
	}
}
