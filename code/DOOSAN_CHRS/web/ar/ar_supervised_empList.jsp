<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.sql.*,
				java.util.*,
				com.ait.hr.bean.*,
				com.ait.ar.bean.*,
				com.ait.util.*,
				com.ait.hr.dao.*,
				com.ait.ar.dao.*,
				com.ait.ar.dao.implementation.*"  
%>
<jsp:useBean id="supervisorInfoList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="emp_list" class="java.util.ArrayList" scope="request" /> 
<jsp:useBean id="employee" class="com.ait.hr.bean.EmployeeBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>被考勤人员</title>
<script  type="text/javascript">
	var superviedObjects = new Array();
	function Save(){
		innerSuperVisor();
		//document.all("delSupervisor").style.display="";
		document.Em2Form.submit();
		window.close();
	 }
	function supervisorInfoControl(check,deptid,index){
		if(check == false){
			superviedObjects[index] = deptid;
		}
		if(check == true){
			superviedObjects[index] = null;
		}
	 }
	function innerSuperVisor(){
		var str = "";
		for (var i=0;i<superviedObjects.length;i++){
			if (superviedObjects[i]!=null) str += "<input type=\"hidden\" name=\"delEmpId\" value=\""+superviedObjects[i]+"\">";
		}
		document.all("delSupervisor").innerHTML = str;
	}
</script>
</head>

<body>
<%
	String deptId = StringUtil.checkNull(request.getParameter("deptId"));
	HrDAO hrDAO = new HrDAO();
	emp_list =(ArrayList) hrDAO.getEmpByDeptID(deptId,"");
	
	String supervisorId = StringUtil.checkNull(request.getParameter("supervisorId"));
	SupervisorInfoDAO supervisorInfoDAO= new SupervisorInfoDAOImpl();
	supervisorInfoList = (ArrayList)supervisorInfoDAO.getSupervisorInfoList(supervisorId);
	
	List supervisedEmpList = new ArrayList();
	for(int i=0;i<supervisorInfoList.size();i++){
		supervisedEmpList.add(((SupervisorInfo)supervisorInfoList.get(i)).getSupervisedDeptID());
	}	
%>
<form action="/arControlServlet" method="post" name="Em2Form">
	<input type="hidden" name="operation" value="ar_modify">
	<input type="hidden" name="content" value="supervisedEmp">
	<input type="hidden" name="supervisorID" value="<%=request.getParameter("supervisorId")%>">
	<input type="hidden" name="size" value="<%=emp_list.size()%>">
    <input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
	<table width="600" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr align="center">
		  <td width="6%" bgcolor="#F5F5F5">选择</td>
			<td width="21%" height="30" bgcolor="#F5F5F5">姓名</td>
			<td width="19%" bgcolor="#F5F5F5">工号</td>
			<td width="54%" bgcolor="#F5F5F5">部门</td>
		</tr>
		<%
			// show all selected employees
			for(int j=0; j<emp_list.size();j++){
				employee = (EmployeeBean) emp_list.get(j);
		%>		
	  <tr align="center">
		<td><input type="checkbox" name="empid<%=j%>" class="check"  onClick="javaScript:supervisorInfoControl(this.checked,'<%=employee.getEmpID()%>',<%=j%>);"
		 value="<%=employee.getEmpID()%>" <% if(supervisedEmpList.contains(employee.getEmpID())){%>checked<%}%>> </td>
		<td height="30"><%=StringUtil.toUnicode(StringUtil.toISO(employee.getChineseName()),"UTF-8")%>&nbsp;</td>
		<td><%=employee.getEmpID()%>&nbsp;</td>
		<td><%=employee.getDepartment()%>&nbsp;</td>
	  </tr>
		<%}%>
	  <tr align="center">
		<td height="30" colspan="4"><input name="imageField" type="image" src="../images/btn/p_setup.gif" width="63" height="21" border="0" onclick="javascript:Save();"></td>
	  </tr>
	</table>
	<div id="delSupervisor" style="display:none "></div>
</form>
</body>
</html>
