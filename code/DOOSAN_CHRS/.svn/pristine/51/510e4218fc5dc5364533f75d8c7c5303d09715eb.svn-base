<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hrm.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="supervisorInfoList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="deptListWithObject" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="selectSupervisor" class="com.ait.ar.bean.Supervisor" scope="request"/>
<jsp:useBean id="department" class="com.ait.hrm.bean.Department"/> <!-- data type contained in the dept_list -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考勤 > 考勤员</title>
</head>
<SCRIPT type="text/javascript">

var superviedObjects = new Array();
function Save(){
	innerSuperVisor();
	//document.all("delSupervisor").style.display="";
	document.Em2Form.submit();
 }
function supervisorInfoControl(check,deptid,index){
	if(check == false){
		superviedObjects[index] = deptid;
	}
	if(check == true){
		superviedObjects[index] = null;
	}
 }
function ListEmployee(deptId,supervisorId){
	url = '/ar/ar_supervised_empList.jsp?deptId='+deptId+'&supervisorId='+supervisorId;
	window.open(url,'emplist','width=610,height=400, top=200, left=200, scrollbars=yes,resizable=yes');
}
function innerSuperVisor(){
	var str = "";
	for (var i=0;i<superviedObjects.length;i++){
		if (superviedObjects[i]!=null) str += "<input type=\"hidden\" name=\"delDeptid\" value=\""+superviedObjects[i]+"\">";
	}
	document.all("delSupervisor").innerHTML = str;
}
</SCRIPT>

<body>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");
	List supervisedDeptidList = new ArrayList();
	for(int i=0;i<supervisorInfoList.size();i++){
		supervisedDeptidList.add(((SupervisorInfo)supervisorInfoList.get(i)).getSupervisedDeptID());
	}
%>
<link href="/css/default.css" rel="stylesheet" type="text/css">

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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤员-->
					<ait:message  messageID="display.mutual.attendance_keeper"/></td>
			</tr>
		</table>
		<table width="100%" height="33"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"  style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td width="95" class="info_title_01"><!-- 中文名-->
					<ait:message  messageID="display.mutual.name"/></td>
		    <td width="254" class="info_content_01">
		    	<ait:content enContent="${selectSupervisor.pinyin}" zhContent="${selectSupervisor.chineseName}" koContent="${selectSupervisor.korName}"/>
		    &nbsp;</td>
		    <td width="98" class="info_title_01"><!-- 所属部门-->
					<ait:message  messageID="display.mutual.dept"/></td>
		    <td width="297" class="info_content_01">
		    	<ait:content enContent="${selectSupervisor.enDept}" zhContent="${selectSupervisor.department}" koContent="${selectSupervisor.korDept}"/>
		    &nbsp;</td>
		  </tr>
		</table>
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td alig="left" class="title1" colspan="10">权限</td>
				</tr>
		</table>
		<form action="arControlServlet" method="post" name="Em2Form">
			<input type="hidden" name="operation" value="ar_modify">
			<input type="hidden" name="content" value="supervisorInfo">
			<input type="hidden" name="size" value="<%=dept_list.size()%>">
			<input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
			<input type="hidden" name="superVisorID" value="<%=StringUtil.checkNull(request.getParameter("superVisorID"))%>">
		 <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr>
					<input type="hidden" name="itemSize" value="${item_group_cut}"/>
					<c:forEach items="${item_group}" var="item" varStatus="i">
						<td class="info_title_01" width="10%">
							<input type="checkbox" name="itemCheck${i.index}" value="${item.CODE_ID}" class="check" 
							<c:forEach items="${supervisor_item}" var="result">
								<c:if test="${result.itemId eq item.CODE_ID}">
									checked
								</c:if>
								${result.itemId}
							</c:forEach>>

							${item.CODE_NAME}
						</td>
					</c:forEach>
				</tr>
			</table>
			<br>
		 <table width="100%" height="53" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		     <tr align="center">
		    <td height="27" colspan="2" align="left" class="info_title_01"> <!--被考勤部门-->
					<ait:message  messageID="display.att.setting.keeper.edit.responsible_for" module="ar"/></td>
			</tr> 
		<% 
			int level= -1; 
			for (int i=0; i<dept_list.size();i++) {
				department = (Department) dept_list.get(i);
				level = department.getDeptLevel();
		%>			 
		  <tr>
		    <td >
				<% for(int j=0;j<level;j++){ %> &nbsp;&nbsp;&nbsp;&nbsp;<%}%>
				<input type="checkbox" name="dept_id<%=i%>" value="<%=department.getDeptID()%>" class="check" onClick="javaScript:supervisorInfoControl(this.checked,'<%=department.getDeptID()%>',<%=i%>);" 
		       <% if(supervisedDeptidList.contains(department.getDeptID())){%>checked<%}%>> 
		       <font <% if(deptListWithObject.contains(department.getDeptID())){%> color="#FF0000" style="font-weight:800 " <%} 
		       			else{%>style="font-weight:200 " <%}%>> 
		       <%if(admin1.getLanguagePreference().equals("zh")){%>
     				 <%=department.getDeptName()%>
   				<%}else if(admin1.getLanguagePreference().equals("ko")){%>
   					<%=department.getKorDept()%>
   				<%}else{%>
   					<%=department.getDeptEnName()%>
   				<%} %>
		       </font>
			</td>
		  </tr>
		<%}%>
		</table>
		<div id="delSupervisor" style="display:none "></div>
		</form>

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


</body>
</html>