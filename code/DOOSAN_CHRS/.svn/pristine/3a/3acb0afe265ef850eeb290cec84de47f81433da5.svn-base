<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gm_id" class="java.lang.String" scope="request" />
<jsp:useBean id="gmtype" class="java.lang.String" scope="request" />
<jsp:useBean id="xs" class="java.lang.String" scope="request" />
<jsp:useBean id="fz" class="java.lang.String" scope="request" />
<jsp:useBean id="xse" class="java.lang.String" scope="request" />
<jsp:useBean id="fze" class="java.lang.String" scope="request" />
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean" />
<html>
<head>
<script src="../js/prototype.js"></script>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
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
	if(document.form1.typeName.value.length==0){
		alert("餐别不能为空!");
    	document.form1.typeName.focus();
     	return false;
	}
	document.form1.action="/gmControlServlet?operation=GM_UPDATESAVE&menu_code=${param.menu_code}";
	
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">就餐维护</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gm_id" value="${gm_id}" />

			<tr>

				<td width="10%" class="info_title_01"><!--职号--> 就餐类型</td>
				<td class="info_content_00"><input name="typeName" id="typeId"
					type="text" value="${gmtype}" size="10" maxlength="10" /></td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01"><!--职号--> 开始时间</td>
				<td align="left" style="color: #666666;">
					<select name="listHH">
						<c:forEach items="${listHH}" var="oneResult" varStatus="i">
							<option value="${oneResult}"
								<c:if test="${oneResult==xs }">selected="selected"</c:if>>${oneResult}
							</option>
						</c:forEach>
					</select> 
					： 
					<select name="listMM">
						<c:forEach items="${listMM}" var="oneResult" varStatus="i">
							<option value="${oneResult}"
								<c:if test="${oneResult==fz }">selected="selected"</c:if>>${oneResult}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01"><!--职号--> 结束时间</td>
				<td align="left" style="color: #666666;">
					<select name="listHHEND">
						<c:forEach items="${listHH}" var="oneResult" varStatus="i">
	
							<option value="${oneResult}"
								<c:if test="${oneResult==xse }">selected="selected"</c:if>>${oneResult}</option>
						</c:forEach>
					</select>
					 ： 
					<select name="listMMEND">
						<option value="00" <c:if test="${'00'==fze}">selected="selected"</c:if>>00</option>
		 				<option value="10" <c:if test="${'10'==fze}">selected="selected"</c:if>>10</option>
		 				<option value="20" <c:if test="${'20'==fze}">selected="selected"</c:if>>20</option>
		 				<option value="30" <c:if test="${'30'==fze}">selected="selected"</c:if>>30</option>
		 				<option value="35" <c:if test="${'35'==fze}">selected="selected"</c:if>>35</option>
		 				<option value="40" <c:if test="${'40'==fze}">selected="selected"</c:if>>40</option>
		 				<option value="45" <c:if test="${'45'==fze}">selected="selected"</c:if>>45</option>
		 				<option value="50" <c:if test="${'50'==fze}">selected="selected"</c:if>>50</option>
		 				<option value="55" <c:if test="${'55'==fze}">selected="selected"</c:if>>55</option>
					</select>
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
