<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LSFC&gt;合同管理&gt;合同报表</title>

</head>

<SCRIPT type="text/javascript">
<!--hidden
function ImportExcel(){
	document.reportContract.target="_blank";
	document.reportContract.submit();
 }

 //-->
</SCRIPT>

<body>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<c:import url="/inc/common_toolbar_report.jsp" /></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info --> 
		
		<!-- display 3 level menu -->
		<c:import url="inc/hrm_dl_toolbar.jsp" /> 
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<form name="reportContract" action="hrmControlServlet" method="post">
			<jodd:form fromRequest="true">
				<input type="hidden" name="operation" value="createContractReport">
				<input type="hidden" name="menu_code"
					value="${toolbarInfo.menu_code}" />
				<tr>
    				<td class="title1"><!-- 报表条件 -->
					<ait:message  messageID="display.emp.contract_info.contract_report.report_condition" module="hrm"/>
				</td>
  				</tr>
  				<tr>
					<td>
						<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td width="10%" class="info_title_01" nowrap><!-- 部门 -->
								<ait:message  messageID="display.mutual.department"/>
							</td>
			          		<td width="20%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
			          		<td width="10%" class="info_title_01" nowrap><!-- 开始日期 --><ait:message  messageID="display.mutual.start_date"/></td>
			          		<td width="25%" class="info_content_00"><input type="text" name="contractStartDate" class="content" style="width:80px " onClick="setday(this);" /></td>
							<td width="10%" class="info_title_01" nowrap><!-- 结束日期 --><ait:message  messageID="display.mutual.end_date"/></td>
			          		<td width="25%" class="info_content_00">
			          			<input type="text" name="contractEndDate" class="content" style="width:80px " onClick="setday(this);" />
			          		</td>
			          	</tr>
			          	<tr>
							<td width="10%" class="info_title_01" nowrap><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
			          		<td width="20%" class="info_content_00"><input name="EMPIDORCHINESENAME" type="text" style="width:90px "
								id="EMPIDORCHINESENAME" />
							</td>
							<td width="10%" class="info_title_01" nowrap><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>
			          		<td width="25%" class="info_content_00"><ait:codeClass codeClass="ContractTypeCode" name="ContractTypeCode" all="all" disabled="false" />
							</td>
							<td width="10%" class="info_title_01" nowrap></td>
			          		<td width="25%" class="info_content_00"></td>
						</tr>
						</table>
					</td>
				</tr>
			</jodd:form></form>
		</table>


		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="280">
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
