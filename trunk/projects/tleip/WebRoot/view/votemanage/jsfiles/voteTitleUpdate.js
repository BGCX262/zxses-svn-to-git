var count = 1;

$(function(){
	count = $("#itemsCount").val();
});
	
 function add(){
	voteForm.submit();
 }
    
function addItem(){    
  	count++; 
   	//删除前面的选项按钮，只留下最后生成的删除选项按钮
	/*$("#item1").siblings().each(function(){
		alert(this.html());//this.find(".removeButton").remove();
	});*/
	$("#item1").siblings().children(".removeButton").hide();
	var itemHtml = "<div style=\"display:inline;\" id=\"item" + count + "\"><span>选项" + count + "：</span><input type=\"text\" name=\"itemName\" id=\"itemName"+count+"\"/><input id=\"itemButton"+count+"\" class=\"removeButton\" type=\"button\" onclick=\"removeItem('"+count+"')\" value=\"删除选项\"/><br/></div>";
    $("#itemArea").append(itemHtml);
}
     
function removeItem(curCount){
	$("#item" + curCount).remove();
	//显示删除选项倒数第一个删除选项
	$("#item1").siblings().children("#itemButton" + (count-1)).show();
	count--;
}