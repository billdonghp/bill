<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="codeMap" class="java.util.HashMap" scope="page" />
<jsp:useBean id="affirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="annual" class="com.ait.ar.bean.Annual" scope="request" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="applyTypeVector" class="java.util.Vector" scope="request" />
<jsp:useBean id="essLeaveBean" class="com.ait.ess.bean.EssLeaveBean" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<script src="../js/prototype.js"></script>
<script language="javascript" src="../js/meizzDate.js"></script>
<SCRIPT type="text/javascript">
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

var time=null;
function SearchEmp(condition,id){		
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
    
	layer.innerHTML=XmlHttpRequest.responseText;
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
	document.getElementById("empId").value=cell.childNodes[0].firstChild.nodeValue;
	document.ApplyForm.operation.value="view";
	document.ApplyForm.submit();
	layerClose();
}



function Save(){
	
	if ($('emp_list').style.visibility == 'visible') {
		alert ('please select');
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
		<%@ include file="/inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<form name="ApplyForm" id="ApplyForm" action="/essControlServlet" method="POST">
			<input type="hidden" name="operation" value="view" />
			<input type="hidden" name="content" value="trainingapply" />
			<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 培训申请 -->
		  <ait:message messageID="display.ess.attendance_request.training_request"  module="ess"></ait:message>		
				</td>
			</tr>
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td width="15%" height="30"  class="info_title_01"><!-- 培训人选择 -->
	  <ait:message messageID="display.ess.attendance_request.training_request.trainee"  module="ess"></ait:message>	    
		    </td>
		    <td width="35%" align="left">
		    <input type="text" id="empId" name="empId" class="content" style="width:90px " value="<%=essLeaveBean.getEmpId() %>" onKeyUp="SearchEmp(this.value,this.id)" />
		    	&nbsp;[&nbsp;
		     		<ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getChinesePinYin())%>' 
			    		zhContent='<%=StringUtil.checkNull(essLeaveBean.getChineseName()) %>'
			      		koContent='<%=StringUtil.checkNull(essLeaveBean.getKoreanname()) %>'>
			      	</ait:content> 
		     	&nbsp;]
		    </td>              
		    <td width="15%" class="info_title_01"><!-- 申请日期 -->
	  <ait:message messageID="display.mutual.request_date"  module="ess"></ait:message>	    
		    </td>
		    <td width="35%" align="left"><%=essLeaveBean.getCreateDate() %></td>
		  </tr>
		  <tr align="center">
		    <td height="30"  class="info_title_01"><!-- 开始日期 -->
	  <ait:message messageID="display.mutual.start_time"  module="ess"></ait:message>	    
		    </td>
		    <td align="left"><input type="text" name="leaveFromDate" class="content" readonly style="width:90px " value="<%=essLeaveBean.getLeaveFromTime().substring(0, 10) %>" onClick="setday(this);">&nbsp;
		      
		      <select name="leaveFromTime" style="width: 90px ">
		        <%for (int i=0;i<timeList.size();i++) {%>
		        <option value="<%=(String) timeList.get(i) %>"<%=((String) timeList.get(i)).equals(essLeaveBean.getLeaveFromTime().substring(11, 16))?" selected":""%>><%=(String) timeList.get(i) %></option>
		        <%}%>
		      </select>
		      </td>
		    <td class="info_title_01"><!--   结束日期-->
		  <ait:message messageID="display.mutual.end_time"  module="ess"></ait:message>    
		   </td>
		    <td align="left"><input type="text" name="leaveToDate" class="content" readonly style="width:90px " value="<%=essLeaveBean.getLeaveToTime().substring(0, 10) %>" onClick="setday(this);">&nbsp;
		     
		      <select name="leaveToTime" style="width: 90px ">
		        <%for (int i=0;i<timeList.size();i++) {%>
		        <option value="<%=(String) timeList.get(i) %>"<%=((String) timeList.get(i)).equals(essLeaveBean.getLeaveToTime().substring(11, 16))?" selected":""%>><%=(String) timeList.get(i) %></option>
		        <%}%>
		      </select>
		      </td>
		  </tr>
		  <tr align="center">
		    <td height="30"  class="info_title_01"><!--  培训类型 -->
  <ait:message messageID="display.ess.attendance_request.training_request.training_type"  module="ess"></ait:message>		    
		    </td>
		    <td align="left">
		      <ait:codeClass name="trainingTypeCode" codeClass="TrainingTypeCode"  />   
		    <%-- 
		      <select name="trainingTypeCode"  style="width:85px ">
		        <%for (int i=0;i<applyTypeVector.size();i++) {
		          codeMap = (HashMap) applyTypeVector.elementAt(i);%>
		          <option value="<%=codeMap.get("code")%>"<%=essLeaveBean.getLeaveTypeCode().equals(codeMap.get("code"))?" selected":""%>><%=codeMap.get("name")%></option>
		        <%}%>
		      </select>
		      --%>
		      </td>
			<td class="info_title_01">&nbsp;</td>
			<td align="left">&nbsp;</td>
		  </tr>
		  <!-- <tr>
		    <td height="30"  class="info_title_01">本月可用倒休</td>
			<td align="left"><%=essLeaveBean.getLeaveThisMonth()%>&nbsp;天</td>
			<td class="info_title_01">下月可用倒休</td>
			<td align="left"><%=essLeaveBean.getLeaveNextMonth()%>&nbsp;天</td> 
		  </tr> -->
		  
		  <%if (essLeaveBean.getAffirmorList().size() > 0) {
			for (int i=0;i<essLeaveBean.getAffirmorList().size();i++) {
			  affirmor = (EssAffirmor) essLeaveBean.getAffirmorList().get(i);%>
			  <tr>
				<td class="info_title_01" height="30"><%=affirmor.getAffirmLevel()%>&nbsp;<!-- 级决裁者 -->
	  <ait:message messageID="display.mutual.approver"  module="ess"></ait:message>			
				</td>
				<td align="left" class="info_content_00">
				<ait:content enContent='<%=StringUtil.checkNull(affirmor.getAffirmorEnName())%>'  zhContent='<%=StringUtil.checkNull(affirmor.getAffirmorName())%>' koContent='<%=StringUtil.checkNull(affirmor.getAffirmorKorName())%>'></ait:content>
				&nbsp;</td>  
				<%i++;
				if (i<essLeaveBean.getAffirmorList().size()) {
				  affirmor = (EssAffirmor) essLeaveBean.getAffirmorList().get(i);%>
				  <td class="info_title_01"><%=affirmor.getAffirmLevel()%>&nbsp;<!--  级决裁者 -->
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
			<tr>
			  <td align="center" height="30" colspan="4" style="color:red;"><!-- 当前员工未设置决裁者,请速与人事部门相关人员联系 -->
			<ait:message messageID="display.ess.attendance_request.absence.has_no_approver"  module="ess"></ait:message></td>				  
			</tr>
		  <%}%>
		  <tr>
			<td class="info_title_01" height="90px"><!-- 培训事由 -->
	  <ait:message messageID="display.ess.attendance_request.training_request.reason_for_training"  module="ess"></ait:message>		
			</td>
			<td colspan="3"><textarea name="leaveReason" style=" height: 200px;width:600px "><%=essLeaveBean.getLeaveReason() %></textarea></td>
		  </tr>
		</table>
		<!--
		<input name="leaveFromTime" type="hidden" value="00:00">
		<input name="leaveToTime" type="hidden" value="00:00">
		  -->
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
