<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="/inc/meta.jsp" %>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>  
<title>人员历史记录查看</title>   
</head>
<body>
<table width="98%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	<tr align="center">
		  <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.name"></ait:message></td>
		  <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.emp_id"></ait:message></td>
		  <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.dept"></ait:message></td>
		  <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.post_group"></ait:message></td>
          <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.position"></ait:message></td>
          <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.post"></ait:message></td>
          <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.post_grade"></ait:message></td>
          <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.pay_grade"></ait:message></td>
          <td class="info_title_01" nowrap="nowrap"><ait:message messageID="display.mutual.staff_status"></ait:message></td>
	</tr>
		<c:forEach items="${listdata}" var="oneResult" >             
		<tr>  
			<td class="info_content_01" nowrap><ait:content enContent="${oneResult.ENGLISHNAME}" 
			zhContent="${oneResult.CHINESENAME}" 
			koContent="${oneResult.ENGLISHNAME}"></ait:content>  
			&nbsp;</td>
			<td class="info_content_01" nowrap>  
			${oneResult.EMPID}&nbsp;</td>   
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.DEPTENNAME}" 
			zhContent="${oneResult.DEPARTMENT}" 
			koContent="${oneResult.DEPTKORNAME}"></ait:content>  
			&nbsp;</td>
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.POSTGROUPENNAME}" 
			zhContent="${oneResult.POSTGROUPNAME}" 
			koContent="${oneResult.POSTGROUPKORNAME}"></ait:content>  
			&nbsp;</td>
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.POSITIONENNAME}" 
			zhContent="${oneResult.POSITIONNAME}" 
			koContent="${oneResult.POSITIONKORNAME}"></ait:content>  
			&nbsp;</td>             
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.POSTENNAME}" 
			zhContent="${oneResult.POSTNAME}" 
			koContent="${oneResult.POSTKORNAME}"></ait:content>  
			&nbsp;</td>
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.POSTGRADEENNAME}" 
			zhContent="${oneResult.POSTGRADENAME}" 
			koContent="${oneResult.POSTGRADEKORNAME}"></ait:content>  
			&nbsp;</td>
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.POSTGRADELEVELENNAME}" 
			zhContent="${oneResult.POSTGRADELEVELNAME}" 
			koContent="${oneResult.POSTGRADELEVELKORNAME}"></ait:content>  
			&nbsp;</td>     
			<td class="info_content_01" nowrap>
			<ait:content enContent="${oneResult.ENSTATUS}" 
			zhContent="${oneResult.STATUS}" 
			koContent="${oneResult.KORSTATUS}"></ait:content>  
			&nbsp;</td>
		</tr>
		</c:forEach>  
</table>
</html>
