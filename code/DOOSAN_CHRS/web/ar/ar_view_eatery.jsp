<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤查看&gt;用餐查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {

	document.form1.action="/ar/ar_add_eatery.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
	if(document.form1.RECORD_NO.value == 0) {
	//"请选择修改项"
		alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateEateryRecord&menu_code=${param.menu_code}";
	document.form1.submit();
}
function Delete() {
	var flag = false ;
	for (i=0;i<document.form1.records.length;i++){
		if(document.form1.records[i].checked){
			flag = true;
			break ;
		}
	}
	
	if(document.form1.records.checked)
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
		document.form1.action="/arControlServlet?operation=deleteEateryRecord&menu_code=${param.menu_code}";
		document.form1.submit();
	}
}
function Search() {

	document.form1.action="/arControlServlet?operation=retrieveEateryRecordList&menu_code=${param.menu_code}";
	document.form1.submit();
}

function CheckAll() {
	if (document.form1.records) {
		if (document.form1.records[0]) {
			for (i=0;i<document.form1.records.length;i++)
				document.form1.records[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.records.checked = document.form1.checkAll.checked;
		}
	}
}

function exportExcel()
{
	if(document.form1.DEPTID.value=="" && document.form1.key.value=="" && document.form1.R_TIME.value=="") {
	//"请选择导出的Excel条件范围."
		alert('<ait:message  messageID="alert.att.maintenance.visit.select_criteria" module="ar"/>');
		return;
	}
    form1.action = "/arControlServlet?operation=exportEateryRecordReport&menu_code=${param.menu_code}";
    form1.submit();    
}

function importExcel()
{
	url="/arControlServlet?operation=importEateryRecordReport$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function downloadImportTemplate()
{
	form1.action = "/arControlServlet?operation=downloadEateryRecordTemplate&menu_code=${param.menu_code}";
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

		document.emp_list.location.href = "/inc/ArSearchEmployeeByEatery.jsp?content="+encodeURIComponent(empid)+"&id=key";
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
     	var pars = "operation=arSearchEmployeeByEatery&condition=" + encodeURIComponent(condition);
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
	document.form1.RECORD_NO.value=i;
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
			<%@ include file="../hrm/inc/hrtoolbar.jsp"%>
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
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
				  <tr>
					 <td width="10%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department"/></td>
			          <td width="15%" class="info_content_00"><ait:selDept name="DEPTID" selected="${DEPTID}" all="all"  supervisorType="ar"/></td>
			          <td width="10%" class="info_title_01" nowrap><!--卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
			          <td width="10%" class="info_content_00"><input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)"></td>
					  <td width="10%" class="info_title_01" nowrap><!--刷卡时间-->
					<ait:message  messageID="display.att.view.punch.punch_time" module="ar"/></td>
			          <td width="10%" class="info_content_00"><input type="text" size="10" maxlength="10" name="R_TIME" value="${R_TIME }" onClick="setday(this);" /></td>
					  <td width="50%" class="info_content_01">
					  <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
			          
			    </tr>
			    <tr>
			          <td width="10%" class="info_title_01"><!--就餐类型-->
					<ait:message  messageID="display.att.view.dining.dining_type" module="ar"/></td>
			          <td width="15%" class="info_content_00"><ait:codeClass name="REPAST_TYPE" codeClass="RepastType" selected="${REPAST_TYPE}" all="all"/></td>
			          <td width="10%" class="info_title_01" nowrap><!--数据来源-->
					<ait:message  messageID="display.att.view.dining.data_source" module="ar"/></td>
			          <td width="10%" class="info_content_00"><ait:codeClass name="INSERT_BY" codeClass="RecordSource" selected="${INSERT_BY}" all="all"/></td>
			          <td width="10%" class="info_title_01" nowrap><!--ID卡类别-->
					<ait:message  messageID="display.att.view.dining.id_type" module="ar"/></td>
			          <td width="10%" class="info_content_00"><ait:codeClass name="CARD_TYPE" codeClass="CardType" selected="${CARD_TYPE}" all="all"/></td>
			          <td width="50%" class="info_content_01" nowrap>
					  <ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand"/> | 
					  <ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" onclick="javascript:downloadImportTemplate();" style="cursor:hand"/> |
					  <ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel();" style="cursor:hand"/></td>
				 </tr>
			</table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_view_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--就餐记录-->
					<ait:message  messageID="display.att.view.dining.dining_record" module="ar"/></td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="5%" class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" /></td>
		      <td width="6%" class="info_title_01" nowrap="nowrap"><!--工号-->
					<ait:message  messageID="display.mutual.emp_id"/></td>
		      <td width="10%" class="info_title_01" nowrap="nowrap"><!--姓名-->
					<ait:message  messageID="display.att.view.dining.visitor_name" module="ar"/></td>
		      <td width="10%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department"/></td>
		      <td width="6%" class="info_title_01" nowrap><!--卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
		      <td width="10%" class="info_title_01"><!--时间-->
					<ait:message  messageID="display.att.view.dining.dining_time" module="ar"/></td>
		      <td width="7%" class="info_title_01" nowrap><!--类型-->
					<ait:message  messageID="display.att.view.dining.dining_type" module="ar"/></td>
		      <td width="8%" class="info_title_01"><!--班次-->
					<ait:message  messageID="display.mutual.shift"/></td>
		      <td width="7%" class="info_title_01"><!--来源-->
					<ait:message  messageID="display.att.view.dining.source" module="ar"/></td>
		      <td width="10%" class="info_title_01"><!--操作者-->
					<ait:message  messageID="display.att.view.dining.edited_by" module="ar"/></td>
		      <td width="10%" class="info_title_01"><!--修改时间-->
					<ait:message  messageID="display.att.view.dining.time_edited" module="ar"/></td>
		      <td width="11%" class="info_title_01"><!--备注-->
					<ait:message  messageID="display.mutual.notes"/></td>
		    </tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
		    <tr align="center" onClick="band('#E7F0EF','black',${oneResult.RECORD_NO})"> 
		      <td height="30" align="center" style="color: #666666;"><input type="checkbox" value="${oneResult.RECORD_NO}" name="records" id="records" ></td>
		      <td align="center" style="color: #666666;">${oneResult.EMPID}&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.CHINESE_PINYIN}" zhContent="${oneResult.CHINESENAME}" koContent="${oneResult.KOREANNAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.DEPT_EN_NAME}" zhContent="${oneResult.DEPTNAME}" koContent="${oneResult.DEPT_KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.CARD_NO}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.R_TIME}&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.REPAST_TYPE_EN_NAME}" zhContent="${oneResult.REPAST_TYPE_NAME}" koContent="${oneResult.REPAST_TYPE_KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.SHIFT_EN_NAME}" zhContent="${oneResult.SHIFT_NAME}" koContent="${oneResult.SHIFT_KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.INSERT_BY_EN_NAME}" zhContent="${oneResult.INSERT_BY_NAME}" koContent="${oneResult.INSERT_BY_KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;"><ait:content enContent="${oneResult.OPERATOR_EN_NAME}" zhContent="${oneResult.OPERATOR_NAME}" koContent="${oneResult.OPERATOR_KOR_NAME}"/>&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.INSERT_TIME}&nbsp;</td>
		      <td align="center" style="color: #666666;">${oneResult.REMARK}&nbsp;</td>
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="RECORD_NO" value="0" />
		 
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=retrieveEateryRecordList&menu_code=${param.menu_code}&DEPTID=${DEPTID}&key=${key}&R_TIME=${R_TIME}&REPAST_TYPE=${REPAST_TYPE}&INSERT_BY=${INSERT_BY}&CARD_TYPE=${CARD_TYPE}" 
		               total="${recordCount}"
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
			<c:if test="${fn:length(recordList) < 5}">
				<c:forEach var="i" begin="1" end="${5-fn:length(recordList)}"
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