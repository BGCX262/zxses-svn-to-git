package com.lcweb.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.PageList;
import com.lcweb.base.util.SysObj;
import com.lcweb.base.util.WebUtil;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.FrontUser;
import com.lcweb.bean.pojo.SysRole;
import com.lcweb.bean.pojo.VoteItems;
import com.lcweb.bean.pojo.VoteTitle;
import com.lcweb.bean.pojo.VoteUser;
import com.lcweb.bean.pojo.VoteUserId;
import com.lcweb.commons.CheckRight;
import com.lcweb.service.vote.VoteService;
import com.lcweb.service.vote.VoteUserService;
import com.lcweb.struts.form.RightManageForm;
import com.lcweb.struts.form.VoteForm;

public class VoteManageAction extends DispatchAction {
	private VoteService voteService;
	private VoteUserService voteUserService;
	private CheckRight checkRight;

	/**
	 * 查询投票列表信息(后台管理)
	 */
	public ActionForward queryVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String sql = "from VoteTitle c where 1=1 order by c.voteDate desc";
		String sqlCount = "select count(*) from VoteTitle c where 1=1 ";

		String path = request.getContextPath() + "/view/votemanage.do?method=queryVoteTitle";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, voteService, "voteForm");
		request.setAttribute("pageList", pageList);

		return mapping.findForward("voteList");
	}
	
	/**
	 * 跳转到新增投票主题界面
	 */
	public ActionForward toAddVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		return mapping.findForward("voteTitleAdd");
	}

	/**
	 * 新增投票主题
	 */
	public ActionForward addVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		VoteForm voteForm = (VoteForm) form;
		VoteTitle voteTitle = new VoteTitle();
		voteTitle.setVoteDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		voteTitle.setVoteName(voteForm.getVoteName());
		voteTitle.setVoteType(voteForm.getVoteType());
		voteService.saveObject(voteTitle);

		for (int i = 0; i < voteForm.getItemName().length; i++) {
			VoteItems items = new VoteItems();
			items.setItemName(voteForm.getItemName()[i]);
			items.setVoteTitle(voteTitle);
			items.setItemBallot(0);
			voteService.saveObject(items);
		}
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return queryVoteTitle(mapping, form, request, response);
	}

	/**
	 * 删除投票主题
	 */
	public ActionForward deleteVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		voteService.deleteVoteTitles(ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return queryVoteTitle(mapping, form, request, response);
	}

	/**
	 * 删除投票选项 to dos
	 */
	public ActionForward deleteVoteItem(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		voteService.deleteVoteTitles(ids);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(ids.length, ""));
		return queryVoteTitle(mapping, form, request, response);
	}
	
	/**
	 * 发布投票主题
	 */
	public ActionForward publishVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "publish");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = voteService.publishVoteTitle(ids);
		request.setAttribute("showMsg", SysObj.createPublishMassageBox(effectCount));
		return queryVoteTitle(mapping,form,request,response);
	}
	
	/**
	 * 反发布投票主题
	 */
	public ActionForward unPublishVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "member", "enable");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] ids = request.getParameterValues("check");
		int effectCount = voteService.unPublishVoteTitle(ids);
		request.setAttribute("showMsg", SysObj.createUnPublishMassageBox(effectCount));
		return queryVoteTitle(mapping,form,request,response);
	}
	
	/**
	 * 跳转到修改投票主题界面
	 */
	public ActionForward enterUpdateVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		Long id = Long.parseLong(request.getParameter("voteTitleId"));
		VoteTitle voteTitle = (VoteTitle) voteService.queryObjectByLongId(VoteTitle.class, id);
		request.setAttribute("voteTitleInfo", voteTitle);
		return mapping.findForward("enterUpdateVoteTitle");
	}
	
	/**
	 * 修改投票主题
	 */
	public ActionForward updateVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "vote", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		VoteForm voteForm = (VoteForm) form;
		/*VoteTitle voteTitle = new VoteTitle();
		voteTitle.setVoteDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		voteTitle.setVoteName(voteForm.getVoteName());
		voteTitle.setVoteType(voteForm.getVoteType());
		voteService.saveObject(voteTitle);*/
		
		VoteTitle voteTitle = (VoteTitle)voteService.queryObjectById(VoteTitle.class, voteForm.getVoteId());
		//删除投票主题选线然后重新新增
		voteService.deleteVoteTitleItems(voteTitle.getVoteId().toString());
		for (int i = 0; i < voteForm.getItemName().length; i++) {
			VoteItems items = new VoteItems();
			items.setItemName(voteForm.getItemName()[i]);
			items.setVoteTitle(voteTitle);
			items.setItemBallot(0);
			voteService.saveObject(items);
		}
		request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
		return queryVoteTitle(mapping, form, request, response);
	}
	
	/**
	 * 查询投票主题下的投票选项详情
	 */
	public ActionForward queryVoteItems(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String voteId = request.getParameter("voteId");
		VoteTitle title = (VoteTitle) voteService.queryObjectById(VoteTitle.class, Long.valueOf(voteId));
		Integer totalCount = 1;
		if (title != null) {
			totalCount = voteService.getItemsBallotCount(title.getVoteId());
			// 如果还没有投票记录 totalCount为0，前台计算百分比(items.itemBallot/0)会出现NaN问题,重新设置为1
			if (totalCount == 0) {
				totalCount = 1;
			}
		}
		request.setAttribute("voteTitle", title);
		request.setAttribute("totalCount", totalCount);

		return mapping.findForward("voteTitleDetails");
	}
	

	/**
	 * 查询留言列表信息(前台)
	 */
	public ActionForward queryVoteList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * String sql = "from VoteTitle c where 1=1 order by c.voteDate desc"; String sqlCount = "select count(*) from VoteTitle c
		 * where 1=1 ";
		 * 
		 * String path = request.getContextPath() + "/front/vote.do?method=queryVoteList"; String pagesize =
		 * request.getParameter("pagesize"); String currentPage = request.getParameter("currentPage");
		 * 
		 * PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, voteService, "voteForm");
		 * request.setAttribute("pageList", pageList);
		 */
		// 按照日期查询最新发布状态的投票
		VoteTitle title = voteService.queryNewVoteTitle();
		Integer totalCount = 1;
		if (title != null) {
			totalCount = voteService.getItemsBallotCount(title.getVoteId());
			// 如果还没有投票记录 totalCount为0，前台计算百分比(items.itemBallot/0)会出现NaN问题,重新设置为1
			if (totalCount == 0) {
				totalCount = 1;
			}
		}
		request.setAttribute("voteTitle", title);
		request.setAttribute("totalCount", totalCount);

		return mapping.findForward("voteFrontList");
	}
	
	public ActionForward queryVoteListInMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// 按照日期查询最新发布状态的投票
		VoteTitle title = voteService.queryNewVoteTitle();
		request.setAttribute("voteTitle", title);
		return new ActionForward("/client/index/content/vote/voteList.jsp");
	}

	/**
	 * 检查用户是否登录以及登录用户是否已经投票
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkVote(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//只有登录用户才能投票
		FrontUser fuser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
		if(fuser == null){
			//return mapping.findForward("fail");
			WebUtil.writeResponse(response, "unLogin");
			return null;
		}
		
		String voteId = request.getParameter("voteId");
		
		//检查用户是否已经投票
		VoteUserId voteUserId = new VoteUserId(Long.parseLong(voteId), fuser.getUserId());
		VoteUser voteUserExist = (VoteUser)voteUserService.queryObjectById(VoteUser.class, voteUserId);
		if(voteUserExist != null){
			//return mapping.findForward("havaBallot");
			WebUtil.writeResponse(response, "havaBallot");
			return null;
		}
		WebUtil.writeResponse(response, "noBallot");
		return null;
	}
	
	
	/**
	 * 投票
	 */
	public ActionForward ballotVoteTitle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FrontUser fuser = (FrontUser)request.getSession().getAttribute("frontUserInfo");
		String voteId = request.getParameter("voteId");
		
		
		
		String[] itemsIds = request.getParameterValues("itemsIds");
		// 处理表单代码 start
		//String voteFlag = request.getParameter("voteFlag");
		//HttpSession session = request.getSession();
		//用户刷新
		if(fuser != null){
			VoteUserId voteUserId = new VoteUserId(Long.parseLong(voteId), fuser.getUserId());
			//检查用户是否已经投票
			VoteUser voteUserExist = (VoteUser)voteUserService.queryObjectById(VoteUser.class, voteUserId);
			if(voteUserExist == null){
				if (voteId != null && itemsIds != null) {
					// 更新人气值
					voteService.updateTitleVoteCount(voteId);
					// 更新投票选项投票数
					voteService.updateItemsBallotCount(itemsIds);
					
					//记录用户投票信息
					voteUserService.saveObject(new VoteUser(voteUserId));
					return queryVoteList(mapping, form, request, response);
				}
			}
		}
		return queryVoteList(mapping, form, request, response);
	}

	public void setVoteService(VoteService voteService) {
		this.voteService = voteService;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}

	/**
	 * 销毁flag
	 * 
	 * @param session
	 */
	public void destroyFlag(HttpSession session) {
		session.removeAttribute("voteFlag");
	}

	public void setVoteUserService(VoteUserService voteUserService) {
		this.voteUserService = voteUserService;
	}
}
