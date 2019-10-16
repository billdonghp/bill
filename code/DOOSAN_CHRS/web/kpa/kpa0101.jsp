<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.ait.pa.*"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.ait.reports.reportservices.PaReportService"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<script src="../js/prototype.js"></script>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<%
			SimpleMap parameterObject = new SimpleMap();
			List paFilialeDeptList = new ArrayList();
			List paFactoryDeptList = new ArrayList();

			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;

			PaReportService paReportService = new PaReportService();

			paFilialeDeptList = paReportService
					.retrievePaFilialeDeptList(parameterObject);
			paFactoryDeptList = paReportService
					.retrievePaDeptList(parameterObject);

			request.setAttribute("paFilialeDeptList", paFilialeDeptList);
			request.setAttribute("paFactoryDeptList", paFactoryDeptList);
		%>

		<br>
		<form name="rpt" method="post">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15"></td>
					<td width="11" height="33" valign="TOP" align="RIGHT">
						<img src="../images/tablbk01_r1_c1.gif">
					</td>
					<td background="../images/tablbk01_r1_c2.gif">

						<!-- display toolbar -->
						<%@ include file="../kpa/inc/pa_toolbar_report.jsp"%>
					</td>
					<td width="10" height="33" align="LEFT" valign="TOP">
						<img src="../images/tablbk01_r1_c26.gif">
					</td>
					<td width="11"></td>
				</tr>
				<tr>
				<td width="16"></td>
				<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
				<td valign="TOP" align="CENTER">
						<%@ include file="../pa/inc/common_toolbar.jsp"%>

						<!-- display content -->
						<br>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td align="left" class="title1" colspan="11">
									<!-- 工资报表 -->
									<ait:message messageID="display.pay.view.report.payroll_report"
										module="pay"></ait:message>
								</td>
							</tr>
						</table>
						<table width="100%" border="1" cellpadding="0" cellspacing="0"
							bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
							style="padding: 2px 2px 2px 2px;">

							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
								</td>
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">

								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="6" align="center" valign="middle"
									nowrap="nowrap">
									&nbsp;
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_tax.jsp?">
									<!-- 年税报表(不含成果奖金) -->
									<ait:message messageID="display.pay.report.pay_TaxYearNoR"
										module="pay" />
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_tax.jsp?make=1">
									<!-- 年税报表(含成果奖金) -->
									<ait:message messageID="display.pay.report.pay_TaxYear"
										module="pay" />
								</td>
								<td height="30" colspan="3" rowspan="3" align="center"
									valign="middle" nowrap="nowrap">

									<!-- 员工-->
									<ait:message messageID="display.mutual.emp_no_name"
										module="pay" />
									&nbsp;:&nbsp;
									<input id="key" name="key" size="10" value="${key}"
										onkeyup="SearchEmp(this.value,this.id)"
										title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>' />
									<span id="name"></span> &nbsp;&nbsp;&nbsp;
									</p>
									<!-- 工资月 -->
									<ait:message messageID="display.mutual.pay_month" module="pay" />
									:&nbsp;
									<ait:date yearName="pa_factory_Year"
										monthName="pa_factory_Month" yearPlus="10" />
									&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_achievement.jsp?">
									<!-- 中国海外住在员成果奖表 -->
									<ait:message messageID="display.pay.report.pay_Achievement"
										module="pay" />
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_Payroll.jsp?">
									<!-- 工中国海外住在员工资表 -->
									<ait:message messageID="display.pay.report.pay_Payroll"
										module="pay" />
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_IndividualsWage.jsp?">
									<!-- 个人别工资(月) -->
									<ait:message messageID="display.pay.report.pay_IndividualsWage"
										module="pay" />
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_IndividualsWageSingle.jsp?">
									<!-- 个人别工资清算表(工号/姓名) -->
									<ait:message
										messageID="display.pay.report.pay_IndividualsWageSingle"
										module="pay" />
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_SupplementWagePromotedTax.jsp?">
									<!-- 晋升者税额、工资补差 -->
									<ait:message messageID="display.pay.report.pay_SupplementWagePromotedTax"
										module="pay" />
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_SupplementPayTax.jsp?">
									<!-- 调薪税额 -->
									<ait:message
										messageID="display.pay.report.pay_SupplementPayTax"
										module="pay" />
								</td>
							</tr>
				   			<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_SupplementAnnualSalaryAdjust.jsp?">
									<!-- 年薪调整补差金额 -->
									<ait:message messageID="display.pay.report.pay_SupplementAnnualSalaryAdjustment"
										module="pay" />
									<input name="url" type="radio" class="check"
										value="kpa01_rpt_TaxYearChange.jsp?">
									<!-- 08年税额_税额变更 -->
									<ait:message
										messageID="display.pay.report.pay_TaxYearChange"
										module="pay" />
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="6" align="center" valign="middle">
									&nbsp;
								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">

								</td>
								<td height="30" colspan="3" rowspan="2" align="center"
									valign="middle" nowrap="nowrap">

								</td>
							</tr>
							<tr align="center">
								<td height="30" colspan="3" align="center" valign="middle"
									nowrap="nowrap">
	
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="3" step="1">
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
			<iframe id='iemp'
				style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
			</iframe>
			<div id="emp_list"
				style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">
			</div>
		</form>
	</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--  
//打开excel的函数
function ImportExcel(){
	var paMonth = "" ;

	var url = "" ;
	for (i=0;i<document.rpt.url.length;i++){
		 if (document.rpt.url[i].checked){
		 	url =document.rpt.url[i].value;
		 }
	}
	if (url == ""){
		//请选择报表
		alert('<ait:message messageID="alert.pay.select_report" module="pay"/>');
	}else{
		//年税报表 
		if (url == 'kpa01_rpt_tax.jsp?') {
			var year = document.rpt.pa_factory_Year.value;
			url='../../reports/kpa_report/'+url+'&year='+ year ;
		} 
		//包含成果奖 kpa01_rpt_tax.jsp?make=1
		else if(url == 'kpa01_rpt_tax.jsp?make=1'){
			var year = document.rpt.pa_factory_Year.value;
			url='../../reports/kpa_report/'+url+'&year='+ year + '&make=1' ;
		}
		else if(url == 'kpa01_rpt_achievement.jsp?'){
			var year = document.rpt.pa_factory_Year.value;
			url='../../reports/kpa_report/'+url+'&year='+ year ;
		}
		//中国海外住在员工资表
		else if(url == 'kpa01_rpt_Payroll.jsp?'){
			url='../../reports/kpa_report/'+url+'&paYear='+ document.rpt.pa_factory_Year.value +'&paMonth='+ document.rpt.pa_factory_Month.value;
		}
		//月个人别工资 (DICC)
		else if(url == 'kpa01_rpt_IndividualsWage.jsp?'){
			url='../../reports/kpa_report/'+url+'&paYear='+ document.rpt.pa_factory_Year.value +'&paMonth='+ document.rpt.pa_factory_Month.value;
		}
		//月个人别工资 (单独一人)
		else if(url == 'kpa01_rpt_IndividualsWageSingle.jsp?'){
			var emp=document.getElementById('name');//document.rpt.key.value;
			if(document.rpt.key.value==''){
				alert('<ait:message messageID="alert.pay.view.enter_id" module="pay"/>');
				return ;
			}
			url='../../reports/kpa_report/'+url+'&paYear='+ document.rpt.pa_factory_Year.value +'&emp='+ document.rpt.key.value;
		} 
		//晋升者税额、工资补差	
		else if(url == 'kpa01_rpt_SupplementWagePromotedTax.jsp?'){
			var emp=document.getElementById('name');//document.rpt.key.value;
			url='../../reports/kpa_report/'+url+'&paYear='+ document.rpt.pa_factory_Year.value;// +'&emp='+ document.rpt.key.value;
		}
		//调薪税额 
		else if(url == 'kpa01_rpt_SupplementPayTax.jsp?'){
			var emp=document.getElementById('name');//document.rpt.key.value;
			url='../../reports/kpa_report/'+url+'&paYear='+ document.rpt.pa_factory_Year.value;// +'&emp='+ document.rpt.key.value;
		}
		// 调薪所致的补差计算	
		else if (url == 'kpa01_rpt_SupplementAnnualSalaryAdjust.jsp?')
		{
			var year = document.rpt.pa_factory_Year.value
			url = "../../reports/kpa_report/" + url + "&year=" + year ;
		}			
		//年税额_税额变更 
		else if (url == 'kpa01_rpt_TaxYearChange.jsp?')
		{
			var year = document.rpt.pa_factory_Year.value
			url = "../../reports/kpa_report/" + url + "&year=" + year ;
		}	

		else
		{
			alert("此报表不支持导出excel功能!!!!") ;
			return ;
		}
		
		//alert(url) ;
		window.open(url ,"");
		
	}
}




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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
		
		//alert('SearchE');	  
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
    
	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}

function selectFlag(num){

	var selectCheckbox=document.getElementsByName("selectC");
	for(var i=0;i<selectCheckbox.length;i++){
	if(selectCheckbox[i].value==num){
	selectCheckbox[i].checked=true
	}
	
	}

}
//-->
</SCRIPT>

