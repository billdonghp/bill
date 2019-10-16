<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Search() {
	
	document.form1.seqNo.value="";	
	document.form1.action="/gaControlServlet?operation=retrieveFoodApplyView&menu_code=${param.menu_code}";
	document.form1.submit();
}
function exportExcel()
{
    form1.action = "/gaControlServlet?operation=exportFoodApply&menu_code=${param.menu_code}";
    form1.submit();    
}
</SCRIPT>
</head>
<body>
<form name="form1" method="post"><input type="hidden"
	name="seqNo" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
			<ait:history/>
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
				<td class="title1"><!--查询条件--> <ait:message
					messageID="display.mutual.search_criteria" /></td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="8%" class="info_title_01">日期</td>
						<td width="8%" class="info_content_00">
							<input type="text" name="apply_date" size="15" value="${apply_date}" readonly="readonly" onclick="setday(this);">
						</td>
						<td width="8%" class="info_title_01">部门</td>
						<td width="8%" class="info_content_00"><ait:selDept
							name="DeptId" selected="${DeptId}" all="all" /></td>
						<td width="8%" class="info_content_01"><ait:image
							src="/images/btn/Search.gif" border="0" align="absmiddle"
							onclick="javascript:Search();" style="cursor:hand" /></td>
						<td width="8%" class="info_content_01" colspan="3"><ait:image
							src="/images/btn/Excel_Exp.gif" border="0" align="absmiddle"
							onclick="javascript:exportExcel();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">夜餐统计情况</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="3%" class="info_title_01">部门</td>
				<td width="3%" class="info_title_01">名称</td>
				<td width="3%" class="info_title_01">人数</td>
				<td width="3%" class="info_title_01">夜餐食品</td>
				<td width="3%" class="info_title_01">备注</td>
				<td width="3%" class="info_title_01">申请人</td>
				<td width="3%" class="info_title_01">申请日期</td>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td align="center" style="color: #666666;" height="30">${oneResult.DEPTNAME}</td>
					<td align="center" style="color: #666666;">${oneResult.FOOD_SCHEME_NAME}</td>
					<td align="center" style="color: #666666;">${oneResult.QUENTITY}&nbsp;</td>
					<td align="center" style="color: #666666;" width="20%">
						<table>
							<c:forEach items="${foodDetaiList}" var="result">
								<c:if test="${oneResult.FOOD_SCHEME_ID eq result.FOOD_SCHEME_ID}">
									<tr>
										<td width="10%">&nbsp;&nbsp;礼品名称:&nbsp;<span style="color: #FF0000">${result.FOOD_NAME}</span></td>
										<td width="10%">&nbsp;&nbsp;品牌:&nbsp;<span style="color: #FF0000">${result.BRAND}</span></td>
										<td width="10%">&nbsp;&nbsp;规格:&nbsp;<span style="color: #FF0000">${result.SPECIFIC}</span></td>
										<td width="10%">&nbsp;&nbsp;单价:&nbsp;<span style="color: #FF0000">${result.PRICE}</span>&nbsp;(元)</td>
										<td width="10%">&nbsp;&nbsp;数量:&nbsp;<span style="color: #FF0000">${result.QUENTITY * oneResult.QUENTITY}</span>(${result.UNIT})</td>
										<td width="10%">&nbsp;&nbsp;价格:&nbsp;<span style="color: #FF0000">${(result.QUENTITY * oneResult.QUENTITY)*result.PRICE}</span>&nbsp;(元)</td>
									</tr>
								</c:if>
							</c:forEach>
						 </table>
					</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.APPLY_BY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.APPLY_DATE}&nbsp;</td>
			</c:forEach>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrieveFestivalPresentConfrimView&menu_code=${param.menu_code}&personId=${personId}&DeptId=${DeptId}&empStatus=${empStatus}&flag=${flag}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" /> <!-- Page Navigation End -->
		<input type="hidden" name="pageSize" value="${pageSize}">
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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</body>
<ait:xjos></ait:xjos>
</html>
