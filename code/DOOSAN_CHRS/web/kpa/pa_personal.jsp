<%@ page contentType="text/html; charset=UTF-8" 
	import="com.ait.hr.bean.*,
			com.ait.hr.entity.*,
			com.ait.hr.dao.*,
			java.util.*,
			com.ait.util.*,
			com.ait.sy.bean.AdminBean,
			com.ait.hr.business.HrServices"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="emp_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="employee" class="com.ait.hr.bean.EmployeeBean" />
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title></title>
<%
	String pamonth=request.getParameter("pamonth");
    if (dept_list.size() == 0){
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		dept_list = (ArrayList)new HrServices().getGrantDept(adminId);
    }
%>
</head>
<body>
<%@ include file="/pa/inc/common_toolbar_n.jsp"%>

<br>

<form action="/hrControlServlet" method="post" name="searchForm" >
	<input type="hidden" name="operation" value="hr_search">
	<input type="hidden" name="destination" value="pa_personal">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="pamonth" value="<%=pamonth%>">
    <table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="middle" class="search_title_01">
				<%@ include file="/hr/engine_department.jsp"%>
				<%@ include file="/hr/engine_searchcontent.jsp"%>
				<input name="submit" type="submit" value="查找" width="52" height="19" align="absmiddle">
			</td>
		</tr>
	</table>
</form>

<Br>

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center">
		<td width="20%" height="30" class="info_title_01">中文姓名</td>
		<td width="19%" class="info_title_01">工号44444444</td>

		<td width="21%" class="info_title_01">中文拼音</td>
		<td width="40%" class="info_title_01">部门</td>
	</tr>
	<%
		for(int i=0; i<emp_list.size();i++){
		employee = (EmployeeBean) emp_list.get(i);
		%>
		<tr align="center">
			<td height="30"  class="info_content_01"><a href='/pa/pa_personal_v.jsp?operation=hr_view&empID=<%= employee.getEmpID() %>&info=basic&menu_code=<%=menu_code%>&pamonth=<%=pamonth%>&deptid=<%=employee.getDeptID()%>'><%=StringUtil.toUnicode(StringUtil.toISO(employee.getChineseName()),"UTF-8")%></a></td>
			<td class="info_content_01"><%=employee.getEmpID()%></td>
			<td class="info_content_01"><%=employee.getChinesePinyin()%>&nbsp;</td>
			<td class="info_content_01"><%=employee.getDepartment()%>&nbsp;</td>
		</tr>
	<%}%>
</table>

</html>
