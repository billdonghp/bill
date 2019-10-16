<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 项目列</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="">
var deptname="";

</script>
<%@ include file="inc/evstoolbar_a.jsp"%>
<%
//评价列
Vector vEvColumn=null;
int vEvColumnSize=0;
try{
vEvColumn=SysCodeParser.getCode("EVCOLUMN");
}catch(Exception e){}
if(vEvColumn!=null){
	vEvColumnSize=vEvColumn.size();
}
//评价期间
String evPeriodId="";
String evTypeId="";
String evItemId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
EvsPeriod evsP=new EvsPeriod();
EvsItem evsItem=new  EvsItem();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}
}
List lEvsPeriod=null;
List lEvsType=null;
List lEvsItem=null;
try{
	lEvsPeriod=evsP.getEvsPeriodByYear("");
	lEvsType=evsP.getEvTypeByEvPeriodId(evPeriodId);
	if(evTypeId.equals("")){
		if(lEvsType!=null){
			evTypeId=((EvsType)lEvsType.get(0)).getEvTypeID();
		}
	}
	lEvsItem=evsItem.getItemListByTypePeriod(evPeriodId,evTypeId);
}catch(Exception e){}

%>
<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">

	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td height="2"></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td></td>
			</tr>
			<tr align="center">
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价期间</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价类型</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价项目</div>
				</td>
				<td width="20%" bgcolor="#F5F5F5">
				<div align="center">评价列</div>
				</td>
			</tr>
			<tr>
				<td width="20%" >
				<div align="center">
				<select name="evPeriodId"
					onchange="location.href='/evs/evs0107_a.jsp?menu_code=evs0107&evPeriodId='+this.value+'&evTypeId='+document.LGFORM.evTypeId.value+'&evItemId='+document.LGFORM.evItemId.value;">
					<%
		if(lEvsPeriod!=null){
		int periodSize=lEvsPeriod.size();
		
		for(int i=0;i<periodSize;i++){
			EvsPeriod evsPeriod=(EvsPeriod)lEvsPeriod.get(i);
			
		%>
					<option value="<%=evsPeriod.getEvPeriodID()%>"
						<%if(evsPeriod.getEvPeriodID().equals(evPeriodId)){out.print(" selected ");}%>><%=evsPeriod.getEvPeriodName()%>
					</option>
					<%}}%>
				</select></div>
				</td>
				<td width="20%" >
				<div align="center">
				<select name="evTypeId" 
					onchange="location.href='/evs/evs0107_a.jsp?menu_code=evs0107&evPeriodId='+document.LGFORM.evPeriodId.value+'&evTypeId='+this.value+'&evItemId='+document.LGFORM.evItemId.value;">
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
				</select></div>
				</td>
				<td width="20%" >
				<div align="center">
				<select name="evItemId">
					<%			
			if(lEvsItem!=null){
			 	int  lEvsItemSize=lEvsItem.size();
	            for(int i=0;i<lEvsItemSize;i++){
				HashMap hEvItem=(HashMap)lEvsItem.get(i);
				
			%>
					<option value="<%=hEvItem.get("itemId")%>"
						<%if(hEvItem.get("itemId").equals(evItemId)){out.print(" selected ");}%>>
					<%=hEvItem.get("itemName")%></option>
					<%}}%>
				</select></div>
				</td>
				<td width="20%" >
				<div align="left"><%			
			if(vEvColumn!=null){
	            for(int i=0;i<vEvColumnSize;i++){
				HashMap hEvColumn=(HashMap)vEvColumn.get(i);
				
			%> <input type='checkbox' name='evColumnId'
					value='<%=hEvColumn.get("code")%>'> <%=(String)hEvColumn.get("name").toString().replaceAll("<br>","")%><br>
				<%}}%></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
