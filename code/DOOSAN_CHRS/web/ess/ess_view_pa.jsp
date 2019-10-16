<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:directive.page import="com.ait.sy.common.PaHelper" />
<jsp:directive.page import="com.ait.pa.PaReport" />
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap" />
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil" />
<jsp:directive.page import="com.ait.util.StringUtil" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />  
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
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
	if (lockFlag == null || lockFlag.intValue() == PaHelper.UNLOCK_FLAG) {
		PaReport paReport = new PaReport();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("PaMonth", PAMonth);
		parameterObject.setString("empID", StringUtil.checkNull(sMap
		.getString("empID")));
		paMap = (SimpleMap) paReport.RetrievePaInfo(parameterObject);
		request.setAttribute("paMap", paMap);

	} else {
		out.println("工资未开放!");
	}
%>

<div id='showsearch'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
<iframe id="emp_list" name="emp_list" width="370" height="200"
	frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0"></iframe>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>  
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_search.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
              
		<br>
		<form name="searchMonthPa" method="POST" action="">
		<input type="hidden" name="empID" value="<%=admin.getAdminID() %>"/>  
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
		
						<td width="22%" height="30" class="info_title_01"><!--工资发放月-->
					<ait:message  messageID="display.mutual.pay_month" /></td>
						<td width="22%" class="info_content_01"><ait:date
							yearName="YEAR" monthName="MONTH" yearSelected="${sMap.YEAR}"
							monthSelected="${sMap.MONTH}" yearPlus="10" /></td>
						<td width="17%" class="info_title_01"><!--工 号-->
					<ait:message  messageID="display.mutual.emp_id_2" /></td>
						<td width="17%" class="info_content_01"><c:out value='${sMap.EMPID}'/>	</td>
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
						<td width="11%" class="info_content_01">
		       		<ait:content enContent="${paMap.DEPT_EN_NAME}" zhContent="${paMap.DEPARTMENT}" koContent="${paMap.DEPT_KOR_NAME}"/>
		       	</td>
						<td width="11%" height="30" class="info_title_01"><!--职位-->
					<ait:message  messageID="display.mutual.position" /></td>
						<td width="11%" class="info_content_01">
		       		<ait:content enContent="${paMap.POSITION_EN_NAME}" zhContent="${paMap.POSITION}" koContent="${paMap.POSITION_KOR_NAME}"/>
		       	</td>
						<td width="11%" height="30" class="info_title_01"><!--职 务-->
					<ait:message  messageID="display.mutual.post"  /></td>
						<td width="11%" class="info_content_01">
		       		<ait:content enContent="${paMap.POST_EN_NAME}" zhContent="${paMap.POST}" koContent="${paMap.POST_KOR_NAME}"/>
		       	</td>
						<td width="17%" class="info_title_01"><!--入社日期-->
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
				<td width="11%" height="30" class="info_title_01"><!--应发工资-->
					<ait:message  messageID="display.mutual.salary_payable" /></td>
				<td width="11%" class="info_content_01">${paMap.SALARY_PAYABLE}</td>
				<td width="11%" height="30" class="info_title_01"><!--扣除总额-->
					<ait:message  messageID="display.pay.view.monthly.total_deductions" module="pay"/></td>
				<td width="11%" class="info_content_01">${paMap.PER_INS_TOTAL + paMap.INCOME_TAX - AFTER_TAXABLE_PLUS + AFTER_TAXABLE_DEDUCT}</td>
				<td width="11%" height="30" class="info_title_01"><!--实发工资-->
					<ait:message  messageID="display.mutual.net_pay" /></td>
				<td width="11%" class="info_content_01">${paMap.NET_PAY}</td>
				<td width="17%" height="30" class="info_title_01"><!--工资总额-->
					<ait:message  messageID="display.mutual.gross_pay" /></td>
				<td width="17%" class="info_content_01">${paMap.GROSS}</td>
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
								<td colspan="2" height="33" class="info_title_01" nowrap><!--标准工资-->
					<ait:message  messageID="display.mutual.standard" /></td>
								<td height="33" class="info_content_01">${paMap.STANDARD_WAGE}</td>
							</tr>
							<tr>
								<td width="16%" height="10" rowspan="4" class="info_title_00" nowrap><!--津 贴-->
					<ait:message  messageID="display.mutual.allowance" /></td>
								<td width="50%" height="30" class="info_title_01" nowrap><!--职务津贴-->
					<ait:message  messageID="display.mutual.position_allowance" /></td>
								<td height="30" class="info_content_01">${paMap.POST_ALLOWANCE}</td>
							</tr>
							<tr>
					           <td height="30" class="info_title_01"><!--资格津贴-->
					<ait:message  messageID="display.mutual.qualification_allowance" /></td>
					           <td class="info_content_01">${paMap.QUALITY_ALLOWANCE}</td>
					         </tr>
					         <tr>
					           <td height="30" class="info_title_01"><!--特殊津贴-->
					<ait:message  messageID="display.mutual.special_allowance" /></td>
					           <td class="info_content_01">${paMap.SPECIAL_ALLOWANCE}</td>
					         </tr>
					         <tr>
					           <td height="30" class="info_title_01"><!--补助津贴-->
					<ait:message  messageID="display.mutual.allowance_subsidy" /></td>
					           <td class="info_content_01">${paMap.SUBSIDY_ALLOWANCE}</td>
					         </tr>
							<tr>
								<td height="30" class="info_title_01" colspan="2" nowrap><!--浮动工资-->
					<ait:message  messageID="display.mutual.merit_pay" /></td>
								<td height="30" class="info_content_01">${paMap.VARIABLE_PAY}</td>
							</tr>
							<tr>
								<td width="16%" height="10" rowspan="4" class="info_title_00" nowrap><!--加 减 项-->
					<ait:message  messageID="display.pay.view.monthly.variance" module="pay"/></td>
								<td width="50%" height="30" class="info_title_01" nowrap><!--税前加项-->
					<ait:message  messageID="display.pay.view.monthly.pre_additions" module="pay"/></td>
								<td height="30" class="info_content_01">${paMap.BEFORE_TAXABLE_PLUS}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01"><!--税前减项-->
					<ait:message  messageID="display.pay.view.monthly.pre_deductions" module="pay"/></td>
								<td height="30" class="info_content_01">${paMap.BEFORE_TAXABLE_DEDUCT}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01"><!--税后加项-->
					<ait:message  messageID="display.pay.view.monthly.post_additions" module="pay"/></td>
								<td height="30" class="info_content_01">${paMap.AFTER_TAXABLE_PLUS}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01"><!--税后减项-->
					<ait:message  messageID="display.pay.view.monthly.post_deductions" module="pay"/></td>
								<td height="30" class="info_content_01">${paMap.AFTER_TAXABLE_DEDUCT}</td>
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
										<td width="16%" height="10" rowspan="9" class="info_title_00" nowrap><!--考 勤 扣 款-->
					<ait:message  messageID="display.pay.view.monthly.deduction" module="pay"/></td>
										<td height="30" class="info_title_01" nowrap><!--考勤扣款合计-->
					<ait:message  messageID="display.pay.view.monthly.total_deductions" module="pay"/></td>
										<td width="58%" class="info_content_01">${paMap.ABSENT_DEDUCT_TOTAL}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--病假扣款-->
					<ait:message  messageID="display.mutual.sick_leave" /></td>
										<td class="info_content_01">${paMap.SICK_LEAVE_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--事假扣款-->
					<ait:message  messageID="display.mutual.casual_leave" /></td>
										<td class="info_content_01">${paMap.CASUAL_LEAVE_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--特殊事假扣款-->
					<ait:message  messageID="display.mutual.compassionate_leave" /></td>
										<td width="58%" class="info_content_01">${paMap.COMPASSIONATE_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--旷工扣款-->
					<ait:message  messageID="display.mutual.absenteeism" /></td>
										<td class="info_content_01">${paMap.ABSENTEEISM_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--换算旷工扣款-->
					<ait:message  messageID="display.mutual.special_absenteeism" /></td>
										<td class="info_content_01">${paMap.CONVERT_ABSENT_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--休职扣款-->
					<ait:message  messageID="display.mutual.unpaid_leave" /></td>
										<td class="info_content_01">${paMap.UNPAID_LEAVE_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--漏卡扣款-->
					<ait:message  messageID="display.mutual.miss_punch" /></td>
										<td class="info_content_01">${paMap.CARD_MISS_DEDUCT}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--迟到早退扣款-->
					<ait:message  messageID="display.mutual.tardiness" /></td>
										<td class="info_content_01">${paMap.TARDINESS_TRUANCY_DEDUCT}</td>
									</tr>
									<tr>
									<td width="16%" height="10" rowspan="3" class="info_title_00" nowrap><!--考 勤 津 贴-->
					<ait:message  messageID="display.pay.view.monthly.allowance" module="pay"/></td>
										<td height="30" class="info_title_01" nowrap><!--考勤津贴合计-->
					<ait:message  messageID="display.pay.view.monthly.total_allowance" module="pay"/></td>
										<td width="58%" class="info_content_01">${paMap.DUTY_ALLOWANCE + paMap.BIZ_TRIP_ALLOWANCE}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--值班津贴-->
					<ait:message  messageID="display.pay.view.monthly.night_duty" module="pay"/></td>
										<td width="34%" height="30" class="info_content_01">${paMap.DUTY_ALLOWANCE}</td>
									</tr>
									<tr>
										<td height="30" class="info_title_01" nowrap><!--出差补助-->
					<ait:message  messageID="display.pay.view.monthly.trip" module="pay"/></td>
										<td width="34%" height="30" class="info_content_01">${paMap.BIZ_TRIP_ALLOWANCE}</td>
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
								<td width="51%" colspan="2" class="info_content_01">${paMap.INCOME_TAX}</td>
							</tr>
							<tr>
								<td width="16%" height="10" rowspan="4" class="info_title_00" nowrap><!--个 人 社 保-->
					<ait:message  messageID="display.pay.view.monthly.social_deductions" module="pay"/></td>
								<td height="30" class="info_title_01" nowrap><!--个人社保合计-->
					<ait:message  messageID="display.pay.view.monthly.total_social" module="pay"/></td>
								<td class="info_content_01">${paMap.PER_INS_TOTAL}</td>
							</tr>
							<tr>      
								<td height="30" class="info_title_01" nowrap><!--养 老-->
					<ait:message  messageID="display.mutual.retiree" /></td>
								<td class="info_content_01">${paMap.PER_RETIRE_INS}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--医 疗-->
					<ait:message  messageID="display.mutual.medicare" /></td>
								<td class="info_content_01">${paMap.PER_MEDICARE_INS}</td>
							</tr>
							<tr>
								<td height="30" class="info_title_01" nowrap><!--失 业-->
					<ait:message  messageID="display.mutual.unemployment" /></td>
								<td class="info_content_01">${paMap.PER_UNEMPLOY_INS}</td>
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
											<td class="info_content_01">${paMap.OT_PAY_TOTAIL}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--平 日-->
					<ait:message  messageID="display.pay.view.monthly.weekday" module="pay"/></td>
											<td width="58%"  class="info_content_01" >${paMap.WEEKDAY_OT_PAY}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--公休日-->
					<ait:message  messageID="display.pay.view.monthly.weekend" module="pay"/></td>
											<td width="58%" class="info_content_01" >${paMap.WEEKEND_OT_PAY}</td>
										</tr>
										<tr>
											<td height="30" class="info_title_01"
												nowrap><!--节假日-->
					<ait:message  messageID="display.pay.view.monthly.holiday" module="pay"/></td>
											<td width="58%" class="info_content_01" >${paMap.HOLIDY_OT_PAY}</td>
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
							<td width="50%" class="info_content_01">${paMap.INSURANCE_BASE}</td>
						</tr>
						<tr>
							<td width="17%" rowspan="6" class="info_title_00" nowrap><!--公 司 社 保-->
					<ait:message  messageID="display.pay.view.monthly.social_deduction" module="pay"/></td>
							<td width="50%" height="30" class="info_title_01" nowrap><!--公司社保合计-->
					<ait:message  messageID="display.pay.view.monthly.social_employer" module="pay"/></td>
							<td width="33%" class="info_content_01">${paMap.CPY_INS_TOTAL}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--养 老-->
					<ait:message  messageID="display.mutual.retiree" /></td>
							<td class="info_content_01">${paMap.CPY_RETIRE_INS}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--医 疗-->
					<ait:message  messageID="display.mutual.medicare" /></td>
							<td class="info_content_01">${paMap.CPY_MEDICARE_INS}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--失 业-->
					<ait:message  messageID="display.mutual.unemployment" /></td>
							<td class="info_content_01">${paMap.CPY_UNEMPLOY_INS}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--工 伤-->
					<ait:message  messageID="display.mutual.work_injury" /></td>
							<td class="info_content_01">${paMap.CPY_WORK_INJURY_INS}</td>
						</tr>
						<tr>
							<td height="30" class="info_title_01" nowrap><!--生 育-->
					<ait:message  messageID="display.mutual.chilbirth" /></td>
							<td class="info_content_01">${paMap.CPY_CHILDBIRTH_INS}</td>
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
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>



