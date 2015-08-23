package com.agilefly.web.form;

import org.apache.struts.action.ActionForm;

/**
 * @author boleyn_renlei
 * @date 2012-8-21
 * @Description 系统设置Form
 */
public class SysConfigForm extends ActionForm {
	
	private String sysConfigCode;
	private String sinaWeibo;
	private String onlineQq;
	private String picQq;

	public String getOnlineQq() {
		return onlineQq;
	}

	public void setOnlineQq(String onlineQq) {
		this.onlineQq = onlineQq;
	}

	public String getSinaWeibo() {
		return sinaWeibo;
	}

	public void setSinaWeibo(String sinaWeibo) {
		this.sinaWeibo = sinaWeibo;
	}

	public String getSysConfigCode() {
		return sysConfigCode;
	}

	public void setSysConfigCode(String sysConfigCode) {
		this.sysConfigCode = sysConfigCode;
	}
	
	public String getPicQq() {
		return picQq;
	}

	public void setPicQq(String picQq) {
		this.picQq = picQq;
	}
}
