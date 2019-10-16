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
		$('personId').value = "";
		
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
		$('empID').value=cell.childNodes[0].firstChild.nodeValue;
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		if(cell.childNodes[2].firstChild){
			$('empPinyin').innerHTML=cell.childNodes[2].firstChild.nodeValue;
			$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		}
		layerClose(); 
}

function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=ar0107&operation=searchArEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}

</SCRIPT>

<table width="100%" border="0" cellpadding="0" cellspacing="0"　class="dr_d">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr>
          <td width="10%" class="info_title_01" height="30" ><!-- 姓名-->
				<ait:message messageID="display.mutual.name"/></td>
          <td width="30%" class="info_content_00" colspan="2"><!-- 请输入姓名查找 -->
          	<input id="empName" name="empName" size="8" value="<ait:content enContent='${basic.pinyinName}' zhContent='${basic.chineseName}' koContent='${basic.koreanName}'/>" onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          	(<span id='empPinyin'><c:out value='${basic.pinyinName}'/></span>)</td>
          	<input id="personId" name="personId" type="hidden" value=""/>
          <td width="10%" class="info_title_01" ><!-- 工号-->
				<ait:message messageID="display.mutual.emp_id"/></td>
          <td width="20%" class="info_content_00" ><!-- 请输入工号查找 -->
          	<input id="empID" name="empID" size="8" value="<c:out value='${basic.empID}'/>" onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>' required/>		
          <td width="10%" class="info_title_01" ><!-- 入社日期-->
				<ait:message messageID="display.att.view.individual.hire_date" module="ar"/></td>
          <td width="20%" class="info_content_00" >${basic.joinDate}</td>
        </tr>
        <tr>
		  <td width="10%" class="info_title_01"><!-- 部门-->
				<ait:message messageID="display.mutual.dept"/></td>
          <td width="20%" class="info_content_00" >
          <ait:content enContent='${basic.englishDept}' zhContent='${basic.deptFullName}' koContent='${basic.korDept}'/>
          </td>
 		  <td width="10%" class="info_content_00" >
 		  <ait:image src="/images/btn/Dep.gif"  border="0" align="absmiddle" onclick="showDept();" style="cursor:hand" />
 		  </td>
 		  <td width="10%" class="info_title_01"><!-- 查询年月-->
				<ait:message messageID="display.att.view.individual.timing" module="ar"/></td>
          <td width="20%" class="info_content_00" style="padding-top: 2px;padding-bottom: 2px;">
          	<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
          </td>
          <td width="10%" class="info_title_01"><!-- 所属公社-->
				<ait:message messageID="display.mutual.staff_status"/></td>
          <td width="20%"class="info_content_00">
          <ait:content enContent='${basic.englishStatus}' zhContent='${basic.status}' koContent='${basic.korStatus}'/>
          </td>
        </tr>
      </table>
     </td>
  </tr>
</table>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>
<br>
<ait:xjos />