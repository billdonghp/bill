<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ait.sqlmap.util.ObjectBindUtil"%>
<%@ page import="com.ait.sqlmap.util.SimpleMap"%>
<%@ page import="com.ait.sqlmap.util.UserConfiguration"%>
<%@ page import="com.ait.util.NumberUtil"%>
<%@ page import="com.ait.hrm.business.*"%>
<%@ page import="com.ait.ess.dao.EssDAO"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="com.ait.util.StringUtil"%>
<html>
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
	sMap.setString("CONDITION",adminId);
	
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
	request.setAttribute("menu_code", "ess0601");
	Set s=sMap.keySet();
	Iterator iter=s.iterator();
	while(iter.hasNext()){
		String key = (String)iter.next();
		request.setAttribute(key, sMap.get(key));
	}
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/hrtoolbar_null.jsp"%>
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

		<!-- display 3 level menu -->
		<%@ include file="/inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--个人信息申请  -->
				  <ait:message  messageID="display.ess.review.personal_info.personal_info_request"  module="ess"/>	
				</td>
			</tr>
		</table>
		<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		  <tr align="center">
		    <td  height="30"  class="info_title_01"><!-- 序号 -->
		      <ait:message  messageID="display.mutual.no"  module="ess"/>	
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 申请者 -->
		      <ait:message  messageID="display.ess.review.personal_info.applicant"  module="ess"/>	
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 申请日期 -->
		      <ait:message  messageID="display.mutual.request_date"  module="ess"/>	
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 申请内容 -->
		      <ait:message  messageID="display.ess.review.personal_info.request_narrative"  module="ess"/>	
		    </td>
		    <td  height="30"  class="info_title_01"><!-- 决裁情况 -->
		      <ait:message  messageID="display.mutual.status_2"  module="ess"/>	
		    </td>
		    <td height="30"  class="info_title_01"><!--  删除-->
		      <ait:message  messageID="display.mutual.delete"  module="ess"/>	
		    </td>
		  </tr>
		  
		  <c:forEach items="${list}" var="oneResult" varStatus="i">
		      <tr>
		        <td  height="30" align="center">${i.index+1}</td>
		        <td  nowrap align="center">
		        
		        <ait:content enContent="${oneResult.chinesePinyin}" zhContent="${oneResult.chineseName}"></ait:content>
		        </td>
		        <td  nowrap align="center">${oneResult.createDate}</td>
		        <td  nowrap align="center">
		        <a href="/ess/ess0510_v.jsp?menu_code=${menu_code}&empid=${oneResult.empID}"><!--查看内容  -->
		        <ait:message  messageID="display.ess.approval.query_on_detail"  module="ess"/>	
		        </a>
		        </td>
		        <td  nowrap align="center">
		        <c:choose>                
		        	<c:when test="${oneResult.activity eq 0}">  
		        		<font color="#00CC00"><!-- 未决裁 -->
		        		  <ait:message  messageID="display.ess.approval.pending"  module="ess"/>	
		        		</font>
		        	</c:when>
		        	<c:when test="${oneResult.activity eq 1}">
		        		<font color="#CC0000"> <!-- 通过 -->
		        		  <ait:message  messageID="display.ess.approval.approved"  module="ess"/>	
		        		</font>
		        	</c:when>
		        	<c:when test="${oneResult.activity eq 2}">
		        		<font color="#CC0000"> <!-- 不通过 -->
		        	<ait:message  messageID="display.ess.approval.rejected"  module="ess"/>	
		        		</font>
		        	</c:when>
		        </c:choose>
		        </td>
		        <td  height="30"  align="center">
		        	<c:if test="${oneResult.activity eq 0}">
		        	<a href="/ess/ess0600_t.jsp?menu_code=${menu_code}&NO=${oneResult.empID}&operate=personalInfo">
		        	<ait:image src="/images/btn/Delete_little.gif"  border="0" align="absmiddle" style="cursor:hand" title="删除" enTitle="delete" />
		        	</a>
		        	</c:if>
		        	&nbsp;
		        </td>
		       </tr>
		  </c:forEach>
		 </table>
		 <table width="100%" border="0" cellspacing="0" cellpadding="10"
			<c:if test="${fn:length(list) < 10}">
				<c:forEach var="i" begin="1" end="${10-fn:length(list)}"
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