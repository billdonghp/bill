<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.ArRecords"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArRecordsBean">
</jsp:useBean>
<%
			int no = 0;
			ArRecords info = null;
			String empId = request.getParameter("m_empId");//将要被修改的EMPID
			String rTime = request.getParameter("rTime");//将要被修改的记录的时间
			if (empId != null && 
					!empId.equalsIgnoreCase("") && 
					rTime != null && 
					!rTime.equalsIgnoreCase("")){
				info = dao.getRecords(empId, rTime);
			}else{
				//未正确或的参数跳回列表页面
				response.sendRedirect("arrecordslist.jsp");
			}
			String [] punchTime = rTime.split(" ");//拆开接受过来的参数填入下面的输入框
			String dateStr = punchTime[0];
			String [] timeStr = punchTime[1].split(":");
			String hh = timeStr[0];
			String mm = timeStr[1];
			String ss = timeStr[2];
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/calendarcode.js"></script>
<script language="javascript">
	function Save(){
	  document.form1.action="/arControlServlet?operation=arrecordupdate&no=<%=no%>&menu_code=<%=request.getParameter("menu_code")%>";
	  document.form1.submit();
	}
</script>
</head>
<body>
<%@ include file="/inc/common_toolbar_a.jsp"%>
<form name="form1" method="POST">
<input type="hidden" name="rTime" value="<%=rTime %>" />
<input type="hidden" name="empId" value="<%=empId %>" />


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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">修改刷卡记录</td>
			</tr>
		</table>
		<table width="500" border="1" cellspacing="0" cellpadding="12"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td colspan="2" class="info_title_00"><font size="4"><%=info.getDeptName() %> &nbsp;&nbsp; <%=info.getEmpNameToUnicode() %></font></td>
			</tr>
			<tr>
				<td class="info_title_01">员工号</td>
				<td><input type="text" name="modifyEmpId" size="10" value="<%=info.getEmpid() %>" /></td>
			</tr>
			<tr>
				<td class="info_title_01">刷卡时间</td>
				<td>
				<table width="100%" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
						<input name="date" type="text" size="10" maxlength="10" readonly="true" value="<%=dateStr %>"> 
						<img src="../images/calendar/calender.gif" align="absMiddle" border="0" 
							height="18" width="18" onclick="showCalendar('form1','entertime');" Style="cursor:hand">
						</td>
					</tr>
					<tr>
						<td>
							<input name="hh" type="text" size="2" maxlength="2" value="<%=hh %>"> ： 
							<input name="mm" type="text" size="2" maxlength="2" value="<%=mm %>"> ： 
							<input name="ss" type="text" value="00" size="2" maxlength="2"	 value="<%=ss %>">
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="info_title_01">进门卡或出门卡</td>
				<td>
				<select name="doorType">
					<option value="IN" <%=(info.getState().equalsIgnoreCase("IN"))?"selected":"" %>>进门卡</option>
					<option value="OUT" <%=(info.getState().equalsIgnoreCase("OUT"))?"selected":"" %>>出门卡</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="info_title_01">汇总时有效</td>
				<td>
				<select name="active">
					<option value="Y" <%=(info.getActive().equalsIgnoreCase("Y"))?"selected":"" %>>有效</option>
					<option value="N" <%=(info.getActive().equalsIgnoreCase("N"))?"selected":"" %>>无效</option>
				</select>
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb" width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
