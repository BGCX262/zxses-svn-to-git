package com.lcweb.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.MyDate;
import com.lcweb.base.util.PageList;
import com.lcweb.base.util.StringUtil;
import com.lcweb.base.util.SysObj;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.Monitor;
import com.lcweb.commons.CheckRight;
import com.lcweb.service.monitor.MonitorManageService;

/**
 * 
 * @Title: MonitorManageAction.java
 * @Description: 
 * @Author: feng
 * @Time: Jul 19, 2011
 */
public class MonitorManageAction extends DispatchAction {
	private MonitorManageService monitorManageService;
	private static String PAGESIZE = "pagesize";
	private static String CURRENTPAGE = "currentPage";
	private CheckRight checkRight;

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "monitor", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String pageSize = request.getParameter(PAGESIZE);
		String currentPage = request.getParameter(CURRENTPAGE);
		String startDate = MyDate.getNowTime();
		String endDate = MyDate.getNowTime();
		
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		String path = request.getContextPath() + "/view/monitor.do?method=list";

		conditionMap.put("path", path);
		conditionMap.put("form", "monitorManageForm");
		conditionMap.put(PAGESIZE, pageSize);
		conditionMap.put(CURRENTPAGE, currentPage);
		PageList pageList = monitorManageService.find(conditionMap);
		request.setAttribute("pageList", pageList);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return new ActionForward("/view/monitormanage/list.jsp");
		
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "monitor", "delete");
		if (!right) {
			return mapping.findForward("noright");
		}
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String[] str = request.getParameterValues("check");
		String msg = "";
		int delRecords = 0;
		for (int i = 0; i < str.length; i++) {
			String ids = str[i];
			Monitor m = (Monitor) monitorManageService.queryObjectById(new Monitor(),
					StringUtil.getNullLong(ids));
			if (m == null) {
				break;
			}
			if (m!=null) {
				monitorManageService.deleteObject(m);
				delRecords++;
			} 
		}
		request.setAttribute("showMsg", SysObj.createDeleteMassageBox(delRecords, msg));
		request.setAttribute("parentModuleFlag", parentModuleFlag);
		return list(mapping, form, request, response);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward queryByDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(basicPerson.getPersonAccount(), "monitor", "select");
		if (!right) {
			return mapping.findForward("noright");
		}

		String flag = request.getParameter("flag");
		String pageSize = request.getParameter(PAGESIZE);
		String currentPage = request.getParameter(CURRENTPAGE);
		String startDate = MyDate.getNowTime();
		String endDate = MyDate.getNowTime();
		
		if("1".equals(flag)){
			startDate = request.getParameter("startDate");
			endDate = request.getParameter("endDate");
		}
		
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		String path = request.getContextPath() + "/view/monitor.do?method=queryByDate&flag=1";

		conditionMap.put("path", path);
		conditionMap.put("form", "monitorManageForm");
		conditionMap.put(PAGESIZE, pageSize);
		conditionMap.put(CURRENTPAGE, currentPage);
		PageList pageList = monitorManageService.findByDate(conditionMap,startDate,endDate,flag);
		request.setAttribute("pageList", pageList);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return new ActionForward("/view/monitormanage/list.jsp");
		
	}

	/**   
	 * @return the monitorManageService   
	 */
	public MonitorManageService getMonitorManageService() {
		return monitorManageService;
	}

	/**   
	 * @param monitorManageService the monitorManageService to set   
	 */
	public void setMonitorManageService(MonitorManageService monitorManageService) {
		this.monitorManageService = monitorManageService;
	}


	/**   
	 * @return the checkRight   
	 */
	public CheckRight getCheckRight() {
		return checkRight;
	}

	/**   
	 * @param checkRight the checkRight to set   
	 */
	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}


}
