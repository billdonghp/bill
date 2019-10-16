<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.hr.bean.*,com.ait.ar.bean.*,java.util.*,com.ait.util.*,java.util.Date,org.apache.log4j.Logger"%>
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hr.com.ait.gm.DateBean.bean.Department"/> <!-- data type contained in the dept_list -->
<jsp:useBean id="basic" class="com.ait.hr.entity.BasicInfo" scope="request"/> <!-- basic information of the selected employee -->
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="code_id" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="code_name" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="emprollList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="check" scope="page" class="java.lang.String" />
<jsp:useBean id="info" scope="page" class="com.ait.ar.bean.EmpRoll" />
<jsp:useBean id="Current" scope="page" class="java.util.Date" />
<%
String id  = admin.getAdminID();
Logger.getLogger(getClass()).debug(id);
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
}
%>
<html>
<head>
<DIV class=text id=popupcalendar style="top:0px;left:0px"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<LINK href="../css/calendar.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript"  src="/js/calendarcode.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
function se(){
	document.form1.submit();
}
</script>
</head>

<body>

<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/hr/hr_diaoling_toolbar.jsp"%>
<form name="form1" method="post" action="/arControlServlet">
<input type="hidden" name="operation" value="emproll">
<input type="hidden" name="destination" value="emprollinfo">
<input type="hidden" name="menu_code" value="<%=menu_code%>">
<input type="hidden" name="ess" value="1">
<input type="hidden" name="empid" value="<%=id%>">
<input type="hidden" name="deptid" value="100000000">
  <table width="100%" border="1" cellpadding="5" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td colspan="8"><select name="year">
			<%for (int i = -4; i <= 4; i++) {%>
			<option value="<%=Integer.parseInt(year)+i%>" <%=i==0?"selected":""%>><%=Integer.parseInt(year)+i%></option>
			<%}%>
        </select>年<select name="month">
		<%for (int i = 1; i <= 12; i++) {%>
			<option	value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==Integer.parseInt(month)?"selected":""%>><%=("0" + i).substring(("0" + i).length() - 2, ("0" + i).length())%></option>
		<%}%>
        </select>月<img src="../images/btn/search.gif" width="52" height="19" align="absmiddle" style="cursor:pointer " onClick="se()"></td>
    </tr>
  </table>

</form>
<form name="form2" method="post" action="arcontrolservlet">
<input type="hidden" name="operation" value="emproll">
<input type="hidden" name="destination" value="emprolladd">
<input type="hidden" name="menu_code" value="<%=menu_code%>">
<input type="hidden" name="str" value="0">
<input type="hidden" name="empid" value="<%=id%>">
<input type="hidden" name="year" value="<%=year%>">
<input type="hidden" name="month" value="<%=month%>">
  <%if(emprollList.size()>0){%>
  <table width="100%" border="1" cellpadding="3" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
	<td colspan="4"><h1><%=year%>年<%=month%>月<%=id%>考勤状态列表</h1></td>
	</tr>
	<tr bgcolor="#f5f5f5">
      <td width="136">日期</td>
      <td width="251">出勤情况(小时)</td>
      <td width="117">迟到(小时)</td>
      <td width="119">早退(小时)</td>
    </tr>
    <%for(int i=0;i<emprollList.size();i++){
      info=(EmpRoll)emprollList.get(i);
    %>
    <tr>
      <td><%=year%>-<%=month%>-<%if(i<9){%>0<%=i+1%><%}else{%><%=i+1%><%}%>&nbsp;</td>
      <td><%=info.getTypeName()==null?"":info.getTypeName()%>&nbsp;<%=info.getLeave_time()==null?"":info.getLeave_time()%>&nbsp;<font color="red"><%=info.getLEAVE_REASON()==null?"":info.getLEAVE_REASON()%></font></td>
      <td><%=info.getLATE_TIME()==null?"":info.getLATE_TIME()%>&nbsp;</td>
      <td><%=info.getEARLY_TIME()==null?"":info.getEARLY_TIME()%>&nbsp;</td>
    </tr>
    <%}%>
  </table>
  <%}%>
</form>
</body>
</html>
