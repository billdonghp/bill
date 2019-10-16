<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
response.setHeader("Content-Disposition", "attachment; filename=ContractReport.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");%>

<table width="100%" border="1" cellspacing="0" cellpadding="0">
		  <tr bgcolor="#F5F5F5">
		  		<td width="10%" height="26" align="center"><strong>
		  		<!--工号--><ait:message  messageID="display.mutual.emp_id"/></strong></td>
				<td width="10%" height="26" align="center"><strong>
				<!-- 姓名 --> <ait:message  messageID="display.mutual.name"/></strong></td>
				<td width="*" align="center"><strong>
				<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></strong></td>
<!--				<td width="8%" align="center"><strong>性别</strong></td>-->
				<td width="8%" align="center"><strong>
				<!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></strong></td>
				<td width="8%" align="center"><strong>
				<!-- 职位 --><ait:message  messageID="display.mutual.position"/></strong></td>
<!--				<td width="8%" align="center"><strong>年龄</strong></td>-->
<!--				<td width="10%" align="center"><strong>学历</strong></td>-->
				<td width="10%" align="center"><strong>
				<!--入社日期--><ait:message  messageID="display.emp.staff_info.basic_info.hire_date" module="hrm"/></strong></td>
<!--				<td width="13%" align="center"><strong>身份证号</strong></td>-->
	           <td width="13%" align="center"><strong>
				<!--合同类型--><ait:message  messageID="display.mutual.contract_type"/></strong></td>  
				<td width="13%" align="center"><strong>
				<!--合同开始日--><ait:message  messageID="display.emp.contract_info.contract_query.start_time_of_contract" module="hrm"/></strong></td>
				<td width="13%" align="center"><strong>
				<!--合同到期日--><ait:message  messageID="display.emp.contract_info.contract_query.end_time_of_contract" module="hrm"/></strong></td>
				<td width="13%" align="center"><strong>
				<!--合同时间长度/月--><ait:message  messageID="display.emp.contract.report.period" module="hrm"/></strong></td>
	      </tr>
	<c:forEach items="${searchContractForOutExcel}" var="SCFExcel" varStatus="s">
		  <tr>
		  		<td align="left" height="23">${SCFExcel.EMPID}</td>
			    <td align="left" height="23">
	          		<ait:content enContent='${SCFExcel.PINYIN}' zhContent='${SCFExcel.CHINESENAME}' koContent='${SCFExcel.KORNAME}'/>
         		</td>
			    <td align="left">
	          		<ait:content enContent='${SCFExcel.ENDEPT}' zhContent='${SCFExcel.DEPTNAME}' koContent='${SCFExcel.KORDEPT}'/>
         		</td>  
<!--			    <td align="left">${SCFExcel.SEX}</td>-->
			    <td align="left">
	          		<ait:content enContent='${SCFExcel.ENPOST}' zhContent='${SCFExcel.POST}' koContent='${SCFExcel.KORPOST}'/>
         		&nbsp;</td>
			    <td align="left">
	          		<ait:content enContent='${SCFExcel.ENPOSITION}' zhContent='${SCFExcel.POSITION}' koContent='${SCFExcel.KORPOSITION}'/>
         		&nbsp;</td>
<!--			    <td align="left">${SCFExcel.AGE}</td>-->
<!--			    <td align="left">${SCFExcel.BEFOREDEGREE}</td>-->
			    <td align="left">${SCFExcel.JOINDATE}</td>
			    <td align="left">
	          		<ait:content enContent='${SCFExcel.ENCONTRACTTYPE}' zhContent='${SCFExcel.CONTRACTTYPE}' koContent='${SCFExcel.KORCONTRACTTYPE}'/>
         		&nbsp;</td>   
			    <td align="left">${SCFExcel.CONTRACTSTARTDATE}</td>  
			    <td align="left">${SCFExcel.CONTRACTENDDATE}</td>
			    <td align="left">${SCFExcel.CONTRACTYEARS}</td>   
		  </tr>
	</c:forEach>
</table>
