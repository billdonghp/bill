<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsItem"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Vector"%>
<jsp:useBean id="codemap_item" class="java.util.HashMap" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 评价项目设置</title>
</head>
<body>

<link href="/css/default.css" rel="stylesheet" type="text/css">
<%


 if(request.getProtocol().equals("HTTP/1.0")) { 
        response.setHeader("Pragma", "no-cache");
    }
    else if(request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
    response.setDateHeader("Expires", 0); 
String evTypeId="";
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
String evPeriodId="";
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
Vector vEvsItem=null;
try{
vEvsItem=SysCodeParser.getCode("EVITEM",1);
}catch(Exception e){}
int vEvsItemSize=0;
vEvsItemSize=vEvsItem.size();

%>
<FORM name="ITEMFORM" method="POST" target="mainFrame" action="/evs/evsItemadd_t.jsp">
<input type="hidden"
	name="evTypeId" value="<%=evTypeId%>"> <input type="hidden"
	name="evPeriodId" value="<%=evPeriodId%>">
<table width="100%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td colspan="2" align="Center" height="30" bgcolor="#F5F5F5">评价项目的添加</td>
	</tr>
	<!--<tr>
		<td width="30%" height="30" bgcolor="#F5F5F5" align="Center">评价期间</td>
		<td width="70%">&nbsp;</td>
	</tr>
	<tr>
		<td height="30" bgcolor="#F5F5F5" align="Center">评价类型</td>
		<td>&nbsp;</td>
	</tr>
	--><tr>
		<td height="30" bgcolor="#F5F5F5" align="Center">评价项目</td>
		<td><select name="evItemId">
			<option value="">项目选择</option>
			<%
		if(vEvsItem!=null){
		for(int i=0;i<vEvsItemSize;i++){
			codemap_item=(HashMap)vEvsItem.get(i);
		%>
			<option value="<%=codemap_item.get("code")%>"><%=codemap_item.get("name")%></option>
			<%
		}}
		%>
		</select></td>
	</tr>
	<tr>
		<td height="30" bgcolor="#F5F5F5" align="Center">项目顺序</td>
		<td><input type="text" name=evItemOrder size="5"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="button" value="保存" onClick="subm();" />
		&nbsp;&nbsp; <input type="reset" value="重填" /> &nbsp;&nbsp; <input
			type="button" value="关闭" onClick="window.close();" /></td>
	</tr>
</table>

</FORM>
</body>
</html>
<script language="JavaScript" type="">
<!--
function subm(){
	if(CheckForm()){
		document.ITEMFORM.submit();
		if(confirm(" 是否继续 ? ")){
      		document.ITEMFORM.reset();
    	}else{
      		window.close();
    	}
	}
}
function CheckForm() {
	evItemOrder=document.ITEMFORM.evItemOrder.value; 
	evItemId=document.ITEMFORM.evItemId.value;
	if(evItemOrder=='')
	{
		alert("请输入评价项目顺序！");
		document.ITEMFORM.evItemOrder.focus();
		return false;
	}
	if(isNaN(evItemOrder))
	{
		alert("评价项目顺序只能为数字！");
		document.ITEMFORM.evItemOrder.value="";
		document.ITEMFORM.evItemOrder.focus();
		return false;
	}
	if(evItemId=='')
	{
		alert("请选择评价项目！");
		document.ITEMFORM.evItemId.focus();
		return false;
	}
	return true;
}

//-->
</script>