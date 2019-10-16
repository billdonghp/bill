<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" import="java.util.*,com.ait.sqlmap.util.*,com.ait.util.StringUtil"%>
<jsp:useBean id="arReportService" scope="page" class="com.ait.reports.reportservices.ArReportService"/>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%@ page import="java.util.Calendar"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<SCRIPT type="text/javascript">

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	$('key').value="";
	$('personId').value="";
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}


function ImportExcel() {


var msg1='<ait:message  messageID="alert.att.view.report.select_report" module="ar"/>';
var msg2='<ait:message  messageID="alert.att.view.report.select_date" module="ar"/>';
//开始日期不能为空！
var sData_msg='<ait:message  messageID="alert.mutual.start_time_empty"/>';
//结束日期不能为空！
var eData_msg='<ait:message  messageID="alert.mutual.end_time_empty"/>';

	var reportType ="";
	var url = "" ;
               
	var check = document.getElementsByName("check") ;           
	
	for (i = 0 ; i < check.length ; i++){  
	  
		 if (check[i].checked){
		 	
		 	reportType = check[i].value;
		 }
	} 
	
	if (reportType == ""){
	//"请选择报表"
		alert(msg1);
		return false;
	}
	//else if(reportType == "dailyDetailOverall" || reportType == "dailyDetailStatistics")
	else if(reportType == "report0101")
	{
		var date = $(reportType + "_date").value;
	    if(date == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDate=" + date + "&reportType=" + reportType ;
	}
	else if(reportType == "report0102")
	{
		var year = $(reportType + "_date").value;
	    if(year == "" || month == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDate=" + year + "&reportType=" + reportType ;
	}
	else if(reportType == "report0103")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0104")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0105")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0106")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0107")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
		var tableName = $(reportType + "_tableName").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType + "&tableName=" + tableName;
	}
	else if(reportType == "report01287")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
		var workType = $(reportType + "_workType").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType + "&workType=" + workType;
	}
	else if(reportType == "report0108")
	{
		var deptId = $(reportType + "_deptId").value;
		var year = $(reportType + "_dateStarted").value;
		var month = $(reportType + "_dateEnd").value;
	    if(year == "" || month == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&dateStarted=" + year + "&reportType=" + reportType + "&deptId=" + deptId + "&dateEnd=" + month;
	}
	else if(reportType == "report0109")
	{
		var deptId = $(reportType + "_deptId").value;
		var year = $(reportType + "_dateStarted").value;
		var month = $(reportType + "_dateEnd").value;
	    if(year == "" || month == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&dateStarted=" + year+ "&reportType=" + reportType + "&deptId=" + deptId + "&dateEnd=" + month;
	}
	else if(reportType == "report0110")
	{
		var deptId = $(reportType + "_deptId").value;
		var year = $(reportType + "_dateStarted").value;
		var month = $(reportType + "_dateEnd").value;
	    if(year == "" || month == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&dateStarted=" + year + "&reportType=" + reportType + "&deptId=" + deptId + "&dateEnd=" + month;
	}
	else if(reportType == "report0111")
	{
		var date = $(reportType + "_date").value;
		var month = $(reportType + "_dateEnd").value;
	    if(date == "" || month == "")     
	    {           
		   alert(msg2);
		   return false; 
	    }
	    url = "/reportControlServlet?operation=arReport&arDate=" + date + "&reportType=" + reportType + "&dateEnd=" + month;
	}
	else if(reportType == "report0112")
	{
		var deptId = $(reportType + "_deptId").value;
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType + "&deptId=" + deptId ;
	}
	else if(reportType == "report0113")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
		var deptId = $(reportType + "_deptId").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&deptId="+ deptId +"&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0114")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    }
	    if(!DateDiff(dateStarted,dateEnd))
	    {
	    	alert("日期必须在一个月之内!");
	    	return ;
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0115")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    }
	    if(!DateDiff(dateStarted,dateEnd))
	    {
	    	alert("日期必须在一个月之内!");
	    	return ;
	    }
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0116")
	{
		var year = $("year").value;
		var month = $("month").value;
		var deptId = $(reportType + "_deptId").value;
		var personId = $("personId").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    }
	    url = "/reportControlServlet?operation=arReport&deptId=" + deptId + "&personId=" + personId + "&year=" + year + "&month=" + month + "&reportType=" + reportType ;
	}
	else if(reportType == "report0117")
	{
	    
		var yearMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonth=" + yearMonth  + "&reportType=" + reportType ;
	}
	else if(reportType == "report0118")
	{
	    
		var yearMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonth=" + yearMonth  + "&reportType=" + reportType ;
	}
	else if(reportType == "report0119")
	{
	    
		var yearMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonth=" + yearMonth  + "&reportType=" + reportType ;
	}
	else if(reportType == "report0120")
	{
	   
		var yearMonthStart = $(reportType + "_StartYear").value + $(reportType + "_StartMonth").value ;
		
		var yearMonthEnd = $(reportType + "_EndYear").value + $(reportType + "_EndMonth").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonthStart=" + yearMonthStart  + "&yearMonthEnd=" + yearMonthEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0121")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0123")
	{
	   
		var yearMonthStart = $(reportType + "_StartYear").value + $(reportType + "_StartMonth").value ;
		
		var yearMonthEnd = $(reportType + "_EndYear").value + $(reportType + "_EndMonth").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonthStart=" + yearMonthStart  + "&yearMonthEnd=" + yearMonthEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0124")
	{
	   
		var yearMonthStart = $(reportType + "_StartYear").value + $(reportType + "_StartMonth").value ;
		
		var yearMonthEnd = $(reportType + "_EndYear").value + $(reportType + "_EndMonth").value ;

	    url = "/reportControlServlet?operation=arReport&&personId=" + personId + "&yearMonthStart=" + yearMonthStart  + "&yearMonthEnd=" + yearMonthEnd + "&reportType=" + reportType ;
	}
	else if(reportType == "report0125")
	{
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;
	    if(dateStarted == "" || dateEnd == "")     
	    {           
		   alert(msg2);
		   return false; 
	    } 
	    url = "/reportControlServlet?operation=arReport&arDateStarted=" + dateStarted + "&arDateEnd=" + dateEnd + "&reportType=" + reportType ;
	}
	
	else if(reportType == "report0126")
	{   //DISC考勤明细缺勤报表
		var deptId = $(reportType + "_deptId").value;
		var yearMonth = $(reportType + "_Year").value + $(reportType + "_Month").value ;
	   
	    url = "/reportControlServlet?operation=arReport&yearMonth=" + yearMonth + "&reportType=" + reportType + "&deptId=" + deptId;
	}
	
	else if(reportType == "report0127")
	{   //大于选择时间 的第一次进门卡，（排除包含小于选择时间的进门卡）
	
		var deptId = $(reportType + "_deptId").value;
		var arTime = $(reportType + "_arTime").value;
		var dateStarted = $(reportType + "_dateStarted").value;
		var dateEnd = $(reportType + "_dateEnd").value;

	     if(dateStarted == "" || dateEnd == "") 
	    {           
		   alert(msg2);
		   return false; 
	    } 
	   
	    url = "/reportControlServlet?operation=arReport&arTime=" + arTime + "&reportType=" + reportType + "&deptId=" + deptId + "&dateStarted=" + dateStarted +"&dateEnd=" + dateEnd;
	}
	
	
	else if(reportType == "exportDISCEmpArDetailReport")
	{
		var arMonth = document.form1.year.value + document.form1.month.value;
	    if(arMonth == "")     
	    {           
		   alert("请选择考勤月!!!");
		   return false; 
	    }
	    
	    if (confirm("是否导出DISC月考勤员工明细表!!!"))
	    {
		    for (i = 0 ; i < check.length ; i++){  
		  
				 if (check[i].checked){
				 	check[i].checked = false ;
				 }
			} 
	    	
	    	url = "/reportControlServlet?operation=" + reportType + "&AR_MONTH=" + arMonth ;
	    }  
	}
	
	document.form1.action = url ;
 	document.form1.submit();

}

function DateDiff(asStartDate,asEndDate)   
 {   //获得日期差值   
      var miStart=Date.parse(asStartDate.replace(/\-/g,'/'));      
      var miEnd=Date.parse(asEndDate.replace(/\-/g,'/'));      
      var day = (miEnd-miStart)/(1000*24*3600);
      if(day>31)
      	return false;
      return true;      
 } 
  
</SCRIPT>  
</head>
<body>
<br>
<form name="form1" method="post" target="_blank" action="">
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
		
		<% 
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("screenGrantNo", admin.getScreenGrantNo());
		
			List arReportList = arReportService.retrieveArReportList(parameterObject) ;
			
			request.setAttribute("arReportList",arReportList) ;
			
		%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--考勤报表-->
					<ait:message  messageID="display.att.view.report.attendance_report" module="ar"/></td>
			</tr>
		 </table>
		<table width="100%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			  <tr>
			    <td rowspan="1" class="info_title_01"><!--报表查询-->
					<ait:message  messageID="display.att.view.report.report" module="ar"/></td>
			    <td colspan="8" rowspan="3" align="center">
			      <table width="80%" border="1" cellpadding="2" cellspacing="1" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			      	<c:forEach items="${arReportList}" var="report">
			      	
			      		<c:if test="${report.MENU_CODE eq 'report0101' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_date" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0102' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_date" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0103' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
								<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);"  />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0104' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0105' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0106' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report01287' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> :  
			   						<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加班类型：
						     		<select name="${report.MENU_CODE}_workType">
								  		<option value="1">平日加班</option>
								  		<option value="2">休息日加班</option>
								  		<option value="3">节假日加班</option>
								  	</select> 
						     	</td>
						     	
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0107' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> :  
			   						<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;表区分
						     		<select name="${report.MENU_CODE}_tableName">
								  		<option value="AR_DETAIL"  <c:if test="${tableName eq 'AR_DETAIL'}">selected</c:if> >主表</option>
								  		<option value="AR_DETAIL_HISTORY" <c:if test="${tableName eq 'AR_DETAIL_HISTORY'}">selected</c:if>>分表</option>
								  	</select> 
						     	</td>
						     	
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0108' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤月--> <ait:message  messageID="display.mutual.date" /> : 
<!--			   					<ait:date yearName="${report.MENU_CODE}_year" monthName="${report.MENU_CODE}_month" yearPlus="10" />-->
			   					<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					~ 
			   					<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   					<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0109' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤月--> <ait:message  messageID="display.mutual.date" /> : 
			   					<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					~ 
			   					<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   					<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0110' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤月--> <ait:message  messageID="display.mutual.date" /> : 
			   					<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					~ 
			   					<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
			   					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   					<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0111' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> :
						     		<input name="${report.MENU_CODE}_date" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		~ 
			   					<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0112' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   						<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0113' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   						<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   						<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0114' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
								<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0115' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0116' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   						<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   						<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
			   						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   						<ait:message  messageID="display.mutual.emp_no_name"/> :
			          				<input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)">
			          				<input id="personId" name="personId" type="hidden" value="${personId}">
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0117' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" />&nbsp;&nbsp;
								</td>
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0118' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" />&nbsp;&nbsp;
								</td>
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0119' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" />&nbsp;&nbsp;
								</td>
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0120' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_StartYear" monthName="${report.MENU_CODE}_StartMonth" yearPlus="10" />
						     	     ~ 
						     	   <ait:date yearName="${report.MENU_CODE}_EndYear" monthName="${report.MENU_CODE}_EndMonth" yearPlus="10" />&nbsp;&nbsp;
								</td>
								
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0121' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0123' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_StartYear" monthName="${report.MENU_CODE}_StartMonth" yearPlus="10" />
						     	     ~ 
						     	   <ait:date yearName="${report.MENU_CODE}_EndYear" monthName="${report.MENU_CODE}_EndMonth" yearPlus="10" />&nbsp;&nbsp;
								</td>
								
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0124' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	  月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_StartYear" monthName="${report.MENU_CODE}_StartMonth" yearPlus="10" />
						     	     ~ 
						     	   <ait:date yearName="${report.MENU_CODE}_EndYear" monthName="${report.MENU_CODE}_EndMonth" yearPlus="10" />&nbsp;&nbsp;
								</td>
								
						 	</tr>
						 	
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0125' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		<!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
						     		<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0126' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     		月份 
						     	   :&nbsp;<ait:date yearName="${report.MENU_CODE}_Year" monthName="${report.MENU_CODE}_Month" yearPlus="10" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   						<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   						<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		<c:if test="${report.MENU_CODE eq 'report0127' }">
				      		<tr >
						    	<td height="30" colspan="2" class="info_content_00" >
						     		<input name="check" type="radio" class="check" value="${report.MENU_CODE}" >${report.MENU_INTRO}
						     	</td>
						     	<td height="30" colspan="2" class="info_content_00" >
						     	    <!--考勤日期--><ait:message  messageID="display.mutual.date" /> : 
									<input name="${report.MENU_CODE}_dateStarted" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		 ~ 
						     		<input name="${report.MENU_CODE}_dateEnd" type="text" readonly class="content"  style="width:90;" onClick="setday(this);" />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						     		<ait:time name="${report.MENU_CODE}_arTime" spacing="30" selected="08:00"  />
						     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   						<!--部门--> <ait:message  messageID="display.mutual.department" /> : 
			   						<ait:selDept name="${report.MENU_CODE}_deptId" supervisorType="ar"/>
						     	</td>
						 	</tr>
			      		</c:if>
			      		
			      		
			      	</c:forEach>
				  </table>
			  	</td>
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;visibility: hidden;">   
	</div>
</form>
</body>
</html>
