<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>

<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="all_Visiter_Type" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="totalPeopleTypeNum" class="java.lang.String" scope="request" />
<jsp:useBean id="totalPeopleNum" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script language="javascript1.5" type="text/javascript">
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='0';
	document.ApplyForm.submit();
}
</script> 

<form name="ApplyForm" action="/puControlServlet">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_search.jsp"%>
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
	      <tr>
	          <td width="10%" class="info_title_01"><!--  开始日期-->
	          	开始日期
	          </td>
	          <td width="15%" class="info_content_00">
	          <input type="text" name="StartDate" value="" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td width="10%" class="info_title_01"><!--结束日期  -->
	             结束日期
	          </td>
	          <td width="15%" class="info_content_00">
	          <input type="text" name="EndDate" value="" onClick="setday(this);" readonly="readonly">
	          </td>
	        </tr>
	      
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
				<td align="left" class="title1" colspan="10"><!-- 加班申请 -->
					参观者级别统计
				</td>
			</tr>
		</table>
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	  
	  <tr>
  		<td class="info_title_01" nowrap>
	    	类别
	    </td>
	  <c:forEach items="${all_Visiter_Type}" var="allType" varStatus="i">
	   
	    <td class="info_title_01" nowrap>
	      	${allType.CODE_NAME}
	    </td>
	    </c:forEach>
   
    	<td class="info_title_01" nowrap>
	    	合计
	    </td>
	  </tr>
		  <tr align="center">
			    <td align="center" style="color: #666666;">
			    	人数
			    </td>
			    <c:forEach items="${all_Visiter_Type}" var="allType" varStatus="j">
			    	<c:forEach items="${allType.all_Visiter_Type_Num}" var="allNum" varStatus="k">
					    <td align="center" style="color: #666666;">
					    	&nbsp;${allNum.NUM}
					    </td>
			    	</c:forEach> 
			    </c:forEach>
			    <td align="center" style="color: #666666;">
			    	&nbsp;${totalPeopleTypeNum}
			    </td>
		  </tr>
		  
		  <tr align="center">
			    <td align="center" style="color: #666666;">
			    	来访次数
			    </td>
			   
		    	<c:forEach items="${all_Visiter_Type}" var="allType" varStatus="n">
		    		<c:forEach items="${allType.all_Visiter_Type_VisiterNum}" var="allNum" varStatus="m">
					    <td align="center" style="color: #666666;">
					    	&nbsp;${allNum.NUM}
					    </td>
			    	</c:forEach> 
		    	</c:forEach>
		    	
			    <td align="center" style="color: #666666;">
			    &nbsp;${totalPeopleNum}
			    </td>
		  </tr>
		  
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
<input type="hidden" name="operation" value="visiterManagement"/>
<input type="hidden" name="content" value="visiterTypeSearch"/>
<input type="hidden" name="flag" value="2"/>
<input type="hidden" name="menu_code" value="<%=menu_code%>" />
<input type="hidden" name="currentPage" value="${currentPage}" />
</form>
</body>
</html>
