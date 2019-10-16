<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人别工资支给明细查看</title>
</head>
<%
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=pa_detail_all_person_b.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>
		<table border="1" align="center">
		  <tr align="center">
		  	<td colspan="32" rowspan="3" style="font-size:12pt;FONT-FAMILY:宋体;font-weight:1200">&nbsp;&nbsp;&nbsp;&nbsp;个人别工资支给明细查看（支社）</td>
		  </tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="1" style="FONT-FAMILY:宋体;">
		<tr>
		<td nowrap colspan="4" align="center">部门</td>
		<td nowrap colspan="4" align="center">职位</td>
		<td nowrap colspan="4" align="center">号奉</td>
		<td nowrap colspan="4" align="center">职号</td>
		<td nowrap colspan="4" align="center">姓名</td>
		<td nowrap colspan="4" align="center">入社日期</td>
		<td nowrap colspan="4" align="center">退社日期</td>
		<td nowrap colspan="4" align="center">月平均工资</td>		
		</tr>
		<c:forEach items="${basicList}" var="basic" varStatus="y">
		<td nowrap colspan="4" align="center">&nbsp;${basic.DEPTNAME}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.POST_GRADE_NAME}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.POST_COEF}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.EMPID}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.CHINESENAME}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.DATE_STARTED}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.DATE_LEFT}</td>
		<td nowrap colspan="4" align="center">&nbsp;${basic.AVG_THIS_ACTUAL_WAGE}</td>		
		</c:forEach>
		</table>	
		<table width="100%" border="1" cellpadding="0" cellspacing="1" style="FONT-FAMILY:宋体;">
		<tr>
		            <td class="info_title_00" nowrap >工资月</td>
		            <td class="info_title_00" nowrap >类别</td>
		          	<td class="info_title_00" nowrap ><!--工号--><ait:message messageID="display.mutual.emp_id" /></td>
		            <td class="info_title_00" nowrap><!--姓名--><ait:message messageID="display.mutual.name" /></td>
		            
					<td class="info_title_00" nowrap >基本工资</td>
		            <td class="info_title_00" nowrap >职务津贴</td>
				    <td class="info_title_00" nowrap >住宅补助</td>
		            <td class="info_title_00" nowrap >特殊补助</td>
		            <td class="info_title_00" nowrap >岗位补助</td>
		            <td class="info_title_00" nowrap >技术补助</td>
		            <td class="info_title_00" nowrap >淡季奖</td>
		            <td class="info_title_00" nowrap >其他补偿</td>
		            <td class="info_title_00" nowrap >地区补助</td>
					<td class="info_title_00" nowrap >基本合计</td>		            
		            <td class="info_title_00" nowrap >其他支给</td>
		            <td class="info_title_00" nowrap >餐费</td>
		            <td class="info_title_00" nowrap >绩效工资</td>
		            <td class="info_title_00" nowrap >顾客满意津贴</td>
		            <td class="info_title_00" nowrap >职位补贴</td>
		            <td class="info_title_00" nowrap >支给错误</td>		           
		            <td class="info_title_00" nowrap >迟早减除</td>
		            <td class="info_title_00" nowrap >未勤减除</td>
		            <td class="info_title_00" nowrap >事病减除</td>
		            <td class="info_title_00" nowrap >休假减除</td>
		            <td class="info_title_00" nowrap >休职减除</td>
		            <td class="info_title_00" nowrap >旷工减除</td>
					<td class="info_title_00" nowrap >其他减除</td>
		            <td class="info_title_00" nowrap >住宅减除</td>
		            <td class="info_title_00" nowrap >减除错误</td>
		            <td class="info_title_00" nowrap >放假减除</td>
		            <td class="info_title_00" nowrap >试用扣除</td>
		            <td class="info_title_00" nowrap >支给合计</td>
		            <td align="info_title_00" nowrap>其他计税</td>
		            <td class="info_title_00" nowrap >管理费</td>
		            <td class="info_title_00" nowrap >公司负担</td>
		            <td class="info_title_00" nowrap >个人负担</td>
		            <td class="info_title_00" nowrap >小计</td>		            
		            <td class="info_title_00" nowrap >国企合计</td>
		            <td class="info_title_00" nowrap >所得税</td>		            
		            <td class="info_title_00" nowrap >实领工资</td>
		          </tr>
		          <c:forEach items="${viewDetailList}" var="test" varStatus="i">
		          <tr>
		            <td class="info_content_00" nowrap >${test.PA_MONTH}</td>
		            <td class="info_content_00" nowrap >${test.BONUS_TYPE}</td>
		            <td class="info_content_00" nowrap >${test.EMPID}</td>
		            <td class="info_content_00" nowrap>${test.CHINESENAME}</td>		            
					<td class="info_content_00" nowrap >${test.BASE_PAY}</td>
		            <td class="info_content_00" nowrap >${test.DUTIES_ALLOWANCE}</td>
				    <td class="info_content_00" nowrap >${test.RESIDENTIAL_GRANTS}</td>
		            <td class="info_content_00" nowrap >${test.SPECIAL_GRANTS}</td>
		            <td class="info_content_00" nowrap >${test.POST_SUBSIDIES}</td>
		            <td class="info_content_00" nowrap >${test.TECHNIQUE_ALLOWANCE}</td>
		            <td class="info_content_00" nowrap >${test.OFF_SEA_BOUNS}</td>
		            <td class="info_content_00" nowrap >${test.OTHER_COMPENSATION}</td>
		            <td class="info_content_00" nowrap >${test.REGIONAL_GRANTS}</td>
					<td class="info_content_00" nowrap >${test.TOTAL_BASIC}</td>		            
		            <td class="info_content_00" nowrap >${test.TO_THE_OTHER}</td>
		            <td class="info_content_00" nowrap >${test.MEAL_FEE}</td>
		            <td class="info_content_00" nowrap >${test.PERFORMANCE_PAY}</td>
		            <td class="info_content_00" nowrap >${test.CUSTOMER_SATISFACTION}</td>
		            <td class="info_content_00" nowrap >${test.POSITION_ALLOWANCE}</td>
		          		            
		            <td class="info_content_00" nowrap >${test.DUTY_SUBSIDIES}</td>
		            <td class="info_content_00" nowrap >${test.LATE_EARLY_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.NOT_ATTENDANCE_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEAVE_SICK_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.VACATION_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.LEVEL_OFF_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.ABSENTEEISM_MINUS}</td>
					<td class="info_content_00" nowrap >${test.OTHER_LESS}</td>
		            <td class="info_content_00" nowrap >${test.RESIDENTIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.REDUCE_ERRORS}</td>
		            <td class="info_content_00" nowrap >${test.HOLIDAY_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.TRIAL_MINUS}</td>
		            <td class="info_content_00" nowrap >${test.THIS_TOTAL_SUPPORT}</td>	
		            <td class="info_content_00" nowrap >${test.GIFT_COST}</td>		            
		            <td class="info_content_00" nowrap >${test.HANDLING_CHARGE}</td>
		            <td class="info_content_00" nowrap >${test.PAYMENT_OF_E}</td>
		            <td class="info_content_00" nowrap >${test.PAYMENT_OF_INDIVIDUAL}</td>
		            <td class="info_content_00" nowrap >${test.XIAOJI}</td>
		            <td class="info_content_00" nowrap >${test.GUOQIHEJI}</td>		            
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_TAX}</td>		            
		            <td class="info_content_00" nowrap >${test.THIS_ACTUAL_WAGE}</td>
		          </tr>
		          </c:forEach>
		</table>		
</body>

</html>
