<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="wasteType" class="java.util.ArrayList" scope="request"/>
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
	if(document.form1.wasteName.value == ""){
		alert("请选择废品名称");
		return;
	}
	
	if(document.form1.CalType.value == ""){
		alert("请选择单位");
		return;
	}
	
	if(document.form1.WASTE_SET_APPLICABLE_DATE.value == ""){
		alert("请填写适用日期");	
		return;
	}
	
	if(document.form1.WASTE_SET_QI_AN_ACCORDING_NO.value == ""){
		alert("请填写启案根据号");
		return;
	}
	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test(document.form1.wasteUnitPrice.value)){
      alert("废品单价必须输入数字或者小数！");
      return  false;
    }
    document.form1.action="/gmControlServlet?operation=waste&content=addWasteBasicInformation1&menu_code=${param.menu_code}";
	document.form1.submit();
}
</script>
</head>
<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
SimpleDateFormat timeFormatter1 = new SimpleDateFormat("yyyy-MM-ddHH:mm");		
String today=timeFormatter.format(d);
String today1=timeFormatter1.format(d);
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
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<input type="hidden" name="wasteTypeId" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">废品基本信息添加</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gmIds" value="" />
			
			<tr>
 				<td width="10%" class="info_title_01"> 废品名称</td>
				<td align="left" class="info_content_00">
					<select name="wasteName">
						<option value="">
								请选择
						</option>
	 					<c:forEach items="${wasteType}" var="view" varStatus="i">
							<option value="${view.CODE_ID}">
								${view.CODE_NAME}
							</option> 						
 						</c:forEach>
 					</select>
 					
 				</td>
 			</tr>
			<tr>
				<td width="10%" class="info_title_01"> 单位 </td>
 				<td align="left" class="info_content_00">
					<ait:codeClass name="CalType" codeClass="wasteUnit" all="all"/>
 				</td>
			</tr>
			<tr>
 				<td width="10%" class="info_title_01">单价</td>
				<td align="left" class="info_content_00">
					<input type="text" name="wasteUnitPrice" value="" size="10"/>&nbsp;元
				</td>
			</tr>
			<td width="10%" class="info_title_01"> 适用日期 </td>
				<td align="left" class="info_content_00">
 					<input type="text" style="width:95px" name="WASTE_SET_APPLICABLE_DATE" value="<%=today%>" onClick="setday(this);" readonly="readonly"/>
 				</td>
 			</tr>
 			
 			<tr>
			<td width="10%" class="info_title_01"> 起案根据号 </td>
				<td align="left" class="info_content_00">
 					<input name="WASTE_SET_QI_AN_ACCORDING_NO" type="text" size="10"/>
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
