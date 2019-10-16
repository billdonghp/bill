<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<!-- ga_modify_present.jsp -->
<script language="javascript">

function Save()
{	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test(document.form1.UNIT_PRICE.value)){
      alert("单价必须输入数字或者小数！");
      return  false;
    }
    if(!patrn.test(document.form1.QUENTITY.value)){
      alert("数量必须输入数字！");
      return  false;
    }
    document.form1.action="/gaControlServlet?operation=updatePresentYtgl&menu_code=${param.menu_code}&cpnyId=${cpnyId}";
	document.form1.submit();
}
	function toggle(id){
		var div1=document.getElementById("id1");
		var div2=document.getElementById("id2");
		var div3=document.getElementById("id3");
		
		if(id=="PresentMarried"){
			div1.style.display = "block";
			div2.style.display = "none";
			div3.style.display = "none";
		}else if(id=="PresentCompany"){ 
			div1.style.display = "none";
			div2.style.display = "block";
			div3.style.display = "none";
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
		<form name="form1" method="post" action=""><input type="hidden"
			name="wasteTypeId" value="" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">礼品基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="SEQ_NO" value="${result.SEQ_NO}" />

			<tr>
				<td width="10%" class="info_title_01">礼品类型</td>
				<td class="info_content_00">
								&nbsp;${result.CODE_NAME}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">礼品名称</td>
				<td class="info_content_00">
				&nbsp;${result.PRESENT_NAME}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">单位</td>
				<td class="info_content_00">
					<ait:codeClass name="UNIT" codeClass="PresentUnit" selected="${result.UNIT}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">单价</td>
				<td class="info_content_00">
					<input type="text" name="UNIT_PRICE" size="10" value="${result.UNIT_PRICE}">&nbsp;元
				</td>
			</tr>
				<td width="10%" class="info_title_01">类型</td>
				<td class="info_content_00">
				 &nbsp;${result.DATA_TYPE_NAME}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">数量</td>
				<td class="info_content_00">
					<input name="QUENTITY" type="text" size="10" value="${result.QUENTITY}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">使用部门</td>
				<td class="info_content_00">
					&nbsp;${result.APPLY_DEPT_NAME}
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">备注</td>
				<td class="info_content_00">
					<textarea name="REMARK" rows="3" cols="20">${result.REMARK}</textarea>
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
