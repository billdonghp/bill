<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArFactoryCalendar"%>
<%@ page import="java.util.*" %>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArFactoryCalendarBean">
</jsp:useBean>
<%
ArFactoryCalendar info=null;
ArrayList list=dao.getGroupList();
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function Add()
{
	document.form1.action='/ar/groupcalendar_a.jsp?menu_code=<%=request.getParameter("menu_code")%>#flag';
	document.form1.submit();
}
function Update()
{
	  if(document.form1.pkNo.value==0||document.form1.pkDate.value==0)
	  {
	    alert("清选择更新项目");
	    return;
	  }
  
	var groupNo=document.form1.pkNo.value;
	var arMonth=document.form1.pkDate.value;
	document.form1.action="/ar/groupcalendar_m.jsp?groupNo_="+groupNo+"&arMonth_="+arMonth+"&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.submit();
}
function band(backColor,textColor,i,d)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.pkNo.value=i;
        document.form1.pkDate.value=d;
}

function Delete()
{
  if(document.form1.pkNo.value==0||document.form1.pkDate.value==0)
  {
    alert("清选择删除项目");
    return;
  }
  if(!confirm("确定要删除吗?"))
  {
    return;
  }
  document.form1.action="/arControlServlet?operation=groupcalendardel&menu_code=<%=request.getParameter("menu_code")%>";
  document.form1.submit();
}

</script>
</head>
<body>
<%@ include file="/inc/common_toolbar.jsp"%>
<form name="form1" method="post">
<table width="100%" border="1" align="left" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
  	<td height="30" bgcolor="#F5F5F5">动态组名称</td>
    <td height="30" bgcolor="#F5F5F5">动态组日期</td>
  </tr>
<%
	if(list != null && list.size()>0){
    for(int i=0;i<list.size();i++){
      info=(ArFactoryCalendar)list.get(i);
%>
  <tr align="center" onClick="band('#99CCFF','black',<%=info.getGroupNo()%>,<%=info.getArMonth()%>)">
    <td height="30" width="100"><%=info.getGroupName()%></td>
    <td height="30" width="100"><%=info.getArMonth()%></td>
    </tr>
    <%}
  }%>
</table>

<input type="hidden" name="pkNo" value="0">
<input type="hidden" name="pkDate" value="0"/>

<a name="flag"></a>
</form>
</body>
</html>
