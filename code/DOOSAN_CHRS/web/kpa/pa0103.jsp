<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.sy.common.PaHelper" />
<jsp:directive.page import="com.ait.pa.PaReport" />
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap" />
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil" />
<jsp:directive.page import="com.ait.util.StringUtil" />
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<script language="JavaScript" type="text/JavaScript">
function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
	$('empID').value=cell.childNodes[0].firstChild.nodeValue;
	layerClose();
}

var time=null;
function SearchContent(condition,id){		
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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition)+"&id="+id;
		var inputBox = document.getElementById(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-135;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		
		layer.style.visibility = 'visible';
		new Ajax.Updater({success: layer}, url, {method: 'post', parameters: pars, onFailure: layerClose});	
}
function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=pa0103&operation=searchPaEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
function Search(){

	document.searchMonthPa.submit();
}
<%
SimpleMap sMap = new SimpleMap();
sMap = ObjectBindUtil.getData(request);
request.setAttribute("sMap",sMap);

String PAMonth=sMap.getString("YEAR")+sMap.getString("MONTH");

Integer lockFlag = PaHelper.getProcessLockFlag(PAMonth);
%>
</script>
<body>
<%
	SimpleMap paMap = new SimpleMap();
	PaReport paReport = new PaReport();
	SimpleMap parameterObject = new SimpleMap();
	parameterObject.setString("PaMonth", PAMonth);
	parameterObject.setString("empID", StringUtil.checkNull(sMap.getString("empID")));

	paMap = (SimpleMap) paReport.RetrievePaInfo(parameterObject);
	request.setAttribute("paMap", paMap);

%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_2_notSaveButton.jsp"%>
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
		<form name="searchMonthPa" method="POST" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--月工资-->
					<ait:message  messageID="display.pay.view.monthly" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			　class="dr_d">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="11%" class="info_title_01"><!--姓 名-->
					<ait:message  messageID="display.mutual.name" /></td>
						<td width="11%" class="info_content_01">
		       		<ait:content enContent="${paMap.PINYINNAME}" zhContent="${paMap.CHINESENAME}" koContent="${paMap.KOREANNAME}"/>
		       	</td>
		
						<td width="11%" height="30" class="info_title_01"><!--工资发放月-->
					<ait:message  messageID="display.mutual.pay_month" /></td>
						<td width="22%" class="info_content_01"><ait:date
							yearName="YEAR" monthName="MONTH" yearSelected="${sMap.YEAR}"
							monthSelected="${sMap.MONTH}" yearPlus="10" /></td>
						<td width="11%" class="info_title_01"><!--工 号-->
					<ait:message  messageID="display.mutual.emp_id_2" /></td>
						<td width="28%" class="info_content_01"><input id="empID"
							name="empID" size="8" value="<c:out value='${sMap.empID}'/>"
							onKeyUp="SearchContent(this.value,this.id)"  /> 
							<ait:image src="/images/btn/Dep.gif" align="absmiddle" onclick="showDept();" style="cursor:hand" />
							</td>
		
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			　class="dr_d">
			<tr>
				<td height="1" class="title_line_01"></td>
			</tr>
			<tr>
				<td height="2" class="title_line_02"></td>
			</tr>
			<tr>
				<td height="1"></td>
			</tr>
			<tr>
				<td class="line">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td width="11%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department" /></td>
						<td width="11%" class="info_content_01">${paMap.DEPARTMENT}
		       	</td>
						<td width="11%" height="30" class="info_title_01"><!--职位-->
					<ait:message  messageID="display.mutual.position" /></td>
						<td width="11%" class="info_content_01">${paMap.POSITION}
		       	</td>
						<td width="11%" height="30" class="info_title_01"><!--职务-->
					<ait:message  messageID="display.mutual.post"  /></td>
						<td width="11%" class="info_content_01">${paMap.POST}
		       	</td>
						<td width="11%" class="info_title_01"><!--入社日期-->
					<ait:message  messageID="display.mutual.hire_date" /></td>
						<td width="17%" class="info_content_01">${paMap.JOIN_DATE}</td>
					</tr>
		
				</table>
				</td>
			</tr>
		</table>
		</form>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			class="dr_d">
			<tr>
				<td width="11%" height="30" class="info_title_01"><!--应付合计-->
					应付合计</td>
				<td width="11%" class="info_content_01">${paMap.MEET_TOGETHER + 0}</td>
				<td width="11%" height="30" class="info_title_01"><!--扣除总额-->
					扣除总额</td>
				<td width="11%" class="info_content_01">${paMap.MINUS_SUBTOTAL + paMap.TOTAL_DEDUCTIONS}</td>
				<td width="11%" height="30" class="info_title_01"><!--本次实领工资-->
					本次实领工资</td>
				<td width="11%" class="info_content_01">${paMap.THIS_ACTUAL_WAGE + 0}</td>
				<td width="11%" height="30" class="info_title_01"><!--工厂负担总额-->
					工厂负担总额</td>
				<td width="17%" class="info_content_01">${paMap.THIS_ACTUAL_WAGE + paMap.TOTAL_INSURANCE_COMPANIES + paMap.COMPANY_HPFS}</td>
			</tr>
		</table>
		<table width="950" align="center" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="6"></td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td width="23%" height="30"><span class="title1"><!--工资内容-->
					<ait:message  messageID="display.pay.view.monthly.detail" module="pay"/></span></td>
				<td width="8"></td>
				<td width="23%"><span class="title1"><!--考 勤-->
					<ait:message  messageID="display.pay.view.monthly.attendance" module="pay"/></span></td>
				<td width="8"></td>
				<td width="23%"><span class="title1"><!--个人扣除-->
					<ait:message  messageID="display.pay.view.monthly.deduction_individual" module="pay"/></span></td>
				<td width="8"></td>
				<td width="23%"><span class="title1"><!--公司支付社保-->
					<ait:message  messageID="display.pay.view.monthly.social_employer_2" module="pay"/></span></td>
			</tr>
			<tr>
				<td height="365" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					　class="dr_d">
					<tr>
						<td class="line">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="dr_d">
							<tr>
								<td colspan="2" height="33" class="info_title_01" nowrap><!--基本工资-->
					<ait:message  messageID="display.mutual.standard" /></td>
								<td height="33" class="info_content_01">${paMap.BASE_PAY + 0}</td>
							</tr>
							<tr>
								<td width="16%" height="10" rowspan="4" class="info_title_00" nowrap><!--津 贴-->
					<ait:message  messageID="display.mutual.allowance" /></td>
								<td width="50%" height="30" class="info_title_01" nowrap><!--职务津贴-->
					<ait:message  messageID="display.mutual.position_allowance" /></td>
								<td height="30" class="info_content_01">${paMap.DUTIES_ALLOWANCE + 0}</td>
							</tr>
					         <tr>
					           <td height="30" class="info_title_01"><!--特殊津贴-->
					<ait:message  messageID="display.mutual.special_allowance" /></td>
					           <td class="info_content_01">${paMap.SPECIAL_GRANTS + 0}</td>
					         </tr>
					         <tr>
					           <td height="30" class="info_title_01"><!--住房补助-->
					<ait:message  messageID="display.mutual.allowance_subsidy" /></td>
					           <td class="info_content_01">${paMap.RESIDENTIAL_GRANTS + 0}</td>
					         </tr>
					         <tr>
					           <td height="30" class="info_title_01"><!--值班补助-->
					值班补助</td>
					           <td class="info_content_01">${paMap.DUTY_SUBSIDIES+ 0}</td>
					         </tr>
					         <tr>
						           <td width="14%" rowspan="5" class="info_title_00" nowrap="nowrap"><!--加 减 项-->
									<ait:message  messageID="display.pay.view.monthly.variance" module="pay"/></td>
						           <td height="30"  class="info_title_01" nowrap="nowrap"><!--支给错误-->支给错误</td>
						           <td width="58%" class="info_content_01">${paMap.STICKS_TO_THE_WRONG + 0}</td>
						     </tr>
							 <tr>
						           <td height="30"  class="info_title_01" nowrap="nowrap"><!--其他支给-->其他支给</td>
						           <td width="58%" class="info_content_01">${paMap.TO_THE_OTHER + 0}</td>
						     </tr>
						     <tr>
						           <td height="30" class="info_title_01" nowrap="nowrap"><!--住宅减除-->住宅减除</td>
						           <td width="58%" class="info_content_01">${paMap.RESIDENTIAL_MINUS + 0}</td>
						     </tr>
						     <tr>
						           <td height="30" class="info_title_01" nowrap="nowrap"><!--减除错误-->减除错误</td>
						           <td width="58%" class="info_content_01">${paMap.REDUCE_ERRORS + 0}</td>
						     </tr>
						     <tr>
						           <td height="30" class="info_title_01" nowrap="nowrap"><!--其他减除-->其他减除</td>
						           <td width="58%" class="info_content_01">${paMap.OTHER_LESS + 0}</td>
						     </tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
				<td></td>
				<td valign="top"">

				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							　class="dr_d">
							<tr>
								<td class="line">
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									class="dr_d">
									<tr>
										<td width="16%" height="10" rowspan="13" class="info_title_00" nowrap><!--考 勤 扣 款-->
					<ait:message  messageID="display.pay.view.monthly.deduction" module="pay"/></td>
										<td height="30" class="info_title_01" nowrap><!--考勤扣款合计-->
					<ait:message  messageID="display.pay.view.monthly.total_deductions" module="pay"/></td>
										<td width="58%" class="info_content_01">${paMap.ATTENDANCE_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--病假扣款-->
					<ait:message  messageID="display.mutual.sick_leave" /></td>
										<td class="info_content_01">${paMap.SICK_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--事假扣款-->
					<ait:message  messageID="display.mutual.casual_leave" /></td>
										<td class="info_content_01">${paMap.LEAVE_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--旷工扣款-->
					<ait:message  messageID="display.mutual.absenteeism" /></td>
										<td class="info_content_01">${paMap.ABSENTEEISM_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--休职扣款-->
					<ait:message  messageID="display.mutual.unpaid_leave" /></td>
										<td class="info_content_01">${paMap.LEVEL_OFF_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--迟到早退扣款-->
					<ait:message  messageID="display.mutual.tt" /></td>
										<td class="info_content_01">${paMap.LATE_MINUS + paMap.LEAVE_EARLY_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--产假扣款-->
					<ait:message  messageID="display.mutual.birth_leave" /></td>
										<td class="info_content_01">${paMap.MATERNITY_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--病休3月内扣款-->
					<ait:message  messageID="display.mutual.ill1_leave" /></td>
										<td class="info_content_01">${paMap.LESS_THREE_MONTHS_SICK_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--病休3月以上扣款-->
					<ait:message  messageID="display.mutual.ill2_leave" /></td>
										<td class="info_content_01">${paMap.MORE_THREE_MONTHS_SICK_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--放假扣款-->
					<ait:message  messageID="display.mutual.rest_leave" /></td>
										<td class="info_content_01">${paMap.HOLIDAY_MINUS + 0}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--未勤扣款-->
					<ait:message  messageID="display.mutual.noduty_leave" /></td>
										<td class="info_content_01">${paMap.NOT_ATTENDANCE_MINUS + 0}</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>

				</td>
				<td>&nbsp;</td>
				<td valign="top">
		
		
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					　class="dr_d">
					<tr>
						<td class="line" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							class="dr_d">
							<tr>
								<td width="49%" colspan="2" height="30" class="info_title_01"
									nowrap><!--个人所得税-->
					<ait:message  messageID="display.mutual.income_tax" /></td>
								<td width="51%" colspan="2" class="info_content_01">${paMap.THIS_ACTUAL_TAX + 0}</td>
							</tr>
							<tr>
								<td width="16%" height="10" rowspan="5" class="info_title_00" nowrap><!--个 人 社 保-->
					<ait:message  messageID="display.pay.view.monthly.social_deductions" module="pay"/></td>
								<td height="30" class="info_title_01" nowrap><!--个人社保合计-->
					<ait:message  messageID="display.pay.view.monthly.total_social" module="pay"/></td>
								<td class="info_content_01">${paMap.TOTAL_PERSONAL_INSURANCE + 0}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--养 老-->
					<ait:message  messageID="display.mutual.retiree" /></td>
								<td class="info_content_01">${paMap.PERSONAL_PENSION + 0}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--医 疗-->
					<ait:message  messageID="display.mutual.medicare" /></td>
								<td class="info_content_01">${paMap.PERSONAL_MEDICAL + 0}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--失 业-->
					<ait:message  messageID="display.mutual.unemployment" /></td>
								<td class="info_content_01">${paMap.PERSONAL_UNEMPLOYED + 0}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--公积金-->
					<ait:message  messageID="display.mutual.fund" /></td>
								<td class="info_content_01">${paMap.PERSONAL_HOUSING_FUND + 0}</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
		
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="35"><span class="title1"><!--加班工资-->
					<ait:message  messageID="display.pay.view.monthly.ot_pay" module="pay"/></span></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								　class="dr_d">
								<tr>
									<td class="line">
									<table width="100%" border="0" cellpadding="0" cellspacing="1"
										class="dr_d">
										<tr>
											<td width="16%" height="10" rowspan="4" class="info_title_00" nowrap><!--加 班 工 资-->
					<ait:message  messageID="display.pay.view.monthly.ot_pay" module="pay"/></td>
											<td height="30" class="info_title_01" nowrap><!--加班合计-->
					<ait:message  messageID="display.pay.view.monthly.ot_total" module="pay"/></td>
											<td class="info_content_01">${paMap.PERFORMANCE_PAY2 + paMap.PERFORMANCE_PAY3}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--平 日-->
					<ait:message  messageID="display.pay.view.monthly.weekday" module="pay"/></td>
											<td width="58%"  class="info_content_01" >${paMap.PEACETIME_OVERTIME + 0}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--公休日-->
					<ait:message  messageID="display.pay.view.monthly.weekend" module="pay"/></td>
											<td width="58%" class="info_content_01" >${paMap.OVERTIME_REST_DAY + 0}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--节假日-->
					<ait:message  messageID="display.pay.view.monthly.holiday" module="pay"/></td>
											<td width="58%" class="info_content_01" >${paMap.OVERTIME_HOLIDAYS + 0}</td>
										</tr>
		
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
				  </table>
				</td>
				
				<td>&nbsp;</td>
				<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					　class="dr_d">
					<td class="line">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="dr_d">
						<tr>
							<td width="50%" height="30" class="info_title_01" colspan="2" nowrap><!--保险基数-->
					<ait:message  messageID="display.pay.view.monthly.insurance_base" module="pay"/></td>
							<td width="50%" class="info_content_01">${paMap.PENSION_INSURANCE_BASE + 0}</td>
						</tr>
						<tr>
							<td width="17%" rowspan="7" class="info_title_00" nowrap><!--公 司 社 保-->
					<ait:message  messageID="display.pay.view.monthly.social_deduction" module="pay"/></td>
							<td width="50%" height="30" class="info_title_01" nowrap><!--公司社保合计-->
					<ait:message  messageID="display.pay.view.monthly.social_employer" module="pay"/></td>
							<td width="33%" class="info_content_01">${paMap.TOTAL_INSURANCE_COMPANIES + 0}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--养 老-->
					<ait:message  messageID="display.mutual.retiree" /></td>
							<td class="info_content_01">${paMap.COMPANY_PENSION + 0}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--医 疗-->
					<ait:message  messageID="display.mutual.medicare" /></td>
							<td class="info_content_01">${paMap.COMPANY_MEDICAL + 0}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--失 业-->
					<ait:message  messageID="display.mutual.unemployment" /></td>
							<td class="info_content_01">${paMap.COMPANY_UNEMPLOYED + 0}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--工 伤-->
					<ait:message  messageID="display.mutual.work_injury" /></td>
							<td class="info_content_01">${paMap.COMPANY_INJURY + 0}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--生 育-->
					<ait:message  messageID="display.mutual.chilbirth" /></td>
							<td class="info_content_01">${paMap.COMPANY_GROWTH + 0}</td>
						</tr>
						<tr>
								<td height="30" class="info_title_01" nowrap><!--公积金-->
					<ait:message  messageID="display.mutual.fund" /></td>
								<td class="info_content_01">${paMap.COMPANY_HPFS + 0}</td>
						</tr>
					</table>
					</td>
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
		<td bgcolor="#D0D0FF" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:1;"></div>
</body>
</html>



