
<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.evs.EvsNorm"%>
<%@ page import="com.ait.evs.EvsDeptGrade"%>
<%@ page import="com.ait.evs.EvsGradeRate"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="codemap_grade" class="java.util.HashMap" scope="page"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价基准设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evs0108toolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<%
EvsNorm evNorm=new EvsNorm();
List lEvDeptGrade=null;
Vector vEvDeptGrade=null;
String evDeptGrade="";
evDeptGrade=StringUtil.checkNull(request.getParameter("evDeptGrade"));
try{
vEvDeptGrade=SysCodeParser.getCode("EVDEPTGRADE");
if(vEvDeptGrade!=null){
	if(evDeptGrade.equals("")){
		evDeptGrade=((HashMap)vEvDeptGrade.get(0)).get("code").toString();
	}
}
lEvDeptGrade=evNorm.getEvsNorm(evDeptGrade);
}catch(Exception e){}
%>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp" >
		<input type="hidden"
			name="menu_code" value="<%=menu_code%>">
		<td height="2" align="right">
		部门等级<select name="evDeptGrade"
			onchange="LGFORM.submit();">
			<%
		if(vEvDeptGrade!=null){
		int evDeptGradeSize=vEvDeptGrade.size();
		
		for(int i=0;i<evDeptGradeSize;i++){
			codemap_grade=(HashMap)vEvDeptGrade.get(i);
		%>
			<option value=<%=codemap_grade.get("code").toString()%>
				<%if(evDeptGrade.equals(codemap_grade.get("code").toString())){out.print(" selected ");}%>>
				<%=codemap_grade.get("name").toString()%></option>
			<%}}%>
		</select></td>
		</FORM>
	</tr>
	<tr>
		<td class="line">
		<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:100%; height:' + (document.body.clientHeight-158) + ';\">')
ID='<%=evDeptGrade%>';
//-->
</script>
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center" >
				<td width="60" height="30" bgcolor="#F5F5F5">
				<div align="center">部门等级</div>
				</td>
				<td width="60" bgcolor="#F5F5F5">
				<div align="center">总人数</div>
				</td>
				<%
				Vector vEvEmpGrade=evNorm.getVEvEmpGrade();
				if(vEvEmpGrade!=null){
					int vEvEmpGradeSize=vEvEmpGrade.size();
					for(int i=0;i<vEvEmpGradeSize;i++){
						HashMap h=(HashMap)vEvEmpGrade.get(i);
						%>
						<td width="100/<%=vEvEmpGradeSize%>" bgcolor="#F5F5F5" >
							<div align="center"><%=StringUtil.checkNull(h.get("name").toString())%>级人数</div>
						</td>
						<%
					}
				}
				%>
			</tr>
			<%

		if(lEvDeptGrade!=null){ 
			int lEvDeptGradeSize=lEvDeptGrade.size();
		   for(int i=0;i<lEvDeptGradeSize;i++){
		      EvsDeptGrade evD=(EvsDeptGrade)lEvDeptGrade.get(i);
		      
		%>
			<tr
				onClick="HighLightTR('#99CCFF','black','<%=evD.getEvDeptGradeId()%>','<%=evD.getEvEmpSum()%>','<%=menu_code%>')">
		
				<td >
				<div align="center" height="30" class="info_content_01">
				<%=evD.getEvDeptGradeName()%>&nbsp;
				</div>
				</td>
				<td>
				<div align="center" height="30" class="info_content_01"><%=evD.getEvEmpSum()%>&nbsp;</div>
				</td>
<%
				List lEvGradeRate=evD.getLEvsGradeRate();
				for(int j=0;j<lEvGradeRate.size();j++){
					EvsGradeRate evGradeRate=(EvsGradeRate)lEvGradeRate.get(j);
					
					%>
					<td height="30">
					<div align="center" height="30" class="info_content_01">
					<%
					if(evGradeRate.getEmpGradeCount()!=-1){
						out.println(evGradeRate.getEmpGradeCount()+"");
					}
					%>
					&nbsp;
					</div>
					</td>
					<%
				}
				%>
			</tr>
			<%}}%>
		</table>
		</div>
		</td>
	</tr>
</table>

</body>
</html>
