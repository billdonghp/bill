<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsCommonItem"%>
<%@ page import="com.ait.evs.EvsCommonColumn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<title>evcommon</title>
</head>
<%
String evPeriodId="";
String evTypeId="";
String evItemId="";
evItemId=request.getParameter("evItemId")!=null?request.getParameter("evItemId"):evItemId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsCommonItem evCommonItems=null;
EvsItem evItem=null;
List lEvItem=null;
List lEvCommonColumns=null;
if(!evTypeId.equals("")&&!evPeriodId.equals("")){
	evCommonItems=new EvsCommonItem(evPeriodId,evTypeId);
	evItem=new EvsItem(evPeriodId,evTypeId);
	try{
		lEvItem=evItem.getItemByTypePeriod();
		lEvCommonColumns=evCommonItems.getEvCommonColumns(evItemId);
	}catch(Exception e){}
}
%>
<body bgcolor="#ffffff">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<form name="evs0106" action="evs0106_t.jsp" onsubmit="return checkNum();">
<input type="hidden"
	name="evPeriodId" value="<%=evPeriodId%>"> <input type="hidden"
	name="evTypeId" value="<%=evTypeId%>"> <input type="hidden" name="flag"
	value="add" >
<table width="98%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	
	<tr>
	<td colspan="2">
		<select name="evItemId" onchange="location.href='/evs/evs0106_a.jsp?menu_code=evs0106&evPeriodId=<%=evPeriodId%>&evTypeId=<%=evTypeId%>&evItemId='+this.value">
			<option value=''>评价项目</option>
		<%if(lEvItem!=null){
			int lEvsItemSize=lEvItem.size();
			for(int i=0;i<lEvsItemSize;i++){
				EvsItem evItem_tr=(EvsItem)lEvItem.get(i);		
		%>
			<option value='<%=evItem_tr.getEvItemID()%>' <%if(evItemId.equals(evItem_tr.getEvItemID())){%> selected="selected" <%}%>>
			<%=evItem_tr.getEvItemName()%>
			</option>
		<%}}%>	
		</select>
		</td>
	</tr>
	<tr align="center" bgcolor="#F5F5F5">
		<td >项目列</td>
		<td>项目列内容</td>
	</tr>
	<tr align="left">
		<td >比重</td>
		<td><input type="text" value="" name="evItemDetailProp"/></td>
	</tr>
	<%
	if(lEvCommonColumns!=null){
		int lEvCommonColumnsSize=lEvCommonColumns.size();
		for(int i=0;i<lEvCommonColumnsSize;i++){
			EvsCommonColumn evCommonColumn=(EvsCommonColumn)lEvCommonColumns.get(i);
	%>
			<tr align="left">
			<td><%=evCommonColumn.getEvColumnName()%></td>	
			<td>
			<textarea cols="60" rows="3" name="<%=evCommonColumn.getEvColumnId()+"_detail"%>"></textarea>
			<!--<input type="text" value="" name="<%//=evCommonColumn.getEvColumnId()+"_detail"%>" size='80'/>
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
	var prop=document.evs0106.evItemDetailProp.value;
	if(isNaN(prop)){
		alert("项目比重只能为数字！");
		document.evs0106.evItemDetailProp.value="";
		document.evs0106.evItemDetailProp.focus();
		return false;
	}
	return true;
}
</script>
