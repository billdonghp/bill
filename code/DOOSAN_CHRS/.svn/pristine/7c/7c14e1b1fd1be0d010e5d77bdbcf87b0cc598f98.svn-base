<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤查看&gt;刷卡查看</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

/* function Add() {

	//document.form1.action="/ar/ar_add_record.jsp?menu_code=${param.menu_code}";
	//document.form1.submit();
}*/
function Update() {
   var flag = false ;
	
	if(document.form1.RECORD_NO.value!=0)
	{
		flag = true;
	}
	
	if (!flag)
	{
		alert("请选择要修改的记录！") ;
		return ;
	}
    if(confirm("确认修改该条刷卡记录？")){
	
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateAttRecord&menu_code=${param.menu_code}";
	document.form1.submit();
	}
	
}
//function Delete() {
	/*
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
		document.form1.action="/arControlServlet?operation=deleteAttRecord&menu_code=${param.menu_code}";
		document.form1.submit();
	}
	*/
//}
function Search() {

	document.form1.action="/arControlServlet?operation=retrieveAttRecordList&menu_code=${param.menu_code}";
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
	if(document.form1.DEPTID.value=="" && document.form1.key.value=="" && document.form1.R_START_TIME.value=="" && document.form1.R_END_TIME.value=="") {
	//"请选择导出的Excel条件范围."
		alert('<ait:message  messageID="alert.att.maintenance.visit.select_criteria" module="ar"/>');
		return;
	}
    form1.action = "/arControlServlet?operation=exportAttRecordReport&menu_code=${param.menu_code}";
    form1.submit();    
}

function importExcel()
{
	url="/arControlServlet?operation=importAttRecordReport$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
}
function downloadImportTemplate()
{
	form1.action = "/arControlServlet?operation=downloadAttRecordTemplate&menu_code=${param.menu_code}";
    form1.submit();
}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
		$('personId').value="";

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
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		//alert($('personId').value) ;
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
	document.form1.isCaltaFlag.value = document.form1["isCaltaFlag_" + i].value ;
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
			<td class="title1"><!-- 查询条件 -->
				<ait:message messageID="display.mutual.search_criteria" /></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       		 <tr>
					 <td nowrap="nowrap" class="info_title_01"><!--部门 -->
				<ait:message messageID="display.mutual.dept" /></td>
			          <td nowrap="nowrap" class="info_content_00"><ait:selDept name="DEPTID" selected="${DEPTID}" all="all" supervisorType="ar"/></td>
			          <td nowrap="nowrap" class="info_title_01"><!--姓名/工号 -->
				<ait:message messageID="display.mutual.emp_no_name" /></td>
				<td nowrap="nowrap" class="info_content_00">
					<input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)">
					<input id="personId" name="personId" type="hidden" value="">
				</td>
				<td nowrap="nowrap" class="info_content_01" >
					  <ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand"/></td>
				
				</tr>
				
				<tr>
					  <td nowrap="nowrap" class="info_title_01"><!--刷卡时间-->
					<ait:message  messageID="display.att.view.punch.punch_time" module="ar"/></td>
			          <td nowrap="nowrap" class="info_content_00">
			          		<input type="text" size="10" maxlength="10" name="R_START_TIME" value="${R_START_TIME}" onClick="setday(this);" />
			          		~
			          		<input type="text" size="10" maxlength="10" name="R_END_TIME" value="${R_END_TIME}" onClick="setday(this);" />
			          </td>
			          <td nowrap="nowrap" class="info_title_01"><!-- 表区分 -->表区分</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="TABLE_NAME">
				  		<option value="AR_MAC_RECORDS"  <c:if test="${TABLE_NAME eq 'AR_MAC_RECORDS'}">selected</c:if> >主表</option>
				  		<option value="AR_MAC_RECORDS_HISTORY" <c:if test="${TABLE_NAME eq 'AR_MAC_RECORDS_HISTORY'}">selected</c:if>>分表</option>
				  	</select>
				</td>
			         	    
				<td nowrap="nowrap" class="info_content_01" >
					  <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
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
				<td align="left" class="title1" colspan="10"><!--刷卡记录-->
					<ait:message  messageID="display.att.view.punch.punch_record" module="ar"/></td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01" nowrap="nowrap"><!--工号-->
				<ait:message messageID="display.mutual.emp_id" /></td>
		      <td nowrap="nowrap" class="info_title_01"><!--姓名-->
				<ait:message messageID="display.mutual.name" /></td>
		     <td nowrap="nowrap" class="info_title_01"><!--课组 -->课组</td>
		      <td nowrap="nowrap" class="info_title_01"><!--部门 -->
				<ait:message messageID="display.mutual.dept" /></td>
		      <td nowrap="nowrap" class="info_title_01"><!--卡号 -->
				<ait:message messageID="display.mutual.card_no" /></td>
		      <td nowrap="nowrap" class="info_title_01"><!--刷卡时间-->
					<ait:message  messageID="display.att.view.punch.punch_time" module="ar"/></td>
			  <td nowrap="nowrap" class="info_title_01"><!--刷卡类型-->
					刷卡类型</td>
		      <td nowrap="nowrap" class="info_title_01"><!--修改时间 -->
				<ait:message messageID="display.att.view.punch.time_edited" module="ar"/></td>
			  <td nowrap="nowrap" class="info_title_01">计算标识
				</td>
			  <td nowrap="nowrap" class="info_title_01">修改者
			  	</td>
		    </tr>
			<c:forEach items="${recordList}" var="oneResult" varStatus="i">
		    <tr nowrap="nowrap" align="center" onClick="band('#E7F0EF','black','${oneResult.RECORD_NO}')"> 
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.EMPID}<input type="hidden" value="${oneResult.RECORD_NO}" name="records" id="records" ></td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.CHINESENAME}&nbsp;</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.DEPTNAME}&nbsp;</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.FOURTHDEPTNAME}&nbsp;</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.CARD_NO}&nbsp;</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.R_TIME}</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.DOOR_TYPE}&nbsp;</td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.INSERT_TIME}&nbsp;</td>
		      <td>
		      <select name="isCaltaFlag_${oneResult.RECORD_NO}">
		      <option value="1" <c:if test="${oneResult.ACTIVE == 1}">selected</c:if>>参与</option>
		      <option value="0" <c:if test="${oneResult.ACTIVE == 0}">selected</c:if>>不参与</option>
		      </select>		     
		      </td>
		      <td nowrap="nowrap" align="center" style="color: #666666;">${oneResult.OPERATOR_NAME}</td>
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		 <input type="hidden" name="RECORD_NO" value="0" />
		 <input type="hidden" name="isCaltaFlag" value="0" />
		 
	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=retrieveAttRecordList&menu_code=${param.menu_code}&DEPTID=${DEPTID}&key=${key}&R_START_TIME=${R_START_TIME}&R_END_TIME=${R_END_TIME}&TABLE_NAME=${TABLE_NAME}" 
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