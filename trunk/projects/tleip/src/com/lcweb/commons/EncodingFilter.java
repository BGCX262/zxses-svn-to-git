package com.lcweb.commons;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.Velocity;

public class EncodingFilter implements Filter {
	protected FilterConfig filterConfig = null;   
	protected String encoding = ""; 
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  ((HttpServletResponse) response).setHeader("Cache-Control","no-cache");   
		  ((HttpServletResponse) response).setHeader("Pragma","no-cache");   
		  ((HttpServletResponse) response).setDateHeader ("Expires", -1); 
		  if(encoding != null){
			  request.setCharacterEncoding(encoding);  
			  response.setContentType("text/html;charset="+encoding); 

		  }			 
		 chain.doFilter(request, response);   

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		 this.filterConfig = filterConfig;   
	     this.encoding = filterConfig.getInitParameter("encoding");   
	     
	     //初始化velocity
	     Properties props = new Properties();
	     props.put("runtime.log", filterConfig.getServletContext().getRealPath("/WEB-INF/velocity.log"));
	     props.put("file.resource.loader.path", filterConfig.getServletContext().getRealPath("/WEB-INF/vm"));
	     props.put("input.encoding", "UTF-8");
	     //props.put("default.contentType", "text/html; charset=UTF-8");
	     props.put("output.encoding", "UTF-8");
	     try {
	    	 Velocity.init(props);
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
	}

}
