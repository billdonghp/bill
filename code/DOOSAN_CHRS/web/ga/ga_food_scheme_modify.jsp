<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<SCRIPT type="text/javascript">

function Save() {
	
	document.form1.action="/gaControlServlet?operation=updateFoodScheme&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}
</SCRIPT>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="schemeName" value="${result.SEQ_NO}">
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
		<td valign="TOP" align="CENTER"><br>
		<br>
		<table id="Tables" width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">夜餐方案信息</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">名称</td>
				<td nowrap="nowrap" class="info_content_00">
					${result.SCHEME_NAME }
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="remark" rows="2" cols="25">${result.REMARK }</textarea></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">食品名称</td>
				<td width="15%" class="info_title_01">品牌</td>
				<td width="15%" class="info_title_01">规格</td>
				<td width="15%" class="info_title_01">单位</td>
				<td width="15%" class="info_title_01">单价</td>
				<td width="15%" class="info_title_01">数量</td>
				<td width="15%" class="info_title_01">总价</td>
			</tr>
			<c:forEach items="${foodDetaiList}" var="results">
				<tr>
					<c:if test="${result.SCHEME_NAME eq results.SCHEME_NAME}">
						<td nowrap="nowrap" class="info_content_01">${results.FOOD_NAME }</td>
						<td nowrap="nowrap" class="info_content_01">${results.BRAND }</td>
						<td nowrap="nowrap" class="info_content_01">${results.SPECIFIC }</td>
						<td nowrap="nowrap" class="info_content_01">${results.UNIT }</td>
						<td nowrap="nowrap" class="info_content_01">${results.PRICE }&nbsp;元</td>
						<td nowrap="nowrap" class="info_content_01">${results.QUENTITY }</td>
						<td nowrap="nowrap" class="info_content_01">${results.PRICE * results.QUENTITY }&nbsp;元</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(recordList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(recordList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<br>
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
</form>
</body>
<ait:xjos/>
</html>
