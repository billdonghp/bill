<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>工资维护&gt;工资结算</title>
</head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<SCRIPT type="text/javascript">
function Save(type, status) {
	//alert(status+'---'+type);	
	if (status == 1) {
	//"确定对工资进行解锁？"
		if (!confirm('<ait:message  messageID="alert.pay.unlock" module="pay"/>'))
			return;
	} else {
	//"工资锁定后将不能进行相关操作，确定？"
		if (!confirm('<ait:message  messageID="alert.pay.associated_lock" module="pay"/>'))
		 return;
	}
	
	document.form1.action="/arControlServlet?operation=ar_modify&content=payStatus&menu_code=${menu_code}&status=" + status + "&STAT_TYPE_CODE=1";
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

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--工资状态-->
					<ait:message  messageID="display.pay.maintenance.lock.status" module="pay"/></td>
			</tr>
		 </table>		
		<table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01""><!--考勤月-->
					<ait:message  messageID="display.mutual.attendance_month" /></td>
		      <td width="85%" align="left"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" /></td>
		    </tr>
		 </table>
		 <br>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td height="30" class="info_title_01" width="10%" nowrap="nowrap"><!--考勤月-->
					<ait:message  messageID="display.mutual.attendance_month" /></td>
			  <%--<td height="30" class="info_title_01" width="10%">考勤类型</td>
		      <td height="30" class="info_title_01" width="30%"><!--日考勤锁定-->
					<ait:message  messageID="display.att.maintenance.lock.daily" module="ar"/></td>
		      <td height="30" class="info_title_01" width="30%"><!--月考勤锁定-->
					<ait:message  messageID="display.att.maintenance.lock.monthly" module="ar"/></td>
		      --%><td height="30" class="info_title_01" width="30%"><!--工资锁定-->
					<ait:message  messageID="display.pay.maintenance.lock.lock_pay" module="pay"/></td>
		    </tr>
		    <c:if test="${menu_code == 'kpa0206'}">
			<c:forEach items="${kstatusList}" var="oneResult">
		    <tr align="center"> 
		      <td>${oneResult.PA_MONTH_STR}</td>
		      <%--<td>${oneResult.STAT_TYPE}</td>
			  <td height="30" class="info_content_01"><img src="/images/${oneResult.ATT_MO_FLAG}.gif" width="20" height="25" border="0"></td>
			  <td height="30" class="info_content_01"><img src="/images/${oneResult.ATT_MO_LOCK_FLAG}.gif" border="0" width="20" height="25"></td>
			  --%><td height="30" class="info_content_01"><a  href="javascript:Save('PA_LOCK_FLAG','${oneResult.PA_LOCK_FLAG}')"><img src="/images/${oneResult.PA_LOCK_FLAG}.gif" border="0" width="20" height="25"></a></td>
		      <input type="hidden" name="PA_MONTH_STR" value="${oneResult.PA_MONTH_STR}">
		    </tr>		    
		    </c:forEach>
		    </c:if>
		    <c:if test="${menu_code == 'pa0206'}">
			<c:forEach items="${statusList}" var="oneResult">
		    <tr align="center"> 
		      <td>${oneResult.PA_MONTH_STR}</td>
		      <%--<td>${oneResult.STAT_TYPE}</td>
			  <td height="30" class="info_content_01"><img src="/images/${oneResult.ATT_MO_FLAG}.gif" width="20" height="25" border="0"></td>
			  <td height="30" class="info_content_01"><img src="/images/${oneResult.ATT_MO_LOCK_FLAG}.gif" border="0" width="20" height="25"></td>
			  --%><td height="30" class="info_content_01"><a  href="javascript:Save('PA_LOCK_FLAG','${oneResult.PA_LOCK_FLAG}')"><img src="/images/${oneResult.PA_LOCK_FLAG}.gif" border="0" width="20" height="25"></a></td>
		      <input type="hidden" name="PA_MONTH_STR" value="${oneResult.PA_MONTH_STR}">
		    </tr>		    
		    </c:forEach>
		    </c:if>		    
		 </table>
		 <table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(statusList) < 8}">
				<c:forEach var="i" begin="1" end="${8-fn:length(statusList)}"
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</form>
</body>
</html>