<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<jsp:useBean id="empId" class="java.lang.String" scope="request"/>
<jsp:useBean id="empName" class="java.lang.String" scope="request"/>
<jsp:useBean id="deptName" class="java.lang.String" scope="request"/>
<jsp:useBean id="dateStarted" class="java.lang.String" scope="request"/>
<jsp:useBean id="position" class="java.lang.String" scope="request"/>
<jsp:useBean id="positionCode" class="java.lang.String" scope="request"/>
<jsp:useBean id="disease" class="java.lang.String" scope="request"/>
<jsp:useBean id="diseaseCode" class="java.lang.String" scope="request"/>
<jsp:useBean id="diseaseType" class="java.lang.String" scope="request"/>
<jsp:useBean id="diseaseTypeCode" class="java.lang.String" scope="request"/>
<jsp:useBean id="startTime" class="java.lang.String" scope="request"/>
<jsp:useBean id="huangangFlag" class="java.lang.String" scope="request"/>
<jsp:useBean id="EMPID" class="java.lang.String" scope="request"/>
<jsp:useBean id="allMedicalInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="tempjobId" class="java.lang.String" scope="request"/>


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
var changePositionFlag = 0;
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
changePositionFlag = 1;
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
changePositionFlag = 1;
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
    ///alert(document.getElementById('medicalflag0').innerHTML);
	i = Number(document.form1.iindex.value);	
	var nTr = document.getElementById('operateTable').insertRow();
	
	td = nTr.insertCell() ;
	td.className = "info_title_01" ;
	td.innerHTML = "体检结果  <input type='radio' name='rowNum'/>";
	
	td = nTr.insertCell() ;
	td.className = "info_content_00" ;
	td.innerHTML = "<input type='text' id='jobHealth"+i+"' name='jobHealth"+i+"' value='请输入体检单位' onmousedown='job("+i+")'>&nbsp;<input type='text' name='medicalDate"+i+"' value='请输入体检时间' onClick='setday(this);' readonly='readonly'>&nbsp;<select name='medicalstateflag"+i+"'> " + document.getElementById('medicalstateflag0').innerHTML + "</select>&nbsp;&nbsp;<select name='medicalflag"+i+"' onchange='changeFlag(this.value,"+ i +")'> <option value='0'>请选择</option><option value='1'>正常</option>	<option value='2'>异常</option></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='medicalRemark"+i+"' style='visibility: visible'>备注: </span><textarea name='remarkText"+i+"' style='height:25px;width:300px;visibility: visible;' type='_moz' onfocus=\"this.style.height='50px';\" onblur=\"this.style.height='20px';\"></textarea><span id='medicalResult"+i+"' style='visibility: hidden'>体检异常处理结果: </span><textarea name='remark"+i+"' style='height:25px;width:300px;visibility: hidden;' type='_moz' onfocus=\"this.style.height='50px';\" onblur=\"this.style.height='20px';\"></textarea>";
	
	document.form1.iindex.value = Number(document.form1.iindex.value) + 1;
}

	
tableUtil.deleteRow = function(){
	document.form1.iindex.value = Number(document.form1.iindex.value) - 1;
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
	    ///alert(flagId);
	    document.all('medicalResult'+i).style.visibility = 'visible';
		document.form1('remark'+i).style.visibility = 'visible';
		///alert('ee');
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

function job(num){
  var jobHealth = "jobHealth"+num;
	if(document.getElementById(jobHealth).value == '请输入体检单位'){
		document.getElementById(jobHealth).value="";
	}

}

function Save()
{
	i = Number(document.form1.iindex.value);

	for(var z=0 ; z<i ; z++){
		
		if(document.form1('medicalDate'+z) != null && document.form1('medicalDate'+z).value == ""){
			alert("请填写体检日期");
			return;
		}
	}
	for(var z=0 ; z<i ; z++){
		if(document.form1('medicalflag'+z) != null && document.form1('medicalflag'+z).value == "0"){
			alert("请选择体检结果");
			return;
		}
	}
	for(var z=0 ; z<i ; z++){
		if((document.form1('jobHealth'+z) != null && document.form1('jobHealth'+z).value == "") || document.form1('jobHealth'+z).value == '请输入体检单位'){
			alert("请输入体检单位");
			return;
		}
		if((document.form1('medicalDate'+z) != null && document.form1('medicalDate'+z).value == "") || document.form1('medicalDate'+z).value == "请输入体检时间"){
			alert("请输入体检时间");
			return;
		}
	}
	
	for(var z=0 ; z<i ; z++){
		if(document.form1('medicalflag'+z) != null && document.form1('medicalflag'+z).value == "2"){
			if(document.form1('remark'+z) != null && document.form1('remark'+z).value == ""){
				alert("请填写异常说明");
				return;
			}
			if(document.form1('remark'+z) != null && document.form1('remark'+z).value.length > 200){
				alert("异常说明不能超过200字");
				return;
			}
		}
	}
	
	if((document.form1.JOB_DISEASE_CODE == null || document.form1.JOB_DISEASE_TYPE_CODE == null) && (document.form1.diseaseCode.value == "" || document.form1.diseaseTypeCode.value == "")){
		alert("职业危害和职业病类型不能为空！请到岗位与职业病关系页面做设置！");
		return;
	}
	 var  workFlagAr=document.form1.gangwei;
	 for(var s=0;s<workFlagAr.length;s++){
		if(workFlagAr[s].checked==true){  	
				if(workFlagAr[s].value=="dangqian"){
					document.form1.action="/safeControlServlet?operation=jobHealth&content=updatePositionInformation&menu_code=${param.menu_code}&i="+i+"&JOB_DISEASE_CODE="+document.form1.diseaseCode.value+"&JOB_DISEASE_TYPE_CODE="+document.form1.diseaseTypeCode.value+"&changePositionFlag="+changePositionFlag+"&hgFlagTemp"+document.form1.hgFlagTemp.value+"&tempjobId="+document.form1.tempjobId.value+"&fromFor="+document.form1.fromFor.value;
					document.form1.submit();
				}else{
					document.form1.action="/safeControlServlet?operation=jobHealth&content=updatePositionInformation&menu_code=${param.menu_code}&i="+i+"&JOB_DISEASE_CODE="+document.form1.diseaseCode.value+"&JOB_DISEASE_TYPE_CODE="+document.form1.diseaseTypeCode.value+"&changePositionFlag="+changePositionFlag+"&hgFlagTemp"+document.form1.hgFlagTemp.value+"&tempjobId="+document.form1.tempjobId.value+"&fromFor="+document.form1.fromFor.value;
					document.form1.submit();
				}
		}
	}
}


function ChengeGangwei(huangang){  
	document.form1.action="/safeControlServlet?operation=jobHealth&content=updatejobHealthView&menu_code=${param.menu_code}&huangangFlag="+huangang+"&EMPID="+document.form1.EMPID.value;
	document.form1.submit();
   
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
		
		<input type="hidden" name="tempjobId" value="${tempjobId }"/>
		
		<input type="hidden" name="hgFlagTemp" value="${huangangFlag }"/>
		
		<input type="hidden" name="EMPID" value="${empId}"/>
		
		<input type="hidden" name="iindex" value="${allMedicalInformationSize}"/>
		<input type="hidden" name="fromFor" value="${allMedicalInformationSize}"/>
		<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="temp" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">岗位信息修改</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td width="20%" class="info_title_01">职业健康编号</td>
				<td class="info_content_00"><input name="DOCUMENTNO" value="${DOCUMENTNO}" type="text">			
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">
		      		姓名/职号
		      	</td>
      		    <td class="info_content_00"><!-- 请输入姓名查找 -->
	          		<input id="empName" name="empName" size="8" value="${empName}" readonly="readonly"/>
       				<input type="text" id="empID" name="empID" size="8" value="${empId}"  
       				required readonly="readonly"/>
	            </td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">部门</td>
				<td class="info_content_00" id="dept">
					${deptName}&nbsp;
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">出生日期</td>
				<td class="info_content_00" id="birth_ymd">
				${birth_ymd}
				&nbsp;
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">入社日期</td>
				<td class="info_content_00" id="joinDate">
					${dateStarted}&nbsp;
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">岗位</td>
				<td class="info_content_00">
					<select name="Position" onchange="selectPOSITION(this.value);selectDiseaseType(this.value);">
						<c:forEach items="${POSITION_INFORMATION}" var="All" varStatus="j">
	 						<c:choose>
					         	<c:when test="${All.RELATIONS_ID==positionCode}">
					         		<option value="${All.RELATIONS_ID}" selected>${All.CODE_NAME}</option>
					         	</c:when>
					         	<c:otherwise>
					         		<option value="${All.RELATIONS_ID}">
					         			${All.CODE_NAME}
					         		</option>
					         	</c:otherwise>
					       </c:choose>
				       </c:forEach>
 					</select>
 					&nbsp;
 					<input type="radio" name="gangwei" value="dangqian" onclick="ChengeGangwei(this.value)" <c:if test="${huangangFlag=='dangqian'}"> checked="checked"</c:if> />修改当前岗位
 					<input type="radio" name="gangwei" value="huangang" onclick="ChengeGangwei(this.value)" <c:if test="${huangangFlag=='huangang'}"> checked="checked"</c:if> />换岗
				</td>
			</tr>
			<%
				if(huangangFlag.equals("dangqian")){
			%>
			<tr>
				<td width="10%" class="info_title_01">职业危害</td>
				<td class="info_content_00">
					<span id="disease">${disease}</span>
					<input type="hidden" name="diseaseCode" value="${diseaseCode}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">职业病类型</td>
				<td class="info_content_00">
					<span id="diseasetype">${diseaseType}</span>
					<input type="hidden" name="diseaseTypeCode" value="${diseaseTypeCode}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">从事本岗时间</td>
				<td class="info_content_00">
					<input type="text" name="StartDate" value="${startTime}" onClick="setday(this);" readonly="readonly">
				</td>
			</tr>
			<input type="hidden" name="jindex" value="${allMedicalInformationSize}"/>
			<tr>
					<td width="20%" class="info_title_01">
					<img src="../images/btn/Add.gif" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
					<td class="info_content_00">
					&nbsp;
					</td>
				</tr>
			<c:forEach items="${allMedicalInformation}" var="all" varStatus="j">
			
				<tr>
					<td width="20%" class="info_title_01">体检结果
						<input type='radio' name='rowNum'/>
					</td>
					<td class="info_content_00">
					<input type="text" id="jobHealth${j.index}" name="jobHealth${j.index}" value="${all.MEDICAL_UNIT }">
						<input type="text" id="medicalDate${j.index}" name="medicalDate${j.index}" value="${all.MEDICAL_YEAR}" onClick="setday(this);" readonly="readonly">&nbsp;
						
						<select name="medicalstateflag${j.index}" id="medicalstateflag${j.index}">
							<option value="0" <c:if test="${all.MEDICAL_STATE=='请选择'}">selected="selected"</c:if>>请选择</option>
							<option value="come" <c:if test="${all.MEDICAL_STATE=='入岗体检'}">selected="selected"</c:if>>入岗体检</option>
							<option value="on" <c:if test="${all.MEDICAL_STATE=='在岗体检'}">selected="selected"</c:if>>在岗体检</option>
							<option value="out" <c:if test="${all.MEDICAL_STATE=='离岗体检'}">selected="selected"</c:if>>离岗体检</option>
						</select>
						
						
						<select name="medicalflag${j.index}" id="medicalflag${j.index}" onchange="changeFlag(this.value,${j.index});">
							<option value="0" <c:if test="${all.MEDICAL_FLAG=='请选择'}">selected="selected"</c:if>>请选择</option>
							<option value="1" <c:if test="${all.MEDICAL_FLAG=='正常'}">selected="selected"</c:if>>正常</option>
							<option value="2" <c:if test="${all.MEDICAL_FLAG=='异常'}">selected="selected"</c:if>>异常</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${all.MEDICAL_FLAG=='异常'}">
							<span id="medicalResult${j.index}" style="visibility: visible;">体检异常处理结果:</span>
							<textarea id="remarkId${j.index}" name="remark${j.index}" style="height:25px;width:300px;visibility: visible;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';">${all.MEDICAL_RESULT}</textarea>
							
							<span id="medicalRemark${j.index}" style="visibility: hidden;">备注:</span>
							<textarea id="remarkTextId${j.index}" name="remarkText${j.index}" style="height:25px;width:300px;visibility: hidden;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';">${all.MEDICAL_REMARK}</textarea>
						</c:if>	
						<c:if test="${all.MEDICAL_FLAG=='正常'}">
							<span id="medicalRemark${j.index}" style="visibility: visible;">备注:</span>
							<textarea id="remarkTextId${j.index}" name="remarkText${j.index}" style="height:25px;width:300px;visibility: visible;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';">${all.MEDICAL_REMARK}</textarea>
							<span id="medicalResult${j.index}" style="visibility: hidden;">体检异常处理结果:</span>
							<textarea id="remarkId${j.index}" name="remark${j.index}" style="height:25px;width:300px;visibility: hidden;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';">${all.MEDICAL_RESULT}</textarea>
						</c:if>		
									
					</td>
				</tr>
			</c:forEach>
			<%
				}else if(huangangFlag.equals("huangang")){
			%>
			<tr>
				<td width="10%" class="info_title_01">职业危害</td>
				<td class="info_content_00">
					<span id="disease">${disease}</span>
					<input type="hidden" name="diseaseCode" value="${diseaseCode}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">职业病类型</td>
				<td class="info_content_00">
					<span id="diseasetype">${diseaseType}</span>
					<input type="hidden" name="diseaseTypeCode" value="${diseaseTypeCode}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">从事本岗时间</td>
				<td class="info_content_00">
					<input type="text" name="StartDate" value="${startTime}" onClick="setday(this);" readonly="readonly">
				</td>
			</tr>
			<input type="hidden" name="jindex" value="${allMedicalInformationSize}"/>
			<tr>
					<td width="20%" class="info_title_01">
					<img src="../images/btn/Add.gif" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
					<td class="info_content_00">
					&nbsp;
					</td>
				</tr>
					<tr>
					<td width="20%" class="info_title_01">体检结果
						<input type='radio' name='rowNum'/>
					</td>
					<td class="info_content_00">
					<input type="text" id="jobHealth0" name="jobHealth0" value="请输入体检单位" onmousedown='job(0)'>
						<input type="text" id="medicalDate0" name="medicalDate0" value="请输入体检时间" onClick="setday(this);" readonly="readonly">&nbsp;
						
						<select name="medicalstateflag0" id="medicalstateflag0">
							<option value="0">请选择</option>
							<option value="come">入岗体检</option>
							<option value="on">在岗体检</option>
							<option value="out">离岗体检</option>
						</select>
						
						
						<select name="medicalflag0" id="medicalflag0" onchange="changeFlag(this.value,0);">
							<option value="0">请选择</option>
							<option value="1">正常</option>
							<option value="2">异常</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						    <span id="medicalRemark0" style="visibility: hidden;">备注:</span>
							<textarea id="remarkTextId0" name="remarkText0" style="height:25px;width:300px;visibility: hidden;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';"></textarea>
							<span id="medicalResult0" style="visibility: hidden;">体检异常处理结果:</span>
							<textarea id="remarkId0" name="remark0" style="height:25px;width:300px;visibility: hidden;" type="_moz"  onfocus="this.style.height='50px';" onblur="this.style.height='20px';"></textarea>
							
					</td>
				</tr>
			<%
				}
			%>
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
