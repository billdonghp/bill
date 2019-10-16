<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsOtherItem"%>
<%@ page import="com.ait.evs.EvsOtherItemDetail"%>
<%@ page import="com.ait.evs.EvsOtherColumn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>evcommon</title>
</head>
<%
String evEmpId="";
String evPeriodId="";
String evTypeId="";
int seq=-1;
seq=Integer.parseInt(request.getParameter("ID"));
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evEmpId=request.getParameter("evEmpId")!=null?request.getParameter("evEmpId"):evEmpId;

EvsOtherItem evOtherItem=null;
EvsOtherItemDetail evOtherItemDetail=null;
List lEvOtherColumns=null;
if(!evTypeId.equals("")&&!evPeriodId.equals("")&&seq!=-1){
	evOtherItem=new EvsOtherItem(evPeriodId,evTypeId,evEmpId);
	try{
		evOtherItemDetail=evOtherItem.getEvOtherItemDetailBySeq(seq);
		lEvOtherColumns=evOtherItemDetail.getLEvOtherColumn();
		
	}catch(Exception e){}
}

%>
<body bgcolor="#ffffff">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%

%>
<form name="evs0208" action="evs0208_t.jsp" target="_self" > 
<input type="hidden" name="evPeriodId" value="<%=evPeriodId%>">
<input type="hidden" name="evItemId" value="<%=StringUtil.checkNull(evOtherItemDetail.getEvItemId())%>">  
<input type="hidden" name="evTypeId" value="<%=evTypeId%>">
<input type="hidden" name="evEmpId" value="<%=evEmpId%>">
<input type="hidden" name="evOtherDetailSeq" value="<%=seq%>">  
<input type="hidden" name="flag" value="update">
<table width="98%" border="1" align="center" cellpadding="0" 
cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr align="center" bgcolor="#F5F5F5">
	<td colspan="2" ><%=evOtherItemDetail.getEvItemName()%></td>
	</tr>
	<tr align="center" bgcolor="#F5F5F5">
		<td >项目列</td>
		<td>项目列内容</td>
	</tr>
	<!--<tr align="center">
		<td >比重</td>
		<td><input type="text" value="<%//=evOtherItemDetail.getEvDetailProp()%>" name="evItemDetailProp"/></td>
	</tr>
	--><%
	if(lEvOtherColumns!=null){
		int lEvOtherColumnsSize=lEvOtherColumns.size();
		for(int i=0;i<lEvOtherColumnsSize;i++){
			EvsOtherColumn evOtherColumn=(EvsOtherColumn)lEvOtherColumns.get(i);
			
	%>
			<tr align="center">
			<td><%=(StringUtil.checkNull(evOtherColumn.getEvColumnName())).replaceAll("<br>","")%></td>	
			<td >
			<textarea cols="80" rows="3" name="<%=evOtherColumn.getEvColumnId()+"_detail"%>"><%=evOtherColumn.getEvColumnDetail()%></textarea>
			<!--<input type="text" value="<%//=evOtherColumn.getEvColumnDetail()%>" name="<%//=evOtherColumn.getEvColumnId()+"_detail"%>" />
			--></td>
	</tr>
	<%
		}
	}
	%>
	<tr align="center">
		<td colspan="2">
		<input type="submit" value="保存" />&nbsp;&nbsp; 
		<input type="reset" value="取消" />&nbsp;&nbsp; 
		<input type="button" value="返回" onclick="history.back();"/>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">

function checkNum(){
	var prop=document.evs0208.evItemDetailProp.value;
	if(isNaN(prop)){
		alert("项目比重只能为数字！");
		document.evs0208.evItemDetailProp.value="";
		document.evs0208.evItemDetailProp.focus();
		return false;
	}
	return true;
}
</script> 