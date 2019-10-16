<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0124.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		Date startMonth = new SimpleDateFormat("yyyyMM").parse(request.getParameter("yearMonthStart")) ;
		Date endMonth = new SimpleDateFormat("yyyyMM").parse(request.getParameter("yearMonthEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="${5 + fn:length(arMonthList)*3}" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	  <td colspan="${5 + fn:length(arMonthList)*3}" ><fmt:formatDate value="<%= startMonth %>" pattern="yyyy年M月"/>~<fmt:formatDate value="<%= endMonth %>" pattern="yyyy年M月"/>&nbsp;&nbsp;&nbsp;&nbsp;个人别加班情况表</td>
		 
		  </tr>
		  <tr><td colspan="${5 + fn:length(arMonthList)*3}">&nbsp;</td></tr>	
		  <tr align="center">
		    <td  colspan="5">&nbsp;</td>
		    
		    <c:forEach items="${arMonthList}" var="calendar">
		    	<td colspan="3"> <fmt:formatDate value="${calendar.time}" pattern="yyyy年M月"/></td>
		    </c:forEach>
		    
		  </tr>
		  
		 
		  <tr align="center">
		  	<td>部门</td>
		  	<td>课/组</td>
		  	<td>职号</td>
		  	<td>姓名</td>
		  	<td>职级</td>
		  	<c:forEach items="${arMonthList}" var="calendar">
		    	<td >平日加班</td>
		    	<td >休息日加班</td>
		    	<td >节假日加班</td>
		    </c:forEach>
		  </tr>
		 <%
		List arDataList = (List)request.getAttribute("arDataList") ; 
		List arMonthList = (List)request.getAttribute("arMonthList") ;
		
		for (int i = 0 ; i < arDataList.size(); ++i)
		{
			SimpleMap arData = (SimpleMap)arDataList.get(i) ;
			
			if (arData.getString("EMPID") != null )
			{
%>
		<tr align="center">	

            <td ><%= StringUtil.checkNull(arData.getString("OFFICENAME")) %></td>
            <td ><%= StringUtil.checkNull(arData.getString("DEPTNAME")) %></td>
			<td ><%= StringUtil.checkNull(arData.getString("EMPID")) %></td>
			<td><%= arData.getString("CHINESENAME") %></td>
		    <td><%= arData.getString("POST_GROUP_CODE") %></td>
<%

				for (int j = 0 ; j < arMonthList.size(); ++j)
				{
					Calendar calendar = (Calendar)arMonthList.get(j) ;
					SimpleDateFormat report0124DateFormat = new SimpleDateFormat("yyyyMM") ;
					String dateStr = report0124DateFormat.format(calendar.getTime()) ;

%>
						<td ><%= StringUtil.checkNull(arData.getString(dateStr + "_WEEKDAY")) %></td>
						<td ><%= StringUtil.checkNull(arData.getString(dateStr + "_WEEKEND")) %></td>
					     <td ><%= StringUtil.checkNull(arData.getString(dateStr + "_HOLIDAY"))%></td>
<%				}
%>

<%			}
		}
%> 

		</table>
	</body>
</html>

