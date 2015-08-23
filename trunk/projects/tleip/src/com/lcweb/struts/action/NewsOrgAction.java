package com.lcweb.struts.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.PageList;
import com.lcweb.base.util.StringUtil;
import com.lcweb.base.util.SysObj;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SchCollegeDepartment;
import com.lcweb.commons.CheckRight;
import com.lcweb.commons.GlobalConst;
import com.lcweb.service.newsmanage.NewsManageService;
import com.lcweb.service.org.NewsOrgService;

public class NewsOrgAction extends DispatchAction {

	private NewsOrgService newsOrgService;

	public void setNewsOrgService(NewsOrgService newsOrgService) {
		this.newsOrgService = newsOrgService;
	}

	private NewsManageService newsManageService;

	public void setNewsManageService(NewsManageService newsManageService) {
		this.newsManageService = newsManageService;
	}

	private CheckRight checkRight;

	public CheckRight getCheckRight() {
		return checkRight;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}


	/**
	 * 
	 * 
	 */
	public ActionForward findSchCollegeDepartment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "sch_college_department",
				"select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String xxdm = StringUtil.getStringValueForServletRequest(request, "xxdm");
		String yxdm = GlobalConst.getFirstPageMarkBySchoolCode(xxdm);
		String firstPage = GlobalConst.FirstPageMark;

		String sql = "from SchCollegeDepartment c where 1=1 ";
		String sqlCount = "select count(*) from SchCollegeDepartment c where 1= 1 ";

		if (xxdm == null || xxdm.trim().equalsIgnoreCase("")) {
			xxdm = GlobalConst.SchoolCode;
		}
		sql = sql + " and  c.yxmc != '" + firstPage + "'";
		sqlCount = sqlCount + " and  c.yxmc != '" + firstPage + "'";

		sql = sql + " and  c.yxsywmc != '" + firstPage + "'";
		sqlCount = sqlCount + " and  c.yxsywmc != '" + firstPage + "'";

		if (firstPage != null && !firstPage.trim().equalsIgnoreCase("0")) {
			sql = sql + " and  c.yxdm != '" + yxdm + "'";
			sqlCount = sqlCount + " and  c.yxdm != '" + yxdm + "'";
		}

		if (xxdm != null && !xxdm.equalsIgnoreCase("0")) {
			sql = sql + " and  c.xxdm='" + xxdm + "'";
			sqlCount = sqlCount + " and  c.xxdm='" + xxdm + "'";
		}

		String path = request.getContextPath() + "/view/newsorg.do?method=findSchCollegeDepartment";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsOrgService,
				"newsOrgMangeForm");
		request.setAttribute("allXxdmSelect", newsOrgService.getSchoolSelect(xxdm));
		request.setAttribute("pageList", pageList);
		request.setAttribute("xxdm", xxdm);

		return mapping.findForward("defindSchCollDept");
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAddNewsCollege(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "sch_college_department", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		String xxdm = StringUtil.getStringValueForServletRequest(request, "xxdm");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("xxdm", xxdm);
		if (yxdm == null || yxdm.trim().equalsIgnoreCase("")) {
			return mapping.findForward("addSchCollDept");
		} else {
			SchCollegeDepartment scd = (SchCollegeDepartment) newsOrgService.queryObjectById(
					new SchCollegeDepartment(), yxdm);
			request.setAttribute("schCollDept", scd);
			return mapping.findForward("amendSchCollDept");
		}
	}

	/**
	 * add new college
	 */
	public ActionForward addNewsCollege(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "sch_college_department", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String xxdm = StringUtil.getStringValueForServletRequest(request, "xxdm");
		String yxdm = request.getParameter("yxdm");
		String yxmc = request.getParameter("yxmc");
		String yxsywmc = request.getParameter("yxsywmc");
		String yxsjc = request.getParameter("yxsjc");
		String yxsbbm = request.getParameter("yxsbbm");
		String yxslbm = request.getParameter("yxslbm");
		String jlny = request.getParameter("jlny");
		String xzfzr = request.getParameter("xzfzr");
		String dwfzr = request.getParameter("dwfzr");

		SchCollegeDepartment scd = new SchCollegeDepartment();
		scd.setXxdm(xxdm);
		scd.setYxdm(yxdm);
		scd.setYxmc(yxmc);
		scd.setYxsywmc(yxsywmc);
		scd.setYxsjc(yxsjc);
		scd.setYxsbbm(yxsbbm);
		scd.setYxslbm(yxslbm);
		scd.setJlny(jlny);
		scd.setXzfzr(xzfzr);
		scd.setDwfzr(dwfzr);

		request.setAttribute("yxdm", yxdm);
		request.setAttribute("xxdm", xxdm);

		String forward = "addSchCollDept";
		try {
			String str = "success";
			if (newsOrgService.checkExistForCollage(scd)) {
				str = "isExists";
			}
			if ("success".equalsIgnoreCase(str)) {
				newsOrgService.saveObject(scd);
				request.setAttribute("yxdm", "");
				request.setAttribute("showMsg", SysObj.createAddMassageBox(yxmc));
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("schCollDept", scd);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(yxdm));
			} else {
				forward = "error";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * add new college
	 */
	public ActionForward amendNewsCollege(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "sch_college_department",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String xxdm = StringUtil.getStringValueForServletRequest(request, "xxdm");
		String yxdm = request.getParameter("yxdm");
		String yxmc = request.getParameter("yxmc");
		String yxsywmc = request.getParameter("yxsywmc");
		String yxsjc = request.getParameter("yxsjc");
		String yxsbbm = request.getParameter("yxsbbm");
		String yxslbm = request.getParameter("yxslbm");
		String jlny = request.getParameter("jlny");
		String xzfzr = request.getParameter("xzfzr");
		String dwfzr = request.getParameter("dwfzr");

		SchCollegeDepartment scd = (SchCollegeDepartment) newsOrgService.queryObjectById(new SchCollegeDepartment(),
				yxdm);
		// scd.setXxdm(xxdm);
		// scd.setYxdm(yxdm);
		scd.setYxmc(yxmc);
		scd.setYxsywmc(yxsywmc);
		scd.setYxsjc(yxsjc);
		scd.setYxsbbm(yxsbbm);
		scd.setYxslbm(yxslbm);
		scd.setJlny(jlny);
		scd.setXzfzr(xzfzr);
		scd.setDwfzr(dwfzr);

		request.setAttribute("yxdm", yxdm);
		request.setAttribute("xxdm", xxdm);
		String forward = "amendSchCollDept";
		request.setAttribute("schCollDept", scd);
		try {
			String str = "success";
			newsOrgService.saveOrUpdate(scd);
			if ("success".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createEditMassageBox(yxmc));
				// return findNewsItemBigById(mapping,form,request,response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("schCollDept", scd);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(yxdm));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete college
	 */
	public ActionForward deleteNewsCollege(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "sch_college_department",
				"delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecord = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];

			SchCollegeDepartment scd = (SchCollegeDepartment) newsOrgService.queryObjectById(
					new SchCollegeDepartment(), ids);

			if (newsManageService.queryNewsItemBigByYxdm(scd.getYxdm(), GlobalConst.IS_DISPLAY, 2).size() == 0) {
				newsOrgService.deleteObject(scd);
				delRecord++;
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + scd.getYxmc();
				} else {
					msg = msg + ";" + scd.getYxmc();
				}
			}

		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecord, msg));
		return findSchCollegeDepartment(mapping, form, request, response);
	}
}
