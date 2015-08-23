package com.lcweb.base.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class WebUtil {
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
