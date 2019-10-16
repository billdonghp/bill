<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{	
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(document.form1.assetType.value == ""){
		alert("请选择资产类型");
		return false;
	}
	if(!patrn.test(document.form1.unitPrice.value)){
      alert("单价必须输入数字或者小数！");
      return  false;
    }
    if(!patrn.test(document.form1.quantity.value)){
      alert("数量必须输入数字！");
      return  false;
    }
    if(document.form1.telephone.value != ""){
    	if(!patrn.test(document.form1.telephone.value))
    	{
    		alert("厂家联系电话必须为数字!");
    		return false;
    	}
    }
    if(document.form1.useDeptId.value ==""){
    	alert("请选择使用部门");
    	return false;
    }
    document.form1.action="/gaControlServlet?operation=UpdateAssetCmd&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}

var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchC(condition,id);
					},500);  
}

function SearchC(condition,id){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeSysAdmin&condition=" + encodeURIComponent(condition);

		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('empId').value=cell.childNodes[0].firstChild.nodeValue;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
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
		<input type="hidden" name="seqNo" value="${result.SEQ_NO}"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">修改资产基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="20%" class="info_title_01">资产类型</td>
				<td class="info_content_00"><ait:codeClass name="assetType"
					codeClass="AssetType" selected="${result.ASSET_TYPE}" all="all" /></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">资产编号</td>
				<td class="info_content_00"><input name="assetNo" type="text"
					size="15" value="${result.ASSET_NO}" required/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">名称</td>
				<td class="info_content_00"><input type="text" name="assetName"
					size="15" value="${result.ASSET_NAME}" required/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">起案编号</td>
				<td aclass="info_content_00">&nbsp;&nbsp;<input type="text" name="fileNo"
					size="15" value="${result.FILE_NO}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">品牌</td>
				<td class="info_content_00"><input type="text" name="brand"
					size="15" value="${result.BRAND}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">规格</td>
				<td class="info_content_00"><input type="text" name="specific"
					size="15" value="${result.SPECIFIC}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">产品号</td>
				<td class="info_content_00"><input type="text" name="productNo"
					size="15" value="${result.PRODUCT_NO}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">数量</td>
				<td class="info_content_00"><input type="text" name="quantity"
					size="15" value="${result.QUANTITY}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">单价</td>
				<td class="info_content_00"><input type="text" name="unitPrice"
					size="15" value="${result.UNIT_PRICE}"/>&nbsp;元</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">购买厂家</td>
				<td class="info_content_00"><input type="text" name="vendor"
					size="15" value="${result.VENDOR}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">厂家联系电话</td>
				<td class="info_content_00"><input type="text" name="telephone"
					size="15" value="${result.TELEPHONE}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">购买日期</td>
				<td class="info_content_00"><input type="text" name="buyDate"
					size="15" value="${result.BUY_DATE}" readonly="readonly" onclick="setday(this)" /></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">保修期</td>
				<td class="info_content_00"><input type="text" name="warranty"
					size="15" value="${result.WARRANTY}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">固定资产编号</td>
				<td class="info_content_00"><input type="text"
					name="capitalAssetNo" size="15" value="${result.CAPITAL_ASSET_NO}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">位置</td>
				<td class="info_content_00"><input type="text" name="assetPosition"
					size="15" value="${result.ASSET_POSITION}"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">使用部门</td>
				<td class="info_content_00"><ait:selDept name="useDeptId"
					selected="${result.USE_DEPT_ID}" all="all" /></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">管理担当</td>
				<td class="info_content_00">
				<input type="text" id="empId" name="empId" size="15" value="${result.ASSET_KEEPER_NAME}" onkeyup="SearchContent(this.value,this.id)" required/>
				<input type="hidden" id="personId" name="personId" size="8" value="${result.ASSET_KEEPER_ID}" /></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">预点检日期</td>
				<td class="info_content_00"><input type="text" name="examineDate"
					size="15" value="${result.EXAMINE_DATE}" readonly="readonly" onclick="setday(this)" required/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">备注</td>
				<td class="info_content_00"><textarea name="remark" rows="3"
					cols="25" >${result.REMARK}</textarea></td>
			</tr>
		</table>
		<iframe id='iemp'
			style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
		</iframe>
		<div id="emp_list"
		style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
		</div>
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
