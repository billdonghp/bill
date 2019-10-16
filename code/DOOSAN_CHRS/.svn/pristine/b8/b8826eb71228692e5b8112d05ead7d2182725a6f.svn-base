<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if($F('dataType')=="CheckType0001"){
	
		if(!patrn.test(document.form1.periodNum.value)){
      		alert("点检周期必须输入数字!");
      		return  false;
    	}
   
    	if(document.form1.periodUnit.value=="" ||document.form1.periodUnit.value =="all"){
    		alert("请选择点检周期的单位！");
    		return false;
    	}
    	if(document.form1.dataType.value=="" ||document.form1.dataType.value=="all"){
    		alert("请选择类别！");
    		return false;
    	}
    
    	if($F('empId')==""){
    		alert("请选择点检人!");
    		return false;
    	}
    }
   	if($F('dataType')=="CheckType0002"){
   		$('status').value = "CheckStatus0003";
   	}
   
    AssetNoTest(document.form1.assetNo.value);
   
}

function AssetNoTest(content){
	var url = "/ajaxControlServlet" ;
    var pars = "operation=assetNoTest&condition=" + encodeURIComponent(content);
	new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: testResponse}
        );
}

function testResponse(XmlHttpRequest){		
		str = XmlHttpRequest.responseText;
		if(str.length==3){
			$('assetNoSpan').innerHTML="没有此资产编号!";
		}
		else
		{
			 document.form1.action="/gaControlServlet?operation=insertInspection&menu_code=${param.menu_code}";
			 document.form1.fireSubmit();
		}
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


var time1=null;
function SearchContentA(condition,id){		
	if(time1!=null){
		clearTimeout(time1);
		time1=null;  
	}
	time1=setTimeout(function(){
					//	alert(condition);
						SearchCA(condition,id);
					},500);  
}

function SearchCA(condition,id){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=searchAsset&condition=" + encodeURIComponent(condition);

		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list1');
		layeri = $('iemp1');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
	
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse1}
        );
}	

function showResponse1(XmlHttpRequest){
	
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[3].firstChild.nodeValue;
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

function layerCloseA(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValueA(cell) {
		$('assetSeqNo').value=cell.childNodes[0].firstChild.nodeValue;
		$('assetNo').value=cell.childNodes[1].firstChild.nodeValue;
		span = document.all('assetNameSpan');
		span.innerHTML = cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

function changeView(){
	if($('dataType').value=="CheckType0002"){
		
		obj = $('tableObj');
		obj.deleteRow(6);
		obj.deleteRow(5);
		obj.deleteRow(4);
		obj.deleteRow(3);
		
		obj = $('tableObj').insertRow(3);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "维修日期";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='inspectionDate' size='15' onclick='setday(this);' readonly='readonly'/>";
		
		obj = $('tableObj').insertRow(4);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "维修厂家";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='maintainVendor' size='15'/>";
		
		obj = $('tableObj').insertRow(5);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "维修内容";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='maintainContent' size='15'/>";
		
		obj = $('tableObj').insertRow(6);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "费用";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='maintainCost' size='15'/>&nbsp;元";
		
		obj = $('tableObj').insertRow(7);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "保质期";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='warrantyLength' size='15'/><input type='hidden' name='status'/>";
		
	}
	if($('dataType').value=="CheckType0001"){
		
		obj = $('tableObj');
		obj.deleteRow(7);
		obj.deleteRow(6);
		obj.deleteRow(5);
		obj.deleteRow(4);
		obj.deleteRow(3);
		
		obj = $('tableObj').insertRow(3);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "点检周期";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='periodNum' size='10'/>&nbsp"+$('divObj1').innerHTML;
		
		obj = $('tableObj').insertRow(4);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "点检日期";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' name='inspectionDate' size='15' onclick='setday(this);' readonly='readonly'/>";
		
		obj = $('tableObj').insertRow(5);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "点检人";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = "<input type='text' id='empId' name='empId' size='15' value='${empId}' onkeyup='SearchContent(this.value,this.id);' />"+
					   "<input type='hidden' id='personId' name='personId' size='8' value='${personId}'/>";
		
		obj = $('tableObj').insertRow(6);
		td = obj.insertCell();
		td.className = "info_title_01";
		td.innerHTML = "点检状态";
		td = obj.insertCell();
		td.className = "info_content_00";
		td.innerHTML = $('divObj2').innerHTML;
		
	}
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">添加点检\维修基本信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="20%" class="info_title_01">资产编号</td>
				<td class="info_content_00"><input type="text" id="assetNo"
					name="assetNo" size="15" value="${assetNo}"
					onkeyup="SearchContentA(this.value,this.id)" required /> <input
					type="hidden" id="assetSeqNo" name="assetSeqNo"
					value="${assetSeqNo}" /><span id="assetNoSpan" style="color:#FF0000">&nbsp;</span></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">资产名称</td>
				<td class="info_content_00" /><span id="assetNameSpan">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">类别</td>
				<td class="info_content_00"><ait:codeClass name="dataType"
					codeClass="CheckType" selected="${dataType}" onChange="changeView();"/></td>
			</tr>
			
			<tr>
				<td width="20%" class="info_title_01">点检周期</td>
				<td class="info_content_00"><input type="text" name="periodNum"
					size="10" />&nbsp;<ait:codeClass name="periodUnit"
					codeClass="checkUnit" selected="${periodUnit}" all="all" />&nbsp;(单位)</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">点检日期</td>
				<td class="info_content_00"><input type="text"
					name="inspectionDate" size="15" onclick="setday(this);" readonly="readonly"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">点检人</td>
				<td class="info_content_00"><input type="text" id="empId" name="empId" size="15" value="${empId}"
					onkeyup="SearchContent(this.value,this.id);" /> <input
					type="hidden" id="personId" name="personId" size="8"
					value="${personId}" /></td>
			</tr>	
			<tr>
				<td width="20%" class="info_title_01">点检状态</td>
				<td class="info_content_00"><ait:codeClass name="status"
					codeClass="CheckStatus" all="all" remove="CheckStatus0003"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">备注</td>
				<td class="info_content_00"><textarea name="remark" rows="3"
					cols="25"></textarea></td>
			</tr>
		</table>
		
		<div id="divObj1" style="display: none;">
			
			<ait:codeClass name="periodUnit" codeClass="checkUnit" selected="${periodUnit}" all="all"/>&nbsp;(单位)
		</div>
		<div id="divObj2" style="display: none;">
			<ait:codeClass name="status" codeClass="CheckStatus" all="all" remove="CheckStatus0003"/>
		</div>
		
		<iframe id='iemp'
			style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
		</iframe>
		<div id="emp_list"
			style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
		</div>
		<iframe id='iemp1'
			style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
		</iframe>
		<div id="emp_list1"
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
