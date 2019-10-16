<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<!-- ga_visiter_Approval.jsp -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>参观者决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function band(backColor,textColor,i)
{
    
	var t;
	if(typeof(preEl)!='undefined')
	{
	preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	document.form1.temp.value=i;
	
} 
function ImpExcel(applyno){
document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=ga_visiter_excel&applyno="+applyno;
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
    if (confirm(msg5)) {
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&flag=1&content=visiterApprovalOKorNO";
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
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&flag=2&content=visiterApprovalOKorNO";
	document.form1.submit();
	}
}
 function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=visiterApproval";
 document.form1.submit(); 
 } 
 
 function visiterApprovalOKorNO(applyno,flag){
document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=visiterApprovalOKorNO&flag="+flag+"&applyno="+applyno;
document.form1.submit();
}
function Update() {
	
	if(document.form1.temp.value==0) {
		// "请选择修改项目"
	    alert("请选择修改项目");
		return;
	}
	document.form1.action="/puControlServlet?operation=visiterManagement&content=visiterManagementModifyView&menu_code=${param.menu_code}&visiterManagementId="+document.form1.temp.value+"&returnFlag=affirm";
	document.form1.submit();
}
</SCRIPT>

<body>
<form name="form1" method="post" action="">

<input type="hidden" name="tempapplyId" value="${tempapplyId}"/>
<input type="hidden" name="empid" value="<%=admin.getAdminID() %>"/>
<input type="hidden" name="ck_all" value="0" />
<input type="hidden" name="tempAffirmNo" value=""/>
<input type="hidden" name="GA_VISITER_ID" value=""/>
<input type="hidden" name="temp" value="" />
	<input type="hidden" name="allvisiterApplyInformationCount" value="${allvisiterApplyInformationCount}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_affirm_update.jsp"%>
			
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
				参观者决裁
				</td>
			</tr>     
		</table>
		<%
			if (!errorMsg.equals("")) {
		%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%
			}
		%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		    
		     <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"><!-- 全选 --> 
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				申请人</td>
		<!--      <td nowrap="nowrap" class="info_title_01"  >
				职务</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				工作单位</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				区分</td>			 
			  <td nowrap="nowrap" class="info_title_01"  >
				来访日期</td>-->
			  <!--<td nowrap="nowrap" class="info_title_01" style="width: 130px">
				截止时间</td>
			  -->
			  <td nowrap="nowrap" class="info_title_01">
				来访日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				到达时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				离开时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				来访单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问地点</td>
		      <td nowrap="nowrap" class="info_title_01">
				来访目的</td>
			  <td nowrap="nowrap" class="info_title_01">
				来访人数</td>
			  <td nowrap="nowrap" class="info_title_01" >
				查看详情</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>
<!--			  <td nowrap="nowrap" class="info_title_01" >
				导出申请书</td>-->
			 <td nowrap="nowrap" class="info_title_01">
				意见</td>	
			  
		    </tr>
		    <c:forEach items="${allvisiterApplyInformation}" var="all" varStatus="i">
			   					
					<tr	onClick="band('#E7F0EF','black','${all.GA_VISITER_APPLY_ID}')" align="center">		     
				      <td nowrap="nowrap" align="center" class="info_content_01" >
				      	&nbsp;<c:if test="${all.ACTIVE==0 && all.AFFIRM_FLAG==0}">
				      	 <input type="checkbox" name="applyno" value="${all.GA_VISITER_APPLY_ID}" />
				      	</c:if>&nbsp;
					  </td>
					  <td nowrap="nowrap" class="info_content_01">
			    			&nbsp;${all.GA_VISITER_APPLY_ID}
			    			<input type="hidden" name="applerName_${all.GA_VISITER_APPLY_ID}" value="${all.PLAY_APPLYCHINESENAME}">
			    			<input type="hidden" name="applerId1_${all.GA_VISITER_APPLY_ID}" value="${all.PLAY_APPLY}">
				      </td>
				       <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.PLAY_APPLYCHINESENAME}
				      </td>
<!--			    	  <td nowrap="nowrap" class="info_content_01">
			    			&nbsp;${all.GA_VISITER_APPLY_ID}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_NAME}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DUTY}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DISTINICTION}
				      </td>		-->		      
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DATE}<input type="hidden" name="VISITER_DATE_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_DATE}">
				      </td>
				      <!--<td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_OUT_DATE}
				      </td>
				      --><td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_COME_TIME}<input type="hidden" name="VISITER_COME_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_COME_TIME}">
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_END_TIME}<input type="hidden" name="VISITER_END_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_END_TIME}">
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISIT_PLACE}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_OBJECTIVE}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISIT_COUNT}<input type="hidden" name="VISIT_COUNT_${all.GA_VISITER_APPLY_ID}" value="${all.VISIT_COUNT}">
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		<a href="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewDetail&applyno=${all.GA_VISITER_APPLY_ID}" style="color:red">查看详情</a>
				      </td>
			    <td nowrap="nowrap" align="center" class="info_content_00">
		      <table align="center">
	    			
					     <c:forEach items="${all.allVisiterApproval}" var="view" varStatus="j">
					     	<tr>
					     	<c:if test="${view.CHINESENAME != adminName}">
					     		<td nowrap="nowrap">
				      	 		${view.AFFIRM_LEVEL }${view.CHINESENAME }
						     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	<br>
						      	</td>
				      	 	</c:if>
					     
					     	<c:if test="${view.CHINESENAME == adminName}">
					     	<input type="hidden" name="affirmNo_${all.GA_VISITER_APPLY_ID}" value="${view.GA_AFFIRM_NO}">
					     	<input type="hidden" name="MAX_AFFIRM_FLAG_${all.GA_VISITER_APPLY_ID}" value="${view.MAX_AFFIRM_FLAG}">
					     	<input type="hidden" name="AFFIRM_LEVEL_${all.GA_VISITER_APPLY_ID}" value="${view.AFFIRM_LEVEL}">
					     	  <td nowrap="nowrap">	
					     		${view.AFFIRM_LEVEL}${view.CHINESENAME}
						     	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#990099">未决裁</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">已通过</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	
						      	<c:if test="${all.ACTIVE==0&& view.AFFIRM_FLAG==0}">|
						      		<a href="#" onclick="visiterApprovalOKorNO('${all.GA_VISITER_APPLY_ID}','1')"><font color="red">通过</font></a>
						      	</c:if>
						      	<c:if test="${all.ACTIVE==0 && view.AFFIRM_FLAG==0}">
						      		<a href="#" onclick="visiterApprovalOKorNO('${all.GA_VISITER_APPLY_ID}','2')"><font color="red">否决</font></a>
						      	</c:if>
				      	 		<br>
				      	 		</td>
				      	 	</c:if>
				      	 	</tr>
					     </c:forEach>
					      </table> 		    
				    </td>
				    <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${all.GA_VISITER_APPLY_ID}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${all.AFFIRMORIDEA}</textarea></td>
<!--				    <td nowrap="nowrap" class="info_content_01">
				      		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel('${all.GA_VISITER_APPLY_ID}');"/>
				    </td>				    -->
			    </tr>
		    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		  <!-- Page Navigation Start-->
		<ait:page       
          link="/gaControlServlet"
          parameters="&operation=visiterApplications&content=visiterApproval&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}"
          total="${recordCount}"
          currentpage="${currentPage}"
          pagesize= "${pageSize}"
          beginlabel="paging_prv10"
          endlabel="paging_next10"
          prevlabel="paging_prv"
          nextlabel="paging_next"
          pagegroupsize="${pageGroupsize}"
          useJS="false"/>      
	<!-- Page Navigation End -->
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