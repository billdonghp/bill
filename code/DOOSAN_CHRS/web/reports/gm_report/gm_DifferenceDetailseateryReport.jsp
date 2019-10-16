<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil,com.ait.reports.servlet.EatReport,java.util.*,com.ait.sqlmap.util.SimpleMap"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="Eatreport" class="com.ait.reports.servlet.EatReport"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>
<jsp:useBean id="parameterObject" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>
<jsp:useBean id="empMap" class="com.ait.sqlmap.util.SimpleMap"></jsp:useBean>
<%
ActivityReport pareport = new ActivityReport();
List listtype=Eatreport.returnEateryType();

int listtypeSize = listtype.size();

Vector list = new Vector();
Vector data = new Vector();

String startdate = request.getParameter("startdate");
String enddate = request.getParameter("enddate");

parameterObject.set("startdate",startdate);
parameterObject.set("enddate",enddate);


String sql =" SELECT HE.CHINESENAME AS 姓名, HE.EMPID AS 工号, HD.DEPTNAME AS 部门, S.CODE_NAME AS 职务 ";
	for(int i=0 ; i<listtype.size() ; i++){
		empMap = (SimpleMap) listtype.get(i);
		String gm_type = empMap.getString("GM_TYPE");
		sql+=" ,SHIJI.实际"+gm_type+",NVL(YINGGAI.应该"+gm_type+",0) AS 应该"+gm_type+",SHIJI.实际"+gm_type+" - NVL(YINGGAI.应该"+gm_type+",0) AS "+gm_type+"差异";
	}
	sql += " FROM (SELECT A.EMPID ";
	for(int i=0 ; i<listtype.size() ; i++){
		empMap = (SimpleMap) listtype.get(i);
		String gm_type = empMap.getString("GM_TYPE");
		sql += ",COUNT(CASE WHEN GE.GM_TYPE = '"+gm_type+"' THEN A.EMPID END) AS 实际"+gm_type+"";
	}
	sql += " FROM AR_EATERY_RECORDS A, GM_EATERY GE"+
		   " WHERE TO_CHAR(A.R_TIME,'HH24:MI') BETWEEN GE.GM_FROM_DATE AND GE.GM_TO_DATE"+
		   " AND TO_CHAR(A.R_TIME,'YYYY-MM-DD') >= '"+ startdate+"' "+
		   " AND TO_CHAR(A.R_TIME,'YYYY-MM-DD') <= '"+ enddate+"' "+
		   " GROUP BY A.EMPID) SHIJI,"+
		   " (SELECT G.EMP_ID";
	for(int i=0 ; i<listtype.size() ; i++){
		empMap = (SimpleMap) listtype.get(i);
		String gm_type = empMap.getString("GM_TYPE");
		String gm_id = empMap.getString("GM_ID");
		sql += " ,NVL(COUNT(CASE WHEN G.EATERY_TYPE_ID =" +gm_id+" THEN G.EMP_ID END),'0') AS 应该"+gm_type+"";
	}
		sql += " FROM GM_EAT_APPLY_EMP G, AR_EATERY_RECORDS A"+
			   " WHERE G.EMP_ID = A.EMPID"+
			   " AND TO_CHAR(A.R_TIME,'YYYY-MM-DD') >= '"+startdate+"' "+
			   " AND TO_CHAR(A.R_TIME,'YYYY-MM-DD') <= '"+enddate+"' "+
			   " GROUP BY G.EMP_ID) YINGGAI,"+
			   " HR_EMPLOYEE HE,HR_DEPARTMENT HD,SY_CODE S WHERE SHIJI.EMPID = YINGGAI.EMP_ID(+) AND HE.EMPID = SHIJI.EMPID AND HE.DEPTID = HD.DEPTID AND HE.POST_CODE = S.CODE_ID(+)";
				   
Logger.getLogger(getClass()).debug(StringUtil.toISO(sql));
list = pareport.DataSelect(sql);
String tabledata = "";
String tr="";
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=DifferenceDetailsEateryReport.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
%>
<table width="100%" align="center">
	<tr align="center">
		<td align="center" colspan="16"><b><font size="+2">就餐差异明细对比表</font></b></td>
	</tr>
</table>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
	<tr align="center">
		<td align="center" colspan="1">&nbsp;</td>
		<td align="center" colspan="1">&nbsp;</td>
		<td align="center" colspan="1">&nbsp;</td>
		<td align="center" colspan="1">&nbsp;</td>
		<td align="center" colspan="3">晚餐</td>
		<td align="center" colspan="3">早餐</td>
		<td align="center" colspan="3">中餐</td>
		<td align="center" colspan="3">夜餐</td>
	</tr>
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