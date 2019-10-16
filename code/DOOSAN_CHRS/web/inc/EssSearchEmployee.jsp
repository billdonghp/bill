<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.util.*,com.ait.hrm.bean.*,java.util.*,com.ait.hrm.business.*,com.ait.sqlmap.util.SimpleMap" 
	errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="employeebean" class="com.ait.hrm.bean.BasicInfo" />
<jsp:useBean id="employee_list" class="java.util.ArrayList" />

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function FillEmpID (empId) {
	
	var mark = "${param.mark}";
	if ( mark == null || mark == "") {
	
		parent.document.ApplyForm.empId.value=empId;
	} else {
	
		parent.document.getElementById("empId_" + mark).value=empId;
	}
	
	parent.document.ApplyForm.operation.value="view";
	parent.document.ApplyForm.submit();
}
function back(){
	if(parent.document.all.showsearch){
		parent.document.all.showsearch.style.visibility='hidden';
	}
}
</script>
</head>
<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
<%
HrmServices hrmServices=HrmServices.getInstance();
SimpleMap parameterObject=new SimpleMap();
parameterObject.setString("CONDITION",StringUtil.toCN(request.getParameter("condition")));
parameterObject.setString("SUPERVISOR_ID",admin.getAdminID());  
employee_list =(ArrayList) hrmServices.retrieveEmpList(parameterObject);
%>
  <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d"><!-- 点击表行关闭窗口  -->
      <tr onclick="back();" style="position: relative; top: expression(this.offsetParent.scrollTop);" title="<ait:message  messageID="alert.mutual.click_close"/>"> 
      <td height="25" class="info_title_01" ><!-- 工号 -->
     <ait:message messageID="display.mutual.emp_id" module="ess"></ait:message>
      </td>
      <td height="25" class="info_title_01" ><!-- 姓名 -->
        <ait:message messageID="display.mutual.name" module="ess"></ait:message>
      </td>
      <td height="25" class="info_title_01" ><!--拼音  -->
    <ait:message messageID="display.mutual.pin_yin" module="ess"></ait:message>  
      </td>
      <td height="25" class="info_title_01" ><!--  部门名称 -->
      <ait:message messageID="display.mutual.department" module="ess"></ait:message>    
     </td>
    </tr>
   <%if( employee_list.size()!=0){
       for(int i=0;i<employee_list.size();i++){
	      employeebean = (BasicInfo)employee_list.get(i);%>
    <tr onClick="javascript:FillEmpID('<%=employeebean.getEmpID()%>')" style="cursor:pointer;"> 
      <td  class="info_search_02" height="25" align="center"><%=employeebean.getEmpID()%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getChineseName())%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getPinyinName())%>&nbsp;</td>
     <td  class="info_search_02" height="25" align="center"><ait:content enContent='<%=StringUtil.checkNull(employeebean.getEnglishDept())%>' zhContent='<%=StringUtil.checkNull(employeebean.getDeptFullName())%>' koContent='<%=StringUtil.checkNull(employeebean.getKorDept())%>'></ait:content>
    </tr>    
   <%}}else{%>                                                     
    <tr align="center"> 
      <td colspan="4"　class="info_search_02" height="25" align="center"><br>
     <ait:message messageID="alert.ess.overtime.no_appropriate_and_authority" module="ess"> </ait:message>
   <!--   没有符合条件的员工或您的考勤权限不足请检查输入或联系管理员！  --></td>    
    </tr>
    <%}%>
  </table>
</body>
</html>