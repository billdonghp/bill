<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤维护&gt;月考勤锁定</title>
</head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<SCRIPT type="text/javascript">
function Save(type, status, cpnyId,statTypeCode) {

	if (type == "ATT_MO_FLAG") {
		
		if (status == 1) {
		//"确定对日考勤进行解锁？"
			if (!confirm('<ait:message  messageID="alert.att.maintenance.total.unlock_daily" module="ar"/>'))
				return;
		} else {//"日考勤锁定后将不能进行相关操作，确定？"
			if (!confirm('<ait:message  messageID="alert.att.maintenance.total.associated_daily_operation" module="ar"/>'))
			 return;
		}
		document.form1.action="/arControlServlet?operation=ar_modify&content=dailyStatus&menu_code=${menu_code}&status=" + status + "&STAT_TYPE_CODE=" + statTypeCode+"&cpnyId="+cpnyId;
	} else { 
	
		if (status == 1) {//"确定对月考勤进行解锁？"
			if (!confirm('<ait:message  messageID="alert.att.maintenance.total.unlock_monthly" module="ar"/>'))
				return;
		} else {//"月考勤锁定后将不能进行相关操作，确定？"
			if (!confirm('<ait:message  messageID="alert.att.maintenance.total.associated_monthly_operation" module="ar"/>'))
				return;
		}
		document.form1.action="/arControlServlet?operation=ar_modify&content=monthlyStatus&menu_code=${menu_code}&status=" + status + "&STAT_TYPE_CODE=" + statTypeCode+"&cpnyId="+cpnyId;
	}
	document.form1.submit();
}

function Search() {
	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=attStatus_v&menu_code=${menu_code}";
	document.form1.submit();
}

</SCRIPT>
<body>
<form name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_2.jsp"%>
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
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       	<tr>
		      <td width="15%" class="info_title_01"><!--考勤月-->
					<ait:message  messageID="display.att.maintenance.lock.timing" module="ar"/></td>
		      <td width="85%" class="info_content_00"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" /></td>
		    </tr>
			</table>
	      </td>
		</tr>
		</table>
		 
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--考勤锁定-->
					<ait:message  messageID="display.att.maintenance.lock.activity" module="ar"/></td>
			</tr>
		 </table>
		
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td height="30" class="info_title_01" width="10%"><!--考勤月-->
					<ait:message  messageID="display.att.maintenance.lock.period" module="ar"/></td>
			  <td height="30" class="info_title_01" width="10%">所属法人</td>
			  <td height="30" class="info_title_01" width="10%">员工区分</td>
		      <td height="30" class="info_title_01" width="30%"><!--日考勤锁定-->
					<ait:message  messageID="display.att.maintenance.lock.daily" module="ar"/></td>
		      <td height="30" class="info_title_01" width="30%"><!--月考勤锁定-->
					<ait:message  messageID="display.att.maintenance.lock.monthly" module="ar"/></td>
		      <td height="30" class="info_title_01" width="30%"><!--工资锁定-->
					<ait:message  messageID="display.att.maintenance.lock.payroll" module="ar"/></td>
		    </tr>
			<c:forEach items="${statusList}" var="oneResult">
		    <tr align="center"> 
		      <td>${oneResult.PA_MONTH_STR}</td>
		      <td>${oneResult.CPNYNAME}</td>
		      <td>${oneResult.STAT_TYPE}</td>
			  <c:choose>
			     <c:when test="${oneResult.ATT_MO_LOCK_FLAG == 0}">
					<td height="30" class="info_content_01"><a  href="javascript:Save('ATT_MO_FLAG','${oneResult.ATT_MO_FLAG}','${oneResult.CPNY_ID}','${oneResult.STAT_TYPE_CODE}')">
					<image src="/images/${oneResult.ATT_MO_FLAG}.gif" align="absmiddle" width="20" height="25"/>
					</a></td>
			     </c:when>
			     <c:otherwise>
					<td height="30" class="info_content_01"><!-- "请先打开月考勤" -->
					<image src="/images/${oneResult.ATT_MO_FLAG}.gif" width="20" height="25" align="absmiddle" title="<ait:message  messageID='alert.att.maintenance.total.open_attendance' module='ar'/>" />
					</td>
			     </c:otherwise>     
			  </c:choose>
			  <c:choose>
			     <c:when test="${oneResult.PA_LOCK_FLAG == 0}">
					<td height="30" class="info_content_01"><a  href="javascript:Save('ATT_MO_LOCK_FLAG','${oneResult.ATT_MO_LOCK_FLAG}','${oneResult.CPNY_ID}','${oneResult.STAT_TYPE_CODE}')">
					<image src="/images/${oneResult.ATT_MO_LOCK_FLAG}.gif" width="20" height="25" align="absmiddle"/>
					</a></td>
			     </c:when>
			     <c:otherwise>
					<td height="30" class="info_content_01"><!-- "请先打开工资" -->
					<image src="/images/${oneResult.ATT_MO_LOCK_FLAG}.gif" width="20" height="25" align="absmiddle" title="<ait:message  messageID='alert.att.maintenance.total.open_payroll' module='ar'/>" />
					</td>
			     </c:otherwise>     
			  </c:choose>
		      <td height="30" class="info_content_01">
		      <image src="/images/${oneResult.PA_LOCK_FLAG}.gif" width="20" height="25" align="absmiddle"/>
		      </td>
		      <input type="hidden" name="PA_MONTH_STR" value="${oneResult.PA_MONTH_STR}">
		    </tr>
		    </c:forEach>
		 </table>
 		
 		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(statusList) < 7}">
				<c:forEach var="i" begin="1" end="${7-fn:length(statusList)}"
					step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
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