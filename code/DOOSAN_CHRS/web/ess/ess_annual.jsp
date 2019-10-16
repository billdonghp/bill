<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.Annual"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="annual" class="com.ait.ar.bean.Annual" scope="page" />
<jsp:useBean id="annualList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="date" class="java.util.GregorianCalendar" scope="request" />
<jsp:useBean id="excel" class="java.lang.String" scope="request" />
<%if (!excel.equals("true")) {
	String key = StringUtil.checkNull(request.getParameter("key")); %>
<html>
<head>
<script language="javascript">
function Save() {
	document.annualForm.target="_blank";
	document.annualForm.excel.value="true";
	document.annualForm.submit();
}
</script>
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../inc/common_toolbar_a.jsp"%>		
<table width="100%" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="annualForm" method="post" target="" action="/essControlServlet">
<input type="hidden" name="content" value="annual" />
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="excel" value="false" />		
	<tr>
	<td colspan="8">年份&nbsp;<select name="year">
	<%int year = date.get(GregorianCalendar.YEAR);
	for (int i=-5;i<=2;i++){%>
		<option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year+i%></option>
	<%}%>
						</select>&nbsp;<input type="text" name="key" value="<%=StringUtil.toCN(key)%>" />&nbsp;<img src="../images/btn/search.gif" width="52" height="19" align="absmiddle" style="cursor:pointer " onClick="document.annualForm.submit();"></td>
					<td>截止日期：&nbsp;<%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(date.getTime())%></td>
				</tr>
			</table>
<%} else {
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=Ar_annual.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
}%>
<table width="100%" border="1" cellpadding="3" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr bgcolor="#f5f5f5">
		<td width="15%">员工号</td>
		<td width="12%">姓名</td>
		<td width="*">部门</td>
		<td width="12%">入社时间</td>
		<td width="12%">当前应有年假</td>
		<td width="10%">已用年假</td>
		<td width="10%">剩余年假</td>
	</tr>
	<%for(int i=0;i<annualList.size();i++){
		annual = (Annual) annualList.get(i);%>
				<tr>
					<td><%=annual.getEmpID() %></td>
					<td><%=StringUtil.checkNull(annual.getChineseName(), "无")%></td>
					<td><%=StringUtil.checkNull(annual.getReason(),"&nbsp;") %></td>
					<td><%=StringUtil.checkNull(annual.getDATE_STARTED(), "无")%></td>
					<td><%=annual.getAnnualHours() %></td>
					<td><%=annual.getAnnualHoursUsed() %></td>
		<%if (annual.getAnnualHoursLeft() < 0) {%>
					<td><font color="red"><%=annual.getAnnualHoursLeft() %></font></td>
		<%} else {%>
					<td><%=annual.getAnnualHoursLeft() %></td>
		<%}%>
				</tr>
	<%}%>
		</table>
<%if (!excel.equals("true")) {%>
	</form>
<%}%>
	</body>
</html>
