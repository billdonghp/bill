<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hrm.bean.*,java.util.*,com.ait.util.*,com.ait.hrm.dao.*"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hrm.bean.Department"/> <!-- data type contained in the dept_list -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息统计</title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%	String pamonth = request.getParameter("pamonth");
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
	pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
	}
%>
<script language="JavaScript" type="">
function showReport() {
	document.rpt.action = document.rpt.reportname.value;
  document.rpt.pamonth.value = document.rpt.year.value+document.rpt.month.value;;
  document.rpt.submit();
}
</script></head>
<body>
<%@ include file="/ess/esstoolbar.jsp"%>
<%@ include file="/hr/hr_diaoling_toolbar.jsp"%>
<br>
<table width="490" height="152" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <form name="rpt" target="_blank" method="post" onSubmit="return showReport()">
    <tr align="center">
      <td width="101" rowspan="3"  align="center" bgcolor="#F5F5F5">人事报表</td>
      <td height="30" align="center" valign="middle">
        <select name="reportname">
          <option value="hr_excel.jsp" selected>人事记录卡-实时</option>
          <option value="hr_excel_history.jsp">人事记录卡-历史</option>
          </select></td>
    </tr>
    <tr align="center">
      <td height="30" align="center" valign="middle"><select name="year" class="pamonth" >
        <%
	for (int i=-4;i<=4;i++){
	%>
        <option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
        <%}%>
      </select>
年
<select name="month" class="pamonth" >
  <%
	  	for (int i=1;i<=12;i++){
	%>
  <option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
  <%}%>
</select>
月</td>
    </tr><input type="hidden" name="pamonth">
    <tr align="center">
      <td width="383" height="30" align="center" valign="middle"><input type="button" name="Submit" value="查询" onClick="return showReport()"></td>
    </tr>
  </form>
</table>
</body>
</html>


