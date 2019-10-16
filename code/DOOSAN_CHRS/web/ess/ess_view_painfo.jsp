<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hr.bean.*,com.ait.hr.entity.*,java.util.*,java.util.Vector"%>
<jsp:useBean id="paInfo" class="com.ait.hr.com.ait.gm.DateBean.bean.PaEmpInfo"/>
<jsp:useBean id="paInfo_List" class="java.util.ArrayList" scope="request" />  <!-- contains the health info -->
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Relative information</title>
</head>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="/ess/esstoolbar.jsp"%>
<%@ include file="/hr/hr_view_basic.jsp"%>
<%@ include file="../hr/hr_diaoling_toolbar.jsp"%>
<br>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
      <tr align="center">
        <td width="80"  height="30" bgcolor="#F5F5F5" >工资计算标志</td>
        <td bgcolor="#F5F5F5" >地市</td>
        <td bgcolor="#F5F5F5" >开户行</td>
        <td bgcolor="#F5F5F5" >账户名</td>
        <td bgcolor="#F5F5F5" >帐号</td>
        <td bgcolor="#F5F5F5" >CostCenter</td>
      </tr>
      <% for (int i=0;i<paInfo_List.size();i++){
  	paInfo = (PaEmpInfo)paInfo_List.get(i);
  %>
      <tr align="center">
        <td height="30"  class="info_content_01"><%
		if (paInfo.getCalcFlag().equals("Y")){
		out.print("是");
		}else{
		out.print("否");
		}
		%></td>        
        <td class="info_content_01"><%=com.ait.util.StringUtil.editNbsp(paInfo.getBankCityName())%></td>
        <td class="info_content_01"><%=com.ait.util.StringUtil.editNbsp(paInfo.getBankNameName())%></td>
        <td class="info_content_01"><%=com.ait.util.StringUtil.editNbsp(paInfo.getCardName())%></td>
        <td class="info_content_01"><%=com.ait.util.StringUtil.editNbsp(paInfo.getCardNo())%></td>
        <td class="info_content_01"><%=com.ait.util.StringUtil.editNbsp(paInfo.getCostCenterName())%></td>
	  </tr>
      <%}%>
	<% if (paInfo_List.size()<=0){ %>
       <tr align="center">
         <td height="30"  class="info_content_01">&nbsp;</td>
         <td class="info_content_01">&nbsp;</td>
         <td class="info_content_01">&nbsp;</td>
         <td class="info_content_01">&nbsp;</td>
         <td class="info_content_01">&nbsp;</td>
         <td class="info_content_01">&nbsp;</td>
       </tr>
	<%}%>
    </table>
<br>
</body>
<SCRIPT type="text/javascript">
<!--hidden
function Add(){
                    if(<%=paInfo_List.size() == 0%>){
                          document.sf.info.value = "add_pa_info";
                          document.sf.submit();
                    }else{
        	alert("对不起，该人员已存在帐号");
                     return ;
                    }
 }
function Update(){
	document.sf.info.value ="pa_info";
	document.sf.submit();
}
 //-->
</SCRIPT>
</html>
