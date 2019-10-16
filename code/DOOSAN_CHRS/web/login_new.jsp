<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="./inc/taglibs.jsp"%>
<%@ include file="./inc/meta.jsp"%>

<head>
<!-- login.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>斗山人事系统</title>
<style type="text/css">
<!--
body,.login_page,form{
	margin:0px;
	padding:0px;
}
html,body {
	width:100%;
	height:100%;
}

a:link{
	text-decoration:none;
	color:white;
}
a:hover{
	text-decoration:none;
	color:white;
	background-color:#76bf48;
}
.login_div {width:885px;height:531px;margin:6% auto;}
.login_div .login_logo {
	height:55px;
	background-image:url(images/index/logo.jpg);
	background-repeat: no-repeat;
	background-position: 12px 0px;
}
.login_div .login_div_bg {
	width:885px;
	height:424px;
	background-image:url(images/index/login_bg.jpg);
	background-repeat: no-repeat;
	padding-top:52px;
}

.login-box{
	margin:0px 0px 0px 541px;
	width:250px;
	height:150px;
	background:white;
	border-radius:5px;
	background:rgba(255, 255, 255, 0.95);
}

.row{
	height:37px;
	line-height:37px;
	text-align:center;
	font-size:12px;
	color:#666666;
	font-family:"Heiti SC","微软雅黑","黑体";
}

.row-input{
	font-size:12px;
	color:#666666;
	height:20px;
	line-height:20px;
	font-family:"Heiti SC","微软雅黑","黑体";
}

.login-button{
	display:block;
	border-bottom-right-radius:3px;
	border-bottom-left-radius:3px;
    width:250px;
	height:34px;
	line-height:34px;
	background-color:#9ed04d;
	text-align:center;
	font-size:14px;
	color:white;
	border:0 none;
	cursor:pointer;
	font-family:"Heiti SC","微软雅黑","黑体";
}
.loginbox-title{
	color:#666666;
	padding-left:20px;
	text-align:left;
	line-height:41px;
	font-size:16px;
	border-bottom:1px solid #C4C4C4;
	font-family:"Heiti SC","微软雅黑","黑体";
}

.login_input_div_input {width:174px;padding-right:7px;;height:22px;float:left;text-align:right;}
.login_input_div_button {width:80px;height:22px;float:left;text-align:left;}
.login_select {width:154px;}
-->
</style>

<script type="text/javascript"> 

window.onload = function(){	
    accountShow();
    document.getElementById("userId").focus();
    if(document.getElementById("userId").value !=""){
    	document.getElementById("userPwd").focus();
    }
    
};

function accountShow() {
    try {
        if (document.cookie != '') {
            var userId = getCookie('userId');
            document.getElementById("userId").value=userId;
        } else {
        	document.getElementById("userId").focus();
        }
    } catch(e) {
    }

}

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null)
        return unescape(arr[2]);
    return '';

}

function createCookie() {
    var loginData = getLoginData();
    var date = new Date();
    date.setTime(date.getTime() + 365 * 24 * 3600 * 1000);
        SetCookie('userId', loginData.userId);
}
function SetCookie(name, value) {
    var Days = 60;   //cookie 将被保存两个月
    var exp = new Date();  //获得当前时间
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);  //换成毫秒
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getLoginData() {
	var userId = document.getElementById("userId").value;
    return {userId:userId};
}

function loginSys(){
	var userId = document.getElementById("userId").value;
	var userPwd = document.getElementById("userPwd").value;
	
	if(userId==""){
		alert("请输入用户名");
		return false;
	}
	
	if(userPwd==""){
		alert("请输入密码");
		return false;
	}

//保存用户名

	var loginData = getLoginData();
        createCookie();
	
	
	var url = "/ajaxControlServlet" ;
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'checkADLoginCmd',
			'userId' : $("userId").value,
			'userPwd' : $("userPwd").value
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   var param = hash.get("param");
		   
		   if(param.message=='NG'){
			   alert("登录信息错误，请重新登录！");
		   }else{
			  // window.location.href='/company.jsp?a='+$("userId").value;
			   var cpnyId = document.getElementById("cpnyId");
				
				if(cpnyId.value == ""){
					alert("无法人权限，请联系管理员！");
					return false;
				}
				var a = document.getElementById("a");
				var b = document.getElementById("b");
				a.value = $("userId").value;
				b.value = cpnyId.value;
				//alert(b.value);
				regist.submit();    
		   }	   	  
		},
	    onError : function(transport){
	    	alert("登录时发生错误，请重新登录！");
	    }
	});
	
	//regist.submit();    
} 
function getEmpDivision()
{	  
	
	    var url = "/ajaxControlServlet" ;
		new Ajax.Request(url, {
			parameters : new Hash({
				'operation' : 'getEmpDivision',
				'loginId' : $("userId").value
			}),
			onSuccess : function(transport) {
				
			   var hash = $H(transport.responseJSON);
			   var empDiffs = hash.get("empDiff");
			   var param = hash.get("param");
			   
		   		var content = "<select id='cpnyId' name='cpnyId'>"
		   		empDiffs.each(
			   		function(empDiff){
			   			var obj = $H(empDiff);
			   			content += "<option value='"+ obj.get("ID") +"'>"+ obj.get("NAME") + "</option>";
			   		}
		   		);
		   		
		   		if(content == "<select id='cpnyId' name='cpnyId'>"){
		   			content += "<option value=''>无法人权限</option>"
		   		}
		   			   			
		   		content += "</select>"
		   		document.getElementById("empDiff").innerHTML=content;
			}
		});
		
		/* var content = "<select id='cpnyId' name='cpnyId'>"
 		content += "<option value='12132'>DICC</option>";
	 	content += "<option value='12332'>DISD</option>";
		content += "</select>"
		document.getElementById("empDiff").innerHTML=content; */
}
</script>

<%
/*
Cookie[] cookies = request.getCookies();
String loName = "";
if(cookies != null && cookies.length > 0){
	for(Cookie c:cookies){
		if(c.getName().equals("coName")){
			loName = c.getValue();
			break;
		}
	}
}

*/

%>

</head>
<body class="login_page">
<form name="regist" action="/controlServlet" method="post">
<input type="hidden" name="operation" value="login"/>
<input type="hidden" id="a" name="a" value="${a}"/>
<input type="hidden" id="b" name="b" value="${b}"/>
<div class="login_div">
	<div class="login_logo"></div>
	<div class="login_div_bg">
		<!-- <form action="" method="get"> -->
			<div class="login-box">
				<div class="loginbox-title">
					<h3></h3>LOGIN</h3>
				</div>
				<div class="row">
					<label>用户名</label>
					<input type="text" name="userId" id="userId"  class="row-input" style="width:150px;" onkeydown="javascript:if(event.keyCode==13){loginSys();}"/>
				</div>
				<div class="row" style="margin:-3px 0 3px 0;">
					<label>密码</label>
					<input type="password" name="userPwd" id="userPwd" class="row-input" onkeydown="javascript:getEmpDivision();" style="margin-left:12px;width:150px;"/>
				</div>
				
				<div class="login_input_div_input">
					<span id="empDiff">
					</span>
				</div>
				<div>
					<a href="javascript:void(0);" class="login-button" onclick="loginSys(); return false;">登录</a>
				</div>    
				
			</div>
		<!--</form>-->
	</div>
</div>
</form>
</body>
</html>
