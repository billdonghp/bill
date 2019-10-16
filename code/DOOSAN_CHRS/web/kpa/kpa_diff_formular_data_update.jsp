<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
	function Save()
	{
		document.data.fireSubmit(); 
	}
</script>
</head>
<body>
<div align="right"></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<form name="data" method="post" action="/kpaControlServlet?operation=kpa_diff_formular_data_update&formular_no=${diffFormular.FORMULAR_NO}&pa_item_no=${diffFormular.PA_ITEM_NO}">
              <tr align="center"> 
                <td width="30%" class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/></td>
                <td height="24" class="info_title_01">
                <textarea name="condition_cn" style="width:100%;height:70">${diffFormular.CONDITION_CN}</textarea>
                </td>
              </tr>          
              <tr align="center">
                <td height="24" class="info_title_01"><!--公式-->
					<ait:message  messageID="display.mutual.formula"/></td> 
                <td class="info_title_01">
                <textarea name="formular_cn" style="width:100%;height:140" required>${diffFormular.FORMULAR_CN}</textarea>
                </td>
              </tr>
              <tr align="center">
                <td height="24" colspan="2" class="info_title_01">
                <ait:image src="/images/btn/Save.gif" width="67" height="24" onclick="Save();"/>
                </td>
              </tr>
</form>
</table>
<script language="JavaScript" type="text/JavaScript">
function active(obj) {
	var newWindow = parent.open('/paControlServlet?operation=pa_util&payType=month','pa_util','toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width=754,height=300,left=240,top=0');
	if (newWindow.document.all("obj")) {
		newWindow.document.all("obj").value = obj;
	}
}
</script>

</body>
<ait:xjos />
</html>