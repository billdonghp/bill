<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.PaReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title></title></head>


 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_Payroll.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 

		  		String year = request.getParameter("paYear") ;
		  		String month = request.getParameter("paMonth");
		  		SimpleMap parameterObject = new SimpleMap() ;
		  		parameterObject.set("DATE",year+month) ;
		  		System.out.println(year+month);		  		
				PaReportService service = new PaReportService() ;				
				List endDataList = new ArrayList() ;
				//Payroll
				endDataList = service.retrievePayrollList(parameterObject) ;
				request.setAttribute("bodyDataList",endDataList) ;	
%>
	<body>

		<table border="1" cellspacing="0" cellpadding="0">
		  <tr align="left">
		  	<td colspan="9" >&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">▣ ▣ <%=year %><!-- 年中国海外住在员工资表--><ait:message  messageID="display.pay.massageSpayroll" module="pay"/>(DICI,DICC,DIY) <%=year %>-<%=month %>	단위 : USD, 원화</font></td>
		  </tr>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center"><!-- 工号 --><ait:message  messageID="display.mutual.emp_id"/></td>
		    <td align="center" colspan="4">	
		    	<table border="1" cellspacing="0" cellpadding="0">
	    		<tr><td align="center" colspan="4"><!-- 年薪内容 --><ait:message  messageID="display.pay.AnnualSalaryContent" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 韩国年薪 --><ait:message  messageID="display.pay.korSalary" module="pay"/>(￦)</td>
						<td align="center"><!-- 年薪税 --><ait:message  messageID="display.pay.korSalaryTax" module="pay"/>(￦)</td>
						<td align="center"><!-- 海外补贴 --><ait:message  messageID="display.pay.OverseasSubsidies" module="pay"/>($)</td>		
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/>($)</td>				
		    		</tr>
		    	</table>		    	   
		    </td>	
		    <td align="center">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center"><!-- 月别支付 --><ait:message  messageID="display.pay.monthToPay" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 月薪 현지 --><ait:message  messageID="display.pay.monthly" module="pay"/>(100%)</td>
		    		</tr>
		    	</table>		    	   
		    </td>			
		   </tr>

			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.工号}</td>
				<td align="center">${oneResult.韩国年薪}</td>
				<td align="center">${oneResult.年薪税}</td>
				<td align="center">${oneResult.海外补贴}</td>				
				<td align="center">${oneResult.合计}</td>
				<td align="center">${oneResult.月薪}</td>				
			</tr>							
			</c:forEach>
	</body>
</html>
