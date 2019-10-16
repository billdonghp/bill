<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" import="com.ait.utils.*,com.ait.pa.PaReport,com.ait.sy.bean.AdminBean,java.util.Vector,com.ait.sy.bean.AdminBean" %>
<%
	Func func = new Func();
	String column = func.isoStr(request.getParameter("sql"));
	PaReport pareport = new PaReport();
	Vector vlist = new Vector();
	Vector data = new Vector();
	if (column != null){
		vlist = pareport.DataSelect(column);
	}
	else{
		vlist = pareport.DataSelect();
	}
	String tabledata = "";
	String tr="";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<% 
	if (vlist.size()!=0){
		response.setHeader("Content-Disposition", "inline; filename=PA_DETAIL.xls");   
		response.setHeader("Content-Description", "JSP Generated Data");
		for ( int i = 0 ; i < vlist.size() ; i++ ){ 		  
			pareport=(PaReport)vlist.elementAt(i);
%>
	<tr align="center" style="font-size:9pt"> 
<% 
	data = pareport.getData();
	tr="";
	if (data.size()!=0) {
		for (int j=0;j<data.size();j++) {
			tabledata=(String)data.elementAt(j) ;
				tr +="<td> "+tabledata+"</td>";
		}%>
		<%=tr%>
	<%}%>
	</tr>
<%}}%>
</table>

