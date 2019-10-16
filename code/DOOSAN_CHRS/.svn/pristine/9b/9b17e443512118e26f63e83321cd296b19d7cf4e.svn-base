<%@ page contentType="text/html; charset=UTF-8"  import="java.util.*,com.ait.sqlmap.util.*,com.ait.util.StringUtil"%>
<jsp:useBean id="reportDAO" scope="page" class="com.ait.web.ReportDAO"/>
<jsp:useBean id="arServices" scope="page" class="com.ait.ar.business.ArServices"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><ait:message messageID="report.mutual.title.detailed_statement" module="report"/></title>
</head>                              
<body>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=detailStatisticsReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
		
		SimpleMap parameterObject = (SimpleMap)request.getAttribute("parameterObject") ;
    	List itemList = (List)request.getAttribute("itemList") ;
		
     %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr align="center" height="60">
    <td colspan="2">             
	    <table width="100%">  
	    	<tr>
	    		<td align="center" colspan="<%= itemList.size() + 4 %>"><b><font size="+2">${title}</font></b></td>
	    	</tr>
	    </table>    	
    </td>
  </tr>
  
  <tr>
    <td>
    	<table width="100%" border="1"align="center" cellpadding="0" cellspacing="0"style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
			<tr align="center">
			 	 <td ><!-- 工号 --><ait:message messageID="report.global.title.empID" module="report"></ait:message></td>       
		         <td ><!-- 姓名 --><ait:message messageID="report.global.title.name" module="report"></ait:message></td>
		         <td ><!-- 部门 --><ait:message messageID="report.global.title.deptName" module="report"></ait:message></td>
		         <td ><!-- 员工类别 --><ait:message messageID="report.mutual.title.staff_class" module="report"></ait:message></td>
	         	 <c:forEach items="${itemList}" var="item">
			      	<td ><ait:content enContent="${item.ITEM_EN_NAME}" zhContent="${item.ITEM_NAME}"/>(${item.UNIT})</td>
			     </c:forEach>
	        </tr>
	        
	   <% 
	        	String sqlContent_sum = reportDAO.getDetailStatisticsSql(itemList,"sum") ;
	        	String sqlContent_count = reportDAO.getDetailStatisticsSql(itemList,"count") ;
	        	parameterObject.setString("sqlContent_sum",sqlContent_sum) ;
	        	parameterObject.setString("sqlContent_count",sqlContent_count) ;
	        	
	        	List endDataList = arServices.retrieveArDetailStatisticsData_end(parameterObject) ;       	
	        	List deptList = reportDAO.getDeptByParent_dept_id(parameterObject.getString("deptID")) ;
	        	
	        	for (int k = 0 ; k < deptList.size() ; k ++)
	        	{
	        		String deptid = deptList.get(k).toString() ;
	        		parameterObject.setString("deptID",deptid) ;
	        	
		        	List list = arServices.retrieveArDetailStatisticsData(parameterObject) ;
		        	for (int i = 0 ; i < list.size() ; i ++)
		        	{
		        		SimpleMap dataMap = (SimpleMap)list.get(i) ;
		        		if (dataMap.getString("EMPID").equals("totalA"))
		        		{
		%>
		        <tr align="center" >       
			         <td  colspan="2"><!-- 考勤状态小计 --><ait:message messageID="report.mutual.title.hours_subtotal" module="report"/></td>
			         <td ><ait:content enContent="<%= StringUtil.checkNull(dataMap.getString("DEPT_EN_NAME")) %>" zhContent="<%= StringUtil.checkNull(dataMap.getString("DEPARTMENT")) %>"/></td>
			         <td > </td>
	    <% 
		        		}
		        		else if (dataMap.getString("EMPID").equals("totalB"))
		        		{
		 %>
		        <tr align="center" >       
			         <td  colspan="2"><!-- 考勤人数小计 --><ait:message messageID="report.mutual.title.people_subtotal" module="report"/></td>
			         <td ><%= dataMap.getString("CHINESENAME") %><!-- 人 --><ait:message messageID="report.global.title.person" module="report"/></td>
			         <td > </td>
		 <%			
		        		}
		        		else
		        		{
	     %>
	   	        <tr align="center">       
	   		         <td ><%= dataMap.getString("EMPID") %>&nbsp;</td>
	   		         <td ><ait:content enContent="<%= StringUtil.checkNull(dataMap.getString("CHINESE_PINYIN")) %>" zhContent="<%= StringUtil.checkNull(dataMap.getString("CHINESENAME")) %>"/></td>
	   		         <td ><ait:content enContent="<%= StringUtil.checkNull(dataMap.getString("DEPT_EN_NAME")) %>" zhContent="<%= StringUtil.checkNull(dataMap.getString("DEPARTMENT")) %>"/></td>
	   		         <td ><ait:content enContent="<%= StringUtil.checkNull(dataMap.getString("JOIN_EN_TYPE")) %>" zhContent="<%= StringUtil.checkNull(dataMap.getString("JOIN_TYPE")) %>"/></td>
	     <% 			
		        		}
		        		
		        		for (int j = 0 ; j < itemList.size() ; j ++)
		        		{
		        			SimpleMap itemMap = (SimpleMap)itemList.get(j) ;
		 %>			
				      	<td ><%= StringUtil.checkNull(dataMap.getString(itemMap.getString("ITEM_ID")),"0") %></td>
		 <%
		        		}
		 %>
		        </tr>
		 <%
		        	}
	        	}

	        	for (int m = 0 ; m < endDataList.size() ; m ++)
	        	{
	        		SimpleMap endDataMap = (SimpleMap)endDataList.get(m) ;
	        		if (endDataMap.getString("TOTAL").equals("totalA"))
	        		{
	%>
	        <tr align="center" >       
		         <td  colspan="3"><!-- 考勤状态合计 --><ait:message messageID="report.mutual.title.total_hours" module="report"/></td>
		         <td ></td>
    <% 
	        		}
	        		else if (endDataMap.getString("TOTAL").equals("totalB"))
	        		{
	 %>
	        <tr align="center" >       
		         <td  colspan="2"><!-- 考勤人数合计 --><ait:message messageID="report.mutual.title.total_people" module="report"/></td>
		         <td ><%= endDataMap.getString("EMP_COUNT") %>&nbsp;<!-- 人 --><ait:message messageID="report.global.title.person" module="report"/></td>
		         <td ></td>
	 <%			
	        		}  		
	        		for (int j = 0 ; j < itemList.size() ; j ++)
	        		{
	        			SimpleMap itemMap = (SimpleMap)itemList.get(j) ;
	 %>			
			      	<td ><%= StringUtil.checkNull(endDataMap.getString(itemMap.getString("ITEM_ID")),"0") %></td>
	 <%
	        		}
	 %>
	        </tr>
	 <%
	        	}        	
	 %>
		  
		  
	        
        </table>  
	  </td>
  </tr>
  <tr>
  	<td>
  		<table>
  			<tr>
  			</tr>
  			<tr>
  				<c:forEach items="${itemList}" var="item">
			     <td ></td>
			    </c:forEach>
			     <td ></td>
				<td align="right"><img src=<%=url%>></td>
  			</tr>
  		</table>
  	</td>
  </tr>
  
</table>
</body>
</html>