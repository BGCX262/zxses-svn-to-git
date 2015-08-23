<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>修改部门信息</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
		<script type="text/javascript">
			
			if(${flushLeft=="true"}){
				window.parent.leftIframe.location.reload();
			}
			
			if(${selectHrDept!=null}){
				var treeObj = window.parent.$.fn.zTree.getZTreeObj("treeDemo");
				var selectNode = treeObj.getSelectedNodes();
				var tmpParentNode=null;
				if(selectNode.length>0){
					tmpParentNode=selectNode[0];
				}
				tmpParentNode.name="${selectHrDept.name}";
				treeObj.updateNode(tmpParentNode);
			}
		</script>
	</head>

	<body>
		<center>
			<div id="msgdiv" style="line-height: 20px; color: white;">
				<s:if test="#attr.messageInfo!=null">
					<span
						style="vertical-align: middle; padding: 10px 0px 5px 5px; background-color: #68AF02;"><s:property
							value="%{#attr.messageInfo}" /> </span>
				</s:if>
				<s:if test="#attr.messageError!=null">
					<span
						style="vertical-align: middle; padding: 10px 0px 5px 5px; background-color: #eaa000"><s:property
							value="%{#attr.messageError}" /> </span>
				</s:if>
			</div>
		</center>
		<s:form action="hrDept!updateHrDept.action" method="post"
			id="hrDeptForm" name="hrDeptForm">
			<s:hidden name="inputPage" value="update.jsp"></s:hidden>
			<s:hidden name="hrDept.id"></s:hidden>
			<div class="percentage100 tdbk">
				<table class="edittable">
					<tr>
						<td class="tdleft">
							部门编号：
						</td>
						<td class="tdright">
							<s:textfield name="hrDept.code" cssClass="zd1" id="hrDept_code"></s:textfield>
							<s:fielderror theme="simple">
								<s:param>hrDept.code</s:param>
							</s:fielderror>
							<s:actionmessage value="actionMessages[0]" cssStyle=" color: red" />
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							部门名称：
						</td>
						<td class="tdright">
							<s:textfield name="hrDept.name" cssClass="zd1" id="hrDept_name"></s:textfield>
							<s:fielderror theme="simple">
								<s:param>hrDept.name</s:param>
							</s:fielderror>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							部门描述：
						</td>
						<td class="tdright">
							<s:textarea name="hrDept.comment" cssClass="zd1"
								id="hrDept_comment"></s:textarea>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:submit cssClass="an" value="保存"></s:submit>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
