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
		response.setHeader("Content-Disposition", "attachment; filename=report0104.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
		SimpleDateFormat report0104DateFormat = new SimpleDateFormat("yyyy/MM/dd") ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="35" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="35" ><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;日勤态报表</td>
		  </tr>
		  <tr><td colspan="35">&nbsp;</td></tr>	
		  <tr align="center">
		  	<td rowspan="2">部<br>门</td>
		  	<td rowspan="2">课<br>别</td>
		  	<td rowspan="2">班<br>别</td>
		  	<td rowspan="2">总<br>数</td>
		  	
		  	<td >教<br>育</td>
		  	<td >出<br>差</td>
		  	<td >研<br>修</td>
		  	<td >培<br>训</td>
		  	<td >因<br>公<br>外<br>出</td>
		  	<td >哺<br>乳<br>假</td>
		  	
		  	<td >丧<br>假</td>
		  	<td >婚<br>假</td>
		  	<td >年<br>休<br>假</td>
		  	<td >调<br>休</td>
		  	<td >产<br>假<br>护<br>理<br>假</td>
		  	<td >有<br>薪<br>产<br>假</td>
		  	<td >计<br>生<br>假</td>
		  	<td >结<br>婚<br>登<br>记<br>假</td>
		  	<td >产<br>前<br>检<br>查</td>
		  	
		  	<td >休<br>假</td>
		  	<td >工<br>伤<br>假</td>
		  	
		  	<td >休<br>职</td>
		  	<td >事<br>假</td>
		  	<td >旷<br>工</td>
		  	<td >病<br>假</td>
		  	<td >病<br>休</td>
		  	<td >产<br>假</td>
		  	<td >未<br>出<br>勤<br>小<br>计</td>
		  	
		  	<td >迟<br>到</td>
		  	<td >早<br>退</td>
		  	
		  	<td colspan="2">正常出勤	</td>
		  	<td colspan="2">加班(实绩)</td>
		  	<td rowspan="2">备注</td>
		  	
		  </tr>
		  
		  <tr align="center">
		  	<td colspan="6">算出勤</td>
		  	<td colspan="12">不计算出勤</td>
		  	<td colspan="5">不计算出勤</td>
		  	<td >&nbsp;</td>
		  	<td >&nbsp;</td>
		  	<td >&nbsp;</td>
		  	<td >人员</td>
		  	<td >出勤率</td>
		  	<td >加班</td>
		  	<td >加班率</td>
		  </tr>
		  
<%
		ArReportService service = new ArReportService() ;
		SimpleMap parameterObject = new SimpleMap() ;
		parameterObject.set("arDateStarted", report0104DateFormat.format(arDateStarted.getTime())) ;
		parameterObject.set("arDateEnd", report0104DateFormat.format(arDateEnd.getTime())) ;
		
		List bodyDataList = (List)request.getAttribute("arDataList") ;
		
		for (int k = 0 ; k < bodyDataList.size() ; k ++)
		{
			List contentList = new ArrayList() ;
			SimpleMap bodyDataMap = (SimpleMap)bodyDataList.get(k) ;
			
			if (bodyDataMap.getString("TEAM_DEPTID") != null && bodyDataMap.getString("TEAM_DEPTID").length() > 0) {
				parameterObject.set("deptID",bodyDataMap.getString("TEAM_DEPTID")) ;
				contentList = service.retrieveReport0104ContentData(parameterObject) ;
			}
			bodyDataMap.set("contentList",contentList) ;
		}
%>
		  
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  		<tr align="center">	
				  		
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && arDataList[i.index - 1].DEPTNAME ne arData.DEPTNAME }" >
							<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		
					  	<c:if test="${arData.OFFICENAME ne arData.TEAMNAME}">  	
					  	<td>&nbsp;</td>
					  	<td>${arData.TEAMNAME}</td>
					  	</c:if>
					  	
					  	<c:if test="${arData.OFFICENAME eq arData.TEAMNAME}"> 
					  	<td>${arData.OFFICENAME}</td>
					  	<td>&nbsp;</td>
					  	</c:if>
					  	
					  	<td >${arData.zongShu}</td>
						  	
			    		<td >${arData.jiaoYu}</td>
					  	<td >${arData.chuChai}</td>
					  	<td >${arData.yanXiu}</td>
					  	<td >${arData.peiXun}</td>
					  	<td >${arData.yinGongWaiChu}</td>
					  	<td >${arData.puRuJia}</td>
					  	
					  	<td >${arData.SongJia}</td>
					  	<td >${arData.hunJia}</td>
					  	<td >${arData.nianXiuJia}</td>
					  	<td >${arData.tiaoXiu}</td>
					  	<td >${arData.chanJiaHuLi}</td>
					  	<td >${arData.jiShengJia}</td>
					  	
					  	<td >${arData.jiHunDengJi}</td>
					  	<td >${arData.chanQianJianCha}</td>
					  	<td >${arData.xiuXi}</td>
					  	<td >${arData.xiuJia}</td>
					  	<td >${arData.gongShangJia}</td>
					 	
					 	<td >${arData.xiuZhi}</td> 						  	
					  	<td >${arData.shiJia}</td>
					  	<td >${arData.kuangGong}</td>
					  	<td >${arData.bingJia}</td>
					  	<td >${arData.bingXiu}</td>
					  	<td >${arData.chanJia}</td>
					  	<td >${arData.quQing - arData.chiDao - arData.zaoTui}</td>
					  	
					  	<td >${arData.chiDao}</td>
					  	<td >${arData.zaoTui}</td>
					  	
					  	<td >${arData.zhengChang}</td>
					  	<td><fmt:formatNumber value="${arData.zhengChang/arData.zongShu}" type="percent" /></td>
					  	
					  	<td >${arData.otRenShu}</td>
						<td>
							<c:if test="${arData.zhengChang == 0}">
							 	${arData.zhengChang}%	
							 </c:if>	 	
							 <c:if test="${arData.zhengChang != 0}">
							 	<fmt:formatNumber value="${arData.otRenShu/arData.zhengChang}" type="percent" />	
							 </c:if>
						 </td>
					  	<td align="left">
					  		<c:forEach items="${arData.contentList}" var="content" varStatus="i">
					  			${content.CONTENT}
					  			<c:if test="${i.index + 1 != fn:length(contentList) }"><br></c:if>
					  		</c:forEach>
					  		&nbsp;
					  	</td>
				</tr>
		  </c:forEach>
		  
		</table>
	</body>
</html>

