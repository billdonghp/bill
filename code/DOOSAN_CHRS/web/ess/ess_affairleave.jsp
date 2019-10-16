<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.jdbc.core.SQLResult"%>
<%@ page import="com.ait.jdbc.core.RowResult"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.DateUtil"%>
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
		<form name="annualForm" method="post" target="" action="/essControlServlet">
			<input type="hidden" name="content" value="affairleave" />
			<input type="hidden" name="operation" value="view" />
			<input type="hidden" name="isAll" value="1" />
			<input type="hidden" name="menu_code" value="<%=menu_code%>" />
			<input type="hidden" name="excel" value="false" />
			<table width="100%" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr>
					<td colspan="8">年份&nbsp;<select name="year">
	<%int year = DateUtil.getYear(new java.util.Date());
      	for (int i=-5;i<=2;i++){%>
		  <option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year+i%></option>
	<%}%>
						</select>&nbsp;<input type="text" name="key" value="<%=StringUtil.toCN(key)%>" />&nbsp;<img src="../images/btn/search.gif" width="52" height="19" align="absmiddle" style="cursor:pointer " onClick="document.annualForm.submit();"></td>
					
				</tr>
			</table>
<%} else {
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=Ar_annual.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
}%>
			<table width="98%" border="1" cellpadding="3" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr bgcolor="#f5f5f5">
					<td width="15%">员工号</td>
					<td width="12%">姓名</td>
					<td width="*">部门</td>
					<td width="12%">入社时间</td>
					<td width="12%">事假</td>
				</tr>
	<%
	    SQLResult rs = (SQLResult)request.getAttribute("sqlResult");
	    java.util.Iterator it = rs.iterator();
	    while (it.hasNext()){
	      RowResult rt = (RowResult)it.next();
	%>    
	     <tr>
	       <td><%=rt.getString(1)%></td>
	       <td><%=rt.getString(2)%></td>
	       <td><%=rt.getString(3)%></td>
	       <td><%=DateUtil.formatDate(rt.getDate(4), DateUtil.DATE_PATTERN)%></td>
	       <td><%=rt.getDouble(5)%></td>
	     </tr>
	<% } %>
		</table>
<%if (!excel.equals("true")) {%>
	</form>
<%}%>
	</body>
</html>
