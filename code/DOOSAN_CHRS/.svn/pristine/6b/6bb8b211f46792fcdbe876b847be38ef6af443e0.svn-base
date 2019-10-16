<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Vector,com.ait.kpa.PaReport"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.sy.common.PaHelper"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
	String pamonth = request.getParameter("pamonth");
	//String statTypeCode = request.getParameter("statTypeCode");
	String statTypeCode = "";
	
	String lockFlag = request.getParameter("lockFlag");
	if (lockFlag != null && Integer.parseInt(lockFlag) == PaHelper.UNLOCK_FLAG){
	    PaHelper.openProgress(pamonth);
	}
	
	PaReport report = new PaReport();  
	Vector vlist = new Vector();
	vlist = report.paBonusColumnSelect(statTypeCode);
	request.setAttribute("vlist",vlist);
	String column = "";
	
	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	String today=timeFormatter.format(d);
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../kpa/inc/pa_toolbar_balance.jsp"%>
			
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
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 邮件发送 --></td>
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
				<!--考勤月-->
				    <ait:message  messageID="display.mutual.attendance_month" />
			<ait:date yearName="year" monthName="month" monthSelected="${param.month}" yearSelected="${param.year}"  />&nbsp;&nbsp; 
					<!-- 
					<ait:message  messageID="alert.pay.way" module="pay"/>:&nbsp;&nbsp;&nbsp;&nbsp;<ait:message  messageID="alert.pay.all" module="pay"/>&nbsp;<input type="radio" name="payOpenType" value="all" <c:if test="${param.payOpenType == 'all'}">checked</c:if> />
					&nbsp;<ait:message  messageID="alert.pay.data" module="pay"/>&nbsp;<input type="radio" name="payOpenType" value="payData" <c:if test="${param.payOpenType == 'payData'}">checked</c:if> />
					&nbsp;<ait:message  messageID="alert.pay.email" module="pay"/>&nbsp;<input type="radio" name="payOpenType" value="payMail" <c:if test="${param.payOpenType == 'payMail'}">checked</c:if> />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 -->
					<ait:image src="/images/btn/Salary_Open.gif"  border="0" align="absmiddle"
          	onclick="javascript:openProcess();" style="cursor:hand"  />
				</td>
			</tr>
		</table>
		
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					<ait:message  messageID="display.pay.maintenance.result.pay_result" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		    <td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		    <!-- 员工区分类型 --><%--员工区分类型&nbsp;&nbsp;
		    <ait:codeClass codeClass="EmpDiffType" name="statTypeCode"  selected="${param.statTypeCode}" onChange="changeStatType()"/>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    --%><!--奖金项目--><ait:message  messageID="alert.pay.prizeItemName" module="pay"/>
		    ( <!--全部选中--><ait:message  messageID="display.mutual.select_2" />
		    <input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this.checked) " class="check">)
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <ait:image src="/images/btn/Balance.gif"  border="0" align="absmiddle" onclick="javascript:Balance();" style="cursor:hand" />
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand" />
		   </td> 
		 
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		  <tr align="center">
		    <td height="30" colspan="2"  align="center" class="info_content_01">
		      <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		        <form name="pa" method="post" action="kpa_report_t_pa_bonus_result.jsp">
		        
		        <tr  height="30" align="left">
		        <%
					if (vlist.size()!=0){
					int i=0;
					%>
					<c:forEach items="${vlist}" var="vlist" >
						<td>
							<input name="column" type="checkbox" value="${vlist.columnId}" class="check">
						</td>
						<td>
							<ait:content enContent="${vlist.columnEnName}" zhContent="${vlist.columnname}" koContent="${vlist.columnKoName}"/>
						</td>
						<% if((i+1)%4==0){%></tr><tr><%} %>
						<%i++; %>
					</c:forEach>
					<%} %>
				</tr>
				</tr>
				<input name="sql" type="hidden">
				</form>
		      </table></td>
		  </tr>
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
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkAll(para){
// alert(para);
	if(para){
		for(i=0;i<document.pa.column.length;i++){
			document.pa.column[i].checked=true;
		}
	}else{
		for(i=0;i<document.pa.column.length;i++){
			document.pa.column[i].checked=false;
		}
	}
}

function changeStatType(){
	location.href = "pa_bonus_result.jsp?menu_code=${param.menu_code}" + "&statTypeCode="+document.all("statTypeCode").value ;
	}


function ImportExcel(){
	var a = false;
	var s ="";
	for(i=0;i<document.pa.column.length;i++){
		if (document.pa.column[i].checked){
			a = true;
			//s+=document.pa.column[i].value+" as \""+document.pa.column1[i].value+"\",";
		}
	}
	if (a==true){
		//document.pa.sql.value ="select " + s.substring(0,s.length-1) +" from T_PA_BONUS_RESULT";
		// alert(document.pa.sql.value);
		document.pa.submit();
	}
	else{
	//"请选择所需项目"
	alert('<ait:message  messageID="alert.pay.select_items" module="pay"/>');
	}
}
function Balance(){
	location.href = "/kpaControlServlet?operation=pa_bonus_result&menu_code=<%=menu_code%>";
}

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
	  	//alert("请选择工资开放的方式!!!") ;
	  	alert('<ait:message  messageID="alert.pay.select_wages_open_Way" module="pay"/>');
	  	payOpenTypes[0].focus() ;
	  	return ;
  }
  
  if (!confirm ('是否发送奖金的邮件'))
  {
  		return ;
  }
  
  var url = "/kpaControlServlet?operation=pa_OpenProgress&TABLE_NAME=KPA_BONUS_HISTORY&menu_code=${param.menu_code}&PA_MONTH=" + pamonth + "&payOpenType=" + payOpenType + "&year=" + year + "&month=" + month ;
  
  //alert(url) ;
  location.href = url;
  
}

</SCRIPT>