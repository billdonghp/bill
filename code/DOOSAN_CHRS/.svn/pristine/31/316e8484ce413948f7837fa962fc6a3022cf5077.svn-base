<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%
int itemNo=Integer.parseInt(request.getParameter("itemNo"));
%>
<html>
<head>
  <LINK href="../css/calendar.css" rel="stylesheet" type="text/css">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function add()
{
	/*if(document.form1.condition.value.indexOf("考勤项目") >= 0)
	{
		alert("条件中不能包含考勤项目");
		return;
	}*/
	if(document.form1.formular.value=="")
	{//"请填写公式"
		alert('<ait:message  messageID="alert.att.setting.formula.enter_formula" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=formularadd";
	document.form1.submit();
	//"添加成功"
    alert('<ait:message  messageID="alert.att.add_successfully" module="ar"/>');
    location.href="/ar/formular_date.jsp?item_no=<%=itemNo%>";

}
</script>
</head>
<body>
<form name="form1" method="post" >
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
              <tr align="center">
                <td width="20%" class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/></td>
                <td height="24" width="60%"><textarea name="condition" style="width:100%;height:70"></textarea></td>
              </tr>
              <tr align="center">
                <td height="24" class="info_title_01"><!--公式-->
					<ait:message  messageID="display.att.setting.formula" module="ar"/></td>
                <td ><textarea name="formular" style="width:100%;height:140"></textarea></td>
              </tr>
              <tr align="center">
                <td height="24" colspan="2" class="info_title_01">
                	<ait:image src="../images/btn/Save.gif" width="67" height="24" onclick="add()"/>
                </td>
              </tr>
</table>
<input type="hidden" name="itemNo" value="<%=itemNo%>"/>
</form>
</body>
</html>
