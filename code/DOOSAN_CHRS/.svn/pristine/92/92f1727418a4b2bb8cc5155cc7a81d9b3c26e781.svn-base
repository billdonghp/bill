<%@ page contentType="text/html; charset=UTF-8" language="java"
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 员工状态</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="/evs/inc/evs0301toolbar_v.jsp"%>
<%@ include file="/evs/inc/evs_nav.jsp"%>
<%
String adminId="";
if(admin!=null){
	adminId=admin.getAdminID();
}
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
List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp=null;
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	lEvsDept=EvsEmp.getEvEmpDeptList();
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=EvsEmp.getEvEmpTypeList();
	
	//lEvsEmp=evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId);
	lEvsEmp=evsMaster.getEvEmpsByMasterPeriod(evPeriodId,adminId,evDeptId,evTypeId,evProcessId);
	
}catch(Exception e){}

//评价流程列表
Vector vEvProcess=new Vector();
try{
vEvProcess=SysCodeParser.getCode("EVPROCESS",1);
}catch(Exception e){}
int vEvProcessSize=vEvProcess.size();
%>
<a name="top"></a>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>

		<form action="/evs/evs0301.jsp?menu_code=evs0301" method="POST"
			name="evs0301">
		<td height="2" align="right" width="74%"><%--<input type="text"
			name="GoEmp" id="GoEmp" size="6"> &nbsp;<a
			onClick="location.href='#'+document.all.GoEmp.value;"
			style="cursor:hand">定位</a> --%>
			<select name="evDeptId" onChange="evs0301.submit();">
			<option value="">评价部门</option>
			<%			if(lEvsDept!=null){
							int lEvsDeptSize=lEvsDept.size();
							Hashtable dept_h=new Hashtable();
	                        for ( int i = 0 ; i < lEvsDeptSize; i++ )
	                        {
	                          dept_h = (Hashtable) lEvsDept.get(i);
                          %>
			<option value="<%=(String)dept_h.get("deptId")%>"
				<%if (((String)dept_h.get("deptId")).equals(evDeptId)){%> selected
				<%}%>><%
                            int level=Integer.parseInt((String)dept_h.get("deptLevel"));
                            String deptname = "";
                            for(int j=0;j<level;j++){
                              deptname +="　";
                            }
                            out.print(deptname+(String)dept_h.get("deptName"));

                            %></option>
			<%}}%>
		</select> &nbsp;<select name="evPeriodId" onChange="evs0301.submit();">

			<%
                            if(lEvsPeriod!=null){
                            	int lEvsPeriodSize=lEvsPeriod.size();
                            	for(int i=0;i<lEvsPeriodSize;i++){
                            		EvsPeriod evsPeriod_sel=(EvsPeriod)lEvsPeriod.get(i);
                              %>
			<option value="<%=evsPeriod_sel.getEvPeriodID()%>"
				<%if (evPeriodId.equals(evsPeriod_sel.getEvPeriodID())) {%>
				selected="selected" <%}%>><%=evsPeriod_sel.getEvPeriodName()%></option>
			<%}}%>
		</select> <select name="evProcessId" 
			onChange="evs0301.submit();">
			<option value="">评价流程</option>
			<%
								for(int k=0;k<vEvProcessSize;k++){
								HashMap codemap_opt=(HashMap)vEvProcess.get(k);
								%>
			<option value="<%=codemap_opt.get("code")%>"
				<%if(evProcessId.equals((String)codemap_opt.get("code"))){out.print(" selected ");}%>>
			<%=codemap_opt.get("name")%></option>
			<%}%>
		</select> <select name="evTypeId" onChange="evs0301.submit();">
			<option value="">评价类型</option>
			<%
                            if(lEvsType!=null){
                            	int lEvsTypeSize=lEvsType.size();
                            	for(int i=0;i<lEvsTypeSize;i++){
                            		EvsType evsType_sel=(EvsType)lEvsType.get(i);
                              %>
			<option value="<%=evsType_sel.getEvTypeID()%>"
				<%if (evTypeId.equals(evsType_sel.getEvTypeID())) {%>
				selected="selected" <%}%>><%=evsType_sel.getEvTypeName()%></option>
			<%}}%>
		</select></td>
		</form>
	</tr>

	<tr>
		<td class="line" colspan="3"><script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-130) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" bgcolor="#F5F5F5">
				<td height="30">序号</td>
				<td>员工号</td>
				<td>姓名</td>
				<td>部门</td>
				<td>评价类型</td>
				<td>当前状态</td>
				<td>步骤期间</td>
			</tr>
			<%	
				if(lEvsEmp!=null){
					int lEvsEmpSize=lEvsEmp.size();
	    			for(int i=0;i<lEvsEmpSize;i++){
	        			EvsEmp evsEmp_tr=(EvsEmp)lEvsEmp.get(i);
	        %>

			<tr
				onClick="HighLightTR('#99CCFF','black','<%=evsEmp_tr.getEvEmpID()%>','<%=evsEmp_tr.getEvPeriodID()%>','<%=menu_code%>')">
				<td height="30" class="tablecontent" align="center"><%=i+1%></td>
				<td align="center"><%=evsEmp_tr.getEvEmpID()%>&nbsp;
				<%--<a href="#top">&#X0018;</a><a name="<%=evsEmp_tr.getEvEmpID()%>"></a>--%>
				</td>
				<td align="center"><%=StringUtil.toUnicode(StringUtil.toISO(evsEmp_tr.getEvEmpName()),"UTF-8")%></td>
				<td align="center"><%=evsEmp_tr.getEvDeptName()%></td>
				<td align="center"><%=evsEmp_tr.getEvTypeName()%></td>
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
		</table>
		</div>
		</td>
	</tr>
</table>

</body>
</html>
