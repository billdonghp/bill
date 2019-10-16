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
<jsp:useBean id="leaveList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essLeaveBean" class="com.ait.ess.bean.EssLeaveBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
<%@page import="com.crystaldecisions.Utilities.Logger"%>
<html>
<head>
<!-- ess_leave_info.jsp -->
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#leftnewstd .ellipsis_row{width:40px}
.ellipsis_row{
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap;

}

</style>
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="leaveview";
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.flag.value="";
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="leaveview";
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}

function DeleteOne(cell) {
	var  msg ='<ait:message messageID="alert.ess.common.delete" module="ess"></ait:message>';
	if (confirm(msg)) {  
		document.ApplyForm.action = "/essControlServlet";
		document.ApplyForm.operation.value="delete";		
		var leaveNo = cell.parentNode.parentNode.childNodes[0].firstChild ;
    	leaveNo.checked = true ;	
		document.ApplyForm.submit();  
	}
}

function Delete() {
	
	var size = document.getElementsByName("leaveNo").length ;
    var leaveNo = document.getElementsByName("leaveNo") ;
  	var flag = false ;
  	
  	for (var i = 0 ; i < size ; ++i)
  	{
  		if (leaveNo[i].checked)
  		{
  			flag = true ;
  			break ;
  		}
  	}
  	
  	if (!flag) {
  		alert("请选择要删除的数据!!!");
  		return;
  	}
	
	if (confirm("你确定要删除当前已选择的记录？")) {  
		document.ApplyForm.operation.value="delete";
		document.ApplyForm.action = "/essControlServlet";
		document.ApplyForm.submit();  
	}
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var size = document.getElementsByName("leaveNo").length ;
    var leaveNo = document.getElementsByName("leaveNo") ;
  	for (var i=0 ; i< size; i++){
		leaveNo[i].checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function importExcel(leaveNo,type){

	if(type==1)
	{
		document.ApplyForm.action="/xlsReportServlet?xlsKey=leaveApplyForm&LeaveNo="+leaveNo+"&type="+type;
	 	document.ApplyForm.submit(); 		
	}else if(type==2)
	{
		document.ApplyForm.action="/xlsReportServlet?xlsKey=leaveApplyForm&LeaveNo="+leaveNo+"&type="+type;
	 	document.ApplyForm.submit(); 	
	}else {
		document.ApplyForm.action="/xlsReportServlet?xlsKey=leaveApplyForm&LeaveNo="+leaveNo+"&type="+type;
	 	document.ApplyForm.submit(); 
	}
}


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
				  ,autoScroll: true
				  , layout : 'fit'
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
				 ,autoScroll: true
				  , layout : 'fit'
				 , html : html
				 ,title : '详细信息'
			});
		editDlg.show();	
} 

function lookFile(leaveNo)
{
	document.ApplyForm.action = "/xlsReportServlet?xlsKey=lookFile&leaveNo="+leaveNo;
	document.ApplyForm.submit(); 
}

function exportExcel()
{
	document.ApplyForm.action = "/essControlServlet";
	document.ApplyForm.content.value="exportLeaveViewExcel";
	document.ApplyForm.submit();
}

</script>
<form name="ApplyForm" action="/essControlServlet" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_search.jsp"%>
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
	          <td width="10%" class="info_title_01"><!--  开始日期-->
	         <ait:message messageID="display.mutual.start_date" module="ess"></ait:message>   
	          </td>
	          <td width="7%" class="info_content_00"><input type="text" name="sDate" size="7" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
	          <td width="10%" class="info_title_01"><!-- 结束日期 -->
	           <ait:message messageID="display.mutual.end_date" module="ess"></ait:message>  
	          </td>
	          <td width="7%" class="info_content_00"><input type="text" name="eDate" size="7" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>

			  <td width="10%" class="info_title_01"><!-- 部门 -->
	           <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>              
	          <td width="15%" class="info_content_00"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
	          <td width="10%" class="info_title_01"><!--  状态-->
		       <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		      </td>
		          <td width="10%" class="info_content_00">
				     <select name="qryType">
				        <option value="0"  <c:if test="${qryType==0}">selected</c:if>>未确认    
				         </option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认      
				         </option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决   
				         <option value="3" <c:if test="${qryType==3}">selected</c:if>>全部 
				         </option>                
				     </select>
			</td>  
			
			<!-- 添加查询  11-18-->
			
			 <%		    	
		    	if(admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") ){
			  %>
			
			<td width="10%" class="info_title_01"><!--  状态-->
		       出入状态            	  
		      </td>
		     
		       <td width="10%" class="info_content_00">
				     <select name="stsType">
				     <option value="0" <c:if test="${stsType==0}">selected</c:if>>未确认 
				        <option value="1"  <c:if test="${stsType=='1'}">selected</c:if>>正常  
				         </option>   
				         <option value="2" <c:if test="${stsType=='2'}">selected</c:if>>异常      
				         </option>
				           
				         <option value="3" <c:if test="${stsType==3}">selected</c:if>>全部 
				         </option>                
				     </select>
			</td>  
			 <%
				}	
		      %>
	          <td width="10%" class="info_title_01"><!-- 关键字 -->
	             <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>     
	          </td>
	          <td width="10%" class="info_content_00">
	          <input type="text" name="key" value="${key}" /></td>
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
				<td align="left" class="title1" colspan="10">勤态信息
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		  	<td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 -->全<br>选</td>
		    <td class="info_title_01" nowrap>序<br>号</td>
		    <td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap>姓名</td>
		    <td class="info_title_01" nowrap><!-- 部门 -->
		      <ait:message messageID="display.mutual.department" ></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!--职级  -->
		    <ait:message messageID="display.mutual.post_grade" ></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 申请日期 -->
		    <ait:message messageID="display.mutual.request_date" module="ess"></ait:message> 
		    </td>
		    <td class="info_title_01" nowrap><!--  休假类型-->
		  勤态<br>类型   
		    </td>
		    <td class="info_title_01" nowrap>记假<br>方式		  
		    </td>
		   <td class="info_title_01" nowrap>勤态时段</td>
		   <td class="info_title_01" nowrap>创建者</td>
		    <td class="info_title_01" nowrap>申请<br>书</td>
			<td class="info_title_01" nowrap><!-- 申请原因 -->
		   申请<br>原因
		    </td>
		    <td class="info_title_01" nowrap>查看<br>文件 
		    </td>
		    <td class="info_title_01" nowrap>决裁<Br>意见</td>
		    <td class="info_title_01" nowrap><!--  决裁情况-->
			 <ait:message messageID="display.mutual.status_2" module="ess"></ait:message>   
			</td>
			  <%		    	
		    	if(admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") ){
			  %>	
			<td class="info_title_01" nowrap>出入<Br>状态</td>
			  <%
				}	
		      %>
		    <td class="info_title_01" nowrap><!-- 删除 -->
		      删<br>除
		    </td>
		  </tr>
		<%for(int i=0;i<leaveList.size();i++){
			essLeaveBean = (EssLeaveBean) leaveList.get(i);%>
		  <tr align="center">
		  	<td height="30" class="info_content_09" nowrap="nowrap">		  	
		  	<%if (essLeaveBean.getDel_flag() == 1) {%>
		     <input type="checkbox" name="leaveNo" value="<%= essLeaveBean.getLeaveNo()%>" />
		     <% }else{
		  		out.println("&nbsp;") ;
		    	}	
		     %>
		    </td>
		  	<td class="info_content_09" nowrap><%= i + 1 %></td>
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
			<td class="info_content_09" id="leftnewstd">
			<span class=ellipsis_row title='<%=StringUtil.checkNull(essLeaveBean.getPostGradeCode())%> '><%=StringUtil.checkNull(essLeaveBean.getPostGradeCode())%></span>
			</td>
		    <td class="info_content_09" nowrap><%=essLeaveBean.getCreateDate() %></td>
		    <td class="info_content_09" id="leftnewstd">
		    <span class=ellipsis_row title='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName())%> '><%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName())%></span>
		   </td>
		    <td class="info_content_09" nowrap><%=essLeaveBean.getApplyLeaveType() %></td>	
		    <td class="info_content_09" nowrap><%=essLeaveBean.getLeaveFromTime() %><br><%=essLeaveBean.getLeaveToTime() %></td>
		    <td class="info_content_09" nowrap><%=essLeaveBean.getCrechineseName()%></td>
		    <%
		    	
		    	if(admin.getCpnyId().equals("63000000")){
		    		
		    	//if (essLeaveBean.getMax_affirm_flag()== 1) {
		    	if (1== 1) {//只要申请就可以导出勤态书20101119改
		  		// 年休假
		    	if(essLeaveBean.getLeaveTypeCode().equals("H9")){
		    %>	
		    		<td class="info_content_09" nowrap>
		    			<a onclick="importExcel('<%= essLeaveBean.getLeaveNo()%>',1)" href="#" title="点击导出年休假申请书">
		    				<font color="red">导出</font>
		    			</a>
		    		</td>
		    <%  // 休职,病休1,病休2,有薪工伤,无薪工伤 
		    	}else if(essLeaveBean.getLeaveTypeCode().equals("H8") || essLeaveBean.getLeaveTypeCode().equals("H13") 
		    		  || essLeaveBean.getLeaveTypeCode().equals("H14") || essLeaveBean.getLeaveTypeCode().equals("H4") || essLeaveBean.getLeaveTypeCode().equals("H26")
		    		  ){
		    		
		    %>
		    		<td class="info_content_09" nowrap>
		    			<a onclick="importExcel('<%= essLeaveBean.getLeaveNo()%>',2)" href="#" title="点击导出休职申请书">
		    				<font color="red">导出</font>
		    			</a>
		    		</td>
		    <%
		    	}else{
		    %>
		    		<td class="info_content_09" nowrap>
		    			<a onclick="importExcel('<%= essLeaveBean.getLeaveNo()%>',3)" href="#" title="点击导出勤态申请书">
		    				<font color="red">导出</font>
		    			</a>
		    		</td>
		    <%
		    	}}else{
		    %>
		    	<td class="info_content_09" nowrap>&nbsp;</td>
		    <%	
		    	}}else if(essLeaveBean.getMax_affirm_flag()== 1){
			%>
				<td class="info_content_09" nowrap>
		    			<a onclick="importExcel('<%= essLeaveBean.getLeaveNo()%>',3)" href="#" title="点击导出勤态申请书">
		    				<font color="red">导出</font>
		    			</a>
		    	</td>
			<%
		    	}else {
			%>
				<td class="info_content_09" nowrap>&nbsp;</td>
			<%
		    	}
			%>
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
		     <td class="info_content_00" nowrap="nowrap">
		    	<!--<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=essLeaveBean.getLeaveNo()%>');">
		    		<span class=ellipsis_row><%=StringUtil.checkNull(essLeaveBean.getRemark()) %></span>
	            </a>
		    	<input type = "hidden"
		    		   id="memo2_<%=essLeaveBean.getLeaveNo()%>" 
		    		   value="<%=StringUtil.checkNull(essLeaveBean.getRemark()) %>" />	            
            -->
            <%  essAffirmorList = essLeaveBean.getAffirmorList();
			    if (essAffirmorList.size() > 0) {
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<a style="cursor:hand" id="leftnewstd" title='查看详细信息' onClick="showMemo2('<%=essAffirmor.getAffirmNo()%>');">
		    				<span class=ellipsis_row><%=StringUtil.checkNull(essAffirmor.getRemark()) %></span>
	            		</a>
				    	<input type = "hidden"
				    		   id="memo2_<%=essAffirmor.getAffirmNo()%>" 
				    		   value="<%=StringUtil.checkNull(essAffirmor.getRemark()) %>" />
				    <%}
				} else {%>
					&nbsp;
				<%}%>
            </td>
		    <td class="info_content_09" nowrap align="left">
			    <%essAffirmorList = essLeaveBean.getAffirmorList();
			    if (essAffirmorList.size() > 0) {
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
			    		<%=essAffirmor.getAffirmorName() + StringUtil.getString((4 - essAffirmor.getAffirmorName().length()) , "&nbsp;&nbsp;&nbsp;&nbsp;") + " " +  StringUtil.checkNull(essAffirmor.getUpdateDate()) + "&nbsp;&nbsp;&nbsp;&nbsp;" + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
				    	</font><br>
				    <%}
				} else {%>
					&nbsp;
				<%}%>
		     <%if(essLeaveBean.getOpFlag()!=null){ %>
			<%if(essLeaveBean.getOpFlag()==0 ||essLeaveBean.getOpFlag()==3 ){ %><font color="<%=(String) colorMap.get("0")%>">人事未确认</font><%}%>
			<%if(essLeaveBean.getOpFlag()==1){ %><font color="<%=(String) colorMap.get("1")%>">人事已确认</font><%}%>
			<%if(essLeaveBean.getOpFlag()==2){ %><font color="<%=(String) colorMap.get("2")%>">人事已否决</font><%}%>
			
			<%}%>
		    </td>		    
		    <%		    	
		    	if(admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") ){
			  %>	
			<td class="info_content_09" nowrap><%=essLeaveBean.getSts() %></td>
			  <%
				}	
		      %>
		    <td class="info_content_09" nowrap>
		   		<%if (essLeaveBean.getDel_flag() == 1) {%>
		    		<span style="color:red; cursor:pointer;" onClick="DeleteOne(this);">
		    		<ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand" title="删除" enTitle="delete" /></span>
				<%} else out.print("&nbsp;");%>
			</td>
		  </tr>
		  <div id="opition_<%=i %>" style="display: none;">
			<%essAffirmorList = essLeaveBean.getAffirmorList();
			    if (essAffirmorList.size() > 0) {
				    for(int j=0;j<essAffirmorList.size();j++){
				    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
				    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
			    		<%=essAffirmor.getAffirmorName() + " " +  StringUtil.checkNull(essAffirmor.getUpdateDate()) + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
				    	</font>&nbsp;<%=StringUtil.checkNull(essAffirmor.getRemark()) %><br>
				    <%}
				} else {%>
					&nbsp;
				<%}%>
		    <%if(essLeaveBean.getOpFlag()!=null){ %>
			<%if(essLeaveBean.getOpFlag()==0 ||essLeaveBean.getOpFlag()==3 ){ %><font color="<%=(String) colorMap.get("0")%>">人事未确认</font><%}%>
			<%if(essLeaveBean.getOpFlag()==1){ %><font color="<%=(String) colorMap.get("1")%>">人事已确认</font>&nbsp;<%=StringUtil.checkNull(essLeaveBean.getRemark())%><%}%>
			<%if(essLeaveBean.getOpFlag()==2){ %><font color="<%=(String) colorMap.get("2")%>">人事已否决</font>&nbsp;<%=StringUtil.checkNull(essLeaveBean.getRemark())%><%}%>
			
			<%}%>
	      </div>
		<%}%>
		</table>
		
		<!-- Page Navigation Start-->
					<ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=leaveview&menu_code=${param.menu_code}&sDate=${sDate}&eDate=${eDate}&deptID=${deptID}&key=${key}&qryType=${qryType}&stsType=${stsType}" 
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
<input type="hidden" name="flag" value="${requestScope.flag }" />
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="operation" value="view" />
<input type="hidden" name="content" value="leaveview" />
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="${currentPage}" />

</form>
</body>
</html>
