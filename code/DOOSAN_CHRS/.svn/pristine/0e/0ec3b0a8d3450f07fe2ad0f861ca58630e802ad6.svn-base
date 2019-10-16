<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essLeaveBean" class="com.ait.ess.bean.EssLeaveBean"
	scope="request" />
<%@page import="com.ait.util.SqlUtil"%>
<%
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	try {
		conn = ConnBean.getConn();
		st = conn.createStatement();
		rs = st
				.executeQuery("SELECT ESS_OTAPPLY_SEQ.NEXTVAL SEQ_NO FROM DUAL");
		String seq_no = null;
		if (rs.next()) {
			seq_no = rs.getString("SEQ_NO");
		}
		request.setAttribute("seq_no", seq_no);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		SqlUtil.close(rs, st, conn);
	}
%>
<html>
<head>
<!-- ess_leave_apply.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>
<SCRIPT type="text/javascript">

function getAffirmInfo()
{		
		document.getElementById("affirmData").innerHTML = "数据提取中，请等待!!!" ;
		
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=essSearchLeaveAffirmData&empId=" + document.ApplyForm.person_id.value 
     			 + "&leaveFromDate=" + document.ApplyForm.leaveFromDate.value + "&leaveFromTime=" + document.ApplyForm.leaveFromTime.value 
     			 + "&leaveToDate=" + document.ApplyForm.leaveToDate.value + "&leaveToTime=" + document.ApplyForm.leaveToTime.value 
     			 + "&leaveTypeCode=" + document.ApplyForm.leaveTypeCode.value ;
		  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showAffirm}
        );
        
}
function showAffirm(XmlHttpRequest){
    
    var affirms = XmlHttpRequest.responseText.split(";");
    
    document.ApplyForm.affirmLevel.value = affirms.length -1 ;
    
	document.getElementById("affirmData").innerHTML=XmlHttpRequest.responseText;
}

function applyCcontent(){
	var cpnyId = '${admin.cpnyId}' ;
	
    document.ApplyForm.affirmLevel.value = 0 ;
	document.getElementById("affirmData").innerHTML = "";
	
	var leaveType = document.ApplyForm.leaveTypeCode.value ;
	if (leaveType == 'H9' && cpnyId == '63000000' && document.getElementById("leaveTypeH9")== null){
		var nTr = document.getElementById('operateTable').insertRow(6) ;
		nTr.id = "leaveTypeH9" ; 
		
		td = nTr.insertCell() ;
		td.height = "30" ; 
		td.className = "info_title_01" ;
		td.align = "center" ;
		td.innerHTML = "年假信息" ;
		
		td = nTr.insertCell() ;
		td.align="left" ; 
		td.colSpan="3" ;
		td.innerHTML = "担当业务:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "<textarea style='height:20px;width:155px'  onfocus=this.style.height='40px' onblur=this.style.height='20px' name=H9business></textarea><br>"
				 + "联系方式:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "<textarea style='height:20px;width:155px'  onfocus=this.style.height='40px' onblur=this.style.height='20px' name=H9contactMode ></textarea><br>"
				 + "业务代理:&nbsp;&nbsp;职号:&nbsp;&nbsp;<input type=text name='H9empId'/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "姓名: &nbsp;&nbsp;<input type=text name='H9name' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 职位: &nbsp;&nbsp;<input type=text name=H9position /><br>" 
				 + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;办公电话: &nbsp;&nbsp;<input type=text name=H9officePhone />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
				 + "手机: &nbsp;&nbsp;<input type=text name=H9cellphone /><br> 其他事项:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				 + "<textarea style='height:20px;width:155px' onfocus=this.style.height='40px' onblur=this.style.height='20px' name=H9otherBusiness></textarea><br>" ;
		
	}else if(cpnyId == '63000000' && leaveType != 'H9' ){
		if (document.getElementById("leaveTypeH9") != null ){
			document.getElementById("operateTable").deleteRow(document.getElementById("leaveTypeH9").rowIndex)
		}
		
	}
}

var tru=0;
function Save()
{		
		//alert(document.ApplyForm.H9empId.value) ;leaveToTime
		
		var cpnyId = '${admin.cpnyId}' ;
		var leaveType = document.ApplyForm.leaveTypeCode.value ;

		if(cpnyId == '60000000' && leaveType == 'H1'){
			var ov=document.ApplyForm.leaveToTime.value;
			var st=document.ApplyForm.leaveFromTime.value;
			var fr,to,di,to1,fr1,tf;
			//结束
			to=ov.substr(0,2);
			to1=ov.substr(3,5);
			//开始
			fr=st.substr(0,2);
			fr1=st.substr(3,5);
			di=to-fr;
			tf=fr1-to1;
			if(to == fr || fr >= to){
				alert("事假最低申请单位为一小时，请确认！");
				return;
			}

			if(di == "1" && tf == "30"){
				alert("事假最低申请单位为一小时，请确认！");
				return;
			}
		}
		if(cpnyId == '60000000' && leaveType == 'H3'){
			var ov=document.ApplyForm.leaveToTime.value;
			var st=document.ApplyForm.leaveFromTime.value;
			var fr,to,di,to1,fr1,tf;
			//结束
			to=ov.substr(0,2);
			to1=ov.substr(3,5);
			//开始
			fr=st.substr(0,2);
			fr1=st.substr(3,5);
			di=to-fr;
			tf=fr1-to1;
			if(to == fr || fr >= to){
				alert("病假最低申请单位为一小时，请确认！");
				return;
			}

			if(di == "1" && tf == "30"){
				alert("病假最低申请单位为一小时，请确认！");
				return;
			}
		}
		if(cpnyId == '60000000' && (leaveType == 'H2' || leaveType == 'B1' || leaveType == 'H13' || leaveType == 'H14' || leaveType == 'H21' || leaveType == 'H5')){
			if(tru == 0){
				alert("请上传文件，再申请！");
				return;
			}
		}

		if (document.ApplyForm.empId.value  == "" || document.ApplyForm.empId.value.length == 0)
		{
			alert("请选择需要休假的员工!!!") ;
			return ;
		}
		if (document.ApplyForm.leaveReason.value  == "" || document.ApplyForm.leaveReason.value.length == 0)
		{
			alert("请填写休假原因!!!") ;
			return ;
		}
		if (document.ApplyForm.affirmLevel.value  == 0)
		{
			alert("请先取得决裁者列表，在进行申请!!!") ;
			return ;
		}
		
		if (leaveType == 'H9' && cpnyId == '63000000'){
			
			var H9business = document.getElementById("H9business").value ;
			var H9empId = document.getElementById("H9empId").value ;
			var H9name = document.getElementById("H9name").value ;
			var H9position = document.getElementById("H9position").value  ;
			var H9officePhone = document.getElementById("H9officePhone").value ;
			var H9cellphone = document.getElementById("H9cellphone").value ;
			var H9otherBusiness = document.getElementById("H9otherBusiness").value ;
			var H9contactMode = document.getElementById("H9contactMode").value ;
			
			if (H9business == '' || H9empId == '' || H9name == '' || H9position == ''
			   || H9officePhone == ''  || H9cellphone == ''  || H9otherBusiness == '' || H9contactMode == '')
			   {
			   		alert("请填写年休假申请的信息!!!") ;
			   		if (H9business == ''){
			   			document.getElementById("H9business").focus() ;
			   		}
			   		else if(H9contactMode == ''){
			   			document.getElementById("H9contactMode").focus() ;
			   		}
			   		else if(H9empId == ''){
			   			document.getElementById("H9empId").focus() ;
			   		}
			   		else if(H9name == ''){ 
			   			document.getElementById("H9name").focus() ;
			   		}
			   		else if(H9position == ''){
			   			document.getElementById("H9position").focus() ;
			   		}
			   		else if(H9officePhone == ''){
			   			document.getElementById("H9officePhone").focus() ;
			   		}
			   		else if(H9cellphone == ''){
			   			document.getElementById("H9cellphone").focus() ;
			   		}
			   		else if(H9otherBusiness == ''){
			   			document.getElementById("H9otherBusiness").focus() ;
			   		}
					return ;
			   }
		}
		
		document.ApplyForm.operation.value="add";
		document.ApplyForm.submit();
		document.getElementById("trname").style.display="none";//避免重复提交，隐藏按钮
}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){SearchE(condition,id);},500);  
}

function SearchE(condition,id){
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
		$('empId').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML=cell.childNodes[1].firstChild.nodeValue;
		document.getElementById("person_id").value=cell.childNodes[2].firstChild.nodeValue;
		layerClose();
		document.ApplyForm.action="/essControlServlet?operation=view&content=leaveapply&menu_code=${param.menu_code}";
		document.ApplyForm.submit();
}

function upLoadFile(){
	
	window.open("ess/upLoadLeavaApplyFile.jsp?fileName="+$('ess_seq_no').value,"UpLoadFile","resizable=no,scrollbars,dependent,width=480,height=100,left=450,top=450");
	tru=1;
}

</SCRIPT>
<body>

<form name="ApplyForm" id="ApplyForm" action="/essControlServlet"
	method="POST"><input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="leaveapply" /> <input
	type="hidden" name="menu_code" value="${param.menu_code}" /> <input
	type="hidden" name="affirmLevel" value="0" /> <input type="hidden"
	name="ess_seq_no" value="${seq_no}"> <input type="hidden"
	name="fileUploadFlag" value="0">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="inc/esstoolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">勤态申请</td>
			</tr>
		</table>
		<c:if test="${fn:length(errorMsgList) > 0 }">
			<table width="100%" border="1" cellpadding="0" cellspacing="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
				style="padding: 2px 2px 2px 2px;">
				<c:forEach items="${errorMsgList}" var="errorMsg" varStatus="i">
					<tr align="center">
						<td align="center"><font color="red">${errorMsg}</font></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<table id="operateTable" width="100%" border="1" cellpadding="0"
			cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="19%" height="30" class="info_title_01"><!-- 年假提醒 -->
				总年假</td>
				<td width="35%" align="left" class="info_content_00">
				${allTotVaction}&nbsp;天</td>
				<td width="15%" height="30" class="info_title_01">可用年假</td>
				<td width="34%" height="30" align="left" class="info_content_00">
				${lastTotVaction}&nbsp;天</td>
			</tr>
			<c:if test="${flag eq 'true'}">
				<tr align="center">
					<td width="19%" height="30" class="info_title_01"><!-- 有薪病假提醒 -->
					总有薪病假</td>
					<td width="35%" align="left" class="info_content_00">
					${totalSickleave}&nbsp;天</td>
					<td width="15%" height="30" class="info_title_01">可用有薪病假</td>
					<td width="34%" height="30" align="left" class="info_content_00">
					${sickLeaveLeft}&nbsp;天</td>
				</tr>
			</c:if>
			<tr align="center">
				<td width="19%" height="30" class="info_title_01"><!-- 上年剩余年假 -->
				上年剩余年假</td>
				<td width="35%" align="left" class="info_content_00">
				${totalLastAnnualVacation}&nbsp;天</td>
				<td width="19%" height="30" class="info_title_01"><!-- 上年剩余年假 -->
				(2月28日之前)可用上年剩余年假</td>
				<td width="35%" align="left" class="info_content_00">
				${lastAnnualVacationLeft}&nbsp;天</td>
				
			</tr>
			<tr align="center">
				<td width="19%" height="30" class="info_title_01"><!-- 有薪病假提醒 -->
				总有团聚假</td>
				<td width="35%" align="left" class="info_content_00">
				${totalReuniteleave}&nbsp;天</td>
				<td width="15%" height="30" class="info_title_01">可用团聚假</td>
				<td width="34%" height="30" align="left" class="info_content_00">
				${reuniteLeaveLeft}&nbsp;天</td>
			</tr>

			<tr align="center">
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr align="center">
				<td width="19%" height="30" class="info_title_01">职号</td>
				<td width="35%" align="left" class="info_content_00"><input
					type="hidden" name="person_id" id="person_id"
					value="${essLeaveBean.person_id}" /> <input type="text" id="empId"
					name="empId" class="content" style="width: 90px"
					value="<%=essLeaveBean.getEmpId()%>"
					onKeyUp="SearchEmp(this.value,this.id);" /></td>
				<td width="15%" height="30" class="info_title_01">姓名
				<td width="34%" height="30" align="left" class="info_content_00"><span
					id="name"><%=essLeaveBean.getChineseName()%></span></td>
			</tr>
			<tr align="center">
				<td height="30" class="info_title_01"><!--  开始日期 --> <ait:message
					messageID="display.mutual.start_time" module="ess"></ait:message></td>
				<td align="left"><input type="text" name="leaveFromDate"
					class="content" readonly style="width: 90px"
					value="<%=essLeaveBean.getLeaveFromDate()%>"
					onClick="setday(this);applyCcontent();">&nbsp; <select
					name="leaveFromTime" style="width: 90px"
					onchange="applyCcontent();">
					<%
						for (int i = 0; i < timeList.size(); i++) {
					%>
					<option value="<%=(String) timeList.get(i)%>"
						<%=((String) timeList.get(i)).equals(essLeaveBean
						.getLeaveFromTime()) ? " selected" : ""%>><%=(String) timeList.get(i)%></option>
					<%
						}
					%>
				</select></td>
				<td class="info_title_01"><!--  结束日期 --> <ait:message
					messageID="display.mutual.end_time" module="ess"></ait:message></td>
				<td align="left"><input type="text" name="leaveToDate"
					class="content" readonly style="width: 90px"
					value="<%=essLeaveBean.getLeaveToDate()%>"
					onClick="setday(this);applyCcontent();">&nbsp; <select
					name="leaveToTime" style="width: 90px" onchange="applyCcontent();">
					<%
						for (int i = 0; i < timeList.size(); i++) {
					%>
					<option value="<%=(String) timeList.get(i)%>"
						<%=((String) timeList.get(i)).equals(essLeaveBean
						.getLeaveToTime()) ? " selected" : ""%>><%=(String) timeList.get(i)%></option>
					<%
						}
					%>
				</select></td>
			</tr>
			<tr align="center">
				<td height="30" class="info_title_01">勤态类型</td>
				<td align="left"><ait:codeClass name="leaveTypeCode"
					onChange="applyCcontent();" codeClass="LeaveTypeCode"
					selected="<%= essLeaveBean.getLeaveTypeCode() %>" /></td>
				<td class="info_title_01"><!--   申请日期 --><ait:message
					messageID="display.mutual.request_date" module="ess"></ait:message>
				</td>
				<td align="left" class="info_content_00"><%=essLeaveBean.getCreateDate()%></td>
			</tr>
			<tr align="center">
				<td height="30" class="info_title_01"><!-- 记假方式 --> 记假方式</td>
				<td align="left" ><ait:codeClass
					name="ApplyLeaveType" onChange="" codeClass="ApplyLeaveType"
					selected="<%=essLeaveBean.getApplyLeaveType()%>" /></td>
				<td nowrap="nowrap" class="info_title_01">
				出厂地点 </td>
			<td nowrap="nowrap" class="info_content_00" align="left">
			<ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" />
			</tr>
			<tr>
				<td width="15%" class="info_title_01"><a href="#"
					onclick="getAffirmInfo();"><font color="red">获取决裁者信息(单击)</font></a></td>
				<td colspan="3" align="left" class="info_content_00" id="affirmData">&nbsp;&nbsp;</td>
			</tr>

			<tr>
				<td width="15%" class="info_title_01">上传文件</td>
				<td colspan="3" align="left" class="info_content_00"><input
					type="button" value="上传..." onclick="upLoadFile();" /></td>
			</tr>
			<tr>
				<td class="info_title_01" height="90px"><!-- 休假原因  --> <ait:message
					messageID="display.ess.review.time_off.reason" module="ess"></ait:message><font
					color="red">(必填)</font></td>
				<td colspan="3"><textarea name="leaveReason"
					style="height: 100px; width: 600px"><%=essLeaveBean.getLeaveReason()%></textarea></td>
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

<iframe id='iemp'
	style="position: absolute; top: 100; width: 370; height: 200; z-index: 1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position: absolute; overflow: auto; top: 100; width: 370; height: 210; z-index: 2; visibility: hidden;">
</div>


</form>
</body>
</html>
