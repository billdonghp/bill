<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="loginAdmin" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>隐患整改反馈</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ScenePhotos(photosid){
photosid = photosid+"1";
//window.open("/ajaxControlServlet?operation=uploadImp&correctiveplan=correctiveplan&documentno="+photosid,"","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
 function search(){
 document.form1.action="/gaControlServlet?menu_code=ga0208&operation=securityEnvironment&content=completedRectificationAffirm";
 document.form1.submit(); 
 }
 
  function visiterApprovalOKorNO(applyno,flag){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=crfongingAffirm&flag="+flag+"&applyno="+applyno;
	document.form1.submit();
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
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" readonly onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" readonly onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3" <c:if test="${qryType==3}">selected</c:if> >全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
				  </td>                
		        </tr> 
		        <tr>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDept name="deptid" supervisorType="pa" all="all" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>       
	
		           <td  class="info_title_01">&nbsp;
		          </td>
		         <td  class="info_content_00">
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
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
				隐患整改反馈决裁
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
				整改人</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改要求日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				实际完成日期</td>	
			  <td nowrap="nowrap" class="info_title_01">
				整改情况及结果（少于500个汉字）</td>
		      <td nowrap="nowrap" class="info_title_01">
				整改完成照片</td>			 
			 <td nowrap="nowrap" class="info_title_01">
				决裁</td>
			<td nowrap="nowrap" class="info_title_01">
				意见</td>		
		    </tr>			
		 <c:forEach items="${applyList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.SECURITYCHECKSNO}
		      <input type="hidden" name="applerId_${varTest.APPLYNO}" value="${varTest.APPLYORID}" >
		       <input type="hidden" name="documentno_${varTest.APPLYNO}" value="${varTest.SECURITYCHECKSNO}" >
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CHINESENAME}<input type="hidden" name="applyorname_${varTest.APPLYNO}" value="${varTest.CHINESENAME}" ></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.COMPLETIONDATE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.OVERDATE}<input type="hidden" name="overdate_${varTest.APPLYNO}" value="${varTest.OVERDATE}" ></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CRFNOTE}&nbsp;<input type="hidden" name="useInformation_${varTest.APPLYNO}" value="${varTest.CRFNOTE}" /></td>
		      <td nowrap="nowrap" align="center" class="info_content_01"><span style="color:red; cursor:pointer;" onClick="ScenePhotos('${varTest.SECURITYCHECKSNO}')">查看整改完成照片</span></td>
		      <td nowrap="nowrap" class="info_content_01">
		      
		       <c:forEach items="${varTest.affirmorList}" var="view" varStatus="j">		        	
		                <c:if test="${view.AFFIRMOR_ID != admin.adminID}">
				      	 		<font color="#990099">${view.AFFIRM_LEVEL }${view.CHINESENAME }</font>
				      	 		 <c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		                         <c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
		                         <c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	<br>
				      	 </c:if>
					     
					     	<c:if test="${view.AFFIRMOR_ID == admin.adminID}">
					     	<input type="hidden" name="affirmNo_${varTest.APPLYNO}" value="${view.GA_AFFIRM_NO}">
					     	<input type="hidden" name="MAX_AFFIRM_FLAG_${varTest.APPLYNO}" value="${view.MAX_AFFIRM_FLAG}">
					     	<input type="hidden" name="AFFIRM_LEVEL_${varTest.APPLYNO}" value="${view.AFFIRM_LEVEL}">					     	
					     		<font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
					     		 <c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		                         <c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
		                         <c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>						   
						      	<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">|
						      		<a href="#" onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','1')"><font color="red">通过</font></a>
						      	</c:if>
						      	<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">
						      		<a href="#" onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','2')"><font color="red">否决</font></a>
						      	</c:if>
				      	 		<br>
				      	 	</c:if>
		      
		          </c:forEach>					    
					  
					 
				    </td>	
				   <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${varTest.APPLYNO}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${varTest.AFFIRMORIDEA}</textarea></td>   
			 </tr>	
		</c:forEach>	
		<input type="hidden" name="currentPage" value="${currentPage}">	
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=securityEnvironment&content=completedRectificationAffirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
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
