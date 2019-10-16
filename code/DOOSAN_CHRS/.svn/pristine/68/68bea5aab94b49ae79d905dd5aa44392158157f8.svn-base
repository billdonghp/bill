<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.util.StringUtil"%>
<%@ page import="com.ait.reports.reportservices.PaReportService,com.ait.sqlmap.util.ObjectBindUtil,com.ait.sqlmap.util.SimpleMap"%>
<jsp:useBean id="admin" scope="session" class="com.ait.sy.bean.AdminBean" />
<html>
<head>
<%
	PaReportService service = new PaReportService() ;
	SimpleMap paData = new SimpleMap() ;
	SimpleMap parameterObject = ObjectBindUtil.getData(request);
	
	parameterObject.setString("cpnyId", admin.getCpnyId());
	List recordList = service.retrieve780Report0210List(parameterObject);

	if (recordList != null && recordList.size() == 1)
	{
		paData = (SimpleMap)recordList.get(0) ;
	}
	
	request.setAttribute("paData",paData) ;
%>
<title></title>
<%@ include file="../inc/meta.jsp" %>
</head>
<script language="javascript" src="../js/meizzMonth.js"></script>
<script language="JavaScript" type="text/JavaScript">
	if (window.parent){
		if (window.parent.document.getElementById("deptmentName")) {
			window.parent.document.getElementById("deptmentName").innerHTML = "${paData.DEPARTMENT}" ;
		}
		
		if (window.parent.document.getElementById("data_started")) {
			window.parent.document.getElementById("data_started").innerHTML = "${paData.DATE_STARTED}" ;
		}
		
		if (window.parent.document.getElementById("name")) {
			window.parent.document.getElementById("name").innerHTML = "${paData.CHINESENAME}" ;
		}
	}
function bonus_trim(paData)  { return paData.replace("," ,"") ;  }

function paCal(){
	
	document.maintain_data.THIS_TOTAL_SUPPORT.value = 
		  (parseFloat(bonus_trim(document.maintain_data.BASE_PAY.value)) + parseFloat(bonus_trim(document.maintain_data.RESIDENTIAL_GRANTS.value))
		+ parseFloat(bonus_trim(document.maintain_data.DUTIES_ALLOWANCE.value)) + parseFloat(bonus_trim(document.maintain_data.SPECIAL_GRANTS.value)) 
		
		+ parseFloat(bonus_trim(document.maintain_data.REGIONAL_GRANTS.value)) + parseFloat(bonus_trim(document.maintain_data.OTHER_COMPENSATION.value))
		+ parseFloat(bonus_trim(document.maintain_data.TO_THE_OTHER.value)) + parseFloat(bonus_trim(document.maintain_data.STICKS_TO_THE_WRONG.value))
		
		- parseFloat(bonus_trim(document.maintain_data.NOT_ATTENDANCE_MINUS.value)) - parseFloat(bonus_trim(document.maintain_data.LATE_MINUS.value)) 
		- parseFloat(bonus_trim(document.maintain_data.LEAVE_EARLY_MINUS.value)) - parseFloat(bonus_trim(document.maintain_data.LEAVE_MINUS.value)) 
		
		- parseFloat(bonus_trim(document.maintain_data.SICK_MINUS.value)) - parseFloat(bonus_trim(document.maintain_data.VACATION_MINUS.value)) 
		- parseFloat(bonus_trim(document.maintain_data.ABSENTEEISM_MINUS.value)) - parseFloat(bonus_trim(document.maintain_data.LEVEL_OFF_MINUS.value))
		
		 - parseFloat(bonus_trim(document.maintain_data.LESS_THREE_MONTHS_SICK_MINUS.value)) - parseFloat(bonus_trim(document.maintain_data.MORE_THREE_MONTHS_SICK_MINUS.value))
		- parseFloat(bonus_trim(document.maintain_data.OTHER_LESS.value)) - parseFloat(bonus_trim(document.maintain_data.REDUCE_ERRORS.value)) 
		
		- parseFloat(bonus_trim(document.maintain_data.TRIAL_MINUS.value))).toFixed(2) ;
	
	
	document.maintain_data.TOTAL_DEDUCTIONS.value =
	      (parseFloat(bonus_trim(document.maintain_data.PAYMENT_OF_INDIVIDUAL.value)) + parseFloat(bonus_trim(document.maintain_data.THIS_ACTUAL_TAX.value))).toFixed(2) ;
	
	document.maintain_data.THIS_ACTUAL_WAGE.value = (parseFloat(bonus_trim(document.maintain_data.THIS_TOTAL_SUPPORT.value)) - parseFloat(bonus_trim(document.maintain_data.TOTAL_DEDUCTIONS.value))).toFixed(2) ; 
}

function save(){
	
	if (document.maintain_data.personId.value == "")
	{
		alert("没有搜索的数据!!!") ;
		return ;
	}

	document.maintain_data.fireSubmit(); 
}



</script>

<body>

<table width="80%" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form name="maintain_data" method="post" action="/paControlServlet?operation=updatePaHistory" >
<input type="hidden" name="url" value="pa_maintain_data_filialess_78000000.jsp" />
<input type="hidden" name="tableName" value="PA_HISTORY" />
<input type="hidden" name="personId" value="${paData.PERSON_ID}" />
<input type="hidden" name="empId" value="${paData.EMPID}" />
<input type="hidden" name="statTypeCode" value="${paData.ATTENDANCE_PERIOD_CODE}" />
<input type="hidden" name="paMonth" value="${paData.PA_MONTH}" />
  <tr>
  	<td align="right" colspan="2">
  		<input type="checkBox" name="paCalcFlag" />&nbsp;&nbsp;是否自动计算合计项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" onclick="javascript:save();" style="cursor:hand" title="保存" enTitle="save" />
  	</td>
  </tr>
  
  <tr align="center" >
    <td nowrap>
    	<table>
    		<tr>
    			<td colspan="7" align="center" class="info_title_01"><font size="5">收入</font></td>
    		</tr>	
    		<tr>
    			<td class="info_title_01">基本工资</td>
    			<td class="info_title_01">住宅补助</td>
    			<td class="info_title_01">&nbsp;</td>
    			<td class="info_title_01">职务津贴</td>
    			<td class="info_title_01">特殊补助</td>
    			<td class="info_title_01">地区补助</td>
    			<td class="info_title_01">其他补偿</td>
    		</tr>
    		<tr>
    			<!-- 因为奖金的计算公式的原因，奖金的基本工资，取得是级号工资 -->
    			<td><input type="text" size="8" maxlength="10" name="BASE_PAY"  value="${paData.PAY_CLASS_WAGES}" required  float/> </td>
    			<td><input type="text" size="8" maxlength="10" name="RESIDENTIAL_GRANTS"  value="${paData.RESIDENTIAL_GRANTS}" required  float/></td>
    			<td>&nbsp;</td>
    			<td><input type="text" size="8" maxlength="10" name="DUTIES_ALLOWANCE"  value="${paData.DUTIES_ALLOWANCE}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="SPECIAL_GRANTS"  value="${paData.SPECIAL_GRANTS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="REGIONAL_GRANTS"  value="${paData.REGIONAL_GRANTS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="OTHER_COMPENSATION"  value="${paData.OTHER_COMPENSATION}" required  float/></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">其他支给</td>
    			<td class="info_title_01">支给错误</td>
    			<td class="info_title_01">&nbsp;</td>
    			<td class="info_title_01">绩效工资</td>
    			<td class="info_title_01">未勤减除</td>
    			<td class="info_title_01">迟到扣除</td>
    			<td class="info_title_01">事假减除</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="TO_THE_OTHER"  value="${paData.TO_THE_OTHER}" required  float/> </td>
    			<td><input type="text" size="8" maxlength="10" name="STICKS_TO_THE_WRONG"  value="${paData.STICKS_TO_THE_WRONG}" required  float/></td>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    			<td><input type="text" size="8" maxlength="10" name="NOT_ATTENDANCE_MINUS"  value="${paData.NOT_ATTENDANCE_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="LATE_MINUS"  value="${paData.LATE_MINUS}" required  float/></td><!-- LATE_MINUS + LEAVE_EARLY_MINUS -->
    			<td><input type="text" size="8" maxlength="10" name="LEAVE_MINUS"  value="${paData.LEAVE_MINUS}" required  float/></td><!-- LEAVE_MINUS + SICK_MINUS -->
    		</tr>
    		<tr>
    			<td class="info_title_01">产假减除</td>
    			<td class="info_title_01">休职减除</td>
     			<td class="info_title_01">短期放假减除</td>
    			<td class="info_title_01">长期放假减除</td>
    			<td class="info_title_01">旷工减除</td>
    			<td class="info_title_01">其他减除</td>
    			<td class="info_title_01">减除错误</td>
    			
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="MATERNITY_MINUS"  value="${paData.MATERNITY_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="LEVEL_OFF_MINUS"  value="${paData.LEVEL_OFF_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="HOLIDAY_MINUS_SHORT" value="${paData.HOLIDAY_MINUS_SHORT}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="HOLIDAY_MINUS_LONG" value="${paData.HOLIDAY_MINUS_LONG}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="ABSENTEEISM_MINUS"  value="${paData.ABSENTEEISM_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="OTHER_LESS"  value="${paData.OTHER_LESS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="REDUCE_ERRORS"  value="${paData.REDUCE_ERRORS}" required  float/></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">试用扣除</td>
    			<td class="info_title_01">病假减除</td>
    			<td class="info_title_01">&nbsp;</td>
    			<td class="info_title_01">早退减除</td>
    			<td class="info_title_01">病休1</td>
    			<td class="info_title_01">病休2</td>
    			<td class="info_title_01">支给合计</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="TRIAL_MINUS"  value="${paData.TRIAL_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="SICK_MINUS"  value="${paData.SICK_MINUS}" required  float/></td>
    			<td>&nbsp;</td>
    			<td><input type="text" size="8" maxlength="10" name="LEAVE_EARLY_MINUS"  value="${paData.LEAVE_EARLY_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="LESS_THREE_MONTHS_SICK_MINUS"  value="${paData.LESS_THREE_MONTHS_SICK_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="MORE_THREE_MONTHS_SICK_MINUS"  value="${paData.MORE_THREE_MONTHS_SICK_MINUS}" required  float/></td>
    			<td><input type="text" size="8" maxlength="10" name="THIS_TOTAL_SUPPORT" readonly="readonly"  value="${paData.THIS_TOTAL_SUPPORT}" required  float/></td>
    		</tr>
    	</table>
    
    </td>
    
    <td  nowrap>
    	<table>
    		<tr>
    			<td colspan="2" align="center" class="info_title_01"><font size="5">支出</font></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">个人负担</td>
    			<td class="info_title_01">&nbsp;</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="PAYMENT_OF_INDIVIDUAL"  value="${paData.PAYMENT_OF_INDIVIDUAL}" required  float/></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">所得税</td>
    			<td></td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="THIS_ACTUAL_TAX"  value="${paData.THIS_ACTUAL_TAX}" required  float/></td>
    			<td></td>
    		</tr>
    		<tr>
    			<td class="info_title_01">&nbsp;</td>
    			<td class="info_title_01">&nbsp;</td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>&nbsp;</td>
    		</tr>
    		<tr>
    			<td class="info_title_01">减除合计</td>
    			<td class="info_title_01">实领工资</td>
    		</tr>
    		<tr>
    			<td><input type="text" size="8" maxlength="10" name="TOTAL_DEDUCTIONS" readonly="readonly" value="${paData.TOTAL_DEDUCTIONS}" required  float/> </td>
    			<td><input type="text" size="8" maxlength="10" name="THIS_ACTUAL_WAGE" readonly="readonly" value="${paData.THIS_ACTUAL_WAGE}" required  float/></td>
    		</tr>
    	</table>
    </td>
  </tr>
</form>
</table>
</body>
<ait:xjos />
</html>