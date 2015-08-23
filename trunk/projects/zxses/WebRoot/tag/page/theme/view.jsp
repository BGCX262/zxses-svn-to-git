<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.lang.xwork.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page"
	uri="http://jsptags.com/tags/navigation/pagination"%>
<%
	String hrefUrl = request.getParameter("url");
	//当删除等操作时，需要保留当前的页数等信息
	String isHidden = request.getParameter("isHidden");
%>
<c:if test="${paginate.total==0}">
	<span style="color: red">对不起！没有找到相应的记录！</span>
</c:if>
<c:if test="${paginate.total>0}">
	<page:pagination url="<%=hrefUrl%>" items="${paginate.total }"
		export="currPageNum=pageNumber" maxPageItems="${paginate.size}"
		maxIndexPages="20" id="paginate">

		<%
			Enumeration e = request.getParameterNames();
						while (e.hasMoreElements()) {
							String a = (String) e.nextElement();
							if (!a.equals("paginate.offset")) {
								String tmpStr = request.getParameter(a);
								if (tmpStr != null) {
		%>
		<page:param name="<%=a%>" value="<%=tmpStr%>" />
		<%
			}
							}
							if (StringUtils.isNotEmpty(isHidden)
									&& "true".equals(isHidden)) {
								if ("theme".equals(a)
										|| "paginate.offset".equals(a)) {
									String tmpStr2 = request.getParameter(a);
									if (tmpStr2 == null) {
										tmpStr2 = "";
									}
		%>
		<input type="hidden" name="<%=a%>" value="<%=tmpStr2%>" />
		<%
			}
							}
						}
		%>
		<page:first>
			<span id="firstPageUrl" style="display: none;">${pageUrl}</span>
			<c:if test="${currPageNum==1}">
				<font color="gray"> 首页</font>&nbsp;|&nbsp;
		</c:if>
			<c:if test="${currPageNum!=1}">
				<a href="${pageUrl}">首页</a>&nbsp;|&nbsp;
		</c:if>
		</page:first>
		<page:prev ifnull="true">
			<c:if test="${pageUrl!=null}">
				<a href="${pageUrl}">上一页</a>&nbsp;&nbsp;
		</c:if>
			<c:if test="${pageUrl==null}">
				<font color="gray">上一页</font>&nbsp;&nbsp;
		</c:if>
		</page:prev>
		<page:pages>
			<c:if test="${currPageNum==pageNumber}">
				<font color="red"><b>${pageNumber}</b> </font>
			</c:if>
			<c:if test="${currPageNum!=pageNumber}">
				<a href="${pageUrl}">${pageNumber}</a>
			</c:if>
		</page:pages>
	&nbsp;&nbsp;
	<page:next ifnull="true">
			<c:if test="${pageUrl!=null}">
				<a href="${pageUrl}">下一页</a>&nbsp;|&nbsp;
		</c:if>
			<c:if test="${pageUrl==null}">
				<font color="gray">下一页</font>&nbsp;|&nbsp;
		</c:if>
		</page:next>
		<page:last>
			<c:if test="${currPageNum==pageNumber}">
				<font color="gray">尾页</font>
			</c:if>
			<c:if test="${currPageNum!=pageNumber}">
				<a href="${pageUrl}">尾页 </a>
			</c:if>
		</page:last>
	</page:pagination>
</c:if>