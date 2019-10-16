<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@page import="com.ait.ga.cmd.visit.*,com.ait.ga.bean.*,com.ait.util.StringUtil" %>

<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="getAllAffirm" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="voitureBean" class="com.ait.ga.bean.VoitureBean"/>
<jsp:useBean id="vbInfo" class="com.ait.ga.bean.VoitureBean"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/prototype.js"></script>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>派车确认</title>
</head>

<SCRIPT type="text/javascript">

var time=null;
function Batch(condition,id,temp){	
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
				 	//alert(temp);
						SearchE(condition,id,temp);
					},500);  
}

function SearchE(condition,id,temp){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=GaVoitureSearch&temp="+temp+"&condition="+condition+"&date="+document.getElementById("APPLY_ENDDATE"+temp).value+document.getElementById("APPLY_ENDTIME"+temp).value+"&date1="+document.getElementById("APPLY_DATE"+temp).value+document.getElementById("APPLY_STARTTIME"+temp).value
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('voiture_list');
		layer1 = $('divi');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6+17;
		layer.style.left = iBleft-370;  
		layeri.style.top = iBtop+iBheight+6+17;
		layeri.style.left = iBleft-370;
		layer1.style.top = iBtop+iBheight+3;
		layer1.style.left = iBleft-370;
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){

	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[5].firstChild.nodeValue;
    size = size*25+30;
   
    if(size<=30){
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
    layer1.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('voiture_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	layer1.style.visibility = 'hidden';	
}

function driverInfo(num,num2)
{
	$("driver_phone_" + num2).innerHTML = document.getElementById("phone"+num).value;
	document.getElementById("driver_phone" + num2).value = document.getElementById("phone"+num).value;
	
}
function updateValue(cell,temp) {
	voitureid = cell.childNodes[0].firstChild.nodeValue;
	voiturename = cell.childNodes[1].firstChild.nodeValue;
	voiturenumber = cell.childNodes[3].firstChild.nodeValue;
	///alert(i);
	var msg='<ait:message messageID="alert.ess.overtime.has_been_added_to_list"  module="ess"></ait:message>';
	var   arry  = document.getElementById("empIds"+temp).value.split(",");
	var   name  =  document.getElementById("empName"+temp).innerText.split(",");
	for (i=0;i<arry.length;i++){ 
		if(voitureid==arry[i]){
		    replacename=voiturename+voiturenumber+",";
			///alert(replacename);  
			document.getElementById("empIds"+temp).value = document.getElementById("empIds"+temp).innerText.replace((voitureid+","),"") ;
	        document.getElementById("empName"+temp).innerText =document.getElementById("empName"+temp).innerText.replace(replacename,"");
	        document.getElementById("carName"+temp).value = document.getElementById("empName"+temp).innerText.replace(replacename,"");
	        return;
		}      
	}
	if (document.getElementById("empIds"+temp).value == '')
		document.getElementById("empIds"+temp).value += voitureid+",";
	else
		document.getElementById("empIds"+temp).value += "," + voitureid ;
		document.getElementById("carName"+temp).value +=(voiturename+voiturenumber)+",";
	    document.getElementById("empName"+temp).innerText +=(voiturename+voiturenumber)+",";
	    layerClose();
}

 function hrefsubmit(applyno,temp,falg){
	if(falg == 1){
		if(document.getElementById("empIds"+temp).value==""||document.getElementById("empIds"+temp).value==null){
		alert("派车信息不能为空！");
		return;
	}
	}
 if(confirm('确认派车信息无误吗？')){
 		document.form1.operation.value="bookingVoiture";
       document.form1.content.value="booking";
 var url = "/gaControlServlet?menu_code=${param.menu_code}&operation=bookingVoiture&content=booking&applyno="+applyno+"&falg="+falg+"&temp="+temp;
	  document.form1.action=url;
	  document.form1.submit();
		}
}
 function modifysubmit(temp){
 if(document.getElementById("empIds"+temp).value==""||document.getElementById("empIds"+temp).value==null){
 	if(confirm('确认清空派车信息吗？')){
 	   document.form1.operation.value="bookingVoiture";
       document.form1.content.value="updateBookingVoiture";
       document.form1.temp.value=temp;
       document.form1.isdelete.value="isdelete";
       document.form1.action="/gaControlServlet";
       document.form1.submit(); 	
 	}
 
 }else{ 
   document.form1.operation.value="bookingVoiture";
   document.form1.content.value="updateBookingVoiture";
   document.form1.temp.value=temp;
   document.form1.action="/gaControlServlet";
   document.form1.submit(); 
 }
 }
function band(backColor,textColor,voitureid,i,falg)
	{
		 var t;
		 if(typeof(preEl)!='undefined')
		 {
		 preEl.bgColor=orgBColor;
	
		 try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
		 }
		 var el = event.srcElement;
		 el = el.parentElement;
		 orgBColor = el.bgColor;
		 orgTColor = el.style.color;
		 el.bgColor=backColor;
		 try{ChangeTextColor(el,textColor);}catch(e){;}
		 preEl = el;
		 s=voitureid;
		 document.form1.temp.value=voitureid;
		 document.form1.temp1.value=i;
		 document.form1.temp2.value=falg;
	} 

function Update() {
var s = document.form1.temp.value;
var temp1 = document.form1.temp1.value;
var falg = document.form1.temp2.value;
if(falg == ''){
	alert("已经派车，不允许修改！");
	return;
}
if(falg == '2'){
	alert("信息已经否决，不允许修改！");
	return;
}
if(s==""||s==null){
alert("请选择项目");
return;
}
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=bookingVoiture&content=updateBookingVoiture&applyno="+s+"&temp="+temp1;
	document.form1.submit();
}

function Delete(){
alert("不可以删除数据！");
}

function search(){
	document.form1.action="gaControlServlet?menu_code=ga0409&operation=bookingVoiture&content=bookVoiture";
	document.form1.submit();
}

function view(id){
	document.form1.action="gaControlServlet?menu_code=ga0409&operation=bookingVoiture&content=view&applyNo="+id;
	document.form1.submit();
}
function ImpExcel(applyno){
document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=ga_booking_voiture_Excel&applyno="+applyno;
document.form1.submit();
}
function checkTimeValue(str){
  var z=/^(([0-1][0-9])|([2-2][0-3])):([0-5][0-9])$/;
  if(!z.test($(str).value)){
   alert("请输入正确的时间格式(xx:xx)");
   $(str).value="";
   return false;  
  }

}
</SCRIPT>

<body>
<%GaAffirm  ga = new GaAffirm(); %>

<form name="form1" method="post" action="">
<input type="hidden" name="operation" value="">
<input type="hidden" name="content" value="">
<input type="hidden" name="temp" value="">
<input type="hidden" name="temp1" value="">
<input type="hidden" name="temp2" value="">
<input type="hidden" name="isdelete" value="">
<input type="hidden" name="menu_code" value="gm0201">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_noAdd.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
	<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="title1"><!-- 查询条件 -->
		<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>		
				</td>
			</tr>
		    <tr>        
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="" <c:if test="${qryType==''}">selected</c:if>>全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未派车</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已派车</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>				     
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
		        </tr> 
		        <tr>
		         <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
		         </td> 
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>       
	
		                        
		        </tr>      
		        </table>
		      </td>
			</tr>
			</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				派车确认
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
				<td nowrap="nowrap" class="info_title_01">
				申请日期</td>
				<td nowrap="nowrap" class="info_title_01">
				乘用人</td>
		      <td nowrap="nowrap" class="info_title_01">
				乘车人数</td>
			  <td nowrap="nowrap" class="info_title_01">
				接送信息</td>
				<td owrap="nowrap" class="info_title_01">
				车辆名称</td>
				<td owrap="nowrap" class="info_title_01">
				司机</td>
				<td owrap="nowrap" class="info_title_01">
				联系方式</td>
				<!-- <td nowrap="nowrap" class="info_title_01">
				申请书</td> -->	
				 <td nowrap="nowrap" class="info_title_01">
				确认情况</td>		
				<td owrap="nowrap" class="info_title_01">
				使用状况</td>
			 
			<td nowrap="nowrap" class="info_title_01">
				意见</td>
						
		    </tr>
		   <c:forEach items="${getAllAffirm}" var="all" varStatus="j">				
		    <tr align="center" onClick="band('#E7F0EF','black','${all.APPLY_NO }','${j.index }','${all.MODIFY_FLAG }')">
		      <input type="hidden" name="empIds${j.index }" value="" />
		      <input type="hidden" name="applyno${j.index }" value="${all.APPLY_NO}" /> 
		      <td nowrap="nowrap"  align="center" height="30px">${all.CHINESENAME }</td>
		      <td nowrap="nowrap"  align="center">${all.APPLYER_DEPTNAME }</td>
		      <td nowrap="nowrap"  align="center">${all.CREATE_DATE }</td>
		      <td nowrap="nowrap"  align="center">${all.LARDER }</td>		
       
		      <td nowrap="nowrap"  align="center">&nbsp;
		      ${all.APPLY_USERSCOUNT }
		      </td>
		     
		      <td nowrap="nowrap" align="center" class="info_content_01" >
   			 <c:if test="${all.DISTINCTION0==1}">区分:接&nbsp;&nbsp;
   			 			使用时间:${all.APPLY_DATE0}&nbsp;${all.APPLY_STARTTIME0}
   			 			&nbsp;&nbsp;业务内容:${all.CONTENT0 }
   			 			&nbsp;&nbsp;乘车路线:${all.DRIVE_WAY0 }
   			 			<c:if test="${all.NOTE0!=NULL}">&nbsp;&nbsp;备注:${all.NOTE0 }</c:if>
   			 			<input type="hidden" name="APPLY_DATE${j.index }" id="APPLY_DATE${j.index }" value="${all.APPLY_DATE0}" style="width: 80px" readonly onClick="setday(this);">
		      
		      	<input  type="hidden" name="APPLY_STARTTIME${j.index }" id="APPLY_STARTTIME${j.index }" value="${all.APPLY_STARTTIME0}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_STARTTIME${j.index }" spacing="30" selected="${all.APPLY_STARTTIME}"/>-->
		      
		      <input type="hidden" name="APPLY_ENDDATE${j.index }" id="APPLY_ENDDATE${j.index }" value="${all.APPLY_ENDDATE0 }" style="width: 80px" readonly onClick="setday(this);"/>
		    
		       <input type="hidden" name="APPLY_ENDTIME${j.index }" id="APPLY_ENDTIME${j.index }" value="${all.APPLY_ENDTIME0}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_ENDTIME${j.index }" spacing="30" selected="${all.APPLY_ENDTIME}"/>-->
   			  </c:if>  
   			 <c:if test="${all.DISTINCTION0==2}">区分:送&nbsp;&nbsp;
   			 			使用时间:${all.APPLY_DATE0}&nbsp;${all.APPLY_STARTTIME0}
   			 			&nbsp;&nbsp;业务内容:${all.CONTENT0 }
   			 			&nbsp;&nbsp;乘车路线:${all.DRIVE_WAY0 }
   			 			<c:if test="${all.NOTE0!=NULL}">&nbsp;&nbsp;备注:${all.NOTE0 }</c:if>
   			 			<input type="hidden" name="APPLY_DATE${j.index }" id="APPLY_DATE${j.index }" value="${all.APPLY_DATE0}" style="width: 80px" readonly onClick="setday(this);">
		      
		      	<input  type="hidden" name="APPLY_STARTTIME${j.index }" id="APPLY_STARTTIME${j.index }" value="${all.APPLY_STARTTIME0}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_STARTTIME${j.index }" spacing="30" selected="${all.APPLY_STARTTIME}"/>-->
		      
		      <input type="hidden" name="APPLY_ENDDATE${j.index }" id="APPLY_ENDDATE${j.index }" value="${all.APPLY_ENDDATE0 }" style="width: 80px" readonly onClick="setday(this);"/>
		    
		       <input type="hidden" name="APPLY_ENDTIME${j.index }" id="APPLY_ENDTIME${j.index }" value="${all.APPLY_ENDTIME0}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_ENDTIME${j.index }" spacing="30" selected="${all.APPLY_ENDTIME}"/>-->
   			  </c:if>  
   			 <c:if test="${all.DISTINCTION1==2}"><br>区分:送&nbsp;&nbsp;
   			 			使用时间:${all.APPLY_DATE1}&nbsp;${all.APPLY_STARTTIME1}
   			 			&nbsp;&nbsp;业务内容:${all.CONTENT1 }
   			 			&nbsp;&nbsp;乘车路线:${all.DRIVE_WAY1 }
   			 			<c:if test="${all.NOTE1!=NULL}">&nbsp;&nbsp;备注:${all.NOTE1 }</c:if>
   			 			<input type="hidden" name="APPLY_DATE${j.index }" id="APPLY_DATE${j.index }" value="${all.APPLY_DATE1}" style="width: 80px" readonly onClick="setday(this);">
		      
		      	<input  type="hidden" name="APPLY_STARTTIME${j.index }" id="APPLY_STARTTIME${j.index }" value="${all.APPLY_STARTTIME1}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_STARTTIME${j.index }" spacing="30" selected="${all.APPLY_STARTTIME}"/>-->
		      
		      <input type="hidden" name="APPLY_ENDDATE${j.index }" id="APPLY_ENDDATE${j.index }" value="${all.APPLY_ENDDATE1 }" style="width: 80px" readonly onClick="setday(this);"/>
		    
		       <input type="hidden" name="APPLY_ENDTIME${j.index }" id="APPLY_ENDTIME${j.index }" value="${all.APPLY_ENDTIME1}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_ENDTIME${j.index }" spacing="30" selected="${all.APPLY_ENDTIME}"/>-->
   			  </c:if> 
   			  <c:if test="${all.DISTINCTION1==1}"><br>区分:接&nbsp;&nbsp;
   			 			使用时间:${all.APPLY_DATE1}&nbsp;${all.APPLY_STARTTIME1}
   			 			&nbsp;&nbsp;业务内容:${all.CONTENT1 }
   			 			&nbsp;&nbsp;乘车路线:${all.DRIVE_WAY1 }
   			 			<c:if test="${all.NOTE1!=NULL}">&nbsp;&nbsp;备注:${all.NOTE1 }</c:if>
   			 			<input type="hidden" name="APPLY_DATE${j.index }" id="APPLY_DATE${j.index }" value="${all.APPLY_DATE1}" style="width: 80px" readonly onClick="setday(this);">
		      
		      	<input  type="hidden" name="APPLY_STARTTIME${j.index }" id="APPLY_STARTTIME${j.index }" value="${all.APPLY_STARTTIME1}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_STARTTIME${j.index }" spacing="30" selected="${all.APPLY_STARTTIME}"/>-->
		      
		      <input type="hidden" name="APPLY_ENDDATE${j.index }" id="APPLY_ENDDATE${j.index }" value="${all.APPLY_ENDDATE1 }" style="width: 80px" readonly onClick="setday(this);"/>
		    
		       <input type="hidden" name="APPLY_ENDTIME${j.index }" id="APPLY_ENDTIME${j.index }" value="${all.APPLY_ENDTIME1}" style="width:35px" onblur="checkTimeValue(this);"/>
<!--		      <ait:time name="APPLY_ENDTIME${j.index }" spacing="30" selected="${all.APPLY_ENDTIME}"/>-->
   			  </c:if> 
   			  </td>
			 <c:if test="${all.MODIFY_FLAG == '0' }">
			  
		      <td nowrap="nowrap"  align="center" style="width: 60px">
		      <table><tr><td nowrap="nowrap"  align="center">
		      <input name="shiftID${j.index }" id="shiftID${j.index }" type="text" value="${all.EMPNAME }" size="8" onKeyUp="Batch(this.value,this.id,'${j.index }');" /><br />
		      <span id="empName${j.index }"></span><input type="hidden" name="carName${j.index }"> </td></tr></table></td>
		      <!-- <td nowrap="nowrap"  align="center"><input type="text" name="driver${j.index }" value="${all.DRIVER }" style="width: 50px"> </td>
		       -->
		      <td nowrap="nowrap"  align="center">
		      		<select name="driver${j.index }" id="driver${j.index }" onchange="driverInfo(document.form1.driver${j.index }.options.selectedIndex,${j.index });" >
		      			<option selected>请选择</option>
		     		 		<c:forEach items="${getDriverInfo}" var="driverInfo" varStatus="i">	
		     		 			<option value="${driverInfo.DRIVER_NAME } " >${driverInfo.DRIVER_NAME}</option>
		     		 		</c:forEach>
		     		 </select>
		      </td>
		      <c:forEach items="${getDriverInfo}" var="driverInfo" varStatus="i">	
		     		<input type="hidden" name="phone${i.index+1 }" id="phone${i.index+1 }" value="${driverInfo.DRIVER_PHONE }"></input> 			
		     </c:forEach>
		      <td nowrap="nowrap" class="info_content_01"><div id="driver_phone_${j.index }" >&nbsp;</div>
		      	<input type="hidden" id="driver_phone${j.index }" name ="driver_phone${j.index }" />
		      </td>
		      <!-- <td nowrap="nowrap"  align="center"><img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel(${all.APPLY_NO});"/></td> -->
		      <td nowrap="nowrap"  align="center">
		      <span style="color:#00CC00; cursor:pointer;" onClick="hrefsubmit('${all.APPLY_NO }','${j.index }','1')">
		      派车&nbsp;&nbsp;</span>|<span style="color:#00CC00; cursor:pointer;" onClick="hrefsubmit('${all.APPLY_NO }','${j.index }','2')">&nbsp;&nbsp; 否决</span>
		      </td>
		      <td nowrap="nowrap"  align="center"><a href="#" onclick="view('${all.APPLY_NO }')"><font color="red"> 查看</font></a> </td>
		      </c:if>
		      <c:if test="${all.MODIFY_FLAG == '1' }">
		     <input type="text" name="vehicle${j.index }" value="${all.VEHICLE }"></input>
		      <td nowrap="nowrap"  align="center">
		      ${all.EMPNAME }&nbsp;
		      </td>
		       <td nowrap="nowrap"  align="center">
				<select name="driver${j.index }" id="driver${j.index }" onchange="driverInfo(document.form1.driver${j.index }.options.selectedIndex,${j.index });" >
		      			<option>请选择</option>
		     		 		<c:forEach items="${getDriverInfo}" var="driverInfo" varStatus="i">	
						        <option value="${driverInfo.DRIVER_NAME } " >${driverInfo.DRIVER_NAME}</option>
		     		 		</c:forEach>
		     		 </select>
			   </td>
		      <!--  <td nowrap="nowrap"  align="center"><img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel(${all.APPLY_NO});"/></td>
		      -->
		      <c:forEach items="${getDriverInfo}" var="driverInfo" varStatus="i">	
		     		<input type="hidden" name="phone${i.index+1 }" id="phone${i.index+1 }" value="${driverInfo.DRIVER_PHONE }"></input> 			
		     </c:forEach>
		      <td nowrap="nowrap" class="info_content_01"><div id="driver_phone_${j.index }" >${all.DRIVER }:${all.DRIVER_PHONE }</div>
		      	<input type="hidden" id="driver_phone${j.index }" name ="driver_phone${j.index }" />
		      </td>
		      <td nowrap="nowrap"  align="center"><font style="color:#00CC00">已派车</font></td>
		      <td nowrap="nowrap"  align="center"><a href="#" onclick="view('${all.APPLY_NO }')"><font color="red"> 查看</font></a> </td>
		      </c:if>
		      <c:if test="${all.MODIFY_FLAG == '2' }">
		      <td nowrap="nowrap"  align="center" >
			  	&nbsp; 
			  	</td>
			  	<td nowrap="nowrap"  align="center">&nbsp; </td>
			  	<td nowrap="nowrap"  align="center">&nbsp; </td>
			  	<!--  <td nowrap="nowrap"  align="center"><img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel(${all.APPLY_NO});"/></td>
			  	-->
			  	<td nowrap="nowrap"  align="center"><font color="red">已否决</font> </td>
			  	<td nowrap="nowrap"  align="center"><a href="#" onclick="view('${all.APPLY_NO }')"><font color="red"> 查看</font></a></td>
		      </c:if>
		      
		      <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${j.index }" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${all.CONFIRMIDEA}</textarea></td>
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="currentPage" value="${currentPage}">
		  <!-- Page Navigation Start-->
			<ait:page       
               link="/gaControlServlet"
               parameters="&operation=bookingVoiture&content=bookVoiture&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}"
               total="${voitureInt}"
               currentpage="${currentPage}"
               pagesize= "${pageSize}"
               beginlabel="paging_prv10"
               endlabel="paging_next10"
               prevlabel="paging_prv"
               nextlabel="paging_next"
               pagegroupsize="${pageGroupsize}"
               useJS="false"/>   
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
	<div id='divi' style="position:absolute;overflow:auto; top:100;width:370; height:25; z-index:1; visibility: hidden;">
		<table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		    <tr>		     
		     </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="layerClose();" style="cursor:pointer;"><!--  关闭-->
		      <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.close" module="ess"></ait:message>
		      </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		    </tr>
	  	</table>
	</div>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="voiture_list"  style="position:absolute;overflow:auto; top:500;width:370; height:200; z-index:2;">  
	</div>

</body>
<ait:xjos />
</html>
