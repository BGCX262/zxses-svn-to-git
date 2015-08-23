<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>缺失号码录入</title>
		<%@ include file="/inc/common.inc"%>
		<%@ include file="/inc/js.inc"%>
		<script type="text/javascript">
		
		function setFocusInput(focusInputId){
			if(focusInputId==""){
				document.getElementById("misStaNumber").focus();
				document.getElementById("misStaNumber").select();
			}else{
				document.getElementById(focusInputId).focus();
				document.getElementById(focusInputId).select();
			}
		}
		</script>
	</head>
	<body class="centerbg" onload="setFocusInput('${focusInputId }')">
		<jsp:include page="/core/header.jsp">
			<jsp:param name="buttons"
				value="返回--view/misAdd!listMan.action?theme=simple" />
		</jsp:include>

		<s:form action="misAdd!saveMis.action" method="post" id="myform">
			<s:hidden name="inputPage" value="listMis.jsp"></s:hidden>
			<s:hidden name="focusInputId"></s:hidden>
			<s:hidden name="checkManIds"></s:hidden>
			<div class="queryheader">
				起始号：
				<s:textfield name="misStaNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misStaNumber"></s:textfield>
				&nbsp;&nbsp; 终止号：
				<s:textfield name="misEndNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misEndNumber"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="保存"></s:submit>
			</div>
			<hr />
			<table id="mytable">
				<tr>
					<th>
						生产单号
					</th>
					<th>
						客户名称
					</th>
					<th>
						产品名称
					</th>
					<th>
						起始号
					</th>
					<th>
						检码
					</th>
					<th>
						终止号
					</th>
					<th>
						检码
					</th>
					<th width="120">
						补号
					</th>
				</tr>
				<s:iterator value="misSegments" status="i">
					<tr class="tr${i.index%2+1}" onMouseOver="this.className='cstd2'"
						onMouseOut="this.className='tr${i.index%2+1}'">
						<td>
							<s:property value="manufacture.manNumber" />
						</td>
						<td>
							<s:property value="manufacture.cusName" />
						</td>
						<td>
							<s:property value="manufacture.proName" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+staNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="staCheckCode" />
						</td>
						<td>
							<s:property
								value="manufacture.prefix+endNumber+manufacture.suffix" />
						</td>
						<td>
							<s:property value="endCheckCode" />
						</td>
						<td>
							<s:if test="isFilling==0">
								<span style="color: blue;">未补号</span>
							</s:if>
							<s:else>
								<span style="color: red;"> 已补号</span>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misAdd!listMis.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
