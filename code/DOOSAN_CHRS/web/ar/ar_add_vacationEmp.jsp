<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
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
function Save()
{
	document.form1.action="/arControlServlet?operation=ar_vacationEmp_add&menu_code=<%=request.getParameter("menu_code")%>";
	document.form1.fireSubmit(); 
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
		$('key').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}
function paseValueToAmount(value){   
            if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
	                    if(decimalIndex=='-1'){   
	                        return false;   
	                    }else{   
	                        var decimalPart=value.substring(decimalIndex+1);   
	                        //alert(decimalPart);
	                        if(Number(decimalPart)>10){   
	                            return true;   
	                        }else{   
	                            return false;   
	                        }   
	                    }   
            }   
                return false;   
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
					<td align="left" class="title1" colspan="10"><!--个人休假记录-->
					<ait:message  messageID="display.att.view.vacation.record" module="ar"/></td>
				</tr>
			</table>
		  <table width="100%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		      <td width="15%" class="info_title_01" align="center"><!--工号-->
					<ait:message  messageID="display.mutual.emp_id"/></td>
		      <td width="75%" class="info_content_00">
		      <input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)" required></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--工作时间-->工作时间</td>
		      <td width="85%" class="info_content_00"><input type="text" size="10" maxlength="10" name="work_year" /></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--休假类型-->
					<ait:message  messageID="display.att.setting.dayoff.type" module="ar"/></td>
		      <td width="85%" class="info_content_00"><ait:codeClass name="vac_tp" codeClass="VacationType" remove="VacType20" /></td>
		    </tr>
		    <tr>
		      <td width="15%" class="info_title_01"><!--休假ID-->
					<ait:message  messageID="display.att.view.dayoff.cycle" module="ar"/></td>
		      <td width="85%" class="info_content_00"><ait:date yearName="year" yearPlus="10" /></td>
		    </tr>
		    
		    <tr>
		      <td class="info_title_01"><!--开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		      <td class="info_content_00"><input type="text" size="10" maxlength="10" name="strt_date" onClick="setday(this);" required /></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		      <td class="info_content_00"><input type="text" size="10" maxlength="10" name="end_date" onClick="setday(this);" required/></td>
		    </tr>
		    <tr>
		      <td class="info_title_01"><!--休假天数-->
					<ait:message  messageID="display.att.view.vacation.days" module="ar"/></td>
		      <td class="info_content_00"><input type="text" size="10" maxlength="10" name="tot_vac_cnt" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')"/></td>
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>
</body>

<ait:xjos />
</html>
