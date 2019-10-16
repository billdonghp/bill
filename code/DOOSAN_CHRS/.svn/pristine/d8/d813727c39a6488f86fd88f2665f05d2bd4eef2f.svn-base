<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<jsp:useBean id="EMSTotalExcelList" class="java.util.ArrayList" scope="request"/>
<%@ page import="com.ait.util.*,java.util.Calendar,java.text.ParseException,java.util.Date,java.text.SimpleDateFormat,com.ait.util.StringUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
Calendar startdate = Calendar.getInstance();
Date d = new Date();
SimpleDateFormat  sdf =  new SimpleDateFormat("yyyy-MM-dd");
try {
	d=sdf.parse(StringUtil.checkNull(request.getParameter("startdate")));
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
startdate.setTime(d);
Calendar enddate = Calendar.getInstance();
Date d1 = new Date();
try {
	d1=sdf.parse(StringUtil.checkNull(request.getParameter("enddate")));
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
enddate.setTime(d1);
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=EMSTotalExcel.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="50%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center" style="FONT-FAMILY:宋体">
	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="5"><b><font style="FONT-FAMILY:宋体;font-size:14pt;"><%=startdate.get(Calendar.YEAR)%>年<%=startdate.get(Calendar.MONTH)+1%>月<%=startdate.get(Calendar.DATE)%>日--<%=enddate.get(Calendar.YEAR)%>年<%=enddate.get(Calendar.MONTH)+1%>月<%=enddate.get(Calendar.DATE)%>日 快件月结算报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="50%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="FONT-FAMILY:宋体">
		    <tr align="center" bgcolor="#F5F5F5">
			  <td align="center" rowspan="2">
				部门</td>
			  <td align="center" rowspan="2">
				件数</td>
			 <td align="center" colspan="3">
				费用</td>			   	 	     
		    </tr>
		    <tr align="center">
		      <td align="center" >
				分摊费</td>    
			  <td align="center" >
				邮件费 </td>
			  <td align="center" >
				合计 </td>		
		    </tr>
		 <c:forEach items="${EMSTotalExcelList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td align="center" >${varTest.DEPTNAME}</td>
		      <td align="center" >${varTest.SHARES} </td>
		      <td align="center" >${varTest.COSTSHARING} </td>
		      <td align="center" >${varTest.COSTS}</td>	
		      <td align="center" >${varTest.SUMCOSTS}</td>		     	     
			 </tr>	
		</c:forEach>	
		 </table>
		</td>
	</tr>
</table>
</body>
</html>