<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource" 
errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.ait.sy.sy0105.bean.*"%>
<%
Biz b=new Biz();
int scNo=new Integer(request.getParameter("screenGrantNo")).intValue();

AdminBean admin = (AdminBean) session.getAttribute("admin");
MessageSource messageSource = new MessageSource("sys",admin.getLocale(), "UTF-8");
String message = messageSource.getMessage("alert.sys.fail"); 
if(!b.checkNo(scNo))
message = messageSource.getMessage("alert.sys.successful");   
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
    <td><!-- 权限组号 -->  
    <ait:message messageID="display.sys.authority.group.edit.group_no"  module="sys"></ait:message>
    </td>
    <td><font color="#FF0000"><%=message%></font></td>
  </tr>
</table>
</body>
<input type="hidden" name="check" value="<%=message%>">
</form>
<script language="javascript">
opener.document.all.check.value=document.form1.check.value;
</script>
</html>
