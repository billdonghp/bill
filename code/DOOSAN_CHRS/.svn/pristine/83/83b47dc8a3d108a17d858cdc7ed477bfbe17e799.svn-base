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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_IndividualsWage.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 

		  		String year = request.getParameter("paYear") ;
		  		String month = request.getParameter("paMonth");
		  		SimpleMap parameterObject = new SimpleMap() ;
		  		parameterObject.set("DATE",year+month) ;
		  		System.out.println(year+month);		  		
				PaReportService service = new PaReportService() ;				
				List endDataList = new ArrayList() ;
				endDataList = service.retrieveIndividualsWageList(parameterObject) ;
				request.setAttribute("bodyDataList",endDataList) ;	
%>
	<body>

		<table border="1" cellspacing="0" cellpadding="0">
		  <tr align="left">
		  	<td colspan="20" >&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">▣ ▣ <%=year %>- <%=month %><!-- 月个人别工资 --><ait:message  messageID="display.pay.monthlySingleSalary" module="pay"/>(DICC) </font></td>
		  </tr>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center"><!-- 工号 --><ait:message  messageID="display.mutual.emp_id"/></td>
		    <td align="center"><%=year %>.<%=month %><!-- 月固定工资 --><ait:message  messageID="display.pay.monthlyFixSalary" module="pay"/>(USD)</td>
		    <td align="center" colspan="2">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!-- 其它支付 1--><ait:message  messageID="display.pay.otherPayments1" module="pay"/></td></tr>
		    		<tr>
						<td align="center">(￦)</td>
						<td align="center">(USD)</td>			
		    		</tr>
		    	</table>		    	   
		    </td>	
		    <td align="center" colspan="2">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!-- 其它支付 2--><ait:message  messageID="display.pay.otherPayments2" module="pay"/></td></tr>
		    		<tr>
						<td align="center">(￦)</td>
						<td align="center">(USD)</td>			
		    		</tr>
		    	</table>		    	   
		    </td>	
			<td align="center"><!-- 小记 --><ait:message  messageID="display.pay.notes" module="pay"/>(A)(USD)</td>
		    <td align="center" colspan="2">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!-- 公司负担保险 --><ait:message  messageID="display.pay.insuranceCompanies" module="pay"/>(B)</td></tr>
		    		<tr>
						<td align="center">(￦)</td>
						<td align="center">(USD)</td>			
		    		</tr>
		    	</table>		    	   
		    </td>	
			<td align="center"><!-- 总计--><ait:message  messageID="display.pay.sum" module="pay"/>(A+B)(USD)</td>
		    <td align="center" colspan="6">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="6"><!-- 个人负担保险 --><ait:message  messageID="display.pay.insurancePersonal" module="pay"/>(C )</td></tr> 					
		    		<tr>
						<td align="center"><!-- 健康保险=건강보험 --><ait:message  messageID="display.pay.healthInsurance" module="pay"/></td>
						<td align="center"><!-- 健康(医疗)保险 건강(료양)보험 --><ait:message  messageID="display.pay.healthInsuranceMedical" module="pay"/></td>
						<td align="center"><!-- 国民保险=건강보험 --><ait:message  messageID="display.pay.nationalInsurance" module="pay"/></td>		
						<td align="center"><!-- 其他=기타 --><ait:message  messageID="display.pay.other" module="pay"/></td>
						<td align="center"><!-- 记=계 --><ait:message  messageID="display.pay.hutchison" module="pay"/>(￦)</td>	
						<td align="center"><!-- 记=계 --><ait:message  messageID="display.pay.hutchison" module="pay"/>(USD)</td>	
		    		</tr>
		    	</table>		    	   
		    </td>					
		    <td align="center"><!-- 实发月薪=개인지급계 --><ait:message  messageID="display.pay.monthSalaryActual" module="pay"/>(A-C)</td>
		    <td align="center"><!-- 备注=비고 --><ait:message  messageID="display.mutual.notes"/></td>
			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.工号}</td>
				<td align="center">${oneResult.月固定工资}</td>
				<td align="center">${oneResult.其它支付1W}</td>
				<td align="center">${oneResult.其它支付1U}</td>	
				<td align="center">${oneResult.其它支付2W}</td>
				<td align="center">${oneResult.其它支付2U}</td>				
				<td align="center">${oneResult.小记}</td>
				<td align="center">${oneResult.公司负担保险W}</td>	
				<td align="center">${oneResult.公司负担保险U}</td>
				<td align="center">${oneResult.总计}</td>	
				<td align="center">${oneResult.个人负担健康保险}</td>
				<td align="center">${oneResult.健康医疗保险}</td>
				<td align="center">${oneResult.个人负担国民保险}</td>	
				<td align="center">${oneResult.个人负担其它保险}</td>
				<td align="center">${oneResult.KOREA}</td>
				<td align="center">${oneResult.USA}</td>			
				<td align="center">${oneResult.实发月薪}</td>	
				<td align="center"></td>	
			</tr>							
			</c:forEach>
	</body>
</html>
