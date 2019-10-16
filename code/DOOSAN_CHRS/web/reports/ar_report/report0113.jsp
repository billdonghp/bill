<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=report0113.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>

<body>
<table width="1024">
	<tr align="center">
		<td colspan="5"></td>
	</tr>
</table>
<table width="1024" border="1" align="center">
	<tr align="center">
		<td rowspan="1">序号</td>
		<td rowspan="1">部门</td>
		<td rowspan="1">课别</td>
		<td rowspan="1">班别</td>
		<td rowspan="1">职号</td>
		<td rowspan="1">姓名</td>
		<td rowspan="1">区分</td>
		<td rowspan="1">日期</td>
		<td rowspan="1">旷工</td>
		<td rowspan="1">事假</td>
		<td rowspan="1">未勤</td>
		<td rowspan="1">迟到</td>
		<td rowspan="1">早退</td>
		<td rowspan="1">积分</td>
		<td rowspan="1">其他</td>
		<td rowspan="1">时间</td>
	</tr>

	<c:forEach items="${recordList}" var="result" varStatus="i">
		<tr align="center">
			<td rowspan="1">${result.SEQ_NO}</td>
			<td rowspan="1">${result.DEPT_NAME}</td>
			<td rowspan="1">${result.OFFICE_NAME}</td>
			<td rowspan="1">${result.TEAM_NAME}</td>
			<td rowspan="1">${result.EMPID}</td>
			<td rowspan="1">${result.CHINESENAME}</td>
			<td rowspan="1">${result.JOIN_TYPE_NAME}</td>
			<td rowspan="1">${result.AR_DATE}</td>
			<td rowspan="1">${result.ABSENTEEISM_QUANTITY}</td>
			<td rowspan="1">${result.CASUAL_LEAVE_QUANTITY}</td>
			<td rowspan="1">${result.INSUFFICIENT_QUANTITY}</td>
			<td rowspan="1">${result.LATE_QUANTITY}</td>
			<td rowspan="1">${result.LEAVE_QUANTITY}</td>
			<td rowspan="1">${result.INTEGRAL}</td>
			<td rowspan="1">${result.ITEM_NAME}</td>
			<td rowspan="1">${result.OTHER_QUANTITY}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

