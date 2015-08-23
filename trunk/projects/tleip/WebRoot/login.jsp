<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/css.css" />
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/checkright.js"></script>
		<!--[if lte IE 6]>
		<script src="<%=basePath%>res/admin/js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
		    <script type="text/javascript">
		        DD_belatedPNG.fix('div, ul, img, li, input , a');
		    </script>
		<![endif]-->
		<script type="text/javascript">	
		  function send(event) {   
		    if(event.keyCode == 13) {   
		       login();   
		     }   
		  } 
		 
			function login(){ 
		    var username = document.getElementsByName("username")[0].value;
		    var password = document.getElementsByName("password")[0].value;
		    var imagecode = document.getElementsByName("imagecode")[0].value;
		    var mes =  document.getElementsByName("message")[0]; 
		    if(username==""){
		      mes.innerHTML = "用户名不能为空";
		      document.getElementsByName("username")[0].focus();
		      return false;
		    }
		    if(password==""){
		      mes.innerHTML = "密码不能为空";
		      document.getElementsByName("password")[0].focus();
		      return false;
		    }
		    if(imagecode==""){
		      mes.innerHTML = "验证码不能为空";
		      document.getElementsByName("password")[0].value="";
		      document.getElementsByName("imagecode")[0].focus();
		      return false;
		    }
		   $.post("login.do?method=login",{username:username,password:password,imagecode:imagecode},function(response){
		            if(response=="loginfail"){
		               mes.innerHTML = "用户名或密码错误";
		               document.getElementsByName("password")[0].value="";
		               return;
		            }if(response=="code"){
		               mes.innerHTML = "验证码错误";
		               document.getElementsByName("password")[0].value="";
		               return;
		            }else{ 
		                window.location.href="view/main.jsp";
		            }
		     });
		}
			function reset(){
				 document.getElementsByName("username")[0].value='';
		    	 document.getElementsByName("password")[0].value='';
		    	 document.getElementsByName("imagecode")[0].value='';
			}
			function changeimage(){
				var date = new Date();
				document.getElementById('codeImage').src ="codeImageAction.do?timestamp="+date.getTime();
			}
 	</script>
	</head>
	<body class="backgroundcolor">
		<div class="SystemName">
			<img src="<%=basePath%>res/theme/blue/images/systemname.png"
				width="580" height="101" />
		</div>
		<div class="logink">
			<table align="center" class="percentage50">
				<tr>
					<td colspan="3"></td>
				</tr>

				<tr>
					<td class="tdwidth220"></td>
					<td width="50">
						用户名：</td>
					<td align="left">
						<input type="text" name="username" id="textfield" class="zdpic1"
							value="feng" />
					</td>
				</tr>

				<tr>
					<td class="tdwidth220"></td>
					<td class="height40 ">
						密&nbsp;&nbsp; 码：</td>
					<td align="left">
						<input type="password" name="password" id="textfield2"
							value="888888" class="zdpic2" />
					</td>
				</tr>

				<tr>
					<td class="tdwidth220"></td>
					<td class="height40 ">
						验证码：
					</td>
					<td align="left">
						<input type="text" name="imagecode" id="textfield3" value=""
							class="zdpic3" maxlength="4" />&nbsp;&nbsp;<span><img id="codeImage" src="<%=basePath%>codeImageAction.do" style="vertical-align:bottom;padding-bottom: 3px;"/></span>
					</td>
				</tr>

				<tr>
					<td class="tdwidth220"></td>
					<td>
					</td>
					<td align="left">
					&nbsp;&nbsp;<a href="javascript:changeimage()" class="imglink">看不清楚？ 换个验证码 </a>
					</td>
				</tr>

				<tr>
					<td>
					</td>
					<td colspan="2" height="50">
						<input type="submit" name="button" id="button" value="登录"
							class="loginan" onclick="login()" />
						<input type="button" name="button2" id="button2" value="重置"
							class="loginan" onclick="reset()" />
					</td>
				</tr>

				<tr>
					<td id="message" colspan="3" class="tdcenter hong">
					</td>
				</tr>
			</table>
		</div>
		

	</body>
</html>
