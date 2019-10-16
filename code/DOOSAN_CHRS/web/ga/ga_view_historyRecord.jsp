<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

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
		<ait:image src="/images/btn/Back.gif"  border="0" align="RIGHT"
          	onclick="javascript:history.back();" style="cursor:hand" title="后退" enTitle="return" />

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
		<table width="100%" border="1" cellspacing="0" cellpadding="2"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="8%" class="info_title_01">职号</td>
				<td width="8%" class="info_title_01">姓名</td>
				<td width="8%" class="info_title_01">性别</td>
				<td width="8%" class="info_title_01">课组</td>
				<td width="8%" class="info_title_01">部门</td>
				<td width="8%" class="info_title_01">入社时间</td>
				<td width="8%" class="info_title_01">名称</td>
				<td width="8%" class="info_title_01">型号</td>
				<td width="8%" class="info_title_01">频率</td>
				<td width="8%" class="info_title_01">备注</td>
				<td width="8%" class="info_title_01">发放日期</td>
			</tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
				<tr align="center">
					<td align="center" style="color: #666666;" height="27">${oneResult.EMPID}</td>
					<td align="center" style="color: #666666;">${oneResult.CHINESENAME}</td>
					<td align="center" style="color: #666666;">${oneResult.SEX}</td>
					<td align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.FOURTHDEPTNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.DATESTARTED}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.SMOCKNAME}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.MODELNUMBER}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.PERIOD}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
					<td align="center" style="color: #666666;">${oneResult.CREATE_DATE}&nbsp;</td>
				</tr>
			</c:forEach>
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
