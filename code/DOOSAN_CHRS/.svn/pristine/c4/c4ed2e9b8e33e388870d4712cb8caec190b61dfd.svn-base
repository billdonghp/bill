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
	if (document.save.health_test_date.value == "") 
	{
		alert ("\n请填写体检日期！");
		document.save.health_test_date.focus();
		return false;
	}
	
	if (isNaN(document.save.health_height.value)) 
	{
		alert ("\n 身高应该是数字型！");
		document.save.health_height.focus();
		return false;
	}
	if (isNaN(document.save.health_weight.value)) 
	{
		alert ("\n 体重应该是数字型！");
		document.save.health_weight.focus();
		return false;
	}
	if (isNaN(document.save.health_left_eye.value)) 
	{
		alert ("\n 视力应该是数字型！");
		document.save.health_left_eye.focus();
		return false;
	}
	if (isNaN(document.save.health_right_eye.value)) 
	{
		alert ("\n 视力应该是数字型！");
		document.save.health_right_eye.focus();
		return false;
	}
	var reg=/^[1,2]\d{3}-\d{1,2}-\d{2}$/;
	var date=document.save.health_test_date.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.health_test_date.focus();
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
<span class="title1">基本信息申请>健康信息申请</span></div>
<form name="save" action="/EssContorlServlet">
<input name="command" value="InfoApplyCommandImp" type="hidden">
<input name="content" value="InfoApplyHealthContentImp" type="hidden">
<input name="empID" value="<%=admin.getAdminID()%>" type="hidden">
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">体检日期</td>
    <td width="28%" height="30"  align="left"><input type="text" name="health_test_date" class="content" style="width:94px "  >&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('save','health_test_date');" style="cursor:hand"></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">身高(cm)</td>
    <td width="34%" height="30"  align="left"><input maxlength="10" name="health_height" type="text" class="content" style="width:94px "  value=""/></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">体重(kg)</td>
    <td width="28%" height="30"  align="left"><input maxlength="10" name="health_weight" type="text" class="content" style="width:94px "  value=""/></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">左眼视力</td>
    <td width="34%" height="30"  align="left"><input maxlength="10"  name="health_left_eye" type="text" class="content" style="width:94px "  value=""/></td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">右眼视力</td>
    <td width="28%" height="30"  align="left"><input maxlength="10"  name="health_right_eye" type="text" class="content" style="width:94px "  value=""/></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">辨色力</td>
    <td width="28%" height="30"  align="left"><input maxlength="10" name="health_colour" type="text" class="content" style="width:94px "  value="" /></td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">血型</td>
    <td width="28%" height="30"  align="left"><select name="health_blood" style="width:94px " >
            <option value=""></option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("BloodTypeCode");
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
			%>
            <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
            <%	}
					}catch (Exception e){
					}
			%>
          </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">备注</td>
    <td width="28%" height="30"  align="left"><input maxlength="200"  name="health_content" type="text" class="content" value="" size="" style="width:94px " /></td>
  </tr>

</table>
</form>
</body>
</html>
