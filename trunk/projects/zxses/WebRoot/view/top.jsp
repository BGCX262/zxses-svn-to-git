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
		<title>top页面</title>
		<%@ include file="/inc/theme.inc"%>
		<script type="text/javascript">
			  function logout(){
			    	window.parent.location.href="login!logout.action";
			  }
			  var lastid="";
			  function go(menuid){
				    if(lastid==""){   
				       document.getElementById(menuid).className="menu001";
				       document.getElementById("a"+menuid).className="linklv";
				    }else{ 
				      document.getElementById(lastid).className="menu01";
				      document.getElementById(menuid).className="menu001";
				      document.getElementById("a"+lastid).className="linkbai";
				       document.getElementById("a"+menuid).className="linklv";
				    }
				    lastid=menuid;
				    window.parent.frames["left"].location="<%=basePath%>view/main!leftTree.action?menuid="+menuid;
				    window.parent.frames["center"].location="<%=basePath%>view/right.jsp";
			  }
		</script>

	</head>

	<body  onload=" go('${sysMenu.id}')">
		<table class="toptable">
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>
								<div id="logo"></div>
								<div class="topright">
									<div class="topquit">
										<a href="#" onclick="javascript:logout()">退出</a>
									</div>
									<div class="toppassword">
										<a href="view/main!updatepassword.action" target="center">修改密码</a>
									</div>
									<div class="tophelp">
										<a href="javascript:void(0)">帮助</a>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="menutable">
						<tr>
							<s:iterator id="menu" value="#request.rootMenuList"> 
					          <td class="menu01" id="${menu.id }">
					            <a onclick="go('${menu.id }')"  href="javascript:;" class="linkbai" id="a${menu.id }"> <s:property value="#menu.name"/></a> 
					          </td>
					       </s:iterator>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="topfontbg">
				    当前用户：${loginer.userName }&nbsp;&nbsp; 当前IP：${loginer.currentLoginIp } &nbsp;&nbsp;上次登录时间：${loginer.lastLoginTime }&nbsp;&nbsp;上次登录IP：${loginer.lastLoginIp } 
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="frameleft"></td>
				<td class="framecenter">
					&nbsp;
				</td>
				<td class="frameright"></td>
			</tr>
		</table>
	</body>
</html>
