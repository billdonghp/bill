<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssLeaveBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="confirmMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="leaveList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essLeaveBean" class="com.ait.ess.bean.EssLeaveBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}
#leftnewstd .ellipsis_row2{width:70px}
.ellipsis_row2{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;
}
</style>
</head>
<body>
<script language="javascript1.5" type="text/javascript">

function showMemo(val) {
	var memo = document.getElementById('memo_'+val).value;
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
				  , layout : 'fit'
				   ,autoScroll: true
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
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
				  , layout : 'fit'
				   ,autoScroll: true
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
}

function showReport(leaveNo,report){

    theUrl="/reportControlServlet?operation=crystal&reportName="+report+"&leaveNo="+leaveNo;
    var name = "";
    var features = "status=yes,menubar=no,resizable=yes,scrollbars=yes,width=800,height=600";
    window.open(theUrl,name,features);
}


function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="leaveconfirm";
	document.ApplyForm.currentPage.value='1';
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="leaveconfirm";
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}
function Confirm(leaveno, flag, batchTag, sign) {
var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';
var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';
var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	var tags = document.getElementsByName("ck_selectedTag");
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
			document.ApplyForm.leaveno.value=leaveno;
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
	 document.ApplyForm.leaveno.value=leaveno;
	 document.ApplyForm.flag.value=flag;
	 document.ApplyForm.batchTag.value=batchTag;
	 document.ApplyForm.submit();  } 
   }
}
function checkAll()
{
  var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
    {
      document.ApplyForm.elements[i].checked = selected;
    }
  }
  document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function exportExcel()
{
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="exportLeaveConfirmExcel";
	document.ApplyForm.submit();
}

function lookFile(leaveNo)
{
	document.ApplyForm.action = "/xlsReportServlet?xlsKey=lookFile&leaveNo="+leaveNo;
	document.ApplyForm.submit(); 
}

</script>
	<%
	   String qryType = request.getParameter("qryType"); 
	%>
	<%! String selected(String valueSel, String value){
	      return valueSel.equals(value) ? "selected" : "";
	    }
	 %>
<form name="ApplyForm" action="" method="post">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_confirm.jsp"%>
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
				<td class="title1"><!--查询条件  -->
				<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
		    <tr>
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		          <td width="10%" class="info_title_01"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message>       
		          </td>
		          <td width="10%" class="info_content_00"><input type="text" name="sDate" size="10" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
		          <td width="10%" class="info_title_01"><!--结束日期  -->
		<ait:message 	messageID="display.mutual.end_date" module="ess"></ait:message>          
		          </td>
		          <td width="10%" class="info_content_00"><input type="text" name="eDate" size="10" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>
		         
		       	  <td width="7%" class="info_title_01"><!-- 状态 -->
		    <ait:message messageID="display.mutual.status" module="ess"></ait:message>   	  
		       	  </td>
		          <td width="10%" class="info_content_00">
		          <%--   
				     <select name="qryType">
				     	 <option value="4" <%= selected("4", qryType) %>>未确定</option>   
				         <option value="0" <%= selected("0", qryType) %>>全部</option>   
				         <option value="1" <%= selected("1", qryType) %>>全部决裁</option>  
				         <option value="2" <%= selected("2", qryType) %>>部分决裁</option> 
				         <option value="3" <%= selected("3", qryType) %>>未决裁</option>                
				     </select>
				  --%>
				     <select name="qryType">
							<option value="0" <%= selected("0", qryType) %>><!-- 未决裁 -->
							<ait:message messageID="display.ess.approval.pending"
								module="ess"></ait:message></option>
							<option value="1" <%= selected("1", qryType) %>><!-- 已通过 -->
							<ait:message messageID="display.ess.approval.approved"
								module="ess"></ait:message></option>
							<option value="2" <%= selected("2", qryType) %>><!-- 未通过 -->
							<ait:message messageID="display.ess.approval.rejected"
								module="ess"></ait:message></option>
							<option value="3" <%= selected("3", qryType) %>><!-- 全部 -->
							<ait:message messageID="display.ess.approval.all"
								module="ess"></ait:message></option>
						</select>
				  </td>
		        </tr>
		        <tr>
		          <td class="info_title_01"><!-- 部门 --> <ait:message
							messageID="display.mutual.dept"></ait:message></td>
						<td  class="info_content_00"><ait:selDept
							name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
						<td  class="info_title_01"><!-- 关键字 --> <ait:message
							messageID="display.mutual.key_word" module="ess"></ait:message></td>
						<td  class="info_content_00"><input type="text"
							name="key" value="${key }" /></td>
						<td class="info_title_01"></td>  
						<td  class="info_content_00"></td>
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
					<td align="left" class="title1" colspan="10">勤态确认
					</td>        
				</tr>
			</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td class="info_title_01" nowrap><a href="#" onclick="checkAll();" ><!--  全选-->
		    全<br>选
		    </a></td>
		    <td class="info_title_01" nowrap><!-- 工号 -->
		    职号
		    </td>
		    <td class="info_title_01" nowrap><!-- 休假人 -->
		    姓名
		    </td>
		    <%--
		    <td class="info_title_01" nowrap><!--申请号  --> <ait:message
					messageID="display.ess.confirmation.personal_info.request_no"
					module="ess"></ait:message></td>
			 --%>
		    <td class="info_title_01" nowrap><!--  部门-->
		    <ait:message messageID="display.mutual.department" module="ess"></ait:message>
		    </td>
		  <!--  <td class="info_title_01" nowrap>  职位
		    <ait:message messageID="display.mutual.position"></ait:message>
		    </td>-->
		 <!--   <td class="info_title_01" nowrap> 岗位职务 
		   <ait:message messageID="display.mutual.post"></ait:message> 
		    </td> -->
		    <td class="info_title_01" nowrap><!-- 申请日期 -->
		    <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 休假类型 -->
		    休假<Br>类型
		    </td>
		    <td class="info_title_01" nowrap>记假<Br>方式 </td>
		    <%--
		    <td class="info_title_01" nowrap><!--  倒休可用天数-->
		  <ait:message messageID="display.mutual.hours_substitution" module="ess"></ait:message>  
		    </td>
		     --%>
		    <td class="info_title_01" nowrap>勤态时段
		    </td>
		    <td class="info_title_01" nowrap><!-- 休假原因 -->
		   <ait:message messageID="display.ess.confirmation.time_off.reason" module="ess"></ait:message> 
		    </td>
		    <td class="info_title_01" nowrap>查看<Br>文件 
		    </td>
           <td class="info_title_01" nowrap>决裁情况</td>
		    <td class="info_title_01" nowrap><!-- 人事确认 -->
		   人事<br>确认 
		    </td>
		    <td class="info_title_01" nowrap>意见</td>
		    <!-- <td class="info_title_01" nowrap>报表</td>  -->
		  </tr>
		<% int temp = -1;
		for(int i=0;i<leaveList.size();i++){
			essLeaveBean = (EssLeaveBean) leaveList.get(i);%>
		  <tr >
		    <td><%if (essLeaveBean.getOpFlag()>=0) {
		    		temp++;
		    	%>
		    	<input type="checkbox" name="ck_selectedTag" value="<%=essLeaveBean.getLeaveNo()%>"/>
		    	<input type="hidden" name="hidden_otno" value="<%=essLeaveBean.getLeaveNo() %>" ><%} else {%>&nbsp;<%}%>
		    </td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=essLeaveBean.getEmpId() %>'><%=essLeaveBean.getEmpId() %></span>
		    </td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=essLeaveBean.getChineseName() %>'><%=essLeaveBean.getChineseName() %></span>
		    </td>
			<td class="info_content_09" id="leftnewstd">
				<%
			    	if (essLeaveBean.getDeptName().equals(essLeaveBean.getFourthDeptName())){
			    %>
			    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essLeaveBean.getDeptName())%>'><%=StringUtil.checkNull(essLeaveBean.getDeptName()) %></span>
			    <% 
			    	}else{
			    %>		
			    	<span class=ellipsis_row2 title='<%=StringUtil.checkNull(essLeaveBean.getFourthDeptName())%> <%=StringUtil.checkNull(essLeaveBean.getDeptName()) %>'>
			    	<%=StringUtil.checkNull(essLeaveBean.getFourthDeptName())%><br><%=StringUtil.checkNull(essLeaveBean.getDeptName()) %></span>
			    <%  } %>
			</td>
<!--		    <td class="info_content_09" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPositon())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getPosition())%>'></ait:content>&nbsp;
		   </td>
		    <td class="info_content_09" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPost())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getPost())%>'></ait:content>&nbsp;
		    </td>-->
		    <td class="info_content_09" nowrap><%=essLeaveBean.getCreateDate() %></td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName())%> '><%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName())%></span>
		   </td>
		    <td class="info_content_09" nowrap>
		   <%=StringUtil.checkNull(essLeaveBean.getApplyLeaveType())%>&nbsp;
		    </td>
		    <%-- 
		    <td class="info_content_09"  nowrap="nowrap"><!-- 本月 -->
		    <ait:message messageID="display.ess.confirmation.overtime.this_month" module="ess"></ait:message>
		    ：<%= essLeaveBean.getLeaveThisMonth() %>
		    <!-- 天 --><ait:message messageID="display.ess.attendance_request.absence.day" module="ess"></ait:message><br/><!-- 下月 -->
		    <ait:message messageID="display.ess.confirmation.overtime.next_month" module="ess"></ait:message>:
		    <%= essLeaveBean.getLeaveNextMonth() %><!-- 天 --> 
		  <ait:message messageID="display.ess.attendance_request.absence.day" module="ess"></ait:message>  
		    </td>  --%>
		    <td class="info_content_09" nowrap><%=essLeaveBean.getLeaveFromTime() %>
		    <br><%=essLeaveBean.getLeaveToTime() %></td>
		    <td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo('<%=essLeaveBean.getLeaveNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essLeaveBean.getLeaveReason()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo_<%=essLeaveBean.getLeaveNo()%>" 
		    		   value="<%=StringUtil.checkNull(essLeaveBean.getLeaveReason()) %>" />	            
            </td>
		    
		    <td class="info_content_09" nowrap>
		    <%
		    	if (essLeaveBean.getFileUploadFlag() == 1){
		    %>
		    		<a onclick="lookFile(<%= essLeaveBean.getLeaveNo()%>)" href="#" title="点击查看文件">
		    			<font color="red">查看</font>
		    		</a>
		    <% 	}else{%>
		    		&nbsp;
		    <%  } %>
		    </td>
		    <td class="info_content_09" nowrap>
			    <%essAffirmorList = essLeaveBean.getAffirmorList();       
			    if (essAffirmorList.size() > 0) {
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
			    		<%=essAffirmor.getAffirmorName() + StringUtil.getString((4 - essAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
				    	</font><br>
				    <%}
				} else {%>
					&nbsp;  
				<%}%>
		    </td>
		   
		    <td class="info_content_09" nowrap>
				<font color="<%=(String) colorMap.get(String.valueOf(essLeaveBean.getActivity()))%>">
					<%=(String) confirmMap.get(String.valueOf(essLeaveBean.getActivity()))%>
		    		<%if (essLeaveBean.getOpFlag() == 0) {%>
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 1, false, <%=temp %>);"><!--  通过-->
		    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
		    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=essLeaveBean.getLeaveNo()%>, 2, false, <%=temp %>);"><!-- 否决 -->
		    		<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
		    			</span>
		    		<%} else if (essLeaveBean.getOpFlag() == 1) {%>
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 1, false, <%=temp %>);"><!--  通过-->
		    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
		    			</span>
		    		<%} else if (essLeaveBean.getOpFlag() == 2) {%>        
		    			<br><span style="color:red; cursor:pointer;" 
		    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 2, false, <%=temp %>);"><!-- 否决 -->
		    		<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
		    			</span>
					<%}%>                                       
				</font>
				<%--<input	name="text_remark"  type="hidden" value="<%=StringUtil.checkNull(essLeaveBean.getLeaveReason(), "")%>"/>--%>
			</td>	
		 
		    <%--
		    <td  nowrap align="center">
		    <%if (essLeaveBean.getActivity() ==1) {%>
		        <a onClick="showReport('<%=essLeaveBean.getLeaveNo()%>','essLeave')" style="cursor:hand;" >报表</a>
		        <%} else if (essLeaveBean.getActivity() == 0)
		        {%>&nbsp;<%}%>
		    </td>
		    --%>
			    <%
				if (essLeaveBean.getOpFlag() == 0) {
				%>
				 <td width='100' style='word-break:break-all' align="left">
				    	<textarea name="remark<%=essLeaveBean.getLeaveNo()%>" style=" height: 25px;width:100px " type="_moz"
						onfocus="this.style.height='50px'" onblur="this.style.height='25px';"></textarea>
				</td>
			    	
				<%
				}else if(essLeaveBean.getRemark()!=null){
				%>
					<td class="info_content_00" nowrap="nowrap">
		    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=essLeaveBean.getLeaveNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essLeaveBean.getRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_<%=essLeaveBean.getLeaveNo()%>" 
		    		   value="<%=StringUtil.checkNull(essLeaveBean.getRemark()) %>" />	            
            </td>
				<%
				}else{%><td>&nbsp;</td>
				<%
				}
				%>
		  </tr>
		<%}%>
		</table>
		
		<table border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<%if(pageControl.getPageCount() > 10 && pageControl.getCurrentPage() > 10){%>
			  <td width="11%"><img src="/images/btn/p_last101.gif" align="absmiddle" style="cursor:pointer;" onClick="JumpPage(<%=((pageControl.getCurrentPage()-1)/10)*10%>)"></td>
			<%}%>
			<td>&nbsp;</td>
			<%int count = 0;
			for(int i=((pageControl.getCurrentPage()-1)/10)*10+1;count<10 && i<=pageControl.getPageCount();i++){
			  count++;%>
			  <td><b>
			  <%if (i==pageControl.getCurrentPage()) {%>
			  	<span style="color:#6600CC;"><%=i%></span>
			  <%} else {%>
			    <span style="color:#666666; cursor:pointer;" onClick="JumpPage(<%=i%>);"><%=i%></span>
			  <%}%>
			  </b></td><td>&nbsp;</td>
			<%}%>
			<%if(pageControl.getPageCount() > 10 && ((pageControl.getCurrentPage()-1)/10)*10+11 <= pageControl.getPageCount()){%>
			  <td width="11%"><img src="/images/btn/p_next101.gif" align="absmiddle" style="cursor:pointer;" onClick="JumpPage(<%=((pageControl.getCurrentPage()-1)/10)*10+11%>)"></td>
			<%}%>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<c:if test="${fn:length(leaveList) < 7}">
				<c:forEach var="i" begin="1" end="${7-fn:length(leaveList)}"
					step="1">
					<tr>
						<td class="info_content_09" height="30"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
						<td class="info_content_09"></td>
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
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="leaveconfirm" />
<input type="hidden" name="leaveno" value="" />
<input type="hidden" name="flag" value="" />
<input type="hidden" name="batchTag" value="" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />
</form>
</body>
</html>