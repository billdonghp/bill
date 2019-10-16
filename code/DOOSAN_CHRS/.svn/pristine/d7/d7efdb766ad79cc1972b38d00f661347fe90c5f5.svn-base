<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.ar.bean.ArStaFormular"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="formular" scope="page" class="com.ait.ar.dao.ArStaFormularBean">
</jsp:useBean>
<%int formular_no = Integer.parseInt(request
                    .getParameter("formularNo"));
            int item_no = Integer.parseInt(request.getParameter("itemNo"));
            ArStaFormular ar = formular.getRollformula(formular_no, item_no);
%>

<html>
	<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
		<script language="JavaScript">
function update()
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
	document.form1.action="/arControlServlet?operation=formularupdate";
	document.form1.submit();
	//"修改成功"
    alert('<ait:message  messageID="alert.att.edit_successfully" module="ar"/>');
    location.href="/ar/formular_date.jsp?item_no=<%=ar.getItem_no()%>";

}</script>
	</head>
	<body>
		<form name="form1" method="post">
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
				<tr align="center">
					<td width="20%" class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/>
					</td>
					<td height="24" width="60%" >
					<textarea name="condition" style="width:100%;height:70"><%=ar.getCondition_cn() == null ? "" : ar.getCondition_cn()%></textarea></td>
				</tr>
				<tr align="center">
					<td height="24" class="info_title_01"><!--公式-->
					<ait:message  messageID="display.att.setting.formula" module="ar"/></td>
					<td class="ir"><textarea name="formular" style="width:100%;height:140"><%=ar.getFormular_cn()%></textarea></td>
				</tr>
				<tr align="center">
					<td height="24" colspan="2" class="info_title_01" align="right" >
					<ait:image  src="../images/btn/Save.gif" onclick="update()"/>
					<!-- <img src="../images/btn/save2.jpg" onClick="update()"> -->
					</td>
				</tr>
			</table>
			<input type="hidden" name="itemNo" value="<%=ar.getItem_no()%>" />
			<input type="hidden" name="formularNo" value="<%=ar.getFormular_no()%>" />
		</form>
	</body>
</html>
