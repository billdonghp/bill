<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/ga/ga_certificate_add.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {

	if(document.form1.seqNo.value=="") {
		
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/gaControlServlet?operation=retrieveDataForUpdateCertificate&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	if(document.form1.seqNo.value=="") {
		
	    alert("请选择删除项目");
		return;
	}
	if(confirm('确定删除吗？')){
		document.form1.action="/gaControlServlet?operation=deleteCertificate&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {

	document.form1.seqNo.value="";	
	document.form1.action="/gaControlServlet?operation=retrieveCertificate&menu_code=${param.menu_code}";
	document.form1.submit();
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

function historyRecord(seqNo)
{
	document.form1.seqNo.value = seqNo;
	document.form1.action="/gaControlServlet?operation=retrieveDataForCertificateHistory&menu_code=${param.menu_code}";
	document.form1.submit();
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
				<td class="title1">查询条件</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td width="10%" class="info_title_01">开始日期</td>
						<td width="10%" class="info_content_00">
							<input id="startDate" name="startDate" type="text" size="15" value="${startDate}" readonly="readonly" onclick="setday(this)"/></td>
						<td width="10%" class="info_title_01">结束日期</td>
						<td width="10%" class="info_content_00">
							<input id="endDate" name="endDate" type="text" size="15" value="${endDate }" readonly="readonly" onclick="setday(this)"></td>
						<td width="10%" class="info_title_01">部门</td>
						<td width="10%" class="info_content_00">
							<ait:selDept name="deptId" deptLevel="4" selected="${deptId}" all="all" /></td>
						<td width="10%" class="info_title_01">证书名称</td>
						<td width="10%" class="info_content_00">
							<ait:select dataListName="certNameList" name="certName" selected="${certName}" all="all"/></td>
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
				<td align="left" class="title1" colspan="10">证书基本信息</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="8%" class="info_title_01">部门</td>
				<td width="8%" class="info_title_01">证书名称</td>
				<td width="8%" class="info_title_01">发证时间</td>
				<td width="8%" class="info_title_01">首次发证时间</td>
				<td width="8%" class="info_title_01">发证机关</td>
				<td width="8%" class="info_title_01">更新周期</td>
				<td width="8%" class="info_title_01">预更新日期</td>
				<td width="8%" class="info_title_01">更新日期</td>
				<td width="8%" class="info_title_01">负责人</td>
				<td width="8%" class="info_title_01">备注</td>
				<td width="8%" class="info_title_01">历史记录</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center" onClick="band('#E7F0EF','black',${oneResult.SEQ_NO});">
					<td align="center" style="color: #666666;" height="27">${oneResult.DEPTNAME}</td>
					<td align="center" style="color: #666666;">${oneResult.CERTIFICATE_ID}</td>
					<td align="center" style="color: #666666;">${oneResult.ISSUED_DATE}</td>
					<td align="center" style="color: #666666;">${oneResult.ISSUE_DATE}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.ISSUING_AUTHORITY}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PERIOD_NUM}${oneResult.PERIOD_UNIT_NAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.NOVATION_DATE}&nbsp;</td>
					<c:if test="${oneResult.NOVATIONED_DATE eq null}">
						<td align="center" style="color: #666666;">***</td>
					</c:if>
					<c:if test="${oneResult.NOVATIONED_DATE ne null}">
						<td align="center" style="color: #666666;">${oneResult.NOVATIONED_DATE}&nbsp;</td>
					</c:if>
					<td align="center" style="color: #666666;">${oneResult.SUPERINTEND}</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td><a href="#" onclick="historyRecord(${oneResult.SEQ_NO});"><font color="#FF0000">查看</font></a></td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- Page Navigation Start-->
		<ait:page link="/gaControlServlet"
			parameters="&operation=retrieveCertificate&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&dpetId=${deptId}&certName=${certName}"
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
