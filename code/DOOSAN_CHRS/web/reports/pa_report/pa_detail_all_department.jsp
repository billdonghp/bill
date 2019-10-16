<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门别工资支给明细查看</title>
</head>
<%
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=pa_detail_all_department.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>
		<table border="1" align="center">
		  <tr align="center">
		  	<td colspan="33" rowspan="3" style="font-size:12pt;FONT-FAMILY:宋体;font-weight:1200">&nbsp;&nbsp;&nbsp;&nbsp;部门别工资支给明细查看</td>
		  </tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="1" style="FONT-FAMILY:宋体;">
		<tr>
		<td nowrap colspan="8" align="center">部门编号</td>
		<td nowrap colspan="8" align="center">部门名称</td>
		<td nowrap colspan="9" align="center">平均工资</td>
		<td nowrap colspan="8" align="center">人数</td>	
		</tr>
		<c:forEach items="${basicList}" var="basic" varStatus="y">
		<td nowrap colspan="8" align="center">&nbsp;${basic.DEPTID}</td>
		<td nowrap colspan="8" align="center">&nbsp;${basic.DEPARTMENT}</td>
		<td nowrap colspan="9" align="center">&nbsp;${basic.AVG_THIS_ACTUAL_WAGE}</td>
		<td nowrap colspan="8" align="center">&nbsp;${basic.COUNTEMPID}</td>	
		</c:forEach>
		</table>	
		<table width="100%" border="1" cellpadding="0" cellspacing="1" style="FONT-FAMILY:宋体;">
		<tr>
		            <td align="center" nowrap >工资月</td>	
		            <td align="center" nowrap >类别</td>	
		            <td align="center" nowrap >人数</td>	       
					<td align="center" nowrap>基本工资</td>
		            <td align="center" nowrap>职务津贴</td>
				    <td align="center" nowrap>住宅补助</td>
		            <td align="center" nowrap>特殊补助</td>
					<td align="center" nowrap>基本合计</td>
		            <td align="center" nowrap>业绩工资2</td>
		            <td align="center" nowrap>业绩工资3</td>
		            <td align="center" nowrap>其他支给</td>
		            <td align="center" nowrap>支给错误</td>
		            <td align="center" nowrap>夜班补助</td>
		            <td align="center" nowrap>值班补助</td>
		            <td align="center" nowrap>迟早减除</td>
		            <td align="center" nowrap>未勤减除</td>
		            <td align="center" nowrap>事病减除</td>
		            <td align="center" nowrap>休假减除</td>
		            <td align="center" nowrap>休职减除</td>
		            <td align="center" nowrap>旷工减除</td>
					<td align="center" nowrap>其他减除</td>
		            <td align="center" nowrap>住宅减除</td>
		            <td align="center" nowrap>减除错误</td>
		            <td align="center" nowrap>放假减除</td>
		            <td align="center" nowrap>试用扣除</td>
		            <td align="center" nowrap>支给合计</td>
		            <td align="center" nowrap>养老保险</td>
		            <td align="center" nowrap>医疗保险</td>
		            <td align="center" nowrap>待业保险</td>
		            <td align="center" nowrap>公积金</td>
		            <td align="center" nowrap>所得税</td>
		            <td align="center" nowrap>减除合计</td>
		            <td align="center" nowrap>实领工资</td>
		          </tr>
		          <c:forEach items="${viewDetailList}" var="test" varStatus="i">
		          <tr>
		            <td nowrap >${test.PA_MONTH}</td>
		            <td nowrap >${test.BONUS_TYPE}</td>
		            <td nowrap >${test.COUNTEMPID}</td>	            
					<td nowrap>${test.BASE_PAY}</td>
		            <td nowrap>${test.DUTIES_ALLOWANCE}</td>
				    <td nowrap>${test.RESIDENTIAL_GRANTS}</td>
		            <td nowrap>${test.SPECIAL_GRANTS}</td>
					<td nowrap>${test.TOTAL_BASIC}</td>
		            <td nowrap>${test.PERFORMANCE_PAY2}</td>
		            <td nowrap>${test.PERFORMANCE_PAY3}</td>
		            <td nowrap>${test.TO_THE_OTHER}</td>
		            <td nowrap>${test.STICKS_TO_THE_WRONG}</td>
		            <td nowrap>${test.NIGHT_DUTY_SUBSIDY}</td>
		            <td nowrap>${test.DUTY_SUBSIDIES}</td>
		            <td nowrap>${test.LATE_EARLY_MINUS}</td>
		            <td nowrap>${test.NOT_ATTENDANCE_MINUS}</td>
		            <td nowrap>${test.LEAVE_SICK_MINUS}</td>
		            <td nowrap>${test.VACATION_MINUS}</td>
		            <td nowrap>${test.LEVEL_OFF_MINUS}</td>
		            <td nowrap>${test.ABSENTEEISM_MINUS}</td>
					<td nowrap>${test.OTHER_LESS}</td>
		            <td nowrap>${test.RESIDENTIAL_MINUS}</td>
		            <td nowrap>${test.REDUCE_ERRORS}</td>
		            <td nowrap>${test.HOLIDAY_MINUS}</td>
		            <td nowrap>${test.TRIAL_MINUS}</td>
		            <td nowrap>${test.THIS_TOTAL_SUPPORT}</td>
		            <td nowrap>${test.PERSONAL_PENSION}</td>
		            <td nowrap>${test.PERSONAL_MEDICAL}</td>
		            <td nowrap>${test.PERSONAL_UNEMPLOYED}</td>
		            <td nowrap>${test.PERSONAL_HOUSING_FUND}</td>
		            <td nowrap>${test.THIS_ACTUAL_TAX}</td>
		            <td nowrap>${test.TOTAL_DEDUCTIONS}</td>
		            <td nowrap>${test.THIS_ACTUAL_WAGE}</td>
		          </tr>
		          </c:forEach>
		</table>		
</body>

</html>
