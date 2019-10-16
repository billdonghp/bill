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
<head><title>职位别起点工资统计表</title></head>


 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=pa_position_wage.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>

	<body>
		<table>
		  <tr align="center">
		  	<td colspan="11" ></td>
		  </tr>
		</table>
		<table border="1" align="center">
		  <tr align="left">
		  	<td colspan="11" >&nbsp;&nbsp;&nbsp;&nbsp;DICC职位别起点工资统计表</td>
		  </tr>
		</table>  
		<table border="1" cellspacing="0" cellpadding="0">
		  <tr>
		    <td align="center">区 分</td>
		    <td align="center">职位</td>
		    <td align="center">基本工资</td>
		    <td align="center">职务工资</td>
		    <td align="center">住房补助</td>
		    <td align="center">月合计A</td>
		    <td align="center">年工资B</td>
		    <td align="center">年奖金(640%)C</td>
		    <td align="center">年人均收入D=B+C </td>
		    <td align="center">年加班费36H*12</td>
		    <td align="center">年收入(含加班)</td>
		  </tr>
<%
		  		String year = request.getParameter("year") ;
		  		SimpleMap parameterObject = new SimpleMap() ;
		  		parameterObject.set("YEAR",year) ;
		  		parameterObject.set("cpnyId",admin.getCpnyId()) ;
		  		
		  		String[] empType = new String[]{"职员","工人","工会主席"} ;
				PaReportService service = new PaReportService() ;
				
				List endDataList = new ArrayList() ;
				int endSize = 0 ;
				endDataList = service.retrievePaPositionWageList(parameterObject) ;
				request.setAttribute("endDataList",endDataList) ;
				
				List bodyDataList = new ArrayList() ;
				int bodySize = 0 ;
				for (int i = 0 ; i < empType.length; i ++ )
				{
					parameterObject.set("EMP_TYPE",empType[i]) ;
					bodyDataList = service.retrievePaPositionWageList(parameterObject) ;
					bodySize = bodyDataList.size() ;
					
					request.setAttribute("parameterObject",parameterObject) ;
					request.setAttribute("bodyDataList",bodyDataList) ;
					request.setAttribute("bodySize",bodySize) ;
%>
			<c:forEach items="${bodyDataList}" var="oneResult" varStatus="i">
			<tr>
				<c:if test="${i.index == 0}"><td align="center" rowspan="${bodySize}">${parameterObject.EMP_TYPE}</td></c:if>
				<td align="center">${oneResult.POST}</td>
				<td align="center">${oneResult.WAGE_ARG}</td>
				<td align="center">${oneResult.DUTIES_ALLOWANCE_ARG}</td>
				<td align="center">${oneResult.RESIDENTIAL_GRANTS_ARG}</td>
				<td align="center">${oneResult.MONTH_AGE}</td>
				
				<td align="center">${oneResult.YEAR_SUM}</td>
				<td align="center">${oneResult.BONUS_SUM}</td>
				
				<td align="center">${oneResult.YEAR_WAGE}</td>
				<td align="center">${oneResult.YEAR_OT}</td>
				<td align="center">${oneResult.TOTAL}</td>
			</tr>							
			</c:forEach>
<%					
	
				}
%>			
			<c:forEach items="${endDataList}" var="oneResult" varStatus="i">
			<tr>
				<td align="center" colspan="2">合计</td>
				<td align="center">${oneResult.WAGE_ARG}</td>
				<td align="center">${oneResult.DUTIES_ALLOWANCE_ARG}</td>
				<td align="center">${oneResult.RESIDENTIAL_GRANTS_ARG}</td>
				<td align="center">${oneResult.MONTH_AGE}</td>
				
				<td align="center">${oneResult.YEAR_SUM}</td>
				<td align="center">${oneResult.BONUS_SUM}</td>
				
				<td align="center">${oneResult.YEAR_WAGE}</td>
				<td align="center">${oneResult.YEAR_OT}</td>
				<td align="center">${oneResult.TOTAL}</td>
			</tr>							
			</c:forEach>
		</table>
		
	</body>
</html>

