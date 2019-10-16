<%@ page contentType="application/vnd.ms-excel;text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>进行情况</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<%
response.setHeader("Content-Disposition", "inline; filename=evsInfo.xls");
response.setHeader("Content-Description", "JSP Generated Data");
String evDeptId="";
String evPeriodId="";
String evTypeId="";
String evProcessId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;


EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}	
}
List lEvsEmp=null;
List lProcessEvsEmp=null;
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{

	lEvsEmp=evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId,evProcessId);
	lProcessEvsEmp=evsEmp.getEvEmpCountByDeptPeriodType(evDeptId,evPeriodId,evTypeId,evProcessId);
	//lEvsEmp=evsMaster.getEvEmpsByMasterPeriod(evPeriodId,adminId,evDeptId,evTypeId);
	
}catch(Exception e){}
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	
	<tr>
		<td class="line" colspan="3">
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" >
			<tr align="center"><td align="center" colspan="8" height="30">评价进行情况</td></tr>
			<tr align="center" bgcolor="#F5F5F5">
				<td height="30">序号</td>
				<td>员工号</td>
				<td>姓名</td>
				<td>部门</td>
				<td>评价类型</td>
				<td>评价总分</td>
				<td>评价等级</td>
				<td>当前状态</td>
				<td>步骤期间</td>
			</tr>
			<%	
				if(lEvsEmp!=null){
					int lEvsEmpSize=lEvsEmp.size();
	    			for(int i=0;i<lEvsEmpSize;i++){
	        			EvsEmp evsEmp_tr=(EvsEmp)lEvsEmp.get(i);
	        %>

			<tr>
				<td height="30" class="tablecontent" align="center" ><%=i+1%></td>
				<td align="center"><%=evsEmp_tr.getEvEmpID()%></td>
				<td align="center"><%=evsEmp_tr.getEvEmpName()%></td>
				<td align="center"><%=evsEmp_tr.getEvDeptName()%></td>
				<td align="center"><%=evsEmp_tr.getEvTypeName()%></td>
				<td align="center"><%=evsEmp_tr.getEvMark()%></td>
				<td align="center"><%=StringUtil.checkNull(evsEmp_tr.getEvGradeName())%></td>
				<td align="center"><%=evsEmp_tr.getEvCurrentProcessName()%></td>
				<%
                  
                  EvsProcess evsProcess=new EvsProcess(evsEmp_tr.getEvPeriodID(),evsEmp_tr.getEvTypeID(),evsEmp_tr.getEvCurrentProcessID());
                  try{
                  	evsProcess.getProcessByTypePeriodProcess();
                  }catch(Exception e){}
                
                  %>
				<td align="center"><%=StringUtil.checkNull(evsProcess.getEvProcessSDate())%>
				～ <%=StringUtil.checkNull(evsProcess.getEvProcessEDate())%></td>
			</tr>
			
			<% }}%>
			<%
			if(lProcessEvsEmp!=null){
				out.println("<tr height='30'><td colspan='6'></td></tr>");
				int lProcessEvsEmpSize=lProcessEvsEmp.size();
				for(int i=0;i<lProcessEvsEmpSize;i++){
					HashMap hEvEmp=(HashMap)lProcessEvsEmp.get(i);
					%>
					
					<tr height='30' >
						<td colspan='3' bgcolor="#F5F5F5">评价流程:<%=hEvEmp.get("evProcessName")%></td>
						<td colspan='3' bgcolor="#F5F5F5">总人数:<%=hEvEmp.get("empCount")%></td>
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
