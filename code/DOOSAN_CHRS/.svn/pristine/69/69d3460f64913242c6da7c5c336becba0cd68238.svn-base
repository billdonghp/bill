<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.kpa.PaCalc,com.ait.i18n.MessageSource"%>
<%@ page import="com.ait.itfc.IfImporter"%>
<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function calc(){
	var PaMonth = document.pa.year.value+document.pa.month.value;

		if(confirm("是否进行 " + PaMonth + "月 工资计算!!!"))
		{
			document.pa.flag.value = "calc" ;
			document.pa.pamonth.value = PaMonth ;
			beforeSubmit();
			document.pa.submit();
			afterSubmit();
			//location.href="pa0204.jsp?menu_code=pa0204&flag=calc&pamonth="+PaMonth + "&statTypeCode=" + statTypeCode + "&retroactiveTaxType=" + retroactiveTaxType ;
		}
		
}

//-->
</SCRIPT></head>
<body>
<%
String pamonth = "";
String flag = "";
pamonth = request.getParameter("pamonth");
flag = request.getParameter("flag");
if (pamonth != null) {
	if (flag != null) {
	flag = "calc";
}
}

java.util.Calendar now = java.util.Calendar.getInstance();
int year = now.get(Calendar.YEAR);
int month = now.get(Calendar.MONTH) + 1;
if (pamonth == null || pamonth.equals("")) {
	pamonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
} else {
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
}

%>
<form name="pa" method="post">
<input type="hidden" name="menu_code" value="${param.menu_code}"/>
<input type="hidden" name="flag" value=""/>
<input type="hidden" name="pamonth" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../kpa/inc/pa_toolbar_total_calc.jsp"%>
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
		<%@ include file="../kpa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--工资计算--><ait:message  messageID="display.pay.maintenance.cal.cal" module="pay"/></td>
			</tr>
		</table>
		<%
		if ("sync".equals(flag)) {
			IfImporter importer = new IfImporter();
			importer.start();
		}
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--同步人事数据--><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></td>
				<td class="info_content_01">
					<a href="kpa0204.jsp?menu_code=kpa0204&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--工资计算--><ait:message  messageID="display.pay.maintenance.cal.cal" module="pay"/></td>
				<td width="85%" class="info_content_00">
					<select name="year" class="pamonth">
						<%for (int i = -4; i <= 4; i++){%>
							<option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year+i%></option>
						<%}%>
					</select>&nbsp;
					<!--年--><ait:message  messageID="display.mutual.year" />&nbsp;
					<select name="month" class="pamonth">
						<%for (int i=1;i<=12;i++){%>
							<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
						<%}%>
					</select>&nbsp;
					<!--月--><ait:message  messageID="display.mutual.month" />&nbsp;
				</td>
			</tr>
			<%--<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--补税方式-->补税方式</td>
				<td width="85%" class="info_content_00">
					<ait:codeClass codeClass="RetroactiveTaxType" name="retroactiveTaxType" all="all"/>&nbsp;
				</td>
			</tr>
			--%><%//请进行计算
			HttpSession session2 = request.getSession(true);
			AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
			MessageSource messageSource = new MessageSource("pay", admin2.getLocale(), "UTF-8");
			String outstr=messageSource.getMessage("display.pay.maintenance.cal.start_cal");
			if (flag !=null && flag.equals("calc")){
				PaCalc pacalc = new PaCalc();
				outstr = pacalc.CalcProcess(pamonth);
			}
			%>
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--计算状态--><ait:message  messageID="display.pay.maintenance.cal.status" module="pay"/></td>
				<td class="info_content_01"><ait:processBar />&nbsp;<br><%=outstr%></td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="9" step="1">
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<ait:xjos />
</body>
</html>