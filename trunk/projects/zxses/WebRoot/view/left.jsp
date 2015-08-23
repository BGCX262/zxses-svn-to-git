<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<title>left</title>
		<%@ include file="/inc/theme.inc"%>
		<%@ include file="/inc/js.inc"%>
	</head>

	
<body  class="panel"  style="overflow: hidden">
<table width="167" border="0" cellspacing="0" cellpadding="0">
    
    <c:forEach var="pesec" items="${menu.childList}">
       <c:choose>
       <c:when test="${empty pesec.childList }">
           <tr>
             <td>
           	  <table>
				<tr>
					<td class="leftmenu"><a href="view/main!jumpURL.action?menuid=${pesec.id }"  target="center" id="f${pesec.id }" class="leftmeun1"><span>${pesec.name }</span> </a></td>
				</tr>
			 </table>	
			 </td>
           </tr>
       </c:when>
       <c:otherwise>
        <tr>
             <td>
           	  <table>
				<tr>
					<td class="leftmenu">${pesec.name }</td>
				</tr>
				<tr>
					<td>
					  <div style="height: 100%; overflow-x: hidden; overflow-y: auto; padding-top: 3px;  padding-left: 5px">
						<c:forEach var="pethi" items="${pesec.childList}" varStatus="id">
						  <table>
							<tr>
								<td class="${pethi.imgSrc}"></td>
								<td align="left"><div class="lefticon"><a href="view/main!jumpURL.action?menuid=${pethi.id }" target="center" id="f${pethi.id }" class="leftmeun1">${pethi.name } </a></div></td>
							</tr>
						</table>
					    </c:forEach>
					   </div> 
					</td>
				</tr>
			 </table>	
			 </td>
           </tr>
       </c:otherwise>
       </c:choose>
    </c:forEach>
  </table>
</body>
</html>
