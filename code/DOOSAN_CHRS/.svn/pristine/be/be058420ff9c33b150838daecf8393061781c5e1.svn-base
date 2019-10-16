<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>点检\维修信息</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/ga/ga_add_inspection.jsp?menu_code=${param.menu_code}";
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
	if(document.form1.checkStatus.value=="all")
		document.form1.checkStatus.value=="";
	if(document.form1.checkType.value=="all")
		document.form1.checkType.value=="";
	document.form1.action="/gaControlServlet?operation=updateForUpdateInspection&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.seqNo.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=deleteInspection&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {
	if(document.form1.assetName.value=="all")
		document.form1.assetName.value="";
	document.form1.action="/gaControlServlet?operation=RetrieveInspectionListCmd&menu_code=${param.menu_code}";
	document.form1.submit();
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

function exportExcel()
{
	document.form1.action="/gaControlServlet?operation=exportInspection&menu_code=${param.menu_code}&type=exportExcel";
	document.form1.submit();
}

function downloadImportTemplate()
{
	
	document.form1.action="/gaControlServlet?operation=exportInspection&menu_code=${param.menu_code}&type=downloadImportTemplate";
	document.form1.submit();
}

function importExcel()
{
	url="/gaControlServlet?operation=exportInspection$menu_code=${param.menu_code}$type=importExcel";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
}

function CheckAll() {
	
	var selected = document.form1.ck_all.value == "0" ? true : false;
    var affirmno = document.getElementsByName("assetNo") ;
  	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	}
	document.form1.ck_all.value = selected ? "1" : "0";
}

function Confirm()
{
	var flag = false ;
	var affirmno = document.getElementsByName("assetNo") ;
	for (var i = 0; i< affirmno.length ; i++){
	
		if(affirmno[i].checked == true )
		{
			flag = true;
		}
			
	}
	if(flag == false)
	{
		alert("请选择确认项目!");
		return ;
	}
	document.form1.action="/gaControlServlet?operation=confirmInspection&menu_code=${param.menu_code}";
	document.form1.submit();
}
</SCRIPT>
<body>
<form name="form1" method="post">
	<input type="hidden" name="seqNo"/>
	<input type="hidden" name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
			<%@ include file="./inc/gatoolbar2.jsp"%>
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
				<td width="8%" class="info_content_01" colspan="3"><ait:image
							src="/images/btn/Excel_Exp.gif" border="0" align="absmiddle"
							onclick="javascript:exportExcel();" style="cursor:hand" /> | 
							<a target="_blank" href="/reports/template/inspection_template.xls"><ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" /></a> |
							<!-- <ait:image
							src="/images/btn/Template.gif" border="0" align="absmiddle"
							onclick="javascript:downloadImportTemplate();"
							style="cursor:hand" /> | -->
							<ait:image
							src="/images/btn/Excel_Imp.gif" border="0" align="absmiddle"
							onclick="javascript:importExcel();" style="cursor:hand" /></td>

			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01" nowrap="nowrap">点检开始日期</td>
						<td width="10%" class="info_content_00"><input id="startDate"
							name="startDate" type="text" size="15" value="${startDate}"
							readonly="readonly" onclick="setday(this)" /></td>
						<td width="10%" class="info_title_01">点检结束日期</td>
						<td width="10%" class="info_content_00"><input id="endDate"
							name="endDate" type="text" size="15" value="${endDate }"
							readonly="readonly" onclick="setday(this)"></td>
						<td width="10%" class="info_title_01">资产类型</td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="assetType" codeClass="AssetType" selected="${assetType}"
							all="all" /></td>
						<td width="10%" class="info_title_01">类别</td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="checkType" codeClass="CheckType" selected="${checkType}"
							all="all" /></td>
					</tr>
					<tr>
						<td width="10%" class="info_title_01">资产编号</td>
						<td width="10%" class="info_content_00"><input id="assetNo"
							name="assetNo" type="text" value="${assetNo}" size="15" /></td>
						<td width="10%" class="info_title_01">资产名称</td>
						<td width="10%" class="info_content_00"><ait:select
							dataListName="recordAssetName" name="assetName"
							selected="${assetName}" all="all" /></td>
						<td width="10%" class="info_title_01">点检\维修状态</td>
						<td width="10%" class="info_content_00"><ait:codeClass
							name="checkStatus" codeClass="CheckStatus" selected="${checkStatus}" all="all" /></td>
						<td width="10%" class="info_title_01">搜索</td>
						<td width="10%" class="info_content_01"><ait:image
							src="/images/btn/Search.gif" border="0" align="absmiddle"
							onclick="javascript:Search();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>

		<!-- display 3 level menu --> <%@ include
			file="../hrm/inc/hrm_view_toolbar.jsp"%> <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">点检\维修记录</td>
			</tr>
		</table>

		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="3%" class="info_title_01"><a href="#" onclick="CheckAll();" style="color:red">全选</a></td>
				<td width="8%" class="info_title_01">资产编号</td>
				<td width="8%" class="info_title_01">名称</td>
				<td width="8%" class="info_title_01">资产类型</td>
				<td width="8%" class="info_title_01">类别</td>
				<td width="8%" class="info_title_01">点检\维修日期</td>
				<td width="8%" class="info_title_01">点检状态</td>
				<td width="8%" class="info_title_01">点检人</td>
				<td width="8%" class="info_title_01">点检周期</td>
				<td width="8%" class="info_title_01">维修厂家</td>
				<td width="8%" class="info_title_01">维修内容</td>
				<td width="8%" class="info_title_01">保质期</td>
				<td width="8%" class="info_title_01">费用</td>
				<td width="8%" class="info_title_01">备注</td>
				<td width="8%" class="info_title_01">确认人</td>
				<td width="8%" class="info_title_01">确认日期</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center"
					onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">
						<input type="checkbox" name="assetNo" value="${oneResult.SEQ_NO}">
					</td>
					<td align="center" style="color: #666666;" height="27">${oneResult.ASSET_NO}</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_NAME}</td>
					<td align="center" style="color: #666666;">${oneResult.ASSET_TYPE}</td>
					<td align="center" style="color: #666666;">${oneResult.DATA_TYPE_NAME}</td>
					<td align="center" style="color: #666666;">${oneResult.INSPECTION_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.STATUS_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.INSPECTION_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PERIOD}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.MAINTAIN_VENDOR}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.MAINTAIN_CONTENT}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.WARRANTY_LENGTH}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.MAINTAIN_COST}<c:if test="${oneResult.MAINTAIN_COST ne null}">&nbsp;元</c:if>&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.UPDATED_BY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.UPDATE_DATE}&nbsp;</td>
				</tr>
			</c:forEach>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=RetrieveInspectionListCmd&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&assetType=${assetType}&checkType=${checkType}&assetNO=${assetNo}&assetName=${assetName}&checkStatus=${checkStatus}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupSize}"
			useJS="false" />

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
