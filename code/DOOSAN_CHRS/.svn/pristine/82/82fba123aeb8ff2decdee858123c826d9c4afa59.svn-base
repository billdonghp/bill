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


var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
			SearchC(condition,id);
		},500);  
}

function SearchC(condition,id){

	var url = "/ajaxControlServlet" ;
    	var pars = "operation=hrmSearchEmployeeAll&condition=" + encodeURIComponent(condition);
    	
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
		$('empID').value=cell.childNodes[0].firstChild.nodeValue;
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		
		var empID = document.form1.empID.value;
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=SearchEmployeeInformation&empID=" + empID;
		
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showEmployeePartInformation}
        );
		layerClose();
}


function SearchEmployeeInformation(empName){

		var url = "/ajaxControlServlet" ;
     	var pars = "operation=SearchEmployeeInformation&empID=" + empID;
		
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showEmployeePartInformation}
        );
}	

function showEmployeePartInformation(XmlHttpRequest){
    
    var dept = $('dept');
    var joinDate = $('joinDate');
    var birth_ymd=$('birth_ymd');
    var info = XmlHttpRequest.responseText.split(",") ;
	dept.innerHTML= info[0];
    joinDate.innerHTML= info[1];
    birth_ymd.innerHTML= info[2];
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
	
	i = Number(document.form1.temp1.value)+1;
	document.form1.temp1.value = i;
	
	var nTr = document.getElementById('operateTable').insertRow();
	
	td = nTr.insertCell() ;
	td.className = "info_title_01" ;
	td.innerHTML = "体检结果<input type='radio' name='rowNum'/>";
	
	td = nTr.insertCell() ;
	td.className = "info_content_00" ;
	td.innerHTML = "体检单位:&nbsp;<input type='text' id='jobHealth"+i+"' name='jobHealth"+i+"' value='' onmousedown='job("+i+")'> 体检时间:&nbsp;<input type='text' name='medicalDate"+i+"' value='' onClick='setday(this);' readonly='readonly'>	&nbsp;&nbsp;<select name='StateMedicalFlag"+i+"'> " + document.getElementById('StateMedicalFlag0').innerHTML + "</select>&nbsp;<select name='medicalflag"+i+"' onchange='changeFlag(this.value,"+ i +")'> " + document.getElementById('medicalflag0').innerHTML + "	</select>&nbsp;&nbsp;	<table><tr><td>	<span id='medicalRemark"+i+"' style='visibility: hidden'> 备注:</span><textarea name='remarkText"+i+"' style='height:20px;width:500px;visibility: hidden;' type='_moz'  onfocus=\"this.style.height='30px';\" onblur=\"this.style.height='20px';\"></textarea><br><span id='medicalResult"+i+"' style='visibility: hidden'>体检异常处理结果:</span><textarea name='remark"+i+"' style='height:20px;width:500px;visibility: hidden;' type='_moz' onfocus=\"this.style.height='30px';\" onblur=\"this.style.height='20px';\"></textarea></td></tr></table>";
}

tableUtil.deleteRow = function(){
	document.form1.temp1.value = document.form1.temp1.value - 1;
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

function changeFlag(flagId,i){
	if(flagId==2){
		document.all('medicalResult'+i).style.visibility = 'visible';
		document.form1('remark'+i).style.visibility = 'visible';
		
		document.all('medicalRemark'+i).style.visibility = 'hidden';
		document.form1('remarkText'+i).style.visibility = 'hidden';
	}else if(flagId==1){
		document.all('medicalRemark'+i).style.visibility = 'visible';
		document.form1('remarkText'+i).style.visibility = 'visible';
		
		document.all('medicalResult'+i).style.visibility = 'hidden';
		document.form1('remark'+i).style.visibility = 'hidden';
	}else{
		document.all('medicalResult'+i).style.visibility = 'hidden';
		document.form1('remark'+i).style.visibility = 'hidden';
		document.all('medicalRemark'+i).style.visibility = 'hidden';
		document.form1('remarkText'+i).style.visibility = 'hidden';
	}
}

function Save()
{
	i = Number(document.form1.temp1.value);
	j = Number(document.form1.temp.value);
	
	if(document.form1.empName.value=="" || document.form1.empID.value==""){
		alert("您未填写员工姓名或者工号！");
		return;
	}
	if(document.form1.Position.value == ""){
		alert("请选择岗位！");
		return;
	}
	
	if(document.form1.JOB_DISEASE_CODE == null || document.form1.JOB_DISEASE_TYPE_CODE == null){
		alert("职业危害和职业病类型不能为空！请到岗位与职业病关系页面做设置！");
		return;
	}
	
	if(document.form1.StartDate.value == ""){
		alert("请填写入岗日期！");
		return;
	}
	
	for(var z=0 ; z<=i ; z++){
		if((document.form1('medicalDate'+z) != null && document.form1('medicalDate'+z).value == "") || document.form1('medicalDate'+z).value == '请输入体检时间'){
			alert("请填写体检日期");
			return;
		}
	}
	
	for(var z=0 ; z<=i ; z++){
		if(document.form1('medicalflag'+z) != null && document.form1('medicalflag'+z).value == "0"){
			alert("请选择体检结果");
			return;
		}
	}
	
	for(var z=0 ; z<=i ; z++){
		if((document.form1('jobHealth'+z) != null && document.form1('jobHealth'+z).value == "0") || document.form1('jobHealth'+z).value == '请输入体检单位'){
			alert("请选择体检单位");
			return;
		}
	}
	
	for(var z=0 ; z<=i ; z++){
		if(document.form1('medicalflag'+z) != null && document.form1('medicalflag'+z).value == "2"){
			if(document.form1('remark'+z) != null && document.form1('remark'+z).value == ""){
				alert("请填写异常说明");
				return;
			}
			if(document.form1('remark'+z) != null && document.form1('remark'+z).value.length > 200){
				alert("异常说明不能超过200字");
				return;
			}
			
			if(document.form1('remarkText'+z) != null && document.form1('remarkText'+z).value.length > 200){
				alert("备注说明不能超过200字");
				return;
			}
		}
	}
	
	
	document.form1.action="/safeControlServlet?operation=jobHealth&content=addPositionInformation&menu_code=${param.menu_code}&i="+j;
	document.form1.submit();
}

function job(num){
  var jobHealth = "jobHealth"+num;
	if(document.getElementById(jobHealth).value == '请输入体检单位'){
		document.getElementById(jobHealth).value="";
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
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="0">
		<input type="hidden" name="temp1" value="0">
		<input type="hidden" name="person_id" value="">
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">岗位信息添加</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="20%" class="info_title_01">职业健康编号</td>
				<td class="info_content_00"><input name="DOCUMENTNO" value="" type="text">			
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">
		      		姓名/职号
		      	</td>
	      		  <td class="info_content_00"><!-- 请输入姓名查找 -->
		          	<input id="empName" name="empName" size="8" value="<ait:content enContent='${basic.pinyinName}' zhContent='${basic.chineseName}' koContent='${basic.koreanName}'/>" 
		          		onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          				
          				<input type="text" id="empID" name="empID" size="8" value="<c:out value='${basic.empID}'/>" 
          				onkeyup="SearchContent(this.value,this.id)" 
          				title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id"
          				 module="hrm"/>' required/>
          				
		         </td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">部门</td>
				<td class="info_content_00" id="dept">
				&nbsp;
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">出生日期</td>
				<td class="info_content_00" id="birth_ymd">
				&nbsp;
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">入社日期</td>
				<td class="info_content_00" id="joinDate">
				&nbsp;
				</td>
			</tr>			
			<tr>
				<td width="20%" class="info_title_01">岗位</td>
				<td class="info_content_00">
					<select name="Position" onchange="selectPOSITION(this.value);selectDiseaseType(this.value);">
						<option value="">
							请选择
						</option>
						<c:forEach items="${POSITION_INFORMATION}" var="All" varStatus="i">
							<option value="${All.RELATIONS_ID}">
								${All.CODE_NAME}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">职业危害</td>
				<td class="info_content_00">
					<span id="disease">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">职业病类型</td>
				<td class="info_content_00">
					<span id="diseasetype">&nbsp;</span>
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">从事本岗日期</td>
				<td class="info_content_00">
					<input type="text" name="StartDate" value="" onClick="setday(this);" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">体检结果<br>
				<img src="../images/btn/Add.gif" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
				<td class="info_content_00">
					体检单位:&nbsp;<input type="text" id="jobHealth0" name="jobHealth0" value="" onmousedown="job(0)">
					体检时间:&nbsp;<input type="text" id="medicalDate0" name="medicalDate0" value="" onClick="setday(this);" readonly="readonly">&nbsp;
					
					<select name="StateMedicalFlag0" id="StateMedicalFlag0">
						<option value="0">请选择</option>
						<option value="come">入岗体检</option>
						<option value="on">在岗体检</option>
						<option value="out">离岗体检</option>
					</select>
					
					<select name="medicalflag0" id="medicalflag0" onchange="changeFlag(this.value,0)">
						<option value="0">体检结果</option>
						<option value="1">正常</option>
						<option value="2">异常</option>
					</select>
				
				     
					<table>
					<tr>
					<td>
					
				    <span id="medicalRemark0" style="visibility: hidden">备注:</span><textarea id="remarkTextId0" name="remarkText0" style="height:20px;width:500px;visibility: hidden;" type="_moz"  onfocus="this.style.height='30px';" onblur="this.style.height='20px';"></textarea>
				    <br><span id="medicalResult0" style="visibility: hidden">体检异常处理结果:</span><textarea id="remarkId0" name="remark0" style="height:20px;width:500px;visibility: hidden;" type="_moz"  onfocus="this.style.height='30px';" onblur="this.style.height='20px';"></textarea>
					</td></tr>
					</table>
				   
				
				</td>
				<td>
				
				</td>
			</tr>
			</table>
			
			<iframe id='diseasetype' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
		
			<iframe id='disease' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
		
			<iframe id='dept' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
			
			<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
			</iframe>
			<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
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
