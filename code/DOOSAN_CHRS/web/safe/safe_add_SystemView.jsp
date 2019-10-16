<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="AllMenu" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<!-- safe_add_SystemView.jsp -->
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
	var fileaddress = document.form1.myfile.value;
	
	aa = fileaddress.lastIndexOf (".");
	var bb = fileaddress.substring(aa,fileaddress.lenght)
	fileaddress = bb.toLowerCase();
	var patrn1 = /.(doc|xls|pdf|ppt|jpg|gif|txt)$/;
	if(!patrn1.test(fileaddress)){   
      alert("文件格式不匹配！只可以添加Office格式的文档");   
      return;   
    }else{	
	fileaddress = fileaddress.split("\\").join("\\\\");
	document.form1.action="/safeControlServlet?operation=rules&content=add_file&menu_code=${param.menu_code}&myfile="+encodeURIComponent(fileaddress)+"&date="+document.form1.date.value+"&menu="+document.form1.menu.value;
	document.form1.fireSubmit(); 
	}
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
		<form name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">制度添加</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="10%" class="info_title_01">上传文件</td>
				<td class="info_content_00">
					<input type='file' name='myfile' value="" size='47'>&nbsp;
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">实施日期</td>
				<td class="info_content_00">
					<input type="text" name="date" value="" onClick="setday(this);" readonly>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">存放目录</td>
				<td class="info_content_00">
					<select name="menu">
						<c:forEach items="${AllMenu}" var="AllMenu" varStatus="i">
							<option value="${AllMenu.CODE_ID}">
								 <c:forEach begin="2" end="${AllMenu.LEVEL}" step="1">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 </c:forEach>
								${AllMenu.CODE_NAME}
							</option>
						</c:forEach>
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
