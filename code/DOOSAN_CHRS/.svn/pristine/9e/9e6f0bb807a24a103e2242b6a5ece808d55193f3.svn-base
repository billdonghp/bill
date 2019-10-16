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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_SupplementAnnualSalaryAdjustment.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		String make="不包含成果奖";
			
		String year = request.getParameter("year");
		System.out.println("year:  "+year);
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.set("YEAR",year) ;		  		
		PaReportService service = new PaReportService() ;				
		List endDataList = new ArrayList() ;
		//调薪所致的税额及补差计算
		//endDataList = service.retrieveKpaAmountList(parameterObject) ;
		endDataList = service.retrieveKpaAnnualAdjustmentList(parameterObject) ;
		request.setAttribute("bodyDataList",endDataList) ;	

%>

	<body>

		<table border="1" cellspacing="0" cellpadding="0">
		  	<td colspan="11" >
		  		&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">※<!-- 调薪所致的补差计算 --><ait:message  messageID="display.pay.massageCalculationSupplementAnnualSalaryAdjustment" module="pay"/>
		  		</font>
		  	</td>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center" colspan="2">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!--  年薪 연봉 --><ait:message  messageID="display.pay.view.annual.annual_pay" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 变更前 변경 전--><ait:message  messageID="display.pay.ChangeBefore" module="pay"/></td>
						<td align="center"><!-- 变更后 변경 후--><ait:message  messageID="display.pay.ChangeAfter" module="pay"/></td>
		    		</tr>
		    	</table>		    	   
		    </td>			
		    <td align="center" colspan="2">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!--  税额 --><ait:message  messageID="display.pay.taxSupplement" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 变更前 변경 전--><ait:message  messageID="display.pay.ChangeBefore" module="pay"/></td>
						<td align="center"><!-- 变更后 변경 후--><ait:message  messageID="display.pay.ChangeAfter" module="pay"/></td>		    		</tr>
		    	</table>		    
		    </td> 
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  补差清算  --><ait:message  messageID="display.pay.CompenSettl" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪  연봉  --><ait:message  messageID="display.pay.view.annual.annual_pay" module="pay"/></td>
						<td align="center"><!-- 未缴税额 --><ait:message  messageID="display.pay.UnpaidTax" module="pay"/></td>
						<td align="center"><!-- 补差金额 --><ait:message  messageID="display.pay.wagesSupplement" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td>	
		    <td align="center">
				<!-- 备注 -->
				<ait:message  messageID="display.mutual.notes"/>					    
		    </td>		    
		  </tr>

			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.变更前所得韩国年薪}</td>
				<td align="center">${oneResult.变更后所得韩国年薪}</td>
				<td align="center">${oneResult.变更前税总纳税额}</td>
				<td align="center">${oneResult.变更后税总纳税额}</td>	
							
				<td align="center">${oneResult.年薪}</td>
				<td align="center">${oneResult.未缴税额}</td>				
				<td align="center">${oneResult.补差金额}</td>		
				<td align="center">${oneResult.备注}</td>						
			</tr>							
			</c:forEach>
	</body>
</html>
