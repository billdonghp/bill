<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ibm.icu.text.SimpleDateFormat,java.util.Date" %>
<jsp:useBean id="seq" class="java.lang.String" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function Save() {
	document.ApplyForm.action="/gmControlServlet?operation=ga_carManager&content=Save_CarManager&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
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
	document.ApplyForm.temp.value=i;
} 

function uploadImp(photosid){
window.open("/ga/ga_visiter_upload.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
</script> 
<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="temp" value=""/>
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

		<!-- display content -->
		<br>		
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%> 
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					车辆基本信息添加
				</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		   <tr align="center" bgcolor="#F5F5F5">
		      <td width="15%" nowrap="nowrap" class="info_title_01" class="info_title_01">
				车辆名称</td>
			  <td class="info_content_00"><input type="text" value="" name="CAR_NAME"> </td>
			  </tr>
			  <tr>
		      <td nowrap="nowrap" class="info_title_01">
				型号</td>
				<td class="info_content_00"><input type="text" value="" name="MODEL_NUMBER"> </td>
			  </tr>
			  <tr>
			  <td nowrap="nowrap" class="info_title_01">
				出厂日期</td>
				<td class="info_content_00"><input name="OUT_DATE" type="text" value="" style="width:80px " onClick="setday(this);"> </td>
			  </tr>
			  <tr>
			  <td nowrap="nowrap" class="info_title_01">
				车牌号码</td>	
				<td class="info_content_00"><input name="CAR_NUM" type="text" value=""> </td>
			  </tr>
			  <tr>		  
		      <td nowrap="nowrap" class="info_title_01">
				座位数</td>	
				<td class="info_content_00"><input name="SIT_BUM" type="text" value=""> </td>
			  </tr>
			  <tr>		      
			  <td nowrap="nowrap" class="info_title_01">
				里程数</td>
				<td class="info_content_00"><input name="KILOMETER" type="text" value=""> </td>
			  </tr>
			  <tr>
			  <td nowrap="nowrap" class="info_title_01">
				车辆使用状态</td>
				<td class="info_content_00">
				<select name="CAR_USEFLAG">
					<option value="0">未使用</option>
					<option value="1">已派出</option>
				</select>
				
				</td>
			  </tr>
			  <tr>
			  <td nowrap="nowrap" class="info_title_01">
				车辆状态</td>
				<td class="info_content_00">
				<ait:codeClass name="CAR_FLAG" codeClass="voitureState"/>
				</td>
			  </tr>
			  <tr>
			  <td nowrap="nowrap" class="info_title_01">
				上传图车辆片</td>
				<td class="info_content_00">
				<a href="#" onclick="uploadImp('${applyId}')" style="color:red" title="上传图片">上传扫描文件</a> </td>
			  </tr>
			  <tr>
		 </table>
		 </form>
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
