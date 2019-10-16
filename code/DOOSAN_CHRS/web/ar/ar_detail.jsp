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
<jsp:useBean id="sDate" scope="request" class="java.lang.String" />
<jsp:useBean id="eDate" scope="request" class="java.lang.String" />
<html>
<head>
<!-- ar_detail.jsp -->
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>

<script language="javascript">
function Add() {
	var flag = false ;
	
	if(document.form1.details == null)
	{
		return ;
	}
	
	for (i=0;i<document.form1.details.length;i++){
		if(document.form1.details[i].checked){     
			flag = true;   
			break ;
		}
	}
	
	if(document.form1.details.checked)
	{
		flag = true;
	}
	
	if (!flag)
	{//"请选择要添加的数据!!"
		alert("请选择要添加的数据!") ;
		return ;
	}
	document.form1.action="/arControlServlet?operation=ar_add&content=detail&menu_code=<%=request.getParameter("menu_code")%>&currentPage=<%=request.getAttribute("currentPage")%>";
	document.form1.submit();
}
function Update() {//该方法暂时没有使用，update方法在common_toolbar_addsavedelapply.jsp中调用
    
    
	
	var flag = false ;
	if(document.form1.details == null)
	{
		return ;
	}
	for (i=0;i<document.form1.details.length;i++){
		if(document.form1.details[i].checked){     
			flag = true;   
			break ;
		}
	}
	
	if(document.form1.details.checked)
	{
		flag = true;
	}
	

	
	
	if (!flag)
	{//"请选择要修改的数据!!"
		alert("请选择要修改的数据!!") ;
		return ;
	}
	
	var s = document.getElementsByName("details");
	
	for( var i = 0; i < s.length; i++ )
	{
	  if ( s[i].checked ){
	       
	       ///if(($('deptid' + i).value == '780B6100') || ($('deptid' + i).value == '780B6200') || ($('deptid' + i).value == '780B6300') || ($('deptid' + i).value == '780B6000')){
			if($('flag' + i).value == 0 ){
				alert("未决裁的考勤不能修改！");
				return;
			}
			
			if($('flag' + i).value == 1 || $('flag' + i).value == 2 ){
				alert("已通过和否决的考勤不能再次修改，只可以删除！");
				return;
			}
		  ///}	
	  }
	}
	
	document.form1.action="/arControlServlet?operation=ar_modify&content=detail&menu_code=<%=request.getParameter("menu_code")%>&currentPage=<%=request.getAttribute("currentPage")%>";
	document.form1.submit();
}
function Delete() {
	var flag = false ;
	if(document.form1.details == null)
	{
		return ;
	}
	for (i=0;i<document.form1.details.length;i++){
		if(document.form1.details[i].checked){     
			flag = true;   
			break ;
		}
	}
	
	if(document.form1.details.checked)
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
		document.form1.action="/arControlServlet?operation=ar_delete&content=detail&menu_code=<%=request.getParameter("menu_code")%>&currentPage=<%=request.getAttribute("currentPage")%>";
		document.form1.submit();
	}
}
function Search() {
	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=search";
	document.form1.submit();
}

function sendMailAll(){
	
	if (document.form1.mailSize.value == 0)
	{
		alert("请先进行查询，再发送邮件!!!") ;
		return ;
	}

	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=arEmaiSend";
	document.form1.submit();
	
	
	
}

function ImportForExcel() {
	document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&flag=report";
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
		$('personId').value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
}
function downloadExcelWindow()
{ 
    document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=downloadArOtDetailTemplate&menu_code=ar0201";
	document.form1.submit();
}

function importExcelWindow()
{
	url="/arControlServlet?operation=importArOtDetailReport$menu_code=${param.menu_code}";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
	
}

function importExcelNextWindow()
{
	url="/arControlServlet?operation=importArOtDetailReport$menu_code=${param.menu_code}$flag=next";
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');
	
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
			<%@ include file="../inc/common_toolbar_addsavedelapply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
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
		          <td class="info_title_01"><!--开始日期--><ait:message  messageID="display.mutual.start_date"/></td>
		          <td class="info_content_00"><input type="text" size="10" maxlength="10" name="sDate" value="<%=sDate%>" readonly onClick="setday(this);"/></td>
		          <td class="info_title_01"><!--结束日期--><ait:message  messageID="display.mutual.end_date"/></td>
		          <td class="info_content_00"><input type="text" size="10" maxlength="10" name="eDate" value="<%=eDate%>" readonly onClick="setday(this);"/></td>
		          <td class="info_title_01"><!--部门 --><ait:message messageID="display.mutual.dept" /></td>
				  <td class="info_content_00"><ait:selDept name="deptid" selected="${deptid}" all="all" supervisorType="ar"/></td>
				  <td class="info_title_01"><!--表区分 -->表区分</td>
				  <td class="info_content_00" >
				  	<select name="tableName">
				  		<option value="AR_DETAIL"  <c:if test="${tableName eq 'AR_DETAIL'}">selected</c:if> >主表</option>
				  		<option value="AR_DETAIL_HISTORY" <c:if test="${tableName eq 'AR_DETAIL_HISTORY'}">selected</c:if>>分表</option>
				  	</select> </td>
				  <td class="info_content_00">
		          <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />
		          </td>
		          <% if("60000000".equals(admin1.getCpnyId())){%> 	
		          <td class="info_content_01" > 
						  <ait:image src="/images/btn/Template.gif"   border="0" align="absmiddle" onclick="javascript:downloadExcelWindow();" style="cursor:hand"/> 
				   </td> 
				  <%} %>
		        </tr>
			    </tr>
			    <tr>
			      <td class="info_title_01"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/></td>
		          <td class="info_content_00"><input id="key" name="key" type="text" size="10" value="<%=StringUtil.checkNull(key)%>" onKeyUp="SearchEmp(this.value,this.id)">&nbsp;(<span id="name"></span>)</td>   
		          <input id="personId" name="personId" type="hidden" value=""/>
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
			      <td class="info_title_01"><!-- 长度 -->长度</td>
			      <td class="info_content_00"><input name="sLength" type="text" size="2" maxlength="2" value="${sLength}"/>
			      &nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;
			      <input name="eLength" type="text" size="3" maxlength="3" value="${eLength}"/>(小时/分钟)</td>        
		          <td class="info_title_01">区分</td>
				  <td class="info_content_00">
				  		<select name="empType">
				  		    <option value="">请选择</option>
				  		    <option value="office" <c:if test="${empType == 'office'}">selected</c:if> >职员</option>
				  			<option value="work" <c:if test="${empType == 'work'}">selected</c:if> >工人</option>
				  		</select>
				  </td>
				  <td class="info_content_00">
		          <ait:image src="/images/btn/Excel_Exp.gif"  border="0" align="absmiddle" onclick="javascript:ImportForExcel();" style="cursor:hand" />
		          </td>
		           <% if("60000000".equals(admin1.getCpnyId())){%> 	
		          <td class="info_content_01" >
						 
						  <ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcelWindow();"   style="cursor:hand"/>
				   </td> 
				  <%} %>
				
				
		        </tr>
		      </table>
	      </td>
		</tr>
		</table>
		
		<c:if test="${errorList != null}">
			<table>
				<c:forEach var="error" items="${errorList}">
					<tr>
						<td><font color="red">${error}</font></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${info != null}">
		    <script lang="javascript">
			alert('${info}');		
		    </script>
		
		</c:if>
		
	
		<!-- test display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		   <input type="hidden" name="mailSize" value="${fn:length(detailList)}"/>  
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤明细-->
        	<ait:message  messageID="display.att.maintenance.maintenance.maintenance" module="ar"/></td>
			</tr>
		  </table>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td width="5%" class="info_title_01"><input type="checkbox" name="checkAll" onClick="CheckAll();" class="check"/></td>
		      <td width="10%" class="info_title_01"><!-- 日期-->
        	<ait:message  messageID="display.mutual.date"/></td>
		      <td width="10%" class="info_title_01"><!-- 员工-->
        	<ait:message  messageID="display.mutual.emp_no_name"/></td>
		      <td width="10%" class="info_title_01"><!-- 部门-->
        	<ait:message  messageID="display.mutual.department"/></td>
		      <td width="10%" class="info_title_01"><!-- 班次-->
        	<ait:message  messageID="display.mutual.shift"/></td>
		      <td width="10%" class="info_title_01"><!-- 项目-->
        	<ait:message  messageID="display.att.maintenance.maintenance.item" module="ar"/></td>
		      <td width="19%" class="info_title_01"><!-- 时间段-->
        	<ait:message  messageID="display.att.maintenance.maintenance.duration" module="ar"/></td>
		      <td width="10%" class="info_title_01"><!-- 长度-->
        	<ait:message  messageID="display.att.maintenance.maintenance.timing" module="ar"/></td>
        	<!--<td width="8%" class="info_title_01"> 夜班夜班</td>
		      --><td width="8%" class="info_title_01"><!-- 锁定-->
        	<ait:message  messageID="display.mutual.lock"/></td>
        	
        	<% if("78000000".equals(admin1.getCpnyId()) || "61000000".equals(admin1.getCpnyId()) || "59000000".equals(admin1.getCpnyId()) || "63000000".equals(admin1.getCpnyId())){%> 	
        	  <td width="10%" class="info_title_01"><!-- 长度-->
        	修改原由
        	</td>
        	<%} %>
        	<% if("78000000".equals(admin1.getCpnyId()) || "59000000".equals(admin1.getCpnyId()) || "63000000".equals(admin1.getCpnyId())){%> 	
        	 <td width="10%" class="info_title_01"><!-- 长度-->
        	考勤修改<br>
        	决裁情况</td>
        	<td width="10%" class="info_title_01"><!-- 长度-->
        	支社考勤<br>
        	确认情况</td>
		    </tr>
		    <%} %>
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
		      <%if (detail.getStatus() == 0 ){ num++; %>  
		      <td><input type="checkbox" name="details" value="<%=detail.getPkNo()%>" class="check"/>
		           <input name="flag<%=num%>" type="hidden" value="<%=detail.getFlag()%>" >
		          <input name="deptid<%=num%>" type="hidden" value="<%=detail.getDeptId()%>" >   
		          <input name="cpnyId<%=num%>" type="hidden" value="<%=admin1.getCpnyId()%>" >   
		      </td>
		      <%}else{%>
		      <td>&nbsp</td>
		      <%} %>
		      <td><%=ar_date_str%></td>
		      
		      <% if(detail.getIsLock().equals("Y") && "Y".equals(detail.getAbnormalFlag())){%>
		      <td><font color='red'>(<%=detail.getEmpID()%>)&nbsp;<%=detail.getEmpName()%></font></td>
		      <%}else{%>
		      <td>(<%=detail.getEmpID()%>)&nbsp;<%=detail.getEmpName()%></td>
		      <%}%>
		      
		      <td nowrap="nowrap"><%=detail.getDeptName()%></td>
		      <td><%=detail.getShiftName()%></td>
			  
		      <td><select name="item_<%=detail.getPkNo()%>" onChange="javascript:if(document.form1.details[<%=num%>]){document.form1.details[<%=num%>].checked=true;}else{document.form1.details.checked=true;};" >
		      	<%for (int j=0;j<arItemList.size();j++) {
		      		item = (ArItem) arItemList.get(j); %>
					<option value="<%=item.getItemNo()%>"<%=item.getItemNo()==detail.getItemNo()?" selected":""%>>
							<%=item.getItemName()%>					
					</option>
		      	<%}%>
		      </select></td>
		      <td><% if(admin1.getLanguagePreference().equals("zh")){%>
		      		<%=StringUtil.checkNull(detail.getFromTime(),"未刷卡")%><br><%=StringUtil.checkNull(detail.getToTime(),"未刷卡")%></td>
		      	  <%}else{%>
		      	  	<%=StringUtil.checkNull(detail.getFromTime(),"No Record")%><br><%=StringUtil.checkNull(detail.getToTime(),"No Record")%></td>
		      	  <%}%>
		      </td>
		      <td>
		      <input type="text" name="quantity_<%=detail.getPkNo()%>" value="<%=detail.getQuantity()%>" style="width:30px;" onChange="javascript:if(document.form1.details[<%=num%>]){document.form1.details[<%=num%>].checked=true;}else{document.form1.details.checked=true;};"/>
		      &nbsp;<%=detail.getUnitName()%>
		      </td>
		      <input name="night_<%=detail.getPkNo()%>" type="hidden" value="N">
		     <td>      	
		      	<%=detail.getIsLock().equals("Y") ? "<font color='red'>锁定</font>" : "开放"%>
		      </td>
		        <% if("78000000".equals(admin1.getCpnyId()) || "61000000".equals(admin1.getCpnyId()) || "59000000".equals(admin1.getCpnyId()) || "63000000".equals(admin1.getCpnyId())){%> 
		      
		       <td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark_<%=detail.getPkNo()%>" style=" height: 25px;width:100px " type="_moz" 
						onfocus="this.style.height='50px'" onblur="this.style.height='25px';"><%=StringUtil.checkNull(detail.getRemark())%></textarea>
				</td>
		        <%}%>
		        	
		      
		       <% if("78000000".equals(admin1.getCpnyId()) || "59000000".equals(admin1.getCpnyId()) || "63000000".equals(admin1.getCpnyId())){%>   
		       <td>  
		      <% if(detail.getFlag()==1){%> 	
		           通过
		        <%}else if(detail.getFlag()==2){%>
		          否决
		        <%}else if(detail.getFlag()==0){%>
		         未决裁
		        <%}else{%>
		         未修改
		         <%}%>
		      </td>
		        <%}%>
		      <% if("78000000".equals(admin1.getCpnyId()) || "59000000".equals(admin1.getCpnyId()) || "63000000".equals(admin1.getCpnyId())){%> 	
		      <td>     
		      <% if(detail.getArFlag()==1){%> 	
		           通过
		        <%}else if(detail.getArFlag()==2){%>
		          否决
		        <%}else if(detail.getArFlag()==0){%>
		         未确认
		        <%}else{%>
		         未发送
		         <%}%>
		      </td>
		        <%}%>
		       
		    </tr>
		<%}%>
		
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
		
		      <!-- Page Navigation Start-->
			        <ait:page       
			               link="/arControlServlet"
			               parameters="&operation=ar_pagecontrol&page=detail_v&menu_code=ar0201&sDate=${sDate}&eDate=${eDate}&key=${key}&deptid=${deptid}&itemNo=${itemNo}&sLength=${sLength}&eLength=${eLength}&empType=${empType}&tableName=${tableName}&personId=${personId}" 
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
</body>
</html>
