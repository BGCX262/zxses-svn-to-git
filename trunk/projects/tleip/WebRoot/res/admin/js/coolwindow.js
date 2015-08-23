document.write("<iframe id=\"iframe1\" style=\"position:absolute; left:0; top:0px; width:0px; height:0px; z-index:101; background-color: #999999; layer-background-color: #999999; border: 1px none #000000;filter: Alpha(Opacity=50, FinishOpacity=50);\" frameborder=\"0\"></iframe>");
document.write("<iframe id=\"iframe2\" style=\"position:absolute; left:0; top:0px; width:0px; height:0px; z-index:103; background-color: #999999; layer-background-color: #999999;border:2px solid #33AFF5;overflow:visible\" scr=\"\" frameborder=\"0\" scrolling=\"no\" name=\"coolframe\"></iframe>");
var parameter="";
var multiplechoice=false;
var realpath="";
 /*
  *url:打开地址
  *width:窗口宽度
  *height：窗口高度
  *param: 传递过来页面的元素，用于窗口返回值赋值到元素
  *mchoice：ture，false 多选或单选
  *
  */
  
 function showloading(url,width,height){
	document.getElementById("iframe1").style.width=window.screen.width;
	document.getElementById("iframe1").style.height=window.screen.height;
	document.getElementById("iframe1").style.top=document.body.scrollTop;
	document.getElementById("iframe2").style.width=width;
	document.getElementById("iframe2").style.height=height;
	document.getElementById("iframe2").style.left=(document.body.clientWidth-width)/2;
	document.getElementById("iframe2").style.border="0px";
    document.getElementById("iframe2").style.top=document.body.scrollTop+200;
	document.getElementById("iframe2").src=url;
	document.body.scroll="no";	
	 
	 
} 
function showwindow(url,width,height,param,mchoice,rpath){
    parameter = param;
    multiplechoice=mchoice;
    realpath=rpath;
	document.getElementById("iframe1").style.width=window.screen.width;
	document.getElementById("iframe1").style.height=window.screen.height;
	document.getElementById("iframe1").style.top=document.body.scrollTop;
	document.getElementById("iframe2").style.width=width;
	document.getElementById("iframe2").style.height=height;
	document.getElementById("iframe2").style.left=(document.body.clientWidth-width)/2;
	document.getElementById("iframe2").style.top=document.body.scrollTop+150;
	document.getElementById("iframe2").src=url;
	document.body.scroll="no";	
	 
	 
}

function showwindow_top(url,width,height,param,mchoice,rpath){
    parameter = param;
    multiplechoice=mchoice;
    realpath=rpath;
	document.getElementById("iframe1").style.width=window.screen.width;
	document.getElementById("iframe1").style.height=window.screen.height;
	document.getElementById("iframe1").style.top=document.body.scrollTop;
	document.getElementById("iframe2").style.width=width;
	document.getElementById("iframe2").style.height=height;
	document.getElementById("iframe2").style.left=(document.body.clientWidth-width)/2;
	document.getElementById("iframe2").style.top=document.body.scrollTop+50;
	document.getElementById("iframe2").src=url;
	document.body.scroll="no";	
	 
	 
}

function hidewindow(){
	document.getElementById("iframe1").style.width=0;
	document.getElementById("iframe1").style.height=0;
	document.getElementById("iframe1").style.top=-10;
	document.getElementById("iframe1").style.left=-10;
	document.getElementById("iframe2").style.width=0;
	document.getElementById("iframe2").style.height=0;
	document.getElementById("iframe2").style.top=-10;
	document.getElementById("iframe2").style.left=-10;
	document.body.scroll="auto";	 
}
 
function hidewindowreload(){
	hidewindow();
	document.location.reload();
}
function returnvalue(value){
  document.getElementsByName(parameter)[0].value=value;
}

function getframename(){
	return document.getElementById("iframe2").name;
}

function getMultipleChoice(){
	return multiplechoice;
}


function cleardata(element){
  document.getElementsByName(element)[0].value="";
   }
   
function getParameter(){
	return parameter;
}   

function getRealpath(){
	return realpath;
} 
   
