<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<!-- ga_add_checkBank.jsp -->
<script language="javascript">

function Save()
{	
	var patrn=/^[0-9]*$/;
    if(!patrn.test(document.form1.ACCOUNT.value)){
      alert("账号必须输入数字！");
      return  false;
    }if(document.form1.CHECKBANK_TYPE.value == ''){
      alert("银行名称不可为空！");
      return  false;
    }
    document.form1.action="/gaControlServlet?operation=CheckManager&content=addBankAccount&menu_code=${param.menu_code}";
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
				<td align="left" class="title1" colspan="10">银行账号基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="10%" class="info_title_01">银行名称</td>
				<td class="info_content_00">
					<ait:codeClass name="CHECKBANK_TYPE" codeClass="CheckBankInfor" all="all"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">账号</td>
				<td class="info_content_00">
				<input type="text" name="ACCOUNT" size="27" required>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">录入日期</td>
				
				<td class="info_content_00">
					<input type="text" name="ENTRY_DATE" size="10"  onClick="setday(this);" readonly>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="NOTE" rows="3" cols="20"></textarea>
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