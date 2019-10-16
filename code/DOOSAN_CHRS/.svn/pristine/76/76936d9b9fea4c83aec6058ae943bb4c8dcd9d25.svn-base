<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sy.bean.AdminBean"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="detailSupervisorLockList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="detailLockList" scope="request" class="java.util.ArrayList" />

<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
</style>
<title>日考勤锁定</title>
<SCRIPT type="text/javascript">
var attMoFlag = '${ATT_MO_FLAG}' ;

function Search() {

  document.form1.action="/arControlServlet?operation=retrieveDetailSupervisorLockList&menu_code=${param.menu_code}&actionType=search";
  document.form1.submit();
}

function dayCheckAll() {
	if (document.form1.days) {
		if (document.form1.days[0]) {
			for (i=0;i<document.form1.days.length;i++)
				document.form1.days[i].checked = document.form1.dayCheck.checked;
		} else {
			document.form1.days.checked = document.form1.dayCheck.checked;
		}
	}
}

function supervisorCheckAll() {
	var selected = document.form1.supervisorCheck.value == "0" ? true : false;
    var size = document.form1.supervisorId.length ;
    if (size != null){
	  	for (var i = 0; i< size; i++){
			document.form1.supervisorId[i].checked = selected ;
		
		}
	}
	else
	{
		document.form1.supervisorId.checked = selected ;
	}
    document.form1.supervisorCheck.value = selected ? "1" : "0";
}

function Update() {
	
	if (attMoFlag == 1)
	{
		alert("当月明细已经锁定!!!") ;
		return ;
	}

	var empCnt = 0 ;
	
	var size = document.form1.supervisorId.length ;
	if (size != null){
	  	for (var i = 0; i< size; i++){
			if (document.form1.supervisorId[i].checked)
			{
				empCnt++ ;
			}
		}
	}
	else
	{
		if (document.form1.supervisorId.checked)
		{
			empCnt++ ;
		}
	}
	
	if (empCnt == 0){
		alert("请选择要修改的考勤员!!!") ;
		if(size != null){
			document.form1.supervisorId[0].focus() ;
		}
		else{
			document.form1.supervisorId.focus() ;
		}
		
		return ;
	}
	else if (empCnt > 1){
		alert("只能选择一个考勤员,进行修改!!!") ;
		if(size != null){
			document.form1.supervisorId[0].focus() ;
		}
		else{
			document.form1.supervisorId.focus() ;
		}
		return ;
	}
	
	document.form1.action="/arControlServlet?operation=retrieveDetailSupervisorLockList&menu_code=${param.menu_code}&actionType=update";
	document.form1.submit();

}

function Save() {
	
	if (attMoFlag == 1)
	{
		alert("当月明细已经锁定!!!") ;
		return ;
	}
	
	var dayFlag = false ;
	
	if (document.form1.days) {
		if (document.form1.days[0]) {
			for (i=0;i<document.form1.days.length;i++){
				if (document.form1.days[i].checked){
					dayFlag = true ;
					break ;
				}
			}
		}
	}
	
	if (!dayFlag)
	{
		alert("请选择要修改的日期!!!") ;
		document.form1.days[0].focus() ;
		return ; 
	}
	
	var supervisorFlag = false ;
	
	if (document.form1.supervisorId) {
		if (document.form1.supervisorId[0]) {
			for (i=0;i<document.form1.supervisorId.length;i++){
				if (document.form1.supervisorId[i].checked){
					supervisorFlag = true ;
					break ;
				}
			}
		}
		else
		{
			if (document.form1.supervisorId.checked){
					supervisorFlag = true ;
				}
		}
	}
	
	if (!supervisorFlag)
	{
		alert("请选择要修改的考勤员!!!") ;
		return ; 
	}
	
	if (document.form1.lockFlag.value == -1)
	{
		alert("请选择锁定的类型!!!") ;
		document.form1.lockFlag.focus() ;
		return ;
	}
	
	document.form1.action="/arControlServlet?operation=updateDetailSupervisorLock&menu_code=${param.menu_code}&actionType=search";
	document.form1.submit();
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
		$('personId').value = "" ; 
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-80;    //文本框的定位点宽
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
		$('name').innerHTML=cell.childNodes[1].firstChild.nodeValue;
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}

</SCRIPT>
</head>
<body>
<%
HttpSession session1 =request.getSession(true);
AdminBean admin1=(AdminBean)session1.getAttribute("admin");
request.setAttribute("cpnyDiff",admin1.getCpnyId());
 %>
<form  name="form1" method="post" action="" > 
<input type="hidden" name="supervisorCheck" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_s_5.jsp"%>
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
		      <td class="info_title_01"  ><!--考勤月-->
				<ait:message messageID="display.att.view.monthly.monthly" module="ar"/></td>
		      <td  class="info_content_00"><ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" /></td>
		      <td class="info_title_01" >员工区分</td>
		      <td class="info_content_00"><ait:empDiff  name="statTypeCode"  cpnyDiff="${cpnyDiff}" selected="${statTypeCode}" /></td>
		      <td class="info_title_01"><!--部门 --><ait:message messageID="display.mutual.dept" /></td>
				  <td class="info_content_00"><ait:selDept name="deptid" selected="${deptid}" all="all" supervisorType="ar"/></td>
		      <td class="info_title_01"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/></td>
		      <td class="info_content_00"><input id="key" name="key" type="text" size="10" value="${key}" onKeyUp="SearchEmp(this.value,this.id)">&nbsp;(<span id="name"></span>)</td>   
		      <input id="personId" name="personId" type="hidden" value=""/>
		      <td class="info_content_00" nowrap="nowrap">日期全选<input type="checkbox" name="dayCheck" onClick="dayCheckAll();" class="check"></td>
		    </tr>
		    </table>
	      </td>
		</tr>
		</table>
 		<br>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="5">
		  <tr>
		    <td  class="title1">日考勤考勤员锁定&nbsp;&nbsp;&nbsp;&nbsp;
		    	<select name="lockFlag">
		    		<option value="-1">请选择</option>
					<option value="0">未锁</option>
					<option value="1">锁定</option>
				</select>
			</td> 
		  </tr>
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center">
		      <td class="info_title_01" height="30"><a href="#" onclick="supervisorCheckAll();" style="color:red;">全<br>选</a></td>
		      <td class="info_title_01" height="30">职号</td>
		      <td class="info_title_01" height="30">姓名</td>
		      <td class="info_title_01" height="30">部门</td>
			  <% for(int i = 0 ;i < detailLockList.size() ; ++i){
			  		SimpleMap calenderMap = (SimpleMap)detailLockList.get(i); %>
			  <td class="info_title_02" height="30">
			  <input type="checkbox" name="days" value="<%= calenderMap.getString("AR_DATE_STR") %>">
			  <br><%= calenderMap.getString("IDAY") %></td>
			  <% } %>
		    </tr>
			<%
		if (detailSupervisorLockList.size()>0) {
			for(int i = 0 ;i < detailSupervisorLockList.size() ; ++i){
				SimpleMap supervisorMap = (SimpleMap)detailSupervisorLockList.get(i);
		%>
		<tr align="center">
			<td height="30"><input type="checkbox" name="supervisorId" value="<%= supervisorMap.getString("PERSON_ID") %>" class="check"/></td>
			<td id="leftnewstd"> <span class=ellipsis_row title='<%= supervisorMap.getString("EMPID") %>'><%= supervisorMap.getString("EMPID") %></span></td>
			<td><%= supervisorMap.getString("CHINESENAME") %></td>
			<td id="leftnewstd"> <span class=ellipsis_row title='<%= supervisorMap.getString("DEPTNAME") %>'><%= supervisorMap.getString("DEPTNAME") %></span></td>
			<%
				for(int j = 0 ;j < detailLockList.size() ; ++j){
			  		SimpleMap calenderMap = (SimpleMap)detailLockList.get(j);
			%>
			<td>
			<%= supervisorMap.getInt(calenderMap.getString("AR_DATE_STR")) == 1 ? "锁" : "未" %>
			</td>
			<%
				}
			%>
		</tr>
		<% 
			}
		} else {%>
			<tr align="center">
				<td height="30" width="100%" colspan="7" align="center"><font color="red">请按条件进行搜索!!!</font></td>
			</tr>
		<%}%>
		<c:if test="${fn:length(detailSupervisorLockList) < 6}">
			<c:forEach var="i" begin="1" end="${6-fn:length(detailSupervisorLockList)}" step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
		</c:if>
		</table>
		
		<!-- Page Navigation Start-->
			        <ait:page       
			               link="/arControlServlet"
			               parameters="&operation=retrieveDetailSupervisorLockList&menu_code=${param.menu_code}&key=${key}&deptid=${deptid}&personId=${personId}&year=${year}&month=${month}&statTypeCode=${statTypeCode}&actionType=search" 
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
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
	</div>
</form>
</body>
</html>
