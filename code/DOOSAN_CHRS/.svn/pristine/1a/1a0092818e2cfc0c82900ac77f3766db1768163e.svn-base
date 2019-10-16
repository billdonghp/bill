<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.util.*"%>
<jsp:useBean id="allEateryType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="result" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allEateryTypeSize" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	response.setHeader("Content-Type",
	"application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition",
	"attachment; filename=eateryPeopleReport.xls");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0" align="center">

	<tr align="center" height="30">
		<td align="center">
		<table width="100%" align="center" style="font-family:宋体;">
			<tr align="center">
				<td align="center" colspan="${allEateryTypeSize+allEateryTypeSize+3}"><b><font size="15pt"><%=request.getParameter("startdate") %>至<%=request.getParameter("enddate") %> &nbsp;&nbsp;&nbsp;就餐人数统计表</font></b></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;font-family:宋体;font-size: 10pt;">

		<tr>
	  	<td class="info_title_01" nowrap colspan="1" align="center">&nbsp;</td>
	  	<td class="info_title_01" nowrap colspan="5" align="center">员工就餐</td>
	  	<td class="info_title_01" nowrap colspan="5" align="center">客人就餐(临时卡)</td>
	  </tr>
	  
	  <tr>
	  	<td class="info_title_01" nowrap colspan="1" align="center">部门</td>
	  	
	  	<c:forEach items="${allEateryType}" var="all" varStatus="i">
	  		<td class="info_title_01" nowrap colspan="1" align="center">${all.GM_TYPE}</td>
	  	</c:forEach>
	  	<td class="info_title_01" nowrap colspan="1" align="center">总人数</td>
	  	
	  	<c:forEach items="${allEateryType}" var="all" varStatus="i">
	  		<td class="info_title_01" nowrap colspan="1" align="center">${all.GM_TYPE}</td>
	  	</c:forEach>
	  	<td class="info_title_01" nowrap colspan="1" align="center">总人数</td>
	  </tr>
	  <%
	  		SimpleMap map = null;
	  		SimpleMap map1 = null;
	  		SimpleMap map2 = null;
	  		String dept = "";
	  		String numEmp = "";
	  		String numEmpTotal = "";
	  		String numProvisionalEmp = "";
	  		String numProvisionalTotal = "";
	  		String eateryType = "";
	  		for(int i=0 ; i<result.size() ; i++){
	  			map = (SimpleMap) result.get(i);
	  			dept = map.getString("DEPTNAME");
	  			
	  			numEmpTotal = map.getString("员工就餐合计");
	  			numProvisionalTotal = map.getString("客人就餐合计");
	  %>
	  <tr>
	  		<%if(dept != null){ %>
	  		<td align="center">
	  			<%=dept %>
	  		</td>
	  		<%} %>
	  		<%if(dept == null || dept == ""){ %>
	  		<td align="center">
	  			合计
	  		</td>
	  		<%} %>
	  			<%
	  				for(int j=0 ; j<allEateryType.size() ; j++){
	  					map1 = (SimpleMap) allEateryType.get(j);
	  					eateryType = map1.getString("GM_TYPE");
	  					numEmp = map.getString(eateryType+"人数");
	  			%>
		  		<td align="center">
		  			<%=numEmp %>
		  		</td>
		  		<% 
	  				}
		  		%>
		  		<td align="center">
		  			<%=numEmpTotal %>
		  		</td>
		  		<%
	  				for(int j=0 ; j<allEateryType.size() ; j++){
	  					map2 = (SimpleMap) allEateryType.get(j);
	  					eateryType = map2.getString("GM_TYPE");
	  					numProvisionalEmp = map.getString("临时"+eateryType);
	  			%>
		  		<td align="center">
		  			<%=numProvisionalEmp %>
		  		</td>
		  		<% 
	  				}
		  		%>
		  		<td align="center">
		  			<%=numProvisionalTotal %>
		  		</td>
	  </tr>
	  <%
	  		}
	  %>
		</table>
		</td>
	</tr>
</table>
</body>
</html>