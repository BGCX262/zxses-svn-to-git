<%@ page language="java" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="foot">
	<div class="foot_bot">
		<div class="foot_inner">
			<div class="foot_logo fl">
				<img src="<%=basePath%>res/client/css/img/foot-logo.gif" />
			</div>
			<div class="foot_cont fl">
				<p>
					<a style="cursor: pointer;"
						onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.sztlxx.com');">设为首页</a>|
					<a  style="cursor: pointer;"
						onClick="this.style.behavior='url(#default#homepage)';window.external.addFavorite('http://www.sztlxx.com','深圳市南山区塘朗小学');">加入收藏</a>|
					<a href="mailto:tlx-lsw@163.com">联系站长</a>|
					<a href="<%=basePath%>client/itembig1000015.html">友情链接</a>|
					<a href="<%=basePath%>client/itembig1000001.html">关于我们</a>
				</p>
				<p>
					<a href="http://www.sztlxx.com">深圳市南山区塘朗小学</a> 版权所有Copyright &copy; 2002-2012 联系电话：0755-86007697
					QQ:324651(黑马）
				</p>
				<p>
					<a href="http://www.miibeian.gov.cn/">备案序号:粤ICP备05084369号</a>
					<a href="http://www.szzhfx.com">本站技术支持：深圳智翔信息技术有限公司</a>
					<img src="<%=basePath%>res/client/css/img/foot-icon.gif" />
				</p>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
