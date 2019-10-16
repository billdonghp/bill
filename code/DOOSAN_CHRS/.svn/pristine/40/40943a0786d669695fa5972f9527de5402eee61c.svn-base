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
		response.setHeader("Content-Disposition", "attachment; filename=report0105.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="${7 + fn:length(arDateList)*3}" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="${7 + fn:length(arDateList)*3}" ><fmt:formatDate value="<%= startDate %>" pattern="yyyy年M月d日"/>~<fmt:formatDate value="<%= endDate %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;加班实绩表</td>
		  </tr>
		  <tr><td colspan="${7 + fn:length(arDateList)*3}">&nbsp;</td></tr>	
		  <tr align="center">
		    <td rowspan="2" colspan="6">部门</td>
		    
		    <c:forEach items="${arDateList}" var="calendar">
		    	<td colspan="3">实际</td>
		    </c:forEach>
		    <td rowspan="3">累计加班</td>
		  </tr>
		  <tr align="center">
		  	<c:forEach items="${arDateList}" var="calendar">
		    	<td colspan="3"> <fmt:formatDate value="${calendar.time}" pattern="yyyy年M月d日"/></td>
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
		    </c:forEach>
		  </tr>
		  
<%
		List arDataList = (List)request.getAttribute("arDataList") ; 
		List arDateList = (List)request.getAttribute("arDateList") ;
		
		for (int i = 0 ; i < arDataList.size(); ++i)
		{
			SimpleMap arData = (SimpleMap)arDataList.get(i) ;
			
			if (arData.getString("EMPID") != null || arData.getString("TEAMNAME").equals("小计") || arData.getString("TEAMNAME").equals("合计"))
			{
%>
		<tr align="center">	
<%
				if (i == 0){
%>
			<td rowspan="<%= arData.getString("DEPT_CNT") %>"> <%= arData.getString("DEPTNAME") %></td>		
<%				}
				else if(i > 0){
					SimpleMap map = (SimpleMap)arDataList.get(i - 1) ;
					
					if (!map.getString("DEPTNAME").equals(arData.getString("DEPTNAME"))){
%>
						<td rowspan="<%= arData.getString("DEPT_CNT") %>"><%= StringUtil.checkNull(arData.getString("DEPTNAME")) %></td>		
<%					}
				}

				if (!arData.getString("OFFICENAME").equals(arData.getString("TEAMNAME"))){
%>
						<td>&nbsp;</td>
						<td><%= arData.getString("TEAMNAME") %></td>
<%				}
				else{
%>
					  	<td><%= arData.getString("OFFICENAME") %></td>
					  	<td>&nbsp;</td>
<%				}
%>
			<td ><%= StringUtil.checkNull(arData.getString("EMPID")) %></td>
<%
				if (!arData.getString("TEAMNAME").equals("小计") && !arData.getString("TEAMNAME").equals("合计")){
%>
					<td><%= arData.getString("CHINESENAME") %></td>
					<td><%= arData.getString("POST_GRADE") %></td>
<%				}
				else{
%>
					<td>&nbsp;</td>
				  	<td>&nbsp;</td>
<%				}
				
				for (int j = 0 ; j < arDateList.size(); ++j)
				{
					Calendar calendar = (Calendar)arDateList.get(j) ;
					SimpleDateFormat report0105DateFormat = new SimpleDateFormat("yyyyMMdd") ;
					String dateStr = report0105DateFormat.format(calendar.getTime()) ;

					if (arData.getString("TEAMNAME").equals("小计") || arData.getString("TEAMNAME").equals("合计")){
%>
						<td>&nbsp;</td>
				  		<td>&nbsp;</td>
<% 					}
					else{
%>
						<td >&nbsp;<%= StringUtil.checkNull(arData.getString(dateStr + "_FROM_TIME")) %>&nbsp;</td>
						<td >&nbsp;<%= StringUtil.checkNull(arData.getString(dateStr + "_TO_TIME")) %>&nbsp;</td>
<% 					}
%>
					<td ><%= arData.getString(dateStr + "_QUANTITY") %></td>
<%				}
%>
				<td ><%= arData.getString("SUM_ALL") %></td>
<%			}
		}
%>
		</table>
	</body>
</html>

