<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.bean.*,com.ait.ess.entity.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="hrDao" class="com.ait.hr.dao.HrDAO"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="empList" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="employee" class="com.ait.hr.com.ait.gm.DateBean.bean.EmployeeBean" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
if(document.LeaveForm.leaveApplyTypeCode.options[document.LeaveForm.leaveApplyTypeCode.selectedIndex].value == "")
	{
		alert("请选择休假类型！^_^");
		return;
	}	
	if(document.LeaveForm.work_content.value=="")
	{
		alert("请写明出差/培训事由!^_^");
		return;
	}		
    document.LeaveForm.submit();
}
function reloadPage(empid){
	location.href='/ess/ess0202.jsp?menu_code=ess0202&leaveApplyPerson='+empid;
}
 //-->
</SCRIPT>
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
%>
<form name="LeaveForm" action="/ess/ess0202_t.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="empID" value="<%=admin.getAdminID()%>"/>
<div align="left">
<span class="title1">申请信息>出差/培训申请</span></div>
<table width="90%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请人选择</td>
    <td width="28%" height="30"  align="left"><select name="leaveApplyPerson" style="width: 90px " onChange="javascript:reloadPage(this.value);">
	                                <%
									
									   empList = (ArrayList)hrDao.supervisedEmplist(admin.getAdminID(),"");
									   for(int i=0;i<empList.size();i++){
									       employee = (EmployeeBean)empList.get(i);
									%>
	                                         <option value="<%=employee.getEmpID()%>" <%if(StringUtil.checkNull(request.getParameter("leaveApplyPerson"),admin.getAdminID()).equals(employee.getEmpID())){%> selected <%}%>><%=StringUtil.toUnicode(StringUtil.toISO(employee.getChineseName()),"UTF-8")%></option>
										<%}%>
									</select></td>
  <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请类型</td>
    <td width="34%" height="30"  align="left"><select name="leaveApplyTypeCode"  style="width:85px ">
            //<%
				//	try{
				//		Vector vector = SysCodeParser.getCode("LeaveTypeCode");
				//		for ( int i=0; i < vector.size(); i++)
				//		{
				//			codemap = (HashMap) vector.elementAt(i);
			//	%>
			<option value="">请选择</option>
            <option value="出差">出差</option>
            <option value="培训">培训</option>
            <%//	}
				//	}catch (Exception e){
				//	}
			%>
          </select></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td width="28%" height="30"  align="left"><%=yearCurrentDate%></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">出差/培训长度</td>
    <td width="34%" height="30"  align="left">共&nbsp;<input name="leaveHours" type="text" style="width:40px ">&nbsp;小时</td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="leaveStartDate" class="content"  style="width: 90px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('LeaveForm','leaveStartDate');" style="cursor:hand">
     <select name="start_time" style="width: 60px ">
	                                           <%
											      String starttime1="";
											      String starttime2="";
											      for(int i=0;i<24;i++){
												    if(i<10){
													   starttime1="0"+i+":00";
													   starttime2="0"+i+":30";
													   }
												    if(i>=10){
													   starttime1= i+":00";
													   starttime2= i+":30";
													   }
											   %>
	                                           <option value="<%=starttime1%>"><%=starttime1%></option>
	                                           <option value="<%=starttime2%>"><%=starttime2%></option>
											   <%}%>
											   </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td width="34%" height="30"  align="left"><input type="text" name="leaveEndDate" class="content"  style="width: 90px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('LeaveForm','leaveEndDate');" style="cursor:hand">
    <select name="end_time" style="width: 60px ">
	                                           <%
											      String endtime1="";
											      String endtime2="";
											      for(int i=0;i<24;i++){
												    if(i<10){
													   endtime1="0"+i+":00";
													   endtime2="0"+i+":30";
													   }
												    if(i>=10){
													   endtime1= i+":00";
													   endtime2= i+":30";
													   }
											   %>
	                                           <option value="<%=endtime1%>"><%=endtime1%></option>
	                                           <option value="<%=endtime2%>"><%=endtime2%></option>
											   <%}%>
											   </select>
    </td>
  </tr>
    <%
	   String empidaffirmed = StringUtil.checkNull(request.getParameter("leaveApplyPerson"),admin.getAdminID());
	   String affirmcodefront ="";
	   String affirmnamefront ="";
	   affirm_list =(ArrayList)affirmdao.getAffirmorList(empidaffirmed,"LeaveApply");
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
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="34%" height="30"  align="left">&nbsp;</td>
  </tr>
  <%}}}%>
  <tr>
     <td align="center" bgcolor="#F5F5F5" height="90px">出差/培训事由</td>
     <td colspan="3"><textarea name="work_content" style=" height: 90px;width:400px "></textarea></td>
</tr> 
</table>
</form>
</body>
</html>
