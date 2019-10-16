<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.Vector,com.ait.kpa.Paformular,com.ait.utils.Func,com.ait.util.StringUtil"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Delete(id){
//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
	if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
		location.href="/kpaControlServlet?operation=kpa_diff_formular_data_delete&pa_item_no=${pa_item_no}&formular_no="+id;
		}
}
</script>
<body>
<div align="right"></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<form name="data" method="post" action="">
    	<tr align="center"> 
    		<td width="30" class="info_title_01"><!--顺序-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td width="25%" height="24" class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/></td>
			<td bgcolor="F7F7F7" class="info_title_01"><!--公式-->
					<ait:message  messageID="display.mutual.formula"/></td>
			<td width="50" class="info_title_01"><!--操作 -->
					<ait:message  messageID="display.mutual.operate"/>
				<a href="/kpa/kpa_diff_formular_data_add.jsp?pa_item_no=${pa_item_no}" >
					<img src="/images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt='<ait:message  messageID="display.mutual.add"/>'>
				</a>
			</td>
		</tr>
	
			<c:forEach items="${diffFormularList}" var="formular" varStatus="i">
			<tr align="center"> 
				<td class="ir">${formular.CONDITION_SEQ}</td>
                <td height="26" class="ir">${formular.CONDITION_CN}</td>
                <td class="ir">${formular.FORMULAR_CN}</td>
                <td class="ir">
	                <a href="/kpa/kpa_diff_formular_data_add.jsp?pa_item_no=${formular.PA_ITEM_NO}" ><!-- 添加  -->
 						<img src="/images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt='<ait:message  messageID="display.mutual.add"/>'>
	                </a>
	                <a href="/kpaControlServlet?operation=kpa_diff_formular_data_forUpdate&formular_no=${formular.FORMULAR_NO}"><!-- 察看详细  -->
	                	<img src="/images/left_menubullet_main_d.gif" width="14" height="22" border="0" alt='<ait:message  messageID="display.pay.formula.datail" module="pay"/>' align="absmiddle">
	                </a> 
	                <a href="javascript:Delete('${formular.FORMULAR_NO}')"><!-- 删除  -->
	                	<img src="/images/left_menubullet_sub_m.gif" width="14" height="22" alt='<ait:message  messageID="display.mutual.delete"/>' align="absmiddle">
	                </a>
                </td>
			</tr>
			</c:forEach>
	</form>
</table>
</body>
</html>