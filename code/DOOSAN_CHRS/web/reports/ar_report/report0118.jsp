<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0118.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
	
%>

	<body>
		
		<table width="1024" border="1" align="center">
		  <tr>
	    		<td align="center" colspan="23" HEIGHT=70 >
	    		<b><font size="+3">${yearMonth}月份班组出勤率</font></b></td>
	    	</tr>
		 
		  
		  
		  <tr align="center">
		  	<td rowspan="2"><b><font size="+1">组别</font></b></td>
		  	<td rowspan="2"><b><font size="+1">人数</font></b></td>
		  	<td rowspan="2"><b><font size="+1">正时</font></b></td>
		  	<td rowspan="2"><b><font size="+1">加班</font></b></td>
		  	<td rowspan="2"><b><font size="+1">总MH</font></b></td>
		  	<td colspan="6"><b><font size="+1">无薪缺勤</font></b></td>
		  	<td colspan="10"><b><font size="+1">有薪缺勤</font></b></td>
		  	<td rowspan="2"><b><font size="+1">总投入MH</font></b></td>
		  	<td rowspan="2"><b><font size="+1">出勤率</font></b></td>
		   </tr>
		    <tr align="center">
		  	<td ><b><font >迟到<br>(分)</font></b></td>
		  	<td ><b><font >早退<br>(分)</font></b></td>
		  	<td ><b><font >事假<br>(小时)</font></b></td>
		  	<td ><b><font >病假<br>(小时)</font></b></td>
		  	<td ><b><font >矿工<br>(小时)</font></b></td>
		  	<td ><b><font >合计<br>(小时)</font></b></td>
		  	<td ><b><font >病休1<br>(天)</font></b></td>
		  	<td ><b><font >病休2<br>(天)</font></b></td>
		  	<td ><b><font >结婚登<br>记假(天)</font></b></td>
		  	<td ><b><font >婚假<br>(天)</font></b></td>
		  	<td ><b><font >丧假<br>(天)</font></b></td>
		  	<td ><b><font >产假护<br>理假(天)</font></b></td>
		  	<td ><b><font >年休假<br>(天)</font></b></td>
		  	<td ><b><font >休假<br>(天)</font></b></td>
		  	<td ><b><font >调休<br>(天)</font></b></td>
		  	<td ><b><font >合计<br>(小时)</font></b></td>
		  </tr>
		  <c:forEach items="${recordList}" var="arData" varStatus="i">
		  		<tr align="center">
					<td>
						${arData.DEPARTMENT}
					</td>
					<td>
						${arData.SUMEMPID}
					</td>
					<td>
						${arData.SCHEDULED_WORK_DAYS}
					</td>
					<td>
						${arData.TOTAL_OT}
					</td>
					<td>
						${arData.TOTAL_MH}
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
						${arData.SICK_LEAVE}
					</td>
					<td>
						${arData.ABSENTEEISM}
					</td>
					<td>
						${arData.WUXINTOTAL}
					</td>
					<td>
						${arData.SICK_LEAVE_THREE_MONTH}
					</td>
					<td>
						${arData.SICK_LEAVE_PAST_THREE_MONTH}
					</td>
					<td>
						${arData.MARRIAGE_REGISTER}
					</td>
					<td>
						${arData.MARRIAGE_LEAVE}
					</td>
					<td>
						${arData.BEREFT_LEAVE}
					</td>
					<td>
						${arData.PATERNITY_LEAVE}
					</td>
					<td>
						${arData.ANNUAL_VACATION}
					</td>
					<td>
						${arData.HOLIDAY}
					</td>
					<td>
						${arData.TIAO_XIU}
					</td>
					<td>
						${arData.YOUXINTOTAL}
					</td>
					<td>
						${arData.SUMTOTALMH}
					</td>
					<td>
					    <fmt:formatNumber value="${arData.CHUQINLV}" type="currency" pattern=".00%"/> 
						
					</td>
					

				</tr>
		    	
		  </c:forEach>
		  
		</table>
	</body>
</html>

