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
import com.lcweb.bean.pojo.FrontMessage;
import com.lcweb.bean.pojo.VoteTitle;
import com.lcweb.commons.CheckRight;
import com.lcweb.service.message.MessageService;

public class MessageManageAction extends DispatchAction {
	private MessageService messageService;
	private CheckRight checkRight;
	
	/**
	 * 查询留言列表信息(后台管理)
	 */
	public ActionForward queryMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "message", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String sql = "from FrontMessage c where 1=1 order by c.messageDate desc";
		String sqlCount = "select count(*) from FrontMessage c where 1=1 ";
		
		String path = request.getContextPath() + "/view/messagemanage.do?method=queryMessage";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, messageService,
				"messageForm");
		request.setAttribute("pageList", pageList);
		
		return mapping.findForward("messageList");
	}
	
	/**
	 * 查看留言详情
	 */
	public ActionForward viewDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String messageId = request.getParameter("messageId");
		FrontMessage message = (FrontMessage) messageService.queryObjectById(FrontMessage.class, Long.valueOf(messageId));
		request.setAttribute("messageInfo", message);

		return mapping.findForward("messageDetails");
	}
	
	/**
	 * 删除留言
	 */
	public ActionForward deleteMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "message", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		messageService.deleteMessages(ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length,""));
		return queryMessage(mapping,form,request,response);
	}
	
	
	/**
	 * 审批留言
	 */
	public ActionForward approveMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "message", "approve");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = messageService.approveMessages(ids);
		request.setAttribute("showMsg", SysObj.createApproveMassageBox(effectCount));
		return queryMessage(mapping,form,request,response);
	}
	
	/**
	 * 反审批留言
	 */
	public ActionForward unApproveMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "message", "unApprove");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = messageService.unApproveMessages(ids);
		request.setAttribute("showMsg", SysObj.createUnApproveMassageBox(effectCount));
		return queryMessage(mapping,form,request,response);
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}
}
