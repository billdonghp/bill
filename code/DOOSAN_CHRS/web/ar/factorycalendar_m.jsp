<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArCalendarType"%>
<%@ page import="com.ait.ar.bean.*" %>
<%@ page import="java.util.*,com.ait.sy.bean.*" %>
<jsp:useBean id="factory" scope="page" class="com.ait.ar.dao.ArFactoryCalendarBean">
</jsp:useBean>
<jsp:useBean id="calendarType" scope="page" class="com.ait.ar.dao.ArCalendarTypeBean">
</jsp:useBean>
<jsp:useBean id="shift" scope="page" class="com.ait.ar.dao.ArShift010Bean">
</jsp:useBean>
<%
	ArCalendarType ar = null;
	ArrayList items = calendarType.getCalendarType();
	request.setAttribute("items",items);
	String factorycalendar = null;
	
	ArShift010 shiftObject = null;
	ArrayList shiftList = shift.getShift010();
	request.setAttribute("shiftList",shiftList);
	
	int year = Integer.parseInt(request.getParameter("hyear"));
	int month = Integer.parseInt(request.getParameter("hmonth"));
	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");

	factory.setLanguage(admin1.getLanguagePreference());
	factorycalendar=factory.getCalendar(year,month,1);
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Save()
{
        document.form1.action="/arControlServlet?operation=factorycalendarupdateall&menu_code=<%=request.getParameter("menu_code")%>";
        document.form1.submit();
}
</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="/inc/common_toolbar_a.jsp"%>
			
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
		
		<!-- display content -->
		<br>
		<form name="form1" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 公司日历-->
					<ait:message  messageID="display.att.setting.calendar" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" border="1" align="left" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		 <tr align="center">
		   <td ><strong><%=year%><!--  年-->-<%=month%> <!--  月--></strong></td>
		   <td class="info_conetent_00"><!-- 是否工作-->
					<ait:message  messageID="display.att.setting.calendar.edit.work_status" module="ar"/></td>
		   <td class="info_conetent_01">
		   	  <select name="work" style="width:100px ">
		        <option value="1"><!-- 工作 --><ait:message  messageID="display.att.setting.calendar.edit.work_status2" module="ar"/></option>
		        <option value="0"><!-- 不工作 -->不工作</option>
		      </select>
		    </td>
		   <td class="info_conetent_00"><!-- 日期性质-->
					<ait:message  messageID="display.att.setting.calendar.edit.time_division" module="ar"/></td>
		   <td class="info_conetent_01">
		     <select name="type" style="width:100px ">
		     <c:forEach items="${items}" var="items">
		     	<option value="${items.typeNo}">
		     		<ait:content enContent="${items.enTypeName}" zhContent="${items.typeName}" koContent="${items.korTypeName}"/>
		     	</option>
		     </c:forEach>
		      </select>
		   </td>
		   <td class="info_conetent_00"><!-- 班次-->
					<ait:message  messageID="display.mutual.shift"/></td>
		   <td class="info_conetent_01">
		   <select name="shift" style="width:150px ">
		   		<c:forEach items="${shiftList}" var="shiftList">
		   			<option value="${shiftList.shift_no}">
		      		<ait:content enContent="${shiftList.shift_En_Name}" zhContent="${shiftList.shift_Name}" koContent="${shiftList.shift_Kor_Name}"/>
		      		</option>
		   		</c:forEach>
		    </select>
		   </td>
		</tr>
		  <tr align="center">
		    <td class="info_title_01" height="30" width="100"><font color="red"><!-- 星期日-->
					<ait:message  messageID="display.att.setting.calendar.sunday" module="ar"/></font></td>
		    <td class="info_title_01" height="30" width="100"><!-- 星期一-->
					<ait:message  messageID="display.att.setting.calendar.monday" module="ar"/></td>
		    <td class="info_title_01" height="30" width="100"><!-- 星期二-->
					<ait:message  messageID="display.att.setting.calendar.tuesday" module="ar"/></td>
		    <td class="info_title_01" height="30" width="100"><!-- 星期三-->
					<ait:message  messageID="display.att.setting.calendar.wednesday" module="ar"/></td>
		    <td class="info_title_01" height="30" width="100"><!-- 星期四-->
					<ait:message  messageID="display.att.setting.calendar.thursday" module="ar"/></td>
		    <td class="info_title_01" height="30" width="100"><!-- 星期五-->
					<ait:message  messageID="display.att.setting.calendar.friday" module="ar"/></td>
		    <td class="info_title_01" height="30" width="100"><font color="red"><!-- 星期六-->
					<ait:message  messageID="display.att.setting.calendar.saturday" module="ar"/></font></td>
		  </tr>
		  <%=factorycalendar!=null?factorycalendar:""%>
		</table>
		<input type="hidden" name="hyear" value="<%=year%>">
		<input type="hidden" name="hmonth" value="<%=month%>">
		<a name="flag"></a>
		</form>

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

</body>
</html>

