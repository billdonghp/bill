<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.CalendarDay"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="calendarList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="calendarDay" scope="request" class="com.ait.ar.bean.CalendarDay" />
<jsp:useBean id="supervisorLock" scope="request" class="com.ait.sqlmap.util.SimpleMap" />
<jsp:useBean id="date" scope="page" class="java.util.GregorianCalendar" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>日考勤考勤员锁定</title>
<SCRIPT type="text/javascript">

function Save() {

	var flag = false ;
	
	if (document.form1.days) {
		if (document.form1.days[0]) {
			for (i=0;i<document.form1.days.length;i++)
			{
				if (document.form1.days[i].checked)
				{
					flag = true ;
					break ;
				}
			}
		}
	}
	
	if (!flag)
	{
		alert("请选择修改的记录!!!") ;
		return ; 
	}
	
	
	document.form1.action="/arControlServlet?operation=updateDetailSupervisorLock&menu_code=${param.menu_code}&actionType=update";
	document.form1.submit();
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
			<%@ include file="../inc/common_toolbar_a.jsp"%>
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
			<td class="title1">基本信息</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	         <tr>
		      <td class="info_title_01" ><!--考勤月-->
				<ait:message messageID="display.att.view.monthly.monthly" module="ar"/></td>
		        <td class="info_content_00">${year}${month}</td>
		        <input type="hidden" name="year" value="${year}" />
		        <input type="hidden" name="month" value="${month}" />
		        <input type="hidden" name="deptid" value="${deptid}" />
		        <input type="hidden" name="statTypeCode" value="${statTypeCode}" />
		        <input type="hidden" name="key" value="${key}" />
		        <input type="hidden" name="personId" value="${supervisorLock.PERSON_ID}" />
		     	<td class="info_title_01"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/></td>
		     	<td class="info_content_00">${supervisorLock.EMPID}/${supervisorLock.CHINESENAME}</td>
		     	<td class="info_title_01"><!--部门 --><ait:message messageID="display.mutual.dept" /></td>
				  <td class="info_content_00">${supervisorLock.DEPTNAME}</td>
		    </tr>
		    </table>
	      </td>
		</tr>
		</table>
 
		<br>
		<table width="100%" border="0" cellpadding="5">
		  <tr>
		    <td  class="title1">日考勤考勤员锁定
			</td> 
		  </tr>
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><font color="red"><!-- 星期日-->
					<ait:message  messageID="display.att.setting.calendar.sunday" module="ar"/></font></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期一-->
					<ait:message  messageID="display.att.setting.calendar.monday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期二-->
					<ait:message  messageID="display.att.setting.calendar.tuesday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期三-->
					<ait:message  messageID="display.att.setting.calendar.wednesday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期四-->
					<ait:message  messageID="display.att.setting.calendar.thursday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期五-->
					<ait:message  messageID="display.att.setting.calendar.friday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><font color="red"><!-- 星期六-->
					<ait:message  messageID="display.att.setting.calendar.saturday" module="ar"/></font></td>
		    </tr>
			<%
		if (calendarList.size()>0) {
			calendarDay = (CalendarDay) calendarList.get(0);
			int daysBefore = calendarDay.getCalendarDay().get(java.util.Calendar.DAY_OF_WEEK)-1;
			calendarDay = (CalendarDay) calendarList.get(calendarList.size()-1);
			int daysAfter = 7 - calendarDay.getCalendarDay().get(java.util.Calendar.DAY_OF_WEEK);
			for (int i=0;i<calendarList.size();i++) {
				calendarDay = (CalendarDay) calendarList.get(i);
				date = calendarDay.getCalendarDay();

				if (date.get(java.util.Calendar.DAY_OF_WEEK)==1 || daysBefore>0) {%>
					<tr align="center">
				<%}
				for (;daysBefore>0;daysBefore--){%>
					<td height="30" width="100">&nbsp;</td>
				<%}%>
				<td height="30" width="120" align="center">
					<table border="0" cellspacing="0" cellpadding="1" width="100%">
						<tr><td align="center" width="30%"  colspan="2">
							<%if (calendarDay.getDayTypeId() != 1) {%><font color="red"><%}%>
							<b><%=date.get(java.util.Calendar.DAY_OF_MONTH) %></b>
							<%if (calendarDay.getDayTypeId() != 1) {%></font><%}%></td>
						</tr>
						<tr><td align="center">
							<input type="checkbox" name="days" value="<%= calendarDay.getArDateStr() %>" class="check"/></td>
						<td align="center" colspan="2">
							<select name="lockFlag_<%= calendarDay.getArDateStr() %>" style="width:100%;" onChange="document.form1.days[<%=i%>].checked=true;">
								<option value="0">未锁</option>
								<option value="1" <% if (supervisorLock.getInt(calendarDay.getArDateStr()) == 1){ out.println("selected") ; } %> >锁定</option>
							</select>
						</td>
					</table>
				</td>
				<%if (date.get(java.util.Calendar.DAY_OF_WEEK)==7){%>
					</tr>
				<%}%>
			<%}
			if (daysAfter > 0) {
				for (;daysAfter>0;daysAfter--){%>
					<td height="30" width="100">&nbsp;</td>
				<%}%>
				</tr>
			<%}
		} else {%>
			<tr align="center">
				<td height="30" width="100%" colspan="7" align="center"><font color="red">请按条件进行搜索!!!</font></td>
			</tr>
		<%}%>
		
			<c:forEach var="i" begin="1" end="${3}"
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
