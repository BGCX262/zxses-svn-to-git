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
		<title>缺失号码管理</title>
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
		<jsp:include page="/core/header.jsp" />

		<s:form action="misCheck!listMis.action" method="post" id="myform">
			<s:hidden name="focusInputId"></s:hidden>
			<div class="queryheader">
				起始号：
				<s:textfield name="misSegmentOp.staNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misStaNumber"></s:textfield>
				&nbsp;&nbsp; 终止号：
				<s:textfield name="misSegmentOp.endNumber" cssClass="zd1Solid"
					cssStyle="width:17%" id="misEndNumber"></s:textfield>
				&nbsp;
				<s:submit cssClass="an" value="检测"></s:submit>
				&nbsp;
				<s:if test="isEnable!=null">
					<s:if test="isEnable==0">
					&nbsp;
						<img alt="不可用" src="theme/blue/images/no.jpg" width="20px"
							height="20px" />
						<span style="color: red;"> 不可用</span>
					</s:if>
					<s:else>
					&nbsp;
						 <img alt="可用" src="theme/blue/images/yes.jpg" width="20px"
							height="20px" />
						<span style="color: #68AF02;"> 可用</span>
					</s:else>
				</s:if>
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
						终止号
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
							<s:property
								value="manufacture.prefix+endNumber+manufacture.suffix" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:form>
		<jsp:include page="/tag/page/page.jsp" flush="true">
			<jsp:param name="url" value="view/misCheck!listMis.action" />
			<jsp:param name="theme" value="simple" />
		</jsp:include>
	</body>
</html>
