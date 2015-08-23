package com.lcweb.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.PageList;
import com.lcweb.base.util.SysObj;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.commons.CheckRight;
import com.lcweb.service.member.MemberManageService;

/**
 * 前台会员管理
 */
public class MemberManageAction extends DispatchAction {
	private MemberManageService memberManageService;
	private CheckRight checkRight;
	
	/**
	 * 查询会员列表信息（前端用户）
	 */
	public ActionForward queryMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String sql = "from FrontUser c where 1=1 and c.recordStatus = 1 ";
		String sqlCount = "select count(*) from FrontUser c where 1=1 and c.recordStatus = 1";
		
		String path = request.getContextPath() + "/view/membermanage.do?method=queryMember";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, memberManageService,
				"memberManageForm");
		request.setAttribute("pageList", pageList);
		
		return mapping.findForward("memberList");
	}
	
	/**
	 * 删除会员(逻辑删除)
	 */
	public ActionForward deleteMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		memberManageService.deleteMembers(ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length,""));
		return queryMember(mapping,form,request,response);
	}
	
	/**
	 * 启用会员
	 */
	public ActionForward enableMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "enable");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = memberManageService.enableMembers(ids);
		request.setAttribute("showMsg", SysObj.createEnableMassageBox(effectCount));
		return queryMember(mapping,form,request,response);
	}
	
	/**
	 * 禁用会员
	 */
	public ActionForward disableMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "disable");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = memberManageService.disableMembers(ids);
		request.setAttribute("showMsg", SysObj.createDisableMassageBox(effectCount));
		return queryMember(mapping,form,request,response);
	}
	
	/**
	 * 审批会员
	 */
	public ActionForward approveMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "approve");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = memberManageService.approveMembers(ids);
		request.setAttribute("showMsg", SysObj.createApproveMassageBox(effectCount));
		return queryMember(mapping,form,request,response);
	}
	
	/**
	 * 反审批会员
	 */
	public ActionForward unApproveMember(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "unApprove");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = memberManageService.unApproveMembers(ids);
		request.setAttribute("showMsg", SysObj.createUnApproveMassageBox(effectCount));
		return queryMember(mapping,form,request,response);
	}

	public void setMemberManageService(MemberManageService memberManageService) {
		this.memberManageService = memberManageService;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}
}
