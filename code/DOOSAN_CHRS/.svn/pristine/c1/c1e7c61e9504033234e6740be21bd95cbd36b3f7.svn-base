<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.SysCodeParser,com.ait.pa.PaParamItem,com.ait.pa.PaDistinct"%>
<%@ include file="../inc/taglibs.jsp"%>
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
		<table width="100%" height="300" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form method="post" name="sf" action="/paControlServlet?operation=pa_bonus_param_update&menu_code=${param.menu_code}&param_no=${bonusParam.PARAM_NO}" >
		<tr>
		<td width="150" height="26" class="info_title_01"><!--员工区分类型-->
					员工区分类型</td>
		<td colspan="3" style="padding-left:40px">
			<ait:empDiff name="statTypeCode"  cpnyDiff="${cpnyDiff}" selected="${bonusParam.STAT_TYPE_CODE}"/>
		</td>
		</tr>
		<tr>
			<td  width="150" height="26" class="info_title_01"><!--参数ID-->
					<ait:message  messageID="display.pay.maintenance.setting.add.parameter_name" module="pay"/>  <font color="red">*</font></td>
			<td colspan="3" style="padding-left:40px">
			<input name="param_id" size="30" maxlength="29" value="${bonusParam.PARAM_ID}">&nbsp;&nbsp;参数ID长度不能大于30个字符,单词之间以_相连</td>
	</tr>
		<tr>
			<td  width="150" height="26" class="info_title_01"><!--参数名称 -->
					<ait:message  messageID="display.name_chinese" /><font color="red">*</font></td>
			<td colspan="3" style="padding-left:40px">
			<input name="param_name" size="30" maxlength="25" value="${bonusParam.PARAM_NAME}"></td>
	</tr>
	<tr>
	<td  width="150" height="26" class="info_title_01"><!--区分项目-->
					<ait:message  messageID="display.pay.maintenance.setting.add.item1" module="pay"/></td>
	<td colspan="3" style="padding-left:40px">
		<select name="distinct_field">		
			<c:forEach items="${padistinctv}" var="padistinctv">
				<c:choose>
					<c:when test="${padistinctv.distinct_field == bonusParam.DISTINCT_FIELD}">
						<option value="${padistinctv.distinct_field}" selected="selected">
							<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
						</option>
					</c:when>
					<c:otherwise>
						<option value="${padistinctv.distinct_field}">
							<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
						</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</select></td>
	</tr>
	<tr>
	<td width="150" height="26" class="info_title_01"><!--区分项目2-->
					<ait:message  messageID="display.pay.maintenance.setting.add.item2" module="pay"/></td>
	<td colspan="3" style="padding-left:40px">
		<select name="distinct_field_2nd">
				<c:choose>
					<c:when test='${bonusParam.DISTINCT_FIELD_2ND == ""}'>
						<option value="" selected="selected"><!--选择-->
							<ait:message  messageID="display.mutual.select" /></option>
					</c:when>
					<c:otherwise>
						<option value=""><!--选择-->
							<ait:message  messageID="display.mutual.select" /></option>
					</c:otherwise>
				</c:choose>
				<c:forEach items="${padistinctv}" var="padistinctv">
					<c:choose>
						<c:when test="${padistinctv.distinct_field == bonusParam.DISTINCT_FIELD_2ND}">
							<option value="${padistinctv.distinct_field}" selected="selected">
								<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
							</option>
						</c:when>
						<c:otherwise>
							<option value="${padistinctv.distinct_field}">
								<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
	<td  width="150" height="26" class="info_title_01"><!--数据类型-->
					<ait:message  messageID="display.mutual.data_type"  /></td>
	<td colspan="3" style="padding-left:40px">
		<ait:codeClass name="data_type" codeClass="DataType" selected="${bonusParam.DATA_TYPE}"/></td>
	</tr>
	<tr>
	<td  width="150" height="26" class="info_title_01"><!--描述-->
					<ait:message  messageID="display.mutual.description" /></td>
	<td colspan="3" style="padding-left:40px"><input name="descr" size="50" value="${bonusParam.DESCR}"></td>
	</tr>
	</form>
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
