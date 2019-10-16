<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>LSFC&gt;发令处理&gt;派遣查看</title>
</head>
<SCRIPT type="text/javascript">
function checkAll(){

	var selected = document.save.ck_all.value == "0" ? true : false;
  
 	for(var i=0;i<document.save.elements.length;i++)
 	{
		if(document.save.elements[i].type=="checkbox" && !document.save.elements[i].disabled&&document.save.elements[i].name!='c')
		{
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
			}
		}		  
	
	}

	if(check==""){
		//	alert("请选择人员！");                                                            
	    alert('<ait:message  messageID="alert.emp.regulation_query.post.please_select_employees" module="hrm"/>');
	    return false;
	}
	
	return true;
}

function Save(){
	
	if(CheckForm()){
		//"确认删除!"
		if(confirm('<ait:message  messageID="alert.emp.regulation_query.post.be_sure_to_delete." module="hrm"/>')){
			document.save.submit();
		}
		
	}	
}
function Search()
{
  document.searchForm.submit();
}
</SCRIPT>
<body >

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<c:import url="inc/hr_dl_toolbar_d.jsp" />
			
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
		<input type="hidden" name="operation" value="searchDispatch">
		<input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}"/>
		  	<tr>
				<td class="title1"><!-- 查询条件 -->
					<ait:message  messageID="display.mutual.search_criteria"/>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" height="30" border="0" cellpadding="0"
						cellspacing="1" class="dr_d">
						<tr>
							<td width="10%" class="info_title_01" nowrap><!-- 部门 -->
								<ait:message  messageID="display.mutual.dept"/>
							</td>
							<td width="25%" class="info_content_00"><ait:selDept
								dataListName="dept_list" name="DEPTID" /></td>
							<td width="13%" class="info_title_01" nowrap><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
							<td width="25%" class="info_content_00"><input type="text"
								style="width:90px " name="CONDITION" align="absmiddle" /></td>  
<!--							<td width="10%" class="info_title_01" nowrap>发令类型</td>-->
<!--							<td width="15%" class="info_content_00"><ait:codeClass style="content" codeClass="TransDispatch" name="TRANSCODE" all="all" filling="align='absmiddle'"/></td>-->
							<td width="13%" class="info_title_01" nowrap>
						<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
							<td width="20%" class="info_content_00"><input type="text"
								name="TRANSNO" style="width:90px " /></td>
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 发令信息 -->
								<ait:message  messageID="display.mutual.regulation_info"/>
							</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d" style="word-break:break-all">
		  <form name="save" action="hrmControlServlet" method="post">
			  <input type="hidden" name="operation" value="delDispatch"/>
			  <input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}" />
			  <input type="hidden" name="ck_all" value="0" />
		  <tr>
		  	<td class="info_title_01" height="30"  nowrap><a href="#" onClick="checkAll();" title="全选">
				      <!--选择--><ait:message  messageID="display.mutual.select"/></a></td>
		    <td class="info_title_01" height="30" nowrap >
						<!-- 人事令号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
		    <!-- 去除人事令类型、机关部门和活跃，增加部门、职位、岗位职务 -->
		     
<!--		    <td class="info_title_01" nowrap>人事令种类</td>-->
		     
		    <td class="info_title_01" nowrap><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
		    <td class="info_title_01" nowrap><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
		    <td class="info_title_01" nowrap><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
		    <td class="info_title_01" nowrap><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
		    <td class="info_title_01" nowrap><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
		   	<td class="info_title_01">
						<!-- 开始日期 --><ait:message  messageID="display.mutual.start_date"/></td>
		    <td class="info_title_01">
						<!-- 结束日期 --><ait:message  messageID="display.mutual.end_date"/></td>
		    <!-- 
		    <td class="info_title_01">机关部门</td>
		     -->
		    <td class="info_title_01" nowrap="nowrap"><!-- 派遣内容 -->
					<ait:message  messageID="display.emp.staff_info.assignment.assignment_contents" module="hrm"/>
				</td>
				  <td class="info_title_01" nowrap>Excel</td>
		    <!-- 
		    <td class="info_title_01" nowrap>活跃</td>
		     -->
		  </tr>
		  <c:forEach items="${list}" var="oneResult" varStatus="i">
		      <tr>
		        <td class="info_content_01" >
		        	<c:if test="${oneResult.activity eq 0}">
		        	<input type="checkbox" name="selectedTag" value="${i.index}" class="check"/>
		        	</c:if>
		        	<input type="hidden" name="dispNo_${i.index}" value="${oneResult.dispNo}"/>
		        	<input type="hidden" name="activity_${i.index}" value="${oneResult.activity}"/>
		        </td>
		        <td class="info_content_01" >${oneResult.transNo}</td>
		         
<!--		        <td class="info_content_01" nowrap>${oneResult.transCodeName}</td>-->
		         
		        <td class="info_content_01" nowrap>${oneResult.empID}</td>
		        <td class="info_content_01" nowrap>
							<ait:content enContent='${oneResult.pinyin}' zhContent='${oneResult.chineseName}' koContent='${oneResult.korName}'/>
							</td>
		        <td class="info_content_01" nowrap>
							<ait:content enContent='${oneResult.enDept}' zhContent='${oneResult.department}' koContent='${oneResult.korDept}'/>
							</td>
		        <td class="info_content_01" nowrap>
							<ait:content enContent='${oneResult.enPosition}' zhContent='${oneResult.position}' koContent='${oneResult.korPosition}'/>
							</td>
		        <td class="info_content_01" nowrap>
							<ait:content enContent='${oneResult.enPost}' zhContent='${oneResult.post}' koContent='${oneResult.korPost}'/>
							</td>
		        <td class="info_content_01" nowrap>${oneResult.startDate}</td>
		        <td class="info_content_01" nowrap>${oneResult.endDate}</td>
		        <!-- 
		        <td class="info_content_01" nowrap>${oneResult.disDept}</td>
		         -->  
		        <td class="info_content_01" nowrap="nowrap">${oneResult.contents}</td>
		        <td class="info_content_01" nowrap="nowrap">
		    	<a href="/hrmControlServlet?operation=exportInfo&type=dispatch&empid=${oneResult.empID}" target="_blank"><!-- 查看内容 -->
		    	  excel
		    	</a>                 
		     </td>    
		        <!-- 
		        <td class="info_content_01" nowrap>
		        	<img src="../images/a_${oneResult.activity}.gif" width="22" height="25">
		        </td>
		         -->
		       </tr>
		  </c:forEach>
		  <c:if test="${fn:length(list) < 6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(list)}"
					step="1">
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
						<td class="info_content_01"></td>
					</tr>
				</c:forEach>
			</c:if>
		  </form>
		</table>
		<ait:page       
		         link="/hrmControlServlet"
		         parameters="&operation=searchDispatch&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&CONDITION=${CONDITION}&TRANSCODE=${TRANSCODE}&TRANSNO=${TRANSNO}&ACTIVITY=${ACTIVITY}" 
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
