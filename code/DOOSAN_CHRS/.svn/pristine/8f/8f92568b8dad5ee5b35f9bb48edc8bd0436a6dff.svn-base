<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<html>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<head>
<title>璇�环�����/title>
<%
String empID = "";
String evPeriodId = "";
empID=request.getParameter("empID")!=null?request.getParameter("empID"):empID;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsEmp evsEmp=new EvsEmp(empID,evPeriodId);
List lEvsMaster=null;
try{	
	evsEmp.getEvEmpMastersByEmpIdPeriod();
	lEvsMaster=evsEmp.getEvsMaster();
}catch(Exception e){}

%>
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td width="100%">
		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" width="100%">
			<tr bgcolor="#F5F5F5">
				<td align="center">��伐��/td>
				<td align="center">��伐濮��</td>
				<td align="center">�ㄩ���О</td>
			</tr>
			<tr>
				<td align="center"><%=empID%>&nbsp;</td>
				<td align="center"><%=StringUtil.checkNull(evsEmp.getEvEmpName())%>&nbsp;</td>
				<td align="center"><%=StringUtil.checkNull(evsEmp.getEvDeptName())%>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td width="100%">
		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;" width="100%">
			<tr bgcolor="#F5F5F5">
				<td align="center">璇�环娴��</td>
				<!--
				<td align="center">璇�环椤圭�</td>
				--><td align="center">�����/td>
			</tr>
			<%
  if(lEvsMaster!=null){
	  int lEvsMasterSize=lEvsMaster.size();
	  for(int i=0;i<lEvsMasterSize;i++){
	    EvsMaster m = (EvsMaster)lEvsMaster.get(i);
%>
			<tr>
				<td align="center">&nbsp;<%=StringUtil.checkNull(m.getEvProcessName())%></td>
				<td align="center">&nbsp;<%=StringUtil.toUnicode(StringUtil.toISO(m.getEvMasterName()),"UTF-8")%></td>
				
			</tr>
			<%
   	}
  }
%>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
