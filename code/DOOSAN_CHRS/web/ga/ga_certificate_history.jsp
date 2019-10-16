<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<ait:image src="/images/btn/Back.gif"  border="0" align="right"
          	onclick="javascript:history.back();" style="cursor:hand" title="后退" enTitle="return" />

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
				<td align="left" class="title1" colspan="10">证件信息历史查看</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="8%" class="info_title_01">部门</td>
				<td width="8%" class="info_title_01">证书名称</td>
				<td width="8%" class="info_title_01">首次发证时间</td>
				<td width="8%" class="info_title_01">发证机关</td>
				<td width="8%" class="info_title_01">更新周期</td>
				<td width="8%" class="info_title_01">更新日期</td>
				<td width="8%" class="info_title_01">负责人</td>
				<td width="8%" class="info_title_01">备注</td>
				<td width="8%" class="info_title_01">更新人</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td align="center" style="color: #666666;" height="27">${oneResult.DEPTNAME}</td>
					<td align="center" style="color: #666666;">${oneResult.CERTIFICATE_ID}</td>
					<td align="center" style="color: #666666;">${oneResult.ISSUE_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.ISSUING_AUTHORITY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PERIOD_NUM}${oneResult.PERIOD_UNIT}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.NOVATIONED_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.SUPERINTEND}</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.UPDATED_BY}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		</form>
		
		<!-- Page Navigation End -->

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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
<ait:xjos />
</html>
