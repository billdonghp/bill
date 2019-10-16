<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sqlmap.util.UserConfiguration"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.ess.dao.EssDAO"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<html>
<script language="javascript" type="text/javascript">
	function Search(){
		
		document.searchForm.submit();
	}
</script>
<head>
<%@ include file="../inc/meta.jsp" %>
</head>

<body>
<% 	
	String adminId=admin.getAdminID();
	
	SimpleMap parameterObject = new SimpleMap();
	parameterObject.set("EMPID", adminId);
	HrmServices services = HrmServices.getInstance();
	List dept_list = services.retrieveDeptTree(parameterObject);
	
	request.setAttribute("dept_list", dept_list);
	
	SimpleMap sMap = new SimpleMap();
	sMap = ObjectBindUtil.getData(request);
	
	UserConfiguration userConfig = UserConfiguration.getInstance("/system.properties");
	int pageSize = userConfig.getInt("page.style1.pagesize");
	int pageGroupSize = userConfig.getInt("page.style1.pagegroupsize");
	int currentPage = 0;
	int resultCount = 0;
	
	currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
	
	List list=new ArrayList();
	EssDAO essDAO=new EssDAO();
	resultCount=NumberUtil.parseInt(essDAO.getEssPersonalInfoListCnt(sMap).toString(),0);
	
	list=essDAO.getEssPersonalInfoList(sMap,currentPage,pageSize);
	
	request.setAttribute("list", list);
	request.setAttribute("resultCount", resultCount);
	request.setAttribute("currentPage", currentPage + "");
	request.setAttribute("pageSize", pageSize + "");
	request.setAttribute("pageGroupsize", pageGroupSize + "");
	request.setAttribute("menu_code", "ess0510");
	Set s=sMap.keySet();
	Iterator iter=s.iterator();
	while(iter.hasNext()){
		String key = (String)iter.next();
		request.setAttribute(key, sMap.get(key));
	}
%>

<form action="" method="post" name="searchForm" onkeydown="if(event.keyCode==13) searchForm.submit();" >


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../ess/inc/ess_toolbar_search.jsp"%>
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
			<jodd:form fromRequest="true">
		  	<input type="hidden" name="menu_code" value="${menu_code}"/>
	        <tr>
	          <td width="15%" class="info_title_01"><!-- 工号/姓名 -->
	          <ait:message messageID="display.mutual.emp_no_name" module="ess"></ait:message>
	          </td>
	          <td width="20%" class="info_content_00"><input type="text" style="width:90px " name="CONDITION" align="absmiddle"/></td>
	          <td width="15%" class="info_title_01"><!-- 状态 -->
	           <ait:message messageID="display.mutual.status" module="ess"></ait:message>
	          </td>
	          <td width="15%" class="info_content_00"><select name="ACTIVITY">
	          	                                             <option value=""><!-- 全部 -->
	          	                                              <ait:message messageID="display.ess.approval.all" module="ess"></ait:message>
	          	                                             </option>
												        	<option value="0"><!-- 未决裁 -->
												        	 <ait:message messageID="display.ess.approval.pending" module="ess"></ait:message>
												        	</option>
												        	<option value="1"><!-- 通过 -->
												        	 <ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>
												        	</option>
												        	<option value="2"><!-- 不通过 -->
												        	 <ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>
												        	</option>
												        </select></td>
	          <td width="15%" class="info_title_01"><!--部门  -->
	           <ait:message messageID="display.mutual.department" module="ess"></ait:message>
	          </td>
	          <td width="20%" class="info_content_00"><ait:selDept name="deptID" selected="${deptID}" all="all"  supervisorType="ar"/></td>
	        </tr>
	        </jodd:form>
	        </table>
	      </td>
		</tr>
		</table>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 个人信息确认 -->
				 <ait:message messageID="display.ess.confirmation.personal_info.personal_info" module="ess"></ait:message>
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td  height="30"  class="info_title_01"><!-- 序号  -->
		     <ait:message messageID="display.mutual.no" module="ess"></ait:message>
		    </td>         
		    <td  height="30"  class="info_title_01"><!-- 申请者 -->
		     <ait:message messageID="display.ess.review.personal_info.applicant" module="ess"></ait:message>
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 申请日期 -->
		     <ait:message messageID="display.mutual.request_date" module="ess"></ait:message>
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 申请内容 -->
		     <ait:message messageID="display.ess.review.personal_info.request_narrative" module="ess"></ait:message>
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 人事确认 -->
		     <ait:message messageID="display.mutual.confirmation" module="ess"></ait:message>
		    </td>
		  </tr>
		  
		  <c:forEach items="${list}" var="oneResult" varStatus="i">
		      <tr>  
		        <td  height="30" class="info_content_01">${i.index+1}</td>
		        <td  nowrap class="info_content_01">
		        <ait:content enContent="${oneResult.chinesePinyin}"  zhContent="${oneResult.chineseName}"></ait:content>&nbsp;
		        </td>
		        <td  nowrap class="info_content_01">${oneResult.createDate}</td>
		        <td  nowrap class="info_content_01">
		        <a href="/ess/ess0510_v.jsp?menu_code=${menu_code}&empid=${oneResult.empID}"><!-- 查看内容 -->
		    <ait:message messageID="display.ess.approval.query_on_detail" module="ess"></ait:message>     
		        </a>
		        </td>
		        <td  nowrap class="info_content_01">
		        <c:choose>
		        	<c:when test="${oneResult.activity eq 0}">
		        		<a href="/ess/ess0510_t.jsp?empID=${oneResult.empID}&activity=1&menu_code=${menu_code}">
					    <font color="#00CC00"><!--通过  -->
					 <ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>    
					    </font>
					    </a>
					    <font color="#00CC00">&nbsp;/&nbsp;</font>
					    <a href="/ess/ess0510_t.jsp?empID=${oneResult.empID}&activity=2&menu_code=${menu_code}">
					    <font color="#00CC00"><!-- 不通过 -->
					 <ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>    
					    </font>
					    </a>
		        	</c:when>
		        	<c:when test="${oneResult.activity eq 1}">
		        		<font color="#CC0000">
		        		 <ait:content enContent="${oneResult.updatedByEnName}"  zhContent="${oneResult.updatedBy}"></ait:content>&nbsp;/
		        		<!--通过  --> 
		         <ait:message messageID="display.ess.approval.approved" module="ess"></ait:message>		
		        		</font>
		        	</c:when>
		        	<c:when test="${oneResult.activity eq 2}">
		        		<font color="#CC0000">
		        	 <ait:content enContent="${oneResult.updatedByEnName}"  zhContent="${oneResult.updatedBy}"></ait:content>&nbsp;/
		        		<!--不通过  -->
		        	 <ait:message messageID="display.ess.approval.rejected" module="ess"></ait:message>	
		        		 </font>
		        	</c:when>              
		        </c:choose>
		        </td>
		       </tr>
		  </c:forEach>
		 </table>
		 <table width="100%" border="0" cellspacing="0" cellpadding="10"
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
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<ait:page       
		         link="/ess0510.jsp"
		         parameters="&menu_code=ess0510&DEPTID=${DEPTID}&CONDITION=${CONDITION}&ACTIVITY=${ACTIVITY}" 
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
</form>
</body>
</html>
