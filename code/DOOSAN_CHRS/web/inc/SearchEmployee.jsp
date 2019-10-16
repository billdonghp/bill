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
</head>
<script language="JavaScript" type="text/JavaScript">
function document.onselectstart()
{
    var tmpObj= event.srcElement.tagName;
    if ((tmpObj=="INPUT" || tmpObj=="TEXTAREA") && event.srcElement.readOnly==false)
            return true;
    if (tmpObj.toUpperCase()=="DATEPICKER")
            return true;
    return false;
}

var preEl ;
var orgBColor;
var orgTColor;
var ID='';
var MENU_CODE='';

function HighLightTR(backColor,textColor,i,menu_code){
var t;
if(typeof(preEl)!='undefined') {
preEl.bgColor=orgBColor;

try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
}
var el = event.srcElement;
el = el.parentElement;
orgBColor = el.bgColor;
orgTColor = el.style.color;
el.bgColor=backColor;                
try{ChangeTextColor(el,textColor);}catch(e){;}
preEl = el;               
ID=i;            
MENU_CODE=menu_code;                                                                
document.dd.action="/sy/sy0106_emp_t.jsp?menu_code="+menu_code+"&affirmor="+ID;         
document.dd.submit();
}
function back(){
	if(parent.document.all.layername){
		parent.document.all.layername.style.visibility='hidden';
	}
}
</script>
<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
<%
String deptcodeId = request.getParameter("deptcodeId")!=null?request.getParameter("deptcodeId"):"";
String affirmorid = request.getParameter("affirmorid")!=null?request.getParameter("affirmorid"):"";
String affirmtypeId = request.getParameter("affirmtypeId")!=null?request.getParameter("affirmtypeId"):"";
String empId = StringUtil.checkNull(request.getParameter("empId"));


HrmServices hrmServices=HrmServices.getInstance();
SimpleMap parameterObject=new SimpleMap();
parameterObject.setString("CONDITION",StringUtil.toCN(affirmorid));
employee_list =(ArrayList) hrmServices.retrieveEmpList(parameterObject);
%>
<form action="" method="post" name="dd" target="_parent">
<input type="hidden" name="deptcodeId" value="<%=deptcodeId%>">
<input type="hidden" name="empId" value="<%=empId%>">
<input type="hidden" name="affirmorid" value="<%=affirmorid%>">
<input type="hidden" name="affirmtypeId" value="<%=affirmtypeId%>">
<table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d"><!-- 点击表行关闭窗口  -->
<tr onclick="back();" style="position: relative; top: expression(this.offsetParent.scrollTop);" title="<ait:message  messageID="alert.mutual.click_close"/>"> 
      <td height="25" align="center" bgcolor="#F5F5F5" ><!--  工号-->
      <ait:message messageID="display.mutual.emp_id" ></ait:message>
      </td>
      <td height="25" align="center" bgcolor="#F5F5F5" ><!--姓名  -->
       <ait:message messageID="display.mutual.name" ></ait:message>
      </td>
      <td height="25" align="center" bgcolor="#F5F5F5" ><!-- 拼音 -->
       <ait:message messageID="display.mutual.pin_yin"></ait:message>
      </td>
      <td height="25" align="center" bgcolor="#F5F5F5" ><!-- 部门名称 -->
       <ait:message messageID="display.sys.basic_maintenance.dept_maintenance.dept_name" module="sys"></ait:message>
      </td>
    </tr>            
  <%if( employee_list.size()!=0){  
       for(int i=0;i<employee_list.size();i++){                          
	      employeebean = (BasicInfo)employee_list.get(i);%>
    <tr onClick="HighLightTR('#99CCFF','black','<%=employeebean.getEmpID()%>',${param.menu_code})" > 
      <td  class="info_search_02" height="25" align="center"><%=employeebean.getEmpID()%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getChineseName())%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center"><%=StringUtil.checkNull(employeebean.getPinyinName())%>&nbsp;</td>
      <td  class="info_search_02" height="25" align="center">
      <ait:content enContent='<%=StringUtil.checkNull(employeebean.getEnglishDept())%>' 
      zhContent='<%=StringUtil.checkNull(employeebean.getDepartment())%>'></ait:content>&nbsp;</td>
    </tr>                  
   <%}}else{%>
    <tr align="center">           
      <td colspan="4"　class="info_search_02" height="25" align="center"><br><!-- 没有符合条件的员工或您的考勤权限不足,请检查输入或联系管理员！ -->
       <ait:message messageID="alert.ess.overtime.no_appropriate_and_authority" module="ess"></ait:message>
      </td>
    </tr>
    <%}%>
  </table>
</form>
</body>
</html>