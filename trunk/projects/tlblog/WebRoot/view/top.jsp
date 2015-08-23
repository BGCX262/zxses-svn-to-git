<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>塘朗小学文学社管理系统</title>
		<link href="<%=basePath%>res/admin/theme/blue/css/css.css" type=text/css
			rel=stylesheet>
		<script language="javascript" type="text/javascript">
		function changehead(headA){
			var headAs = document.getElementsByName('headA');
			if(headAs.length >0){
			for(var i=0;i<headAs.length;i++){
				headAs[i].className="linkbai";
				headAs[i].parentElement.className='menu01';
			}
			headA.className='linklv';
			headA.parentElement.className='menu001';
			window.parent.right.location ="<%=basePath%>view/right.jsp";
			}
		}
		
		 function goout(){
		      window.parent.location.href="<%=basePath%>sys/login.do?method=loginOut";
		    }
		 function goindex(){
		      window.parent.location.href="<%=basePath%>";
		    }
		 function updatepassword(){
		      window.parent.right.location="<%=basePath%>login.do?method=toupdatePassword";
		    }
		    
		</script>

	</head>
	<body>
		<!-- top menu -->
		<table class="toptable">
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td valign="top">
								<div id="logo">
									<img src="<%=basePath%>res/admin/theme/blue/images/system_name.jpg"
										width="520" height="50" />
								</div>
								<div class="topright">
									<div class="topquit">
										<a style="cursor: pointer" onclick="goout()">退出</a>
									</div>
									<div class="toppassword">
										<a style="cursor: pointer" onclick="updatepassword()">修改密码</a>
									</div>
									<div class="tophelp">
										帮助
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
							<logic:present name="sysFirstModules">
								<logic:iterate id="m" name="sysFirstModules">
									<td class="menu01">
										<a href="<%=basePath %>sys/login.do?method=leftList&module_id=${m.id}" target="left"
											class="linkbai" onclick="changehead(this)" name="headA">${m.name}</a>
									</td>
								</logic:iterate>
							</logic:present>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="topfontbg">
					当前用户: ${sysUserLoginInfo.username } &nbsp;&nbsp;&nbsp;&nbsp;${date}
				</td>
				<td class="topindex">
					<a href="<%=basePath%>" target="_blank">返回首页</a>
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

		<script type="text/javascript">
		changehead(document.getElementsByName('headA')[0]);
	</script>

	</body>
</html>




