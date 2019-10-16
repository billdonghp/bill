<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="gmType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="shift" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<html>
<head>
<script src="../js/prototype.js"></script>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<style>
body{
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #808080;
	scrollbar-highlight-color: #808080;
    scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #F5F5F5;
	scrollbar-arrow-color: #808080;
}
</style>
<script language="javascript">

function Save()
{
		var flag = 0 ;
		for(var i = 0 ; i < document.form1.gm_id.length ; i++ )
		{
			if(document.form1.gm_id[i].checked)
			{
				flag = 1 ;
				break ;
			}
		}
	    if(flag == 1){
		        alert(":选了")  
		      	document.form1.action="/gmControlServlet?operation=typeandshift_SAVE&menu_code=${param.menu_code}";
			//	document.form1.fireSubmit();  
		  }else{  
		      alert(":没选")   
		      return; 
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
		<td valign="TOP" align="CENTER"><!-- display basic info --> 
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">班次与餐别设置</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			<tr>
				<td width="10%" class="info_title_01"><!--职号--> 餐别</td>
				<td width="10%" class="info_title_01"><!--职号--> 班次</td>
				<td width="10%" class="info_title_01"><!--职号-->可就餐时间段（在此区间内打出门卡的员工可以就餐）</td>
			</tr>
			<tr>
				<td align="center" style="color: #666666;">
		          	<c:forEach items="${gmType}" var="oneResult" varStatus="i">
		          		<input type="radio" name="gm_id" id="gm_id" value="${oneResult.GM_ID}">
								${oneResult.GM_TYPE}&nbsp;
	          		</c:forEach>
				</td>
				<td align="center" style="color: #666666;">
		          	<c:forEach items="${shift}" var="oneResult" varStatus="i">
		          		<input type="checkbox" name="SHIFT_NO"  value="${oneResult.SHIFT_NO}">
								${oneResult.SHIFT_NAME}&nbsp;
	          		</c:forEach>
          		</td>
          		<td align="center" style="color: #666666;">
					<select name="listHHS">
	          			<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
	          		<option value="${lhh}">${lhh}</option>
	          			</c:forEach>
	          		</select>
	          		<select name="listMMS">
	          		<c:forEach items="${listMM}" var="lmm" varStatus="i">
	          			<option value="${lmm}">${lmm}</option>
	          		</c:forEach>
	          		</select>
	          		至
	          		<select name="listHHE">
	          			<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
	          		<option value="${lhh}">${lhh}</option>
	          			</c:forEach>
	          		</select>
	          		<select name="listMME">
	          		<c:forEach items="${listMM}" var="lmm" varStatus="i">
	          			<option value="${lmm}">${lmm}</option>
	          		</c:forEach>
	          		</select>
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
