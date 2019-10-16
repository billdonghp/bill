<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LSFC&gt;合同管理&gt;添加合同</title>
<SCRIPT type="text/javascript">
             
function AddContract(){
var str="";
var select=new Array();
var j=0;
var msg=new Array('<ait:message messageID="alert.emp.contract_info.contract_query.set_start" module="hrm"></ait:message>',
                   '<ait:message messageID="alert.emp.contract_info.contract_query.search_first" module="hrm"></ait:message>');        
for (var k = 0 ;k < document.save.isChecked.length ; k++)
{
	if (document.save.isChecked[k].checked)
	{
	  select[j]=document.save.isChecked[k].value;j=j+1;    
	}
}       
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
if(str != "checked")
{
  alert(msg[1]);
  return;
}          
if(select.length>0)
{
	for(var i=0;i<select.length;i++)  
	{
	  var num=select[i];          
	  if(document.getElementById("contractStartDate_"+num).value=="")       
	 {
	      alert(msg[0]);                      
	      return false;
	      }              
	 }      
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
function Search()
{
  document.searchEmpWithoutContract.submit();
}

function checknull(size)
{
  for(var i=0;i<size;i++)
  {
    return
  }
}

</SCRIPT>
</head>

<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="images/tablbk01_r1_c1.gif"></td>
		<td background="images/tablbk01_r1_c2.gif">
		
		<!-- display toolbar -->
		<c:import url="inc/hr_contract_toolbar_a.jsp" /></td>
		
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<font color="red"><div align="center">${message}</div></font>
		<br>
		<table width="100%" height="30" border="0" cellspacing="0"
			cellpadding="0">
			<form name="searchEmpWithoutContract" action="hrmControlServlet"
				method="post"
				onkeydown="if(event.keyCode==13) searchEmpWithoutContract.submit();">
			<jodd:form fromRequest="true">
				<input type="hidden" name="operation" value="viewContractByInsert" />
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
							<td width="20%" class="info_title_01" nowrap="nowrap"><!-- 部门 -->
								<ait:message  messageID="display.mutual.department"/>
							</td>
			          		<td width="30%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
							<td width="20%" class="info_title_01" nowrap="nowrap"><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
			          		<td width="30%" class="info_content_00"><input name="EMPIDORCHINESENAME" type="text" style="width:90px "
								id="EMPIDORCHINESENAME" />
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<!-- 
				<tr>
					<td valign="middle" class="search_title_01"><ait:selDept
						dataListName="dept_list" name="deptID" /> 姓名/编号：<input
						type="text" name="EMPIDORCHINESENAME" size="12" /> <img
						src="/images/btn/search.gif" align="absmiddle"
						onclick="searchEmpWithoutContract.submit();" style="cursor:hand;">
					</td>
				</tr> -->
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
			class="dr_d" >
		<form name="save" action="hrmControlServlet" method="post">
		<input type="hidden" name="operation" value="insertContract" />
			<tr>
				<td class="info_title_01" height="30" width="5%" nowrap="nowrap"><a
					href="#" onClick="checkAll();"><!-- 全选 --><ait:message  messageID="display.mutual.select_2"/></a></td>
				<td class="info_title_01" width="7%" nowrap="nowrap"><!-- 员工号 --><ait:message  messageID="display.mutual.emp_id_2"/></td>
				<td class="info_title_01" width="6%" nowrap="nowrap"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				<!-- 增加部门、职位、岗位职务的显示 -->
				<td class="info_title_01" width="10%" nowrap="nowrap"><!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				<td class="info_title_01" width="9%" nowrap="nowrap"><!-- 职位 --><ait:message  messageID="display.mutual.position"/> </td>
				<td class="info_title_01" width="12%" nowrap="nowrap"><!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				<td class="info_title_01" width="13%" nowrap="nowrap"><!-- 合同类型 -->
				<ait:message  messageID="display.mutual.contract_type"/>
				</td>
				<td class="info_title_01" width="10%" nowrap="nowrap"><!-- 开始日期 -->
				<ait:message  messageID="display.mutual.start_date"/></td>
				<td class="info_title_01" width="10%" nowrap="nowrap"><!-- 结束日期 -->
				<ait:message  messageID="display.mutual.end_date"/></td>
				<td class="info_title_01" width="10%" nowrap="nowrap"><!-- 再签与否 -->
				<ait:message  messageID="display.emp.offer_regulation.contract_info.extend_or_not" module="hrm"/></td>
				<td class="info_title_01" width="18%" nowrap="nowrap"><!-- 备注 -->
				<ait:message  messageID="display.mutual.notes"/></td>
			</tr>
			<c:forEach items="${searchEmpWithoutContract}" var="VEWCtr"
				varStatus="s">
				<tr>
					<td class="info_content_02" height="30" nowrap="nowrap"><input
						type="checkbox" name="isChecked" value="${s.index}" class="check" /></td>
					<td class="info_content_01" nowrap="nowrap">${VEWCtr.EMPID}</td>
					<td class="info_content_01" nowrap="nowrap">
						<ait:content enContent="${VEWCtr.CHINESE_PINYIN}" zhContent="${VEWCtr.CHINESENAME}" koContent="${VEWCtr.KOREANNAME}"/>
					</td>
					<td class="info_content_01" nowrap="nowrap">
						<ait:content enContent="${VEWCtr.ENDEPT}" zhContent="${VEWCtr.DEPARTMENT}" koContent="${VEWCtr.KORDEPT}"/>
					</td>    
					<td class="info_content_01" nowrap="nowrap">
						<ait:content enContent="${VEWCtr.ENGLISHPOSITION}" zhContent="${VEWCtr.POSITION}" koContent="${VEWCtr.KORPOSITION}"/>
					</td>
					<td class="info_content_01" nowrap="nowrap">
						<ait:content enContent="${VEWCtr.ENGLISHPOST}" zhContent="${VEWCtr.POST}" koContent="${VEWCtr.KORPOST}"/>
					</td>
					<td class="info_content_02" nowrap="nowrap"><ait:codeClass                
						codeClass="ContractTypeCode" name="contractTypeCode_${s.index}"
						style="content"
						onChange="setCheckboxChecked('isChecked',${s.index})" /> </td>  
					<td class="info_content_01" nowrap="nowrap"><input 
						name="contractStartDate_${s.index}" type="text" id="contractStartDate_${s.index}"
						style="width:80px "                
						onClick="setday(this);setCheckboxChecked('isChecked',${s.index});" /></td>
					<td class="info_content_01" nowrap="nowrap"><input
						name="contractEndDate_${s.index}" type="text" style="width:80px "
						onClick="setday(this);setCheckboxChecked('isChecked',${s.index});" /></td>
					<td class="info_content_01"><ait:codeClass
					    codeClass="ContractStatusCode" name="contractContCode_${s.index}"  all="all" /></td>  
					<td class="info_content_01" nowrap="nowrap"><input type="text"
						name="remark_${s.index}"                
						onChange="setCheckboxChecked('isChecked',${s.index})" /></td>
				</tr>
				<input type="hidden"    
						name="empID_${s.index}" value="${VEWCtr.EMPID}" class="content" />  
			</c:forEach>
			<c:if test="${fn:length(searchEmpWithoutContract) <10}">
				<c:forEach var="i" begin="1" end="${10-fn:length(searchEmpWithoutContract)}" step="1">
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
		<input type="hidden" name="menu_code" value="${toolbarInfo.menu_code}" />
		<input type="hidden" name="str" value="0" />
		</form>
		</table>
		
  <ait:page       
         link="/hrmControlServlet"
         parameters="&operation=viewContractByInsert&menu_code=${toolbarInfo.menu_code}&DEPTID=${DEPTID}&EMPIDORCHINESENAME=${EMPIDORCHINESENAME}" 
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
