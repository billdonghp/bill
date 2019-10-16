<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<%@ page import="java.util.*,java.util.Date" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>历史记录</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">

function search() {
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=viewRoomInfo";
	document.form1.submit();
}

function ImportExcel() {
	
	document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=viewRoomInfo&flag=2";
	document.form1.submit();

}

</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
%>
<form name="form1" method="post" action="">
<input type="hidden" name="menu_code" value="pu0101">
<input type="hidden" name="isView" value="">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
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
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					查询条件
				</td>
			</tr>     
		</table>
		<table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	       <tr>
	          <td nowrap="nowrap" class="info_title_01">
	          	开始日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" name="starttime" style="width: 80px" value="${starttime}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_title_01">
	          	结束日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" name="endtime" style="width: 80px" value="${endtime}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_title_01">
	          	会议室
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<select name="conferenceRoom">
						<option value="">请选择</option>
						<c:forEach items="${roomname}" var="roomname" varStatus="i">
							<option value="${roomname.ID}" <c:if test="${roomname.ID==conferenceRoom}">selected</c:if>>
								${roomname.ROOMNAME}
							</option>
						</c:forEach>
					</select>
	          </td>
	          <td nowrap="nowrap" class="info_title_01">
	          	申请部门
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<ait:selDept name="dept"  style="width: 80px" all="all" supervisorType="hr" selected="${dept}"/>
	       	 </td>	
	       	 <td nowrap="nowrap" class="info_content_00">
	          		<img src="../images/btn/Search.gif" style="cursor: pointer;" onclick="search();">
	       			<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportExcel();"/>
	       	 </td>
	        </tr>
	      </table>		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				会议室使用情况
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
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				开始日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				开始时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				结束时间</td>
              <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会人员</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会领导</td>
		      <td nowrap="nowrap" class="info_title_01">
				与会人数</td>
		      <td nowrap="nowrap" class="info_title_01">
				会议室 </td>	    
		      <td nowrap="nowrap" class="info_title_01">
				设备 </td>
			  <td nowrap="nowrap" class="info_title_01">
				其它要求</td>
			  <td nowrap="nowrap" class="info_title_01">
				会议主题</td>
      
          </tr>	
           <c:forEach items="${viewRoomInfoList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.CONFERENCENO}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.BOOKDATE}<%--<br>
		     		 <c:choose>
						<c:when test="${varTest.BOOKDATE!=varTest.BEFORE_BOOKDATE}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKDATE}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.BOOKSTARTTIME}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKSTARTTIME}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.ENDDATE}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKSTARTTIME!=varTest.BEFORE_BOOKSTARTTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKSTARTTIME}</font>
						</c:when>
					</c:choose>${varTest.ENDDATE}
		      --%></td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.BOOKENDTIME}
		      		<%--<c:choose>
						<c:when test="${varTest.BOOKENDTIME!=varTest.BEFORE_BOOKENDTIME}">
							<br>原信息：<font color="red">${varTest.BEFORE_BOOKENDTIME}</font>
						</c:when>
					</c:choose>
		      --%></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.PEOPLECLASS}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.PEOPLECLASS_UP} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.BOOKNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.ROOMNAME}
		      						
					<%--<c:choose>
						<c:when test="${varTest.CONFERENCEROOM!=varTest.BEFORE_CONFERENCEROOM}">
							<br>原信息：<font color="red">${varTest.BEFORE_ROOMNAME}</font>
						</c:when>
					</c:choose>
								
			  --%></td>
			  <td nowrap="nowrap" class="info_content_00">&nbsp;${varTest.EQUIPS}</td>
			  <td nowrap="nowrap" class="info_content_00">&nbsp;${varTest.OTHERREQUEST}</td>
			  <td class="info_content_00">&nbsp;${varTest.PURPOSEOFUSE}</td>
		    </tr>
		    
		    <input type="hidden" name="roomname" value="&nbsp;${varTest.ROOMNAME}">
		   </c:forEach>
		  </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/puControlServlet"
		               parameters="&operation=conferenceRoom&content=viewRoomInfo&menu_code=${param.menu_code}&starttime=${starttime}&endtime=${endtime}&conferenceRoom=${conferenceRoom}&dept=${dept}" 
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
