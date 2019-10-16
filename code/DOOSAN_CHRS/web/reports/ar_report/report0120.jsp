<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0120.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
	
%>

	<body>
		
		<table width="1024" border="1" align="center">
		    <tr>
	    		<td align="center" colspan="28" HEIGHT=40 >
	    		<b><font size="+2">${yearMonthStart} ~ ${yearMonthEnd}月份个人别勤态汇总表</font></b></td>
	    	</tr>
		 
		  
		  <tr align="center">
		  	<td ><b><font size="+1">部门</font></b></td>
		  	<td ><b><font size="+1">课组</font></b></td>
		  	<td ><b><font size="+1">工号</font></b></td>
		  	<td ><b><font size="+1">姓名</font></b></td>
		  	<td ><b><font size="+1">入社日期</font></b></td>
		  	<td ><b><font size="+1">试用期满日</font></b></td>
		  	<td ><b><font size="+1">号俸</font></b></td>
		  	<td ><b><font size="+1">员工区分</font></b></td>
		  	<td ><b><font size="+1">实勤天数(天)</font></b></td>
		  	<td ><b><font size="+1">考勤减除(天)</font></b></td>
		  	<td ><b><font size="+1">迟到(次)</font></b></td>
		  	<td ><b><font size="+1">早退(次)</font></b></td>
		  	<td ><b><font size="+1">事假(小时)</font></b></td>
		  	<td ><b><font size="+1">无薪产假(天)</font></b></td>
		  	<td ><b><font size="+1">产假护理假(天)</font></b></td>
		  	<td ><b><font size="+1">病假(小时)</font></b></td>
		  	<td ><b><font size="+1">病休1(天)</font></b></td>
		  	<td ><b><font size="+1">病休2(天)</font></b></td>
		  	<td ><b><font size="+1">婚丧假(天)</font></b></td>
		  	<td ><b><font size="+1">婚假(天)</font></b></td>
		  	<td ><b><font size="+1">丧假(天)</font></b></td>
		  	<td ><b><font size="+1">旷工(小时)</font></b></td>
		  	<td ><b><font size="+1">休假(天)</font></b></td>
		  	<td ><b><font size="+1">工伤(天)</font></b></td>
		  	<td ><b><font size="+1">放假1(天)</font></b></td>
		  	<td ><b><font size="+1">放假2(天)</font></b></td>
		  	<td ><b><font size="+1">放假3(天)</font></b></td>
		  	<td ><b><font size="+1">放假4(天)</font></b></td>
		  </tr>
		  <c:forEach items="${recordList}" var="arData" varStatus="i">
		  		<tr align="center">
					<td>
						${arData.DEPTNAME}
					</td>
					<td>
						${arData.DEPT}
					</td>
					<td>
						${arData.EMPID}
					</td>
					<td>
						${arData.CHINESENAME}
					</td>
					<td>
						${arData.DATE_STARTED}
					</td>
					<td>
						${arData.END_PROBATION_DATE}
					</td>
					<td>
						${arData.POST_COEF}
					</td>
					<td>
						${arData.CODE_NAME}
					</td>
					<td>
						${arData.WORK_TIME}
					</td>
					<td>
						${arData.INSUFFICIENT_ATTENDANCE}
					</td>
					<td>
						${arData.TOTAL_TARDINESS}
					</td>
					<td>
						${arData.TOTAL_TRUANCY}
					</td>
					<td>
						${arData.CASUAL_LEAVE}
					</td>
					<td>
						${arData.MATERNITY_LEAVE}
					</td>
					<td>
						${arData.PATERNITY_LEAVE}
					</td>
					<td>
						${arData.SICK_LEAVE}
					</td>
					<td>
						${arData.SICK_LEAVE_THREE_MONTH}
					</td>
					<td>
						${arData.SICK_LEAVE_PAST_THREE_MONTH}
					</td>
					<td>
						${arData.MARRIAGE_BEREFT_LEAVE}
					</td>
					<td>
						${arData.MARRIAGE_LEAVE}
					</td>
					<td>
						${arData.BEREFT_LEAVE}
					</td>
					
					<td>
						${arData.ABSENTEEISM}
					</td>
					<td>
						${arData.HOLIDAY}
					</td>
					<td>
						${arData.INDUSTRY_INJURY}
					</td>
					<td>
						${arData.VACATION_SHORT}
					</td>
					<td>
						${arData.VACATION_LONG}
					</td>
					<td>
						${arData.VACATION_LONG_A}
					</td>
					<td>
						${arData.VACATION_LONG_B}
					</td>
					
				</tr>
		    	
		  </c:forEach>
		  
		</table>
	</body>
</html>

