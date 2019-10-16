<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>资产管理&gt;资产信息</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/ga/ga_asset_add.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.seqNo.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	if(document.form1.assetName.value=="all")
		document.form1.assetName.value="";
	if(document.form1.assetType.value=="all")
		document.form1.assetType.value="";
	if(document.form1.useDeptId.value=="all")
		document.form1.useDeptId.value="";	
	document.form1.action="/gaControlServlet?operation=RetrieveDataForUpdateAssetCmd&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.seqNo.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=DeleteAssetCmd&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {
	if(document.form1.assetName.value=="all")
		document.form1.assetName.value="";
	document.form1.seqNo.value="";	
	document.form1.action="/gaControlServlet?operation=AssetManage&menu_code=${param.menu_code}";
	document.form1.submit();
}

function CheckAll() {
	if (document.form1.assetInfo) {
		if (document.form1.assetInfo[0]) {
			for (i=0;i<document.form1.assetInfo.length;i++)
				document.form1.assetInfo[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.assetInfo.checked = document.form1.checkAll.checked;
		}
	}
}

function exportExcel()
{
	if(document.form1.assetName.value=="all")
		document.form1.assetName.value="";
	if(document.form1.assetType.value=="all")
		document.form1.assetType.value="";
	if(document.form1.useDeptId.value=="all")
		document.form1.useDeptId.value="";	
    form1.action = "/gaControlServlet?operation=ExportAssetReportCmd&menu_code=${param.menu_code}";
    form1.submit();    
}

function importExcel()
{
	url="/gaControlServlet?operation=ImportAssetReportCmd$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function downloadImportTemplate()
{
	form1.action = "/gaControlServlet?operation=DownloadAssetTemplateCmd&menu_code=${param.menu_code}";
    form1.submit();
}
function SearchEmp(empid){

	if(empid!=''){
		var inputBox = document.getElementById("key");
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		showsearch.style.top = iBtop+iBheight+6;
		showsearch.style.left = iBleft;
		showsearch.style.visibility='visible';

		document.emp_list.location.href = "/inc/ArSearchEmployee.jsp?content="+encodeURIComponent(empid)+"&id=key";
	} else {
		document.all.showsearch.style.visibility='hidden';
	}
}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
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
    
	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		layerClose();
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
	document.form1.seqNo.value=i;
} 
</SCRIPT>
<body>
<form name="form1" method="post">
<input type="hidden" name="seqNo" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../hrm/inc/hrtoolbar.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <br>
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!--查询条件--> <ait:message
					messageID="display.mutual.search_criteria" /></td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01" nowrap="nowrap"><!--开始日期-->
						<ait:message messageID="display.ga.asset.start_time" module="ga" /></td>
						<td width="10%" class="info_content_00"><input id="startDate"
							name="startDate" type="text" size="15" value="${startDate}" readonly="readonly" onclick="setday(this)"/></td>
						<td width="10%" class="info_title_01"><!--结束日期--> <ait:message
							messageID="display.ga.asset.end_time" module="ga" /></td>
						<td width="10%" class="info_content_00"><input id="endDate"
							name="endDate" type="text" size="15" value="${endDate }" readonly="readonly" onclick="setday(this)"></td>
						<td width="10%" class="info_title_01"><!--资产类型--> <ait:message
							messageID="display.ga.asset.asset_type" module="ga" /></td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="assetType" codeClass="AssetType" selected="${assetType}" all="all" /></td>
						<td width="10%" class="info_title_01"><!--点检状态--> <ait:message
							messageID="display.ga.asset.check_status" module="ga" /></td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="status" codeClass="CheckStatus" selected="${status}" all="all" /></td>
						<td width="10%" class="info_content_01"><ait:image
							src="/images/btn/Search.gif" border="0" align="absmiddle"
							onclick="javascript:Search();" style="cursor:hand" /></td>
					</tr>
					<tr>
						<td width="10%" class="info_title_01"><!--资产编号--> <ait:message
							messageID="display.ga.asset.asset_no" module="ga" /></td>
						<td width="10%" class="info_content_00">
							<input id="assetNo" name="assetNo" type="text" value="${assetNo}" size="15"/></td>
						<td width="10%" class="info_title_01"><!--名称--> <ait:message
							messageID="display.ga.asset.asset_name" module="ga"/></td>
						<td width="10%" class="info_content_00">
							<ait:select dataListName="recordName" name="assetName" selected="${assetName}" all="all"/></td>
						<td width="10%" class="info_title_01"><!--管理部门--> <ait:message
							messageID="display.ga.asset.manage_dept" module="ga" /></td>
						<td width="10%" class="info_content_00"><ait:selDept
							name="useDeptId" selected="${useDeptId}" all="all" /></td>
						<td width="45%" class="info_content_01" colspan="3"><ait:image
							src="/images/btn/Excel_Exp.gif" border="0" align="absmiddle"
							onclick="javascript:exportExcel();" style="cursor:hand" /> | <ait:image
							src="/images/btn/Template.gif" border="0" align="absmiddle"
							onclick="javascript:downloadImportTemplate();"
							style="cursor:hand" /> | <ait:image
							src="/images/btn/Excel_Imp.gif" border="0" align="absmiddle"
							onclick="javascript:importExcel();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10"><!--资产记录--> <ait:message
					messageID="display.ga.view.asset_manage" module="ga" /></td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="8%" class="info_title_01"><!--资产类型--> <ait:message
					messageID="display.ga.asset.asset_type" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--资产编号--> <ait:message
					messageID="display.ga.asset.asset_no" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--名称--> <ait:message
					messageID="display.ga.asset.asset_name" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--起案编号--> <ait:message
					messageID="display.ga.asset.file_no" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--品牌--> <ait:message
					messageID="display.ga.asset.brand" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--规格--> <ait:message
					messageID="display.ga.asset.specific" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--产品号--> <ait:message
					messageID="display.ga.asset.product_no" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--数量--> <ait:message
					messageID="display.ga.asset.quantity" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--单价--> <ait:message
					messageID="display.ga.asset.unit_price" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--购买日期--> <ait:message
					messageID="diaplay.ga.asset.buy_date" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--保修期--> <ait:message
					messageID="display.ga.asset.warranty" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--购买厂家--> <ait:message
					messageID="display.ga.asset.vendor" module="ga" /></td>
				<td width="8%" class="info_title_01">厂家联系电话</td>
				<td width="8%" class="info_title_01"><!--固定资产编号--> <ait:message
					messageID="display.ga.asset.capital_asset_no" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--使用位置--> <ait:message
					messageID="display.ga.asset.position" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--使用部门--> <ait:message
					messageID="display.ga.asset.use_dept" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--管理担当--> <ait:message
					messageID="display.ga.asset.keeper" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--点检状态--> <ait:message
					messageID="display.ga.asset.exanine_status" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--点检周期--> <ait:message
					messageID="display.ga.asset.exanine_date" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--预点检日期--> <ait:message
					messageID="display.ga.asset.check_date" module="ga" /></td>
				<td width="8%" class="info_title_01"><!--备注--> <ait:message
					messageID="display.ga.asset.remark" module="ga" /></td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center" onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">${oneResult.ASSET_TYPE_NAME}</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_NO}</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_NAME}</td>
					<td align="center" style="color: #666666;">${oneResult.FILE_NO}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.BRAND}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.SPECIFIC}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PRODUCT_NO}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.QUANTITY}</td>
					<td align="center" style="color: #666666;">${oneResult.UNIT_PRICE}&nbsp;元</td>
					<td align="center" style="color: #666666;">${oneResult.BUY_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.WARRANTY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.VENDOR}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.TELEPHONE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.CAPITAL_ASSET_NO}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_POSITION}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.USE_DEPT_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_KEEPER_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.STATUS}&nbsp;</td>
					
					<c:choose>
						<c:when test="${oneResult.STATUS eq '已报废'||oneResult.STATUS eq '维修中'}">
							<td align="center" style="color: #FF0000;">***&nbsp;</td>
							<td align="center" style="color: #FF0000;">***&nbsp;</td>	
						</c:when>
						<c:otherwise>
							<td align="center" style="color: #666666;">${oneResult.PERIOD}&nbsp;</td>
							<td align="center" style="color: #666666;">${oneResult.INSPECTION_DATE}&nbsp;</td>
						</c:otherwise>
					</c:choose>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- Page Navigation Start-->
		<ait:page link="/gaControlServlet"
			parameters="&operation=AssetManage&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&assetNo=${assetNo}&assetName=${assetName}&assetType=${assetType}&status=${status}&useDeptId=${useDeptId}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" />

		<!-- Page Navigation End -->

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(recordList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(recordList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
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
</form>
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">
</div>
</body>
</html>
