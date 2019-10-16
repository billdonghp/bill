<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.evs.EvsDeptRadio" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价部门百分数</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_m.jsp"%>
<%
//部门等级。员工等级
Vector vEvDeptGrade=null;
Vector vEvEmpGrade=null;
int vEvDeptGradeSize=0;
int vEvEmpGradeSize=0;
try{
vEvDeptGrade=SysCodeParser.getCode("EVDEPTGRADE");
vEvEmpGrade=SysCodeParser.getCode("EVEMPGRADE");
}catch(Exception e){}
if(vEvDeptGrade!=null){
	vEvDeptGradeSize=vEvDeptGrade.size();
}
if(vEvEmpGrade!=null){
	vEvEmpGradeSize=vEvEmpGrade.size();
}
String evDeptId="";
String evPeriodId="";
evDeptId=request.getParameter("ID2")!=null?request.getParameter("ID2"):evDeptId;
evPeriodId=request.getParameter("ID")!=null?request.getParameter("ID"):evPeriodId;
%>
<input type="hidden" value="<%=evDeptId%>" name="evDeptId">
<input type="hidden" value="<%=evPeriodId%>" name="evPeriodId">
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td>
				</td>
			</tr>
			<tr align="center">
				<td width="100%" bgcolor="#F5F5F5">
				<div align="center">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				部门等级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				员工等级&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				等级比例&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				PI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
						<%
						if(vEvDeptGrade!=null){
							
							for(int i=0;i<vEvDeptGradeSize;i++){
								HashMap mEvDeptGrade=(HashMap)vEvDeptGrade.get(i);
								String evDeptGradeId=(String)mEvDeptGrade.get("code");
								out.print("<tr>");
								out.print("<td width='20%' align='center' >");
								out.print(mEvDeptGrade.get("name"));
								out.print("</td><td width='80%'>");
								if(vEvEmpGrade!=null){
								out.print("<table width='100%' border='1' cellpadding='0' cellspacing='0' bordercolorlight='#E7E7E7' bordercolordark='#FFFFFF' style='padding: 2px 2px 2px 2px;'>");
								for(int j=0;j<vEvEmpGradeSize;j++){
									HashMap mEvEmpGrade=(HashMap)vEvEmpGrade.get(j);
									String evEmpGradeId=(String)mEvEmpGrade.get("code");
									EvsDeptRadio evsDeptRadio=new EvsDeptRadio(evPeriodId,evDeptId,evDeptGradeId,evEmpGradeId);
									try{
									evsDeptRadio.getEvsDeptRadio();
									}catch(Exception e){}
									
									%>
									<TR>
									<TD width="20%" align='center' ><%=mEvEmpGrade.get("name")%></TD>
									<TD width="40%" align='center'>
									<INPUT TYPE='TEXT' NAME='<%=evDeptGradeId+"_"+evEmpGradeId+"_"+"gradeProp"%>' VALUE='<%=evsDeptRadio.getEvGradeProp()%>'>
									</TD>
									<TD width="40%" align='center'>
									<INPUT TYPE='TEXT' NAME='<%=evDeptGradeId+"_"+evEmpGradeId+"_"+"wageRadio"%>' VALUE='<%=evsDeptRadio.getEvWageRadio()%>'>
									</TD>
									</TR>
									<%
								}
								out.print("</table>");
								}
								out.print("</td>");
								out.print("</tr>");
								
							}
						}
						%>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>
</html>
