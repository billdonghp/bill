<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArCalendarType"%>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date"%>
<jsp:useBean id="factory" scope="page" class="com.ait.ar.dao.ArFactoryCalendarBean">
</jsp:useBean>
<jsp:useBean id="calendarType" scope="page" class="com.ait.ar.dao.ArCalendarTypeBean">
</jsp:useBean>
<jsp:useBean id="shift" scope="page" class="com.ait.ar.dao.ArShift010Bean">
</jsp:useBean>
<%
ArCalendarType ar=null;
ArrayList items=calendarType.getCalendarType();
String factorycalendar=null;

ArShift010 shiftObject = null;
ArrayList shiftList = shift.getShift010();

int groupNo=0;
String arMonth=null;
if(request.getParameter("groupNo_")!=null&&request.getParameter("arMonth_")!=null){
  groupNo=Integer.parseInt(request.getParameter("groupNo_"));
  arMonth=request.getParameter("arMonth_");
  factorycalendar=factory.getGroupCalendar(groupNo,arMonth,0);
}

%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">


function editShow(id)
{

  document.form1.pk.value=id;
  var x=event.x;
  var y=event.y;
  document.all.edit.style.top=y-30;
  document.all.edit.style.left=x;
  document.all.edit.style.display="";
}
function Edit()
{
  document.form1.action="/arControlServlet?operation=groupcalendarupdate&menu_code=<%=request.getParameter("menu_code")%>"
  document.form1.submit();
}


function editClose(){
document.all.edit.style.display="none";
}


</script>
</head>
<body>
<%@ include file="../inc/common_toolbar_n.jsp"%>
<form name="form1" method="post">
<table width="100%" border="1" align="left" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期日</font></td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期一</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期二</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期三</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期四</td>
    <td bgcolor="#F5F5F5" height="30" width="100">星期五</td>
    <td bgcolor="#F5F5F5" height="30" width="100"><font color="red">星期六</font></td>
  </tr>
  <%=factorycalendar%>
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

<%
				for(int i=0;i<items.size();i++){
          ar=(ArCalendarType)items.get(i);
%>
        <option value="<%=ar.getTypeNo()%>"><%=ar.getTypeName()%></option>
<%
				}
%>
      </select></td>
      <td>
      <select name="shift">
<%
				for(int i=0;i<shiftList.size();i++){
          shiftObject=(ArShift010)shiftList.get(i);
%>
        <option value="<%=shiftObject.getShift_no()%>"><%=shiftObject.getShift_Name()%></option>
<%
				}
%>
      </select></td>
    </tr>
    <tr>
      <td><input type="button" value="更新" onclick="Edit()"/></td>
      <td><input type="button" name="Button" value="取消" onClick="editClose()"></td>
    </tr>
  </table>
</div>
<input type="hidden" name="pk" value="0">
<input type="hidden" name="groupID" value="<%=groupNo%>">
<input type="hidden" name="arMonth" value="<%=arMonth%>">
<a name="flag"></a>
</form>
</body>
</html>
