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
<title>快件决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var affirmno = document.getElementsByName("affirmno") ;
  	for (var i = 0; i< affirmno.length ; i++){
		affirmno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}
function doReject(){
	var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
	var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
	var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	
	var affirmno = document.getElementsByName("affirmno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg2);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg4);
		return false;
	}                                       
    if (confirm(msg6)) {
		document.form1.action = "/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=ongingAffirm&flag=2"
		document.form1.submit();
	}
}

function doAffirm(){

	var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("affirmno");
	if (affirmno == null || affirmno.length == null || affirmno.length == 0){
	  	alert(msg1);
		return false;
	}
	var c = 0;
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
			c++;
			break ;
		}
	}
	if (c==0){
	  	alert(msg3);
		return false;
	}                                       
    if (confirm(msg5)) {
		document.form1.action = "/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=ongingAffirm&flag=1"
		document.form1.submit();
	}

}

 function submitA(affirmno,flag){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=ongingAffirm&affirmno="+affirmno+"&flag="+flag;
 document.form1.submit();
 }
 
 function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=expressAffirm";
 document.form1.submit(); 
 }
 function companyChange(){
	document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=expressApplyAndAffirm&content=expressAffirm&companyId='+document.form1.company.value;
	document.form1.submit();	
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
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>				     
		          <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td> 
		        </tr> 
		        <tr>
		          <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		           	法人区分     
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <select name="company" onchange="companyChange()">
					    <c:forEach items="${companyList}" var="list">
					  		<c:choose>
							<c:when test="${companyId==list.CPNY_ID}">
								<option value="${list.CPNY_ID}" selected="selected">
									${list.CPNY_LOCATION}
								</option>
							</c:when>
							<c:otherwise>
								<option value="${list.CPNY_ID}">
									${list.CPNY_LOCATION }
								</option>
							</c:otherwise>
							</c:choose>
					   </c:forEach>
				  </select>
		          </td>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${companyId}" selected="${deptid}" />
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/>
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
				快件决裁
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
		      <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
		      <!--<td nowrap="nowrap" class="info_title_01">
				发件人职号</td>-->
		      <td nowrap="nowrap" class="info_title_01">
				发件人</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人部门</td>
		      <!--<td nowrap="nowrap" class="info_title_01">
				申请人职号</td>-->
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>	
			  <td nowrap="nowrap" class="info_title_01">
				申请时间</td>
		      <td nowrap="nowrap" class="info_title_01">
				快件类型</td>	
			 <td nowrap="nowrap" class="info_title_01">
				快递单号</td>	
			 <td nowrap="nowrap" class="info_title_01">
				发件城市</td>	      
		      <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>	
			  <td nowrap="nowrap" class="info_title_01">
				快件内容</td>		
				<!--
			  <td nowrap="nowrap" class="info_title_01">
				收件单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				发件原因</td>		
			  <td nowrap="nowrap" class="info_title_01">
				收件人</td>
				 -->	
			  <td nowrap="nowrap" class="info_title_01">
				决裁</td>				
		      <td nowrap="nowrap" class="info_title_01">
				意见</td>	
		    </tr>
		 <c:forEach items="${expressAffirmList}" var="varTest" varStatus="i">		 
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" >
		      	&nbsp;<c:if test="${ varTest.ISCONFIRM == 0 && varTest.ACTIVE==0 }">
		      	 <input type="checkbox" name="affirmno" value="${varTest.APPLY_NO}" />
		      	 <input type="hidden" name="affirmlevel_${varTest.APPLY_NO}" value="${varTest.AFFIRM_LEVEL}">
		      	</c:if>&nbsp;
			  </td>
			  <!-- <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.EMPID1}</td>-->
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.SENDPERSONNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.DEPTNAME}</td>
		      <!-- <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.EMPID2}<input type="hidden" name="applyorid_${varTest.APPLY_NO}" value="${varTest.APPLYOR_ID}"></td> -->
		      <input type="hidden" name="applyorid_${varTest.APPLY_NO}" value="${varTest.APPLYOR_ID}">
		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.APPLYEMPNAME}<input type="hidden" name="applyorname_${varTest.APPLY_NO}" value="${varTest.APPLYEMPNAME}"></td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >&nbsp;${varTest.APPLYDATE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.CODE_NAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.POSTNUMBER}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.CITYSENDTO}<input type="hidden" name="citySent_${varTest.APPLY_NO}" value="${varTest.CITYSENDTO}"></td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">&nbsp;${varTest.CITYARRIVE_S }${varTest.CITYARRIVE }${varTest.CITYARRIVE_Q }${varTest.CITYARRIVE_D}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.POSTDESCRIPTION}<input type="hidden" name="postDescription_${varTest.APPLY_NO}" value="${varTest.POSTDESCRIPTION}"></td>
		     <!-- 
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.POSTADDRESS}<input type="hidden" name="postAddress_${varTest.APPLY_NO}" value="${varTest.POSTADDRESS}"></td>
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.CAUSE}</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;${varTest.SENDTOPERSON}</td>
		      -->
		      <td nowrap="nowrap" align="center" class="info_content_00">
<!--		      <table align="center">		    		     
		      <c:forEach items="${varTest.affirmorList}" var="varAffiror" varStatus="j">		      	      
		      <tr>
		      <td nowrap="nowrap"><font color="#990099">${varAffiror.AFFIRM_LEVEL}${varAffiror.CHINESENAME}</font></td>
		      <c:if test="${varAffiror.AFFIRMOR_ID==loginAdmin}">
		      <input type="hidden" name="affirmlevel_${varTest.APPLY_NO}" value="${varAffiror.AFFIRM_LEVEL}">
		      <input type="hidden" name="MAX_AFFIRM_FLAG_${varTest.APPLY_NO}" value="${varAffiror.MAX_AFFIRM_FLAG}">
		      </c:if>		    
		      <td nowrap="nowrap">
		      <c:if test="${varAffiror.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
		      <c:if test="${varAffiror.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
		      <c:if test="${varAffiror.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
		      |</td>
		      <td nowrap="nowrap">
		      <c:if test="${varTest.ACTIVE==0 && varAffiror.AFFIRM_FLAG==0 }"><a href="#" onClick="submitA('${varTest.APPLY_NO}','1')" style="color:red;">通过</a></c:if>
		      <c:if test="${varTest.ACTIVE==0 && varAffiror.AFFIRM_FLAG==0}"><a href="#" onClick="submitA('${varTest.APPLY_NO}','2')" style="color:red;">否决</a></c:if>
		      </td>
		      </tr>	
		      </c:forEach>
		      </table>   -->
		      			<c:forEach items="${varTest.affirmorList}" var="view" varStatus="j">
					     	<c:if test="${view.AFFIRMOR_ID != adminId}">
				      	 		<font color="#00CC00">${view.AFFIRM_LEVEL }${view.CHINESENAME }</font>
						     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	<br>
				      	 	</c:if>
					     
					     	<c:if test="${view.AFFIRMOR_ID == adminId}">
					     	<input type="hidden" name="affirmNo_${varTest.APPLY_NO}" value="${view.GA_AFFIRM_NO}">
					     	<input type="hidden" name="MAX_AFFIRM_FLAG_${varTest.APPLY_NO}" value="${view.MAX_AFFIRM_FLAG}">
					     	<input type="hidden" name="AFFIRM_LEVEL_${varTest.APPLY_NO}" value="${view.AFFIRM_LEVEL}">
					     	
					     		${view.AFFIRM_LEVEL}${view.CHINESENAME}
						     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	
						      	<c:if test="${varTest.ACTIVE==0&& view.AFFIRM_FLAG==0}">|
						      		<a href="#" onclick="submitA('${varTest.APPLY_NO}','1')"><font color="red">通过</font></a>
						      	</c:if>
						      	<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">
						      		<a href="#" onclick="submitA('${varTest.APPLY_NO}','2')"><font color="red">否决</font></a>
						      	</c:if>
				      	 		<br>
				      	 	</c:if>
					     </c:forEach>  &nbsp; 
		      </td>	
		      <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${varTest.APPLY_NO}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${varTest.AFFIRMORIDEA}</textarea></td>	   
			 </tr>	
		</c:forEach>
		<input type="hidden" name="currentPage" value="${currentPage}">		
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=expressApplyAndAffirm&content=expressAffirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}" 
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
