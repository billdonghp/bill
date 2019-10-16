<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArDetailStat"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ page import="com.ait.hr.bean.Department"%>
<%@ page import="com.ait.hr.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date"%>
<jsp:useBean id="arstat" scope="page" class="com.ait.ar.dao.ArDetailStatBean">
</jsp:useBean>
<jsp:useBean id="hr" scope="page" class="com.ait.hr.dao.DeptDAO">
</jsp:useBean>
<%
ArrayList clos=null;
ArrayList ps=null;
ArrayList emps=null;
ArDetailStat ar=null;
ArDetailStat idar=null;
int year=0;
int month=0;
String deptID=null;
if(request.getParameter("year_")!=null&&request.getParameter("month_")!=null)
{
year=Integer.parseInt(request.getParameter("year_"));
month=Integer.parseInt(request.getParameter("month_"));

deptID=request.getParameter("deptid");
clos=arstat.getClos(year,month);//得到列
ps=arstat.getDetailStat(deptID,year,month);
}
Date d=new Date();
String dates=d.toLocaleString();
dates=dates.substring(0,dates.indexOf(" "));
String[] ds=dates.split("-");
year=Integer.parseInt(ds[0].toString());
month=Integer.parseInt(ds[1].toString());



HttpSession session1 = request.getSession(false);
AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
String adminID  = admin1.getAdminID();
List dept_list = hr.getSupervisedDeptList(adminID);
Department de=null;
%>
<html>
<head>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function EditClos(year,month,day)
{
	if(!confirm("确定要统计"+year+"年"+month+"月"+day+"日所有员工的考勤状态吗?"))
  {
    return;
  }
  document.form1.action="editardetaistatservlet?type=clos&year_="+year+"&month_="+month+"&day_="+day;
  document.form1.submit();
}
function EditEmpID(year,month,empid)
{
  if(!confirm("确定要统计员工"+empid+" "+year+"年"+month+"月的考勤状态吗?"))
  {
    return;
  }
  document.form1.action="editardetaistatservlet?type=empID&year_="+year+"&month_="+month+"&empid="+empid;
  document.form1.submit();
}
function EditDay(year,month,day,empid)
{
  document.form1.action="editardetaistatservlet?type=day&year_="+year+"&month_="+month+"&day_="+day+"&empid="+empid;
  document.form1.submit();
}


function Search()
{
if(isNaN(document.form1.year.value)||document.form1.year.value=="")
{
	alert("年份必须为数值");
	return;
}
if(document.form1.year.value.length!=4)
{
	alert("请输入4位数年份");
	return ;
}
var year=document.form1.year.value;
if(isNaN(document.form1.month.value)||document.form1.month.value=="")
{
	alert("月份必须为数值");
	return;
}
var month=document.form1.month.value;
if(document.form1.deptid.value=="")
return;
document.form1.action="ar_detail_stat.jsp?menu_code=<%=request.getParameter("menu_code")%>&year_="+year+"&month_="+month;

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
</script>
</head>
<body>
  <%@ include file="../inc/common_toolbar_n.jsp"%>
<form name="form1" method="post" action="">
  <table width="100%" border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td colspan="<%if(clos!=null){%><%=clos.size()+2%><%}else{%>20<%}%>"><script language="javascript">outyear()</script>
        年
        <script language="javascript">outmonth()</script>
        月
        <select name="deptid" onChange="Search()">
				<option value="">请选择部门</option>
					<%


					for ( int i = 0 ; i < dept_list.size() ; i++ )
					{
						de = (Department) dept_list.get(i);
						if (de.getDeptID()==null){ break;}
					%>
				<option value="<%=de.getDeptID()%>">

                                  <%
                                  int level=de.getDeptLevel();
                                  if(level==0){
                                    out.print(de.getDeptName());
                                  }if(level==1){
                                    out.print("&nbsp;&nbsp;&nbsp;"+de.getDeptName());
                                  }if(level==2){
                                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+de.getDeptName());
                                  }if(level==3){
                                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+de.getDeptName());
                                  }
                                  %>
                                </option>
					<%}%>
			</select></td>
    </tr>
	<%if(ps!=null){%>
    <tr bgcolor="#F5F5F5" align="center">
      <td>工号</td>
      <%for(int i=0;i<clos.size();i++){
      %>
      <td><a href="javascript:EditClos('<%=year%>','<%=month%>','<%=(String)clos.get(i)%>')"><%=(String)clos.get(i)%></a></td>
      <%}%>
      <td>&nbsp;</td>
    </tr>
    <%for(int i=0;i<ps.size();i++){//总几人
    emps=(ArrayList)ps.get(i);//每个人
    %>
    <tr>
      <%idar=(ArDetailStat)emps.get(0);%>
      <td><%=idar.getEmpID()%></td>
      <%for(int j=0;j<emps.size();j++){//每个人的状态
        ar=(ArDetailStat)emps.get(j);
      %>
      <%if(ar.getStat()==1){%>
      <td><img alt="未统计" src="../images/btn/stat0.gif" style="cursor:pointer " onClick="EditDay('<%=year%>','<%=month%>','<%=ar.getDay()%>','<%=ar.getEmpID()%>')"/></td>
      <%}else{%>
      <td><img alt="已统计" src="../images/btn/stat1.gif"></td>
      <%}%>
      <%}%>
      <td><a href="javascript:EditEmpID('<%=year%>','<%=month%>','<%=ar.getEmpID()%>')">更新</a></td>
    </tr>
    <%}}%>
  </table>
 <input type="hidden" name="hmenu_code" value="<%=menu_code%>">
</form>
</body>
</html>
