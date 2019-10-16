<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*" %>
<html>
<head>
<script src="../js/prototype.js"></script>
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
function ImportExcelNotAllpy() {
	var date = document.form1.Date.value;
    window.open("/reports/gm_report/SpecialeateryReportNotApply.jsp?date="+date,"");
} 

function ImportExcelOt() {
	var date = document.form1.Date1.value;
    window.open("/reports/gm_report/SpecialeateryReportOt.jsp?date="+date,"");
} 
function ImportExcelNonNormal() {
	var date = document.form1.NonNormal.value;
    window.open("/reports/gm_report/SpecialeateryReportNonNormal.jsp?date="+date,"");
} 

</script>
</head>
<%@ include file="../inc/meta.jsp" %>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_return.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">特殊就餐情况统计报表</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
			<td width="10%" class="info_title_01"> 日期</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<input type="text" name="Date" value="" onClick="setday(this);" readonly="readonly" style="width:80px">
 				</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcelNotAllpy()"/>
 				</td>
 				<td align="left" width="70%" style="color: #666666;">
 					&nbsp;--未申请
 				</td>
			</tr>
			<tr>
			<td width="10%" class="info_title_01"> 日期</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<input type="text" name="Date1" value="" onClick="setday(this);" readonly="readonly" style="width:80px">
 				</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcelOt()"/>
 				</td>
 				<td align="left" width="70%" style="color: #666666;">
 					&nbsp;--加班
 				</td>
			</tr>
			<tr>
			<td width="10%" class="info_title_01"> 日期</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<input type="text" name="NonNormal" value="" onClick="setday(this);" readonly="readonly" style="width:80px">
 				</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcelNonNormal()"/>
 				</td>
 				<td align="left" width="70%" style="color: #666666;">
 					&nbsp;--非正常就餐情况
 				</td>
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
<ait:xjos />
</html>