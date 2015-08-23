package com.agilefly.commons.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilefly.bean.SysUser;

public class WebUtils {
	/**
	 * 获取登录系统用户
	 * @param request
	 * @return
	 */
	public static SysUser getSysUser(HttpServletRequest request){
		return (SysUser)request.getSession().getAttribute("sysUserLoginInfo");
	}
	
	/**
	 * 客户端写入信息
	 * @param response
	 * @param text
	 */
	public static void writeResponse(HttpServletResponse response,String text){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(text);
			out.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(out != null){
				out.close();
				out = null;
			}
		}
	}
}
