<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<!--被评价者-->
<%
String evEmpId="";
String evPeriodId="";
evEmpId=request.getParameter("ID")!=null?request.getParameter("ID"):evEmpId;
evPeriodId=request.getParameter("ID2")!=null?request.getParameter("ID2"):evPeriodId;
EvsEmp evsEmp=new EvsEmp(evEmpId,evPeriodId);
evsEmp.getEvEmpMastersByEmpIdPeriod();

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 员工状态</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="/evs/inc/evstoolbar_m.jsp"%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2" class="title_line_01"></td>
	</tr>
	<tr>
		<td height="2" class="title_line_02"></td>
	</tr>
	<tr>
		<td class="line">

		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="evPeriodId"
				value="<%=StringUtil.checkNull(evsEmp.getEvPeriodID())%>">
			<input type="hidden" name="evTypeId"
				value="<%=StringUtil.checkNull(evsEmp.getEvTypeID())%>">
			<input type="hidden" name="evDeptId"
				value="<%=StringUtil.checkNull(evsEmp.getEvDeptID())%>">
			<input type="hidden" name="evEmpId"
				value="<%=StringUtil.checkNull(evsEmp.getEvEmpID())%>">
		<tr align="center" bgcolor="#F5F5F5">
           
            <td height="30">员工号</td>
            <td>姓名</td>
            <td>部门</td>
            <td>评价类型</td>
            <td>当前状态</td>
          </tr>

			<tr align="center">
				<td width="15%"><%=evsEmp.getEvEmpID()%>&nbsp;</td>
				<td width="15%"><%=StringUtil.toUnicode(StringUtil.toISO(evsEmp.getEvEmpName()),"UTF-8")%>&nbsp;</td>
				<td width="20%"><%=evsEmp.getEvDeptName()%>&nbsp;</td>
				<td width="20%"><%=evsEmp.getEvTypeName()%>&nbsp;</td>
				<td width="15%">
				<%
				  String evProcesId=evsEmp.getEvCurrentProcessID();
                  List lEvsProcess=null;
                  
                  EvsProcess evsProcess=new EvsProcess(evsEmp.getEvPeriodID(),evsEmp.getEvTypeID());
                  lEvsProcess=evsProcess.getProcessByTypePeriod(); 
                %>
                <select name="evProcessId">
                	<%
                	if(lEvsProcess!=null){
                		int lEvsProcssSize=lEvsProcess.size();
                		for(int i=0;i<lEvsProcssSize;i++){
                			EvsProcess evsPro=(EvsProcess)lEvsProcess.get(i);
                			
                			%>
                			<option value="<%=evsPro.getEvProcessID()%>" <%if(evProcesId.equals(evsPro.getEvProcessID())){out.print("  selected ");}%>><%=evsPro.getEvProcessName()%></opiton>
                			<%
                		}
                		
                	}
                	%>
                </select>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
