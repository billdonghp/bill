<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" import="com.ait.utils.*,java.util.Vector" %>
<%
ActivityReport pareport = new ActivityReport();
Vector vlist = new Vector();
Vector data = new Vector();
vlist = pareport.DataSelect("select * from pa_item_V");
String tabledata = "";
Func func = new Func();
String tr="";
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<% 
if (vlist.size()!=0)
{
response.setHeader("Content-Disposition", "inline; filename=hrdetail.xls");   
response.setHeader("Content-Description", "JSP Generated Data");
for ( int i = 0 ; i < vlist.size() ; i++ )
{ 		  
pareport=(ActivityReport)vlist.elementAt(i);
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