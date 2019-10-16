<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支票确认报表</title>
</head>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=CheckReport.xls");
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
				<td align="center" colspan="13"><b><font size="+2">支票确认报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap">
				申请人</td>
			  <td nowrap="nowrap">
				申请部门</td>
			  <td nowrap="nowrap">
				申请日期</td>
		      <td nowrap="nowrap">
				金额</td>
			  <td nowrap="nowrap" >
				银行名称</td>
			  <td nowrap="nowrap">
				账号</td>
		      <td nowrap="nowrap">
				支票类型</td>
			  <td nowrap="nowrap">
				支票号</td>	
		      <td nowrap="nowrap">
				摘要</td>
			  <td nowrap="nowrap">
				收款人全称</td>
			  <td nowrap="nowrap">
				费用申请企案号</td>
			  <td nowrap="nowrap">
				费用凭证号</td>
			  <td nowrap="nowrap">
				费用凭证日期</td>				
		    </tr>			
		 <c:forEach items="${checkList}" var="test" varStatus="i">
		      <tr align="center">
		      <td nowrap="nowrap" align="center">${test.APPLYER}</td>
		      <td nowrap="nowrap"  align="center">${test.APPLYER_DEPT}</td>
		      <td nowrap="nowrap" align="center">${test.APPLY_DATE}</td>
		      <td nowrap="nowrap"  align="center">${test.JINE}</td>
		      <td nowrap="nowrap"  align="center">${test.BANKNAME}</td>
		      <td nowrap="nowrap" align="center">${test.ACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.CHECKTYPE}</td>
		      <td nowrap="nowrap"  align="center">${test.CHECKACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap"  align="center">${test.NOTE}</td>
		      <td nowrap="nowrap" align="center">${test.SKALLNAME} </td>
		      <td nowrap="nowrap" align="center">${test.QIANACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.PZACCOUNT}&nbsp;</td>
		      <td nowrap="nowrap" align="center">${test.PZDATE} </td>
			 </tr>
		    </c:forEach>
		 </table>
		</td>
	</tr>
</table>
</body>
</html>