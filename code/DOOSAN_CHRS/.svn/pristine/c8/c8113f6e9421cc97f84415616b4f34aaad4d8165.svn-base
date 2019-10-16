<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
%>
<form name="LeaveForm">
<div align="left">
<span class="title1">申请信息&gt;销假申请</span></div>
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">按销假选择</td>
    <td width="28%" height="30"  align="left"><select name="leaveTypeCode" style="width: 90px ">
      <option></option>
    </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">销假日期</td>
    <td width="34%" height="30"  align="left"><%=yearCurrentDate%></td>
  </tr>
  <tr><td colspan="4" height="20" bgcolor="#F5F5F5" align="center">以下是您申请休假的内容</td></tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">休假类型</td>
    <td width="28%" height="30"  align="left"><select name="leaveApplyTypeCode"  style="width:85px ">
	                                          <option value="">请选择</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("LeaveTypeCode");
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
				%>
            <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
            <%	}
					}catch (Exception e){
					}
			%>
          </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td width="34%" height="30"  align="left"><%=yearCurrentDate%></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请休假时间</td>
    <td width="28%" height="30"  align="left">&nbsp;共&nbsp;
      <input name="leaveHours" type="text" style="width:40px ">
      &nbsp;小时</td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td width="34%" height="30"  align="left">
      <input type="text" name="leaveStartDate" class="content"  style="width: 90px ">
      <img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('LeaveForm','leaveStartDate');" style="cursor:hand"></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="leaveEndDate" class="content"  style="width: 90px ">
      <img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('LeaveForm','leaveEndDate');" style="cursor:hand"></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="34%" height="30"  align="left">&nbsp;</td>
  </tr>
    <%
	   String empidaffirmed = admin.getAdminID();
	   String affirmcodefront ="";
	   String affirmnamefront ="";
	   affirm_list =(ArrayList)affirmdao.getAffirmorList(empidaffirmed,"OtApply");
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
	                                          <option value="affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="34%" height="30"  align="left"><select name="affirmlist"style="width: 90px ">
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
    <td width="28%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="34%" height="30"  align="left">&nbsp;</td>
  </tr>
  <%}}}%>
  <tr>
     <td align="center" bgcolor="#F5F5F5" height="90px">休假事由</td>
     <td colspan="3"><textarea name="work_content" style=" height: 90px;width:400px "></textarea></td>
</tr>
</table>
</form>
</body>
</html>
