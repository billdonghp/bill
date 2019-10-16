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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/meizzDate.js"></script>
</head>
<body>
<script  type="text/javascript">

function showReport(leaveNo,report){

    theUrl="/reportControlServlet?operation=crystal&reportName="+report+"&leaveNo="+leaveNo;
    var name = "";
    var features = "status=yes,menubar=no,resizable=yes,scrollbars=yes,width=800,height=600";
    window.open(theUrl,name,features);  
}


function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='1';
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
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

</script>
	<%
	   String qryType = request.getParameter("qryType"); 
	%>
	<%! String selected(String valueSel, String value){
	      return valueSel.equals(value) ? "selected" : "";
	    }
	 %>
<form name="ApplyForm" action="/essControlServlet">
	
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
		          <td width="7%" class="info_title_01"><!-- 部门 --> <ait:message
							messageID="display.mutual.dept"></ait:message></td>
						<td width="15%" class="info_content_00"><ait:selDept
							name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
						<td width="7%" class="info_title_01"><!-- 关键字 --> <ait:message
							messageID="display.mutual.key_word" module="ess"></ait:message></td>
						<td width="14%" class="info_content_00"><input type="text"
							name="key" value="${key }" /></td>
						<td width="7%" class="info_title_01"></td>  
						<td width="14%" class="info_content_00"></td>
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
					<td align="left" class="title1" colspan="10"><!--  出差确认-->
					 <ait:message messageID="display.ess.confirmation.biz_trip.biz_trip_confirmation" module="ess"></ait:message>
					</td>
				</tr>
			</table>
			<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			  <tr>
			    <td class="info_title_01" nowrap><a href="#" onclick="checkAll();"><!--  全选-->
		    <ait:message messageID="display.mutual.select_2" module="ess"></ait:message>
		    </a></td>
			    <td class="info_title_01" nowrap><!-- 工号 -->
		    <ait:message messageID="display.mutual.emp_id" module="ess"></ait:message>
		    </td>
			    <td class="info_title_01" nowrap><!-- 出差人 -->
			 <ait:message messageID="display.ess.confirmation.biz_trip.employee" module="ess"></ait:message>    
			    </td>
			    <td class="info_title_01" nowrap><!--申请号  --> <ait:message
					messageID="display.ess.confirmation.personal_info.request_no"
					module="ess"></ait:message></td>
			     <td class="info_title_01" nowrap><!--  部门-->
		    <ait:message messageID="display.mutual.department" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!--  职位-->
		    <ait:message messageID="display.mutual.position"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 岗位职务 -->
		   <ait:message messageID="display.mutual.post"></ait:message> 
		    </td> 
		    <td class="info_title_01" nowrap><!-- 申请日期 -->
		    <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
		    </td>
			    <td class="info_title_01" nowrap><!-- 出差类型 -->
			  <ait:message messageID="display.ess.confirmation.biz_trip.type" module="ess"></ait:message>   
			    </td>
			  <td class="info_title_01" nowrap><!--  倒休可用天数-->
		  <ait:message messageID="display.mutual.hours_substitution" module="ess"></ait:message>  
		    </td>
			    <td class="info_title_01" nowrap><!-- 出差时段 -->
			  <ait:message messageID="display.ess.confirmation.biz_trip.period" module="ess"></ait:message>   
			    </td>
			    <td class="info_title_01" nowrap><!-- 出差原因 -->
			     <ait:message messageID="display.ess.confirmation.biz_trip.reason" module="ess"></ait:message>
			    </td>
<!--			    <td class="info_title_01" nowrap>决裁情况</td>-->
			 <td class="info_title_01" nowrap><!-- 人事确认 -->
		   <ait:message messageID="display.mutual.confirmation" module="ess"></ait:message> 
		    </td>
		    <td class="info_title_01" nowrap><!-- 备注 -->
		  <ait:message messageID="display.mutual.notes" module="ess"></ait:message>  
		    </td>
			    <!-- <td class="info_title_01" nowrap>报表</td>  -->
			  </tr>
			<% int temp = -1;
			for(int i=0;i<leaveList.size();i++){
				essLeaveBean = (EssLeaveBean) leaveList.get(i);%>
			  <tr align="center">
			    <td class="info_content_01"><%if (essLeaveBean.getOpFlag()>=0) {
			    		temp++;
			    	%>
			    	<input type="checkbox" name="ck_selectedTag" value="<%=essLeaveBean.getLeaveNo()%>"/>
			    	<input type="hidden" name="hidden_otno" value="<%=essLeaveBean.getLeaveNo() %>" ><%} else {%>&nbsp;<%}%>
			    </td>
			    <td class="info_content_01" nowrap><%=essLeaveBean.getEmpId() %></td>
			   <td class="info_content_01" nowrap>
		   <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getChinesePinYin())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getChineseName())%>'></ait:content>&nbsp; 
		    </td>
		     <td class="info_content_01" nowrap><%=essLeaveBean.getLeaveNo()%></td>
		    <td class="info_content_01" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getDeptEnName())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getDeptName())%>'></ait:content>&nbsp;
		    </td>            
		    <td class="info_content_01" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPositon())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getPosition())%>'></ait:content>&nbsp;
		   </td>
		    <td class="info_content_01" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPost())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getPost())%>'></ait:content>&nbsp;
		    </td>
		    <td class="info_content_01" nowrap><%=essLeaveBean.getCreateDate() %></td>
		    <td class="info_content_01" nowrap>
		    <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeEnName())%>'
					zhContent='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName())%>'></ait:content>&nbsp;
		    </td>
			    <td class="info_content_01" nowrap="nowrap"><!-- 本月 --> 
			      <ait:message messageID="display.ess.confirmation.overtime.this_month" module="ess"></ait:message> ：
			   <%=essLeaveBean.getLeaveThisMonth() %><!-- 天 --><ait:message messageID="display.ess.attendance_request.absence.day" module="ess"></ait:message>  <br/>
			    <!--  下月--><ait:message messageID="display.ess.confirmation.overtime.next_month" module="ess"></ait:message>：
			    <%=essLeaveBean.getLeaveNextMonth() %><!--天  --><ait:message messageID="display.ess.attendance_request.absence.day" module="ess"></ait:message>   </td>
			    <td class="info_content_01" nowrap><%=essLeaveBean.getLeaveFromTime() %><br><%=essLeaveBean.getLeaveToTime() %></td>
			    <td class="info_content_01" nowrap="nowrap">
			    	<a href="/essControlServlet?operation=retrieveApplyEvectionDetail&menu_code=<%=menu_code%>
			    	&leaveNo=<%=essLeaveBean.getLeaveNo()%>" target="_blank"><!-- 查看内容 -->
			    	<ait:message messageID="display.ess.approval.query_on_detail" module="ess"></ait:message>
			    	</a>
			    </td>
			    <%-- 
			    <td class="info_content_01" nowrap>
				    <%essAffirmorList = essLeaveBean.getAffirmorList();   
				    if (essAffirmorList.size() > 0) {
					    for(int j=0;j<essAffirmorList.size();j++){
					    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
					    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
				    		<%=essAffirmor.getAffirmorName() + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
					    	</font><br>
					    <%}
					} else {%>
						&nbsp;
					<%}%>
			    </td>
			    --%>
			    <td class="info_content_01" nowrap>
					<font color="<%=(String) colorMap.get(String.valueOf(essLeaveBean.getActivity()))%>">
						<%=(String) confirmMap.get(String.valueOf(essLeaveBean.getActivity()))%>
			    		<%if (essLeaveBean.getOpFlag() == 0) {%>
			    			<br><span style="color:red; cursor:pointer;" 
			    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 1, false, <%=temp %>);"><!--通过  -->
			    		<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
			    			</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" 
			    			onClick="Confirm(<%=essLeaveBean.getLeaveNo()%>, 2, false, <%=temp %>);"><!-- 否决 -->
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
			    			</span>
			    		<%} else if (essLeaveBean.getOpFlag() == 1) {%>
			    			<br><span style="color:red; cursor:pointer;" 
			    			
			    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 1, false, <%=temp %>);"><!--通过  -->
			    			<ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
			    			</span>
			    		<%} else if (essLeaveBean.getOpFlag() == 2) {%>
			    			<br><span style="color:red; cursor:pointer;" 
			    			onClick="Confirm(<%=essLeaveBean.getLeaveNo() %>, 2, false, <%=temp %>);"><!-- 否决 -->
			    			<ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
			    			</span>
						<%}%>
					</font>
				</td>
				<td align="left" style="width: 80px "><textarea name="text_remark" rows="2"><%=StringUtil.checkNull(essLeaveBean.getRemark(), "")%></textarea></td>
			 
			    <%--
			    <td  nowrap align="center">
			    <%if (essLeaveBean.getActivity() ==1) {%>
			        <a onClick="showReport('<%=essLeaveBean.getLeaveNo()%>','essLeave')" style="cursor:hand;" >报表</a>
			        <%} else if (essLeaveBean.getActivity() == 0)
			        {%>&nbsp;<%}%>
			    </td>
			    --%>
			          
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
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="evectionconfirm" />
	<input type="hidden" name="leaveno" value="" />
	<input type="hidden" name="flag" value="" />
	<input type="hidden" name="batchTag" value="" />
	<input type="hidden" name="ck_all" value="0" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />
</form>
</body>
</html>