/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.lcweb.struts.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lcweb.base.util.ValidateCode;
import com.lcweb.bean.pojo.BasicPerson;
import com.lcweb.bean.pojo.SysAdmin;
import com.lcweb.bean.pojo.SysModule;
import com.lcweb.commons.CheckRight;
import com.lcweb.commons.CipherUtil;
import com.lcweb.service.login.LoginService;

public class LoginAction extends DispatchAction {
	private CheckRight checkRight;
	private LoginService loginServiceDao;
	private String sysModuleId = "0201000000";
	private String adminModuleId = "0101000000";
	private String adminName = "admin";

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String loginname = request.getParameter("username");
		String password = request.getParameter("password");
		String imagecode = request.getParameter("imagecode");
		String randcode = "";
		
		// MD5加密类
		password = CipherUtil.generatePassword(password);
		boolean flag = loginServiceDao.findByUnamePass(request, loginname, password);
		if (!flag) {
			flag = loginServiceDao.findByUnamePass(request, loginname, password);
		}
		if (flag) {
			try {
				randcode = request.getSession().getAttribute("rand").toString();
				if (!randcode.equals(imagecode)) {
					response.getWriter().write("code");
					return null;
				}
				response.getWriter().write(loginname);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			try {
				response.getWriter().write("loginfail");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	public ActionForward loginFromIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String loginname = request.getParameter("username");
		String password = request.getParameter("password");
		
		// MD5加密类
		password = CipherUtil.generatePassword(password);
		boolean flag = loginServiceDao.findByUnamePass(request, loginname, password);
		if (!flag) {
			flag = loginServiceDao.findByUnamePass(request, loginname, password);
		}
		if (!flag) {
			try {
				response.getWriter().write("loginfail");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public ActionForward loginByCas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String loginname=(String)request.getSession().getAttribute("edu.yale.its.tp.cas.client.filter.user");
		BasicPerson person = loginServiceDao.getLoginerBySSOUser(loginname);
		if (person!=null) {
			request.getSession().setAttribute("logininfo", person);
			return new ActionForward("/view/main.jsp");
		}
		return null;
	}

	public ActionForward createImageCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ValidateCode image = new ValidateCode();

		try {
			ImageIO.write(image.creatImage(), "JPEG", response.getOutputStream());
			// 将认证码存入SESSION
			request.getSession().setAttribute("rand", image.sRand);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 至修改密码页面
	 */
	public ActionForward toupdatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		request.setAttribute("basicPerson", basicPerson);
		return mapping.findForward("toupdate");
	}

	/**
	 * 修改密码
	 */
	public ActionForward updatePassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		String password = request.getParameter("password");
		// MD5加密类
		if ("admin".equals(basicPerson.getPersonAccount())) {
			SysAdmin admin = (SysAdmin) loginServiceDao.queryObjectList("from SysAdmin where adminName = 'admin'").get(
					0);
			admin.setPassword(CipherUtil.generatePassword(password));
			loginServiceDao.updateObject(admin);

		} else {
			basicPerson.setPassword(CipherUtil.generatePassword(password));
			loginServiceDao.updateObject(basicPerson);
		}
		try {
			response.getWriter().write("true");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询密码是否正确修改密码
	 */
	@SuppressWarnings("static-access")
	public ActionForward checkPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		BasicPerson basicPerson = (BasicPerson) request.getSession().getAttribute("logininfo");
		String password = request.getParameter("password");
		// MD5加密类
		CipherUtil util = new CipherUtil();
		boolean flag = loginServiceDao.findByUnamePass(request, basicPerson.getPersonAccount(), util
				.generatePassword(password));
		if (!flag) {
			flag = loginServiceDao.findByUnamePass(request, basicPerson.getPersonAccount(), password);
		}
		try {
			if (flag) {
				response.getWriter().write("true");
			} else {
				response.getWriter().write("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward loginOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("logininfo");
		request.getSession().invalidate();
		return mapping.findForward("index");
	}

	public ActionForward checkRight(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("logininfo") == null) {
			return mapping.findForward("index");
		}
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String operationModuleFlag = request.getParameter("operationModuleFlag");
		BasicPerson person = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRight(person.getPersonAccount(), parentModuleFlag,
				operationModuleFlag);
		if (right) {
			try {
				response.getWriter().write("true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward checkRightForNews(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("logininfo") == null) {
			return mapping.findForward("index");
		}
		String parentModuleFlag = request.getParameter("parentModuleFlag");
		String operationModuleFlag = request.getParameter("operationModuleFlag");
		BasicPerson person = (BasicPerson) request.getSession().getAttribute("logininfo");
		boolean right = checkRight.moduleOperationRightForNews(person.getPersonAccount(), parentModuleFlag,
				operationModuleFlag);
		if (right) {
			try {
				response.getWriter().write("true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ActionForward loginIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("loginindex");
	}

	@SuppressWarnings("unchecked")
	public ActionForward topList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("logininfo") == null) {
			return mapping.findForward("loginindex");
		}
		BasicPerson person = (BasicPerson) request.getSession().getAttribute("logininfo");

		List<?> moduleList = null;
		if (moduleList == null) {
			if (person.getPersonName()==null) {
				moduleList = loginServiceDao
						.queryObjectList("from SysModule sm where sm.upModule='0100000000' order by sm.moduleId");
				if (moduleList.size() > 0) {
					SysModule m = (SysModule) moduleList.get(0);
					setAdminModuleId(m.getModuleId());
				}
			} else {
				moduleList = loginServiceDao
						.queryObjectList("from SysModule sm where sm.upModule='0200000000' and  sm.moduleId<>'0100000000'  order by sm.moduleId");
				if (moduleList.size() > 0) {
					SysModule m = (SysModule) moduleList.get(0);
					setSysModuleId(m.getModuleId());
				}
			}
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		request.setAttribute("date", sdf.format(date) + " " + getWeekOfDate());
		request.setAttribute("moduleList", moduleList);
		return mapping.findForward("top");
	}

	public String getWeekOfDate() {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}

		return weekDays[w];
	}

	@SuppressWarnings("unchecked")
	public ActionForward leftList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession().getAttribute("logininfo") == null) {
			return mapping.findForward("index");
		}
		BasicPerson person = (BasicPerson) request.getSession().getAttribute("logininfo");
		String module_id = request.getParameter("module_id");
		List modules = new ArrayList();
		if ((module_id == null) || "".equals(module_id)) {
			if (adminName.equals(person.getPersonAccount())) {
				module_id = adminModuleId;
			} else {
				module_id = sysModuleId;
			}
		}
			List childList = new ArrayList();
			if (person.getPersonName()==null) {
				childList = loginServiceDao.queryObjectList("from SysModule sm where sm.moduleId='" + module_id
						+ "' order by sm.moduleId");
			} else {
				childList = loginServiceDao
						.queryObjectList("select distinct rm.sysModule.parent    from SysRoleModule rm,SysRolePerson rp where "
								+ "  rm.sysRole.roleId=rp.sysRole.roleId and rm.sysModule.parent.moduleId='"
								+ module_id + "' and rp.basicPerson.personId=" + person.getPersonId());

			}
			for (int i = 0; i < childList.size(); i++) {
				SysModule m = (SysModule) childList.get(i);
				Map map = new TreeMap();
				String sql;
				if (person.getPersonName()==null) {
					sql = "from SysModule sm where sm.upModule='" + m.getModuleId() + "' order by sm.moduleId";
				} else {
					sql = "select distinct rm.sysModule.parent  from SysRoleModule rm,SysRolePerson rp where "
							+ "  rm.sysRole.roleId=rp.sysRole.roleId and rm.sysModule.parent.upModule='"
							+ m.getModuleId() + "' and rp.basicPerson.personId=" + person.getPersonId();
				}
				map.put(m.getModuleName(), loginServiceDao.queryObjectList(sql));
				modules.add(map);
			}
		request.setAttribute("modules", modules);
		return mapping.findForward("left");
	}
	
	public CheckRight getCheckRight() {
		return checkRight;
	}

	public void setCheckRight(CheckRight checkRight) {
		this.checkRight = checkRight;
	}

	/**
	 * @return the sysModuleId
	 */
	public String getSysModuleId() {
		return sysModuleId;
	}

	/**
	 * @param sysModuleId
	 *            the sysModuleId to set
	 */
	public void setSysModuleId(String sysModuleId) {
		this.sysModuleId = sysModuleId;
	}

	/**
	 * @return the adminModuleId
	 */
	public String getAdminModuleId() {
		return adminModuleId;
	}

	/**
	 * @param adminModuleId
	 *            the adminModuleId to set
	 */
	public void setAdminModuleId(String adminModuleId) {
		this.adminModuleId = adminModuleId;
	}

	public LoginService getLoginServiceDao() {
		return loginServiceDao;
	}

	public void setLoginServiceDao(LoginService loginServiceDao) {
		this.loginServiceDao = loginServiceDao;
	}
	/**   
	 * @return the adminName   
	 */
	public String getAdminName() {
		return adminName;
	}
	/**   
	 * @param adminName the adminName to set   
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}