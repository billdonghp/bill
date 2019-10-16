<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
<%@ page import="java.util.*,
				com.ait.hr.bean.*,
				com.ait.hr.dao.*,
				com.ait.ess.dao.*,
				com.ait.ess.entity.*,
				com.ait.util.*"
%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="codemap" class="java.util.HashMap" />  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function clickaction(){
    document.familyForm.submit();
}
function Save(){
    document.employeeForm.submit();
}
 //-->
</SCRIPT>

<body>
<div align="left">
<span class="title1">考勤信息申请/批量加班申请</span></div>
<%
   String selectContent = request.getParameter("selectContent")!=null?request.getParameter("selectContent"):"";
   String selectCondition = request.getParameter("selectCondition")!=null?request.getParameter("selectCondition"):"";
   String SQL = "";
   if(!selectCondition.equals("")&&!selectContent.equals("")){
            SQL =selectCondition+"'%" +selectContent+"%'";
   }
   HrDAO hrDao = new HrDAO();
   List employe_list = new ArrayList();
   employe_list = hrDao.supervisedEmplist(admin.getAdminID(),SQL);
   EmployeeBean employeebean = new EmployeeBean();
%>
<form name="familyForm" action="/ess/ess0204.jsp" method="post">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
    <tr>
	  <td align="right" width="100%"><input type="text" name="selectContent" style="width:90px ">&nbsp;<select name="selectCondition">
                                                                                        <option value="">全部</option>
                                                                                        <option value=" and DEPARTMENT like ">部门</option>
                                                                                        <option value=" and empid like ">工号</option>
                                                                                        <option value=" and chinesename like ">中文姓名</option>
                                                                                        <option value=" and chinese_pinyin like ">姓名拼音</option>
                                                                                        </select> <img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:clickaction();">
																						</td></tr>
</form>
<form name="employeeForm" method="post" action="/ess/ess0204_t.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<input type="hidden" name="emp_length" value="<%=employe_list.size()%>">
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="23"  height="30"  align="center" bgcolor="#F5F5F5"><input type="checkbox" name="checkAll" value="checkbox" class="check" onclick="javascript:CheckAll('checkbox');"></td>
    <td width="51"  height="30"  align="center" bgcolor="#F5F5F5">姓名</td>
    <td width="46"  height="30"  align="center" bgcolor="#F5F5F5">工号</td>
    <td width="126"  height="30"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillAll('otdate')">加班日期</a></td>
    <td width="77"  height="30"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillAll('deductTime')">扣除时间</a></td>
    <td width="70"  height="30"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillAll('anotherDay')">隔日</a></td>
    <td width="185"  height="30"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillOtTimes('start_time','end_time')">加班时刻</a></td>
    <td width="86"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillAll('otTypeCode')">加班类型</a></td>
    <td width="70"  height="30"  align="center" bgcolor="#F5F5F5"><a href="javascript:FillAll('OtContent')">加班内容</a></td>
  </tr>
  <%
     if(employe_list.size()>0){
     for(int i=0;i<employe_list.size();i++){
     employeebean = (EmployeeBean) employe_list.get(i);
  %>
   <tr align="center">
    <td  height="30"  align="center" bgcolor="#F5F5F5"><input type="checkbox" name="checkbox<%=i%>" class="check"></td>
    <td  height="30"  align="center"><%=StringUtil.editNbsp(employeebean.getChineseName())%></td>
    <td  height="30"  align="center"><%=StringUtil.editNbsp(employeebean.getEmpID())%><input type="hidden" name="empid<%=i%>" value="<%=employeebean.getEmpID()%>"></td>
    <td  height="30"  align="center"><input type="text" name="otdate<%=i%>" class="content"  style="width: 80px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('employeeForm','otdate<%=i%>');" style="cursor:hand"></td>
    <td  height="30"  align="center"><select name="deductTime<%=i%>" style="width: 70px ">
	                                           <option value="0">0</option>
	                                           <%
											      double deducttime=  0.0;
											      for(int j=0;j<10;j++){
												    deducttime = deducttime+0.5;
											   %>
	                                           <option value="<%=deducttime%>"><%=deducttime%></option>
											   <%}%>
											   </select></td>
    <td  height="30"  align="center"><select name="anotherDay<%=i%>" style="width: 70px ">
												<%
													for(int k=0;k<3;k++){
														out.println("<option value=\""+k+"\">"+k+"</option>");
													}
												%>
	                                               </select></td>
    <td  height="30"  align="center"><select name="start_time<%=i%>" style="width: 60px ">
	                                           <%
											      String starttime1="";
											      String starttime2="";
											      for(int j=0;j<24;j++){
												    if(j<10){
													   starttime1="0"+j+":00";
													   starttime2="0"+j+":30";
													   }
												    if(j>=10){
													   starttime1= j+":00";
													   starttime2= j+":30";
													   }
											   %>
	                                           <option value="<%=starttime1%>"><%=starttime1%></option>
	                                           <option value="<%=starttime2%>"><%=starttime2%></option>
											   <%}%>
											   </select>～<select name="end_time<%=i%>" style="width: 60px ">
	                                           <%
											      String endtime1="";
											      String endtime2="";
											      for(int j=0;j<24;j++){
												    if(j<10){
													   endtime1="0"+j+":00";
													   endtime2="0"+j+":30";
													   }
												    if(j>=10){
													   endtime1= j+":00";
													   endtime2= j+":30";
													   }
											   %>
	                                           <option value="<%=endtime1%>"><%=endtime1%></option>
	                                           <option value="<%=endtime2%>"><%=endtime2%></option>
											   <%}%>
											   </select></td>
    <td  align="center">
		 <select name="otTypeCode<%=i%>"  style="width:85px ">
		   <%
						try{
							Vector vector = SysCodeParser.getCode("OTTypeCode");
							for ( int j=0; j < vector.size(); j++)
							{
								codemap = (HashMap) vector.elementAt(j);
					%>
		   <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
		   <%	}
						}catch (Exception e){
						}
				%>
		 </select>
	</td>
    <td  height="30"  align="center"><input name="OtContent<%=i%>" type="text" style="width:70px "></td>
  </tr>
  <%}}else{%>
   <tr align="center">
    <td  height="30"  align="center" colspan="9">请通过搜索查找您所需要的人!</td>
  </tr>
  <%}%>
 </table>
</form>
 </body>
<script language="JavaScript" type="text/JavaScript">
function CheckAll(check)
{
	for (var i=0;;i++)
	{
		var e =document.all(check+i);
		if(e==null) break;
		e.checked = document.all('checkAll').checked;
	}
}
function FillAll(name){
	for (var i=1;;i++)
	{
	var e =document.all(name+i);
		if(e==null) break;
	e.value = document.all(name+0).value;
	}
}
function FillOtTimes(from,to){
	FillAll(from);
	FillAll(to);
}
</script>
</html>
