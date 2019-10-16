<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.kpa.PaCalc,com.ait.i18n.MessageSource"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.itfc.IfImporter"%>
<%@ page import="com.ait.kpa.business.PaServices"%>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil"%>
<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--

function openProcess(){
  var year = document.all("year").value ;
  var month = document.all("month").value;
  var pamonth = year + month;
  var payOpenType = "payMail" ;
  //var payOpenTypes = document.all("payOpenType") ;
  /*
  for (var i = 0 ; i < payOpenTypes.length; ++i)
  {
	  	 if (payOpenTypes[i].checked)
	  	 {
	  	 	payOpenType = payOpenTypes[i].value ;
	  	 }
  }
  */
  if (payOpenType == null || payOpenType.length == 0)
  {
	  	alert("请选择工资开放的方式!!!") ;
	  	payOpenTypes[0].focus() ;
	  	return ;
  }
  
  if (!confirm ('是否发送工资补差的邮件'))
  {
  		return ;
  }
  
  var url = "/kpaControlServlet?operation=pa_OpenProgress&TABLE_NAME=KPA_DIFF_HISTORY&menu_code=${param.menu_code}&PA_MONTH=" + pamonth + "&payOpenType=" + payOpenType + "&year=" + year + "&month=" + month ;
  //alert(url) ;
  location.href = url;
  
}

function checkCalc(){
	
  var   yearS1,monthS1,yearE1,monthE1,yearS2,monthS2,yearE2,monthE2,yearS3,monthS3,yearE3,monthE3;   
  <%--
   for(var   i=0;i<document.pa.diffTypeCode.length;i++){   
          if   (document.pa.diffTypeCode[i].checked)   
          {   
          		 if   (document.pa.diffTypeCode[i].value==1) {
                  	yearS=document.pa.year11.value;
                  	monthS=document.pa.month11.value;
                  	yearE=document.pa.year12.value;
                  	monthE=document.pa.month12.value;
		  		  }   
		  		  if   (document.pa.diffTypeCode[i].value==2) { 
		          	yearS=document.pa.year21.value;
                  	monthS=document.pa.month21.value;
                  	yearE=document.pa.year22.value;
                  	monthE=document.pa.month22.value;
		  		  }   
		  		  if   (document.pa.diffTypeCode[i].value==3) {
                  	yearS=document.pa.year31.value;
                  	monthS=document.pa.month31.value;
                  	yearE=document.pa.year32.value;
                  	monthE=document.pa.month32.value;
		  		  }  
           }   
  
	}--%>
	yearS1=document.pa.year11.value;
   	monthS1=document.pa.month11.value;
   	yearE1=document.pa.year12.value;
   	monthE1=document.pa.month12.value;
   	
   	yearS2=document.pa.year21.value;
   	monthS2=document.pa.month21.value;
   	yearE2=document.pa.year22.value;
   	monthE2=document.pa.month22.value;
   	
   	yearS3=document.pa.year31.value;
   	monthS3=document.pa.month31.value;
   	yearE3=document.pa.year32.value;
   	monthE3=document.pa.month32.value;

	var KpaMonthS1 = yearS1 + monthS1;
	var KpaMonthE1 = yearE1 + monthE1;
	var KpaMonthS2 = yearS2 + monthS2;
	var KpaMonthE2 = yearE2 + monthE2;
	var KpaMonthS3 = yearS3 + monthS3;
	var KpaMonthE3 = yearE3 + monthE3;
	
	//var statTypeCode = document.pa.statTypeCode.value ;
	//var diffTypeCode = document.pa.diffTypeCode.value ;
	//var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	//var bonusNo = document.pa.bonusNo.value ;
	
	var flag = 'No' ;
	flag = 'calc' ;
	if(flag.indexOf('No') == -1)
    {
      	if(!confirm("是否进行  补差计算!!!"))
		{
			return ;
		}
    }
	
	location.href= "kpa_diff_calculate.jsp?menu_code=${param.menu_code}&yearS1=" + yearS1 + "&monthS1=" + monthS1 + "&yearE1=" + yearE1 + "&monthE1=" + monthE1
				 + "&yearS2=" + yearS2 + "&monthS2=" + monthS2 + "&yearE2=" + yearE2 + "&monthE2=" + monthE2
				 + "&yearS3=" + yearS3 + "&monthS3=" + monthS3 + "&yearE3=" + yearE3 + "&monthE3=" + monthE3
				 + "&flag=calc&pamonths1="+KpaMonthS1+"&pamonthe1="+KpaMonthE1+"&pamonths2="+KpaMonthS2+"&pamonthe2="+KpaMonthE2+"&pamonths3="+KpaMonthS3+"&pamonthe3="+KpaMonthE3 ;

	
	
	
}
<%--
function calc(){
	//var PaMonth = document.pa.year.value + document.pa.month.value;
	var diffTypeCode = document.pa.diffTypeCode.value ;
	//var bonusType = document.pa.bonusTypeCode.options[document.pa.bonusTypeCode.selectedIndex].text ;
	//var bonusNo = document.pa.bonusNo.value ;
	
	var url = "/ajaxControlServlet" ;
    var pars = "operation=checkDiffCalculate&diffTypeCode=" + diffTypeCode ;
	  
	new Ajax.Request(url,{method: 'post', parameters: pars, onComplete: checkCalc});
}--%>
-->
</SCRIPT></head>
<body>
<%
String pamonth = request.getParameter("pamonthe1");
String pamonths = request.getParameter("pamonths1");
//String yearS = request.getParameter("yearS");
//String monthS = request.getParameter("monthS");
//String yearE = request.getParameter("yearE");
//String monthE = request.getParameter("monthE");

//String bonusTypeCode = request.getParameter("bonusTypeCode") ;

%>
<form name="pa" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../kpa/inc/kpa_toolbar_total_calc.jsp"%>
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
				<td align="left" class="title1" colspan="10"><!--奖金计算--><ait:message  messageID="alert.pay.bonusCalculation" module="pay"/></td>
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
					<a href="kpa_diff_calculate.jsp?menu_code=kpa0404&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr><%--
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01"><!--员工区分-->员工区分</td>
				<td width="85%" class="info_content_00">
					<ait:codeClass codeClass="EmpDiffType" name="statTypeCode" selected="${param.statTypeCode}" onChange="changeStatType();"/>&nbsp;
				</td>
			</tr>
			--%><tr align="center">
				<td width="15%" height="30"  class="info_title_01">
				<ait:message  messageID="alert.pay.WageSupplementCalcul" module="pay"/></td>
				<td width="85%" class="info_content_00">
				<ait:date yearName="year11" monthName="month11" yearSelected="${param.year}" monthSelected="${param.month}"/>  ---  <ait:date yearName="year12" monthName="month12" yearSelected="${param.year}" monthSelected="${param.month}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">
				<ait:message  messageID="alert.pay.TaxSupplementCalcul" module="pay"/></td>
				<td width="85%" class="info_content_00">
				<ait:date yearName="year21" monthName="month21" yearSelected="${param.year}" monthSelected="${param.month}"/>  ---  <ait:date yearName="year22" monthName="month22" yearSelected="${param.year}" monthSelected="${param.month}"/>
				</td>
			</tr>
			<tr align="center">
				<td width="15%" height="30"  class="info_title_01">
				<!-- 海外补贴计算 --><ait:message  messageID="alert.pay.OverseasSubsidiesCalcul" module="pay"/></td>
				<td width="85%" class="info_content_00">
				<ait:date yearName="year31" monthName="month31" yearSelected="${param.year}" monthSelected="${param.month}"/>  ---  <ait:date yearName="year32" monthName="month32" yearSelected="${param.year}" monthSelected="${param.month}"/>
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
				SimpleMap parameterObject = new SimpleMap();
				parameterObject = ObjectBindUtil.getData(request);
				PaServices.getInstance().updateDiffDefaultParam(parameterObject);

				int numS = PaServices.getInstance().findHistoryS(parameterObject);
				int num_taxS = PaServices.getInstance().findHistoryS_tax(parameterObject);
				int num_overseaS = PaServices.getInstance().findHistoryS_oversea(parameterObject);
				
				if (numS==0)
					PaServices.getInstance().updateDiffSalaryS1(parameterObject);
				else
					PaServices.getInstance().updateDiffSalaryS(parameterObject);
					
				if (num_taxS==0)
					PaServices.getInstance().updateDiffTaxS1(parameterObject);
				else
					PaServices.getInstance().updateDiffTaxS(parameterObject);
					
				if (num_overseaS==0)
					PaServices.getInstance().updateDiffOverseaS1(parameterObject);
				else
					PaServices.getInstance().updateDiffOverseaS(parameterObject);

				
				int numE = PaServices.getInstance().findHistoryE(parameterObject);
				int num_taxE = PaServices.getInstance().findHistoryE_tax(parameterObject);
				int num_overseaE = PaServices.getInstance().findHistoryE_oversea(parameterObject);
				
				if (numE==0)
					PaServices.getInstance().updateDiffSalaryE1(parameterObject);
				else
					PaServices.getInstance().updateDiffSalaryE(parameterObject);
					
				if (num_taxE==0)
					PaServices.getInstance().updateDiffTaxE1(parameterObject);
				else
					PaServices.getInstance().updateDiffTaxE(parameterObject);
					
				if (num_overseaE==0)
					PaServices.getInstance().updateDiffOverseaE1(parameterObject);
				else
					PaServices.getInstance().updateDiffOverseaE(parameterObject);
					
				PaCalc pacalc = new PaCalc();
				outstr = pacalc.CalcDiffProcess(pamonth,pamonths) ;
			}
			%>
			<tr align="center">
				<td height="30"  class="info_title_01" ><!--计算状态--><ait:message  messageID="display.pay.maintenance.cal.status" module="pay"/></td>
				<td class="info_content_01"><ait:processBar />&nbsp;<br><%=outstr%></td>
			</tr>
		</table>
		<%
		    java.util.Calendar now = java.util.Calendar.getInstance();
			int year = now.get(now.YEAR);
			int month = now.get(now.MONTH)+1;
			if (pamonth ==null) {
				pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
			}else{
				year = Integer.parseInt(pamonth.substring(0,4));
				month = Integer.parseInt(pamonth.substring(4,6));
			}
		 %>
		 <br><br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 邮件发送 메일발송--><ait:message  messageID="display.pay.sendEmail" module="pay"/></td>
			</tr>
		 </table>
		 <c:if test="${fn:length(emailErrorList) > 0 }">
			  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	
				<c:forEach items="${emailErrorList}" var="errorMsg" varStatus="i">
					<tr align="center"><td align="center"><font color="red">${errorMsg}</font></td></tr>
				</c:forEach>
			  </table>
			</c:if>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
				<!--考勤月--><ait:message  messageID="display.mutual.attendance_month" />
				    <select name="year" ><%
					for (int i=-4;i<=4;i++){
					%><option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
					<%}%>
				      </select>
				      <!--年-->
							<ait:message  messageID="display.mutual.year" />
				      <select name="month" ><%
					  	for (int i=1;i<=12;i++){
					%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
					<%}%>
				      </select>
				   <!-- 月 -->
					<ait:message  messageID="display.mutual.month" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 
					방식:&nbsp;&nbsp;&nbsp;&nbsp;全部&nbsp;<input type="radio" name="payOpenType" value="all" <c:if test="${param.payOpenType == 'all'}">checked</c:if> />
					&nbsp;数据&nbsp;<input type="radio" name="payOpenType" value="payData" <c:if test="${param.payOpenType == 'payData'}">checked</c:if> />
					&nbsp;邮件&nbsp;<input type="radio" name="payOpenType" value="payMail" <c:if test="${param.payOpenType == 'payMail'}">checked</c:if> />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					-->
					<ait:image src="/images/btn/Salary_Open.gif"  border="0" align="absmiddle"
          	onclick="javascript:openProcess();" style="cursor:hand"  />
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
<ait:xjos />
</body>
</html>
