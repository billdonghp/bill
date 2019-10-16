<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test($F('price'))){
		alert("单价必须为数字!");
		return false;
	}
    document.form1.action="/gaControlServlet?operation=insertFoodProduct&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}
</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_a.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action=""><input type="hidden"
			name="wasteTypeId" value="" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">食品基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="15%" class="info_title_01">食品名称</td>
				<td class="info_content_00">
					<input type="text" name="foodName" size="15" required/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">品牌</td>
				<td class="info_content_00">
					<input type="text" name="brand" size="15" required/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">规格</td>
				<td class="info_content_00">
					<input type="text" name="specific" size="15" required/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">单位</td>
				<td class="info_content_00">
					<ait:codeClass codeClass="foodUnit" name="unit" all="all" />
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">单价</td>
				<td class="info_content_00">
					<input type="text" name="price" size="5" />&nbsp;元
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">购买公司</td>
				<td class="info_content_00">
					<input type="text" name="purchaseCpny" size="15" />
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="remark" rows="3" cols="25"></textarea></td>
			</tr>
		</table>
		</form>

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
<ait:xjos />
</html>
