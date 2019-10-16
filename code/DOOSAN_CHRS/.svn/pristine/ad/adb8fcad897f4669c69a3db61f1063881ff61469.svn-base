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
<title>整改计划通报决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
 function submitA(affirmno,ini,flag,applyno,documentno,applyorid,applyorname,affrimlevel){
 document.form1.action="/gaControlServlet?menu_code=ga0206&operation=securityEnvironment&content=ongingAffirm&flag="+flag+"&affirmno="+affirmno+"&checkDate="+document.form1["APPLY_DATE"+ini].value+"&applyno="+applyno+"&applyorid="+applyorid+"&applyorname="+applyorname+"&affrimlevel="+affrimlevel;
 document.form1.documentno.value=documentno;
 document.form1.submit();
 }
 function search(){
 document.form1.action="/gaControlServlet?menu_code=ga0206&operation=securityEnvironment&content=correctivePlanAffirm";
 document.form1.submit(); 
 }
</SCRIPT>

<body>
<form name="form1" method="post" action="">
	<input type="hidden" name="documentno" value="">
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
				         <option value="">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
		        </tr> 
		        <tr>
		       	 <td  class="info_title_01">法人区分
		          </td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
		         </td> 
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>                     
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
				整改计划通报决裁
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
				申请人</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				要求完成日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				计划完成日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改计划（500个汉字以内）</td>			 
			 <td nowrap="nowrap" class="info_title_01">
				决裁</td>		
		    </tr>			
		 <c:forEach items="${applyList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.SECURITYCHECKSNO}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CHINESENAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.RECTIFICATIONCOMPLETIONDATE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01"><input type="text" name="APPLY_DATE${i.index}" class="content" style="width:70px "  value="${varTest.PLANCOMPLETIONDATE}" readonly onClick="setday(this);"></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01"><textarea name="useInformation" style=" height: 20px;width:350px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';" readonly>${varTest.CORRECTIVEPLAN}</textarea></td>
		      <td nowrap="nowrap" align="center" class="info_content_00">
		      <table align="center">
		      <%int isaffirm = 0; %>
		      <c:forEach items="${varTest.affirmorList}" var="varAffiror" varStatus="j">
		        <c:if test="${varAffiror.AFFIRM_FLAG==2 }">
		      	<% isaffirm = 1;%>
		       </c:if>
		      <%request.setAttribute("isaffirm", isaffirm); %>
		      <tr>
		      <td nowrap="nowrap"><font color="#990099">${varAffiror.AFFIRM_LEVEL}${varAffiror.CHINESENAME}</font></td>
		      <td nowrap="nowrap">
		      <c:if test="${varAffiror.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		      <c:if test="${varAffiror.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
		      <c:if test="${varAffiror.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      </td>
		      <td nowrap="nowrap">
		      <c:if test="${isaffirm==0 && (varAffiror.AFFIRM_FLAG==0 || varAffiror.AFFIRM_FLAG==2) && (varAffiror.AFFIRMOR_ID==loginAdmin)}"><a href="#" onClick="submitA('${varAffiror.GA_AFFIRM_NO}','${i.index}','1','${varTest.APPLYNO}','${varTest.SECURITYCHECKSNO }','${varTest.APPLYORID}','${varTest.CHINESENAME}','${varAffiror.AFFIRM_LEVEL}')" style="color:red;">通过</a></c:if>
		      <c:if test="${isaffirm==0 && (varAffiror.AFFIRM_FLAG==0 || varAffiror.AFFIRM_FLAG==1) && (varAffiror.AFFIRMOR_ID==loginAdmin)}"><a href="#" onClick="submitA('${varAffiror.GA_AFFIRM_NO}','${i.index}','2','${varTest.APPLYNO}','${varTest.SECURITYCHECKSNO }','${varTest.APPLYORID}','${varTest.CHINESENAME}','${varAffiror.AFFIRM_LEVEL}')" style="color:red;">否决</a></c:if>
		      </td>
		      </tr>			    
		      </c:forEach>
		      </table> 		    
		      </td>		   
			 </tr>	
		</c:forEach>
		<input type="hidden" name="currentPage" value="${currentPage}">		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=securityEnvironment&content=correctivePlanAffirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
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
