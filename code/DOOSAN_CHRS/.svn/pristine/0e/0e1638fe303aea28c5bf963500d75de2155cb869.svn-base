<%@ page contentType="text/html; charset=UTF-8" import="java.util.Date,java.util.Calendar,java.util.GregorianCalendar,com.ait.ar.ArCalc,com.ait.sy.bean.AdminBean"%>
<%
	GregorianCalendar currentDay = new GregorianCalendar();
	int month=currentDay.get(Calendar.MONTH);
	int year= currentDay.get(Calendar.YEAR); 

	if (request.getParameter("month") != null && request.getParameter("year") != null) {
		try {
			month=Integer.parseInt(request.getParameter("month"));
			year= Integer.parseInt(request.getParameter("year"));
		} catch (Exception e) {
			month=currentDay.get(Calendar.MONTH);
			year= currentDay.get(Calendar.YEAR);
		}
	}
	AdminBean admin1 = (AdminBean)session.getAttribute("admin");
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>

<body>
  <%@ include file="/inc/common_toolbar.jsp"%>
<form name="form1" method="post" action="">
  <table width="90%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td width="63" align="center">考勤月</td>
      <td width="125" align="center"><select name="year" class="pamonth">
	<%for (int i=-4;i<=4;i++){%>
		<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
	<%}%>
      </select>年&nbsp;&nbsp;<select name="month" class="pamonth">
    <%for (int i=1;i<=12;i++){%>
    	<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
	<%}%>
      </select></td>
	  <td align="center" width="63"><span style="cursor:pointer;" onClick="javascript:if(confirm('开始计算?')){document.form1.submit();}">进行计算</span></td>
	  <%
	  String outstr="未计算";
	  if (request.getParameter("year")!=null&&request.getParameter("month")!=null){
		  ArCalc arcalc=new ArCalc();
		  outstr=arcalc.CalcProcess(request.getParameter("year")+request.getParameter("month"),admin1.getAdminID());
	  }
	  %> 
	    <td align="center"><%=outstr%></td>
    </tr>
  </table>
</form>
<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
</body>
</html>
