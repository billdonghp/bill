<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){

	$('SEQ_NO').value = "";
	document.ApplyForm.action="/gaControlServlet?operation=retrieveClothingList&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function Add() {
	document.ApplyForm.action="/ga/ga_add_clothing.jsp?menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gaControlServlet?operation=retrieveDataForUpdateClothing&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gaControlServlet?operation=deleteClothing&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
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
	document.ApplyForm.SEQ_NO.value=i;
} 

</script>

<form name="ApplyForm" action="" method="post">
<input type="hidden" name="SEQ_NO" value="" />
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
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01">名称</td>
						<td width="75%" class="info_content_00">
							<ait:codeClass name="clothingId" codeClass="WashName" selected="${clothingId}" all="all"/>
						</td>
						<td width="15%" class="info_content_00"><ait:image
							src="/images/btn/Search.gif" align="absmiddle"
							onclick="javascript:search();" style="cursor:hand" />
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="25%" class="info_title_01" nowrap>衣物名称</td>
				<td width="15%" class="info_title_01" nowrap>单价</td>
				<td width="15%" class="info_title_01" nowrap>单位</td>
				<td width="20%" class="info_title_01" nowrap>创建者</td>
				<td width="20%" class="info_title_01" nowrap>创建时间</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center"
					onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.WASH_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.UNIT_PRICE}&nbsp;元</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.UNIT_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.CREATED_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.CREATE_DATE}</td>
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
			parameters="&operation=retrievePresentList&menu_code=${param.menu_code}&clothingId=${clothingId}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
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
</form>
</body>
</html>
