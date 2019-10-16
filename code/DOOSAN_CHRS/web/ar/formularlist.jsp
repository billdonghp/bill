<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArStaItem,com.ait.sy.bean.AdminBean"%>
<%@ page import="java.util.ArrayList"%>
<jsp:useBean id="staItem" scope="page" class="com.ait.ar.dao.ArStaItemBean">
</jsp:useBean>
<%
	ArrayList items = staItem.getStaItemList();
	request.setAttribute("items",items);
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function changedata(para){
	formular_date.location.href = "formular_date.jsp?item_no="+para;
	}
//-->
</SCRIPT>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="/ar/inc/common_toolbar_none.jsp"%>
			
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
		 <form name="form1" method="POST">
		  <table width="100%" height="300" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td width="100" height="30" class="info_title_01"><!-- 汇总项目列表-->
		    <ait:message  messageID="display.att.setting.formula.item_list" module="ar"/></td>
		    <td width="500" align="center" class="info_title_01"><!-- 考勤项目-->
					<ait:message  messageID="display.att.setting.formula.items" module="ar"/></td>
		    <td width="60" align="center" class="info_title_01">
		    <ait:image src="/images/btn/Tool.gif"  border="0" align="absmiddle"
          	onclick="window.open('formular_util.jsp','pa_util','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width=754,height=300,left=240,top=0');" style="cursor:hand" />
		   </td>
		  </tr>
		<tr align="center" >
		    <td align="center"  style="padding:5 5 5 5 " valign="top">
			    <select name="itemno" size="20" style="width:100% " onChange="changedata(this.value)">
				  	<c:forEach items="${items}" var="oneResult" varStatus="i">
				  		<option value="${oneResult.itemNo}"><ait:content enContent="${oneResult.itemEnName}" zhContent="${oneResult.itemName}" koContent="${oneResult.itemKoName}"/></option>
				  	</c:forEach>
				</select>
			</td>
		    <td colspan="2"  align="center"  style="padding:5 5 5 5 ">
		    <iframe width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" name="formular_date"></iframe></td>
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
