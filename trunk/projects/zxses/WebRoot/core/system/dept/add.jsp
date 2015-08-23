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
		<title>新增部门信息</title>

		<title>部门管理</title>
		<%@ include file="/inc/common.inc"%>
		<script type="text/javascript" src="js/jquery/jquery-1.7.1.min.js"></script>
		<script type="text/javascript">
			function saveHrDept(){
				var treeObj = window.parent.$.fn.zTree.getZTreeObj("treeDemo");
				var selectNode = treeObj.getSelectedNodes();
				var tmpParentNode;
				if(selectNode.length>0){
					if($("input[name=addType]")[0].checked){
						$('#hrDept_hrDept_id').val(selectNode[0].pId);
						tmpParentNode=selectNode[0].getParentNode();
					}else{
						$('#hrDept_hrDept_id').val(selectNode[0].id);
						tmpParentNode=selectNode[0];
					}
				}
				return true;
			}
			
			if(${selectHrDept!=null}){
				var treeObj = window.parent.$.fn.zTree.getZTreeObj("treeDemo");
				var selectNode = treeObj.getSelectedNodes();
				var tmpParentNode=null;
				if(selectNode.length>0){
					if(${addType=="curr"}){
						tmpParentNode=selectNode[0].getParentNode();
					}else{
						tmpParentNode=selectNode[0];
					}
				}
				var newNode = {id:'${selectHrDept.id}'
								,pId:'${selectHrDept.hrDept==null?"":selectHrDept.hrDept.id}'
								,name:'${selectHrDept.name}'};
				treeObj.addNodes(tmpParentNode,newNode);
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
		<s:form action="hrDept!saveHrDept.action" method="post"
			id="hrDeptForm" name="hrDeptForm" onsubmit="return saveHrDept()">
			<s:hidden name="inputPage" value="add.jsp"></s:hidden>
			<s:hidden name="hrDept.hrDept.id" id="hrDept_hrDept_id"></s:hidden>
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
						<td class="tdleft">
							新增类型：
						</td>
						<td class="tdright">
							<s:radio list="# {'curr':'新增平级','child':'新增下级'}" name="addType"
								value="%{'child'}"></s:radio>
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
