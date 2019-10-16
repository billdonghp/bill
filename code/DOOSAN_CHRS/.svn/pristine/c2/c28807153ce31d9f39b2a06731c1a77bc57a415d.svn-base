<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Vector,com.ait.pa.PaReport"%>
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
	String statTypeCode = request.getParameter("statTypeCode");
	
	String lockFlag = request.getParameter("lockFlag");
	/*
	if (lockFlag != null && Integer.parseInt(lockFlag) == PaHelper.UNLOCK_FLAG){
	    PaHelper.openProgress(pamonth);
	}
	*/
	
	HttpSession session2 = request.getSession(true);
	AdminBean admin2 = (AdminBean) session2.getAttribute("admin");
	request.setAttribute("cpnyDiff",admin2.getCpnyId());	
	PaReport report = new PaReport();  
	Vector vlist = new Vector();
	vlist = report.paBonusColumnSelect();
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
		<c:if test="${cpnyDiff=='78000000'||cpnyDiff=='63000000'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">数据传送</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
				<!--考勤月-->
				    <ait:message  messageID="display.mutual.attendance_month" />
			<ait:date yearName="year" monthName="month" monthSelected="${param.month}" yearSelected="${param.year}"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="color:red; cursor:pointer;" onClick="openProcess();">向韩国传输数据</span>
				</td>
			</tr>
		</table>
		</c:if>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--计算结果-->
					<ait:message  messageID="display.pay.maintenance.result.pay_result" module="pay"/></td>
			</tr>
		 </table>
	<form name="pa" method="post" action="pa_report_t_pa_bonus_result.jsp" target="_blank">
		<input type="hidden" name="pamonth" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		    <td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		    <!--考勤月--><ait:message  messageID="display.mutual.attendance_month" />
		    <ait:date monthName="month_result" yearName="year_result" />
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <ait:codeClass codeClass="BonusType" name="bonusTypeCode" selected="${param.bonusTypeCode}" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="bonusNo">
				<c:forEach var="i" begin="1" end="9" step="1">
					<option value="${i}" <c:if test="${i == param.bonusNo}">selected</c:if> >${i}</option>
				</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- 员工区分类型 -->员工区分类型&nbsp;&nbsp;
		    <ait:empDiff name="statTypeCode"  cpnyDiff="${cpnyDiff}" selected="${param.statTypeCode}" /> <!-- onChange="changeStatType()" -->
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!--奖金项目-->奖金项目
		    ( <!--全部选中--><ait:message  messageID="display.mutual.select_2" />
		    <input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this.checked) " class="check">)
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <!-- 
		    <ait:image src="/images/btn/Balance.gif"  border="0" align="absmiddle" onclick="javascript:Balance();" style="cursor:hand" />
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	 -->
          	<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:ImportExcel();" style="cursor:hand" />
		   </td> 
		 
		</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		  <tr align="center">
		    <td height="30" colspan="2"  align="center" class="info_content_01">
		      <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		        
		        
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
				
		      </table></td>
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
	var year = document.all("year_result").value ;
    var month = document.all("month_result").value;
    var pamonth = year + month;

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
		document.pa.pamonth.value = pamonth ;
		document.pa.submit();
	}
	else{
	//"请选择所需项目"
	alert('<ait:message  messageID="alert.pay.select_items" module="pay"/>');
	}
}
function Balance(){
	location.href = "/paControlServlet?operation=pa_bonus_result&menu_code=<%=menu_code%>";
}

function openProcess(){
  var year = document.all("year").value ;
  var month = document.all("month").value;
  var pamonth = year + month;
  
  if (!confirm ('向韩国传送 ' + pamonth + ' 的奖金数据!!!' ))
  {
  		return ;
  }
  
  var url = "/paControlServlet?operation=pa_OpenProgress&TABLE_NAME=PA_BONUS_HISTORY&menu_code=${param.menu_code}&lockFlag=<%=PaHelper.UNLOCK_FLAG%>&PA_MONTH=" 
  			+ pamonth + "&year=" + year + "&month=" + month ;
  
  //alert(url) ;
  location.href = url;
  
}

</SCRIPT>