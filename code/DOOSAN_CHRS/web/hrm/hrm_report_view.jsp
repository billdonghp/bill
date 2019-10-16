
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<script src="../js/prototype.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LSFC&gt;信息统计&gt;人事报表</title>
<style type="text/css">
.dojoTabPaneWrapper {
  padding : 10px 10px 10px 10px;
}                             
</style>
</head>
<script type="text/javascript" src="../js/dojo/dojo.js"></script>
<script type="text/javascript">
	dojo.require("dojo.widget.TabContainer");
	dojo.require("dojo.widget.LinkPane");
	dojo.require("dojo.widget.ContentPane");
</script>
<script type="text/javascript">

function SearchContentWorkExp(condition){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeInfo&condition=" + encodeURIComponent(condition)+"&id=work";
		var inputBox = $('EmpId_');
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

function showReportWorkExp() {
          var theUrl="";
          var Prove_Pur = document.workExp.Prove_Pur[document.workExp.Prove_Pur.selectedIndex].text;
          document.workExp.target="_blank";
          document.workExp.action="/reportControlServlet?operation=crystal&reportName=hr_workexperience&Prove_Pur="+Prove_Pur;
          document.workExp.submit();
}


function layerClose(){
	$('emp_list').innerHTML = "" ;
	if(window.layeri){
		layeri.style.visibility = 'hidden';
	}	
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell,ssid) {
	if(ssid=='work'){
		$('EmpId_').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML=cell.childNodes[1].firstChild.nodeValue;
		$('deptName').innerHTML=cell.childNodes[2].firstChild.nodeValue;
		if(cell.childNodes[4].firstChild.nodeValue=='null'){
			$('post').innerHTML=""; 		
		}else{			
			$('post').innerHTML=cell.childNodes[4].firstChild.nodeValue; 
		}
		
		if(cell.childNodes[5].firstChild.nodeValue=='null'){
			$('position').innerHTML=""; 		
		}else{			
			$('position').innerHTML=cell.childNodes[5].firstChild.nodeValue; 
		}
		
		if(cell.childNodes[6].firstChild.nodeValue=='null'){
			$('idCardOrPassport').innerHTML=""; 		
		}else{			
			$('idCardOrPassport').innerHTML=cell.childNodes[6].firstChild.nodeValue; 
		}
		
		if(cell.childNodes[7].firstChild.nodeValue=='null'){
			$('joinDate').innerHTML=""; 		
		}else{			
			$('joinDate').innerHTML=cell.childNodes[7].firstChild.nodeValue; 
		}
		
		if(cell.childNodes[8].firstChild.nodeValue=='null'){
			$('dateLeft').innerHTML=""; 		
		}else{			
			$('dateLeft').innerHTML=cell.childNodes[8].firstChild.nodeValue; 
		}
	}
	else{
		$(ssid).value=cell.childNodes[0].firstChild.nodeValue;
		$("name_"+ssid).innerHTML=cell.childNodes[1].firstChild.nodeValue;
		$("dept_"+ssid).innerHTML=cell.childNodes[2].firstChild.nodeValue;
		$("post_"+ssid).innerHTML=cell.childNodes[4].firstChild.nodeValue;
		$("position_"+ssid).innerHTML=cell.childNodes[5].firstChild.nodeValue; 
	}
	layerClose();
}

var time=null;
function send(condition,id,checkw){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						if(checkw=='1'){
							SearchContent(condition,id);						
						}else if(checkw=='2'){
							SearchContentWorkExp(condition);
						}
					},500);  
}

function SearchContent(condition,id){
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeInfo&condition=" + encodeURIComponent(condition)+"&id="+id;
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
		
		layer.style.visibility = 'visible';
		new Ajax.Updater({success: layer}, url, {method: 'post', parameters: pars, onFailure: layerClose});	
}

function showReport(empId,report){
	     
	      var language = "${admin.languagePreference}" ;
          var theUrl="";
          var EmpId="";
          for(var i=1;i<9;i++){
          	if(document.getElementById(empId+i) && document.getElementById(empId+i).value!=""){
	          		EmpId=EmpId+document.getElementById(empId+i).value+",";
	        }
          }
          if(EmpId.charAt(EmpId.length-1)==","){
             EmpId=EmpId.slice(0,EmpId.length-1);
           }
           
          if(language == "en" && report == "hr_documentary")
          {
          		report += "_en" ;
          }                             
                                       
          theUrl="/reportControlServlet?operation=crystal&reportName="+report+"&EmpId=" + EmpId;
          //alert(theUrl) ;
          var name = "";
          var features = "status=yes,menubar=no,resizable=yes,scrollbars=yes,width=800,height=600";
          window.open(theUrl,name,features);
}
</SCRIPT>


<%@ include file="../inc/common_toolbar_n.jsp"%>

<body>
	<br>
	<div id="mainTabContainer" dojoType="TabContainer" style="width: 90%; height: 86%;visibility: visible;" selectedTab="tab1"  >
	
	<a dojoType="LinkPane" href="hrm_report_documentary.jsp" refreshOnShow="true" id="tab1" >
	<table><tr><td  class="info_title_01">
	<!--社员人事登记表-->
      <ait:message  messageID="display.emp.statistic.personnel_report.personnel_record_form" module="hrm"/>
     </td> </tr> </table>
     </a>
	
	
	
<!--	<a dojoType="LinkPane" href="hrm_report_position.jsp" refreshOnShow="true" >岗位判断书</a>-->
	
	<a dojoType="LinkPane" href="hrm_report_workexp.jsp" refreshOnShow="true"  executeScripts="true">
	<table><tr><td  class="info_title_01">
	<!--经历证明书-->
     <ait:message  messageID="display.emp.statistic.personnel_report.document_proof" module="hrm"/>
     </td> </tr> </table>
    </a>
	</div>
	
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>
</body>
</html>
