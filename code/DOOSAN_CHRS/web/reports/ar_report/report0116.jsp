<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition", "attachment; filename=report0116.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="1">
	<tr>
		<td align="center">区间 : ${year}年-${month}月</td>
	</tr>
	<c:forEach items="${basicList}" var="basic">		
	<tr>
		<td>
		<table border="1" bcellpadding="0" cellspacing="1">
			<tr>
				<td nowrap="nowrap">区分(${basic.CHINESENAME})</td>
				<c:forEach items="${basic.overTimeList}" var="oneResult">
					<c:if test="${oneResult.EMPID == basic.PERSONID }">
							<td align="center" nowrap="nowrap">${oneResult.DATE_DAY}</td>
					</c:if>
				</c:forEach>
				<c:if test="${basic.overTimeList eq null}">
					<c:forEach begin="1" end="31" step="1" var="i">
						<td align="center" nowrap="nowrap">${i}</td>
					</c:forEach>
				</c:if>
			</tr>

			<tr>
				<td>工作状态</td>
				<c:forEach items="${basic.overTimeList}" var="oneResult">
					<c:forEach items="${basic.detailList}" var="twoResult">
						<c:if test="${oneResult.DATE_DAY == twoResult.date_day }">
							<td>
								<ait:content enContent="${twoResult.shiftName}" zhContent="${twoResult.shiftName}"></ait:content>
							</td>
						</c:if>
					</c:forEach>
				</c:forEach>
				<c:if test="${basic.overTimeList eq null}">
					<c:forEach begin="1" end="31" step="1" var="i">
						<td>&nbsp;</td>
					</c:forEach>
				</c:if>
			</tr>
			
			<tr>
				<td nowrap="nowrap">缺勤情况</td>
				<c:forEach items="${basic.overTimeList}" var="oneResult">
					<c:forEach items="${basic.detailList}" var="twoResult">
						<c:if test="${oneResult.DATE_DAY == twoResult.date_day}">
							<td>${twoResult.itemName}</td>
						</c:if>
					</c:forEach>
				</c:forEach>
				<c:if test="${basic.overTimeList eq null}">
					<c:forEach begin="1" end="31" step="1" var="i">
						<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
				</c:if>
			</tr>
			
			<tr>
				<td nowrap="nowrap">异常情况</td>
				<c:forEach items="${basic.overTimeList}" var="oneResult">
					<c:forEach items="${basic.specialItemList}" var="twoResult">
						<c:if test="${oneResult.DATE_DAY == twoResult.date_day}">
							<td>${twoResult.itemName}</td>
						</c:if>
					</c:forEach>
				</c:forEach>
				<c:if test="${basic.overTimeList eq null}">
					<c:forEach begin="1" end="31" step="1" var="i">
						<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
				</c:if>
			</tr>
			
			<tr>
				<td nowrap="nowrap">加班</td>
				<c:forEach items="${basic.overTimeList}" var="oneResult">
					<td align="center">${oneResult.OVERTIME }</td>
				</c:forEach>
				<c:if test="${basic.overTimeList eq null}">
					<c:forEach begin="1" end="31" step="1" var="i">
						<td nowrap="nowrap">&nbsp;</td>
					</c:forEach>
				</c:if>
			</tr>
		</table>
		</td>
	</tr>

			
	</c:forEach>
</table>

</body>
</html>
