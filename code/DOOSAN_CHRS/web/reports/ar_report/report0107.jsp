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
		response.setHeader("Content-Disposition", "attachment; filename=report0107.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String cpnyId = admin.getCpnyId();
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="10" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="10" ><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;加班汇总表</td>
		  </tr>
		  <tr><td colspan="10">&nbsp;</td></tr>
		  <tr align="center">
		  	<td rowspan="1">部门</td>
		  	<td rowspan="1">课别</td>
		  	<td rowspan="1">班别</td>
		  	<td rowspan="1">职号</td>
		  	<td rowspan="1">姓名</td>
		  	<td rowspan="1">分区</td>
		  	
		  	<td rowspan="1">平时<br>加班时数</td>
		  	<td rowspan="1">休息日<br>加班时数</td>
		  	<td rowspan="1">节假日<br>加班时数</td>
		  	<td rowspan="1">总数</td>
		  </tr>
		  
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  	
		  		<tr align="center">	
		  		<c:if test="${arData.EMPID ne NULL or arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">
				  		<% if( "78000000".equals(cpnyId)){ %>
				  		<td>${arData.DEPTNAME}</td>
				  		<% }else{%>
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && arDataList[i.index - 1].DEPTNAME ne arData.DEPTNAME }" >
							<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		<% }%>
				  		
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
						 
						
			    		<td >${arData.SUM_OT_A}</td>
			    		<td >${arData.SUM_OT_B}</td>
			    		<td >${arData.SUM_OT_C}</td>
			    		<td >${arData.SUM_ALL}</td>
		    	</c:if>
				</tr>
		  </c:forEach>
		  
		  <c:forEach items="${arDataList1}" var="arData1" varStatus="j">
		  	
		  		<tr align="center">	
				  		<% if( "78000000".equals(cpnyId)){ %>
				  		<td>${arData1.DEPTNAME}</td>
				  		<% }else{%>
				  		<c:if test="${j.index == 0}" >
				  			<td rowspan="${arData1.DEPT_CNT}">${arData1.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${j.index > 0 && arDataList1[j.index - 1].DEPTNAME ne arData1.DEPTNAME }" >
							<td rowspan="${arData1.DEPT_CNT}">${arData1.DEPTNAME}</td>
				  		</c:if>
				  		<% }%>
				  		
					  	<td>&nbsp;</td>
					  	<td>${arData1.TEAMNAME}</td>
					  	<td colspan="3">组平均小时数</td>
						<td >${arData1.SUM_OT_A}</td>
			    		<td >${arData1.SUM_OT_B}</td>
			    		<td >${arData1.SUM_OT_C}</td>
			    		<td >${arData1.SUM_ALL}</td>
				</tr>
		  </c:forEach>
		  
		  <c:forEach items="${arDataList2}" var="arData2" varStatus="k">
		  	
		  		<tr align="center">	
				  		<% if( "78000000".equals(cpnyId)){ %>
				  		<td>${arData2.DEPTNAME}</td>
				  		<% }else{%>
				  		<c:if test="${k.index == 0}" >
				  			<td rowspan="${arData2.DEPT_CNT}">${arData2.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${k.index > 0 && arDataList2[k.index - 1].DEPTNAME ne arData2.DEPTNAME }" >
							<td rowspan="${arData2.DEPT_CNT}">${arData2.DEPTNAME}</td>
				  		</c:if>
				  		<% }%>
				  			
					  	<td colspan="5">部门平均小时数</td>	
			    		<td >${arData2.SUM_OT_A}</td>
			    		<td >${arData2.SUM_OT_B}</td>
			    		<td >${arData2.SUM_OT_C}</td>
			    		<td >${arData2.SUM_ALL}</td>
				</tr>
		  </c:forEach>
		  
		</table>
	</body>
</html>

