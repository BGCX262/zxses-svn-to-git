package com.lcweb.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.PageList;
import com.lcweb.base.util.WebUtil;
import com.lcweb.bean.pojo.FrontMessage;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.service.message.MessageService;
import com.lcweb.struts.form.MessageForm;

public class MessageAction extends DispatchAction {
	private MessageService messageService;
	
	/**
	 * 查询留言列表信息（审批后的留言）
	 */
	public ActionForward queryMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		/*BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "select");
		if (!right) {
			return mapping.findForward("noright");
		}*/
		String sql = "from FrontMessage c where 1=1 and c.approveStatus = 1 order by c.messageDate desc ";
		String sqlCount = "select count(*) from FrontMessage c where 1=1 and c.approveStatus = 1";
		
		String path = request.getContextPath() + "/front/message.do?method=queryMessage";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, messageService,
				"messageForm");
		request.setAttribute("pageList", pageList);
		
		return mapping.findForward("messageIndex");
	}
	
	/**
	 * 检查用户是否登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkToAddMsg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//只有登录用户才能留言
		FrontUser fuser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
		if(fuser != null){
			//return mapping.findForward("toAddMessage");
			WebUtil.writeResponse(response, "success");
			return null;
		}else {
			//return mapping.findForward("fail");
			WebUtil.writeResponse(response, "unLogin");
			return null;
		}
		
	}
	
	/**
	 * 跳转到留言界面
	 */
	public ActionForward toAddMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//只有登录用户才能留言
		FrontUser fuser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
		if(fuser != null){
			return mapping.findForward("toAddMessage");
		}else {
			return mapping.findForward("fail");
		}
		
	}
	
	
	/**
	 * 新增留言
	 */
	public ActionForward addMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MessageForm messageForm = (MessageForm)form;
		
		FrontMessage message = new FrontMessage();
		
		//只有登录用户才能留言
		if(messageForm.getFrontUserId() == 0){
			//页面没有传递，则判断session中是否有用户，可能采用ajax登录然后留言
			FrontUser frontUser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
			if(frontUser != null){
				message.setFrontUser(frontUser);
				
				message.setMessageContent(messageForm.getMessageContent());
				message.setMessageDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				message.setFacePic(messageForm.getFacePic());
				message.setHeadPic(messageForm.getHeadPic());
				message.setApproveStatus((short)0);
				message.setIsTop((short)0);
			}else{
				return mapping.findForward("fail");
			}
		}else{
			FrontUser user = (FrontUser)messageService.queryObjectById(FrontUser.class, messageForm.getFrontUserId());
			
			message.setFrontUser(user);
			message.setMessageContent(messageForm.getMessageContent());
			message.setMessageDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			message.setFacePic(messageForm.getFacePic());
			message.setHeadPic(messageForm.getHeadPic());
			message.setApproveStatus((short)0);
			message.setIsTop((short)0);
		}
		messageService.saveObject(message);
		return mapping.findForward("success");
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
