<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.hr.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date"%>
<%@ page import="com.ait.hr.bean.Department"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="hr" scope="page" class="com.ait.hr.business.HrServices">
</jsp:useBean>
<%
HttpSession session1 = request.getSession(false);
AdminBean admin = (AdminBean) session1.getAttribute("admin");
String adminID  = admin.getAdminID();
List dept_list = hr.getGrantDept(adminID);
Department de=null;
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>

<body>
<form name="form1" method="post" action="">
<table width="100%" height="53" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
     <tr align="center">
    <td height="27" colspan="2" align="left" bgcolor="#F5F5F5" v> 被考勤部门:</th>
	</tr>
<%
	int level= -1;
	for (int i=0; i<dept_list.size();i++) {
		de = (Department) dept_list.get(i);
		level = de.getDeptLevel();
%>
  <tr>
    <td >
		<% for(int j=0;j<level;j++){ %> &nbsp;&nbsp;&nbsp;&nbsp;<%}%>
		<a href="sy_dd.jsp?menu_code=<%=request.getParameter("menu_code")%>&deptid=<%=de.getDeptID()%>"><%=de.getDeptName()%></a>
	</td>
  </tr>
<%}%>
</table>
</form>

</body>
</html>
