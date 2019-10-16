<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;帐户信息</title>
</head>
<SCRIPT type="text/javascript">

function Add(){
	
	if(${emptyFlag} == 1){
	
		document.form1.action="hrmControlServlet?menu_code=hr0111&operation=retrieveDataForInsertAccountsInfo&empID=<c:out value='${empID}'/>";
		document.form1.submit();
	} else {
	
		alert('<ait:message  messageID="alert.emp.staff_info.account_info.sorry_only_one_account_number_is_allowed" module="hrm"/>');
		
		
						
				
	}
 }
 
function Update(){

	if(${emptyFlag} == 1){
		
		alert('<ait:message  messageID="alert.emp.staff_info.account_info.please_add_account_message_first" module="hrm"/>');
	} else {
	
		document.form1.action="hrmControlServlet?menu_code=hr0111&operation=retrieveDataForUpdateAccountsInfo&empID=<c:out value='${empID}'/>";
		document.form1.submit();
	}

}

</SCRIPT>

<body>
<form name="form1" method="post">




<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<%@ include file="./inc/hrtoolbar.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_view_basic.jsp"%> 
		
		<!-- display 3 level menu -->
		<%@ include file="./inc/hrm_view_toolbar.jsp"%>
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="5">
			<tr>
				<td class="title1"><!-- 帐户 -->
					<ait:message  messageID="display.emp.staff_info.account" module="hrm"/>	
				</td>
			</tr>
			<tr height="135">
				<td>
				<div style="overflow:auto; width:100%; height:100%;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr
						style="position: relative; top: expression(this.offsetParent.scrollTop);">
						<td class="info_title_01" height="30" nowrap><!-- 工资计算标志 -->
							<ait:message  messageID="display.emp.staff_info.account.cal_symbol" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 城市 -->
							<ait:message  messageID="display.emp.staff_info.account.city" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 开户行 -->
							<ait:message  messageID="display.emp.staff_info.account.ac_with_bank" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 帐户名 -->
							<ait:message  messageID="display.emp.staff_info.account.beneficiary" module="hrm"/>	
						</td>
						<td class="info_title_01" nowrap><!-- 帐号 -->
							<ait:message  messageID="display.emp.staff_info.account.ac_no" module="hrm"/>	
						</td>
					</tr>
					<tr>
						<td class="info_content_01" height="30" nowrap width="15%">
						<ait:content enContent="${paEmpInfo.enCalcFlagName}" zhContent="${paEmpInfo.calcFlagName}" />
						</td>
						<td class="info_content_01" nowrap width="15%">
							<ait:content enContent="${paEmpInfo.enBankAreaName}" zhContent="${paEmpInfo.bankAreaName}" koContent="${paEmpInfo.korBankAreaName}"/>
						</td>
						<td class="info_content_01" nowrap width="15%">
							<ait:content enContent="${paEmpInfo.enBankName}" zhContent="${paEmpInfo.bankName}" koContent="${paEmpInfo.korBankName}"/>
						</td>
						<td class="info_content_01" nowrap width="15%">${paEmpInfo.cardName}</td>
						<td class="info_content_01" nowrap width="30%">${paEmpInfo.cardNo}</td>
				</table>
				</div>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</body>

</html>
