<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.hr.bean.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="hrdao" class="com.ait.hr.dao.HrDAO" scope="request"/>
<html>
<head>
<title></title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
<!--
	function document.onselectstart(){
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
			try{
				ChangeTextColor(preEl,orgTColor);
			}catch(e){
				;
			}
		}
		var el = event.srcElement;
		el = el.parentElement;
		orgBColor = el.bgColor;
		orgTColor = el.style.color;
		el.bgColor=backColor;
		try{
			ChangeTextColor(el,textColor);
		}catch(e){
			;
		}
		preEl = el;
		ID=i;
		MENU_CODE=menu_code;
		document.dd.action="batchApplyOfVacation.jsp?session=add&empId="+ID;
		document.dd.submit();
	}
-->
</script>
<body leftmargin="0" topmargin="0">
<%
	String value = request.getParameter("value");
	List empList =(List)hrdao.getEmployeeForSearch(value);
%>
<form action="" method="post" name="dd" target="_parent">
	<input type="hidden" name="value" value="<%=value %>">
  <table width="100%" hight="100%" border="1" cellpadding="0" cellspacing="0" 
  	bordercolorlight="#F5F5F5" bordercolordark="#F5F5F5" style="padding: 2px 2px 2px 2px;">
    <tr> 
      <th width="57" height="20%" align="center" bgcolor="#F5F5F5">员工ID</th>
      <th width="63" height="20%" align="center" bgcolor="#F5F5F5">中文姓名</th>
      <th width="57" height="20%" align="center" bgcolor="#F5F5F5">汉语拼音</th>
      <th width="163" height="40%" align="center" bgcolor="#F5F5F5">部门名称</th>
    </tr>
<%
			if(empList != null){
				EmployeeBean emp;
				for(int i=0; i < empList.size(); i++){
					emp = (EmployeeBean)empList.get(i);
%>
    <tr onClick="HighLightTR('#99CCFF','black','<%=emp.getEmpID()%>','ess0202')" > 
      <td height="30" align="center"><%=emp.getEmpID()%>&nbsp;</td>
      <td height="30" align="center"><%=emp.getChineseName() %>&nbsp;</td>
      <td height="30" align="center"><%=emp.getChinesePinyin()%>&nbsp;</td>
      <td height="30" align="center"><%=emp.getDepartment()%>&nbsp;</td>
    </tr>
<%
				}
			}else{
%>
    <tr align="center"> 
      <td colspan="4">没有您要查的人员！</td>
    </tr>
<%
			}
%>
		</table>
</form>
</body>
</html>