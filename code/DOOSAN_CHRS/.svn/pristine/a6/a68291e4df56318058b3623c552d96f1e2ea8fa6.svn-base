<%@ page contentType="text/html; charset=UTF-8" import="com.ait.ar.bean.*,java.util.*,com.ait.util.*,com.ait.hrm.bean.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="emp_list" class="java.util.ArrayList" scope="request" /> <!-- after using the search engine,a vector contains all fit employees will be return -->
<jsp:useBean id="employee" class="com.ait.hrm.bean.BasicInfo" />
<jsp:useBean id="supervisor" class="com.ait.hrm.bean.BasicInfo" scope="request"/>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- 先是所有部门列表，和部门相应的考勤员信息  -->
<jsp:useBean id="department" class="com.ait.hrm.bean.Department"/> <!-- data type contained in the dept_list -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤 > 考勤员</title>
<script type="text/javascript" src="../js/ar_search_employee.js"></script>
<SCRIPT type="text/javascript">
<!--hidden
	function Save(){
		if(document.save == null) {
		//"请先选择员工"
			alert('<ait:message  messageID="alert.att.setting.keeper.add.select_employee" module="ar"/>');
			return;
		}
		document.save.submit();
	 } 

	function ListEmployee(deptId,supervisorId){
		url = '/ar/ar_supervised_empList_a.jsp?deptId='+deptId+'&supervisorId='+supervisorId;
		window.open(url,'emplist','width=610,height=400, top=200, left=200, scrollbars=yes,resizable=yes');
	}
	function CheckAll(form){
		for (var i=0;i<form.elements.length;i++){
			var e = form.elements[i];
			if (e.name != 'checkall')
				e.checked = form.checkall.checked;
		}
	}
 //-->
</SCRIPT>

<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<%HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session.getAttribute("admin");
%>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%> 
			
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
		<br>
		<form action="arControlServlet" method="post"  name="searchForm" >
			<input type="hidden" name="operation" value="ar_search">
			<input type="hidden" name="content" value="supervisor">
			<input name="menu_code" type="hidden" value="<%=request.getParameter("menu_code")%>">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/> </td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0"
						cellspacing="1" class="dr_d">
						<tr>
							<td width="15%" class="info_title_01"><!-- 部门-->
								<ait:message  messageID="display.mutual.dept"/> </td>
							<td width="65%" class="info_content_00">
								<%@ include file="../inc/hr_search_engine_no_change.jsp"%>
								<ait:image src="/images/btn/Search.gif" align="absmiddle"
				          			onclick="javascript:document.searchForm.submit();" style="cursor:hand" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			
		</form>
		
		<!-- display 3 level menu -->
		
		<!-- display content -->
		<%if(emp_list.size() > 0){ %>
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 考勤员-->
					<ait:message  messageID="display.mutual.attendance_keeper"/></td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				 <tr align="center">
					<td width="20%" height="29" class="info_title_01"><!-- 中文名-->
					<ait:message  messageID="display.mutual.name"/></td>
					<td width="19%" height="29" class="info_title_01"><!-- 工号-->
					<ait:message  messageID="display.mutual.emp_id_2"/></td>
					<td width="20%" height="29" class="info_title_01"><!-- 职位-->
					<ait:message  messageID="display.mutual.position"/></td>
					<td width="41%" height="29" class="info_title_01"><!-- 所属部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
				</tr>
		
				<c:forEach items="${emp_list}" var="employee">
					<a href='arControlServlet?operation=ar_pagecontrol&empID=${employee.personId }&page=ms_a&menu_code=<%=request.getParameter("menu_code")%>'>
						<%@ include file="../inc/employee_list.jsp"%> 
					</a>
				</c:forEach>
			</table>
		<%}%>
		
		
		<%
			if(supervisor.getEmpID() != null){
		%>
		<form name="save" action="arControlServlet" method="post">
			<input type="hidden" name="operation" value="ar_add">
			<input type="hidden" name="content" value="supervisor">
			<input type="hidden" name="size" value="<%=dept_list.size()%>">
		    <input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
		    <table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!-- 考勤员-->
					<ait:message  messageID="display.mutual.attendance_keeper"/></td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr align="center">
					<td class="info_title_01" height="30" width="20%"><!-- 考勤员号-->
					<ait:message  messageID="display.mutual.emp_id_2"/></td>
					<td class="info_content_00" width="24%">
					<%=supervisor.getEmpID()%><input type="hidden" name="supervisorID" value="<%=supervisor.getPersonId()%>"></td>
					<td class="info_title_01"height="30" width="21%"><!-- 中文名-->
					<ait:message  messageID="display.mutual.name"/></td>
					<td class="info_content_00" width="35%">
					<%=supervisor.getChineseName()%><input type="hidden" name="chineseName" value="<%=supervisor.getChineseName()%>"></td>
				</tr>
			</table><br>

			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td alig="left" class="title1" colspan="10">权限</td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr>
					<input type="hidden" name="itemSize" value="${itemGroupCut}">
					<c:forEach items="${itemGroupList}" var="item" varStatus="i">
						<td class="info_title_01" width="10%">
							<input type="checkbox" name="itemCheck${i.index}" value="${item.CODE_ID}" class="check" checked>
							${item.CODE_NAME}
						</td>
					</c:forEach>
				</tr>
			</table>
			<br>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr align="center">
					<td height="26" colspan="2" align="left" class="info_title_01"><input type="checkbox" name="checkall" onClick="CheckAll(document.save)" class="check">  
					<!--被考勤部门-->
					<ait:message  messageID="display.att.setting.keeper.edit.responsible_for" module="ar"/></th>
				</tr>
				<%
					int level= -1;
					for (int i=0; i<dept_list.size();i++) {
						department = (Department) dept_list.get(i);
						level = department.getDeptLevel();
				%>
				  <tr>
					<td width="99%" height="26">
						<% for(int j=0;j<level;j++){ %> &nbsp;&nbsp;<%}%>
						<input type="checkbox" name="dept_id<%=i%>" value="<%=department.getDeptID()%>" class="check"> 
						<%if(admin1.getLanguagePreference().equals("zh")){%>
		       				<%=department.getDeptName()%>
	       				<%}else if(admin1.getLanguagePreference().equals("ko")){%>
	       					<%=department.getKorDept()%>
	       				<%}else{%>
	       					<%=department.getDeptEnName()%>
	       				<%} %>
					</td>
				  </tr>
				<%}%>
			</table>
		</form>
		<%}%>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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

</body>
</html>
