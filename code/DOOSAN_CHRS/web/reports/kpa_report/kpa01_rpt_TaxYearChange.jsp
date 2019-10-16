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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_TaxYearChange.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
		String year = request.getParameter("year");
		System.out.println("year:  "+year);
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.set("YEAR",year) ;		  		
		PaReportService service = new PaReportService() ;				
		List endDataList = new ArrayList() ;
		//年税额_税额变更 kpa01_rpt_TaxYearChange.jsp
		endDataList = service.retrieveKpaTaxYearChangeList(parameterObject) ;
		request.setAttribute("bodyDataList",endDataList) ;	

%>

	<body>

		<table border="1" cellspacing="0" cellpadding="0">
		  <tr align="left">
		  	<td colspan="12" >
		  		&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">※<!-- 调薪所致的税额及补差计算 --><ait:message  messageID="display.pay.massageCalculationSupplement" module="pay"/>
		  		</font>
		  	</td>
		  </tr>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  变更前税内容 --><ait:message  messageID="display.pay.ChangeBeforeTax" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪税   연봉 세금   --><ait:message  messageID="display.pay.korSalaryTax" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖税  성과급 세금 --><ait:message  messageID="display.pay.ResulTax" module="pay"/></td>
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td> 			
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  变更后税内容 --><ait:message  messageID="display.pay.CompenSettl" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪税   연봉 세금   --><ait:message  messageID="display.pay.CompenSettl" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖税  성과급 세금 --><ait:message  messageID="display.pay.ResulTax" module="pay"/></td>
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td> 
		    
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  税额补差清算 세액 소급정산--><ait:message  messageID="display.pay.taxliquidate" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪税差(C=A-B)    --><ait:message  messageID="display.pay.wagesSupplementInfo" module="pay"/></td>
						<td align="center"><!-- 补差税额(￦) --><ait:message  messageID="display.pay.TaxSupplement" module="pay"/>(￦)</td>
						<td align="center"><!-- 补差税额 세액소급 --><ait:message  messageID="display.pay.TaxSupplement" module="pay"/>($)</td>
		    		</tr>
		    	</table>		    
		    </td>	


			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.变更前税总纳税额}</td>
				<td align="center">${oneResult.变更前税成果奖税}</td>
				<td align="center">${oneResult.变更前总纳税额}</td>
				<td align="center">${oneResult.变更后税总纳税额}</td>				
				<td align="center">${oneResult.变更后税成果奖税}</td>
				<td align="center">${oneResult.变更后总纳税额}</td>				
				<td align="center">${oneResult.年薪税差}</td>
				<td align="center">${oneResult.补差税额}</td>
				<td align="center">${oneResult.美元税额补差数}</td>		
					
			</tr>							
			</c:forEach>
	</body>
</html>
