<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*" %>
<jsp:useBean id="getTime" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean" />
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


function ImportExcel() {
	    document.form1.action="/gmControlServlet?operation=MonthEateryPeople&menu_code=${param.menu_code}";
		document.form1.submit();	
}

function ImportExcel1() {
	    document.form1.action="/gmControlServlet?operation=MonthEateryPeople&flag=company&menu_code=${param.menu_code}";
		document.form1.submit();	
}


function submitSB(){
        document.form1.action="/gmControlServlet?operation=EateryCount&menu_code=${param.menu_code}";
		document.form1.submit();
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
	    <table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="10"> 申报时间：<input type="text" name="APPLY_DATE" class="content" style="width:100px "  value="${applydate}" readonly onClick="setday(this);"></td>
			</tr>
		</table>
		 <table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">	
			<tr>
			<td nowrap="nowrap" class="info_title_01">项目</td>
			<td nowrap="nowrap" class="info_title_01">早餐</td>
			<td nowrap="nowrap" class="info_title_01">午餐</td>
			<td nowrap="nowrap" class="info_title_01">晚餐</td>
			<td nowrap="nowrap" class="info_title_01">夜餐</td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">签字</td>
			<input type="hidden" name="item1" value="qz">
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="qzBreakfast" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="qzLunch" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="qzDinner" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="qzNight" value="" style="width:70px"></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">通报</td>
			<input type="hidden" name="item1" value="tb">
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="tbBreakfast" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="tbLunch" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="tbDinner" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="tbNight" value="" style="width:70px"></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">加餐</td>
			<input type="hidden" name="item2" value="jc">
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="jc" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">VIP食堂</td>
			<input type="hidden" name="item2" value="vip">
			<td nowrap="nowrap" class="info_content_01"><input type="text" name="vip" value="" style="width:70px"></td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			<td nowrap="nowrap" class="info_content_01"><img src="/images/btn/submit.gif" onclick="submitSB()"/></td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">食堂消费明细表</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gmIds" value="" />
			<tr>
			<td width="10%" class="info_title_01"> 日期</td>
 				<td align="left" style="color: #666666;">
 				<ait:date yearName="year" monthName="month" yearPlus="10" />
 				</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcel()"/>
 				</td>
			</tr>
		</table>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">公司就餐人数统计表</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gmIds" value="" />
			<tr>
			<td width="10%" class="info_title_01"> 日期</td>
 				<td align="left" style="color: #666666;">
 				<ait:date yearName="year1" monthName="month1" yearPlus="10" />
 				</td>
 				<td align="left" width="10%" style="color: #666666;">
 					<img src="/images/btn/Excel_Exp.gif" onclick="ImportExcel1()"/>
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