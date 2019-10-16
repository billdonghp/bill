<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<SCRIPT type="text/javascript">

function Save() {
	
	var rowNum = Number($("maxRowNum").value);
	for(var i=0; i<=rowNum; i++){
		if($('presentName' + i) != null) {
			if($('presentName'+i).value == "")
			{
				alert("请选择礼品名称!");
				return false;
			}
			var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
			if(!patrn.test($('quentity'+i).value)){
				alert("数量必须为数字且不能为空 !");
				return false;
			}
		}
	}
	document.form1.action="/gaControlServlet?operation=updateFestivalScheme&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}
</SCRIPT>
<body>
<form name="form1" method="post" action="">
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
		<td valign="TOP" align="CENTER"><br>
		<br>
		<table id="Tables" width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">礼品方案信息</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">方案名称</td>
				<td nowrap="nowrap" class="info_content_01">
					<input type="hidden" name="seqNo" value="${result.SEQ_NO}">
					${result.SCHEME_NAME}
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_01">
					<textarea name="schemeRemark" rows="2" cols="25">${result.SCHEME_REMARK}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">礼品名称</td>
				<td width="15%" class="info_title_01">品牌</td>
				<td width="15%" class="info_title_01">规格</td>
				<td width="15%" class="info_title_01">单位</td>
				<td width="15%" class="info_title_01">单价</td>
				<td width="15%" class="info_title_01">数量</td>
				<td width="15%" class="info_title_01">总价</td>
				
			</tr>
			<%! int maxRowNum = 0; int j=0;%>
			<%
				request.setAttribute("j",j);
			%>
			<c:forEach items="${recordDetaiList}" var="oneResult" varStatus="i">
				<c:if test="${oneResult.SCHEME_NO eq result.SEQ_NO}">
				<tr>
					<td nowrap="nowrap" class="info_content_01">${oneResult.PRESENT_NAME}</td>
					<td nowrap="nowrap" class="info_content_01"><span id="span1${j}">${oneResult.BRAND}&nbsp;</span></td>
					<td nowrap="nowrap" class="info_content_01"><span id="span2${j}">${oneResult.SPECIFIC}&nbsp;</span></td>
					<td nowrap="nowrap" class="info_content_01"><span id="span3${j}">${oneResult.UNIT}&nbsp;</span></td>
					<td nowrap="nowrap" class="info_content_01"><span id="span4${j}">${oneResult.UNIT_PRICE}&nbsp;</span></td>
					<td nowrap="nowrap" class="info_content_01">${oneResult.QUENTITY}</td>
					<td nowrap="nowrap" class="info_content_01"><span id="span6${j}">${oneResult.UNIT_PRICE * oneResult.QUENTITY}&nbsp;元</span></td>
				</tr>
				<%
					maxRowNum = maxRowNum +1;
					j++;
					request.setAttribute("j",j);
				%>
				</c:if>
			</c:forEach>
			<input type="hidden" name="maxRowNum" value="<%=maxRowNum%>"> 
			<input type="hidden" name="rowCount" value="<%=maxRowNum%>"> 
		</table>

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
		<br>
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
<ait:xjos/>
</html>
