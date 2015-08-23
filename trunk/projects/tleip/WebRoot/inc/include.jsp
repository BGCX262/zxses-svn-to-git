<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String pathBase = request.getContextPath();
	String webPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathBase+"/";
	request.setAttribute("basePath", webPath);
%>
