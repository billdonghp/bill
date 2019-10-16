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
		response.setHeader("Content-Disposition", "attachment; filename=kpa01_rpt_achievement.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>
<%
		  		String year = request.getParameter("year") ;
		  		SimpleMap parameterObject = new SimpleMap() ;
		  		parameterObject.set("YEAR",year) ;		  		
				PaReportService service = new PaReportService() ;				
				List endDataList = new ArrayList() ;
				endDataList = service.retrieveAchievementList(parameterObject) ;
				request.setAttribute("bodyDataList",endDataList) ;	
%>
	<body>

		<table border="1" cellspacing="0" cellpadding="0">
		  <tr align="left">
		  	<td colspan="9" >&nbsp;&nbsp;&nbsp;&nbsp;<font size="5">▣ ▣ <%=year %><!-- 年中国海外住在员工资表 --><ait:message  messageID="display.pay.massageSpayroll" module="pay"/>(DICI,DICC,DIY) 	단위 : USD, 원화</font></td>
		  </tr>
		  <tr>
		    <td align="center"><!-- 区分 --><ait:message  messageID="display.mutual.no"/></td>
		    <td align="center"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td align="center"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td align="center" colspan="1">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center"><%=year%><!-- 成果奖 성과급 --><ait:message  messageID="display.pay.Resul" module="pay"/>(A)</td></tr>
		    		<tr>
						<td align="center"><!-- 成果奖 성과급 --><ait:message  messageID="display.pay.Resul" module="pay"/></td>						
		    		</tr>
		    	</table>		    	   
		    </td>	
		    <td align="center" colspan="3">	
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan="3"><!-- 税 --><ait:message  messageID="display.pay.taxSupplement" module="pay"/>(￦)</td></tr>
		    		<tr>
						<td align="center"><!--总税(成果)--> <ait:message  messageID="display.pay.taxTotal" module="pay"/>(B)</td>
						<td align="center"><!--年薪税(不成果)--><ait:message  messageID="display.pay.korSalaryTax" module="pay"/>(C)</td>
						<td align="center"><!--成果奖税--><ait:message  messageID="display.pay.ResulTax" module="pay"/>(D=B-C)</td>
		    		</tr>
		    	</table>		    	   
		    </td>			
		    <td align="center" colspan="2">
		    	<table border="1" cellspacing="0" cellpadding="0">
		    		<tr><td align="center" colspan=2"><!--实发成果奖--><ait:message  messageID="display.pay.ActualResul" module="pay"/>(E=A-D)</td></tr>
		    		<tr>
						<td align="center">(￦)</td>
						<td align="center">($)</td>
		    		</tr>
		    	</table>		    
		    </td>

		  </tr>

			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center">${oneResult.RANK}</td>
				<td align="center">${oneResult.职位}</td>
				<td align="center">${oneResult.姓名}</td>
				<td align="center">${oneResult.成果奖}</td>
				<td align="center">${oneResult.总纳税额含成果奖}</td>
				<td align="center">${oneResult.成果奖税}</td>
				<td align="center">${oneResult.年薪税}</td>				
				<td align="center">${oneResult.实发成果奖1}</td>
				<td align="center">${oneResult.实发成果奖2}</td>				
			</tr>							
			</c:forEach>
	</body>
</html>
