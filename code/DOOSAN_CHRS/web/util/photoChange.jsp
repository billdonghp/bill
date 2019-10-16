<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function CheckForm() {
	if (document.PhotoForm.photo.value == '')
	{
	//"请选择图片"
	alert('<ait:message  messageID="alert.emp.staff_info.basic_info.select_picture" module="hrm"/>');
	return false;
	}
	else {
	return true;
	}
}
//-->
</SCRIPT>
<%@ page import="com.ait.utils.Photo"%>
<% 
    Photo photo = new Photo();  
    String flag = "";
	String empID = "";
	String parurl = "";
	String menu_code = "";
	int ing = 0,ind = 0;
   if(request.getParameter("empid")!=null && !request.getParameter("empid").equals("")){
        empID = request.getParameter("empid");
   }
   
    if(request.getParameter("flag")!=null && !request.getParameter("flag").equals("")){
	   flag = request.getParameter("flag");
	 }
	if(request.getParameter("parurl")!=null && !request.getParameter("parurl").equals("")) {
	   parurl = request.getParameter("parurl");
	   if(parurl.lastIndexOf("/") != -1)
	   ing = parurl.lastIndexOf("/")+1;
       ind = parurl.indexOf("_");
       menu_code=parurl.substring(ing,ind);
	   
	 }
%>
<body><br>

<script language="javascript">
   function onOK(){     
	 window.opener.location.href ="<%=parurl%>?menu_code=<%=menu_code%>&EmpID=<%=empID%>";	
	 document.PhotoForm.submit();
	 window.close();
   
   }
   
   function onDel(){            
	  window.opener.location.href = "<%=parurl%>?menu_code=<%=menu_code%>&EmpID=<%=empID%>";	
	  window.close();
	  
   }
</script>



<table width="570">
<form name="PhotoForm" method="post" enctype="multipart/form-data" action="/PhotoControl?flag=insert&empID=<%=empID%>">
<tr>
	 <td colspan="4" class="font1" style="padding-left:40px"><font color="red"><span class="style1">
	 <!--请选择要更换的照片--><ait:message  messageID="alert.emp.staff_info.basic_info.select_change" module="hrm"/>
	 </span></font></td>
</tr>
<tr> 
  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
</tr>
<tr>
  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
</tr>
<tr>
	<td align=left bgcolor="F2F2F2" class="font1" style="padding-left:40px"><b> <!--图片1-->
  <ait:message  messageID="display.emp.staff_info.basic_info.picture" module="hrm"/></b></td>
	<td colspan="3" style="padding-left:40px" class="ir">
	<input type="file" name="photo" class="box" size="50">
	</td>
</tr>
<tr>
  <td colspan="4" bgcolor="#ABABAB" height="1"></td>
</tr>
<tr> 
  <td colspan="4" bgcolor="#ABABAB" height="3"></td>
</tr>
<tr> 
  <td colspan="4" height="20" style="padding-left:40px"><span class="style1">
  <!-- 注:所选择的图片必须为jpg或gif格式的,文件名为员工工号！ -->
  <ait:message  messageID="display.emp.staff_info.basic_info.format_picture" module="hrm"/>
  </span></td>
</tr>
<tr>
	<td>
	</td>
	<td align="center"><!-- "提交" -->
		<input name="Ok" type="button" value='<ait:message  messageID="button.emp.submit" module="hrm"/>' onClick="if(CheckForm()) PhotoForm.submit();">
	</td>
	<td><!-- "复位" -->
		<input name="Reset" type="reset" value='<ait:message  messageID="button.emp.reset" module="hrm"/>' class="reset"></td>
	<td align="right">&nbsp;</td>
</tr>
</form>
</table>
</body>
</html>
