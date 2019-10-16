<%@ page contentType="text/html; charset=UTF-8"%>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchC(condition,id);
					},500);  
}

function SearchC(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		
		layer = $('emp_list');
		layer1 = $('divi');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6+17;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6+17;
		layeri.style.left = iBleft;
		layer1.style.top = iBtop+iBheight+3;
		layer1.style.left = iBleft;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
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

	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
    layer1.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	layer1.style.visibility = 'hidden';
	
}

function updateValue(cell) {
    var  empID=cell.childNodes[0].firstChild.nodeValue;
    var  empName=cell.childNodes[1].firstChild.nodeValue;
    var deptName=cell.childNodes[4].firstChild.nodeValue;
    var person_id=cell.childNodes[3].firstChild.nodeValue;	
      $('empIDList').innerHTML +=empID+",";
      $('empNameList').innerHTML +=empName+" <span style=\"color:#00CC00; cursor:pointer;\" onClick=\"javaScript:deleteEmployee('"+person_id+"');\"><img src=\"/images/left_menubullet_sub_m.gif\" width=\"15\" height=\"25\" alt=\"删除\" align=\"absmiddle\"></span> ,";
      $('deptNameList').innerHTML +=deptName+","; 
      $('person_id').innerHTML +=person_id+","; 
	layerClose();
}

function deleteEmployee(empid){
	var empidlistarray=$('empIDList').innerHTML.split(",");	
	var empnamelistarray=$('empNameList').innerHTML.split(",");
	var empdeptlistarray=$('deptNameList').innerHTML.split(",");
	var person_idlistarray =$('person_id').innerHTML.split(",");
	for(var i=0;i<person_idlistarray.length;i++){
		if(empid==person_idlistarray[i]){
		$('empIDList').innerHTML=$('empIDList').innerHTML.replace(empidlistarray[i]+",","");
		$('empNameList').innerHTML=$('empNameList').innerHTML.replace(empnamelistarray[i]+",","");
		$('deptNameList').innerHTML=$('deptNameList').innerHTML.replace(empdeptlistarray[i]+",","");
		$('person_id').innerHTML=$('person_id').innerHTML.replace(empid+",","");
		return;
		}
	}
}
function Reset(){
      $('empIDList').innerHTML="";
      $('empNameList').innerHTML="";
      $('deptNameList').innerHTML="";
      $('person_id').innerHTML="";

}
</SCRIPT>

<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>            
          <td width="20%" class="info_content_00" ><!-- 请输入工号查找 -->
           
          	<input id="empID" name="empID" style="width:80px" value="<c:out value='${basic.empID}'/>"  onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>' required/>         
         
        </tr>      
      </table>
     </td>
  </tr>
</table>
     <div id='divi' style="position:absolute;overflow:auto; top:100;width:370; height:25; z-index:1; visibility: hidden;">
		<table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		    <tr>
		      <td width="20%" height="20" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="Reset();" style="cursor:pointer;">清除所有</td>
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
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden; ">   
	</div>
<br>
<ait:xjos />