<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="securityChecksList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>安全检查记录</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
function ImportExcel() {
	
	document.form2.action="/safeControlServlet?menu_code=${param.menu_code}&operation=securityChecks&content=securityChecksForExcel";
	document.form2.submit();

}

///function ImportTotalExcel() {
///	
///	document.form2.action="/safeControlServlet?menu_code=${param.menu_code}&operation=securityChecks&content=securityChecksForTotalExcel";
///	document.form2.submit();
///
//}

function search(){
document.form2.action="/safeControlServlet?operation=securityChecks&content=securityChecksView&menu_code=${param.menu_code}";
document.form2.submit();
}
function Add(){
 document.form1.action="/safeControlServlet?operation=securityChecks&content=securityChecksaddpage";
 document.form1.submit();
}
function Update(){
if(document.form1.temp.value=="" || document.form1.temp.value==null){
alert("请选择项目！");
}else if(document.form1.isOver.value==0 && (document.form1.isCanUpdate.value==1||document.form1.isCanUpdate.value==2)){
alert("该项目正在进行中，不能修改！");
}else if(document.form1.isCanUpdate.value==1){
alert("该项目已完成，不能修改！");
}else{
document.form1.action="/safeControlServlet?operation=securityChecks&content=getAnSecurityChecksInformationForUpdate&menu_code=${param.menu_code}&securityChecksNo="+document.form1.temp.value;
document.form1.submit();
}
}

 function band(backColor,textColor,i,isupdate,isOver,plan)
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
		 document.form1.isCanUpdate.value=isupdate;
		 document.form1.isOver.value=isOver;
		 document.form1.isPlan.value=plan;
	} 
function Delete(){
	if(document.form1.temp.value=="" || document.form1.temp.value==null){
	alert("请选择项目！");
	return;
	} 
	if(document.form1.isOver.value==0 && (document.form1.isCanUpdate.value==1||document.form1.isCanUpdate.value==2)){
	alert("该项目正在进行中，不能删除！");
	return;
	}
	if(document.form1.isOver.value==1){
	alert("该项目已完成，不能删除！");
	return;
	}
	if(document.form1.isPlan.value==1){
	alert("该项目正在进行中，不能删除！");
	return;
	}
	if(confirm("确认要删除吗？")){
	document.form1.action="/safeControlServlet?operation=securityChecks&content=deleteSecurityCheck&menu_code=${param.menu_code}&securityChecksNo="+document.form1.temp.value;
	document.form1.submit();
	}
	

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

function view(id,flag){
document.form2.action="/safeControlServlet?operation=securityChecks&content=view&menu_code=${param.menu_code}&DOCUMENTNO="+id+"&flag="+flag;
document.form2.submit();
}
function tixing(id){
document.form1.action="/safeControlServlet?operation=securityChecks&content=tixing&menu_code=${param.menu_code}&securityChecksNo="+id;
document.form1.submit();
}


function OKAndNO(id,falg){
document.form1.action="/safeControlServlet?operation=securityChecks&content=oingConfirm&menu_code=${param.menu_code}&falg="+falg+"&securityChecksNo="+id;
document.form1.submit();
}
</SCRIPT>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/common_toolbar_all.jsp"%> 
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
	<form action="" name="form2" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<br>
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
		      <td nowrap="nowrap" class="info_title_01">
		         编号
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          		<input type="text" name="documentno" value="${documentno}" style="width:100px"/>
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--  开始日期-->
	          	开始日期 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" style="width:80px" name="StartDate" value="${StartDate}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             结束日期
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<input type="text" style="width:80px" name="EndDate" value="${EndDate}" onClick="setday(this);" readonly="readonly">
	          </td>
	          <td nowrap="nowrap" class="info_title_01"><!--结束日期  -->
	             部门
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<ait:selDept name="serchDept" all="all" supervisorType="hr" selected="${serchDept}"/>
	          </td>	 
	           <td width="10%" class="info_content_00"  align="right">
	         
          		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImportExcel();"/>
	          </td>
	              
	        </tr>
	        
	        <tr>
		      <td nowrap="nowrap" class="info_title_01">
		         隐患及不符合项目
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          		<ait:codeClass name="HiddenDangersCommunications" codeClass="danger_code" all="请选择" selected="${HiddenDangersCommunications}"/>
	          </td>
	          <td nowrap="nowrap" class="info_title_01">
	          	整改要求 
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<ait:codeClass name="Rectification_requirements" codeClass="Reorganizes_code" all="请选择" selected="${Rectification_requirements}"/>
	          </td>
	           <td nowrap="nowrap" class="info_title_01">
		         分类
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          		<ait:codeClass name="HiddenDangersType" codeClass="Danger_Type" all="请选择" selected="${HiddenDangersType}"/>
	          </td>   
	          <td nowrap="nowrap" class="info_title_01">
	             进展状态
	          </td>
	          <td nowrap="nowrap" class="info_content_00">
	          	<select name="status_code">
	          		<option value="">请选择</option>
	          		<option value="4" <c:if test="${status_code=='4'}">selected</c:if>>已通知</option>
	          		<option value="0" <c:if test="${status_code=='0'}">selected</c:if>>计划反馈</option>
	          		<option value="1" <c:if test="${status_code=='1'}">selected</c:if>>整改反馈</option>
	          		<option value="2" <c:if test="${status_code=='2'}">selected</c:if>>未确认</option>
	          		<option value="3" <c:if test="${status_code=='3'}">selected</c:if>>确认完成</option>
	          		<option value="5" <c:if test="${status_code=='5'}">selected</c:if>>退回</option>
	          	</select>
	          </td>
	          
	         
	          <td width="10%" class="info_content_00"  align="right">
	          <ait:image src="/images/btn/Search.gif" align="absmiddle"
         				onclick="javascript:search();" style="cursor:hand" />
          	
	          </td>
	           
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
<form name="form1" method="post" action="">
<input type="hidden" name="menu_code" value="${param.menu_code}">
<input type="hidden" name="temp" value="">
<input type="hidden" name="isCanUpdate" value="">
<input type="hidden" name="isOver" value="">
<input type="hidden" name="isPlan" value="">
<input type="hidden" name="ck_all" value="">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				安全检查记录
				</td>
			</tr>  
		</table>			
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    
		    <tr align="center" bgcolor="#F5F5F5">
<!--		    <td class="info_title_01"  nowrap><a href="#"onclick="checkAll();" style="color:red;"> 全选  -->
<!--		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a></td>-->
			  <td nowrap="nowrap" class="info_title_01">
				编号</td>    
		      <td nowrap="nowrap" class="info_title_01">
				检查人</td>
			  <td nowrap="nowrap" class="info_title_01">
				检查日期</td>
				 <td nowrap="nowrap" class="info_title_01">
				分类</td>
		      <td nowrap="nowrap" class="info_title_01">
				地点</td>
		
			  <td nowrap="nowrap" class="info_title_01">
				隐患及不符合通报</td>
			  <td nowrap="nowrap" class="info_title_01">
				责任部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改次数</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改要求</td>
		      <td nowrap="nowrap" class="info_title_01">
				要求完成日期 </td>
		      <td nowrap="nowrap" class="info_title_01">
				备注</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改计划</td>
		      <td nowrap="nowrap" class="info_title_01">
				计划完成日期 </td>
		      <td nowrap="nowrap" class="info_title_01">
				实际完成日期 </td>
		      <td nowrap="nowrap" class="info_title_01">
				隐患整改反馈结果</td>
			  <td nowrap="nowrap" class="info_title_01">
				进展状态</td>	
			  <td nowrap="nowrap" class="info_title_01">
				确认意见</td>	
			  <td nowrap="nowrap" class="info_title_01">
				邮件提醒</td>	 	     
		    </tr>
		    <c:forEach items="${securityChecksList}" var="list">
		    <tr onClick="band('#E7F0EF','black','${list.DOCUMENTNO }','${list.ISCOMPLETEDRECTIFICATION }','${list.ISRECTIFICATION }','${list.ISPLAN }')" height="30">
			  <td nowrap="nowrap" align="center">&nbsp;${list.DOCUMENTNO }&nbsp;</td>
			  <td nowrap="nowrap" align="center">&nbsp;${list.CHINESENAME }&nbsp;</td>	
			  <td nowrap="nowrap" align="center">&nbsp;${list.SECURITYCHECKSDATE }&nbsp;
			  <input type="hidden" name="check_date" value="${list.SECURITYCHECKSDATE }">
			  </td>	
			  <td nowrap="nowrap" align="center">&nbsp;${list.HIDDENDANGERSTYPE_NAME }</td>	
			  <td nowrap="nowrap" align="center">&nbsp;${list.LOCATION }&nbsp;</td>	
			  
			  <td nowrap="nowrap" align="center">&nbsp;${list.HIDDENDANGERS_NAME }</td>	
			  <td nowrap="nowrap" align="center">&nbsp;${list.DEPTNAME }&nbsp;</td>
			  <td nowrap="nowrap" align="center">&nbsp;
				<c:choose>
				<c:when test="${list.ZHENGGAINUM == 0}">&nbsp;首次</c:when>
				<c:otherwise>再次&nbsp;</c:otherwise>
				</c:choose>
				</td>	
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;${list.RECTIFICATION_NAME }&nbsp;</td>
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;${list.RECTIFICATION_DATE }&nbsp;
				<input type="hidden" name="Rectification_date" value="${list.RECTIFICATION_DATE }">
				</td>
				<td nowrap="nowrap" align="center"  style="color: #666666;">${list.BRIEF }&nbsp;</td>
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;
				
				<c:if test="${list.ZHENGGAIPLAN == 0 && list.ISCOMPLETEDRECTIFICATION == 0}"><a href="#" onclick="view('${list.DOCUMENTNO }','0')"><font color="red">&nbsp;制定</font></a></c:if>
				<c:if test="${list.ZHENGGAIPLAN == 1 && list.ISCOMPLETEDRECTIFICATION == 0}"><a href="#" onclick="view('${list.DOCUMENTNO }','1')"><font color="red">&nbsp;查看</font></a></c:if>
				<c:if test="${list.ZHENGGAIPLAN == 1 && list.ISCOMPLETEDRECTIFICATION == 1}"><a href="#" onclick="view('${list.DOCUMENTNO }','2')"><font color="red">&nbsp;查看</font></a></c:if>
				
				</td>
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;${list.PLANCOMPLETIONDATE }</td>
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;${list.OVERDATE }</td>	
				<td nowrap="nowrap" align="center"  style="color: #666666;"> 
				<c:choose>
			
				<c:when test="${list.ISRECTIFICATION != 0 && list.ISCOMPLETEDRECTIFICATION == 1}">
				<a href="/safeControlServlet?operation=securityChecks&content=viewCorrectivePlan_plan&menu_code=${param.menu_code}&securityChecksNo=${list.DOCUMENTNO }" style="color:red;">&nbsp;详情</a>
				</c:when>
				<c:when test="${list.ISCOMPLETEDRECTIFICATION == 1 && list.ISRECTIFICATION == 0}">
				
				<a href="/safeControlServlet?operation=securityChecks&content=viewCorrectivePlan&menu_code=${param.menu_code}&securityChecksNo=${list.DOCUMENTNO }" style="color:red;">&nbsp;详情</a> 
				</c:when>
				<c:otherwise>
				<font color="#00CC00">&nbsp;暂无详情&nbsp;</font>
				</c:otherwise>
				</c:choose>
				</td>	
				
		    	<td nowrap="nowrap" align="center"  style="color: #666666;">	
		    	<c:if test="${list.ISRECTIFICATION == 1}"> <font color="#00CC00">&nbsp;确认完成&nbsp;</font></c:if>
		    	<c:if test="${list.ISRECTIFICATION == 2}"><font color="#990099">&nbsp;退回&nbsp;</font></c:if>
		    	
		    	<c:if test="${list.ISCOMPLETEDRECTIFICATION == 0 && list.ISPLAN == 1}">
		    	<font color="#990099">&nbsp;计划反馈&nbsp;</font></c:if>
		    	<c:if test="${list.ISCOMPLETEDRECTIFICATION == 1 && list.ISRECTIFICATION == 0}">
		    	<font color="#990099">&nbsp;整改反馈&nbsp;</font></c:if>
		    	<c:if test="${list.ISRECTIFICATION == 3}">
		    	<font color="#990099">&nbsp;未确认&nbsp;
		    	<a href="#" onclick="OKAndNO('${list.DOCUMENTNO }','1')" style="color:red;">&nbsp;通过&nbsp;</a>
		    	<a href="#" onclick="OKAndNO('${list.DOCUMENTNO }','2')" style="color:red;">&nbsp;退回&nbsp;</a>
				</c:if>
				</td>
				<td nowrap="nowrap"  align="center" class="info_content_06">
				<textarea name="affirmorIdea_${list.DOCUMENTNO }" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${list.CONFIRMIDEA }</textarea></td>
				
				<td nowrap="nowrap" align="center"  style="color: #666666;">&nbsp;
				<c:if test="${toolbarInfo.insertMenu == 1}">
				<a href="#" style="color:red;" onclick="tixing('${list.DOCUMENTNO }')">
				发送邮件(${list.MAIL_NUM })</a>
				<input type="hidden" name="mail_num" value="${list.MAIL_NUM }">
				</c:if></td>
		    </tr>
		   </c:forEach>	
		    <input type="hidden" name="currentPage" value="${currentPage}">
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/safeControlServlet"
		               parameters="&operation=securityChecks&content=securityChecksView&menu_code=${param.menu_code}&documentno=${documentno}&StartDate=${StartDate}&{EndDate=${EndDate}&serchDept=${serchDept}&HiddenDangersCommunications=${HiddenDangersCommunications}&HiddenDangersType=${HiddenDangersType}&Rectification_requirements=${Rectification_requirements}&status_code=${status_code}" 
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
<ait:xjos />
</body>

</html>
