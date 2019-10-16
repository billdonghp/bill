<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="com.ait.ga.servlet.FacilitiesMaintenanceA" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>

<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="documentnoBian" class="java.lang.String"
	scope="request" />
	<jsp:useBean id="frNo" class="java.lang.String"
	scope="request" />
<jsp:useBean id="applyNoFaht" class="java.lang.String" scope="request" />

<%@ page
	import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat"%>
<%@ page
	import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*"%>
<%@ page
	import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil"%>
<jsp:useBean id="affirmList"  class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList"
	scope="page" />
<jsp:useBean id="IsNotExitsCorrectiveplanList"
	class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap" />
<jsp:useBean id="maintainType" class="java.lang.String" scope="request" />
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>设施维修反馈</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function CheckNumberLength(){
 var text=document.form1.CompletedRectification.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>1000){
 return true;
 }else{
 return false;
 }
}

function Save() {
	if(document.form1.applyType.value == "2"){
		
		   if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
			 alert("没有设置决裁者！请设置");
		   }else if(document.form1.document_number.value=="" ||document.form1.document_number.value==null){
		   alert("编号不能为空！");
		   }else if(document.form1.SENDPERSON.value=="" ||document.form1.SENDPERSON.value==null){
		   alert("申请人不能为空！");
		   }else if(CheckNumberLength()){
		   alert("500个汉字以内！");
		   }else{
			   document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=addfacilitiesRectificationApply';
			   document.form1.submit();
			   }
	} else{
		if(document.form1.carType_01.value == "" || document.form1.carType_01.value == null){
			alert("请选择车类型。");
		}else if(document.form1.faultType.value == "" || document.form1.faultType.value == null){
			alert("请选择故障类型。");
		}else if(document.form1.mileage.value == "" || document.form1.mileage.value == null){
			alert("请填写里程数。");
		}else if(document.form1.FAULTDATE.value == "" || document.form1.FAULTDATE.value == null){
			alert("故障时间不能为空。");
		}else if(document.form1.CompletedRectificationdetails.value == "" || document.form1.CompletedRectificationdetails.value == null){
			alert("公章详情不能为空。");
		}else{
			document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=addForkliftApply';
			document.form1.submit();
		}
   }

}


function uploadImp(){
var number = document.form1.document_number.value;
number = number+"1";
if(document.form1.document_number.value=="" ||document.form1.document_number.value==null ){
alert("请点击查看详情！");
}else{
window.open("/safe/safe_upload1.jsp?documentno="+number,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
}
function ScenePhotos(){
var number = document.form1.document_number.value;
number = number+"1";
window.open("/safeControlServlet?operation=securityChecks&content=viewPhoto&menu_code=${param.menu_code}&documentno="+number,"","resizable=no,scrollbars,dependent,width=350,height=100,left=350,top=500");
}

function getDocumentno(documentno,Checkdate,complatedate){
document.form1.document_number.value=documentno;
document.form1.RECTIFICATIONCOMPLETIONDATE.value=Checkdate;
document.form1.overDate.value=complatedate;
}

function deletechange(AffirmorName,AffirmorId,AffirmLevel,ApplyNoFa){
	alert("你确定要删除决裁者吗？");
	document.form1.action='/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=deleteficationaffrim&ApplyNoFa='+ApplyNoFa+'&AffirmLevel='+AffirmLevel+'&AffirmorId='+AffirmorId+'&AffirmorName='+AffirmorName;
	document.form1.submit();
	
}

function maintainChange(){
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaintenanceApp&content=maintainChange";
	document.form1.submit();	
}  

function toggle(id){
		
	var div1=document.getElementById("id1");
	var div2=document.getElementById("id2");
	var div3=document.getElementById("id3");
	
	if(id=="ForkliftNo"){
		div1.style.display = "block";
		div2.style.display = "none";
		div3.style.display = "none";
	}else if(id=="TCarNo"){ 
		div1.style.display = "none";
		div2.style.display = "block";
		div3.style.display = "none";
	}else if(id == "SweepCarNo"){
		div1.style.display = "none";
		div2.style.display = "none";
		div3.style.display = "block";
		}
	}
</SCRIPT>

<body>


<%
	Date d = new Date();
	SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
	String today = timeFormatter.format(d);
%>
<form name="form1" method="post" action=""><input name="today"
	type="hidden" value="<%=today%>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="left"><!-- display basic info --> <!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="5">设施维修反馈</td>
				<td align="right" class="title1" colspan="5">申请类型：
				<select name="applyType" onchange="maintainChange()">
					<option value="2" <%if (maintainType.equals("2")) {%> selected <%}%>>设施维修申请</option>
					<option value="1" <%if (maintainType.equals("1")) {%> selected <%}%>>叉车维修申请</option>
				</select>
				</td>
			</tr>
		</table>
		<br>
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
		%> <input type="hidden" name="isCanSave"
			value="<%=IsNotExitsCorrectiveplanList.size()%>"> 
		<c:choose>
		<c:when test="${maintainType == '1'}">
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">车辆所属</td>
				<td nowrap="nowrap" class="info_title_01">车类型</td>
				<td nowrap="nowrap" class="info_title_01">车号</td>
				<td nowrap="nowrap" class="info_title_01">故障类型</td>
				<td nowrap="nowrap" class="info_title_01">里程数</td>

				<td nowrap="nowrap" class="info_title_01">申请时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详情内容</td>
			
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
				
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="document_number" value="<%= frNo %>"
					style="width: 85px"></td>
 
 				<input name="applyNoFaht"
					type="hidden" value="<%=applyNoFaht%>">
 				
				<td nowrap="nowrap" class="info_content_01"><input
					id="SENDPERSON" name="SENDPERSON" type="hidden"
					value="<%=admin.getAdminID()%>"><%=admin.getChineseName()%></td>
				<input type="hidden" name="ChineseName001"
					value="<%=admin.getChineseName()%>">
				<td nowrap="nowrap" class="info_content_01"><%=admin.getDepartment()%></td>
				<!-- 车辆所属 -->
				<td nowrap="nowrap" class="info_content_01">
					<ait:codeClass name="carBelong" codeClass="carBelong" cpnyId="${cpnyId}"  all="all"/>
				</td>
				<td nowrap="nowrap" class="info_content_01">
					<ait:codeClass name="carType_01" codeClass="carType" cpnyId="${cpnyId}" onChange="toggle(this.value);" all="all"/>
				</td>
				<!-- 车号 -->
				<td nowrap="nowrap" class="info_content_01">
				<div id="id1" style="display:none">
					<ait:codeClass name="ForkliftNo_0" codeClass="ForkliftNo" cpnyId="${cpnyId}"  all="all"/>
		         </div>   
		         <div id="id2" style="display:none">
					<ait:codeClass name="TCarNo_0" codeClass="TCarNo" cpnyId="${cpnyId}"  all="all"/>
		         </div>   
		         <div id="id3">
					<ait:codeClass name="SweepCarNo_0" codeClass="SweepCarNo" cpnyId="${cpnyId}"  all="all"/>
		         </div>  
				</td>
				
				<!-- 分类 -->
				<td class="info_content_01">
				<select name="faultType">
					<option value="" selected >请选择</option>
					<option value="外观">外观</option>
					<option value="动力系统">动力系统</option>
					<option value="液压系统">液压系统</option>
					<option value="电器系统">电器系统</option>
				</select></td>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="mileage" style="width: 85px"></td>

				<!-- 故障时间 -->
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="FAULTDATE" class="content" style="width: 70px" value=""
					readonly onClick="setday(this);"></td>
				<!--  故障详情-->
				<td nowrap="nowrap" class="info_content_01"><textarea
					name="CompletedRectificationdetails"
					style="height: 20px; width: 250px" type="_moz"
					onfocus="this.style.height='40px'"
					onblur="this.style.height='20px';"></textarea></td>
				
				<td nowrap="nowrap">
				<%if(!affirmList.isEmpty()){%>
							      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
							      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

									      <%int affirmLevel=10;
									      for(int i=0;i<affirmList.size();i++){
									      gaAffirmList=(GaAffirmList)affirmList.get(i);
									      
									      if(gaAffirmList.getAffirmorId().equals(admin.getAdminID())){
									    	  continue;
									      }
									      
									      //C_12005_17  课长   仅限课长进行决裁
									      if(!gaAffirmList.getAffirmorDuty().equals("C_12005_17")){
									    	  continue;
									      }
									      
									      %> 
									    <tr>
									      <td>
					
									      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
									      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
										<%
											
										 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
										 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
										 		affirmLevel=gaAffirmList.getAffirmLevel();
										 	}
										
											//if (gaAffirmList.getAffirmLevel()<affirmLevel){
										 %>
									      <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    
										<%
										//	}
										%>	
									      </td>
								      </tr>
								      <%
									      }
										%>	
							      </table> 
				<%
					} else {
				%> <input type="hidden" value="" name="affirmor">
				<table align="center">
					<tr>
						<td><font color="red">没有决裁者</font></td>
					</tr>
				</table>
				<%
					}
				%>
				</td>
			</tr>

		</table>
		</c:when>
		<c:when test="${maintainType == '2'}">
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_title_01">姓名</td>
				<td nowrap="nowrap" class="info_title_01">部门</td>
				<td nowrap="nowrap" class="info_title_01">分类</td>
				<td nowrap="nowrap" class="info_title_01">位置</td>

				<%-- <td nowrap="nowrap" class="info_title_01">
				整改要求日期</td>  --%>
				<td nowrap="nowrap" class="info_title_01">故障时间</td>
				<td nowrap="nowrap" class="info_title_01">故障详情</td>
				<td nowrap="nowrap" class="info_title_01">要求维修内容</td>
				<%--  <td nowrap="nowrap" class="info_title_01">
				整改完成照片</td>	--%>
				<td nowrap="nowrap" class="info_title_01">决裁者</td>
			</tr>
			<tr>
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="document_number" value="<%=documentnoBian%>"
					style="width: 85px"></td>
 
 				<input name="applyNoFaht"
					type="hidden" value="<%=applyNoFaht%>">
 				
				<td nowrap="nowrap" class="info_content_01"><input
					id="SENDPERSON" name="SENDPERSON" type="hidden"
					value="<%=admin.getAdminID()%>"><%=admin.getChineseName()%></td>
				<input type="hidden" name="ChineseName001"
					value="<%=admin.getChineseName()%>">
				<td nowrap="nowrap" class="info_content_01"><%=admin.getDepartment()%></td>
				<!-- 分类 -->
				<td class="info_content_01"><select name="empType">
					<option value="">请选择</option>
					<option value="建筑设施">1.建筑设施</option>
					<option value="公用设施">2.公用设施</option>
				</select></td>
				<!-- 位置 -->
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="location" style="width: 85px"></td>

				<%--   <td nowrap="nowrap" class="info_content_01"><input type="text" name="RECTIFICATIONCOMPLETIONDATE" class="content" style="width:80px "  readonly></td>  --%>
				<!-- 故障时间 -->
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="overDate" class="content" style="width: 70px" value=""
					readonly onClick="setday(this);"></td>
				<!--  故障详情-->
				<td nowrap="nowrap" class="info_content_01"><textarea
					name="CompletedRectificationdetails"
					style="height: 20px; width: 250px" type="_moz"
					onfocus="this.style.height='40px'"
					onblur="this.style.height='20px';"></textarea></td>
				<!-- 要求维修内容 -->
				<td nowrap="nowrap" class="info_content_01"><textarea
					name="CompletedRectification" style="height: 20px; width: 250px"
					type="_moz" onfocus="this.style.height='40px'"
					onblur="this.style.height='20px';"></textarea></td>
				<td nowrap="nowrap">
				<%if(!affirmList.isEmpty()){%>
							      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
							      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

									      <%int affirmLevel=10;
									      for(int i=0;i<affirmList.size();i++){
									      gaAffirmList=(GaAffirmList)affirmList.get(i);
									      
									      if(gaAffirmList.getAffirmorId().equals(admin.getAdminID())){
									    	  continue;
									      }
									      
									      if(gaAffirmList.getAffirmorDuty().equals("C_12005_1331763") ||gaAffirmList.getAffirmorDuty().equals("C_12005_93817")||gaAffirmList.getAffirmorDuty().equals("C_12005_1331764")){
									    	  continue;
									      }
									      
									      %> 
									    <tr>
									      <td>
					
									      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
									      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
										<%
											
										 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
										 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
										 		affirmLevel=gaAffirmList.getAffirmLevel();
										 	}
										
											//if (gaAffirmList.getAffirmLevel()<affirmLevel){
										 %>
									      <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    
										<%
										//	}
										%>	
									      </td>
								      </tr>
								      <%
									      }
										%>	
							      </table> 
				<%
					} else {
				%> <input type="hidden" value="" name="affirmor">
				<table align="center">
					<tr>
						<td><font color="red">没有决裁者</font></td>
					</tr>
				</table>
				<%
					}
				%>
				</td>
			</tr>

		</table>
		</c:when>
		</c:choose>
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
<iframe id='iemp'
	style="position: absolute; top: 100; width: 370; height: 200; z-index: 1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
</iframe>
<div id="emp_list"
	style="position: absolute; overflow: auto; top: 100; width: 370; height: 210; z-index: 2; visibility: hidden;">
</div>
</body>
<ait:xjos />
</html>
