<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gm_id" class="java.lang.String" scope="request" />
<jsp:useBean id="gmtype" class="java.lang.String" scope="request" />
<jsp:useBean id="xs" class="java.lang.String" scope="request" />
<jsp:useBean id="fz" class="java.lang.String" scope="request" />
<jsp:useBean id="xse" class="java.lang.String" scope="request" />
<jsp:useBean id="fze" class="java.lang.String" scope="request" />
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean" />
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
function Add(){
	if($('serialNumber').value=="" || $('serialNumber').value== null){
	alert("卡编号不能为空！");
	return;
	
	}
	if($('cardNumber').value=="" || $('cardNumber').value== null){
	alert("卡号不能为空！");
	return;
	}
	
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=cardProvideAdd";
	
	document.form1.submit(); 
}
function Update(){
 if(document.form1.iindex.value==""||document.form1.iindex.value==null){
    alert("请选择修改项目！");
    return;
   }else{	
	
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=cardProvideUpdate";
	
	document.form1.submit(); 
  }
}

function Delete(){
   if(document.form1.iindex.value==""||document.form1.iindex.value==null){
    alert("请选择删除项目！");
    return;
   }
   if(confirm("确定删除相关信息吗？")){	
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=cardProvideDelete";
	
	document.form1.submit(); 
   }
}
 function Back(){
  location.href="/gaControlServlet?menu_code=ga0407&operation=view&content=cardapplicationview";
 }
function band(backColor,textColor,pkno,i)
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
		document.form1.pkNo.value=pkno;
		document.form1.iindex.value=i;
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
		<%@ include file="inc/common_toolbar_allb.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<input type="hidden" name="applyNo" value="${applyNo}">
		<input type="hidden" name="pkNo" value="">
		<input type="hidden" name="iindex" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">发放就餐卡</td>
			</tr>
		</table>
		<BR>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">		

			<tr>

				<td width="10%" class="info_title_01"><!--卡编号--> 卡编号</td>
				<td class="info_content_00">
				<input name="serialNumber" type="text" value="" width="80px"/></td>
			
				<td width="10%" class="info_title_01"><!--卡号--> 卡号</td>
				<td align="left" style="color: #666666;">
					<input name="cardNumber" type="text" value="" width="80px"/>
				</td>
			
				<td width="10%" class="info_title_01"><!--卡类型--> 卡类型</td>
				<td align="left" style="color: #666666;">
					<select name="cardType">
						<c:forEach items="${cardTypeList}" var="oneResult" varStatus="i">	
							<option value="${oneResult.TYPE_NO}">${oneResult.TYPE_NAME}</option>
						</c:forEach>
					</select>					
				</td>			
			</tr>
		</table>
		<BR>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">就餐卡发放情况</td>
			</tr>
		</table>
		<BR>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">		

			<tr>

				<td width="10%" class="info_title_01">卡编号</td>	
				<td width="10%" class="info_title_01">卡号</td>
				<td width="10%" class="info_title_01">卡类型</td>
				<td width="10%" class="info_title_01">发放状态</td>
				<td width="10%" class="info_title_01">发卡人</td>	
				<td width="10%" class="info_title_01">发卡时间</td>				
			</tr>
			<c:forEach items="${cardProvidList}" var="test" varStatus="i">
			<tr onClick="band('#E7F0EF','black','${test.PKNO}','${i.index}')">
			    <input type="hidden" name="pkNo_${i.index}"  value="${test.PKNO}">			   
				<td align="center" style="color: #666666;"> <input type="text" name="serialNumber_${i.index}"  value="${test.SERIAL_NUMBER}"></td>	
				<td align="center" style="color: #666666;"> <input type="text" name="cardNumber_${i.index}"  value="${test.CARD_NUMBER}"></td>
				<td align="center" style="color: #666666;">
					<select name="cardType_${i.index}">
						<c:forEach items="${cardTypeList}" var="oneResult" varStatus="j">	
							<option value="${oneResult.TYPE_NO}" <c:if test="${oneResult.TYPE_NO==test.CARD_TYPE}">selected</c:if>>${oneResult.TYPE_NAME}</option>
						</c:forEach>
					</select>					
				</td>	
								
				<td align="center" style="color: #666666;">
				<select name="flag_${i.index}">
				<option value="0" <c:if test="${test.FLAG==0}">selected</c:if>>未发放</option>
				<option value="1" <c:if test="${test.FLAG==1}">selected</c:if>>已发放</option>
				<option value="2" <c:if test="${test.FLAG==2}">selected</c:if>>未收回</option>
				<option value="3" <c:if test="${test.FLAG==3}">selected</c:if>>已收回</option>				
				</select>
				</td>
			
				<td align="center" style="color: #666666;">${test.CHINESENAME}</td>	
				<td align="center" style="color: #666666;">${test.PROVIDE_DATE}</td>				
			</tr>
			</c:forEach>
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
</html>
