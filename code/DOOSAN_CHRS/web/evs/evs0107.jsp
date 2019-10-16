<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsColumn"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 项目列</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evs0107toolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<%
String evPeriodId="";
String evTypeId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
EvsItem evsItem=null;
List lEvsPeriod=null;
List lEvsItemColumn=null;
List lEvsType=null;
try{
	lEvsPeriod=evsP.getEvsPeriodByYear("");
	
	lEvsType=evsP.getEvTypeByEvPeriodId(evPeriodId);
	evsItem=new EvsItem(evPeriodId,evTypeId);
	lEvsItemColumn=evsItem.getItemColumnList();
}catch(Exception e){}

%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<FORM name="LGFORM" method="POST" action="/evs/<%=menu_code%>.jsp"><input
			type="hidden" name="menu_code" value="<%=menu_code%>">
		<td height="2" align="right"><select name="evPeriodId"
			onchange="LGFORM.submit();">
			<%
			if(lEvsPeriod!=null){
			int periodSize=lEvsPeriod.size();
			for(int i=0;i<periodSize;i++){
				EvsPeriod evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
			%>
			<option value="<%=evsPeriod.getEvPeriodID()%>"
				<%if(evsPeriod.getEvPeriodID().equals(evPeriodId)){out.print(" selected ");}%>>
			<%=evsPeriod.getEvPeriodName()%></option>
			<%}}%>
		</select>&nbsp; <select name="evTypeId" onChange="LGFORM.submit();">
			<option value="">评价类型</option>
			<%			
			if(lEvsType!=null){
							int lEvsTypeSize=lEvsType.size();
	            for(int i=0;i<lEvsTypeSize;i++){
				EvsType evsType=(EvsType)lEvsType.get(i);
			%>
			<option value="<%=evsType.getEvTypeID()%>"
				<%if(evsType.getEvTypeID().equals(evTypeId)){out.print(" selected ");}%>>
			<%=evsType.getEvTypeName()%></option>
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

				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价类型</div>
				</td>
				<td width="30%" bgcolor="#F5F5F5">
				<div align="center">评价项目</div>
				</td>
				<td width="40%" bgcolor="#F5F5F5">
				<div align="center">评价列</div>
				</td>
			</tr>
			<%
		
		if(lEvsItemColumn!=null){ 
		  int lEvItemColumnSize=lEvsItemColumn.size();
		   for(int i=0;i<lEvItemColumnSize;i++){
		     EvsItem  evItem=(EvsItem)lEvsItemColumn.get(i); 
		     
		%>
			<tr
				onClick="HighLightTR('#99CCFF','black','<%=evItem.getEvPeriodID()%>','<%=evItem.getEvTypeID()%>','<%=evItem.getEvItemID()%>','<%=menu_code%>')">
				<td height="30" class="tablecontent">
				<div align="center"><%=i+1%>&nbsp;</div>
				</td>
				<td>
				<div align="center" class="info_content_01"><%=StringUtil.checkNull(evItem.getEvTypeName())%>&nbsp;
				</div>
				</td>
				<td align="CENTER"><%=evItem.getEvItemName().replaceAll("<br>","")%></td>
				<td><%
					List lEvsColumn=null;
					lEvsColumn=evItem.getLItemColumns();
					if(lEvsColumn!=null){
					 	int lEvsColumnSize=lEvsColumn.size();
					 	
						for(int k=0;k<lEvsColumnSize;k++){
							EvsColumn evsColumn=(EvsColumn)lEvsColumn.get(k);
							if(evsColumn!=null){
								out.println("<div align='left'>"+(k+1)+"."+StringUtil.checkNull(evsColumn.getEvColumnName()).replaceAll("<br>","")+"</div>");
							}
						}
					}
					%></td>
			</tr>
			<%}}%>
		</table>
		</div>
		</td>
	</tr>
</table>
</body>
</html>
