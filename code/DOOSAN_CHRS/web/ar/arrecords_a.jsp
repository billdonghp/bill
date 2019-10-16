<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String currentDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/calendarcode.js"></script>
<script language="javascript">
function Save()
{
  if(document.form1.empID.value=="")
  {
  	alert("请输入员工号");
	return false;
  }
  document.form1.action="/arControlServlet?operation=arrecordadd&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}
</script>
</head>
<body>
<form name="form1" method="POST">

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
				<td align="left" class="title1" colspan="10">添加刷卡记录</td>
			</tr>
		</table>
		<table width="100%" border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
		      <td colspan="2" class="info_title_00">
		<%
						if(request.getParameter("seq") != null){
							out.print("<font color=\"red\" size=\"5\">添加 进门记录:" + request.getParameter("insertIn") + 
												" 出门记录:" + request.getParameter("insertOut") + "<font>");
						}else{
							out.print("&nbsp;");
						}
		%>
					</td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01">员工号</td>
		      <td width="85%"><input name="empID" type="text" id="empID" size="10" /></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">上班刷卡</td>
		      <td><table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
		        <tr>
		          <td>
		          	<input name="enterDate" type="text" size="10" maxlength="10" value="<%=currentDateStr %>" readonly="true" />
		            <input name="InH" type="text"  size="2" maxlength="2" />：
		         	<input name="InM" type="text"  size="2" maxlength="2" />：
		      		<input name="InS" type="text"  value="00" size="2" maxlength="2" />
		            &nbsp;&nbsp;&nbsp;<img src="../images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onclick="showCalendar('form1','enterDate');" Style="cursor:hand" />
		            </td>
		        </tr>
		      </table></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">下班刷卡</td>
		      <td>
						<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0">
		        <tr>
		          <td>
		          	<input name="outDate" type="text" size="10" maxlength="10" value="<%=currentDateStr %>" readonly="true" />
		            <input name="outH" type="text"  size="2" maxlength="2" />：
		        	<input name="outM" type="text"  size="2" maxlength="2" />：
		      		<input name="outS" type="text"  value="00" size="2" maxlength="2" />
		            &nbsp;&nbsp;&nbsp;<img src="../images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onclick="showCalendar('form1','outDate');" Style="cursor:hand" />
		           </td>
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
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
