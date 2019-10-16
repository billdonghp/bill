<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="visiterViewDetail" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

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
			<%@ include file="../inc/toolbar_onlyback.jsp"%>
			
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
		<form name="form1" method="post" action="" enctype="multipart/form-data">

		
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					车辆使用信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">序号</td>
				<td nowrap="nowrap" class="info_title_01">车辆名称</td>
				<td nowrap="nowrap" class="info_title_01">型号</td>
				<td nowrap="nowrap" class="info_title_01">牌号</td>
				<td nowrap="nowrap" class="info_title_01">乘用人</td>
				<td nowrap="nowrap" class="info_title_01">座位数</td>
				<td nowrap="nowrap" class="info_title_01">目的地</td>
				
				
				<td nowrap="nowrap" class="info_title_01">使用区间</td>
			</tr>
			<c:forEach items="${list}" var="test" varStatus="j">
			<tr>			
				<td nowrap="nowrap" class="info_content_01">${j.index+1 } &nbsp; </td>
				<td nowrap="nowrap" class="info_content_01">${test.voiture_Brand }&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01">${test.voiture_Model }&nbsp;</td>
					
				<td nowrap="nowrap" class="info_content_01">${test.voiture_Number }&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01">${test.LARDER }&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01">${test.Seats }&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01">${test.DESTINATIONS }&nbsp;</td>
				
				
				<td nowrap="nowrap" class="info_content_01">${test.star }&nbsp;&nbsp;至&nbsp;&nbsp;${test.enddate }&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</form>
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
<ait:xjos />
</body>

</html>