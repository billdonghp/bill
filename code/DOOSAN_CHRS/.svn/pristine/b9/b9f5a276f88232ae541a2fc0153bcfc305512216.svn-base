<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="POSITION_INFORMATION" class="java.util.ArrayList" scope="request"/>
<html>
<head>
<script src="../js/prototype.js"></script>
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

function selectPOSITION(opsitionId){
	var url = "/ajaxControlServlet" ;
    var pars = "operation=opsitionIdCounterpartsDisease&opsitionId=" + opsitionId;
	
	new Ajax.Request(
           url,{method: 'post', parameters: pars, onComplete: showPositionInformation}
       );
}

function showPositionInformation(XmlHttpRequest){
    
    var disease = $('disease');
	disease.innerHTML=XmlHttpRequest.responseText;
    disease.style.visibility = 'visible';
}


function selectDiseaseType(opsitionId){

	var url = "/ajaxControlServlet" ;
    var pars = "operation=opsitionIdCounterpartsDiseaseType&opsitionId=" + opsitionId;
	
	new Ajax.Request(
           url,{method: 'post', parameters: pars, onComplete: showPositionDiseaseType}
       );
}

function showPositionDiseaseType(XmlHttpRequest){
    
    var diseasetype = $('diseasetype');
	diseasetype.innerHTML=XmlHttpRequest.responseText;
    diseasetype.style.visibility = 'visible';
}


var type = null;

var tableUtil = new Object();
var i=0;
tableUtil.appendRow = function(){
	document.form1.temp.value = Number(document.form1.temp.value) + 1;
	
	i = Number(document.form1.temp.value);
	//document.form1.temp1.value = i;
	
	var nTr = document.getElementById('operateTable').insertRow();
	
	td = nTr.insertCell() ;
	td.className = "info_title_01" ;
	td.innerHTML = "设备<input type='radio' name='rowNum'/>";
	
	td = nTr.insertCell() ;
	td.className = "info_content_00" ;
	td.innerHTML = "<input type='text' name='medicalDate"+i+"' value=''>";
}

tableUtil.deleteRow = function(){
	//document.form1.temp.value = document.form1.temp.value - 1;
	var radioArr = document.getElementsByName('rowNum');
	var tbody = document.getElementById('operateTable').tBodies[0];
	var flag = false;
	for(var i=0;i<radioArr.length;i++)
		if(radioArr[i].checked){
			tbody.removeChild(radioArr[i].parentNode.parentNode);
			flag = true;
		}
	if(flag)
		return;
	else
		alert('请先选择要删除的行');
}


var type1 = null;

var tableUtil1 = new Object();
var k=0;
tableUtil1.appendRow1 = function(){
	document.form1.temp1.value = Number(document.form1.temp1.value) + 1;
	
	k = Number(document.form1.temp1.value);
	//document.form1.temp1.value = i;
	
	var nTr = document.getElementById('operateTable1').insertRow();
	
	td = nTr.insertCell() ;
	td.className = "info_title_01" ;
	td.innerHTML = "管理担当<input type='radio' name='rowNum1' />";
	
	td = nTr.insertCell() ;
	td.className = "info_content_00" ;
	td.innerHTML = "<input type='text' id='empId"+k+"' name='empId"+k+"' value='' onKeyUp='SearchContent(this.value,this.id,"+k+")'> <input type='hidden' id='personId"+k+"' name='personId"+k+"' value=''>";
	
	
}

tableUtil1.deleteRow1 = function(){
	//document.form1.temp.value = document.form1.temp.value - 1;
	var radioArr = document.getElementsByName('rowNum1');
	var tbody = document.getElementById('operateTable1').tBodies[0];
	var flag = false;
	for(var i=0;i<radioArr.length;i++)
		if(radioArr[i].checked){
			tbody.removeChild(radioArr[i].parentNode.parentNode);
			flag = true;
		}
	if(flag)
		return;
	else
		alert('请先选择要删除的行');
}



function Save()
{
	///i = Number(document.form1.temp1.value);
	j = Number(document.form1.temp.value);
	k = Number(document.form1.temp1.value);
	
	if(document.form1.roomname.value==""){
		alert("您未填写会议室名称！");
		return;
	}
	if(document.form1.peoples.value == ""){
		alert("您未填写可容纳人数！");
		return;
	}
	
	document.form1.action="/puControlServlet?operation=Roomsetup&content=saveRoomSetup&menu_code=${param.menu_code}&i="+j+"&k="+k;
	document.form1.submit();
}

 function paseValueToAmount(value){   
                if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
                    if(decimalIndex=='-1'){   
                        return false;   
                    }else{   
                        var decimalPart=value.substring(decimalIndex+1,value.length);   
                        if(decimalPart.length>2){   
                            return true;   
                        }else{   
                            return false;   
                        }   
                    }   
                }   
                return false;   
 } 
 
 
 
function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell,no) {
	$('empId'+no).value=cell.childNodes[0].firstChild.nodeValue;
	$('personId'+no).value=cell.childNodes[2].firstChild.nodeValue;
	layerClose();
}

var time=null;
function SearchContent(condition,id,no){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	
	time=setTimeout(function(){
					///	alert(condition);
						SearchE(condition,id,no);
					},500);  
}


function SearchE(condition,id,no){
		
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=evSearchEmployeeAll&condition=" + encodeURIComponent(condition)+"&id="+id+"&no="+no;
		///alert(pars);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}
function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=pa0102&operation=searchPaEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
		layer.style.height = 48; 
    }else if(size<210){
		layer.style.height = size;  
    }else{
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
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
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="0">
		<input type="hidden" name="temp1" value="0">
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">会议室信息添加</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
			<td nowrap="nowrap" class="info_title_01">所属法人</td>
				<td nowrap="nowrap" class="info_content_00">									
 					 <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}"/>
				</td>			
			</tr>
			<tr>
				<td width="20%" class="info_title_01">
		      		会议室名称
		      	</td>
	      		  <td class="info_content_00"><!-- 请输入姓名查找 -->
		          	 	<input type="text" id="roomname" name="roomname" required/>
		         </td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">可容纳人数</td>
				<td class="info_content_00">
					<input type="text" name="peoples" value="" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')" required>
				</td>
			</tr>
			<!-- <tr>
				<td width="20%" class="info_title_01">管理担当</td>
				<td class="info_content_00">
				<input type="text" id="empId" name="empId" size="15" value="${empId}" onkeyup="SearchContent(this.value,this.id)" />
				<input type="hidden" id="personId" name="personId" size="8" value="${personId}" /></td>
			</tr> -->
			<tr>
				<td width="20%" class="info_title_01">设备提供<br>
				<img src="../images/btn/Add.gif" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
				<td class="info_content_00">
					<input type="text" id="medicalDate0" name="medicalDate0" value="">&nbsp;
				</td>
			</tr>
			
			</table>
			
			<table id="operateTable1" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			<tr>
				<td width="20%" class="info_title_01">管理担当<br>
				<img src="../images/btn/Add.gif" onclick="tableUtil1.appendRow1();" name="a2" id="a2">
					<img src="../images/btn/Delete.gif" onclick="tableUtil1.deleteRow1();" name="a2" id="a2">
				</td>
				<td class="info_content_00">
					<input type="text" id="empId0" name="empId0" value="" onKeyUp='SearchContent(this.value,this.id,0)'>&nbsp;
					<input type="hidden" id="personId0" name="personId0" size="8" value="" /></td>
				</td>
			</tr>
			
			
			</table>
			
			<iframe id='diseasetype' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
		
			<iframe id='disease' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
			
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
