<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ar.dao.ArShift010Bean,com.ait.sqlmap.util.*,java.text.*,java.util.*,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<%@ include file="../../inc/taglibs.jsp"%>
<HTML>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head><title>排班表</title></head>
<body>
<%
AdminBean adminBean = new AdminBean();
adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
ActivityReport pareport = new ActivityReport();
String from=StringUtil.checkNull(request.getParameter("from"));
String to=StringUtil.checkNull(request.getParameter("to"));
String deptid=StringUtil.checkNull(request.getParameter("deptid"));
String key=StringUtil.toCN(request.getParameter("key"));

Vector vlist = new Vector();
Vector data = new Vector();
String sql="";
int cols=3;
Map mShift=new HashMap();
Map cShift=new HashMap();
ArShift010Bean bean = new ArShift010Bean();
List<SimpleMap> list = bean.retrieveShiftList();
for (SimpleMap map: list) {
		mShift.put(map.getString("SHIFT_NO"),map.getString("SHIFT_NAME"));

}

cShift.put("3","blue");
cShift.put("4","blue");
cShift.put("5","#FF0000");
cShift.put("7","black");
cShift.put("8","black");

if (!from.equals("")&&!("").equals(to)){


String sql_name=" SELECT  H.deptname AS 部门, SY.CODE_NAME AS 职位, H.EMPID AS　工号, H.CHINESENAME　AS 姓名 ";

String sql_admin= " AND (DATE_LEFT IS NULL OR DATE_LEFT BETWEEN TO_DATE('" + from + "','YYYY-MM-DD') AND TO_DATE('" + to + "','YYYY-MM-DD') ) " 
				+ " AND H.DEPTID IN (SELECT S.SUPERVISED_DEPTID FROM AR_SUPERVISOR_INFO S WHERE S.AR_SUPERVISOR_ID = '" + admin.getAdminID() + "') ";
                
String sql_statement="";

	if(!"".equals(deptid)){
		sql_statement="and h.deptid IN(" +
							"SELECT deptid " +
							"FROM hr_department " +
							"START WITH deptid ='" + deptid + "' " +
							"CONNECT BY PRIOR deptid = parent_dept_id)";
	}
	if(key != null && !"".equals(key)){
		sql_statement=sql_statement+" and ( upper(h.empid)='"+key+"'  or h.chinesename like '%"+key+"%')  ";
	}
	 
                
StringBuffer sqlField=new StringBuffer();
StringBuffer sqlTable=new StringBuffer();
StringBuffer sqlCon=new StringBuffer();
java.util.Date fromDate= java.sql.Date.valueOf(from);
java.util.Date toDate= java.sql.Date.valueOf(to);
Calendar ca=Calendar.getInstance();

SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd");
SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");
while(fromDate.compareTo(toDate)<=0){
	
	sqlField.append(" ,\"SHIFT_"+format2.format(fromDate)+"\".\""+format2.format(fromDate) + "\"");
	sqlTable.append(" ,(SELECT person_id,AR_GET_SHIFTNO(person_id,'"+format1.format(fromDate)+"') AS \""+format2.format(fromDate)+"\" from HR_EMPLOYEE) \"SHIFT_"+format2.format(fromDate) + "\"");
	sqlCon.append(" AND \"SHIFT_"+format2.format(fromDate)+"\".person_id=H.person_id");
	ca.setTime(fromDate);   
    ca.add(Calendar.DATE,1);
    fromDate=(java.util.Date)ca.getTime();
    cols++;
}
sql=sql_name+sqlField.toString()+"  FROM HR_EMP_V H,SY_CODE SY "+sqlTable.toString()+" WHERE h.Cpny_Id='"+ adminBean.getCpnyId() +"' and H.POST_CODE = SY.CODE_ID(+) "+sqlCon.toString()+sql_admin+sql_statement+" order by h.deptid DESC,H.POSITION_CODE,h.person_id ";
}
vlist = pareport.DataSelect(sql);
String tabledata = "";
String tr="";
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<% 
HttpSession session1 = request.getSession(true);
AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
MessageSource messageSource1 = new MessageSource("ar",admin1.getLocale(), "UTF-8");
String shift = messageSource1.getMessage("display.att.maintenance.shift.shift_2");
if (vlist.size()!=0){
	
	
	response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Pragma", "public");
	response.setHeader("Cache-Control", "max-age=0");
	
	response.setHeader("Content-Disposition", "attachment; filename=ar_shift_reports.xls");   
	response.setHeader("Content-Description", "JSP Generated Data");//排班情况
	out.println("<tr  align=\"center\" style=\"font-weight:bold;;font-size:12pt\">"+shift+"&nbsp;"+from+" - "+to+""+"</td></tr>");
	for ( int i = 0 ; i < vlist.size() ; i++ ){ 		  
		pareport=(ActivityReport)vlist.elementAt(i);
		
		String bgColor="";
		if(i%2==2){
			bgColor="#F5F5F5";
		}
		%>
		<tr align="center" style="font-size:9pt" bgcolor="<%=bgColor%>" > 
		<% data = pareport.getData();
		 tr="";
		 if (data.size()!=0) {
			for (int j=0;j<data.size();j++) {
				tabledata=(String)data.elementAt(j) ;
				try {
					Integer.parseInt(tabledata);
					tr +="<td>"+mShift.get(tabledata)+"</td>";
				} catch (Exception e) {
					tr +="<td>"+tabledata+"</td>";
				}
			}%>
			<%=tr%>
		<%}%>
		</tr>
	<%
	}
}%>
</table>
</body></html>
