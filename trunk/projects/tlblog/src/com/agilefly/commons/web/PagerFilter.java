package com.agilefly.commons.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;

import com.agilefly.bean.SysPrivilegeId;

/**
 * @author boleyn_renlei
 * @date May 17, 2012 12:32:10 AM
 * 根据当前线程设置分页参数
 */
public class PagerFilter implements Filter {
	public static final String PAGE_SIZE_NAME = "ps";
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		SystemContext.setOffset(getOffset(httpRequest));
		SystemContext.setPagesize(getPagesize(httpRequest));
		try{
			chain.doFilter(request, response);
		}finally{
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
	}
	
	private int getOffset(HttpServletRequest request){
		int offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception ignore) {
		}
		return offset;
	}
	
	private int getPagesize(HttpServletRequest httpRequest){
//		return 10;
		
		//首先判断request中是否有pagesize参数，如果有这个参数，证明客户端正在请求改变每页显示的行数
		String psvalue = httpRequest.getParameter("pagesize");
		if(psvalue != null && !psvalue.trim().equals("")){
			Integer ps = 0;
			try {
				ps = Integer.parseInt(psvalue);
			} catch (Exception e) {
			}
			if(ps != 0){
				httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, ps);
			}
		}
		
		//判断当前session中是否有pagesize的值
		Integer pagesize = (Integer)httpRequest.getSession().getAttribute(PAGE_SIZE_NAME);
		if(pagesize == null){
			httpRequest.getSession().setAttribute(PAGE_SIZE_NAME, 10);
			return 10;
		}
		
		return pagesize;
	}

	public void init(FilterConfig config) throws ServletException {
		//注册类型转换器
		ConvertUtils.register(new SysPrivilegeIdConverter(), SysPrivilegeId.class);
	}
}
