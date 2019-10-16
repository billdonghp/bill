<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="./inc/taglibs.jsp"%>
<%@ include file="./inc/meta.jsp"%>

<% 
	String loginId = (String)request.getParameter("a");
%>

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
.login_input_div {
	width:261px;
	height:36px;
	background-image:url(images/index/login_input_bg.jpg);
	background-repeat: no-repeat;
	margin:0px 0px 0px 541px;
	padding-top:72px;
}
.login_input_div_input {width:174px;padding-right:7px;;height:22px;float:left;text-align:right;}
.login_input_div_button {width:80px;height:22px;float:left;text-align:left;}
.login_select {width:154px;}
-->
</style>

<script language='javascript' type="text/javascript"> 
function loginSys(){

	var cpnyId = document.getElementById("cpnyId");
	
	if(cpnyId.value == ""){
		alert("无法人权限，请联系管理员！");
		return false;
	}
	
	var b = document.getElementById("b");
	b.value = cpnyId.value;
	regist.submit();    
}
     
function getEmpDivision()
{	  
	
	    var url = "/ajaxControlServlet" ;
		new Ajax.Request(url, {
			parameters : new Hash({
				'operation' : 'getEmpDivision',
				'loginId' : $("a").value
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
function defaultLogin(){
   $("a").value = "<%=loginId%>";
   getEmpDivision();
 }
</script>

</head>
<body class="login_page" onload="defaultLogin();">
<form name="regist" action="/controlServlet" method="post">
<input type="hidden" name="operation" value="login"/>
<input type="hidden" id="a" name="a" value="${a}"/>
<input type="hidden" id="b" name="b" value="${b}"/>
<div class="login_div">
	<div class="login_logo"></div>
	<div class="login_div_bg">
		<!-- <form action="" method="get"> -->
			<div class="login_input_div">
				<div class="login_input_div_input">
					<span id="empDiff">
					</span>
				</div>
				<div class="login_input_div_button">
					<img style="cursor:hand" src="images/index/login_button.jpg" onClick="loginSys();" />				
				</div>
			</div>
		<!--</form>-->
	</div>
</div>
</form>
</body>
</html>
