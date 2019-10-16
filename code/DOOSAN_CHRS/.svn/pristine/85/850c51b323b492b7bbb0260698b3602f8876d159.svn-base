<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
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
function Update(){
	if($('cardNumber').value=="" || $('cardNumber').value== null){
	alert("卡号不能为空！");
	return;
	}
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=view&content=tempCardSave";
	
	document.form1.submit(); 

}

 function Back(){
  location.href="/gaControlServlet?menu_code=ga0413&operation=view&content=temporarycardapplicationview";
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
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">            
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">          	
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">        
            <ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle"
          	onclick="javascript:Update();" style="cursor:hand" title="保存" enTitle="update" />      
          <ait:image src="/images/btn/Back.gif"  border="0" align="absmiddle"
          	onclick="javascript:Back();" style="cursor:hand" title="后退" enTitle="return" />
          </td>  
        </tr> 
      </table>
      </td>
  </tr>
</table>

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
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">临时卡发放情况</td>
			</tr>
		</table>
		<BR>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">		

			<tr>
				<td width="10%" class="info_title_01">卡号</td>
				<td width="10%" class="info_title_01">是否返还</td>
				<td width="10%" class="info_title_01">发卡人</td>	
				<td width="10%" class="info_title_01">归还日期</td>				
			</tr>
			<c:forEach items="${tempCardProvidList}" var="test" varStatus="i">
			<tr>
			    <input type="hidden" name="applyNO"  value="${test.APPLY_NO}">	
			    
				      <td nowrap="nowrap" align="center" class="info_content_01">   
		            <input type="text" name="cardNumber" value="${test.CARD_NUMBER}">    
		           &nbsp;
		      </td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">   
		        <select name="returnYn">
		        <option value="">请选择</option>
				<option value="1" <c:if test="${test.RETURN_YN==1}">selected</c:if> >已归还</option>
				<option value="0" <c:if test="${test.RETURN_YN==0}">selected</c:if> >未归还</option>		
				</select>
		      </td>	
		      <c:if test="${test.CARD_PERSON==null}">
			  <td align="center" style="color: #666666;">
		            <input type="text" name="adminName" value="${name}" readonly>  
		        </td></c:if>
		        
		      <c:if test="${test.CARD_PERSON!=null}">
			  <td align="center" style="color: #666666;"><input type="text" name="adminName" value="${test.CARD_PERSON}" readonly> </td>
			  </c:if>
			  
		      <td nowrap="nowrap" align="center" class="info_content_01"> 
		            <input type="text" name="returnDate" class="content" style="width:70px "  value="${test.RETURN_DATE}" readonly onClick="setday(this);"> 
		              
		      </td>	
		      
		      
		      		
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
