<%@ page contentType="text/html; charset=UTF-8"%>

<%
String empid =(String) request.getAttribute("empid");
String type =  (String) request.getAttribute("type");
String des=null;
if(type.equals("2")){
	des="休假申请";
}else if(type.equals("1")){
	des="加班申请";
}
%>
<html>
<head>
</head>
<body>
<center><font color="red"><%out.println(empid+"  你的"+des+"已经成功提交,请等待批复,谢谢使用");%></font></center>
</body>
</html>