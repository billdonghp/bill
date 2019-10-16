<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.ess.entity.*,com.ait.ess.dao.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="tolerence" class="com.ait.ess.entity.Tolerence" scope="request"/> 
<jsp:useBean id="tolerenceContent" class="com.ait.ess.entity.TolerenceContent" scope="request"/> 
<jsp:useBean id="tolerenceFeeContent" class="com.ait.ess.entity.TolerenceFeeContent" scope="request"/>
<jsp:useBean id="tolerenceContent_list" class="java.util.ArrayList" scope="request"/> 
<jsp:useBean id="tolerenceFeeContent_list" class="java.util.ArrayList" scope="request"/>  
<jsp:useBean id="affirmdao" class="com.ait.ess.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/inc/hrtoolbar_b.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function backFront(){
    var menu_code = document.tolerenceForm.menu_code.value;
	location.href="/ess/"+menu_code+".jsp?menu_code="+menu_code;
	
}
 //-->
</SCRIPT>

<body>
<% 
   String tolerenceNo = request.getParameter("tolerenceNo")!=null? request.getParameter("tolerenceNo"):"";
   tolerence = affirmdao.getTolerenceApplyAffirm(tolerenceNo);
   tolerenceContent_list = (ArrayList)affirmdao.getTolerenceContentAffirm(tolerenceNo);
   tolerenceFeeContent_list =(ArrayList)affirmdao.getTolerenceFeeContentAffirm(tolerenceNo);
%>
<form name="tolerenceForm" method="post" action="/ess/ess0508_v.jsp">
<input name="menu_code" value="<%=menu_code%>" type="hidden">
<div align="left">
<span class="title1">出差申请</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
       <td align="center" height="30" bgcolor="#F5F5F5" colspan="2">出差开始日期</td>
       <td align="center" height="30" colspan="2"><%=tolerence.getTolerenceFromTime()%></td>
       <td align="center" height="30" bgcolor="#F5F5F5" colspan="2">出差结束日期</td>
       <td align="center" height="30" colspan="3"><%=tolerence.getTolerenceToTime()%></td>
  <tr>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差目的</td>
    <td  height="30"  align="left" align="center" colspan="8"><%=tolerence.getTolerenceTarget()%></td>
  </tr>
  <tr>
    <td  height="20"  align="center" bgcolor="#F5F5F5" colspan="9"><strong>出 差 日 程 和 内 容</strong></td>
  </tr>
  <tr align="center">
    <td  height="30"  align="center" bgcolor="#F5F5F5" >时间</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >交通方式</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="2">地域</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="2">公社</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="3">活动内容</td>
  </tr>
   <%   if(tolerenceContent_list.size()>0){
      for(int i=0; i<tolerenceContent_list.size(); i++){
	    tolerenceContent = (TolerenceContent) tolerenceContent_list.get(i);
   %>
  <tr>
    <td  height="30"  align="center"><%=tolerenceContent.getTolerenceTime()%>&nbsp;</td>
    <td  height="30"  align="center"><%=tolerenceContent.getTolerencTransfer()%>&nbsp;</td>
    <td  height="30"  align="center" colspan="2"><%=tolerenceContent.getTolerencPlace()%>&nbsp;</td>
    <td  height="30"  align="center" colspan="2"><%=tolerenceContent.getTolerencCompany()%>&nbsp;</td>
    <td  height="30"  align="center" colspan="3"><%=tolerenceContent.getTolerencContent()%>&nbsp;</td>
  </tr>
  <%}}%>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  
  <tr>
    <td  height="20"  align="center" bgcolor="#F5F5F5" colspan="9"><strong>出 差 费 用</strong></td>
  </tr>
  <tr>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >时间</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >票价</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >住宿费</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >工杂费</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >其它</td>
  </tr>  
  <%  if(tolerenceFeeContent_list.size()>0){  
      for(int i=0; i<tolerenceFeeContent_list.size(); i++){
	    tolerenceFeeContent = (TolerenceFeeContent) tolerenceFeeContent_list.get(i);
  %>
  <tr align="center">
    <td  height="20" rowspan="2" align="center"><%=tolerenceFeeContent.getTolerencFeeTime()%>&nbsp;</td>
    <td  height="20"  align="center">￥ <%=tolerenceFeeContent.getTicketFee()%>元&nbsp;  共 <%=tolerenceFeeContent.getTicketCount()%> 份</td>
    <td  height="20"  align="center">￥ <%=tolerenceFeeContent.getHouseFee()%>元&nbsp;  共 <%=tolerenceFeeContent.getHouseCount()%> 份</td>
    <td  height="20"  align="center">￥ <%=tolerenceFeeContent.getWorkFee()%>元&nbsp;  共 <%=tolerenceFeeContent.getWorkCount()%> 份</td>
    <td  height="20"  align="center">￥ <%=tolerenceFeeContent.getOtherFee()%>元&nbsp;  共 <%=tolerenceFeeContent.getOtherCount()%> 份</td>
  </tr>
  <tr align="center">
    <td  height="20"  align="center">$  <%=tolerenceFeeContent.getTicketFeeD()%>元&nbsp;  共 <%=tolerenceFeeContent.getTicketCountD()%> 份</td>
    <td  height="20"  align="center">$  <%=tolerenceFeeContent.getHouseFeeD()%>元&nbsp;  共 <%=tolerenceFeeContent.getHouseCountD()%> 份</td>
    <td  height="20"  align="center">$  <%=tolerenceFeeContent.getWorkFeeD()%>元&nbsp;  共 <%=tolerenceFeeContent.getWorkCountD()%> 份</td>
    <td  height="20"  align="center">$  <%=tolerenceFeeContent.getOtherFeeD()%>元&nbsp;  共 <%=tolerenceFeeContent.getOtherCountD()%> 份</td>
  </tr>
 <%}}%>
 </table>
</form>
</body>
</html>
