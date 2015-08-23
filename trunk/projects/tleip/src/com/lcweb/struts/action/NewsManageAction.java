package com.lcweb.struts.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.lcweb.base.util.HtmlAndText;
import com.lcweb.base.util.HtmlFile;
import com.lcweb.base.util.IndexToHtml;
import com.lcweb.base.util.MyDate;
import com.lcweb.base.util.PageList;
import com.lcweb.base.util.StringUtil;
import com.lcweb.base.util.SysObj;
import com.lcweb.base.util.SystemConfig;
import com.lcweb.bean.pojo.BasicDepartment;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.HtmlFileParameter;
import com.lcweb.bean.pojo.Monitor;
import com.lcweb.bean.pojo.NewsAdManage;
import com.lcweb.bean.pojo.NewsAdType;
import com.lcweb.bean.pojo.NewsContentManage;
import com.lcweb.bean.pojo.NewsItemBig;
import com.lcweb.bean.pojo.NewsItemConfig;
import com.lcweb.bean.pojo.NewsItemNavigation;
import com.lcweb.bean.pojo.NewsItemSmall;
import com.lcweb.bean.pojo.SchCollegeDepartment;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.commons.CheckRight;
import com.lcweb.commons.GlobalConst;
import com.lcweb.service.newsmanage.NewsManageService;
import com.lcweb.service.org.NewsOrgService;

public class NewsManageAction extends DispatchAction {
	private static final String NEWS_TEMPLATE_ONE = "1";
	private static String xxdm = GlobalConst.SchoolCode;
	private static String workPath;
	private NewsManageService newsManageService;
	

	public void setNewsManageService(NewsManageService newsManageService) {
		this.newsManageService = newsManageService;
	}

	private NewsOrgService newsOrgService;

	public void setNewsOrgService(NewsOrgService newsOrgService) {
		this.newsOrgService = newsOrgService;
	}

	private CheckRight checkRight;

	public CheckRight getCheckRight() {
		return checkRight;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}

	private static MyDate mydate = new MyDate();

	private void setCommonAttribute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String showMsg = StringUtil.getStringValueForServletRequest(request, "showMsg");

		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("msg", msg);
		request.setAttribute("showMsg", showMsg);
	}

	/**
	 * 
	 * search the data of news item big
	 */
	public ActionForward findNewsItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String sql = "from NewsItemBig c where 1=1";
		String sqlCount = "select count(*) from NewsItemBig c where 1=1";
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			sql = sql + " and c.yxdm='" + yxdm + "'";
			sqlCount = sqlCount + " and c.yxdm='" + yxdm + "'";
		}
		sql = sql + " order by c.orderId";
		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsItemBig";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchCollegeDepart(yxdm, basicPerson));

		request.setAttribute("pageList", pageList);

		setCommonAttribute(mapping, form, request, response);
		
		return mapping.findForward("defindNewsItemBig");
	}

	/**
	 * search the data of news item big for add
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findNewsItemBigById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		HashMap paraMap = new HashMap();
		paraMap.put("tableType", "NewsItemBig");
		paraMap.put("yxdm", yxdm);
		request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));
		request.setAttribute("schCollDept", newsOrgService.getSchCollegeDepartment(yxdm));
		setCommonAttribute(mapping, form, request, response);
		return mapping.findForward("findAddNewsItemBig");
	}

	// public ActionForward findNewsItemBigs(ActionMapping mapping, ActionForm
	// form, HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// BasicPerson basicPerson = (BasicPerson)
	// request.getSession().getAttribute("logininfo");
	// boolean right =
	// checkRight.moduleOperationRight(basicPerson.getPersonAccount(),
	// "news_item_big", "select");
	// if (!right) {
	// return mapping.findForward("noright");
	// }
	//
	// // String classId = StringUtil.getStringValueForServletRequest(request,
	// // "classId");
	// String yxdm = StringUtil.getStringValueForServletRequest(request,
	// "yxdm");
	//
	// // String msg = StringUtil.getStringValueForServletRequest(request,
	// // "msg");
	//
	// if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
	// yxdm = xxdm;
	// }
	//
	// String sql = "from NewsItemBig c where 1=1";
	// String sqlCount = "select count(*) from NewsItemBig c where 1=1";
	//
	// if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
	// sql = sql + " and c.yxdm='" + yxdm + "'";
	// sqlCount = sqlCount + " and c.yxdm='" + yxdm + "'";
	// }
	// sql = sql + " order by c.ifDisplay desc, c.orderId";
	// String path = request.getContextPath() +
	// "/view/newsmanage.do?method=findNewsItemBigs";
	// String pagesize = request.getParameter("pagesize");
	// String currentPage = request.getParameter("currentPage");
	//
	// PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize,
	// path, newsManageService,
	// "newsmanageForm");
	// request.setAttribute("yxdm", yxdm);
	// request.setAttribute("allYxdmSelect",
	// newsOrgService.getSchCollegeDeptSelectBySchoolCode(xxdm, yxdm,
	// basicPerson));
	//
	// request.setAttribute("pageList", pageList);
	//
	// setCommonAttribute(mapping, form, request, response);
	//
	// return mapping.findForward("defindNewsItemBig");
	// }

	/**
	 * add new item big
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addNewsItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		String orderId = request.getParameter("orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		String ifHaveAd = request.getParameter("ifHaveAd");
		String yxdm = request.getParameter("yxdm");
		int ifIndex = Integer.parseInt(request.getParameter("ifIndex"));
		NewsItemBig nib = new NewsItemBig();
		nib.setClassId(yxdm + "" + classId);
		nib.setClassName(className);
		nib.setIfDisplay(ifDisplay);
		nib.setIfHaveAd(ifHaveAd);
		nib.setOrderId(new Integer(StringUtil.getNullInt(orderId)));
		nib.setIfIndex(ifIndex);
		nib.setYxdm(yxdm);
		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("schCollDept", newsOrgService.getSchCollegeDepartment(yxdm));
		String forward = "findAddNewsItemBig";

		try {
			List list = newsManageService.queryObjectList("from SysModule where parent.moduleId='" + yxdm + "'");
			if ((list != null) && (list.size() > 0)) {
				yxdm = ((SysModule) list.get(0)).getModuleId();
			}
			String str = newsManageService.addSecondBig(nib, yxdm);
			if ("success".equalsIgnoreCase(str)) {
				request.setAttribute("classId", "");
				request.setAttribute("className", "");
				HashMap paraMap = new HashMap();
				paraMap.put("tableType", "NewsItemBig");
				paraMap.put("yxdm", yxdm);
				request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));
				request.setAttribute("showMsg", SysObj.createAddMassageBox(nib.getClassName()));
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("className", className);
				request.setAttribute("classId", classId);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(classId));
			} else {
				forward = "error";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * 
	 * search the data of news item big for modification
	 */
	public ActionForward findAmendItemBigById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		// String msg = StringUtil.getStringValueForServletRequest(request,
		// "msg");
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");

		NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);

		request.setAttribute("newsItemBig", nib);

		request.setAttribute("schCollDept", newsOrgService.getSchCollegeDepartment(nib.getYxdm()));
		setCommonAttribute(mapping, form, request, response);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);
		return mapping.findForward("findAmendNewsItemBig");
	}




	/**
	 * modify the news item big
	 */
	public ActionForward amendNewsItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		int orderId = StringUtil.getIntValueForServletRequest(request, "orderId");

		String ifDisplay = request.getParameter("ifDisplay");
		String ifHaveAd = request.getParameter("ifHaveAd");
		String yxdm = request.getParameter("yxdm");
		int ifIndex = Integer.parseInt(request.getParameter("ifIndex"));
		NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		nib.setClassName(className);
		nib.setIfDisplay(ifDisplay);
		nib.setIfIndex(ifIndex);
		nib.setIfHaveAd(ifHaveAd);
		nib.setOrderId(orderId);

		request.setAttribute("className", className);
		setCommonAttribute(mapping, form, request, response);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String forward = "findNewsItemBig";
		try {
			String str = newsManageService.updateBigAndModuel(nib);
			if ("success".equalsIgnoreCase(str)) {
				// ssoManage.updatePermissionStatus(basicPerson);
				request.setAttribute("showMsg", SysObj.createEditMassageBox(className));
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
				return findNewsItemBig(mapping, form, request, response);
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete news item big
	 */
	public ActionForward deleteNewsItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String yxdm = request.getParameter("yxdm");
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecord = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), ids);
			if (nib == null) {
				break;
			}
			if (!newsManageService.checkUseedForNewsItemsBig(nib)) {
				newsManageService.deleteBigAndModule(nib);

				delRecord++;
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + nib.getClassName();
				} else {
					msg = msg + ";" + nib.getClassName();
				}
			}
		}
		try {
			IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecord, msg));
		request.setAttribute("yxdm", yxdm);
		return findNewsItemBig(mapping, form, request, response);
	}

	/**
	 * 
	 * search the data of news item big for order by
	 */
	public ActionForward findOrderItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm, null,
				ifIndex, classId));
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchCollegeDepart(yxdm, basicPerson));
		return mapping.findForward("orderbyNewsItemBig");
	}

	/**
	 * 
	 * update the data of index
	 */
	public ActionForward updateOrderItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_big", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String value = StringUtil.getStringValueForServletRequest(request, "classIdOrderSelectValues");
		String[] str = value.split("-");
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			if (((i == 0) || (classId == null)) && classId.equalsIgnoreCase("")) {
				classId = ids;
			}
			NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), ids);
			nib.setOrderId(i);
			newsManageService.saveOrUpdate(nib);
		}
		 IndexToHtml.createIndexHtml(request);
		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm, null,
				ifIndex, classId));
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchCollegeDepart(yxdm, basicPerson));
		request.setAttribute("showMsg", SysObj.createMassageBox("重新排序成功！"));
		return mapping.findForward("orderbyNewsItemBig");
	}

	/**
	 * 
	 * search the data of news item small for order by
	 */
	public ActionForward findOrderItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm, null,
				3, classId));
		request.setAttribute("allSmallOrderSelect", newsManageService.getNewsItemSmallSelectForOrderByClassId(classId,
				""));
		return mapping.findForward("orderbyNewsItemSmall");
	}

	/**
	 * 
	 * update the data of index
	 */
	public ActionForward updateOrderItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String value = StringUtil.getStringValueForServletRequest(request, "smallOrderSelectValues");
		String[] str = value.split("-");
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemSmall nis = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), Integer
					.parseInt(ids));
			nis.setOrderId(i);
			newsManageService.saveOrUpdate(nis);
		}
		 IndexToHtml.createIndexHtml(request);
		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm, null,
				3, classId));
		request.setAttribute("allSmallOrderSelect", newsManageService.getNewsItemSmallSelectForOrderByClassId(classId,
				""));
		request.setAttribute("showMsg", SysObj.createMassageBox("重新排序成功！"));
		return mapping.findForward("orderbyNewsItemSmall");
	}

	/**
	 * search the data of news item small
	 * 
	 */
	public ActionForward findNewsItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		// String msg = StringUtil.getStringValueForServletRequest(request,
		// "msg");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			if (!(classId.substring(0, yxdm.length())).equals(yxdm)) {
				classId = "0";
			}
		}

		String sql = "from NewsItemSmall c where 1=1";
		String sqlCount = "select count(*) from NewsItemSmall c where 1=1";

		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.yxdm='" + yxdm + "' ";
			sqlCount += " and c.newsItemBig.yxdm='" + yxdm + "'";
		}

		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and c.newsItemBig.classId='" + classId + "'";
		}
		sql = sql + " order by ifDisplay desc, typeId, orderId";
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchCollegeDepart(yxdm, basicPerson));

		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm, null, 3,
					classId));
		}
		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsItemSmall";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");

		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("pageList", pageList);
		

		return mapping.findForward("defindNewsItemSmall");
	}

	
	/**
	 * 更改小类显示状态
	 * 
	 * @return
	 * @Description:
	 */
	public ActionForward displayItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = request.getParameter("displayStatus");
		Integer typeId = Integer.valueOf(request.getParameter("typeId"));
		NewsItemSmall nis = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), typeId);
		try {
			nis.setIfDisplay("1".equals(status) ? "0" : "1");
			newsManageService.saveOrUpdate(nis);
			response.getWriter().write("1".equals(status) ? "0" : "1");
		} catch (Exception ex) {
			try {
				response.getWriter().write("-1");
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	
	/**
	 * 更改大类显示状态
	 * 
	 * @return
	 * @Description:
	 */
	public ActionForward displayItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = request.getParameter("displayStatus");
		String classId = request.getParameter("classId");
		NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		try {
			nib.setIfDisplay("1".equals(status) ? "0" : "1");
			newsManageService.saveOrUpdate(nib);
			response.getWriter().write("1".equals(status) ? "0" : "1");
		} catch (Exception ex) {
			try {
				response.getWriter().write("-1");
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	
	/**
	 * 更改大类显示状态
	 * 
	 * @return
	 * @Description:
	 */
	public ActionForward indexItemBig(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = request.getParameter("displayStatus");
		String classId = request.getParameter("classId");
		NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		try {
			nib.setIfIndex("1".equals(status) ? 0 : 1);
			newsManageService.saveOrUpdate(nib);
			response.getWriter().write("1".equals(status) ? "0" : "1");
		} catch (Exception ex) {
			try {
				response.getWriter().write("-1");
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findNewsItemSmallId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		// String msg = StringUtil.getStringValueForServletRequest(request,
		// "msg");

		NewsItemBig newsItemBig = (NewsItemBig) request.getAttribute("newsItemBig");
		if (newsItemBig == null) {
			newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		}
		HashMap paraMap = new HashMap();
		paraMap.put("tableType", "NewsItemSmall");
		paraMap.put("classId", classId);
		request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));

		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("newsItemBig", newsItemBig);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("dateFormat", newsManageService.getDateFormatString(""));
		request.setAttribute("moreDateFormat", newsManageService.getDateFormatString(""));

		return mapping.findForward("findAddNewsItemSmall");
	}

	/**
	 * add new item small
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addNewsItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String str = "success";
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		NewsItemSmall nis = new NewsItemSmall();
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");

		String typeName = request.getParameter("typeName");
		String orderId = request.getParameter("orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		String checkFlag = StringUtil.getStringValueForServletRequest(request, "checkFlag");
		String announceType = request.getParameter("announceType");

		if (NEWS_TEMPLATE_ONE.equals(announceType)) {
			String httpUrl = request.getParameter("httpUrl");
			nis.setHttpUrl(httpUrl);
		}

		if (checkFlag.isEmpty()) {
			checkFlag = NEWS_TEMPLATE_ONE;
		}
		NewsItemBig newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		nis.setTypeName(typeName);
		nis.setIfDisplay(ifDisplay);
		nis.setOrderId(new Integer(StringUtil.getNullInt(orderId)));
		nis.setNewsItemBig(newsItemBig);
		nis.setCheckFlag(checkFlag);
		nis.setAnnounceType(announceType);
		String className = request.getParameter("className");

		NewsItemConfig nic = new NewsItemConfig();
		String displayRowCount = request.getParameter("displayRowCount");
		String titleCharacterCount = request.getParameter("titleCharacterCount");
		String titleImgUrl = request.getParameter("titleImgUrl");
		String dateFormat = request.getParameter("dateFormat");
		nic.setDisplayRowCount(new Integer(StringUtil.getNullInt(displayRowCount)));
		nic.setTitleCharacterCount(new Integer(StringUtil.getNullInt(titleCharacterCount)));
		nic.setTitleImgUrl(titleImgUrl);
		nic.setDateFormat(dateFormat);

		String newFlagTime = request.getParameter("newFlagTime");
		String moreRowCount = request.getParameter("moreRowCount");
		String moreTitleCount = request.getParameter("moreTitleCount");
		String moreTitleImgUrl = request.getParameter("moreTitleImgUrl");
		String moreDateFormat = request.getParameter("moreDateFormat");
		String ifPopWindow = request.getParameter("ifPopWindow");
		String ifPermissionSearch = request.getParameter("ifPermissionSearch");

		nic.setNewFlagTime(newFlagTime);
		nic.setMoreRowCount(new Integer(StringUtil.getNullInt(moreRowCount)));
		nic.setMoreTitleCount(new Integer(StringUtil.getNullInt(moreTitleCount)));
		nic.setMoreTitleImgUrl(moreTitleImgUrl);
		nic.setMoreDateFormat(moreDateFormat);
		nic.setIfPopWindow(ifPopWindow);
		nic.setIfPermissionSearch(ifPermissionSearch);
		nic.setNewsItemSmall(nis);
		Set newsItemConfigs = new HashSet(0);
		newsItemConfigs.add(nic);
		nis.setNewsItemConfigs(newsItemConfigs);
		String forward = "findAddNewsItemSmall";
		try {

			if (newsManageService.isExistsNewsItemSmall(nis.getNewsItemBig().getClassId(), nis.getTypeName())) {
				str = "isExists";
			}
			if ("success".equalsIgnoreCase(str)) {
				str = newsManageService.addSmallItem(className, nis, classId, false, yxdm);
				newsManageService.saveObject(nis);
				nic.setNewsItemSmall(nis);
				newsManageService.saveObject(nic);
				request.setAttribute("newsItemBig", newsItemBig);
				request.setAttribute("showMsg", SysObj.createAddMassageBox(nis.getTypeName()));
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
				return findNewsItemSmallId(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("classId", classId);
				request.setAttribute("newsItemBig", newsItemBig);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(nis.getTypeName()));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAmendNewsItemSmallId(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		// String classId = StringUtil.getStringValueForServletRequest(request,
		// "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String typeId = request.getParameter("typeId");
		NewsItemSmall newsItemSmall = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(),
				new Integer(typeId));
		Set newsItemConfigs = newsItemSmall.getNewsItemConfigs();
		NewsItemConfig newsItemConfig = new NewsItemConfig();
		if (newsItemConfigs.size() > 0) {
			newsItemConfig = (NewsItemConfig) (newsItemConfigs.toArray()[0]);
			request.setAttribute("newsItemConfig", newsItemConfig);
		}

		request.setAttribute("yxdm", yxdm);
		request.setAttribute("msg", msg);
		request.setAttribute("typeId", typeId);

		request.setAttribute("classId", newsItemSmall.getNewsItemBig().getClassId());

		request.setAttribute("newsItemBig", newsItemSmall.getNewsItemBig());
		request.setAttribute("newsItemSmall", newsItemSmall);
		request.setAttribute("dateFormat", newsManageService.getDateFormatString(newsItemConfig.getDateFormat()));
		request.setAttribute("moreDateFormat", newsManageService
				.getDateFormatString(newsItemConfig.getMoreDateFormat()));

		return mapping.findForward("findAmendNewsItemSmall");
	}

	/**
	 * add new item big
	 */
	@SuppressWarnings("unchecked")
	public ActionForward amendNewsItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String typeId = request.getParameter("typeId");
		SysModule mondel = new SysModule();

		NewsItemSmall nis = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), new Integer(typeId));
		Set newsItemConfigs = nis.getNewsItemConfigs();
		NewsItemConfig nic = new NewsItemConfig();
		if (newsItemConfigs.size() > 0) {
			nic = (NewsItemConfig) (newsItemConfigs.toArray()[0]);
		}
		String announceType = request.getParameter("announceType");
		if (NEWS_TEMPLATE_ONE.equals(announceType)) {
			String httpUrl = request.getParameter("httpUrl");
			nis.setHttpUrl(httpUrl);
		}

		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");
		String typeName = request.getParameter("typeName");
		String orderId = request.getParameter("orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		String checkFlag = StringUtil.getStringValueForServletRequest(request, "checkFlag");

		String url = "newsmanage.do?method=findNewsContentManage&classId=";
		mondel.setModuleName(typeName);
		mondel.setModuleId(request.getParameter("maxModuelId"));
		mondel.setUpModule(request.getParameter("upMondel"));
		mondel.setUrl(url + classId + "&typeId=" + nis.getTypeId() + "&parentModuleFlag=news_index_bigItem"
				+ request.getParameter("moduel2_int"));
		mondel.setIfOpen("1");

		if (checkFlag.equalsIgnoreCase("")) {
			checkFlag = NEWS_TEMPLATE_ONE;
		}

		nis.setAnnounceType(announceType);
		nis.setTypeName(typeName.trim());
		nis.setIfDisplay(ifDisplay);
		nis.setOrderId(new Integer(StringUtil.getNullInt(orderId)));
		nis.setCheckFlag(checkFlag);

		String displayRowCount = request.getParameter("displayRowCount");
		String titleCharacterCount = request.getParameter("titleCharacterCount");
		String titleImgUrl = request.getParameter("titleImgUrl");
		String dateFormat = request.getParameter("dateFormat");
		nic.setDisplayRowCount(new Integer(StringUtil.getNullInt(displayRowCount)));
		nic.setTitleCharacterCount(new Integer(StringUtil.getNullInt(titleCharacterCount)));
		nic.setTitleImgUrl(titleImgUrl);
		nic.setDateFormat(dateFormat);

		String newFlagTime = request.getParameter("newFlagTime");
		String moreRowCount = request.getParameter("moreRowCount");
		String moreTitleCount = request.getParameter("moreTitleCount");
		String moreTitleImgUrl = request.getParameter("moreTitleImgUrl");
		String moreDateFormat = request.getParameter("moreDateFormat");
		String ifPopWindow = request.getParameter("ifPopWindow");
		String ifPermissionSearch = request.getParameter("ifPermissionSearch");

		nic.setMoreRowCount(new Integer(StringUtil.getNullInt(moreRowCount)));
		nic.setMoreTitleCount(new Integer(StringUtil.getNullInt(moreTitleCount)));
		nic.setMoreTitleImgUrl(moreTitleImgUrl);
		nic.setMoreDateFormat(moreDateFormat);
		nic.setNewFlagTime(newFlagTime);
		nic.setIfPopWindow(ifPopWindow);
		nic.setIfPermissionSearch(ifPermissionSearch);
		nic.setNewsItemSmall(nis);

		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String forward = "findNewsItemSmall";
		try {
			// String str = "success";
			newsManageService.saveObject(nic);
			newsManageService.updateSmallAndModuel(nis);
			// ssoManage.updatePermissionStatus(basicPerson);
			IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
			request.setAttribute("showMsg", SysObj.createEditMassageBox(nis.getTypeName()));
			setCommonAttribute(mapping, form, request, response);
			return findNewsItemSmall(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			forward = "error";
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete new item big
	 */
	public ActionForward deleteNewsItemSmall(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delrecords = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemSmall nis = (NewsItemSmall) newsManageService
					.queryObjectById(new NewsItemSmall(), new Integer(ids));
			if (nis == null) {
				break;
			}
			if (!newsManageService.checkUseedForNewsItemSmall(nis)) {
				delrecords++;
				Object[] obj = nis.getNewsItemConfigs().toArray();
				if (obj.length > 0) {
					for (int j = 0; j < obj.length; j++) {
						newsManageService.deleteObject(obj[j]);
					}
				}
				newsManageService.deleteSmallAndModule(nis);
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + nis.getTypeName();
				} else {
					msg = msg + ";" + nis.getTypeName();
				}
			}
		}
		try {
			IndexToHtml.createIndexHtml(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delrecords, msg));
		return findNewsItemSmall(mapping, form, request, response);
	}

	/**
	 * 
	 * search the data of navigation for order by
	 */
	public ActionForward findOrderNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm,
				GlobalConst.IS_DISPLAY, ifIndex, classId));
		request.setAttribute("allNavigationOrderSelect", newsManageService.getNewsNavigationSelectForOrderByClassId(
				classId, ""));
		return mapping.findForward("orderbyNavigation");
	}

	/**
	 * 
	 * update the data of index
	 */
	public ActionForward updateOrderNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		int ifIndex = StringUtil.getIntValueForServletRequest(request, "ifIndex");

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String value = StringUtil.getStringValueForServletRequest(request, "orderNavigationSelectValues");
		String[] str = value.split("-");
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemNavigation nin = (NewsItemNavigation) newsManageService.queryObjectById(new NewsItemNavigation(),
					Integer.parseInt(ids));
			nin.setOrderId(i);
			newsManageService.saveOrUpdate(nin);
		}

		request.setAttribute("ifIndex", ifIndex);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("allClassIdOrderSelect", newsManageService.getNewsItemBigSelectForOrderByYxdm(yxdm,
				GlobalConst.IS_DISPLAY, ifIndex, classId));
		request.setAttribute("allNavigationOrderSelect", newsManageService.getNewsNavigationSelectForOrderByClassId(
				classId, ""));
		request.setAttribute("showMsg", SysObj.createMassageBox("重新排序成功！"));
		return mapping.findForward("orderbyNavigation");
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");

		String sql = "from NewsItemNavigation c where 1=1";
		String sqlCount = "select count(*) from NewsItemNavigation c where 1=1";

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			sql += " and c.newsItemBig.yxdm='" + yxdm + "' ";
			sqlCount += " and c.newsItemBig.yxdm='" + yxdm + "'";
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			sql += " and c.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and c.newsItemBig.classId='" + classId + "'";
		}
		sql = sql + " order by orderId";
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm, null, 3,
					classId));
		}
		String path = request.getContextPath() + "/view/newsmanage.do?method=findNavigation";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");

		request.setAttribute("allYxdmSelect", newsOrgService.getSchCollegeDeptSelectBySchoolCode(xxdm, yxdm,
				basicPerson));

		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("pageList", pageList);
		request.setAttribute("msg", request.getAttribute("msg"));

		return mapping.findForward("defineNewsNavigation");
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAddNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String msg = request.getParameter("msg");

		String classId = request.getParameter("classId");
		NewsItemBig newsItemBig = (NewsItemBig) request.getAttribute("newsItemBig");
		if (newsItemBig == null) {
			newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		}
		HashMap paraMap = new HashMap();
		paraMap.put("tableType", "NewsItemNavigation");
		paraMap.put("classId", classId);
		request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));

		request.setAttribute("newsItemBig", newsItemBig);
		request.setAttribute("msg", msg);
		request.setAttribute("classId", classId);
		return mapping.findForward("addNewsNavigation");
	}

	/**
	 * add new item big
	 */
	public ActionForward addNewsNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String navigationName = request.getParameter("navigationName");
		String orderId = request.getParameter("orderId");
		String navigationUrl = request.getParameter("navigationUrl");
		String yxdm = request.getParameter("yxdm");

		NewsItemBig newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);

		NewsItemNavigation ning = new NewsItemNavigation();
		ning.setNavigationName(navigationName);
		ning.setNavigationUrl(navigationUrl);
		ning.setOrderId(StringUtil.getNullInt(orderId));
		ning.setNewsItemBig(newsItemBig);

		String forward = "findAddNavigation";
		try {
			String str = "success";
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveObject(ning);
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createAddMassageBox(ning.getNavigationName()));
				return findAddNavigation(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(ning.getNavigationName()));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAmendNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String msg = request.getParameter("msg");
		if (msg == null) {
			msg = (String) request.getAttribute("msg");
		}
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String navigationId = request.getParameter("navigationId");
		NewsItemNavigation ning = (NewsItemNavigation) newsManageService.queryObjectById(new NewsItemNavigation(),
				StringUtil.getNullInt(navigationId));
		request.setAttribute("msg", msg);
		request.setAttribute("navigationId", navigationId);
		request.setAttribute("newsItemNavigation", ning);
		return mapping.findForward("amendNewsNavigation");
	}

	/**
	 * add new item big
	 */
	public ActionForward amendNewsNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");

		String navigationName = request.getParameter("navigationName");
		String orderId = request.getParameter("orderId");
		String navigationUrl = request.getParameter("navigationUrl");
		String navigationId = request.getParameter("navigationId");

		NewsItemNavigation ning = (NewsItemNavigation) newsManageService.queryObjectById(new NewsItemNavigation(),
				StringUtil.getNullInt(navigationId));
		ning.setNavigationName(navigationName);
		ning.setNavigationUrl(navigationUrl);
		ning.setOrderId(StringUtil.getNullInt(orderId));
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String forward = "findAmendNavigation";
		try {
			// String str = "success";
			newsManageService.saveObject(ning);
			request.setAttribute("classId", classId);
			request.setAttribute("yxdm", yxdm);
			request.setAttribute("navigationId", navigationId);
			request.setAttribute("showMsg", SysObj.createEditMassageBox(ning.getNavigationName()));
			setCommonAttribute(mapping, form, request, response);
			return findAmendNavigation(mapping, form, request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
			forward = "error";
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete new item big
	 */
	public ActionForward deleteNewsNavigation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_navigation",
				"delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecords = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemNavigation ning = (NewsItemNavigation) newsManageService.queryObjectById(new NewsItemNavigation(),
					new Integer(ids));
			if (ning == null) {
				break;
			}
			newsManageService.deleteObject(ning);
			delRecords++;
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecords, msg));
		return findNavigation(mapping, form, request, response);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");
		if ((yxdm == null) && (classId == null)) {
			yxdm = xxdm;
		}

		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			if (!(classId.substring(0, yxdm.length())).equals(yxdm)) {
				classId = "0";
			}
		}

		String sql = "from NewsAdType c where 1=1";
		String sqlCount = "select count(*) from NewsAdType c where 1=1";

		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			sql += " and c.newsItemBig.yxdm='" + yxdm + "' ";
			sqlCount += " and c.newsItemBig.yxdm='" + yxdm + "'";
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and c.newsItemBig.classId='" + classId + "'";
		}
		sql += "order by c.newsItemBig.orderId";

		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm,
					GlobalConst.IS_HAVEAD, classId));
		}

		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsAdType";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");

		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchoolDepart(yxdm, basicPerson));

		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("pageList", pageList);
		request.setAttribute("msg", request.getAttribute("msg"));

		return mapping.findForward("defineNewsAdType");
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAddNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");

		NewsItemBig newsItemBig = (NewsItemBig) request.getAttribute("newsItemBig");
		if (newsItemBig == null) {
			newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		}
		request.setAttribute("newsItemBig", newsItemBig);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		return mapping.findForward("addNewsAdType");
	}

	/**
	 * add new item big
	 */
	public ActionForward addNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");
		String adTypeName = request.getParameter("adTypeName");
		String adHeight = request.getParameter("adHeight");
		String adWidth = request.getParameter("adWidth");
		String adTypeTag = request.getParameter("adTypeTag");

		NewsItemBig newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);

		NewsAdType nat = new NewsAdType();
		nat.setAdTypeName(adTypeName);
		nat.setAdTypeTag(adTypeTag);
		nat.setAdWidth(StringUtil.getNullInt(adWidth));
		nat.setAdHeight(StringUtil.getNullInt(adHeight));
		nat.setNewsItemBig(newsItemBig);

		String forward = "addNewsAdType";
		try {
			String str = "success";
			// if (newsManageService.isExistsNewsAdTypeByName(adTypeName)) {
			// str = "isExists";
			// }
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveObject(nat);
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createAddMassageBox(adTypeName));
				return findAddNewsAdType(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(adTypeName));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAmendNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = request.getParameter("yxdm");
		String msg = request.getParameter("msg");
		if (msg == null) {
			msg = (String) request.getAttribute("msg");
		}
		String adTypeId = request.getParameter("adTypeId");
		NewsAdType nat = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), StringUtil
				.getNullInt(adTypeId));

		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("msg", msg);
		request.setAttribute("adTypeId", adTypeId);
		request.setAttribute("newsAdType", nat);
		return mapping.findForward("amendNewsAdType");
	}

	/**
	 * add new item big
	 */
	public ActionForward amendNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");
		String adTypeName = request.getParameter("adTypeName");
		String adHeight = request.getParameter("adHeight");
		String adWidth = request.getParameter("adWidth");
		String adTypeTag = request.getParameter("adTypeTag");

		String adTypeId = request.getParameter("adTypeId");

		// NewsItemBig newsItemBig =
		// (NewsItemBig)newsManageService.queryObjectById(new NewsItemBig(),
		// classId );

		NewsAdType nat = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), StringUtil
				.getNullInt(adTypeId));

		nat.setAdTypeName(adTypeName);
		nat.setAdTypeTag(adTypeTag);
		nat.setAdWidth(StringUtil.getNullInt(adWidth));
		nat.setAdHeight(StringUtil.getNullInt(adHeight));
		// nat.setNewsItemBig(newsItemBig);

		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String forward = "amendNewsAdType";
		try {
			String str = "success";
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveObject(nat);
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createEditMassageBox(adTypeName));
				return findAmendNewsAdType(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("classId", classId);
				request.setAttribute("classId", classId);
				request.setAttribute("yxdm", yxdm);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(adTypeName));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete news ad type
	 */
	public ActionForward deleteNewsAdType(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_type", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecords = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsAdType nat = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), StringUtil
					.getNullInt(ids));
			if (nat == null) {
				break;
			}
			if (nat.getNewsAdManages().size() <= 0) {
				newsManageService.deleteObject(nat);
				delRecords++;
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + nat.getAdTypeName();
				} else {
					msg = msg + ";" + nat.getAdTypeName();
				}
			}
		}
//		if (!"".equalsIgnoreCase(msg)) {
//			msg = "还存在相关的信息，不能删除！";
//		} else {
//			msg = "删除成功！";
//		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecords, msg));
		setCommonAttribute(mapping, form, request, response);
		return findNewsAdType(mapping, form, request, response);
	}

	public ActionForward findAdMangeTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		return mapping.findForward("newsAdManageTreeInit");
	}

	public ActionForward findNewsAdManageInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		return findNewsAdManage(mapping, form, request, response);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");
		String adTypeId = request.getParameter("adTypeId");
		String checkId = "";
		NewsItemBig itembig = newsManageService.getNewsItemBigByClassId(classId);
		if ((adTypeId != null) && !adTypeId.equalsIgnoreCase("") && !adTypeId.equalsIgnoreCase("0")) {
			NewsAdType newsAdType = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), StringUtil
					.getNullInt(adTypeId));
			checkId = newsAdType.getNewsItemBig().getClassId();
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			if (!checkId.equalsIgnoreCase("") && !classId.equalsIgnoreCase(checkId)) {
				adTypeId = "0";
			}

			if (!(classId.substring(0, yxdm.length())).equals(yxdm)) {
				classId = "0";
			}
		}

		if ((yxdm == null) && (classId == null)) {
			yxdm = xxdm;
		}
		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = itembig.getYxdm();
		}

		String sql = "from NewsAdManage c where 1=1";
		String sqlCount = "select count(*) from NewsAdManage c where 1=1";

		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			sql += " and c.newsAdType.newsItemBig.yxdm='" + yxdm + "'";
			sqlCount += " and c.newsAdType.newsItemBig.yxdm='" + yxdm + "'";
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			sql += " and c.newsAdType.newsItemBig.classId='" + classId + "'";
			sqlCount += " and c.newsAdType.newsItemBig.classId='" + classId + "'";
			if ((adTypeId != null) && !adTypeId.equalsIgnoreCase("0")) {
				sql += " and c.newsAdType.adTypeId=" + adTypeId;
				sqlCount += " and c.newsAdType.adTypeId=" + adTypeId;
			}
		}
		sql += "order by c.newsAdType.newsItemBig.orderId";
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm,
					GlobalConst.IS_HAVEAD, classId));
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.trim().equalsIgnoreCase("")) {
			request.setAttribute("allAdTypeSelect", newsManageService.getNewsAdTypeSelectByClassId(classId, adTypeId
					+ ""));
		}
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchoolDepart(yxdm, basicPerson));

		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsAdManage";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");

		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("adTypeId", adTypeId);
		request.setAttribute("pageList", pageList);
		request.setAttribute("msg", request.getAttribute("msg"));
		return mapping.findForward("defineAdManage");
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAddNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String msg = request.getParameter("msg");
		if ((msg == null) || msg.trim().equalsIgnoreCase("")) {
			msg = (String) request.getAttribute("msg");
		}
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String adTypeId = request.getParameter("adTypeId");

		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			request.setAttribute("allAdTypeSelect", newsManageService.getNewsAdTypeSelectByClassId(classId, adTypeId
					+ ""));
		}
		request.setAttribute("msg", msg);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("adTypeId", adTypeId);

		return mapping.findForward("addAdManage");
	}

	/**
	 * add new item big
	 */
	public ActionForward addNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = request.getParameter("yxdm");
		String adTypeId = request.getParameter("adTypeId");
		String classId = request.getParameter("classId");

		String adName = request.getParameter("adName");
		String adPosition = request.getParameter("adPosition");
		String adImgUrl = request.getParameter("adImgUrl");
		String adHttpUrl = request.getParameter("adHttpUrl");
		String personCode = request.getParameter("personCode");
		String adValidityDate = request.getParameter("adValidityDate");
		String ifDisplay = request.getParameter("ifDisplay");

		NewsAdType newsAdType = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), new Integer(adTypeId));

		NewsAdManage nam = new NewsAdManage();
		nam.setAdHttpUrl(adHttpUrl);
		nam.setAdName(adName);
		nam.setAdImgUrl(adImgUrl);
		nam.setAdPosition(StringUtil.getNullInt(adPosition));
		nam.setAdAnnounceDate(new Date());
		nam.setAdValidityDate(mydate.getUtilDate(adValidityDate));
		nam.setIfDisplay(ifDisplay);
		nam.setPersonCode(personCode);
		nam.setNewsAdType(newsAdType);

		request.setAttribute("adTypeId", adTypeId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);

		String forward = "addAdManage";
		try {
			String str = "success";
			// if (newsManageService.isExistsNewsAdManageByName(adName)) {
			// str = "isExists";
			// }
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveAdManage(nam, yxdm);
				request.setAttribute("showMsg", SysObj.createAddMassageBox(adName));
				return findAddNewsAdManage(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(adName));
				return findAddNewsAdManage(mapping, form, request, response);
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findAmendNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String adId = StringUtil.getStringValueForServletRequest(request, "adId");

		NewsAdManage nam = (NewsAdManage) newsManageService.queryObjectById(new NewsAdManage(), StringUtil
				.getNullInt(adId));
		String classId = nam.getNewsAdType().getNewsItemBig().getClassId();
		request.setAttribute("allAdTypeSelect", newsManageService.getNewsAdTypeSelectByClassId(classId, nam
				.getNewsAdType().getAdTypeId()
				+ ""));
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);
		request.setAttribute("msg", msg);
		request.setAttribute("adId", adId);
		request.setAttribute("newsAdManage", nam);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("adTypeId", nam.getNewsAdType().getAdTypeId());
		return mapping.findForward("amendAdManage");
	}

	/**
	 * add new item big
	 */
	public ActionForward amendNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "update");
		if (!right) {
			return mapping.findForward("noright");
		}

		String yxdm = request.getParameter("yxdm");
		String adId = request.getParameter("adId");
		if ((adId == null) || adId.trim().equalsIgnoreCase("")) {
			adId = (String) request.getAttribute("adId");
		}

		String classId = request.getParameter("classId");
		if ((classId == null) || classId.trim().equalsIgnoreCase("")) {
			classId = (String) request.getAttribute("classId");
		}

		String adTypeId = request.getParameter("adTypeId");
		if ((adTypeId == null) || adTypeId.trim().equalsIgnoreCase("")) {
			adTypeId = (String) request.getAttribute("adTypeId");
		}

		String adName = request.getParameter("adName");
		String adPosition = request.getParameter("adPosition");
		String adImgUrl = request.getParameter("adImgUrl");
		String adHttpUrl = request.getParameter("adHttpUrl");
		String personCode = request.getParameter("personCode");
		String adValidityDate = request.getParameter("adValidityDate");
		String ifDisplay = request.getParameter("ifDisplay");

		NewsAdManage nam = (NewsAdManage) newsManageService.queryObjectById(new NewsAdManage(), StringUtil
				.getNullInt(adId));
		NewsAdType newsAdType = (NewsAdType) newsManageService.queryObjectById(new NewsAdType(), StringUtil
				.getNullInt(adTypeId));

		nam.setAdHttpUrl(adHttpUrl);
		nam.setAdName(adName);
		nam.setAdImgUrl(adImgUrl);
		nam.setAdPosition(StringUtil.getNullInt(adPosition));
		nam.setAdValidityDate(mydate.getUtilDate(adValidityDate));
		nam.setIfDisplay(ifDisplay);
		nam.setPersonCode(personCode);
		nam.setNewsAdType(newsAdType);

		request.setAttribute("adId", adId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("adTypeId", adTypeId);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);
		String forward = "amendAdManage";
		try {
			String str = "success";
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.updateAdMange(nam, yxdm);
				request.setAttribute("showMsg", SysObj.createEditMassageBox(adName));
				return findAmendNewsAdManage(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(adName));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete news ad type
	 */
	public ActionForward deleteNewsAdManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_ad_manage", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecords = 0;
		String realFilePath = SystemConfig.getInstance().getDirConstant("path");
		String adImgUrl="";
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsAdManage nam = (NewsAdManage) newsManageService.queryObjectById(new NewsAdManage(), StringUtil
					.getNullInt(ids));
			if (nam == null) {
				break;
			}
			adImgUrl = nam.getAdImgUrl();
			if((adImgUrl!=null) && (adImgUrl.trim().length()!=0)){
				String filePath = realFilePath + adImgUrl;
				File file = new File(filePath);
				FileUtils.deleteQuietly(file);
			}
			newsManageService.deleteObject(nam);
			delRecords++;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecords, msg));
		return findNewsAdManage(mapping, form, request, response);
	}

	/**
	 * 
	 * @Description:信息发布树查询
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findNewsItemsBigTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = request.getParameter("yxdm");
		List list = new ArrayList();
		list = newsOrgService.findSpecialDepartByPersonId(basicPerson);
		if (list != null) {
			SchCollegeDepartment scd = (SchCollegeDepartment) list.get(0);
			yxdm = scd.getYxdm();
		}

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String classId = request.getParameter("classId");
		request.setAttribute("classId", classId);
		request.setAttribute("newsItemBig", newsManageService.getDefaultNewsItemBigByClassId(yxdm, classId));
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchoolDepart(yxdm, basicPerson));
		if (servlet.getServletContext().getAttribute(basicPerson.getPersonAccount()) == null) {
			request.setAttribute("createZTree", newsManageService.createZTree(yxdm, getBasePath(request)));
		} else {
			return null;
		}
		return mapping.findForward("newsItemsBigTreeInit");
	}

	public ActionForward findNewsContentManageInit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		return mapping.findForward("newsContentManageInit");

	}

	/**
	 * search news content info
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String parentModuleFlag = request.getParameter("parentModuleFlag");
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");
		String typeId = request.getParameter("typeId");
		NewsItemBig itembig = newsManageService.getNewsItemBigByClassId(classId);

		if ((yxdm == null) || yxdm.trim().equals("")) {
			yxdm = itembig.getYxdm();
		}
		if ((parentModuleFlag == null) || parentModuleFlag.trim().equals("")) {
			parentModuleFlag = "news_content_manage";
		}
		NewsItemBig newsItemBig = newsManageService.getDefaultNewsItemBigByClassId(yxdm, classId, basicPerson);
		if (newsItemBig != null) {
			classId = newsItemBig.getClassId();
		} else {
			return null;
		}
		request.setAttribute("newsItemBig", newsItemBig);
		classId = newsItemBig.getClassId();
		request.setAttribute("classId", classId);

		String sql = "select c from NewsContentManage c ";
		// "concat(''+c.newsItemSmalls.typeId,',')"+
		// "(select nis.typeName from NewsItemSmall nis where
		// nis.newsContentManages.newsId=c.newsId)"+
		// " from NewsContentManage c ";
		String sqlCount = "select count(distinct c.newsId) from NewsContentManage c ";

		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			sql += " join c.newsItemSmalls s where s.newsItemBig.classId='" + classId + "' ";
			sqlCount += " join c.newsItemSmalls s where s.newsItemBig.classId='" + classId + "'";
		}
		if ((typeId != null) && !typeId.equalsIgnoreCase("0") && !typeId.equalsIgnoreCase("")
				&& (!typeId.equals("null"))) {
			sql += " and s.typeId=" + typeId;
			sqlCount += " and s.typeId=" + typeId;
		}
		sql += "order by c.ifTopRow desc,c.newsDate desc";

		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsContentManage&parentModuleFlag="
				+ parentModuleFlag;
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		// ------------------过滤多条重复数据
		// ------------------begin
		// List<NewsContentManage> tempList = new
		// ArrayList<NewsContentManage>();
		// List<NewsContentManage> list = pageList.getList();
		//		
		// for (int i = 0; i < list.size(); i++) {
		// if (!tempList.contains(list.get(i))) {
		// tempList.add(list.get(i));
		// }
		// }
		// pageList.setList(tempList);
		// pageList.setPageSize(tempList.size());
		// pageList.setRecordCounts(tempList.size());
		// List<NewsContentManage> list = pageList.getList();
		// HashSet set = new HashSet(list);
		// list.clear();
		// list.addAll(set);
		// pageList.setList(list);
		// pageList.setPageSize(list.size());

		// pageList.setRecordCounts(tempList.size());
		// System.out.println(pageList.getRecordCounts());
		// if(k!=m){
		// pageList.setRecordCounts(pageList.getRecordCounts()-(k-m));
		// }
		// ------------------end
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("typeId", typeId);
		request.setAttribute("pageList", pageList);
		request.setAttribute("msg", request.getAttribute("msg"));
		request.setAttribute("parentModuleFlag", parentModuleFlag);
		return mapping.findForward("defineNewsContentManage");
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAddNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		NewsItemBig itemBig = newsManageService.getNewsItemBigByClassId(classId);
		NewsItemSmall itemSmall = new NewsItemSmall();
		yxdm = itemBig.getYxdm();
		int typeId = StringUtil.getIntValueForServletRequest(request, "typeId");
		int announceType = StringUtil.getIntValueForServletRequest(request, "announceType");
		if ((String.valueOf(typeId) == null) || (typeId == 0)) {
			List list = newsManageService.queryNewsItemSmallByClassId(classId, GlobalConst.IS_DISPLAY);
			if (list.size() > 0) {
				itemSmall = (NewsItemSmall) list.get(0);
			}
		}

		else {
			itemSmall = newsManageService.getNewsItemSmallByTypeId(typeId);
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			request.setAttribute("allTypeList", newsManageService.queryNewsItemSmallByClassId(classId, null));
			request.setAttribute("typeId", typeId);
		}
		// -------------添加大类List
		// -------------begin
		StringBuffer buffer = new StringBuffer();
		List newsList = newsManageService.queryObjectList("from NewsItemBig nib where nib.yxdm = '" + yxdm
				+ "'order by nib.classId");
		if ((newsList != null) && (newsList.size() != 0)) {
			buffer.append("<option value=''>— 请选择 —</option>");
			for (int i = 0; i < newsList.size(); i++) {
				NewsItemBig bigItems = (NewsItemBig) newsList.get(i);
				buffer.append("<optgroup label=" + bigItems.getClassName() + "></optgroup>");
				if (bigItems.getNewsItemSmalls().size() != 0) {
					Iterator<NewsItemSmall> newsItemSmallIt = bigItems.getNewsItemSmalls().iterator();
					while (newsItemSmallIt.hasNext()) {
						NewsItemSmall nis = newsItemSmallIt.next();
						buffer.append("<option value=" + nis.getTypeId() + ">--" + nis.getTypeName() + "</option>");
					}
				}
			}
		}
		request.setAttribute("buffer", buffer);
		// -------------end

		List departmentList = newsManageService.queryObjectList("from BasicDepartment");
		try {
//			initWebNoteEditor(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("msg", msg);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("announceType", announceType);
		request.setAttribute("itemSmall", itemSmall);
		request.setAttribute("parentModuleFlag", parentModuleFlag);
		request.setAttribute("newsDate", MyDate.getNowDetailTime());
		return mapping.findForward("addNewsContentManage");
	}

	/**
	 * add new item big
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");
		NewsItemBig newsItemBig = newsManageService.getNewsItemBigByClassId(classId);
		NewsItemSmall itemSmall = null;
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		//上传内容
		SmartUpload mySmartUpload = new SmartUpload();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this.servlet, request, response, null,
				true, 8192, true);
		mySmartUpload.setCharset("utf-8"); // 编码方式应该与表单所在的页面的编码相同，否则可能出现乱码
		try {
			mySmartUpload.initialize(pageContext);
			mySmartUpload.upload();
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		int typeId = Integer.valueOf(mySmartUpload.getRequest().getParameter("typeId"));
		String announceType = mySmartUpload.getRequest().getParameter("announceType");
		String newsTitle = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsTitle" + announceType));
		String newsKeyword = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsKeyword"));
		String newsWriter = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsWriter"));
		String newsSource = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsSource"));
		
		String announcePerson = basicPerson.getPersonId().toString();
		
		String newsDate = (mySmartUpload.getRequest().getParameter("newsDate"));
		String newsTemplate = mySmartUpload.getRequest().getParameter("newsTemplate");
		String departmentId = mySmartUpload.getRequest().getParameter("publishDept");
		String ifImgNews = mySmartUpload.getRequest().getParameter("ifImgNews" + announceType);
		String imgNewsAddress = mySmartUpload.getRequest().getParameter("imgNewsAddress" + announceType);
		String imgNewsTitle = mySmartUpload.getRequest().getParameter("imgNewsTitle" + announceType);
		String ifTitleImg = mySmartUpload.getRequest().getParameter("ifTitleImg" + announceType);
		String ifTopRow = mySmartUpload.getRequest().getParameter("ifTopRow" + announceType);

		String ifRecommend = mySmartUpload.getRequest().getParameter("ifRecommend" + announceType);
		String httpUrl = mySmartUpload.getRequest().getParameter("httpUrl");
		String annexAddress = mySmartUpload.getRequest().getParameter("file_annex_url");
		String ifVodNews = mySmartUpload.getRequest().getParameter("ifVodNews");
		String displayOrderId = mySmartUpload.getRequest().getParameter("displayOrderId");
		String checkPerson = mySmartUpload.getRequest().getParameter("checkPerson");
		String checkDate = mySmartUpload.getRequest().getParameter("checkDate");
		String visitCount = mySmartUpload.getRequest().getParameter("visitCount");
		
		String newsContent =  mySmartUpload.getRequest().getParameter("editor_k");
		String checkFlag = mySmartUpload.getRequest().getParameter("checkFlag");
		Set<NewsItemSmall> ncmSet = new HashSet<NewsItemSmall>();
		String selStr = request.getParameter("selStr"); // 获得多选下拉框的值
		String[] newItemsId = selStr.split(",");
		for (int i = 0; i < newItemsId.length; i++) {
			if ((newItemsId[i] != null) && (newItemsId[i].trim().length() > 0)) {
				NewsItemSmall ncmTmp = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), Integer
						.parseInt(newItemsId[i]));
				ncmSet.add(ncmTmp);
			}
		}
		NewsContentManage ncm = new NewsContentManage();
		ncm.setNewsItemSmalls(ncmSet);
		ncm.setAnnounceType(announceType);
		ncm.setNewsTitle(newsTitle);
		//内容为经过处理
		ncm.setNewsContent(HtmlAndText.HtmlToText(newsContent));
		
		ncm.setNewsKeyword(newsKeyword);
		ncm.setNewsWriter(newsWriter);
		ncm.setNewsSource(newsSource);
		ncm.setAnnouncePerson(announcePerson);
		if ((newsDate == null) || (newsDate.trim().length() <= 0)) {
			newsDate = MyDate.getNowDetailTime();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
			try {
				ncm.setNewsDate(format.parse(newsDate));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		 
		ncm.setNewsTemplate(newsTemplate);
		ncm.setIfImgNews(ifImgNews);
		if(imgNewsAddress!=null&&!"".equals(imgNewsAddress)){
			ncm.setImgNewsAddress(GlobalConst.UPLOAD_PATH+imgNewsAddress);
		}
		ncm.setImgNewsTitle(imgNewsTitle);
		ncm.setIfTitleImg(ifTitleImg);
		if ("".equals(ifTopRow) || (ifTopRow == null)) {
			ifTopRow="0";
		}
		ncm.setIfTopRow(ifTopRow);
		ncm.setIfRecommend(ifRecommend);
		ncm.setHttpUrl(httpUrl);
		if(annexAddress!=null&&!"".equals(annexAddress)){
			ncm.setAnnexAddress(GlobalConst.UPLOAD_PATH+annexAddress);
		}
		ncm.setIfVodNews(ifVodNews);
		ncm.setDisplayOrderId(StringUtil.getNullInt(displayOrderId));
		ncm.setCheckPerson(checkPerson);
		ncm.setCheckDate(mydate.getUtilDate(checkDate));
		
		String tmp = HtmlFile.getHtmlFileNameForDatabase(ncm,GlobalConst.SchoolCode);
		
		if (!"".equals(tmp)|| (tmp != null)){
			ncm.setNewsFilesPath(tmp.substring(0,tmp.lastIndexOf("/")+1));
		}
		
		if ("".equals(visitCount) || (visitCount == null)) {
			visitCount = "0";
		}
		ncm.setVisitCount(StringUtil.getNullInt(visitCount));
		ncm.setCheckFlag(checkFlag);// 审核标志
		BasicDepartment department = (BasicDepartment) newsManageService.queryObjectById(new BasicDepartment(), Long
				.parseLong(departmentId));
		ncm.setDepartment(department);
		if ("0".equals(announceType)) {
			String htmlFileName = HtmlFile.getHtmlFileNameForDatabase(ncm, yxdm);
			ncm.setHtmlFileName(htmlFileName);
		}
		if(String.valueOf(typeId) !=null && !"".equals(typeId)){
			itemSmall = newsManageService.getNewsItemSmallByTypeId(typeId);
		}
		//monitor 		
		Monitor monitor = new Monitor();
		monitor.setOperator(basicPerson.getPersonName());
		monitor.setClassName(newsItemBig.getClassName());
		monitor.setTypeName(itemSmall.getTypeName());
		monitor.setOperateTime(new Date());
		monitor.setStatus("add");
		monitor.setLoginfo("<font color=\"#ff0000\">"+basicPerson.getPersonName()+"</font>  "+"添加："+newsTitle);
		
		request.setAttribute("typeId", typeId);
		request.setAttribute("announceType", announceType);

		String forward = "addNewsContentManage";
		try {
			String str = "success";
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveNewsContent(ncm);
				newsManageService.saveMonitor(monitor);
				if (announceType.equalsIgnoreCase("0")) {
					try {
						createHtml(request, yxdm,classId, ncm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				request.setAttribute("showMsg", SysObj.createAddMassageBox(""));
				request.setAttribute("parentModuleFlag", parentModuleFlag);
				IndexToHtml.createIndexHtml(request);
				return findAddNewsContentManage(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(ncm.getNewsTitle()));
				return findAddNewsContentManage(mapping, form, request, response);
			} else {
				forward = "error";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	private String getBasePath(HttpServletRequest request) {
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/";
		return basePath;
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAmendNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String parentModuleFlag = request.getParameter("parentModuleFlag");
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		long newsId = StringUtil.getLongValueForServletRequest(request, "newsId");
		String classId = StringUtil.getValueForServletRequest(request, "classId") + "";
		String typeId = StringUtil.getValueForServletRequest(request, "typeId") + "";
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		NewsItemBig itemBig = newsManageService.getNewsItemBigByClassId(classId);
		yxdm = itemBig.getYxdm();
		NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(), newsId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ncm.setNewsContent(HtmlAndText.TextToHtml(ncm.getNewsContent()));
		request.setAttribute("newsItemSmall", ncm.getNewsItemSmalls());

		List departmentList = newsManageService.queryObjectList("from BasicDepartment");
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("announceType", ncm.getAnnounceType());
		request.setAttribute("msg", msg);
		request.setAttribute("newsContentManage", ncm);
		request.setAttribute("newsId", newsId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("classId", classId);
		request.setAttribute("newsDate", format.format(ncm.getNewsDate()));
		request.setAttribute("yxdm", yxdm);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);
		// -------------添加大类List
		// -------------begin
		StringBuffer buffer = new StringBuffer();
		List newsList = newsManageService.queryObjectList("from NewsItemBig");
		if ((newsList != null) && (newsList.size() != 0)) {
			buffer.append("<option value=''>— 请选择 —</option>");
			for (int i = 0; i < newsList.size(); i++) {
				NewsItemBig bigItems = (NewsItemBig) newsList.get(i);
				buffer.append("<optgroup label=" + bigItems.getClassName() + "></optgroup>");
				if (bigItems.getNewsItemSmalls().size() != 0) {
					Iterator<NewsItemSmall> newsItemSmallIt = bigItems.getNewsItemSmalls().iterator();
					while (newsItemSmallIt.hasNext()) {
						NewsItemSmall nis = newsItemSmallIt.next();
						buffer.append("<option value=" + nis.getTypeId() + ">--" + nis.getTypeName() + "</option>");
					}
				}
			}
		}
		request.setAttribute("buffer", buffer);
		// -------------end
		request.setAttribute("parentModuleFlag", parentModuleFlag);
		return mapping.findForward("amendNewsContentManage");
	}

	/**
	 * 更改是否置顶
	 * 
	 * @return
	 * @Description:
	 */
	public ActionForward updateIsEnable(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String status = request.getParameter("displayStatus");
		long newsId = Long.valueOf(request.getParameter("newsId"));
		NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(), newsId);
		try {
			ncm.setIfTopRow("1".equals(status) ? "0" : "1");
			newsManageService.saveOrUpdate(ncm);
			response.getWriter().write("1".equals(status) ? "0" : "1");
		} catch (Exception ex) {
			try {
				response.getWriter().write("-1");
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	
	
	
	

	/**
	 * add new item big
	 */
	@SuppressWarnings("static-access")
	public ActionForward amendNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		String parentModuleFlag = request.getParameter("parentModuleFlag");
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		//上传内容
		SmartUpload mySmartUpload = new SmartUpload();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this.servlet, request, response, null,
				true, 8192, true);
		mySmartUpload.setCharset("utf-8"); // 编码方式应该与表单所在的页面的编码相同，否则可能出现乱码
		try {
			mySmartUpload.initialize(pageContext);
			mySmartUpload.upload();
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		String classId = mySmartUpload.getRequest().getParameter("classId");
		String typeId = mySmartUpload.getRequest().getParameter("typeId");
		NewsItemBig itemBig = newsManageService.getNewsItemBigByClassId(classId);
		NewsItemSmall itemSmall = null;
		if(typeId !=null && !"".equals(typeId)){
			itemSmall = newsManageService.getNewsItemSmallByTypeId(Integer.parseInt(typeId));
		}
		
		String yxdm = itemBig.getYxdm();
		long newsId = Long.valueOf(mySmartUpload.getRequest().getParameter("newsId"));
		

		NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(), newsId);
		String announceType = ncm.getAnnounceType();
		String newsTitle = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsTitle" + announceType));
		String newsContent = mySmartUpload.getRequest().getParameter("editor_k");
		String newsKeyword = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsKeyword"));
		String newsWriter = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsWriter"));
		String newsDate = mySmartUpload.getRequest().getParameter("newsDate");
		String newsSource = HtmlAndText.TextToHtml(mySmartUpload.getRequest().getParameter("newsSource"));
		String announcePerson = basicPerson.getPersonId().toString();
		String newsTemplate = mySmartUpload.getRequest().getParameter("newsTemplate");
		String departmentId = mySmartUpload.getRequest().getParameter("publishDept");
		String ifImgNews = mySmartUpload.getRequest().getParameter("ifImgNews" + announceType);
		String imgNewsAddress = mySmartUpload.getRequest().getParameter("imgNewsAddress" + announceType);
		String imgNewsTitle = mySmartUpload.getRequest().getParameter("imgNewsTitle" + announceType);
		String ifTitleImg = mySmartUpload.getRequest().getParameter("ifTitleImg" + announceType);
		String ifTopRow = mySmartUpload.getRequest().getParameter("ifTopRow" + announceType);
		String ifRecommend = mySmartUpload.getRequest().getParameter("ifRecommend" + announceType);
		String httpUrl = mySmartUpload.getRequest().getParameter("httpUrl");
		String annexAddress = mySmartUpload.getRequest().getParameter("annexAddress");
		String ifVodNews = mySmartUpload.getRequest().getParameter("ifVodNews");
		String displayOrderId = mySmartUpload.getRequest().getParameter("displayOrderId");
		String checkPerson = mySmartUpload.getRequest().getParameter("checkPerson");
		String checkDate = mySmartUpload.getRequest().getParameter("checkDate");
		//		String visitCount = mySmartUpload.getRequest().getParameter("visitCount");
		String checkFlag = mySmartUpload.getRequest().getParameter("checkFlag");
		Set<NewsItemSmall> ncmSet = new HashSet<NewsItemSmall>();
		String selStr = request.getParameter("selStr"); // 获得多选下拉框的值
		if ((selStr != null) && !StringUtils.isBlank(selStr)) {
			String[] newItemsId = selStr.split(",");
			for (int i = 0; i < newItemsId.length; i++) {
				NewsItemSmall ncmTmp = new NewsItemSmall();
				ncmTmp = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), Integer
						.valueOf(newItemsId[i]));
				ncmSet.add(ncmTmp);
			}
		}
		ncm.setNewsItemSmalls(ncmSet);
		ncm.setAnnounceType(announceType);
		ncm.setNewsTitle(newsTitle);
		ncm.setNewsContent(HtmlAndText.HtmlToText(newsContent));
		ncm.setNewsKeyword(newsKeyword);
		ncm.setNewsWriter(newsWriter);
		ncm.setNewsSource(newsSource);
		ncm.setAnnouncePerson(announcePerson);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ncm.setNewsDate(format.parse(newsDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		ncm.setNewsTemplate(newsTemplate);
		ncm.setIfImgNews(ifImgNews);
		if(imgNewsAddress!=null&&!"".equals(imgNewsAddress)){
			if(imgNewsAddress.lastIndexOf("/")>0){
				ncm.setImgNewsAddress(imgNewsAddress);
			}else{
				ncm.setImgNewsAddress(GlobalConst.UPLOAD_PATH+imgNewsAddress);
			}
		}
		ncm.setImgNewsTitle(imgNewsTitle);
		ncm.setIfTitleImg(ifTitleImg);
		if ("".equals(ifTopRow) || (ifTopRow == null)) {
			ifTopRow = "0";
		}
		ncm.setIfTopRow(ifTopRow);
		ncm.setIfRecommend(ifRecommend);
		ncm.setHttpUrl(httpUrl);
		if(annexAddress!=null&&!"".equals(annexAddress)){
			ncm.setAnnexAddress(GlobalConst.UPLOAD_PATH+annexAddress);
		}
		ncm.setIfVodNews(ifVodNews);
		ncm.setDisplayOrderId(StringUtil.getNullInt(displayOrderId));
		ncm.setCheckPerson(checkPerson);
		ncm.setCheckDate(mydate.getUtilDate(checkDate));
		String tmp = HtmlFile.getHtmlFileNameForDatabase(ncm,GlobalConst.SchoolCode);
		if (!"".equals(tmp)|| (tmp != null)){
			ncm.setNewsFilesPath(tmp.substring(0,tmp.lastIndexOf("/")+1));
		}
		String visitCount = ncm.getVisitCount().toString();
		if ("".equals(visitCount) || (visitCount == null)) {
			visitCount = "0";
		}
		//		ncm.setVisitCount(StringUtil.getNullInt(visitCount));
		ncm.setVisitCount(StringUtil.getNullInt(ncm.getVisitCount()));
		ncm.setCheckFlag(checkFlag);
		BasicDepartment department = (BasicDepartment) newsManageService.queryObjectById(new BasicDepartment(), Long
				.parseLong(departmentId));
		ncm.setDepartment(department);
		if ("0".equals(announceType)) {
			String htmlFileName = StringUtil.getNullString(ncm.getHtmlFileName());
			if (htmlFileName.equalsIgnoreCase("") || (htmlFileName.indexOf(".") < 0)) {
				htmlFileName = HtmlFile.getHtmlFileNameForDatabase(ncm, yxdm);
				ncm.setHtmlFileName(htmlFileName);
			}
		}

		Monitor monitor = new Monitor();
		monitor.setOperator(basicPerson.getPersonName());
		monitor.setClassName(itemBig.getClassName());
		monitor.setTypeName(itemSmall.getTypeName());
		monitor.setOperateTime(new Date());
		monitor.setStatus("update");
		monitor.setLoginfo("<font color=\"#ff0000\">"+basicPerson.getPersonName()+"</font>  "+"修改："+ ""+newsTitle+"");
		
		request.setAttribute("classId", classId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("newsId", newsId);
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		request.setAttribute("optMark", optMark);

		String forward = "amendNewsContentManage";
		try {
			String str = "success";
			if ("success".equalsIgnoreCase(str)) {
				newsManageService.saveOrUpdate(ncm);
				newsManageService.saveMonitor(monitor);
				if ("0".equals(announceType)) {
					try {
						createHtml(request, yxdm, classId,ncm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				request.setAttribute("showMsg", SysObj.createEditMassageBox(ncm.getNewsTitle()));
				request.setAttribute("parentModuleFlag", parentModuleFlag);
				IndexToHtml.createIndexHtml(request);
				return findAmendNewsContentManage(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createEditMassageBox(ncm.getNewsTitle()));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete news ad type
	 */
	@SuppressWarnings("unchecked")
	public ActionForward deleteNewsContentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
		.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_manage", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String classId = request.getParameter("classId");
		int typeId = StringUtil.getIntValueForServletRequest(request, "typeId");
		NewsItemBig itemBig = newsManageService.getNewsItemBigByClassId(classId);
		NewsItemSmall itemSmall = new NewsItemSmall();
		String yxdm = itemBig.getYxdm();
		String[] str = request.getParameterValues("check");
		String msg = "";
		
		//获取上传文件路径
		String baseDir = request.getSession().getServletContext().getRealPath("");
		String realFilePath = baseDir +"/";
		
		String newsImgAddress="";
		int delRecords = 0;
		
		if ((String.valueOf(typeId) == null) || (typeId == 0)) {
			List list = newsManageService.queryNewsItemSmallByClassId(classId, GlobalConst.IS_DISPLAY);
			if (list.size() > 0) {
				itemSmall = (NewsItemSmall) list.get(0);
			}
		}
		else {
			itemSmall = newsManageService.getNewsItemSmallByTypeId(typeId);
		}
		
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(),
					StringUtil.getNullLong(ids));
			if (ncm == null) {
				break;
			}
			if (ncm.getNewsContentReviews().size() <= 0) {
//				if ((ncm.getNewsFilesPath() != null)&&!"".equals(ncm.getNewsFilesPath())) {
////					String path =HtmlFile.getRealPath(ncm.getNewsFilesPath());
////					boolean isdeleteSuccess = fileOperate.RD(path);// RD方法，用于删除制定物理路径的文件夹
//					String newsPath = realFilePath+ncm.getNewsFilesPath();
//					boolean isdeleteSuccess = HtmlFile.RD((newsPath));
//					
//					if (!isdeleteSuccess) {
//						msg = "删除目录出错";
//					}
//				}
				
				newsImgAddress = ncm.getImgNewsAddress();
				 
				if((newsImgAddress!=null) && (newsImgAddress.trim().length()!=0)){
//					String filePath = getServlet().getServletContext().getRealPath(newsImgAddress);
					String filePath = realFilePath + newsImgAddress;
					File file = new File(filePath);
					
					if(file.isFile()){
						FileUtils.deleteQuietly(file);
					}
				}
				if("2".equals(ncm.getAnnounceType())){
					String attachmentPath = realFilePath+ncm.getHttpUrl();
//					String filePath = getServlet().getServletContext().getRealPath(attachmentPath);
					File file = new File(attachmentPath);
					
					if(file.isFile()){
						FileUtils.deleteQuietly(file);
					}
					
				}
				Monitor monitor = new Monitor();
				monitor.setOperator(basicPerson.getPersonName());
				monitor.setClassName(itemBig.getClassName());
				monitor.setTypeName(itemSmall.getTypeName());
				monitor.setOperateTime(new Date());
				monitor.setStatus("delete");
				monitor.setLoginfo("<font color=\"#ff0000\">"+basicPerson.getPersonName()+"</font>  "+"删除："+ncm.getNewsTitle());
				
				newsManageService.deleteObject(ncm);
				newsManageService.saveMonitor(monitor);
				if ("0".equals(ncm.getAnnounceType())) {
					String realPath = servlet.getServletContext().getRealPath("");
					String fileName = realPath + "/" + ncm.getHtmlFileName();
					try {
						HtmlFile.deleteHtmlFile(fileName);
						
						if(ncm.getNewsFilesPath()!=null && !"".equals(ncm.getNewsFilesPath())){
							HtmlFile.RD(realPath+"/"+ncm.getNewsFilesPath());
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				delRecords++;
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + ncm.getNewsTitle();
				} else {
					msg = msg + ";" + ncm.getNewsTitle();
				}
			}
		}
		IndexToHtml.createIndexHtml(request);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecords, msg));
		request.setAttribute("parentModuleFlag", parentModuleFlag);
		return findNewsContentManage(mapping, form, request, response);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "first_news_item_big", "select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");

		String sql = "from NewsItemBig c where 1=1  ";
		String sqlCount = "select count(*) from NewsItemBig c where 1=1 ";

		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		if ((yxdm != null) && !yxdm.trim().equalsIgnoreCase("0")) {
			sql = sql + " and c.yxdm='" + yxdm + "' ";
			sqlCount = sqlCount + " and c.yxdm='" + yxdm + "'";
		}
		sql = sql + " order by ifDisplay desc, c.orderId";

		String path = request.getContextPath() + "/view/newsmanage.do?method=findFirstPage";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		request.setAttribute("allYxdmSelect", newsOrgService.getSchCollegeDeptSelectBySchoolCode(xxdm, yxdm,
				basicPerson));
		request.setAttribute("pageList", pageList);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("msg", msg);

		return mapping.findForward("defindFirstPage");
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAddFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String option = "";
		if ((classId == null) || classId.trim().equalsIgnoreCase("")) {
			option = "add";
		} else {
			option = "update";
		}
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "first_news_item_big", option);
		if (!right) {
			return mapping.findForward("noright");
		}
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		request.setAttribute("xxdm", xxdm);
		String schoolName = GlobalConst.getSchoolName(yxdm);
		if ((classId == null) || classId.trim().equalsIgnoreCase("")) {
			if (schoolName == null) {
				SchCollegeDepartment scd = (SchCollegeDepartment) newsManageService.queryObjectById(
						new SchCollegeDepartment(), yxdm);
				schoolName = scd.getYxmc();
			}
			HashMap paraMap = new HashMap();
			paraMap.put("tableType", "NewsItemBig");
			paraMap.put("yxdm", yxdm);
			paraMap.put("ifIndex", GlobalConst.IS_INDEX);
			request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));
			request.setAttribute("yxdm", yxdm);
			request.setAttribute("schoolName", schoolName);
			request.setAttribute("msg", msg);
			request.setAttribute("classId", classId);
			return mapping.findForward("findAddFirstPage");
		} else {
			NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
			request.setAttribute("newsItemBig", nib);
			String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
			// schoolName = GlobalConst.getSchoolName(nib.getYxdm());
			// if (schoolName == null) {
			// SchCollegeDepartment scd = (SchCollegeDepartment)
			// newsManageService
			// .queryObjectById(new SchCollegeDepartment(), nib
			// .getYxdm());
			// schoolName = scd.getYxmc();
			// }
			request.setAttribute("yxdm", nib.getYxdm());
			request.setAttribute("schoolName", schoolName);
			request.setAttribute("msg", msg);
			request.setAttribute("classId", classId);
			request.setAttribute("optMark", optMark);
			return mapping.findForward("findAmendFirstPage");
		}
	}

	/**
	 * add fisrt page
	 */
	public ActionForward addFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "first_news_item_big", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		int orderId = StringUtil.getIntValueForServletRequest(request, "orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		String ifHaveAd = request.getParameter("ifHaveAd");
		String yxdm = request.getParameter("yxdm");
		int ifIndex = Integer.parseInt(request.getParameter("ifIndex"));
		NewsItemBig nib = new NewsItemBig();
		nib.setClassId(yxdm + "" + classId);
		nib.setClassName(className);
		nib.setIfDisplay(ifDisplay);
		nib.setIfHaveAd(ifHaveAd);
		nib.setOrderId(orderId);
		nib.setYxdm(yxdm);
		nib.setIfIndex(ifIndex);
		// String schoolName = GlobalConst.getSchoolName(nib.getYxdm());
		// if (schoolName == null) {
		// SchCollegeDepartment scd = (SchCollegeDepartment) newsManageService
		// .queryObjectById(new SchCollegeDepartment(), yxdm);
		// schoolName = scd.getYxmc();
		// }
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("xxdm", xxdm);
		// request.setAttribute("schoolName", schoolName);
		String forward = "findAddFirstPage";
		try {
			String str = newsManageService.addNewsItemBig(nib);
			if ("success".equalsIgnoreCase(str)) {
				request.setAttribute("classId", "");
				request.setAttribute("className", "");
				request.setAttribute("showMsg", SysObj.createAddMassageBox(nib.getClassName()));
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("className", className);
				request.setAttribute("classId", classId);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(classId));
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * amend fisrt page
	 */
	public ActionForward amendFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "first_news_item_big", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String classId = request.getParameter("classId");
		String className = request.getParameter("className");
		int orderId = StringUtil.getIntValueForServletRequest(request, "orderId");
		int ifIndex = Integer.parseInt(request.getParameter("ifIndex"));
		String ifDisplay = request.getParameter("ifDisplay");
		String ifHaveAd = request.getParameter("ifHaveAd");
		String yxdm = request.getParameter("yxdm");
		String optMark = request.getParameter("optMark");
		NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		nib.setClassName(className);
		nib.setIfDisplay(ifDisplay);
		nib.setIfHaveAd(ifHaveAd);
		nib.setOrderId(orderId);
		nib.setIfIndex(ifIndex);
		String schoolName = GlobalConst.getSchoolName(yxdm);
		if (schoolName == null) {
			SchCollegeDepartment scd = (SchCollegeDepartment) newsManageService.queryObjectById(
					new SchCollegeDepartment(), yxdm);
			schoolName = scd.getYxmc();
		}
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("schoolName", schoolName);
		request.setAttribute("optMark", optMark);
		String forward = "findAmendFirstPage";
		try {
			String str = newsManageService.updateBigAndModuel(nib);
			if ("success".equalsIgnoreCase(str)) {
				request.setAttribute("showMsg", SysObj.createEditMassageBox(classId));
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
				return findFirstPage(mapping, form, request, response);
			} else {
				forward = "error";
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete fisrt page
	 */
	public ActionForward deleteFristPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight
				.moduleOperationRight(basicPerson.getPersonAccount(), "first_news_item_big", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecord = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsItemBig nib = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), ids);
			if (nib == null) {
				break;
			}
			if (!newsManageService.checkUseedForNewsItemsBig(nib)) {
				newsManageService.deleteBigAndModule(nib);
				delRecord++;
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + nib.getClassName();
				} else {
					msg = msg + ";" + nib.getClassName();
				}
			}

		}
		IndexToHtml.createIndexHtml(request);
		String xxdm = StringUtil.getStringValueForServletRequest(request, "xxdm");
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecord, msg));
		return findFirstPage(mapping, form, request, response);
	}

	/**
	 * 
	 * 
	 */
	public ActionForward findSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		// boolean right = checkRight.moduleOperationRight(basicPerson
		// .getPersonAccount(), "first_news_item_small", "select");
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		// String msg = StringUtil.getStringValueForServletRequest(request,
		// "msg");
		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		String sql = "from NewsItemSmall c where 1=1";
		String sqlCount = "select count(*) from NewsItemSmall c where 1=1";
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.yxdm='" + yxdm + "' ";
			sqlCount += " and c.newsItemBig.yxdm='" + yxdm + "'";
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and c.newsItemBig.classId='" + classId + "'";
		}
		sql = sql + " order by ifDisplay desc, orderId";
		request.setAttribute("allYxdmSelect", newsOrgService.getSchCollegeDeptSelectBySchoolCode(xxdm, yxdm,
				basicPerson));
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm, null, 3,
					classId));
		}
		String path = request.getContextPath() + "/view/newsmanage.do?method=findSmallFirstPage";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("pageList", pageList);
		return mapping.findForward("defindSmallFirstPage");
	}

	/**
	 * 
	 * 
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public ActionForward findAddSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = StringUtil.getStringValueForServletRequest(request, "classId");

		NewsItemBig newsItemBig = (NewsItemBig) request.getAttribute("newsItemBig");
		if (newsItemBig == null) {
			newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		}
		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("newsItemBig", newsItemBig);
		request.setAttribute("dateFormat", newsManageService.getDateFormatString(""));
		request.setAttribute("moreDateFormat", newsManageService.getDateFormatString(""));
		HashMap paraMap = new HashMap();
		paraMap.put("tableType", "NewsItemSmall");
		paraMap.put("classId", classId);
		request.setAttribute("orderId", newsManageService.getMaxOrderIdByTableName(paraMap));
		return mapping.findForward("findAddSmallFirstPage");
	}

	/**
	 * add new item small
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String str = "success";
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "add");
		if (!right) {
			return mapping.findForward("noright");
		}
		// set NewsItemSmall object data
		NewsItemSmall nis = new NewsItemSmall();
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String checkFlag = StringUtil.getStringValueForServletRequest(request, "checkFlag");
		if (checkFlag.equalsIgnoreCase("")) {
			checkFlag = NEWS_TEMPLATE_ONE;
		}
		String typeName = request.getParameter("typeName");
		String orderId = request.getParameter("orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		NewsItemBig newsItemBig = (NewsItemBig) newsManageService.queryObjectById(new NewsItemBig(), classId);
		nis.setTypeName(typeName);
		nis.setIfDisplay(ifDisplay);
		nis.setOrderId(new Integer(StringUtil.getNullInt(orderId)));
		nis.setNewsItemBig(newsItemBig);
		nis.setCheckFlag(checkFlag);
		String className = request.getParameter("className");
		NewsItemConfig nic = new NewsItemConfig();
		String displayRowCount = request.getParameter("displayRowCount");
		String titleCharacterCount = request.getParameter("titleCharacterCount");
		String titleImgUrl = request.getParameter("titleImgUrl");
		String dateFormat = request.getParameter("dateFormat");
		nic.setDisplayRowCount(new Integer(StringUtil.getNullInt(displayRowCount)));
		nic.setTitleCharacterCount(new Integer(StringUtil.getNullInt(titleCharacterCount)));
		nic.setTitleImgUrl(titleImgUrl);
		nic.setDateFormat(dateFormat);
		String moreRowCount = request.getParameter("moreRowCount");
		String moreTitleCount = request.getParameter("moreTitleCount");
		String moreTitleImgUrl = request.getParameter("moreTitleImgUrl");
		String moreDateFormat = request.getParameter("moreDateFormat");
		String ifPopWindow = request.getParameter("ifPopWindow");
		String ifPermissionSearch = request.getParameter("ifPermissionSearch");
		nic.setMoreRowCount(new Integer(StringUtil.getNullInt(moreRowCount)));
		nic.setMoreTitleCount(new Integer(StringUtil.getNullInt(moreTitleCount)));
		nic.setMoreTitleImgUrl(moreTitleImgUrl);
		nic.setMoreDateFormat(moreDateFormat);
		nic.setIfPopWindow(ifPopWindow);
		nic.setIfPermissionSearch(ifPermissionSearch);
		nic.setNewsItemSmall(nis);
		Set newsItemConfigs = new HashSet(0);
		newsItemConfigs.add(nic);
		nis.setNewsItemConfigs(newsItemConfigs);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("msg", msg);
		String forward = "findAddSmallFirstPage";
		try {
			if (newsManageService.isExistsNewsItemSmall(nis.getNewsItemBig().getClassId(), nis.getTypeName())) {
				str = "isExists";
			}
			if ("success".equalsIgnoreCase(str)) {
				str = newsManageService.addSmallItem(className, nis, classId, true, yxdm);
				nic.setNewsItemSmall(nis);
				newsManageService.saveObject(nic);
				IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
				request.setAttribute("newsItemBig", newsItemBig);
				request.setAttribute("showMsg", SysObj.createAddMassageBox(nis.getTypeName()));
				return findAddSmallFirstPage(mapping, form, request, response);
			} else if ("isExists".equalsIgnoreCase(str)) {
				request.setAttribute("classId", classId);
				request.setAttribute("newsItemBig", newsItemBig);
				request.setAttribute("showMsg", SysObj.createExistsMassageBox(nis.getTypeName()));
			} else {
				forward = "error";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward(forward);
	}

	/**
	 * search the data of small first for mian page
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findAmendSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		String msg = StringUtil.getStringValueForServletRequest(request, "msg");
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		String typeId = request.getParameter("typeId");
		NewsItemSmall newsItemSmall = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(),
				new Integer(typeId));
		Set newsItemConfigs = newsItemSmall.getNewsItemConfigs();
		NewsItemConfig newsItemConfig = new NewsItemConfig();
		if (newsItemConfigs.size() > 0) {
			newsItemConfig = (NewsItemConfig) (newsItemConfigs.toArray()[0]);
			request.setAttribute("newsItemConfig", newsItemConfig);
		}
		// NewsItemConfig
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("msg", msg);
		request.setAttribute("typeId", typeId);
		request.setAttribute("optMark", optMark);
		request.setAttribute("classId", newsItemSmall.getNewsItemBig().getClassId());
		request.setAttribute("newsItemBig", newsItemSmall.getNewsItemBig());
		request.setAttribute("newsItemSmall", newsItemSmall);
		request.setAttribute("dateFormat", newsManageService.getDateFormatString(newsItemConfig.getDateFormat()));
		request.setAttribute("moreDateFormat", newsManageService
				.getDateFormatString(newsItemConfig.getMoreDateFormat()));
		return mapping.findForward("findAmendSmallFirstPage");
	}

	/**
	 * add new item big
	 */
	@SuppressWarnings("unchecked")
	public ActionForward amendSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String typeId = request.getParameter("typeId");
		NewsItemSmall nis = (NewsItemSmall) newsManageService.queryObjectById(new NewsItemSmall(), new Integer(typeId));
		Set newsItemConfigs = nis.getNewsItemConfigs();
		NewsItemConfig nic = new NewsItemConfig();// (NewsItemConfig)newsManageService.queryObjectById(new
		if (newsItemConfigs.size() > 0) {
			nic = (NewsItemConfig) (newsItemConfigs.toArray()[0]);
		}
		String optMark = StringUtil.getStringValueForServletRequest(request, "optMark");
		String yxdm = request.getParameter("yxdm");
		String classId = request.getParameter("classId");
		String typeName = request.getParameter("typeName");
		String orderId = request.getParameter("orderId");
		String ifDisplay = request.getParameter("ifDisplay");
		String checkFlag = StringUtil.getStringValueForServletRequest(request, "checkFlag");
		if (checkFlag.equalsIgnoreCase("")) {
			checkFlag = NEWS_TEMPLATE_ONE;
		}
		nis.setTypeName(typeName);
		nis.setIfDisplay(ifDisplay);
		nis.setOrderId(new Integer(StringUtil.getNullInt(orderId)));
		nis.setCheckFlag(checkFlag);
		String displayRowCount = request.getParameter("displayRowCount");
		String titleCharacterCount = request.getParameter("titleCharacterCount");
		String titleImgUrl = request.getParameter("titleImgUrl");
		String dateFormat = request.getParameter("dateFormat");
		nic.setDisplayRowCount(new Integer(StringUtil.getNullInt(displayRowCount)));
		nic.setTitleCharacterCount(new Integer(StringUtil.getNullInt(titleCharacterCount)));
		nic.setTitleImgUrl(titleImgUrl);
		nic.setDateFormat(dateFormat);
		String moreRowCount = request.getParameter("moreRowCount");
		String moreTitleCount = request.getParameter("moreTitleCount");
		String moreTitleImgUrl = request.getParameter("moreTitleImgUrl");
		String moreDateFormat = request.getParameter("moreDateFormat");
		String ifPopWindow = request.getParameter("ifPopWindow");
		String ifPermissionSearch = request.getParameter("ifPermissionSearch");
		nic.setMoreRowCount(new Integer(StringUtil.getNullInt(moreRowCount)));
		nic.setMoreTitleCount(new Integer(StringUtil.getNullInt(moreTitleCount)));
		nic.setMoreTitleImgUrl(moreTitleImgUrl);
		nic.setMoreDateFormat(moreDateFormat);
		nic.setIfPopWindow(ifPopWindow);
		nic.setIfPermissionSearch(ifPermissionSearch);
		nic.setNewsItemSmall(nis);
		request.setAttribute("classId", classId);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("optMark", optMark);
		String forward = "findSmallFirstPage";
		try {
			newsManageService.updateSmallAndModuel(nis);
			newsManageService.saveObject(nic);
			IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
			request.setAttribute("showMsg", SysObj.createEditMassageBox(nis.getTypeName()));
			setCommonAttribute(mapping, form, request, response);
			return findSmallFirstPage(mapping, form, request, response);
		} catch (RuntimeException e) {
			e.printStackTrace();
			forward = "error";
		}
		return mapping.findForward(forward);
	}

	/**
	 * delete new item big
	 */
	@SuppressWarnings("unchecked")
	public ActionForward deleteSmallFirstPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		String msg = "";
		int delrecords = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			Map map = new HashMap();
			NewsItemSmall nis = (NewsItemSmall) newsManageService
					.queryObjectById(new NewsItemSmall(), new Integer(ids));
			map.put("moduleName", request.getParameter("className"));
			// String maxModuelId = newsManageService.querySysModule(map);
			if (nis == null) {
				break;
			}
			if (!newsManageService.checkUseedForNewsItemSmall(nis)) {
				delrecords++;
				Object[] obj = nis.getNewsItemConfigs().toArray();
				if (obj.length > 0) {
					for (int j = 0; j < obj.length; j++) {
						newsManageService.deleteObject(obj[j]);
					}
				}
				newsManageService.deleteSmallAndModule(nis);
			} else {
				if ("".equalsIgnoreCase(msg)) {
					msg = msg + nis.getTypeName();
				} else {
					msg = msg + ";" + nis.getTypeName();
				}
			}
		}
		IndexToHtml.createIndexHtml(request);
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delrecords, msg));
		return findSmallFirstPage(mapping, form, request, response);
	}

	/**
	 * search the data of small first for check setting
	 * 
	 */
	public ActionForward findNewsItemSmallCheckFlag(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small_check",
				"select");
		if (!right) {
			return mapping.findForward("noright");
		}
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");

		if ((yxdm == null) && (classId == null)) {
			yxdm = xxdm;
		}
		if (yxdm.trim().equalsIgnoreCase("") && classId.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}

		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			if (!(classId.substring(0, yxdm.length())).equals(yxdm)) {
				classId = "0";
			}
		}

		String sql = "from NewsItemSmall c where 1=1 and  c.ifDisplay='1'";
		String sqlCount = "select count(*) from NewsItemSmall c where 1=1 and c.ifDisplay='1'";
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.yxdm='" + yxdm + "' ";
			sqlCount += " and c.newsItemBig.yxdm='" + yxdm + "'";
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0") && !classId.equalsIgnoreCase("")) {
			sql += " and c.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and c.newsItemBig.classId='" + classId + "'";
		}
		sql = sql + " order by c.orderId";
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchoolDepart(yxdm, basicPerson));
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm,
					GlobalConst.IS_DISPLAY, GlobalConst.IS_ALL_INDEX, classId));
		}
		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsItemSmallCheckFlag";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");
		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		setCommonAttribute(mapping, form, request, response);
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		request.setAttribute("pageList", pageList);
		return mapping.findForward("newsItemSmallCheckFlag");
	}

	/**
	 * update news item small check flag
	 */
	public ActionForward updateNewsItemSmallCheckFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_item_small_check",
				"update");
		if (!right) {
			return mapping.findForward("noright");
		}
		String classId = StringUtil.getStringValueForServletRequest(request, "classId");
		String yxdm = StringUtil.getStringValueForServletRequest(request, "yxdm");
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("classId", classId);
		String[] str = request.getParameterValues("check");
		String ids = "";
		String checkFlag = NEWS_TEMPLATE_ONE;
		for (int i = 0; i < str.length; i++) {
			ids = str[i];
			NewsItemSmall nis = (NewsItemSmall) newsManageService
					.queryObjectById(new NewsItemSmall(), new Integer(ids));
			checkFlag = StringUtil.getStringValueForServletRequest(request, ids);
			nis.setCheckFlag(checkFlag);
			newsManageService.saveOrUpdate(nis);
		}
		request.setAttribute("showMsg", SysObj.createMassageBox("审核设置成功！"));
		IndexToHtml.createIndexHtmlByYxdm(request, yxdm);
		return findNewsItemSmallCheckFlag(mapping, form, request, response);
	}

	/**
	 * search news content info for check setting
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward findNewsContentCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_check", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");
		String typeId = request.getParameter("typeId");
		String checkId = "";
		NewsItemBig itembig = newsManageService.getNewsItemBigByClassId(classId);
		if ((typeId != null) && !typeId.equalsIgnoreCase("") && !typeId.equalsIgnoreCase("0")) {
			NewsItemSmall itemsmall = newsManageService.getNewsItemSmallByTypeId(StringUtil.getNullInt(typeId));
			checkId = itemsmall.getNewsItemBig().getClassId();
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			if (!checkId.equalsIgnoreCase("") && !classId.equalsIgnoreCase(checkId)) {
				typeId = "0";
			}
		}
		if ((yxdm == null) && (classId == null)) {
			yxdm = xxdm;
		}
		if (yxdm.trim().equalsIgnoreCase("") && classId.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		if ((yxdm == null) || yxdm.trim().equals("")) {
			yxdm = itembig.getYxdm();
		}
		String sql = "select c from NewsContentManage c join c.newsItemSmalls s where 1=1 and (c.checkFlag='0' or c.checkFlag=null)";
		String sqlCount = "select count(c.newsId) from NewsContentManage c join c.newsItemSmalls s where 1=1 and (c.checkFlag='0' or c.checkFlag=null)";

		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			sql += " and s.newsItemBig.classId='" + classId + "' ";
			sqlCount += " and s.newsItemBig.classId='" + classId + "'";
			if ((typeId != null) && !typeId.equalsIgnoreCase("0") && !typeId.equalsIgnoreCase("")) {
				sql += " and s.typeId=" + typeId;
				sqlCount += " and s.typeId=" + typeId;
			}
		}
		// System.out.println(sql);
		request.setAttribute("allYxdmSelect", newsOrgService.getAllSchoolDepart(yxdm, basicPerson));
		if ((yxdm != null) && !yxdm.equalsIgnoreCase("0")) {
			request.setAttribute("allClassIdSelect", newsManageService.getNewsItemBigSelectByYxdm(yxdm,
					GlobalConst.IS_DISPLAY, GlobalConst.IS_ALL_INDEX, classId));
		}
		if ((classId != null) && !classId.equalsIgnoreCase("0")) {
			request.setAttribute("allTypeSelect", newsManageService.getNewsItemSmallSelectByClassId(classId,
					GlobalConst.IS_DISPLAY, typeId));
		}

		String path = request.getContextPath() + "/view/newsmanage.do?method=findNewsContentCheck";
		String pagesize = request.getParameter("pagesize");
		String currentPage = request.getParameter("currentPage");

		PageList pageList = PageList.page(sqlCount, sql, currentPage, pagesize, path, newsManageService,
				"newsmanageForm");
		// List<NewsContentManage> tempList = new
		// ArrayList<NewsContentManage>();
		// List<NewsContentManage> list = pageList.getList();
		// for (int i = 0; i < list.size(); i++) {
		// if (!tempList.contains(list.get(i))) {
		// tempList.add(list.get(i));
		// }
		//
		// }
		// pageList.setList(tempList);
		// pageList.setPageSize(tempList.size());
		// pageList.setRecordCounts(tempList.size());
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("typeId", typeId);
		request.setAttribute("classId", classId);
		request.setAttribute("pageList", pageList);
		request.setAttribute("msg", request.getAttribute("msg"));
		return mapping.findForward("newsContentCheck");
	}

	/**
	 * 
	 * update news content check setting
	 */
	public ActionForward upateNewsContentCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "news_content_check", "add");
		if (!right) {
			return mapping.findForward("noright");
		}

		String[] str = request.getParameterValues("check");
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(),
					StringUtil.getNullLong(ids));
			ncm.setCheckFlag(NEWS_TEMPLATE_ONE);
			newsManageService.saveOrUpdate(ncm);
		}
		String classId = request.getParameter("classId");
		String yxdm = request.getParameter("yxdm");
		String typeId = request.getParameter("typeId");
		if ((yxdm == null) || yxdm.trim().equalsIgnoreCase("")) {
			yxdm = xxdm;
		}
		request.setAttribute("yxdm", yxdm);
		request.setAttribute("typeId", typeId);
		request.setAttribute("classId", classId);
		request.setAttribute("showMsg", SysObj.createMassageBox("新闻审核设置成功！"));
		return findNewsContentCheck(mapping, form, request, response);
	}

	/**
	 * 
	 * 生成单条新闻的静态页面
	 */
	public ActionForward generateItemToHtmlFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] str = request.getParameterValues("check");
		String msg = "";
		String classId = request.getParameter("classId");
		NewsItemBig itemBig = newsManageService.getNewsItemBigByClassId(classId);
		String yxdm = itemBig.getYxdm();
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			NewsContentManage newsContent = (NewsContentManage) newsManageService.queryObjectById(
					new NewsContentManage(), StringUtil.getNullLong(ids));
			if (newsContent == null) {
				break;
			}
			if ("0".equalsIgnoreCase(newsContent.getAnnounceType())) {
				createHtml(request, yxdm,classId, newsContent);
				newsManageService.saveOrUpdate(newsContent);

			}
		}
		if (yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			IndexToHtml.createIndexHtml(request);
		}
		request.setAttribute("showMsg", SysObj.createGenerateMessageBox(str.length, msg));
		return findNewsContentManage(mapping, form, request, response);
	}

	/**
	 * 生成所有新闻的静态页面
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward allItemToHtmlFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List list = new ArrayList();
		String msg = "";
		String classId ="";
		String yxdm = request.getParameter("yxdm");
		list = (List) newsManageService.queryNewsContentManageByYxdm(yxdm);
		for (int i = 0; i < list.size(); i++) {
			NewsContentManage newsContent = (NewsContentManage) list.get(i);
			if (newsContent == null) {
				break;
			}
			try {
				Object[] newsItemSmalls = newsContent.getNewsItemSmalls().toArray();
				if(newsItemSmalls.length>0){
					NewsItemSmall nis = (NewsItemSmall) newsItemSmalls[0];
					classId = nis.getNewsItemBig().getClassId();
				}
				createHtml(request, yxdm,classId,newsContent);
				newsManageService.saveOrUpdate(newsContent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			IndexToHtml.createIndexHtml(request);
		}
		request.setAttribute("showMsg", SysObj.createGenerateMessageBox(list.size(), msg));
		return findNewsContentManage(mapping, form, request, response);
	}

	/**
	 * 
	 * 生成新闻静态页面
	 */
	public void createHtml(HttpServletRequest request, String yxdm,String classId,NewsContentManage newsContent) throws Exception {
		String basePath = getBasePath(request);
		// 主页
		if (yxdm.equalsIgnoreCase(GlobalConst.SchoolCode)) {
			String outputPath = servlet.getServletContext().getRealPath("/client/");
			if ((newsContent.getHtmlFileName() == null) 
					|| (newsContent.getHtmlFileName().trim().length() == 0)) {
				String htmlFileName = HtmlFile.getHtmlFileNameForDatabase(newsContent, yxdm);
				newsContent.setHtmlFileName(htmlFileName);
			}
			String templateFilePath = null;
			if (GlobalConst.NEWS_TEMPLATE_ONE.equals(newsContent.getNewsTemplate())) {
				templateFilePath = servlet.getServletContext().getRealPath("/template") + "/news_main_template.html";
			}
			
			Map<String, Object> paraMap = new HashMap<String, Object>();
			
			//顶部菜单
			paraMap.put(GlobalConst.HTML_MENU, basePath + "client/newsClient.do?method=queryMenu");
			//左列菜单
			paraMap.put(GlobalConst.HTML_LEFT, basePath + "client/newsClient.do?method=queryLeft&classId="+classId);
			// 底部
			paraMap.put(GlobalConst.HTML_FOOT_INFO, basePath + "client/newsClient.do?method=queryFootInfo");

			HtmlFileParameter parameter = new HtmlFileParameter();
			parameter.setNewsContent(newsContent);
			parameter.setOutputPath(outputPath);
			parameter.setTemplateFilePath(templateFilePath);
			parameter.setBasePath(basePath);
			parameter.setAction(paraMap);
			HtmlFile.createHtmlFileByTemplate(parameter);
		}
	}

	public ActionForward queryByNewsContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long newsId = StringUtil.getLongValueForServletRequest(request, "newsId");
		NewsContentManage ncm = (NewsContentManage) newsManageService.queryObjectById(new NewsContentManage(), newsId);
		request.setAttribute("newsContents", ncm);
		return new ActionForward("/view/newsmanage/news/newsCheckContent.jsp");
	}

	/**   
	 * @return the workPath   
	 */
	public static String getWorkPath() {
		return workPath;
	}

	/**   
	 * @param workPath the workPath to set   
	 */
	public static void setWorkPath(String workPath) {
		NewsManageAction.workPath = workPath;
	}
}
