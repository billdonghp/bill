<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.Vector,com.ait.pa.PaReport"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<%@ page import="com.ait.sy.common.PaHelper"%>

<html>
<head>
<!-- pa0506.jsp -->
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
	String statTypeCode = request.getParameter("statTypeCode");
	
	
	
	
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
	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	String today=timeFormatter.format(d);
	String Project=request.getParameter("Project");
	String year1=request.getParameter("year");
	String month1=request.getParameter("month");
	request.setAttribute("Project",Project);
	request.setAttribute("year",year1);
	request.setAttribute("month",month1);
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
	   <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					工资项目分配</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		分配项目&nbsp;&nbsp;
		<select name="Project">
		<c:if test="${admin.cpnyId != '59000000' && admin.cpnyId != '61000000' && admin.cpnyId != 'FN000000' }">
		<option value="FactoriesPayProvision" <c:if test="${Project=='FactoriesPayProvision'}">selected="selected"</c:if>>工厂工资计提</option>
		<option value="FactoriesPayProvisionalProvision"  <c:if test="${Project=='FactoriesPayProvisionalProvision'}">selected="selected"</c:if>>工厂临时工工资计提</option>
			<c:if test="${admin.cpnyId != '60000000'}">
			<option value="FactoriesBonusesProvision" <c:if test="${Project=='FactoriesBonusesProvision'}">selected="selected"</c:if>>工厂奖金计提</option>
			</c:if>
			<c:if test="${admin.cpnyId == '60000000'}">
			<option value="FactoriesBonusesProvisionA" <c:if test="${Project=='FactoriesBonusesProvisionA'}">selected="selected"</c:if>>工厂A职员奖金计提</option>
			<option value="FactoriesBonusesProvisionB" <c:if test="${Project=='FactoriesBonusesProvisionB'}">selected="selected"</c:if>>工厂B职员奖金计提</option>
			</c:if>
		<option value="FactoriesEndowmentInsurance" <c:if test="${Project=='FactoriesEndowmentInsurance'}">selected="selected"</c:if>>工厂养老保险</option>
		<option value="FactoriesMedicalInsurance" <c:if test="${Project=='FactoriesMedicalInsurance'}">selected="selected"</c:if>>工厂医疗保险</option>
		<option value="FactoriesUnemployedInsurance" <c:if test="${Project=='FactoriesUnemployedInsurance'}">selected="selected"</c:if>>工厂待业保险</option>
		<option value="FactoriesInjuriesInsurance" <c:if test="${Project=='FactoriesInjuriesInsurance'}">selected="selected"</c:if>>工厂工伤保险</option>
		<option value="FactoriesGrowthInsurance" <c:if test="${Project=='FactoriesGrowthInsurance'}">selected="selected"</c:if>>工厂生育保险</option>
		<option value="FactoriesProvidentFundInsurance" <c:if test="${Project=='FactoriesProvidentFundInsurance'}">selected="selected"</c:if>>工厂住房公积金</option>
		<c:if test="${admin.cpnyId == '78000000' || admin.cpnyId == '63000000' }">
		<option value="FactoriesPayProvisionalProvision_G"  <c:if test="${Project=='FactoriesPayProvisionalProvision_G'}">selected="selected"</c:if>>工厂退休返聘人员工资计提</option>
			<option value="FactoriesPayProvision_G" <c:if test="${Project=='FactoriesPayProvision_G'}">selected="selected"</c:if>>工厂工人工资计提</option>
				<option value="FactoriesEndowmentInsurance_G" <c:if test="${Project=='FactoriesEndowmentInsurance_G'}">selected="selected"</c:if>>工厂工人养老保险</option>
		<option value="FactoriesMedicalInsurance_G" <c:if test="${Project=='FactoriesMedicalInsurance_G'}">selected="selected"</c:if>>工厂工人医疗保险</option>
		<option value="FactoriesUnemployedInsurance_G" <c:if test="${Project=='FactoriesUnemployedInsurance_G'}">selected="selected"</c:if>>工厂工人待业保险</option>
		<option value="FactoriesInjuriesInsurance_G" <c:if test="${Project=='FactoriesInjuriesInsurance_G'}">selected="selected"</c:if>>工厂工人工伤保险</option>
		<option value="FactoriesGrowthInsurance_G" <c:if test="${Project=='FactoriesGrowthInsurance_G'}">selected="selected"</c:if>>工厂工人生育保险</option>
		<option value="FactoriesProvidentFundInsurance_G" <c:if test="${Project=='FactoriesProvidentFundInsurance_G'}">selected="selected"</c:if>>工厂工人住房公积金</option>
		<option value="FactoriesPayProvision_Z" <c:if test="${Project=='FactoriesPayProvision_Z'}">selected="selected"</c:if>>工厂职员工资计提</option>
			<option value="FactoriesEndowmentInsurance_Z" <c:if test="${Project=='FactoriesEndowmentInsurance_Z'}">selected="selected"</c:if>>工厂职员养老保险</option>
		<option value="FactoriesMedicalInsurance_Z" <c:if test="${Project=='FactoriesMedicalInsurance_Z'}">selected="selected"</c:if>>工厂职员医疗保险</option>
		<option value="FactoriesUnemployedInsurance_Z" <c:if test="${Project=='FactoriesUnemployedInsurance_Z'}">selected="selected"</c:if>>工厂职员待业保险</option>
		<option value="FactoriesInjuriesInsurance_Z" <c:if test="${Project=='FactoriesInjuriesInsurance_Z'}">selected="selected"</c:if>>工厂职员工伤保险</option>
		<option value="FactoriesGrowthInsurance_Z" <c:if test="${Project=='FactoriesGrowthInsurance_Z'}">selected="selected"</c:if>>工厂职员生育保险</option>
		<option value="FactoriesProvidentFundInsurance_Z" <c:if test="${Project=='FactoriesProvidentFundInsurance_Z'}">selected="selected"</c:if>>工厂职员住房公积金</option>
		</c:if>
		<option value="BranchPayProvision" <c:if test="${Project=='BranchPayProvision'}">selected="selected"</c:if>>支社工资计提</option>
			<c:if test="${admin.cpnyId != '60000000'}">
			<option value="BranchFixedBonusesProvision" <c:if test="${Project=='BranchFixedBonusesProvision'}">selected="selected"</c:if>>支社固定奖金计提</option>
			</c:if>
			<c:if test="${admin.cpnyId == '60000000'}">
			<option value="BranchFixedBonusesProvisionA" <c:if test="${Project=='BranchFixedBonusesProvisionA'}">selected="selected"</c:if>>支社A职员固定奖金计提</option>
			<option value="BranchFixedBonusesProvisionB" <c:if test="${Project=='BranchFixedBonusesProvisionB'}">selected="selected"</c:if>>支社B职员固定奖金计提</option>
			</c:if>
		<option value="BranchPerformanceResultsBonusesProvision" <c:if test="${Project=='BranchPerformanceResultsBonusesProvision'}">selected="selected"</c:if>>支社成果业绩奖金计提</option>
		<option value="BranchLegalRoomBonusesProvision" <c:if test="${Project=='BranchLegalRoomBonusesProvision'}">selected="selected"</c:if>>法律事务室诉讼奖金计提</option>
		<option value="BranchProvisionLaborInsurance" <c:if test="${Project=='BranchProvisionLaborInsurance'}">selected="selected"</c:if>>支社计提公司负担劳动保险</option>
		<option value="BranchBurdenManagementFees" <c:if test="${Project=='BranchBurdenManagementFees'}">selected="selected"</c:if>>支社负担国企管理费</option>
		<option value="RetirementCompensation" <c:if test="${Project=='RetirementCompensation'}">selected="selected"</c:if>>退职补偿金</option>
		
		</c:if>
		<c:if test="${admin.cpnyId == '59000000'}">
		<option value="FactoriesPayProvision59B" <c:if test="${Project=='FactoriesPayProvision59B'}">selected="selected"</c:if>>工资计提(北京)</option>	
		<option value="FactoriesEndowmentInsurance59B" <c:if test="${Project=='FactoriesEndowmentInsurance59B'}">selected="selected"</c:if>>养老保险(北京)</option>
		<option value="FactoriesMedicalInsurance59B" <c:if test="${Project=='FactoriesMedicalInsurance59B'}">selected="selected"</c:if>>医疗保险(北京)</option>
		<option value="FactoriesUnemployedInsurance59B" <c:if test="${Project=='FactoriesUnemployedInsurance59B'}">selected="selected"</c:if>>待业保险(北京)</option>
		<option value="FactoriesInjuriesInsurance59B" <c:if test="${Project=='FactoriesInjuriesInsurance59B'}">selected="selected"</c:if>>工伤保险(北京)</option>
		<option value="FactoriesGrowthInsurance59B" <c:if test="${Project=='FactoriesGrowthInsurance59B'}">selected="selected"</c:if>>生育保险(北京)</option>
		<option value="FactoriesProvidentFundInsurance59B" <c:if test="${Project=='FactoriesProvidentFundInsurance59B'}">selected="selected"</c:if>>住房公积金(北京)</option>
		<option value="FactoriesBonusesProvision59B" <c:if test="${Project=='FactoriesBonusesProvision59B'}">selected="selected"</c:if>>奖金计提(北京)</option>
		<option value="BranchBurdenManagementFees59B" <c:if test="${Project=='BranchBurdenManagementFees59B'}">selected="selected"</c:if>>负担国企管理费(北京)</option>
		<option value="FactoriesPayProvision59Y" <c:if test="${Project=='FactoriesPayProvision59Y'}">selected="selected"</c:if>>工资计提(烟台)</option>	
		<option value="FactoriesEndowmentInsurance59Y" <c:if test="${Project=='FactoriesEndowmentInsurance59Y'}">selected="selected"</c:if>>养老保险(烟台)</option>
		<option value="FactoriesMedicalInsurance59Y" <c:if test="${Project=='FactoriesMedicalInsurance59Y'}">selected="selected"</c:if>>医疗保险(烟台)</option>
		<option value="FactoriesUnemployedInsurance59Y" <c:if test="${Project=='FactoriesUnemployedInsurance59Y'}">selected="selected"</c:if>>待业保险(烟台)</option>
		<option value="FactoriesInjuriesInsurance59Y" <c:if test="${Project=='FactoriesInjuriesInsurance59Y'}">selected="selected"</c:if>>工伤保险(烟台)</option>
		<option value="FactoriesGrowthInsurance59Y" <c:if test="${Project=='FactoriesGrowthInsurance59Y'}">selected="selected"</c:if>>生育保险(烟台)</option>
		<option value="FactoriesProvidentFundInsurance59Y" <c:if test="${Project=='FactoriesProvidentFundInsurance59Y'}">selected="selected"</c:if>>住房公积金(烟台)</option>
		<option value="FactoriesBonusesProvision59Y" <c:if test="${Project=='FactoriesBonusesProvision59Y'}">selected="selected"</c:if>>奖金计提(烟台)</option>
		<option value="BranchBurdenManagementFees59Y" <c:if test="${Project=='BranchBurdenManagementFees59Y'}">selected="selected"</c:if>>负担国企管理费(烟台)</option>
		</c:if>
		
		<c:if test="${admin.cpnyId == '61000000' || admin.cpnyId == 'FN000000'}">
		<option value="FactoriesPayProvision" <c:if test="${Project=='FactoriesPayProvision'}">selected="selected"</c:if>>工厂工资计提</option>
		<option value="FactoriesPayProvisionalProvision"  <c:if test="${Project=='FactoriesPayProvisionalProvision'}">selected="selected"</c:if>>工厂临时工工资计提</option>
		<option value="FactoriesBonusesProvision" <c:if test="${Project=='FactoriesBonusesProvision'}">selected="selected"</c:if>>工厂奖金计提</option>
		<option value="FactoriesProvidentFundInsurance" <c:if test="${Project=='FactoriesProvidentFundInsurance'}">selected="selected"</c:if>>工厂住房公积金</option>
		<option value="FactoriesPayProvision_61_G" <c:if test="${Project=='FactoriesPayProvision_61_G'}">selected="selected"</c:if>>工厂工人工资计提</option>
		<option value="FactoriesPayProvision_61_Z" <c:if test="${Project=='FactoriesPayProvision_61_Z'}">selected="selected"</c:if>>工厂职员工资计提</option>
		<option value="FactoriesProvidentFundInsurance_61_G" <c:if test="${Project=='FactoriesProvidentFundInsurance_61_G'}">selected="selected"</c:if>>工厂工人住房公积金</option>
		<option value="FactoriesProvidentFundInsurance_61_Z" <c:if test="${Project=='FactoriesProvidentFundInsurance_61_Z'}">selected="selected"</c:if>>工厂职员住房公积金</option>
		<option value="BranchPayProvision_61" <c:if test="${Project=='BranchPayProvision_61'}">selected="selected"</c:if>>支社工资计提</option>
		<option value="BranchProvidentFundInsurance_61" <c:if test="${Project=='BranchProvidentFundInsurance_61'}">selected="selected"</c:if>>支社住房公积金</option>
		<option value="BranchLegalRoomBonusesProvision" <c:if test="${Project=='BranchLegalRoomBonusesProvision'}">selected="selected"</c:if>>法律事务室诉讼奖金计提</option>
		<option value="RetirementCompensation" <c:if test="${Project=='RetirementCompensation'}">selected="selected"</c:if>>退职补偿金</option>
		</c:if>
		
		
		
		</select>
		 基准年月
		 :&nbsp;<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
		      输入基数&nbsp;&nbsp;&nbsp;<input name="InputBase" value=""  style="width:70px" type="text" onkeyup="CheckNumber(this.value)">
		      奖金编号&nbsp;&nbsp;&nbsp;<input name="BonuseNo" value=""  style="width:30px" type="text" onkeyup="CheckNumber(this.value)">
				发放工资日期&nbsp;&nbsp;&nbsp;<input type="text" name="PaymentDates" class="content" style="width:70px "  value="<%=today%>" readonly onClick="setday(this);">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="paymentPay()">生成凭证</span>
		 </td>
		</tr>
		<tr align="center">
		<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
			 		<ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:outExcel();" style="cursor:hand"/> |				    
					<ait:image src="/images/btn/Excel_Imp.gif" align="absmiddle" onclick="javascript:inputExcel();" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		</tr>		
		</table>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					凭证数据导出</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		
		 基准年月:&nbsp;<ait:date yearName="interfaceYear" monthName="interfaceMonth" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />			
	    &nbsp;&nbsp;&nbsp;<ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:interfaceOutExcel();" style="cursor:hand"/>
	    </td>
		</tr>		
		</table>	
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					凭证数据删除</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td width="*" height="30"  align="center" valign="middle" class="info_title_01">
		分配项目&nbsp;&nbsp;<select name="deleteProject">
		<option value="BranchPerformanceResultsBonusesProvision">支社成果业绩奖金计提</option>
		<option value="BranchLegalRoomBonusesProvision">法律事务室诉讼奖金计提</option>
		</select>
		 凭证年月:&nbsp;<ait:date yearName="deleteYear" monthName="deleteMonth" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />&nbsp;文件编号：<input type="text" name="fileno" value="" size="4">			
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="deleteInterfaceData()">清空数据</span>
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
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">

function paymentPay(){
  var pamonth = document.all("month").value;
  var payear=document.all("year").value;
  var padate=document.all("PaymentDates").value;    
  var url = "/paControlServlet?operation=paInterface&content="+document.all("Project").value+"&AnnualPaymentsYear="+payear+"&OnPayMonth="+pamonth+"&PaymentDates="+padate+"&InputBase="+document.all("InputBase").value+"&BonuseNo="+document.all("BonuseNo").value;
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
  
 function outExcel(){
   var pamonth = document.all("month").value;
   var payear=document.all("year").value;
   var Project=document.all("Project").value;   
  var url = "/paControlServlet?operation=paInterface&content=outExcel&AnnualPaymentsYear="+payear+"&OnPayMonth="+pamonth+"&Project="+Project;
  location.href = url; 
  }
  function inputExcel()	{
	  var pamonth = document.all("month").value;
	  var payear=document.all("year").value;
	  var Project=document.all("Project").value;  
	   url="/paControlServlet?operation=paInterface$content=inputExcel$AnnualPaymentsYear="+payear+"$OnPayMonth="+pamonth+"$Project="+Project;
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function interfaceOutExcel(){
      var pamonth = document.all("interfaceMonth").value;
	  var payear=document.all("interfaceYear").value;
	  var url = "/paControlServlet?operation=paInterface&content=interfaceOutExcel&pamonth="+pamonth+"&payear="+payear;
      location.href = url; 

}
function deleteInterfaceData(){
      var pamonth = document.all("deleteMonth").value;
	  var payear=document.all("deleteYear").value;
	  var deleteProject=document.all("deleteProject").value;
	  var fileno=document.all("fileno").value;
	  var url = "/paControlServlet?operation=paInterface&content=deleteInterfaceData&pamonth="+pamonth+"&payear="+payear+"&deleteProject="+deleteProject+"&fileno="+fileno;
      location.href = url; 

}
//-->
</SCRIPT>