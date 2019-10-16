<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<title>LOTTE&gt;提示信息&gt;发令信息浏览</title>
</head>  
<SCRIPT type="text/javascript">  
function Search()
{
  document.searchForm.action="/hrmControlServlet?operation=viewupgradeinfofieldcmd&menu_code=hr0406";
  document.searchForm.submit();
}
function ImportExcel()
{                                            

  document.searchForm.action="/hrmControlServlet?operation=tipmessagereportcmd&gowhere=upgrade";
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
		<c:import url="./inc/TipMessageToolBar.jsp" /></td>
		
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
		<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">
			<form action="hrmControlServlet" method="post" name="searchForm"><jodd:form fromRequest="true">
				<input type="hidden" name="operation" value="viewupgradeinfofieldcmd"> 
				 <input type="hidden" name="menu_code" value="hr0406">                
				<tr>
    				<td class="title1"><!-- 查询条件 -->
					<ait:message  messageID="display.mutual.search_criteria"/>
				</td>
  				</tr>     
				<tr>                 
					<td>
						<table width="100%" height="30"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
						<tr>
							<td width="8%" class="info_title_01" nowrap>
						<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
			          		<td width="16%" class="info_content_00"><ait:selDept dataListName="dept_list" name="DEPTID" /></td>
							<td width="10%" class="info_title_01" nowrap>
							<!-- 时间段 --><ait:message  messageID="display.emp.statistics.time_horizon" module="hrm"/></td>
			          		<td width="20%" class="info_content_00">
			          	 		<input type="text" name="sDate" class="content" style="width:70px " onClick="setday(this);">
			            	~
			          			<input type="text" name="eDate" class="content" style="width:70px " onClick="setday(this);">
			          		</td>     
			          		<td width="8%" class="info_title_01" nowrap><!-- 员工类别 -->
								<ait:message  messageID="display.mutual.staff_class"/></td>
			          		<td width="10%" class="info_content_00">
			          		<ait:codeClass codeClass="EmpDivision" name="joinTypeCode" selected="${joinTypeCode}" all="全部"/></td>
							<td width="8%" class="info_title_01" nowrap><!-- 工号/姓名 -->
								<ait:message  messageID="display.mutual.emp_no_name"/>
							</td>
			          		<td width="10%" class="info_content_00"><input name="EMPIDORCHINESENAME" type="text" style="width:90px "
								id="EMPIDORCHINESENAME" />
							</td>  
						</tr>  
						</table>
					</td>
				</tr>
				</jodd:form>   
            </form>                 
		</table>
			<c:import url="inc/hrm_dl_toolbar.jsp" />
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
 				<td align="left" class="title1" colspan="10"><!-- 发令信息 -->
								<ait:message  messageID="display.mutual.regulation_info"/>
							</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
			<tr>
				<td height="30" class="info_title_01" nowrap>
						<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
				<td class="info_title_01" nowrap>
						<!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
				<td class="info_title_01" nowrap>
						<!-- 部门 --><ait:message  messageID="display.mutual.dept"/></td>
				<td class="info_title_01" nowrap>
				      <!-- 职位 --><ait:message  messageID="display.mutual.position"/></td>
				<td class="info_title_01" nowrap>
				      <!-- 岗位职务 --><ait:message  messageID="display.mutual.post"/></td>
				<td class="info_title_01" nowrap><!-- 人事号 --><ait:message  messageID="display.mutual.regulation_no"/></td>
				<td class="info_title_01" nowrap><!-- 人事令内容 --><ait:message  messageID="display.mutual.narrative"/></td>
			</tr>
			<c:forEach items="${listdata}" var="VCtr" varStatus="status">
				<tr>
					<td height="30" class="info_content_01" nowrap>
						<ait:content enContent='${VCtr.PINYIN}' zhContent='${VCtr.NAME}' koContent='${VCtr.KORNAME}'/>
						</td>
					<td class="info_content_01" nowrap>${VCtr.EMPID}</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent='${VCtr.ENDEPT}' zhContent='${VCtr.DEPT}' koContent='${VCtr.KORDEPT}'/>
						</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent='${VCtr.ENPOST}' zhContent='${VCtr.POST}' koContent='${VCtr.KORPOST}'/>
						</td>
					<td class="info_content_01" nowrap>
						<ait:content enContent='${VCtr.ENPOSITION}' zhContent='${VCtr.POSITION}' koContent='${VCtr.KORPOSITION}'/>
						</td>
					<td class="info_content_01" nowrap><c:out
						value="${VCtr.TRANSNO}" /></td>
					<td class="info_content_01" nowrap><c:out  
						value="${VCtr.CONTENT}" /></td>
				</tr>               
			</c:forEach>  
			<c:if test="${fn:length(listdata)<6}">
				<c:forEach var="i" begin="1" end="${6-fn:length(listdata)}"                   
					step="1">                 
				<tr>
					<td class="info_content_01" height="30"></td>
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
         parameters="&operation=viewupgradeinfofieldcmd&DEPTID=${deptid}&sDate=${sDate}&eDate=${eDate}&EMPIDORCHINESENAME=${key}&joinTypeCode=${empSort}&menu_code=${toolbarInfo.menu_code}" 
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
