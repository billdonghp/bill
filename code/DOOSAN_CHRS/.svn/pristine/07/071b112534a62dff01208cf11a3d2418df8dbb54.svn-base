<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">
function Save()
{
  if(isNaN(document.form1.hour.value)||document.form1.hour.value==""||document.form1.hour.value.length!=2||document.form1.hour.value > 23)
  {//"请正确输入2位小时时间"
  	alert('<ait:message  messageID="alert.att.maintenance.punch.hour_correct" module="ar"/>');
	return;
  }
  
   if(isNaN(document.form1.minute.value)||document.form1.minute.value==""||document.form1.minute.value.length!=2||document.form1.minute.value>59 )
  {//"请正确输入2位分钟时间"
  	alert('<ait:message  messageID="alert.att.maintenance.punch.minute_correct" module="ar"/>');
	return;
  }
  
	document.form1.action="/arControlServlet?operation=insertAttRecord&menu_code=${param.menu_code}";
	document.form1.fireSubmit(); 
}
function SearchEmp(empid){

	if(empid!=''){
		var inputBox = document.getElementById("key");
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
		showsearch.style.top = iBtop+iBheight+6;
		showsearch.style.left = iBleft;
		showsearch.style.visibility='visible';
		
		document.emp_list.location.href = "./ArSearchEmployeeByCard.jsp?content="+encodeURIComponent(empid)+"&id=key";
	} else {
		document.all.showsearch.style.visibility='hidden';
	}
}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
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
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
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
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
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

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<form name="form1" method="post" action="">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10"><!--刷卡记录-->
					<ait:message  messageID="display.att.view.punch.punch_record" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!--工号-->
				<ait:message messageID="display.mutual.emp_id" /></td>
		      <td width="75%" class="info_content_00"><input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)" required></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--时间-->
					<ait:message  messageID="display.mutual.time" /></td>
		      <td class="info_content_00">
		      	<input type="text" size="10" maxlength="10" name="R_TIME" onClick="setday(this);" required/>&nbsp;
		      	<input name="hour" type="text" size="2" maxlength="2" required>
	          	:
				<input name="minute" type="text"  size="2" maxlength="2" required></td>
		      </td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--类型-->
					<ait:message  messageID="display.att.view.punch.action" module="ar"/></td>
		      <td class="info_content_00"><ait:codeClass name="DOOR_TYPE" codeClass="DoorType"/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--备注 -->
				<ait:message messageID="display.mutual.notes" /></td>
		      <td class="info_content_00"><textarea name="REMARK" cols="30" rows="3"></textarea></td>
		    </tr>
		  </table>
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>

</body>
<ait:xjos />
</html>
