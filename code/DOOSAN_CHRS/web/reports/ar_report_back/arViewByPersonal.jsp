<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>
<head>

<title>考勤查看&gt;个人别考勤查看</title>
</head>
<body>
 <%
 		response.reset();
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=arViewByPersonal.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
 %>
 <table width="100%" border="1" cellpadding="0" cellspacing="1" >
 <tr><td align="center" colspan="30" style="font-size:14pt;Font-FAMILY:宋体;font-weight:900">个人考勤查看</td></tr>
<br>
<br>
 </table>
  <table width="100%" border="1" cellpadding="0" cellspacing="1" >
  <tr>
  <td align="center">月考勤汇总</td>
  <td align="center">基本信息</td>
  <td align="center">月考勤汇总</td>
  </tr>
  <tr>
  <td>
  		        <table width="100%" border="1" cellpadding="0" cellspacing="1" >
		          <tr>
		            <td align="center" colspan="4"  height="30" nowrap><!-- 总年休假-->
					<ait:message  messageID="display.att.view.individual.total_time" module="ar"/></td>
		            <td  align="center"  colspan="2"nowrap><!-- 已使用年假-->
					<ait:message  messageID="display.att.view.individual.vacation_taken" module="ar"/></td>
		            <td  align="center"  colspan="2"nowrap><!-- 剩余年假-->
					<ait:message  messageID="display.att.view.individual.vacation_owing" module="ar"/></td>
		          </tr>
		          <tr>
		            <td colspan="4" align="center"  height="30" nowrap>${annualHolidayList[0].TOT_VAC_CNT + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		            <td align="center"  colspan="2" nowrap>${annualHolidayList[0].USE_VAC_CNT + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		            <td align="center" colspan="2"  nowrap>${annualHolidayList[0].REST_VAC_CNT + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		          </tr>
		          <tr>
		            <td  align="center" height="30" nowrap  colspan="2" ><!--加班总时间-->
					<ait:message  messageID="display.att.view.ot.total" module="ar"/></td>
		            <td align="center"  nowrap  colspan="2" ><!--平日加班-->
					<ait:message  messageID="display.att.view.ot.weekday" module="ar"/></td>
		            <td align="center"  nowrap  colspan="2" ><!--公休日加班-->
					<ait:message  messageID="display.att.view.ot.weekend" module="ar"/></td>
		            <td align="center" nowrap  colspan="2" ><!--节假日加班-->
					<ait:message  messageID="display.att.view.ot.holiday" module="ar"/></td>
		          </tr>
		          <tr>
		            <td align="center"  height="30" colspan="2" nowrap>${monthlyData.FIXED_OT_WINTER_NIGHT + monthlyData.FIXED_OT_WINTER_DAY + monthlyData.WEEKDAY_OT + monthlyData.WEEKEND_OT + monthlyData.HOLIDAY_OT}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center"  nowrap colspan="2" >${monthlyData.FIXED_OT_WINTER_NIGHT + monthlyData.FIXED_OT_WINTER_DAY + monthlyData.WEEKDAY_OT}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center"  nowrap colspan="2" >${monthlyData.WEEKEND_OT + 0}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center"  nowrap colspan="2" >${monthlyData.HOLIDAY_OT + 0}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		          </tr>
		        </table>  
  </td>  
  <td>
  <table width="100%" border="1" cellpadding="0" cellspacing="1" > 
  <tr>
  <td colspan="2" align="center">职号</td>
  <td colspan="2" align="center">姓名</td>
  <td colspan="2" align="center">部门</td>
  <td colspan="2" align="center">查询年月</td>
  <td colspan="2" align="center">入社日期</td>
  <td colspan="2" align="center">员工状态</td>
  </tr>
 <tr>
  <td colspan="2" align="center">${basic.empID}</td>
  <td colspan="2" align="center">${basic.chineseName}</td>
  <td colspan="2" align="center">${basic.deptFullName}</td>
  <td colspan="2" align="center">${year}${month}</td>
  <td colspan="2" align="center">${basic.joinDate}</td>
  <td colspan="2" align="center">${basic.status}</td>
  </tr>
  </table>
  
  </td>   
   <td>   
   <table width="100%" border="1" cellpadding="0" cellspacing="1">
		          <tr>
		            <td  align="center" height="30" colspan="2" nowrap><!-- 事假-->
					<ait:message  messageID="display.mutual.casual_leave" /></td>
		            <td align="center" nowrap colspan="2"><!-- 休假-->
					休假</td>
					<td align="center" nowrap colspan="2"><!-- 迟到-->
					<ait:message  messageID="display.mutual.tardiness" /></td>
		            <td align="center" nowrap colspan="2"><!-- 早退-->
					<ait:message  messageID="display.mutual.truancy" /></td>
		            <td  align="center" nowrap colspan="2"><!-- 旷工-->
					<ait:message  messageID="display.mutual.absenteeism" /></td>
		          </tr>
		          <tr>
		            <td align="center"  height="30" nowrap colspan="2">${monthlyData.CASUAL_LEAVE + 0}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center"  nowrap colspan="2">${monthlyData.HOLIDAY/8 + 0}&nbsp;<!--天 -->
					<ait:message messageID="display.mutual.day" /></td>
		            <td align="center"  nowrap colspan="2">${monthlyData.CONVERTED_TARDINESS + 0}&nbsp;<!--分钟-->分钟</td>
					<td align="center" nowrap colspan="2">${monthlyData.CONVERTED_TRUANCY + 0}&nbsp;<!--分钟-->分钟</td>
		            <td align="center"  nowrap colspan="2">${monthlyData.ABSENTEEISM + 0}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		          </tr>
		          <tr>
		          	<td align="center" height="30" nowrap colspan="2"><!-- 产假 -->
					<ait:message messageID="display.mutual.maternity_leave" /></td>
					<td align="center" nowrap colspan="2"><!-- 病假 -->
					<ait:message messageID="display.mutual.sick_leave"/></td>
		            <td align="center" nowrap colspan="2"><!-- 病休1 -->
					病休1</td>
		            <td align="center" nowrap colspan="2"><!-- 病休2-->
					病休2</td>
		            <td align="center" nowrap colspan="2"><!-- 工伤-->
					<ait:message messageID="display.mutual.industrial_injury" /></td>
		          </tr>
		          <tr>
		            <td align="center"  height="30" nowrap colspan="2">${monthlyData.MATERNITY_LEAVE/8 + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
				 <td align="center"   nowrap colspan="2">${monthlyData.SICK_LEAVE + 0}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center"  nowrap colspan="2">${monthlyData.SICK_LEAVE_THREE_MONTH/8 + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		            <td align="center"  nowrap colspan="2">${monthlyData.SICK_LEAVE_PAST_THREE_MONTH/8 + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		            <td align="center"  nowrap colspan="2">${monthlyData.INDUSTRY_INJURY/8 + 0}&nbsp;<!--天 -->
				<ait:message messageID="display.mutual.day" /></td>
		          </tr>
		        </table>
   
   
   </td>
  </tr>
  <tr><td colspan="3" align="center">月别考勤信息</td></tr>
  <tr>
  <td colspan="3">
  <table  width="100%" border="1" cellpadding="0" cellspacing="1">
		      <tr align="center">
		        <td width="70" height="30"  nowrap><!--区分-->
					<ait:message  messageID="display.att.view.ot.division" module="ar"/></td>
		        <c:forEach items="${overTimeList}" var="oneResult">
		        	<td align="center"  nowrap>${oneResult.DATE_DAY}</td>
		        </c:forEach>
		        
		        <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td align="center"  nowrap>${i}</td>
		        	</c:forEach>
		        </c:if>
		      </tr>
		      <tr>
		   		<td height="30"  align="center" nowrap><!--工作状态-->
					<ait:message  messageID="display.att.view.ot.work_status" module="ar"/></td>
		   		<c:forEach items="${overTimeList}" var="oneResult">
		        	<c:forEach items="${detailList}" var="twoResult">
		        		<c:if test="${oneResult.DATE_DAY == twoResult.date_day }">
		        			<td align="center"  nowrap>		        			
		        			<ait:content enContent="${twoResult.shiftName}" zhContent="${twoResult.shiftName}"></ait:content>
		        			</td>
		        		</c:if>
		        	</c:forEach>
		        </c:forEach>
		        
		        <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td align="center"  nowrap>&nbsp;</td>
		        	</c:forEach>
		        </c:if>
		   	  </tr>
		      <tr>
		   		<td height="30"  nowrap><!--缺勤情况-->
					<ait:message  messageID="display.att.view.ot.item" module="ar"/></td>      	
		      	<c:forEach items="${overTimeList}" var="oneResult">
		        	<c:forEach items="${detailList}" var="twoResult">
		        		<c:if test="${oneResult.DATE_DAY == twoResult.date_day}">
		        			<td align="center"  nowrap>${twoResult.itemName}</td>
		        		</c:if>
		        	</c:forEach>
		      	 </c:forEach>
		      	 
		      	 <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td align="center"  nowrap>&nbsp;</td>
		        	</c:forEach>
		         </c:if>
		   	  </tr>
		   	  <tr>
		   		 <td height="30"  nowrap><!--加班-->
					<ait:message  messageID="display.att.view.ot.ot" module="ar"/></td>
		   		 <c:forEach items="${overTimeList}" var="oneResult">
		        	<td align="center"  nowrap>${oneResult.OVERTIME }</td>
		   		 </c:forEach>
		   		 
		   		 <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td align="center"  nowrap>&nbsp;</td>
		        	</c:forEach>
		         </c:if>
		   	  </tr>
		     </table> 
  
  </td>
  </tr> 
  </table>		      
</body>
</html>
