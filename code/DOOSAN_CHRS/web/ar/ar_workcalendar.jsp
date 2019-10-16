<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="com.ait.ar.bean.CalendarDay"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ar.bean.ArShift010"%>
<jsp:useBean id="employee" scope="request" class="com.ait.hrm.bean.BasicInfo" />
<jsp:useBean id="yearMonth" scope="request" class="java.util.GregorianCalendar" />
<jsp:useBean id="arShiftList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="calendarList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="calendarDay" scope="request" class="com.ait.ar.bean.CalendarDay" />
<jsp:useBean id="date" scope="page" class="java.util.GregorianCalendar" />
<jsp:useBean id="arShift" scope="page" class="com.ait.ar.bean.ArShift010" />
<html>
<head>
<title>员工工作日历表</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<script language="javascript">
var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
	    $('person_id').value = "" ;

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-80;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('empID').value=cell.childNodes[0].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		
		layerClose();
}



function CheckAll() {
	if (document.form1.days) {
		if (document.form1.days[0]) {
			for (i=0;i<document.form1.days.length;i++)
				document.form1.days[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.days.checked = document.form1.checkAll.checked;
		}
	}
}
function Add() {
	//"本页面没有添加功能"
	alert("本页面没有添加功能");
}
function Update() {
	document.form1.action="/arControlServlet?operation=ar_modify&content=empCalendar&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.submit();
}
function Delete() {
	//"确认删除选定的数据?"
	if(confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>')) {
	
		document.form1.action="/arControlServlet?operation=ar_delete&content=empCalendar&menu_code=<%=request.getParameter("menu_code")%>";
		document.form1.submit();
	}
}
function Search() {
	if(document.form1.empID.value == null || document.form1.empID.value.length == 0)
	{
		alert("请输入工号!!!") ;
		document.form1.empID.focus() ;
		return ;
	}

	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=ec_v&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.submit();
}
</script>
</head>
<body>

<%
HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session.getAttribute("admin");
String dayTypes[] = (String[]) request.getAttribute("dayTypes");
String calendarTypes[] = (String[]) request.getAttribute("calendarTypes");
int year = yearMonth.get(java.util.GregorianCalendar.YEAR);
int month = yearMonth.get(java.util.GregorianCalendar.MONTH)+1;
Logger.getLogger(getClass()).debug("month : " + String.valueOf(month));

%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
			
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
		<form name="form1" method="post" action="" >
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--个人日历-->
					<ait:message  messageID="display.att.maintenance.personal_calendar.personal_calendar" module="ar"/></td>
			</tr>
		  </table>
		  <table width="100%" border="1"  cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center">
		      <td class="info_content_01" nowrap="nowrap">
		        <%if(admin1.getLanguagePreference().equals("zh")){%>
					<%=employee.getChineseName() %>
				<%}else if(admin1.getLanguagePreference().equals("ko")){%>
					<%=employee.getKoreanName() %>
				<%}else{%>
					<%=employee.getPinyinName() %>
				<%} %>
		      
		      </td>
		      <td class="info_content_00" nowrap="nowrap">
		      <input onKeyUp="SearchEmp(this.value,this.id)" id="empID" name="empID" type="text" value="<%=employee.getEmpID() %>" style="width:90px;" />
		      <input type="hidden" name="person_id" id="person_id" value="<%=employee.getPersonId() %>">
		      </td>
		      <td class="info_content_01" nowrap="nowrap"><!--考勤月-->
					<font size="3"><ait:message  messageID="display.att.maintenance.personal_calendar.timing" module="ar"/>(请注意查询的月份)</font></td>
		      <td class="info_content_00" nowrap="nowrap"><select name="year" >
					<%for (int i = -4; i <= 4; i++) {%>
					<option value="<%=year+i%>"<%=i==0?" selected":""%>><%=year + i%></option>
					<%}%>
				</select>&nbsp;<!--年-->
					<ait:message  messageID="display.mutual.year"/>&nbsp;<select name="month" >
					<%for (int i = 1; i <= 12; i++) {%>
					<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>"<%=i==month?" selected":""%>><%=("0"+i).substring(("0"+ i).length()-2,("0"+i).length())%></option>
					<%}%>
				</select>&nbsp;<!--月-->
					<ait:message  messageID="display.mutual.month"/>
			  </td>
		      <td class="info_content_01" nowrap="nowrap">
		      <ait:image src="../images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search()" style="cursor:hand" />
		      <td class="info_content_00" nowrap="nowrap"><!--全选-->
					<ait:message  messageID="display.mutual.select_2"/><input type="checkbox" name="checkAll" onClick="CheckAll();" class="check"></td>
		    </tr>
		  </table>
		  <table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><font color="red"><!-- 星期日-->
					<ait:message  messageID="display.att.setting.calendar.sunday" module="ar"/></font></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期一-->
					<ait:message  messageID="display.att.setting.calendar.monday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期二-->
					<ait:message  messageID="display.att.setting.calendar.tuesday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期三-->
					<ait:message  messageID="display.att.setting.calendar.wednesday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期四-->
					<ait:message  messageID="display.att.setting.calendar.thursday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><!-- 星期五-->
					<ait:message  messageID="display.att.setting.calendar.friday" module="ar"/></td>
		      <td class="info_title_01" height="30" width="<%=100/7%>%"><font color="red"><!-- 星期六-->
					<ait:message  messageID="display.att.setting.calendar.saturday" module="ar"/></font></td>
		    </tr>
		<%
		if (calendarList.size()>0) {
			calendarDay = (CalendarDay) calendarList.get(0);
			int daysBefore = calendarDay.getCalendarDay().get(java.util.Calendar.DAY_OF_WEEK)-1;
			calendarDay = (CalendarDay) calendarList.get(calendarList.size()-1);
			int daysAfter = 7 - calendarDay.getCalendarDay().get(java.util.Calendar.DAY_OF_WEEK);
			for (int i=0;i<calendarList.size();i++) {
				calendarDay = (CalendarDay) calendarList.get(i);
				date = calendarDay.getCalendarDay();
				String yearStr = String.valueOf(date.get(java.util.Calendar.YEAR));
				String monthStr = ("0"+String.valueOf(date.get(java.util.Calendar.MONTH)+1)).substring(("0"+String.valueOf(date.get(java.util.Calendar.MONTH)+1)).length()-2,("0"+String.valueOf(date.get(java.util.Calendar.MONTH)+1)).length());
				String dayStr = ("0"+date.get(java.util.Calendar.DAY_OF_MONTH)).substring(("0"+date.get(java.util.Calendar.DAY_OF_MONTH)).length()-2,("0"+date.get(java.util.Calendar.DAY_OF_MONTH)).length());
				String dateStr = yearStr + "/" + monthStr + "/" + dayStr;
				if (date.get(java.util.Calendar.DAY_OF_WEEK)==1 || daysBefore>0) {%>
					<tr align="center">
				<%}
				for (;daysBefore>0;daysBefore--){%>
					<td height="30" width="100">&nbsp;</td>
				<%}%>
				<td height="30" width="120" align="center">
					<table border="0" cellspacing="0" cellpadding="1" width="100%">
						<tr><td align="center" width="20%">
							<%if (calendarDay.getDayTypeId() != 1) {%><font color="red"><%}%>
							<b><%=date.get(java.util.Calendar.DAY_OF_MONTH) %></b>
							<%if (calendarDay.getDayTypeId() != 1) {%></font><%}%></td>
						<td align="center" width="40%">
							<%=dayTypes[calendarDay.getDayTypeId()-1] %></td>
						<td align="center" width="40%">
							<%=calendarTypes[calendarDay.getCalendarType()]%></td></tr>
						<tr><td align="center">
							<input type="checkbox" name="days" value="<%=dateStr%>" class="check"/></td>
						<td align="center" colspan="2">
						<select name="shifts_<%=dateStr%>" style="width:120;" onChange="document.form1.days[<%=i%>].checked=true;">
						<%for (int j=0;j<arShiftList.size();j++) {
							arShift = (ArShift010) arShiftList.get(j); %>
							<option value="<%=arShift.getShift_no()%>"<%=arShift.getShift_no() == calendarDay.getShiftNo()?" selected":""%>>
							<%if(admin1.getLanguagePreference().equals("zh")){%>
								<%=arShift.getShift_Name()%>
							<%}else if(admin1.getLanguagePreference().equals("ko")){%>
								<%=arShift.getShift_Kor_Name()%>
							<%}else{%>
								<%=arShift.getShift_En_Name()%>
							<%} %>
							
							</option>
							<%}%></select></td>
					</table>
				</td>
				<%if (date.get(java.util.Calendar.DAY_OF_WEEK)==7){%>
					</tr>
				<%}%>
			<%}
			if (daysAfter > 0) {
				for (;daysAfter>0;daysAfter--){%>
					<td height="30" width="100">&nbsp;</td>
				<%}%>
				</tr>
			<%}
		} else {%>
			<tr align="center">
				<td height="30" width="100%" colspan="7" align="center"><font color="red">
				<ait:message messageID="alert.att.no_calendar" module="ar"></ait:message></font></td>
			</tr>
		<%}%>
		</table>           
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td class="info_content_01"><font size="5">说明:&nbsp;工-工作,&nbsp休-休息,&nbsp节-节假日&nbsp;&nbsp;&nbsp;&nbsp;公-公司班次,&nbsp班-个人班次</font></td>
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

	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>
</body>
</html>