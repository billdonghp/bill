<%@ page contentType="application/vnd.NOTEPAD; charset=UTF-8" language="java" import="com.ait.utils.*,com.ait.pa.PaReport,java.util.Vector,com.ait.util.StringUtil" %><%
String pamonth = StringUtil.checkNull(request.getParameter("PaMonth"));
String  column = "select CostCenter,VenderCode,Expense_Type as ExpensType,DebitCredit,Value,dept as DeptCode from PA_EXPORT_SAP_V where pamonth = '"+pamonth+"'";
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
if (vlist.size()!=0)
{
response.setHeader("Content-Disposition", "inline; filename=sap_"+pamonth+".txt");   
response.setHeader("Content-Description", "JSP Generated Data");
for ( int i = 0 ; i < vlist.size() ; i++ )
{ 		  
pareport=(PaReport)vlist.elementAt(i);
data = pareport.getData();
tr="";
 if (data.size()!=0) {
	for (int j=0;j<data.size();j++) {
		tabledata=(String)data.elementAt(j) ;
		tr +=tabledata;
		if (data.size()-j>1){
		tr+="	";
		}
	}
out.println(tr);
}}}%>