<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.apache.commons.lang.xwork.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="page"
	uri="http://jsptags.com/tags/navigation/pagination"%>
<script type="text/javascript">
	function goPage(pageIndex,url) {
		var pager;
		var pageIndex1;
		var offset;
		var offsetIndex;
		pageIndex1 = pageIndex.value;
		if(!/^[0-9]*[1-9][0-9]*$/.test(pageIndex1)){
			alert('页数不正确,请重新输入一个正确的数值!');
			return;
		}
		if(pageIndex1>${paginate.totalPage}){
			pageIndex1=${paginate.totalPage};
		}
		offset = ${paginate.size} * (pageIndex1 - 1);
		pager = "paginate.offset=" + offset;
		offsetIndex = url.indexOf("paginate.offset=");
		url = url.replace(url.substring(offsetIndex),pager);
		window.location.href = url ;
	}
</script>
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
		maxIndexPages="5" id="paginate">
		<%
			Enumeration e = request.getParameterNames();
						while (e.hasMoreElements()) {
							String tmpOffset = (String) e.nextElement();
							if (!tmpOffset.equals("paginate.offset")) {
								String tmpStr = request.getParameter(tmpOffset);
								if (tmpStr != null) {
		%>
		<page:param name="<%=tmpOffset%>" value="<%=tmpStr%>" />
		<%
			}
							}
							if (StringUtils.isNotEmpty(isHidden)
									&& "true".equals(isHidden)) {
								if ("theme".equals(tmpOffset)
										|| "paginate.offset".equals(tmpOffset)) {
									String tmpStr2 = request
											.getParameter(tmpOffset);
									if (tmpStr2 == null) {
										tmpStr2 = "";
									}
		%>
		<input type="hidden" name="<%=tmpOffset%>" value="<%=tmpStr2%>" />
		<%
			}
							}
						}
		%>
		<page:page>
		共&nbsp;<font color="red">${paginate.total }</font>&nbsp;条&nbsp;&nbsp;&nbsp;
		第&nbsp;<input type="text" value="${currPageNum }"
				style="width: 35px; "
				onkeydown="if(event.keyCode==13){goPage(this,'${pageUrl}');}" />
					/&nbsp;<font color="red">${paginate.totalPage }</font>&nbsp;页&nbsp;&nbsp;&nbsp;
					</page:page>
		<page:first>
			<span id="firstPageUrl" style="display: none;">${pageUrl}</span>
			<c:if test="${currPageNum==1}">
				<font color="gray">首页</font>&nbsp;|&nbsp; 
			</c:if>
			<c:if test="${currPageNum!=1}">
				<a href="${pageUrl}">首页</a>&nbsp;|&nbsp;
			</c:if>
		</page:first>
		<page:prev ifnull="true">
			<c:if test="${pageUrl!=null}">
				<a href="${pageUrl}">上一页</a>&nbsp;|&nbsp;
			</c:if>
			<c:if test="${pageUrl==null}">
				<font color="gray">上一页</font>&nbsp;|&nbsp;
			</c:if>
		</page:prev>
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
				<a href="${pageUrl}">尾页</a>
			</c:if>
		</page:last>
	</page:pagination>
</c:if>