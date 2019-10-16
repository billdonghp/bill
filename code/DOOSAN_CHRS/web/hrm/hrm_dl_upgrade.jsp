<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>LOTTE&gt;发令处理&gt;岗位/岗位职务</title>
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
	    			//"发令生效日期不能为空！"
		     			alert('<ait:message  messageID="alert.emp.offer_regulation.post.effective_date_cannot_be_empty" module="hrm"/>');
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
	//"人事令号不能为空！"
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
{ //"请输入查询条件!"                                                             
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
		<c:import url="inc/hr_dl_toolbar_a.jsp" /></td>
		
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
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<form action="hrmControlServlet" method="post" name="searchForm"><jodd:form
				fromRequest="true">
				<input type="hidden" name="operation" value="viewUpgrade">
				<input type="hidden" name="menu_code"
					value="${toolbarInfo.menu_code}" />  
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
			</jodd:form></form>
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
		<table width="100%"  border="0" cellpadding="0"
			cellspacing="0" >
			<form name="save" action="hrmControlServlet" method="post"><input
				type="hidden" name="operation" value="insertUpgrade" /> <input
				type="hidden" name="menu_code" value="${toolbarInfo.menu_code}" /> <input
				type="hidden" name="ck_all" value="0" />
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30" width="10%" nowrap><!-- 发令类型 -->
								<ait:message  messageID="display.mutual.order_type"/>
							</td>
						<td class="info_content_00" width="15%"><ait:codeClass
							style="content" codeClass="TransCode1_120" name="transCode"/></td>
						<td class="info_title_01" width="10%" nowrap>
						<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
						<td class="info_content_00" width="50%"><input name="transNo"
							type="text"
							onchange="if(this.value!='')this.style.backgroundColor=''" /></td>
						<td class="info_title_01" width="13%" nowrap>
						<!-- 下拉框联动 --><ait:message  messageID="display.mutual.toggle_all"/></td>
						<td class="info_content_01" width="2%"><input type="checkbox"
							name="c" value="" class="check" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="info_content_01"></td>
			</tr>
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
					<tr>
						<td class="info_title_01" height="30" nowrap><a href="#"
							onClick="checkAll();" title="all">
							<!--选择--><ait:message  messageID="display.mutual.select"/></a></td>
						<td class="info_title_01" nowrap>
						<!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
						<td class="info_title_01" nowrap>
						<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
						<td class="info_title_01" nowrap>
						<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
						<td class="info_title_01" nowrap>
						<!-- 职级 --><ait:message  messageID="display.mutual.post_grade"/></td>
						<td class="info_title_01" nowrap>
						<!-- 级号 --><ait:message  messageID="display.mutual.pay_grade"/></td>
						<td class="info_title_01" nowrap>
						<!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
						<!-- 删除岗位职务等级、岗位职务系数的输入 -->
						<!-- 
			      <td class="info_title_01" nowrap>岗位职务等级</td>
			      <td class="info_title_01" nowrap>岗位职务系数</td>
			       -->
						<!-- 修改职位为职位 -->
						<td class="info_title_01" nowrap>
						<!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
						<!-- 修改工作状态为所属公司 -->
						<!--			      <td class="info_title_01" nowrap>所属公司</td>-->
						<td class="info_title_01" nowrap onclick="">
						<!--生效日期--><ait:message  messageID="display.mutual.effective_dates"/></td>
						<td class="info_title_01" nowrap onclick=""><!--内容--><ait:message  messageID="display.mutual.narrative"/></td>
					</tr>
					<c:forEach items="${emp_list}" var="emp" varStatus="j">
						<tr id="">
							<td class="info_content_01" height="30"><input
								type="checkbox" name="selectedTag" value="${j.index}"
								class="check" /> <input type="hidden"
								name="transCode_${j.index}" value="" id="tc" /> <input
								type="hidden" name="transNo_${j.index}" value="" id="tn" /> <input
								type="hidden" name="activity_${j.index}" value="0" /> <input
								type="hidden" name="empID_${j.index}" value="${emp.empID}" /> <input
								type="hidden" name="createdBy_${j.index}"  
								value="${admin.adminID}" /></td>
							<td class="info_content_01" nowrap>${emp.empID}</td>
							<td class="info_content_01" nowrap>
							<ait:content enContent='${emp.pinyinName}' zhContent='${emp.chineseName}' koContent='${emp.koreanName}'/>
							</td>
							<td class="info_content_01">
							<ait:select style="content"
								dataListName="dept_list" name="deptID_${j.index}"
								selected="${emp.deptID}" 
								onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('deptID_',this.id);" />
							<%-- 
							<select name="deptID_${j.index}" onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('deptID_',this.id);" >
							<c:forEach items="${dept_list}" var="temp">           
							<c:if test="${temp.deptID==emp.deptID}">
						      <option value="${temp.deptID}" selected="selected" >
						       <c:forEach begin="0" end="${temp.deptLevel}" step="1" >        
							    &nbsp;&nbsp;  
				              </c:forEach>  
						   <ait:content enContent='${temp.deptEnName}' zhContent='${temp.deptName}' koContent='${temp.korDept}'/>      
							      </option>        
							 </c:if>    
							 <c:if test="${temp.deptID!=emp.deptID}">     
							  <option value="${temp.deptID}" >
							   <c:forEach begin="0" end="${temp.deptLevel}" step="1" >
							    &nbsp;&nbsp;   
				              </c:forEach>  
						<ait:content enContent='${temp.deptEnName}' zhContent='${temp.deptName}' koContent='${temp.korDept}'/>      
							</option>
							</c:if>
							</c:forEach>	
							</select>
							--%>
                           </td>
							<td class="info_content_01"><ait:select style="content"
								dataListName="post_grade_list" name="postGradeCode_${j.index}"
								selected="${emp.postGradeCode}" 
								onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('postGradeCode_',this.id);" />
							</td>     
							
							<td class="info_content_01"><ait:select  style="content"
								dataListName="post_grade_level_list" name="postCoef_${j.index}"
								selected="${emp.postCoef}"
								onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('postCoef_',this.id);" all="all"/>
							</td>  
<!--							-->
							<td class="info_content_01"><ait:select style="content"
								dataListName="post_list" name="postCode_${j.index}"
								selected="${emp.postCode}"   
								onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('postCode_',this.id);" /></td>
							<!-- 去除岗位职务等级、岗位职务系数 -->
							<%-- 
			      <td class="info_content_01">
			      	<ait:codeClass style="content" codeClass="PGCode" name="postGradeCode_${j.index}" selected="${emp.postGradeCode}" onChange="setCheckboxChecked('selectedTag',${j.index})"/>
			      </td>
			      <td class="info_content_01">
			      	<input id="postCoef" type="text" class="content" name="postCoef_${j.index}" value="${emp.postCoef}" onChange="setCheckboxChecked('selectedTag',${j.index})"/>
			      </td>
			       --%>
							<td class="info_content_01"><ait:codeClass style="content"
								codeClass="PositionCode" name="positionCode_${j.index}"    
								selected="${emp.positionCode}"
								onChange="setCheckboxChecked('selectedTag',${j.index});selMuli('positionCode_',this.id);" /></td>
							<!--			      <td class="info_content_01">-->
							<%--			      	<ait:codeClass style="content" codeClass="WorkingCode" name="statusCode_${j.index}" selected="${emp.statusCode}" onChange="setCheckboxChecked('selectedTag',${j.index})"/>--%>
							<!--			      </td>-->
							<td class="info_content_01"><input type="text" size="10"
								id="startDate_${j.index}" name="startDate_${j.index}"          
								onClick="setday(this);setCheckboxChecked('selectedTag',${j.index});textMuli('startDate_',this.id)"
								/></td>
							<td class="info_content_01"><input style="content"
								type="text" size="20" codeClass="WorkingCode"   
								name="reasons_${j.index}" maxlength="200"/></td>                        
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
				</table>
				</td>
			</tr>
		</form>
		</table>
<ait:page       
         link="/hrmControlServlet"
         parameters="&operation=viewUpgrade&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&CONDITION=${CONDITION}" 
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
