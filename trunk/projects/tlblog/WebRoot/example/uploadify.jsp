<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/tags.inc"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'uploadify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ include file="/res/common/inc/resources.jsp"%>
	<link rel="stylesheet" type="text/css" href="${basePath }uploadify/uploadify.css" />
	<script type="text/javascript" src="${basePath }uploadify/jquery.uploadify-3.1.js"></script>
  	<script type="text/javascript">
  		$(function() {
		    $('#file_upload').uploadify({
		        'swf'      : $.basePath + 'uploadify/uploadify.swf',
		        'uploader' : $.basePath + 'uploadify.php',
		        'cancelImg' : $.basePath + "uploadify/uploadify-cancel.png",
		        'buttonText' : "选择照片",
		        'method' :  'post',  
	            'buttonClass'   :  'upload_button',  
	            'fileTypeDesc'  :   '图片文件',  
	            'fileTypeExts'  :   '*.gif;*.jpg;*.png;*.bmp',  
	            'multi'             :   false,  
	              
	            'onUploadComplete': function(file){  
	                  
	            },  
	              
	            /** 
	             * 上传成功后触发事件 
	             */  
	             'onUploadSuccess' : function(file, data, response) {  
	                //参数data保存的是上传后的图片的路径  
	                //alert(data);  
	                //$('#photo').css("background-color","#f00");  
	                  
	                var path=PUBLIC+"/Uploads/Photo/"+data;  
	                $('#photo>img').attr("src",path);  
	                $('#photoname_hidden').val(data);  
	            }
		        // Put your options here
		    });
		});
  	</script>
  </head>
  
  <body>
    <input type="file" name="file_upload" id="file_upload" />
  </body>
</html>
