<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">
function Save()
{
	document.form1.action="/arControlServlet?operation=ar_vacation_add&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.fireSubmit(); 
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
		<form name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!--休假维护-->
					<ait:message  messageID="display.att.setting.dayoff.maintenance" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01"><!--休假类型-->
					<ait:message  messageID="display.att.setting.dayoff.type" module="ar"/></td>
		      <td width="85%" class="info_content_00">
		      <ait:codeClass name="vac_tp" codeClass="VacationType" remove="VacType20" /></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--开始月-->
					<ait:message  messageID="display.att.setting.dayoff.start_month" module="ar"/></td>
		      <td class="info_content_00"><input name="strt_month" type="text" size="10" maxlength="10" required numeric/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--结束月-->
					<ait:message  messageID="display.att.setting.dayoff.end_month" module="ar"/></td>
		      <td class="info_content_00"><input name="end_month" type="text" size="10" maxlength="10" required numeric/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--休假时间-->
					<ait:message  messageID="display.att.setting.dayoff.time" module="ar"/></td>
		      <td class="info_content_00"><input name="vac_day_cnt" type="text" size="10" maxlength="10" required numeric/></td>
		    </tr>
		  </table>
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
<div id='showsearch' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="emp_list" ></iframe>
</div>
<ait:xjos />
</html>
