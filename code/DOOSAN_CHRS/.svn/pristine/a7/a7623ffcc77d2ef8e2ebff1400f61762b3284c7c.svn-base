<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>会议室决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

</SCRIPT>

<body>
<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_none.jsp"%>
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
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				会议室决裁
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				预订部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				预订日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
		      <td nowrap="nowrap" class="info_title_01">
				使用人数</td>
		      <td nowrap="nowrap" class="info_title_01">
				使用会议室 </td>	    
			  <td width="10%" align="center" class="info_title_01">
				会议主题</td>
			  <td nowrap="nowrap" class="info_title_01">
				决裁</td>				
		      
		    </tr>
		 <c:forEach items="${expressAffirmList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKDATE}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.BOOKSTARTTIME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKENDTIME} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.BOOKNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME} </td>
		      <td width="10%" class="info_content_00"><textarea name="useInformation" style=" height: 20px;width:150px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';" readonly>${varTest.PURPOSEOFUSE}</textarea></td>
		      <td nowrap="nowrap" align="center" class="info_content_00">
		      <table align="center">
		      <tr><td><font color="#990099">${varTest.AFFIRM_LEVEL}<%=admin.getChineseName()%></font>
		      <c:if test="${varTest.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		      <c:if test="${varTest.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
		      <c:if test="${varTest.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      </td>
		      <td>
		      <c:if test="${(varTest.AFFIRM_FLAG==0||varTest.AFFIRM_FLAG==2) && (varTest.ISCONFIRM==0)}"><a href="/gaControlServlet?menu_code=ga0205&operation=conferenceRoom&content=ongingAffirm&flag=1&affirmno=${varTest.APPLYNO}&affirmorid=${varTest.AFFIRMOR_ID}"><font color="red">通过</font></a></c:if><c:if test="${(varTest.AFFIRM_FLAG==0 ) && (varTest.ISCONFIRM==0)}">|</c:if>
		      <c:if test="${(varTest.AFFIRM_FLAG==0||varTest.AFFIRM_FLAG==1) && (varTest.ISCONFIRM==0)}"><a href="/gaControlServlet?menu_code=ga0205&operation=conferenceRoom&content=ongingAffirm&flag=2&affirmno=${varTest.APPLYNO}&affirmorid=${varTest.AFFIRMOR_ID}"><font color="red">否决</font></a></c:if>    
		      </td></tr>
		      </table>     
		      </td>		   
			 </tr>	
		</c:forEach>		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=conferenceRoom&content=andconferenceRoomAffirm&menu_code=${param.menu_code}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
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
