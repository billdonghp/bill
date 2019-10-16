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
		response.setHeader("Content-Disposition", "attachment; filename=report0109.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="${9 + fn:length(arDateList)}" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="${9 + fn:length(arDateList)}" >加班明细</td>
		  </tr>
		  <tr><td colspan="${9 + fn:length(arDateList)}">&nbsp;</td></tr>
		  <tr align="center">
		  	<td rowspan="1">部</td>
		  	<td rowspan="1">课</td>
		  	<td rowspan="1">班</td>
		  	<td rowspan="1">职号</td>
		  	<td rowspan="1">姓名</td>
<%		
		ArReportService service = new ArReportService() ;
		String week[] = {"无","日","一","二","三","四","五","六"} ;
	
		List arDateList = (List)request.getAttribute("arDateList") ;
		for (int i = 0; i < arDateList.size(); ++i)
		{
			Calendar calendar = (Calendar)arDateList.get(i) ;
%>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体"><fmt:formatDate value="<%= calendar.getTime() %>" pattern="yyyy年M月d日"/><br><%= week[calendar.get(Calendar.DAY_OF_WEEK)] %></td>
<%		}	%>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体">总加<br>班</td>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体">平<br>日</td>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体">休息<br>日</td>
		<td style="font-size:7pt; font-style: normal; font-family: 宋体">节假<br>日</td>
		  </tr>
<%
		List arDataList = (List)request.getAttribute("arDataList") ; 
		
		for (int i = 0 ; i < arDataList.size(); ++i)
		{
			SimpleMap arData = (SimpleMap)arDataList.get(i) ;
			arData.setString("cpnyId",admin.getCpnyId()) ;
			
			if (arData.getString("EMPID") != null || arData.getString("TEAMNAME").equals("小计") || arData.getString("TEAMNAME").equals("合计"))
			{
%>
		<tr align="center">	
<%
				if (i == 0 && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
%>
			<td rowspan="<%= arData.getInt("DEPT_CNT") %>"> <%= arData.getString("DEPTNAME") %></td>
			<td rowspan="<%= arData.getString("OFFICE_CNT") %>"> <%= arData.getString("OFFICENAME") %></td>
			
<%				}
				else if(i > 0 && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
					SimpleMap map = (SimpleMap)arDataList.get(i - 1) ;
					
					if (!map.getString("DEPTNAME").equals(arData.getString("DEPTNAME"))){
%>
						<td rowspan="<%= arData.getString("DEPT_CNT") %>"><%= StringUtil.checkNull(arData.getString("DEPTNAME")) %></td>		
<%					}
					
					if (!map.getString("OFFICENAME").equals(arData.getString("OFFICENAME"))){
%>				
						<td rowspan="<%= arData.getString("OFFICE_CNT") %>"> <%= StringUtil.checkNull(arData.getString("OFFICENAME")) %></td>		
<%					}
				}else if (arData.getString("TEAMNAME").equals("合计")){
%>			  	
				<td>&nbsp;</td>					
<%				}else if (arData.getString("TEAMNAME").equals("总计")){
%>
					<td>&nbsp;</td>
				  	<td>&nbsp;</td>
<%				}		%>
			<td><%= arData.getString("TEAMNAME") %></td>
<%
				if (!arData.getString("TEAMNAME").equals("小计") && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
%>
					<td ><%= StringUtil.checkNull(arData.getString("EMPID")) %></td>
					<td ><%= StringUtil.checkNull(arData.getString("CHINESENAME")) %></td>
<%				}
				else{
%>
					<td>&nbsp;</td>
				  	<td>&nbsp;</td>
<%				}
                String dateStarted = (String)request.getAttribute("dateStarted") ;
                String dateEnd = (String)request.getAttribute("dateEnd") ;
                arData.setString("dateStarted",dateStarted);
                arData.setString("dateEnd",dateEnd);
                
				List personDataList = new ArrayList() ;
				
				if (!arData.getString("TEAMNAME").equals("小计") && !arData.getString("TEAMNAME").equals("合计") && !arData.getString("TEAMNAME").equals("总计")){
					personDataList = service.retrieveReport0109Data2(arData) ;
				}
				else if (arData.getString("TEAMNAME").equals("小计")){
					arData.setString("sumType","sumA") ;
					personDataList = service.retrieveReport0109Data2(arData) ;
				}else if (arData.getString("TEAMNAME").equals("合计")){
					arData.setString("sumType","sumB") ;
					arData.setString("deptId",arData.getString("DEPT_DEPTID")) ;
					personDataList = service.retrieveReport0109Data2(arData) ;
				}else if (arData.getString("TEAMNAME").equals("总计")){
					arData.setString("sumType","sumC") ;
					arData.setString("deptId",request.getParameter("deptId")) ;
					personDataList = service.retrieveReport0109Data2(arData) ;
				}
				
				SimpleDateFormat report0109DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
				float otAll = 0 ;
				float otA = 0 ;
				float otB = 0 ;
				float otC = 0 ;
				int itemNo = 0 ;
				for (int j = 0 ; j < arDateList.size(); ++j)
				{
					Calendar calendar = (Calendar)arDateList.get(j) ;
					String dateStr = report0109DateFormat.format(calendar.getTime()) ;
%>			<td style="font-size:7pt; font-style: normal; font-family: 宋体">	<%
					
					int k = 0 ;
					String itemName = "" ;
					float quantity = 0 ;
					for (int m = 0 ; m < personDataList.size() ; ++m)
					{
						SimpleMap personData = (SimpleMap)personDataList.get(m) ;
						
						itemNo = personData.getInt("ITEM_NO") ;
						if (dateStr.equals(personData.getString("AR_DATE_STR"))){
							
							itemName += personData.getString("ITEM_NAME") ;
							quantity += personData.getFloat("QUANTITY") ;
							++k ;
							
							otAll += personData.getFloat("QUANTITY") ;
							if (itemNo == 31){
								otB += personData.getFloat("QUANTITY") ;
							}else if (itemNo == 32){
								otC += personData.getFloat("QUANTITY") ;
							}else{
								otA += personData.getFloat("QUANTITY") ;
							}
						}
					}
						
					if (k == 0){
						out.println("&nbsp;") ;
					}else{
						out.println("加班<br>" + quantity) ;
					}
%>
			</td>
<%				}
%>
			<td><%= otAll %></td>
			<td><%= otA %></td>
			<td><%= otB %></td>
			<td><%= otC %></td>
			</tr> 
<%			}
		}
%>

		</table>
	</body>
</html>

