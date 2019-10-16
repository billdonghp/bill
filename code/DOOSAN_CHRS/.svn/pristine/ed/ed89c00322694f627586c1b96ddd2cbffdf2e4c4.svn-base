<%@ page contentType="text/html; charset=UTF-8"  import="java.util.*,com.ait.sqlmap.util.*,com.ait.util.StringUtil,java.text.SimpleDateFormat,com.ait.hrm.bean.Department"%>
<jsp:useBean id="reportDAO" scope="page" class="com.ait.web.ReportDAO"/>
<jsp:useBean id="dataFormat" scope="page" class="java.text.SimpleDateFormat"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/inc/taglibs.jsp"%>
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><!-- 日考勤统计表  -->日考勤统计表</title>
</head>                              
<body>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=dailyDetailStatisticsSectionReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		String url="http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/images/report_logo.png";
		SimpleMap parameterObject = (SimpleMap)request.getAttribute("parameterObject") ;
		
%>
<table border="0" align="center" cellpadding="0" cellspacing="0">

  <tr align="center" height="30">
    <td >             
	    <table width="100%">  
	    	<tr>
	    		<td align="center" colspan="13" ><b><font size="+2"><!-- 日工资统计表(科室) --><%= parameterObject.getString("dateStarted") %> <ait:message messageID="display.att.view.report.daily_section" module="ar"/></font></b></td>
	    	</tr>
	    </table>    	
    </td>
  </tr>
  
  <tr>
    <td>
    	<table width="100%" border="1"align="center" cellpadding="0" cellspacing="0" style="padding: 2px 2px 2px 2px;border-collapse:collapse">  
			<tr align="center"> 
		         <td colspan="3"><!-- 部门  --><ait:message messageID="report.global.title.deptName" module="report"/></td>      
		         <td ><!-- 应出勤 --><ait:message messageID="report.mutual.title.scheduled" module="report"/></td>
		         <td ><!-- 实际出勤 --><ait:message messageID="report.mutual.title.actual" module="report"/></td>	
		         <td ><!-- 旷工 --><ait:message messageID="report.att.view.monthly.title.absenteeism" module="report"/></td>
		         <td ><!-- 工伤假--><ait:message messageID="report.att.view.monthly.title.industry.injury" module="report"/></td>	
		         <td ><!-- 事假 --><ait:message messageID="report.att.view.monthly.title.casual.leave" module="report"/></td>
		         <td ><!-- 病假 --><ait:message messageID="report.att.view.monthly.title.sick.leave" module="report"/></td>	         
		         <td ><!-- 其它 --><ait:message messageID="report.mutual.title.other" module="report"/></td>
		         <td ><!-- 迟到 --><ait:message messageID="report.att.view.monthly.title.tardiness" module="report"/></td>
		         <td ><!-- 早退 --><ait:message messageID="report.att.view.monthly.title.truancy" module="report"/></td>
		         <td ><!-- 加班 --><ait:message messageID="display.att.view.ot.ot" module="ar"/></td>
	        </tr>
	        
	   <% 
	            
	   			
   			SimpleMap dataMap = new SimpleMap() ;
  			String date = parameterObject.getString("dateStarted") ;
  			
		   	List oneList = reportDAO.getDept_section_head(date,parameterObject.getString("deptID")) ;//根据顶级部门ID，取出一级部门．
        	for (int i = 0 ; i < oneList.size() ; i ++)        
        	{
        		Department oneDept = (Department)oneList.get(i) ;
        		List twoList = reportDAO.getDept_PayDaily(date,oneDept.getDeptID(),oneDept.getDeptLevel()) ;
        		
        		for (int j = 0 ; j < twoList.size() ; j ++)
        		{
        			Department twoDept = (Department)twoList.get(j) ;
        			List threeList = reportDAO.getDept_section(date,twoDept.getDeptID(),twoDept.getDeptLevel()) ;//根据一级部门ID，取出二级部门
        			
					for (int k = 0 ; k < threeList.size() ; k ++)
					{
						Department threeDept = (Department)threeList.get(k) ;    
						dataMap = reportDAO.getData_arDailyDetailStatisticsSection(date,threeDept.getDeptID(),threeDept.getDeptLevel(),"single"); //三级部门的数据
						if( j == 0 && k == 0) //判断是否，为一级部门的第一行
						{
			%>
		  <tr align="center">
   			<td rowspan="<%= reportDAO.getDeptRowspan_paDailyStatisticsSection(date,oneDept.getDeptID(),oneDept.getDeptLevel(),1) %>"  align="center">
   				<ait:content enContent="<%= StringUtil.checkNull(oneDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(oneDept.getDeptName()) %>" ></ait:content>			
   			</td>		
				<td rowspan="<%= reportDAO.getDeptRowspan_paDailyStatisticsSection(date,twoDept.getDeptID(),twoDept.getDeptLevel(),2) %>"  align="center">
			<ait:content enContent="<%= StringUtil.checkNull(twoDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(twoDept.getDeptName()) %>" ></ait:content>	
				</td>
				<td >                             
			<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>			
				</td>
   			     <td ><%= dataMap.getString("EMP_COUNT") %></td>
   		         <td ><%= dataMap.getString("WORK_COUNT") %></td>
   		         <td ><%= dataMap.getString("ABSENCE") %></td>
   		         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
   		         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
   		         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
   		         <td ><%= dataMap.getString("OTHER") %></td>
   		         <td ><%= dataMap.getString("TARDINESS") %></td>
   		         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
   		         <td ><%= dataMap.getString("OVERTIME") %></td>
	   	</tr>			
			<%				
							}else if ( j != 0 && k == 0 ) //判断是否为,二级部门的第一行
							{
			%>
		<tr align="center">
				<td rowspan="<%= reportDAO.getDeptRowspan_paDailyStatisticsSection(date,twoDept.getDeptID(),twoDept.getDeptLevel(),2) %>"  align="center">
		<ait:content enContent="<%= StringUtil.checkNull(twoDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(twoDept.getDeptName()) %>" ></ait:content>		
				</td>
				<td >
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>				
				</td>
   			     <td ><%= dataMap.getString("EMP_COUNT") %></td>
   		         <td ><%= dataMap.getString("WORK_COUNT") %></td>
   		         <td ><%= dataMap.getString("ABSENCE") %></td>
   		         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
   		         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
   		         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
   		         <td ><%= dataMap.getString("OTHER") %></td>
   		         <td ><%= dataMap.getString("TARDINESS") %></td>
   		         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
   		         <td ><%= dataMap.getString("OVERTIME") %></td>
	   	</tr>
			<%				
							}else{
			%>
		<tr align="center">
				<td >
		<ait:content enContent="<%= StringUtil.checkNull(threeDept.getDeptEnName()) %>" zhContent="<%= StringUtil.checkNull(threeDept.getDeptName()) %>" ></ait:content>		
				</td>
   			     <td ><%= dataMap.getString("EMP_COUNT") %></td>
 		         <td ><%= dataMap.getString("WORK_COUNT") %></td>
 		         <td ><%= dataMap.getString("ABSENCE") %></td>
 		         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
 		         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
 		         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
 		         <td ><%= dataMap.getString("OTHER") %></td>
 		         <td ><%= dataMap.getString("TARDINESS") %></td>
 		         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
 		         <td ><%= dataMap.getString("OVERTIME") %></td>
	   	</tr>					
			<%							
							}												
						}
			%>
		<tr align="center">	
			<%			
						dataMap = reportDAO.getData_arDailyDetailStatisticsSection(date,twoDept.getDeptID(),twoDept.getDeptLevel(),"subtotal"); //二级部门的数据
			%>
   			 <td ><!-- 小计 -->
        	 <ait:message  messageID="report.hrm.sex.distribute.small" module="report"/></td>
   			 <td ><%= dataMap.getString("EMP_COUNT") %></td>
	         <td ><%= dataMap.getString("WORK_COUNT") %></td>
	         <td ><%= dataMap.getString("ABSENCE") %></td>
	         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
	         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
	         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
	         <td ><%= dataMap.getString("OTHER") %></td>
	         <td ><%= dataMap.getString("TARDINESS") %></td>
	         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
	         <td ><%= dataMap.getString("OVERTIME") %></td>
   		</tr>	
			<%
        		}
	        		dataMap = reportDAO.getData_arDailyDetailStatisticsSection(date,oneDept.getDeptID(),oneDept.getDeptLevel(),"total"); //一级部门的数据
		    %>
        <tr align="center">
   			 <td colspan="2"  align="center"><!-- 科小计 -->
        	 <ait:message  messageID="report.hrm.organize.structure.faculty" module="report"/></td>  
   			 <td ><%= dataMap.getString("EMP_COUNT") %></td>
	         <td ><%= dataMap.getString("WORK_COUNT") %></td>
	         <td ><%= dataMap.getString("ABSENCE") %></td>
	         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
	         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
	         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
	         <td ><%= dataMap.getString("OTHER") %></td>
	         <td ><%= dataMap.getString("TARDINESS") %></td>
	         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
	         <td ><%= dataMap.getString("OVERTIME") %></td>
   		</tr>			
        <%		
       		}
	        	dataMap = reportDAO.getData_arDailyDetailStatisticsSection(date,parameterObject.getString("deptID"),1,"total")  ;
		%>
		<tr align="center">       
		         <td colspan="3"><!-- 合计 --><ait:message messageID="report.hrm.organize.structure.sum" module="report"/></td>
		         <td ><%= dataMap.getString("EMP_COUNT") %></td>
   		         <td ><%= dataMap.getString("WORK_COUNT") %></td>
   		         <td ><%= dataMap.getString("ABSENCE") %></td>
   		         <td ><%= dataMap.getString("INDUSTRIAL_INJURY") %></td>
   		         <td ><%= dataMap.getString("CASUAL_LEAVE") %></td>
   		         <td ><%= dataMap.getString("SICK_LEAVE") %></td>
   		         <td ><%= dataMap.getString("OTHER") %></td>
   		         <td ><%= dataMap.getString("TARDINESS") %></td>
   		         <td ><%= dataMap.getString("TRUANCY") %></td>	   		         
   		         <td ><%= dataMap.getString("OVERTIME") %></td>
	    </tr>
	      
        </table>  
	  </td>
  </tr>
  <tr>
  	<td>
  		<table>
  			<tr>
  				<td></td>
  				<td></td>
  				<td></td>
  				<td></td>
  				
  				<td></td>
  				<td></td>
  				<td align="right"><img src=<%=url%>></td>
  			</tr>
  		</table>
  	</td>	 
  </tr>
</table>
</body>
</html>