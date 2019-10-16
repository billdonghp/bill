<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.ar.bean.ArCalendarType"%>
<%@ page import="com.ait.ar.bean.*,java.util.Date,java.util.ArrayList"%>
<jsp:useBean id="factory" scope="page" class="com.ait.ar.dao.ArFactoryCalendarBean" />
<jsp:useBean id="calendarType" scope="page" class="com.ait.ar.dao.ArCalendarTypeBean" />
<jsp:useBean id="shift" scope="page" class="com.ait.ar.dao.ArShift010Bean" />
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
ArCalendarType ar=null;
ArrayList items=calendarType.getCalendarType();
String factorycalendar=null;

ArShift010 shiftObject = null;
ArrayList shiftList = shift.getShift010();
request.setAttribute("shiftList",shiftList);

HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session.getAttribute("admin");

factory.setLanguage(admin1.getLanguagePreference());

int year;
int month;
boolean isNull = false;
if(request.getParameter("year_")!=null&&request.getParameter("month_")!=null){
  year=Integer.parseInt(request.getParameter("year_"));
  month=Integer.parseInt(request.getParameter("month_"));
  factorycalendar=factory.getCalendar(year,month,0);
  
  if(factorycalendar == null)
	  isNull = true;
}
else{
  Date d=new Date();
	String dates = new java.text.SimpleDateFormat("yyyy-MM-dd").format(d);
	String[] ds=dates.split("-");
	year=Integer.parseInt(ds[0].toString());
	month=Integer.parseInt(ds[1].toString());
	factorycalendar=factory.getCalendar(year,month,0);
	if(factorycalendar == null)
		isNull = true;
}
%>
<script language="javascript">
function Search()
{
var year=document.form1.year.value;
var month=document.form1.month.value;
document.form1.action="/ar/factorycalendar.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
document.form1.submit();
}
function Add()
{
      document.form1.action="/ar/factorycalendar_a.jsp?menu_code=<%=request.getParameter("menu_code")%>";
      document.form1.submit();
}
function Update()
{
	if(<%=isNull%>) {
		//"没有可以修改的数据"
		alert('<ait:message  messageID="alert.att.setting.lsfc_calendar.no_date" module="ar"/>');
		return;
	}
	var year=document.form1.hyear.value;
	var month=document.form1.hmonth.value;
	document.form1.action="/ar/factorycalendar_m.jsp?menu_code=<%=request.getParameter("menu_code")%>#flag";
	document.form1.submit();
}
function editShow(id)
{

/*  document.form1.pk.value=id;
  var x=event.x;
  var y=event.y;
  document.all.edit.style.top=y-30;
  document.all.edit.style.left=x;
  document.all.edit.style.display=""; */
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
for(var i=2005;i<=2019;i++)
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
	document.form1.action="/ar/factorycalendar.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
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
	document.form1.action="/ar/factorycalendar.jsp?year_="+year+"&month_="+month+"&menu_code=<%=request.getParameter("menu_code")%>#flag";
	document.form1.submit();
} 
</script>
<form name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_no_del.jsp"%> 
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->

		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 公司日历-->
					<ait:message  messageID="display.att.setting.calendar" module="ar"/></td>
			</tr>
		</table>
		<table width="100%" border="1" align="left" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		  	<td colspan="2"><a href="javascript:gotob()" title="previous">&lt;&lt;</a></td>
		  	
		  	<!-- 年数据 -->
		    <td height="30" colspan="3" style="color: #666666;"><script language="javascript">outyear();</script>
		     <!--  年-->
					<ait:message  messageID="display.mutual.year"/>
		      <script language="javascript">outmonth();</script>
		     <!--  月-->
					<ait:message  messageID="display.mutual.month"/>
		      <ait:image src="../images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search()" style="cursor:hand" /></td>
		      </td>
			  <td colspan="2"><a href="javascript:gotoa()" title="next">&gt;&gt;</a></td>
		  </tr>
		  <tr align="center">
		    <td  class="info_title_01" height="30" width="100"><font color="red"><!-- 星期日-->
					<ait:message  messageID="display.att.setting.calendar.sunday" module="ar"/></font></td>
		    <td  class="info_title_01" height="30" width="100"><!-- 星期一-->
					<ait:message  messageID="display.att.setting.calendar.monday" module="ar"/></td>
		    <td  class="info_title_01" height="30" width="100"><!-- 星期二-->
					<ait:message  messageID="display.att.setting.calendar.tuesday" module="ar"/></td>
		    <td  class="info_title_01" height="30" width="100"><!-- 星期三-->
					<ait:message  messageID="display.att.setting.calendar.wednesday" module="ar"/></td>
		    <td  class="info_title_01" height="30" width="100"><!-- 星期四-->
					<ait:message  messageID="display.att.setting.calendar.thursday" module="ar"/></td>
		    <td  class="info_title_01" height="30" width="100"><!-- 星期五-->
					<ait:message  messageID="display.att.setting.calendar.friday" module="ar"/></td>
		    <td  class="info_title_01" height="30" width="100"><font color="red"><!-- 星期六-->
					<ait:message  messageID="display.att.setting.calendar.saturday" module="ar"/></font></td>
		  </tr>
		  <!-- 日期显示 -->
		  <%=factorycalendar!=null?factorycalendar:""%> 
		</table>
		<div id="edit" style='position:absolute; left:0px; top:0px; z-index:1; background-color: #FFFFEC; layer-background-color: #FFFFEC; border: 1px double #000000; overflow: hidden; display: "none";'>
		<table width="200" border="1" align="left" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr>
		      <td style="color: #666666;">
		      <select name="work">
		        <option value="1">工作</option>
		        <option value="0">不工作</option>
		      </select>
		      </td>
		    </tr>
		    <tr>
		      <td style="color: #666666;">
		      <select name="type">
		        <%for(int i=0;i<items.size();i++){
		          ar=(ArCalendarType)items.get(i);
		        %>
		        <option value="<%=ar.getTypeNo()%>"><%=ar.getTypeName()%></option>
		        <%}%>
		      </select>
		      </td>
		      <td style="color: #666666;"><select name="shift">
		      	<c:forEach items="${shiftList}" var="shiftList">
		   			<option value="${shiftList.shift_no}">
		      		<ait:content enContent="${shiftList.shift_En_Name}" zhContent="${shiftList.shift_Name}" koContent="${shiftList.shift_Kor_Name}"/>
		      		</option>
		   		</c:forEach>
		      </select></td>
		    </tr>
		    <tr>
		      <td><input type="button" value="更新" onclick="Edit()"/></td>
		        <td><input type="button" name="Button" value="取消" onClick="editClose()"></td>
		    </tr>
		  </table>
		</div>
		<input type="hidden" name="pk" value="0">
		<input type="hidden" name="hyear" value="<%=year%>">
		<input type="hidden" name="hmonth" value="<%=month%>">
		<a name="flag"></a>
		

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
</form>
</body>
</html>
