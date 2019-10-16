<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="./inc/taglibs.jsp"%>
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<head>
<!-- hint.jsp -->
<SCRIPT type=text/javascript>

window.onload=function(){
	//定时器每秒调用一次fnDate()
	setInterval(function(){
	fnDate();
	ad();
	},1000);
	}

//js 获取当前时间
function fnDate(){
//var oDiv=document.getElementById("div1");
var date=new Date();
var year=date.getFullYear();//当前年份
var month=date.getMonth();//当前月份
var data=date.getDate();//天
var hours=date.getHours();//小时
var minute=date.getMinutes();//分
var second=date.getSeconds();//秒
var time=fnW(data);
//oDiv.innerHTML=time;
document.getElementById("div3").value = time;
}

//补位 当某个字段不是两位数时补0
function fnW(str){
var num;
str>=10?num=str:num="0"+str;
return num;
}

function ad(){
	var s2 ="28";
	var s = document.getElementById("div3").value;
	if(s == s2){
		setTimeout(self.close(),1);
	}
}
/*
function colseseit(){
	setTimeout(self.close(),100000); //毫秒 
}
*/	
</SCRIPT>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
<form name="form1" action="/home.jsp" method="post">
<input type="hidden" id="div3" />
<!-- 
<input type = "button" value = "关闭" align = "left" onclick = "window.close()" />
 -->
<table align = "center">
	<br/><br/>
	<tr><td><th><font color='red'>消息提示框</font></th></td>
	</tr>
</table>
<table align='center' width="60%">
	<tr align="left">
		<th>新添加功能一：</th>
		<td>以热爱祖国为荣，以危害祖国为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能二：</th>
		<td>以服务人民为荣，以背离人民为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能三：</th>
		<td>以崇尚科学为荣，以愚昧无知为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能四：</th>
		<td>以辛勤劳动为荣，以好逸恶劳为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能五：</th>
		<td>以团结互助为荣，以损人利己为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能六：</th>
		<td>以诚实守信为荣，以见利忘义为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能七：</th>
		<td>以遵纪守法为荣，以违法乱纪为耻！</td>
	</tr>
	<tr align="left">
		<th>新添加功能八：</th>
		<td>以艰苦奋斗为荣，以骄奢淫逸为耻！</td>
		
	</tr>
</table>
</form>
</body>
</html>
