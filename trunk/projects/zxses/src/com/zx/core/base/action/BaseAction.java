package com.zx.core.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lcsoft.control.tag.page.pojo.Paginate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zx.core.login.service.LoginService;
import com.zx.core.util.PropertiesUtil;
import com.zx.core.util.SysLoggerUtil;
import com.zx.core.vo.Loginer;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 4666739594207591798L;

	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String INFO = "info";

	protected Long id;
	protected Long[] ids;
	protected String checkIds;
	protected String redirectionUrl;// 操作提示后的跳转URL,为null则返回前一页

	private Paginate paginate = new Paginate();

	protected Loginer loginer;
	
	protected SysLoggerUtil logger = SysLoggerUtil.getSysLogger(BaseAction.class
			.getName());

	// 获取Request
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 设置Session
	@SuppressWarnings("unchecked")
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	// 获取Session
	@SuppressWarnings("unchecked")
	public Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}

	// 获取Attribute
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	/**
	 * 从session中取出用户登录信息
	 * 
	 * @param request
	 * @return
	 */
	public Loginer getCurrUserInfo(HttpServletRequest request) {
		if (loginer == null) {
			loginer = (Loginer) getSession("loginer");
		}
		return loginer;
	}

	/***************************************************************************
	 * 添加head用户提示消息
	 * 
	 * @param key
	 */
	public void addMessageInfo(String key) {
		String msg = "";
		if (!key.equals(getText(key))) {
			msg = getText(key);
		} else {
			msg = PropertiesUtil.getInstance().load(
					"config/struts/resource.properties").getProperty(key);
		}
		this.getRequest().setAttribute("messageInfo", msg);
	}

	/***************************************************************************
	 * 添加head操作错误信息
	 * 
	 * @param key
	 */
	public void addMessageError(String key) {
		String msg = "";
		if (!key.equals(getText(key))) {
			msg = getText(key);
		} else {
			msg = PropertiesUtil.getInstance().load(
					"config/struts/resource.properties").getProperty(key);
		}
		this.getRequest().setAttribute("messageError", msg);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getRedirectionUrl() {
		return redirectionUrl;
	}

	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}

	public Paginate getPaginate() {
		return paginate;
	}

	public void setPaginate(Paginate paginate) {
		this.paginate = paginate;
	}

	public String getCheckIds() {
		return checkIds;
	}

	public void setCheckIds(String checkIds) {
		this.checkIds = checkIds;
	}

}
