<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="loginAdmin" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<!-- ga_seal_affirm.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>公章决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function search(){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&content=sealAffirm";
	document.form1.submit();
}

 function sealApprovalOKorNO(applyno,flag){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&content=sealApprovalOKorNO&flag="+flag+"&applyno="+applyno;
		
		if(document.getElementById("READ_FLAG_"+applyno).value=="0" 
			   		&& document.getElementById("FILEPATH_"+applyno).value != ""
			   			&& document.getElementById("CPNY_ID_"+applyno).value == "61000000"){
			   		alert("请查看编号"+applyno+"上传文件后再进行公章决裁");
			   		return false;
		}
	document.form1.submit();
	}
	
function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var applyno = document.getElementsByName("applyno") ;
  	for (var i = 0; i< applyno.length ; i++){
		applyno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}

function doAffirm(){
	var msg1='<ait:message messageID="alert.ess.common.nodatatopass" module="ess"></ait:message>';
	
	var msg3='<ait:message messageID="alert.ess.common.checkpass" module="ess"></ait:message>';

	var msg5='<ait:message messageID="alert.ess.common.cofirmpass" module="ess"></ait:message>';

	var affirmno = document.getElementsByName("applyno");
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
	//////////////////////////////////////////////// 
	for (var i=0; i<affirmno.length; i++){
		if(affirmno[i].checked == true){
		   
		   if(document.getElementById("READ_FLAG_"+affirmno[i].value).value=="0" 
		   		&& document.getElementById("FILEPATH_"+affirmno[i].value).value != ""
		   			&& document.getElementById("CPNY_ID_"+affirmno[i].value).value == "61000000"){
		   		alert("请查看编号"+affirmno[i].value+"上传文件后再进行公章决裁");
		   		return false;
		   }
			
		}
	}                                   
    if (confirm(msg5)) {
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&flag=1&content=sealApprovalOKorNO";
	document.form1.submit();
	}
}

function doReject(){
	var msg2='<ait:message messageID="alert.ess.common.nodatatoreject" module="ess"></ait:message>';
	var msg4='<ait:message messageID="alert.ess.common.checkreject" module="ess"></ait:message>';
	var msg6='<ait:message messageID="alert.ess.common.cofirmreject" module="ess"></ait:message>';
	
	var affirmno = document.getElementsByName("applyno");
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
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&flag=2&content=sealApprovalOKorNO";
	document.form1.submit();
	}
}
function viewFile(fileAddress,documentno){
document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=sealManger&content=ongingRead&documentno="+documentno;
document.form1.submit();
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
}

</SCRIPT>

<body>
<form name="form1" method="post" action="">
	<input type="hidden" name="ck_all" value="0" />
	<input type="hidden" name="checkSeal" value="1">
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
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3" <c:if test="${qryType==3}">selected</c:if>>全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未决裁</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
		        </tr> 
		        <tr>
		         <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" all="all" selected="${cpnyId}" onChange="search();"/>
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
		<table id = 'checkRoomInfo' width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				公章决裁
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
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				起案号</td>	
			  <td nowrap="nowrap" class="info_title_01">
				使用章</td>
		      <td nowrap="nowrap" class="info_title_01">
				盖章文件的接收单位</td>
		      <td nowrap="nowrap" class="info_title_01">
				外借</td>
			  <td nowrap="nowrap" class="info_title_01">
				使用内容</td>	
		      <td nowrap="nowrap" class="info_title_01">
				份数 </td>
			  <td nowrap="nowrap" class="info_title_01">
				上传文件 </td>	
			  <td nowrap="nowrap" class="info_title_01">
				归还日期</td>					 	    
			  <td nowrap="nowrap" align="center" class="info_title_01">
				备注</td>
			  <td width="20%" class="info_title_01">
				决裁</td>
			  <td nowrap="nowrap" class="info_title_01">
				意见</td>		
		    </tr>			
		 <c:forEach items="${applyList}" var="varTest" varStatus="i">
		    <tr align="center">
		    <td nowrap="nowrap" align="center" class="info_content_01" >
		      	&nbsp;<c:if test="${varTest.ACTIVE==0 && varTest.AFFIRM_FLAG==0}">
		      	 <input type="checkbox" name="applyno" value="${varTest.DOCUMENTNO}" />
		      	</c:if>&nbsp;
			  </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DOCUMENTNO}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CHINESENAME}
		      <input type="hidden" value="${varTest.CHINESENAME}" name="CHINESENAME_${varTest.DOCUMENTNO}">
		      <input type="hidden" value="${varTest.APPLYORID}" name="applerId_${varTest.DOCUMENTNO}">
		      <input type="hidden" value="${varTest.FILEPATH}" name="FILEPATH_${varTest.DOCUMENTNO}">
		      <input type="hidden" value="${varTest.CPNY_ID}" name="CPNY_ID_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.APPLYDEPTNAME}
		      <input type="hidden" value="${varTest.APPLYDEPTNAME}" name="adminDept_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.USEDATE}
		      <input type="hidden" value="${varTest.USEDATE}" name="useDate_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.DRAFT_NO}
		      <input type="hidden" value="${varTest.DRAFT_NO}" name="draftNo_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME}
		      <input type="hidden" value="${varTest.CODE_NAME}" name="sealType_${varTest.DOCUMENTNO}">
		      <input type="hidden" value="${varTest.SEALTYPE}" name="sealTypeCode_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.ISLEND}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01" style='word-break:break-all' align="left" width="300">${varTest.USEINFORMATION}
		      <input type="hidden" value="${varTest.USEINFORMATION}" name="useInformation_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.USESHARES}
		      <input type="hidden" value="${varTest.USESHARES}" name="useShares_${varTest.DOCUMENTNO}">
		      </td>
		      
		      <td nowrap="nowrap" align="center" class="info_content_01" >
		        <c:if test="${varTest.FILEPATH!=null}">
	             <a href="#" onclick="viewFile('${varTest.FILEPATH}','${varTest.DOCUMENTNO}');"><font color="red">${varTest.FILENAME}</font></a>
	             &nbsp;&nbsp;${varTest.READ_FLAG_NAME}
	            </c:if>	 
	            <c:if test="${varTest.FILEPATH==null}">
	            &nbsp;
	            </c:if>  
	          </td>
	          <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.RETURNDATE}
		      <input type="hidden" value="${varTest.RETURNDATE}" name="returnDate_${varTest.DOCUMENTNO}">
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" style='word-break:break-all' align="left" width="250">${varTest.NOTE}</td>
		      
		      <td nowrap="nowrap" align="center" class="info_content_00">
		      <table align="center">
		      <c:forEach items="${varTest.affirmorList}" var="view" varStatus="j">
			     	<tr>
			     	
			     	<c:if test="${view.PERSON_ID != adminId}">
			     	<td nowrap="nowrap">
		      	 		${view.AFFIRM_LEVEL }${view.CHINESENAME }&nbsp;${view.UPDATE_DATE}
				     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
				      	<br> 
				       </td>
				       	
		      	 	</c:if>
			    
			     
			     	<c:if test="${view.PERSON_ID == adminId}">
			     	<input type="hidden" name="affirmNo_${varTest.DOCUMENTNO}" value="${view.GA_AFFIRM_NO}">
			     	<input type="hidden" name="MAX_AFFIRM_FLAG_${varTest.DOCUMENTNO}" value="${view.MAX_AFFIRM_FLAG}">
			     	<input type="hidden" name="AFFIRM_LEVEL_${varTest.DOCUMENTNO}" value="${view.AFFIRM_LEVEL}">
			     	<input type="hidden" name="READ_FLAG_${varTest.DOCUMENTNO}" value="${view.READ_FLAG}">
			     		   <td nowrap="nowrap">	
			     		${view.AFFIRM_LEVEL}${view.CHINESENAME}&nbsp;${view.UPDATE_DATE}
				     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
				      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
				      	
				      	<c:if test="${varTest.ACTIVE==0&& view.AFFIRM_FLAG==0}">|
				      		<a href="#" onclick="sealApprovalOKorNO('${varTest.DOCUMENTNO}','1')"><font color="red">通过</font></a>
				      	</c:if>
				      	<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">
				      		<a href="#" onclick="sealApprovalOKorNO('${varTest.DOCUMENTNO}','2')"><font color="red">否决</font></a>
				      	</c:if>
				      	<br>
		      	 		</td>
		      	 		
		      	 		
		      	 	</c:if>
		      	 	
		      	 	</tr>
			     </c:forEach>		     
		      </table> 		    
		      </td>	
		       <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${varTest.DOCUMENTNO}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${varTest.AFFIRMORIDEA}</textarea></td>	   
			 </tr>	
		</c:forEach>		
		 <tr align="center"> 
			 </tr>
		 </table>
		  <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=sealManger&content=sealAffirm&menu_code=${param.menu_code}&qryType=${qryType }&deptid=${deptid }&endDate=${endDate }&startDate=${startDate }&key=${key }" 
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
<input type="hidden" name="currentPage" value="${currentPage}">
</form>
<table>
 <tr>  
  </tr>
</table>
</body>
</html>