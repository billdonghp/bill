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
		response.setHeader("Content-Disposition", "attachment; filename=report0103.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		String cpnyId = admin.getCpnyId();
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="31" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  <% if( "78000000".equals(cpnyId)){ %>
		  <tr align="center">
		  	<td colspan="34" >
		  	<fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;日勤态报表</td>
		  </tr>
		  <tr><td colspan="34">&nbsp;</td></tr>	
		 <% }else{%>
		   <tr align="center">
		  	<td colspan="31" >
		  	<fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;日勤态报表</td>
		   </tr>
		   <tr><td colspan="31">&nbsp;</td></tr>	
		 <% }%>
		  
		  <tr align="center">
		  	<td rowspan="2">部<br>门</td>
		  	<td rowspan="2">课<br>别</td>
		  	<td rowspan="2">班<br>别</td>
		  	<td rowspan="2">职<br>号</td>
		  	<td rowspan="2">姓<br>名</td>
		  	<td rowspan="2">区<br>分</td>
		  	
		  	<td colspan="16">全薪假</td>
		  	
		  	 <% if( "78000000".equals(cpnyId)){ %>
			   <td colspan="13">非全薪假</td>
			 <% }else{%>
			   <td colspan="10">非全薪假</td>
			 <% }%>
		  	
		  </tr>
		  
		  <tr align="center">
		  	<td >教<br>育</td>
		  	<td >出<br>差</td>
		  	<td >研<br>修</td>
		  	<td >培<br>训</td>
		  	<td >因公<br>外出</td>
		  	
		  	<td >哺乳<br>假</td>
		  	<td >丧<br>假</td>
		  	<td >产假<br>护理假</td>
		  	<td >年休<br>假</td>
		  	
		  	<td >婚<br>假</td>
		  	<td >产前<br>检查</td>
		  	<td >计生<br>假</td>
		  	<td >结婚<br>登记假</td>
		  	<td >调<br>休</td>
		  	
		  	<td >休<br>假</td>
		  	<td >工伤<br>假</td>
		  	
		  	<td >休<br>职</td>
		  	<td >事<br>假</td>
		  	
		  	<td >旷<br>工</td>
		  	<td >病<br>假</td>
		  	<td >病<br>休</td>
		  	
		  	<td >迟<br>到</td>
		  	<td >早<br>退</td>
		  	<td >放假<br>1</td>
		  	<td >放假<br>2</td>
		  	<td >产<br>假</td>
		  	 <% if( "78000000".equals(cpnyId)){ %>
			   	<td >岗位<br>保留</td>
		  	    <td >年休假<br>补班</td>
		  	    <td >年休假<br>多休</td>
			 <% }%>
		  	
		  </tr>
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  	
		  		<c:if test="${arData.EMPID ne NULL or arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">
		  		<tr align="center">	
				  		<% if( "78000000".equals(cpnyId)){ %>
				  		<td>${arData.DEPTNAME}</td>
				  		<% }else{%>
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && (arDataList[i.index - 1].DEPTNAME ne arData.DEPTNAME) }" >
							<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		<% }%>
				  		
					  	<c:if test="${arData.OFFICENAME ne arData.TEAMNAME}">  	
					  	<td>&nbsp;</td>
					  	<td>${arData.TEAMNAME}</td>
					  	</c:if>
					  	
					  	<c:if test="${arData.OFFICENAME eq arData.TEAMNAME}"> 
					  	<td>${arData.OFFICENAME}</td>
					  	<td>&nbsp;</td>
					  	</c:if>
					  	
					  	<td>${arData.EMPID}</td>
					  	
					  	<c:if test="${arData.TEAMNAME ne '小计' and arData.TEAMNAME ne '合计'}">  	
						  	<td>${arData.CHINESENAME}</td>
						  	<td>${arData.POST_GRADE}</td>
						</c:if>
						
						<c:if test="${arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">  	
						  	<td>&nbsp;</td>
						  	<td>&nbsp;</td>
						</c:if>
						
			    		<td >${arData.jiaoYu}</td>
					  	<td >${arData.chuChai}</td>
					  	<td >${arData.yanXiu}</td>
					  	<td >${arData.peiXun}</td>
					  	<td >${arData.yinGongWaiChu}</td>
					  	
					  	<td >${arData.puRuJia}</td>
					  	<td >${arData.SongJia}</td>
					  	<td >${arData.chanJiaHuLi}</td>
					  	<td >${arData.nianXiuJia}</td>
					  	
					  	
					  	<td >${arData.hunJia}</td>
					  	<td >${arData.chanQianJianCha}</td>
					  	<td >${arData.jiShengJia}</td>
					  	<td >${arData.jiHunDengJi}</td>
					  	
					  	<td >${arData.tiaoXiu}</td>
					  	
					  	<td >${arData.xiuJia}</td>
					  	<td >${arData.gongShangJia}</td>
					  	
					  	<td >${arData.xiuZhi}</td>
					  	
					  	<td >${arData.shiJia}</td>
					  	<td >${arData.kuangGong}</td>
					  	<td >${arData.bingJia}</td>
					  	
					  	<td >${arData.bingXiu}</td>
					  	<td >${arData.chiDao}</td>
					  	<td >${arData.zaoTui}</td>
					  	<td >${arData.fangJia1}</td>
					  	<td >${arData.fangJia2}</td>
					  	
					  	<td >${arData.chanJia}</td>
					  	<% if( "78000000".equals(cpnyId)){ %>
				  		<td >${arData.gangWeiBaoLiu}</td>
				  		<td >${arData.nianXiuJiaBuBan}</td>
				  		<td >${arData.nianXiuJiaDuoXiu}</td>
				  		<% }%>
				</tr>
		    	</c:if>
		  </c:forEach>
		  
		</table>
	</body>
</html>

