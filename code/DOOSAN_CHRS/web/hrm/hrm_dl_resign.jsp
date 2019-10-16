<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>LSFC&gt;发令处理&gt;离职</title>
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
	    		var date=document.getElementById("resignDate_"+checks[i].value);
	   		
	    		if(date){
	    			if(date.value==""){
		     			// alert("离职日期不能为空！");
		     			alert('<ait:message  messageID="alert.emp.offer_regulation.dimission.dimission_date_cannot_be_empty" module="hrm"/>');
		     			//date.style.backgroundColor="#EFF9FF";
		     			date.focus();
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
	
	
	
	var tcValue=document.save.transCode.value;
	var tnValue=document.save.transNo.value;
	
	if(tnValue==""){
	// 	alert("人事令号不能为空！");
		alert('<ait:message  messageID="alert.emp.offer_regulation.post.regulation_number_cannot_be_empty" module="hrm"/>');
		//document.save.transNo.style.backgroundColor="#EFF9FF";
		document.save.transNo.focus();
		return false;
	}
	
	//发令类型与人事令号赋值
	for(var i=0;i<document.save.elements.length;i++){
 
		if(document.save.elements[i].type=="hidden"){
    
      		var h=document.save.elements[i];
      		
      		if(h.name.indexOf("transCode_")!=-1){
      			h.value=tcValue;
      		}
      		
      		if(h.name.indexOf("transNo_")!=-1){
      			h.value=tnValue;
      		}
		}
	}
	
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
		<form action="hrmControlServlet" method="post" name="searchForm" onkeydown="if(event.keyCode==13) searchForm.submit();">
		  <jodd:form fromRequest="true">
		  <input type="hidden" name="operation" value="viewResign">
		  <input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}"/>
		  <input type="hidden" name="resign" value="HR_RESIGN" />
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
 				<td align="left" class="title1" colspan="10"><!-- 发令信息 -->
								<ait:message  messageID="display.mutual.regulation_info"/>
							</td>
			</tr>
		</table>
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0">
			  <form name="save" action="hrmControlServlet" method="post">
			  <input type="hidden" name="operation" value="insertResign"/>
			  <input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}"/>
			  <input type="hidden" name="ck_all" value="0" />
		  	<tr>    
		  		<td>
			  		 <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
					  	<tr>
						  	<td class="info_title_01" height="30" width="10%"><!-- 发令类型 -->
								<ait:message  messageID="display.mutual.order_type"/>
							</td>
						  	<td class="info_content_00" width="15%">
								<ait:codeClass style="content" codeClass="TransResign" name="transCode"/>
							</td>
						  	<td class="info_title_01" width="10%" nowrap="nowrap">
						<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
						  	<td class="info_content_00" width="20%">
						  		<input name="transNo" type="text" onchange="if(this.value!='')this.style.backgroundColor=''"/>
						  	</td>
						  	<td class="info_content_00" width="30%">
						   	<font color="red"><!--  最近一条人事令号--> 
						  	<ait:message messageID="display.emp.personnel_number" module="hrm"></ait:message> :${lasttransno}</font>
						  	</td>
						  	<td class="info_title_01" width="13%">
						<!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/></td>
						  	<td class="info_content_00" width="2%">
						  		<input type="checkbox" name="c" value="" class="check"/>
						  	</td>
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
				      <td class="info_title_01">
						<!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				      <td class="info_title_01">
						<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				       <!-- 取消离职类型、离职原因和转职公司，增加部门、职位、岗位职务 -->
				       <td class="info_title_01">
						<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				       <td class="info_title_01"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				       <td class="info_title_01"><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				       
				      <td class="info_title_01"><!-- 离职日期 --><ait:message  messageID="display.mutual.leaving_date"/></td>
				      <!-- 
				      <td class="info_title_01">离职类型</td>
				      <td class="info_title_01">离职原因</td>
				      <td class="info_title_01">转职公司</td>
				       -->
				      <td class="info_title_01"><!-- 离职事由 --><ait:message  messageID="display.mutual.dimission_reason"/></td>
				    </tr>
				    <c:forEach items="${emp_list}" var="emp" varStatus="j">
					    <tr>
					       <td class="info_content_02" height="30">
								<input type="checkbox" name="selectedTag" value="${j.index}" class="check"/>
								<input type="hidden" name="transCode_${j.index}" value="" id="tc"/>
								<input type="hidden" name="transNo_${j.index}" value="" id="tn"/>
								<input type="hidden" name="activity_${j.index}" value="0"/>
								<input type="hidden" name="empID_${j.index}" value="${emp.empID}"/>
								<input type="hidden" name="createdBy_${j.index}" value="${admin.adminID}"/>
								<input type="hidden" name="resignTypeCode_${j.index}" value="ResignTypeCode10"/>
								<input type="hidden" name="resignReasonCode_${j.index}" value="resignReasonCode"/>
								<input type="hidden" name="newCompany_${j.index}" value="newCompany"/>
						  </td>         
					      <td class="info_content_02" nowrap>${emp.empID}</td>
						  <td class="info_content_02" nowrap>
							<ait:content enContent='${emp.pinyinName}' zhContent='${emp.chineseName}' koContent='${emp.koreanName}'/>
							</td>
						  <td class="info_content_02" nowrap>
							<ait:content enContent='${emp.englishDept}' zhContent='${emp.department}' koContent='${emp.korDept}'/>
							</td>
						  <td class="info_content_02" nowrap>
							<ait:content enContent='${emp.englishPosition}' zhContent='${emp.position}' koContent='${emp.korPosition}'/>
							</td>
						  <td class="info_content_02" nowrap>
							<ait:content enContent='${emp.englishPost}' zhContent='${emp.post}' koContent='${emp.korPost}'/>
							</td>
						  <td class="info_content_02">
							<input style="content" type="text" size="10" id="resignDate_${j.index}" name="resignDate_${j.index}" 
						      		onClick="setday(this);setCheckboxChecked('selectedTag',${j.index});textMuli('resignDate_',this.id)" 
						      		onChange="textMuli('resignDate_',this.id)" 
						      		onBlur="textMuli('resignDate_',this.id)"/>
						  </td>    
						  <!-- 取消离职类型、离职原因和转职公司 -->
						  <!-- 
					      <td class="info_content_02">
					      	<ait:codeClass style="content" codeClass="ResignTypeCode" name="resignTypeCode_${j.index}" onChange="setCheckboxChecked('selectedTag',${j.index})"/>
					      </td>
					      <td class="info_content_02">
					      	<ait:codeClass style="content" codeClass="ResignReasonCode" name="resignReasonCode_${j.index}" onChange="setCheckboxChecked('selectedTag',${j.index})"/>
					      </td>
					      <td class="info_content_02">
					      	<input style="content" type="text" size="20" id="newCompany_${j.index}" name="newCompany_${j.index}" 
								      		onChange="setCheckboxChecked('selectedTag',${j.index});" />
					      </td>
					       -->
					      <td class="info_content_02">
					      	<input style="content" type="text" size="20" id="resignDesc_${j.index}" name="resignDesc_${j.index}" 
								      		onChange="setCheckboxChecked('selectedTag',${j.index});;textMuli('resignDesc_',this.id)" maxlength="200"/>
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
								</tr>
							</c:forEach>
					  </c:if>
				  </table>
				 </td>
				</tr>
			</form>
		</table>
 <ait:page       
         link="/hrmControlServlet"
         parameters="&operation=viewResign&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&CONDITION=${CONDITION}" 
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
