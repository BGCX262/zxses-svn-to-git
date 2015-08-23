package com.lcweb.base.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * @Title: SessionCounter.java
 * @Description: 
 * @Author: feng
 * @Time: Jun 27, 2011
 */

public class SessionCounter extends HttpServlet implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	private static int sessionCounter = 0;

	public void sessionCreated(HttpSessionEvent se) {
		sessionCounter++;
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCounter--;
	}

	public static int getOnlineSession() {
		return sessionCounter;
	}
}
