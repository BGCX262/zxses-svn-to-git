<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>图片上传</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>commons/upload.css"/>
		<script src="<%=basePath%>tools/artdialog/js/artDialog.js"></script>
		<script src="<%=basePath%>tools/artdialog/js/iframeTools.js"></script>
		<script language="javascript" type="text/javascript"> 
		function load(){
		if (art.dialog.data('announceType')) {
			document.getElementById('announceType').value = art.dialog.data('announceType');// 获取由主页面传递过来的数据
		};
		}
		</script>
	</head>
	<body bgcolor="#ffffff" onload="load();">
		<div align="center">
			<form method="post" name="frm1" enctype="multipart/form-data"
				action="<%=basePath%>servlet/FileUploader">
				<input type="hidden"  name="announceType" id="announceType" />
				<div align="center">
					<table width="400" border="1" height="100" cellpadding="0"
						cellspacing="0" bordercolor="999999">

						<tr bordercolor="#FFFFFF" bgcolor="#FFF4E6">
							<td height="31" colspan="2">
								<font color="#666666">
									&nbsp;&nbsp;&nbsp;&nbsp;请点击“浏览”按钮，选择要上传的文件。</font>
							</td>
						</tr>
						<tr bordercolor="#FFFFFF" bgcolor="#FFF4E6">
							<td width="153" height="50">
								<font color="#000000">文件名称：</font>
							</td>
							<td width="263">
								<input type=file size=20 name="fname" class=input
									style="background-color: #FFF4E6;"/>
								<input type="hidden" size=20 name="dname" value="OK"/>
							</td>
						</tr>
						<tr bordercolor="#FFFFFF" bgcolor="#FFF4E6">
							<td height="20" colspan="2">
								<div align="center">
									<input class=input type="submit" name="btSubmit" value="确 定"
										style="background-color: #FFF4E6;" />&nbsp;&nbsp;
									<input class=input type="button" name="btSubmit" value="取 消"
										style="background-color: #FFF4E6;" onclick="art.dialog.close();"/>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<div align="center"></div>
	</body>
</html>
