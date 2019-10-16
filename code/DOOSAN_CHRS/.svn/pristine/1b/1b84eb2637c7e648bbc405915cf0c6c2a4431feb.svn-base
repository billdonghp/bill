<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="allvisiterApplyInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="flag" class="java.lang.String" scope="request"/>
<jsp:useBean id="chinesename" class="java.lang.String" scope="request"/>
<jsp:useBean id="level" class="java.lang.String" scope="request"/>
<jsp:useBean id="affirmorId" class="java.lang.String" scope="request"/>
<jsp:useBean id="affirmId" class="java.lang.String" scope="request"/>
<jsp:useBean id="applyId" class="java.lang.String" scope="request"/>
<html>
<head>
<!-- ga_room_approval_information1.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>会议室情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ImpExcel(applyno){
document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=ga_visiter_excel&applyno="+applyno;
document.form1.submit();
}
 
 function exportExcel(){
 document.form1.action="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=conferenceRoomExcel";		            

 document.form1.submit(); 
 }
 
 function search(){
 document.form1.content="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=conferenceRoomConfirm1";		            
 
 document.form1.submit(); 
 }
 
 function Delete(applyno){

    if(confirm("确认删除该记录吗？")){
      document.form1.content="/puControlServlet?menu_code=${param.menu_code}&operation=conferenceRoom&content=Delete&applyno="+applyno;
      document.form1.submit();
      }else{
       return ;
      }
}
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
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if>>未确认</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
				     </select>
				  </td>
				  <td  class="info_content_00">
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />&nbsp;&nbsp; &nbsp;&nbsp; 
		         <ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle" onclick="javascript:exportExcel();" style="cursor:hand" />
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
				<td align="left" class="title1" colspan="10">
				会议室情况
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
			<td nowrap="nowrap" class="info_title_01">意见</td>
			<td nowrap="nowrap" class="info_title_01">
				操作</td>
			  <td nowrap="nowrap" class="info_title_01">
				状态</td>
          </tr>	 
			<c:forEach items="${conferenceRoomConfirmList}" var="varTest" varStatus="i">
		    <tr height="30">
		      <td nowrap="nowrap" align="center">${varTest.CONFERENCENO}</td>
		      <td nowrap="nowrap" align="center">
		      ${varTest.BOOKDATE}
		      </td>
		      <td nowrap="nowrap"  align="center">
		         ${varTest.hour}:${varTest.min}
		      </td>
		      <td nowrap="nowrap" align="center">
		      ${varTest.ENDDATE}
		      </td>
		      <td nowrap="nowrap" align="center">
		         ${varTest.endhour}:${varTest.endmin}
		      </td>
		      <td nowrap="nowrap" align="center">${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap"  align="center">&nbsp;${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap" align="center">&nbsp;${varTest.PEOPLECLASS}</td>
		      <td nowrap="nowrap" align="center">&nbsp;${varTest.PEOPLECLASS_UP}</td>		      
		      <td nowrap="nowrap" align="center">&nbsp;${varTest.BOOKNUMBER}</td>
		      <td nowrap="nowrap" align="center">&nbsp;${varTest.ROOMNAME}
			  </td>
			  <td nowrap="nowrap" id = 'equips${varTest.APPLYNO}'>&nbsp;
			  <c:forEach items="${varTest.equipsList}" var="equipsList" varStatus="j">
			  	 	<c:forEach items="${equipsList.EQUIP}" var="equip" varStatus="k">
			  	 		<c:forEach items="${varTest.equipsApplyList}" var="equipsApplyList" varStatus="m">
			  	 		<c:forEach items="${equipsApplyList.EQUIPS}" var="equips" varStatus="n">
			  	 			<c:if test="${equip==equips}">
			  	 				${equip}
			  	 			</c:if>
			  	 		</c:forEach>	
			  	 		</c:forEach>
			  	 		
			  	 	</c:forEach>
			  </c:forEach>
			  </td>
			  <td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;${varTest.OTHERREQUEST}</td>
			  <td nowrap="nowrap" align="center"  style="color: #666666;">${varTest.PURPOSEOFUSE}&nbsp;</td>
			   <td nowrap="nowrap" align="center"  style="color: #666666;">${varTest.AFFIRMORIDEA }&nbsp;</td>
			 <!--varTest.ISCONFIRM==0 && varTest.APPLYORID eq adminID 确认后也可删除 -->
		      <td nowrap="nowrap" align="center"  style="color: #666666;">
		     
		      <c:if test="${varTest.APPLYORID eq adminID}">
		      <a href="/puControlServlet?menu_code=ga0313&operation=conferenceRoom&content=Delete&applyno=${varTest.APPLYNO}" onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font color="red">删除</font></a>
		     </c:if>
		     &nbsp;
		      </td>
		      <td nowrap="nowrap" align="center" style="color: #666666;"><c:if test="${varTest.ISCONFIRM==1}"><font color="#00CC00">已确认</font></c:if><c:if test="${varTest.ISCONFIRM==0}"><font color="red">未确认</font></c:if></td>		      
		    </tr>
		   </c:forEach>
		   <input type="hidden" name="currentPage" value="${currentPage}">
		  </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/puControlServlet"
		               parameters="&menu_code=ga0313&operation=conferenceRoom&content=conferenceRoomConfirm1&startDate=${startDate}&endDate=${endDate }&qryType=${qryType}" 
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