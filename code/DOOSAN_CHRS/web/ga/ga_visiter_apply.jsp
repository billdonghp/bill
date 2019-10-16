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
    
	if(document.form1.visit_intro_dept.value == ""){
		alert("请填写访问介绍单位！");
		return;
	}
	if(document.form1.visit_phone.value == ""){
		alert("请填写联系人电话！");
		return;
	}
	if(document.form1.visit_dept.value == ""){
		alert("请填写来访单位！");
		return;
	}
	if(document.form1.visit_place.value == ""){
		alert("请填写访问地点！");
		return;
	}
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
	if(document.form1.visit_count.value == ""){
		alert("请填写来访人数！");
		return;
	}
	
	if(document.form1.visit_name_position.value == ""){
		alert("请填写主要客人姓名/职务！");
		return;
	}
	
	if(document.form1.visit_objective.value == ""){
		alert("请填写来访目的！");
		return;
	}
	if(document.form1.expect_result.value == ""){
		alert("请填写期待效果！");
		return;
	}
	if(document.form1.visit_type.value == ""){
		alert("请填写来访类型！");
		return;
	}
	if(document.form1.visitor_type.value == ""){
		alert("请填写访客类型！");
		return;
	}
	if(document.form1.frontdeptID.value == ""){
		alert("请填写接待部门！");
		return;
	}
	if(document.form1.empName.value == ""){
		alert("请填写接待担当！");
		return;
	}
	
	if(document.form1.reception_act_phone.value == ""){
		alert("请填写接待担当电话！");
		return;
	}

	
	///if(document.form1.frontdeptID.value == ""){
	///	alert("请选择接待部门！");
	///	return;	
	///}
	
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

function ifSelect1(){
var aa = '';
		if(document.form1['welcome'].checked){
			aa += document.form1['welcome'].value;
		}
	document.form1.welcome1.value = aa;
	return;
}
function ifSelect2(){
var aa = '';
		if(document.form1['reception_office'].checked){
			aa += document.form1['reception_office'].value;
		}
	document.form1.reception_office1.value = aa;
	return;
}
function ifSelect3(){
var aa = '';
		if(document.form1['tea'].checked){
			aa += document.form1['tea'].value;
		}
	document.form1.tea1.value = aa;
	return;
}
function ifSelect4(){
var aa = '';
		if(document.form1['coffee'].checked){
			aa += document.form1['coffee'].value;
		}
	document.form1.coffee1.value = aa;
	return;
}
function ifSelect5(){
var aa = '';
		if(document.form1['advertising_video'].checked){
			aa += document.form1['advertising_video'].value;
		}
	document.form1.advertising_video1.value = aa;
	return;
}
function ifSelect6(){
var aa = '';
		if(document.form1['defend_facility'].checked){
			aa += document.form1['defend_facility'].value;
		}
	document.form1.defend_facility1.value = aa;
	return;
}
function ifSelect7(){
var aa = '';
		if(document.form1['interpret_facility'].checked){
			aa += document.form1['interpret_facility'].value;
		}
	document.form1.interpret_facility1.value = aa;
	return;
}
function ifSelect8(){
var aa = '';
		if(document.form1['shoot'].checked){
			aa += document.form1['shoot'].value;
		}
	document.form1.shoot1.value = aa;
	return;
}
function ifSelect9(){
var aa = '';
		if(document.form1['present'].checked){
			aa += document.form1['present'].value;
		}
	document.form1.present1.value = aa;
	return;
}
function ifSelect10(){
var aa = '';
		if(document.form1['lunch_repast'].checked){
			aa += document.form1['lunch_repast'].value;
		}
	document.form1.lunch_repast1.value = aa;
	return;
}
function ifSelect11(){
var aa = '';
		if(document.form1['restaurant'].checked){
			aa += document.form1['restaurant'].value;
		}
	document.form1.restaurant1.value = aa;
	return;
}
function ifSelect12(){
var aa = '';
		if(document.form1['fruit'].checked){
			aa += document.form1['fruit'].value;
		}
	document.form1.fruit1.value = aa;
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
					访问者信息<font color="red" size="2">${declaration}</font>
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
				<td nowrap="nowrap" class="info_title_01">访问介绍单位</td>
				<td nowrap="nowrap" class="info_content_00">
		          	
          			<input type="text" id="visit_intro_dept" name="visit_intro_dept" value=""/>
		        </td>
		        <td nowrap="nowrap" class="info_title_01">联系人电话</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_phone" name="visit_phone" value=""/>
		        </td>
		         <td nowrap="nowrap" class="info_title_01">来访单位</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_dept" name="visit_dept" value=""/>
		        </td>				
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_title_01">访问地点</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_place" name="visit_place" value=""/>
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
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_title_01">来访人数</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_count" name="visit_count" value=""/>
		        </td>
		        <td nowrap="nowrap" class="info_title_01">主要客人姓名/职务</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_name_position" name="visit_name_position" value=""/>
		        </td>	
		        <td nowrap="nowrap" class="info_title_01">车牌号</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="car_num" name="car_num" value=""/>
		        </td>
		        <td nowrap="nowrap" class="info_title_01">来访目的</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="visit_objective" name="visit_objective" value=""/>
		        </td>			
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_title_01">来访需求</td>
		        <td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="expect_result" name="expect_result" value=""  title="例：促进销售"
          			onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
          			onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999" />
		        </td>
		        
		        <td nowrap="nowrap" class="info_title_01">来访类型</td>
		        <td nowrap="nowrap" class="info_content_00">
		        	<select id="visit_type" name="visit_type">
		        		<option value="">请选择</option>
		        		<option value="参观交流">参观交流</option>
		        		<option value="商务考察">商务考察</option>
		        		<option value="政府安排">政府安排</option>
		        	</select>
		        </td>
		        <td nowrap="nowrap" class="info_title_01">访客类型</td>
		        <td nowrap="nowrap" class="info_content_00">
		        	<select id="visitor_type" name="visitor_type">
		        		<option value="">请选择</option>
		        		<option value="政府/协会/机构">政府/协会/机构</option>
		        		<option value="斗山集团">斗山集团</option>
		        		<option value="代理商/外协厂">代理商/外协厂</option>
		        		<option value="学校">学校</option>
		        		<option value="竞争企业">竞争企业</option>
		        		<option value="非竞争企业">非竞争企业</option>
		        	</select>
		        </td>
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
			    <!-- <td nowrap="nowrap" class="info_title_01">参观法人</td>
				<td nowrap="nowrap" class="info_content_00">				
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}"/>
		        </td> -->
		        
		        	
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
		
		<br>
		<br>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">
					管理部协助内容<font color="red" size="2"></font>
				</td>
<!--				<td align="right" colspan="10">
					<span onclick="jumpHuiYiShi()" style="cursor: pointer;"><font color="red">申请会议室</font></span>
				</td>-->
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" 
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td nowrap="nowrap" class="info_title_01">欢迎屏</td>
				<td nowrap="nowrap" class="info_title_01">接待地点(主楼一楼)</td>
				<td nowrap="nowrap" class="info_title_01">茶</td>
				<td nowrap="nowrap" class="info_title_01">咖啡</td>
				<td nowrap="nowrap" class="info_title_01">矿泉水</td>
				<td nowrap="nowrap" class="info_title_01">宣传片</td>
				
			</tr>				
			<tr>
				<td nowrap="nowrap" class="info_content_00" >
					<div align="center">
						<input type="checkbox" name="welcome" value="欢迎屏"  onclick="ifSelect1()"></input>
						<input type="hidden" name="welcome1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<select  name="reception_office1" id="reception_office1">
							<option value="">请选择</option>
							<option value="VIP接待室">VIP接待室</option>
							<option value="演播室">演播室</option>
							<option value="一楼大会议室">一楼大会议室</option>
						</select>
						<input type="hidden" name="reception_office1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="tea" value="茶" onclick="ifSelect3()"></input>
						<input type="hidden" name="tea1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="coffee" value="咖啡" onclick="ifSelect4()"></input>
						<input type="hidden" name="coffee1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="present" value="矿泉水" onclick="ifSelect9()"></input>
						<input type="hidden" name="present1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="advertising_video" value="宣传片" onclick="ifSelect5()"></input>
						<input type="hidden" name="advertising_video1"   />
					</div>
				</td>
				
			</tr>				
			<tr>
				<td nowrap="nowrap" class="info_title_01">现场防护设备</td>
				<td nowrap="nowrap" class="info_title_01">传译设备</td>
				<td nowrap="nowrap" class="info_title_01">拍摄</td>
				<td nowrap="nowrap" class="info_title_01">午餐就餐</td>
				<td nowrap="nowrap" class="info_title_01">VIP餐厅</td>
				<!--<td nowrap="nowrap" class="info_title_01">水果(VIP)</td>-->
			</tr>				
			<tr>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="defend_facility" value="现场防护设备" onclick="ifSelect6()"></input>
						<input type="hidden" name="defend_facility1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00" >
					<div align="center">
						<input type="checkbox" name="interpret_facility" value="传译设备" onclick="ifSelect7()" />
						<input type="hidden" name="interpret_facility1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="shoot" value="拍摄" onclick="ifSelect8()"></input>
						<input type="hidden" name="shoot1"   />
					</div>
				</td>
				
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="lunch_repast" value="午餐就餐" onclick="ifSelect10()"></input>
						<input type="hidden" name="lunch_repast1"   />
					</div>
				</td>
				<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="restaurant" value="VIP餐厅" onclick="ifSelect11()"></input>
						<input type="hidden" name="restaurant1"   />
					</div>
				</td>
				<!--<td nowrap="nowrap" class="info_content_00">
					<div align="center">
						<input type="checkbox" name="fruit" value="水果(VIP)" onclick="ifSelect12()"></input>
						<input type="hidden" name="fruit1"   />
					</div>
				</td>-->
			</tr>				
					
		</table>
		<br>
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">
					接待部门信息<font color="red" size="2"></font>
				</td>
<!--				<td align="right" colspan="10">
					<span onclick="jumpHuiYiShi()" style="cursor: pointer;"><font color="red">申请会议室</font></span>
				</td>-->
			</tr>
		</table>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" 
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr>
				<td nowrap="nowrap" class="info_title_01"><font color="red"></font>接待部门</td>
				<td nowrap="nowrap" class="info_content_00">
				
				<!--<div id="deptName" name="deptName">&nbsp;</div>-->
				<ait:selDept name="frontdeptID" supervisorType="pa" all="all" selected="${DeptID}" />
				</td>
				<td nowrap="nowrap" class="info_title_01">接待担当</td>
				<td nowrap="nowrap" class="info_content_00">
		          	<input id="empName" name="empName" style="width: 150px" value="<ait:content enContent='${chinesename}' zhContent='${chinesename}' koContent='${chinesename}'/>" 
		          		onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          				
          			<input type="hidden" id="person_id" name="person_id" value="<c:out value='${person_id}'/>"/>
		        </td>	
				
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_title_01">接待领导</td>
				<td nowrap="nowrap" class="info_content_00">
		          	
          				
          			<input type="text" id="reception_lead" name="reception_lead" value=""/>
		        </td>	
		        <td nowrap="nowrap" class="info_title_01">接待担当电话</td>
				<td nowrap="nowrap" class="info_content_00">
          			<input type="text" id="reception_act_phone" name="reception_act_phone" value=""/>
		        </td>
			</tr>
			<tr>
				 <td nowrap="nowrap" class="info_title_01">特记事项</td>
				<td nowrap="nowrap" class="info_content_00" >
          			<textarea cols="60" name="note" id="note"></textarea> 
		        </td>
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
<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
</div>
</form>
</body>
</html>
