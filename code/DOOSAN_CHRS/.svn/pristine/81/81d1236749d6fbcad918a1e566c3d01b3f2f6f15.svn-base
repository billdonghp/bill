<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="flag" class="java.lang.String" scope="request" />
<%@ page import="com.ibm.icu.text.SimpleDateFormat,com.ait.util.*,java.util.Date" %>
<html>
<head>
<ait:processBarJs />
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT LANGUAGE="JavaScript">
function ImpEMSExcel(){
if((document.form1.expressExcel.value=="EMSDetailsExcel" || document.form1.expressExcel.value=="EMSTotalExcel") && (document.form1.inputdata.value==""||document.form1.inputdata.value==null)){
alert("分摊费不能为空！");
}else{
document.form1.action="/gmControlServlet?menu_code=gm0404&operation=expressManger&content=ImpexpressExcel&flag="+document.form1.expressExcel.value;
document.form1.submit();
}
}

function ImportExcel(){
var reportType = "";
var check = document.getElementsByName("check") ;           
	
	for (i = 0 ; i < check.length ; i++){  
	  
		 if (check[i].checked){
		 	
		 	reportType = check[i].value;
		 	
		 }
	} 
	if (reportType == ""){
	//"请选择报表"
		alert("请选择报表！");
		return false;
	}
	if(reportType == 'ImpVoiTureCostExcel'){
		document.form1.action="/reports/ga_report/ga_AreaSummaryCosts_Voiture.jsp?startdate="+document.form1.excelStartDate1.value+"&enddate="+document.form1.excelEndDate1.value;
		document.form1.submit();
	}else if(reportType == 'ImpVoiTureCostExcel1'){
		document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=VoiTureCost&startdate="+document.form1.excelStartDate1.value+"&enddate="+document.form1.excelEndDate1.value;
		document.form1.submit();
	}else if(reportType == 'ImpVoiTureCostExcel2'){
		document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=VoiTureDetail&startdate="+document.form1.excelStartDate1.value+"&enddate="+document.form1.excelEndDate1.value;
		document.form1.submit();
	}else if(reportType=='EateryCountsExcel'){
	
	   location.href="/gmControlServlet?operation=eateryReportCommand&content=EateryCountsExcel&StartDate="+$('EateryCountsExcelStartDate').value+"&EndDate="+$('EateryCountsExcelEndDate').value; 	
	
	}else if(reportType=='EateryTotalByDeptExcel'){
	
	   location.href="/gmControlServlet?operation=eateryReportCommand&content=EateryTotalByDeptExcel&StartDate="+$('EateryTotalByDeptExcelStartDate').value+"&EndDate="+$('EateryTotalByDeptExcelEndDate').value; 	
	
	}else if(reportType=='EateryViolationExcel'){
	 
	  location.href="/reports/gm_report/SpecialeateryReportNonNormal.jsp?date="+$('EateryViolationExcelDate').value; 
	  
	}else if(reportType=='EateryYearCountsExcel'){
	
	 
	  location.href="/gmControlServlet?operation=eateryReportCommand&content=EateryYearCountsExcel&menu_code=${param.menu_code}&year="+$('EateryYearCountsYear').value; 
	}else if(reportType=='EateryMonthConsumeExcel'){
	  if($('Rate').value==""||$('Rate').value==null){
	   alert("承担比例不能为空！");
	   return;	   
	  }
	  if($('floatingNum').value==""||$('floatingNum').value==null){
	   alert("浮动人数不能为空！");
	   return;	   
	  }
	
	  location.href="/gmControlServlet?operation=eateryReportCommand&content=EateryMonthConsumeExcel&StartDate="+$('EateryMonthConsumeStartDate').value+"&EndDate="+$('EateryMonthConsumeEndDate').value+"&Rate="+$('Rate').value+"&floatingNum="+$('floatingNum').value; 
	
	}else if(reportType=='EMSDetailsExcel'){
		if((document.form1.inputdata1.value==""||document.form1.inputdata1.value==null)){
			alert("分摊费不能为空！");return;}
	  document.form1.action="/gmControlServlet?menu_code=gm0404&operation=expressManger&content=ImpexpressExcel&flag=EMSDetailsExcel";
	  document.form1.submit();
	}else if(reportType=='EMSTotalExcel'){
		if((document.form1.inputdata2.value==""||document.form1.inputdata2.value==null)){
			alert("分摊费不能为空！");return;}
	  document.form1.action="/gmControlServlet?menu_code=gm0404&operation=expressManger&content=ImpexpressExcel&flag=EMSTotalExcel&excelStartDate2="+document.form1.excelStartDate_2.value+"&excelEndDate2="+document.form1.excelEndDate_2.value;
	  document.form1.submit();
	}else if(reportType=='EMSStatisticsExcel'){
	  document.form1.action="/gmControlServlet?menu_code=gm0404&operation=expressManger&content=ImpexpressExcel&flag=EMSStatisticsExcel";
	  document.form1.submit();
	  
	}else if(reportType=='presentQuentStat'){  
	  
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='presentSendStat'){  
	
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='presentSendDetail'){  
	
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='washStatsByPerson'){  
	
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportWashReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='washStatsByDept'){  
	
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportWashReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='smockRadioStatus'){
	
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportSmockReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='smockRadioStatus1'){
		
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportSmockReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='schemeStatus'){
	  
	  //方案统计
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportFestivalPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='presentProvideStatus'){
	  
	  //礼品发放明细
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportFestivalPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='presentQuantityStatus'){
	  
	  //礼品数量统计
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportFestivalPresentReport&reportType=" + reportType;
	  document.form1.submit();
	}else if(reportType=='foodStatus'){
	  //部门夜餐明细表
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportFoodReport&reportType=" + reportType;
	  document.form1.submit();	
	}else if(reportType=='foodTotalStatus'){
	  
	  //夜餐消费月别统计表
	  document.form1.action="/gaControlServlet?menu_code=gm0404&operation=exportFoodReport&reportType=" + reportType;
	  document.form1.submit();	
	}else if(reportType == 'safeChecksStatus'){
	  
	  //安全检查统计表
	  document.form1.action="/safeControlServlet?operation=securityChecks&content=securityChecksForTotalExcel";
	  document.form1.submit();	
	}else{
	   alert("报表不存在！");
	   return;
	}
	
	
	
}

</SCRIPT></head>
<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
String year=today.substring(0,4);    
String month=StringUtil.checkNull((NumberUtil.parseInt(today.substring(5, 7))-1));  
String day=today.substring(8,10);
String backInMotnDate="";
try {
	backInMotnDate=timeFormatter.format(timeFormatter.parse(year+"-"+month+"-"+day));
} catch (Exception e) {	
	e.printStackTrace();
}

%>
<form name="form1" method="post" action="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_report.jsp"%>
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
		<%@ include file="../inc/common_toolbar2.jsp"%>
		
		<!-- display content -->
		<br>
		<c:forEach items="${reportList}" var="report">
		<c:if test="${report.MENU_CODE=='report0301'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">快件报表</td>
			</tr>
		</table>
		<br>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	     <tr>
	    
	              <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EMSDetailsExcel" >快件月结算明细表
	              分摊费<input name="inputdata1" value="" type="text" style="width:60px">元
	              </td>
			      <td style="width: 650px" nowrap="nowrap" class="info_content_00">
			      <input type="text" name="excelStartDate2" class="content" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
			      <input type="text" name="excelEndDate2" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);"></td>
		   
		</tr>
		<tr>
	              <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EMSTotalExcel" >快件月结算报表
			      分摊费<input name="inputdata2" value="" type="text" style="width:60px">元</td>
			      <td style="width: 650px" nowrap="nowrap" class="info_content_00">
			      <input type="text" name="excelStartDate_2" class="content" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
			      <input type="text" name="excelEndDate_2" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		   		  <ait:codeClass codeClass="ExpressType" name="expenses_type" all="all"></ait:codeClass>
		   		  </td>
		</tr>
		<tr>
	              <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EMSStatisticsExcel" >快件发送情况统计表
					</td>
				  <td style="width: 650px" nowrap="nowrap" class="info_content_00"> &nbsp; </td>
		</tr>
<!--	    <tr>
	              <td nowrap="nowrap" class="info_title_01">报表</td>
			      <td nowrap="nowrap" class="info_content_00">
			      <select name="expressExcel">
			      <option value="EMSDetailsExcel">EMS月结算明细表</option>
			      <option value="EMSTotalExcel">EMS月结算报表</option>
			      <option value="EMSStatisticsExcel">EMS发送情况统计表</option>
			      </select>
			      </td>
			      <td nowrap="nowrap" class="info_content_00">
			      <ait:image src="../images/btn/Excel_Exp.gif" onclick="ImpEMSExcel()"></ait:image></td>				      
		   
		</tr>		-->
		</table>
		<br>
		</c:if>		
		<c:if test="${report.MENU_CODE=='report0302'}">
<!--		车辆事故报表-->
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">车辆报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	          <input name="check" type="radio" class="check" value="ImpVoiTureCostExcel" >车辆履历费用汇总表</td>
	          	     	
		<td nowrap="nowrap" class="info_content_00" style="width: 650px"   rowspan="3">
			      <input type="text" name="excelStartDate1" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
		 <input type="text" name="excelEndDate1" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
	    </td>
	     </tr>
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="ImpVoiTureCostExcel1" >车辆使用情况汇总表 </td>
	     </tr>
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="ImpVoiTureCostExcel2" >车辆使用明细表
	     </td>		     
		</tr>		
		</table>
		<br>
		</c:if>			
<!--		就餐报表-->
          <c:if test="${report.MENU_CODE=='report0303'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">就餐报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		 <tr>
	     <td nowrap="nowrap" class="info_content_00">
	          <input name="check" type="radio" class="check" value="EateryCountsExcel" >就餐人数统计表
	      </td>
	      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	          <input type="text" name="EateryCountsExcelStartDate" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
			      <input type="text" name="EateryCountsExcelEndDate" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
	         
	      </td>
	          
	     </tr>	    
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EateryViolationExcel" >违规就餐情况表 
	      </td>
	      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <input type="text" name="EateryViolationExcelDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
	     </td>
	     </tr>
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EateryYearCountsExcel" >年间就餐统计表
	     </td>
	     <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <ait:date yearName="EateryYearCountsYear"/>
	              
	              </td>
	     </tr>
	     
		<tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="EateryMonthConsumeExcel" >就餐消费月别结算表&nbsp;&nbsp;&nbsp;
	              承担比例<input type="text" name="Rate" style="width: 60px"  value="0" onkeyup='this.value=this.value.replace(/\D/gi,"0")'> &nbsp;&nbsp;&nbsp;浮动人数<input type="text" name="floatingNum"  value="0"  style="width: 60px"  onkeyup='this.value=this.value.replace(/\D/gi,"0")'>
	     </td>
	      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <input type="text" name="EateryMonthConsumeStartDate" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
			      <input type="text" name="EateryMonthConsumeEndDate" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">	               
	              </td>	    
		</tr>
		<tr>         
	     <td nowrap="nowrap" class="info_content_00">
	          <input name="check" type="radio" class="check" value="EateryTotalByDeptExcel" >就餐刷卡统计表
	      </td>
	      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	          <input type="text" name="EateryTotalByDeptExcelStartDate" style="width:70px" value="<%=backInMotnDate%>" readonly onClick="setday(this);">
			      &nbsp;&nbsp;至&nbsp;&nbsp;
			      <input type="text" name="EateryTotalByDeptExcelEndDate" class="content" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
	         
	      </td>         
		</tr>				
		</table>	
		<br>
		</c:if>
		<!--礼品报表-->
		
		<c:if test="${report.MENU_CODE=='report0304'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">礼品报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		 <tr>
	     <td nowrap="nowrap" class="info_content_00">
	          <input name="check" type="radio" class="check" value="presentQuentStat" >礼品数量统计表
	      </td>
	      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	         &nbsp;
	      </td>
	          
	     </tr>	    
	     <tr>
		      <td nowrap="nowrap" class="info_content_00">
		              <input name="check" type="radio" class="check" value="presentSendStat" >礼品发放统计表
		      </td>
		      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
		              <ait:selDept name="presentDeptId_stat" deptLevel="4" all="all"/>&nbsp;&nbsp;
		              <input type="text" name="presentStartDate_stat" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
			      		&nbsp;&nbsp;至&nbsp;&nbsp;
			      	  <input type="text" name="presentEndDate_stat" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      </td>
	     </tr>
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="presentSendDetail" >礼品发放明细表
	     </td>
	     <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <ait:selDept name="presentDeptId_detail" deptLevel="4" all="all"/>&nbsp;&nbsp;
	              <input type="text" name="presentStartDate_detail" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="presentEndDate_detail" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		      	  <ait:codeClass name="presentId_detail" codeClass="PresentName" all="all"/>
		</tr>			
		</table>	
		<br>
		</c:if>
		<!--洗衣报表-->
		
		<c:if test="${report.MENU_CODE=='report0305'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">洗衣房报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">   
	     <tr>
		      <td nowrap="nowrap" class="info_content_00">
		              <input name="check" type="radio" class="check" value="washStatsByPerson" >个人别洗衣统计表
		      </td>
		      <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
		              <input type="text" name="washStartDate_person" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
			      		&nbsp;&nbsp;至&nbsp;&nbsp;
			      	  <input type="text" name="washEndDate_person" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      </td>
	     </tr>
	     <tr>
	     <td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="washStatsByDept" >部门别洗衣统计表
	     </td>
	     <td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <ait:selDept name="washDeptId" all="all"/>&nbsp;&nbsp;
	              <input type="text" name="washStartDate_dept" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="washEndDate_dept" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		</tr>
		</table>
		<br>
		</c:if>
		
		<c:if test="${report.MENU_CODE=='report0306'}">
		<!--工作服报表-->
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">工作服报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">   
	     <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="smockRadioStatus" >工作服发放部门别统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <ait:selDept name="smockDeptId" deptLevel="4" all="all"/>&nbsp;&nbsp;
	              <ait:codeClass codeClass="smockInfo" name="smockInfo" />&nbsp;&nbsp;
	              <ait:codeClass codeClass="provideReportStat" name="provideStatus" />&nbsp;&nbsp;
	              <input type="text" name="startDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="endDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		 </tr>
		 <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="smockRadioStatus1" >工作服部门别频率统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <ait:selDept name="smockDeptId1" deptLevel="4" all="all"/>&nbsp;&nbsp;
	        </td>
		 </tr>
		</table>
		<br>
		</c:if>
		
		<c:if test="${report.MENU_CODE=='report0307'}">
		<!--节日礼品统计报表-->
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">节日礼品报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">   
	     <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="schemeStatus" >方案统计表
	     	</td>
	    
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	     		  
	              <input type="text" name="SchemeStartDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="SchemeEndDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		    </td>
		 </tr>
		 <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="presentProvideStatus" >礼品发放明细统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <input type="text" name="provideStartDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="provideEndDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		 </tr>
		 <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="presentQuantityStatus" >礼品员工别统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	     		  <ait:select dataListName="recordList" name="schemeName" all="all"/>&nbsp;&nbsp;
	              <input type="text" name="QuentityStartDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="QuentityEndDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		 </tr>
		</table>
		<br>
		</c:if>
		
		<!--夜餐统计报表-->
		<c:if test="${report.MENU_CODE=='report0308'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">夜餐统计报表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">   
	     <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="foodStatus" >部门夜餐明细表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <input type="text" name="foodStartDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="foodEndDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		 </tr>
		 <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="foodTotalStatus" >夜餐消费月别统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
		      	  <ait:date yearName="foodYear" monthName="foodMonth"/>
		 </tr>
		</table>
		</c:if>
		
		<!--安全环保报表-->
		<c:if test="${report.MENU_CODE=='report0309'}">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">安全检查记录表</td>
			</tr>
		</table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">   
	     <tr>
	     	<td nowrap="nowrap" class="info_content_00">
	              <input name="check" type="radio" class="check" value="safeChecksStatus" >安全检查记录统计表
	     	</td>
	     	<td nowrap="nowrap" class="info_content_00" style="width: 650px" >
	              <input type="text" name="safeStartDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">
		      		&nbsp;&nbsp;至&nbsp;&nbsp;
		      	  <input type="text" name="safeEndDate" style="width:70px" value="<%=today%>" readonly onClick="setday(this);">&nbsp;&nbsp;
		 </tr>
		
		</table>
		</c:if>
		
		
		
		</c:forEach>
		<table width="100%" border="0" cellspacing="0">
			<c:forEach var="i" begin="1" end="6" step="1">
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
</body>
</html>