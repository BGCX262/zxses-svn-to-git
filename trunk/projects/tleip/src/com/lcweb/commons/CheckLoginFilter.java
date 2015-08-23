package com.lcweb.commons;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter implements Filter {
	CheckRight cr;

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		if (session != null) {

			if (session.getAttribute("logininfo") != null && !"".equals(session.getAttribute("logininfo"))) {
				filterChain.doFilter(servletRequest, servletResponse);
			} else {
				response.sendRedirect("../loginovertime.jsp");
			}
		} else {
			response.sendRedirect("login.do?method=loginOut");
		}

	}

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public CheckRight getCr() {
		return cr;
	}

	public void setCr(CheckRight cr) {
		this.cr = cr;
	}

}
