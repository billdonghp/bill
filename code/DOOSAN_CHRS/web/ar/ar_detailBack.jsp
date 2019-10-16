<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.ArDetail"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="java.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="item" scope="page" class="com.ait.ar.bean.ArItem" />
<jsp:useBean id="arItemList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="detail" scope="page" class="com.ait.ar.bean.ArDetail" />
<jsp:useBean id="detailList" scope="request" class="java.util.ArrayList" />
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>

<script language="javascript">
var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/; 

function Add() {}
function Update() {}
function Delete() {}


function checkDate(objectName)
{
	var r = $F(objectName).match(reg);
	if(r!=null){
		r[2]=r[2]-1 ;
		var d = new Date(r[1], r[2],r[3]); 
		if(d.getFullYear() != r[1] || d.getMonth() != r[2] || d.getDate() != r[3]){
			alert($F(objectName) + " 日期错误!") ;
			$(objectName).focus();
			return false ; 
		}
		else
		{
			return true ;
		}
	}
	else{
		alert("日期格式不对,(9999-12-31)!") ;
	 	$(objectName).focus(); 
		return false ;
	}
}

function Search() {
	
	var sDate = document.form1.sDate.value ; 
	var eDate = document.form1.eDate.value ;
	var sDateU = document.form1.sDateU.value ;
	var eDateU = document.form1.eDateU.value ;
	
	
	if ( sDate == '' && eDate == '' && sDateU == '' && eDateU == ''){
	 	alert("请输入一个搜索的时间段!!!") ;
	 	document.form1.sDate.focus(); 
		return ;
	 }
	 else{
	 		if ((sDate == '' || eDate == '') && (sDate != '' || eDate != '')){
	 			alert("请输入,考勤的时间段,或者不填") ;
	 			if (sDate == '')
	 				document.form1.sDate.focus();
	 			else
	 				document.form1.eDate.focus();
				return ;
	 		}
	 		else if (sDate != '' && eDate != ''){
	 			if (!checkDate("sDate")){
	 				return ;
	 			}
	 			
	 			if (!checkDate("eDate")){
	 				return ;
	 			}
	 		}
	 		
	 		if ((sDateU == '' || eDateU == '') && (sDateU != '' || eDateU != '')){
	 			alert("请输入,修改的时间段,或者不填") ;
	 			if (sDateU == '')
	 				document.form1.sDateU.focus();
	 			else
	 				document.form1.eDateU.focus();
				return ;
	 		}
	 		else if(sDateU != '' && eDateU != '') {
	 			if (!checkDate("sDateU")){
	 				return ;
	 			}
	 			
	 			if (!checkDate("eDateU")){
	 				return ;
	 			}
	 		}
	 }
	
	document.form1.action = "/arControlServlet?operation=arDetialBackList&menu_code=${param.menu_code}&flag=search";
	//alert(document.form1.action) ;
	document.form1.submit();
}

function ImportForExcel() {
	document.form1.action="/arControlServlet?operation=arDetialBackList&menu_code=${param.menu_code}&flag=report";
	document.form1.submit();
}
function checkNumber(id,newValue) {
  if(isNaN(newValue)) {
    document.form1.all(id).value="";
    //"数据应为数值类型"
    alert('<ait:message  messageID="alert.att.maintenance.data_numeric" module="ar"/>');
    return;
  } else {
    document.form1.all(id).value=newValue;
    return;
  }
}
function CheckAll() {
	if (document.form1.details) {
		if (document.form1.details[0]) {
			for (i=0;i<document.form1.details.length;i++)
				document.form1.details[i].checked = document.form1.checkAll.checked;
		} else {
			document.form1.details.checked = document.form1.checkAll.checked;
		}
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
		$('personId').value=cell.childNodes[1].firstChild.nodeValue;
		
		layerClose();
}


</script>
</head>
<body>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");
	String key = StringUtil.toCN(request.getParameter("key"));
%>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_addsavedel.jsp"%>
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
		<form name="form1" method="post" action="">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!--查询条件 -->
        	<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"cellspacing="1" class="dr_d">
		        <tr>
		          <td class="info_title_01">考勤日期</td>
		          <td width="20%" class="info_content_00">
		          <input type="text" size="10" maxlength="10" name="sDate" value="${sDate}" onClick="setday(this);"/>~
		          <input type="text" size="10" maxlength="10" name="eDate" value="${eDate}" onClick="setday(this);"/></td>
		          <td class="info_title_01"><!--部门 --><ait:message messageID="display.mutual.dept" /></td>
				  <td width="20%" class="info_content_00"><ait:selDept name="deptid" selected="${deptid}" all="all"  supervisorType="ar"/></td>
				  <td class="info_title_01"><!--  项目  -->项目</td>
			      <td class="info_content_00">
			          <select name="itemNo">
			              <option value="" >请选择</option>
				      	  <c:forEach items="${arItemList}" var="item" varStatus="i">
					      	  <c:choose>
					      	  	<c:when test="${item.itemNo == itemNo}">
					      	  		<option value="${item.itemNo}" selected >${item.itemName}</option>
					      	  	</c:when>
					      	  	<c:otherwise>
					      	  		<option value="${item.itemNo}" >${item.itemName}</option>
					      	  	</c:otherwise>
					      	  </c:choose>
				      	  </c:forEach>
			      	  </select>
			      </td>
				  <td class="info_content_00" colspan="2" >
			          <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;
			          <ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:ImportForExcel();" style="cursor:hand" />
		          </td>
			    </tr>
			    <tr>
			      <td class="info_title_01">修改日期</td>
		          <td width="20%" class="info_content_00">
		          <input type="text" size="10" maxlength="10" name="sDateU" value="${sDateU}" onClick="setday(this);"/>~
		          <input type="text" size="10" maxlength="10" name="eDateU" value="${eDateU}" onClick="setday(this);"/></td>
			      <td class="info_title_01"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/>
			      <input id="personId" name="personId" type="hidden"/>
			      </td>
		          <td class="info_content_00"><input id="key" name="key" type="text" size="10" value="<%=StringUtil.checkNull(key)%>" onKeyUp="SearchEmp(this.value,this.id)">&nbsp;(<span id="name"></span>)</td>   
			      <td class="info_title_01"><!-- 长度 -->长度</td>
			      <td class="info_content_00"><input name="sLength" type="text" size="2" maxlength="2" value="${sLength}"/>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<input name="eLength" type="text" size="2" maxlength="2" value="${eLength}"/></td>        
		          <td class="info_content_00">
				  		<select name="empType">
				  		    <option value="">请选择</option>
				  		    <option value="office" <c:if test="${empType == 'office'}">selected</c:if> >职员</option>
				  			<option value="work" <c:if test="${empType == 'work'}">selected</c:if> >工人</option>
				  		</select>
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
				<td align="left" class="title1" colspan="10"><!-- 考勤明细-->
        	<ait:message  messageID="display.att.maintenance.maintenance.maintenance" module="ar"/></td>
			</tr>
		  </table>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td class="info_title_01" nowrap="nowrap"><!-- 日期-->
        	<ait:message  messageID="display.mutual.date"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 员工-->
        	<ait:message  messageID="display.mutual.emp_no_name"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 部门-->
        	<ait:message  messageID="display.mutual.department"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 班次-->
        	<ait:message  messageID="display.mutual.shift"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 项目-->
        	<ait:message  messageID="display.att.maintenance.maintenance.item" module="ar"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 时间段-->
        	<ait:message  messageID="display.att.maintenance.maintenance.duration" module="ar"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 长度-->
		      <ait:message  messageID="display.att.maintenance.maintenance.timing" module="ar"/></td>
		      <td nowrap="nowrap" class="info_title_01"><!-- 锁定-->
        	<ait:message  messageID="display.mutual.lock"/></td>
        	<td nowrap="nowrap" class="info_title_01" ><!--创建者-->
        	<ait:message  messageID="display.mutual.created_by"/></td>
		    <td nowrap="nowrap" class="info_title_01" ><!--创建时间-->
		    <ait:message  messageID="report.global.title.creation_date" module="report"/></td>
		    <td nowrap="nowrap" class="info_title_01" >修改原由</td>
		    <td nowrap="nowrap" class="info_title_01" >操作</td>
		    </tr>
		<%String ar_date_str = "";
		  int num = -1;
		for(int i=0;i<detailList.size();i++) {
			detail = (ArDetail) detailList.get(i);
			if (detail.getDate_type() == 1) {
		  		ar_date_str = detail.getAr_date_str();
		  	} else {
		  		ar_date_str = "<font color=\"red\">" + detail.getAr_date_str() + "</font>";
		  	}%>
		    <tr align="center">
		      <td><%=ar_date_str%></td>
		      <td>(<%=detail.getEmpID()%>)&nbsp;<%=detail.getEmpName()%></td>
		      <td nowrap="nowrap"><%=detail.getDeptName()%></td>
		      <td><%=detail.getShiftName()%></td>
		      	
		      <td><%=detail.getItemName() %></td>
		      <td><%=StringUtil.checkNull(detail.getFromTime(),"未刷卡")%><br><%=StringUtil.checkNull(detail.getToTime(),"未刷卡")%></td>
		      	 
		      <td><%=detail.getQuantity()%>&nbsp;<%=detail.getUnitName()%> </td>
		      <td>      	
		      	<%=detail.getIsLock().equals("Y") ? "<font color='red'>锁定</font>" : "开放"%>
		      </td>
		      <td>
		      	<%if(detail.getCreateBy() == null){ %>
		      		&nbsp;
		      	<%}else{ %>    	
		      		<%=detail.getCreateBy() %>
		      	<%} %>
		      </td>
		      <td>      	
		      	<%=detail.getCreateDate() %>
		      </td>
		      <td>
		      <%=detail.getRemark()!=null?detail.getRemark():"" %>
		      </td>
		      <td>
		      	<% 
		      	if (detail.getOperation() != null && detail.getOperation().equals("CREATE"))
		      	{
		      		out.println("添加") ;
		      	}
		      	else if (detail.getOperation() != null && detail.getOperation().equals("UPDATE"))
		      	{
		      		out.println("修改") ;
		      	}
		      	else if (detail.getOperation() != null && detail.getOperation().equals("DELETE"))
		      	{
		      		out.println("删除") ;
		      	}else
		      	{
		      		out.println("结果");
		      	}
		      	%>&nbsp;
		      </td>
		      
		      
		    </tr>
		<%}%>
		      <!-- Page Navigation Start-->
			        <ait:page       
			               link="/arControlServlet"
			               parameters="operation=arDetialBackList&menu_code=${param.menu_code}&sDate=${sDate}&eDate=${eDate}&sDate=${sDateU}&eDate=${eDateU}&key=${key}&deptid=${deptid}&itemNo=${itemNo}&sLength=${sLength}&eLength=${eLength}&empType=${empType}&personId=${personId}" 
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
			<c:if test="${fn:length(detailList) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(detailList)}"
					step="1">
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
</body>
</html>
