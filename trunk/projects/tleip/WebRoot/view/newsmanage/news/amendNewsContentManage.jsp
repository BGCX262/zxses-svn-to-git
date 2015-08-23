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
		<title>信息发布管理系统</title>
		<link rel="stylesheet" type="text/css" 
			href="<%=basePath%>res/theme/blue/css/css.css"/>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>res/theme/blue/css/old.css"/>
		<script type="text/javascript" language="javascript"
			src="<%=basePath%>tools/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/move.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/common.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/coolwindow.js"></script>
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>res/admin/js/jquery.js"></script>
		
		<script charset="utf-8" src="<%=basePath%>tools/kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath%>tools/kindeditor/lang/zh_CN.js"></script>
		<script>
			var editor;
			KindEditor.ready(function(K) {
			var options = {
			        uploadJson : '<%=basePath%>tools/kindeditor/jsp/upload_json.jsp',
					fileManagerJson : '<%=basePath%>tools/kindeditor/jsp/file_manager_json.jsp',
					allowFileManager : true				
			 };
			editor = K.create('textarea[name="editor_k"]', options);
			});
		</script>
		<!--[if gte IE 7]><!-->
		<link href="<%=basePath%>tools/artdialog/skins/blue.css"
			rel="stylesheet" />
		<!--<![endif]-->
		<!--[if lt IE 7]>
		    <link href="<%=basePath%>tools/artdialog/skins/default.css" rel="stylesheet" />
		<![endif]-->
		<script src="<%=basePath%>tools/artdialog/js/artDialog.js"></script>
		<script src="<%=basePath%>tools/artdialog/js/iframeTools.js"></script>
			
		<script language="javascript" type="text/javascript"> 
			function check(){
				var announceTypeValue = document.getElementById("announceType").value;
				var classId = document.getElementById("classId");
				var yxdm = document.getElementById('yxdm');
				var typeId = document.getElementById('typeId');
				var newsTitle= document.getElementById("newsTitle"+announceTypeValue);
				var imgNewsTitle= document.getElementById("imgNewsTitle"+announceTypeValue);
				var updateNewsContentForm = document.getElementById('updateNewsContentForm');
				var newsKeyword = document.getElementById("newsKeyword");
				if ($.trim(typeId.value).length<=0){
					alert("所属的栏目不能为空!");
					typeId.focus();
					return ;
				}
				if (announceTypeValue == 1){
					if ($.trim(newsTitle.value).length<=0){
						alert("链接标题不能为空!");
						newsTitle.focus();
						return ;
					}
				}else{
					if ($.trim(newsTitle.value).length<=0){
						alert("新闻标题不能为空!");
						newsTitle.focus();
						return ;
					}
				}
				if (announceTypeValue == 2){
					var uploadFileValue = "";
					var len = document.updateNewsContentForm.file_annex_url.options.length;
					if (len != null && len > 0 ){
						for (var i = 0;i<len;i++){
							uploadFileValue = uploadFileValue + document.updateNewsContentForm.file_annex_url.options[i].value ;
						}
					}else{
						alert("附件不能为空！");
						return ;
					}
					updateNewsContentForm.file_annex_url.value = uploadFileValue ;
				}
				var selStr="";
				var str = document.getElementById("str");
				for(var i=0;i<str.options.length;i++){
					if(str.options[i].selected == true){
						selStr+=str.options[i].value+",";
					}
				}
				var checkFlag="";
				var checks=document.getElementsByName("checkFlag");
				for(var i=0;i<checks.length;i++){
					if(checks[i].checked==true){
						checkFlag=checks[i].value;
					}
				}
				var optMark="";
				editor.sync();			
				showloading("<%=basePath%>commons/ajaxload.jsp",90,90); 
				updateNewsContentForm.action = "<%=basePath%>view/newsmanageWebNote.do?method=amendNewsContentManage"+"&optMark="+optMark+"&checkFlag="+checkFlag+"&selStr="+selStr;
				updateNewsContentForm.submit();
	    	}
	    	
	    	function back(){
	    		var yxdm = document.getElementById("yxdm").value;
	    		var classId = document.getElementById("classId").value;
	    		var typeId = document.getElementById('currentTypeId').value;
	    		var parentModuleFlag = document.getElementById('parentModuleFlag').value;

			 	window.location.href = "<%=basePath%>view/newsmanage.do?method=findNewsContentManage&currentPage=1&classId="+classId+"&typeId="+typeId+"&parentModuleFlag="+parentModuleFlag;
			
			 }
			 
			 function checkNumberTemp(objString){
			 	var obj = document.getElementById(objString);
				if (obj.value != ""){
					if ( checkNumber(objString,obj.value)== false ){
						obj.focus();
						return false;
					}
				}else{
					obj.value = 0;
				}
			 	return true;
			 }
			 function selectLink(obj){
			 
			 var value = obj.value;
				if (value == 1){
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='';	
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPage").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='';
					
				}else if (value == 2){
					
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='none';	
					document.getElementById("newsPageUpload").style.display ='';
					document.getElementById("newsPage").style.display ='none';
					document.getElementById("idHttpUrl").style.display ='none';
				}else{
					
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';	
					document.getElementById("idHttpUrl").style.display ='';
				}
			 }
			 function changeInput(mark){
				if ( mark == 0 ){
					document.getElementById("selDiv").style.display='none';
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';
					document.getElementById("newsProperty").style.display ='';	
					document.getElementById("newsStyle").style.display ='';	
					document.getElementById("idHttpUrl").style.display ='';
					document.getElementById("myEditor").style.display ='none';
					document.getElementById("titleSet").innerText ='标题设置';
				}else{
					document.getElementById("selDiv").style.display='none';
					document.getElementById("newsPageUpload").style.display ='none';
					document.getElementById("newsPageNoLink").style.display ='none';
					document.getElementById("newsPageLink").style.display ='none';
					document.getElementById("newsPage").style.display ='';	
					document.getElementById("newsProperty").style.display ='none';	
					document.getElementById("newsStyle").style.display ='none';	
					document.getElementById("myEditor").style.display ='';
					document.getElementById("idHttpUrl").style.display ='';
					document.getElementById("titleSet").innerText ='点击返回标题设置';
				}
				return false;
			}
			function showImgNews(obj){
			 	if ( obj.checked == true ){
			 		document.getElementById("ifImgNewsTr1").style.display ='';
			 	}else{
			 		document.getElementById("ifImgNewsTr1").style.display ='none';
			 	}
			 }
			function showImgNewsLink(obj){
			 	if ( obj.checked == true ){
			 		document.getElementById("ifImgNewsTrLink").style.display ='';
			 	}else{
			 		document.getElementById("ifImgNewsTrLink").style.display ='none';
			 	}
			 } 
			function showDivs()
			{
				if(document.getElementById("selDiv").style.display=='none'){
				
					document.getElementById("selDiv").style.display='block';
				}else{
				
					document.getElementById("selDiv").style.display='none';
				}
			}
			function loaddata()
			{
				var typeID=document.getElementsByName("typeId");
				var announceTypeValue = document.getElementById("announceType").value;
				var sel=document.getElementById("str");
				for(var j=0;j<typeID.length;j++){
					for(var i=1;i<sel.options.length;i++){
						if(''+typeID[j].value==''+sel.options[i].value){  
			          		sel.options[i].selected=true;         
			          	}
		        	} 
		        } 
		         
			}
	       	function removeFileItem(){
				var delSelect = document.updateNewsContentForm.file_annex_url;
				removeItem(delSelect);
			}
			function showArtDialog(){
				var announceTypeValue = document.getElementById("announceType").value;
			(function (config) {
			    config['title'] = '上传文件';
			    config['lock'] = true;
			})(art.dialog.defaults);
				art.dialog.data('announceType', announceTypeValue);
				art.dialog.open('<%=basePath%>commons/upload.jsp',null,false);
			}
				
		</script>
	</head>
	<body onload="loaddata();">
		<input type="hidden" id="currentTypeId" name="currentTypeId" value="${typeId }" />
		<form action="view/newsmanageWebNote.do?method=amendNewsContentManage"
			method="post" name="updateNewsContentForm" id="updateNewsContentForm"
			enctype="multipart/form-data">
			<input type="hidden" name="parentModuleFlag" id="parentModuleFlag"
				value="${parentModuleFlag }" />
			<input type="hidden" name="newsId" value="${newsId}" />
			<input type="hidden" name="newsTemplate" value="1" />
			<table cellspacing="0" cellpadding="0" width="100%" border="0"
				align="center">
				<tr>
					<td valign="top">
						<table cellspacing="0" cellpadding="0" border="0" width="100%"
							align="left" height="190">
							<tr>
								<td width="100%" align="left" valign="top">
									<table class="pathbg">
										<tr>
											<td width="514" height="24">
												新闻发布管理-&gt;
												<span class="lv chuti">修改新闻</span>${showMsg }
											</td>
											<td align="right">
												<input name="button" type="button" class="an" id="button"
													onclick="javascript:check();" value="保存" />
												&nbsp;
												<input name="button2" type="button" class="an" id="button2"
													value="返回" onclick="javascript:back();" />
											</td>
											<td width="30"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="table" cellspacing="0" cellpadding="0"
										width="100%" border="0">
										<tr>
											<td class="td_center">
												<table class="table" cellspacing="0" cellpadding="0"
													width="100%" border="0">

													<tr id="newsProperty">
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>所属栏目：
														</td>
														<td class="td_right" width="50%">
															<input type="hidden" name="classId" id="classId" size=6 maxlength=6
																value="${classId}"/>
															<input type="hidden" name="yxdm" id="yxdm" size=6 maxlength=6
																value="${yxdm}"/>
															<input type="hidden" name="newsId" id="newsId" size=6 maxlength=6
																value="${newsId}"/>
															<input type="hidden" name="optMark" value="${optMark}"/>
															<logic:iterate id="item" name="newsContentManage"
																property="newsItemSmalls">
																<input type="hidden" name="typeId" id="typeId"
																	value="${item.typeId }" />
															</logic:iterate>
															<div
																title="<logic:iterate id="item" name="newsContentManage" property="newsItemSmalls"> ${item.typeName}</logic:iterate>">
																<logic:iterate id="item" name="newsContentManage"
																	property="newsItemSmalls" length="3">
									                         ${item.typeName }&nbsp;&nbsp;</logic:iterate>
																<bean:size id="smaillSize" name="newsContentManage"
																	property="newsItemSmalls" />
																<c:if test="${smaillSize>3}">
									                         ......
									                         </c:if>
																<%--
																<c:if test="${optMark == 'Y'}">  &nbsp;&nbsp;<a
																		href="javascript:showDivs()">发布到其他栏目</a>
																</c:if>
																--%>	
															
															</div>
														</td>
													</tr>
													<%----%>
													<tr id="selDiv" style="display: none;">
														<td class="td_left" width="50%"></td>
														<td class="td_right" width="50%">
															<select multiple="multiple" name="str" id="str"
																style="width: 40%; height: 140px; text-align: center;">
																${buffer}
															</select>
															<font color="orangered">注:按住CTRL或SHIFT 键盘多选</font>
														</td>
													</tr>
													

													<tr id="newsPage">
														<td class="td_left" width="50%">
															<a href="#" onclick="changeInput('0'); " id="titleSet">标题设置&nbsp;&nbsp;</a>
														</td>
														<td class="td_right" width="50%">
															<a href="#" onclick="changeInput('1'); ">&nbsp;&nbsp;输入正文内容</a>
														</td>
													</tr>

													<tr>
														<td class="td_left" width="50%">
															审核标志：
														</td>
														<td class="td_right" width="50%">
															<c:if
																test="${newsContentManage.checkFlag eq '0'||newsContentManage.checkFlag==null}">
																<input type="radio" name="checkFlag" value="1" />
																自动审核
															<input type="radio" name="checkFlag" value="0"
																	checked="checked" />
																手工审核
																</c:if>
															<c:if test="${newsContentManage.checkFlag eq '1'}">
																<input type="radio" name="checkFlag" value="1"
																	checked="checked" />
																自动审核
															<input type="radio" name="checkFlag" value="0" />
																手工审核
																</c:if>
														</td>
													</tr>
													<tr id="newsStyle">
														<td class="td_left" width="50%">
															<font color="#ff0000">* </font>发布方式：
														</td>
														<td class="td_right" width="50%">
															<input type="hidden" name="announceType" size="6" id="announceType"
																maxlength="6" value="${newsContentManage.announceType}"/>
															<c:if test="${newsContentManage.announceType == 0}">非地址链接式</c:if>
															<c:if test="${newsContentManage.announceType == 1}">地址链接式</c:if>
															<c:if test="${newsContentManage.announceType == 2}">上传附件</c:if>
															<font color="#ff0000">${msg } </font>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															发布部门：
														</td>
														<td class="td_right" width="50%">
															<select name="publishDept" >
																<logic:iterate id="department" name="departmentList">
																	<c:choose>
																		<c:when test="${department.deptId==newsContentManage.department.deptId}">
																			<option value="${department.deptId}" selected="selected">
																				${department.deptName}
																			</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${department.deptId}" >
																				${department.deptName}
																			</option>
																		</c:otherwise>
																	</c:choose>
																</logic:iterate>
															</select>
														</td>
													</tr>
													<tr>
														<td class="td_left" width="50%">
															发布时间：
														</td>
														<td class="td_right" width="50%">
															<input class="Wdate" type="text" id="newsDate" name="newsDate"
																value="${newsDate}" readonly="readonly"
																onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
														</td>
													</tr>
												</table>
												<!-- 设置非地址链接式 开始 -->
												<div id="newsPageNoLink">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">
														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle0" id="newsTitle0"
																	value="${newsContentManage.newsTitle}" size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																关键字：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsKeyword" id="newsKeyword"
																	value="${newsContentManage.newsKeyword}" size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																作者：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsWriter"
																	value="${newsContentManage.newsWriter}" />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																来源：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsSource"
																	value="${newsContentManage.newsSource}" size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow0"
																	<c:if test="${newsContentManage.ifTopRow == 1}">checked="checked"</c:if> />
																置顶
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																图片新闻：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifImgNews0" id="ifImgNews0"
																	onclick="showImgNews(this);"
																	<c:if test="${newsContentManage.ifImgNews == 1}"> checked="checked"</c:if> />
																图片新闻
															</td>
														</tr>
															
														<tr id="ifImgNewsTr1" style="display: none">
															<td class="td_left" width="100%" colspan=2>
																<table class="table" cellspacing="0" cellpadding="0"
																	width="100%" border="0">
																	<tr>
																		<td class="td_left" width="50%">
																			调用图片：
																		</td>
																		<td class="td_right" width="50%">
																			<input type="text" name="imgNewsAddress0" id="imgNewsAddress0"
																				value="${newsContentManage.imgNewsAddress}" readonly
																				size=40 />
																			<br/>
																			<a href="#" onclick="showArtDialog()"><font
																				color="#FF0000">点击这里上传图片</font> </a>(注:预览图片宽为120,高为100)
																		</td>
																	</tr>

																	<tr>
																		<td class="td_left" width="50%"></td>
																		<td class="td_right" width="50%">
																			<div align="left">
																				<c:if
																					test="${newsContentManage.imgNewsAddress == null }">
																					<img name=faceImg0 id="faceImg0"
																						src="<%=basePath%>res/admin/img/wu.jpg"
																						width="120" height="100" />
																				</c:if>
																				<c:if
																					test="${newsContentManage.imgNewsAddress != null }">
																					<img name=faceImg0
																						src="<%=basePath%>${newsContentManage.imgNewsAddress}"
																						width="120" height="100" />
																				</c:if>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																			调用文字：
																		</td>
																		<td class="td_right" width="50%">
																			<textarea cols=40 rows=5 id=imgNewsTitle0
																				name="imgNewsTitle0">${newsContentManage.imgNewsTitle}</textarea>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>

													</table>
												</div>
												 <div id="myEditor" style="display: none;">
													
													<textarea id="editor_k" name="editor_k" style="width:100%;height:400px;visibility:hidden;">${newsContentManage.newsContent}</textarea>
															
												</div>
												<!-- 非地址链接式 结束 -->

												<!-- 设置地址链接式 开始 -->
												<div id="newsPageLink">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">
														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle1" id="newsTitle1"
																	value="${newsContentManage.newsTitle}" size=60 />
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																链接地址：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="httpUrl"
																	value="${newsContentManage.httpUrl}" size=60 />
															</td>
														</tr>

														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow1"
																	<c:if test="${newsContentManage.ifTopRow == 1}">checked="checked"</c:if> />
																置顶
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																是否有图片：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifImgNews1" id="ifImgNews1"
																	onclick="showImgNewsLink(this);"
																	<c:if test="${newsContentManage.ifImgNews == 1}">checked="checked"</c:if> />
																图片新闻
															</td>
														</tr>
														<tr id="ifImgNewsTrLink" style="display: none">
															<td class="td_left" width="100%" colspan=2>
																<table class="table" cellspacing="0" cellpadding="0"
																	width="100%" border="0">
																	<tr>
																		<td class="td_left" width="50%">
																			调用图片：
																		</td>
																		<td class="td_right" width="50%">
																			<input type="text" name="imgNewsAddress1" id="imgNewsAddress1"
																				value="${newsContentManage.imgNewsAddress}" readonly
																				size=50 />
																			<br/>
																			<a href="#" onclick="showArtDialog()"><font
																				color="#FF0000">点击这里上传图片</font> </a>(注:预览图片宽为120,高为100)
																		</td>
																	</tr>
																	<tr>
																		<td class="td_left" width="50%">
																		</td>
																		<td class="td_right" width="50%">
																			<div align="left">
																				<c:if
																					test="${newsContentManage.imgNewsAddress == '' }">
																					<img name="faceImg1" id="faceImg1"
																						src="<%=basePath%>res/admin/img/wu.jpg"
																						width="120" height="100"/>
																				</c:if>
																				<c:if
																					test="${newsContentManage.imgNewsAddress != '' }">
																					<img name="faceImg1"
																						src="<%=basePath%>${newsContentManage.imgNewsAddress}"
																						width="120" height="100"/>
																				</c:if>
																			</div>
																		</td>
																	</tr>
																	
																	<tr>
																		<td class="td_left" width="50%">
																			调用文字：
																		</td>
																		<td class="td_right" width="50%">
																			<textarea cols=40 rows=5 id=imgNewsTitle1
																				name="imgNewsTitle1">${newsContentManage.imgNewsTitle}</textarea>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
													<div id="idHttpUrl">
													</div>
												</div>
												<!-- 设置地址链接式 结束 -->

												<!-- 设置上传新 开始 -->
												<div id="newsPageUpload" style="display: none">
													<table class="table" cellspacing="0" cellpadding="0"
														width="100%" border="0">
														<tr>
															<td class="td_left" width="50%">
																<font color="#ff0000">* </font>新闻标题：
															</td>
															<td class="td_right" width="50%">
																<input type="text" name="newsTitle2" id="newsTitle2"
																	value="${newsContentManage.newsTitle}" size=60 />
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																是否置顶：
															</td>
															<td class="td_right" width="50%">
																<input type="checkbox" value="1" name="ifTopRow2"
																	<c:if test="${newsContentManage.ifTopRow == 1}">checked="checked"</c:if> />
																置顶
															</td>
														</tr>
														<tr>
															<td class="td_left" width="50%">
																附件：<br />
															</td>
															<td class="td_right" width="50%">
																<select name="file_annex_url" id="file_annex_url"
																	size="3" multiple style="width: 395px"></select>
																<a href="#" onclick="showArtDialog()">
																<br/>
																<font color="#FF0000">上传附件</font></a>&nbsp;
																<a href="#" onclick="javascript:removeFileItem();"
																	title="删除附件"><font color="#FF0000">删除附件</font></a>(注:附件只允许上传一个)
															</td>
														</tr>
													</table>
												</div>
												<!-- 设置上传 结束 -->

												<script type="text/javascript">
													selectLink(document.getElementById("announceType"));
													showImgNews(document.getElementById("ifImgNews0"));												
													showImgNewsLink(document.getElementById("ifImgNews1"));
												</script>
												<c:if test="${newsContentManage.announceType == 2}">
													<script language="javascript" type="text/javascript"> 
													    var addList = document.updateNewsContentForm.file_annex_url;
														var fileName = "${newsContentManage.annexAddress}";
														if (fileName!= null && fileName != ""){
															var fileNames = fileName.split("/");
															fileName = fileNames[2];
															addItem(fileName,addList);
														}
													</script>
												</c:if>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>