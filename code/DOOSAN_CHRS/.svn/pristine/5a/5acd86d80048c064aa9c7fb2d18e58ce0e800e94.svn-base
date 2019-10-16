<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="PositionDiseaseDiseaseType" class="java.util.ArrayList" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>岗位与职业危害</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function Update() {
	if(document.form1.temp.value == 0){
		alert("请选择一行");
		return;
	}
	document.form1.action="/safeControlServlet?operation=jobHealth&content=PositionDiseaseDiseaseTypeUpdateView&menu_code=${param.menu_code}&relations_id="+document.form1.temp.value;
	document.form1.submit();
}


function Add() {
	document.form1.action="/safeControlServlet?operation=jobHealth&content=PositionDiseaseDiseaseTypeAddView&menu_code=${param.menu_code}&relations_id="+document.form1.temp.value;
	document.form1.submit();
}

function Delete() {
	if(document.form1.temp.value == 0){
		alert("请选择一行");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/safeControlServlet?operation=jobHealth&content=deletePositionDiseaseDiseaseType&menu_code=${param.menu_code}&relations_id="+document.form1.temp.value;
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
	document.form1.temp.value=i;
} 

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="temp" value="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">岗位与职业危害</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="10%" class="info_title_01"> 岗位</td>
				<td width="10%" class="info_title_01"> 职业危害</td>
				<td width="10%" class="info_title_01"> 职业病类型</td>
			</tr>
			<c:forEach items="${PositionDiseaseDiseaseType}" var="all" varStatus="i">
			<tr onClick="band('#E7F0EF','black',${all.RELATIONS_ID})" style="height: 30px">
				<td align="center" style="color: #666666;">
					${all.POSITIONNAME}&nbsp;
				</td>
				<td align="center" style="color: #666666;">
					${all.DISEASENAME}&nbsp;
				</td>
				<td align="center" style="color: #666666;">
					${all.DISEASETYPENAME}&nbsp;
				</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(PositionDiseaseDiseaseType) < 10}">
				<c:forEach var="i" begin="1" end="${10-fn:length(PositionDiseaseDiseaseType)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
						<td align="center" style="color: #666666;">&nbsp;</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		</form>
		<br />
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