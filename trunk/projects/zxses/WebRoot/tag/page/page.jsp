<div style="width: 98%;text-align: right;">
<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.lang.xwork.StringUtils"%>
<%
	String theme = request.getParameter("theme");
	if (StringUtils.isEmpty(theme)) {
		theme = "simple";
	}
%>
<%
	if ("simple".equals(theme)) {
%>
<jsp:include page="/tag/page/theme/simple.jsp" flush="true" />
<%
	} else if ("view".equals(theme)) {
%>
<jsp:include page="/tag/page/theme/view.jsp" flush="true" />
<%
	} else {
%>
<jsp:include page="/tag/page/theme/simple.jsp" flush="true" />
<%
	}
%>
</div>