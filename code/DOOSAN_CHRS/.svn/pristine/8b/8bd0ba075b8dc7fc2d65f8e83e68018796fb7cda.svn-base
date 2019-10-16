<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{	
	if($('deptId').value == "")
	{
		alert("请选择部门!");
		return false;
	}
	if($('periodUnit').value == "")
	{
		alert("请选择更新周期单位!");
		return false;
	}
	var patrn=/^([1-9][0-9]*|0)(\.[0-9]+)?$/;
	if(!patrn.test($('periodNum').value))
	{
		alert("更新周期必须为数字!");
		return false;
	}
	if($('personId').value == "")
	{
		alert("请输入负责人!");
		return false;
	}
    document.form1.action="/gaControlServlet?operation=insertCertificate&menu_code=${param.menu_code}";
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
		<form name="form1" method="post" action=""><input type="hidden"
			name="wasteTypeId" value="" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">添加证件信息</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0"
			cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="15%" class="info_title_01">部门</td>
				<td class="info_content_00">
					<ait:selDept name="deptId" deptLevel="4" all="all"/></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">证件名称</td>
				<td class="info_content_00">
					<input type="text" name="certificateId" size="15" required></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">首次发证时间</td>
				<td class="info_content_00">
					<input type="text" name="issueDate" size="15" onclick="setday(this)" readonly="readonly" required></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">发证机关</td>
				<td class="info_content_00">
					<input type="text" name="issuingAuthority" size="15" required></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">更新周期</td>
				<td class="info_content_00">
					<input type="text" name="periodNum" size="15" required>
					<ait:codeClass codeClass="checkUnit" name="periodUnit" all="all"/>&nbsp;(单位)</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">预更新日期</td>
				<td class="info_content_00">
					<input type="text" name="novationDate" size="15" onclick="setday(this);" readonly="readonly" required></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">负责人</td>
				<td class="info_content_00">
					<input type="text" id="empId" name="empId" size="15" value="${empId}" onkeyup="SearchContent(this.value,this.id);" required/>
					<input type="hidden" id="personId" name="personId" size="8" value="${personId}" /></td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00"><textarea name="remark" rows="3"
					cols="25"></textarea></td>
			</tr>
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
<iframe id='iemp'
	style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">
</div>
<ait:xjos />
</html>
