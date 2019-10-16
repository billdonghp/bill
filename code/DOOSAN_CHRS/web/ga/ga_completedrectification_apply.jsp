<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<jsp:useBean id="IsNotExitsCorrectiveplanList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>隐患整改反馈</title>
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
   if(document.form1.isCanSave.value==0 || document.form1.isCanSave.value==null){
   alert("没有您部门相关的安全检查记录，不能进行申请！");   
   }else if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
	 alert("没有设置决裁者！请设置");
   }else if(document.form1.document_number.value=="" ||document.form1.document_number.value==null){
   alert("编号不能为空！");
   }else if(document.form1.SENDPERSON.value=="" ||document.form1.SENDPERSON.value==null){
   alert("申请人不能为空！");
   }else if(CheckNumberLength()){
   alert("500个汉字以内！");
   }else if((document.form1.today.value>document.form1.overDate.value)){
   alert("实际完成日期必须大于或者等于系统日期");
   }else{
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=addCompletedRectificationApply&firstaffrim="+document.form1.firstaffirmor0.value;
	document.form1.submit();
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
</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
%>
<form name="form1" method="post" action="">
<input name="today" type="hidden" value="<%=today%>">	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="left">
		<!-- display basic info -->
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				隐患整改反馈
				</td>
			</tr>			     
		</table>
		<br>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		<input type="hidden" name="isCanSave" value="<%=IsNotExitsCorrectiveplanList.size()%>">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" ><tr align="left"><td width="60%">
		<%if(IsNotExitsCorrectiveplanList!=null && IsNotExitsCorrectiveplanList.size()!=0){ %>
		<table width="60%" align="left" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		<tr>
		<td nowrap="nowrap" class="info_title_01">
				编号</td>
	    <td nowrap="nowrap" class="info_title_01">
				检查人</td>   
		<td nowrap="nowrap" class="info_title_01">
				检查日期</td> 
		<td nowrap="nowrap" class="info_title_01">
				地点</td> 
		<td nowrap="nowrap" class="info_title_01">
				备注</td> 		    
		<td nowrap="nowrap" class="info_title_01">
				详情查看</td>
		<td nowrap="nowrap" class="info_title_01">
				是否已申请</td>
		</tr>
		<%for(int i=0;i<IsNotExitsCorrectiveplanList.size();i++){
		 dataMap =(SimpleMap)IsNotExitsCorrectiveplanList.get(i);%>
		<tr>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSDATE"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("LOCATION"),"&nbsp;")%></td>	
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("BRIEF"),"&nbsp;")%></td>	
		<td nowrap="nowrap" align="center"  style="color: #666666;"><a href="/safeControlServlet?operation=securityChecks&content=ShowDetailsForHIDDENDANGERSCOMMUNICATIONS&menu_code=se0301&securityChecksNo=<%=dataMap.get("DOCUMENTNO")%>" style="color:red;" onclick="getDocumentno('<%=dataMap.get("DOCUMENTNO")%>','<%=dataMap.get("RECTIFICATION_DATE")%>','<%=dataMap.get("PLANCOMPLETIONDATE")%>')">查看详情</a></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"> <%= dataMap.get("ISCOMPLETEDRECTIFICATION").toString().equals("0")? "否":"是" %> </td>	
		</tr>
		<%} %>
		</table>
		<%}%>
		</td>
		<td colspan="340">&nbsp;</td>
		</tr>		
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改要求日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				实际完成日期</td>				
			  <td nowrap="nowrap" class="info_title_01">
				整改情况及结果（少于500个汉字）</td>
		      <td nowrap="nowrap" class="info_title_01">
				整改完成照片</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁者</td>		
		    </tr>			
		    <tr>
		      <td nowrap="nowrap" class="info_content_01"><input type="text" name="document_number" style="width:85px" readonly></td>
		      <td nowrap="nowrap" class="info_content_01"><input id="SENDPERSON" name="SENDPERSON" type="hidden" value="<%=admin.getAdminID() %>"><%=admin.getChineseName()%></td>
		      <td nowrap="nowrap" class="info_content_01"><%=admin.getDepartment()%></td>
		      <td nowrap="nowrap" class="info_content_01"><input type="text" name="RECTIFICATIONCOMPLETIONDATE" class="content" style="width:80px "  readonly></td>
		      <td nowrap="nowrap" class="info_content_01"><input type="text" name="overDate" class="content" style="width:70px "  value="" readonly onClick="setday(this);"></td>
		      <td nowrap="nowrap" class="info_content_01"><textarea name="CompletedRectification" style=" height: 20px;width:380px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';"></textarea></td>
<!--             <td nowrap="nowrap" class="info_content_01"><span style="color:red; cursor:pointer;width:60px" onclick="uploadImp()" title="上传图片">上传图片</span><span style="color:red; cursor:pointer;width:60px" onClick="ScenePhotos()" title="查看现场照片">查看照片</span></td>-->
		     
		     <td nowrap="nowrap" class="info_content_01"><a href="#" onclick="uploadImp()" style="color:red" title="上传图片">上传图片</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="ScenePhotos()" title="查看现场照片">查看照片</span></td>
		     
		     
		      <td nowrap="nowrap">
		      <% GaAffirm  ga = new GaAffirm();
		      EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		      List list =ga.getAffirmor(admin.getAdminID(),"SecurityComplateApply",essSysparam.isContainsOwner());%>
		      <%if(!list.isEmpty()){%>
		      <input type="hidden" value="<%=list.size()%>" name="affirmor">
		      <table align="center"> 
		      <%for(int i=0;i<list.size();i++){
		      gaAffirmList=(GaAffirmList)list.get(i);%>      
		      <tr><td><input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="firstaffirmor<%=i %>"><font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font></td><td><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font></td></tr>		      
		      <%} %>
		      </table>
		      <%}else{ %>
		      <input type="hidden" value="" name="affirmor">
		      <table align="center">
		      <tr><td><font color="red">没有决裁者</font></td></tr>		      
		      </table>
		      <% }%>		      
		      </td>	         
		    </tr>		  
		
		 </table>
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;visibility: hidden;">   
	</div>
</body>
<ait:xjos />
</html>
