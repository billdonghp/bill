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
		response.setHeader("Content-Disposition", "attachment; filename=report0110.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="${6 + fn:length(arDateList)}" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="${6 + fn:length(arDateList)}" >出勤率</td>
		  </tr>
		  <tr><td colspan="${6 + fn:length(arDateList)}">&nbsp;</td></tr>
		  <tr align="center">
		  	<td rowspan="1">部</td>
		  	<td rowspan="1">课</td>
		  	<td rowspan="1">班</td>
		  	<td rowspan="1">人数</td>
		  	<td rowspan="1">&nbsp;</td>
<%		
		ArReportService service = new ArReportService() ;
		String week[] = {"无","日","一","二","三","四","五","六"} ;
	
		List arDateList = (List)request.getAttribute("arDateList") ;
		for (int i = 0; i < arDateList.size(); ++i)
		{
			Calendar calendar = (Calendar)arDateList.get(i) ;
%>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体"><fmt:formatDate value="<%= calendar.getTime() %>" pattern="d"/><br><%= week[calendar.get(Calendar.DAY_OF_WEEK)] %></td>
<%		}	%>
		<td >TTL</td>
		  </tr>
<%
		List arDataList = (List)request.getAttribute("arDataList") ; 
		
		for (int i = 0 ; i < arDataList.size(); ++i)
		{
			SimpleMap arData = (SimpleMap)arDataList.get(i) ;
			arData.setString("cpnyId",admin.getCpnyId()) ;
			int days = arData.getInt("DAYS");
			request.setAttribute("days",days) ;
%> 
		<tr align="center">	
<%
				if (i == 0 && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
%>
			<td rowspan="<%= arData.getInt("DEPT_CNT") * 2 %>"> <%= arData.getString("DEPTNAME") %></td>
			<td rowspan="<%= arData.getInt("OFFICE_CNT") * 2 %>"> <%= arData.getString("OFFICENAME") %></td>
			
<%				}
				else if(i > 0 && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
					SimpleMap map = (SimpleMap)arDataList.get(i - 1) ;
					
					if (!map.getString("DEPTNAME").equals(arData.getString("DEPTNAME"))){
%>
						<td rowspan="<%= arData.getInt("DEPT_CNT") * 2 %>"><%= StringUtil.checkNull(arData.getString("DEPTNAME")) %></td>		
<%					}
					
					if (!map.getString("OFFICENAME").equals(arData.getString("OFFICENAME"))){
%>				
						<td rowspan="<%= arData.getInt("OFFICE_CNT") * 2 %>"> <%= StringUtil.checkNull(arData.getString("OFFICENAME")) %></td>		
<%					}
				}else if (arData.getString("TEAMNAME").equals("合计")){
%>			  	
				<td>&nbsp;</td>					
<%				}else if (arData.getString("TEAMNAME").equals("总计")){
%>
					<td>&nbsp;</td>
				  	<td>&nbsp;</td>
<%				}		%>
			<td rowspan="2"><%= arData.getString("TEAMNAME") %></td>
			<td rowspan="2"><%= arData.getString("EMP_CNT") %></td>
			<td>
<% 
				if (arData.getString("TEAMNAME").equals("总缺<br>勤率")){
					out.println("请假总数") ;
				}else{
					out.println("请假人数") ;
				}
%>
			</td>
<%	

				List personDataList = new ArrayList() ;
				if (!arData.getString("TEAMNAME").equals("总缺<br>勤率") && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
					arData.setString("sumType","sum") ;
					personDataList = service.retrieveReport0110Data2(arData) ;
				}else if (arData.getString("TEAMNAME").equals("总缺<br>勤率")){
					arData.setString("sumType","sumA") ;
					personDataList = service.retrieveReport0110Data2(arData) ;
				}else if (arData.getString("TEAMNAME").equals("合计")){
					arData.setString("sumType","sumB") ;
					arData.setString("deptId",arData.getString("DEPT_DEPTID")) ;
					personDataList = service.retrieveReport0110Data2(arData) ;
				}else if (arData.getString("TEAMNAME").equals("总计")){
					arData.setString("sumType","sumC") ;
					arData.setString("deptId",request.getParameter("deptId")) ;
					personDataList = service.retrieveReport0110Data2(arData) ;
				}
				
				SimpleDateFormat report0110DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
				int quantityAll = 0 ;
				
				for (int j = 0 ; j < arDateList.size(); ++j)
				{
					Calendar calendar = (Calendar)arDateList.get(j) ;
					String dateStr = report0110DateFormat.format(calendar.getTime()) ;
%>			<td style="font-size:7pt; font-style: normal; font-family: 宋体">	<%
					
					int k = 0 ;
					int quantity = 0 ;
					for (int m = 0 ; m < personDataList.size() ; ++m)
					{
						SimpleMap personData = (SimpleMap)personDataList.get(m) ;
						
						if (dateStr.equals(personData.getString("AR_DATE_STR"))){
							quantity = personData.getInt("PER_CNT") ;
							quantityAll += quantity ;
							++k ;
							break ;
						}
					}
						
					if (k == 0){
						out.println("&nbsp;") ;
					}else{
						out.println(quantity) ;
					}
%>
			</td>
<%				}
%>
			<td style="font-size:7pt; font-style: normal; font-family: 宋体"><%= quantityAll %></td>
			</tr>
			<tr align="center">
<% 				if (arData.getString("TEAMNAME").equals("总缺<br>勤率")){
					out.println("<td>总缺勤率</td>") ;
				}else if (arData.getString("TEAMNAME").equals("合计")){
					out.println("<td>&nbsp;</td><td>出勤率</td>") ;
				}else if (arData.getString("TEAMNAME").equals("总计")){
					out.println("<td>&nbsp;</td><td>&nbsp;</td><td>出勤率</td>") ;
				}else{
					out.println("<td>缺勤率</td>") ;
				}

				for (int j = 0 ; j < arDateList.size(); ++j)
				{
					Calendar calendar = (Calendar)arDateList.get(j) ;
					String dateStr = report0110DateFormat.format(calendar.getTime()) ;
%>			<td style="font-size:7pt; font-style: normal; font-family: 宋体">	<%
					
					int k = 0 ;
					int quantity = 0 ;
					for (int m = 0 ; m < personDataList.size() ; ++m)
					{
						SimpleMap personData = (SimpleMap)personDataList.get(m) ;
						
						if (dateStr.equals(personData.getString("AR_DATE_STR"))){
							quantity = personData.getInt("PER_CNT") ;
							++k ;
							break ;
						}
					}
						
					if (k == 0){
						out.println("&nbsp;") ;
					}else{
						request.setAttribute("empCnt", arData.getInt("EMP_CNT")) ;
						request.setAttribute("perCnt", quantity) ;
						
						if (arData.getString("TEAMNAME").equals("合计") || arData.getString("TEAMNAME").equals("总计")){
%>
							<fmt:formatNumber value="${1 - perCnt/empCnt}" type="percent"/>
<%	
						}else{
%>
							<fmt:formatNumber value="${perCnt/empCnt}" type="percent"/>
<%							
						}

					}
%>
			</td>
<%				}
				request.setAttribute("quantityAll",quantityAll) ;
				if (arData.getString("TEAMNAME").equals("合计") || arData.getString("TEAMNAME").equals("总计")){
%>
					<td style="font-size:7pt; font-style: normal; font-family: 宋体"><fmt:formatNumber value="${1 - quantityAll/empCnt/days}" type="percent"/></td>
<%	
				}else{
%>
					<td style="font-size:7pt; font-style: normal; font-family: 宋体"><fmt:formatNumber value="${quantityAll/empCnt/days}" type="percent"/></td>
<%							
				}
%>			
			</tr>
<%			}		%>
		</table>
	</body>
</html>

