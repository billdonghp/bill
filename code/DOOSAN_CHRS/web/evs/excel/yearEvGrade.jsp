<%@ page contentType="text/html; charset=UTF-8" language="java" 
	import="org.apache.log4j.Logger,java.util.*,com.ait.utils.*,com.ait.util.*,com.ait.evs.EvsPeriod" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
ActivityReport pareport = new ActivityReport();
String year= StringUtil.checkNull(request.getParameter("evYear"));
String type= StringUtil.checkNull(request.getParameter("evType"));
if (!year.equals("")&&!type.equals("")){
Vector list = new Vector();
Vector data = new Vector();
StringBuffer sql=new StringBuffer();
StringBuffer selMain =new StringBuffer();
StringBuffer tableMain=new StringBuffer();

StringBuffer selPeriod =new StringBuffer();
StringBuffer tablePeriod=new StringBuffer();
StringBuffer conPeriod=new StringBuffer();


selMain.append("SELECT e.ev_emp_id as 工号,h.chinesename as 姓名,h.deptfullname as 部门  ") ;
tableMain.append(" FROM (SELECT DISTINCT c.ev_emp_id ");
tableMain.append("                   FROM evs_emp c ");
tableMain.append("                  WHERE EXISTS ( ");
tableMain.append("                           SELECT * ");
tableMain.append("                             FROM (SELECT * ");
tableMain.append("                                     FROM evs_period a "); 
tableMain.append("                                   WHERE a.ev_year = '"+year+"' ");
tableMain.append("                                      AND EXISTS ( ");
tableMain.append("                                             SELECT * ");
tableMain.append("                                              FROM evs_period_type b ");
tableMain.append("                                             WHERE ev_type_id = '"+type+"' ");
tableMain.append("                                                AND a.ev_period_id = ");
tableMain.append("                                                                b.ev_period_id)) d ");
tableMain.append("                            WHERE d.ev_period_id = c.ev_period_id)) e ");
tableMain.append(",hr_employee_v h ");
EvsPeriod evsPeriod=new EvsPeriod();

List<EvsPeriod> lEvsPeriod=evsPeriod.getEvPeriodByTypeAndYear(year,type);

for(int i=0,j=lEvsPeriod.size();i<j;i++){

	EvsPeriod evs=lEvsPeriod.get(i);
	
	selPeriod.append(", get_code_name(\""+evs.getEvPeriodID()+"\""+".grade) as "+"\""+evs.getEvPeriodName()+"\" ");
	tablePeriod.append(",(SELECT ev_grade_id AS grade, ev_emp_id, ev_period_id FROM evs_emp) "+"\""+evs.getEvPeriodID()+"\" " );
	
	conPeriod.append("  AND \""+evs.getEvPeriodID()+"\""+".ev_emp_id(+) = e.ev_emp_id  AND  "+"\""+evs.getEvPeriodID()+"\""+".ev_period_id(+) = '"+evs.getEvPeriodID()+"'");
}
sql.append(selMain);
sql.append(selPeriod);
sql.append(tableMain);
sql.append(tablePeriod);
sql.append(" WHERE h.empid=e.ev_emp_id ");
sql.append(conPeriod);
sql.append(" order by e.ev_emp_id ");
	
	
Logger.getLogger(getClass()).error(StringUtil.toISO(sql.toString()));
list = pareport.DataSelect(sql.toString());
String tabledata = "";
String tr="";
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=evGrade_"+year+".xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>

<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<%
				if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
			pareport = (ActivityReport) list.elementAt(i);
	%>
	<tr align="center" style="font-size:9pt">
		<%
				data = pareport.getData();
				tr = "";
				if (data.size() != 0) {
					for (int j = 0; j < data.size(); j++) {
						tabledata = (String) data.elementAt(j);
						tr += "<td>" + tabledata + "</td>";
					}
		%>
		<%=tr%>
		<%
		}
		%>
	</tr>
	<%
			}
			}
	%>
</table>
<%
}
%>