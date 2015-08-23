<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css"
			href="${basePath }res/client/css/css.css" />
		<title>发表文章</title>
		<%@ include file="/res/common/inc/resources.jsp"%>
		<script charset="utf-8" src="${basePath }tools/kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="${basePath }tools/kindeditor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${basePath}client/blogarticle/jsfiles/blogarticleAdd.js"></script>
	</head>

	<body bgcolor="#e5e8e7">
		<div class="blogout">
			<div class="bloginner">
				<!-- 头部 begin -->
				<jsp:include page="/client/blog/head.jsp" />
				<!-- 头部 end -->

				<div class="bl_main">
					<!-- 左边 begin -->
					<jsp:include page="/client/blog/left.jsp" />
					<!-- 左边 end -->

					<!-- 右边文章列表 begin -->
					<div class="bl_right fr">
						<div class="bl_r_title">
							<center>
								<h1>
									发表文章
								</h1>
							</center>
						</div>
						<div class="bl_r_cont">
							<br>
							<!-- 修改对应的aciton路径 -->
							<html:form action="blog/article.do?method=add" method="post" onsubmit="return checkForm();">
							<table width="100%" cellspacing="0" cellpadding="2" border="0">
								<tbody>
									<tr>
										<td width="72" valign="top" height="24" align="right">
											<span style="font-weight: bold;">标题：</span>
										</td>
										<td align="left">
											<input type="text" value="" maxlength="50" size="60"
												id="articleTitle" name="articleTitle" />
										</td>
									</tr>
									<tr>
										<td valign="top" height="24" align="right">
											<span style="font-weight: bold;">参数：</span>
										</td>
										<td width="517" align="left">
											<select name="publicStatus">
												<option value="1">
													公开日志
												</option>
												<option value="0">
													隐藏日志
												</option>
											</select>
											<label for="label2">
												<input type="checkbox" value="1" id="commentStatus" name="commentStatus">
													禁止评论 
											</label>
										</td>
									</tr>
									<tr>
										<td valign="top" height="24" align="right">
											<strong>所属分类：</strong>
										</td>
										<td align="left">
											<select id="articleTypeId" name="articleTypeId">
												<option selected="selected" value="0">
													==请选择所属分类==
												</option>
												<c:forEach items="${stuArticleTypes}" var="stuArticleType">
													<option value="${stuArticleType.id }">
														${stuArticleType.typeName }
													</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td valign="top" align="right" colspan="2">
											<%--<textarea rows="20" cols="70" id="articleContent" name="articleContent">fdfd</textarea>
											--%>
											<textarea id="editor_id" name="articleContent" style="width:690px;height:400px;"></textarea>
										</td>
									</tr>
									<tr>
										<td valign="top" style="padding-left: 20px;" align="left" colspan="2">
										<%--
											您当前输入了 <span class="word_count1">0</span> 个文字。（字数统计包含HTML代码。）
											--%>
											<font color="red">发表文章内容最少要达100个文字。</font>
											<br />您当前输入了 <span class="word_count2">0</span> 个文字。（字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字。）
											<br/>
											<br/>
										</td>
									</tr>
									<tr>
										<td valign="top" nowrap="" align="right">
											<span style="font-weight: bold;">添加附件：</span>
										</td>
										<td align="left" colspan="2">
											<div id="houseMaps_wrap" class="MultiFile-wrap">
												<input type="file"
													style="font-size: 12px; border-width: 1px;" size="38"
													id="houseMaps" name="upfiles"
													class="attachmentBody MultiFile-applied">
												<div id="houseMaps_wrap_list" class="MultiFile-list"></div>
											</div>
											您还可以上传13个附件。
										</td>
									</tr>
									<tr>
										<td align="center" id="submits" colspan="3">
											<input type="submit" id="postButton" accesskey="S"
												value="发表文章" class="userbutton" name="SaveArticle">
											<%--<input type="submit"
												onclick="document.getElementById('isDraft').value='1'"
												accesskey="D" value="保存为草稿" class="userbutton"
												name="SaveDraft">
											--%><input type="button" onclick="history.go(-1)" accesskey="Q"
												value="返回" class="userbutton" name="ReturnButton">
											<div class="jive-description" id="autosave"></div>
										</td>
									</tr>
									<%--<tr>
										<td align="right" colspan="3">
											友情提示:保存草稿后，日志不会在日志列表中出现。只有再次编辑，
											<b>取消草稿</b>后才显示出来。
										</td>
									</tr>
								--%></tbody>
							</table>
							</html:form>
						</div>
					</div>
					<!-- 右边文章列表 end -->
					<div class="clear"></div>
				</div>

				<!-- 底部 begin -->
				<jsp:include page="/client/blog/bottom.jsp" />
				<!-- 底部 end -->
			</div>
		</div>
	</body>
</html>