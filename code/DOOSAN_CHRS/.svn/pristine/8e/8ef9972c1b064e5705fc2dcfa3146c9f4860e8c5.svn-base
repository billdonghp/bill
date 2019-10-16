<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>勤态报表</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=EvaluateResultSumReport.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 

%>

	<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">	
   <tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center">
			<tr align="center">
				<td align="center" colspan="4"><b><font size="+2">${yearSum}年评价累计报表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>	
		<td>
		<table width="100%"  border="1" >
		    <tr align="center" >
			<td  nowrap>职号</td>
		    <td  nowrap>姓名</td>	
		    <td  nowrap>部门</td>
		  	<td  nowrap>详情</td>	
		  </tr>
		<c:forEach items="${evaluateList}" var="oneResult" varStatus="i">
		  <tr align="center">
		    <td >${oneResult.EMPID}</td>
		    <td >${oneResult.CHINESENAME}</td>
		    <td >${oneResult.DEPTNAME}</td>
		    <td >${oneResult.REMARK}</td>
		  </tr>
		</c:forEach>
		</table>
		</tr>            
		
		</table>
</html>


