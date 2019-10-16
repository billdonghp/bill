<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<body>
<form name="workExp"  method="post" >
<table width="100%" border="0" cellpadding="0" >
	<tr  height="25">
		<td align="right">
		<ait:image src="/images/btn/Report.gif"  border="0" align="absmiddle"
          	onclick="showReportWorkExp()" style="cursor:hand" />
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			  <tr>
				<td width="21%" nowrap class="info_title_01" height="30">
				<!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				<td width="32%" class="info_content_01" nowrap>
					<input id="EmpId_" name="EmpId" size="8" onKeyUp="send(this.value,'','2')" /></td>
				<td width="15%" nowrap class="info_title_01">
				<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				<td width="32%" class="info_content_01" nowrap><div id="name"></div></td>
			  </tr>
			  <tr>
				<td nowrap class="info_title_01" height="30">
				<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				<td class="info_content_01" nowrap><div id="deptName"></div></td>
				<td nowrap class="info_title_01"><!--身份证号--><ait:message  messageID="display.mutual.id_no"/></td>
				<td class="info_content_01" nowrap><div id="idCardOrPassport"></div></td>
			  </tr>
			  <tr>
			    <td nowrap class="info_title_01" height="30">
			    <!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				<td class="info_content_01" nowrap><div id="position"></div></td>
			    <td nowrap class="info_title_01" height="30">
			    <!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				<td class="info_content_01" nowrap><div id="post"></div></td>
			  </tr>
			  <tr>
				<td nowrap class="info_title_01" height="30">
				<!--入社日期--><ait:message  messageID="display.emp.staff_info.basic_info.hire_date" module="hrm"/></td>
				<td class="info_content_01" nowrap><div id="joinDate"></div></td>
				<td nowrap class="info_title_01">
				<!--离职日期--><ait:message  messageID="display.mutual.leaving_date"/></td>
				<td class="info_content_01" nowrap><div id="dateLeft"></div></td>
			  </tr>
			  <tr>
				<td nowrap class="info_title_01" height="30"><!--证明类型-->
				<ait:message  messageID="display.emp.statistic.personnel_report.working_certificate.type_of_certificate" module="hrm"/></td>
				<td colspan="3" class="info_content_01" nowrap>
					<ait:codeClass codeClass="ProvePurposeCode" name="Prove_Pur" selected="${ProvePurposeCode}" /></td>
			  </tr>
			  <tr>
				<td class="info_content_01" colspan="4"><!--备注--><ait:message  messageID="display.mutual.notes"/></td>
		  	  </tr>
		     <tr>
			     <td colspan="4" class="info_content_01">
			     	<textarea name="Remark"  style=" width:100%; height:100px; " type="_moz">
			     		<ait:message  messageID="display.emp.statistic.personnel_report.input_here" module="hrm"/>
			     	</textarea>
			     </td>
		     </tr>
			</table>
		</td>
	</tr>
</table>
</form>	