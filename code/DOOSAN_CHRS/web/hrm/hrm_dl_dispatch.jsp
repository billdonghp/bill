<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>LSFC&gt;发令处理&gt;派遣</title>
</head>
<SCRIPT type="text/javascript">

//下拉框多选
function selMuli(name,id){

	if(document.save.c.checked==true){
	
		var checks="";	
		var selIndex=document.getElementById(id).selectedIndex;
		checks=document.getElementsByName("selectedTag");
		
		if(checks.length>0){
		
			for(var i=0;i<checks.length;i++){
			
				if(checks[i].checked==true){
					
		    		var sel=document.getElementById(name+checks[i].value);
		    		
		    		if(sel.type=="select-one"){
		    		
		    			sel.options[selIndex].selected=true;
		    			
		    		}
				}
			}		  
		}
	}
}

//输入多选
function textMuli(name,id){

	if(document.save.c.checked==true){
	
		var checks="";	
		
		var dateValue=document.getElementById(id).value;
		
		checks=document.getElementsByName("selectedTag");
		
		if(checks.length>0){
		
			for(var i=0;i<checks.length;i++){
			
				if(checks[i].checked==true){
					
		    		document.getElementById(name+checks[i].value).value=dateValue;

				}
			}		  
		}
	}
}

function checkAll(){

	var selected = document.save.ck_all.value == "0" ? true : false;
  
 	for(var i=0;i<document.save.elements.length;i++){
  
		if(document.save.elements[i].type=="checkbox" && !document.save.elements[i].disabled&&document.save.elements[i].name!='c'){
    
      		document.save.elements[i].checked = selected;
      
		}
    
	}
  
	document.save.ck_all.value = selected ? "1" : "0";
}

function CheckForm(){

	var check="";
	var checks="";	
	checks=document.getElementsByName("selectedTag");
	
	if(checks.length>0){
		
		for(var i=0;i<checks.length;i++){
		
			if(checks[i].checked==true){
			    	
			    check = "checked";
			    //发令日期验证
	    		var date=document.getElementById("startDate_"+checks[i].value);
	   			
	    		if(date){
	    			if(date.value==""){
		     			 //   alert("开始日期不能为空！");
		     			alert('<ait:message  messageID="alert.mutual.start_time_empty"/>');
		     			//date.style.backgroundColor="#EFF9FF";
		     			date.focus();
						return false;
					}
	    		}
	    		
	    		var endDate=document.getElementById("endDate_"+checks[i].value);
	   			
	    		if(endDate){
	    			if(endDate.value==""){
		     			//alert("结束日期不能为空！");
		     			alert('<ait:message  messageID="alert.mutual.end_time_empty"/>');
		     			//date.style.backgroundColor="#EFF9FF";
		     			endDate.focus();
						return false;
					}
	    		}		
			}
		}		  
	
	}

	if(check==""){
		// "请选择发令人员！"
		alert('<ait:message  messageID="alert.emp.offer_regulation.post.please_select_employee" module="hrm"/>');
		return false;
	}
	/**
	
	var tcValue=document.searchForm.transCode.value;
	
	document.save.transCode.value=tcValue;
	
	//发令类型与人事令号赋值
	for(var i=0;i<document.save.elements.length;i++){
 
		if(document.save.elements[i].type=="hidden"){
    
      		var h=document.save.elements[i];
      		
      		if(h.name.indexOf("transCode_")!=-1){
      			h.value=tcValue;
      		}
		}
	}**/
	
	return true;
}

function Save(){
	
	if(CheckForm()){
		
		document.save.submit();
		
	}	
}

function Search()                                   
{                                                                                                               
var temp =document.searchForm.CONDITION.value; 
var dept=document.searchForm.DEPTID.value;          
if(temp==""&&dept=="")                  
{                                                                             
    //"请输入查询条件!"                                                             
   alert('<ait:message  messageID="alert.emp.offer_regulation.post.please_enter_search_criteria" module="hrm"/>');
   return ;
}
  document.searchForm.submit();  
}
</SCRIPT>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<c:import url="inc/hr_dl_toolbar_a.jsp"/>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<form action="hrmControlServlet" method="post" name="searchForm" onkeydown="if(event.keyCode==13) searchForm.submit();" >
		  <jodd:form fromRequest="true">
		  <input type="hidden" name="operation" value="viewDispatch">
		  <input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}"/>
		    <tr>
   				<td class="title1"><!-- 查询条件 -->
					<ait:message  messageID="display.mutual.search_criteria"/>
				</td>
 				</tr>
			<tr>
				<td>
					<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
					<tr>
						<td width="20%" class="info_title_01" nowrap><!-- 部门 -->
								<ait:message  messageID="display.mutual.dept"/>
							</td>
		          		<td width="30%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" all="all"/></td>
						<td width="20%" class="info_title_01" nowrap><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
		          		<td width="30%" class="info_content_00"><input type="text" style="width:90px " name="CONDITION"
							align="absmiddle" />
						</td>
					</tr>
					</table>
				</td>
			</tr>
		  </jodd:form>
		</form>    
		</table>
		
		<!-- display 3 level menu -->
		<c:import url="inc/hrm_dl_toolbar.jsp" />
		
		<!-- display content -->
			<br>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
	 				<td align="left" class="title1" colspan="5"><!-- 发令信息 -->
								<ait:message  messageID="display.mutual.regulation_info"/>
							</td>
					<td class="info_content_00" nowrap>
						  	 	<font color="red"><!--  最近一条人事令号--> 
						  	<ait:message messageID="display.emp.personnel_number" module="hrm"></ait:message> :${lasttransno}</font>
						  	</td>  
				</tr>
			</table>
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
		  	<form name="save" action="hrmControlServlet" method="post">
			  <input type="hidden" name="operation" value="insertDispatch"/>
			  <input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}"/>
			  <input type="hidden" name="transCode" value="TransCode6_100"/>         
			  <input type="hidden" name="ck_all" value="0" />             
			<tr>   
		  		<td>
				  	<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="dr_d">
						<tr>
							<td class="info_title_01" width="13%">
						<!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/></td>
							<td class="info_content_00" width="87%"><input type="checkbox"
								name="c" value="" class="check" /></td>
						</tr>
					</table>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td	class="info_content_01">
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>
				  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
				    <tr align="center">
				      <td class="info_title_01" height="30" ><a href="#" onClick="checkAll();">
				      <!--选择--><ait:message  messageID="display.mutual.select"/></a></td>
				      <td class="info_title_01"><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				      <td class="info_title_01"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				      <td class="info_title_01"><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				      <td class="info_title_01"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				      <td class="info_title_01"><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				      <!-- 取消机关部门，增加部门、职位、岗位职务 -->
				      <td class="info_title_01">
						<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
				      <td class="info_title_01">
						<!-- 开始日期 --><ait:message  messageID="display.mutual.start_date"/></td>
				      <td class="info_title_01">
						<!-- 结束日期 --><ait:message  messageID="display.mutual.end_date"/></td>
				      <!-- 
				      <td class="info_title_01">机关部门</td>
				       -->
				      <td class="info_title_01"><!-- 派遣内容 -->
					<ait:message  messageID="display.emp.staff_info.assignment.assignment_contents" module="hrm"/>
				</td>
				    </tr>
				    
				     <c:choose>
						    <c:when test="${transCode eq 'TransDispatch03'}">
						    	<c:forEach items="${emp_list}" var="emp" varStatus="j">
									<tr>             
										<td class="info_content_01" height="30">
											<input type="checkbox" name="selectedTag" value="${j.index}" class="check"/>
											<input type="hidden" name="transCode_${j.index}" value="TransCode6_100" id="tc"/>
											<input type="hidden" name="transNo_${j.index}" value="${emp.transNo}" id="tn"/>
											<input type="hidden" name="activity_${j.index}" value="1"/>
					 						<input type="hidden" name="empID_${j.index}" value="${emp.empID}"/>
											<input type="hidden" name="updatedBy_${j.index}" value="${admin.adminID}"/>
											<input type="hidden" name="dispNo_${j.index}" value="${emp.dispNo}"/>
											
										</td>
										<td class="info_content_01" nowrap>${emp.empID}</td>
										<td class="info_content_01" nowrap>
							<ait:content enContent='${emp.pinyinName}' zhContent='${emp.chineseName}' koContent='${emp.koreanName}'/>
							</td>
										<td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishDept}' zhContent='${emp.department}' koContent='${emp.korDept}'/>
							</td>
										<td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishPosition}' zhContent='${emp.position}' koContent='${emp.korPosition}'/>
							</td>
										<td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishPost}' zhContent='${emp.post}' koContent='${emp.korPost}'/>
							</td>
										<td class="info_content_01">${emp.transNo}</td>
										<td class="info_content_01">${emp.startDate}</td>
										<td class="info_content_01">
											<input style="content" type="text" size="10" id="endDate_${j.index}" name="endDate_${j.index}" 
										      		onClick="setday(this);setCheckboxChecked('selectedTag',${j.index});this.style.backgroundColor='';textMuli('endDate_',this.id)" 
										      		onChange="textMuli('endDate_',this.id)" 
										      		onBlur="textMuli('endDate_',this.id)"
										      		value="${emp.endDate}"
										      		/>
										</td>
										<!-- 
										<td class="info_content_01">${emp.disDept}</td>
										 -->
										<td class="info_content_01">${emp.contents}</td>
									</tr>
							    </c:forEach>
							    <c:if test="${fn:length(emp_list) < 6}">
								    <c:forEach var="i" begin="1" end="${6-fn:length(emp_list)}" step="1">
										<tr>
											<td class="info_content_01" height="30"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
										</tr>
									</c:forEach>
								</c:if> 
						    </c:when>
						    <c:otherwise>
						    	 <c:forEach items="${emp_list}" var="emp" varStatus="j">
								    <tr>
								      <td class="info_content_01" height="30">
										<input type="checkbox" name="selectedTag" value="${j.index}" class="check"/>
										<input type="hidden" name="transCode_${j.index}" value="TransCode6_100" id="tc"/>
										<input type="hidden" name="activity_${j.index}" value="0"/>
										<input type="hidden" name="empID_${j.index}" value="${emp.empID}"/>
										<input type="hidden" name="createdBy_${j.index}" value="${admin.adminID}"/>
									  </td>
								      <td class="info_content_01" nowrap>${emp.empID}</td>
									  <td class="info_content_01" nowrap>
							<ait:content enContent='${emp.pinyinName}' zhContent='${emp.chineseName}' koContent='${emp.koreanName}'/>
							</td>
									  <td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishDept}' zhContent='${emp.department}' koContent='${emp.korDept}'/>
							</td>
									  <td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishPosition}' zhContent='${emp.position}' koContent='${emp.korPosition}'/>
							</td>
									  <td class="info_content_01" nowrap>
							<ait:content enContent='${emp.englishPost}' zhContent='${emp.post}' koContent='${emp.korPost}'/>
							</td>
									  <td class="info_content_01">
											<input style="content" type="text" size="10" id="transNo_${j.index}" name="transNo_${j.index}" 
										      		onChange="javaScript:setCheckboxChecked('selectedTag',${j.index});textMuli('transNo_',this.id);" />
									  </td>
								      <td class="info_content_01">
										<input type="text" size="10" id="startDate_${j.index}" name="startDate_${j.index}" 
									      		onClick="setday(this);setCheckboxChecked('selectedTag',${j.index});this.style.backgroundColor='';textMuli('startDate_',this.id)" 
									      		onChange="textMuli('startDate_',this.id)" 
									      		onBlur="textMuli('startDate_',this.id)"/>
									  </td>
									  <td class="info_content_01">
										<input style="content" type="text" size="10" id="endDate_${j.index}" name="endDate_${j.index}" 
									      		onClick="setday(this);setCheckboxChecked('selectedTag',${j.index});this.style.backgroundColor='';textMuli('endDate_',this.id)" 
									      		onChange="textMuli('endDate_',this.id)" 
									      		onBlur="textMuli('endDate_',this.id)"/>
									  </td>
									  <!-- 
								      <td class="info_content_01">
								      	<input style="content" type="text" size="20" id="disDept_${j.index}" name="disDept_${j.index}" 
											      		onChange="setCheckboxChecked('selectedTag',${j.index});textMuli('disDept_',this.id)" />
								      </td>
								       -->
								      <td class="info_content_01">
								      	<input style="content" type="text" size="20" id="contents_${j.index}" name="contents_${j.index}" 
											      		onChange="setCheckboxChecked('selectedTag',${j.index});textMuli('contents_',this.id)" />
								      </td>
								    </tr>
								</c:forEach>    
								<c:if test="${fn:length(emp_list) < 6}">
									<c:forEach var="i" begin="1" end="${6-fn:length(emp_list)}" step="1">
										<tr>
											<td class="info_content_01" height="30"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
											<td class="info_content_01"></td>
										</tr>
									</c:forEach>
								</c:if>
						    </c:otherwise>
					    </c:choose>
				    
				  </table>
				</td>
			</tr>
		</form>
		</table> 
<ait:page       
         link="/hrmControlServlet"
         parameters="&operation=viewDispatch&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&CONDITION=${CONDITION}" 
         total="${resultCount}"
         currentpage="${currentPage}"              
         pagesize= "${pageSize}"
         beginlabel="paging_prv10"   
         endlabel="paging_next10"
         prevlabel="paging_prv"
         nextlabel="paging_next"  
         pagegroupsize="${pageGroupsize}"
         useJS="false"/>     
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="30">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>



</body>
</html>
