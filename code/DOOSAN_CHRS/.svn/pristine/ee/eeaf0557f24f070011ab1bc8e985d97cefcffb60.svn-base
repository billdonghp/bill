<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>家庭信息申请</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden

function CheckForm()
{
	if (document.save.military_type.value == "") 
	{
		alert ("\n 请选择兵役种类！");
		document.save.military_type.focus();
		return false;
	}
	
	if (document.save.military_start.value == "") 
	{
		alert ("\n 请填写服役开始日期！");
		document.save.military_start.focus();
		return false;
	}
	
	var reg=/^[1,2]\d{3}-\d{1,2}-\d{2}$/;
	var date=document.save.military_start.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.military_start.focus();
		return false;
	}
	date=document.save.military_end.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.military_end.focus();
		return false;
	}
	return true;
}
function Save(){
	if(CheckForm()){
    	document.save.submit();
	}
}
 //-->
</SCRIPT>
<div align="left">
<span class="title1">基本信息申请>兵役信息申请</span></div>
<form name="save" action="/EssContorlServlet">
<input name="command" value="InfoApplyCommandImp" type="hidden">
<input name="content" value="InfoApplyEscuageContentImp" type="hidden">
<input name="empID" value="<%=admin.getAdminID()%>" type="hidden">
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">兵役种类</td>
    <td width="28%" height="30"  align="left">
				<select name="military_type" style="width:140px ">
				  <option value=""></option>
				  <%
						try{
							Vector military_type_vector = SysCodeParser.getCode("MilitaryTypeCode");
							for ( int i=0; i < military_type_vector.size(); i++)
							{
								codemap = (HashMap) military_type_vector.elementAt(i);
					%>
				  <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
				  <%	}
						}catch (Exception e){
						}
					%>
				</select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">兵役等级</td>
    <td width="34%" height="30"  align="left"><select name="military_level" style="width:140px ">
				  <option value=""></option>
				  <%
						try{
							Vector military_level_vector = SysCodeParser.getCode("MilitaryLevelCode");
							for ( int i=0; i < military_level_vector.size(); i++)
							{
								codemap = (HashMap) military_level_vector.elementAt(i);
					%>
				  <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
				  <%	}
						}catch (Exception e){
						}
					%>
				</select></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">兵役地区</td>
    <td width="28%" height="30"  align="left"><select name="military_area" style="width:140px ">
				  <option value=""></option>
				  <%
						try{
							Vector military_area_vector = SysCodeParser.getCode("MilitaryAreaCode");
							for ( int i=0; i < military_area_vector.size(); i++)
							{
								codemap = (HashMap) military_area_vector.elementAt(i);
					%>
				  <option value="<%=codemap.get("code")%>" ><%=codemap.get("name")%></option>
				  <%	}
						}catch (Exception e){
						}
					%>
				</select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td width="34%" height="30"  align="left"><input type="text" name="military_start" class="content" style="width:140px " >&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('save','military_start');" style="cursor:hand"></td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">结束日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="military_end" class="content" style="width:140px " >&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('save','military_end');" style="cursor:hand"></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">&nbsp;</td>
    <td width="28%" height="30"  align="left">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
