<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<%@ page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ait.ar.bean.ArItem"%>
<%@ include file="../../inc/taglibs.jsp"%>
<%@page import="com.ait.sqlmap.util.SimpleMap"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
			response.setHeader("Content-Type",
			"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
			"attachment; filename=report0112.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<body>
<table width="1024">
	<tr align="center">
		<td colspan="5"></td>
	</tr>
</table>
<table width="1024" border="1" align="center">
	<tr align="center">
		<td rowspan="1">职号</td>
		<td rowspan="1">姓名</td>
		<td rowspan="1">部门</td>
		<%
			List itemList = (List) request.getAttribute("itemList");
			Iterator iter = itemList.iterator();
			for (; iter.hasNext();) {
				ArItem arItem = (ArItem) iter.next();
			String itemUnit = "";	
			if("S1".equals(arItem.getShortName())||"S2".equals(arItem.getShortName())){
				itemUnit = "(分钟)";
			}else{
				itemUnit = "(小时)";
			}	
		%>
		<td rowspan="1"><%=arItem.getItemName()%><%=itemUnit%></td>
		<%
		}
		%>
	</tr>

	<%
		List dataList = (List) request.getAttribute("arDataList");
		Iterator dataIter = dataList.iterator();
		for (; dataIter.hasNext();) {
		SimpleMap map = (SimpleMap)dataIter.next();
	%>
	<tr align="center">
		<td rowspan="1"><%=map.getString("EMPID") %></td>
		<td rowspan="1"><%=map.getString("CHINESENAME") %></td>
		<td rowspan="1"><%=map.getString("DEPTNAME") %></td>
		<%
			List itemList1 = (List) request.getAttribute("itemList");
			Iterator iter1 = itemList1.iterator();
			for (;iter1.hasNext();) {
				ArItem arItem = (ArItem) iter1.next();
		%>
		<td rowspan="1"><%=map.getString(arItem.getItemId())%></td>
		
		
		 
		<%
		}
		%>
	</tr>
	<%
	}
	%>
</table>
</body>
</html>

