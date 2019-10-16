<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

 function Save(){
 if(document.form1.PRODUCTS_NAME.value==""||document.form1.PRODUCTS_NAME.value==null){
 alert("制品名称不能为空！");
 }else if(CheckNumber(document.form1.MEASUREMENT_UNIT_PRICE.value)){
 alert("计量单价只能为数字或者小数");
 document.form1.MEASUREMENT_UNIT_PRICE.focus();
 }else{
 
 document.form1.action="/gmControlServlet?menu_code=ga0502&operation=wpCommand&content=addSealproductionSet";
 document.form1.submit();
 }
 }

 
  function  CheckNumber(tempvalue){   
    var   patrn=/^[0-9].{0,1}[0-9]{0,3}$/;
    if  (patrn.test(tempvalue)){   
       return  false;   
      }   
       return true; 
   } 
 
</script>
</head>
<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<form name="form1" method="post" action="">
		<input type="hidden" name="seqId" value="${seqId}">
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
		<!-- display content -->
		<br>

			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">
					刻章基本信息设置</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01" align="center">
					名称</td>
		      <td class="info_content_00">
		      <input name="PRODUCTS_NAME" type="text" size="10" value="${PRODUCTS_NAME}"></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
					单价</td>
		      <td class="info_content_00">
		      	<input type="text" size="10" name="MEASUREMENT_UNIT_PRICE" value="${MEASUREMENT_UNIT_PRICE }"/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
				详细信息</td>
		      <td class="info_content_00">
		      	<textarea name="notes" style="width:200px;height: 30px; ">${notes}</textarea></td>
		    </tr>
		   <tr>
		      <td class="info_title_01">
					添加者</td>
		      <td class="info_content_00">
		      	<font color="red"><%=admin.getChineseName() %></font></td>
		    </tr>

		  </table>
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
