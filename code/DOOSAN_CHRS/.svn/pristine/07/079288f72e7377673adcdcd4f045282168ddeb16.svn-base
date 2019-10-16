<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="securityChecksList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

function CheckNumberlength2(){
 var text=document.form1.brief.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>1000){
 return true;
 }else{
 return false;
 }
}
function uploadImp(photosid){
window.open("/safe/safe_upload1.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
function ScenePhotos(photosid){
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
function Save(){
   if(stardateCheck()){
   alert("检查时间必须小于或者等于整改时间！");
    }else if(document.getElementById("person_id").innerHTML=="" ||document.getElementById("person_id").innerHTML==null){
	alert("邮件通知人不能为空！");
	}else if(CheckNumberlength2()){
	alert("备注，在500汉字以内！");
	}else if(document.form1.Location.value==""||document.form1.Location.value==null){
	alert("地点不能为空！");
	}else{
	document.form1.action="/safeControlServlet?operation=securityChecks&content=updateSecurityChecks&menu_code=${param.menu_code}&person_id="+document.getElementById("person_id").innerHTML+"&falg=1";
	document.form1.submit();
 }
}


 function stardateCheck(){
	var start=document.form1.check_date.value.replace("-","");
	var end=document.form1.Rectification_date.value.replace("-","");

	 if((end >start) ||(end==start)){
	 	return false;
	 }else{
	 	return true;
	 }	
 }
</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr> 
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		<!-- display content -->
		<br>
		
      <form name="form1" method="post" action="">
			<table width="85%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">修改检查记录</td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
          <%for(int i=0;i<securityChecksList.size();i++){ 
          dataMap=(SimpleMap)securityChecksList.get(i);%>
			<tr>
				<td width="20%" class="info_title_01">编号</td>
				<td class="info_content_00"><input type="hidden" name="No_number" value="<%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%>" style="width:80px" readonly><%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查人</td>
				<td class="info_content_00"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查日期</td>
				<td class="info_content_00"><input type="text" name="check_date"  value="<%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSDATE"),"&nbsp;")%>" class="content" style="width:80px "  readonly onClick="setday(this);" required></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">受检部门</td>
				<td class="info_content_00"><ait:selDept name="deptid" supervisorType="hr" selected="<%=StringUtil.checkNull(dataMap.get("DEPTID"),"&nbsp;")%>"></ait:selDept></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">分类</td>
				<td class="info_content_00"><ait:codeClass name="HiddenDangersType1" codeClass="Danger_Type" selected="<%=StringUtil.checkNull(dataMap.get("HIDDENDANGERSTYPE"),"&nbsp;")%>"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">地点</td>
				<td class="info_content_00"><input type="text" name="Location" value="<%=StringUtil.checkNull(dataMap.get("LOCATION"),"&nbsp;")%>" style="width:80px" required></td>
			</tr>
			
			<tr>
				<td width="20%" class="info_title_01">隐患及不符合通报</td>
				<td class="info_content_00"><ait:codeClass name="HiddenDangersCommunications1" codeClass="danger_code" selected="<%=StringUtil.checkNull(dataMap.get("HIDDENDANGERSCOMMUNICATIONS"),"&nbsp;")%>"/></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">整改要求日期</td>
				<td class="info_content_00"><input type="text" name="Rectification_date" value="<%=StringUtil.checkNull(dataMap.get("RECTIFICATION_DATE"),"&nbsp;")%>" class="content" style="width:80px "  readonly onClick="setday(this);" required></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">整改要求</td>
				<td class="info_content_00"><ait:codeClass name="Rectification_requirements1" codeClass="Reorganizes_code" selected="<%=StringUtil.checkNull(dataMap.get("RECTIFICATIONREQUIREMENTS"),"&nbsp;")%>"/></td>
			</tr>	
			<tr>
				<td width="20%" class="info_title_01">问题点及不符合项</td>
				<td class="info_content_00"><textarea name="brief" class="content" style="width:400px "><%=StringUtil.checkNull(dataMap.get("BRIEF"),"&nbsp;")%></textarea></td>
			</tr>   
			<tr>
			 <td width="20%" class="info_title_01">&nbsp;</td>
			 <td class="info_content_00"><a href="#" onclick="uploadImp('<%=dataMap.get("DOCUMENTNO")%>')" style="color:red" title="上传图片">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="ScenePhotos('<%=dataMap.get("DOCUMENTNO")%>')" title="查看现场照片">查看照片</span></td>				
            </tr>
		<%}%>
		    <tr>
				<td width="20%" class="info_title_01">邮件通知</td>
				<td class="info_content_00"><%@ include file="./safe_util_departmentAndemployee.jsp"%></td>
			</tr>
			<tr>
			<td width="20%" class="info_title_01">原邮件通知人</td>
			<td class="info_content_00"><c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.CHINESENAME}(${test.DEPTNAME}),
			
			</c:forEach></td>			
			</tr>
			<tr>
			  <td width="20%" class="info_title_01">职号</td>
			  <td class="info_content_00"><span id="empIDList">
			  <c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.EMPID},
			
			</c:forEach>
			  
			  </span>
			  <span id="person_id" style="display: none;">
			  
			   <c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.PERSON_ID},
			
			</c:forEach>
			  
			  </span>
<!--			  <input type="hidden" name="person_id" value="${selectEmpidLStr }"> -->
			  </td> 
				
			</tr>
			<tr>
			  <td width="20%" class="info_title_01">姓名</td>
			  <td class="info_content_00"><span id="empNameList">
			  
			  <c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.CHINESENAME},
			<span style="color:#00CC00; cursor:pointer;" onClick="javaScript:deleteEmployee('${test.PERSON_ID}');"><img src="/images/left_menubullet_sub_m.gif" width="15" height="25" alt="删除" align="absmiddle"></span> ,
			</c:forEach>
			  
			  </span></td> 
				
			</tr>
			<tr>
			  <td width="20%" class="info_title_01">部门</td>
			  <td class="info_content_00"><span id="deptNameList">
			  
			  <c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.DEPTNAME},
			
			</c:forEach>
			  
			  </span></td> 
				
			</tr>	
		</table>

	</form>
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
<ait:xjos />
</body>

</html>
