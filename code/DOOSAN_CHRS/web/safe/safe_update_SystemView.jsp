<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*" %>
<jsp:useBean id="AllUpdateInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="AllMenuInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="singleMenu" class="java.lang.String" scope="request"/>
<jsp:useBean id="singleMenuId" class="java.lang.String" scope="request"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
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
var temp;
function Save()
{	
	var fileaddress = document.form1.file.value;
	
	aa = fileaddress.lastIndexOf (".");
	var bb = fileaddress.substring(aa,fileaddress.lenght)
	fileaddress = bb.toLowerCase();
	var patrn1 = /.(doc|xls|pdf|ppt|jpg|gif)$/;
	 if(!patrn1.test(fileaddress)){   
      alert("文件格式不匹配！只可以添加Office格式的文档");   
      return  false;   
    }
	if(document.form1.file.value == ""){
		document.form1.action="/safeControlServlet?operation=rules&content=update_file&menu_code=${param.menu_code}&uploadId="+document.form1.uploadId.value+"&fileloadpath="+document.form1.fileloadpath.value+"&singleMenu="+document.form1.singleMenu.value+"&date="+document.form1.date.value+"&flag=1";
		document.form1.fireSubmit(); 
	}else{
	    document.form1.action="/safeControlServlet?operation=rules&content=update_file&menu_code=${param.menu_code}&uploadId="+document.form1.uploadId.value+"&fileloadpath="+document.form1.fileloadpath.value+"&singleMenu="+document.form1.singleMenu.value+"&date="+document.form1.date.value+"&flag=2";
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
	<input type="hidden" name="temp1" value=""/>
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">制度修改</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
				<c:forEach items="${AllUpdateInformation}" var="Information" varStatus="i">
					<tr>
						<td width="10%" class="info_title_01">上传文件</td>
						<td class="info_content_00">
							<input type="file" name="file" value="" size='47'>&nbsp;
							<input type="hidden" name="fileaddress" value="${fileaddress}"/>
							<input type="hidden" name="uploadId" value="${Information.UPLOAD_ID}"/>
							<input type="hidden" name="fileloadpath" value="${Information.UPLOAD_PATH}"/>
						</td>
					</tr>
					<tr>
						<td width="10%" class="info_title_01">实施日期</td>
						<td class="info_content_00">
							<input type="text" name="date" value="${Information.IMPLEMENTATION_DATE}" onClick="setday(this);" readonly>
						</td>
					</tr>
					<tr>
						<td width="10%" class="info_title_01">所在目录</td>
						<td class="info_content_00">
						
							<select name="singleMenu">
								<c:forEach items="${AllMenuInformation}" var="AllMenuInformation" varStatus="j">
									
									<c:choose>
							         	<c:when test="${AllMenuInformation.CODE_ID==menuid}">
							         		<option value="${menuid}" selected>${AllMenuInformation.CODE_NAME}</option>
							         	</c:when>
							         	<c:otherwise>
							         		<option value="${AllMenuInformation.CODE_ID}">
							         		<c:forEach begin="2" end="${AllMenuInformation.LEVEL}" step="1">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											 </c:forEach>
							         			${AllMenuInformation.CODE_NAME}
							         		</option>
							         	</c:otherwise>
							       </c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:forEach>
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
