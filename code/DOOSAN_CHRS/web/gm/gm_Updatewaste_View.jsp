<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="allUpdateWasteInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="wasteAllType" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="wasteId" class="java.lang.String" scope="request"/>
<jsp:useBean id="wasteType" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="allCompanyCustomers" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="revenue_approach" class="java.util.ArrayList" scope="request" />
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
	if(document.form1.wasteType.value==0){
		alert("请选择类别");
		return;
	}
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	var patrn1=/^([1-9][0-9]*|0)$/;
	
    if(!patrn1.test(document.form1.wasteNumber.value)){   
      alert("废品数量必须输入整数！");
      return  false;   
    }
    
    if(document.form1.total.value == "" || document.form1.total.value == null){   
      alert("总价不能为空！");   
      return ;   
    }
    
        if(document.form1.Company_Customers.value == "" && document.form1.COMPANY_OTHER.value == ""){
   		alert("请选择客户公司！");   
    	return ;   
     		//Company_other
    }
    if(document.form1.Company_Customers.value != "" && document.form1.COMPANY_OTHER.value != ""){
   		alert("客户公司只能录入一个！");   
     	return ;   
      		//Company_other
    }
    
    document.form1.wasteUnitPrice.value = document.form1.wasteUnitPrice1.value;
	document.form1.action="/gmControlServlet?operation=waste&content=updateWaste&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}

function Calculation(){
	if(document.form1.wasteType.value==0){
		alert("请选择废品类别！");
		return;
	}
	
	if(document.form1.wasteNumber.value==""){
		alert("请填写废品数量！");
		return;
	}
	
	var patrn1=/^([1-9][0-9]*|0)$/;
    if(!patrn1.test(document.form1.wasteNumber.value)){   
      alert("废品数量必须输入整数！");
      return  false;   
    }
    
	document.form1.total.value = (parseFloat(document.form1.wasteUnitPrice1.value) * parseFloat(document.form1.wasteNumber.value)).toFixed(2);
	document.form1.total1.value = (parseFloat(document.form1.wasteUnitPrice1.value) * parseFloat(document.form1.wasteNumber.value)).toFixed(2);
}


function changeWasteType(){
	document.form1.wasteTypeId.value = document.form1.wasteType.value;
}

function changeWasteaTypeShowUnitPrice(TypeId){
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=changeWasteaTypeShowUnitPrice&TypeId="+document.form1.wasteType.value;

		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){

	var unitp = $('unitp');
	unitp.innerHTML=XmlHttpRequest.responseText;
	document.form1.wasteUnitPrice1.value=document.form1.UP.value;
	document.form1.wasteUnitPrice.value=document.form1.UP.value;
	document.form1.Units1.value = document.form1.UNITS.value;
	document.form1.Units.value = document.form1.UNITS.value;
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
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action="">
		<input type="hidden" name="wasteTypeId" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">废品修改</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<c:forEach items="${allUpdateWasteInformation}" var="all" varStatus="i">
			<input type="hidden" name="wasteId" value="${wasteId}"/>
				<tr>
	 				<td width="10%" class="info_title_01"> 废品类别 </td>
					<td align="left" class="info_content_00">
	 					<select name="wasteType" onchange="changeWasteType();changeWasteaTypeShowUnitPrice(this.value);">
							<option value="0">
									请选择类别
							</option>
							<c:forEach items="${wasteType}" var="view" varStatus="i">
								<c:choose>
									<c:when test="${view.CODE_ID == all.wasteTypeId}">
										<option value="${all.wasteTypeId}" selected>${all.wasteType}</option>
									</c:when>
									<c:otherwise>
										<option value="${view.CODE_ID}">
											${view.CODE_NAME}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
	 				</td>
	 				<span id="unitp"></span>
	 			</tr>
				<tr>
	 				<td width="10%" class="info_title_01">单位</td>
					<td align="left" class="info_content_00">
						<input type="text" name="Units1" disabled="disabled" value="${all.wasteUnits}" size="10"/>
						<input type="hidden" name="Units" value="${all.wasteUnits}"/>
					</td>
				</tr>
				<tr>
	 				<td width="10%" class="info_title_01">单价</td>
					<td align="left" class="info_content_00">
						<input type="text" name="wasteUnitPrice1" disabled="disabled" value="${all.wasteUNITPRICE}" size="10"/>&nbsp;元
						<input type="hidden" name="wasteUnitPrice" value="${all.wasteUNITPRICE}"/>
					</td>
				</tr>
				<tr>
					<td width="10%" class="info_title_01"> 数量 </td>
	 				<td align="left" class="info_content_00">
						<input type="text" name="wasteNumber" value="${all.wasteNumber}" size="10" onchange="Calculation();"/>
	 				</td>
				</tr>
	 			<tr>
				<td width="10%" class="info_title_01"> 金额 </td>
					<td align="left" class="info_content_00">
	 					<input name="total1" type="text" value="${all.wasteTotal}" size="10" disabled="disabled" money/>&nbsp;元
	 					<input name="total" type="hidden" value="${all.wasteTotal}"/>
	 				</td>
	 			</tr>
	 			<tr>
					<td width="10%" class="info_title_01"> 客户公司 </td>
					<td align="left" class="info_content_00">
	 					<select name="Company_Customers" onchange="changeWasteType();changeWasteaTypeShowUnitPrice(this.value);">
							<option value="">
									请选择
							</option>
							<c:forEach items="${allCompanyCustomers}" var="view" varStatus="i">
								<c:choose>
									<c:when test="${view.CODE_ID == all.company_customers_id}">
										<option value="${all.company_customers_id}" selected>${all.company_customers}</option>
									</c:when>
									<c:otherwise>
										<option value="${view.CODE_ID}">
											${view.CODE_NAME}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
	 				</td>
	 			</tr>
	 			<tr>
					<td width="10%" class="info_title_01"> 其他客户公司 </td>
					<td align="left" class="info_content_00">
	 					<input type="text" name="COMPANY_OTHER" value="${all.COMPANY_OTHER}" style="width: 250px" />
	 				</td>
	 			</tr>
	 			<tr>
					<td width="10%" class="info_title_01"> 支付方式 </td>
					<td align="left" class="info_content_00">
	 					<select name="revenue_approach">
							<option value="">
									请选择
							</option>
							<c:forEach items="${revenue_approach}" var="view" varStatus="i">
								<c:choose>
									<c:when test="${view.CODE_ID == all.REVENUE_APPROACH_ID}">
										<option value="${all.REVENUE_APPROACH_ID}" selected>${all.REVENUE_APPROACH_NAME}</option>
									</c:when>
									<c:otherwise>
										<option value="${view.CODE_ID}">
											${view.CODE_NAME}
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
	 				</td>
	 			</tr>
	 			<tr>
					<td width="10%" class="info_title_01"> 搬出人员 </td>
	 				<td align="left" class="info_content_00">
						<input type="text" name="OUT_STAFF" value="${all.OUT_STAFF}" size="10"/>
	 				</td>
				</tr>
				<tr>
				<td width="10%" class="info_title_01"> 搬出理由 </td>
				<td align="left" class="info_content_00">
					<ait:codeClass codeClass="CiteReasons" name="citeReasons" selected="${all.CITEREASONS}"/>
 				</td>
 			    </tr>
				<tr>
					<td width="10%" class="info_title_01"> 车辆号 </td>
	 				<td align="left" class="info_content_00">
						<input type="text" name="CAR_NO" value="${all.CAR_NO}" style="width: 250px"/>
	 				</td>
				</tr>
	 			<tr>
				<td width="10%" class="info_title_01"> 备注 </td>
					<td align="left" class="info_content_00">
	 					<textarea name="remarks" style="height: 80px;width: 300px;">${all.wasteRemark}</textarea>
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
<ait:xjos />
</html>
