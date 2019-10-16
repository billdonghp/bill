<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essLeaveBean" class="com.ait.ess.bean.EssLeaveBean" scope="request" />
<%@ include file="../inc/taglibs.jsp"%>

<html>
<%@ include file="../inc/taglibs.jsp"%>
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
<script language="javascript" src="../js/essMuli.js"></script>
<script language="javascript">

function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.action = 
		"/essControlServlet?operation=view&content=leaveapply_batch&menu_code=${param.menu_code}&deptID=" 
	  + document.ApplyForm.deptID.value + "&key=" + encodeURIComponent(document.ApplyForm.key.value) ;
	document.ApplyForm.submit();
}

function checkOne(i)
{
		document.ApplyForm("ck_" + i).checked = true ;
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var size = document.ApplyForm.applySize.value ;
    var tags ;
  	for (var i=1; i<=size; i++){
		tags = document.ApplyForm("ck_" + i);
		tags.checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function ViewShift(i){
	var empid = document.getElementById("empId_" + i);
	var leaveToDate = document.getElementById("leaveFromDate_" + i);
	var url = "/ess/inc/ess_emp_shift.jsp?empid=" + empid.value + "&otdate=" + leaveToDate.value;
	window.open(url,'viewshift','width=400, height=210, top=200, left=200, status=no, scrollbars=no,resizable=no');
}

function Save(){
	
	var size = document.ApplyForm.applySize.value ;
	var tags ;
	var c = 0;
	for (var i=1; i<=size; i++){
		tags = document.ApplyForm("ck_" + i);
		if(tags.checked == true){
			c++;
			
			tags = document.ApplyForm("leaveReason_" + i) ;
		    if(tags == null || tags.value.length <= 0)
		    {
		   		alert("请填写休假原因!!!") ;
		   		document.ApplyForm["leaveReason_" + i].focus(); 
		   		return ;
		    }
		}
		
	}
	if (c==0){
	    alert("请选择申请的员工!!!");
	    return ;
	}
	document.ApplyForm.submit();
	document.getElementById("trname").style.display="none";//避免重复提交，隐藏按钮
}
</script>
</head>
<body >


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/esstoolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<form name="ApplyForm" method="post" action="/essControlServlet">
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	        <tr>
	          <td class="info_title_01" nowrap="nowrap"><!-- 部门 -->
	        <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td class="info_content_00"  nowrap="nowrap"><ait:selDept name="deptID" selected="${deptID}" all="all" supervisorType="ar"/></td>
	          <td class="info_title_01"  nowrap="nowrap"><!-- 职号/姓名 -->
	         <ait:message messageID="display.mutual.emp_no_name" module="ess" ></ait:message> 
	          </td>
	          <td class="info_content_00"  nowrap="nowrap">
	          <input type="text" name="key" value="${key}" /></td>
	          <td class="info_content_00"  nowrap="nowrap">
	          	<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />
	          </td>
	        </tr></table>
	      </td>
		</tr>
		</table>

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		
			<input type="hidden" name="operation" value="add" />
			<input type="hidden" name="menu_code" value="${param.menu_code}"/>
			<input type="hidden" name="applySize" value="${fn:length(applyList)}"/>    
			<input type="hidden" name="currentPage" value="${currentPage}"/>
			<input type="hidden" name="content" value="leaveapply_batch" />
			<input type="hidden" name="menu_code" value="${param.menu_code}"/>  
			<input type="hidden" name="ck_all" value="0" />
			<table width="100%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">批量勤态申请					
					</td>
					<td class="info_content_09" align="center"><!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/>
						<input type="checkbox" name="c" value="c" class="check" <c:if test="${c == 'c'}"> checked </c:if> />
				</td>
				</tr>
			</table>
			<c:if test="${fn:length(errorMsgList) > 0 }">
			  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">	
				<c:forEach items="${errorMsgList}" var="errorMsg" varStatus="i">
					<tr align="center"><td align="center"><font color="red">${errorMsg}</font></td></tr>
				</c:forEach>
			  </table>
			</c:if>
		  <table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    <tr>
		    <td class="info_title_01" nowrap ><a href="#" onclick="checkAll();" style="color:red;" ><!-- 全选 --> 
		       全<br>选</a></td>
		    <td class="info_title_01" nowrap>序<br>号</td>
		    <td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap>
		      姓名	      
		      </td>
			   <td class="info_title_01" nowrap><!-- 部门 --> <ait:message
					messageID="display.mutual.department"></ait:message></td>
		      <td  class="info_title_01" nowrap="nowrap"><!--  休假开始日期 -->
    <ait:message messageID="display.ess.attendance_request.bulk_time_off.start_date"  module="ess"></ait:message>					      
		     </td>
		      <td  class="info_title_01" nowrap="nowrap"><!-- 休假结束日期 -->
	<ait:message messageID="display.ess.attendance_request.bulk_time_off.end_date"  module="ess"></ait:message>				      
		      </td>
		      <td  class="info_title_01" nowrap="nowrap">勤态类型				      
		      </td>
		      <td nowrap="nowrap" class="info_title_01">
				出厂地点 </td>
			
		       <td  class="info_title_01" nowrap="nowrap"><!-- 计假方式 -->
	                  计假<br>方式				      
		      </td>
            <td  class="info_title_01" nowrap="nowrap"><!--	总年假  -->
	                      总<br>年假			      
		      </td>
		      <td  class="info_title_01" nowrap="nowrap"><!--剩余年假  -->
	剩余<br>年假				      
		      </td>
		      <%--<td nowrap="nowrap" class="info_title_01" nowrap="nowrap"><!--  决裁者列表 -->
	<ait:message messageID="display.mutual.approver"  module="ess"></ait:message>				      
		     </td>
		      --%>
		      <td nowrap="nowrap" class="info_title_01" nowrap="nowrap"><!-- 休假原因  -->
	<ait:message messageID="display.ess.review.time_off.reason"  module="ess"></ait:message><font color="red">(必添)</font>				      
		      </td>
		    </tr>
		  	<c:forEach items="${applyList}" var="oneResult" varStatus="i">		  
			   <tr>
			     <input type="hidden" name="empId_${i.count}" id="empId_${i.count}" value="${oneResult.empId}" />
			     <input type="hidden" name="person_id_${i.count}" id="person_id_${i.count}" value="${oneResult.person_id}" />
			     <input type="hidden" name="postgradecode_${i.count}" id="postgradecode_${i.count}" value="${oneResult.postGradeCode}" />
			     <td height="30" class="info_content_09" nowrap="nowrap">
			     <input type="checkbox" name="ck_${i.count}" value="${oneResult.person_id}" 
			     <c:if test="${oneResult.person_id == oneResult.ck}"> checked </c:if>/>
			     </td>
			     <td height="30" class="info_content_09" nowrap="nowrap">${i.count}</td>
			     <td height="30" class="info_content_09" id="leftnewstd">
			     <span class=ellipsis_row title='${oneResult.empId}'>${oneResult.empId}</span>
			     </td>  
		         <td height="30" class="info_content_09" nowrap="nowrap">${oneResult.chineseName}</td> 
		         <input type="hidden" name="chinesname_${i.count}" id="chinesname_${i.count}" value="${oneResult.chineseName}" />  
		         <td height="30" class="info_content_09" id="leftnewstd">
		         <c:if test="${oneResult.deptName eq oneResult.fourthDeptName}">
		         	<span class=ellipsis_row2 title='${oneResult.deptName}'>${oneResult.deptName}</span>
		         </c:if>
		         <c:if test="${oneResult.deptName ne oneResult.fourthDeptName}">
		         	<span class=ellipsis_row2 title='${oneResult.fourthDeptName} ${oneResult.deptName}'>${oneResult.fourthDeptName}<br>${oneResult.deptName}</span>
		         </c:if>
		         </td>
		      
		         <td class="info_content_09"  nowrap="nowrap"><input type="text" name="leaveFromDate_${i.count}" class="content" readonly style="width:90px " value="${oneResult.leaveFromDate}" onClick="setDayMuli(this,${i.count},'leaveFromDate_');">&nbsp;
		         <ait:time name="leaveFromTime_${i.count}" spacing="30" selected="${oneResult.leaveFromTime}"  onChange="selMuli('leaveFromTime_',${i.count});checkOne(${i.count}); "/>
		         <span onClick="ViewShift(${i.count});" style="cursor:pointer;">
		         <ait:message messageID="display.ess.attendance_request.ot_request.shift"  module="ess"></ait:message>
		       <!-- 班次  --> </span>
		       </td>
		         <td class="info_content_09" nowrap="nowrap"><input type="text" name="leaveToDate_${i.count}" class="content" readonly style="width:90px " value="${oneResult.leaveToDate}" onClick="setDayMuli(this,${i.count},'leaveToDate_');">
		              <ait:time name="leaveToTime_${i.count}" spacing="30" selected="${oneResult.leaveToTime}"  onChange="selMuli('leaveToTime_',${i.count});checkOne(${i.count}); "/>                                                  
		         </td>  
		        
		         <td class="info_content_09" nowrap="nowrap"><ait:codeClass name="leaveTypeCode_${i.count}" codeClass="LeaveTypeCode" selected="${oneResult.leaveTypeCode}"  onChange="selMuli('leaveTypeCode_',${i.count});checkOne(${i.count}); "/></td>  
		          <td nowrap="nowrap" class="info_content_00" align="left">
			<ait:codeClass codeClass="EMP_DIFF" name="cpnyId_${i.count}" selected="${cpnyId}" /></td>
		          <td class="info_content_09" nowrap="nowrap">
		           <ait:codeClass name="ApplyLeaveType_${i.count}" onChange="" codeClass="ApplyLeaveType" selected="${oneResult.applyLeaveType}" />  
		           </td>     
		       <c:forEach items="${oneResult.vactionList}" var="vaction" varStatus="j">
		        <td class="info_content_09" nowrap="nowrap">${vaction.allTotVaction}&nbsp; </td>	
		          <td class="info_content_09" nowrap="nowrap">${vaction.lastTotVaction}&nbsp; </td>    
		       
		       </c:forEach>
		       <%--<c:choose>
		         	<c:when test="${oneResult.AFFIRMDATA != null}">
			         	    	<td align="center" height="30" nowrap="nowrap">${oneResult.AFFIRMDATA}</td>                
		         	</c:when>
		            <c:otherwise>
		         	    		<td align="center" height="30" style="color:red;" nowrap="nowrap"><!-- 未置决裁者  -->
		       						<ait:message messageID="display.ess.attendance_request.absence.has_no_approver"  module="ess"></ait:message>			  	    		
		         	    		</td>
		         	</c:otherwise>
		         </c:choose>
		         --%><td class="info_content_09" nowrap="nowrap"><textarea name="leaveReason_${i.count}" style=" height: 25px;width:150px " type="_moz" onfocus="this.style.height='40px'" onblur="this.style.height='25px';textMuli('leaveReason_',${i.count})">${oneResult.leaveReason}</textarea></td>
		       </tr>
			</c:forEach>
		  </table>
		  <ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=leaveapply_batch&otsort=normal&menu_code=${param.menu_code}&deptID=${deptID}&key=${key}" 
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
	 
	
</body>
</html>
