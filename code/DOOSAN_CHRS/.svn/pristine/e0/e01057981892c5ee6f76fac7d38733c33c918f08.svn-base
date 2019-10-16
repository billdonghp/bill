<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hr.com.ait.gm.DateBean.bean.Department"/> <!-- data type contained in the dept_list -->
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="annualList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="info" scope="page" class="com.ait.ar.bean.Annual" />
<jsp:useBean id="Current" scope="page" class="java.util.Date" />

<%
admin = (AdminBean)session.getAttribute("admin");
String id  = admin.getAdminID();
String key="";
String[] date=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
String year=date[0].toString();
String month=date[1].toString();
if(request.getParameter("year")!=null){
	year=request.getParameter("year");
	month=request.getParameter("month");
}
if(request.getParameter("key")!=null){
	key=request.getParameter("key");
	key=StringUtil.toCN(key);
}%>
<html>
<head>
<DIV class=text id=popupcalendar style="top:0px;left:0px"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  src="/js/calendarcode.js"></script>
<script language="javascript">
function band(backColor,textColor,i)
{
	var t;
	if(typeof(preEl)!='undefined') {
	preEl.bgColor=orgBColor;
	try{
		ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{
		ChangeTextColor(el,textColor);
	} catch(e) {;}
	preEl = el;
	document.save.empid.value=i;
    document.save.submit();
}

function se() {
	document.form1.submit();
}
function exc() {
	document.form1.action="arcontrolservlet?excel=0";
	document.form1.submit();
}
</script>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/hr/hr_diaoling_toolbar.jsp"%>
<form name="form1" method="post" action="/arControlServlet">
<input type="hidden" name="operation" value="annual" />
<input type="hidden" name="destination" value="annualsearch" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="ess" value="1" />
<input type="hidden" name="key" value="<%=id%>" />
<input type="hidden" name="deptid" value="100000000" />
  <table width="100%" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td colspan="8">年份<select name="year">
			<%for (int i = -4; i <= 4; i++) {%>
			<option value="<%=Integer.parseInt(year)+i%>" <%=i==0?"selected":""%>><%=Integer.parseInt(year)+i%></option>
			<%}%>
        </select><img src="../images/btn/search.gif" width="52" height="19" align="middle" style="cursor:pointer " onClick="se()"></td>
    </tr>
  </table>
</form>
<form name="excel" method="post" action="/arControlServlet">

<input type="hidden" name="operation" value="annual">
<input type="hidden" name="page" value="to_ar0203_excel">

</form>
	<form name="save" method="post" action="/arcontrolservlet">
	<input type="hidden" name="operation" value="annual">
	<input type="hidden" name="destination" value="annualinfo">
	<input type="hidden" name="menu_code" value="<%=menu_code%>">
    <input type="hidden" name="empid" value="0">
    <input type="hidden" name="year" value="<%=year%>">
	<table width="100%" border="1" cellpadding="3" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
      <tr bgcolor="#f5f5f5">
      <td width="112">员工号</td>
      <td width="305">姓名</td>
      <td width="311">当前应有年假</td>
      <td width="311">已用年假</td>
      <td width="311">剩余年假</td>
      </tr>
<%for(int i=0;i<annualList.size();i++){
	info=(Annual)annualList.get(i);%>
	<tr onClick="band('#99CCFF','black','<%=info.getEmpid()%>')">
      <td><%=info.getEmpid()%></td>
      <td><%=info.getEmpname()%></td>
      <td><%=info.getYearDays()%></td>
      <td><%=info.getUseryearDays()%>&nbsp;</td>
      <td><%=info.getLeftyearDays()%>&nbsp;</td>
	</tr>
<%}%>
</table>
</form>
</body>
</html>
