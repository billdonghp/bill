<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.bean.*,com.ait.hr.entity.*,java.util.*,java.util.Vector" errorPage="" %>
<jsp:useBean id="file" class="com.ait.hr.entity.Archive" scope="request"/>
<jsp:useBean id="contract" class="com.ait.hr.entity.Contract"/>
<jsp:useBean id="contract_vector" class="java.util.Vector" scope="request" />  <!-- contains the health info -->
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<jsp:useBean id="employee" class="com.ait.hr.com.ait.gm.DateBean.bean.EmployeeBean" scope="request"/> <!-- formed from emp id -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>contract information</title>
</head>
<!-- -->
<body>
<link href="css/default.css" rel="stylesheet" type="text/css">

<%@ include file="../ess/esstoolbar.jsp"%>
<%@ include file="/hr/hr_view_basic.jsp"%>


<%@ include file="../hr/hr_diaoling_toolbar.jsp"%>
<br>
<div align="left">
<span class="title1">合同信息</span></div>

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<tr align="center">
  <td bgcolor="#F5F5F5">入社日期</td>
    <td height="30" bgcolor="#F5F5F5">合同年限</td>
	<td bgcolor="#F5F5F5">开始日期</td>
	<td bgcolor="#F5F5F5">结束日期</td>
  </tr>
  <% for (int i=0;i<contract_vector.size();i++){
  	contract = (Contract)contract_vector.elementAt(i);
  %>
  		<tr align="center">
  		  <td class="info_content_01"><%=basic.getJoinDate()%>&nbsp;</td>
			<td height="30" class="info_content_01"><%=contract.getContractPeriod()%>&nbsp;</td>
			<td class="info_content_01"><%=contract.getContractStartDate()%>&nbsp;</td>
			<td class="info_content_01"><%=contract.getContractEndDate()%>&nbsp;</td>
		</tr>
	<%}%>
  
</table>
<div align="left"> <span class="title1">档案信息</span></div>
  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center"> 
      <td width="12%" height="30" bgcolor="#F5F5F5">档案号</td>
      <td width="12%" bgcolor="#F5F5F5">建档日期</td>
      <td width="12%" bgcolor="#F5F5F5">档案位置</td>
      <td width="21%" bgcolor="#F5F5F5">存放机构</td>
      <td width="21%" bgcolor="#F5F5F5">转出信息</td>
      <td width="22%" bgcolor="#F5F5F5">备注</td>	  
    </tr>
	<% if(file.getFileDate()!=null){ %>
    <tr align="center"> 
      <td height="30"  class="info_content_01"><%=file.getFileNo()%>&nbsp;</td>
      <td class="info_content_01"><%=file.getFileDate()%>&nbsp;</td>
      <td class="info_content_01"><%=file.getFilePositionID()%>&nbsp;</td>
      <td class="info_content_01"><%=file.getFileLocatedInstitute()%>&nbsp;</td>
      <td class="info_content_01">&nbsp;</td>
      <td class="info_content_01"><%=file.getComments()%>&nbsp;</td>
    </tr>
	<%}%>
  </table>
  <br>
</body>
</html>
