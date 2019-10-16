<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0123.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
	
%>

	<body>
		
		<table width="1024" border="1" align="center">
		    <tr>
	    		<td align="center" colspan="3" HEIGHT=40 >
	    		<b><font size="+2">${yearMonthStart} ~ ${yearMonthEnd}部门别出勤时间表</font></b></td>
	    	</tr>
		 
		  
		  <tr align="center">
		  	<td ><b><font size="+1">部门</font></b></td>
		  	<td ><b><font size="+1">工人/职员</font></b></td>
		  	<td ><b><font size="+1">出勤小时数</font></b></td>
		  	
		  </tr>
		  <c:forEach items="${recordList}" var="arData" varStatus="i">
		  		<tr align="center">
					<td>
						${arData.DEPTNAME}
					</td>
					<td>
						${arData.WORKTYPE}
					</td>
					<td>
						${arData.TOTALHOUR}
					</td>
				</tr>
		    	
		  </c:forEach>
		  
		</table>
	</body>
</html>

