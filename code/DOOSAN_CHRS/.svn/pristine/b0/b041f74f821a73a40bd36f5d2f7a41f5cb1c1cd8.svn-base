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
<title>年度、部门 、平均分数</title>
</head>
<body>

<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0" style="position: relative; top: expression(this.offsetParent.scrollTop);">
<form action="hrmControlServlet" method="post" name="direction" >
<jodd:form fromRequest="true">
	<input type="hidden" name="operation" value="report0539001" >
	<input type="hidden" name="nextOperation" value="${nextOperation}" >
	<input type="hidden" name="menu_code" value="${menu_code}" >
</jodd:form>	
</form>
</table>
<p>
<table width="98%" border="0" cellspacing="0" cellspacing="０">
		<tr align="left">
			<td　class="info_content_01">年度、部门 、综合分数</td>
		</tr>
		<br>
</table>

<p>
<table width="98%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	<tr align="center">
		<td width="10%" height="25" class="info_title_01">评价年</td> 
		<td width="10%" class="info_title_01">部门</td> 
	<!-- 
		<td width="10%" class="info_title_01">熟练度</td>
		<td width="10%" class="info_title_01">设备保全</td>
		<td width="10%" class="info_title_01">作业基准书遵守</td>
		<td width="10%" height="25" class="info_title_01">作业品质</td>
		<td width="10%" class="info_title_01">标准动作</td>
		 -->
		<td width="10%" class="info_title_01">平均得分</td> 
	</tr>
		<c:forEach items="${recordList}" var="employee" varStatus="j">
		<tr bgColor='#ffffff'align="center" style="cursor:hand"  onmouseover="this.bgColor='#F8F8FF';" onmouseout="this.bgColor='#ffffff';">
			<td >${employee.EV_YEAR}&nbsp;</td> 
			<td >${employee.EV_DEPT_NAME}&nbsp;</td> 
			<!-- 
			<td >${employee.PROFIC}&nbsp;</td>
			<td >${employee.SHEPQ}&nbsp;</td>
			<td >${employee.OPERA}&nbsp;</td>
			<td >${employee.QUALIT}&nbsp;</td>
			<td >${employee.STAND}&nbsp;</td>
			 -->
			<td >${employee.COMPOS}&nbsp;</td> 
			<td ></td>
		</tr>
		</c:forEach>
</table>
<SCRIPT type="text/javascript">
function parentReload(empID){
	opener.location.href="/hrmControlServlet?operation=${nextOperation}&menu_code=${menu_code}&empID="+empID;
	window.close();
}
</SCRIPT>
</html>
