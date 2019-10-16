<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.StringUtil,com.ait.pa.PaDistinct"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Save(){
	var a = new Array();
	//请填写工资项目ID！ 请填写中文名称！请填写英文名称！ 您输入的中文名称字数太长，请在25个字符以内。
	a[0] = '<ait:message  messageID="alert.pay.enter_pay_id" module="pay"/>';
	a[1] = '<ait:message  messageID="alert.pay.enter_chinese" module="pay"/>';
	if ( CheckForm(a)){
	document.sf.submit();
	} 
}
function changeEmpDiff(cpnyId){
	location.href="/paControlServlet?operation=paParamItemConfigureList&method=addView&menu_code=${param.menu_code}&PARAM_ID=${paParamItim.PARAM_ID}&cpnyDiff="+cpnyId;
}
</script>
<script language="JavaScript" src="/pa/js/pa_param_item_configure.js"></script>
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
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目-->
					<ait:message  messageID="display.pay.maintenance.setting.item_setting" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<form method="post" name="sf" action="/paControlServlet?operation=paParamItemConfigureList&method=add&menu_code=${param.menu_code}" onSubmit="return CheckForm()">
		<tr>
		<td width="150" height="26" class="info_title_01">
					法人区分</td>
		<td colspan="3" style="padding-left:40px">
			<!--  -->
			<ait:codeClass codeClass="EMP_DIFF" name="CPNY_ID" onChange="changeEmpDiff(this.value)"  selected="${cpnyDiff}"/>
		</td>
		</tr>
	<tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--项目名称-->项目名称</td>
		<td colspan="3" style="padding-left:40px">
			<input type="hidden" name="PARAM_NO" value="${paParamItim.PARAM_NO}">
			${paParamItim.PARAM_ID} / ${paParamItim.PARAM_NAME}
		</td>
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--员工区分类型-->员工区分类型</td>
		<td colspan="3" style="padding-left:40px">
			<ait:empDiff name="STAT_TYPE_CODE"  cpnyDiff="${cpnyDiff}" />
		</td>
		</tr>
		<td width="150" height="26" class="info_title_01"><!--参数类型-->
					<ait:message  messageID="display.pay.maintenance.setting.add.parameter_type" module="pay"/></td>
		<td colspan="3" style="padding-left:40px">
			<ait:codeClass name="GENERATE_TYPE_CODE" codeClass="ParamTypeID" />
		</td>
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--区分项目-->
					<ait:message  messageID="display.pay.maintenance.setting.add.item1" module="pay"/></td>
		<td colspan="3" style="padding-left:40px">
			<select name="DISTINCT_FIELD">
				<%
				try{
					PaDistinct padistinct = new PaDistinct();
					Vector v = padistinct.getPaDistinctList();
					request.setAttribute("padistinctv",v);
				%>
				
				<c:forEach items="${padistinctv}" var="padistinctv">					
					<option value="${padistinctv.distinct_field}">
						<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
					</option>
				</c:forEach>
				<%}catch (Exception e){}%>
				</select></td>
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--区分项目2-->
					<ait:message  messageID="display.pay.maintenance.setting.add.item2" module="pay"/></td>
		<td colspan="3" style="padding-left:40px">
			<select name="DISTINCT_FIELD_2ND">
				<option value="" selected="selected"><!--选择-->
					<ait:message  messageID="display.mutual.select" /></option>
				<c:forEach items="${padistinctv}" var="padistinctv">
					<option value="${padistinctv.distinct_field}">
						<ait:content enContent="${padistinctv.fieldEnName}" zhContent="${padistinctv.field_name}" koContent="${padistinctv.fieldKorName}"/>
					</option>				
				</c:forEach>
				</select></td>		
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--考勤月-->
					<ait:message  messageID="display.mutual.attendance_month" /></td>
		<td colspan="3" style="padding-left:40px"><input name="PA_MONTH_STR" size="25" value=""></td>
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--数据类型-->
					<ait:message  messageID="display.mutual.data_type"  /></td>
		<td colspan="3" style="padding-left:40px">
			<ait:codeClass name="DATA_TYPE" codeClass="DataType" selected="NUMBER(14,4)" onChange="javascript:if(this.selectedIndex==1){ document.sf.DEFAULT_VAL.value='';}else{document.sf.DEFAULT_VAL.value='0'}"/>
		</td>
		</tr>
		<tr>
		<td width="150" height="26" class="info_title_01"><!--默认值-->
					<ait:message  messageID="display.pay.maintenance.setting.add.default" module="pay"/></td>
		<td colspan="3" style="padding-left:40px"><input name="DEFAULT_VAL" size="25" value="0"></td>
		</tr>
		</form>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
				<c:forEach var="i" begin="1" end="7"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						
					</tr>
				</c:forEach>
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
</html>
