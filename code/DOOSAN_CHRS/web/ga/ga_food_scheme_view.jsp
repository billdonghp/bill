<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Add() {

	document.form1.action="/gaControlServlet?operation=retrieveDataForInsertFoodScheme&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.schemeName.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/gaControlServlet?operation=retrieveDataForUpdateFoodScheme&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.seqNo.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=deleteFoodScheme&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}

function band(backColor,textColor,i)
{
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.schemeName.value=i;
} 

</script>

<form name="form1" action="/gmControlServlet" method="post">
<input type="hidden" name="schemeName" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_all.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <br>
		
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">夜餐方案信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
					<td width="10%" class="info_title_01">类型</td>
					<td width="20%" class="info_title_01">夜餐食品</td>
					<td width="10%" class="info_title_01">添加人</td>
					<td width="10%" class="info_title_01">添加日期</td>
					<td width="10%" class="info_title_01">备注</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center"
					onClick="band('#E7F0EF','black','${oneResult.SEQ_NO}');">
					<td align="center" style="color: #666666;" height="27">${oneResult.SCHEME_NAME}&nbsp;</td>
					<td align="left" style="color: #666666;">
						<table width="100%">
							<c:forEach items="${foodDetaiList}" var="result">
								<c:if test="${result.SCHEME_NAME eq oneResult.SCHEME_NAME}">
									<tr>
										<td width="5%" align="left" style="color: #666666;">食品名称:
											<font color="#FF0000">${result.FOOD_NAME}</font>&nbsp;</td>
										<td align="left" style="color: #666666;">品牌:
											<font color="#FF0000">${result.BRAND}</font>&nbsp;</td>
										<td align="left" style="color: #666666;">规格:
											<font color="#FF0000">${result.SPECIFIC}</font>&nbsp;</td>
										<td align="left" style="color: #666666;">单价:
											<font color="#FF0000">${result.PRICE}</font>&nbsp;元</td>
										<td align="left" style="color: #666666;">数量:
											<font color="#FF0000">${result.QUENTITY}</font>${result.UNIT}</td>
										<td align="left" style="color: #666666;">购买公司:
											<font color="#FF0000">${result.PURCHASE_CPNY}</font>&nbsp;</td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
					<td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.CREATED_BY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${recordCount < 6}">
				<c:forEach var="i" begin="1" end="${6-recordCount}" step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveSmockList&menu_code=${param.menu_code}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" /> <!-- Page Navigation End -->

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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</form>
</body>
</html>
