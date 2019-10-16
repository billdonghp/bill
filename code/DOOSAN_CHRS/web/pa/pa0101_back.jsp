<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.ait.pa.*" %>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.ait.reports.reportservices.PaReportService"%>
<%@ page import="com.ait.pa.business.PaServices"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>

<jsp:useBean id="arReportService" scope="page" class="com.ait.reports.reportservices.ArReportService"/>

<html>
<head>
<title></title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<br>
<form name="rpt" method="post">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/pa_toolbar_report.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="16"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
<%
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("screenGrantNo", admin.getScreenGrantNo());
		parameterObject.setString("cpnyId",admin.getCpnyId()) ;
		
		PaReportService paReportService = new PaReportService() ;
		PaServices paServices = PaServices.getInstance() ;
		
		List paReportList = paReportService.retrievePaReportList(parameterObject) ;
		List paReportList2 = new ArrayList() ;
		List paReportList3 = new ArrayList() ;
		List paReportList4 = new ArrayList() ;
		List paReportList5 = new ArrayList() ;
		
		for (int i = 0; i < paReportList.size(); ++i )
		{
			SimpleMap reportMap = (SimpleMap)paReportList.get(i) ;
			
			if (   reportMap.getString("MENU_CODE").equals("report0202") || reportMap.getString("MENU_CODE").equals("report0203")
			    || reportMap.getString("MENU_CODE").equals("report0204") || reportMap.getString("MENU_CODE").equals("report0205")
			    || reportMap.getString("MENU_CODE").equals("report0206") || reportMap.getString("MENU_CODE").equals("report0207") )
			{
				
			}
			else if (  reportMap.getString("MENU_CODE").equals("report0208") || reportMap.getString("MENU_CODE").equals("report0209")
				    || reportMap.getString("MENU_CODE").equals("report0210") || reportMap.getString("MENU_CODE").equals("report0211") )
			{

			}
		}
		
		
		request.setAttribute("arReportList",paReportList) ;
	
		// 查询条件
		List paFilialeDeptList = new ArrayList() ;
		List paFactoryDeptList = new ArrayList() ;
		List paTypeList = new ArrayList() ;
		
	    Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
	
		paFilialeDeptList = paReportService.retrievePaFilialeDeptList(parameterObject) ;
		paFactoryDeptList = paReportService.retrievePaDeptList(parameterObject) ;
		paTypeList = paServices.paTypeList(parameterObject);	
		
		request.setAttribute("paFilialeDeptList",paFilialeDeptList) ;
		request.setAttribute("paFactoryDeptList",paFactoryDeptList) ;
%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="11"><!-- 工资报表 -->
				<ait:message messageID="display.pay.view.report.payroll_report" module="pay"></ait:message>
				</td>
			</tr>
		 </table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			  
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap"><!-- 职位别起点工资 -->			   
			      <input name="url" type="radio" class="check" value="pa_position_wage.jsp?">
			  		职位别起点工资&nbsp;&nbsp;&nbsp;
			    </td>
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			    	<!-- 年度 -->年度:&nbsp;<ait:date yearName="pa_post_execl_year"yearPlus="10" />
			    </td>
			  </tr>
			  <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
			  </tr>
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_factory"><!-- 工厂工资支给总表 -->工厂工资支给总表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_detail_factory"><!-- 工厂奖金支给总表 -->工厂奖金支给总表
			   </td>
			   <td height="30" colspan="3" rowspan="3" align="center" valign="middle" nowrap="nowrap">
			    	<!-- 部门 -->部门:&nbsp;
			    						  <select name="pa_factory_deptid">
			    						  	<option value="">请选择</option>
			    						  	<c:forEach var="dept" items="${paFactoryDeptList}" varStatus="i">
			    						  		<option value="${dept.DEPTID}"><c:forEach begin="1" end="${dept.DEPT_LEVEL - 2}">&nbsp;&nbsp;</c:forEach>${dept.DEPTNAME}</option>
			    						  	</c:forEach>
			    						  </select>
			   		&nbsp;&nbsp;&nbsp;
			   		<!-- 员工区分 -->员工区分:&nbsp;
			   								职员&nbsp;<input type="checkbox" value="职员" name="pa_factory_distinction"> 
			   								工人&nbsp;<input type="checkbox" value="工人" name="pa_factory_distinction"> 
			   								临时工&nbsp;<input type="checkbox" value="临时工" name="pa_factory_distinction"> 
			   		&nbsp;&nbsp;&nbsp;</p>
			   		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="pa_factory_Year" monthName="pa_factory_Month" yearPlus="10" />
			   		&nbsp;&nbsp;&nbsp;奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="pa_factory_bonusTypeCode" />
			   		&nbsp;&nbsp;&nbsp;
					<select name="pa_factory_bonusNo">
						<c:forEach var="i" begin="1" end="9" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
			   </td>
			  </tr>                  
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_part_factory"><!-- 工厂工资支给总表 -->工厂工资支给中总表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_detail_part_factory"><!-- 工厂奖金支给总表 -->工厂奖金支给中总表
			    </td>  
			  </tr>
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_all_factory"><!-- 工厂工资支给总表 -->工厂工资支给明细表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_detail_all_factory"><!-- 工厂奖金支给总表 -->工厂奖金支给明细表
			    </td>
			  </tr>
			  <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle">&nbsp;</td>
			  </tr>
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_filiale"><!-- 支社工资支给总表 -->支社工资支给总表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_detail_filiale"><!-- 支社奖金支给总表 -->支社奖金支给总表
			   </td>
			   <td height="30" colspan="3" rowspan="2" align="center" valign="middle" nowrap="nowrap">
			    	<!-- 部门 -->部门:&nbsp;
			    						  <select name="pa_filiale_deptid">
			    						  	<option value="">请选择</option>
			    						  	<c:forEach var="dept" items="${paFilialeDeptList}" varStatus="i">
			    						  		<option value="${dept.DEPTID}">${dept.DEPTNAME}</option>
			    						  	</c:forEach>
			    						  </select>
			   		&nbsp;&nbsp;&nbsp;</p>
			   		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="pa_filiale_Year" monthName="pa_filiale_Month" yearPlus="10" />
			   		&nbsp;&nbsp;&nbsp;奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="pa_filiale_bonusTypeCode" />
			   		&nbsp;&nbsp;&nbsp;
					<select name="pa_filiale_bonusNo">
						<c:forEach var="i" begin="1" end="9" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
			    </td>                    
			  </tr>
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_all_filiale"><!-- 支社工资支给明细表 -->支社工资支给明细表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_detail_all_filiale"><!-- 支社奖金支给明细表 -->支社奖金支给明细表
			    </td>     
			  </tr>
			  <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
			  </tr>
			  <tr align="center">
			    <td height="30" colspan="3" rowspan="2" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_no_email"><!-- 无邮件支给明细表 -->无邮件支给明细表&nbsp;&nbsp;&nbsp;
			    </td>
			    <td height="30" colspan="3" rowspan="2" align="center" valign="middle" nowrap="nowrap">
			        考勤区分:&nbsp;<ait:codeClass codeClass="EmpDiffType" name="no_email_statTypeCode"  selected="${no_email_statTypeCode}" />&nbsp;&nbsp;&nbsp;
			    	区分:&nbsp;
			    	<select name="no_email_tableName">
			        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
			        		<option value="PA_REPLENISHMENT_HISTORY" <c:if test="${tableName == 'PA_REPLENISHMENT_HISTORY'}">selected</c:if> >工资(补)</option>
			        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
			        </select>&nbsp;&nbsp;&nbsp;
			        <!-- 部门 -->部门:&nbsp;<ait:selDept name="no_email_deptid" all=" " supervisorType="pa"/></p>
			   		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="no_email_Year" monthName="no_email_Month" yearPlus="10" />
			   		&nbsp;&nbsp;&nbsp;奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="no_email_bonusTypeCode" />
			   		&nbsp;&nbsp;&nbsp;
					<select name="no_email_bonusNo">
						<c:forEach var="i" begin="1" end="9" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select> 
			    </td>     
			  </tr>
			 <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
			 </tr>
			 <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
			 </tr>
			  <tr align="center">
			   <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_payslip_factory"><!-- 工厂工资工资单 -->工厂工资工资单&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_payslip_factory2"><!-- 工厂工资工资单2 -->工厂工资工资单2
			    </td> 
			   <td height="30" colspan="3" rowspan="3" align="center" valign="middle" nowrap="nowrap">		   		
			   		<!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:&nbsp;<ait:date yearName="personal_Year" monthName="personal_Month" yearPlus="10" />
			   		&nbsp;&nbsp;&nbsp;奖金名称:&nbsp;<ait:codeClass codeClass="BonusType" name="personal_bonusTypeCode" />
			   		&nbsp;&nbsp;&nbsp;
					<select name="personal_bonusNo">
						<c:forEach var="i" begin="1" end="9" step="1">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select><br>&nbsp;&nbsp;&nbsp;					
					<!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			   </td>
			  </tr>			
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_bonus_payslip_factory"><!-- 工厂奖金工资单 -->工厂奖金工资单&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_bonus_payslip_filiale"><!--支社奖金工资单 -->支社奖金工资单
			    </td>     
			  </tr>	
			  <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_payslip_filiale"><!-- 支社工资工资单 -->支社工资工资单&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_payslip_filiale2"><!-- 支社工资工资单2 -->支社工资工资单2
			    </td> 		  
			  </tr>
			  <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
			 </tr>
			 <tr align="center">
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			      <input name="url" type="radio" class="check"  value="pa_detail_factory_average"><!-- 工厂平均工资明细表 -->工厂平均工资明细表&nbsp;&nbsp;&nbsp;
			      <input name="url" type="radio" class="check"  value="pa_detail_filiale_average"><!-- 支社平均工资明细表 -->支社平均工资明细表&nbsp;&nbsp;&nbsp;
			    </td>
			    <td height="30" colspan="3" align="center" valign="middle" nowrap="nowrap">
			        区间：&nbsp;<ait:date yearName="averageStartYear" monthName="averageStartMonth" yearPlus="10" />--<ait:date yearName="averageEndYear" monthName="averageEndMonth" yearPlus="10" />
			    </td>     
			  </tr>
			  <tr align="center">
			    <td height="10" colspan="6" align="center" valign="middle" nowrap="nowrap">&nbsp;</td>
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

</form>
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--  
//打开水晶报表的函数
function PrintReport()
{
	var paMonth = "" ;

	var url = "" ;
	for (i=0;i<document.rpt.url.length;i++){
		 if (document.rpt.url[i].checked){
		 	url =document.rpt.url[i].value;
		 }
	}
	if (url == ""){
		//请选择报表
		alert('<ait:message messageID="alert.att.view.report.select_report" module="ar"/>');
	}else{
		// 工厂支给
		if (   url == 'pa_detail_factory'      || url == 'pa_bonus_detail_factory'
			|| url == 'pa_detail_part_factory' || url == 'pa_bonus_detail_part_factory'
			|| url == 'pa_detail_all_factory'  || url == 'pa_bonus_detail_all_factory'
			)
		{
			var distinction = "" ;
			var deptid = document.rpt.pa_factory_deptid.value ;
			var bonusTypeCode = document.rpt.pa_factory_bonusTypeCode.value ;
			var bonusNo = document.rpt.pa_factory_bonusNo.value ;
			var data =  document.getElementsByName("pa_factory_distinction") ;
			var size = data.length ;
			for (var i = 0 ; i < size ; i ++)
			{
				if( data[i].checked )
				{
					distinction += "," + data[i].value  ;
				}
			}
			
			if (distinction == "")
			{
				alert("请选择员工区分!!!");
				return ;
			}		
			
			paMonth = document.rpt.pa_factory_Year.value + document.rpt.pa_factory_Month.value ;
			url = "/reportControlServlet?operation=crystal&reportName=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&distinction=" + encodeURIComponent(distinction.slice(1)) ;
		}		
				
		// 支社支给
		else if (   url == 'pa_detail_filiale'     || url == 'pa_bonus_detail_filiale'
				 || url == 'pa_detail_all_filiale' || url == 'pa_bonus_detail_all_filiale'
			)
		{
			var deptid = document.rpt.pa_filiale_deptid.value ;
			var bonusTypeCode = document.rpt.pa_filiale_bonusTypeCode.value ;
			var bonusNo = document.rpt.pa_filiale_bonusNo.value ;
			
			paMonth = document.rpt.pa_filiale_Year.value + document.rpt.pa_filiale_Month.value ;
			url = "/reportControlServlet?operation=crystal&reportName=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo ;
		}
		else if(url=='pa_payslip_factory' || url=='pa_payslip_factory2' || url=='pa_payslip_filiale' || url=='pa_payslip_filiale2'){
		  var empid=document.rpt.key.value;			    
		  paMonth=document.rpt.personal_Year.value+document.rpt.personal_Month.value;		  
		  if(empid==""){
		  alert("职号不能为空！");
		  return ;
		  }		  	
		  url = "/reportControlServlet?operation=crystal&reportName=" + url + "&paMonth=" + paMonth + "&empId=" + empid; 
		}else if(url=='pa_bonus_payslip_factory' || url=='pa_bonus_payslip_filiale'){
		  var empid=document.rpt.key.value;		 
		  paMonth=document.rpt.personal_Year.value+document.rpt.personal_Month.value;
		  var personal_bonusTypeCode=document.rpt.personal_bonusTypeCode.value;
		  var personal_bonusNo=document.rpt.personal_bonusNo.value;
		  if(empid==""){
		  alert("职号不能为空！");
		  return ;
		  }
		  url = "/reportControlServlet?operation=crystal&reportName=" + url + "&paMonth=" + paMonth + "&empId=" + empid+"&bonusTypeCode="+personal_bonusTypeCode+"&bonusNo="+personal_bonusNo; 
		}else 
		{
			alert("此报表不支持打印功能!!!!") ;
			return ;
		}
		//alert(url) ;
		window.open(url ,"");
	}
}

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
		alert('<ait:message messageID="alert.att.view.report.select_report" module="ar"/>');
	}else{
		//职位别起点工资
		if (url == 'pa_position_wage.jsp?')
		{
			var year = document.rpt.pa_post_execl_year.value
			url = "../../reports/pa_report/" + url + "&year=" + year ;
		}
		// 工厂支给
		else if (    url == 'pa_detail_factory'      || url == 'pa_bonus_detail_factory'
				  || url == 'pa_detail_part_factory' || url == 'pa_bonus_detail_part_factory'
				  || url == 'pa_detail_all_factory'  || url == 'pa_bonus_detail_all_factory'
		)
		{
			var distinction = "" ;
			var deptid = document.rpt.pa_factory_deptid.value ;
			var bonusTypeCode = document.rpt.pa_factory_bonusTypeCode.value ;
			var bonusNo = document.rpt.pa_factory_bonusNo.value ;
			var data =  document.getElementsByName("pa_factory_distinction") ;
			var size = data.length ;
			for (var i = 0 ; i < size ; i ++)
			{
				if( data[i].checked )
				{
					distinction += "," + data[i].value  ;
				}
			}		
			
			paMonth = document.rpt.pa_factory_Year.value + document.rpt.pa_factory_Month.value ;
			url = "/reportControlServlet?operation=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&distinction=" + encodeURIComponent(distinction.slice(1)) ;
		}		
				
		// 支社支给
		else if (   url == 'pa_detail_filiale'     || url == 'pa_bonus_detail_filiale'
				 || url == 'pa_detail_all_filiale' || url == 'pa_bonus_detail_all_filiale'
		)
		{
			var deptid = document.rpt.pa_filiale_deptid.value ;
			var bonusTypeCode = document.rpt.pa_filiale_bonusTypeCode.value ;
			var bonusNo = document.rpt.pa_filiale_bonusNo.value ;
			
			paMonth = document.rpt.pa_filiale_Year.value + document.rpt.pa_filiale_Month.value ;
			url = "/reportControlServlet?operation=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo ;
		}
		else if ( url == 'pa_detail_no_email' )
		{
		    var statTypeCode = document.rpt.no_email_statTypeCode.value ;
			var tableName = document.rpt.no_email_tableName.value ;
			var deptid = document.rpt.no_email_deptid.value ;
			var bonusTypeCode = document.rpt.no_email_bonusTypeCode.value ;
			var bonusNo = document.rpt.no_email_bonusNo.value ; 
			paMonth = document.rpt.no_email_Year.value + document.rpt.no_email_Month.value ;
			
			if ( tableName == 'PA_HISTORY' )
			{
				if ( statTypeCode == 'C_12067_1330306' )
				{
					url = 'pa_detail_all_factory' ;
				}
				else if ( statTypeCode == 'C_12067_1330308' )
				{
					url = 'pa_detail_all_filiale' ;
				}
				url = "/reportControlServlet?operation=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&noEmail=" + tableName  ;
			}
			else if ( tableName == 'PA_REPLENISHMENT_HISTORY' )
			{
				url = "/reportControlServlet?operation=pa_detail_replenishment&paMonth=" + paMonth + "&deptid=" + deptid + "&statTypeCode=" +  statTypeCode + "&retroactiveTaxType=RetroactiveTaxType01&noEmail=" + tableName  ;
			}
			else if ( tableName == 'PA_BONUS_HISTORY' )
			{
				if ( statTypeCode == 'C_12067_1330306' )
				{
					url = 'pa_bonus_detail_all_factory' ;
				}
				else if ( statTypeCode == 'C_12067_1330308' )
				{
					url = 'pa_bonus_detail_all_filiale' ;
				}
				url = "/reportControlServlet?operation=" + url + "&paMonth=" + paMonth + "&deptid=" + deptid + "&bonusTypeCode=" + bonusTypeCode + "&bonusNo=" + bonusNo + "&noEmail=" + tableName  ;
			}
						
		}else if(url=='pa_detail_factory_average' || url=='pa_detail_filiale_average' ){
		var paStartMonth=document.rpt.averageStartYear.value+document.rpt.averageStartMonth.value;
		var paEndMonth=document.rpt.averageEndYear.value+document.rpt.averageEndMonth.value;
		url = "/reportControlServlet?operation=" + url + "&paStartMonth=" + paStartMonth + "&paEndMonth=" + paEndMonth ;
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
		layer.style.left = iBleft-3;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft-3;
		  
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
//-->

</SCRIPT>
			  