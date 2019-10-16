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
		response.setHeader("Content-Disposition", "attachment; filename=report0102.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.setString("cpnyId" , admin.getCpnyId()) ;
		parameterObject.setString("supervisorId" , admin.getAdminID()) ;
		
		Date arDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDate")) ;
		
		parameterObject.setString("arDate" , new SimpleDateFormat("yyyy/MM/dd").format(arDate)) ;
%>

	<body>
		<table>
		  <tr align="center">
		  	<td colspan="26" ></td>
		  </tr>
		</table>
		<table border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="26" ><fmt:formatDate value="<%= arDate %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;勤态日报表-总括</td>
		  </tr>
		  <tr><td colspan="26">&nbsp;</td></tr>	
<%
	ArReportService service = new ArReportService() ;

	SimpleMap headMap = new SimpleMap() ;
	List bodyDataList = new ArrayList() ;
	
	double headZongshu = 1 ;
	int headSize = 0 ;
	
	parameterObject.remove("parentDeptID") ;
	headMap = service.retrieveReport0101HeadData(parameterObject) ;
	
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

	bodyDataList = service.retrieveReport0102CenterData(parameterObject) ;	
	
	request.setAttribute("parameterObject",parameterObject) ;
	request.setAttribute("headMap",headMap) ;
	request.setAttribute("bodyDataList",bodyDataList) ;
%>
		  <tr align="center">
		    <td rowspan="3" >部门</td>
		    <td colspan="3">应出勤人数</td>
		    <td colspan="7" >实出勤人数</td>
		    <td rowspan="3" ><br>缺勤<br>人员</td>
		    <td colspan="10" >勤态区分</td>
		    <td colspan="4" >前日加班统计</td>
		  </tr>
		  <tr align="center">
		  	<td rowspan="2" >计</td>
		  	<td colspan="2" >人员区分</td>
		  	<td rowspan="2" >计</td>
		  	<td colspan="2" >工厂</td>
		  	<td colspan="4" >其他</td>
		  	<td rowspan="2" >产假</td>
		  	<td rowspan="2" >婚丧</td>
		  	<td rowspan="2" >工伤</td>
		  	<td rowspan="2" >事假</td>
		  	<td rowspan="2" >病假</td>
		  	<td rowspan="2" >旷工</td>
		  	<td rowspan="2" >病休</td>
		  	<td rowspan="2" >休假</td>
		  	<td rowspan="2" >其他</td>
		  	<td rowspan="2" >迟到</td>
		  	<td colspan="2">职员</td>
		  	<td colspan="2">工人</td>
		  </tr>
		  <tr>
		  	<td >职员</td>
		  	<td >工人</td>
		  	<td >职员</td>
		  	<td >工人</td>
		  	<td >培训</td>
		  	<td >研修</td>
		  	<td >出差</td>
		  	<td >教育</td>
		  	<td >人员</td>
		  	<td >时间</td>
		  	<td >人员</td>
		  	<td >时间</td>
		  </tr>
		  
		  <tr align="center">
		  	<td rowspan="2" >总计<br>(比例)</td>
		  	<td>${headMap.zongShu}</td>
		  	<td>${headMap.zongShuA}</td>
		  	<td>${headMap.zongShuB}</td>
		  	
		  	<td>${headMap.zhengChangA + headMap.zhengChangB + headMap.peiXun + headMap.yanXiu + headMap.chuChai + headMap.jiaoYu}</td>
		  	<td>${headMap.zhengChangA}</td>
		  	<td>${headMap.zhengChangB}</td>
		  	<td>${headMap.peiXun}</td>
		  	<td>${headMap.yanXiu}</td>
		  	<td>${headMap.chuChai}</td>
		  	<td>${headMap.jiaoYu}</td>
		  	
		  	<td>${headMap.queQing}</td>
		  	<td>${headMap.chanJia}</td>
		  	<td>${headMap.hunSongJia}</td>
		  	<td>${headMap.gongShangJia}</td>
		  	<td>${headMap.shiJia}</td>
		  	<td>${headMap.bingJia}</td>
		  	<td>${headMap.kuangGong}</td>
		  	<td>${headMap.bingXiu}</td>
		  	<td>${headMap.xiuJia}</td>
		  	<td>${headMap.queQing - headMap.chanJia - headMap.hunSongJia - headMap.gongShangJia - headMap.shiJia - headMap.bingJia - headMap.kuangGong - headMap.bingXiu - headMap.xiuJia - headMap.chiDao}</td>
		  	<td>${headMap.chiDao}</td>
		  	
		  	<td>${headMap.otRenShuA}</td>
		  	<td>${headMap.otShiJianA}</td>
		  	<td>${headMap.otRenShuB}</td>
		  	<td>${headMap.otShiJianB}</td>
		  </tr>
		  <tr align="center">
		  	
		  	<td><fmt:formatNumber value="${headMap.zongShu/headZongshu}" type="percent" /></td>
		  	<td><fmt:formatNumber value="${headMap.zongShuA/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.zongShuB/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${(headMap.zhengChang + headMap.zhengChangA + headMap.zhengChangB + headMap.peiXun + headMap.yanXiu + headMap.chuChai + headMap.jiaoYu)/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.zhengChangA/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.zhengChangB/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${headMap.peiXun/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.yanXiu/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.chuChai/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.jiaoYu/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${headMap.queQing/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.chanJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.hunSongJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.gongShangJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.shiJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.bingJia/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.kuangGong/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.bingXiu/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.xiuJia/headZongshu}" type="percent"/></td>
		  	
		  	<td><fmt:formatNumber value="${(headMap.queQing - headMap.chanJia - headMap.hunSongJia - headMap.gongShangJia - headMap.shiJia - headMap.bingJia - headMap.kuangGong - headMap.bingXiu - headMap.xiuJia - headMap.chiDao)/headZongshu}" type="percent"/></td>
		  	<td><fmt:formatNumber value="${headMap.chiDao/headZongshu}" type="percent"/></td>	  	
		  	
		  	<td colspan="2">
				 <c:if test="${headMap.otRenShuA == 0}">
				 	${headMap.otRenShuA}%	
				 </c:if>	 	
				 <c:if test="${headMap.otRenShuA != 0}">
				 	<fmt:formatNumber value="${headMap.otRenShuA/headMap.otShiJianA}" type="percent"/>	
				 </c:if>
			</td>
		 	
		 	<td colspan="2">
				 <c:if test="${headMap.otRenShuB == 0}">
				 	${headMap.otRenShuB}% 	
				 </c:if>	 	
				 <c:if test="${headMap.otRenShuB != 0}">
				 	<fmt:formatNumber value="${headMap.otRenShuB/headMap.otShiJianB}" type="percent"/>	
				 </c:if>
		 	</td>
		</tr>
		
		<c:forEach items="${bodyDataList}" var="bodyDataMap"> 
		  <tr align="center">	
		  
			<td>${bodyDataMap.DEPTNAME}</td>
			<td>${bodyDataMap.zongShu}</td>
		  	<td>${bodyDataMap.zongShuA}</td>
		  	<td>${bodyDataMap.zongShuB}</td>
		  	
		  	<td>${bodyDataMap.zhengChangA + bodyDataMap.zhengChangB + bodyDataMap.peiXun + bodyDataMap.yanXiu + bodyDataMap.chuChai + bodyDataMap.jiaoYu}</td>
		  	<td>${bodyDataMap.zhengChangA}</td>
		  	<td>${bodyDataMap.zhengChangB}</td>
		  	<td>${bodyDataMap.peiXun}</td>
		  	<td>${bodyDataMap.yanXiu}</td>
		  	<td>${bodyDataMap.chuChai}</td>
		  	<td>${bodyDataMap.jiaoYu}</td>
		  	
		  	<td>${bodyDataMap.queQing}</td>
		  	<td>${bodyDataMap.chanJia}</td>
		  	<td>${bodyDataMap.hunSongJia}</td>
		  	<td>${bodyDataMap.gongShangJia}</td>
		  	<td>${bodyDataMap.shiJia}</td>
		  	<td>${bodyDataMap.bingJia}</td>
		  	<td>${bodyDataMap.kuangGong}</td>
		  	<td>${bodyDataMap.bingXiu}</td>
		  	<td>${bodyDataMap.xiuJia}</td>
		  	<td>${bodyDataMap.queQing - bodyDataMap.chanJia - bodyDataMap.hunSongJia - bodyDataMap.gongShangJia - bodyDataMap.shiJia - bodyDataMap.bingJia - bodyDataMap.kuangGong - bodyDataMap.bingXiu - bodyDataMap.xiuJia - bodyDataMap.chiDao}</td>
		  	<td>${bodyDataMap.chiDao}</td>
		  	
		  	<td>${bodyDataMap.otRenShuA}</td>
		  	<td>${bodyDataMap.otShiJianA}</td>
		  	<td>${bodyDataMap.otRenShuB}</td>
		  	<td>${bodyDataMap.otShiJianB}</td>
		  			  		
		 </tr>	
		</c:forEach>
		  <tr>
		  	  <td align="center">备注</td>
		  	  <td height="100" colspan="25">&nbsp;</td>
		  </tr>	
		</table>
	</body>
</html>

