<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="AllMenuInformation" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<script src="../js/prototype.js"></script>
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">

function Save()
{
	document.form1.action="/safeControlServlet?operation=rules&content=add_menu&menu_code=${param.menu_code}";
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
		<form name="form1" method="post" action="">
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">目录添加</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="10%" class="info_title_01">请填写目录</td>
				<td class="info_content_00">
					<input type="text" name="menuName" value=""/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">选择存放目录</td>
				<td class="info_content_00">
					<select name="menu">
						<c:forEach items="${AllMenuInformation}" var="AllMenu" varStatus="i">
							<option value="${AllMenu.CODE_ID}">
								<c:forEach begin="1" end="${AllMenu.LEVEL}" step="1">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</c:forEach>
								${AllMenu.CODE_NAME}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">目录显示顺序</td>
				<td class="info_content_00">
					<input type="text" name="orderno" value=""/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">目录显示状态</td>
				<td class="info_content_00">
					显示:&nbsp;<input type="radio" name="activity" value="1" checked="checked">&nbsp;
					不显示:&nbsp;<input type="radio" name="activity" value="0">
				</td>
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