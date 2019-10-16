<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0127.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
	
%>

	<body>
		
		<table width="1024" border="1" align="center">
		    <tr>
	    		<td align="center" colspan="6" HEIGHT=20 >
	    		<b><font size="+1">${dateStarted} 至 ${dateEnd} 区间 ${arTime} 后进门刷卡记录</font></b></td>
	    	</tr>
		 
		  
		  <tr align="center">
		  	<td ><b>部门</b></td>
		  	<td ><b>课组</b></td>
		  	<td ><b>工号</b></td>
		  	<td ><b>姓名</b></td>
		  	<td ><b>职级</b></td>
		  	<td ><b>刷卡时间</b></td>

		  </tr>
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  		<tr align="center">
					<td>
						${arData.OFFICENAME}
					</td>
					<td>
						${arData.DEPTNAME}
					</td>
					<td>
						${arData.EMPID}
					</td>
					<td>
						${arData.CHINESENAME}
					</td>
					<td>
						${arData.POST_GRADE_NAME}
					</td>
					<td>
						${arData.R_TIME}
					</td>
				</tr>
		    	
		  </c:forEach>
		  
		</table>
	</body>
</html>

