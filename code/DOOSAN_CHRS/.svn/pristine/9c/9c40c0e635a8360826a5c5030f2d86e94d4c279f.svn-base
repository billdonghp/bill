<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%@page import="com.ait.gm.bean.*"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date"%>
<html>
	<head>
		<script src="../js/prototype.js"></script>
		<link href="../css/default.css" rel="stylesheet" type="text/css">
		<link href="../css/xjos.css" rel="stylesheet" type="text/css">
		<style>
body {
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
	
	document.form1.action="/gmControlServlet?operation=gm_menu_save&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}

</script>
	</head>
	<body>
		<%
			Date d = new Date();
			SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");

			String today = timeFormatter.format(d);
		%>
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
				<td background="../images/tablbk01_r4_c1.gif" width="11">
					&nbsp;
				</td>
				<td valign="TOP" align="CENTER">
					<!-- display basic info -->
					<!-- display 3 level menu -->

					<!-- display content -->
					<br>
					<form name="form1" method="post" action="">
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td align="left" class="title1" colspan="10">
									菜谱制作
								</td>
							</tr>
						</table>
						<table width="100%" border="1" cellspacing="0" cellpadding="5"
							bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
							style="padding: 2px 2px 2px 2px;">
							<input type="hidden" name="gmIds" value="" />

							<tr>

								<td width="10%" class="info_title_01">
									日期
								</td>
								<td class="info_content_00">
									<input type="text" style="width: 95px" name="MENU_DATE"
										onClick="setday(this);" value="<%=today%>" readonly="readonly" />
								</td>
							</tr>
							<tr>

								<td nowrap="nowrap" class="info_title_01">
									餐饮类别
								</td>
								<td nowrap="nowrap" class="info_content_00">
									<ait:codeClass codeClass="RepastType" name="MENU_EATTYPE" />
								</td>
							</tr>
							<tr>
								<td width="10%" class="info_title_01">
									
									菜品名称
								</td>
								<td class="info_content_00">

									<select name="VEGET_ID">
										<c:forEach items="${gmVegetableALL}" var="oneResult"
											varStatus="i">
											<option value="${oneResult.VEGET_ID}">
												${oneResult.VEGET_NAME}
											</option>
										</c:forEach>
									</select>

								</td>
							</tr>
							
							<tr>

								<td width="10%" class="info_title_01">
									菜品制作担当
								</td>
								<td class="info_content_00">
									<input name="MAKER" id="MAKER" type="text" size="10" />
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
