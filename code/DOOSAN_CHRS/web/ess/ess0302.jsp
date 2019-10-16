<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.ess.entity.*,com.ait.util.*,java.util.Date,
				com.ait.ess.dao.*,java.util.*,java.text.*,
				com.ait.hr.entity.*,java.net.URLDecoder" errorPage="" %>
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
	if (document.save.familyrelation.value == "") 
	{
		alert ("\n 与本人关系未填写。");
		document.save.familyrelation.focus();
		return false;
	}
	
	if (document.save.chinesename.value == "") 
	{
		alert ("\n 亲属姓名未填写。");
		document.save.chinesename.focus();
		return false;
	}
	
	
	var phoneReg=/^\d{0,5}-?\d{1,5}-?\d+$/;
	var phone=document.save.phone.value;
	if (phone != "" && !phone.match(phoneReg))
	{
		alert ("\n 联系电话格式错误!\n\n正确格式：0086-10-24342342!");
		document.save.phone.focus();
		return false;
	}
	var reg=/^[1,2]\d{3}-\d{1,2}-\d{2}$/;
	var date=document.save.borndate.value;
	if(date != "" && !date.match(reg))
	{
		alert ("\n 日期格式不正确!\n\n正确格式：2005-10-24!");
		document.save.borndate.focus();
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
<%
     String timeFormat = "yyyy-MM-dd hh:mm:ss";
     SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
     String sDate = timeFormatter.format(new Date());
	 String yearCurrentDate = StringUtil.substring(sDate, 10);
	 String errorCode = request.getParameter("errorcode");
%>
<script language="javascript" src="../js/meizzDate.js"></script>
<div align="left">
<span class="title1">基本信息申请>家庭信息申请</span></div>
<form name="save" action="/ess/ess0302_t.jsp" >
<input name="menu_code" value="<%=menu_code%>" type="hidden">
<input name="empID" value="<%=admin.getAdminID()%>" type="hidden">
<table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">关系类型</td>
    <td width="28%" height="30"  align="left"><select name="familyrelation" style="width:85px ">
			<option value="">-----</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("RelationalTypeCode");
						for ( int i=0; i < vector.size(); i++)
						{
							codemap = (HashMap) vector.elementAt(i);
				%>
            <option value="<%=codemap.get("code")%>"><%=codemap.get("name")%></option>
            <%	    }
					}catch (Exception e){
					}
			%>
          </select></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">姓名</td>
    <td width="34%" height="30"  align="left"><input maxlength="20" type="text" name="chinesename" style="width:85px "></td>
  </tr>
   <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">学历</td>
    <td width="28%" height="30"  align="left"><select name="degree"  style="width:85px ">
			<option value="">-----</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("DegreeCode");
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
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">国籍</td>
    <td width="34%" height="30"  align="left"><select name="country"  style="width:85px ">
			<option value="">-----</option>
            <%
					try{
						Vector vector = SysCodeParser.getCode("NationalityCode");
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
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">出生日期</td>
    <td width="28%" height="30"  align="left"><input maxlength="20"   type="text" name="borndate"   onClick="setday(this);"  class="content" style="width:85px " ></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">工作单位</td>
    <td width="34%" height="30"  align="left"><input maxlength="200"  type="text" name="companyName" style="width:85px "></td>
  </tr>
  <tr align="center">
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">联系电话</td>
    <td width="28%" height="30"  align="left"><input maxlength="20"  type="text" name="phone" style="width:85px "></td>
    <td width="19%" height="30"  align="center" bgcolor="#F5F5F5">家庭住址</td>
    <td width="34%" height="30"  align="left"><input  maxlength="100" type="text" name="address" style="width:160px "></td>
  </tr>
</table>
</form>
</body>
</html>
