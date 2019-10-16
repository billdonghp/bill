<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=ExpressManagerReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
		<tr>
			<td align="center"  colspan="10"><font style="FONT-FAMILY:宋体;font-size:14pt;" colspan="10"><strong>住房公积金</strong></font></td>
		</tr>
	</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0"
		bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
		style="padding: 2px 2px 2px 2px;">
		<tr align="center">
			<td nowrap="nowrap" rowspan="2">职号</td>
			<td nowrap="nowrap" rowspan="2">姓名</td>
			<td nowrap="nowrap" rowspan="2">年月</td>
			<td nowrap="nowrap" rowspan="2">基数</td>
			<td nowrap="nowrap" colspan="3">工资存入</td>
			<td nowrap="nowrap" rowspan="2">其他存入</td>
			<td nowrap="nowrap" rowspan="2">支出</td>
			<td nowrap="nowrap" rowspan="2">余额</td>
		</tr>
		<tr align="center">
			<td nowrap="nowrap">个人</td>
			<td nowrap="nowrap">公司</td>
			<td nowrap="nowrap">合计</td>
		</tr>

		<c:forEach items="${listhouse}" var="list">
			<tr align="center">
				<td nowrap="nowrap" align="center">${list.EMPID }</td>
				<td nowrap="nowrap" align="center">${list.CHINESENAME }</td>
				<td nowrap="nowrap" align="center">${list.PA_MONTH }</td>
				<td nowrap="nowrap" align="center">${list.HPFS_BASE }</td>
				<td nowrap="nowrap" align="center">${list.GEREN }</td>
				<td nowrap="nowrap" align="center">${list.GONGSI }</td>
				<td nowrap="nowrap" align="center">${list.HEJI }</td>
				<td height="30" align="center"><c:if test="${list.OCCUR_TYPE eq '1' }">${list.OCCUR_AMOUNT }</c:if>
				</td>
				<td height="30" align="center"><c:if test="${list.OCCUR_TYPE eq '2' }">${list.OCCUR_AMOUNT }</c:if>
				</td>
				<td nowrap="nowrap" align="center">${list.ALLPRICE } &nbsp;</td>
			</tr>
		</c:forEach>
	</table>
</table>
</body>
</html>