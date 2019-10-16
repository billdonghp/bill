<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LOTTE&gt;合同管理&gt;合同浏览</title>
</head>
<SCRIPT type="text/javascript">
function Search()
{
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
		<c:import url="./inc/hrmsearchcontracttoolbar.jsp" /></td>
		
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
		<table width="100%" height="30" border="0" cellspacing="0"
			cellpadding="0">
			<form action="hrmControlServlet" method="post" name="searchForm"
				onkeydown="if(event.keyCode==13) searchForm.submit();"><jodd:form
				fromRequest="true">
				<input type="hidden" name="operation" value="viewContract">
				<input type="hidden" name="menu_code"
					value="<c:out value='${toolbarInfo.menu_code}'/>" />
					  
				<tr>
    				<td class="title1"><!-- 查询条件 -->
					<ait:message  messageID="display.mutual.search_criteria"/>
				</td>
  				</tr>
				<tr>
					<td>
						<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td width="15%" class="info_title_01" nowrap><!-- 部门 -->
								<ait:message  messageID="display.mutual.department"/>
							</td>
			          		<td width="20%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
							<td width="15%" class="info_title_01" nowrap><!-- 合同结束时间 -->
					<ait:message  messageID="display.emp.contract_info.contract_query.end_time_of_contract" module="hrm"/>
				</td>
			          		<td width="25%" class="info_content_00">
			          			<input type="text" name="ENDp_CONTRACT_DATE" class="content" style="width:80px " onClick="setday(this);">
			          			~
			          			<input type="text" name="ENDf_CONTRACT_DATE" class="content" style="width:80px " onClick="setday(this);">
			          		</td>
							<td width="15%" class="info_title_01" nowrap><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
			          		<td width="10%" class="info_content_00"><input name="EMPIDORCHINESENAME" type="text" style="width:90px "
								id="EMPIDORCHINESENAME" />
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
 				<td align="left" class="title1" colspan="10"><!-- 合同信息 -->
								<ait:message  messageID="display.mutual.contract_info"/>
							</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1"
			class="dr_d">
			<tr>
				<td height="30" class="info_title_01" nowrap>
				<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				<td class="info_title_01" nowrap><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				<td class="info_title_01" nowrap><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				<td class="info_title_01" nowrap><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				<!-- 增加岗位职务属性的显示   -->
				<td class="info_title_01" nowrap><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				<td class="info_title_01" nowrap>
				<!--入社日期--><ait:message  messageID="display.emp.staff_info.basic_info.hire_date" module="hrm"/></td>
				<td class="info_title_01" nowrap><!-- 合同状态 -->
				<ait:message  messageID="display.emp.contract_info.contract_query.contract_status" module="hrm"/></td>
				<td class="info_title_01" nowrap><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>
				<td class="info_title_01" nowrap><!-- 合同开始时间 -->
				<ait:message  messageID="display.emp.contract_info.contract_query.start_time_of_contract" module="hrm"/>
				</td>
				<td class="info_title_01" nowrap><!-- 合同结束时间 -->
				<ait:message  messageID="display.emp.contract_info.contract_query.end_time_of_contract" module="hrm"/>
				</td>
			</tr>
			<c:forEach items="${searchViewContractList}" var="VCtr"
				varStatus="status">
				<tr>
					<td height="30" class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.PINYIN}" zhContent="${VCtr.CHINESENAME}" koContent="${VCtr.KORNAME}"/>
					</td>
					<td class="info_content_01" nowrap>${VCtr.EMPID}</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.ENDEPT}" zhContent="${VCtr.DEPTNAME}" koContent="${VCtr.KORDEPT}"/>
					</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.ENPOSITION}" zhContent="${VCtr.POSITION}" koContent="${VCtr.KORPOSITION}"/>
					</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.ENPOST}" zhContent="${VCtr.POST}" koContent="${VCtr.KORPOST}"/>
					</td>
					<td class="info_content_01" nowrap><c:out
						value="${VCtr.JOINDATE}" /></td>
					<td class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.ENCONTRACTCONT}" zhContent="${VCtr.CONTRACTCONT}" koContent="${VCtr.KORCONTRACTCONT}"/>
					</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent="${VCtr.ENCONTRACTTYPE}" zhContent="${VCtr.CONTRACTTYPE}" koContent="${VCtr.KORCONTRACTTYPE}"/>
					</td>
					<td class="info_content_01" nowrap><c:out
						value="${VCtr.CONTRACTSTARTDATE}" /></td>
					<td class="info_content_01" nowrap><c:out
						value="${VCtr.CONTRACTENDDATE}" /></td>
				</tr>
			</c:forEach>
			<c:if test="${10>fn:length(searchViewContractList)}">  
			<c:forEach var="i" begin="1" end="${10-fn:length(searchViewContractList)}" step="1">
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
		
		
	<ait:page       
         link="/hrmControlServlet"
         parameters="&operation=viewContract&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&ENDp_CONTRACT_DATE=${ENDp_CONTRACT_DATE}&ENDf_CONTRACT_DATE=${ENDf_CONTRACT_DATE}&EMPIDORCHINESENAME=${EMPIDORCHINESENAME}" 
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
