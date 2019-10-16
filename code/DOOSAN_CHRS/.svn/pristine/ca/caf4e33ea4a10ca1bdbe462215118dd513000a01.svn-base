<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="deductList" class="java.util.ArrayList" scope="request" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/meizzDate.js"></script>
<script src="../js/prototype.js"></script>
<script language="javascript">
function monthChange(month)
{
	document.ApplyForm.operation.value = "view" ;
	document.ApplyForm.content.value = "ot_toplimitapply_batch" ;
	document.ApplyForm.submit();
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
	var otdate = document.getElementById("otDate_" + i);
	var url = "/ess/inc/ess_emp_shift.jsp?empid=" + empid.value + "&otdate=" + otdate.value;
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
			break ;
		}
	}
	if (c==0){
	    alert("请选择申请的员工!!!");
	    return ;
	}
	else
	{
		for (var i=1; i<=size; i++){
			tags = document.ApplyForm("ck_" + i);
			var otLength = document.ApplyForm("otLength_" + i).value;
			if(tags.checked == true){
				if(isNaN(otLength) || otLength == "" || otLength == "0")
				{
					alert("申请小时只能输入数字") ;
					document.ApplyForm("otLength_" + i).focus() ;
					return  ;
				}
			}
		}
	}
	
	document.ApplyForm.submit();
}
</script>
</head>
<body>

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
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->

		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->   
		<br>
		<form name="ApplyForm" method="post" action="/essControlServlet">
		    <input type="hidden" name="operation" value="add" />
			<input type="hidden" name="content" value="ottoplimitapply_batch" />
			<input type="hidden" name="menu_code" value="${param.menu_code}"/>
			<input type="hidden" name="applySize" value="${fn:length(applyList)}"/>    
			<input type="hidden" name="currentPage" value="${currentPage}"/>
		    <input type="hidden" name="ck_all" value="0" />
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" height="100%">
			<tr>
				<td align="left" class="title1" colspan="9">批量加班上限申请</td>
				<td align="left" class="info_content_01" ><!-- 月份 --><ait:message messageID="display.pay.view.annual.month" module="pay"/>&nbsp;:&nbsp;
				<select name="planMonth" style="width: 70px " onChange="monthChange(this.value);" >
					<c:forEach items="${planMonthList}" var="oneResult" varStatus="i">
						<c:choose>
				            <c:when test="${oneResult.PLAN_MONTH == planMonth}">
					         	 <option value="${oneResult.PLAN_MONTH}" selected>${oneResult.PLAN_MONTH}</option>   	                
				         	</c:when>
				            <c:otherwise>
				         	    <option value="${oneResult.PLAN_MONTH}" >${oneResult.PLAN_MONTH}</option>		
				         	</c:otherwise>
				        </c:choose>
					</c:forEach>
				</select>	
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
		    <tr align="center" >
		    <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --><ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
		    <td class="info_title_01" nowrap>序号</td>
		    <td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap="nowrap"><!--  员工 --><ait:message messageID="display.ess.attendance_request.bulk_overtime.employee"  module="ess"></ait:message></td>
		    <td class="info_title_01" nowrap>职位</td>
		    <td class="info_title_01" nowrap>加班月份</td>   
		    <td class="info_title_01" nowrap>原有上限</td>
		    <td class="info_title_01" nowrap>申请小时</td>      
		    <td class="info_title_01" nowrap="nowrap"><!-- 决裁者列表 --><ait:message messageID="display.mutual.approver"  module="ess"></ait:message></td>
		    <td class="info_title_01" nowrap="nowrap">加班事由</td>
		    </tr>
		  	<c:forEach items="${applyList}" var="oneResult" varStatus="i">
			   <tr>
			     <input type="hidden" name="empId_${i.count}" id="empId_${i.count}" value="${oneResult.empId}" />
			     <input type="hidden" name="otPlanMonth_${i.count}" id="otPlanMonth_${i.count}" value="${oneResult.otPlanMonth}"/>
			     <td height="30" class="info_content_01" nowrap="nowrap">
			     <input type="checkbox" name="ck_${i.count}" value="${oneResult.empId}" 
			     <c:if test="${oneResult.empId == oneResult.ck}"> checked </c:if>/>
			     </td>
			     <td height="30" class="info_content_01" nowrap="nowrap">${i.count}&nbsp;</td>
			     <td height="30" class="info_content_01" nowrap="nowrap">${oneResult.empId}&nbsp;</td>
		         <td height="30" class="info_content_01" nowrap="nowrap">${oneResult.chineseName}&nbsp;</td>
		         <td height="30" class="info_content_01" nowrap="nowrap">${oneResult.position}&nbsp;</td>
		         <td height="30" class="info_content_01" nowrap="nowrap">${oneResult.otPlanMonth}&nbsp;</td>                 
		         <td height="30" class="info_content_01" nowrap="nowrap">${oneResult.limit_ot}&nbsp;</td>
		         <td height="30" class="info_content_01" nowrap="nowrap"><input type="text" name="otLength_${i.count}" size="5" value="${oneResult.otLength}"/></td>
		         <c:choose>
		            <c:when test="${oneResult.affirm_emp != null}">
			         	    	<td align="center" height="30" nowrap="nowrap">${oneResult.affirm_emp}</td>                
		         	</c:when>
		         	<c:when test="${oneResult.affirm_dept != null}">
			         	    	<td align="center" height="30" nowrap="nowrap">${oneResult.affirm_dept}</td>                
		         	</c:when>
		            <c:otherwise>
		         	    		<td align="center" height="30" style="color:red;"><!-- 未置决裁者  -->
		       						<ait:message messageID="display.ess.attendance_request.absence.has_no_approver"  module="ess"></ait:message>			  	    		
		         	    		</td>
		         	</c:otherwise>
		         </c:choose>
		         <td height="30" class="info_content_01"><textarea name="otRemark_${i.count}" style=" height: 20px;width:200px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px'">${oneResult.otRemark}</textarea></td>
		       </tr>
			</c:forEach>
		  </table>
		  <ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=ot_toplimitapply_batch&menu_code=${param.menu_code}" 
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
