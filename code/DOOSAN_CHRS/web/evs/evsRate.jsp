
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.evs.Constants"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsGradeRate"%>
<%@ page import="com.ait.evs.EvsDeptGradeState"%>
<%@ page import="com.ait.evs.EvsDeptDefaultGrade"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<link href="/css/default.css" rel="stylesheet" type="text/css">	
<%

String adminId="";
if(admin.getAdminID()!=null){
	adminId=admin.getAdminID();
}
String evProcessId=Constants.EVPROCESS015;
String flag="";
String menu_code="";
String RedirectURL="";
String evPeriodId="";
String evDeptId="";
String evTypeId="";
EvsDeptDefaultGrade evsDDG=null;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evTypeId = evTypeId.startsWith(",") ? evTypeId.substring(1,evTypeId.length()) : evTypeId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evsDDG=new EvsDeptDefaultGrade(evDeptId,evPeriodId,evProcessId,evTypeId,adminId);

EvsDeptGradeState state=evsDDG.getDeptGradeState();
%>
<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

<%
if(state!=null){
	%>
	
	<TR bgcolor="#F5F5F5">
	<td width='20%'>等级</td>
	<%
	float width=0f;
	width=80/state.getLEvEmpGrade().size();
	for (int i = 0; i < state.getLEvEmpGrade().size(); i++) {
    	 HashMap h = (HashMap)state.getLEvEmpGrade().get(i);
	     String evGrade = h.get("code").toString();
	     String evGradeName=h.get("name").toString();
	     
        out.println("<td width="+width+"'%'>"+evGradeName+"</td>");
   }
   %>
   </TR>
   <TR >
   <td>基准</td>
	<%
	for (int i = 0; i < state.getLEvNormGrade().size(); i++) {
		EvsGradeRate rate=(EvsGradeRate)state.getLEvNormGrade().get(i);
        out.println("<td>"+rate.getEmpGradeCount()+"</td>");
   }
   %>
   </TR>
   <TR >
   <td >实际</td>
	<%
	for (int i = 0; i < state.getLEvFactGrade().size(); i++) {
		EvsGradeRate normRate=(EvsGradeRate)state.getLEvNormGrade().get(i);
		EvsGradeRate factRate=(EvsGradeRate)state.getLEvFactGrade().get(i);
		if(normRate.getEmpGradeCount()<factRate.getEmpGradeCount()){
        	out.println("<td ><font color='red'>"+factRate.getEmpGradeCount()+" 溢出</font></td>");
        }else if(normRate.getEmpGradeCount()>factRate.getEmpGradeCount()){
        	out.println("<td><font color='blue'>"+factRate.getEmpGradeCount()+"</font></td>");
        }else{
        	out.println("<td>"+factRate.getEmpGradeCount()+"</td>");
        }
   }
   %>
   </TR>
   <%
}
%>
</table>