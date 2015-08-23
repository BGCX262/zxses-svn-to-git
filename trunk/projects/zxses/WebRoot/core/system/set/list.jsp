<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>系统配置</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>theme/blue/css/css.css" />
	</head>
	
	<body class="centerbg">
		<s:form action="configSet!save.action" method="post" name="myform" namespace="/view" theme="simple"
				enctype="multipart/form-data">
		<jsp:include page="/core/header.jsp" />
			<div class="editdiv">
				<table class="edittable">
					<tr>
						<td class="tdleft">
							系统名称：
						</td>
						<td class="tdright">
							<s:textfield name="configSetVO.systemName" cssClass="zd1" />
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							版权信息：
						</td>
						<td class="tdright">
							<s:textfield name="configSetVO.copyRight" cssClass="zd1" />
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							Logo图片：
						</td>
						<td class="tdright">
							<s:file name="myFile" label="Image File1" cssClass="zd1"/>
							(
							<span class="hong">*</span>
							<span class="hui"> 建议上传的图片宽为：520 高为：50</span>)
						</td>
					</tr>
					<tr>
						<td class="tdleft">
						&nbsp;
						</td>
						<td class="tdright">
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							登录策略：
						</td>
						<td class="tdright">
						
							<s:checkbox name="configSetVO.checkCode" value="configSetVO.checkCode"></s:checkbox>
							验证码<br/>
							<s:checkbox name="configSetVO.failLock" value="configSetVO.failLock"></s:checkbox>
							失败锁定<br/>
							<s:checkbox name="configSetVO.ipControl" value="configSetVO.ipControl"></s:checkbox>
							IP控制<br/>
						</td>
					</tr>
					<tr>
						<td class="tdleft">
						&nbsp;
						</td>
						<td class="tdright">
						</td>
					</tr>
					<tr>
						<td class="tdleft">
							修改密码策略：
						</td>
						<td class="tdright">
							<s:checkbox name="configSetVO.lengthLimit" value="configSetVO.lengthLimit"></s:checkbox>
							长度限制<br/>
							<s:checkbox name="configSetVO.mixNumber" value="configSetVO.mixNumber"></s:checkbox>
							数字字母混合<br/>
							<s:checkbox name="configSetVO.checkPassword" value="configSetVO.checkPassword"></s:checkbox>
							简易密码检测<br/>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td height="32" align="left">
							<s:submit cssClass="an" value="保存"></s:submit>
						<br /></td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
