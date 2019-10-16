<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<SCRIPT type="text/javascript">
function Search(){
document.direction.submit();
}
</SCRIPT>
<%@ include file="../inc/meta.jsp" %>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title></title>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_4.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<form action="hrmControlServlet" method="post" name="direction" >
		<jodd:form fromRequest="true">
			<input type="hidden" name="operation" value="destinationCmd">
			<input type="hidden" name="destination" value="pa_personal">
			<input type="hidden" name="menu_code" value="${menu_code}" >
			<input type="hidden" name="pamonth" value="${pamonth}" >
			<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
			</tr>
		    <tr>
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		       	<tr>
					<td width="15%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
					<td width="35%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
					<td width="15%" class="info_title_01"><!--姓名/工号-->
					<ait:message  messageID="display.mutual.emp_no_name"/></td>
					<td width="35%" class="info_content_00">
						<input type="text" name="CONDITION" size="12"/>
					</td>
			    </tr>
				</table>
		      </td>
			</tr>
			</table>
		</jodd:form>	
		</form>
		
		<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
			<tr align="center">
				<td width="20%" height="30" class="info_title_01"><!--中文姓名-->
					<ait:message  messageID="display.pay.maintenance.setting.chinese" module="pay"/></td>
				<td width="19%" class="info_title_01"><!--工号-->
					<ait:message  messageID="display.mutual.emp_id"/></td>
		
				<td width="21%" class="info_title_01"><!--中文拼音-->
					<ait:message  messageID="display.pay.maintenance.setting.pinyin" module="pay"/></td>
				<td width="40%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
			</tr>
			
				<c:forEach items="${emp_list}" var="employee" varStatus="j">
				<tr align="center">
					<td height="30"  class="info_content_01">
					<a target="_blank" href='/pa/pa_personal_v.jsp?operation=hr_view&personId=${employee.personId}&info=basic&menu_code=${menu_code}&pamonth=${pamonth}&deptid=${employee.deptID}'>
					<font color="red">${employee.chineseName}</font>
					</a>&nbsp;</td>
					<td class="info_content_01">${employee.empID}&nbsp;</td>
					<td class="info_content_01">${employee.pinyinName}&nbsp;</td>
					<td class="info_content_01">
					<ait:content enContent="${employee.englishDept}" zhContent="${employee.department}" koContent="${employee.korDept}"/>
					&nbsp;</td>
				</tr>
				</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>


</html>
