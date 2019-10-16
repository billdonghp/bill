<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<!-- ga_view_present.jsp -->
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function search(){

	$('SEQ_NO').value = "";
	document.ApplyForm.action="/gaControlServlet?operation=ytglPresentList&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}

function Add() {
	document.ApplyForm.action="/ga/ga_add_present_ytgl.jsp?menu_code=${param.menu_code}&cpnyId="+78888888;
	document.ApplyForm.submit();
}
function Update() {

	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.ApplyForm.action="/gaControlServlet?operation=ytglUpdatePresent&menu_code=${param.menu_code}";
	document.ApplyForm.submit();
}
function Delete() {
	if(document.ApplyForm.SEQ_NO.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.ApplyForm.action="/gaControlServlet?operation=deletePresentYtgl&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
	}
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
	document.ApplyForm.SEQ_NO.value=i;
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

<form name="ApplyForm" action="/gmControlServlet" method="post">
<input type="hidden" name="SEQ_NO" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_all.jsp"%>
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
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01">开始日期</td>
						<td width="15%" class="info_content_00"><input type="text"
							style="width:80px" name="startDate" value="${startDate}"
							onClick="setday(this);" readonly="readonly"></td>
						<td width="10%" class="info_title_01">结束日期</td>
						<td width="15%" class="info_content_00"><input type="text"
							style="width:80px" name="endDate" value="${endDate}"
							onClick="setday(this);" readonly="readonly"></td>
						<td nowrap="nowrap" class="info_title_01">所属法人</td>
						<td nowrap="nowrap" class="info_content_00">									
   					       DICC
						</td>
						<td width="10%" class="info_title_01">礼品名称</td>
						<td width="15%" class="info_content_00"><ait:codeClass
							name="presentId" codeClass="PresentName" cpnyId="78888888" selected="${presentId}"
							all="all" /></td>
						<td width="10%" class="info_title_01">类型</td>
						<td width="15%" class="info_content_00"><ait:codeClass
							name="dataType" codeClass="StockType" selected="${dataType}"
							all="all" /></td>
						<td width="10%" class="info_title_01">人员选择</td>
						<td width="15%" class="info_content_00"><!-- 请输入姓名查找 --> <input
							id="empId" name="empId" size="8" value="${empId}"
							onkeyup="SearchContent(this.value,this.id);" /> <input
							type="hidden" id="personId" name="personId" size="8"
							value="${personId}" /></td>
						<td width="15%" class="info_content_00"><ait:image
							src="/images/btn/Search.gif" align="absmiddle"
							onclick="javascript:search();" style="cursor:hand" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="15%" class="info_title_01" nowrap>礼品类型</td>
				<td width="15%" class="info_title_01" nowrap>礼品名称</td>
				<td width="8%" class="info_title_01" nowrap>单价</td>
				<td width="8%" class="info_title_01" nowrap>单位</td>
				<td width="8%" class="info_title_01" nowrap>类型</td>
				<td width="8%" class="info_title_01" nowrap>数量</td>
				<td width="8%" class="info_title_01" nowrap>库存量</td>
				<td width="29%" class="info_title_01" nowrap>备注</td>
				<td width="8%" class="info_title_01" nowrap>使用部门</td>
				<td width="8%" class="info_title_01" nowrap>创建者</td>
				<td width="8%" class="info_title_01" nowrap>创建时间</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center"
					onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.CODE_NAME}</td>
					<td align="center" style="color: #666666;" height="27">
					&nbsp;${oneResult.PRESENT_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.UNIT_PRICE}&nbsp;元</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.UNIT_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.DATA_TYPE_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.QUENTITY}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.STOCK_QUENTITY}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.REMARK}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.APPLY_DEPT_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.CREATED_NAME}</td>
					<td align="center" style="color: #666666;">
					&nbsp;${oneResult.CREATE_DATE}</td>
				</tr>
			</c:forEach>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${recordCount < 6}">
				<c:forEach var="i" begin="1" end="${6-recordCount}" step="1">
					<tr>
						<td class="info_content_01" height="30"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=retrievePresentList&menu_code=${param.menu_code}&personId=${personId}&empId=${empId}&startDate=${startDate}&endDate=${endDate}&presentId=${presentId}&dataType=${dataType}"
			total="${recordCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" /> <!-- Page Navigation End -->

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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
</form>
</body>
</html>
