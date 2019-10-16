<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LSFC&gt;合同管理&gt;到期合同</title>
</head>
<SCRIPT type="text/javascript">

function AddContract(){
  var str="";
for(var i=0;i<document.save.elements.length;i++)
{
  if(save.elements[i].type=="checkbox")
  {
    if(save.elements[i].checked==true)
    {
      str = "checked";
    }  
  }
}
document.save.str.value=str;
if(str!="checked")
{
  alert("请选择续签合同");
  return;
}
	document.save.submit();
}

function checkAll()
{
  for(var i=0;i<document.save.elements.length;i++)
  {
    if(save.elements[i].type=="checkbox")
    {
      save.elements[i].checked = !save.elements[i].checked;
    }
  }
}

function ImportExcel(){
	var contractType = document.searchcontract.ContractTypeCode.value;
	var deptId = document.searchcontract.DEPTID.value;
	var EMPIDORCHINESENAME = document.searchcontract.EMPIDORCHINESENAME.value;
	var endDateP = document.searchcontract.ENDp_CONTRACT_DATE.value;
	var endDateF = document.searchcontract.ENDf_CONTRACT_DATE.value;
	
    url = "/hrmControlServlet?operation=makeExpiredContractExcel&DEPTID="+deptId+"&ContractTypeCode="+contractType+"&EMPIDORCHINESENAME="+EMPIDORCHINESENAME+"&ENDp_CONTRACT_DATE="+endDateP+"&ENDf_CONTRACT_DATE="+endDateF;
    location.href=url;
    //window.open(url,'','');
}

function Search()
{
  document.searchcontract.submit();
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
		<c:import url="inc/hr_contract_toolbar_a.jsp" />
		
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
		<table width="100%" height="30" border="0" cellspacing="0"
			cellpadding="0">
			<form name="searchcontract" action="hrmControlServlet" method="post"
				onkeydown="if(event.keyCode==13) searchcontract.submit();">
			<jodd:form fromRequest="true">
				<input type="hidden" name="operation" value="viewExpiredContract" />
				<input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}" />
				<tr>
    				<td class="title1"><!-- 查询条件 -->
					<ait:message  messageID="display.mutual.search_criteria"/>
				</td>
  				</tr>
  				<tr>  
					<td>
						<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td width="9%" class="info_title_01" nowrap="nowrap"="nowrap="nowrap""><!-- 部门 -->
								<ait:message  messageID="display.mutual.dept"/>
							</td>
			          		<td width="15%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
			          		<td width="9%" class="info_title_01" nowrap="nowrap"><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>            
			          		<td  class="info_content_00" nowrap="nowrap"><ait:codeClass codeClass="ContractTypeCode" name="ContractTypeCode" all="all" disabled="false" /></td>
							<td  class="info_title_01" nowrap="nowrap"><!-- 合同结束时间 -->
					<ait:message  messageID="display.emp.contract_info.contract_query.end_time_of_contract" module="hrm"/>
				</td>
			          		<td width="22%" class="info_content_00">     
			          			<input type="text" name="ENDp_CONTRACT_DATE" class="content" style="width:80px " onClick="setday(this);">
			          			~
			          			<input type="text" name="ENDf_CONTRACT_DATE" class="content" style="width:80px " onClick="setday(this);">
			          		</td>
							<td width="10%" class="info_title_01" nowrap="nowrap"><!-- 工号/姓名 -->
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
		
		<br>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
 				<td align="left" class="title1" colspan="10"><!-- 合同信息 -->
								<ait:message  messageID="display.mutual.contract_info"/>
							</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="5">
			<form name="save" action="hrmControlServlet" method="post">
			<input type="hidden" name="operation" value="insertExpiredContract" /> 
			<input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}" />
			<tr valign="top">
				<td height="164">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td class="info_title_01" height="30" nowrap="nowrap"><a href="#"
							onClick="checkAll();"><!-- 全选 --><ait:message  messageID="display.mutual.select_2"/></a></td>
						<td width="13%" nowrap="nowrap" class="info_title_01">
						<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
						<td width="8%" nowrap="nowrap" class="info_title_01">
						<!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
						<!-- 增加部门、岗位职务、职位 -->
						<td width="8%" nowrap="nowrap" class="info_title_01">
						<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
						<td width="8%" nowrap="nowrap" class="info_title_01"><!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
						<td width="8%" nowrap="nowrap" class="info_title_01"><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
						<td width="14%" nowrap="nowrap" class="info_title_01"><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>
						<td width="28%" nowrap="nowrap" class="info_title_01"><!-- 合同期间 --><ait:message  messageID="display.mutual.contract_duration"/></td>
						<td width="15%" nowrap="nowrap" class="info_title_01"><!-- 新合同开始日期 -->
					<ait:message  messageID="display.emp.contract_info.expired_contract.start_date_of_new_contract" module="hrm"/>
				</td>
						<td width="15%" nowrap="nowrap" class="info_title_01"><!-- 新合同结束日期 -->
					<ait:message  messageID="display.emp.contract_info.expired_contract.end_date_of_new_contract" module="hrm"/>
				</td>
					</tr>
					<c:forEach items="${searchExpiredContract}" var="VECtr"
						varStatus="s">
						<tr>
							<td class="info_content_02" scope="row" height="30"><input
								type="checkbox" name="isChecked" value="${s.index}"
								class="check" /></td>

							<td class="info_content_01" nowrap="nowrap"> 
								<ait:content enContent="${VECtr.PINYIN}" zhContent="${VECtr.CHINESENAME}" koContent="${VECtr.KORNAME}"/>
							<input type="hidden" name="empID_${s.index}" value="${VECtr.EMPID}" />
							<input type="hidden" name="contractNo_${s.index}"
								value="${VECtr.CONTRACTNO}" /> <input type="hidden"
								name="contractTypeCode_${s.index}"
								value="${VECtr.CONTRACTTYPECODE}" /> 
							<input type="hidden"
								name="contractContCode_${s.index}" value="ContractStatus03" /></td>
							<td class="info_content_01" nowrap="nowrap">${VECtr.EMPID}</td>
							<td class="info_content_01" nowrap="nowrap">
								<ait:content enContent="${VECtr.ENDEPT}" zhContent="${VECtr.DEPTNAME}" koContent="${VECtr.KORDEPT}"/>
							</td>
							<td class="info_content_01" nowrap="nowrap">
								<ait:content enContent="${VECtr.ENPOSITION}" zhContent="${VECtr.POSITION}" koContent="${VECtr.KORPOSITION}"/>
							</td>
							<td class="info_content_01" nowrap="nowrap">
								<ait:content enContent="${VECtr.ENPOST}" zhContent="${VECtr.POST}" koContent="${VECtr.KORPOST}"/>
							</td>

							<td class="info_content_01" nowrap="nowrap">
								<ait:content enContent="${VECtr.ENCONTRACTTYPE}" zhContent="${VECtr.CONTRACTTYPE}" koContent="${VECtr.KORCONTRACTTYPE}"/>
							</td>
							<td class="info_content_01" nowrap="nowrap">${VECtr.CONTRACTSTARTDATE}--${VECtr.CONTRACTENDDATE}</td>
							<td class="info_content_01" nowrap="nowrap"><input type="text"
								name="contractStartDate_${s.index}" class="content" readonly
								style="width:90px " value="${VECtr.CONTRACTNEWBEGINDATE}"
								onClick="setday(this);" />
							<td class="info_content_01" nowrap="nowrap"><input type="text"
								name="contractEndDate_${s.index}" class="content" readonly
								style="width:90px "
								onClick="setday(this);setCheckboxChecked('isChecked',${s.index});" /></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(searchExpiredContract) <7}">
						<c:forEach var="i" begin="1" end="${7-fn:length(searchExpiredContract)}" step="1">
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
			<input type="hidden" name="str" value="0" />
	 </form>
		</table>  
        <ait:page       
         link="/hrmControlServlet"  
         parameters="&operation=viewExpiredContract&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&ENDp_CONTRACT_DATE=${ENDp_CONTRACT_DATE}&ENDf_CONTRACT_DATE=${ENDf_CONTRACT_DATE}&EMPIDORCHINESENAME=${EMPIDORCHINESENAME}&ContractTypeCode=${ContractTypeCode}" 
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

