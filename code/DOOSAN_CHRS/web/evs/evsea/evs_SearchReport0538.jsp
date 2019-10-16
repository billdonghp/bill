<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="/inc/meta.jsp" %>
<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<title>部门、个人技能水平</title>
</head>
<body>

<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0" style="position: relative; top: expression(this.offsetParent.scrollTop);">
<form action="hrmControlServlet" method="post" name="direction" >
<jodd:form fromRequest="true">
	<input type="hidden" name="operation" value="report0538001" >
	<input type="hidden" name="nextOperation" value="${nextOperation}" >
	<input type="hidden" name="menu_code" value="${menu_code}" >
</jodd:form>	
</form>
</table>
<p>
<table width="98%" border="0" cellspacing="0" cellspacing="0">
		<tr align="left">
			<td　class="info_content_01">部门、个人技能水平</td>
		</tr>
		<br>
</table>

</p>
<table width="98%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	<tr align="center">
		<td width="16%" height="25" class="info_title_01">评价年</td>
		<td width="16%" class="info_title_01">职号</td>
		<td width="26%" class="info_title_01">姓名</td>
		<td width="40%" class="info_title_01">部门</td>
		<td width="16%" height="25" class="info_title_01">评价类型</td>
		<td width="16%" class="info_title_01">当前状态</td>
		<td width="26%" class="info_title_01">工种名称</td>
		<td width="40%" class="info_title_01">技能类型</td>
		<td width="16%" height="25" class="info_title_01">技能Line</td>
		<!-- 
		<td width="16%" class="info_title_01">机种名称</td>
		 -->
		<td width="26%" class="info_title_01">工序名称</td>
		<!-- 
		<td width="40%" class="info_title_01">作业内容</td>
		 <td width="16%" height="25" class="info_title_01">作业类型</td>-->
		<td width="16%" class="info_title_01">职业资格</td>
		<td width="16%" class="info_title_01">技能积分</td>
		<td width="16%" class="info_title_01">技能等级</td>
		<!-- 
		<td width="26%" class="info_title_01">熟练度</td>
		<td width="26%" class="info_title_01">设备保全</td>
		<td width="40%" class="info_title_01">作业基准书遵守</td>
		<td width="16%" height="25" class="info_title_01">作业品质</td>
		<td width="16%" class="info_title_01">标准动作</td>
		 -->
		<td width="26%" class="info_title_01">综合得分</td>
		<td width="40%" class="info_title_01">主要问题点</td>
		<td width="16%" class="info_title_01">培训目标</td>		
	</tr>
		<c:forEach items="${recordList}" var="employee" varStatus="j">
		<tr bgColor='#ffffff' align="center" style="cursor:hand"  onmouseover="this.bgColor='#F8F8FF';" onmouseout="this.bgColor='#ffffff';">
			<td >${employee.EV_YEAR}&nbsp;</td>
			<td >${employee.EV_EMPID}&nbsp;</td>
			<td >${employee.EV_EMPNAME}&nbsp;</td>
			<td >${employee.EV_DEPT_NAME}&nbsp;</td>
			<td >${employee.EV_TYPE_ID}&nbsp;</td>
			<td >${employee.EV_PROCESS_NAME}&nbsp;</td>
			<td >${employee.CRAFT}&nbsp;</td>
			<td >${employee.SKILLTYPE}&nbsp;</td>
			<td >${employee.LINE}&nbsp;</td>
			<!-- 
			<td >${employee.AIRCRAFT}&nbsp;</td>
			 -->
			<td >${employee.PROCESS}&nbsp;</td>
		<!-- 	<td >${employee.JOBCONTENT}&nbsp;</td>
			 <td >${employee.TYPEOPERATION}&nbsp;</td>-->
			<td >${employee.QUALIFICATION}&nbsp;</td>
			<td >${employee.SKILLSCROE}&nbsp;</td>
			<!-- 
			<td >${employee.SKILLLEVEL}&nbsp;</td>
			<td >${employee.PROFICIENCY}&nbsp;</td>
			<td >${employee.SHEOPERCYQ}&nbsp;</td>
			<td >${employee.OPERATIONCOM}&nbsp;</td>
			<td >${employee.QUALITYOFWORK}&nbsp;</td>
			 -->
			<td >${employee.STANDARDACTION}&nbsp;</td>
			<td >${employee.COMPOSITE}&nbsp;</td>
			<td >${employee.REMARK}&nbsp;</td>
			<td >${employee.PURPOSE}&nbsp;</td>
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
</body>
</html>
