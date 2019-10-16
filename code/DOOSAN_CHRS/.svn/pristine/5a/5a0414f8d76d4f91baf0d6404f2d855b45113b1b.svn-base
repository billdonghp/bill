<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="ov" class="com.ait.ess.entity.OvertimeView" scope="request"></jsp:useBean>

<jsp:useBean id="map1" class="java.util.HashMap" scope="request"></jsp:useBean>
<jsp:useBean id="map2" class="java.util.HashMap" scope="request"></jsp:useBean>
<jsp:useBean id="pageDate" class="java.lang.String" scope="request"></jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<script src="../js/birthday.js"></script>
<SCRIPT type="text/javascript">
<!--hidden

function Save(){
	if(document.addworkForm.otTypeCode.value==""){
			alert("请选择加班类型");
			return ;
	}
	if(trim(document.addworkForm.work_content.value).length<1){
			alert("必须输入工作内容");
			return ;
	}
	if(!confirm("是否确定提交加班申请"))
	{
		return;
	}
    document.addworkForm.submit();
}

//去掉字串左边的空格
function lTrim(str)
{
if (str.charAt(0) == " ")
{
//如果字串左边第一个字符为空格
str = str.slice(1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(1, str.length);
str = lTrim(str); //递归调用
}
return str;
}


//去掉字串右边的空格
function rTrim(str)
{
var iLength;

iLength = str.length;
if (str.charAt(iLength - 1) == " ")
{
//如果字串右边第一个字符为空格
str = str.slice(0, iLength - 1);//将空格从字串中去掉
//这一句也可改成 str = str.substring(0, iLength - 1);
str = rTrim(str); //递归调用
}
return str;
}


//去掉字串两边的空格
function trim(str)
{
return lTrim(rTrim(str));
}

function start_time(){
	<%if(((String)map1.get(ov.getType())).equals("NOT")){%>
	for(var i=18;i<=24;i++)
	{
		if(i==18){
		document.write("<option value=18:30>18:30</option>");
		}else{
		document.write("<option value="+i+":00>"+i+":00</option>");
		document.write("<option value="+i+":30>"+i+":30</option>");
		}
		
	}
	<%}else{%>
	for(var i=1;i<=24;i++){
		if(i<10){
		document.write("<option value=0"+i+":00>0"+i+":00</option>");
		}else if(i>=10){
		document.write("<option value="+i+":00>"+i+":00</option>");
		}
	}
	<%}%>
}

function end_time(){
	<%if(((String)map1.get(ov.getType())).equals("NOT")){%>
		for(var i=19;i<=24;i++){
		document.write("<option value="+i+":00>"+i+":00</option>");
		document.write("<option value="+i+":30>"+i+":30</option>");
		}

		for(var i=1;i<=8;i++){
		if(i<10){
		document.write("<option value=0"+i+":00>0"+i+":00</option>");
		document.write("<option value=0"+i+":30>0"+i+":30</option>");
		}
	}
	<%}else{%>
	for(var i=1;i<=24;i++){
		if(i<10){
		document.write("<option value=0"+i+":00>0"+i+":00</option>");
		document.write("<option value=0"+i+":30>0"+i+":30</option>");
		}else if(i>=10){
		document.write("<option value="+i+":00>"+i+":00</option>");
		document.write("<option value="+i+":30>"+i+":30</option>");
		}
	}
	<%}%>
}

function kc_start(){
	var start=document.addworkForm.start_time.value;
	var end=document.addworkForm.end_time.value;
	if(start>end){
	alert("开始时间不能小于结束时间");
	}
	<%if(!((String)map1.get(ov.getType())).equals("NOT")){%>
		
		if(start>="08:00" && end <="17:30" ){
		document.all.kc.innerHTML="1";
		document.addworkForm.deduct_time.value=1;
		}
		if(start>="08:00" && end >="18:30" ){
		document.all.kc.innerHTML="2";
		document.addworkForm.deduct_time.value=2;
		}
		if(start>="17:30" && end >="18:30" ){
		document.all.kc.innerHTML="1";
		document.addworkForm.deduct_time.value=1;
		}
		if(start>="18:30" && end >="18:30" ){
		document.all.kc.innerHTML="0";
		document.addworkForm.deduct_time.value=0;
		}
		<%}%>
}

function check(){
var rl1=document.addworkForm.rlflag.value;
var rl2=document.addworkForm.OTdate.value;
if(rl1 != rl2){
document.addworkForm.rlflag.value=rl2;
document.addworkForm.page.value="ess_WorkApplyOvertime";
document.addworkForm.pageDate.value=rl2;
document.addworkForm.submit();
}
window.setTimeout("check()",1000);
}
 //-->
</SCRIPT>
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
	 
%>

<form name="addworkForm" action="/EssContorlServlet">
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
	<input type="hidden" name="empID" value="<%=admin.getAdminID()%>"/>
	<input type="hidden" name="content" value="WorkApplyOvertimeContentImp"/>
	<input type="hidden" name="command" value="WorkApplyCommandImp"/>
	<input type="hidden" name="otTypeCode" value="<%=(String)map1.get(ov.getType())%>"/>
	<input type="hidden" name="deduct_time" value="<%if(((String)map1.get(ov.getType())).equals("NOT")){%>0<%}%>"/>
	<input type="hidden" name="rlflag" value="<%=pageDate%>"/>
	<input type="hidden" name="page" value=""/>
	<input type="hidden" name="pageDate" value="<%=pageDate%>"/>
<div align="left">
<span class="title1">申请信息>加班申请</span></div>
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">加班日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="OTdate" class="content" style="width:90px " onclick="show_cele_date('','1970-1-1','2010-1-1',this)"  readonly="true" value="<%=pageDate%>">&nbsp;</td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">申请日期</td>
    <td width="34%" height="30"  align="left"><%=yearCurrentDate%></td>
  </tr>
   <tr align="center">
     <td height="30"  align="center" bgcolor="#F5F5F5">上班开始时间</td>
     <td height="30"  align="left"><%=ov.getFrom_time()%></td>
     <td height="30"  align="center" bgcolor="#F5F5F5">上班结束时间</td>
     <td height="30"  align="left"><%=ov.getTo_time()%></td>
   </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">加班开始时间</td>
    <td width="28%" height="30"  align="left"><select name="start_time" style="width: 90px " onchange="kc_start()">
	                                           <script>start_time()</script>
											   </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">隔天</td>
    <td width="34%" height="30"  align="left"><%if(((String)map1.get(ov.getType())).equals("NOT")){%>
    <select name="anotherDay" style="width: 70px ">
    <%}else{%>
    <select name="anotherDay" style="width: 70px " disabled="true">
    <%}%>
												<%
													for(int k=0;k<=1;k++){
														out.println("<option value=\""+k+"\">"+k+"</option>");
													}
												%>
	                                               </select></td>
  </tr>
  
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">加班结束时间</td>
    <td width="28%" height="30"  align="left"><select name="end_time" style="width: 90px " onchange="kc_start()">
	                                       		<script>end_time()</script>
											   </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">扣除时间</td>
    <td width="34%" height="30"  align="left"><div id="kc"></div></td>
    
  </tr>
  <tr align="center">
      <td height="30"  align="center" bgcolor="#F5F5F5">加班类型</td>
      <td height="30"  align="left"><%=(String)map2.get(ov.getType())%></td>
      <td height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
      <td height="30"  align="left">&nbsp;</td>
    </tr>
    <%
	   String empidaffirmed = admin.getAdminID();
	   String affirmcodefront ="";
	   String affirmnamefront ="";
	   affirm_list =(ArrayList)affirmdao.getAffirmorList(empidaffirmed,"OtApply");
	   if(affirm_list.size() > 0){
	    for(int i=1;i<=affirm_list.size();i++){
	     codemap = (HashMap)affirm_list.get(i-1);
	     if((i%2)==1){
		     affirmcodefront =(String)codemap.get("affirmorID");
		     affirmnamefront =(String)codemap.get("affirmor");
			 }
		 if((i%2)==0&&i!=0){
	 %>
    
    <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i-1%>级决裁者</td>
    <td width="28%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="34%" height="30"  align="left"><select name="affirmlist"style="width: 70px ">
	                                          <option value="<%=codemap.get("affirmorID")%>"><%=codemap.get("affirmor")%></option>
											  <option value="noperson">无</option>
											  </select></td>
  </tr>
  <%
    affirmcodefront="";
    affirmnamefront="";
    }
	if(i==affirm_list.size() && i%2==1){
  %>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">第<%=i%>级决裁者</td>
    <td width="28%" height="30"  align="left"><select name="affirmlist" style="width: 90px ">
	                                          <option value="<%=affirmcodefront%>"><%=affirmnamefront%></option>
											  <option value="noperson">无</option>
											  </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="34%" height="30"  align="left">&nbsp;</td>
  </tr>
  <%}}}%>
  <tr>
     <td align="center" bgcolor="#F5F5F5" height="90px">工作内容</td>
    <td colspan="3"><textarea name="work_content" style=" height: 90px;width:400px "></textarea></td></tr>
</table>
<script language="javascript">
  document.all.kc.innerHTML=<%if(((String)map1.get(ov.getType())).equals("NOT")){%>0<%}else{%>0<%}%>;
  </script>
  <SCRIPT>check()</SCRIPT>
</form>
</body>
</html>
