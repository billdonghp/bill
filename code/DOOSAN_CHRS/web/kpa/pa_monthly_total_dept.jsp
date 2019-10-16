<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" import="com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil,com.ait.pa.PaReport" %>
<%
PaReport pareport = new PaReport();
String pamonth= StringUtil.checkNull(request.getParameter("PaMonth"));
if (!pamonth.equals("")){
Vector vlist = new Vector();
Vector data = new Vector();
vlist = pareport.DataSelect(pareport.Group_Sql,pamonth);
String tabledata = "";
String tr="";
%>


<table border="1" cellspacing="0" cellpadding="0">
<% 
if (vlist.size()!=0)
{
response.setHeader("Content-Disposition", "inline; filename=Pa_Monthly_Total.xls");   
response.setHeader("Content-Description", "JSP Generated Data");
for ( int i = 0 ; i < vlist.size() ; i++ )
{ 		  
pareport=(PaReport)vlist.elementAt(i);
%>
<tr align="center" style="font-size:9pt"> 
<% data = pareport.getData();
 tr="";
 if (data.size()!=0) {
for (int j=0;j<data.size();j++) {
tabledata=(String)data.elementAt(j) ;
tr +="<td>"+tabledata+"</td>";
}%><%=tr%><%}%>
</tr><%}}%>
</table>
<%}%>