<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<%@ page
	import="com.ait.sqlmap.util.SimpleMap,java.util.*,com.ait.ga.servlet.SecurityEnvironmentCommand,com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="loginAdmin" class="java.lang.String" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"
	scope="request"></jsp:useBean>
<jsp:useBean id="dataMapList" class="com.ait.sqlmap.util.SimpleMap"
	scope="request"></jsp:useBean>
<jsp:useBean id="applyList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="affirmorList" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="maintainType" class="java.lang.String" scope="request" />
<jsp:useBean id="seCommand"
	class="com.ait.ga.servlet.SecurityEnvironmentCommand"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>设施维修情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ScenePhotos(photosid){
photosid = photosid+"1";
//window.open("/ajaxControlServlet?operation=uploadImp&correctiveplan=correctiveplan&documentno="+photosid,"","resizable,scrollbars,dependent,width=500,height=400,left=250,top=300");
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=facilitiesRectificationAffirmInfo";
 document.form1.submit(); 
 }

  function showMemo(val) {
     var memo = $('opition_'+val).innerHTML;
	var html = "<div style='background-color:#FFFFFF;height: 100%;text-align: left;'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 450
				  , height: 200
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,resizable : true
				 ,autoScroll:true
				 ,plain : true	
				 ,title : '决裁意见'
			});
		editDlg.setPosition(400,200);
		editDlg.show();	
} 

  function ExportExcel() {
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=excelfacilitiesview";
		document.form1.submit();
	}

  //维修担当整改完成之后的保存
  function Update1(){
	  var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
			
	    	  if(document.form1["ZHENGAIJIEGU_"+countsArr[i].value].value==null || document.form1["ZHENGAIJIEGU_"+countsArr[i].value].value==""){
			      alert("请填写整改情况内容！！！");
			      document.form1["ZHENGAIJIEGU_"+countsArr[i].value].onfocus();
			      return;
			    }
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要保存的信息！");
			   return;
	   }else{
		   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=updateFacilitiesRectification";
		   document.form1.submit();
		   }
}
  //满意度
  function Update2(){
	  var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
			
	    	  if(document.form1["MANY_"+countsArr[i].value].value==null || document.form1["MANY_"+countsArr[i].value].value==""){
			      alert("请填写内容！！！");
			      document.form1["MANY_"+countsArr[i].value].onfocus();
			      return;
			    }
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要保存的信息！");
			   return;
	   }else{
		   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=updateFacilitiesManYiDu";
		   document.form1.submit();
		   }
}

  //进度保存
  function Update3(){
	  var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
			
	    	  if(document.form1["ZHENGAIJIEGU_"+countsArr[i].value].value==null || document.form1["ZHENGAIJIEGU_"+countsArr[i].value].value==""){
			      alert("请填写内容！！！");
			      document.form1["ZHENGAIJIEGU_"+countsArr[i].value].onfocus();
			      return;
			    }
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要保存的信息！");
			   return;
	   }else{
		   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=updateFacilitiesRectification03";
		   document.form1.submit();
		   }
}
  
function uploadImp(){

	 var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){

	    	  var number=document.form1["APPLYNO_"+countsArr[i].value].value;
//			      document.form1["ZHENGAIJIEGU_"+countsArr[i].value].onfocus();
			     
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要上传的信息！");
			   return;
	   }
	//var number = document.getElementById("document_number").value;
	number = number+"1";
	
	window.open("/safe/safe_upload1.jsp?documentno="+number,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
	}
function ScenePhotos(){

	var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){

	    	  var number=document.form1["APPLYNO_"+countsArr[i].value].value;
//			      document.form1["ZHENGAIJIEGU_"+countsArr[i].value].onfocus();
			     
	       selectFlag=true;
	      }   
	   }
	  if(!selectFlag){
	          alert("请选择要查看的信息！");
			   return;
	   }
	
	//var number = document.getElementById("document_number").value;
	number = number+"1";
	window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+number,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
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

function maintainChange(){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=crMaintainType";
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
		<%@ include file="inc/gatoolbar_facility_none.jsp"%></td>

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
						<td class="info_title_01" nowrap="nowrap"><!-- 开始日期 --> 故障时间
						</td>
						<td class="info_content_00" nowrap="nowrap"><input
							type="text" name="createDate" style="width: 70px"
							readonly onClick="setday(this);" /></td>
						<td class="info_title_01" nowrap="nowrap"><!--  状态--> <ait:message
							messageID="display.mutual.status" module="ess"></ait:message></td>
						<td class="info_content_00" nowrap="nowrap"><select
							name="qryType">
							<option value="3" <c:if test="${qryType==3}">selected</c:if>>全部</option>
							<option value="0" <c:if test="${qryType==0}">selected</c:if>>未决裁</option>
							<option value="1" <c:if test="${qryType==1}">selected</c:if>>已通过</option>
							<option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>
						</select></td>
				        <td  class="info_title_01">法人区分
				          </td>
				         <td  class="info_content_00">
				          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
				         </td>
						<td class="info_title_01" nowrap="nowrap"><!-- 部门 --> <ait:message
							messageID="display.mutual.department" module="ess"></ait:message>
						</td>
						<td class="info_content_00" nowrap="nowrap"><ait:selDeptByCpnyId
							name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}" />
						</td>
					</tr>
					<tr>
						<td class="info_title_01" nowrap="nowrap"><!-- 关键字 --> <ait:message
							messageID="display.mutual.key_word" module="ess"></ait:message></td>
						<td class="info_content_00" nowrap="nowrap"><input
							type="text" name="key" value="${key}" title="输入姓名或者职号" /></td>

						<td class="info_title_01">&nbsp;修改</td>
						<td class="info_content_00"><select
							name="qryTypeOk">
							<option value="0" <c:if test="${qryTypeOk==0}">selected</c:if>>全部</option>
							<option value="1" <c:if test="${qryTypeOk==1}">selected</c:if>>已完成</option>
							<option value="2" <c:if test="${qryTypeOk==2}">selected</c:if>>未完成</option>
						</select>
						</td>
						<td class="info_title_01">&nbsp;设施维修</td>
						<td class="info_content_00"><img
							src="../images/btn/Excel_Exp.gif" style="cursor: pointer;"
							onclick="ExportExcel();" />						
						</td>	
						<td class="info_content_00">
							<font color="red">满意度</font>
						<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" 
          				onclick="javascript:Update2();" style="cursor:hand" title="保存" enTitle="update" />				
						</td>	
						<td class="info_content_00">
						<font color="red">整改进度</font>
						<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" 
          				onclick="javascript:Update3();" style="cursor:hand" title="保存" enTitle="update" />
						</td>

					</tr>
				</table>
				</td>
			</tr>
		</table>
		<!-- display 3 level menu --> <%@ include
			file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">叉车维修情况</td>
				<td align="right" class="title1" colspan="5">申请类型：
				<select name="applyType" onchange="maintainChange()">
					<option value="2" <%if (maintainType.equals("2")) {%> selected <%}%>>设施维修申请</option>
					<option value="1" <%if (maintainType.equals("1")) {%> selected <%}%>>叉车维修申请</option>
				</select>
				</td>
			</tr>
		</table><%--
				<%if (!errorMsg.equals("")) {
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		 --%>
		 
		 <c:choose>
		<c:when test="${maintainType == '1'}">
		 
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01"><a href="#"
					onclick="checkAll();" style="color: red;"><ait:message
					messageID="display.mutual.select_2" module="ess" /></a></td>

				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">申请人</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">车辆所属</td>
				<td nowrap="nowrap" class="info_title_01">车号</td>
				<td nowrap="nowrap" class="info_title_01">里程数</td>

				<td nowrap="nowrap" class="info_title_01">申请时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详情</td>
				<td nowrap="nowrap" class="info_title_01">完成时间</td>
				<td nowrap="nowrap" class="info_title_01">维修长度(h)</td>
				<td nowrap="nowrap" class="info_title_01">整改情况以及结果</td>
				<td nowrap="nowrap" class="info_title_01">使用备件清单</td>
				<td nowrap="nowrap" class="info_title_01">数量</td>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>

				<%-- <td nowrap="nowrap" class="info_title_01">
				确认</td> --%>
				<td nowrap="nowrap" class="info_title_01">满意度</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${applyList}" var="all" varStatus="i">
				<tr align="center">

					<td nowrap="nowrap" align="center" class="info_content_01">
						<input type="checkbox" name="counts" value="${i.index}">
					&nbsp;</td>

					<td nowrap="nowrap" align="center" class="info_content_01"><input
						type="text" id="${all.FORKLIFTMAINTAINNO}" name="document_number"
						value="${all.FORKLIFTMAINTAINNO}" style="width: 85px" readonly></td>
					<input type="hidden" name="applerId_${i.index}"
						value="${all.APPLYORID}">
					<input type="hidden" name="APPLYNO_${i.index}"
						value="${all.APPLYNO}">
					<input type="hidden" name="FAULTDATE_${i.index}"
						value="${all.FAULTDATE}">
						<!-- 故障详情 -->
					<input type="hidden" name="crfnote_${i.index}"
						value="${all.DETAILSA}">
					<input type="hidden" name="chinesname_${i.index}"
						value="${all.CHINESENAME}">
					<input type="hidden" name="documentno_${all.APPLYNO}"
						value="${all.FORKLIFTMAINTAINNO}">
					
					<td nowrap="nowrap" align="center" class="info_content_01">${all.CHINESENAME}<input
						type="hidden" name="applyorname_${i.index}"
						value="${all.CHINESENAME}"></td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.DEPTNAME}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.CARBELONG}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.CARNUM}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.MILEAGE}</td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.FAULTDATE}</td>
					<!-- 故障详情 -->
					<td nowrap="nowrap" align="center" class="info_content_01" >${all.DETAILSA}</td>
					<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="COMPLETIONDATE_${i.index}" class="content" style="width: 70px"  value = '${all.COMPLETIONDATE}'
					readonly onClick="setday(this);"></td>
					
					<td nowrap="nowrap" class="info_content_01">
						<input type="text" name="WORK_H_${i.index}" style="height: 20px; width: 50px" value=${all.WORKH} > 
					</td>
					
					<!-- 整改情况及结果 -->

					<td nowrap="nowrap" class="info_content_01"><textarea
						name="ZHENGAIJIEGU_${i.index}" style="height: 20px; width: 150px" type="_moz"
						onfocus="this.style.height='40px'"
						onblur="this.style.height='20px';">${all.ZHENGAIJIEGU}</textarea></td>
					<td nowrap="nowrap" class="info_content_01"><textarea
						name="CRFNOTE_${i.index}" style="height: 20px; width: 150px" type="_moz"
						onfocus="this.style.height='40px'"
						onblur="this.style.height='20px';">${all.CRFNOTE}</textarea></td>
					<td nowrap="nowrap" class="info_content_01"><textarea
						name="COU_${i.index}" style="height: 20px; width: 50px" type="_moz"
						onfocus="this.style.height='40px'"
						onblur="this.style.height='20px';">${all.COU}</textarea></td>
					<td nowrap="nowrap" class="info_content_01"><c:forEach
						items="${all.affirmorList}" var="view" varStatus="j">
				      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
				      	<c:if test="${view.AFFIRM_FLAG==0}">
							<font color="#990099">未决裁</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==1}">
							<font color="#00CC00">通过</font>
							 <font color="#00CC00">${view.UPDATE_DATE}</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==2}">
							<font color="#00CC00">已否决</font>
							<font color="#00CC00">${view.UPDATE_DATE}</font>
						</c:if>
						<br>
					</c:forEach>
					<font color="#FF0000">维修担当  : ${all.WEIDAN}</font>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_06"><select
						name="MANY_${i.index}"  type="_moz">
							<option value="" <c:if test="${all.MANY==NULL}">selected</c:if>>请选择</option>
							<option value="5" <c:if test="${all.MANY==5}">selected</c:if>>5</option>
							<option value="4" <c:if test="${all.MANY==4}">selected</c:if>>4</option>
							<option value="3" <c:if test="${all.MANY==3}">selected</c:if>>3</option>
							<option value="2" <c:if test="${all.MANY==2}">selected</c:if>>2</option>
							<option value="1" <c:if test="${all.MANY==1}">selected</c:if>>1</option>
						</select></td>
							
					<td nowrap="nowrap" align="center" class="info_content_01"><c:if
						test="${all.AFFIRM_FLAG==0 && all.isfalg == '0'}">
						<a
						<%-- 	href="gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=DelAffirmInfo&SECURITYCHECKSNO=${all.SECURITYCHECKSNO }"--%>
							href="gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=DelAffirmInfoFa&applyType=1&&FORKLIFTMAINTAINNO=${all.FORKLIFTMAINTAINNO}"
							onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font
							color="red">删除</font></a>
					</c:if> <c:if test="${all.AFFIRM_FLAG != 0}">&nbsp;</c:if></td>
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
				<td nowrap="nowrap" class="info_title_01">申请人</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">分类</td>
				<td nowrap="nowrap" class="info_title_01">位置</td>

				<td nowrap="nowrap" class="info_title_01">申请时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详情</td>
				<td nowrap="nowrap" class="info_title_01">要求维修内容</td>
				<td nowrap="nowrap" class="info_title_01">整改情况以及结果</td>
				<td nowrap="nowrap" class="info_title_01">整改照片</td>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>

				<%-- <td nowrap="nowrap" class="info_title_01">
				确认</td> --%>
				<td nowrap="nowrap" class="info_title_01">满意度</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<c:forEach items="${applyList}" var="all" varStatus="i">
				<tr align="center">

					<td nowrap="nowrap" align="center" class="info_content_01">
						<input type="checkbox" name="counts" value="${i.index}">
					&nbsp;</td>

					<td nowrap="nowrap" align="center" class="info_content_01"><input
						type="text" id="${all.SECURITYCHECKSNO}" name="document_number"
						value="${all.SECURITYCHECKSNO}" style="width: 85px" readonly></td>
					<input type="hidden" name="applerId_${i.index}"
						value="${all.APPLYORID}">
					<input type="hidden" name="APPLYNO_${i.index}"
						value="${all.APPLYNO}">
					<input type="hidden" name="COMPLETIONDATE_${i.index}"
						value="${all.COMPLETIONDATE}">
					<input type="hidden" name="crfnote_${i.index}"
						value="${all.CRFNOTE}">
					<input type="hidden" name="chinesname_${i.index}"
						value="${all.CHINESENAME}">
					<input type="hidden" name="documentno_${all.APPLYNO}"
						value="${all.SECURITYCHECKSNO}">
					<td nowrap="nowrap" align="center" class="info_content_01">${all.CHINESENAME}<input
						type="hidden" name="applyorname_${i.index}"
						value="${all.CHINESENAME}"></td>
					<td nowrap="nowrap" align="center" class="info_content_01">${all.DEPTNAME}</td>

					<!-- 分类 -->
					<td nowrap="nowrap" align="center" class="info_content_01">${all.CLASSIFICATION}</td>
					<!-- 位置 -->
					<td nowrap="nowrap" align="center" class="info_content_01">${all.PLACE}</td>

					<!-- 整改要求日期 （故障时间）-->
					<td nowrap="nowrap" align="center" class="info_content_01">${all.COMPLETIONDATE}</td>
					<!-- 故障详情 -->
					<td nowrap="nowrap" align="center" class="info_content_01" style="text-align: left; width: 140">${all.DETAILSA}</td>
					<!-- 要求维修内容 -->
					<td nowrap="nowrap" align="center" class="info_content_01" style="text-align: left; width: 140">${all.CRFNOTE}</td>
					<!-- 整改情况及结果 -->

					<td nowrap="nowrap" class="info_content_01"><textarea
						name="ZHENGAIJIEGU_${i.index}" style="height: 20px; width: 150px" type="_moz"
						onfocus="this.style.height='40px'"
						onblur="this.style.height='20px';">${all.ZHENGAIJIEGU}</textarea></td>


					<td nowrap="nowrap" class="info_content_01"><a href="#"
						onclick="uploadImp()" style="color: red" title="上传图片">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
						style="color: red; cursor: pointer;" onClick="ScenePhotos()"
						title="查看现场照片">查看照片</span></td>
					<td nowrap="nowrap" class="info_content_01"><c:forEach
						items="${all.affirmorList}" var="view" varStatus="j">
				      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
				      	<c:if test="${view.AFFIRM_FLAG==0}">
							<font color="#990099">未决裁</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==1}">
							<font color="#00CC00">通过</font>
						</c:if>
						<c:if test="${view.AFFIRM_FLAG==2}">
							<font color="#00CC00">已否决</font>
						</c:if>
						<br>
					</c:forEach>
					<font color="#FF0000">维修担当  : ${all.WEIDAN}</font>
					</td>
					<td nowrap="nowrap" align="center" class="info_content_06"><select
						name="MANY_${i.index}"  type="_moz" >
							<option value="" <c:if test="${all.MANY==NULL}">selected</c:if>>请选择</option>
							<option value="5" <c:if test="${all.MANY==5}">selected</c:if>>5</option>
							<option value="4" <c:if test="${all.MANY==4}">selected</c:if>>4</option>
							<option value="3" <c:if test="${all.MANY==3}">selected</c:if>>3</option>
							<option value="2" <c:if test="${all.MANY==2}">selected</c:if>>2</option>
							<option value="1" <c:if test="${all.MANY==1}">selected</c:if>>1</option>
						</select></td>

					<td nowrap="nowrap" align="center" class="info_content_01"><c:if
						test="${all.AFFIRM_FLAG==0 && all.isfalg == '0'}">
						<a
						<%-- 	href="gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=DelAffirmInfo&SECURITYCHECKSNO=${all.SECURITYCHECKSNO }"--%>
							href="gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=DelAffirmInfoFa&SECURITYCHECKSNO=${all.SECURITYCHECKSNO }"
							onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font
							color="red">删除</font></a>
					</c:if> <c:if test="${all.AFFIRM_FLAG != 0}">&nbsp;</c:if></td>
				</tr>
			</c:forEach>
			<input type="hidden" name="currentPage" value="${currentPage}">
		</table>
		</c:when>
		</c:choose>
		<!-- Page Navigation Start--> <ait:page link="/gaControlServlet"
			parameters="&operation=facilitiesMaEnvironment&content=facilitiesRectificationAffirmInfo&menu_code=${param.menu_code}&startDate=${startDate}&createDate=${completiondate}&endDate=${endDate}&qryType=${qryType}&qryTypeOk=${qryTypeOk}&deptid=${deptid}&key=${key}&applyType=${maintainType }"
			total="${resultCount}" currentpage="${currentPage}"
			pagesize="${pageSize}" beginlabel="paging_prv10"
			endlabel="paging_next10" prevlabel="paging_prv"
			nextlabel="paging_next" pagegroupsize="${pageGroupsize}"
			useJS="false" />
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