<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.ait.sy.sy0104.bean.*,com.ait.sy.bean.*,com.ait.i18n.*"%>
<%@ include file="/inc/taglibs.jsp"%>
<%
Biz b=new Biz();
String type=request.getParameter("flag");
String empID=request.getParameter("empID");
String userID=request.getParameter("userID");
boolean empi=false;
boolean useri=false;

AdminBean admin =(AdminBean) request.getSession().getAttribute("admin");
			MessageSource messageSource = new MessageSource("sys",admin.getLocale(), "UTF-8");
			String emp = messageSource.getMessage("alert.sys.fail");
			String user = messageSource.getMessage("alert.sys.fail");
			String flag = messageSource.getMessage("alert.sys.fail");
if(type.equals("add")){
	if(b.checkEmp(empID)&&!b.checkEmp_Admin(empID))
	{
		empi=true;
	emp=messageSource.getMessage("alert.sys.successful");
	}
	if(!b.checkName(userID))
	{
		useri=true;          
	user=messageSource.getMessage("alert.sys.successful"); 
	}
	if(empi==true&&useri==true)
	flag=messageSource.getMessage("alert.sys.successful");
}else{
	if(b.checkEmp(empID))
	{
		empi=false;
	emp=messageSource.getMessage("alert.sys.successful");
	}
	if(!b.checkName(userID,empID))
	{
		useri=true;
		user=messageSource.getMessage("alert.sys.successful");
	}
	
	
	if(empi==true&&useri==true)
	flag=messageSource.getMessage("alert.sys.successful");

}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>
<body>
<form name="form1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr align="center">
    <td><!-- 工号 --> <ait:message messageID="display.mutual.emp_id"></ait:message>  </td>
    <td><font color="#FF0000"><%=emp%></font></td>
  </tr>             
  <tr align="center">
    <td><!-- 用户名称 --> <ait:message messageID="display.mutual.user"></ait:message></td>
    <td><font color="#FF0000"><%=user%></font></td>
  </tr>
</table>
<input type="hidden" name="check" value="<%=flag%>">
</body>
</form>
<script language="javascript">
opener.document.all.check.value=document.form1.check.value;
</script>
</html>
