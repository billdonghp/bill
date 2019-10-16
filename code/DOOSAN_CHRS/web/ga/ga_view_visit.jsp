<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>访问管理查询</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ExitDoor(i) {

	document.form1["out_time_" + i].value="1";
	document.form1["out_confirm_" + i].value="1";
	i = i - 1;
	document.form1.id[i].checked = true;
	
	document.form1.action="/gaControlServlet?operation=modify&content=visitmodify&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}

function Add() {

	document.form1.action="/ga/ga_add_visit.jsp?menu_code=${param.menu_code}";
	document.form1.submit();
}
function Update() {
	var existSelected = false;
	for(var i=0;i<document.form1.elements.length;i++)    
	  {
	    if(document.form1.elements[i].type=="checkbox" 
	        && document.form1.elements[i].checked && !document.form1.elements[i].disabled)
	    {
	      existSelected = true;
	      } 	        
	    }
	if (!existSelected){
      alert('<ait:message  messageID="alert.att.select_item_edited" module="ar"/>');
      return;
    }
	
	document.form1.action="/gaControlServlet?operation=modify&content=visitmodify&menu_code=${param.menu_code}";
	document.form1.fireSubmit();
}

function Search() {

  if(document.form1.minute.value!=""&&(document.form1.hour.value.length!=2||document.form1.hour.value > 23))
  {//"请正确输入2位小时时间"
  	alert('<ait:message  messageID="alert.ga.hour_correct" module="ga"/>');
	return;
  }
  
   if(document.form1.minute.value!=""&&(document.form1.minute.value.length!=2||document.form1.minute.value>59))
  {//"请正确输入2位分钟时间"
  	alert('<ait:message  messageID="alert.ga.minute_correct" module="ga"/>');
	return;
  }
  
	document.form1.action="/gaControlServlet?operation=view&content=visitlist&menu_code=${param.menu_code}";
	document.form1.submit();
}

function CheckAll() {
	if (document.form1.id) {
		if (document.form1.id[0]) {
			for (i=0;i<document.form1.id.length;i++)
				document.form1.id[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.id.checked = document.form1.checkAll.checked;
		}
	}
}

function exportExcel()
{
    form1.action = "/arControlServlet?operation=exportEateryRecordReport&menu_code=${param.menu_code}";
    form1.submit();    
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
					},200);  
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=allEmpList&condition=" + encodeURIComponent(condition)+"&id="+id;
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
	
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[3].firstChild.nodeValue;
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

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell,id) {
		document.getElementById(id).value=cell.childNodes[1].firstChild.nodeValue;
		layerClose();
}
</SCRIPT>

<body>

<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar.jsp"%>
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
					 <td width="10%" class="info_title_01">
						<ait:message messageID="display.ga.visit.in_time" module="ga"/></td>
			          <td width="20%" class="info_content_00">
				          <input name="in_date" type="text" size="11" value="${key}"  onClick="setday(this);"><br>
			      		  <input name="hour" type="text" size="2" maxlength="2">
		          		  :
						  <input name="minute" type="text"  size="2" maxlength="2"></td>
			          <td width="10%" class="info_title_01">
						<ait:message messageID="display.ga.visit.visitor_name" module="ga"/></td>
			          <td width="10%" class="info_content_00"><input name="s_visitor_name" type="text" size="10"></td>
					  <td width="10%" class="info_title_01">
						<ait:message  messageID="display.ga.visit.visited_name" module="ga"/></td>
			          <td width="10%" class="info_content_00"><input type="text" size="10" name="s_visited_name"/></td>
					  <td width="60%" class="info_content_01">
					  	<ait:image src="/images/btn/Search.gif"  border="0" align="left" onclick="javascript:Search();" style="cursor:hand"/><%--&nbsp;&nbsp;&nbsp;&nbsp;<ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand"/>
					  	--%></td>
			    </tr>
			</table>
	      </td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				<ait:message messageID="display.ga.view" module="ga"></ait:message>
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1"cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="1%" class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" /></td>
		      <td width="4%" class="info_title_01">
				<ait:message messageID="display.ga.visit.visitor_name" module="ga"/></td>
		      <td width="8%" class="info_title_01">
				<ait:message messageID="display.ga.visit.visitor_company" module="ga"/></td>
		      <td width="13%" class="info_title_01">
				<ait:message messageID="display.ga.visit.visitor_idcard" module="ga"/></td>
		      <td width="8%" class="info_title_01">
				<ait:message messageID="display.ga.visit.visitor_auto" module="ga"/></td>
		      <td width="4%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.visited_name" module="ga"/></td>
		      <td width="8%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.visit_reason" module="ga"/></td>
		      <td width="11%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.in_time" module="ga"/></td>
		      <%--<td width="6%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.in_confirm" module="ga"/></td>
			  --%><td width="11%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.out_time" module="ga"/></td>		
		      <td width="6%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.out_confirm" module="ga"/></td>
		      <td width="8%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.out_cardid" module="ga"/></td>
			  <td width="7%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.brief" module="ga"/></td>
			  <td width="4%" class="info_title_01">
					<ait:message  messageID="display.ga.visit.operation_user" module="ga"/></td>	
		    </tr>

			<c:forEach items="${visitList}" var="oneResult" varStatus="i">
		    <tr align="center">
		      <td height="30" align="center" style="color: #666666;"><input type="checkbox" value="${i.count}" name="id" id="id"><input type="hidden" value="${oneResult.ID}" name="id_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="4%" value="${oneResult.VISITOR_NAME}" name="visitor_name_${i.count}" required></td>
		      <td align="center" style="color: #666666;"><input type="text" size="8%" value="${oneResult.VISITOR_COMPANY}" name="visitor_company_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="18%" value="${oneResult.VISITOR_IDCARD}" name="visitor_idcard_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="8%" value="${oneResult.VISITOR_AUTO}" name="visitor_auto_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="4%" value="${oneResult.VISITED_NAME}" name="visited_name_${i.count}" id="visited_name_${i.count}" onKeyUp="SearchEmp(this.value,this.id)" required>
		      	<input type="hidden" value="${oneResult.VISITED_EMPID}" name="visited_empid_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="8%" value="${oneResult.VISIT_REASON}" name="visit_reason_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="15%" value="${oneResult.IN_TIME}" name="in_time_${i.count}" required></td>
		      <%--<td align="center" style="color: #666666;"><input type="text" size="6%" value="${oneResult.IN_CONFIRM}" name="in_confirm_${i.count}" id="in_confirm_${i.count}" onKeyUp="SearchEmp(this.value,this.id,${i.count})" required></td>
		      --%><td align="center" style="color: #666666;">
			      <c:if test="${oneResult.OUT_CONFIRM == '0'}">
			      		<input type="hidden" size="15%" value="" name="out_time_${i.count}">
			      </c:if>		
			      <c:if test="${oneResult.OUT_CONFIRM != '0'}">
			      		<input type="text" size="15%" value="${oneResult.OUT_TIME}" name="out_time_${i.count}" required>
			      </c:if>
		      </td>
		      <td align="center" style="color: #666666;">
			      <c:if test="${oneResult.OUT_CONFIRM == '0'}">
			      		<img src="/images/btn/0.gif" style="cursor:hand " onClick="ExitDoor('${i.count}');">
			      		<input type="hidden" size="6%" value="0" name="out_confirm_${i.count}">
			      </c:if>		
			      <c:if test="${oneResult.OUT_CONFIRM != '0'}">
			      		<input type="text" size="6%" value="${oneResult.OUT_CONFIRM}" id="out_confirm_${i.count}" name="out_confirm_${i.count}" onKeyUp="SearchEmp(this.value,this.id)" required>
			      </c:if>
		      </td>
		      <td align="center" style="color: #666666;"><input type="text" size="8%" value="${oneResult.OUT_CARDID}" name="out_cardid_${i.count}"></td>
		      <td align="center" style="color: #666666;"><input type="text" size="7%" value="${oneResult.BRIEF}" name="brief_${i.count}"></td>
		      <td align="center" style="color: #666666;">${oneResult.OPERATION_USER}</td>
		    </tr>
		    </c:forEach>
		    <tr align="center"> 
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
<ait:xjos />
</html>
