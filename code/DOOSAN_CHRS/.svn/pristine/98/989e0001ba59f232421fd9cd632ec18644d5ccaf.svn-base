<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.etn.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*"%>
<%@ page import="com.ait.utils.Deptree,com.ait.util.StringUtil"%>
<html>
<head>
<title>Intranet WSG v.2.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/Style.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
<!--
function send()
{
	form = document.etn_dept_tree;
	var str="";
	if (form.checkspace.length)
	{
		for(i=0;i<form.checkspace.length;i++){
			if (form.checkspace[i].checked) {
				str+=" &nbsp;<input name='deptid'value='"+form.deptid[i].value+"'type='hidden'>"+form.deptname[i].value+",";
			}
		}
	}
	else{
		if (form.checkspace.checked) {
		    str+=" &nbsp;<input name='deptid'value='"+form.deptid.value+"'type='hidden'>"+form.deptname.value+",";
		}
	}
	opener.document.all.deptidarea.innerHTML=str.substring(0,str.length-1);
	self.close();
}
//-->
</SCRIPT>
<style type="text/css">
<!--
body {
	margin-left: 0px;
}
-->
</style></head>
<%
  if (request.getParameter("errorMsg")!=null && request.getParameter("errorMsg").trim().length()!=0) {
%>
<table border="0">
  <tr>
    <td>
        <%=StringUtil.toCN(request.getParameter("errorMsg"))%>
    </td>
  </tr>
</table>
<%
  }
Deptree dept = new Deptree();
     Vector dept_vector = dept.getDept();
     for(int i=0; i<dept_vector.size(); i++){
         dept = (Deptree)dept_vector.elementAt(i);
         if(dept.getDeptLevel().equals("0")){
  %>
  <table border="0">
   <tr>
    <td width="30">&nbsp;</td>
    <td><img src="/images/left_menubullet_main_d.gif"></td>
    <td><font color="ff0000"><%=dept.getDeptName()%></font>(<%=dept.getDeptId()%>)</td>
<form action="deptTrans.jsp" method="POST" name="deptTrans">
    <input type="hidden" name="deptID" value="<%=dept.getDeptId()%>"/>
    <input type="hidden" name="deptLevel" value="<%=dept.getDeptLevel()%>"/>
	<input type="hidden" name="cpnyid" value="<%=dept.getCpnyId()%>"/>
    <td>新增编号:<input type="text" name="newID" size="10" maxlength="10"/></td>
    <td>部门名称:<input type="text" name="deptName"/></td>
    <td>
        <input type="submit" name="create" value="添加"/>
        <input type="submit" name="update" value="修改"/>
    </td>
</form>
   </tr>
  </table>
  <%}else if(dept.getDeptLevel().equals("1")){%>
  <table border="0">
  <tr>
    <td width="30">&nbsp;</td>
    <td><img src="/images/left_menubullet_sub_node.gif"><img src="/images/left_menubullet_main_d.gif"></td>
    <td height="10"><font color="ff0000"><%=dept.getDeptName()%></font>(<%=dept.getDeptId()%>)</td>
<form action="deptTrans.jsp" method="POST" name="deptTrans">
    <input type="hidden" name="deptID" value="<%=dept.getDeptId()%>"/>
    <input type="hidden" name="deptLevel" value="<%=dept.getDeptLevel()%>"/>
	<input type="hidden" name="cpnyid" value="<%=dept.getCpnyId()%>"/>
    <td>新增编号:<input type="text" name="newID" size="10" maxlength="10"/></td>
    <td>部门名称:<input type="text" name="deptName"/></td>
    <td>
        <input type="submit" name="create" value="添加"/>
        <input type="submit" name="update" value="修改"/>
        <input type="submit" name="delete" value="删除"/>
    </td>
</form>
  </tr>
  </table>
  <%}else if(dept.getDeptLevel().equals("2")){%>
  <table border="0">
  <tr>
    <td width="30">&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;</td>
    <td><img src="/images/left_menubullet_sub_node.gif"><img src="/images/left_menubullet_main_d.gif"></td>
    <td height="10"><font color="ff0000"><%=dept.getDeptName()%></font>(<%=dept.getDeptId()%>)</td>
<form action="deptTrans.jsp" method="POST" name="deptTrans">
    <input type="hidden" name="deptID" value="<%=dept.getDeptId()%>"/>
    <input type="hidden" name="deptLevel" value="<%=dept.getDeptLevel()%>"/>
	<input type="hidden" name="cpnyid" value="<%=dept.getCpnyId()%>"/>
    <td>新增编号:<input type="text" name="newID" size="10" maxlength="10"/></td>
    <td>部门名称:<input type="text" name="deptName"/></td>
    <td>
        <input type="submit" name="create" value="添加"/>
        <input type="submit" name="update" value="修改"/>
        <input type="submit" name="delete" value="删除"/>
    </td>
</form>
  </tr>
  </table>
   <%}else if(dept.getDeptLevel().equals("3")){%>
  <table border="0">
  <tr>
    <td width="30">&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><img src="/images/left_menubullet_sub_node.gif"><img src="/images/left_menubullet_main_d.gif"></td>
    <td height="10"><font color="ff0000"><%=dept.getDeptName()%></font>(<%=dept.getDeptId()%>)</td>
<form action="deptTrans.jsp" method="POST" name="deptTrans">
    <input type="hidden" name="deptID" value="<%=dept.getDeptId()%>"/>
    <input type="hidden" name="deptLevel" value="<%=dept.getDeptLevel()%>"/>
	<input type="hidden" name="cpnyid" value="<%=dept.getCpnyId()%>"/>
    <td>新增编号:<input type="text" name="newID" size="10" maxlength="10"/></td>
    <td>部门名称:<input type="text" name="deptName"/></td>
    <td>
        <input type="submit" name="create" value="添加"/>
        <input type="submit" name="update" value="修改"/>
        <input type="submit" name="delete" value="删除"/>
    </td>
</form>
  </tr>
  </table>
  <%}else if(dept.getDeptLevel().equals("4")){%>
 <table border="0">
  <tr>
    <td width="30">&nbsp;</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><img src="/images/left_menubullet_sub_node.gif"><img src="/images/left_menubullet_main_d.gif"></td>
    <td height="10"><font color="ff0000"><%=dept.getDeptName()%></font>(<%=dept.getDeptId()%>)</td>
<form action="deptTrans.jsp" method="POST" name="deptTrans">
    <input type="hidden" name="deptID" value="<%=dept.getDeptId()%>"/>
	<input type="hidden" name="cpnyid" value="<%=dept.getCpnyId()%>"/>
    <td>部门名称:<input type="text" name="deptName"/></td>
    <td>
        <input type="submit" name="update" value="修改"/>
        <input type="submit" name="delete" value="删除"/>
    </td>
</form>
  </tr>
  <%}}%>
</table>