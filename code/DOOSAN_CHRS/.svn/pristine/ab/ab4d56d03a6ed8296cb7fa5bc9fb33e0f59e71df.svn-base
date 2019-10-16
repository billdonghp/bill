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
<script src="../js/birthday.js"></script>
<SCRIPT type="text/javascript">
<!--hidden
function Save(){
	if(document.LeaveForm.leaveApplyTypeCode.value=="")
	{
		alert("请选择休假类型");
		return ;
	}
	if(document.LeaveForm.leaveHours.value=="" || isNaN(document.LeaveForm.leaveHours.value) )
	{
		alert("休假长度必须是数值");
		return ;
	}
	if(trim(document.LeaveForm.work_content.value).length<1)
	{
		alert("必须输入休假事由");
		return ;
	}
	if(!confirm("是否确定提交休假申请"))
	{
		return;
	}
	if(checkTime()==1){
		alert("开始时间不能大于结束时间");
		return;
	}
	
    document.LeaveForm.submit();
}
function reloadPage(empid){
	document.LeaveForm.leaveApplyPerson.value=empid;
	
}


//去掉字串左边的空格
function lTrim(str)
{
if (str.charAt(0) == " ")
{
//如果字串左边第一个字符为空格
str = str.slice(1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(1, str.length);
str = lTrim(str); //递归调用
}
return str;
}


//去掉字串右边的空格
function rTrim(str)
{
var iLength;

iLength = str.length;
if (str.charAt(iLength - 1) == " ")
{
//如果字串右边第一个字符为空格
str = str.slice(0, iLength - 1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(0, iLength - 1);
str = rTrim(str); //递归调用
}
return str;
}


//去掉字串两边的空格
function trim(str)
{
return lTrim(rTrim(str));
}

function outHour()
{
	for(var i=0;i<24;i++){
		if(i<10){
		document.write("<option value=0"+i+">0"+i+"</option>");
		}else{
			document.write("<option value="+i+">"+i+"</option>");
		}
	}
}

function outMinute()
{
	for(var i=0;i<60;i++){
		if(i<10){
		document.write("<option value=0"+i+">0"+i+"</option>");
		}else{
			document.write("<option value="+i+">"+i+"</option>");
		}
	}
}


function checkTime(){
	var startdate=document.LeaveForm.leaveStartDate.value;
	var startyear=parseInt(startdate.substring(0,4));
	var startmonth=parseInt(startdate.substring(5,7));
	var startday=parseInt(startdate.substring(8,10));
	var starthour=parseInt(document.LeaveForm.leaveStartHour.value);
	var startminute=parseInt(document.LeaveForm.leaveStartMinute.value);

	var enddate=document.LeaveForm.leaveEndDate.value;
	var endyear=parseInt(enddate.substring(0,4));
	var endmonth=parseInt(enddate.substring(5,7));
	var endday=parseInt(enddate.substring(8,10));
	var endhour=parseInt(document.LeaveForm.leaveEndHour.value);
	var endminute=parseInt(document.LeaveForm.leaveEndMinute.value);
	if(startyear>endyear){
		//alert("开始时间不能大于结束时间");
		return 1;
	}
	else if(startmonth>endmonth){
		//alert("开始时间不能大于结束时间");
		return 1;
	}else if(startday>endday){
		//alert("开始时间不能大于结束时间");
		return 1;
	}else if(starthour>endhour){
	//	alert("开始时间不能大于结束时间");
		return 1;
	}else if(startminute>endminute){
	//	alert("开始时间不能大于结束时间");
		return 1;
	}
	return 0;
}
 //-->
</SCRIPT>
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
	 
%>
<form name="LeaveForm" action="/EssContorlServlet">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="empID" value="<%=admin.getAdminID()%>"/>
	<input type="hidden" name="command" value="WorkApplyCommandImp"/>
	<input type="hidden" name="leaveApplyPerson" value="<%=admin.getAdminID()%>"/>
	
	<input type="hidden" name="content" value="WorkApplyLeaveContentImp"/>
<div align="left">
<span class="title1">申请信息>休假申请</span></div>
<table width="99%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">休假人选择</td>
    <td width="31%" height="30"  align="left"><select name="leaveApplyPerson" style="width: 90px " onChange="javascript:reloadPage(this.value);">
	                                <%
									
									   empList = (ArrayList)hrDao.supervisedEmplist(admin.getAdminID(),"");
									   for(int i=0;i<empList.size();i++){
									       employee = (EmployeeBean)empList.get(i);
									%>
	                                         <option value="<%=employee.getEmpID()%>" <%if(StringUtil.checkNull(request.getParameter("leaveApplyPerson"),admin.getAdminID()).equals(employee.getEmpID())){%> selected <%}%>><%=employee.getChineseName()%></option>
										<%}%>
									</select></td>
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">休假类型</td>
    <td width="35%" height="30"  align="left"><select name="leaveApplyTypeCode"  style="width:85px ">
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
  </tr>
   <tr align="center">
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td width="31%" height="30"  align="left"><%=yearCurrentDate%></td>
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">休假长度</td>
    <td width="35%" height="30"  align="left">共&nbsp;<input name="leaveHours" type="text" style="width:40px ">&nbsp;小时</td>
  </tr>
   <tr align="center">
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td width="31%" height="30"  align="left"><input type="text" name="leaveStartDate" class="content"  style="width: 90px " onclick="show_cele_date('','1970-1-1','2010-1-1',this)">&nbsp;
      <select name="leaveStartHour">
	  <script>outHour()</script>
      </select>
      时
      <select name="leaveStartMinute">
	  <script>outMinute()</script>
      </select>
      分</td>
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td width="35%" height="30"  align="left"><input type="text" name="leaveEndDate" class="content"  style="width: 90px " onclick="show_cele_date('','1970-1-1','2010-1-1',this)">&nbsp;
      <select name="leaveEndHour">
	  <script>outHour()</script>
      </select>
时
<select name="leaveEndMinute">
<script>outMinute()</script>
</select>
分</td>
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
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i-1%>级决裁者</td>
    <td width="31%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="35%" height="30"  align="left"><select name="affirmlist"style="width: 90px ">
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
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="31%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="17%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="35%" height="30"  align="left">&nbsp;</td>
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
