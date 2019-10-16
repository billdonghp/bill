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
<script language="javascript1.5" type="text/javascript">
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='1';
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}
function Delete(leaveno) {

var  msg ='<ait:message messageID="alert.ess.common.delete" module="ess"></ait:message>';
	if (confirm(msg)) {
		document.ApplyForm.operation.value="delete";
		document.ApplyForm.leaveNo.value=leaveno;
		document.ApplyForm.submit();
	}
}
</script>
<form name="ApplyForm" action="/essControlServlet">

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
	          <td width="10%" class="info_title_01"><!-- 开始日期 -->
	         	 <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
	          </td>
	          <td width="15%" class="info_content_00"><input type="text" name="sDate" size="10" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
	          <td width="10%" class="info_title_01"><!-- 结束日期 -->
	          	  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message>
	          </td>
	          <td width="15%" class="info_content_00"><input type="text" name="eDate" size="10" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>
	          <td width="10%" class="info_title_01"><!-- 部门 -->
	          <ait:message messageID="display.mutual.dept" ></ait:message>
	          </td>
	          <td width="15%" class="info_content_00"><ait:selDept name="deptID" selected="${deptID}"  supervisorType="ar"/></td>
	          <td width="10%" class="info_title_01"><!-- 关键字 -->
	          <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>
	          </td>
	          <td width="15%" class="info_content_00">
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
				<td align="left" class="title1" colspan="10"><!-- 出差申请 -->
				<ait:message messageID="display.ess.review.biz_trip.biz_trip_request" module="ess"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <td class="info_title_01" nowrap><!-- 工号 -->
		    	<ait:message messageID="display.mutual.emp_id" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 出差人 -->
		    	<ait:message messageID="display.ess.review.biz_trip.employee" module="ess"></ait:message>
		    </td>
		       <td class="info_title_01" nowrap><!--申请号  --> <ait:message
					messageID="display.ess.confirmation.personal_info.request_no"
					module="ess"></ait:message></td>
		    <td class="info_title_01" nowrap><!--  部门-->
		      <ait:message messageID="display.mutual.department" ></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!--  职位-->
		    	 <ait:message messageID="display.mutual.position" ></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!--  岗位职务-->
		    	<ait:message messageID="display.mutual.post" ></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 申请日期 -->
		      <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 出差类型 -->
		    	<ait:message messageID="display.ess.review.biz_trip.type" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 出差时段 -->
		    	<ait:message messageID="display.ess.review.biz_trip.period" module="ess"></ait:message>
		    </td>
		     <td class="info_title_01" nowrap><!-- 出差原因 -->
			     <ait:message messageID="display.ess.confirmation.biz_trip.reason" module="ess"></ait:message>
			    </td>
		    <!-- <td class="info_title_01" nowrap>备注</td>  -->
<!--		    <td class="info_title_01" nowrap>决裁情况</td>-->
		    <td class="info_title_01" nowrap><!-- 人事确认 -->
		    	 <ait:message messageID="display.mutual.confirmation" module="ess"></ait:message>
		    </td>
		    <td class="info_title_01" nowrap><!-- 删除 -->
		    	 <ait:message messageID="display.mutual.delete" module="ess"></ait:message>
		    </td>
		  </tr>
		<%for(int i=0;i<leaveList.size();i++){
			essLeaveBean = (EssLeaveBean) leaveList.get(i);%>
		  <tr align="center">
		    <td class="info_content_01" nowrap><%=essLeaveBean.getEmpId() %></td>
		    <td class="info_content_01" nowrap>
		     <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getChinesePinYin()) %>' 
		    zhContent='<%=StringUtil.checkNull(essLeaveBean.getChineseName()) %>'></ait:content>&nbsp;</td>
		    <td class="info_content_01" nowrap><%=essLeaveBean.getLeaveNo() %></td>
		    <td class="info_content_01" nowrap>
		     <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getDeptEnName()) %>' 
		    zhContent='<%=StringUtil.checkNull(essLeaveBean.getDeptName()) %>'></ait:content>&nbsp;
           </td>    
		    <td class="info_content_01" nowrap>
           <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPositon()) %>' 
		    zhContent='<%=StringUtil.checkNull(essLeaveBean.getPosition()) %>'></ait:content>&nbsp;
        </td>
		    <td class="info_content_01" nowrap>
		     <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getEnPost()) %>' 
		    zhContent='<%=StringUtil.checkNull(essLeaveBean.getPost()) %>'></ait:content>&nbsp;
		    </td>
		    <td class="info_content_01" nowrap><%=essLeaveBean.getCreateDate() %></td>
		    <td class="info_content_01" nowrap>
         <ait:content enContent='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeEnName()) %>'         
		    zhContent='<%=StringUtil.checkNull(essLeaveBean.getLeaveTypeName()) %>'></ait:content>&nbsp;    
          </td>
		    <td class="info_content_01" nowrap><%=essLeaveBean.getLeaveFromTime() %><br><%=essLeaveBean.getLeaveToTime() %></td>
		    <td class="info_content_01" nowrap="nowrap">
		    	<a href="/essControlServlet?operation=retrieveApplyEvectionDetail&menu_code=<%=menu_code%>
			    	&leaveNo=<%=essLeaveBean.getLeaveNo()%>" target="_blank"><!-- 查看内容 -->
			    	<ait:message messageID="display.ess.approval.query_on_detail" module="ess"></ait:message>
			    	</a>
		    </td> 
		    <%--
		    <td class="info_content_01">
		    	<a href="/essControlServlet?operation=retrieveApplyEvectionDetail&menu_code=<%=menu_code%>&leaveNo=<%=essLeaveBean.getLeaveNo()%>" target="_blank">查看内容</a>
		    </td>
		     <td align="center" width="70"><%=StringUtil.checkNull(essLeaveBean.getRemark(), "&nbsp;")%></td> 
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
				</font>
			</td>
		    <td class="info_content_01" nowrap>
		   		<%if (essLeaveBean.getOpFlag() == 3) {%>
		    		<span style="color:red; cursor:pointer;" onClick="Delete(<%=essLeaveBean.getLeaveNo() %>);">
					<ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand" title="删除" enTitle="delete" /></span>
				<%} else out.print("&nbsp;");%>
			</td>
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
			<c:if test="${fn:length(leaveList) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(leaveList)}"
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
	<input type="hidden" name="content" value="evectionview" />
	<input type="hidden" name="leaveNo" value="" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />
</form>
</body>
</html>
