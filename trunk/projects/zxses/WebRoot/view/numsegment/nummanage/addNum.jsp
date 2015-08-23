<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>用户号码段</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript">
		function clearForm(){
			document.getElementById("manStaNumber").value="";
			document.getElementById("manEndNumber").value="";
			document.getElementById("manPrefix").value="";
			document.getElementById("manSuffix").value="";
		}
		
		function enterPress(e){
			var e=e||window.event;
			if(e.keyCode==13){
				e.keyCode=9;
			}
		}
		
		function setFocusInput(){
			document.getElementById("manStaNumber").focus();
			document.getElementById("manStaNumber").select();
		}
		
		function isCon(){
			document.myform.submit();
		}
		
		function isNoCon(){
			document.getElementById("isContinue").value="";
			document.myform.action="view/numManage!entryAddNum.action";
			document.myform.submit();
		}
		</script>
	</head>

	<body class="centerbg" onload="setFocusInput()">
		<s:form action="numManage!saveNum.action" method="post" id="myform">
			<jsp:include page="/core/header.jsp" />
			<s:hidden name="inputPage" value="addNum.jsp"></s:hidden>
			<s:hidden name="isContinue"></s:hidden>
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft1zx">
							起始号：
						</td>
						<td class="tdright">
							<s:textfield name="manufacture.staNumber" cssClass="zd1Solid"
								id="manStaNumber" onkeypress="enterPress(event)"
								onkeydown="enterPress()"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.staNumber</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							终止号：
						</td>
						<td class="tdright">
							<s:textfield name="manufacture.endNumber" cssClass="zd1Solid"
								id="manEndNumber"
								readonly="(isContinue==1 || isContinue==2)?true:false" />
							<s:fielderror theme="simple">
								<s:param>manufacture.endNumber</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							前缀：
						</td>
						<td class="tdright">
							<s:textfield name="manufacture.prefix" cssClass="zd1Solid"
								id="manPrefix" onkeypress="enterPress(event)"
								onkeydown="enterPress()" />
						</td>
					</tr>
					<tr>
						<td class="tdleft1zx">
							后缀：
						</td>
						<td class="tdright">
							<s:textfield name="manufacture.suffix" cssClass="zd1Solid"
								id="manSuffix" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:if test="isContinue!=1 && isContinue!=2">
								<s:submit cssClass="an" value="下一步" cssStyle="width:55px;"></s:submit>
							&nbsp;
							<button class="an" onclick="clearForm()" type="button"
									style="width: 50px;">
									重置
								</button>
							</s:if>
							<s:else>
								<s:submit cssClass="an" value="下一步" cssStyle="width:55px;"
									disabled="true"></s:submit>
							&nbsp;
							<button class="an" onclick="clearForm()" type="button"
									style="width: 50px;" disabled="disabled">
									重置
								</button>
							</s:else>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
