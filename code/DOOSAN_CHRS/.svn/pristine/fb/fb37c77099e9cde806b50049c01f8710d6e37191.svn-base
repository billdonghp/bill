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
<title>就餐决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
 function checkAll()
	{
	    var selected = document.form1.ck_all.value == "0" ? true : false;
	    var countsArr = document.getElementsByName("counts") ;
	  	for (var i = 0; i< countsArr.length ; i++){
			countsArr[i].checked = selected ;
		
		}
	    document.form1.ck_all.value = selected ? "1" : "0";
	}

 function search(){
 document.form1.action="/gaControlServlet?menu_code=ga0202&operation=gaAffirm&content=cardApplicationAffirm";
 document.form1.submit(); 
 }
 
 function singleAffirm(affirmFlag,counts){
   var countsArr=document.getElementsByName("counts"); 
   countsArr[counts].checked="selected";   
     if(affirmFlag==2){
       doReject();
     }else{
       doAffirm();
     }    
 
 }
  
 function doAffirm(){ 
   var selectFlag=false;
   var countsArr=document.getElementsByName("counts");
   for(var i=0;i<countsArr.length;i++){
      if(countsArr[i].checked){
       selectFlag=true;
      }   
   }
   if(!selectFlag){
          alert("请选择要通过的信息！");
		   return;
   }else{
          document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&content=batchAffirm&affirmFlag=1";
		  document.form1.submit();
   }
    
 }
 
 function doReject(){
   if(confirm("确认对已选择的信息进行否决吗？")){  
	   var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
	           if(document.form1["affirmorIdea_"+countsArr[i].value].value==null || document.form1["affirmorIdea_"+countsArr[i].value].value==""){
			      alert("请填写否决意见！！");
			      document.form1["affirmorIdea_"+countsArr[i].value].onfocus();
			      return;
			    }
	         selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要否决的信息！");
			   return;
	   }else{			  
			  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=gaAffirm&content=batchAffirm&affirmFlag=2";;
			  document.form1.submit();
	  }
  }
}
</SCRIPT>

<body>
<form name="form1" method="post" action="">
<input type="hidden" name="ck_all" value="0" />	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_affirm.jsp"%>
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
		          <input type="text" name="endDate" style="width:70px" value="${endDate}"  onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3">全部</option>
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
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}" />
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}" title="输入姓名或者职号"/></td>       
	             
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
				就餐决裁
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
				<a href="#"onclick="checkAll();" style="color:red;"><ait:message messageID="display.mutual.select_2" module="ess" /></a>
		       	</td>
		      <td nowrap="nowrap" class="info_title_01">
				申请类型</td>
		      <td nowrap="nowrap" class="info_title_01">
				使用人</td>
<!--			  <td nowrap="nowrap" class="info_title_01">
				职务</td>-->
			  <td nowrap="nowrap" class="info_title_01">
				来访单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				用餐人数</td>
			  
			  <td nowrap="nowrap" class="info_title_01">
				餐次</td>
			  <td nowrap="nowrap" class="info_title_01">
				餐饮类别</td>
		      
			  <td nowrap="nowrap" class="info_title_01">
				申请日期</td>
<!--		      <td nowrap="nowrap" class="info_title_01">
				结束日期</td>-->
		      <td nowrap="nowrap" class="info_title_01">
				就餐事由</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>	
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>
			<td nowrap="nowrap" class="info_title_01">
				特记事项</td>	
		    </tr>
		    <c:forEach items="${eatingCardAffirmList}" var="test" varStatus="i">	
		     <input type="hidden" name="applyNo_${i.index}" value="${test.APPLY_NO}">
		     <input type="hidden" name="maxAffirmFlag_${i.index}" value="${test.MAX_AFFIRM_FLAG}">	
		     <input type="hidden" name="affirmNo_${i.index}" value="${test.GA_AFFIRM_NO}">	
		     <input type="hidden" name="applyorEmail_${i.index}" value="${test.EMAIL}">	
		     <input type="hidden" name="applyDate_${i.index}" value="${test.APPLY_DATE}">	
		     <input type="hidden" name="applyUser_${i.index}" value="${test.APPLYER}">		
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" ><c:if test="${test.AFFIRM_FLAG==0 && test.ACTIVE==0 }"><input type="checkbox" name="counts" value="${i.index}"></c:if>&nbsp;</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${test.EMPLOYEE_TYPE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${test.USEROR}</td>
<!--		      <td nowrap="nowrap" align="center" class="info_content_01" >${test.POSTION}&nbsp;</td>-->
		      <td nowrap="nowrap" align="center" class="info_textarea_content" >${test.COMPANYNAME}&nbsp;</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.USER_NUM}</td> 		      
		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.EateryTypeName}</td>	
		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.EATERY_DIFF}</td>	       
		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.START_DATE}</td>
<!--		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.END_DATE}</td>		      -->
		      <td class="info_textarea_content" style="width: 50px">	${test.RMARK} &nbsp;   </td>
		       <td nowrap="nowrap" align="center" class="info_content_01">${test.APPLYER_DEPT}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${test.APPLYER}</td>
		      <td nowrap="nowrap" align="center" class="info_content_00">
              <table align="center">
		      <c:forEach items="${test.affirmorList}" var="affirmor" varStatus="f">
		      <tr>
		      <td nowrap="nowrap">
		          <font color="#990099">${affirmor.AFFIRM_LEVEL}${affirmor.AFFIRMOR_NAME}</font>
		          <c:if test="${affirmor.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==1}"><font color="#00CC00">已决裁</font></c:if>
		          <c:if test="${affirmor.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      </td>
		     <td nowrap="nowrap">		     		    
		     <c:if test="${test.ACTIVE==0 &&affirmor.AFFIRM_FLAG==0 && (affirmor.AFFIRMOR_ID==adminid)}">	
		     <span style="color:red; cursor:pointer;" onClick="singleAffirm(1,'${i.index}');">通过</span>	    
		     </c:if>
		     <c:if test="${test.ACTIVE==0 &&affirmor.AFFIRM_FLAG==0 && (affirmor.AFFIRMOR_ID==adminid)}">
		      <span style="color:red; cursor:pointer;" onClick="singleAffirm(2,'${i.index}');">否决</span>		    
		     </c:if>	    
		     </td>
		      </tr>	
		      </c:forEach>
		      </table>     
		      </td>	
		      <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${i.index}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${test.AFFIRMOR_IDEA}</textarea></td>
		      <td nowrap="nowrap"  align="center" class="info_textarea_content">&nbsp; ${test.TJSX}</textarea></td>				   
			 </tr>	
			  
		</c:forEach>
		<input type="hidden" name="currentPage" value="${currentPage}">	
		 </table>
		 					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=gaAffirm&content=cardApplicationAffirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
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
