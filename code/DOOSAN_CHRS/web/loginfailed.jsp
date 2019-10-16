<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="./inc/taglibs.jsp"%>
	<%@ page import="java.util.Locale"%>
<html>
  <head>
    <title>登陆失败</title>
  </head>
  
  <body> 
  <%
  	String msg1 = "用户名密码错!请重试!"; 
	Locale locale = request.getLocale(); 
	if(!locale.getLanguage().equals("zh")){
		msg1 = "Invalid ID or password. Please try again.";
	}
  %>
	<script language="JavaScript">
		//用户名密码错!请重试!
		var msg = ''
		msg = '<%=msg1%>'
  	    alert(msg);   
		location.href='/index.jsp';
	</script>
  </body>
</html>
