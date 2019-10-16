<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@page import="com.ait.gm.bean.*" %>
<jsp:useBean id="getTime" class="java.util.ArrayList" scope="request"/>
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

function Add(){
	document.form1.action="/gmControlServlet?menu_code=ga0705&operation=eateryAction&content=addInfo";
	document.form1.submit();	
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
		<%@ include file="../inc/common_toolbar_add.jsp"%>

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
				<td align="left" class="title1" colspan="10">就餐异常信息添加</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	异常日期 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          <input type="text" name="listDD"  value="${today}" onClick="setday(this);" readonly style="width:70px">
	          </td>
	      </tr>
	      <tr>
	          <td nowrap="nowrap" class="info_title_01">
			     餐别
			  </td>
	          <td nowrap="nowrap" class="info_content_00"><!-- 餐别-->
	          	<select name="gmtype" style="width:70px">
	          		<c:forEach items="${gmType}"  var="TYPE" varStatus="i">
	          		
	          			<option value="${TYPE.GM_ID}">${TYPE.GM_TYPE}</option>
	          		
	          		</c:forEach>
	          	</select>
	          </td>
	        </tr>
	        <tr>
	          <td nowrap="nowrap" class="info_title_01">
			      备注
			  </td>
	         <td nowrap="nowrap" class="info_content_00">
			      <textarea name="Remarks" style="width:250px;height:50px"></textarea>
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
</html>
