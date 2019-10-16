<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Vector"%>
<jsp:useBean id="codemap_process" class="java.util.HashMap" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="../js/meizzDate.js"></script>
<title>评价 > 基本设置 > 评价流程设置</title>
</head>
<body>

<link href="/css/default.css" rel="stylesheet" type="text/css">
<%
 if(request.getProtocol().equals("HTTP/1.0")) { // HTTP/1.0
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
Vector vEvsProcess=null;
int vEvsProcessSize=0;
try{
	vEvsProcess=SysCodeParser.getCode("EVPROCESS",1);
}catch(Exception e){}
vEvsProcessSize=vEvsProcess.size();
%>

<FORM name="PROCESSFORM" method="POST" target="mainFrame" action="/evs/evsProcessadd_t.jsp"><input type="hidden"
	name="evTypeId" value="<%=evTypeId%>"> <input type="hidden"
	name="evPeriodId" value="<%=evPeriodId%>">
<table width="100%" border="1" align="center" cellpadding="0"
	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
	style="padding: 2px 2px 2px 2px;">
	<tr>
		<td colspan="2" align="Center" height="30" bgcolor="#F5F5F5" class="info_title_01">评价流程的添加</td>
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
		<td height="30" bgcolor="#F5F5F5" align="Center" class="info_title_01">评价流程</td>
		<td><select name="evProcessId">
			<option value="">流程选择</option>
			<%
		if(vEvsProcess!=null){
		for(int i=0;i<vEvsProcessSize;i++){
			codemap_process=(HashMap)vEvsProcess.get(i);
		%>
			<option value="<%=codemap_process.get("code")%>"><%=codemap_process.get("name")%></option>
			<%
		}}
		%>
		</select></td>
	</tr>
	<tr>
		<td height="30" bgcolor="#F5F5F5" align="Center" class="info_title_01">开始时间</td>
		<td><input name="evProcessSDate" type="text" value="" size="15" onClick="setday(this);"></td>
	</tr>
	<tr>
		<td height="30" bgcolor="#F5F5F5" align="Center" class="info_title_01">结束时间</td>
		<td><input name="evProcessEDate" type="text" value="" size="15" onClick="setday(this);"></td>
	</tr>
	<tr>
		<td height="30" bgcolor="#F5F5F5" align="Center" class="info_title_01">流程顺序</td>
		<td><input type="text" name="evProcessOrder" size="5"></td>
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
		document.PROCESSFORM.submit();
		if(confirm(" 是否继续 ? ")){
      		document.PROCESSFORM.reset();
    	}else{
      		window.close();
    	}
	}
}
function CheckForm() {
evProcessOrder=document.PROCESSFORM.evProcessOrder.value; 
evProcessId=document.PROCESSFORM.evProcessId.value;
evProcessSDate=document.PROCESSFORM.evProcessSDate.value;
evProcessEDate=document.PROCESSFORM.evProcessEDate.value;
			 	if(evProcessSDate!=""&&evProcessEDate!=""){
	     	 		evProcessEDate=evProcessEDate.substring(0,4)+"/"+evProcessEDate.substring(5,7)+"/"+evProcessEDate.substring(8,10);
               		evProcessSDate=evProcessSDate.substring(0,4)+"/"+evProcessSDate.substring(5,7)+"/"+evProcessSDate.substring(8,10);
               		var from = Date.parse(evProcessSDate);
               		var to= Date.parse(evProcessEDate); 
              		if(from>to){
            			alert("开始时间不能大于结束时间!");
            			document.PROCESSFORM.evProcessSDate.value="";
			 			document.PROCESSFORM.evProcessSDate.focus();
						return false;
             		}
	       		}
 
	if(evProcessOrder=='')
	{
		alert("请输入评价流程顺序！");
		document.PROCESSFORM.evProcessOrder.focus();
		return false;
	}
	if(isNaN(evProcessOrder))
	{
		alert("评价流程顺序只能为数字！");
		document.PROCESSFORM.evProcessOrder.value="";
		document.PROCESSFORM.evProcessOrder.focus();
		return false;
	}
	if(evProcessId=='')
	{
		alert("请选择评价流程！");
		document.PROCESSFORM.evProcessId.focus();
		return false;
	}
	return true;
}

//-->
</script>
