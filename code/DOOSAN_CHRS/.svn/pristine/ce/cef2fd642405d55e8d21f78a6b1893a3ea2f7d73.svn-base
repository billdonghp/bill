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
	if (document.save.languageTypecode.value == "") 
	{
		alert ("\n 请选择语言类型！");
		document.save.languageTypecode.focus();
		return false;
	}
	
	if (document.save.LanguageLevelCode.value == "") 
	{
		alert ("\n 请选择语言级别！");
		document.save.LanguageLevelCode.focus();
		return false;
	}
	if (document.save.languageQulificationName.value == "") 
	{
		alert ("\n 请选择证书类型！");
		document.save.languageQulificationName.focus();
		return false;
	}	
	
	var reg=/^[1,2]\d{3}-\d{1,2}-\d{2}$/;
	var date=document.save.languageQualificationDate.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.languageQualificationDate.focus();
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
<span class="title1">基本信息申请>外语事项申请</span></div>
<form name="save" action="/EssContorlServlet">
<input name="command" value="InfoApplyCommandImp" type="hidden">
<input name="content" value="InfoApplyLanguageContentImp" type="hidden">
<input name="empID" value="<%=admin.getAdminID()%>" type="hidden">
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">语言类型</td>
    <td width="28%" height="30"  align="left"><select name="languageTypecode" style="width:140px ">
            <option value="">-------</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("LanguageTypeCode");
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
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">级别</td>
    <td width="34%" height="30"  align="left"><select name="LanguageLevelCode" style="width:140px ">
            <option value="">-------</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("LanguageLevelCode");
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
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">证书类型</td>
    <td width="28%" height="30"  align="left"><select name="languageQulificationName" style="width:140px ">
            <option value="">-------</option>
      <%
					try{
						Vector vector = SysCodeParser.getCode("LanguageExamCode");
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
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">发证日期</td>
    <td width="34%" height="30"  align="left"><input type="text" name="languageQualificationDate" class="content"  style="width:140px ">&nbsp;<img src="/images/calendar/calender.gif" align="absMiddle" border="0" height="18" width="18" onClick="showCalendar('save','languageQualificationDate');" style="cursor:hand"> </td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">成绩</td>
    <td width="28%" height="30"  align="left"><input type="text" name="languageResult"  style="width:140px "> </td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">发证部门</td>
    <td width="28%" height="30"  align="left"><input type="text" name="qualificationInstitute" class="content"  style="width:140px "></td>
  </tr>
</table>
</form>
</body>
</html>
