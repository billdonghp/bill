<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*,java.util.*,com.ait.ar.bean.*,com.ait.ar.dao.*,com.ait.ar.dao.implementation.*,com.ait.i18n.MessageSource,com.ait.util.*,com.ait.sy.bean.*"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
	DynamicGroupDAOImpl dynamicGroupDAO = new DynamicGroupDAOImpl();
	AdminBean admin = (AdminBean)session.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("ar",admin.getLocale(), "UTF-8");
	String nogroup = messageSource1.getMessage("display.att.maintenance.pattern.no_group");
	String deleteemp = messageSource1.getMessage("display.att.maintenance.pattern.delete_emp");
	
	dynamicGroupDAO.setLoginID(admin.getAdminID());
	dynamicGroupDAO.setCnpyID(admin.getCpnyId());
	
	String groupNo = request.getParameter("groupNo");
	String conditionNo = request.getParameter("conditionNo");
	String[] empids = request.getParameterValues("deletelist");
	String operation = StringUtil.checkNull(request.getParameter("operation"));
	String title = "符合条件员工预览";

	List memberList = null;

	String operateResult = null;
	if(operation.equals("addStaff")){
		operateResult = dynamicGroupDAO.addOrDeleteGroupMember(conditionNo,"ADD");
	}else if(operation.equals("deleteStaff")){
		operateResult = dynamicGroupDAO.addOrDeleteGroupMember(conditionNo,"DELETE");
	}else if(operation.equals("deleteMember")){
		operateResult = dynamicGroupDAO.deleteGroupMembers(empids);
	}

	if (conditionNo == null){
		memberList = dynamicGroupDAO.getGroupMembers(Integer.parseInt(groupNo),admin.getCpnyId());
		title = "组员预览";
	}
	else
		memberList = dynamicGroupDAO.getGroupMembers(Integer.parseInt(groupNo), Integer.parseInt(conditionNo));

%>
<title><%=title%></title>
<script language="javascript">
	function addstaff(){

	    document.staff.action="/ar/dynamic_group_members.jsp?groupNo=<%=groupNo%>&conditionNo=<%=conditionNo%>&operation=addStaff";
	    document.staff.submit();
	}
	
	function deletestaff(){
		document.staff.action="/ar/dynamic_group_members.jsp?groupNo=<%=groupNo%>&conditionNo=<%=conditionNo%>&operation=deleteStaff";
	    document.staff.submit();
	}
	
	function deleteMember(){
		document.staff.action="/ar/dynamic_group_members.jsp?groupNo=<%=groupNo%>&operation=deleteMember";
	    document.staff.submit();
	}
	
	function checkAll(){
		if (document.staff.deletelist){
			if (document.staff.deletelist[0]){
				if (document.staff.deleteall.checked)
					for(i=0;i<document.staff.deletelist.length;i++)
						document.staff.deletelist[i].checked=true;
				else
					for(i=0;i<document.staff.deletelist.length;i++)
						document.staff.deletelist[i].checked=false;
			} else {
				if (document.staff.deleteall.checked)
					document.staff.deletelist.checked=true;
				else
					document.staff.deletelist.checked=false;
			}
		}
	}
</script>
</head>
<body>
<br>
<form name="staff" method="post" action="">
<%if (conditionNo != null) {%>
<table width="600" border="1" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td><!-- "添加以下员工到当前组"  "从当前组删除以下员工" -->
			<input type="button" name="addSta" value='<ait:message  messageID="display.att.maintenance.pattern.add_current_group" module="ar"/>' style="cursor:pointer;width:250px" onclick="javascript:addstaff();">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="deleteSta" value='<ait:message  messageID="display.att.maintenance.pattern.delete_employee_group" module="ar"/>' style="cursor:pointer;width:280px" onclick="javascript:deletestaff();">
		</td> 
	</tr>
</table>

<%if (operateResult != null) {%>
<br>
<table width="600" border="1" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td width="90%" height="24"><font color="red"><%=operateResult%></font></td>
	</tr>
</table>
<%}%>
<br>
<%}%>
<table width="98%" border="1" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;" align="center">
	<tr height="5">
		<td width="10%" class="info_title_01"><!--序号-->
					<ait:message  messageID="display.mutual.no"/></td>
		<td width="35%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
		<td width="40%" height="24" class="info_title_01"><!--工号--><!--"当前分组" 删除选定 -->
					<ait:message  messageID="display.mutual.emp_id"/>&nbsp;(&nbsp;<%=title.equals("符合条件员工预览")?nogroup:"<input id=\"deleteall\" type=\"checkbox\" onClick=\"javascript:checkAll();\" />&nbsp;&nbsp;<span onClick=\"javascript:deleteMember();\" style=\"cursor:pointer\">"+deleteemp+"</span>"%>&nbsp;)</td>
		<td width="15%" height="24" class="info_title_01" align="center"><!--姓名-->
					<ait:message  messageID="display.mutual.name"/></td>
	</tr>
<%
	for (int i = 0; i < memberList.size(); i++) {
		GroupMember groupMember = (GroupMember) memberList.get(i);
		String deptName = null;
		String groupName = null;
		String name = null;
		if (admin.getLanguagePreference().equals("zh")) {
			
			deptName = groupMember.getDeptname();//"尚无分组"
			groupName = StringUtil.checkNull(groupMember.getGroupname()) == "" ? nogroup:groupMember.getGroupname();
			name = groupMember.getChinesename();
		} else {
			
			deptName = groupMember.getDeptEnName();
			groupName = StringUtil.checkNull(groupMember.getGroupEnName()) == "" ? "No group":groupMember.getGroupEnName();
			name = groupMember.getChinesePinyin();
		}
%>
	<tr height="3">
		<td align="center"><%=i+1%></td>
		<td><%=deptName%>&nbsp;</td>
		<td>
			<%=groupMember.getEmpId()%>
<%
if (title.equals("符合条件员工预览")) {
		out.println("&nbsp;&nbsp;(" + groupName + ")");
}else{
	out.println("&nbsp;&nbsp;<input type=\"checkbox\" name=\"deletelist\" value=\"" + groupMember.getPerson_id() + "\" title=\"选择进行删除\" />");
}
%>
		</td>
		<td height="25" class="content"><%=name%>&nbsp;</td>
	</tr>
	<%}%>
	<%if (memberList.size() <= 0) {%>
	<tr>
		<td height="28" colspan="4" align="center"><font color="red">
		<!-- 无被选定员工! -->
		<ait:message  messageID="display.att.maintenance.pattern.no_employee" module="ar"/>
		</font></td>
	</tr>
	<%}%>
</table>
</form>
</body>
</html>
