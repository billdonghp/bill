<%@ page contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<jsp:useBean id="documentno" class="java.lang.String" scope="request" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<script src="../js/prototype.js"></script>
<script language="javascript">

function Save(){
	if(document.getElementById("dept").innerHTML == ""){
		alert("受检部门不能空！");
		return;
	}
   if(stardateCheck()){
   alert("检查时间必须小于或者等于整改时间！");
    }else if(document.getElementById("empIDList").innerHTML=="" ||document.getElementById("empIDList").innerHTML==null){
	alert("邮件通知人不能为空！");
	}else if(document.form2.Location.value==""||document.form2.Location.value==null){
	alert("地点不能为空！");
	}else{
	document.form2.action="/safeControlServlet?operation=securityChecks&content=addSecurityChecks&menu_code=${param.menu_code}&person_id="+document.getElementById("person_id").innerHTML+"&deptname="+document.form2.deptname.value+"&falg=1";
	document.form2.submit();
 }
}
 function stardateCheck(){
	var start=document.form2.check_date.value.replace("-","");
	var end=document.form2.Rectification_date.value.replace("-","");

	 if((end >start) || (end==start)){
	 	return false;
	 }else{
	 	return true;
	 }	
 }

function uploadImp(photosid){
window.open("/safe/safe_upload1.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
function ScenePhotos(photosid){
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+photosid,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}
function aaa(){
var deptname = document.form2.deptname.value;
var deptid = document.form2.deptid.value;

var deptname1=deptname.split(",");
var bool = true;
	for(var i=0;i<deptname1.length;i++){
		if(deptid==deptname1[i]){
			bool=false;
		}
	}
	if(bool){
	var name =   document.getElementById("deptid");
	var nameid = name.options[name.selectedIndex].value
	var name1 = name.options[name.selectedIndex].text;
	document.getElementById("dept").innerHTML+="<span id='sss'>"+name1+",";
	document.form2.deptname.value += deptid+","
	}
//	var nameid = document.getElementById("deptid").value;
//	var name =   document.getElementById("deptid");
//	var name1 = name.options[name.selectedIndex].text;
//	document.getElementById("dept").innerHTML="<span id='sss'>"+name1+" <a href='#' onclick='sss()'>删除</a></span>";
}
</script>
</head>
<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
%>
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
		
    <form action="" method="post" name="form2">
    <input name="deptname" type="hidden" />
			<table width="85%" border="0" cellpadding="0" cellspacing="1" >
				<tr>
					<td align="left" class="title1" colspan="10">添加信息</td>
				</tr>
			</table>
		  <table width="85%" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="20%" class="info_title_01">编号</td>
				<td class="info_content_00"><input type="text" name="No_number" value="<%=documentno%>" style="width:100px"></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查人</td>
				<td class="info_content_00"><%=admin.getChineseName()%></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">检查日期</td>
				<td class="info_content_00"><input type="text" name="check_date"  value="<%=today%>" class="content" style="width:80px "  readonly onClick="setday(this);" required></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">受检部门</td>
				<td class="info_content_00"><ait:selDept name="deptid" all="all"  onChange="aaa()"></ait:selDept>
				<div name="dept" id="dept"></div>
				</td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">分类</td>
				<td class="info_content_00"><ait:codeClass name="HiddenDangersType1" codeClass="Danger_Type"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">地点</td>
				<td class="info_content_00"><input type="text" name="Location" value="" style="width:120px" required></td>
			</tr>
				
			<tr>
				<td width="20%" class="info_title_01">隐患及不符合通报</td>
				<td class="info_content_00"><ait:codeClass name="HiddenDangersCommunications1" codeClass="danger_code"/></td>
			</tr>		
			<tr>
				<td width="20%" class="info_title_01">整改要求日期</td>
				<td class="info_content_00"><input type="text" name="Rectification_date" value="<%=today%>" class="content" style="width:80px "  readonly onClick="setday(this);" required></td>
			</tr>	
			<tr>
				<td width="20%" class="info_title_01">整改要求</td>
				<td class="info_content_00"><ait:codeClass name="Rectification_requirements1" codeClass="Reorganizes_code"/></td>
			</tr>
			<tr>
				<td width="20%" class="info_title_01">问题点及不符合项</td>
				<td class="info_content_00"><textarea name="brief" class="content" style="width:400px "></textarea></td>
			</tr>
			<tr>
			 <td width="20%" class="info_title_01">&nbsp;</td>
			 <td class="info_content_00"><a href="#" onclick="uploadImp('<%=documentno%>')" style="color:red" title="上传图片">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="ScenePhotos('<%=documentno%>')" title="查看现场照片">查看照片</span></td>				
            </tr>
			<tr>
				<td width="20%" class="info_title_01">邮件通知</td>
				<td class="info_content_00"><%@ include file="./safe_util_departmentAndemployee.jsp"%></td>
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
			  </td> 
				
			</tr>
			<tr>
			  <td width="20%" class="info_title_01">姓名</td>
			  <td class="info_content_00"><span id="empNameList"></span>
				<c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.CHINESENAME},
			<span style="color:#00CC00; cursor:pointer;" onClick="javaScript:deleteEmployee('${test.PERSON_ID}');"><img src="/images/left_menubullet_sub_m.gif" width="15" height="25" alt="删除" align="absmiddle"></span> ,
			</c:forEach></td> 
			</tr>
			<tr>
			  <td width="20%" class="info_title_01">部门</td>
			  <td class="info_content_00"><span id="deptNameList"></span>
				<c:forEach items="${selectEmpidList}" var="test" varStatus="i">
			
			${test.DEPTNAME},
			
			</c:forEach></td> 
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
