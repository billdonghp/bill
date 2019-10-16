<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<SCRIPT type="text/javascript">

function Save() {
	
	
	if($('personId').value==""){
		alert("请输入员工职号!");
		return false;
	}
	var rowNum = Number($("maxRowNum").value);
	for(var i=0; i<=rowNum; i++){
		if($('smockName' + i) != null) {
			if($('smockName'+i).value == "")
			{
				alert("请选择工作服名称!");
				return false;
			}
			
			var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
			if(!patrn.test($('periodNum'+i).value)){
				alert("频率必须为数字!");
				return false;
			}
			
			
		}
	}
	
	document.form1.action="/gaControlServlet?operation=updateSmockRelation&menu_code=${param.menu_code}";
	document.form1.submit();
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
		
		var condition = $('personId').value;
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=getEmployeeInfoForSmockRelation&condition=" + encodeURIComponent(condition);
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete:printResponse}
        );
}

function printResponse(XmlHttpRequest){

		xml = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr");
		$('span1').innerHTML = xml[0].childNodes[0].firstChild.nodeValue;
		$('span2').innerHTML = xml[0].childNodes[1].firstChild.nodeValue;
		$('span3').innerHTML = xml[0].childNodes[2].firstChild.nodeValue;
		$('span4').innerHTML = xml[0].childNodes[3].firstChild.nodeValue;
		$('span5').innerHTML = xml[0].childNodes[4].firstChild.nodeValue;
}

function changeUnit(num){

		var condition = $('smockName'+num).value;
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=getSmockRelationUnit&condition=" + encodeURIComponent(condition);
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onSuccess : function(XmlHttpRequest){		
            							 xml = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr");
										 $('unitName'+num).innerHTML = xml[0].childNodes[0].firstChild.nodeValue ;
										 var sizeType = xml[0].childNodes[1].firstChild.nodeValue ;
										 if(sizeType == "sizeType001"){
										 	$('sizeType'+num).innerHTML = "<select name='modelNumber'>" + document.getElementById('modelNumber_1').innerHTML + "</select>";
										 }
										 else if(sizeType == "sizeType002"){
										 	$('sizeType'+num).innerHTML = "<select name='modelNumber'>" + document.getElementById('modelNumber_2').innerHTML + "</select>";
										 }
										 else if(sizeType == "sizeType003"){
										 	$('sizeType'+num).innerHTML = "<select name='modelNumber'>" + document.getElementById('modelNumber_3').innerHTML + "</select>";
										 }
              }});
}


</SCRIPT>
<body onload="changeUnit(0);">
<form name="form1" method="post" action="">
<input type="hidden" name="maxRowNum" value="0"> 
<input type="hidden" name="rowCount" value="0"> 
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
		<td valign="TOP" align="CENTER"><br>
		<br>
		<table id="Tables" width="100%" border="1" cellspacing="0"
			cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td align="left" class="title1" colspan="18">个人工作服信息</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">职号</td>
				<td width="15%" class="info_title_01">姓名</td>
				<td width="15%" class="info_title_01">性别</td>
				<td width="15%" class="info_title_01">课组</td>
				<td width="15%" class="info_title_01">部门</td>
				<td width="15%" class="info_title_01">入社时间</td>
			</tr>
			<tr>
				<input type="hidden" name="relSeqNo" value="${result.REL_SEQ_NO}"/>
				<td nowrap="nowrap" class="info_content_01" height="27"><input
					type="text" id="empId" name="empId" value="${result.EMPID}"
					onkeyup="SearchContent(this.value,this.id)" /><input type="hidden"
					id="personId" name="personId" value="${result.PERSON_ID}" /></td>
				<td nowrap="nowrap" class="info_content_01"><span id="span1">${result.CHINESENAME}</span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span2">${result.SEX}</span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span3">${result.DEPTNAME}</span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span4">${result.FOURTHIDEPTNAME}</span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><span id="span5">${result.DATESTARTED}</span>&nbsp;</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01" height="27"></td>
				<td nowrap="nowrap" class="info_content_01"></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">名称</td>
				<td width="15%" class="info_title_01">单位</td>
				<td width="15%" class="info_title_01">型号</td>
				<td width="15%" class="info_title_01">频率</td>
				<td width="15%" class="info_title_01">备注</td>
				<td width="15%" class="info_title_01"></td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01"><ait:codeClass
					codeClass="smockInfo" name="smockName0" all="all"
					onChange="changeUnit(0);" selected="${result.SMOCKNAME_T}"/></td>
				<td nowrap="nowrap" class="info_content_01"><span id="unitName0"></span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><span id="sizeType0"></span>&nbsp;</td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="periodNum0" size="5" value="${result.PERIODNUM}"/>&nbsp;<ait:codeClass
					codeClass="smockPeriodNum" name="periodUnit0" all="all" selected="${result.PERIODUNIT}"/></td>
				<td nowrap="nowrap" class="info_content_01"><textarea
					name="remark0" style=" height: 25px;width:150px " type="_moz"
					onfocus="this.style.height='50px'"
					onblur="this.style.height='20px';">${result.REMARK}</textarea></td>
				<td nowrap="nowrap" class="info_content_01">&nbsp;</td>
			</tr>
		</table>

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
		<br>
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
</form>
<div id="standardSize" style="display:none;">
	<ait:codeClass codeClass="smockSize1" name="modelNumber_1"  all="all" />
	<ait:codeClass codeClass="smockSize2" name="modelNumber_2"  all="all" />
	<ait:codeClass codeClass="smockSize3" name="modelNumber_3"  all="all" />
</div>

<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">
</div>
</body>
</html>
