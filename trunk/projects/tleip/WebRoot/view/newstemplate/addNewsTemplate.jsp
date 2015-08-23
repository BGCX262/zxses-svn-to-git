<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.inc"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息发布管理系统</title>
		<link type="text/css" href="./view/css/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="./view/css/ui.util.css" rel="stylesheet" />
		<script type="text/javascript" src="./view/js/jquery-1.3.2.js">
        </script>
		<script type="text/javascript" src="./view/js/ui.core.js">
        </script>
		<script type="text/javascript" src="./view/js/ui.draggable.js">
        </script>
		<script type="text/javascript" src="./view/js/ui.resizable.js">
        </script>
		<script type="text/javascript" src="./view/js/ui.dialog.js">
        </script>
		<script type="text/javascript" src="./view/js/jquery.bgiframe.js">
        </script>

		<script language="JavaScript" type="text/JavaScript">
			function check(){
				var newsTemplateName = document.getElementById("newsTemplate.templateName");
				
				if(!isEmpty(newsTemplateName.value)){
					newsTemplateForm.submit();
				}else{
					alert("模板名称不能为空!");
					return;
				}
				showloading("<%=path%>/commons/ajaxload.jsp",90,90); 
				window.location = "<%=path%>/view/newsTemplate.do?method=addNewsTemplate";
			
			}
			function isEmpty(str){
				if(str==null||str==""){
					return true;
				}
				for(i=0;i<str.length;i++){
					return (str.charAt(i)==" "||str.charAt(i)=="");
				}
				return false;
			}
			function back(){
				window.location = "<%=path%>/view/newsTemplate.do?method=forwardQuery&currentPage=1";
			}
		</script>
		<script type="text/javascript">
          $(function() {
                $("#dialog").dialog({
                    bgiframe: true,
                    height: 200,
					autoOpen: false,
                    modal: true,
					width:400,
					buttons:{
						"上传":function (){
							//$('#uploadForm').submit(function() { 
						        //是否只能上传一个文件，文件名为传过来的目录名
						        //var unique = window.parent.getMultipleChoice();
						        var form = document.getElementById('uploadForm');
						        form.action = "<%=basePath%>"+form.action;
						        $(this).ajaxSubmit(function() {
					                 alert("Thank you for your comment!");
					             }); 
   							//}); 
						},
						'取消': function() {
							$(this).dialog('close');
						}
					}
                });
			$('#clickBtn').click(function() {
				$('#dialog').dialog('open');
			})
		});
		function upload(){
			//var file = document.getElementById('upload');
			var form = document.getElementById('uploadForm');
			//var fileName = document.getElementById('upload').value;
			//alert("<%=basePath%>"+form.action+"&fileName="+fileName);
			//window.location = "<%=basePath%>"+form.action;
			//form.action = "<%=basePath%>"+form.action;
			form.submit();
		}
        </script>
	</head>

	<body>
		<script type="text/javascript" src="./view/js/jscolor.js"></script>
		<html:form action="view/newsTemplate.do?method=addNewsTemplate"
			method="post">
			<table cellSpacing="0" cellPadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellSpacing="0" cellPadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td background="<%=basePath%>/image/dh_bg.gif" width="100%"
									height="31">
									<table cellSpacing="0" cellPadding="0" border="0" width="100%">
										<tr>
											<td width="10" align="center">
												<img src="<%=basePath%>/image/dian1.gif">
											</td>
											<td width="514" height="24" class="dh1">
												模板管理→
												<font color="#ff6600">模板定义</font>
											</td>
											<td width="692" align="right">
												<img src="<%=basePath%>/image/icon_3.gif" onclick="check();"
													style="cursor: pointer" border="0" alt="确定" />
												<img src="<%=basePath%>/image/icon_5.gif" onclick="back();"
													style="cursor: pointer" border="0" alt="返回" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table cellSpacing="0" cellPadding="0" border="0" width="100%"
										align="center">
										<tr>
											<td>
												<table class="table" cellSpacing="1" cellPadding="0"
													width="100%" border="0">
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>模板名称:&nbsp;
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="newsTemplate.templateName" maxlength=6>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>模板背景图 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<file:fileUploadTag action="servlet/FileUploadServlet">1</file:fileUploadTag>
															<input type='button' id='clickBtn' value='上传' />
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															网页结构 :&nbsp;
														</td>
														<td class="td_right" width="50%">
															<select name="newsTemplate.templateStructure"
																style="width: 150px; text-align: center">
																<option value="1" selected="selected">
																	结构1
																</option>
																<option value="2">
																	结构2
																</option>
																<option value="3">
																	结构3
																</option>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															栏目菜单样式 :
														</td>
														<td class="td_right" width="50%">
															<select name="newsTemplate.templateMenuStyle"
																style="width: 150px; text-align: center">
																<option value="1">
																	样式1
																</option>
																<option value="2">
																	样式2
																</option>
																<option value="3">
																	样式3
																</option>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															网站页面完整图 :
														</td>
														<td class="td_right" width="50%">
															<input type="text" name="newsTemplate.templateIndexImg"
																value="${newsContentManage.imgNewsAddress}" readonly
																size=40>
															<a href="/lcweb/view/newsmanage/news/up/uploadimg_2.html"
																onClick="window.open(this.href,'','width=450,top=120,left=150,height=170,statusbar=no,toolbar=no,resize=no,scrollbars=no');return false"
																title=图片上载><font color="#FF0000">点击这里上传图片</font> </a>(注:图片宽为120,高为100)
															<div align="center" style="display: none" name="imgDiv">
																<img name=faceImg
																	src="${newsContentManage.imgNewsAddress}" width="120"
																	height="100" />
															</div>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															是否选择 :
														</td>
														<td class="td_right" width="50%">
															<input type="radio" name="newsTemplate.ifSelect" value="1"
																checked="checked" />
															是
															<input type="radio" name="newsTemplate.ifSelect" value="0" />
															否
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table class="table" cellSpacing="1" cellPadding="0" width="100%"
				border="0">
				<tr>
					<td class="td_center">
						<b><span>模板文字样式定义</span> </b>
					</td>
				</tr>
				<tr>
					<td class="td_center">
						<table class="table" cellSpacing="1" cellPadding="0" width="50%"
							border="0" align="center">
							<tr>
								<td class="td_left">
									<span>普通文字尺寸:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.ordinaryWordSize" value="5" />
								</td>
								<td class="td_left">
									<span>普通文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.ordinaryWordColor" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>导航栏文字尺寸:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.guidanceWordSzie" />
								</td>
								<td class="td_left">
									<span>导航栏文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.guidanceWordColor" class="color" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>有连接的文字的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.likeWordColor" class="color" readonly />
								</td>
								<td class="td_left">
									<span>鼠标经过的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.mouseAfterColor" class="color" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>已经访问的连接的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.visitLinkColor" class="color" readonly />
								</td>
								<td class="td_left">
									<span>活动连接的颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.activeLinkColor" class="color" readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格边框颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableFrameColor" class="color" readonly />
								</td>
								<td class="td_left">
									<span>表格标题背景颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableBackgroundColor" class="color" />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格标题下划线颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableUnderlineColor" class="color" readonly />
								</td>
								<td class="td_left">
									<span>表格标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableWordColor" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>表格内容背景颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableContentBackgroundColor" class="color"
										readonly />
								</td>
								<td class="td_left">
									<span>表格内容文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.tableContentWordColor" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>内容列表标题文字尺寸大小:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.contentTitleSize" value="12" />
								</td>
								<td class="td_left">
									<span>内容列表标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.contentTitleColor" class="color"
										readonly />
								</td>
							</tr>
							<tr>
								<td class="td_left">
									<span>在线调查标题文字颜色:</span>&nbsp;
								</td>
								<td class="td_right">
									<input type="text" name="templateStyle.onlineTitleColor" class="color"
										readonly />
								</td>
								<td class="td_left">
								</td>
								<td class="td_right">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
