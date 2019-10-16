<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script language="javascript">
function adddtz(){
date.location.href="/arControlServlet?operation=ar_pagecontrol&page=dynamicGroupAdd&menu_code=ar0203";

}
function closedtz()
{
	document.form1.action="aritem_re.jsp";
	document.form1.submit();
}

</script>
</head>

<body>
<form name="form1" method="post">
<table width="100%"  border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td width="50%" height="395"><iframe width="100%" height="100%" marginwidth="0" marginheight="0" frameborder="0" name="date"></iframe></td>
  </tr>
  <tr>
    <td><input type="button" name="Submit" value="添加" onClick="adddtz()"><input type="button" name="Submit" value="关闭" onClick="closedtz()"></td>
  </tr>
</table>
</form>
</body>
</html>
