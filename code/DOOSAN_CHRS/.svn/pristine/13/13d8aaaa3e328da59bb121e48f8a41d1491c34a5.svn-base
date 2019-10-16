<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.pa.PaCalc,com.ait.i18n.MessageSource"%>
<%@ page import="com.ait.itfc.IfImporter"%>
<%@ page import="com.ait.util.StringUtil"%>
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

String pamonth = StringUtil.checkNull(request.getParameter("normal_year")) + StringUtil.checkNull(request.getParameter("normal_month"));
String statTypeCode = request.getParameter("statTypeCode") ;
String calType = request.getParameter("calType") ;

String retroactiveTaxType = StringUtil.checkNull(request.getParameter("retroactiveTaxType")) ;
String specialPaMonth = StringUtil.checkNull(request.getParameter("special_year")) + StringUtil.checkNull(request.getParameter("special_month"));
String retroactiveTaxPaMonth = StringUtil.checkNull(request.getParameter("retroactiveTax_year")) + StringUtil.checkNull(request.getParameter("retroactiveTax_month"));

java.util.Calendar now = java.util.Calendar.getInstance();
int year = now.get(Calendar.YEAR);
int month = now.get(Calendar.MONTH) + 1;

if (pamonth == null || pamonth.length() == 0) {
	pamonth = year + ("0" + month).substring(("0" + month).length() - 2, ("0" + month).length());
} else {
	year = Integer.parseInt(pamonth.substring(0,4));
	month = Integer.parseInt(pamonth.substring(4,6));
}
%>
<form name="pa" method="post">
<input type="hidden" name="menu_code" value="${param.menu_code}"/>
<input type="hidden" name="flag" value=""/>
<input type="hidden" name="calType" value=""/>
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
				<td align="left" class="title1" colspan="10"><!--工资计算--><ait:message  messageID="display.pay.maintenance.cal.cal" module="pay"/></td>
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
		<%}		
		%>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--同步人事数据--><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></td>
				<td class="info_content_01">
					<a href="pa0204.jsp?menu_code=pa0204&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr>
			<%//请进行计算
			HttpSession session2 = request.getSession(true);
			AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
			MessageSource messageSource = new MessageSource("pay", admin2.getLocale(), "UTF-8");
			request.setAttribute("cpnyDiff",admin2.getCpnyId());
			String outstr=messageSource.getMessage("display.pay.maintenance.cal.start_cal");
			if (request.getParameter("flag") !=null && request.getParameter("flag").equals("calc")){
				PaCalc pacalc = new PaCalc();
				outstr = pacalc.CalcProcess(pamonth,statTypeCode,calType,admin.getCpnyId(),retroactiveTaxType,specialPaMonth,retroactiveTaxPaMonth);
			}
			%>
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--计算状态--><ait:message  messageID="display.pay.maintenance.cal.status" module="pay"/></td>
				<td class="info_content_01"><ait:processBar />&nbsp;<br><%=outstr%></td>
			</tr>
			
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--员工区分-->员工区分</td>
				<td width="85%" class="info_content_00" id="statTypeCodeT">
					<ait:empDiff name="statTypeCode" selected="${param.statTypeCode}" cpnyDiff="${cpnyDiff}"/>&nbsp;
				</td>
			</tr>
			
			<tr align="center"><td  class="info_content_00" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
			
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--工资计算--><ait:message  messageID="display.pay.maintenance.cal.cal" module="pay"/></td>
				<td width="85%" class="info_content_00">
					<select name="normal_year" >
						<%for (int i = -4; i <= 4; i++){%>
							<option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year+i%></option>
						<%}%>
					</select>&nbsp;
					<!--年--><ait:message  messageID="display.mutual.year" />&nbsp;
					<select name="normal_month" >
						<%for (int i=1;i<=12;i++){%>
							<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
						<%}%>
					</select>&nbsp;
					<!--月--><ait:message  messageID="display.mutual.month" />&nbsp;&nbsp;&nbsp;&nbsp;
					<ait:image src="/images/btn/Cal_Salary.gif"  border="0" align="absmiddle" onclick="javascript:normal_calc();" style="cursor:hand"  />
				</td>
			</tr>
			<tr align="center"><td  class="info_content_00" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--补税计算-->补税计算</td>
				<td width="85%" class="info_content_00">
					方式:&nbsp;&nbsp;<ait:codeClass codeClass="RetroactiveTaxType" name="retroactiveTaxType" selected="${param.retroactiveTaxType}" all="all"/>&nbsp;&nbsp;&nbsp;&nbsp;
					计算月份：<ait:date yearName="special_year" monthName="special_month" yearSelected="${param.special_year}" monthSelected="${param.special_month}"/>&nbsp;&nbsp;&nbsp;&nbsp;
					补税月份：<ait:date yearName="retroactiveTax_year" monthName="retroactiveTax_month" yearSelected="${param.retroactiveTax_year}" monthSelected="${param.retroactiveTax_month}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--操作-->操作</td>
				<td width="85%" class="info_content_00" >
					<ait:image src="/images/btn/Cal_Salary2.gif"  border="0" align="absmiddle" onclick="javascript:special_calc();" style="cursor:hand"  />&nbsp;&nbsp;&nbsp;&nbsp;
					<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand" />
				</td>
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

function ImportExcel(){
	var specialMonth = document.pa.special_year.value + document.pa.special_month.value;
	var retroactiveTaxMonth = document.pa.retroactiveTax_year.value+document.pa.retroactiveTax_month.value;
	
	var statTypeCode = document.pa.statTypeCode.value ;
	var statType = "" ;
	if (document.pa.statTypeCode.type == 'hidden'){
		statType = $("statTypeCodeT").innerText ;
	}else{
		statType = document.pa.statTypeCode.options[document.pa.statTypeCode.selectedIndex].text ;
	}
	var retroactiveTaxType = document.pa.retroactiveTaxType.value ;
	var retroactiveTax = document.pa.retroactiveTaxType.options[document.pa.retroactiveTaxType.selectedIndex].text ;
	
	if(retroactiveTaxType != null && retroactiveTaxType.length > 0)
	{
		if(!confirm("是否导出 [" + specialMonth + "]-[" + statType + "]-[" + retroactiveTax + "] 的Excel!!!"))
		{
			return ;
		}
														
		var url = "/reportControlServlet?operation=pa_detail_replenishment&paMonth=" + specialMonth + "&statTypeCode=" 
     			 + statTypeCode + "&retroactiveTaxType=" + retroactiveTaxType + "&retroactiveTaxMonth=" + retroactiveTaxMonth  ;
		
		window.open(url ,"");
	}
	else
	{
		alert("请选择补税类型!!!") ;
	}
}


function special_checkCalc(XmlHttpRequest){
	var specialMonth = document.pa.special_year.value + document.pa.special_month.value;
	var retroactiveTaxMonth = document.pa.retroactiveTax_year.value+document.pa.retroactiveTax_month.value;
	
	var statTypeCode = document.pa.statTypeCode.value ;
	var statType = "" ;
	if (document.pa.statTypeCode.type == 'hidden'){
		statType = $("statTypeCodeT").innerText ;
	}else{
		statType = document.pa.statTypeCode.options[document.pa.statTypeCode.selectedIndex].text ;
	}
	var retroactiveTaxType = document.pa.retroactiveTaxType.value ;
	var retroactiveTax = document.pa.retroactiveTaxType.options[document.pa.retroactiveTaxType.selectedIndex].text ;
	
	var flag = XmlHttpRequest.responseText ;
	if(flag.indexOf('No') == -1)
    {
     	if(!confirm("此计算已经计算过,是否删除数据,进行 [" + specialMonth + "]-[" + statType + "]-[" + retroactiveTax + "] 重复计算!!!"))
		{
			return ;
		}
    }
    else if(!confirm("是否,进行 [" + specialMonth + "]-[" + statType + "]-[" + retroactiveTax + "] 计算!!!"))
	{
		return  ;
	}
	
	document.pa.flag.value = "calc" ;
	document.pa.calType.value = "special" ;
		
	beforeSubmit();
	document.pa.fireSubmit();
	afterSubmit();
    
    //location.href="pa0204.jsp?menu_code=pa0204&flag=calc&pamonth="+PaMonth + "&statTypeCode=" + statTypeCode + "&retroactiveTaxType=" + retroactiveTaxType ;
  
}

function special_calc(){
	var specialMonth = document.pa.special_year.value + document.pa.special_month.value;
	var retroactiveTaxMonth = document.pa.retroactiveTax_year.value+document.pa.retroactiveTax_month.value;
	
	var statTypeCode = document.pa.statTypeCode.value ;
	var statType = "" ;
	if (document.pa.statTypeCode.type == 'hidden'){
		statType = $("statTypeCodeT").innerText ;
	}else{
		statType = document.pa.statTypeCode.options[document.pa.statTypeCode.selectedIndex].text ;
	}
	var retroactiveTaxType = document.pa.retroactiveTaxType.value ;
	var retroactiveTax = document.pa.retroactiveTaxType.options[document.pa.retroactiveTaxType.selectedIndex].text ;
	
	if(retroactiveTaxType != null && retroactiveTaxType.length > 0)
	{
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=checkPaCalculate&calType=special&cpnyId=<%= admin.getCpnyId() %>&pamonth=" + specialMonth + "&statTypeCode=" 
     			 + statTypeCode + "&retroactiveTaxType=" + retroactiveTaxType + "&retroactiveTaxMonth=" + retroactiveTaxMonth  ;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: special_checkCalc}
        );

	}
	else
	{
		alert("请选择补税类型!!!") ;
	}
}


function normal_calc(){

	var PaMonth = document.pa.normal_year.value+document.pa.normal_month.value;
	var statTypeCode = document.pa.statTypeCode.value ;
	
	var statType = "" ;
	if (document.pa.statTypeCode.type == 'hidden'){
		statType = $("statTypeCodeT").innerText ;
	}else{
		statType = document.pa.statTypeCode.options[document.pa.statTypeCode.selectedIndex].text ;
	}

	var url = "/ajaxControlServlet" ;
   	var pars = "operation=checkPaCalculate&calType=normal&cpnyId=<%= admin.getCpnyId() %>&pamonth="+PaMonth + "&statTypeCode=" + statTypeCode ;
 	
     
	new Ajax.Request(
          url,{method: 'post', parameters: pars, onComplete: normal_checkCalc}
    );
	
	//location.href="pa0204.jsp?menu_code=pa0204&flag=calc&pamonth="+PaMonth + "&statTypeCode=" + statTypeCode;			

}

function normal_checkCalc(XmlHttpRequest){
	var PaMonth = document.pa.normal_year.value+document.pa.normal_month.value;
	var statTypeCode = document.pa.statTypeCode.value ;
	var statType = "" ;
	if (document.pa.statTypeCode.type == 'hidden'){
		statType = $("statTypeCodeT").innerText ;
	}else{
		statType = document.pa.statTypeCode.options[document.pa.statTypeCode.selectedIndex].text ;
	}
	var calcFlag="";
	var arStateFlag="";
	var resultStr = XmlHttpRequest.responseText ;
	calcFlag=resultStr.split(",")[0];
	arStateFlag=resultStr.split(",")[1];
	if(arStateFlag.indexOf('No') == -1)
    {
     	if(!confirm("当月考勤汇总已锁定,是否进行 [" + PaMonth + "]-[" + statType + "] 工资计算!!!"))
		{
			return ;
		}
    }else{      
      if(!confirm("当月考勤汇总未锁定,是否进行 [" + PaMonth + "]-[" + statType + "] 工资计算!!!"))
		{
			return ;
		}    
    }	
	if(calcFlag.indexOf('No') == -1)
    {
     	if(!confirm("此计算已经计算过,是否删除数据,进行 [" + PaMonth + "]-[" + statType + "] 重新计算!!!"))
		{
			return ;
		}
    }
    else if(!confirm("是否,进行 [" + PaMonth + "]-[" + statType + "] 计算!!!"))
	{
		return ;
	}
	
	document.pa.flag.value = "calc" ;
	document.pa.calType.value = "normal" ;
		
	beforeSubmit();
	document.pa.fireSubmit();
	afterSubmit();
    
    //location.href="pa0204.jsp?menu_code=pa0204&flag=calc&pamonth="+PaMonth + "&statTypeCode=" + statTypeCode + "&retroactiveTaxType=" + retroactiveTaxType ;
  
}

//-->
</SCRIPT>


<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<ait:xjos />
</body>
</html>