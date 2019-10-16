<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="applyId" class="java.lang.String" scope="request" />
<jsp:useBean id="apiLanguage" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="sysDate" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptNAME" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptID" class="java.lang.String" scope="request" />
<jsp:useBean id="allVisiterCountry" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" />
<jsp:useBean id="dept" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allDevice" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allVisiterDistiniction" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*" %>
<html>

<head>
<!-- ga_visiter_apply.jsp -->
<script src="../js/prototype.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>参观者申请页面</title>
</head>
<% GaAffirm  ga = new GaAffirm();
EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
	List list =ga.getAffirmor1(admin.getAdminID(),"Visiter_Apply",essSysparam.isContainsOwner());
%>
<SCRIPT type="text/javascript">

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
		i = Number(document.form1.temp1.value)+1;
		document.form1.temp1.value = i;
		
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
		td.innerHTML = "<select name='Distinction"+i+"' > " + document.getElementById('Distinction0').innerHTML + "</select>" +"<span style='color:red; cursor:pointer;' onclick='viewVisiterLevel()'>点击查看</span>";
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='ContactTel"+i+"'  style='width:150px' value='' />";
		
//		td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//		td.innerHTML = "<select name='visiterGiftYesOrNo"+i+"' > " + document.getElementById('visiterGiftYesOrNo0').innerHTML + "</select>" ;
	    
//	    td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//	    td.innerHTML = "<input type='text' name='GiftName"+i+"'  style='width:150pxS' value='' />";
	    
//	    td = nTr.insertCell() ;
//		td.className = "info_content_01" ;
//	    td.innerHTML = "<input type='text' name='GiftNumber"+i+"'  style='width:150px' value='' />";
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='memo"+i+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
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
        
		i2 = Number(document.form1.temp2.value)+1;
		document.form1.temp2.value = i2;
		
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
        
		i3 = Number(document.form1.temp3.value)+1;
		document.form1.temp3.value = i3;
		
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
	
	
function Save() {
    
	if(document.form1.visiterTime.value == ""){
		alert("请填写来访日期！");
		return;
	}
	if(document.form1.empName.value == ""){
		alert("请填写接待人员！");
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
	
	
	
	///if(document.form1.frontdeptID.value == ""){
	///	alert("请选择接待部门！");
	///	return;	
	///}
	
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
	
	if(document.form1.affirmor.value == '' || document.form1.affirmor == null){
		 alert("没有设置决裁者！请设置");
		 return;
   	}
   	
   	k = Number(document.form1.temp2.value);
   	s = Number(document.form1.temp3.value);
   	if(confirm("是否确认申请？")){  
	document.form1.action="/gaControlServlet?operation=visiterApplications&content=visiterApplicationsSave&menu_code=${param.menu_code}&flag="+j+"&flag2="+k+"&flag3="+s;
	document.form1.submit();
	}
	document.getElementById("applysaveid").style.display="none";//避免重复提交，隐藏按钮
}

function ifSelect3(tempSize){
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
function ifSelectPian(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['pian_language_'+i].checked){
			aa += document.form1['pian_language_'+i].value+",";
		}
	}
	document.form1.pian_language.value = aa;
	return;
}
function ifSelect2(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visiter_factory_language_'+i].checked){
			aa += document.form1['visiter_factory_language_'+i].value+",";
		}
	}
	document.form1.visiter_factory_language.value = aa;
	return;
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
 
function jumpHuiYiShi(){
	window.open ('/gaControlServlet?menu_code=ga0105&operation=conferenceRoom&content=conferenceRoomApplyPage&visiterNo='+document.form1.applyId.value+'&temp=visiter',
	 '会议室申请', 'height=500, width=1015, top=40, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=yes') 
}

function viewConferenceRoomUsing(){
   ///document.form1.isView.value="He or She had viewing";
   window.open("/ga/ga_conferenceRoomUsing_view.jsp","","resizable,scrollbars,dependent,width=700,height=400,left=250,top=300");
 }

function viewVisiterLevel(){
   ///document.form1.isView.value="He or She had viewing";
   window.open("/ga/ga_visiter_level_view.jsp","","resizable,scrollbars,dependent,width=700,height=400,left=250,top=300");
 } 
 
 function view(id){
	document.form1.action="gaControlServlet?menu_code=${param.menu_code}&operation=bookingVoiture&content=view&applyNo="+id;
	document.form1.submit();
}

function uploadImp(photosid){
window.open("/ga/ga_visiter_upload.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
function hiddentable(hiddenid,checkvalue){

 var cv = document.getElementById(checkvalue).checked;
 var ht = document.getElementById(hiddenid);

 if(cv == true){
 	ht.style.display = ""
 }else{
    ht.style.display = "none"
 }
 
 ///ht.style.display = ht.style.display == "none"?"":"none"
 
}

</SCRIPT>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="visiter_factory_device" value="">
<input type="hidden" name="visiter_conference_language" value="">
<input type="hidden" name="pian_language" value="">
<input type="hidden" name="visiter_factory_language" value="">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="temp1" value="0">
<input type="hidden" name="temp2" value="0">
<input type="hidden" name="temp3" value="0">
<input type="hidden" name="sysDate" value="${sysDate}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">
					参观者申请<font color="red" size="2">${declaration}</font>
				</td>
<!--				<td align="right" colspan="10">
					<span onclick="jumpHuiYiShi()" style="cursor: pointer;"><font color="red">申请会议室</font></span>
				</td>-->
			</tr>
		</table>
		<%
		if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
		}
		%>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_content_00"><input type="hidden"
					name="applyId" style="width:80px" readonly="readonly"
					value="${applyId}" />${applyId}</td>
				<td nowrap="nowrap" class="info_title_01">接待人员</td>
				<td nowrap="nowrap" class="info_content_00">
		          	<input id="empName" name="empName" style="width: 150px" value="<ait:content enContent='${chinesename}' zhContent='${chinesename}' koContent='${chinesename}'/>" 
		          		onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          				
          			<input type="hidden" id="person_id" name="person_id" value="<c:out value='${person_id}'/>"/>
		        </td>		
				<td nowrap="nowrap" class="info_title_01">来访日期</td>
				<td nowrap="nowrap"" class="info_content_00">
					<input type="text" name="visiterTime" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_title_01">到达时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="hour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="min">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				
			</tr>
			<tr align="center" bgcolor="#F5F5F5">
			    <td nowrap="nowrap" class="info_title_01">离开时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<!--<input type="text" name="visiterOutTime" value="" class="content" readonly onClick="setday(this);" style="width:70px">-->
					<select name="Outhour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="Outmin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				<td nowrap="nowrap" class="info_title_01">人员分类</td>
				<td nowrap="nowrap" class="info_content_00">
				 	<select name="visiterType">
				 			<option value="">
								请选择
							</option>
						<c:forEach items="${visiterType}" var="allType" varStatus="i">
							<option value="${allType.CODE_ID}">
								${allType.CODE_NAME }
							</option>
						</c:forEach>
					</select>
			 	</td>
				<td nowrap="nowrap" class="info_title_01">国别</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="visiterCountry">
						<option value="">
							请选择
						</option>
						<c:forEach items="${allVisiterCountry}" var="all" varStatus="i">
							<option value="${all.CODE_ID}">
								${all.CODE_NAME}
							</option>
						</c:forEach>
					</select>
				</td>
				<td nowrap="nowrap" class="info_title_01">来访人数</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="peopleNum" style="width:150px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')" value="" /></td><!-- 车牌号码 -->
				
				</tr>
				<tr>
				<td nowrap="nowrap" class="info_title_01">车牌号码</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarNum" style="width:150px" value="" /></td>	<!-- 车辆数 -->
				<td nowrap="nowrap" class="info_title_01">车辆数</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="visiterCarNumber" style="width:150px" value="1" onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
				</td>
				<td nowrap="nowrap" class="info_title_01">车型号</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarModel" style="width:150px" value="" /></td>	<!-- 车辆型号 -->
				<td nowrap="nowrap" class="info_title_01">来访目的</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="Objective" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
					
				
				</tr>
				<!-- 
				<tr>
				<td nowrap="nowrap" class="info_title_01" style="width: 180px">使用设备</td>
				<td nowrap="nowrap" class="info_content_00">					
					<c:forEach items="${allDevice}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="checkbox_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect(${i.index})"/>
	      			</c:forEach>
				</td>
				
				<td nowrap="nowrap" class="info_title_01">接待部门</td>
				<td nowrap="nowrap" class="info_content_00">
				<ait:selDept name="frontdeptID" supervisorType="pa" all="all" selected="${DeptID}" />
				</td>
				<td nowrap="nowrap" class="info_title_01">欢迎词</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="Welcome" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				<td nowrap="nowrap" class="info_title_01">宣传片语种</td>
				
				<td nowrap="nowrap" class="info_content_00"><select
					name="api_language">
					<option value="无">请选择</option>
					<c:forEach items="${apiLanguage}" var="apiLanguage" varStatus="i">
						<option value="${apiLanguage.CODE_ID}">
						${apiLanguage.CODE_NAME}</option>
					</c:forEach>
				</select></td>
				
				<td nowrap="nowrap" class="info_title_01">上传扫描件</td>	
				<td nowrap="nowrap" info_content_00>
			    	<a href="#" onclick="uploadImp('${applyId}')" style="color:red" title="上传图片">上传扫描文件</a>
			    </td>	
			</tr>
			 -->
			<tr>
				<!-- 
				<td nowrap="nowrap" class="info_title_01">会议室编号</td>
				<td nowrap="nowrap" class="info_content_00">
			    	<input type="text" style="width:150px" name="huiyishiApplyId" value=""/>
			    </td>
			    <td nowrap="nowrap" class="info_title_01">讲解</td>
		    <td nowrap="nowrap" class="info_content_00">
		    	<select name="jiangjie">
		    	<option value="1">讲解</option>
		    	<option value="2">不讲解</option>
		    	</select>
		    </td> -->
				<td nowrap="nowrap" class="info_title_01">决裁者</td>				
				<td nowrap="nowrap" class="info_content_00" id="affirmBy0" colspan="1">				
			      <%if(!list.isEmpty()){%>
			      <input type="hidden" value="<%=list.size()%>" name="affirmor">
			      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

				      <%int affirmLevel=10;
				      for(int i=0;i<list.size();i++){
				      gaAffirmList=(GaAffirmList)list.get(i);%> 
				    <tr>
				      <td>

				      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
				      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
					<%
					 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
					 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
					 		affirmLevel=gaAffirmList.getAffirmLevel();
					 	}
					
						if (gaAffirmList.getAffirmLevel()<affirmLevel){
					 %>
				      <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    
					<%
						}
					%>	
				      </td>
			      </tr>
			      <%
				      }
					%>	
		      </table> 
			      <%}else{ %>
			      <input type="hidden" value="" name="affirmor">
			      <table>
			      <tr><td nowrap="nowrap"><font color="red">没有决裁者</font></td></tr>		      
			      </table>
			      <% }%>		      
			    </td>
			    <td nowrap="nowrap" class="info_title_01">参观法人</td>
				<td nowrap="nowrap" class="info_content_00">				
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}"/>
		        </td>
			    </tr>
			 <!--   	
		     <tr>	
			    <td nowrap="nowrap" class="info_title_01" colspan="1">参观项目</td>
			<td nowrap="nowrap" class="info_content_00" colspan="3">
			    <c:forEach items="${visit_pian}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visit_pro_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect1(${i.index})"/>
	      			</c:forEach>
			 </td>
			<td nowrap="nowrap" class="info_title_01" colspan="1">宣传片</td>
			 <td nowrap="nowrap" class="info_content_00" colspan="3">
			    	<c:forEach items="${visit_pro}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visit_pian_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect2(${i.index})"/>
	      			</c:forEach>
		    </td>		   
			</tr>	
			-->
		</table>
		
		--------------------------------------------
		<!--  -->
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
				<td nowrap="nowrap" class="info_title_01">级别</td>
				<td nowrap="nowrap" class="info_title_01">联系电话</td>
				<!--  <td nowrap="nowrap" class="info_title_01">礼品申请</td>
				<td nowrap="nowrap" class="info_title_01">礼品名称</td>
				<td nowrap="nowrap" class="info_title_01">礼品数量</td>-->
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			
			<tr>			
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterName0" style="width:70px" value="" /></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterDuty0" style="width:60px" value="" /></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterCompany0" style="width:70px" value="" /></td>
					
				<td nowrap="nowrap" class="info_content_01">
					<select name="Distinction0" id="Distinction0">
					    <option value="">请选择</option>
						<c:forEach items="${allVisiterDistiniction}" var="all" varStatus="i">
				    		<option value="${all.CODE_ID}">${all.CODE_NAME}</option>
			    		</c:forEach>
			    	</select>
			    	<span style="color:red; cursor:pointer;" onclick="viewVisiterLevel()">点击查看</span>
				</td>
			   
				
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="ContactTel0" style="width:150px" value="" /></td>
				<!-- <td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterGiftYesOrNo0"	codeClass="YesOrNo" selected="${visiterEatryType}" all="all"/>
			    </td>		
					
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftName0" style="width:150px" value="" /></td> 
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="GiftNumber0" style="width:150px" value="" /></td>-->
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="memo0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
			</tr>
			
		</table>
		<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="20" width="80%">
					用餐信息<input type="checkbox" id = "name="visiterEatryFlag"" name="visiterEatryFlag"  onclick="hiddentable('operateTable3',this.name)" value = "true"/>
				</td>
				<td align="right" class="info_content_04" colspan="1">
					${visiterEatryInfo}&nbsp;
				</td>
			</tr>
		</table>
		<table id="operateTable3"  width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"  style= "display:none " > 
			
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
			<tr>
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterEatryDistinction0"	codeClass="Visiter_Eatery_Distiniction" selected="${Visiter_Eatery_Distiniction}"  all="all" />
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterEatryName0"  value="" />
			</td>
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="visiterEatryDate0" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
			
				<td nowrap="nowrap" class="info_content_01">
						<select name="visiterEatryHour0">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterEatryMin0">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterEatryType0"	codeClass="Visiter_Eatery_Type" selected="${visiterEatryType}" all="all"/>
			</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterEatryNumber0"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			</td>
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterEatryMemo0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
			<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil3.appendRow();" name="a3" id="a3">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil3.deleteRow();" name="a3" id="a3">
				</td>
			</tr>
			
		</table>
		
		<!-- <br>	
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="5" width="80%">
					住宿信息<input type="checkbox" name="visiterAccommodationFlag"  onclick="hiddentable('operateTable2',this.name)" value = "true"/>
				</td>
				<td align="right" class="info_content_04" >
					${visiterAccommodationInfo}&nbsp;
				</td>
			</tr>
		</table>
		<table id = 'operateTable2' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">	

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
			
			<tr>			
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="accommodationName0" style="width:70px" value="" /></td>
				<td nowrap="nowrap" class="info_content_01">
			     <ait:codeClass  name="accommodationStandard0"	codeClass="Visiter_Accommodation_Standard"  all="all"/>
			    </td>
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="accommodationInDate0" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<select name="accommodationInHour0">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="accommodationInMin0">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>	
				
				<td nowrap="nowrap"" class="info_content_01">
					<input type="text" name="accommodationOutDate0" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<select name="accommodationOutHour0">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="accommodationOutMin0">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>	
			
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="accommodationMemo0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil2.appendRow();" name="a2" id="a2">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil2.deleteRow();" name="a2" id="a2">
				</td>
			</tr>
			
		</table>-->
		
		<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					车辆信息<input type="checkbox" name="visiterVoitureFlag" value = "true"  onclick="hiddentable('visiterVoitureTable',this.name)"/>
				</td>
			</tr>
		</table>
		<table id = "visiterVoitureTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">
			
			<tr>
			
			
			<td nowrap="nowrap" class="info_title_01">人数</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01">出发日期</td>
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterVoitureInDate" value="" class="content" readonly onClick="setday(this);" style="width:70px">
			</td>
			
			<td nowrap="nowrap" class="info_title_01">出发时间</td>
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterVoitureInHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterVoitureInMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_title_01">预计到达日期</td>
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterVoitureOutDate" value="" class="content" readonly onClick="setday(this);" style="width:70px">
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
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterVoitureOutMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_title_01">出发地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureStartArea"  value="" />
			</td>
			<td nowrap="nowrap" class="info_title_01">途经地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoiturePassArea"  value="" />
			</td>
			<td nowrap="nowrap" class="info_title_01">最终目的地</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterVoitureEndArea"  value="" />
			</td>
            <td nowrap="nowrap" class="info_title_01">备注</td>
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterVoitureMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
			
			</tr>
		</table>
		
		
		
			<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					参观者接待<input type="checkbox" name="visiterConferenceFlag" value = "true" onclick="hiddentable('visiterConferenceTable',this.name)"/>
				</td>
			</tr>
		</table>
		<table id = "visiterConferenceTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">
			
			<tr>
			<!-- <td nowrap="nowrap" class="info_title_01">接待室</td>
			<td nowrap="nowrap" class="info_content_01">
      		<select name="visiterConferenceRoom">
				<option value="">请选择</option>
				<c:forEach items="${roomname}" var="roomname" varStatus="i">
					<option value="${roomname.ID}">
						${roomname.ROOMNAME}
					</option>
				</c:forEach>
			</select></td> -->
			
			<td nowrap="nowrap" class="info_title_01">接待地点</td>
			<td nowrap="nowrap" class="info_content_01">
      		<select name="visiterConferenceRoom">
				<option value="">请选择</option>
				<c:forEach items="${roomname}" var="roomname" varStatus="i">
					<option value="${roomname.ID}">
						${roomname.ROOMNAME}
					</option>
				</c:forEach>
			</select></td>
		
			<td nowrap="nowrap" class="info_title_01" >工厂介绍资料</td>
			 <td nowrap="nowrap" class="info_content_00" colspan="1">
			    	<c:forEach items="${apiLanguage}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visiter_conference_language_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect1(${i.index})"/>
	      			</c:forEach>
		    </td>		
			<td nowrap="nowrap" class="info_title_01" >讲解人</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterConferencePeople"  value="" />
			</td>
			
			 <td nowrap="nowrap" class="info_title_01">
				会议室使用动态</td>
			<td nowrap="nowrap" class="info_content_01"><span style="color:red; cursor:pointer;" onclick="viewConferenceRoomUsing()">点击查看</span></td>
			</tr>
			
			
			<tr>
			<!-- <td nowrap="nowrap" class="info_title_01" >与总经理见面</td>
			<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterConferenceManageYesOrNo"	codeClass="YesOrNo"  all="all"/>
			</td> -->
			<td nowrap="nowrap" class="info_title_01" >接待领导</td>
			<td nowrap="nowrap" class="info_content_01">
			    <!--<ait:codeClass name="visiterConferenceManageYesOrNo"	codeClass="YesOrNo"  all="all"/>-->
			    <select name="visiterConferenceManageYesOrNo">
			    <option value="">请选择</option>
			    <option value="总经理">总经理</option>
			    <option value="专务">专务</option>
			    <option value="常务">常务</option>
			    <option value="部长">部长</option>
			    <option value="课长">课长</option>
			    </select>
			</td> 
			<!--  <td nowrap="nowrap" class="info_title_01" >日期</td>	
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterConferenceDate" value="" class="content" readonly onClick="setday(this);" style="width:70px">
			</td>-->
			<td nowrap="nowrap" class="info_title_01" >公司宣传片</td>	
			<td nowrap="nowrap"" class="info_content_01">
			中文<input type="checkbox" name="pian_language_0" value="中文" onclick="ifSelectPian(0)"/>
			韩文<input type="checkbox" name="pian_language_1" value="韩文" onclick="ifSelectPian(1)"/>
			</td>
			<!-- <td nowrap="nowrap" class="info_title_01" >时间</td>	
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterConferenceHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterConferenceMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
			</td> -->
			<td nowrap="nowrap" class="info_title_01" >其他</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterConferencePurpose"  value="" />
			</td>
			
			
			</tr>
		</table>
		
			<br>
		<br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					工厂参观<input type="checkbox" name="visiterFactoryFlag" value = "true" onclick="hiddentable('visiterFactoryTable',this.name)"/>
				</td>
			</tr>
		</table>
		<!-- <table id = "visiterFactoryTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >参观人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
		
			
			<td nowrap="nowrap" class="info_title_01" >参观方法</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryMethod"	codeClass="Visiter_Method"  all="all"/>
			</td>
			<td nowrap="nowrap" class="info_title_01" >参观路线</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryRoute"	codeClass="Visiter_Route"  all="all"/>
			</td>
			</tr>
			
			<tr>
				<td nowrap="nowrap" class="info_title_01" >试听设备</td>
				<td nowrap="nowrap" class="info_content_00">					
					<c:forEach items="${allDevice}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visiter_factory_device_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect3(${i.index})"/>
	      			</c:forEach>
				</td>
				
				<td nowrap="nowrap" class="info_title_01" >安全帽</td>	
		
				<td nowrap="nowrap" class="info_content_01">
				    <ait:codeClass name="visiterFactoryHelmetYesOrNo"	codeClass="YesOrNo"  all="all"/>
				</td>
				<td nowrap="nowrap" class="info_title_01" >数量</td>	
				<td nowrap="nowrap" class="info_content_01">
			    <input type="text" name="visiterFactoryHelmetNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
			    </td>
			</tr>	
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >导览系统</td>	
						
				<td nowrap="nowrap" class="info_content_01">
			    <ait:codeClass name="visiterFactorySystemYesOrNo"	codeClass="YesOrNo"  all="all"/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01" >中文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryCNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			<td nowrap="nowrap" class="info_title_01" >韩文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryKNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			<td nowrap="nowrap" class="info_title_01" >英文人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryENumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			
			</tr>
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >陪同人员</td>	
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryPeople"  value="" />
			</td>
			<td nowrap="nowrap" class="info_title_01" >大厅欢迎词</td>	
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterFactoryWelcomeSpeech"	codeClass="Visiter_Welcome_Speech"  all="all"/>
			</td>
			
			<td nowrap="nowrap" class="info_title_01" >欢迎词语言</td>
			 <td nowrap="nowrap" class="info_content_00">
			    	<c:forEach items="${apiLanguage}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visiter_factory_language_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect2(${i.index})"/>
	      			</c:forEach>
		    </td>	
			<td nowrap="nowrap" class="info_title_01" >其他</td>	
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterFactoryMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
			</td>
			
			</tr>
		</table> -->
		
		<table id = "visiterFactoryTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >参观人数</td>
			
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryNumber"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/></td>
			<td nowrap="nowrap" class="info_title_01" >陪同人员</td>	
			<td nowrap="nowrap" class="info_content_01" >
			<input type="text" name="visiterFactoryPeople"  value="" />
			</td>
			<td nowrap="nowrap" class="info_title_01" ></td>	
			<td nowrap="nowrap" class="info_content_00" >
			</td>
			</tr>
			
			<tr>
			<td nowrap="nowrap" class="info_title_01" >大厅欢迎词</td>	
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterFactoryWelcomeSpeech"  value="" />
			</td>
			
			<td nowrap="nowrap" class="info_title_01" >欢迎词语言</td>
			 <td nowrap="nowrap" class="info_content_00">
			    	<c:forEach items="${apiLanguage}" var="all" varStatus="i">
	      				${all.CODE_NAME}<input type="checkbox" name="visiter_factory_language_${i.index}" value="${all.CODE_NAME}" onclick="ifSelect2(${i.index})"/>
	      			</c:forEach>
		    </td>	
			<td nowrap="nowrap" class="info_title_01" >其他</td>	
			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterFactoryMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
			</td>
			
			</tr>
		</table>
		
		<br>
		
		<!-- <br>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr>
				<td align="left" class="title1" colspan="18">
					观光旅游<input type="checkbox" name="visiterTourismFlag" value = "true" onclick="hiddentable('visiterTourismTable',this.name)"/>
				</td>
			</tr>
		</table>
		<table id = "visiterTourismTable" width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style= "display:none ">
			
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
			    <ait:codeClass name="visiterTourismYesOrNo"	codeClass="YesOrNo"  all="all"/>
			</td>
			
			<td nowrap="nowrap"" class="info_content_01">
			<input type="text" name="visiterTourismDate" value="" class="content" readonly onClick="setday(this);" style="width:70px">
			</td>
			<td nowrap="nowrap" class="info_content_01">
						<select name="visiterTourismHour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="visiterTourismMin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<ait:codeClass name="visiterTourismType"	codeClass="Visiter_Tourism"  all="all"/>
			</td>
			<td nowrap="nowrap" class="info_content_01">
			<input type="text" name="visiterTourismFee"  value="" />
			</td>
		

			<td nowrap="nowrap" class="info_content_01">
				<textarea name="visiterTourismMemo" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
			
			</tr>
		</table>
		
		
		<br> -->
		<br>
		<br>
		<br>
		<br>
		<br>
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
<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
</div>
</form>
</body>
</html>
