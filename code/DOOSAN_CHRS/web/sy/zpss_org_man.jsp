<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.ait.sy.sy0102.bean.*"%>
<%@ page import="com.ait.sy.bean.*"%>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<%@ include file="/inc/import.jsp"%>
<%
		Ent Ent = new Ent();
		String x = request.getParameter("strPage");
		String y= request.getParameter("bigpage");
        String DeptID= request.getParameter("DeptID");
		PageControl pc=new PageControl(300,10);
		int bigpage=pc.getTmpBig(y);
		int strPage=pc.getTmpSmall(x,bigpage);
		pc.setRowCount("HR_EMPLOYEE where DEPTID IN (SELECT DEPTID FROM HR_DEPARTMENT CONNECT BY PRIOR DEPTID=PARENT_DEPT_ID START WITH PARENT_DEPT_ID='"+DeptID+"' ) ");
		pc.setintPage(strPage,bigpage);
		vlist = Biz.List_Emp(DeptID,pc);
	%>
<!-- tool bar -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20">&nbsp;</td>
  </tr>
</table>
<table width="500"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="1" class="title_line_01"></td>
  </tr>
  <tr>
    <td height="2" class="title_line_02"></td>
  </tr>
  <tr>
    <td height="1"></td>
  </tr>
  <tr>
    <td class="line">
    <table width="100%"  border="0" cellspacing="1" cellpadding="3" class="dr_d">
        <tr>
          <td height="30" class="info_title_02" nowrap="nowrap">序号</td>
          <td height="30" class="info_title_02" nowrap="nowrap">工号</td>
          <td height="30" class="info_title_02" nowrap="nowrap">中文姓名</td>
          <td height="30" class="info_title_02" nowrap="nowrap">中文拼音</td>
          <td height="30" class="info_title_02" nowrap="nowrap">部门</td>
        </tr>
        <%
		int listNo = 1 ;
		for ( int i = 0 ; i < vlist.size() ; i++ )
		  {
			Ent=(Ent)vlist.elementAt(i) ;
		%>
        <tr bgcolor="#FFFFFF"">
          <td height="30" class="info_content_02"><%=listNo%>
            <% listNo = listNo + 1 ; %></td>
          <td  class="info_content_02"><%=Ent.getEmpID()%></td>
          <td  class="info_content_02"><%=Ent.getChineseName()%></td>
          <td  class="info_content_02"><%=Ent.getChiesePinYin()%></td>
          <td  class="info_content_02"><%=Ent.getDeptID()%></td>
        </tr>
        <%}%>
      </table></td>
  </tr>
</table>
<br>
