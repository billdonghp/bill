<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>月平均工资计划</title>
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
String sql = " select t.bg_nm, "+
	         " code.code_name as \"职群\", "+
	         " grade.post_grade_name as \"职级\", "+
	         " case "+
			  " when t.pay_type = 'PLAN_PAY01' then "+
			  "  '基本工资' "+
			  " when t.pay_type = 'PLAN_PAY02' then "+
			  "  '奖金' "+
			  " when t.pay_type = 'PLAN_PAY03' then "+
			 "   '业绩工资' "+
			  " when t.pay_type = 'PLAN_PAY05' then "+
			 "   '其他补助' "+
			"   else "+
			"    '工资项目' "+
			" end as \"工资项目\", "+
			" t.pay_month_01 as \"1月\", "+
			" t.pay_month_02 as \"2月\", "+
			" t.pay_month_03 as \"3月\", "+
			" t.pay_month_04 as \"4月\", "+
			" t.pay_month_05 as \"5月\", "+
			" t.pay_month_06 as \"6月\", "+
			" t.pay_month_07 as \"7月\", "+
			" t.pay_month_08 as \"8月\", "+
			" t.pay_month_09 as \"9月\", "+
			" t.pay_month_10 as \"10月\", "+
			" t.pay_month_11 as \"11月\", "+
			" t.pay_month_12 as \"12月\", "+
			" t.pay_month_tot as \"总计\" "+
			" from py_pay_plan_detail t, sy_code code, hr_post_grade grade "+
			" where  t.pos_type = grade.post_grade_id(+) "+
			" and t.auth_group_type = code.code_id(+) "+
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