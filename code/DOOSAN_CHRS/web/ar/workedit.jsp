<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="workcalendar" scope="page" class="com.ait.ar.dao.ArWorkCalendarBean">
</jsp:useBean>
<%
String empid=request.getParameter("empid");
String month=request.getParameter("date");
int day=Integer.parseInt(request.getParameter("day"));
//String menu_code=request.getParameter("menu_code");
String edit=workcalendar.getEditDiv(day,month,empid);



%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/changeDate.js"></script>
<script language="javascript" src="../js/changeTime.js"></script>
<script language="javascript">
function editSubmit(formNo,formid)
{
	var c=0;
	var mx=0;
	var sk=0;
	var temp;
	var cNo="";
	var mxNo="";
	var skNo="";
	for(var i=0;i<document.form1.elements.length;i++)
	{

		if(document.form1.elements[i].name.indexOf("spaceHours_c")!=-1)
		{
			c=parseInt(parseInt(c)+1);
			temp=document.form1.elements[i].name;
			temp=temp.substring(12,temp.length);
			cNo+=temp+"-";
		}
		if(document.form1.elements[i].name.indexOf("spaceHours_mx")!=-1)
		{
			mx=parseInt(parseInt(mx)+1);
			temp=document.form1.elements[i].name;
			temp=temp.substring(13,temp.length);
			mxNo+=temp+"-";
		}
		if(document.form1.elements[i].name.indexOf("spaceHours_sk")!=-1)
		{
			sk=parseInt(parseInt(sk)+1);
			temp=document.form1.elements[i].name;
			temp=temp.substring(13,temp.length);
			skNo+=temp+"-";
		}
		if(document.form1.elements[i].name.indexOf("fromspaceDay_c")!=-1)
		{
			if(document.form1.elements[i].value=="")
			{
				alert("开始日期不对 无法更新");
				return;
			}
		}
		if(document.form1.elements[i].name.indexOf("tospaceDay_c")!=-1)
		{
			if(document.form1.elements[i].value=="")
			{
				alert("结束日期不对 无法更新");
				return ;
			}
		}
	}
	var empID=<%=empid%>;
	var monthstr=<%=month%>;
	document.form1.action="/arControlServlet?operation=workcalendarupdate&menu_code=<%=request.getParameter("menu_code")%>&cNo="+cNo+"&mxNo="+mxNo+"&skNo="+skNo+"&empIDs="+empID+"&monthstr="+monthstr;

    document.form1.submit();
}
function closeE()
{
document.form1.action="workcalendar.jsp?date=<%=month%>&empID=<%=empid%>&menu_code=<%=request.getParameter("menu_code")%>#flag";
document.form1.submit();
}

</script>
</head>
<body>
<form name="form1" method="post" target="_parent">
  <table width="500"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><%=edit%></td>
    </tr>
    <tr>
      <td></td>
    </tr>
  </table>
</form>
</body>
</html>
