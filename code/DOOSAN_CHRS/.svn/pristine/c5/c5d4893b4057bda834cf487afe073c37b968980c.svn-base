<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.util.*,com.ait.hrm.bean.*,java.util.*,com.ait.hrm.business.*,com.ait.sqlmap.util.SimpleMap" 
	errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="employeebean" class="com.ait.hrm.bean.BasicInfo" />
<jsp:useBean id="employee_list" class="java.util.ArrayList" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function FillEmpID (empid, empname) {

 var msg='<ait:message messageID="alert.ess.overtime.has_been_added_to_list"  module="ess"></ait:message>';
	 var   arry  = parent.document.getElementById("empIds").value.split(",");   
	  for (i=0;i<arry.length;i++)   
	  {   
	    if(empid==arry[i]) 
	    {
	      alert(msg);  
	      return;
	    }            
	  }
	if (parent.document.getElementById("empIds").value == '')
		parent.document.getElementById("empIds").value += empid;
	else
		parent.document.getElementById("empIds").value += "," + empid ;
	alert(empname);
	parent.document.getElementById("empName").innerText += empname + ",";
}
function Reset() {
	parent.document.getElementById("shiftID").value = "";
	parent.document.getElementById("empIds").value = "";
	parent.document.getElementById("empName").innerText = "";
}
function Close() {
	parent.showsearch.style.visibility='hidden';
}
if(parent.document.getElementById('empIds').value=='' && parent.document.getElementById('empName').innerText!=''){
	Reset();
}
</script>
</head>
<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
<%
HrmServices hrmServices=HrmServices.getInstance();
SimpleMap parameterObject=new SimpleMap();
parameterObject.setString("CONDITION",StringUtil.toCN(request.getParameter("content")));
parameterObject.setString("DEPTID",StringUtil.toCN(request.getParameter("DEPTID")));
parameterObject.setString("SUPERVISOR_ID",admin.getAdminID());                                
employee_list =(ArrayList) hrmServices.retrieveEmpListIncumbency(parameterObject);
%>                                                                             
  <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
    <tr>
      <td width="20%" height="20" class="info_search_02">&nbsp;</td>
      <td width="20%" align="center" onClick="Reset();" style="cursor:pointer;"><!-- 清除  -->
     <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.clear_all" module="ess"></ait:message>
     </td>
      <td width="20%" class="info_search_02">&nbsp;</td>
      <td width="20%" align="center" onClick="Close();" style="cursor:pointer;"><!--  关闭-->
      <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.close" module="ess"></ait:message>
      </td>
      <td width="20%" class="info_search_02">&nbsp;</td>
    </tr>
  </table>
  <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
      <tr> 
      <td height="25" class="info_title_01" ><!--  工号 -->
     <ait:message messageID="display.mutual.emp_id" module="ess"></ait:message>
     </td>
      <td height="25" class="info_title_01" ><!--姓名  -->
       <ait:message messageID="display.mutual.name" module="ess"></ait:message>
      </td>
      <td height="25" class="info_title_01" ><!-- 拼音 -->
    <ait:message messageID="display.mutual.pin_yin" module="ess"></ait:message>   
      </td>
      <td height="25" class="info_title_01" ><!-- 部门名称 -->
     <ait:message messageID="display.mutual.department" module="ess"></ait:message> 
      </td>
    </tr>
   <%if( employee_list.size()!=0){
       for(int i=0;i<employee_list.size();i++){
	      employeebean = (BasicInfo)employee_list.get(i);
	     String language= admin.getLanguagePreference();      
	     if(language.equals("zh"))
	     {
	    	 %>
    <tr onClick="javascript:FillEmpID('<%=employeebean.getEmpID()%>', '<%=StringUtil.checkNull(employeebean.getChineseName())%>')" style="cursor:pointer;"> 
      <%
	     }
	     else
	     {
      %> 
      <tr onClick="javascript:FillEmpID('<%=employeebean.getEmpID()%>', '<%=StringUtil.checkNull(employeebean.getPinyinName())%>')" style="cursor:pointer;"> 
      <%} %>
      <td  class="info_search_02" height="25" align="center"><%=employeebean.getEmpID()%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getChineseName())%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getPinyinName())%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><ait:content enContent='<%=employeebean.getEnglishDept()%>' zhContent='<%=employeebean.getDeptFullName()%>' koContent='<%=employeebean.getKorDept()%>'>
      </ait:content>&nbsp;</td>
    </tr>
   <%}}else{%>
     <tr align="center">      
      <td colspan="4"　class="info_search_02" height="25" align="center"><br>没有符合条件的员工或您的考勤权限不足<br><br>请检查输入或联系管理员！</td>
    </tr>
    <%}%>
  </table>
</body>
</html>