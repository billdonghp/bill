<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="com.ait.sy.bean.AdminBean" %>
<jsp:useBean id="workcalendar" scope="page" class="com.ait.ar.dao.ArWorkCalendarBean"></jsp:useBean>
<%
String calendar="";
//String div="";
String empID=null;
String month_str=null;
/*传递登陆者ID*/
AdminBean admin1 = (AdminBean)session.getAttribute("admin");
workcalendar.setLoginID(admin1.getAdminID());
if(request.getParameter("date")!=null&&request.getParameter("empID")!=null)
{
  empID=request.getParameter("empID");
  if(empID == null || empID.equalsIgnoreCase("")){
	  empID = admin1.getAdminID();
  }
  month_str=request.getParameter("date");
  Logger.getLogger(getClass()).error("##########" + empID + "###########");
  if(workcalendar.validate(empID)){
	  calendar=workcalendar.getCalender(empID,month_str);
  }
}
int year=0;
int month=0;
if(month_str != null){
	month = Integer.parseInt(month_str.trim().substring(4));
	year = Integer.parseInt(month_str.trim().substring(0,4));
} else {
	Date d=new Date();
	String dates = new java.text.SimpleDateFormat("yyyy-MM-dd").format(d);
	String[] ds=dates.split("-");
	year=Integer.parseInt(ds[0].toString());
	month=Integer.parseInt(ds[1].toString());
}
%>
<html>
<head>
<title>员工工作日历表</title>

<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/changeDate.js"></script>
<script language="javascript" src="../js/changeTime.js"></script>
<script language="javascript">
if(<%=empID%>!=null && !<%=workcalendar.validate(empID)%>)
{
  alert("对不起，员工不存在或者您没有查看此人信息的权限");
}
function calendarSub()
{
  	var a = document.form1.empID.value;
	if(document.form1.empID.value=="")
        {
          	alert("请输入员工号");
                  return false;
        }
        var date=document.form1.year.value+document.form1.month.value;
        document.form1.action="workcalendar.jsp?empID="+document.form1.empID.value+"&date="+date+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
        document.form1.submit();
        return true;
}

function find(dayn)
{
  document.all.layername.style.display="";
  var empid=document.form1.empIDs.value;
  var date=document.form1.monthstr.value;
  editCalendar.location.href="workedit.jsp?empid="+empid+"&date="+date+"&day="+dayn+"&menu_code=<%=request.getParameter("menu_code")%>";

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
function SearchEmp(condition,id){
	if(condition!=''){
		var inputBox = document.getElementById(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		
		layer=document.getElementById("showsearch");
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;
		layer.style.visibility='visible';
		document.emp_list.location.href = "/inc/ArSearchEmployee.jsp?content="+encodeURIComponent(condition）+"&id=" + id;
		
	} else { 
		document.all.showsearch.style.visibility='hidden';
	}
}
</script>
</head>
<body>
<%@ include file="/inc/common_toolbar.jsp"%>
	<div id='showsearch' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
		<iframe width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="emp_list" ></iframe>
	</div>
<form name="form1" method="post" action="" >
  <table width="745" border="1"  cellspacing="0" cellpadding="10" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
      <td align="center">员工号</td>
      <td align="left"><input onKeyUp="SearchEmp(this.value,this.id)" id="empID" name="empID" type="text" size="12" value="<%=request.getParameter("empID")!=null?request.getParameter("empID"):""%>"></td>
      <td align="left">&nbsp;</td>
      <input type="hidden" name="empIDs" value="<%=empID%>">
      <input type="hidden" name="monthstr" value="<%=month_str%>">
    </tr>
    <tr align="center">
      <td>时间</td>
      <td align="left"><script language="javascript">outyear()</script>
        年
        <script language="javascript">outmonth()</script>
      月</td>
      <td align="left"><img src="../images/btn/p_confirm.gif" width="63" height="21" style="cursor: pointer" onClick="calendarSub()"></td>
    </tr>
  </table>
  <table width="745"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr align="center">
      <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期日</font></td>
      <td bgcolor="#F5F5F5" height="30" width="100">星期一</td>
      <td bgcolor="#F5F5F5" height="30" width="100">星期二</td>
      <td bgcolor="#F5F5F5" height="30" width="100">星期三</td>
      <td bgcolor="#F5F5F5" height="30" width="100">星期四</td>
      <td bgcolor="#F5F5F5" height="30" width="100">星期五</td>
      <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期六</font></td>
    </tr>
    <%if(calendar!=null){%>
    <%=calendar%>
    <%}%>
</table>
<div id='layername' style="position:absolute; top:0;left:0; width:500; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; display: none;">
<iframe width="500" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0" name="editCalendar"></iframe></div>
<input type="hidden" name="hidNumber" value="0"/>
<a name="flag"></a>
</form>

</body>

</html>
