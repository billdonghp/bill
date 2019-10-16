<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="deductList" class="java.util.ArrayList" scope="request" />
<html>
<head>
<!-- ess_overtime_apply_batch.jsp -->
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
		"/essControlServlet?operation=view&content=otapply_batch&otsort=normal&menu_code=${param.menu_code}&deptID=" 
	  + document.ApplyForm.deptID.value + "&key=" + encodeURIComponent(document.ApplyForm.key.value) ;
	document.ApplyForm.submit();
}

function otTypeChange(otType,cnt)
{
	var size = document.ApplyForm.applySize.value ;
	if(otType == 'WorkingOtType01')
	{
		if(document.ApplyForm.c.checked){
			for (var i=1; i<=size; i++){
				if(document.ApplyForm("ck_" + i).checked) {
					document.ApplyForm("otLength_" + i).options[0].selected = true ;
				}
			}
		}
		else{
			document.ApplyForm("otLength_" + cnt).options[0].selected = true ;
		}
		
	}
}

function checkOne(i)
{
		document.ApplyForm("ck_" + i).checked = true ;
}

function checkAll()
{
    var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
    var size = document.ApplyForm.applySize.value ;
  	for (var i=1; i<=size; i++){
		document.ApplyForm("ck_" + i).checked = selected ;
	
	}
    document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

function ViewShift(i){ 
	var empid = document.getElementById("person_id_" + i);
	var otdate = document.getElementById("otDate_" + i);
	var url = "/ess/inc/ess_emp_shift.jsp?empid=" + empid.value + "&otdate=" + otdate.value;
	window.open(url,'viewshift','width=400, height=210, top=200, left=200, status=no, scrollbars=no,resizable=no');
}

function Save(){
     	var cpny = document.ApplyForm.cpny.value ;
   
		var size = document.ApplyForm.applySize.value ;
		var tags ;
		var c = 0;
		var otType = "" ;
		var selectIndex = "" ;
		var applyLimtidtime="";
		var otDate="";
		var sysDateTime=document.ApplyForm.sysDateTime.value;;
		var otOurs =0;
		var otFromTime = "" ;
		var otToTime = "" ;
		
		for (var i=1; i<=size; i++){
			tags = document.ApplyForm("ck_" + i);
			if(tags.checked == true){
			   otType = document.ApplyForm["otTypeCode_" + i].options[document.ApplyForm["otTypeCode_" + i].selectedIndex].value ;
			   selectIndex = document.ApplyForm["otLength_" + i].selectedIndex ;
			   otOurs = document.ApplyForm["otLength_" + i].value ;
			   otFromTime = document.ApplyForm["otFromTime_" + i].value ;
			   otToTime = document.ApplyForm["otToTime_" + i].value ;
			   applyLimtidtime =document.ApplyForm["applyLimtidtime_"+i].value;
			   otDate=document.ApplyForm["otDate_"+i].value;		

			   //alert(otFromTime+otToTime)  ;
			   if(applyLimtidtime!=null && applyLimtidtime !=""){
			    //alert(sysDateTime>(otDate+" "+applyLimtidtime))
			    if(sysDateTime>(otDate+" "+applyLimtidtime)){
			     alert("已超过"+applyLimtidtime+"申请限制时间,不能进行申请!")
			     //alert("已超过限制申请时间，不能进行申请!!!");
			     document.ApplyForm["otDate_"+i].focus();
			     return;
			    }		   
			   }
			   if( otFromTime == '00:00')
			   {
			   		alert("请选择加班开始时间!!!") ;
			   		document.ApplyForm["otFromTime_" + i].focus(); 
			   		return ;
			   }
			   if( otToTime == '00:00')
			   {
			   		alert("请选择加班结束时间!!!") ;
			   		document.ApplyForm["otToTime_" + i].focus(); 
			   		return ;
			   }
			   //alert(otOurs)
			   if( otOurs > 16)
			   {
			   		alert("加班长度不能超过 16小时 !!!") ;
			   		document.ApplyForm["otLength_" + i].focus(); 
			   		return ;
			   }
			   if(otType != 'WorkingOtType01' && selectIndex == 0)
			   {
			   		alert("非平日加班，必须选择加班长度!!!") ;
			   		document.ApplyForm["otLength_" + i].focus(); 
			   		return ;
			   }
			   if(otType != 'WorkingOtType01' && document.ApplyForm["otNightFlag_"+i].value == 1)
			   {
			   	   	alert("非平日加班,下夜班标识必须为否!!!");
			   	   	return ;
			   }
			   
			   tags = document.ApplyForm("otRemark_" + i) ;
			   if(tags == null || tags.value.length <= 0)
			   {
			   		alert("请填写工作内容!!!") ;
			   		document.ApplyForm["otRemark_" + i].focus(); 
			   		return ;
			   }
	
			   c++;
			}
		}
		if (c == 0){
		    alert("请选择申请的员工!!!");
		    return ;
		}
		if(cpny == '63000000'){
		    if (confirm('是否确认提交') ){
		       document.ApplyForm.submit();
		    }
		}else{
			document.ApplyForm.submit();
		}
		
		document.getElementById("trname").style.display="none";//避免重复提交，隐藏按钮
		    
	
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
	          <td class="info_title_01" ><!-- 部门 -->
	        <ait:message messageID="display.mutual.dept" ></ait:message>   
	          </td>
	          <td class="info_content_00"  > <ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
	          <td class="info_title_01"  ><!-- 职号/姓名 -->
	         <ait:message messageID="display.mutual.emp_no_name" module="ess" ></ait:message> 
	          </td>
	          <td class="info_content_00"  >
	          <input type="text" name="key" value="${key}" /></td>
	          <td class="info_content_00"  >
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
			<input type="hidden" name="otsort" value="normal" />
			<input type="hidden" name="content" value="otapply_batch" />
			<input type="hidden" name="menu_code" value="${param.menu_code}"/>
			<input type="hidden" name="applySize" value="${fn:length(applyList)}"/>    
			<input type="hidden" name="currentPage" value="${currentPage}"/>
		    <input type="hidden" name="ck_all" value="0" />
		    <input type="hidden" name="sysDateTime" value="${sysDateTime}">
		     <input type="hidden" name="cpny" value="<%=admin.getCpnyId()%>">
		  <table width="100%" border="0" cellpadding="0" cellspacing="1" height="100%">
			<tr>
				<td align="left" class="title1" colspan="9"><!-- 批量加班申请 -->
		<ait:message messageID="display.ess.attendance_request.bulk_overtime.bulk_overtime_request"  module="ess"></ait:message>			
				</td>
				<td class="info_content_09" align="left"><!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/>
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
		    <tr align="center" >
		    <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       全<br>选</a></td>
		    <td class="info_title_01"  nowrap>序<br>号</td>
		    <td class="info_title_01" nowrap>职号</td>
		    <td class="info_title_01" nowrap>姓名	      
		      </td>
			   <td class="info_title_01" nowrap><!-- 部门 --> <ait:message
					messageID="display.mutual.department"></ait:message></td>    
		    <td class="info_title_01" nowrap>上<br>限</td>
		    <td class="info_title_01" nowrap>已</br>加</td> 
		     <td class="info_title_01" nowrap>下夜班</br>标识</td>    
		      <td class="info_title_01" nowrap="nowrap"><!--加班日期  -->
	<ait:message messageID="display.ess.attendance_request.bulk_overtime.ot_time"  module="ess"></ait:message>	   	      
		      </td>
		      <td class="info_title_01" nowrap="nowrap"><!-- 开始时间 -->开始<br>时间	   	      
		      </td>
		      <td class="info_title_01" nowrap="nowrap"><!--结束时间  -->结束<br>时间
		      </td>
		      <td class="info_title_01" nowrap="nowrap"><!-- 跨天数  -->
			跨<br>天数
		     </td>
	<!--  <td height="30" class="info_title_01" nowrap="nowrap">扣除时间
		 <ait:message messageID="display.ess.attendance_request.ot_request.deduction"  module="ess"></ait:message>
		     </td>
	-->	    
		     <td height="30" class="info_title_01" nowrap="nowrap"><!-- 加班长度 -->
		     加班长度</br>
		     (非平日可用)
		     </td>
		      <td class="info_title_01" nowrap="nowrap"><!-- 加班类型 -->
	<ait:message messageID="display.ess.attendance_request.ot_request.overtime_type"  module="ess"></ait:message>	   	      
		      </td>
	<!--      
		      <td class="info_title_01" nowrap="nowrap">强制选择
	<ait:message messageID="display.ess.attendance_request.ot_request.enforce_to_select"  module="ess"></ait:message>	   	      
		      </td>
	-->	  
		      <td class="info_title_01" nowrap="nowrap"><!-- 决裁者列表 -->
	<ait:message messageID="display.mutual.approver"  module="ess"></ait:message>	   	      
		      </td>
		      <td class="info_title_01" nowrap="nowrap"><!--  工作内容 -->
	<ait:message messageID="display.mutual.work_narrative"  module="ess"></ait:message><font color="red">(必添)</font>
				</td>
		    </tr>		    
		  	<c:forEach items="${applyList}" var="oneResult" varStatus="i">
			   <tr>
			     <input type="hidden" name="empId_${i.count}" id="empId_${i.count}" value="${oneResult.empId}" />
			     <input type="hidden" name="person_id_${i.count}" id="person_id_${i.count}" value="${oneResult.person_id}" />
			     <input type="hidden" name="applyLimtidtime_${i.count}" id="applyLimtidtime_${i.count}" value="${oneResult.applyLimtidtime}" />
			     <td height="30" class="info_content_09" nowrap="nowrap">
			     <input type="checkbox" name="ck_${i.count}" value="${oneResult.person_id}" 
			     <c:if test="${oneResult.person_id == oneResult.ck}"> checked </c:if>/>
			     </td>
			     <td height="30" class="info_content_09" nowrap="nowrap">${i.count}</td>
			     <td height="30" class="info_content_09" id="leftnewstd">
			     <span class=ellipsis_row title='${oneResult.empId}'>${oneResult.empId}</span>
			     </td>     
		         <td height="30" class="info_content_09" id="leftnewstd">
		         <span class=ellipsis_row title='${oneResult.chineseName}'>${oneResult.chineseName}</span>
		         </td>
		         <td height="30" class="info_content_09" id="leftnewstd">
		         <c:if test="${oneResult.deptName eq oneResult.fourthDeptName}">
		         	<span class=ellipsis_row2 title='${oneResult.deptName}'>${oneResult.deptName}</span>
		         </c:if>
		         <c:if test="${oneResult.deptName ne oneResult.fourthDeptName}">
		         	<span class=ellipsis_row2 title='${oneResult.fourthDeptName} ${oneResult.deptName}'>${oneResult.fourthDeptName}<br>${oneResult.deptName}</span>
		         </c:if>
		         </td>
		         <td height="30" class="info_content_09" nowrap="nowrap">
		         <c:if test="${oneResult.limit_ot==-1}">无</c:if>
		         <c:if test="${oneResult.limit_ot!=-1}">${oneResult.limit_ot}</c:if>		         
		         </td>
		         <td height="30" class="info_content_09" nowrap="nowrap">
		         <% 
		         	if (admin.getCpnyId().equals("60000000") || admin.getCpnyId().equals("78000000")){
		         %>
		         	${oneResult.appply_ot}/${oneResult.otTotal}
		         <% 
		         	}else{
		         %>
		         	${oneResult.otTotal}
		         <% 	
		         	}
		         %>
		         
		         </td>
		         <td class="info_content_09" nowrap="nowrap">
		         	<select name="otNightFlag_${i.count}" onChange="selMuli('otNightFlag_',${i.count});checkOne(${i.count}); "> 
						<option value="0" <c:if test="${oneResult.otNightFlag == 0 }">selected</c:if> >否</option>
						<option value="1" <c:if test="${oneResult.otNightFlag == 1 }">selected</c:if> >是</option>
					</select>
		         </td>
		         <td class="info_content_09" nowrap="nowrap">
		         	<input type="text" name="otDate_${i.count}" class="content" readonly style="width:60px;font-size:10.5 " value="${oneResult.otDate}" onClick="setDayMuli(this,${i.count},'otDate_');">
		         	&nbsp;<span onClick="ViewShift(${i.count});" style="cursor:pointer;" ><ait:message messageID="display.ess.attendance_request.ot_request.shift"  module="ess"></ait:message><!-- 班次  --> </span>	
		         </td>
		         <td class="info_content_09" nowrap="nowrap"><ait:time style="essOt" name="otFromTime_${i.count}" spacing="30" selected="${oneResult.otFromTime}" onChange="selMuli('otFromTime_',${i.count});checkOne(${i.count}); " /></td>
		         <td class="info_content_09" nowrap="nowrap"><ait:time style="essOt" name="otToTime_${i.count}" spacing="30" selected="${oneResult.otToTime}" onChange="selMuli('otToTime_',${i.count});checkOne(${i.count}); " /></td>
		         <td class="info_content_09" ><select class="essOt"  name="otNextDays_${i.count}" onChange="selMuli('otNextDays_',${i.count});checkOne(${i.count}); "> 
		         												<option value="0" <c:if test="${oneResult.otNextDays == 0 }">selected</c:if> >当日</option>
		         												<option value="1" <c:if test="${oneResult.otNextDays == 1 }">selected</c:if> >次日</option>
		         											</select></td>  
		         <td class="info_content_09" nowrap="nowrap">
		           <select name="otLength_${i.count}" style="width: 50px " onChange="otTypeChange(document.ApplyForm.otTypeCode_${i.count}.options[document.ApplyForm.otTypeCode_${i.count}.selectedIndex].value,${i.count}); selMuli('otLength_',${i.count});checkOne(${i.count});" >
		           	<c:forEach items="${deductList}" var="oneDeduct">
		           		 <c:choose>
				         	<c:when test="${oneResult.otLength == oneDeduct}">
				         		<option value="${oneDeduct}" selected>${oneDeduct}</option>
				         	</c:when>
				         	<c:otherwise>
				         		<option value="${oneDeduct}" >${oneDeduct}</option>
				         	</c:otherwise>
				         </c:choose>
		           	</c:forEach>
		           </select>
		         </td>
		         <td class="info_content_09" >
		         <% 
		         	if (admin.getCpnyId().equals("63000000") || admin.getCpnyId().equals("78000000") || admin.getCpnyId().equals("59000000")){
		         %>
		         	<c:choose>
				         	<c:when test="${oneResult.wages == 'C_20020_2'}">
				         		<ait:codeClass style="essOt" name="otTypeCode_${i.count}" codeClass="OTTypeCode"  selected="${oneResult.otTypeCode}" onChange="otTypeChange(this.options[this.selectedIndex].value,${i.count}) ;selMuli('otTypeCode_',${i.count});checkOne(${i.count});" />
				         	</c:when>
				         	<c:otherwise>
				         		<select id="otTypeCode_${i.count}" name="otTypeCode_${i.count}" onChange="otTypeChange(this.options[this.selectedIndex].value,${i.count}) ;selMuli('otTypeCode_',${i.count});checkOne(${i.count});"  class="essOt" > 
									<option value="WorkingOtType03">法定休假日加班</option>
									<option value="WorkingOtType02">休息日加班</option>
									<option value="WorkingOtType01">平日加班</option>
								</select>
				         	</c:otherwise>
					 </c:choose>
		         <% 
		         	}else{
		         %>
		         	<ait:codeClass style="essOt" name="otTypeCode_${i.count}" codeClass="OTTypeCode"  selected="${oneResult.otTypeCode}" onChange="otTypeChange(this.options[this.selectedIndex].value,${i.count}) ;selMuli('otTypeCode_',${i.count});checkOne(${i.count});" />
		         <% 	
		         	}
		         %>
			         		         
		         </td>
		         <c:choose>
		            <c:when test="${oneResult.affirmData != null}">
			         	    	<td align="center" height="30" id="leftnewstd">
			         	    	<span class=ellipsis_row2 title='${oneResult.affirmData}'>${oneResult.affirmData}</span>
			         	    	</td>                
		         	</c:when>
		            <c:otherwise>
		         	    		<td align="center" height="30" style="color:red;" id="leftnewstd"><!-- 未置决裁者  -->
		         	    			<span class=ellipsis_row2 title='未置决裁者'>未置决裁者</span>
		         	    		</td>
		         	</c:otherwise>
		         </c:choose>
		         <td class="info_content_09" nowrap="nowrap"><textarea name="otRemark_${i.count}" style=" height: 20px;width:100px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';textMuli('otRemark_',${i.count})">${oneResult.otRemark}</textarea></td>
				 <input name="otSortCode_${i.count}" type="hidden" value=""/>
				 <input name="createdBy_${i.count}" type="hidden" value="${adminId}"/>
				 
		       </tr>
			</c:forEach>
		  </table>
		  <ait:page       
		               link="/essControlServlet"
		               parameters="&operation=view&content=otapply_batch&otsort=normal&menu_code=${param.menu_code}&deptID=${deptID}&key=${key}"
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
