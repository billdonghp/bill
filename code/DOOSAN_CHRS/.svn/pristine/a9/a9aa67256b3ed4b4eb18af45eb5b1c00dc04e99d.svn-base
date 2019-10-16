<%@ page language="java"
	contentType="application/vnd.ms-excel; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
			response.setHeader("Content-disposition",
			"attachment;filename=EXPORT_FOOD_APPLY.xls");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="application/vnd.ms-excel; charset=UTF-8">
</head>
<body>
<table width="100%" style="border-collapse:collapse" border="1"
	height="60" cellpadding="5">
	<tr>
		<td width="3%">部门</td>
		<td width="3%">类行</td>
		<td width="3%">人数</td>
		<td width="3%">夜餐食品</td>
		<td width="3%">备注</td>
		<td width="3%">申请人</td>
		<td width="3%">申请日期</td>
		<c:forEach items="${recordList}" var="oneResult" varStatus="i">
			<tr align="center">
				<td align="center" height="30">${oneResult.DEPTNAME}</td>
				<td align="center">${oneResult.FOOD_SCHEME_NAME}</td>
				<td align="center">${oneResult.QUENTITY}&nbsp;</td>
				<td align="center">
				<table>
					<c:forEach items="${foodDetaiList}" var="result">
						<c:if test="${oneResult.FOOD_SCHEME_ID eq result.FOOD_SCHEME_ID}">
							<tr>
								<td width="10%">&nbsp;&nbsp;礼品名称:&nbsp;${result.FOOD_NAME}</td>
								<td width="10%">&nbsp;&nbsp;品牌:&nbsp;${result.BRAND}</td>
								<td width="10%">&nbsp;&nbsp;规格:&nbsp;${result.SPECIFIC}</td>
								<td width="10%">&nbsp;&nbsp;单价:&nbsp;${result.PRICE}&nbsp;(元)</td>
								<td width="10%">&nbsp;&nbsp;数量:&nbsp;${result.QUENTITY * oneResult.QUENTITY}(${result.UNIT})</td>
								<td width="10%">&nbsp;&nbsp;价格:&nbsp;${(result.QUENTITY * oneResult.QUENTITY)*result.PRICE}&nbsp;(元)</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				</td>
				<td align="center">${oneResult.REMARK}&nbsp;</td>
				<td align="center">${oneResult.APPLY_BY}&nbsp;</td>
				<td align="center">${oneResult.APPLY_DATE}&nbsp;</td>
			</tr>
		</c:forEach>
	</tr>
</table>
</body>
</html>
