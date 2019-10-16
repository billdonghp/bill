<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="gmType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="shift" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>餐别与班次设置</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Update() {
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	
	document.form1.action="/gmControlServlet?operation=typenandshiftUpdate&menu_code=${param.menu_code}&gm_id="+document.form1.temp.value;
	document.form1.submit();
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
			
			<%@ include file="../inc/common_toolbar_Modify.jsp"%>
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
				<td align="left" class="title1" colspan="10">餐别与班次设置</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			

			<tr>
				<td width="10%" class="info_title_01"><!--职号--> 餐别</td>
				<td width="10%" class="info_title_01"><!--职号--> 班次</td>
				<td width="10%" class="info_title_01"><!--职号--> 可就餐时间段</td>
			</tr>
			
			<c:forEach items="${gmType}" var="oneResult" varStatus="i">
			<tr onClick="band('#E7F0EF','black',${oneResult.GM_ID})">
				<td align="center" style="color: #666666;">
					${oneResult.GM_TYPE}&nbsp;
				</td>
				<td align="center" style="color: #666666;">
					<c:forEach items="${oneResult.gmTypeIds}" var="shift" varStatus="i">
						${shift.SHIFT_NAME}&nbsp;
					</c:forEach>
					&nbsp;
				</td>
				<td align="center" style="color: #666666;">
					<c:forEach items="${oneResult.timeGmType}" var="time" varStatus="i">
						${time.START_TIME}~~${time.END_TIME}
					</c:forEach>
				</td>
			</tr>
			</c:forEach>
			
		</table>

		</form>
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