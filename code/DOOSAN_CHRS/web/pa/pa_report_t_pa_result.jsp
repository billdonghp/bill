<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="com.ait.pa.business.PaServices" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=t_pa_result.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		SimpleMap columnMap = new SimpleMap() ;
		
		// bind parameter
		String[] columns = request.getParameterValues("column") ;
		
		StringBuffer sqlColumn = new StringBuffer(1000) ; 
		StringBuffer sqlColumn2 = new StringBuffer(1000) ; 
		
		sqlColumn.append("''") ;
		for (int i = 0 ; i < columns.length ; ++i)
		{
			sqlColumn.append(",'" + columns[i] + "'") ;
		}
		
		parameterObject.set("sqlColumn", sqlColumn) ;
		parameterObject.set("paMonth", request.getParameter("pamonth")) ;
		parameterObject.set("statTypeCode", request.getParameter("statTypeCode")) ;
		parameterObject.set("cpnyId", admin.getCpnyId()) ;
		
%>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr align="center" > 
<%
		List columnList = services.retrieveTPaResultColumnList(parameterObject);
		
		sqlColumn2.append(" '' AS \"TEMP\"") ;
		for(int i = 0 ; i < columnList.size() ; ++i )
		{
			columnMap = (SimpleMap)columnList.get(i) ;
			sqlColumn2.append(" ," + columnMap.getString("ID")) ;
%>
		
		  	<td ><%= columnMap.getString("NAME") %></td>
		
<%			 
		}
%>		
		</tr>
<%			
		parameterObject.set("sqlColumn2", sqlColumn2) ;
		parameterObject.setInt("columnSize", columnList.size() + 1) ;

		List resultList = services.retrieveTPaResultDataList(parameterObject) ;
		List dateList = new ArrayList() ;

		for(int i = 0 ; i < resultList.size() ; ++i )
		{
%>
		<tr align="center" >
<%
			dateList = (ArrayList)resultList.get(i) ;
			for(int j = 0 ; j < dateList.size() ; ++j )
			{
%>
			<td ><%= StringUtil.checkNull(dateList.get(j)) %></td>
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

