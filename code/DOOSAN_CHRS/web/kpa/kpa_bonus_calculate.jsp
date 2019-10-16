<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.kpa.PaCalc,com.ait.i18n.MessageSource"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
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

function checkCalc(XmlHttpRequest){
	var PaMonth = document.pa.year.value + document.pa.month.value;
	//var statTypeCode = document.pa.statTypeCode.value ;
	var bonusTypeCode = document.pa.bonusTypeCode.value ;
	//var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	//var bonusNo = document.pa.bonusNo.value ;
	
	var flag = 'No' ;
	flag = XmlHttpRequest.responseText ;
	if(flag.indexOf('No') == -1)
    {
      	if(!confirm("此计算已经计算过,是否删除数据,进行 [" + PaMonth + "]- 奖金 - 重复计算!!!"))
		{
			return ;
		}
    }
	
	location.href= "kpa_bonus_calculate.jsp?menu_code=${param.menu_code}&year=" + document.pa.year.value + "&month=" + document.pa.month.value + "&flag=calc&pamonth="+PaMonth 
				 + "&bonusTypeCode=" + bonusTypeCode ;
	
}

function calc(){
	var PaMonth = document.pa.year.value + document.pa.month.value;
	var bonusTypeCode = document.pa.bonusTypeCode.value ;
	//var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	//var bonusNo = document.pa.bonusNo.value ;
	
	var url = "/ajaxControlServlet" ;
    var pars = "operation=checkPaBonusCalculate&pamonth="+PaMonth + "&bonusTypeCode=" + bonusTypeCode ;
	  
	new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: checkCalc});
}

function changeStatType(){
	location.href = "kpa_bonus_calculate.jsp?menu_code=${param.menu_code}&year=" + document.pa.year.value + "&month=" + document.pa.month.value;
}
-->
</SCRIPT></head>
<body>
<%
String pamonth = request.getParameter("pamonth");

String bonusTypeCode = request.getParameter("bonusTypeCode") ;

%>
<form name="pa" method="post">
<input name="bonusTypeCode" type="hidden" value="BonusType02">
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
				<td align="left" class="title1" colspan="10"><!--奖金计算-->
				<ait:message  messageID="alert.pay.bonusCalculation" module="pay"/>
				</td>
			</tr>
		</table>
		<%
		if ("sync".equals(request.getParameter("flag"))) {
			IfImporter importer = new IfImporter();
			importer.start();
		}
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--同步人事数据--><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></td>
				<td class="info_content_01">
					<a href="kpa_bonus_calculate.jsp?menu_code=kpa0304&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr><%--
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--员工区分-->员工区分</td>
				<td width="85%" class="info_content_00">
					<ait:codeClass codeClass="EmpDiffType" name="statTypeCode" selected="${param.statTypeCode}" onChange="changeStatType();"/>&nbsp;
				</td>
			</tr>
			--%><tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--奖金计算--><ait:message  messageID="alert.pay.bonusCalculation" module="pay"/></td>
				<td width="85%" class="info_content_00"><ait:date yearName="year" monthName="month" yearSelected="${param.year}" monthSelected="${param.month}"/>
				</td>
			</tr>
			<%--<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--奖金名称-->奖金名称</td>
				<td width="85%" class="info_content_00">
				<ait:codeClass codeClass="BonusType" name="bonusTypeCode" selected="${param.bonusTypeCode}" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="bonusNo">
					<c:forEach var="i" begin="1" end="9" step="1">
						<option value="${i}" <c:if test="${i == param.bonusNo}">selected</c:if> >${i}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			--%><%//请进行计算
			HttpSession session2 = request.getSession(true);
			AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
			MessageSource messageSource = new MessageSource("pay", admin2.getLocale(), "UTF-8");
			String outstr=messageSource.getMessage("display.pay.maintenance.cal.start_cal");
			if (request.getParameter("flag") !=null && request.getParameter("flag").equals("calc")){
				PaCalc pacalc = new PaCalc();
				outstr = pacalc.CalcBonusProcess(pamonth,bonusTypeCode) ;
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
</body>
</html>