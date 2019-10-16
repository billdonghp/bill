<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.hr.entity.*,com.ait.hr.dao.*,java.util.*,com.ait.util.*"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="department" class="com.ait.hr.bean.Department"/>
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="emp_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="employee" class="com.ait.hr.bean.EmployeeBean" />
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%
DeptDAO dept = new DeptDAO();
dept_list =(ArrayList)dept.getAllDept();%>
</head>
<script type="text/javascript">
function searchEmployee(){
	var deptid = document.searchForm.deptid.value;
	var option = document.searchForm.searchopt.value;
	var content = document.searchForm.searchcontent.value;
	url = '/hrControlServlet?operation=hr_search&deptid='+deptid +'&searchopt='+option +'&searchcontent='+content+'&destination=Pa_personal';
	location.href=url;
}
</script>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<p><img src="/hr/images/btn/arrow_2.gif" width="6" height="10" align="absmiddle">
<span class="title1">通过搜索引擎查找员工</span></p>
<form action="/hrControlServlet" method="post" name="searchForm" >
	<input type="hidden" name="operation" value="Pa_personal">
	<input type="hidden" name="destination" value="">
        <table width="100%" height="42" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td width="91" valign="middle"  class="search_title_01">

			<select name="deptid" onChange="javascript:searchEmployee();">
				<option>请选择部门</option>
					<%
					String deptID = request.getParameter("deptid");
					for ( int i = 0 ; i < dept_list.size() ; i++ )
					{
						department = (Department) dept_list.get(i);
					%>
				<option value="<%=department.getDeptID()%>" <%if ((department.getDeptID()).equals(deptID)){%>selected<%}%>>

                                  <%
                                  int level=department.getDeptLevel();
                                  if(level==0){
                                    out.print(department.getDeptName());
                                  }if(level==1){
                                    out.print("&nbsp;&nbsp;&nbsp;"+department.getDeptName());
                                  }if(level==2){
                                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+department.getDeptName());
                                  }if(level==3){
                                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+department.getDeptName());
                                  }
                                  %>
                                </option>
					<%}%>
			</select></td>
			<% String searchopt = request.getParameter("searchopt");
			if(searchopt == null)
			searchopt = "";
			String searchcontent = StringUtil.toCN(request.getParameter("searchcontent"));
			if(searchcontent == null)
			searchcontent = "";
			%>
         <td width="115" valign="middle"  class="info_title_01">
			<select name="searchopt">
				<option value="">请选择搜索条件</option>
				<option value="empID" <%if (searchopt.equals("empID")){%>selected<%}%> >按工号搜索</option>
				<option value="ChineseName" <%if (searchopt.equals("ChineseName")){%>selected<%}%>>按姓名搜索</option>
			</select></td>
                        <td width="144" valign="middle"  class="info_title_01"><span class="info_content_01">
			<input name="searchcontent" type="text" class="content" id="searchcontent" lang="cs" value="<%if (searchopt.equals("")){%>请输入<%}else{%><%=searchcontent%><%}%>" onMouseOver="if (this.value =='请输入'){this.value='';}">
			</span></td>
                        <td width="622" valign="middle"  class="info_title_01"><span class="info_content_01">
                        <input name="submit" type="submit" value="查找" img src="/hr/images/btn/search.gif" width="52" height="19" align="absmiddle">
	 </span></td>
</table>
</form>
<%if(emp_list.size() > 0){ %>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center">
		<td width="20%" height="30" bgcolor="#F5F5F5">中文姓名</td>
		<td width="19%" bgcolor="#F5F5F5">工号</td>

		<td width="21%" bgcolor="#F5F5F5">中文拼音</td>
		<td width="40%" bgcolor="#F5F5F5">部门</td>
	</tr>

	<%
		// show all employees
		for(int i=0; i<emp_list.size();i++){
		employee = (EmployeeBean) emp_list.get(i);
		%>
		<tr align="center">
			<td height="30"  class="info_content_01"><a href='/hr/hrControlServlet?operation=hr_view&empID=<%= employee.getEmpID() %>&info=basic&menu_code=hr0101'><%=employee.getChineseName()%></a></td>
			<td class="info_content_01"><%=employee.getEmpID()%></td>

			<td class="info_content_01"><%=employee.getChinesePinyin()%>&nbsp;</td>
			<td class="info_content_01"><%=employee.getDepartment()%>&nbsp;</td>
		</tr>
	<%}%>
</table>
<p>
  <%}%>
</p>
<p>&nbsp;</p>

</html>
