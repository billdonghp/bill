<%@ page contentType="application/vnd.ms-excel; charset=UTF-8" language="java" import="com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil" %>
<%
ActivityReport pareport = new ActivityReport();
String pamonth= StringUtil.checkNull(request.getParameter("PaMonth"));
if (!pamonth.equals("")){
Vector vlist = new Vector();
Vector data = new Vector();
vlist = pareport.DataSelect("SELECT 地区,姓名,工号,Base,岗位职务津贴,职责津贴,固定OT,固定工资,SI奖金,午餐费,本月所得工资,社保基数,公社养老,个人养老,公社医疗,个人医疗,公社失业,个人失业,公社工伤,公社生育,公社保险合计,个人保险合计,公积金基数,公社住房基金,个人住房基金,缺勤扣款,不足扣款,应税所得,个人所得税,税后所得,管理费,总人件费 FROM PA_history where 工资月 = '"+pamonth+"' order by 地区 ");
String tabledata = "";
String tr="";
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<% 
if (vlist.size()!=0)
{
response.setHeader("Content-Disposition", "inline; filename=Pa_Monthly_Total.xls");   
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
<%}%>