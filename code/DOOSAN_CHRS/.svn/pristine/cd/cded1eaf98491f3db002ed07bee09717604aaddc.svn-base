<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0111.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="6" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="6" ><fmt:formatDate value="${arStartDate.time}" pattern="yyyy年M月d日"/> - <fmt:formatDate value="${arEndDate.time}" pattern="yyyy年M月d日"/>	
		  	&nbsp;&nbsp;&nbsp;&nbsp;平均加班时间统计</td>
		  </tr>
		  <tr><td colspan="6" align="right">单位（人数，小时）</td></tr>
		  <tr align="center">
		  	<td rowspan="1">部门</td>
		  	<td rowspan="1">职/组</td>
		  	<td rowspan="1">人员区分</td>
		  	
		  	<td rowspan="1">加班时间</td>
		  	<td rowspan="1">人员</td>
		  	<td rowspan="1">平均</td>
		  </tr>
		  
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  	
		  		<c:if test="${arData.POST_GROUP ne '小计'}">
		  		<tr align="center">	
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="1">${arData.FOURTHDEPTNAME}</td>
				  			<td rowspan="1">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && arDataList[i.index - 1].DEPTNAME ne arData.DEPTNAME }" >
							<td rowspan="1">${arData.FOURTHDEPTNAME}</td>
							<td rowspan="1">${arData.DEPTNAME}</td>
				  		</c:if>

					  	<td>${arData.POST_GROUP}</td>

			    		<td >${arData.OT_SUM}</td>
			    		<td >${arData.OT_PERSON_CNT}</td>
			    		<td >${arData.OT_AVG}</td>
				</tr>
		    	</c:if>
		  </c:forEach>
		  
		</table>
	</body>
</html>

