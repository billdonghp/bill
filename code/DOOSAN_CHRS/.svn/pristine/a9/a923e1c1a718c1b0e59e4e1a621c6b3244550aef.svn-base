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
	
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
		pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
		year = Integer.parseInt(pamonth.substring(0,4));
		month = Integer.parseInt(pamonth.substring(4,6));
	}
	
	String lockFlag = request.getParameter("lockFlag");
	if (lockFlag != null && Integer.parseInt(lockFlag) == PaHelper.UNLOCK_FLAG){
	    PaHelper.openProgress(pamonth);
	}

	PaReport report = new PaReport();  
	Vector vlist = new Vector();
	vlist = report.ColumnSelect();
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
		<%@ include file="../kpa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
	   <br>
		<%--<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					工资项目分配</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		分配项目&nbsp;&nbsp;<select name="Project">
		<option value="FactoriesPayProvision">工厂工资计提</option>
		<option value="FactoriesPayProvisionalProvision">工厂临时工工资计提</option>
		<option value="FactoriesBonusesProvision">工厂奖金计提</option>
		<option value="FactoriesEndowmentInsurance">工厂养老保险</option>
		<option value="FactoriesMedicalInsurance">工厂医疗保险</option>
		<option value="FactoriesUnemployedInsurance">工厂待业保险</option>
		<option value="FactoriesInjuriesInsurance">工厂工伤保险</option>
		<option value="FactoriesGrowthInsurance">工厂生育保险</option>
		<option value="FactoriesProvidentFundInsurance">工厂住房公积金</option>
		<option value="BranchPayProvision">支社工资计提</option>
		<option value="BranchFixedBonusesProvision">支社固定奖金计提</option>
		<option value="BranchPerformanceResultsBonusesProvision">支社成果业绩奖金计提</option>
		<option value="BranchLegalRoomBonusesProvision">法律事务室诉讼奖金计提</option>
		<option value="BranchProvisionLaborInsurance">支社计提公司负担劳动保险</option>
		<option value="BranchBurdenManagementFees">支社负担国企管理费</option>
		<option value="RetirementCompensation">退职补偿金</option>
		</select>
		 基准年月
		 <select name="AnnualPaymentsYear" class="pamonth"><%
			for (int i=-4;i<=4;i++){
			%><option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
			<%}%>
		      </select>
		      <!--年-->
					<ait:message  messageID="display.mutual.year" />
		      <select name="OnPayMonth" class="pamonth"><%
			  	for (int i=1;i<=12;i++){
			%><option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select> <ait:message  messageID="display.mutual.month" />
		      输入基数&nbsp;&nbsp;&nbsp;<input name="InputBase" value=""  style="width:70px" type="text" onkeyup="CheckNumber(this.value)">
				发放工资日期&nbsp;&nbsp;&nbsp;<input type="text" name="PaymentDates" class="content" style="width:70px "  value="<%=today%>" readonly onClick="setday(this);">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="paymentPay()">数据传输</span>
		 </td>
		</tr>
		</table>
		<br>
		--%><table width="100%" border="0" cellpadding="0" cellspacing="1" >
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
					方式:방식&nbsp;&nbsp;&nbsp;&nbsp;全部&nbsp;<input type="radio" name="payOpenType" value="all" <c:if test="${param.payOpenType == 'all'}">checked</c:if> />
					&nbsp;数据&nbsp;<input type="radio" name="payOpenType" value="payData" <c:if test="${param.payOpenType == 'payData'}">checked</c:if> />
					&nbsp;邮件&nbsp;<input type="radio" name="payOpenType" value="payMail" <c:if test="${param.payOpenType == 'payMail'}">checked</c:if> />
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
					<ait:message  messageID="display.pay.maintenance.result.pay_result" module="pay"/>
				</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		    <td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		    <!-- 员工区分类型 --><%--员工区分类型&nbsp;&nbsp;
		    <ait:codeClass codeClass="EmpDiffType" name="statTypeCode"  selected="${param.statTypeCode}" onChange="changeStatType()"/>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    --%><!--工资项目--><ait:message  messageID="display.pay.maintenance.result.list" module="pay"/>
		    ( <!--全部选中--><ait:message  messageID="display.mutual.select_2" />
		    <input type="checkbox" name="checkbox" value="checkbox" onclick="checkAll(this.checked) " class="check">)<!-- </td> -->
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
		        <form name="pa" method="post" action="kpa_report_t_pa_result.jsp">
		        
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
	location.href = "kpa0205.jsp?menu_code=<%=menu_code%>" + "&statTypeCode="+document.all("statTypeCode").value ;
	}

function ImportExcel(){
	var a = false;
	var s ="";
	
	for(i=0;i<document.pa.column.length;i++){
		if (document.pa.column[i].checked){
			a = true;
			break ;
			//s+=document.pa.column[i].value+" as \""+document.pa.column1[i].value+"\",";
		}
	}
	if (a==true){
		//document.pa.sql.value ="select " + s.substring(0,s.length-1) +" from T_PA_RESULT";
		// alert(document.pa.sql.value);
		document.pa.submit();
	}
	else{
	//"请选择所需项目"
	alert('<ait:message  messageID="alert.pay.select_items" module="pay"/>');
	}
}
function Balance(){
location.href = "<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&flag=update&adminid=<%=admin.getAdminID()%>";
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
	  	alert("请选择工资开放的方式!!!") ;
	  	payOpenTypes[0].focus() ;
	  	return ;
  }
  
  if (!confirm ('是否发送工资的邮件'))
  {
  		return ;
  }
  
  var url = "/kpaControlServlet?operation=pa_OpenProgress&TABLE_NAME=KPA_HISTORY&menu_code=${param.menu_code}&PA_MONTH=" + pamonth + "&payOpenType=" + payOpenType + "&year=" + year + "&month=" + month ;
  location.href = url;
  
}

function paymentPay(){
  var pamonth = document.all("AnnualPaymentsYear").value;
  var payear=document.all("OnPayMonth").value;
  var padate=document.all("PaymentDates").value;    
  var url = "/paControlServlet?operation=paInterface&content="+document.all("Project").value+"&AnnualPaymentsYear="+pamonth+"&OnPayMonth="+payear+"&PaymentDates="+padate+"&InputBase="+document.all("InputBase").value;
  location.href = url;  
}

 function   CheckNumber(tempvalue) {   
    var   patrn=/^[0-9]+.{0,1}[0-9]{0,4}$/;
    if  (!patrn.test(tempvalue)){         
       alert("请输入数字");          
       return  false;      
      }   
       return true; 
  } 
//-->
</SCRIPT>