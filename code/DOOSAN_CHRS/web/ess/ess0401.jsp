<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/> 
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
    document.tolerenceForm.submit();
}
 //-->
</SCRIPT>

<body>
<form name="tolerenceForm" action="/ess/ess0401_t.jsp" method="post">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="empID" value="<%=admin.getAdminID()%>"/>
<div align="left">
<span class="title1">出差申请</span></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差日期</td>
    <td  height="30"  align="left" align="center" colspan="8">&nbsp;<input type="text" name="StartDate" class="content"  style="width: 90px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('tolerenceForm','StartDate');" style="cursor:hand">
	    &nbsp;至&nbsp;<input type="text" name="EndDate" class="content"  style="width: 90px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('tolerenceForm','EndDate');" style="cursor:hand"></td>
  </tr>
  <tr>
    <td  height="30"  align="center" bgcolor="#F5F5F5">出差目的</td>
    <td  height="30"  align="left" align="center" colspan="8"><textarea name="tolerenceTarget" style=" height: 90px;width:400px "></textarea></td>
  </tr>
    <%
	   String empidaffirmed = admin.getAdminID();
	   String affirmcodefront ="";
	   String affirmnamefront ="";
	   affirm_list =(ArrayList)affirmdao.getAffirmorList(empidaffirmed,"TolerenceApply");
	   if(affirm_list.size() > 0){
	    for(int i=1;i<=affirm_list.size();i++){
	     codemap = (HashMap)affirm_list.get(i-1);
	     if((i%2)==1){
		     affirmcodefront =(String)codemap.get("affirmorID");
		     affirmnamefront =(String)codemap.get("affirmor");
			 }
		 if((i%2)==0&&i!=0){
	 %>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i-1%>级决裁者</td>
    <td width="28%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="34%" height="30"  align="left" colspan="5"><select name="affirmlist"style="width: 90px ">
	                                          <option value="<%=codemap.get("affirmorID")%>"><%=codemap.get("affirmor")%></option>
											  <option value="noperson">无</option>
											  </select></td>
  </tr>
  <%
    affirmcodefront="";
    affirmnamefront="";
    }
	if(i==affirm_list.size() && i%2==1){
  %>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="28%" height="30"  align="left" ><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="34%" height="30"  align="left" colspan="5">&nbsp;</td>
  </tr>
  <%}}}%>
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
   <%
      for(int i=1; i<=6; i++){
   %>
  <tr>
    <td  height="30"  align="center"><input name="tolerencTime" type="text" style="width:80px "></td>
    <td  height="30"  align="center"><select name="tolerencTransfer" style="width:80px ">
	                                 <option value="">请选择</option>
	                                 <option value="plane">飞机</option>
									 <option value="train">火车</option>
	                                 <option value="bus">汽车</option>
	                                 <option value="otherTransfer">其它</option></td>
    <td  height="30"  align="center" colspan="2"><input type="text" name="tolerencPlace" style="width:120px "></td>
    <td  height="30"  align="center" colspan="2"><input type="text" name="tolerencCompany" style="width:120px "></td>
    <td  height="30"  align="center" colspan="3"><input type="text" name="tolerencContent" style="width:200px "></td>
  </tr>
  <%}%>
</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >时间</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" >票价</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="2">住宿费</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="2">工杂费</td>
    <td  height="30"  align="center" bgcolor="#F5F5F5" colspan="3">其它</td>
  </tr>  
  <%
    for(int j=1; j<=7; j++){
  %>
  <tr align="center">
    <td  height="20" rowspan="2" align="center"><input type="text" name="tolerencFeeTime" style="width:120px; height:40px "></td>
    <td  height="20"  align="center">￥<input type="text" name="ticketFee" style="width:90px ">&nbsp;<select name="ticketCount" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="2"><input type="text" name="houseFee" style="width:90px ">&nbsp;<select name="houseCount" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="2"><input type="text" name="workFee" style="width:90px ">&nbsp;<select name="workCount" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="3"><input type="text" name="otherFee" style="width:90px ">&nbsp;<select name="otherCount" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
  </tr>
  <tr align="center">
    <td  height="20"  align="center">$ <input type="text" name="ticketFeeD" style="width:90px ">&nbsp;<select name="ticketCountD" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="2"><input type="text" name="houseFeeD" style="width:90px ">&nbsp;<select name="houseCountD" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="2"><input type="text" name="workFeeD" style="width:90px ">&nbsp;<select name="workCountD" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
    <td  height="20"  align="center" colspan="3"><input type="text" name="otherFeeD" style="width:90px ">&nbsp;<select name="otherCountD" style="width:40px ">
	                                                                                                                <%for(int i=1;i<=10;i++){
																													%>
	                                                                                                               <option value="<%=i%>"><%=i%></option>
																												   <%}%></select></td>
  </tr>
 <%}%>
</form>
</body>
</html>
