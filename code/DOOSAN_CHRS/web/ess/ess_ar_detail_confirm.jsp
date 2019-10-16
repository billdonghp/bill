<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.bean.ArDetail"%>
<%@ page import="com.ait.ar.bean.ArDetailBack"%>
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="confirmMap" class="java.util.HashMap" scope="request" />
<%@ page import="com.ait.util.*"%>
<%@ page import="java.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="item" scope="page" class="com.ait.ar.bean.ArItem" />
<jsp:useBean id="arItemList" scope="request" class="java.util.ArrayList" />

<jsp:useBean id="detail" scope="page" class="com.ait.ar.bean.ArDetailBack" />
<jsp:useBean id="detailList" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="sDate" scope="request" class="java.lang.String" />
<jsp:useBean id="eDate" scope="request" class="java.lang.String" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<style>
#leftnewstd .ellipsis_row{width:80px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}

</style>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>


<script language="javascript">

function Search() {

			              
	document.ApplyForm.action="/essControlServlet?operation=view&content=ardetailconfirm&menu_code=ess0916";
	document.ApplyForm.submit();
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
	if (document.ApplyForm.details) {
		if (document.ApplyForm.details[0]) {
			for (i=0;i<document.ApplyForm.details.length;i++)
				document.ApplyForm.details[i].checked = document.ApplyForm.checkAll.checked;
		} else {
			document.ApplyForm.details.checked = document.ApplyForm.checkAll.checked;
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
function Confirm(itemno, flag, batchTag, sign) {

    

var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';
var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';
var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	var tags = document.getElementsByName("details");
	if (tags == null || tags.length == null || tags.length == 0){
	if (flag == "2")
		alert(msg2);
		
	else
	  alert(msg1);
		return false;
	}   
	if (batchTag == false) {
		tags(sign).checked = true;
	}
	var c = 0;
	for (var i=0; i<tags.length; i++){
		if(tags(i).checked == true){
			c++;
		}
	}
	if (c==0){
	 if (flag == "2")
		alert(msg4);
	 else
	  alert(msg3);
		return false;
	}  
	
	if(flag=="2")
	{	    
		if (confirm(msg6)) {
			document.ApplyForm.action = "/essControlServlet";
			document.ApplyForm.operation.value="modify";
			document.ApplyForm.itemno.value=itemno;
			document.ApplyForm.flag.value=flag;
			document.ApplyForm.batchTag.value=batchTag;
			document.ApplyForm.submit();
		}
	}
  else 
     {      
     if(confirm(msg5)){    
     document.ApplyForm.action = "/essControlServlet";     
	 document.ApplyForm.operation.value="modify";
	 document.ApplyForm.itemno.value=itemno;
	 document.ApplyForm.flag.value=flag;
	 document.ApplyForm.batchTag.value=batchTag;
	 document.ApplyForm.submit();  } 
   }
}

function showMemo2(val) {
	var memo = document.getElementById('memo2_'+val).value;
	var html = "<div style='background-color:#FFFFFF;height: 100%'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 350
				  , height: 150
				 , shadow: true
				 , closable: true
				 ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 

</script>
</head>
<body>
<%
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session.getAttribute("admin");
	String key = StringUtil.toCN(request.getParameter("key"));
%>
<%
	   String qryType = request.getParameter("qryType"); 
	%>
	<%! String selected(String valueSel, String value){
	      return valueSel.equals(value) ? "selected" : "";
	    }
	 %>

<form name="ApplyForm" action="" method="post">
<input type="hidden" name="batchTag" value="" />
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="itemno" value="" />
<input type="hidden" name="flag" value="" />
<input type="hidden" name="content" value="ardetailconfirm" />
<input type="hidden" name="menu_code" value="ess0916" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_attendance_confirm.jsp"%>
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
		           <td class="info_title_01"><!--工号/姓名--><ait:message  messageID="display.mutual.emp_no_name"/></td>
		          <td class="info_content_00"><input id="key" name="key" type="text" size="10" value="<%=StringUtil.checkNull(key)%>" </td>   
		          <input id="personId" name="personId" type="hidden" value=""/>
		          <td width="10%" class="info_content_00">
		          
				     <select name="qryType">
							<option value="0" <%= selected("0", qryType) %>><!-- 未决裁 -->
							<ait:message messageID="display.ess.approval.pending"
								module="ess"></ait:message></option>
						
						</select>
				  </td>
		          
		        
				  <td class="info_content_00">
		          <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />
		          </td>
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
		<!-- test display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		   <input type="hidden" name="mailSize" value="${fn:length(detailList)}"/>  
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤明细-->
        	考勤修改确认</td>
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
		      <td width="7%" class="info_title_01"><!-- 班次-->
        	<ait:message  messageID="display.mutual.shift"/></td>
		      <td width="10%" class="info_title_01"><!-- 项目-->
        	<ait:message  messageID="display.att.maintenance.maintenance.item" module="ar"/></td>
		      <td width="15%" class="info_title_01"><!-- 时间段-->
        	<ait:message  messageID="display.att.maintenance.maintenance.duration" module="ar"/></td>
		      <td width="10%" class="info_title_01"><!-- 长度-->
        	<ait:message  messageID="display.att.maintenance.maintenance.timing" module="ar"/></td>
        	<!--<td width="8%" class="info_title_01"> 夜班夜班</td>
		      <td width="8%" class="info_title_01">
        	<ait:message  messageID="display.mutual.lock"/></td>-->
        	<td width="5%" class="info_title_01">
        	操作人</td>
        	<td width="8%" class="info_title_01">
        	操作时间</td>
        	<td width="25%" class="info_title_01">
        	修改原由</td>
        	<td width="8%" class="info_title_01">
        	人事确认</td>
		   <!--  <td class="info_title_01" nowrap>意见</td>-->
		    </tr>
		<%String ar_date_str = "";
		  int num = -1;
		for(int i=0;i<detailList.size();i++) {
			detail = (ArDetailBack) detailList.get(i);
			if (detail.getDate_type() == 1) {
		  		ar_date_str = detail.getAr_date_str();
		  	} else {
		  		ar_date_str = "<font color=\"red\">" + detail.getAr_date_str() + "</font>";
		  	}%>
		    <tr align="center">
		      <%if (detail.getFlag() == 0 ){ num++; %>  
		      <td><input type="checkbox" name="details" value="<%=detail.getPkNo()%>" class="check"/>
		      <input type="hidden" name="hidden_otno" value="<%=detail.getPkNo() %>" ></td>
		     
		      <%}else{%>
		      <td>&nbsp</td>
		      <%} %>
		      
		      <td><%=ar_date_str%> </td>
		     
		      <input type="hidden" name="ar_date_str_no<%=detail.getPkNo()%>" value="<%=detail.getAr_date_str()%>"></td>
		      <td>(<%=detail.getEmpID()%>)&nbsp;<%=detail.getEmpName()%></td>
		      <input type="hidden" name="emp_id_no<%=detail.getPkNo()%>" value="<%=detail.getEmpID()%>"></td>
		      <input type="hidden" name="emp_name_no<%=detail.getPkNo()%>" value="<%=detail.getEmpName()%>"></td>
		      <td nowrap="nowrap"><%=detail.getDeptName()%></td>
		       <input type="hidden" id="deptid<%=detail.getPkNo()%>" name="deptid<%=detail.getPkNo()%>" value="<%=detail.getDeptId()%>"></td>
		    
		      
		       <td>
		      		   <table>
				      <tr>
				      <td>
				       
				         <%=detail.getShiftName()%>
				         
				      </td>
				      </tr>	
				       
				      </table>	  	
		      </td>
		      
		       <td>
		      		   <table>
				      <tr>
				      <td>
				       <span style="color: green">
				         <%=detail.getItemName()%>(现)
				         </span>
				      </td>
				      </tr>	
				          <tr>  
				        <td>
				        <span style="color: blue">
				         <%=detail.getItemName1()%>(原)
				         </span>
				      </td>
				      </tr>	  
				      </table>	  	
		      </td>
		      
		   
		       <input type="hidden" name="item_name_no<%=detail.getPkNo()%>" value="<%=detail.getItemName()%>"></td>
			      
		     
		      <td>
				      <table>
				      <tr>
				      <td>
				      <% if(admin1.getLanguagePreference().equals("zh")){%>
				      		<%=StringUtil.checkNull(detail.getFromTime(),"未刷卡")%><br><%=StringUtil.checkNull(detail.getToTime(),"未刷卡")%></td>
				      	  <%}else{%>
				      	  	<%=StringUtil.checkNull(detail.getFromTime(),"No Record")%><br><%=StringUtil.checkNull(detail.getToTime(),"No Record")%></td>
				      	  <%}%>
				      </td>
				       </tr>	 
				      
				      </table>	  
		      <td>
		      		   <table>
				      <tr>
				      <td>
				       <span style="color: green">
				         <%=detail.getQuantity()%>&nbsp;<%=detail.getUnitName()%>(现)
				         </span> 
				      </td>
				      </tr>	
				          <tr>  
				        <td>
				         <span style="color: blue">
				         <%=detail.getQuantity1()%>&nbsp;<%=detail.getUnitName()%>(原)
				          </span>
				      </td>
				      </tr>	  
				      </table>	  	
		      
		      </td>
		      <input name="night_<%=detail.getPkNo()%>" type="hidden" value="N">
		      	
		        <td>
				         <%=detail.getCreateBy1()%>
		      </td>
		       <td>
				         <%=detail.getCreateDate1()%>
		      </td>
	
		      
		       <td>
		    	<a  style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=detail.getPkNo()%>');">
		    		<span class=ellipsis_row ><%=StringUtil.checkNull(detail.getRemark())%></span>
	            </a>
		    	<input type = "hidden" id="memo2_<%=detail.getPkNo()%>"  value="<%=StringUtil.checkNull(detail.getRemark()) %>" />	            
               </td>
		      	
		     
		       <td class="info_content_09" nowrap>
				<font color="<%=(String) colorMap.get(String.valueOf(detail.getFlag()))%>">
					<%=(String) confirmMap.get(String.valueOf(detail.getFlag()))%>
		    		<%if (detail.getFlag() == 0) {%>
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=detail.getPkNo() %>, 1, false, <%=num %>);"><!--  通过-->
		    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
		    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=detail.getPkNo()%>, 2, false, <%=num %>);"><!-- 否决 -->
		    		<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
		    			</span>
		    		<%} else if (detail.getFlag() == 1) {%>
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=detail.getPkNo() %>, 1, false, <%=num %>);"><!--  通过-->
		    			
		    			</span>
		    		<%} else if (detail.getFlag() == 2) {%>        
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=detail.getPkNo() %>, 2, false, <%=num %>);"><!-- 否决 -->
		    		
		    			</span>
					<%}%>                                       
				</font>
				<%--<input	name="text_remark"  type="hidden" value="<%=StringUtil.checkNull(essLeaveBean.getLeaveReason(), "")%>"/>--%>
			</td>	
			  <!-- 
			<td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark<%=detail.getPkNo()%>" style=" height: 25px;width:100px " type="_moz"
						onfocus="this.style.height='50px'" onblur="this.style.height='25px';"></textarea>
				</td>
		    </tr>
		    -->
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
			               link="/essControlServlet"
			               parameters="&operation=view&content=ardetailconfirm&menu_code=ess0916&sDate=${sDate}&eDate=${eDate}&key=${key}&deptid=${deptid}&itemNo=${itemNo}&personId=${personId}&qryType=${qryType}" 
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
