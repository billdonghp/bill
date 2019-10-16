<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>人 件 费 统 计</title></head>

 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=report0221.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0"); 
		
		String columns[] = {"distinction","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","total"} ;
		
		List recordList = (ArrayList)request.getAttribute("recordList")  ;
		List recordList2 = (ArrayList)request.getAttribute("recordList2")  ;
%>

	<body>
		<table>
		  <tr align="center">
		  	<td colspan="14" ></td>
		  </tr>
		</table>
		<table border="1" align="center">
		  
		  <tr align="center">
		  	<td colspan="14" >人 件 费 统 计</td>
		  </tr>
		  <tr><td colspan="14">&nbsp;</td></tr>	
		  <tr><td colspan="14">按人员区分人件费统计</td></tr>
		  <tr align="center">
		  	<td colspan="14" >${year} 实绩</td>
		  </tr>
		  <tr>
		  <%
		  	for (int i = 0 ; i < columns.length ; ++i )
		  	{
		  		if (columns[i].equals("distinction")){
		  			out.println("<td>区分</td>") ;
		  		}
		  		else if (columns[i].equals("total")){
		  			out.println("<td>合计</td>") ;
		  		}
		  		else {
		  			out.println("<td>" + columns[i] + "</td>") ;
		  		}
		  	}
		  		
		  %>
		  </tr>
		  <%
		  	for(int j = 0 ; j < recordList.size(); ++j)
		  	{
		  		out.println("<tr>") ;
		  		SimpleMap record = (SimpleMap)recordList.get(j);
		  		//record.setNullToInitialize(false) ;
			  	for (int i = 0 ; i < columns.length ; ++i )
			  	{
			  		out.println("<td>" + StringUtil.checkNull(record.getString(columns[i]),"0") + "</td>") ;
			  	}
			  	out.println("</tr>") ;
		  	}	
		  %>
		  <tr><td colspan="14">&nbsp;</td></tr>	
		  <tr><td colspan="14">按发放薪资类别人件费统计</td></tr>
		  
		  <tr>
		  <%
		  	for (int i = 0 ; i < columns.length ; ++i )
		  	{
		  		if (columns[i].equals("distinction")){
		  			out.println("<td>区分</td>") ;
		  		}
		  		else if (columns[i].equals("total")){
		  			out.println("<td>合计</td>") ;
		  		}
		  		else {
		  			out.println("<td>" + columns[i] + "</td>") ;
		  		}
		  	}
		  		
		  %>
		  </tr>
		  <%
		  	for(int j = 0 ; j < recordList2.size(); ++j)
		  	{
		  		out.println("<tr>") ;
		  		SimpleMap record = (SimpleMap)recordList2.get(j);
		  		//record.setNullToInitialize(false) ;
			  	for (int i = 0 ; i < columns.length ; ++i )
			  	{
			  		out.println("<td>" + StringUtil.checkNull(record.getString(columns[i]),"0") + "</td>") ;
			  	}
			  	out.println("</tr>") ;
		  	}	
		  %>
		  <tr><td colspan="14">&nbsp;</td></tr>	
		  <tr ><td colspan="14" >人员统计</td></tr>
		  <tr>
		  <%
		  	for (int i = 0 ; i < columns.length ; ++i )
		  	{
		  		if (columns[i].equals("distinction")){
		  			out.println("<td>区分</td>") ;
		  		}
		  		else if (columns[i].equals("total")){
		  			out.println("<td>合计</td>") ;
		  		}
		  		else {
		  			out.println("<td>" + columns[i] + "</td>") ;
		  		}
		  	}
		  		
		  %>
		  </tr>
		  <%
		  	for(int j = 0 ; j < recordList.size(); ++j)
		  	{
		  		out.println("<tr>") ;
		  		SimpleMap record = (SimpleMap)recordList.get(j);
		  		//record.setNullToInitialize(false) ;
			  	for (int i = 0 ; i < columns.length ; ++i )
			  	{
			  		out.println("<td>" + StringUtil.checkNull(record.getString(columns[i] + "_PER_SUM") , "0") + "</td>") ;
			  	}
			  	out.println("</tr>") ;
		  	}	
		  %>
		</table>
	</body>
</html>

