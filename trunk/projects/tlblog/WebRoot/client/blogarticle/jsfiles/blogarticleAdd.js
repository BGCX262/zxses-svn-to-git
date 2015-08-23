var editor;
KindEditor.ready(function(K) {
	var options = {
	        uploadJson : '${basePath}tools/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}tools/kindeditor/jsp/file_manager_json.jsp',
			items : [
				        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
				        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
				        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				        'anchor', 'link', 'unlink', '|', 'about'
					],
			allowFileManager : true,
			resizeType : 1,
			afterChange : function() {
				//K('.word_count1').html(this.count());
				K('.word_count2').html(this.count('text'));
			}
			
	};
	editor = K.create('#editor_id', options);
});

function checkForm(){
	var articleTitle = $("#articleTitle").val();
	var articleTypeId = $("#articleTypeId").val();
	if($.trim(articleTitle) == ""){
		$.alert("请输入文章标题!");
		return false;
	}
	if($.trim(articleTypeId) == 0){
		$.alert("请选择文章类型!");
		return false;
	}
	var textSize = editor.count("text");
	if(textSize < 100){
		$.alert("发表文章内容最少要达100个文字!");
		return false;
	}
	return true;
}