<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年平均工资计划</title>
</head>                              
<body>
<%
ActivityReport pareport = new ActivityReport();
String seqid= StringUtil.checkNull(request.getParameter("seqid"));
String dept= StringUtil.checkNull(request.getParameter("deptid"));
String planyear= StringUtil.checkNull(request.getParameter("planyear"));
if (!planyear.equals("")){
Vector list = new Vector();
Vector data = new Vector();
String sql =  " select rownum as \"编号\", "+
                " code.code_name as \"职群\", "+
                " grade.post_grade_name as \"职级\", "+
                " t.bg_nm as \"部门\", "+
                " nvl(t.plan_inwon,0) as \"人员计划\", "+
                " nvl(t.pay_inc,0) as \"工资上涨比率\",  "+
                " nvl(t.pay_bonus_inc,0) as \"奖金上涨比率\", "+
                " nvl(t.plan_pay01_mon,0) as \"基本工资\", "+
                " nvl(t.plan_pay02_mon,0) as \"奖金\", "+
                " nvl(t.plan_pay03_mon,0) as \"业绩工资\", "+
                " nvl(t.plan_pay05_mon,0) as \"其他补助\", "+
                " nvl(t.plan_tot_mon,0) as \"合计\" "+
                " from py_pay_plan t,sy_code code,hr_post_grade grade "+
				" where t.auth_group_type=code.code_id(+) "+
				" and t.pos_type=grade.post_grade_id(+) "+
				" and t.plan_yy='"+planyear+"' "+
				" and t.bg_gu='"+dept+"' "+
				" and t.seq_id='"+seqid+"' " ;

				        

Logger.getLogger(getClass()).debug(StringUtil.toISO(sql));
list = pareport.DataSelect(sql);
String tabledata = "";
String tr="";
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=averagePayPlanReports.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<%
				if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
			pareport = (ActivityReport) list.elementAt(i);
	%>
	<tr align="center" style="font-size:9pt;font-family:宋体;">
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