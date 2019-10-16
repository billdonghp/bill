<%@ page language="java" contentType="application/vnd.ms-excel; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%
			response.setHeader("Content-disposition",
			"attachment;filename=EXPORT_FOOD_APPLY.xls");
%>
<%@page import="com.ait.sqlmap.util.SimpleMap"%>
<%@page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Iterator"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="application/vnd.ms-excel; charset=UTF-8">
</head>
<body>
<table width="100%" style="border-collapse:collapse" border="1"
	height="60" cellpadding="5">
	<tr>
		<td width="3%" align="center">星期</td>
		<td width="3%" align="center">日期</td>
		<c:forEach items="${schemeName }" var="oneResult">
			<td width="3%" align="center">${oneResult.SEQ_NO }</td>
		</c:forEach>
		<c:forEach items="${foodNameList }" var="twoResult">
			<td width="3%" align="center">${twoResult.FOOD_NAME }</td>
		</c:forEach>
	</tr>
	<%
		SimpleMap map1=null;
		SimpleMap map2=null;
		SimpleMap schemeMap=null;
		SimpleMap foodMap=null;
		Iterator iter1 = ((List)request.getAttribute("recordSchemeList")).iterator();
		Iterator iter2 = ((List)request.getAttribute("recordFoodList")).iterator();

		for(int i=0;i<((List)request.getAttribute("recordSchemeList")).size();i++){
			if(iter1.hasNext()){
				map1 = (SimpleMap)iter1.next();
			}
			if(iter2.hasNext()){
				map2 = (SimpleMap)iter2.next();
			}
			Iterator schemeIter = ((List)request.getAttribute("schemeName")).iterator();
			Iterator foodIter = ((List)request.getAttribute("foodNameList")).iterator();
	%>
		<tr align="center">
			<td align="center" height="30"><%=map1.getString("WEEK") %></td>
			<td align="center"><%=map1.getString("APPLY_DATE") %></td>
			<%
				for(;schemeIter.hasNext();){
					schemeMap = (SimpleMap)schemeIter.next();
			%>
				<td align="center"><%=map1.getString(schemeMap.getString("SEQ_NO")) %></td>
			<%
				}
			%>
			<%
				for(;foodIter.hasNext();){
					foodMap = (SimpleMap)foodIter.next();
			%>
				<td align="center"><%=map2.getString(foodMap.getString("FOOD_NAME")) %></td>
			<%
				}
			%>
		</tr>
	<%
		}
	%>
	<tr align="center">
		<td align="center">人数</td>
		<td align="center">${quentity }</td>
	</tr>
	<tr align="center">
		<td align="center">费用</td>
		<td align="center">${price }(元)</td>
	</tr>
</table>
</body>
</html>
