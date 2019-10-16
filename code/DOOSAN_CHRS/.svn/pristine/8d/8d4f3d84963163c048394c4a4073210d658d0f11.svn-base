<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.reports.reportservices.ArReportService" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0106.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="${6 + fn:length(arDateList)*4}" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="${6 + fn:length(arDateList)*4}" ><fmt:formatDate value="<%= startDate %>" pattern="yyyy年M月d日"/>~<fmt:formatDate value="<%= endDate %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;加班计划</td>
		  </tr>
		  <tr><td colspan="${6 + fn:length(arDateList)*4}">&nbsp;</td></tr>	
		  <tr align="center">
		    <td rowspan="2" colspan="6">部门</td>
		    
		    <c:forEach items="${arDateList}" var="calendar">
		    	<td colspan="4">今日计划</td>
		    </c:forEach>
		    
		  </tr>
		  <tr align="center">
		  	<c:forEach items="${arDateList}" var="calendar">
		    	<td colspan="4"> <fmt:formatDate value="${calendar.time}" pattern="yyyy年M月d日"/></td>
		    </c:forEach>
		  </tr>
		  <tr align="center">
		  	<td>部门</td>
		  	<td>课别</td>
		  	<td>班别</td>
		  	<td>职号</td>
		  	<td>姓名</td>
		  	<td>分区</td>
		  	<c:forEach items="${arDateList}" var="calendar">
		    	<td >开始时间</td>
		    	<td >结束时间</td>
		    	<td >时间</td>
		    	<td >加班内容</td>
		    </c:forEach>
		  </tr>
		  
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  	
		  		<c:if test="${arData.EMPID ne NULL or arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">
		  		<tr align="center">
				  		
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && arDataList[i.index - 1].DEPTNAME ne arData.DEPTNAME }" >
							<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		
					  	<c:if test="${arData.OFFICENAME ne arData.TEAMNAME}">  	
					  	<td>&nbsp;</td>
					  	<td>${arData.TEAMNAME}</td>
					  	</c:if>
					  	
					  	<c:if test="${arData.OFFICENAME eq arData.TEAMNAME}"> 
					  	<td>${arData.OFFICENAME}</td>
					  	<td>&nbsp;</td>
					  	</c:if>
					  	
					  	<td>${arData.EMPID}</td>
					  	
					  	<c:if test="${arData.TEAMNAME ne '小计' and arData.TEAMNAME ne '合计'}">  	
						  	<td>${arData.CHINESENAME}</td>
						  	<td>${arData.POST_GRADE}</td>
						</c:if>
						
						<c:if test="${arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">  	
						  	<td>&nbsp;</td>
						  	<td>&nbsp;</td>
						</c:if>
						  	
				  		<c:forEach items="${arDateList}" var="calendar">
				    		<td >&nbsp;</td>
				    		<td >&nbsp;</td>
				    		<td >&nbsp;</td>
				    		<td >&nbsp;</td>
				    	</c:forEach>
				</tr>
		    	</c:if>
		  </c:forEach>
		  
		</table>
	</body>
</html>

