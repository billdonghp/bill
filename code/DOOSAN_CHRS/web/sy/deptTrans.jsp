<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.sy.DeptAdmin,
		com.ait.util.StringUtil"%>
<html>
<head>
<title>
deptTrans
</title>
</head>
<body bgcolor="#ffffff">
<%
String deptID = "";
String newID = "";
String deptName = "";
String cpnyid = "";
String errorMsg = "";
int deptLevel = 100;
if (request.getParameter("deptID")!=null && request.getParameter("deptID").trim().length()!=0)
  deptID = request.getParameter("deptID");
if (request.getParameter("newID")!=null && request.getParameter("newID").trim().length()!=0)
  newID = request.getParameter("newID");
if (request.getParameter("deptLevel")!=null && request.getParameter("deptLevel").trim().length()!=0)
  deptLevel = new Integer(request.getParameter("deptLevel")).intValue();
if (request.getParameter("deptName")!=null && request.getParameter("deptName").trim().length()!=0)
  deptName = request.getParameter("deptName");
  if (request.getParameter("cpnyid")!=null && request.getParameter("cpnyid").trim().length()!=0)
  cpnyid = request.getParameter("cpnyid");
if (request.getParameter("create")!=null) {
    DeptAdmin dept = new DeptAdmin();
    dept.setDeptId(newID);
    dept.setDeptName(StringUtil.toCN(deptName));
    dept.setParentDeptId(deptID);
    dept.setDeptLevel(deptLevel+1);
	dept.setCpnyId(cpnyid);
    dept.createDept();
}
if (request.getParameter("update")!=null) {
    DeptAdmin dept = new DeptAdmin();
    dept.setDeptId(deptID);
    dept.setDeptName(StringUtil.toCN(deptName));
    dept.modifyDept();
}
if (request.getParameter("delete")!=null) {
    if (DeptAdmin.checkDeptEmp(deptID) && DeptAdmin.checkSubDept(deptID)) {
      DeptAdmin dept = new DeptAdmin();
      dept.setDeptId(deptID);
      dept.removeDept();
    } else {
      errorMsg = "�ㄩ����涓�����宸ユ�瀛�����涓��浠ュ���;
    }
}
%>
<jsp:forward page="deptAdmin.jsp">
    <jsp:param name="errorMsg" value="<%=StringUtil.toISO(errorMsg)%>"/>
</jsp:forward>
</body>
</html>

