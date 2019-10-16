<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0117.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
	
%>

	<body>
		
		<table width="1024" border="1" align="center">
		  <tr>
	    		<td align="center" colspan="12" HEIGHT=70 >
	    		<b><font size="+3">${yearMonth}月份工厂工人加班明细表</font></b></td>
	    	</tr>
		 
		  
		  
		  <tr align="center">
		  	<td ><b><font size="+1">月份</font></b></td>
		  	<td ><b><font size="+1">工号</font></b></td>
		  	<td ><b><font size="+1">姓名</font></b></td>
		  	<td ><b><font size="+1">员工状态</font></b></td>
		  	<td ><b><font size="+1">部门</font></b></td>
		  	<td ><b><font size="+1">部门全称</font></b></td>
		  	<td ><b><font size="+1">平日加班时间</font></b></td>
		  	<td ><b><font size="+1">休息日加班时间</font></b></td>
		  	<td ><b><font size="+1">节假日加班时间</font></b></td>
		  	<td ><b><font size="+1">平日加班费</font></b></td>
		  	<td ><b><font size="+1">休息日加班费</font></b></td>
		  	<td ><b><font size="+1">节假日加班费</font></b></td>
		  	
		  </tr>
		  <c:forEach items="${recordList}" var="arData" varStatus="i">
		  		<tr align="center">
					<td>
						${arData.PA_MONTH}
					</td>
					<td>
						${arData.EMPID}
					</td>
					<td>
						${arData.CHINESENAME}
					</td>
					<td>
						${arData.STATUS}
					</td>
					<td>
						${arData.DEPARTMENT}
					</td>
					<td>
						${arData.DEPTFULLNAME}
					</td>
					<td>
						${arData.TOTAL_WEEKDAY_OT}
					</td>
					<td>
						${arData.TOTAL_WEEKEND_OT}
					</td>
					<td>
						${arData.TOTAL_HOLIDAY_OT}
					</td>
					<td>
						${arData.PEACETIME_OVERTIME}
					</td>
					<td>
						${arData.OVERTIME_REST_DAY}
					</td>
					<td>
						${arData.OVERTIME_HOLIDAYS}
					</td>

				</tr>
		    	
		  </c:forEach>
		  
		</table>
	</body>
</html>

