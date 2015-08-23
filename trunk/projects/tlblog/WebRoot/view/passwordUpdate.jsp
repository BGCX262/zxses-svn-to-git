<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
		
		<%@ include file="/res/common/inc/backresources.jsp"%>
		
		<script type="text/javascript">
     function check(){
       var password = document.getElementsByName("password")[0].value;
       $.post("login.do?method=checkPassword",{password:password},back);
       function back(data){
         if(data=="true"){
           $("#p").html("√");
           $("#p").css("color","green");
           <%--$("#tijiao").show();--%>
<%--               document.getElementById("tijiao")[0].style.display = "block";--%>
			var password1 = document.getElementsByName("password1")[0];
       		var password2 = document.getElementsByName("password2")[0];
			password1.disabled="";
			password2.disabled="";
         }
         if(data=="false"){
            $("#tijiao").hide();
<%--            document.getElementById("tijiao")[0].style.display = "none";--%>
           $("#p").html("密码错误!");
           $("#p").css("color","red");
         }
       }
     }
     function isNull(){
		var password1 = document.getElementsByName("password1")[0].value;
		if(password1==''){
			alert('请输入新密码');
			return;
		}
     }
     function compare(){
       var password1 = document.getElementsByName("password1")[0].value;
       var password2 = document.getElementsByName("password2")[0].value;
       if(password1==password2){
          $("#tijiao").show();
          $("#p1").html("√");
          $("#p1").css("color","green");
       }else{
         $("#tijiao").hide();
          $("#p1").html("两次密码不一致");
          $("#p1").css("color","red");
          document.getElementsByName("password2")[0].value="";
       }
     }
     
     function sub(){
       
       var password1 = document.getElementsByName("password1")[0].value;
       if(password1==''){
       		alert('请输入新密码');
       		return;
       }
       var password2 = document.getElementsByName("password2")[0].value;
       if(password2==''){
       		alert('请输入确认密码');
       		return;
       }
       if(password1==password2){
	     $.post("login.do?method=updatePassword&password="+password2 ,back);
	     function back(data){
         if(data=="true"){
           alert("密码修改成功");
           window.parent.frames('right').location = "<%=basePath%>/login.do?method=toupdatePassword";
         }
       }
       }else{
          alert("两次密码不一致");
          return false;
       }
     }
     function back(){
     	window.parent.frames('right').location = "<%=basePath%>view/right.jsp";
     }
    </script>
	</head>

	<body>
		<form action="login.do?method=updatePassword" method="post"
			name="updateSysAdmin">
			<table cellSpacing="0" cellPadding="0" border="0" width="100%">
				<tr>
					<td>
						<table class="pathbg">
							<tr>
								<td width="514" height="24" class="dh1">
									系统管理-&gt;
									<span class="lv chuti">修改密码</span>
								</td>
								<td align="right">
									<input name="button" type="button" class="an" id="button"
										onclick="javascript:sub();" value="保存" />
									&nbsp;
									<input name="button2" type="button" class="an" id="button2"
										value="返回" onclick="javascript:back();" />
								</td>
							</tr>
						</table>
				</td>
				</tr>
			</table>
			<table cellSpacing="0" cellPadding="0" border="0" width="100%"
				align="center">
				<tr>
					<td>
						<table class="table" cellSpacing="1" cellPadding="0" width="100%"
							border="0" align="center">
							<tr>
								<td class="td_left">
									登录名
								</td>
								<td class="td_right">
									${basicPerson.personAccount }
								</td>
							</tr>
							<tr>
								<td class="td_left">
									真实姓名
								</td>
								<td class="td_right">
									${basicPerson.personAccount }
								</td>
							</tr>
							<tr>
								<td class="td_left">
									旧密码
								</td>
								<td class="td_right">
									<input type="password" name="password" value=""
										onblur="check()">
									<div id="p">
									</div>
								</td>

							</tr>
							<tr>
								<td class="td_left">
									新密码
								</td>
								<td class="td_right">
									<input id="newpassword" type="password" disabled="true"
										name="password1" value="" onblur="isNull()">
								</td>
							</tr>
							<tr>
								<td class="td_left">
									确定新密码
								</td>
								<td class="td_right">
									<input id="comparepassword" type="password" disabled="true"
										name="password2" value="" onblur="compare()">
									<div id="p1">
									</div>
								</td>
							</tr>

						</table>
					</td>
				</tr>
			</table>

		</form>
	</body>
</html>
