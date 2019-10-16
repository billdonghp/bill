<%@ page contentType="text/html; charset=UTF-8" import="java.util.Date,java.util.GregorianCalendar,com.ait.i18n.MessageSource,com.ait.ar.ArCalc"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.itfc.IfImporter"%>
<%	
	GregorianCalendar currentDay = new GregorianCalendar();
	int month=currentDay.get(java.util.Calendar.MONTH) + 1;
	int year= currentDay.get(java.util.Calendar.YEAR);

	if (request.getParameter("month") != null && request.getParameter("year") != null) {
		try {
			month=Integer.parseInt(request.getParameter("month"));
			year= Integer.parseInt(request.getParameter("year"));
		} catch (Exception e) {
			month=currentDay.get(java.util.Calendar.MONTH);
			year= currentDay.get(java.util.Calendar.YEAR);
		}
	}
%>
<%
 HttpSession session2=request.getSession(true);
 AdminBean admin2=(AdminBean)session2.getAttribute("admin");
 request.setAttribute("cpnyDiff",admin2.getCpnyId());

%> 

<html>
<head>
<ait:processBarJs />

<link href="../css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
  
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/ar_toolbar_total_calc.jsp"%>
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
		<%@ include file="/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
	
		<form name="form1" method="post" action="">
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--月考勤汇总-->
					<ait:message  messageID="display.att.maintenance.calculation.complete" module="ar"/></td>
			</tr>
		  </table>
		<%
			String result = "";
			if ("sync".equals(request.getParameter("flag"))) {
				IfImporter importer = new IfImporter();
					result=importer.start();
			%>
			<script lang="javascript">
			alert('<%= result%>');		
			</script>
			<%
				}
		%>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
				<td height="30"  class="info_title_01" ><!--同步人事数据--><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></td>
				<td class="info_content_01">
					<a href="ar_month_calculate.jsp?menu_code=ar0206&flag=sync"><ait:message  messageID="display.pay.hr.synchronize" module="pay"/></a>
				</td>
			</tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!-- 员工区分 -->员工区分</td>
		      <td width="75%" class="info_content_00"><ait:empDiff  name="statTypeCode"  cpnyDiff="${cpnyDiff}" selected="${param.statTypeCode}"/> </td>
		    </tr>
		    <tr>  
		      <td width="15%" class="info_title_01"><!--考勤月--><ait:message  messageID="display.att.maintenance.calculation.timing" module="ar"/></td>
		      <td width="85%"><select name="year" class="pamonth">
			<%for (int i=-4;i<=4;i++){%>
				<option value="<%=year+i%>" <%=i==0?"selected":""%>><%=year+i%></option>
			<%}%>
		      </select><!--年-->
					<ait:message  messageID="display.mutual.year"/>&nbsp;&nbsp;
					<select name="month" class="pamonth">
		    <%for (int i=1;i<=12;i++){%>
		    	<option value="<%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%>" <%=i==month?"selected":""%>><%=("0"+i).substring(("0"+i).length()-2,("0"+i).length())%></option>
			<%}%>
		      </select></td>
			  <%//未计算
			  HttpSession session1 = request.getSession(true);
			  AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
			  MessageSource messageSource = new MessageSource("ar",admin1.getLocale(), "UTF-8");
			  String outstr=messageSource.getMessage("display.att.maintenance.calculation.unvailable");
			  if (request.getParameter("year")!=null&&request.getParameter("month")!=null){
				  ArCalc arcalc=new ArCalc();
				  outstr=arcalc.CalcMonth(request.getParameter("year")+request.getParameter("month"),request.getParameter("statTypeCode"),admin1,messageSource);
			  }
			  %>
			    
			  
		    </tr>
		    <tr>
		    	<td width="15%" class="info_title_01"><!--计算结果-->
					<ait:message  messageID="display.att.maintenance.calculation.result" module="ar"/></td>
		    	<td width="85%" class="info_content_01"><%=outstr%></td>
		    </tr>
		    <tr>
		    	<td class="info_content_01" colspan="2"><ait:processBar />&nbsp;</td>
		    </tr>
		  </table>
		</form>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:forEach var="i" begin="1" end="8"
				step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
		</table>
		
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

<DIV class=text id=popupcalendar style="top:0px;left:0px; z-index:0"></DIV>
<IFRAME name="hiddenCb"  width=0 height=0 frameborder=0></IFRAME>
<ait:xjos />
</body>
</html>
