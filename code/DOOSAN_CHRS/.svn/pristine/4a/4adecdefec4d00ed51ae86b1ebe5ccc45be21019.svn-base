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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_SupplementPayTax.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 

		String year = request.getParameter("paYear");
		System.out.println("year:  "+year);
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.set("YEAR",year) ;		  		
		PaReportService service = new PaReportService() ;				
		List endDataList = new ArrayList() ;
		//调薪所致的税额及补差计算
		endDataList = service.retrieveKpaAmountList(parameterObject) ;
		request.setAttribute("bodyDataList",endDataList) ;	

%>

	<body>
 
		<table border="1" cellspacing="0" cellpadding="0">、
		  	<td colspan="23" >
		  		&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">※<!-- 调薪所致的税额及补差计算 --><ait:message  messageID="display.pay.massageCalculationSupplement" module="pay"/>
		  		</font>
		  	</td>		
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center" colspan="3">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  变更前所得内容 --><ait:message  messageID="display.pay.ChangeBeforeFrom" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪  연봉  --><ait:message  messageID="display.pay.korSalary" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖 성과급 --><ait:message  messageID="display.pay.Resul" module="pay"/></td>
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    		</tr>
		    	</table>		    	   
		    </td>			
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
		    		<tr><td align="center" colspan="3"><!--  变更后所得内容  --><ait:message  messageID="display.pay.ChangeAfterTax" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪  연봉  --><ait:message  messageID="display.pay.korSalary" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖  성과급 --><ait:message  messageID="display.pay.Resul" module="pay"/></td>
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td>	
		    <td align="center" colspan="3">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!--  变更后税内容 --><ait:message  messageID="display.pay.ChangeAfterTax" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 年薪税   연봉 세금   --><ait:message  messageID="display.pay.korSalaryTax" module="pay"/>(￦)</td>
						<td align="center"><!-- 成果奖税  성과급 세금 --><ait:message  messageID="display.pay.ResulTax" module="pay"/></td>
						<td align="center"><!-- 合计 --><ait:message  messageID="display.pay.total" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td> 		    	    
		    <td align="center" colspan="2">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="2"><!-- 海外补贴变更内容($) --><ait:message  messageID="display.pay.OverseasSubsidiesChange" module="pay"/>($)</td></tr>
		    		<tr>
						<td align="center"><!--  变更前  --><ait:message  messageID="display.pay.ChangeBefore" module="pay"/></td>
						<td align="center"><!-- 变更后 --><ait:message  messageID="display.pay.ChangeAfter" module="pay"/></td>
		    		</tr>
		    	</table>		    
		    </td>
			<td align="center" colspan="5">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="5"><!-- 补偿清算	 --><ait:message  messageID="display.pay.CompenSettl" module="pay"/></td></tr>
		    		<tr>
						<td align="center"><!-- 工资(￦) --><ait:message  messageID="display.pay.wages" module="pay"/>(￦)</td>
						<td align="center"><!-- 未缴税额(￦) --><ait:message  messageID="display.pay.UnpaidTax" module="pay"/>(￦)</td>
						<td align="center"><!--  工资补偿额(￦)  --><ait:message  messageID="display.pay.wagesCompens" module="pay"/>(￦)</td>
						<td align="center"><!-- 海外补贴($) --><ait:message  messageID="display.pay.OverseasSubsidies" module="pay"/>($)</td>
						<td align="center"><!--  补偿额合计($)  --><ait:message  messageID="display.pay.Total_amount_compens" module="pay"/>($)</td>					
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
				<td align="center">${oneResult.变更前所得成果奖}</td>
				<td align="center">${oneResult.变更前所得合计}</td>
				<td align="center">${oneResult.变更前税总纳税额}</td>				
				<td align="center">${oneResult.变更前税成果奖税}</td>
				<td align="center">${oneResult.变更前税合计}</td>				
				<td align="center">${oneResult.变更后所得韩国年薪}</td>
				<td align="center">${oneResult.变更后所得成果奖}</td>
				<td align="center">${oneResult.变更后所得合计}</td>				
				<td align="center">${oneResult.变更后税总纳税额}</td>
				<td align="center">${oneResult.变更后税成果奖税}</td>				
				<td align="center">${oneResult.变更后税合计}</td>
				<td align="center">${oneResult.变更前海外补贴}</td>				
				<td align="center">${oneResult.变更后海外补贴}</td>
				<td align="center">${oneResult.月固定定工资补差}</td>
				<td align="center">${oneResult.未缴税额}</td>				
				<td align="center">${oneResult.工资补差额}</td>		
				<td align="center">${oneResult.海外补贴补差额}</td>
				<td align="center">${oneResult.补差合计}</td>				
				<td align="center">${oneResult.备注}</td>						
			</tr>							
			</c:forEach>
	</body>
</html>
