<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="visiterViewDetail1" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="visiterViewDetail2" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="visiterViewDetail3" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="ApplyId" class="java.lang.String" scope="request" />
<jsp:useBean id="ApplyName" class="java.lang.String" scope="request" />
<jsp:useBean id="ApplyCompany" class="java.lang.String" scope="request" />
<jsp:useBean id="ApplyDuty" class="java.lang.String" scope="request" />
<jsp:useBean id="ApplyCountry" class="java.lang.String" scope="request" />
<jsp:useBean id="visiterViewDetail1Int" class="java.lang.String" scope="request" />
<jsp:useBean id="visiterDate" class="java.lang.String" scope="request" />
<jsp:useBean id="hour" class="java.lang.String"
	scope="request" />
<jsp:useBean id="jiangjie" class="java.lang.String"
	scope="request" />
<jsp:useBean id="visit_item" class="java.lang.String"
	scope="request" />
<jsp:useBean id="file1" class="java.lang.String"
	scope="request" />
<jsp:useBean id="visiter_type" class="java.lang.String"
	scope="request" />
<jsp:useBean id="min" class="java.lang.String"
	scope="request" />
<jsp:useBean id="visiter_carnum" class="java.lang.String"
	scope="request" />
<jsp:useBean id="endhour" class="java.lang.String"
	scope="request" />
<jsp:useBean id="endmin" class="java.lang.String"
	scope="request" />

<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="device" class="java.lang.String" scope="request" />

<jsp:useBean id="VisiterCarNumber" class="java.lang.String"
	scope="request" />
<jsp:useBean id="contactTel" class="java.lang.String" scope="request" />
<jsp:useBean id="gift" class="java.lang.String" scope="request" />
<jsp:useBean id="giftNumber" class="java.lang.String" scope="request" />
<jsp:useBean id="apiLanguage" class="java.lang.String" scope="request" />
<jsp:useBean id="dept" class="java.lang.String" scope="request" />
<jsp:useBean id="playApply" class="java.lang.String" scope="request" />
<jsp:useBean id="ApplyObjective" class="java.lang.String"
	scope="request" />
<jsp:useBean id="visiterType" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="visiter_Id" class="java.lang.String" scope="request" />
<jsp:useBean id="visiterCountry" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allapiLanguage" class="java.util.ArrayList"
	scope="request" />
<jsp:useBean id="visiter_country" class="java.lang.String"
	scope="request" />
<jsp:useBean id="api_language_id" class="java.lang.String"
	scope="request" />
<jsp:useBean id="visiterManagementId" class="java.lang.String"   scope="request" />
<jsp:useBean id="chinesename" class="java.lang.String" scope="request" />
<jsp:useBean id="welcome" class="java.lang.String" scope="request" />
<jsp:useBean id="distiniction" class="java.lang.String" scope="request" />
<jsp:useBean id="distinictionid" class="java.lang.String" scope="request" />
<jsp:useBean id="allDistiniction" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="huiyishi_applyid" class="java.lang.String" scope="request" />
<jsp:useBean id="visiter_people_num" class="java.lang.String" scope="request"></jsp:useBean>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.ga.dao.visiter.*" %>
<jsp:useBean id="admin1" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" />
<jsp:useBean id="essSysparam" class="com.ait.sysparam.EssSysparam" scope="request" />


<jsp:useBean id="VISITER_CONFERENCE_LANGUAGE" class="java.lang.String" scope="request" />
<jsp:useBean id="VISITER_FACTORY_DEVICE" class="java.lang.String" scope="request" />
<jsp:useBean id="VISITER_FACTORY_LANGUAGE" class="java.lang.String" scope="request" />

<html>
<head>

<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/commFuncs.js"></script>
<script language="javascript">
<%  
if(request.getAttribute("alert") != null) {%>
  alert("<%=request.getAttribute("alert")%>");
<%}%>
<% GaAffirm  ga = new GaAffirm();
String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
	List list =ga.getAffirmor(adminId,"Visiter_Apply", essSysparam.isContainsOwner());
%>
function Save() {

	if(document.form1.visiterTime.value == ""){
		alert("请填写来访日期！");
		return;
	}
	if(document.form1.hour.value == "" || document.form1.min.value == "" || document.form1.Outhour.value == "" || document.form1.Outmin.value == ""){
		alert("请选择到达或者离开的具体时间！");
		return;
	}

	var selectDate = document.form1.visiterTime.value;
	var selectComeTime = document.form1.hour.value + document.form1.min.value;
	var selectOutTime = document.form1.Outhour.value + document.form1.Outmin.value ;		
	if(selectComeTime > selectOutTime){
		alert("到达时间不能小于离开时间!");
		return;
	}
	
	if(document.form1.visiterCountry.value == ""){
		alert("请选择国别！");
		return;
	}
	
	if(document.form1.peopleNum.value == ""){
		alert("请填写来访人数！");
		return;
	}
	
	if(document.form1.empName.value == ""){
		alert("请填写接待人员！");
		return;	
	}
	
///	if(document.form1.frontdeptID.value == ""){
///		alert("请选择接待部门！");
///		return;	
///	}
	
	j = Number(document.form1.temp1.value);
	
	for(var z=0 ; z<=j ; z++){
		if(document.form1('visiterName'+z) != null && document.form1["visiterName"+z].value == ""){
			alert("请填写姓名！");
			return;
		}
		
		if(document.form1('visiterCompany'+z) != null && document.form1["visiterCompany"+z].value == ""){
			alert("请填写单位！");
			return;
		}		
	}
	
///	if(document.form1.affirmor.value == '' || document.form1.affirmor == null){
///		 alert("没有设置决裁者！请设置");
///		 return;
///   	}
    if(confirm("是否确认修改？")){  
	document.form1.action="/puControlServlet?operation=visiterManagement&content=visiterManagementModify&menu_code=${param.menu_code}&returnFlag="+document.form1.returnFlag.value;
	document.form1.submit();
	}
}
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
    	var pars = "operation=GaSearchEmployee&condition=" + encodeURIComponent(condition);
    	
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
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

function showEmployeePartInformation(XmlHttpRequest){
    
    var dept = $('dept');
    var joinDate = $('joinDate');
    var info = XmlHttpRequest.responseText.split(",") ;
	dept.innerHTML= info[0];
    joinDate.innerHTML= info[1];
}

	var type = null;
	var tableUtil = new Object();
	var i=0;
tableUtil.appendRow = function(){	
		i = Number(document.form1.temp1.value);
		document.form1.temp1.value = i+1;
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterName"+i+"' style='width:70px' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterDuty"+i+"'  style='width:60px' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterCompany"+i+"'  style='width:70px' value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='Distinction"+i+"' > " + document.getElementById('Distinction0').innerHTML + "</select>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='ContactTel"+i+"'  style='width:150px' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='visiterGiftYesOrNo"+i+"' > " + document.getElementById('visiterGiftYesOrNo0').innerHTML + "</select>" ;
	    
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='text' name='GiftName"+i+"'  style='width:150pxS' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<input type='text' name='GiftNumber"+i+"'  style='width:150px' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='memo"+i+"' style='height: 25px;width:150px' type='_moz'  onfocus='this.style.height=50px' onblur='this.style.height=20px'></textarea>";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
		document.form1.temp1.value = Number(document.form1.temp1.value)- 1;
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


        var type2 = null;
	    var tableUtil2 = new Object();
	    var i2=0;

tableUtil2.appendRow = function(){	
        
		i2 = Number(document.form1.temp2.value);
		document.form1.temp2.value = i2+1;
		
		var nTr = document.getElementById('operateTable2').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='accommodationName"+i2+"' style='width:70px' value='' />";
	
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='accommodationStandard"+i2+"' > " + document.getElementById('accommodationStandard0').innerHTML + "</select>" ;
	
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='accommodationInDate"+i2+"'  class='content' readonly onClick='setday(this);' style='width:70px'>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='accommodationInHour"+i2+"' > " + document.getElementById('accommodationInHour0').innerHTML + "</select> : <select name='accommodationInMin"+i2+"' > " + document.getElementById('accommodationInMin0').innerHTML + "</select>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='accommodationOutDate"+i2+"'  class='content' readonly onClick='setday(this);' style='width:70px'>";
	
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='accommodationOutHour"+i2+"' > " + document.getElementById('accommodationOutHour0').innerHTML + "</select> : <select name='accommodationOutMin"+i2+"' > " + document.getElementById('accommodationOutMin0').innerHTML + "</select>" ;
		
	   
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='accommodationMemo"+i2+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum2' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil2.deleteRow = function(){
		document.form1.temp2.value = Number(document.form1.temp2.value)- 1;
		var radioArr = document.getElementsByName('rowNum2');
		var tbody = document.getElementById('operateTable2').tBodies[0];
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
	
var type3 = null;
	    var tableUtil3 = new Object();
	    var i3=0;
	
tableUtil3.appendRow = function(){	
        
		i3 = Number(document.form1.temp3.value);
		document.form1.temp3.value = i3+1;
		
		var nTr = document.getElementById('operateTable3').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='visiterEatryDistinction"+i3+"' > " + document.getElementById('visiterEatryDistinction0').innerHTML + "</select>" ;
	
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterEatryName"+i3+"'  value='' />";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterEatryDate"+i3+"'  class='content' readonly onClick='setday(this);' style='width:70px'>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='visiterEatryHour"+i3+"' > " + document.getElementById('visiterEatryHour0').innerHTML + "</select> : <select name='visiterEatryMin"+i3+"' > " + document.getElementById('visiterEatryMin0').innerHTML + "</select>" ;
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='visiterEatryType"+i3+"' > " + document.getElementById('visiterEatryType0').innerHTML + "</select>" ;
	
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterEatryNumber"+i3+"'  onkeyup='this.value=this.value.replace(/\\D/gi,\"\")'  />";
		
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='visiterEatryMemo"+i3+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum3' title='取消该行请选择后点击删除'/>";			
		
		
	}
	
	tableUtil3.deleteRow = function(){
		document.form1.temp3.value = Number(document.form1.temp3.value)- 1;
		var radioArr = document.getElementsByName('rowNum3');
		var tbody = document.getElementById('operateTable3').tBodies[0];
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
function uploadImp(photosid){
window.open("/ga/ga_visiter_upload.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}

function ifSelect2(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visiter_factory_device_'+i].checked){
			aa += document.form1['visiter_factory_device_'+i].value+",";
		}
	}
	document.form1.visiter_factory_device.value = aa;
	return;
}
function ifSelect1(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visiter_conference_language_'+i].checked){
			aa += document.form1['visiter_conference_language_'+i].value+",";
		}
	}
	document.form1.visiter_conference_language.value = aa;
	return;
}
function ifSelect3(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visiter_factory_language_'+i].checked){
			aa += document.form1['visiter_factory_language_'+i].value+",";
		}
	}
	document.form1.visiter_factory_language.value = aa;
	return;
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
		<td valign="TOP" align="CENTER"><!-- display content --> <br>
		<form name="form1" method="post" action=""><input type="hidden"
			name="visiterManagementId" value="${ApplyId}" />
			<input type="hidden" name=returnFlag value="${returnFlag}">
			<input type="hidden" name="playApply" value="${playApply}" />
			<input type="hidden" name="playApplyName" value="${playApplyName}" />
			<input type="hidden" name="temp" value="0">
			<input type="hidden" name="temp1" value="${visiterViewDetail1Int }">
			<input type="hidden" name="temp2" value="${visiterViewDetail2Int }">
			<input type="hidden" name="temp3" value="${visiterViewDetail3Int }">
			<input type="hidden" name="visiter_conference_language" id="visiter_conference_language" value="${VISITER_CONFERENCE_LANGUAGE }">
			<input type="hidden" name="visiter_factory_language" id="visiter_factory_language" value="${VISITER_FACTORY_LANGUAGE }">
			<input type="hidden" name="visiter_factory_device" id="visiter_factory_device" value="${VISITER_FACTORY_DEVICE }">	
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">参观者管理</td>
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_content_00">
				<input type="hidden" name="ApplyId" value="${ApplyId}">
				${ApplyId}</td>
				<td nowrap="nowrap" class="info_title_01">接待人员</td>
				<td nowrap="nowrap" class="info_content_00">
		          	<input id="empName" name="empName" style="width: 150px" value="<ait:content enContent='${chinesename}' zhContent='${chinesename}' koContent='${chinesename}'/>" 
		          		onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          				
          			<input type="hidden" id="person_id" name="person_id" value="<c:out value='${person_id}'/>"
		        </td>	
				<td nowrap="nowrap" class="info_title_01">来访日期</td>
				<td nowrap="nowrap"" class="info_content_00">
					<input type="text" name="visiterTime" value="${visiterDate}" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_title_01">到达时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="hour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh eq hour}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>               
			          </select>
			          :
			        <select name="min">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm eq min}">selected="selected" </c:if> >${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				
			</tr>
			<tr>
			    <td nowrap="nowrap" class="info_title_01">离开时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="Outhour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh == endhour}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="Outmin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == endmin}">selected="selected" </c:if> >${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				<td nowrap="nowrap" class="info_title_01">人员分类</td>
				<td nowrap="nowrap" class="info_content_00">
			 		<ait:codeClass codeClass="Visiter_Type" name="visiterType" selected="${visiter_type}" all="all"/>
			 	</td>
				<td nowrap="nowrap" class="info_title_01">国别</td>
				<td nowrap="nowrap" class="info_content_00">
					<ait:codeClass codeClass="Visiter_Country" name="visiterCountry" selected="${visiter_country}" all="all"/>
				</td>
				<td nowrap="nowrap" class="info_title_01">来访人数</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="peopleNum" style="width: 150px" value="${visiter_people_num }" onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
				</td>
				
				<tr>
				<td nowrap="nowrap" class="info_title_01">车牌号码</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarNumber" style="width:150px" value="${VisiterCarNumber }" /></td><!-- 车牌号码 -->
				<td nowrap="nowrap" class="info_title_01">车辆数</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarNum" style="width: 150px" value="${visiter_carnum }" /></td>	<!-- 车辆数 -->
				
				<td nowrap="nowrap" class="info_title_01">车型号</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarModel" style="width:150px" value="${VISITER_CAR_MODEL}" /></td>
				<td nowrap="nowrap" class="info_title_01">来访目的</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="Objective" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${ApplyObjective }</textarea>
				</td>	
				
				</tr>
				
		    </td>
		    </tr>
		</table>
		<%if(visiterViewDetail1.size()>0){ %>
		<br>	
		<br>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					参观者信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				<td nowrap="nowrap" class="info_title_01">职务</td>
				<td nowrap="nowrap" class="info_title_01">工作单位</td>
				<td nowrap="nowrap" class="info_title_01">区分</td>
				<td nowrap="nowrap" class="info_title_01">联系电话</td>
				<td nowrap="nowrap" class="info_title_01">礼品申请</td>
				<td nowrap="nowrap" class="info_title_01">礼品名称</td>
				<td nowrap="nowrap" class="info_title_01">礼品数量</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${visiterViewDetail1}" var="e" varStatus="j">
			<tr>	
				<td nowrap="nowrap" class="info_content_01">
				<input type="hidden" name="id${j.index }" style="width:70px" value="${e.ID }" />
				<input type="text" name="visiterName${j.index }" style="width:70px" value="${e.VIST_NAME }" /></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterDuty${j.index }" style="width:60px" value="${e.JOBS }" /></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterCompany${j.index }" style="width:70px" value="${e.CORPORATION }" /></td>
					
				<td nowrap="nowrap" class="info_content_01">
				<ait:codeClass codeClass="Visiter_Distiniction" name="Distinction${j.index }" selected="${e.DIFFER1}"/>
				</td>
				
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="ContactTel${j.index }" style="width:150px" value="${e.TELEPHONE }" /></td>
					
				<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass 	codeClass="YesOrNo" name="visiterGiftYesOrNo${j.index }" selected="${e.PRESENT_APPPLY}" all="all"/>
			    </td>		
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftName${j.index }" style="width:150px" value="${e.PRESENT }" /></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftNumber${j.index }" style="width:150px" value="${e.PRESENT_NUM }" /></td>
				<td nowrap="nowrap" class="info_content_01"><textarea name="memo${j.index }" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${e.MEMO }</textarea></td>
					
				<td nowrap="nowrap" class="info_content_01">
					<c:choose>
					<c:when test="${j.index eq '0'}">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow('${j }');" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow('${j }');" name="a1" id="a1">
					</c:when>
					<c:otherwise>
					<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%}%>
		<%if(visiterViewDetail3.size()>0){ %>
		<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					用餐信息
				</td>
			</tr>
		</table>
		<table id="operateTable3"  width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"  > 
			
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">区分</td>
				<td nowrap="nowrap" class="info_title_01">餐厅名称</td>
				<td nowrap="nowrap" class="info_title_01">用餐日期</td>
				<td nowrap="nowrap" class="info_title_01">用餐时间</td>
				<td nowrap="nowrap" class="info_title_01">餐别</td>
				<td nowrap="nowrap" class="info_title_01">用餐人数</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>	
			<c:forEach items="${visiterViewDetail3}" var="e" varStatus="j">
			<tr>
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterEatryDistinction${j.index}"	codeClass="Visiter_Eatery_Distiniction" selected="${e.VISITER_EATRY_DISTINCTION}" all="all" />
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterEatryName${j.index}"  value="${e.VISITER_EATRY_NAME }" />
			</td>
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="visiterEatryDate${j.index}" value="${e.VISITER_EATRY_DATE }" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
			
				<td nowrap="nowrap" class="info_content_01">
						<select name="visiterEatryHour${j.index}">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          	    <option value="${lhh}" <c:if test="${lhh == e.VISITER_EATRY_HOUR}">selected="selected" </c:if> >${lhh}</option>
			           	</c:forEach>
			          </select>
			          :
			        <select name="visiterEatryMin${j.index}">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          	    <option value="${lmm}" <c:if test="${lmm == e.VISITER_EATRY_MIN}">selected="selected" </c:if> >${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				
				
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterEatryType${j.index}"	codeClass="Visiter_Eatery_Type" selected="${e.VISITER_EATRY_TYPE}" all="all"/>
			</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterEatryNumber${j.index}" value="${e.VISITER_EATRY_NUMBER }" onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			</td>
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterEatryMemo${j.index}"   style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${e.VISITER_EATRY_MEMO}</textarea>
					</td>
			<td nowrap="nowrap" class="info_content_01">
					<c:choose>
					<c:when test="${j.index eq '0'}">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil3.appendRow('${j }');" name="a3" id="a3">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil3.deleteRow('${j }');" name="a3" id="a3">
					</c:when>
					<c:otherwise>
					<input type='radio' name='rowNum3' title='取消该行请选择后点击删除'/>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%} %>
		
		<%if(visiterViewDetail2.size()>0){ %>
		<br>	
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					住宿信息
				</td>
			</tr>
		</table>
		<table id = 'operateTable2' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >	

			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">人员姓名</td>
				<td nowrap="nowrap" class="info_title_01">住宿标准</td>
				<td nowrap="nowrap" class="info_title_01">入住日期</td>
				<td nowrap="nowrap" class="info_title_01">入住时间</td>
				<td nowrap="nowrap" class="info_title_01">退房日期</td>
				<td nowrap="nowrap" class="info_title_01">退房时间</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${visiterViewDetail2}" var="e" varStatus="j">
			
			<tr>			
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="accommodationName${j.index}" style="width:70px" value="${e.ACCOMMODATION_NAME}" /></td>
				<td nowrap="nowrap" class="info_content_01">
			     <ait:codeClass  name="accommodationStandard${j.index}"	codeClass="Visiter_Accommodation_Standard" selected="${e.ACCOMMODATION_STANDARD}"  all="all"/>
			    </td>
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="accommodationInDate${j.index}" value="${e.ACCOMMODATION_IN_DATE}" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<select name="accommodationInHour${j.index}">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          	      <option value="${lhh}" <c:if test="${lhh == e.ACCOMMODATION_IN_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="accommodationInMin${j.index}">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == e.ACCOMMODATION_IN_MIN}">selected="selected" </c:if> >${lmm}</option> 
			          	</c:forEach>
		            </select>
				</td>	
				
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="accommodationOutDate${j.index}" value="${e.ACCOMMODATION_OUT_DATE}" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<select name="accommodationOutHour${j.index}">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		  <option value="${lhh}" <c:if test="${lhh == e.ACCOMMODATION_OUT_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="accommodationOutMin${j.index}">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == e.ACCOMMODATION_OUT_MIN}">selected="selected" </c:if> > ${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>	
				
				
				
			
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="accommodationMemo${j.index}"  style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${e.ACCOMMODATION_MEMO}</textarea>
					</td>
					
				
				<td nowrap="nowrap" class="info_content_01">
					<c:choose>
					<c:when test="${j.index eq '0'}">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil2.appendRow('${j }');" name="a2" id="a2">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil2.deleteRow('${j }');" name="a2" id="a2">
					</c:when>
					<c:otherwise>
					<input type='radio' name='rowNum2' title='取消该行请选择后点击删除'/>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</table>
		<%} %>
	    <c:if test="${VISITER_VOITURE_FLAG eq true}"> 
		<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					车辆信息	</td>
			</tr>
		</table>
		<table id = "visiterVoitureTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			<tr>
			
			
			<td nowrap="nowrap" class="info_title_01">人数</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureNumber" value="${VISITER_VOITURE_NUMBER}" onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01">出发日期</td>
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterVoitureInDate" value="${VISITER_VOITURE_IN_DATE}"  class="content" readonly onClick="setday(this);" style="width:70px">
			</td>
			
			<td nowrap="nowrap" class="info_title_01">出发时间</td>
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterVoitureInHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh == VISITER_VOITURE_IN_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterVoitureInMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == VISITER_VOITURE_IN_MIN}">selected="selected" </c:if> > ${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_title_01">预计到达日期</td>
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterVoitureOutDate" value="${VISITER_VOITURE_OUT_DATE}" class="content" readonly onClick="setday(this);" style="width:70px">
			</td>
			 <td nowrap="nowrap" class="info_title_01">
				车辆使用动态</td>
			<td align="center" class="info_content_01"><a href="#" onclick="view('1')"><font color="red">点击查看</font></a></td>
			</tr>
			<tr>
			<td nowrap="nowrap" class="info_title_01">预计到达时间</td>
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterVoitureOutHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh == VISITER_VOITURE_OUT_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterVoitureOutMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == VISITER_VOITURE_OUT_MIN}">selected="selected" </c:if> > ${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_title_01">出发地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureStartArea"  value="${VISITER_VOITURE_START_AREA}" />
			</td>
			<td nowrap="nowrap" class="info_title_01">途经地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoiturePassArea"  value="${VISITER_VOITURE_PASS_AREA}" />
			</td>
			<td nowrap="nowrap" class="info_title_01">最终目的地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureEndArea"  value="${VISITER_VOITURE_END_AREA}" />
			</td>
            <td nowrap="nowrap" class="info_title_01">备注</td>
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterVoitureMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${VISITER_VOITURE_MEMO}</textarea>
					</td>
			
			</tr>
		</table>
		</c:if>
		
		 <c:if test="${VISITER_CONFERENCE_FLAG eq true}"> 
			<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					参观者接待</td>
			</tr>
		</table>
		<table id = "visiterConferenceTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			<tr>
			<td nowrap="nowrap" class="info_title_01">接待室</td>
			<td nowrap="nowrap" class="info_content_01">
      		<select name="visiterConferenceRoom">
				<option value="0">请选择</option>
				<c:forEach items="${roomname}" var="roomname" varStatus="i">
					<option value="${roomname.ID}" <c:if test="${roomname.ID == VISITER_CONFERENCE_ROOM}">selected="selected" </c:if> >${roomname.ROOMNAME}</option>
				</c:forEach>
			</select></td>
			<td nowrap="nowrap" class="info_title_01" >工厂介绍资料</td>
		
				<td nowrap="nowrap" class="info_content_00">	
				
	      			<%String conferencLangageStr[] = VISITER_CONFERENCE_LANGUAGE.split(",");
	      			
	      			SimpleMap parameterObject3 = new SimpleMap();
	      			VisiterDAO visiterDAO3 = new VisiterDAO();
	      			
	      			
	      			
	      			List list3 = (List)visiterDAO3.allLanguage(parameterObject3);
	      			int size = list3.size()-1;
	      			for(int y=0;y<list3.size();y++){
	      					boolean falg= true;
	      					SimpleMap listobj = (SimpleMap)list3.get(y);
	      					for(int x=0;x<conferencLangageStr.length;x++){
	      						if(listobj.getString("CODE_NAME").equals(conferencLangageStr[x])){
	      							falg = false;
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_conference_language_<%=y %>" checked="checked" name="visiter_conference_language_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect1('<%=size %>')"/>
		      				<%break;
	      					}
	      					}
	      					if(falg){
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_conference_language_<%=y %>" name="visiter_conference_language_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect1('<%=size %>')"/>
		      				<%
	      					}
	      				}
	      			
	      			%>
				</td>
			
		
			
				
		   
			<td nowrap="nowrap" class="info_title_01" >讲解人</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterConferencePeople"  value="${VISITER_CONFERENCE_PEOPLE}" />
			</td>
			
			 <td nowrap="nowrap" class="info_title_01">
				会议室使用动态</td>
			<td nowrap="nowrap" class="info_content_01"><span style="color:red; cursor:pointer;" onclick="viewConferenceRoomUsing()">点击查看</span></td>
			</tr>
			
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >与总经理见面</td>	
		
			<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterConferenceManageYesOrNo"	codeClass="YesOrNo" selected="${VISITER_CONFERENCE_MANAGE}" all="all"/>
			</td>
			<td nowrap="nowrap" class="info_title_01" >日期</td>	
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterConferenceDate"  class="content"  value="${VISITER_CONFERENCE_DATE}" readonly onClick="setday(this);" style="width:70px">
			</td>
			<td nowrap="nowrap" class="info_title_01" >时间</td>	
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterConferenceHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh == VISITER_CONFERENCE_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterConferenceMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}" <c:if test="${lmm == VISITER_CONFERENCE_MIN}">selected="selected" </c:if> > ${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_title_01" >目的</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterConferencePurpose"  value="${VISITER_CONFERENCE_PURPOSE}" />
			</td>
			
			
			</tr>
		</table>
		</c:if>
		
		<c:if test="${VISITER_FACTORY_FLAG eq true}"> 
			<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					工厂参观
				</td>
			</tr>
		</table>
		<table id = "visiterFactoryTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >参观人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryNumber" value="${VISITER_FACTORY_NUMBER}"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
		
			
			<td nowrap="nowrap" class="info_title_01" >参观方法</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryMethod"	codeClass="Visiter_Method" selected="${VISITER_FACTORY_METHOD}" all="all"/>
			</td>
			<td nowrap="nowrap" class="info_title_01" >参观路线</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryRoute"	codeClass="Visiter_Route" selected="${VISITER_FACTORY_ROUTE}" all="all"/>
			</td>
			</tr>
			
			<tr>
				<td nowrap="nowrap" class="info_title_01" >试听设备</td>
				<td nowrap="nowrap" class="info_content_00">	
				
	      			<%String factoryDeviceStr[] = VISITER_FACTORY_DEVICE.split(",");
	      			
	      			SimpleMap parameterObject4 = new SimpleMap();
	      			VisiterDAO visiterDAO4 = new VisiterDAO();

	      			List list4 = (List)visiterDAO4.allDevice(parameterObject4);
	      			int size1 = list4.size()-1;
	      			for(int y=0;y<list4.size();y++){
	      					boolean falg= true;
	      					SimpleMap listobj = (SimpleMap)list4.get(y);
	      					for(int x=0;x<factoryDeviceStr.length;x++){
	      						if(listobj.getString("CODE_NAME").equals(factoryDeviceStr[x])){
	      							falg = false;
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_factory_device_<%=y %>" checked="checked" name="visiter_factory_device_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect2('<%=size1 %>')"/>
		      				<%break;
	      					}
	      					}
	      					if(falg){
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_factory_device_<%=y %>" name="visiter_factory_device_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect2('<%=size1 %>')"/>
		      				<%
	      					}
	      				}
	      			
	      			%>
				</td>
				
				<td nowrap="nowrap" class="info_title_01" >安全帽</td>	
		
				<td nowrap="nowrap" class="info_content_01">
				    <ait:codeClass name="visiterFactoryHelmetYesOrNo"	codeClass="YesOrNo" selected="${VISITER_FACTORY_HELMET}" all="all"/>
				</td>
				<td nowrap="nowrap" class="info_title_01" >数量</td>	
				<td nowrap="nowrap" class="info_content_01">
			    <input type="text" name="visiterFactoryHelmetNumber" value="${VISITER_FACTORY_HELMET_NUMBER}" onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			    </td>
			</tr>	
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >导览系统</td>	
						
				<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterFactorySystemYesOrNo"	codeClass="YesOrNo" selected="${VISITER_FACTORY_SYSTEM}" all="all"/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01" >中文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryCNumber" value="${VISITER_FACTORY_C_NUMBER}"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			<td nowrap="nowrap" class="info_title_01" >韩文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryKNumber" value="${VISITER_FACTORY_K_NUMBER}"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			<td nowrap="nowrap" class="info_title_01" >英文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryENumber" value="${VISITER_FACTORY_E_NUMBER}"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			
			</tr>
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >陪同人员</td>	
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryPeople"  value="${VISITER_FACTORY_PEOPLE}" />
			</td>
			<td nowrap="nowrap" class="info_title_01" >大厅欢迎词</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryWelcomeSpeech"	codeClass="Visiter_Welcome_Speech" selected="${VISITER_FACTORY_WELCOME_SPEECH}" all="all"/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01" >欢迎词语言</td>
		
		    <td nowrap="nowrap" class="info_content_00">	
				
	      			<%String factoryLanguageStr[] = VISITER_FACTORY_LANGUAGE.split(",");
	      			
	      			SimpleMap parameterObject5 = new SimpleMap();
	      			VisiterDAO visiterDAO5 = new VisiterDAO();

	      			List list5 = (List)visiterDAO4.allLanguage(parameterObject5);
	      			int size2 = list5.size()-1;
	      			for(int y=0;y<list5.size();y++){
	      					boolean falg= true;
	      					SimpleMap listobj = (SimpleMap)list5.get(y);
	      					for(int x=0;x<factoryLanguageStr.length;x++){
	      						if(listobj.getString("CODE_NAME").equals(factoryLanguageStr[x])){
	      							falg = false;
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_factory_language_<%=y %>" checked="checked" name="visiter_factory_language_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect3('<%=size2 %>')"/>
		      				<%break;
	      					}
	      					}
	      					if(falg){
	      						%>
		      					<%=listobj.getString("CODE_NAME") %><input type="checkbox" id="visiter_factory_language_<%=y %>" name="visiter_factory_language_<%=y %>" value="<%=listobj.getString("CODE_NAME") %>" onclick="ifSelect3('<%=size2 %>')"/>
		      				<%
	      					}
	      				}
	      			
	      			%>
				</td>
					
			<td nowrap="nowrap" class="info_title_01" >其他</td>	
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterFactoryMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${VISITER_FACTORY_MEMO}</textarea>
			</td>
			
			</tr>
		</table>
		</c:if>
		<c:if test="${VISITER_FACTORY_FLAG eq true}"> 
		<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					观光旅游
				</td>
			</tr>
		</table>
		<table id = "visiterTourismTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" >
			
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">观光旅游</td>
				<td nowrap="nowrap" class="info_title_01">日期</td>
				<td nowrap="nowrap" class="info_title_01">时间</td>
				<td nowrap="nowrap" class="info_title_01">景点</td>
				<td nowrap="nowrap" class="info_title_01">费用负担</td>
				<td nowrap="nowrap" class="info_title_01">备注</td>
			</tr>	
			<tr>
			
			<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterTourismYesOrNo"	codeClass="YesOrNo" selected="${VISITER_TOURISM}"  all="all"/>
			</td>
			
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterTourismDate" value="${VISITER_TOURISM_DATE}"  class="content" readonly onClick="setday(this);" style="width:70px">
			</td>
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterTourismHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}" <c:if test="${lhh == VISITER_TOURISM_HOUR}">selected="selected" </c:if> >${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterTourismMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          			<option value="${lmm}" <c:if test="${lmm == VISITER_TOURISM_MIN}">selected="selected" </c:if> > ${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterTourismType"	codeClass="Visiter_Tourism" selected="${VISITER_TOURISM_TYPE}"  all="all"/>
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterTourismFee"  value="${VISITER_TOURISM_FEE}"  />
			</td>
		

			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterTourismMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';">${VISITER_TOURISM_MEMO}</textarea>
					</td>
			
			</tr>
		</table>
		</c:if>
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="10">
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
<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
</div>
</html>
