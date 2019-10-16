<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="codeMap" class="java.util.HashMap" scope="page" />
<jsp:useBean id="affirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="deductList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="nextDaysList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="applyTypeVector" class="java.util.Vector" scope="request" />
<jsp:useBean id="applySortVector" class="java.util.Vector" scope="request" />
<jsp:useBean id="essOverTimeBean" class="com.ait.ess.bean.EssOverTimeBean" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head> 
<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>
<SCRIPT type="text/javascript">
function ViewShift(){
	var empid = document.ApplyForm.empId.value;
	var otdate = document.ApplyForm.otDate.value;  
	var url = "/ess/inc/ess_emp_shift.jsp?empid=" + empid + "&otdate=" + otdate;
	window.open(url,'viewshift','width=400, height=210, top=200, left=200, status=no, scrollbars=no,resizable=no');
}
function SearchEmp(empid){
	var inputBox = document.ApplyForm.empId;
	var iBtop  = inputBox.offsetTop;     //文本框的定位点高
	var iBheight  = inputBox.clientHeight;  //文本框本身的高
	var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
	while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
	showsearch.style.top = iBtop+iBheight+6;
	showsearch.style.left = iBleft;
	showsearch.style.visibility='visible';
	document.emp_list.location.href = "/inc/EssSearchEmployee.jsp?condition="+encodeURIComponent(empid);
}
function Batch() {
	BatchApply('','empId');
}

function BatchApply(content) {
	var inputBox = document.ApplyForm.empId;
	var iBtop  = inputBox.offsetTop;     //文本框的定位点高
	var iBheight  = inputBox.clientHeight;  //文本框本身的高
	var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
	while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
	showsearch.style.top = iBtop+iBheight+6;
	showsearch.style.left = iBleft;
	showsearch.style.visibility='visible';
	document.emp_list.location.href = "/inc/EssSearchEmployeeForOT.jsp?content="+encodeURIComponent(content);
}

function Reset() {
	document.getElementById("empID").value = "";
	document.getElementById("empIds").value = "";
	document.getElementById("empName").innerText = "";
}

var time=null;
function BatchApply(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						BatchA(condition,id);
					},500);  
}


function BatchA(condition,id){		
		if (document.getElementById("empIds").value == ''){
			document.getElementById("empName").innerText ="";
		}
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
		layer1 = $('divi');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6+17;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6+17;
		layeri.style.left = iBleft;
		layer1.style.top = iBtop+iBheight+3;
		layer1.style.left = iBleft;
		
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}	

function showResponse(XmlHttpRequest){

	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
   
    if(size<=30){
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
    layer1.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){	
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	layer1.style.visibility = 'hidden';
	if(document.getElementById('empIds').value=='' && document.getElementById('empName').innerText!=''){
		Reset();
	}
}

function updateValue(cell) {
	empid = cell.childNodes[0].firstChild.nodeValue;
	empname = cell.childNodes[1].firstChild.nodeValue;
	var msg='<ait:message messageID="alert.ess.overtime.has_been_added_to_list"  module="ess"></ait:message>';
	var   arry  = document.getElementById("empIds").value.split(",");   
	for (i=0;i<arry.length;i++){   
		if(empid==arry[i]){
			alert(msg);  
			return;
		}            
	}
	if (document.getElementById("empIds").value == '')
		document.getElementById("empIds").value += empid;
	else
		document.getElementById("empIds").value += "," + empid ;
	
	document.getElementById("empName").innerText += empname + ",";

}


function Save(){
var msg='<ait:message messageID="alert.ess.overtime.select_ot_employee"  module="ess"></ait:message>';
	if ($('emp_list').style.visibility == 'visible' || document.getElementById("empName").innerText=='') {
		alert (msg);            
	} else {
		document.ApplyForm.operation.value="add";
		document.ApplyForm.submit();
	}
}
</SCRIPT>
<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/esstoolbar_apply.jsp"%>
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

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<form name="ApplyForm" action="/essControlServlet" method="POST">
			<input type="hidden" name="operation" value="view" />
			<input type="hidden" name="content" value="otapply" />
			<input type="hidden" name="otsort" value="<%=StringUtil.checkNull(request.getParameter("otsort"), "normal") %>" />
			<input type="hidden" name="menu_code" value="<%=menu_code%>"/>  
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
				<ait:message messageID="display.ess.attendance_request.ot_request.overtime_work_request"  module="ess"></ait:message>    
				</td>
			</tr>
		 </table>                 
		 <!--错误信息-->	
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>	
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td width="19%" height="30" class="info_title_01"><!-- 员工搜索 -->
			    <ait:message messageID="display.ess.attendance_request.ot_request.staff_search"  module="ess">
			    </ait:message>
			    <input type="hidden" name="empIds" value="" />
		    </td>
		    <td height="30"  align="left">
			    <input type="text" id="empId" name="empId" class="content" style="width:90px " value="<%=essOverTimeBean.getEmpId()%>" onKeyUp="BatchApply(this.value,this.id,'<%=essOverTimeBean.getEmpId()%>');" />&nbsp;
			     <span name="batch" style="cursor:pointer;" onClick="Batch();"><!--  批量申请 -->
			     	<ait:message messageID="display.ess.attendance_request.ot_request.bulk_request"  module="ess"></ait:message>
			     </span> 
		    </td>
		    <td width="19%" height="30" class="info_title_01"><!-- 加班人姓名 -->    
		    <ait:message messageID="display.ess.attendance_request.ot_request.requested_by"  module="ess"></ait:message>
		    </td>
		    <td width="34%" height="30" align="left" class="info_content_00">
		    <span id="empName">
		    <ait:content enContent='<%=StringUtil.checkNull(essOverTimeBean.getChinesePinYin())%>' 
		    zhContent='<%=StringUtil.checkNull(essOverTimeBean.getChineseName()) %>'
		      koContent='<%=StringUtil.checkNull(essOverTimeBean.getKoreanname()) %>'></ait:content>
		   </span></td>                    
		  </tr>                    
		  <tr align="center">
		    <td width="19%" height="30" class="info_title_01"><!-- 加班日期 -->
		     <ait:message messageID="display.ess.attendance_request.ot_request.overtime_date"  module="ess"></ait:message>
		    </td>
		    <td width="28%" height="30" align="left" class="info_content_00">
			  <input type="text" name="otDate" class="content" readonly style="width:90px " value="<%=essOverTimeBean.getOtDate() %>" onClick="setday(this);">
			  &nbsp;
			  <span onClick="ViewShift();" style="cursor:pointer;">  <!--  班次-->
			<ait:message messageID="display.ess.attendance_request.ot_request.shift"  module="ess"></ait:message>
			</span>
		    </td>
		    <td width="19%" height="30" class="info_title_01"><!--  申请日期  -->
		 <ait:message messageID="display.mutual.request_date"  module="ess"></ait:message>
		  </td>
		    <td width="34%" height="30" align="left" class="info_content_00"><%=essOverTimeBean.getCreateDate() %></td>
		  </tr>
		   <tr align="center">
		    <td width="19%" height="30" class="info_title_01"><!--  开始时间 -->
		   <ait:message messageID="display.mutual.start_time"  module="ess"></ait:message>
		   </td>
		    <td width="28%" height="30" align="left">
		      <ait:time name="fromTime" spacing="30" selected="${essOverTimeBean.otFromTime}"  /></td>
		    <td width="19%" height="30" class="info_title_01"><!--  跨天数 -->
		   <ait:message messageID="display.mutual.time_span"  module="ess"></ait:message>
		   </td>
		    <td width="34%" height="30" align="left">
		      <ait:codeClass name="otNextDays" codeClass="SpanDays" selected="${essOverTimeBean.otNextDays}"  /></td>
		  </tr>
		  <tr align="center">
		    <td width="19%" height="30" class="info_title_01"><!--结束时间  -->
		   <ait:message messageID="display.mutual.end_time"  module="ess"></ait:message>
		    </td>
		    <td width="28%" height="30" align="left">
		      <ait:time name="toTime" spacing="30" selected="${essOverTimeBean.otToTime}"  /></td>
		     <!--  
		    <td width="19%" height="30" class="info_title_01">扣除时间</td>
		    <td width="34%" height="30" align="left">
		      <select name="otDeduct" style="width: 70px ">
		        <%--for (int i=0;i<deductList.size();i++) {%>
		          <option value="<%=(String) deductList.get(i) %>"<%=(Double.parseDouble((String) deductList.get(i)) == essOverTimeBean.getOtDeduct())?" selected":""%>><%=(String) deductList.get(i) %></option>
		        <%}--%>
		      </select></td>
		      -->
		    <td width="19%" height="30" class="info_title_01"><!-- 加班类型  -->
		  <ait:message messageID="display.ess.attendance_request.ot_request.overtime_type"  module="ess"></ait:message>
		   </td>
		    <td width="34%" height="30" align="left" class="info_content_00">
		        <ait:codeClass name="otTypeCode" codeClass="OTTypeCode"  />  
		    <%--                                 
		      <select name="otTypeCode" style="width:85px ">
		        <%for (int i=0;i<applyTypeVector.size();i++) {
		          codeMap = (HashMap) applyTypeVector.elementAt(i);%>
		          <option value="<%=codeMap.get("code")%>"<%=essOverTimeBean.getOtTypeCode().equals(codeMap.get("code"))?" selected":""%>><%=codeMap.get("name")%></option>
		        <%}%>
		      </select>
		      --%>
		      &nbsp;&nbsp;&nbsp;<input type="checkbox" name="forceType" value="true" /><!-- 强制类型选择   -->
             <ait:message messageID="display.ess.attendance_request.ot_request.enforce_to_select"  module="ess"></ait:message>
            </td>                                
		  </tr>
		  <!--
		  <tr align="center">
		                    
		    <td width="19%" height="30" class="info_title_01">加班分类</td>
		    <td width="34%" height="30" align="left">
		      <select name="otSortCode" style="width:85px ">
		        <%--or (int i=0;i<applySortVector.size();i++) {
		          codeMap = (HashMap) applySortVector.elementAt(i);%>
		          <option value="<%=codeMap.get("code")%>"<%=essOverTimeBean.getOtSortCode().equals(codeMap.get("code"))?" selected":""%>><%=codeMap.get("name")%></option>
		        <%}--%>
		      </select></td>
		  </tr> 
		  -->
		  <tr>
		    <td width="19%" height="30" class="info_title_01"><!-- 扣除时间 -->
		       <ait:message messageID="display.ess.attendance_request.ot_request.deduction"  module="ess"></ait:message>
		    </td>
		    <td width="34%" height="30" align="left">
		      <select name="otDeduct" style="width: 70px ">
		        <%for (int i=0;i<deductList.size();i++) {%>
		          <option value="<%=(String) deductList.get(i) %>"<%=(Double.parseDouble((String) deductList.get(i)) == essOverTimeBean.getOtDeduct())?" selected":""%>><%=(String) deductList.get(i) %></option>
		        <%}%>
		      </select></td>
		  <td width="19%" height="30" class="info_title_01">&nbsp;</td>
		  <td width="34%" height="30" align="left">&nbsp;</td>
		   </tr>   
		  <%if (essOverTimeBean.getAffirmorList().size() > 0) {
			for (int i=0;i<essOverTimeBean.getAffirmorList().size();i++) {
			  affirmor = (EssAffirmor) essOverTimeBean.getAffirmorList().get(i);%>
			  <tr id="affirmor">
				<td class="info_title_01" height="30"><%=affirmor.getAffirmLevel()%>&nbsp;<!-- 级决裁者 -->
				 <ait:message messageID="display.mutual.approver"  module="ess"></ait:message>                   
				</td>                       
				<td align="left" class="info_content_00">
				<ait:content enContent='<%=StringUtil.checkNull(affirmor.getAffirmorEnName())%>'  zhContent='<%=StringUtil.checkNull(affirmor.getAffirmorName())%>' koContent='<%=StringUtil.checkNull(affirmor.getAffirmorKorName())%>'></ait:content>
				&nbsp;</td>
				<%i++;
				if (i<essOverTimeBean.getAffirmorList().size()) {
				  affirmor = (EssAffirmor) essOverTimeBean.getAffirmorList().get(i);%>
				  <td class="info_title_01"><%=affirmor.getAffirmLevel()%>&nbsp;<!-- 级决裁者 -->
				  <ait:message messageID="display.mutual.approver"  module="ess"></ait:message>
				  </td>
				  <td align="left" class="info_content_00">
				  <ait:content enContent='<%=StringUtil.checkNull(affirmor.getAffirmorEnName())%>'  zhContent='<%=StringUtil.checkNull(affirmor.getAffirmorName())%>' koContent='<%=StringUtil.checkNull(affirmor.getAffirmorKorName())%>'></ait:content>
				&nbsp;</td>
				<%} else {%>
				  <td class="info_title_01">&nbsp;</td>                                 
				  <td align="left">&nbsp;</td>
				<%}%>
			  </tr>           
			<%}
		  } else {%>
			<tr id="affirmor">
			  <td align="center" height="30" colspan="4" style="color:red;"><!--  当前员工未设置决裁者,请速与人事部门相关人员联系 -->
			  <ait:message messageID="display.ess.attendance_request.absence.has_no_approver"  module="ess"></ait:message>
			 </td>
			</tr>
		  <%}%>
		  <tr>
		    <td class="info_title_01" height="90px"><!-- 工作内容  -->
		    <ait:message messageID="display.mutual.work_narrative"  module="ess"></ait:message>
		   </td>
		    <td colspan="3"><textarea name="otRemark" style=" height: 180px;width:600px "></textarea></td>
		  </tr>
		</table>
		<input name="otDeduct" type="hidden" value="0.0">
		<input name="otSortCode" type="hidden" value="">
		</form>
		<br><br><br><br>
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

	<div id='divi' style="position:absolute;overflow:auto; top:100;width:370; height:25; z-index:1; visibility: hidden;">
		<table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		    <tr>
		      <td width="20%" height="20" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="Reset();" style="cursor:pointer;"><!-- 清除  -->
		     <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.clear_all" module="ess"></ait:message>
		     </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		      <td width="20%" align="center" onClick="layerClose();" style="cursor:pointer;"><!--  关闭-->
		      <ait:message messageID="display.ess.attendance_request.ot_request.bulk_request.close" module="ess"></ait:message>
		      </td>
		      <td width="20%" class="info_search_02">&nbsp;</td>
		    </tr>
	  	</table>
	</div>
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>

</body>
</html>
