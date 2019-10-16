<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<!-- ga_modify_checkAccount.jsp -->
<script language="javascript">
	
function Save()
{	
	var patrn=/^[0-9]*$/;
    if(!patrn.test(document.form1.CHECKACCOUNT.value)){
      alert("支票号必须输入数字！");
      return  false;
    }else if(document.form1.CHECK_TYPE.value == ''){
      alert("支票类型不可为空！");
      return  false;
    }else if(document.form1.CHECKACCOUNT.value == ''){
      alert("支票号不可为空！");
      return  false;
    }
    document.form1.action="/gaControlServlet?operation=CheckManager&content=updateSaveCheckAccount&menu_code=${param.menu_code}";
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
		<jodd:form fromRequest="true">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">支票号基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="SEQ_NO" value="${result.SEQ_NO}" />
			<tr>
				<td width="10%" class="info_title_01">银行名称</td>
				<td class="info_content_00">
					&nbsp;${result.BANKNAME}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">账号</td>
				<td class="info_content_00">
					&nbsp;${result.ACCOUNT}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">支票类型</td>
				<td class="info_content_00">
					<ait:codeClass name="CHECK_TYPE" codeClass="CheckType1" selected="${result.CHECK_TYPE}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">支票号</td>
				<td class="info_content_00">
				<input type="text" name="CHECKACCOUNT" size="27" value = "${result.CHECK_ACCOUNT}">
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">录入日期</td>
				<td class="info_content_00">
					&nbsp;${result.ENTRY_DATE}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="NOTE" rows="3" cols="20">${result.NOTE}</textarea>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">支票状态</td>
				<td class="info_content_00">
					<select name="ACTIVITY" style="width:100px">
					<option value="0" <c:if test="${result.ACTIVITY==0}">selected</c:if>>未使用</option>
					<option value="1" <c:if test="${result.ACTIVITY==1}">selected</c:if>>已使用</option>
					<option value="2" <c:if test="${result.ACTIVITY==2}">selected</c:if>>作废</option>
					<option value="3" <c:if test="${result.ACTIVITY==3}">selected</c:if>>使用中</option>
						</select>
				</td>
			</tr>
		</table>
		</jodd:form>		
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