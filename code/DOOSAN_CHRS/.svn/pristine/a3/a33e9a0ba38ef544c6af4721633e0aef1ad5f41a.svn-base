<%@ page contentType="text/html; charset=UTF-8" import="com.ait.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

 function Save(){
  if($('PRODUCTIONNAME').value==""){
	alert("制品名称不能为空！");
	return;
   }
  if($('SPECIFICATIONNO').value==""){
  	alert("编制品号！");
	return;
   }
  if($('PRODUCTSNO').value!="" && $('PRODUCTSNO').value!=null){
	 document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=updateWoodProduction";
	 document.form1.submit();
  }else{
     document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=wpCommand&content=addWoodProduction";
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
		<input type="hidden" name="PRODUCTSNO" value="<%=StringUtil.checkNull(request.getParameter("PRODUCTSNO"))%>">
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
					木制品基本信息设置</td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		   <tr align="center" bgcolor="#F5F5F5">
		    <td class="info_title_01"  align="center">
		       制品名称
		    </td>
		      <td class="info_content_00">
		      <input name="PRODUCTIONNAME" type="text" size="10" value="<%=StringUtil.checkNull(request.getParameter("PRODUCTIONNAME"))%>">
		      </td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01" align="center">
					编制品号</td>
		      <td class="info_content_00">
		      <input name="SPECIFICATIONNO" type="text" size="10" value="<%=StringUtil.checkNull(request.getParameter("SPECIFICATIONNO"))%>">
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
					制品长</td>
		      <td class="info_content_00">
		      <input name="L" type="text" size="10" value="<%=NumberUtil.parseDouble(request.getParameter("L"))%>" onkeyup='this.value=this.value.replace(/\D/gi,"")'>(单位：cm)
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
				制品寛</td>
		      <td class="info_content_00">
		      <input name="W" type="text" size="10" value="<%=NumberUtil.parseDouble(request.getParameter("W"))%>" onkeyup='this.value=this.value.replace(/\D/gi,"")'>(单位：cm)
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
				制品高</td>
		      <td class="info_content_00">
		      <input name="H" type="text" size="10" value="<%=NumberUtil.parseDouble(request.getParameter("H"))%>" onkeyup='this.value=this.value.replace(/\D/gi,"")'>(单位：cm)
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01">
					总价格</td>
		       <td class="info_content_00">
		      <input name="SUMPRICE" type="text" size="10" value="<%=NumberUtil.parseDouble(request.getParameter("SUMPRICE"))%>">(单位：元)
		      </td>
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
		<td width="11">r</td>
	</tr>
</table>

</body>
</html>
