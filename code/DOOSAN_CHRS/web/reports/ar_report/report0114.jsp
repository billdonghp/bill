<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0114.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		
		Date arDateStarted = new SimpleDateFormat("yyyy-MM-dd").parse(request.getAttribute("startDate").toString()) ;
		Date arDateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(request.getAttribute("endDate").toString()) ;
%>

	<body>
		<table width="1024">
		  <tr align="center">
		  	<td colspan="36" ></td>
		  </tr>
		</table>
		<table width="1024" border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="36" ><fmt:formatDate value="<%= arDateStarted %>" pattern="yyyy年M月d日"/>~
		  	<fmt:formatDate value="<%= arDateEnd %>" pattern="yyyy年M月d日"/>&nbsp;&nbsp;&nbsp;&nbsp;日勤态报表</td>
		  </tr>
		  <tr><td colspan="36">&nbsp;</td></tr>	
		  <tr align="center">
		  	<td rowspan="2">部<br>门</td>
		  	<td rowspan="2">课<br>别</td>
		  	<td rowspan="2">班<br>别</td>
		  	<td rowspan="2">职<br>号</td>
		  	<td rowspan="2">姓<br>名</td>
		  	<td rowspan="2">区<br>分</td>
		  </tr>
		  
		  <tr align="center">
		  	<td >平日<br>加班</td>
		  	<td >休息<br>日加班</td>
		  	<td >节假<br>日加班</td>
		  	
		  	<td >出<br>差</td>
		  	<td >教<br>育</td>
		  	<td >培<br>训</td>
		  	<td >研<br>修</td>
		  	
		  	<td >事<br>假</td>
		  	<td >哺乳<br>假</td>
		  	<td >产假<br>护理</td>
		  	<td >计生<br>假</td>
		  	<td >病<br>休1</td>
		  	<td >病<br>休2</td>
		  	<td >放<br>假1</td>
		  	<td >放<br>假2</td> 	
		  	<td >结婚<br>登记假</td>
		  	<td >产前<br>检查</td>	
		  	<td >调<br>休</td>
		  	<td >有薪<br>产假</td>
		  	<td >无薪<br>工伤假</td>
		  	<td >因公<br>外出</td>
		  	<td >有薪<br>放假2</td>
		  	<td >有薪<br>病假</td>
		  	<td >病<br>假</td>
		  	<td >有薪<br>工伤假</td>
		  	<td >婚<br>假</td>
		  	<td >丧<br>假</td>
		  	<td >无薪<br>产假</td>
		  	<td >休<br>职</td>
		  	<td >年休<br>假</td>
		  	
		  </tr>
		  <c:forEach items="${recordList}" var="arData" varStatus="i">
		  	
		  		<c:if test="${arData.EMPID ne NULL or arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">
		  		<tr align="center">	
				  		
				  		<c:if test="${i.index == 0}" >
				  			<td rowspan="${arData.DEPT_CNT}">${arData.DEPTNAME}</td>
				  		</c:if>
				  		
				  		<c:if test="${i.index > 0 && recordList[i.index - 1].DEPTNAME ne arData.DEPTNAME }" >
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
					  	
					  	<td>${arData.EMPID}</td>
					  	
					  	<c:if test="${arData.TEAMNAME ne '小计' and arData.TEAMNAME ne '合计'}">  	
						  	<td>${arData.CHINESENAME}</td>
						  	<td>${arData.POST_GRADE}</td>
						</c:if>
						
						<c:if test="${arData.TEAMNAME eq '小计' or arData.TEAMNAME eq '合计'}">  	
						  	<td>&nbsp;</td>
						  	<td>&nbsp;</td>
						</c:if>
						
			    		<td >${arData.WORKDAY_OVERTIME}</td>
					  	<td >${arData.RESTDAY_OVERTIME}</td>
					  	<td >${arData.HOLIDAY_OVERTIME}</td>
					  	
					  	<td >${arData.B1}</td>
					  	<td >${arData.B2}</td>
					  	<td >${arData.B3}</td>
					  	<td >${arData.B4}</td>
					  	
					  	<td >${arData.H1}</td>
					  	<td >${arData.H10}</td>
					  	<td >${arData.H11}</td>
					  	<td >${arData.H12}</td>
					  	<td >${arData.H13}</td>
					  	<td >${arData.H14}</td>
					  	<td >${arData.H18}</td>
					  	<td >${arData.H19}</td>
					  	<td >${arData.H2}</td>
					  	<td >${arData.H21}</td>
					  	<td >${arData.H22}</td>
					  	<td >${arData.H23}</td>
					  	<td >${arData.H26}</td>
					  	<td >${arData.H27}</td>
					  	<td >${arData.H28}</td>
					  	<td >${arData.H29}</td>
					  	<td >${arData.H3}</td>
					  	<td >${arData.H4}</td>
					  	<td >${arData.H5}</td>
					  	<td >${arData.H6}</td>
					  	<td >${arData.H7}</td>
					  	<td >${arData.H8}</td>
					  	<td >${arData.H9}</td>		  	
  
				</tr>
		    	</c:if>
		  </c:forEach>
		  
		</table>
	</body>
</html>

