<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>深圳智翔信息技术有限公司</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>theme/blue/css/css.css">
		<!--[if lte IE 6]>
		<script src="<%=basePath%>tools/iepng/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
		    <script type="text/javascript">
		        DD_belatedPNG.fix('div, ul, img, li, input , a');
		    </script>
		<![endif]-->
		<script type="text/javascript">
			function resetvalues(){
			     document.getElementById("username").value=""; 
			     document.getElementById("password").value="";
			}
		</script>
	</head>

	<body class="backgroundcolor">
		<s:form action="login!login.action" method="post" theme="simple"
			cssStyle="background-color:#0185cc;">
			<div class="SystemName">
				<img src="theme/blue/images/systemname.png" width="580" height="101">
			</div>
			<div class="logink">
				<table align="center" class="percentage50">
					<tr>
						<td width="220"></td>
						<td height="45">
							用户名：
						</td>
						<td>
							<s:textfield name="username" id="username" cssClass="zdpic1"></s:textfield>
						</td>
					</tr>
					<tr>
						<td></td>
						<td height="50">
							密&nbsp;&nbsp; 码：
						</td>
						<td>
							<input name="password" id="password" type="password"
								class="zdpic2" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td colspan="2" height="50">
							<s:submit value="登录" cssClass="loginan"></s:submit>
							<input type="button" name="button2" id="button2" value="重置"
								class="loginan" onclick="resetvalues();">
						</td>
					</tr>
					<tr>
						<td colspan="3" class="tdcenter hong height50">
							<s:actionmessage />
						</td>
					</tr>
				</table>
			</div>
			<div class="foot">
				Copyright&copy;2011 深圳智翔信息技术有限公司 版权所有 维护电话：0755-21672202
			</div>
		</s:form>
	</body>
</html>
