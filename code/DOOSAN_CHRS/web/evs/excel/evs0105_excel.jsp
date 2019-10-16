<%@ page
	contentType="application/vnd.ms-excel;text/html; charset=UTF-8"
	language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>评价者</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<%
response.setHeader("Content-Disposition", "inline; filename=EvjInfo.xls");
response.setHeader("Content-Description", "JSP Generated Data");
String evDeptId="";
String evPeriodId="";
String evTypeId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

List lEvsEmp=null; 
EvsEmp evjEmp=new EvsEmp();

try{
	lEvsEmp=evjEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId);

}catch(Exception e){}
%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td class="line" colspan="3">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0">
			<tr align="center" bgcolor="#F5F5F5">
				<td width="5%" height="30">
				<div align="center">序号</div>
				</td>
				<td width="6%">
				<div align="center">员工号</div>
				</td>
				<td width="8%">
				<div align="center">员工姓名</div>
				</td>
				<td width="24%">
				<div align="center">部门名称</div>
				</td>
				<td width="11%">
				<div align="center">评价类型</div>
				</td>
				<td align="center">评价流程</td>
				<td align="center">操作者</td>
			</tr>
			<%	 
			    int k=1;
				if(lEvsEmp!=null){
				
					
					int lEvsEmpSize=lEvsEmp.size();
	    			for(int i=0;i<lEvsEmpSize;i++){
	    				
	        			EvsEmp EvsEmp_tr=(EvsEmp)lEvsEmp.get(i);
	        			String empID_emp=EvsEmp_tr.getEvEmpID();
						String evPeriodId_emp=EvsEmp_tr.getEvPeriodID();

						EvsEmp evjEmp_emp=new EvsEmp(empID_emp,evPeriodId_emp);
						List lEvsMaster_emp=null;
						try{	
							evjEmp_emp.getEvEmpMastersByEmpIdPeriod();
							lEvsMaster_emp=evjEmp_emp.getEvsMaster();
						}catch(Exception e){}
							
							
							
						 if(lEvsMaster_emp!=null){
						  
						  int lEvsMasterSize=lEvsMaster_emp.size();
						  %>
						  <tr>
				<td height="30" class="tablecontent" rowspan="<%=lEvsMasterSize%>">
				<div align="center" class="info_content_01"><%=i+1%></div>
				</td>
				<td rowspan="<%=lEvsMasterSize%>">
				<div align="center" class="info_content_01"><%=EvsEmp_tr.getEvEmpID()%></div>
				</td>
				<td rowspan="<%=lEvsMasterSize%>">
				<div align="center" class="info_content_01"><%=StringUtil.toUnicode(StringUtil.toISO(EvsEmp_tr.getEvEmpName()),"UTF-8")%>&nbsp;</div>
				</td>
				<td rowspan="<%=lEvsMasterSize%>">
				<div align="center" class="info_content_01"><%=StringUtil.checkNull(EvsEmp_tr.getEvDeptName())%>&nbsp;</div>
				</td>
				<td rowspan="<%=lEvsMasterSize%>">
				<div align="center" class="info_content_01"><%=StringUtil.checkNull(EvsEmp_tr.getEvTypeName())%>&nbsp;</div>
				</td>
						  <%
						  for(int j=0;j<lEvsMasterSize;j++){
						    EvsMaster m = (EvsMaster)lEvsMaster_emp.get(j);
	        %>
				<td align="center">&nbsp;<%=StringUtil.checkNull(m.getEvProcessName())%></td>
				<td align="center">&nbsp;<%=StringUtil.toUnicode(StringUtil.toISO(m.getEvMasterName()),"UTF-8")%></td>
			   </tr>

			<% }}}}%>
			
		</table>
		</td>
	</tr>
</table>

</body>
</html>
