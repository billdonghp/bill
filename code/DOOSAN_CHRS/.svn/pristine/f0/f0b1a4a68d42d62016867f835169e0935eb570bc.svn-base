<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArCalendarType"%>
<%@ page import="com.ait.ar.bean.ArDynamicGroup"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date"%>
<jsp:useBean id="factory" scope="page" class="com.ait.ar.dao.ArFactoryCalendarBean">
</jsp:useBean>
<jsp:useBean id="calendarType" scope="page" class="com.ait.ar.dao.ArCalendarTypeBean">
</jsp:useBean>
<jsp:useBean id="ArGroup" scope="page" class="com.ait.ar.dao.ArScheduleBean">
</jsp:useBean>
<%
String err=null;
String errMsg = request.getParameter("errMsg");
request.setCharacterEncoding("UTF-8");
if(request.getParameter("errMsg")!=null){
  err=request.getParameter("errMsg");
}
ArrayList groups=ArGroup.getGroupNo();
ArDynamicGroup arg=null;


ArCalendarType ar=null;
ArrayList items=calendarType.getCalendarType();
String factorycalendar=null;

int year;
int month;
if(request.getParameter("year_")!=null&&request.getParameter("month_")!=null){
  year=Integer.parseInt(request.getParameter("year_"));
  month=Integer.parseInt(request.getParameter("month_"));
  factorycalendar=factory.getCalendar(year,month,0);
}
else{
  Date d=new Date();
    String dates=d.toLocaleString();
	dates=dates.substring(0,dates.indexOf(" "));
	String[] ds=dates.split("-");
	year=Integer.parseInt(ds[0].toString());
	month=Integer.parseInt(ds[1].toString());
	factorycalendar=factory.getCalendar(year,month,0);
}
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Search()
{
var year=document.form1.year.value;
var month=document.form1.month.value;
document.form1.action="/ar/groupcalendar_a.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
document.form1.submit();
}

function editShow(id)
{

 /* document.form1.pk.value=id;
  var x=event.x;
  var y=event.y;
  document.all.edit.style.top=y-30;
  document.all.edit.style.left=x;
  document.all.edit.style.display="";*/
}
function Edit()
{
  document.form1.action="/arControlServlet?operation=factorycalendarupdate&menu_code=<%=request.getParameter("menu_code")%>"
  document.form1.submit();
}
function outyear()
{
var check="";
document.write("<select name='year'>");
for(var i=2000;i<=2010;i++)
{
	check="";
	if(i==<%=year%>)
	check="selected";
	document.write("<option value='"+i+"' "+check+" >"+i+"</option>");
}
document.write("</select>");
}
function outmonth()
{
var check="";
document.write("<select name='month'>");
for(var i=1;i<=12;i++)
{
	check="";
	if(i==<%=month%>)
	check="selected";

	if(parseInt(i)<10)
	{
		document.write("<option value='0"+i+"' "+check+" >0"+i+"</option>");
	}
	else
	{
		document.write("<option value='"+i+"' "+check+" >"+i+"</option>");
	}
}
document.write("</select>");
}

function editClose(){
document.all.edit.style.display="none";
}

function gotob()
{
	var year="<%=year%>";
	year=parseInt(year);
	var month="<%=month%>";
	month=parseInt(month);
	if(month==1)
	{
		year=year-1;
		month=12;
	}
	else if(month>1&&month<=12)
	{
		month=month-1;
	}
	document.form1.action="/ar/groupcalendar_a.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
	document.form1.submit();
}
function gotoa()
{
	var year="<%=year%>";
	year=parseInt(year);
	var month="<%=month%>";
	month=parseInt(month);
	if(month==12)
	{
		year=year+1;
		month=1;
	}
	else if(month>=1&&month<12)
	{
		month=month+1;
	}
	document.form1.action="/ar/groupcalendar_a.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
	document.form1.submit();
}

function Save()
{
  	document.form1.action='/arControlServlet?operation=groupcalendaradd&menu_code=<%=request.getParameter("menu_code")%>';
        document.form1.submit();
}

function showMessage()
{
	
	if(<%=errMsg == null %>) 
		return;
 	else 
 		alert("动态组日历已经存在");
}
</script>
</head>
<body>
<%@ include file="/inc/common_toolbar_a.jsp"%>
<form name="form1" method="post">
<script language="javascript">showMessage();</script>
<table width="100%" border="1" align="left" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
  	<td colspan="7">将此日历保存至动态组
  	  <select name="groupId">
	  <%for(int i=0;i<groups.size();i++){
          arg=(ArDynamicGroup)groups.get(i);
        %>
        <option value="<%=arg.getGroupNo()%>"><%=arg.getGroupName()%></option>
        <%}%>
      </select></td>
  </tr>
  <tr align="center">
  	<td colspan="2"><a href="javascript:gotob()" title="上一月">&lt;&lt;</a></td>
    <td height="30" colspan="3"><script language="javascript">outyear()</script>
      年
      <script language="javascript">outmonth()</script>
      月
      <input type="button" name="Submit" value="查看" onClick="Search()"></td>
	  <td colspan="2"><a href="javascript:gotoa()" title="下一月">&gt;&gt;</a></td>
  </tr>
  <tr align="center">
    <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期日</font></td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期一</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期二</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期三</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期四</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期五</td>
    <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期六</font></td>
  </tr>
  <%=factorycalendar!=null?factorycalendar:""%>
</table>
<div id="edit" style='position:absolute; left:0px; top:0px; z-index:1; background-color: #FFFFEC; layer-background-color: #FFFFEC; border: 1px double #000000; overflow: hidden; display: "none";'>
<table width="200" border="1" align="left" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr>
      <td><select name="work">
        <option value="1">工作</option>
        <option value="0">不工作</option>
      </select></td>
    </tr>
    <tr>
      <td><select name="type">
        <%for(int i=0;i<items.size();i++){
          ar=(ArCalendarType)items.get(i);
        %>
        <option value="<%=ar.getTypeNo()%>"><%=ar.getTypeName()%></option>
        <%}%>
      </select></td>
    </tr>
    <tr>
      <td><input type="button" value="更新" onclick="Edit()"/>
        <input type="button" name="Button" value="取消" onClick="editClose()"></td>
    </tr>
  </table>
</div>
<input type="hidden" name="pk" value="0">
<input type="hidden" name="hyear" value="<%=year%>">
<input type="hidden" name="hmonth" value="<%=month%>">
<a name="flag"></a>
</form>
</body>
</html>
