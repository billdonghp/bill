<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.pa.PaCalc,com.ait.i18n.MessageSource"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.itfc.IfImporter"%>
<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
</head>
<body>
<%
String result = "" ;
String pamonth = request.getParameter("year") + request.getParameter("month");

String statTypeCode = request.getParameter("statTypeCode") ;

String bonusTypeCode = request.getParameter("bonusTypeCode") ;

String bonusNo = request.getParameter("bonusNo") ;

%>
<form name="pa" method="post" action="pa_bonus_calculate.jsp">
<input type="hidden" name="menu_code" value="${param.menu_code}"/>
<input type="hidden" name="flag" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/pa_toolbar_total_calc.jsp"%>
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--奖金计算-->奖金计算</td>
			</tr>
		</table>
		<%
		if ("sync".equals(request.getParameter("flag"))) {
			IfImporter importer = new IfImporter();
			result=importer.start();
			%>
			<script lang="javascript">
			alert('<%= result%>');		
		    </script>
		<%}%>		
		<%//请进行计算
			HttpSession session2 = request.getSession(true);
			request.setAttribute("cpnyDiff",admin.getCpnyId());
			MessageSource messageSource = new MessageSource("pay", admin.getLocale(), "UTF-8");
			String outstr=messageSource.getMessage("display.pay.maintenance.cal.start_cal");
			if (request.getParameter("flag") !=null && request.getParameter("flag").equals("calc")){
				PaCalc pacalc = new PaCalc();
				outstr = pacalc.CalcBonusProcess(pamonth,statTypeCode,admin.getCpnyId(), bonusTypeCode,bonusNo) ;
			}
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--同步人事数据--><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></td>
				<td class="info_content_01">
					<a href="pa_bonus_calculate.jsp?menu_code=pa0304&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--员工区分-->员工区分</td>
				<td width="85%" class="info_content_00">
					<ait:empDiff  name="statTypeCode"  cpnyDiff="${cpnyDiff}" selected="${param.statTypeCode}" onChange="changeStatType();"/>&nbsp;
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--奖金计算-->奖金计算</td>
				<td width="85%" class="info_content_00"><ait:date yearName="year" monthName="month" yearSelected="${param.year}" monthSelected="${param.month}"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<ait:image src="/images/btn/Cal_Salary1.gif"  border="0" align="absmiddle" onclick="javascript:calc();" style="cursor:hand"  /> 
				</td>
			</tr>
			<tr align="center">
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
<SCRIPT LANGUAGE="JavaScript">
<!--

function checkCalc(XmlHttpRequest){
	var PaMonth = document.pa.year.value + document.pa.month.value;
	var statTypeCode = document.pa.statTypeCode.value ;
	var bonusTypeCode = document.pa.bonusTypeCode.value ;
	var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	var bonusNo = document.pa.bonusNo.value ;
	
	var flag = 'No' ;
	flag = XmlHttpRequest.responseText ;
	if(flag.indexOf('No') == -1)
    {
      	if(!confirm("此计算已经计算过,是否删除数据,进行 [" + PaMonth + "]-[" + bonusType + "]-[" + document.pa.bonusNo.value + "] 重新计算!!!"))
		{
			return ;
		}
    }
	else if(!confirm("是否,进行 [" + PaMonth + "]-[" + bonusType + "]-[" + document.pa.bonusNo.value + "] 计算!!!"))
	{
		return ;
	}
	
		document.pa.flag.value = "calc" ;
	
		beforeSubmit();
		document.pa.fireSubmit();
		afterSubmit();
	
}

function calc(){
	var PaMonth = document.pa.year.value + document.pa.month.value;
	var statTypeCode = document.pa.statTypeCode.value ;
	var bonusTypeCode = document.pa.bonusTypeCode.value ;
	var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	var bonusNo = document.pa.bonusNo.value ;
	
	var url = "/ajaxControlServlet" ;
    var pars = "operation=checkPaBonusCalculate&cpnyId=<%= admin.getCpnyId() %>pamonth="+PaMonth + "&statTypeCode=" + statTypeCode + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo ;
	  
	new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: checkCalc});
}

function changeStatType(){
	location.href = "pa_bonus_calculate.jsp?menu_code=${param.menu_code}&year=" + document.pa.year.value + "&month=" + document.pa.month.value + "&statTypeCode="+document.all("statTypeCode").value ;
}
-->
</SCRIPT>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<ait:xjos />
</body>
</html>