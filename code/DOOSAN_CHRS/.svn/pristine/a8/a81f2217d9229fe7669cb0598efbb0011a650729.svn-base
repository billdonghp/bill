<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤查看&gt;工厂工人考勤汇总</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/ar/ar_add_vacationEmp.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
	if(document.form1.vacation_no.value == 0) {
	//"请选择修改项"
		alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateVaction_emp&menu_code=${param.menu_code}";
	document.form1.submit();
}            
function Delete() {
	var flag = false ;
	for (i=0;i<document.form1.vacationEmps.length;i++){
		if(document.form1.vacationEmps[i].checked){     
			flag = true;   
			break ;
		}
	}
	
	if(document.form1.vacationEmps.checked)
	{
		flag = true;
	}
	
	if (!flag)
	{//"请选择要删除的数据!!"
		alert('<ait:message  messageID="alert.att.select_item_deleted" module="ar"/>') ;
		return ;
	}
	//"确认删除选中项?"
	if(confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>')) {
		document.form1.action="/arControlServlet?operation=ar_vacation_emp_del&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {

	document.form1.action="/arControlServlet?operation=ar_vacation_emp&menu_code=${param.menu_code}";
	document.form1.submit();
}

function CheckAll() {
	if (document.form1.vacationEmps) {
		if (document.form1.vacationEmps[0]) {
			for (i=0;i<document.form1.vacationEmps.length;i++)
				document.form1.vacationEmps[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.vacationEmps.checked = document.form1.checkAll.checked;
		}
	}
}

function exportExcel()
{
	if(document.form1.deptid.value=="" && document.form1.key.value=="" && document.form1.vac_tp.value=="" && document.form1.vac_id.value=="") {
		//"请选择导出的Excel条件范围."
		alert('<ait:message  messageID="alert.att.maintenance.visit.select_criteria" module="ar"/>');
		return;
	}
    form1.action = "/arControlServlet?operation=exportVacationEmp&menu_code=${param.menu_code}";
    form1.submit();    
}

function importExcel()
{
	url="/arControlServlet?operation=importVacationEmpReport$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function downloadImportTemplate()
{
	form1.action = "/arControlServlet?operation=downloadVacationEmpTemplate&menu_code=${param.menu_code}";
    form1.submit();
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

		document.emp_list.location.href = "/inc/ArSearchEmployee.jsp?content="+encodeURIComponent(empid)+"&id=key";
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



function band(backColor,textColor,i)
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
	document.form1.vacation_no.value=i;
} 
</SCRIPT>
<body>
<form name="form1" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../hrm/inc/hrtoolbar_null.jsp"%>
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
	     
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_view_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					工厂考勤汇总查看</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		     
		      <td width="5%" class="info_title_01" nowrap="nowrap">考勤月份</td>
		      <td width="8%" class="info_title_01">
					员工类型</td>
		      <td width="9%" class="info_title_01">
				工人出勤小时时间</td>
		
		 	  <td width="8%" class="info_title_01">
					工人加班小时时间</td>
		      
		    </tr>
			<c:forEach items="${arFactroyTotalList}" var="oneResult" varStatus="i">
		  
		        <tr align="center" > 
		      <td height="30" align="center" style="color: #666666;">${oneResult.AR_MONTH}</td>
		      <td align="center" style="color: #666666;">${oneResult.JOIN_TYPE}</td>
		      <td align="center" style="color: #666666;">${oneResult.WORKTIME}&nbsp;(小时)</td>

		      <td align="center" style="color: #666666;">${oneResult.OTTIME}&nbsp;(小时)</td>
		      
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		
		  <input type="hidden" name="vacation_no" value="0" />
		 
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=ar_factory_total&menu_code=${param.menu_code}&deptid=${deptid}&key=${key}&vac_tp=${vac_tp}&vac_id=${vac_id}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
		            
		            <!-- Page Navigation End -->
		    
		
	<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(arFactroyTotalList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(arFactroyTotalList)}"
					step="1">
					<tr>
						<td align="center" style="color: #666666;" height="30"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
						<td align="center" style="color: #666666;"></td>
					</tr>
				</c:forEach>
			</c:if>
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
</form>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>
</body>
</html>