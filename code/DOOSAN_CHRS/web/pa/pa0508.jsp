<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function  synchronizePaData(){
 document.form1.action="/paControlServlet?operation=synchronizePaDataCmd&content=synchronizePaData&menu_code=${param.menu_code}";
 document.form1.submit();

}

</SCRIPT>
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
	
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
		pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
		year = Integer.parseInt(pamonth.substring(0,4));
		month = Integer.parseInt(pamonth.substring(4,6));
	}
	HttpSession session2 = request.getSession(true);
	AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
	request.setAttribute("cpnyDiff",admin2.getCpnyId());  

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form name="form1" method="post" action="">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/pa_toolbar_balance.jsp"%>
			
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
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->

	   <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"  >
			<tr>
				<td align="left" class="title1" colspan="10">
					同步财务凭证数据</td>
			</tr>
			<c:if test="${errorMsg!=null}">
			<tr>
				<td align="center" colspan="10">
					<font color="red">${errorMsg}</font></td>
			</tr>			
			</c:if>
			
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">	
		<tr><td width="*" height="30"  align="center" valign="middle" class="info_title_01">	
		工资类型区分：&nbsp;
		<select name="paType">
		<option value="1" <c:if test="${paType=='1'}">selected</c:if>>工资</option>
		<option value="2" <c:if test="${paType=='2'}">selected</c:if>>奖金</option>		
		</select>
		&nbsp;&nbsp;
		员工区分：&nbsp;<ait:empDiff name="statTypeCode" cpnyDiff="${cpnyDiff}"  selected="${statTypeCode}" />&nbsp;&nbsp;
		工资月:&nbsp;<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="synchronizePaData()">同步工资数据</span>
		</td></tr>
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
	</form>
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
