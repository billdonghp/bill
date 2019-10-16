<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="securityChecksList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

function Save(){
   if(document.form1.PLANCOMPLETIONDATE.value==''){
   alert("计划完成日期不能为空！");
   return;
 }
 if(document.form1.ZHENGGAIPLANTEXT.value==''){
   alert("整改计划不能为空！");
   return;
 }
	document.form1.action="/safeControlServlet?operation=securityChecks&content=saveView&menu_code=${param.menu_code}";
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
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr> 
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display content -->
		<br>
		
      <form name="form1" method="post" action="">
      <input type="hidden" name="DOCUMENTNO" value="${DOCUMENTNO }">
			<table width="85%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">整改计划</td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="20%" class="info_title_01">计划完成日期</td>
				<td class="info_content_00">
				<input type="text" name="PLANCOMPLETIONDATE"  value="${PLANCOMPLETIONDATE }" style="width:80px "   readonly onClick="setday(this);" required></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">整改计划（500字以内）</td>
				<td class="info_content_00">
				<textarea rows="10" name="ZHENGGAIPLANTEXT" cols="100">${ZHENGGAIPLANTEXT }</textarea>
				</td>
			</tr>
		</table>
<br>
	</form>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10"></td>
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
<ait:xjos />
</body>

</html>
