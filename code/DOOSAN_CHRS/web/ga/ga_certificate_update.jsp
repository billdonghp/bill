<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{	
	if(confirm("确认跟新数据!"))
	{
    	document.form1.action="/gaControlServlet?operation=insertCertificateUpdate&menu_code=${param.menu_code}";
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
		<form name="form1" method="post" action="">
			<input type="hidden" name="seqNo" value="${result.SEQ_NO}" />
			<input type="hidden" name="periodNum" value="${result.PERIOD_NUM}" />
			<input type="hidden" name="periodUnit" value="${result.PERIOD_UNIT}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">更新证件信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0"
			cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="15%" class="info_title_01">部门</td>
				<td class="info_content_00">${result.DEPTNAME}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">证件名称</td>
				<td class="info_content_00">${result.CERTIFICATE_ID}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">首次发证时间</td>
				<td class="info_content_00">${result.ISSUE_DATE}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">发证机关</td>
				<td class="info_content_00">${result.ISSUING_AUTHORITY}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">更新周期</td>
				<td class="info_content_00">${result.PERIOD_NUM}${result.PERIOD_UNIT_NAME}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">预更新日期</td>
				<td class="info_content_00">${result.NOVATION_DATE}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">负责人</td>
				<td class="info_content_00">${result.SUPERINTEND}</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">更新日期</td>
				<td class="info_content_00">
					<input type="text" name="updateDate" size="10" onclick="setday(this);" readonly="readonly" required/>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00">${result.REMARK}</td>
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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
<ait:xjos />
</html>
