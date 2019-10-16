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
<title>部门别人员查看</title>
</head>
<body>

<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0" style="position: relative; top: expression(this.offsetParent.scrollTop);">
<form action="hrmControlServlet" method="post" name="direction" >
<jodd:form fromRequest="true">
	<input type="hidden" name="operation" value="searchEmpByDept" >
	<input type="hidden" name="nextOperation" value="${nextOperation}" >
	<input type="hidden" name="menu_code" value="${menu_code}" >
		<tr>
			<td valign="middle" class="info_content_01">
				<ait:selDept dataListName="dept_list" name="DEPTID" onChange="document.direction.submit();" all="all" includeOverdue="Y"/>
				
				<ait:message  messageID="display.mutual.emp_no_name"/>：
				<input type="text" name="CONDITION" size="12"/>
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="document.direction.submit();" style="cursor:hand" />
			</td>
		</tr>
		<tr>
    		<td height="1" bgcolor="#CCCCCC"></td>
  		</tr>
</jodd:form>	
</form>
</table>
<p>
<table width="98%" border="0" cellspacing="0" cellspacing="０">
		<tr align="left">
			<td　class="info_content_01"><ait:message  messageID="display.mutual.available_employee"/>${empSize}</td>
		</tr>
</table>
<p>
<table width="98%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	<tr align="center">
		<td width="16%" height="25" class="info_title_01"><ait:message  messageID="display.mutual.name"/></td>
		<td width="16%" class="info_title_01"><ait:message  messageID="display.mutual.emp_id"/></td>
		<td width="26%" class="info_title_01"><ait:message  messageID="display.mutual.pin_yin"/></td>
		<td width="40%" class="info_title_01"><ait:message  messageID="display.mutual.dept"/></td>
	</tr>
		<c:forEach items="${emp_list}" var="employee" varStatus="j">
		<tr bgColor='#ffffff'align="center" onclick="parentReload('${employee.empID}');" style="cursor:hand"  onmouseover="this.bgColor='#F8F8FF';" onmouseout="this.bgColor='#ffffff';">
			<td height="25" >
				<ait:content enContent="${employee.pinyinName}" zhContent="${employee.chineseName}" koContent="${employee.koreanName}"/>&nbsp;
			</td>
			<td >${employee.empID}&nbsp;</td>
			<td >${employee.pinyinName}&nbsp;</td>
			<td >
				<ait:content enContent="${employee.englishDept}" zhContent="${employee.deptFullName}" koContent="${employee.korDept}"/>&nbsp;
			</td>
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
