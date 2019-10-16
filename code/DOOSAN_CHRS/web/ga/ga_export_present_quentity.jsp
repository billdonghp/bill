<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition","attachment;filename=EXPORT_PRESENT_QUENTITY.xls");
%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=UTF-8">
</head>
<body>
	<table width="100%" style="border-collapse:collapse" border="1"
	height="60" cellpadding="5">
	<tr>
		
		<td width="10%" align="center">方案名称</td>
		<td width="10%" align="center">方案内容</td>
		<td width="10%" align="center">部门</td>
		<td width="10%" align="center">课组</td>
		<td width="10%" align="center">姓名</td>
		<td width="10%" align="center">签名</td>
	</tr>
	<c:forEach items="${recordList}" var="oneResult">
		<tr>
			
			<td>${oneResult.SCHEMENAME}&nbsp;</td>
			<td>
			<table>
				<c:forEach items="${recordDetailList}" var="result">
					<c:if test="${oneResult.SCHEME_NO eq result.SCHEME_NO}">
						<tr>
							<td width="10%">&nbsp;&nbsp;礼品名称:&nbsp;${result.PRESENT_NAME}</td>
							<td width="10%">&nbsp;&nbsp;品牌:&nbsp;${result.BRAND}</td>
							<td width="10%">&nbsp;&nbsp;规格:&nbsp;${result.SPECIFIC}</td>
							<td width="10%">&nbsp;&nbsp;单价:&nbsp;${result.UNIT_PRICE}&nbsp;(元)</td>
							<td width="10%">&nbsp;&nbsp;数量:&nbsp;${result.QUENTITY}(${result.UNIT})</td>
							<td width="10%">&nbsp;&nbsp;价格:&nbsp;${result.TOTAL_PRICE}&nbsp;(元)</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			</td>
			<td>${oneResult.FOURDEPTNAME}&nbsp;</td>
			<td>${oneResult.DPETNAME}&nbsp;</td>
			<td>${oneResult.CHINESENAME}&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>