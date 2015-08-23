<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>满天星文学社注册</title>
<%@ include file="/res/common/inc/resources.jsp"%>
<style type="text/css">
	body{ background:url(${basePath }res/client/css/img/wx_bg.jpg) repeat;}
</style>
<script type="text/javascript" src="${basePath}res/client/js/indexjava.js"></script>
<script type="text/javascript" src="${basePath}tools/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath}client/index/jsfiles/reg.js"></script>
</head>

<body>
<div id="wx_wrap">
<div class="wx_bg">
	<!-- 头部 begin -->
	<jsp:include page="/client/index/head.jsp"/>
	<!-- 头部 end -->
	
	<div id="wx_main">
		<div class="art_title"><span>用户注册</span></div>
		<div class="regform wx_form">
			<form name="userReg" id="userReg" method="post" action="${basePath}client/index.do?method=signupUser"
						onsubmit="javascript:return checkRegSubmit();" enctype="multipart/form-data">
				<p>	
					<span class="pspan1 fl"><span class="dot">*</span>登录名：</span>
					<!-- 记录用户名是否已使用,js验证使用 -->
					<input type="hidden" id="usernameUsed" value="false"/>
					<input type="text" id="reg_username" name="username" class="fl" maxlength="50"/>
					<span id="username_tip" class="pspan2">由英文字母、数字和下划线或邮箱构成</span>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>密码：</span>
					<input style="width: 150px;" type="password" id="reg_password" name="password" class="fl" maxlength="30"/>
					<span class="pspan2">请保证密码不易被猜中（至少6位）</span>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>确认密码：</span>
					<input style="width: 150px;" type="password" id="repass" name="repass" class="fl" maxlength="30"/>
					<span class="pspan2">请确保确认密码与上面的密码一致</span>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>真实姓名：</span>
					<input type="text" id="realname" name="realname" class="fl" maxlength="20"/>
					<div class="clear"></div>
				</p>
				<%--
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>博客名称：</span>
					<input type="text" id="userBlogname" name="userBlogname" class="fl" />
					<span class="pspan2"></span>
					<div class="clear"></div>
				</p>
				--%>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>选择身份：</span>
					<select id="userType" name="userType" style="width: 154px">
						<option value="student">学生</option>
						<option value="teacher">教师</option>
					</select>
					<span class="pspan2">请正确选择身份</span>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>性别：</span>
					<select id="gender" name="gender" style="width: 154px">
						<option value="男">男</option>
						<option value="女">女</option>	
					</select>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>头像：</span>
					<input type="file" id="userHeadpic" name="userHeadpic" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>笔名：</span>
					<input type="text" id="userPenname" name="userPenname" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>博客描述：</span>
					<input type="text" id="userBlogdes" name="userBlogdes" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl"><span class="dot">*</span>邮箱地址：</span>
					<input type="text" id="userEmail" name="userEmail" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">生日：</span>
					<input id="userBirthday" name="userBirthday" type="text" maxlength="15" class="fl Wdate" onclick="WdatePicker()" readonly="readonly"/>
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">QQ号码：</span>
					<input type="text" id="userQq" name="userQq" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">新浪微博地址：</span>
					<input type="text" id="sinaWeibo" name="sinaWeibo" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">腾讯微博地址：</span>
					<input type="text" id="tenWeibo" name="tenWeibo" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">联系电话：</span>
					<input type="text" id="phone" name="phone" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span class="pspan1 fl">来自：</span>
					<input type="text" id="userFrom" name="userFrom" class="fl" />
					<div class="clear"></div>
				</p>
				<p>
					<span>带有星号（<span class="dot">*</span>）的项目为必填项！</span>
				</p>
				<p class="queren2">
					<input type="submit" id="sure" value="确认" />
				</p>
			</form>
		</div>
	</div>
	
	<!-- 底部 begin -->
	<jsp:include page="/client/index/bottom.jsp"/>
	<!-- 底部 end -->
</div>
</div>
	<!--floater onlin-->
	<jsp:include page="/client/index/floater.jsp"/>
</body>
</html>
