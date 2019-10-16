<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="maintainType" class="java.lang.String" scope="request" />
<jsp:useBean id="loginAdmin" class="java.lang.String" scope="request"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>设施维修决裁</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ScenePhotos(photosid){
photosid = photosid+"1";
//window.open("/ajaxControlServlet?operation=uploadImp&correctiveplan=correctiveplan&documentno="+photosid,"","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
 function search(){
 document.form1.action="/gaControlServlet?menu_code=ga0216&operation=faEnvironment&content=facilitiesMaintenanceAAffirm";
 document.form1.submit(); 
 }

 
 function visiterApprovalOKorNO(applyno,maxLevelFlag,flag){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesSecurityEnvironment&content=facilitiesCrfongingAffirm&flag="+flag+"&applyno="+applyno+"&maxLevelFlag="+maxLevelFlag;
	document.form1.submit();
}

 function checkAll()
	{
	    var selected = document.form1.ck_all.value == "0" ? true : false;
	    var countsArr = document.getElementsByName("counts") ;
	  	for (var i = 0; i< countsArr.length ; i++){
			countsArr[i].checked = selected ;
		
		}
	    document.form1.ck_all.value = selected ? "1" : "0";
}
//批量通過  或 否決
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

		   if (confirm("你确定批量决裁吗？")) {	   
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesSecurityEnvironmentBatch&content=batchfacilityAffirm&affirmFlag=1";
		   document.form1.submit();
		   }else{
				return false;
			   }
	   }
	    
	 }
	 
function doReject(){
	   if(confirm("确认对已选择的信息进行否决吗？")){  
		   var selectFlag=false;
		   var countsArr=document.getElementsByName("counts");

		   for(var i=0;i<countsArr.length;i++){
			      if(countsArr[i].checked){
			       selectFlag=true;
			      }   
			   }
		   if(!selectFlag){
		          alert("请选择要否决的信息！");
				   return;
		   }else{	
			   if (confirm("你确定批量否决吗？")) {
				  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesSecurityEnvironmentBatch&content=batchfacilityAffirm&affirmFlag=2";
				  document.form1.submit();
			   }else{
					return false;
				   }
		  }
	  }
	}

function sendMailAll(){//按条件发送
	if (document.form1.empId.value == 0 || document.form1.empId.value == "" || document.form1.empId.value == null)
	{
		alert("请选择接收者 ！！！") ;
		return ;
	}

	var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要修改的信息！");
			   return;
	   }else if (confirm("是否按当前的选择条件，发送邮件!!!")) {
	  document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesSecurityEnvironmentBatch&content=sendEmailFacilities";
	  document.form1.submit();
	}
	
}

var time=null;
function SearchContent(condition,id){	
	
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchC(condition,id);
					},500);  
}

function SearchC(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployeeSysAdmin&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		
		
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layeri = $('iemp');
			
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		layeri.style.top = iBtop+iBheight+6;
		layeri.style.left = iBleft;
		  
		new Ajax.Request(
           url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}

function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
    	layeri.style.height = 48;
		layer.style.height = 48; 
    }else if(size<210){
		layeri.style.height = size;
		layer.style.height = size;  
    }else{
    	layeri.style.height = 210;
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace('*','&');
    layer.style.visibility = 'visible';
	layeri.style.visibility = 'visible';
}
function updateValue(cell) {
	$('empId').value=cell.childNodes[0].firstChild.nodeValue;
	$('chinesenamewd').value=cell.childNodes[1].firstChild.nodeValue;
	$('personId').value=cell.childNodes[2].firstChild.nodeValue;
	layerClose();
}
function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function maintainChange(){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=faEnvironment&content=maintainChange";
	document.form1.submit();	
}  

</SCRIPT>

<body>

<form name="form1" method="post" action=""><input type="hidden"
	name="ck_all" value="0" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%--<%@ include file="inc/gatoolbar_none.jsp"%>  --%> <%@ include
			file="inc/gatoolbar_facility_affirm.jsp"%></td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info -->
		<table width="100%" height="30" border="0" cellspacing="1"
			cellpadding="0">
			<tr>
				<td class="title1"><!-- 查询条件 --> <ait:message
					messageID="display.mutual.search_criteria" module="ess"></ait:message>
				</td>
			</tr>
			<tr>
				<td colspan="9">
				<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
					<tr>
						<td  class="info_title_01">法人区分</td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
				         </td> 											
						<td class="info_title_01" nowrap="nowrap"><!-- 部门 --> <ait:message
							messageID="display.mutual.department" module="ess"></ait:message>
						</td>
						<td class="info_content_00" nowrap="nowrap"><ait:selDeptByCpnyId
							name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}" />
						</td>
						<td class="info_title_01" nowrap="nowrap"><!--  状态--> <ait:message
							messageID="display.mutual.status" module="ess"></ait:message></td>
						<td class="info_content_00" nowrap="nowrap"><select
							name="qryType">
							<option value="3" <c:if test="${qryType==3}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${qryType==0}">selected</c:if>>未决裁</option>
							<option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
							<option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>
						</select>
						<ait:image
							src="/images/btn/Search.gif" align="absmiddle"
							onclick="javascript:search();" style="cursor:hand" /></td>
					</tr>
					<tr>
				         <td class="info_title_01" nowrap="nowrap"><!-- 开始日期 --> 故障时间
						</td>
						<td class="info_content_00" nowrap="nowrap"><input
							type="text" name="createDate" style="width: 70px"
							value="${createDate}" readonly onClick="setday(this);" /></td>	
						<td class="info_title_01" nowrap="nowrap"><!-- 关键字 --> <ait:message
							messageID="display.mutual.key_word" module="ess"></ait:message></td>
						<td class="info_content_00" nowrap="nowrap"><input
							type="text" name="key" value="${key}" title="输入姓名或者职号" /></td>
						<td class="info_title_01">条件</td>
						<td width="10%" class="info_content_00"><!-- 请输入姓名查找 --> <input
							id="empId" name="empId" size="8" value="${empId}"
							onkeyup="SearchContent(this.value,this.id);" /> <input
							type="hidden" id="personId" name="personId" size="8"
							value="${personId}" />
							<input
							type="hidden" id="chinesenamewd" name="chinesenamewd" size="8"
							value="${chinesenamewd}" />
							</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu applerId --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">设施维修决裁</td>
				<td align="right" class="title1" colspan="5">申请类型：
				<select name="applyType" onchange="maintainChange()">
					<option value="2" <%if (maintainType.equals("2")) {%> selected <%}%>>设施维修申请</option>
					<option value="1" <%if (maintainType.equals("1")) {%> selected <%}%>>叉车维修申请</option>
				</select>
				</td>
			</tr>
		</table>
		<%
			if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
			}
		%>
<c:choose>
		<c:when test="${maintainType == '1'}">
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">


			<tr align="center" bgcolor="#F5F5F5">

				<td nowrap="nowrap" class="info_title_01"><a href="#"
					onclick="checkAll();" style="color: red;"><ait:message
					messageID="display.mutual.select_2" module="ess" /></a></td>

				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">车号</td>
				<td nowrap="nowrap" class="info_title_01">里程数</td>
				<td nowrap="nowrap" class="info_title_01">故障类型</td>
				<td nowrap="nowrap" class="info_title_01">故障时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详细内容</td>
				
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
				<%--   <td nowrap="nowrap" class="info_title_01">
				意见</td>		--%>
			</tr>
			<c:forEach items="${applyList}" var="varTest" varStatus="i">
				<tr align="center">

					<!-- 申請編號 -->
					<td nowrap="nowrap" align="center" class="info_content_01"><c:if
						test="${varTest.ACTIVE==0 || varTest.ACTIVE==1}">
						<input type="checkbox" name="counts" value="${i.index}">
					</c:if>&nbsp;</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.FORKLIFTMAINTAINNO}
					</td>
					<input type = "hidden" name="applyNo_${i.index}" value = "${varTest.APPLYNO}"/>
					<input type = "hidden" name = "affirmlevelMax_${i.index}" value = "${varTest.MAX_LEVEL_FLAG}"/>
					<input type = "hidden" name = "APPLYORID_FA_${i.index}" value = "${varTest.APPLYORID}"/>
					<input type="hidden" name="AFFIRM_LEVEL_${i.index}" value="${varTest.AFFIRM_LEVEL}">	
					<input type="hidden" name="applyer_${i.index}" value="${varTest.APPLYORID}">
					<input type="hidden" name="crfno_${i.index}" value="${varTest.CRFNOTE}">
					<input type="hidden" name="chinesname_${i.index}" value="${varTest.CHINESENAME}">
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.CHINESENAME}<input
						type="hidden" name="applyorname_${i.index}"
						value="${varTest.CHINESENAME}"></td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.DEPTNAME}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.CARNUM}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.MILEAGE}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.FAILURETYPE}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.FAULTDATE}
					<input type = "hidden" name="faultDate_fa_${i.index}" value ="${varTest.FAULTDATE}"/>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.DETAILSA}
					<input type="hidden" name = "detailSa_fa_${i.index}" value = "${varTest.DETAILSA}"/>
					</td>
					
					<td nowrap="nowrap" class="info_content_01"><c:forEach
						items="${varTest.affirmorList}" var="view" varStatus="j">
						<c:if test="${view.AFFIRMOR_ID != admin.adminID}">
							<font color="#990099">${view.AFFIRM_LEVEL
							}${view.CHINESENAME }</font>
							<c:if test="${view.AFFIRM_FLAG==0}">
								<font color="#990099">未决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==1}">
								<font color="#00CC00">已决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==2}">
								<font color="#00CC00">已否决</font>
							</c:if>
							<br>
						</c:if>

						<c:if test="${view.AFFIRMOR_ID == admin.adminID}">
							<input type="hidden" name="affirmId_Level_${i.index}"
								value="${view.AFFIRM_LEVEL}">
							<input type="hidden" name="affirmNo_${varTest.APPLYNO}"
								value="${view.GA_AFFIRM_NO}">
							<font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
							<c:if test="${view.AFFIRM_FLAG==0}">
								<font color="#990099">未决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==1}">
								<font color="#00CC00">已决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==2}">
								<font color="#00CC00">已否决</font>
							</c:if>
							<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">|
						      		<a href="#"
									onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','${varTest.MAX_LEVEL_FLAG}','1')"><font
									color="red">通过</font></a>
							</c:if>
							<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">
								<a href="#"
									onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','${varTest.MAX_LEVEL_FLAG}','2')"><font
									color="red">否决</font></a>
							</c:if>
							<br>
						</c:if>

					</c:forEach></td>
				</tr>
			</c:forEach>
			<input type="hidden" name="currentPage" value="${currentPage}">
		</table>
</c:when>
		<c:when test="${maintainType == '2'}">
		
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">


			<tr align="center" bgcolor="#F5F5F5">

				<td nowrap="nowrap" class="info_title_01"><a href="#"
					onclick="checkAll();" style="color: red;"><ait:message
					messageID="display.mutual.select_2" module="ess" /></a></td>

				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">分类</td>
				<td nowrap="nowrap" class="info_title_01">位置</td>

				<td nowrap="nowrap" class="info_title_01">故障时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详情</td>
				<td nowrap="nowrap" class="info_title_01">要求维修内容</td>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
				<%--   <td nowrap="nowrap" class="info_title_01">
				意见</td>		--%>
			</tr>
			<c:forEach items="${applyList}" var="varTest" varStatus="i">
				<tr align="center">
				<input type = "hidden" name="applyNo_${i.index}" value = "${varTest.APPLYNO}"/>
					<input type = "hidden" name = "affirmlevelMax_${i.index}" value = "${varTest.MAX_LEVEL_FLAG}"/>
					<input type = "hidden" name = "APPLYORID_FA_${i.index}" value = "${varTest.APPLYORID}"/>
					<input type="hidden" name="AFFIRM_LEVEL_${i.index}" value="${varTest.AFFIRM_LEVEL}">	
					<input type="hidden" name="applyer_${i.index}" value="${varTest.APPLYORID}">
					<input type="hidden" name="crfno_${i.index}" value="${varTest.CRFNOTE}">
					<input type="hidden" name="chinesname_${i.index}" value="${varTest.CHINESENAME}">
					<input type="hidden" name="applyNo_${i.index}"
						value="${varTest.APPLYNO}">
					<input type="hidden" name="applyer_${i.index}"
						value="${varTest.APPLYORID}">
					<input type="hidden" name="maxAffirmFlag001_${i.index}" value="${varTest.MAX_LEVEL_FLAG}">	
					<input type="hidden" name="AFFIRM_LEVEL001_${i.index}" value="${varTest.AFFIRM_LEVEL}">	
					<!-- 申請者 -->
					<input type="hidden" name="applerId_${varTest.APPLYNO}"
						value="${varTest.APPLYORID}">
						
						<!-- 姓名 -->
					<input type="hidden" name="applerIdName_${varTest.APPLYNO}"
						value="${varTest.CHINESENAME}">
						
					<input type="hidden" name="applerIdName003_${i.index}"
						value="${varTest.CHINESENAME}">
						
					<input type="hidden" name="documentno_${varTest.APPLYNO}"
						value="${varTest.SECURITYCHECKSNO}">
					<input type="hidden" name="documentnoCrfno_${varTest.APPLYNO}"
						value="${varTest.CRFNOTE}">
						
					<input type="hidden" name="fcompletionDate_${varTest.APPLYNO}"
						value="${varTest.COMPLETIONDATE}">
					
					<!-- 要求维修内容 -->
					<input type="hidden" name="crfno_${i.index}"
						value="${varTest.CRFNOTE}">
						
					<input type="hidden" name="chinesname_${i.index}"
						value="${varTest.CHINESENAME}">
					<input type="hidden" name="completiondate_${i.index}"
						value="${varTest.COMPLETIONDATE}">
					
					<input type="hidden" name="securitychecksno_${i.index}"
						value="${varTest.SECURITYCHECKSNO}">
					<!-- 申請編號 -->
					<td nowrap="nowrap" align="center" class="info_content_01"><c:if
						test="${varTest.ACTIVE==0 || varTest.ACTIVE==1}">
						<input type="checkbox" name="counts" value="${i.index}">
					</c:if>&nbsp;</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.SECURITYCHECKSNO}

					<%-- <input type="hidden" name="documentno_${i.APPLYNO}" value="${varTest.SECURITYCHECKSNO}" >--%>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.CHINESENAME}<input
						type="hidden" name="applyorname_${varTest.APPLYNO}"
						value="${varTest.CHINESENAME}"></td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.DEPTNAME}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.CLASSIFICATION}</td>
					<!-- 位置 -->
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.PLACE}</td>

					<!-- 整改要求日期 （故障时间）-->
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.COMPLETIONDATE}</td>
					<!-- 故障详情 -->
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.DETAILSA}</td>
					<%-- <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CRFNOTE}&nbsp;<input type="hidden" name="useInformation_${varTest.APPLYNO}" value="${varTest.CRFNOTE}" /></td>--%>
					<%--  <td nowrap="nowrap" align="center" class="info_content_01"><span style="color:red; cursor:pointer;" onClick="ScenePhotos('${varTest.SECURITYCHECKSNO}')">查看整改完成照片</span></td>--%>
					<!-- 要求维修内容 -->
					<td nowrap="nowrap" align="center" class="info_content_01">${varTest.CRFNOTE}</td>
					<td nowrap="nowrap" class="info_content_01"><c:forEach
						items="${varTest.affirmorList}" var="view" varStatus="j">
						<c:if test="${view.AFFIRMOR_ID != admin.adminID}">
							<font color="#990099">${view.AFFIRM_LEVEL
							}${view.CHINESENAME }</font>
							<c:if test="${view.AFFIRM_FLAG==0}">
								<font color="#990099">未决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==1}">
								<font color="#00CC00">已决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==2}">
								<font color="#00CC00">已否决</font>
							</c:if>
							<br>
						</c:if>

						<c:if test="${view.AFFIRMOR_ID == admin.adminID}">
							<input type="hidden" name="affirmNo_${varTest.APPLYNO}"
								value="${view.GA_AFFIRM_NO}">
							<input type="hidden" name="MAX_AFFIRM_FLAG_${varTest.APPLYNO}"
								value="${view.MAX_AFFIRM_FLAG}">
							<input type="hidden" name="AFFIRM_LEVEL_${varTest.APPLYNO}"
								value="${view.AFFIRM_LEVEL}">
							<font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
							<c:if test="${view.AFFIRM_FLAG==0}">
								<font color="#990099">未决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==1}">
								<font color="#00CC00">已决裁</font>
							</c:if>
							<c:if test="${view.AFFIRM_FLAG==2}">
								<font color="#00CC00">已否决</font>
							</c:if>
							<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">|
						      		<a href="#"
									onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','${varTest.MAX_LEVEL_FLAG}','1')"><font
									color="red">通过</font></a>
							</c:if>
							<c:if test="${varTest.ACTIVE==0 && view.AFFIRM_FLAG==0}">
								<a href="#"
									onclick="visiterApprovalOKorNO('${varTest.APPLYNO}','${varTest.MAX_LEVEL_FLAG}','2')"><font
									color="red">否决</font></a>
							</c:if>
							<br>
						</c:if>

					</c:forEach></td>
					<%--   <td nowrap="nowrap"  align="center" class="info_content_06"><textarea name="affirmorIdea_${varTest.APPLYNO}" style=" height: 40px;width:100px " type="_moz" onfocus="this.style.height='50px'" onblur="this.style.height='40px';">${varTest.AFFIRMORIDEA}</textarea></td>--%>
				</tr>
			</c:forEach>
			<input type="hidden" name="currentPage" value="${currentPage}">
		</table>
		
		</c:when>
		</c:choose>
		
		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=faEnvironment&content=facilitiesMaintenanceAAffirm&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}&empId=${empId}&personId=${personId}"
			total="${resultCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" /></td>
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

<iframe id='iemp'
	style="position: absolute; top: 100; width: 370; height: 200; z-index: 1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position: absolute; overflow: auto; top: 100; width: 370; height: 210; z-index: 2; visibility: hidden;">
</div>

</form>

</body>

</html>
