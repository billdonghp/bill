<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">
function Save()
{	
    if($F('unit')==""){
      alert("请选择单位！");
      return  false;
    }
    var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test($F('unitPrice'))){
		alert("单价必须为数字!");
		return false;
	}
    document.form1.action="/gaControlServlet?operation=updateFestivalPresent&menu_code=${param.menu_code}";
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
				<td align="left" class="title1" colspan="10">节日礼品基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="15%" class="info_title_01">礼品名称</td>
				<td class="info_content_00">
					<input type="hidden" name="seqNo" value="${result.SEQ_NO}">
					<input type="text" name="presentName" size="10" value="${result.PRESENT_NAME}" required>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">品牌</td>
				<td class="info_content_00">
					<input type="text" name="brand" size="10" value="${result.BRAND}" required>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">规格</td>
				<td class="info_content_00">
					<input type="text" name="specific" size="10" value="${result.SPECIFIC}" required>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">单位</td>
				<td class="info_content_00">
					<ait:codeClass codeClass="festivalPresentUnit" name="unit" selected="${result.UNIT_CODE}" all="all"/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">单价</td>
				<td class="info_content_00">
					<input type="text" name="unitPrice" size="10" value="${result.UNIT_PRICE}" required>&nbsp;元
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">添加人</td>
				<td class="info_content_00">
					${result.CREATED_BY}
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">修改人</td>
				<td class="info_content_00">
					<%=((AdminBean)request.getSession(false).getAttribute("admin")).getChineseName()%>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="remark" rows="3" cols="25">${result.REMARK}</textarea></td>
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
<ait:xjos/>
</html>
