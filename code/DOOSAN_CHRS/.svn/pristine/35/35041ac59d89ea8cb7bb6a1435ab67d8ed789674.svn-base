<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<jsp:useBean id="hrdao" class="com.ait.ar.dao.ArRecordsBean" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="employeebean" class="com.ait.ar.bean.ArRecords" />
<jsp:useBean id="employee_list" class="java.util.ArrayList" />
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
	function Close() {
		parent.showsearch.style.visibility='hidden';
	}
	function Sure() {
		var num = empId.length;
		var strShow = "";
		var strHidden = "";
		
		if(num == null){
			temp = empId.value.split(";;");
			strShow = strShow + " " + temp[1];
			strHidden = strHidden + "@@" + empId.value;
		} else {
			for(i=0;i<num;i++){
				if(empId[i].checked == true){
					temp = empId[i].value.split(";;");
					strShow = strShow + " " + temp[1];
					strHidden = strHidden + "@@" + empId[i].value;
				}
			}
		}
		parent.EmpName.innerHTML=strShow;
		parent.form1.EmpIds.value=strHidden;
		Close();
	}
</script>
</head>
<body style="margin-left:0px; margin-top:0px;">
<%
employee_list =(ArrayList) hrdao.getAllRecords(null, 
								request.getParameter("date")+" "+
								request.getParameter("shh")+":"+
								request.getParameter("smm")+":"+
								request.getParameter("sss"), 
								request.getParameter("date")+" "+
								request.getParameter("ehh")+":"+
								request.getParameter("emm")+":"+
								request.getParameter("ess"),
								request.getParameter("doorType"));
%>
  <table align="center" width="300" border="0" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#99ddcc" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td width="20%" height="20">&nbsp;</td>
      <td width="20%" align="center" bgcolor="#77ddbb" onClick="Sure();" style="cursor:pointer;">确定</td>
      <td width="20%">&nbsp;</td>
      <td width="20%" align="center" bgcolor="#77ddbb" onClick="Close();" style="cursor:pointer;">关闭</td>
      <td width="20%">&nbsp;</td>
    </tr>
  </table>
  <table width="350" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#99ddcc" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td width="57" height="30" align="center" bgcolor="#77ddbb">&nbsp;</td>
      <td width="57" height="30" align="center" bgcolor="#77ddbb">员工ID</td>
      <td width="63" height="30" align="center" bgcolor="#77ddbb">中文姓名</td>
      <td width="57" height="30" align="center" bgcolor="#77ddbb">汉语拼音</td>
      <td width="163" height="30" align="center" bgcolor="#77ddbb">部门名称</td>
      <td width="163" height="30" align="center" bgcolor="#77ddbb">进门卡或出门卡</td>
      <td width="163" height="30" align="center" bgcolor="#77ddbb">打卡时间</td>
    </tr>
   <%if( employee_list.size()!=0){
       for(int i=0;i<employee_list.size();i++){
	      employeebean = (com.ait.ar.bean.ArRecords)employee_list.get(i);%>
    <tr style="cursor:pointer;"> 
      <td  height="30" align="center">
      	<input type="checkbox" name="empId" value="<%=employeebean.getEmpid()%>;;<%=employeebean.getEmpName()%>;;<%=employeebean.getState()%>;;<%=employeebean.getEnterTime()%>">
      </td>
      <td  height="30" align="center"><%=employeebean.getEmpid()%>&nbsp;</td>
      <td  height="30" align="center"><%=employeebean.getEmpName()%>&nbsp;</td>
      <td  height="30" align="center"><%=employeebean.getChinese_pinyin()%>&nbsp;</td>
      <td  height="30" align="center"><%=employeebean.getDeptName()%>&nbsp;</td>
      <td  height="30" align="center"><%=employeebean.getState_name()%>&nbsp;</td>
      <td  height="30" align="center"><%=employeebean.getEnterTime()%>&nbsp;</td>
    </tr>
   <%}}else{%>
    <tr align="center"> 
      <td colspan="7"><br>没有符合条件的员工或您的考勤权限不足<br><br>请检查输入或联系管理员！</td>
    </tr>
    <%}%>
  </table>
</body>
</html>