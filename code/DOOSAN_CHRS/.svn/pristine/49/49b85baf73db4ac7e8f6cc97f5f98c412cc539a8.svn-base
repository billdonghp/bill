<%@ page contentType="text/html; charset=UTF-8" import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="securityChecksTotalExcelList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=SecurityChecksTotalReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	SimpleMap parameterObject = null;
	parameterObject = ObjectBindUtil.getData(request);
	
%>
<table border="1" align="center" cellpadding="0"
	cellspacing="0" align="center">


	
			<tr align="center">
				<td align="center" colspan="55"><b><font size="+2">安全检查记录统计表</font></b></td>
			</tr>
	
	

	       
		     <tr align="center">
			  <td  rowspan="2">
				部门</td>    
		      <td colspan="9">
				计划反馈</td>
			  <td colspan="9">
				整改反馈</td>
			  <td colspan="9">
				未确认</td>
			  <td colspan="9">
				确认完成</td>
			  <td colspan="9">
				已通知</td>
			  <td colspan="9">
				退回</td> 	     
		    </tr>
		     <tr align="center">
		     
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
			  <td>安全</td> 
			  <td>环境</td>   
			  <td>消防</td>   
			  <td>职业<br>健康</td>   
			  <td>电器</td>   
			  <td>设备</td>   
			  <td>管理</td>   
			  <td>外协</td>   
			  <td>其他</td>    
		    </tr>
		    <%for(int i=0;i<securityChecksTotalExcelList.size();i++){
		    	dataMap=(SimpleMap)securityChecksTotalExcelList.get(i);
		    %>
		    <tr  height="30">
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("DEPTNAME"),"&nbsp;")%></td>
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT0_009"),"0")%></td>
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT1_009"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT2_009"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT3_009"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT4_009"),"0")%></td>		
             <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_001"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_002"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_003"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_004"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_005"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_006"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_007"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_008"),"0")%></td>	
		     <td nowrap="nowrap" align="center"><%=StringUtil.checkNull(dataMap.get("TYPECOUNT5_009"),"0")%></td>	
		    
		    </tr>
		    <%} %>	
		
</table>
</body>
</html>