<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支票号报表</title>
</head>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=CheckAccountReport.xls");
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
				<td align="center" colspan="8"><b><font size="+2">支票号信息</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				银行名称</td>
			  <td nowrap="nowrap">
				账号</td>
			  <td nowrap="nowrap">
				支票类型</td>
		      <td nowrap="nowrap">
				支票号</td>
			  <td nowrap="nowrap" >
				创建者</td>
			  <td nowrap="nowrap" >
				录入日期</td>
			  <td nowrap="nowrap">
				备注</td>
		      <td nowrap="nowrap">
				状态</td>				
		    </tr>			
		 <c:forEach items="${checkAccountList}" var="test" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${test.BANKNAME}</td>
		      <td nowrap="nowrap"  align="center">${test.ACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.CHECK_TYPE}</td>
		      <td nowrap="nowrap"  align="center">${test.CHECK_ACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap"  align="center">${test.CREATE_BY}</td>
		      <td nowrap="nowrap" align="center">${test.ENTRY_DATE}</td>
		      <td nowrap="nowrap" align="center">${test.NOTE}</td>
		      <td nowrap="nowrap"  align="center">${test.ACTIVITY}</td>
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>