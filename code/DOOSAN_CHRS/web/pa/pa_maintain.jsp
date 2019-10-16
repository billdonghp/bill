<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" scope="session" class="com.ait.sy.bean.AdminBean" />
<html>
<head>
<!-- pa_maintain.jsp -->
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script src="../js/prototype.js"></script>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_search.jsp"%>
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td align="left" class="title1" ><!--查询条件--><ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		      	 <tr>
			        <td class="info_title_01">员工区分:</td>
			        <td class="info_content_00" nowrap="nowrap">
			        	<ait:empDiff cpnyDiff="${admin.cpnyId}" name="statTypeCode" selected="${statTypeCode}"></ait:empDiff>
			        </td>
			        <td class="info_title_01"><!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:</td>
			        <td class="info_content_00" nowrap="nowrap">
	        		<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
	        		</td>
	        		<td class="info_title_01">工资名称:</td>
					<td align="center" class="info_content_00" nowrap="nowrap">
				   		<select name="tableName">
								<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
								<!-- <option value="PA_REPLENISHMENT_HISTORY" <c:if test="${tableName == 'PA_REPLENISHMENT_HISTORY'}">selected</c:if> >工资(补)</option> -->
						</select>
					</td>
		        </tr>
	      		<tr>
	      			<td class="info_title_01"><!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>:</td>
			        <td align="center" class="info_content_00" nowrap="nowrap">
					    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
					    &nbsp;&nbsp;姓名：(<span id="name"></span>)
					    <input name="personId" value="" type="hidden">
					    <input name="supplyFlag" value="N" type="hidden">
					</td>
	      			<td class="info_title_01"><!--部门-->
						<ait:message  messageID="display.mutual.department"/></td>
					<td class="info_content_00" colspan="1" ><span id="deptmentName"></span>
					</td>
					<td class="info_title_01"><!--入社日期-->入社日期
					<td class="info_content_00" colspan="1" ><span id="data_started"></span>
					</td>
				 </tr>
			</table>
	      </td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--结果维护-->结果维护</td>
			</tr>
		 </table>
		<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		  <tr align="center">
		    <td colspan="1"  >
		    <!-- 更换不同的页面 -->
		    <iframe width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0" name="pa_data"></iframe>
		    </td>
		  </tr>
		</table>

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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>
</html>
<script language="JavaScript" type="text/JavaScript">

function Save(){
	alert("此页面无此功能!!!") ;
}


function Search() {
  var cpnyId = '${admin.cpnyId}' ;
  var empId = document.all("key").value ;
  var personId = document.all("personId").value ;
  var statTypeCode = document.all("statTypeCode").value ;
  var paMonth = document.all("year").value + document.all("month").value ;
  var tableName = document.all("tableName").value ;
  var supplyFlag = document.all("supplyFlag").value ;
  
  if (empId == null || empId.length == 0)
  {
  	alert("请输入职号!!!") ;
  	document.all("key").focus() ;
  	return ;
  }
  
  
  if ( statTypeCode == "C_12067_1330306" )
  {
  		if ( tableName == "PA_HISTORY")
  		{
  			pa_data.location.href = "pa_maintain_data_factory_" + cpnyId + ".jsp?paMonth=" + paMonth + "&statTypeCode=" + statTypeCode + "&key=" + empId +"&supplyFlag=" + supplyFlag;
  		}
  		else if ( tableName == "PA_REPLENISHMENT_HISTORY")
  		{
  			pa_data.location.href = "pa_maintain_data_factory2_" + cpnyId + ".jsp?paMonth=" + paMonth + "&statTypeCode=" + statTypeCode + "&key=" + empId ;
  		}
  }
  else if ( statTypeCode == "C_12067_1330308" )
  {
  		if ( tableName == "PA_HISTORY")
  		{   //DIY支社工资明细手动维护页面
  			pa_data.location.href = "pa_maintain_data_filialess_" + cpnyId + ".jsp?paMonth=" + paMonth + "&statTypeCode=" + statTypeCode + "&key=" + empId +"&supplyFlag=" + supplyFlag;
  		}
  		else if ( tableName == "PA_REPLENISHMENT_HISTORY")
  		{
  			pa_data.location.href = "pa_maintain_data_filialess_" + cpnyId + ".jsp?paMonth=" + paMonth + "&statTypeCode=" + statTypeCode + "&key=" + empId ;
  		}
		
  }
  else
  {
		alert("请选择正确员工区分参数!!!") ;
		document.all("statTypeCode").focus() ;
		return ;
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
     	var pars = "operation=hrmSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
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
		$('name').innerHTML = cell.childNodes[1].firstChild.nodeValue ;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue ;
		$('deptmentName').innerHTML = cell.childNodes[3].firstChild.nodeValue ;
		
		layerClose();
}

</SCRIPT>