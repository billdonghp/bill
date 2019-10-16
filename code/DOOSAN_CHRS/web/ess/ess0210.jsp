<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.hr.bean.EmployeeBean,com.ait.hr.dao.HrDAO,java.util.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
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
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<%
	String timeFormat = "yyyy-MM-dd hh:mm:ss";
	SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
	String sDate = timeFormatter.format(new Date());
	String yearCurrentDate = StringUtil.substring(sDate, 10);
	String result = request.getParameter("result");
	HrDAO hrDAO = new HrDAO();
	List employeelist = new ArrayList();
	employeelist = hrDAO.getEmployeeForSearch("", admin.getAdminID());
	EmployeeBean employee = new EmployeeBean();
%>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
	document.addworkForm.submit();
}
function add()
{
	var length=document.addworkForm.empids.length;
	var index=document.addworkForm.empid_list.selectedIndex;
	document.addworkForm.empids.options[length]=new Option(document.addworkForm.empid_list.options[index].text,document.addworkForm.empid_list.options[index].value);
	document.addworkForm.empid_list.options[index]=null;
}
function del()
{
	var length=document.addworkForm.empid_list.length;
	var index=document.addworkForm.empids.selectedIndex;
	document.addworkForm.empid_list.options[length]=new Option(document.addworkForm.empids.options[index].text,document.addworkForm.empids.options[index].value);
	document.addworkForm.empids.options[index]=null;
}
 //-->
</SCRIPT>
<form name="addworkForm" action="/ess/ess0201_t.jsp">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="empID" value="<%=admin.getAdminID()%>"/>
<div align="left">
<span class="title1">申请信息＞超时加班申请</span></div>
<%if (result!=null) {
	if (result.equals("0"))
		result = "申请失败";
	else if (result.equals("1"))
		result = "申请成功,请等待决裁者进行批复";
	else if (result.equals("2"))
		result = "无法获取加班类型,请与人事部门联系,检查公社日历设置";
	else if (result.equals("3"))
		result = "开始结束时间错误";
	else if (result.equals("4"))
		result = "加班未超过36小时,请使用普通加班申请";
	else if (result.equals("5"))
		result = "重复申请,请检查之前的申请记录";
	else if (result.equals("6"))
		result = "加班时间与实际班次时间冲突";
%>
<table width="90%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center"><td align="center"><font color="red"><%=result%></font></td>
  </tr>
</table>
<%}%>
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<%if(employeelist.size()!=0){%>
  <tr>
     <td align="center" bgcolor="#F5F5F5" height="90px">加班人员选择</td>
    <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td width="40%"><select name="empid_list" size="6" style="width:100%">
<%for (int i=0; i<employeelist.size();i++) {
	employee = (EmployeeBean) employeelist.get(i);%>
	<OPTION value="<%=employee.getEmpID()%>"><%=employee.getEmpID()%>&nbsp;-&nbsp;<%=employee.getChineseName()%></OPTION>
<%}%>
          </select></td><td width="20%" align="center" valign="middle">
          <input type="button" value="添加" onClick="add()"><br>
          <input type="button" value="删除" onClick="del()"></td><td width="40%">
          <select name="empids" size="6" style="width:100% " >
          </select></td></tr></table></td></tr>
<%}%>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">开始时间</td>
    <td width="28%" height="30"  align="left"><select name="start_time" style="width: 90px ">
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
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">隔天</td>
    <td width="34%" height="30"  align="left"><select name="anotherDay" style="width: 70px ">
												<%
													for(int k=0;k<3;k++){
														out.println("<option value=\""+k+"\">"+k+"</option>");
													}
												%>
	                                               </select></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">结束时间</td>
    <td width="28%" height="30"  align="left"><select name="end_time" style="width: 90px ">
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
											   </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">扣除时间</td>
    <td width="34%" height="30"  align="left"><select name="deduct_time" style="width: 70px ">
	                                           <option value="0">0</option>
	                                           <%
											      double deducttime=  0.0;
											      for(int i=0;i<10;i++){
												    deducttime = deducttime+0.5;
											   %>
	                                           <option value="<%=deducttime%>"><%=deducttime%></option>
											   <%}%>
											   </select></td>
  </tr>
<!--    <tr align="center">
     <td height="30"  align="center" bgcolor="#F5F5F5">加班类型</td>
     <td height="30"  align="left">
		 <select name="otTypeCode"  style="width:85px ">
		   <%
						try{
							Vector vector = SysCodeParser.getCode("SOTTypeCode");
							for ( int i=0; i < vector.size(); i++)
							{
								codemap = (HashMap) vector.elementAt(i);
					%>
		   <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
		   <%	}
						}catch (Exception e){
						}
				%>
		 </select>
	 </td>
     <td height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
     <td height="30"  align="left">&nbsp;</td>
   </tr>
-->
    <%
	   String empidaffirmed = admin.getAdminID();
	   String affirmcodefront ="";
	   String affirmnamefront ="";
	   affirm_list =(ArrayList)affirmdao.getAffirmorList(empidaffirmed,"SOTApply");
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
    <td width="34%" height="30"  align="left"><select name="affirmlist"style="width: 70px ">
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
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">加班日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="OTdate" class="content" style="width:90px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('addworkForm','OTdate');" style="cursor:hand"></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td width="34%" height="30"  align="left"><%=yearCurrentDate%></td>
  </tr>
  <tr>
     <td align="center" bgcolor="#F5F5F5" height="90px">工作内容</td>
    <td colspan="3"><textarea name="work_content" style=" height: 90px;width:400px "></textarea></td></tr>
</table>
</form>
</body>
</html>
