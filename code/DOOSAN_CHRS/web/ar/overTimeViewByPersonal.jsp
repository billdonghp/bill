<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤查看&gt;个人别加班查看</title>
<SCRIPT type="text/javascript">
function Search() {

  document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=overTimeViewByPersonal&menu_code=${param.menu_code}";
  document.form1.fireSubmit();
}
</SCRIPT>
</head>
<body>

<form  name="form1" method="post" action="" > 

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/ar_toolbar_s.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
		</table>
		<%@ include file="./ar_view_basic.jsp"%>

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar_n.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="2">
		  <tr>
		    <td  class="title1"><!--个人别加班查看-->
					<ait:message  messageID="display.att.view.ot.ot_view" module="ar"/></td> 
		  </tr>
		  <tr>
		  	<td> 
		    <table  width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		      <tr>
		        <td width="70" height="30" class="info_title_01" nowrap><!--区分-->
					<ait:message  messageID="display.att.view.ot.division" module="ar"/></td>
		        <c:forEach items="${overTimeList}" var="oneResult">
		        	<td class="info_title_01" nowrap>${oneResult.DATE_DAY}</td>
		        </c:forEach>
		        
		        <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td class="info_title_01" nowrap>${i}</td>
		        	</c:forEach>
		        </c:if>
		      </tr>
		      <tr>
		   		<td height="30" class="info_title_01" nowrap><!--工作状态-->
					<ait:message  messageID="display.att.view.ot.work_status" module="ar"/></td>
		   		<c:forEach items="${overTimeList}" var="oneResult">
		        	<c:forEach items="${detailList}" var="twoResult">
		        		<c:if test="${oneResult.DATE_DAY == twoResult.date_day }">
		        			<td class="info_content_04" nowrap>		        			
		        			<ait:content enContent="${twoResult.shiftName}" zhContent="${twoResult.shiftName}"></ait:content>
		        			</td>
		        		</c:if>
		        	</c:forEach>
		        </c:forEach>
		        
		        <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td class="info_content_04" nowrap>&nbsp;</td>
		        	</c:forEach>
		        </c:if>
		   	  </tr>
		      <tr>
		   		<td height="30" class="info_title_01" nowrap><!--考勤情况-->
					<ait:message  messageID="display.att.view.ot.item" module="ar"/></td>      	
		      	<c:forEach items="${overTimeList}" var="oneResult">
		        	<c:forEach items="${detailList}" var="twoResult">
		        		<c:if test="${oneResult.DATE_DAY == twoResult.date_day}">
		        			<td align="center" class="info_content_04" nowrap>${twoResult.itemName}</td>
		        		</c:if>
		        	</c:forEach>
		      	 </c:forEach>
		      	 
		      	 <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td class="info_content_04" nowrap>&nbsp;</td>
		        	</c:forEach>
		         </c:if>
		   	  </tr>
		   	  <tr>
		   		 <td height="30" class="info_title_01" nowrap><!--加班-->
					<ait:message  messageID="display.att.view.ot.ot" module="ar"/></td>
		   		 <c:forEach items="${overTimeList}" var="oneResult">
		        	<td class="info_content_04" nowrap>${oneResult.OVERTIME }</td>
		   		 </c:forEach>
		   		 
		   		 <c:if test="${overTimeList eq null}">
		        	<c:forEach begin="1" end="31" step="1" var="i">
		        		<td class="info_content_04" nowrap>&nbsp;</td>
		        	</c:forEach>
		         </c:if>
		   	  </tr>
		     </table>
		     </td>
		  </tr>
		  <tr height="1">
		    <td colspan="2">&nbsp;</td>
		  </tr>
		  <tr>
		    <td  class="title1"><!--加班信息-->
					<ait:message  messageID="display.att.view.ot.info" module="ar"/></td>
		  </tr>
		  <tr>
		    <td >
		        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" >
		          <tr>
		            <td class="info_title_01" height="30" nowrap><!--加班总时间-->
					<ait:message  messageID="display.att.view.ot.total" module="ar"/></td>
		            <td class="info_title_01" nowrap><!--平日加班-->
					<ait:message  messageID="display.att.view.ot.weekday" module="ar"/></td>
		            <td class="info_title_01" nowrap><!--公休日加班-->
					<ait:message  messageID="display.att.view.ot.weekend" module="ar"/></td>
		            <td class="info_title_01" nowrap><!--节假日加班-->
					<ait:message  messageID="display.att.view.ot.holiday" module="ar"/></td>
		          </tr>
		          <tr>
		            <td align="center" class="info_content_01" height="30" nowrap>${monthlyDataList[0].TOTAL_WEEKDAY_OT + monthlyDataList[0].WEEKEND_OT + monthlyDataList[0].HOLIDAY_OT}&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center" class="info_content_01" nowrap>${monthlyDataList[0].TOTAL_WEEKDAY_OT }&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center" class="info_content_01" nowrap>${monthlyDataList[0].WEEKEND_OT }&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		            <td align="center" class="info_content_01" nowrap>${monthlyDataList[0].HOLIDAY_OT }&nbsp;<!--小时-->
					<ait:message  messageID="display.att.view.individual.hour" module="ar"/></td>
		          </tr>
				 </table>
			 </td>
		   </tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</body>
</html>
