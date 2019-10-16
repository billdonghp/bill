<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>临时卡确认报表</title>
</head>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=TempCardReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<body>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="13"><b><font size="+2">临时卡确认报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				申请类型</td>
			  <td nowrap="nowrap">
				申请部门</td>
			  <td nowrap="nowrap">
				申请人</td>
		      <td nowrap="nowrap">
				使用者姓名</td>
			  <td nowrap="nowrap" >
				使用者业务</td>
			  <td nowrap="nowrap">
				开始日期</td>
		      <td nowrap="nowrap">
				结束日期</td>
			  <td nowrap="nowrap">
				申请权限</td>	
		      <td nowrap="nowrap">
				申请日期 </td>
			  <td nowrap="nowrap">
				卡号 </td>	
			  <td nowrap="nowrap">
				是否返还 </td>
			  <td nowrap="nowrap">
				发卡人 </td>	
			  <td nowrap="nowrap">
				归还日期 </td>	
		    </tr>			
		 <c:forEach items="${tempCardList}" var="varTest" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${varTest.EMPLOYEE_TYPE}</td>
		      <td nowrap="nowrap"  align="center">${varTest.APPLYER_DEPT}</td>
		      <td nowrap="nowrap" align="center">${varTest.APPLYER}</td>
		      <td nowrap="nowrap"  align="center">${varTest.USER_NAME}</td>
		      <td nowrap="nowrap"  align="center">${varTest.USER_BUSINESS}</td>
		      <td nowrap="nowrap" align="center">${varTest.START_DATE}</td>
		      <td nowrap="nowrap" align="center">${varTest.END_DATE}</td>
		      <td nowrap="nowrap"  align="center">${varTest.permissionsTypeName}</td>
		      <td nowrap="nowrap"  align="center">${varTest.APPLY_DATE}</td>
		      <td nowrap="nowrap" align="center">${varTest.CARD_NUMBER} </td>
		      <td nowrap="nowrap" align="center">
		      <c:if test="${varTest.RETURN_YN==1}">已归还</c:if>
		      <c:if test="${varTest.RETURN_YN==0}">未归还</c:if></td>
		      <td nowrap="nowrap" align="center">${varTest.CARD_PERSON} </td>
		      <td nowrap="nowrap" align="center">${varTest.RETURN_DATE} </td>
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>