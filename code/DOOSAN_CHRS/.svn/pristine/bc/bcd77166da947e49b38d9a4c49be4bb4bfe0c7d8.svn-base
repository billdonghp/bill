<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>考勤设置&gt;加班上限设置</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function Add() {
	document.form1.action="/ar/ar_add_otplan.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
	if(document.form1.NO.value == 0) {
		// "请选择修改项目"
	    alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
		return;
	}
	document.form1.action="/arControlServlet?operation=retrieveDataForUpdateOTPlan&menu_code=${param.menu_code}&currentPage=${currentPage}&ot_plan_no=" + document.form1.NO.value;
	document.form1.submit();
}
function Delete() {
	if(document.form1.NO.value == 0){
		alert("请选择删除项目!");
		return ;
	}
	if(!confirm("确认删除?")){
		return;
	}
	document.form1.action="/arControlServlet?operation=ar_otplan_del&menu_code=${param.menu_code}&currentPage=${currentPage}&ot_plan_no=" + document.form1.NO.value;
	document.form1.submit();
}
function Search() {
	document.form1.action="/arControlServlet?operation=ar_otplan&menu_code=${param.menu_code}";
	document.form1.submit();
}

function downloadExcelWindow()
{
    form1.action = "/arControlServlet?operation=downloadOTPlanTemplate&menu_code=${param.menu_code}";
    form1.submit();    
}

function importExcelWindow()
{
	url="/arControlServlet?operation=importOTPlanReport$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
	
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
	document.form1.NO.value=i;
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
		$('personId').value= "" ;
		
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
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
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
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
			<td class="title1"><!--查询条件--><ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	      		<tr>
					 <td width="10%" class="info_title_01"><!--部门-->
					<ait:message  messageID="display.mutual.department"/></td>
			          <td width="20%" class="info_content_00"><ait:selDept name="deptid" selected="${deptid}" all="all"  supervisorType="ar"/></td>
			          <td width="10%" class="info_title_01"><!--姓名/工号-->
					<ait:message  messageID="display.mutual.emp_no_name"/></td>
			          <td width="15%" class="info_content_00"><input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)"><input id="personId" name="personId" type="hidden"/></td>
					  <td width="20%" class="info_content_01"><ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand"/></td>
			          <td width="45%" class="info_content_01" colspan="3">
						  <ait:image src="/images/btn/Template.gif"   border="0" align="absmiddle" onclick="javascript:downloadExcelWindow();" style="cursor:hand"/> |
						  <ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcelWindow();"   style="cursor:hand"/>
					  </td> 
			    </tr>
	       		
	       		 <!--
					<td width="10%" class="info_title_01"><!-- 查询年月--<ait:message messageID="display.att.view.individual.timing" module="ar"/></td>
			          <td width="20%" class="info_content_00" style="padding-top: 2px;padding-bottom: 2px;">
			          	<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
			          </td>
			     -->
					  
			
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
				<td align="left" class="title1" colspan="10">个人加班上限记录</td>
			</tr>
		</table>
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01" nowrap><!-- 职号 --><ait:message  messageID="display.mutual.emp_id"/></td>
		      <td class="info_title_01" nowrap><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		      <td class="info_title_01" nowrap><!-- 部门 --><ait:message  messageID="display.mutual.department"/></td>
		      <td class="info_title_01" nowrap><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		      <td class="info_title_01" nowrap><!-- 申请限制时间 -->申请限制时间</td>
		      <!--<td class="info_title_01" nowrap>加班月份</td>-->
		      <td class="info_title_01" nowrap>开始月</td>
		      <td class="info_title_01" nowrap>结束月</td>
		      <td class="info_title_01" nowrap>加班上限</td>
		      <td class="info_title_01" nowrap><!--创建者--><ait:message  messageID="display.mutual.created_by"/></td>
		      <td class="info_title_01" nowrap><!--创建时间--><ait:message  messageID="report.global.title.creation_date" module="report"/></td>
		      <td class="info_title_01" nowrap><!--修改者-->修改者</td>
		      <td class="info_title_01" nowrap><!--修改时间--><ait:message  messageID="display.att.setting.dayoff.edit_time" module="ar"/></td>
		    </tr>
			<c:forEach items="${otPlanList}" var="oneResult" varStatus="i">
		    <tr align="center" onClick="band('#E7F0EF','black',${oneResult.OT_PLAN_NO})" > 
		      <td height="40" align="center" style="color: #666666;">${oneResult.EMPID}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.CHINESENAME}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.DEPTNAME}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.POST}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.APPLY_LIMTIDTIME}&nbsp;</td>
		      <!--<td align="center" style="color: #666666;" height="35">${oneResult.PLAN_MONTH}&nbsp;</td>-->
		      <td align="center" style="color: #666666;" height="35">${oneResult.START_MONTH}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.END_MONTH}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.LIMIT_OT}&nbsp;<c:if test="${oneResult.LIMIT_OT ne null}"><!--小时--><ait:message  messageID="report.global.title.hour" module="report"/></c:if></td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.CREATED_BY}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.CREATE_DATE}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.UPDATED_BY}&nbsp;</td>
		      <td align="center" style="color: #666666;" height="35">${oneResult.UPDATE_DATE}&nbsp;</td>	      
		    </tr>
		    </c:forEach>
		    <input type="hidden" name="NO" value="0" />
		    <tr align="center"> </tr>
		 </table>

	 		      <!-- Page Navigation Start-->
		        <ait:page       
		               link="/arControlServlet"
		               parameters="&operation=ar_otplan&menu_code=${param.menu_code}&year=${year}&month=${month}&deptid=${deptid}&key=${key}&personId=${personId}" 
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
</html>