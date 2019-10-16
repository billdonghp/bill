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
<head><title>勤态报表(总括表)</title></head>


 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=dailyDetailStatistics.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		SimpleMap parameterObject = (SimpleMap)request.getAttribute("parameterObject");
		
		Date dateStarted = new SimpleDateFormat("yyyy/MM/dd").parse(parameterObject.getString("dateStarted")) ;
		Date otStart = new SimpleDateFormat("yyyy/MM/dd").parse(parameterObject.getString("otStart")) ;
		Date otEnd = new SimpleDateFormat("yyyy/MM/dd").parse(parameterObject.getString("otEnd")) ;
		
%>

	<body>
		<table>
		  <tr align="center">
		  	<td colspan="23" ></td>
		  </tr>
		</table>
		<table border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="23" ><fmt:formatDate value="<%= dateStarted %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;勤态日报表(烟台工厂)</td>
		  </tr>
		  <tr><td colspan="23">&nbsp;</td></tr>	
<%
	ArReportService service = new ArReportService() ;
	String empType[] = new String[]{"office","work"} ;
	SimpleMap headMap = new SimpleMap() ;
	SimpleMap bodyMap = new SimpleMap() ;
	
	parameterObject.set("level","3") ;
	List deptList = service.retrieveDepartmentByLevel(parameterObject) ;
	
	SimpleMap endMap = service.retrieveDailyDetailOverall(parameterObject) ;
	
	double endZongshu = 1 ;
	double headZongshu = 1 ;
	
	if (endMap.getDouble("zongShu") > 0)
	{
		endZongshu = endMap.getDouble("zongShu") ;
	}
	
	request.setAttribute("endZongshu",endZongshu) ;
	
	Iterator endMapKeyIter = endMap.keySet().iterator() ;	
	while(endMapKeyIter.hasNext())
	{
		String key = endMapKeyIter.next().toString() ;
		
		endMap.set(key,endMap.getDouble(key)) ;
	}
	
	
	for (int i = 0 ; i < empType.length ; i++)
	{
		int headSize = 0 ;
		parameterObject.setString("empType",empType[i]) ;
		parameterObject.remove("parentDeptID") ;
		headMap = service.retrieveDailyDetailOverall(parameterObject) ;
		
		if (headMap.getDouble("zongShu") > 0)
		{
			headZongshu = headMap.getDouble("zongShu") ;
		}
		request.setAttribute("headZongshu",headZongshu) ;
		
		Iterator headMapKeyIter = headMap.keySet().iterator() ;
		while(headMapKeyIter.hasNext())
		{
			String key = headMapKeyIter.next().toString() ;
			
			headMap.set(key,headMap.getDouble(key)) ;
		}
		
		List tempList = new ArrayList() ;
		for (int j = 0 ; j < deptList.size() ; j ++ )
		{
			SimpleMap deptMap = (SimpleMap)deptList.get(j) ;
			parameterObject.setString("parentDeptID",deptMap.getString("DEPTID")) ;
			
			tempList = service.retrieveDailyDetailStatistics(parameterObject) ;	
			
			if (!tempList.isEmpty())
			{
				SimpleMap tempMap = service.retrieveDailyDetailOverall(parameterObject) ;
				tempMap.set("DEPTNAME","小计") ;
				
				tempList.add(tempMap) ;
				
				headSize += tempList.size() ;
			}
			
			bodyMap.set(deptMap.getString("DEPTNAME"),tempList) ;
		}
		
		
		request.setAttribute("parameterObject",parameterObject) ;
		request.setAttribute("headMap",headMap) ;
		request.setAttribute("endMap",endMap) ;
%>
		  <tr align="center">
		    <td rowspan="4" ><br>人员<br>区分</td>
		    <td rowspan="4" colspan="2">部门</td>
		    <td >应出勤</td>
		    <td colspan="6" >实出勤人数</td>
		    <td rowspan="4" ><br>缺勤<br>人员</td>
		    <td colspan="8" >勤态区分</td>
		    <td rowspan="4" ><br>迟<br>到</td>
		    <td colspan="2" >加班统计</td>
		    <td rowspan="4" >详细情况</td>
		  </tr>
		  <tr align="center">
		  	<td>&nbsp;</td>
		  	<td colspan="6" >&nbsp;</td>
		  	<td colspan="3" >区分1</td>
		  	<td colspan="5" >区分2</td>
		  	<td colspan="2" >
		  			<c:if test="${parameterObject.otStart == parameterObject.otEnd }">(<fmt:formatDate value="<%= otStart %>" pattern="M.d日"/>)</c:if>
		  			<c:if test="${parameterObject.otStart != parameterObject.otEnd }">(<fmt:formatDate value="<%= otStart %>" pattern="M.d日"/>&nbsp;-&nbsp;<fmt:formatDate value="<%= otEnd %>" pattern="M.d日"/>)</c:if> 
		  	</td>
		  </tr>
		  <tr align="center">
		  	<td rowspan="2" >计</td>
		  	<td rowspan="2" >计</td>
		  	<td >工厂</td>
		  	<td colspan="4" >其他</td>
		  	<td rowspan="2" >产假</td>
		  	<td rowspan="2" >婚丧</td>
		  	<td rowspan="2" >工伤</td>
		  	<td rowspan="2" >事假</td>
		  	<td rowspan="2" >病假</td>
		  	<td rowspan="2" >旷工</td>
		  	<td rowspan="2" >病休</td>
		  	<td rowspan="2" >休假</td>
		  	<td colspan="2" >
				<c:if test="${parameterObject.empType == 'office'}">职员</c:if>
			  	<c:if test="${parameterObject.empType == 'work'}"  >工人</c:if>
			</td>
		  </tr>
		  <tr align="center">
		  	<td >
		  		<c:if test="${parameterObject.empType == 'office'}">职员</c:if>
			  	<c:if test="${parameterObject.empType == 'work'}"  >工人</c:if>
			</td>
		  	<td >培训</td>
		  	<td >研修</td>
		  	<td >出差</td>
		  	<td >教育</td>
		  	<td >人员</td>
		  	<td >时间</td>
		  </tr>
		  
		  <tr align="center">
		  	<td rowspan="2">&nbsp;</td>
		  	<td colspan="2" >计</td>
		  	<td>${headMap.zongShu}</td>
		  	
		  	<td>${headMap.zhengChang + headMap.peiXun + headMap.yanXiu + headMap.chuChai + headMap.jiaoYu}</td>
		  	<td>${headMap.zhengChang}</td>
		  	<td>${headMap.peiXun}</td>
		  	<td>${headMap.yanXiu}</td>
		  	<td>${headMap.chuChai}</td>
		  	<td>${headMap.jiaoYu}</td>
		  	
		  	<td>${headMap.quQing}</td>
		  	<td>${headMap.chanJia}</td>
		  	<td>${headMap.hunSong}</td>
		  	<td>${headMap.gongShang}</td>
		  	<td>${headMap.shiJia}</td>
		  	<td>${headMap.pingJia}</td>
		  	<td>${headMap.kuangGong}</td>
		  	<td>${headMap.bingXiu}</td>
		  	<td>${headMap.xiuJia}</td>
		  	
		  	<td>${headMap.chiDao}</td>
		  	
		  	<td>${headMap.otRenShu}</td>
		  	<td>${headMap.otShiJian}</td>
		  	<td>&nbsp;</td>	
		  </tr>
		   <tr align="center">
		  	<td >(比率)</td>
		  	
		  	<td><fmt:formatNumber value="${headMap.zongShu/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${(headMap.zhengChang + headMap.peiXun + headMap.yanXiu + headMap.chuChai + headMap.jiaoYu)/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.zhengChang/headZongshu}"  type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.peiXun/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.yanXiu/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.chuChai/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.jiaoYu/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${headMap.quQing/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.chanJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.hunSong/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.gongShang/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.shiJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.pingJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.kuangGong/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.bingXiu/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.xiuJia/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${headMap.chiDao/headZongshu}" type="percent"/></td>	  	
		  	
		 	<td>&nbsp;</td>
		 <c:if test="${headMap.otRenShu == 0}">
		 	<td>${headMap.otShiJian}%</td> 	
		 </c:if>	 	
		 <c:if test="${headMap.otRenShu != 0}">
		 	<td><fmt:formatNumber value="${headMap.otShiJian/headMap.otRenShu}" pattern="0"/></td>	
		 </c:if>
		  	<td>&nbsp;</td>	
		</tr>
		  
<%
		List bodyDataList = new ArrayList() ;
		for (int j = 0 ; j < deptList.size() ; j ++ )
		{
			SimpleMap deptMap = (SimpleMap)deptList.get(j) ;
			bodyDataList = (List)bodyMap.get(deptMap.getString("DEPTNAME")) ;

			for (int k = 0 ; k < bodyDataList.size() ; k ++)
			{
				List contentList = new ArrayList() ;
				SimpleMap bodyDataMap = (SimpleMap)bodyDataList.get(k) ;
				
				if (bodyDataMap.getString("DEPTID") != null && bodyDataMap.getString("DEPTID").length() > 0) {
					parameterObject.set("deptID",bodyDataMap.getString("DEPTID")) ;
					contentList = service.retrieveDailyDetailContent(parameterObject) ;
				}
				
				request.setAttribute("bodyDataMap",bodyDataMap) ;
				request.setAttribute("contentList",contentList) ;
%>
		  <tr align="center">
<% 
				if (j == 0 && k == 0){
%>				
			<td rowspan="<%= headSize %>">
				<c:if test="${parameterObject.empType == 'office'}">职员</c:if>
			  	<c:if test="${parameterObject.empType == 'work'}"  >工人</c:if>
		  	</td>
<%			  		
				}
				if (k == 0 ){
%>				
			<td rowspan="<%= bodyDataList.size() %>"><%= deptMap.getString("DEPTNAME") %></td>
<%			  		
				}
%>
			<td>${bodyDataMap.DEPTNAME}</td>
			<td>${bodyDataMap.zongShu}</td>
		  	
		  	<td>${bodyDataMap.zhengChang + bodyDataMap.peiXun + bodyDataMap.yanXiu + bodyDataMap.chuChai + bodyDataMap.jiaoYu}</td>
		  	<td>${bodyDataMap.zhengChang}</td>
		  	<td>${bodyDataMap.peiXun}</td>
		  	<td>${bodyDataMap.yanXiu}</td>
		  	<td>${bodyDataMap.chuChai}</td>
		  	<td>${bodyDataMap.jiaoYu}</td>
		  	
		  	<td>${bodyDataMap.quQing}</td>
		  	<td>${bodyDataMap.chanJia}</td>
		  	<td>${bodyDataMap.hunSong}</td>
		  	<td>${bodyDataMap.gongShang}</td>
		  	<td>${bodyDataMap.shiJia}</td>
		  	<td>${bodyDataMap.pingJia}</td>
		  	<td>${bodyDataMap.kuangGong}</td>
		  	<td>${bodyDataMap.bingXiu}</td>
		  	<td>${bodyDataMap.xiuJia}</td>
		  	
		  	<td>${bodyDataMap.chiDao}</td>
		  	
		  	<td>${bodyDataMap.otRenShu}</td>
		  	<td>${bodyDataMap.otShiJian}</td>
		  	
		  	<td align="left">
		  		<c:forEach items="${contentList}" var="content" varStatus="i">
		  			${content.CONTENT}
		  			<c:if test="${i.index + 1 != fn:length(contentList) }"><br></c:if>
		  		</c:forEach>
		  		&nbsp;
		  	</td>		  		
		 </tr>	
				
<%				
			}
		}
%>
		<tr><td colspan="23">&nbsp;</td></tr>	
<%
	}
%>
		  <tr align="center">
		  	<td rowspan="2" colspan="3">合计</td>
		  	<td>${endMap.zongShu}</td>
		  	
		  	<td>${endMap.zhengChang + endMap.peiXun + endMap.yanXiu + endMap.chuChai + endMap.jiaoYu}</td>
		  	<td>${endMap.zhengChang}</td>
		  	<td>${endMap.peiXun}</td>
		  	<td>${endMap.yanXiu}</td>
		  	<td>${endMap.chuChai}</td>
		  	<td>${endMap.jiaoYu}</td>
		  	
		  	<td>${endMap.quQing}</td>
		  	<td>${endMap.chanJia}</td>
		  	<td>${endMap.hunSong}</td>
		  	<td>${endMap.gongShang}</td>
		  	<td>${endMap.shiJia}</td>
		  	<td>${endMap.pingJia}</td>
		  	<td>${endMap.kuangGong}</td>
		  	<td>${endMap.bingXiu}</td>
		  	<td>${endMap.xiuJia}</td>
		  	
		  	<td>${endMap.chiDao}</td>
		  	
		  	<td>${endMap.otRenShu}</td>
		  	<td>${endMap.otShiJian}</td>
		  	<td>&nbsp;</td>	
		  </tr>
		  <tr align="center">
		  	<td><fmt:formatNumber value="${endMap.zongShu/endZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${(endMap.zhengChang + endMap.peiXun + endMap.yanXiu + endMap.chuChai + endMap.jiaoYu)/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.zhengChang/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.peiXun/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.yanXiu/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.chuChai/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.jiaoYu/endZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${endMap.quQing/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.chanJia/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.hunSong/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.gongShang/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.shiJia/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.pingJia/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.kuangGong/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.bingXiu/endZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${endMap.xiuJia/endZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${endMap.chiDao/endZongshu}" type="percent"/></td>
	        <td>&nbsp;</td>
    <c:if test="${endMap.otRenShu != 0}">
    	    <td><fmt:formatNumber value="${endMap.otShiJian/endMap.otRenShu}" pattern="0"/></td>
    </c:if>
    <c:if test="${endMap.otRenShu == 0}">
    	    <td>${endMap.otShiJian}</td>
    </c:if>
    		<td>&nbsp;</td>	
		  </tr>
		</table>
	</body>
</html>

