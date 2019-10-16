<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
response.setHeader("Content-Disposition", "attachment; filename=ExpiredContract.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");%>

<body>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
		  <tr bgcolor="#F5F5F5">
		  		<td width="10%" height="26" align="center"><strong>
		  		<!--工号--><ait:message  messageID="display.mutual.emp_id"/></strong></td>
				<td width="10%" height="26" align="center"><strong>
				<!-- 姓名 --> <ait:message  messageID="display.mutual.name"/></strong></td>
				<td width="*" align="center"><strong>
				<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></strong></td>
				<td width="8%" align="center"><strong>
				<!-- 性别 --><ait:message  messageID="display.mutual.sex"/></strong></td>
				<td width="8%" align="center"><strong>
				<!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></strong></td>
				<td width="8%" align="center"><strong>
				<!-- 职位 --><ait:message  messageID="display.mutual.position"/></strong></td>  
				<!--<td width="8%" align="center"><strong>年龄</strong></td>-->
				<td width="10%" align="center"><strong>
				<!-- 学历--><ait:message  messageID="display.emp.staff_info.basic_info.diploma" module="hrm"/></strong></td>
				<td width="10%" align="center"><strong>
				<!--入社日期--><ait:message  messageID="display.emp.staff_info.basic_info.hire_date" module="hrm"/></strong></td>
				<td width="10%" align="center"><strong>
				<!--合同类型--><ait:message  messageID="display.mutual.contract_type"/></strong></td>
				<!--<td width="13%" align="center"><strong>身份证号</strong></td>-->
				<td width="13%" align="center"><strong>
				<!--合同到期日--><ait:message  messageID="display.emp.contract_info.contract_query.end_time_of_contract" module="hrm"/></strong></td>
				
	      </tr>
	<c:forEach items="${makeExpiredContractExcel}" var="MECExcel" varStatus="s">
		  <tr>
		  		<td height="23">${MECExcel.EMPID}&nbsp;</td>  
			    <td height="23">
	          		<ait:content enContent='${MECExcel.PINYIN}' zhContent='${MECExcel.CHINESENAME}' koContent='${MECExcel.KORNAME}'/>
         		&nbsp;</td>
			    <td>
	          		<ait:content enContent='${MECExcel.ENDEPT}' zhContent='${MECExcel.DEPTNAME}' koContent='${MECExcel.KORDEPT}'/>
         		&nbsp;</td>  
			    <td align="center">${MECExcel.SEX}
	          		<ait:content enContent='${MECExcel.ENSEX}' zhContent='${MECExcel.SEX}' koContent='${MECExcel.KORSEX}'/>
         		&nbsp;</td>
			    <td>
	          		<ait:content enContent='${MECExcel.ENPOST}' zhContent='${MECExcel.POST}' koContent='${MECExcel.KORPOST}'/>
         		&nbsp;</td>
			    <td align="center">
	          		<ait:content enContent='${MECExcel.ENPOSITION}' zhContent='${MECExcel.POSITION}' koContent='${MECExcel.KORPOSITION}'/>
         		&nbsp;</td>
<!--			    <td align="center">${MECExcel.AGE}&nbsp;</td>-->
			    <td>${MECExcel.BEFOREDEGREE}
	          		<ait:content enContent='${MECExcel.ENBEFOREDEGREE}' zhContent='${MECExcel.BEFOREDEGREE}' koContent='${MECExcel.KORBEFOREDEGREE}'/>
         		&nbsp;</td>
			    <td>${MECExcel.JOINDATE}&nbsp;</td>
			    <td>
	          		<ait:content enContent='${MECExcel.ENCONTRACTTYPE}' zhContent='${MECExcel.CONTRACTTYPE}' koContent='${MECExcel.KORCONTRACTTYPE}'/>
         		&nbsp;</td>  
<!--			    <td>${MECExcel.IDCARDNO}&nbsp;</td>-->
			    <td>${MECExcel.CONTRACTENDDATE}&nbsp;</td>
		  </tr>
	</c:forEach>
</table>
</body>
