<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/inc/include.jsp"%>

<ul class="for_form">
	<li>用户名：<input id="userAccount" name="userAccount" type="text" value="" maxlength="20"/></li>
	<li><span>密码</span>：<input id="password" name="password" type="password" value="" maxlength="20"/></li>
	<li>验证码：<input id="checkCode" name="checkCode" type="text" value="" maxlength="20"/>
		<span><img id="codeImage" onclick="changeimage(this);"
					src="${basePath}codeImageAction.do?frontUserCode=yes"
					style="vertical-align:bottom;padding-bottom: 4px;" title="看不清，点击换个验证码" id="codeImage" />
		</span>
	</li>
	<%--<li class="check"><img src="${basePath}front/images/yzm.jpg" width="81px" height="25px" /><a href="" class="next">看不清，换一张</a></li>--%>
	<li style="padding: 5px 0px 5px 0px">
		<input id="login" onclick="loginCheck();" type="button" class="sure" value="登 陆" />
		<input type="reset" class="sure" onclick="reset()" value="清 除" />
	</li>
	<li>
		<a href="${basePath}client/index/content/login_reg/userRegInf.jsp">用户注册</a><a href="${basePath}client/index/content/login_reg/findPassword.jsp">忘记密码</a>
	</li>
</ul>
<div class="user_reg">
	<input type="hidden" id="userType" name="userType" value="teacher"/>
	<div class="user_reg_tea"><a id="teacherType" href="javascript:changeUserType('teacher',this);" class="user_hover"><span>教师登陆</span></a></div>
	<div class="user_reg_stu"><a id="studentType" href="javascript:changeUserType('student',this);"><span>学生登陆</span></a></div>
	<div class="user_reg_par"><a id="parentType" href="javascript:changeUserType('parent',this);"><span>家长登陆</span></a></div>
	<div class="user_reg_oth"><a id="normalType" href="javascript:changeUserType('normal',this);"><span>其他登陆</span></a></div>
</div>