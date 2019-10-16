<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,java.util.Date,java.text.*,com.ait.util.*" errorPage="" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="affirmdao" class="com.ait.sy.dao.AffirmDAO" scope="request"/>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<jsp:useBean id="affirm_list" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/meizzDate.js"></script>
<title>家庭信息申请</title>
</head>
<body>
<%@ include file="/inc/hrtoolbar_a.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<SCRIPT type="text/javascript">
<!--hidden
function CheckForm()
{
	if (document.save.qualificationType.value == "") 
	{
		alert ("\n 请选择证书类型！");
		document.save.qualificationType.focus();
		return false;
	}
	var reg=/^[1,2]\d{3}-\d{1,2}-\d{2}$/;
	var date=document.save.qualificationDate.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.qualificationDate.focus();
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
<span class="title1">基本信息申请>资格证书申请</span></div>
<form name="save" action="/ess/ess0303_t.jsp">
<input name="menu_code" value="<%=menu_code%>" type="hidden">
<input name="empID" value="<%=admin.getAdminID()%>" type="hidden">
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">证件号</td>
    <td height="30" colspan="3"  align="left"><input maxlength="20"  type="text"  name="qualificationNumber" style="width:140px "> </td>
    </tr>
   <tr align="center">
    <td width="30%" height="30"  align="center" bgcolor="#F5F5F5">类型</td>
    <td width="19%" height="30"  align="left"><select name="qualificationType" style="width:140px ">
			<option value="">-------</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("QualNameCode");
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
    <td width="34%" height="30"  align="left"><input maxlength="25" type="text" name="qualificationGrade"  style="width:140px "> </td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">发证机关</td>
    <td width="19%" height="30"  align="left"><input maxlength="100" type="text" name="qualificationInstitute"  style="width:140px "> </td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">取证日期</td>
    <td width="19%" height="30"  align="left"><input type="text" name="qualificationDate"
	    class="content" style="width:140px "
	    readonly onClick="setday(this);"></td>
  </tr>
</table>
</form>
</body>
</html>
