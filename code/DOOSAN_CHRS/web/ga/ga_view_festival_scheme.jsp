<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Add() {

	document.form1.action="/gaControlServlet?operation=retrieveDataForInsertFestivalScheme&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.seqNo.value=="") {
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/gaControlServlet?operation=retrieveDataForUpdateFestivalScheme&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.seqNo.value=="") {
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=deleteFestivalScheme&menu_code=${param.menu_code}";
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
	document.form1.seqNo.value=i;
} 

function activityStatus(schemeNo,op)
{
	document.form1.action="/gaControlServlet?operation=updateSchemeActivity&menu_code=${param.menu_code}&op="+op+"&AseqNo="+schemeNo;
	document.form1.submit();
}
</script>

<form name="form1" action="/gmControlServlet" method="post"><input
	type="hidden" name="seqNo" value="" />
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
				<td align="left" class="title1" colspan="10">节日礼品方案信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="5%" class="info_title_01">方案名称</td>
				<td width="25%" class="info_title_01">方案内容</td>
				<td width="5%" class="info_title_01">总价</td>
				<td width="5%" class="info_title_01">添加人</td>
				<td width="5%" class="info_title_01">添加日期</td>
				<td width="5%" class="info_title_01">状态</td>
			</tr>
			<c:if test="${recordList ne null}">
				<c:forEach items="${recordList}" var="oneResult">
					<tr align="center"
						onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
						
						<td align="center" style="color: #666666;">${oneResult.SCHEME_NAME}&nbsp;</td>
						<td align="left" style="color: #666666;">
						 <table>
							<c:forEach items="${recordDetaiList}" var="result">
								<c:if test="${oneResult.SEQ_NO eq result.SCHEME_NO}">
									<tr>
										<td width="10%">&nbsp;&nbsp;礼品名称:&nbsp;<span style="color: #FF0000">${result.PRESENT_NAME}</span></td>
										<td width="10%">&nbsp;&nbsp;品牌:&nbsp;<span style="color: #FF0000">${result.BRAND}</span></td>
										<td width="10%">&nbsp;&nbsp;规格:&nbsp;<span style="color: #FF0000">${result.SPECIFIC}</span></td>
										<td width="10%">&nbsp;&nbsp;单价:&nbsp;<span style="color: #FF0000">${result.UNIT_PRICE}</span>&nbsp;元</td>
										<td width="10%">&nbsp;&nbsp;数量:&nbsp;<span style="color: #FF0000">${result.QUENTITY}</span>${result.UNIT}</td>
										<td width="10%">&nbsp;&nbsp;价格:&nbsp;<span style="color: #FF0000">${result.TOTAL_PRICE}</span>&nbsp;元</td>
									</tr>
								</c:if>
							</c:forEach>
						 </table>
						</td>
						<td align="center" style="color: #666666;">${oneResult.TOTALSUM}&nbsp;元</td>
						<td align="center" style="color: #666666;">${oneResult.CREATED_BY}&nbsp;</td>
						<td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
						<td align="center" style="color: #666666;">
							<c:if test="${oneResult.ACTIVITY eq 1}">
								<a href="#" onclick="activityStatus(${oneResult.SEQ_NO},2)"><font color="red">激活</font></a>
							</c:if>
							<c:if test="${oneResult.ACTIVITY eq 2}">
								<a href="#" onclick="activityStatus(${oneResult.SEQ_NO},1)"><font color="red">失效</font></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${recordCount < 5}">
				<c:forEach var="i" begin="1" end="${5-recordCount}" step="1">
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
			parameters="&operation=retrieveFestivalPresent&menu_code=${param.menu_code}"
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
