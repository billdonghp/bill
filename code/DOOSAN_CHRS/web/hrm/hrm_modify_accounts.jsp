<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LSFC&gt;人事信息&gt;帐户信息</title>
</head>

<SCRIPT type="text/javascript">

function Save(){
	
	document.form1.action="hrmControlServlet?menu_code=hr0111&operation=updateAccountsInfo&empID=<c:out value='${empID}'/>";
	document.form1.fireSubmit();
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
		<%@ include file="./inc/hrtoolbar_a.jsp"%></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		<%@ include file="../hrm/hrm_add_basic.jsp"%> 
		
		<!-- display content -->
		<table width="100%" border="0" cellpadding="5">
			<tr>
				<td tdwidth="50%" class="title1"><!-- 帐户 -->
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
						<td class="info_content_01" height="30" nowrap width="15%"><input
							name="calcFlag" type="checkbox" value="Y"
							<ait:checkbox isChecked="${paEmpInfo.calcFlag}" />></td>
						<td class="info_content_01" nowrap width="15%"><ait:codeClass
							name="bankAreaCode" codeClass="BANKAREA" all="All"
							selected="${paEmpInfo.bankAreaCode}" /></td>
						<td class="info_content_01" nowrap width="15%"><ait:codeClass
							name="bankNameCode" codeClass="BankNameCode" all="All"
							selected="${paEmpInfo.bankNameCode}" /></td>
						<td class="info_content_01" nowrap width="15%"><input type="text"
							class="content" name="cardName" id="cardName"
							value="${paEmpInfo.cardName}" /></td>
						<td class="info_content_01" nowrap nowrap width="30%"><input type="text"
							class="content" name="cardNo" id="cardNo"
							value="${paEmpInfo.cardNo}" /></td>
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
<ait:xjos />
</html>
