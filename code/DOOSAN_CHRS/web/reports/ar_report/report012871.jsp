<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.ArReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report012871.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String cpnyId = admin.getCpnyId();
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="5" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="5" ><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;职员平日打卡加班数据表</td>
		  </tr>
		  <tr><td colspan="5">&nbsp;</td></tr>
		  <tr align="center">
		  	<td rowspan="1">年月</td>
		  	<td rowspan="1">职号</td>
		  	<td rowspan="1">姓名</td>
		  	<td rowspan="1">部门</td>
		  	<td rowspan="1">时数</td>
		  </tr>
		  <c:forEach items="${arDataList1}" var="arData1" varStatus="j">
		  	
		  		<tr align="center">	
					  	<td>${arData1.Y_M}</td>
			    		<td>${arData1.EMPID}</td>
			    		<td>${arData1.CHINESENAME}</td>
			    		<td>${arData1.DEPT}</td>
			    		<td>${arData1.HOUR}</td>
				</tr>
		  </c:forEach>
		  
		</table>
	</body>
</html>

