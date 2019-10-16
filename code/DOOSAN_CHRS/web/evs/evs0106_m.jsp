<%@page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.evs.EvsCommonItem"%>
<%@ page import="com.ait.evs.EvsCommonItemDetail"%>
<%@ page import="com.ait.evs.EvsCommonColumn"%>
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
String evPeriodId="";
String evTypeId="";
int seq=-1;
seq=Integer.parseInt(request.getParameter("ID"));
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;

EvsCommonItem evCommonItem=null;
EvsCommonItemDetail evCommonItemDetail=null;
List lEvCommonColumns=null;
if(!evTypeId.equals("")&&!evPeriodId.equals("")&&seq!=-1){
	evCommonItem=new EvsCommonItem(evPeriodId,evTypeId);
	try{
		evCommonItemDetail=evCommonItem.getEvCommonItemDetailBySeq(seq);
		lEvCommonColumns=evCommonItemDetail.getLEvCommonColumn();
		
	}catch(Exception e){}
}

%>
<body bgcolor="#ffffff">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%

%>
<form name="evs0106" action="evs0106_t.jsp" target="_self" onsubmit="return checkNum();"> 
<input type="hidden" name="evPeriodId" value="<%=evPeriodId%>">
<input type="hidden" name="evItemId" value="<%=evCommonItemDetail.getEvItemId()%>">  
<input type="hidden" name="evTypeId" value="<%=evTypeId%>">
<input type="hidden" name="evCommonDetailSeq" value="<%=seq%>">  
<input type="hidden" name="flag" value="update">
<table width="98%" border="1" align="center" cellpadding="0" 
cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr align="center" bgcolor="#F5F5F5">
	<td colspan="2" ><%=evCommonItemDetail.getEvItemName()%></td>
	</tr>
	<tr align="center" bgcolor="#F5F5F5">
		<td >项目列</td>
		<td>项目列内容</td>
	</tr>
	<tr align="left">
		<td >比重</td>
		<td><input type="text" value="<%=evCommonItemDetail.getEvDetailProp()%>" name="evItemDetailProp"/></td>
	</tr>
	<%
	if(lEvCommonColumns!=null){
		int lEvCommonColumnsSize=lEvCommonColumns.size();
		for(int i=0;i<lEvCommonColumnsSize;i++){
			EvsCommonColumn evCommonColumn=(EvsCommonColumn)lEvCommonColumns.get(i);
			
	%>
			<tr align="left">
			<td><%=(StringUtil.checkNull(evCommonColumn.getEvColumnName())).replaceAll("<br>","")%></td>	
			<td >
			<textarea cols="60" rows="3" name="<%=evCommonColumn.getEvColumnId()+"_detail"%>"><%=evCommonColumn.getEvColumnDetail()%></textarea>
			<!--<input type="text" value="<%//=evCommonColumn.getEvColumnDetail()%>" name="<%//=evCommonColumn.getEvColumnId()+"_detail"%>" size='80'/>
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