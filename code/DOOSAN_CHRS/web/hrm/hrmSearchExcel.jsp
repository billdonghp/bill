<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="com.ait.util.StringUtil,
			com.ait.utils.ActivityReport,
			java.util.Vector" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>	
	<%
		ActivityReport report = new ActivityReport();
		Vector vlist = new Vector();
		Vector data = new Vector();
		String sql = StringUtil.toCN(StringUtil.checkNull(request.getParameter("hole_sql")));
		if (!sql.equals("")) vlist = report.DataSelect(sql);
		int[] sqlTypes = report.getSqlTypes();
		String tabledata = "";
		String tr="";
	%>
	<table border="1" cellspacing="0" cellpadding="0">
		<% 
		if (vlist.size()!=0){
			response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
			response.setHeader("Content-Disposition", "inline; filename=SearchResult.xls");   
			response.setHeader("Content-Description", "JSP Generated Data");
			for ( int i = 0 ; i < vlist.size() ; i++ ){ 		  
				report=(ActivityReport)vlist.elementAt(i);%>
				<tr align="center" style="font-size:9pt; font-style: normal; font-family: 宋体"> 
				<% 
					data = report.getData();
					tr="";
					if (data.size()!=0) {
						for (int j=0;j<data.size();j++) {
							tabledata=(String)data.elementAt(j);
							 tr += "<td>";
							 if (sqlTypes[j] != java.sql.Types.NUMERIC)
							    tr += "&nbsp;";
							tr += tabledata + "</td>";
						}%>
						<%=tr%>
					<%}%>
				</tr>
			<%}
		}%>
	</table>
</body>
</html>