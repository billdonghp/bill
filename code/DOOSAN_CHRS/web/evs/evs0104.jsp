<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsDeptRadio"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价部门百分数</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evs0105toolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<%
String evDeptId="";
String evPeriodId="";
evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	evPeriodId=evsP.getCurrentEvPeriod();
}
List lEvsDept=null;
List lEvsPeriod=null;
List lEvsDeptRadio=null;
EvsDeptRadio evsDeptRadio=new EvsDeptRadio();
try{
	lEvsDept=EvsDeptRadio.getDeptRadioDeptList();
	lEvsPeriod=evsP.getEvsPeriodByYear("");
	lEvsDeptRadio=evsDeptRadio.getEvsDeptRadio(evDeptId,evPeriodId);
}catch(Exception e){}
%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>

		<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp"><input
			type="hidden" name="menu_code" value="<%=menu_code%>">
		<td height="2" align="right">
		<select name="evDeptId"
			onChange="LGFORM.submit();">
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
		</select> &nbsp; <select name="evPeriodId"
			onchange="LGFORM.submit();">
			<%
		if(lEvsPeriod!=null){
		int periodSize=lEvsPeriod.size();
		
		for(int i=0;i<periodSize;i++){
			EvsPeriod evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
			
		%>
			<option value="<%=evsPeriod.getEvPeriodID()%>"
				<%if(evsPeriod.getEvPeriodID().equals(evPeriodId)){out.print(" selected ");}%>><%=evsPeriod.getEvPeriodName()%></option>
			<%}}%>
		</select></td>
		</FORM>

	</tr>

	<tr>
		<td class="line"><script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-152) + ';\">')
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="10%" height="30" bgcolor="#F5F5F5">
				<div align="center">序号</div>
				</td>
				<td width="38%" bgcolor="#F5F5F5">
				<div align="center">部门名称</div>
				</td>
				<td width="12%" bgcolor="#F5F5F5">
				<div align="center">部门等级</div>
				</td>
				<td width="12%" bgcolor="#F5F5F5">
				<div align="center">员工等级</div>
				</td>
				<td width="12%" bgcolor="#F5F5F5">
				<div align="center">等级比例</div>
				</td>
				<td width="12%" bgcolor="#F5F5F5">
				<div align="center">PI</div>
				</td>
			</tr>
			<%
		
		if(lEvsDeptRadio!=null){ 
		  int levDeptRadioSize=lEvsDeptRadio.size();
		   for(int i=0;i<levDeptRadioSize;i++){
		     EvsDeptRadio  evsDR=(EvsDeptRadio)lEvsDeptRadio.get(i); 
		     
		%>
			<tr onClick="HighLightTR('#99CCFF','black','<%=evsDR.getEvPeriodId()%>','<%=evsDR.getEvDeptId()%>','<%=menu_code%>')">
				<td height="30" class="tablecontent">
				<div align="center"><%=i+1%>&nbsp;</div>
				</td>
				<td align="CENTER"><%=evsDR.getEvDeptName()%></td>
				<td>
				<div align="center"  class="info_content_01"><%=StringUtil.checkNull(evsDR.getEvDeptGradeName())%>&nbsp;</div>
				</td>
				<td>
				<div align="center"  class="info_content_01"><%=StringUtil.checkNull( evsDR.getEvEmpGradeName())%>&nbsp;</div>
				</td>
				<td>
				<div align="center"  class="info_content_01"><%=evsDR.getEvGradeProp()%>&nbsp;</div>
				</td>
				<td>
				<div align="center" class="info_content_01"><%=evsDR.getEvWageRadio()%>&nbsp;</div>
				</td>
			</tr>
			<%}}%>
		</table>
		</div>
		</td>
	</tr>
</table>

</body>
</html>
