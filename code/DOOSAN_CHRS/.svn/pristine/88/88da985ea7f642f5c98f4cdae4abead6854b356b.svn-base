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
		response.setHeader("Content-Disposition", "attachment; filename=report0121.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateStarted")) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("arDateEnd")) ;
		
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="35" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="37" ><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;出勤情况报表</td>
		  </tr>
		  <tr align="center">
		  	<td rowspan="3">部门</td>
		  	<td rowspan="3">人数</td>
		  	<td rowspan="3">正常<br>出勤</td>
		  	<td colspan="31">异常出勤</td>
		  	<td rowspan="3">正常&非不良勤态率</td>
		  	<td rowspan="3">不良勤态率</td>
		  	<td rowspan="3">备注</td>
		  </tr>
		  
		  <tr align="center">
		  	<td colspan="21">非不良勤态</td>
		  	<td colspan="10">不良勤态</td>
		  
		  </tr>
		  
		  <tr align="center">
		  	<td>年休假</td>
		  	<td>值班</td>
		  	<td>出差</td>
		    <td>研修</td>
		    <td>培训</td>
		    <td>因公外出</td>
		    <td>调休</td>
		    <td>产假</td>
		    <td>产前检查</td>
		    <td>计生假</td>
		    <td>产假护理假</td>
		    <td>婚假</td>
		    <td>丧假</td>
		    <td>结婚登记假</td>
		    <td>哺乳假</td>
		    <td>岗位保留</td>
		    <td>放假</td>
		    <td>团聚假</td>
		    <td>年休假补班</td>
		    <td>年休假多休</td>
		    <td>小计</td>
		    <td>迟到</td>
		    <td>早退</td>
		    <td>旷工</td>
		    <td>事假</td>
		    <td>休职</td>
		    <td>病假</td>
		    <td>工伤假</td>
		    <td>病休</td>
		    <td>未勤</td>
		    <td>小计</td> 
		  </tr>

		  
		  <c:forEach items="${arDataList}" var="arData" varStatus="i">
		  		<tr align="center">	

				  		<c:if test="${arData.HEJI=='合计'}">	
					  	<td>合计</td>
					  	</c:if>
					  	<c:if test="${arData.HEJI=='小计'}">	
					  	<td>${arData.DEPTNAME}</td>
					  	</c:if>

					  	<td >${arData.RENSHU}</td>
						  	
			    		<td >${arData.ANSHICHUQIN}</td>
					  	<td >${arData.NIANXIUJIA}</td>
					  	<td >${arData.ZHIBAN}</td>
					  	<td >${arData.CHUCHAI}</td>
					  	<td >${arData.YANXIU}</td>
					  	<td >${arData.PEIXUN}</td>
					  	<td >${arData.YINGONGWAICHU}</td>
					  	<td >${arData.TIAOXIU}</td>
					  	<td >${arData.CHANJIA}</td>
					  	<td >${arData.CHANQIANJIANCHA}</td>
					  	<td >${arData.JISHENGJIA}</td>
					  	<td >${arData.CHANJIAHULI}</td>
					  	<td >${arData.HUNJIA}</td>
					  	<td >${arData.SANGJIA}</td>
					  	<td >${arData.JIHUNDENGJI}</td>
					  	<td >${arData.PURUJIA}</td>
					  	<td >${arData.GANGWEIBAOLIU}</td>
					 	<td >${arData.FANGJIA}</td> 						  	
					  	<td >${arData.TUANJUJIA}</td>
					  	<td >${arData.NIANXIUJIABUBAN}</td>
					  	<td >${arData.NIANXIUJIADUOXIU}</td>
					  	<td >${arData.HEJI1}</td>

				
				
					  	<td >${arData.CHIDAO}</td>
					  	<td >${arData.ZAOTUI}</td>
					  	<td >${arData.KUANGGONG}</td>
					  	<td >${arData.SHIJIA}</td>
					  	<td >${arData.XIUZHI}</td>
					  	<td >${arData.BINGJIA}</td>
					  	<td >${arData.GONGSHANGJIA}</td>
					  	<td >${arData.BINGXIU}</td>
					  	<td >${arData.WEIQIN}</td>
					  	<td >${arData.HEJI2}</td>
					  	
					  	<td>
							 	<fmt:formatNumber value="${1 - arData.BILI2}" type="percent" maxFractionDigits="2"/>
						 </td>
						 <td>
							 	<fmt:formatNumber value="${arData.BILI2}" type="percent" maxFractionDigits="2"/>
						 </td>
					    <td ></td>
					  
				</tr>
		  </c:forEach>
		  
		</table>
	</body>
</html>

