<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="xs" class="java.lang.String" scope="request" />
<jsp:useBean id="fz" class="java.lang.String" scope="request" />
<jsp:useBean id="xse" class="java.lang.String" scope="request" />
<jsp:useBean id="fze" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>餐别与班次设置</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var temp;

function Save()
{
	if(document.form1.time_area.checked==true){
		document.form1.time_area.value=0;
		document.form1.action="/gmControlServlet?operation=typeandshiftSave&menu_code=${param.menu_code}&checkBox1="+document.form1.time_area.value;
		document.form1.submit(); 
	}else{
		document.form1.time_area.value=1;
		document.form1.action="/gmControlServlet?operation=typeandshiftSave&menu_code=${param.menu_code}&checkBox1="+document.form1.time_area.value;
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
	temp = i;
}

</SCRIPT>
<body>

<form name="form1" method="post" action="">
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
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">餐别与班次修改</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			<tr>
				<td width="10%" class="info_title_01"><!--职号--> 餐别</td>
				<td width="10%" class="info_title_01"><!--职号--> 班次</td>
				<td width="10%" class="info_title_01"><!--职号-->可就餐时间段</td>
			</tr>		
			<tr>
				<td align="center" style="color: #666666;">
					${gmType.GM_TYPE}&nbsp;
					<input type="hidden" name="gm_id" value="${gmType.GM_ID}" />
				</td>
				<td align="center" style="color: #666666;">
					<c:forEach items="${gmType.gmShifts}" var="oneResult" varStatus="i">
						${oneResult.SHIFT_NAME}&nbsp;
						<input type="checkbox" name="SHIFT_NO" value="${oneResult.SHIFT_NO}" ${oneResult.checkFalg} />
					</c:forEach>
				</td>
				<td align="center" style="color: #666666;">
					<select name="listHHS">
						<c:forEach items="${listHH}" var="oneResult" varStatus="i">
							<option value="${oneResult}"
								<c:if test="${oneResult==xs }">selected="selected"</c:if>>${oneResult}
							</option>
						</c:forEach>
					</select> 
					： 
					<select name="listMMS">
						<c:forEach items="${listMM}" var="oneResult" varStatus="i">
							<option value="${oneResult}"
								<c:if test="${oneResult==fz }">selected="selected"</c:if>>${oneResult}
							</option>
						</c:forEach>
					</select>
	          		至
	          		<select name="listHHE">
						<c:forEach items="${listHH}" var="oneResult" varStatus="i">
	
							<option value="${oneResult}"
								<c:if test="${oneResult==xse }">selected="selected"</c:if>>${oneResult}</option>
						</c:forEach>
					</select>
					 ： 
					<select name="listMME">
						<c:forEach items="${listMM}" var="oneResult" varStatus="i">
							<option value="${oneResult}"
								<c:if test="${oneResult==fze}">selected="selected"</c:if>>${oneResult}</option>
						</c:forEach>
					</select><br>
					<input type="checkbox" value="${if_selected}" name="time_area" ${if_selected==0 ? "checked":""}/>&nbsp;&nbsp;在此时间段内加班的员工，可以进行本次就餐。
				</td>
			</tr>
			
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