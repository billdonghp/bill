<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.SysCodeParser,com.ait.pa.PaParamItem,com.ait.pa.PaDistinct"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<%@page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Save(){
	var a = new Array();
	//请填写工资项目ID！ 请填写中文名称！请填写英文名称！ 您输入的中文名称字数太长，请在25个字符以内。
	a[0] = '<ait:message  messageID="alert.pay.enter_pay_id" module="pay"/>';
	a[1] = '<ait:message  messageID="alert.pay.enter_chinese" module="pay"/>';
	if ( CheckForm(a)){
	document.sf.submit();
	}
}
</script>
<script language="JavaScript" src="/pa/js/pa_param_item.js"></script>
<body>

<%
Func func = new Func();
String param_no = request.getParameter("param_no");
PaParamItem paramitem = PaParamItem.getPaParamItemDetail(param_no);

request.setAttribute("paramitem",paramitem) ;
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目-->
					<ait:message  messageID="display.pay.maintenance.setting.item_setting" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form method="post" name="sf" action="pa0201_t.jsp?flag=update&menu_code=${param.menu_code}&param_no=${param.param_no}" onSubmit="return CheckForm()">
		<tr>
		<td width="150" height="26" class="info_title_01"><!--法人区分-->法人区分</td>
		<td colspan="3" style="padding-left:40px">
			<c:if test="${paramitem.cpnyName eq NULL || paramitem.cpnyName eq '' }">公共&nbsp;</c:if>
		    <c:if test="${paramitem.cpnyName ne ''}">${paramitem.cpnyName}&nbsp;</c:if>
		</td>
		</tr>
		<tr>
			<td  width="150" height="26" class="info_title_01"><!--参数ID-->
					<ait:message  messageID="display.pay.maintenance.setting.add.parameter_name" module="pay"/>  <font color="red">*</font></td>
			<td colspan="3" style="padding-left:40px">
			<input name="param_id" size="30" maxlength="29" value="<%=StringUtils.trimToEmpty(paramitem.getParam_id())%>">&nbsp;&nbsp;参数ID长度不能大于30个字符,单词之间以_相连</td>
	</tr>
		<tr>
			<td  width="150" height="26" class="info_title_01"><!--参数名称 -->
					<ait:message  messageID="display.name_chinese" /><font color="red">*</font></td>
			<td colspan="3" style="padding-left:40px">
			<input name="param_name" size="30" maxlength="25" value="<%=paramitem.getParam_name()%>"></td>
	</tr>
	<tr>
	<td  width="150" height="26" class="info_title_01"><!--描述-->
					<ait:message  messageID="display.mutual.description" /></td>
	<td colspan="3" style="padding-left:40px"><input name="descr" size="50" value="<%=func.editNull(paramitem.getDescr())%>"></td>
	</tr>
	</form>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="10">
				<c:forEach var="i" begin="1" end="10"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						
					</tr>
				</c:forEach>
		</table>
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>

</body>
</html>
