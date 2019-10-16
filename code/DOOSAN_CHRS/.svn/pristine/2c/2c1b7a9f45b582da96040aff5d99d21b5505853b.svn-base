<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" /> 
<%@ page import="com.ait.utils.FormUtil,com.ibm.icu.text.SimpleDateFormat" %>
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date" %>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.util.StringUtil" %>
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" /> 
<jsp:useBean id="IsNotExitsCorrectiveplanList" class="java.util.ArrayList" scope="request"></jsp:useBean>
<jsp:useBean id="dataMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="essSysparam" class="com.ait.sysparam.EssSysparam"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>整改计划通报</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function CheckNumberLength(){
 var text=document.form1.Correctiveplan.value.replace(/[^\x00-\xff]/g,"**");
 if(text.length>1000){
 return true;
 }else{
 return false;
 }
}
var  empidpatrn=/^[A-Za-z]{2}[0-9]{7}$/;
function Save() {
   if(document.form1.isCanSave.value==0 || document.form1.isCanSave.value==null){
   alert("没有您部门相关的安全检查记录，不能进行申请！");   
   }else if(document.form1.affirmor.value==""||document.form1.affirmor.value==null){
	 alert("没有设置决裁者！请设置");
   }else if(document.form1.document_number.value=="" ||document.form1.document_number.value==null){
   alert("编号不能为空！");
   }else if(document.form1.SENDPERSON.value=="" ||document.form1.SENDPERSON.value==null){
   alert("申请人不能为空！");
   }else if(document.form1.RECTIFICATIONCOMPLETIONDATE.value=="" ||document.form1.RECTIFICATIONCOMPLETIONDATE.value==null){   
   alert("整改完成日期不能为空！");
   }else if(CheckNumberLength()){
   alert("500个汉字以内！");
   }else if(!empidpatrn.test(document.form1.SENDPERSON.value)){
     alert("请输入正确的职号！");
     document.form1.SENDPERSON.focus();
   }else{
//	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=andSecurityEnvironmentApply&firstaffrim="+document.form1.firstaffirmor0.value;
	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=securityEnvironment&content=andSecurityEnvironmentApply";
	document.form1.submit();
   }

}

var time=null;
function SearchEmp(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
					//	alert(condition);
						SearchE(condition,id);
					},500);  
}

function SearchE(condition,id){
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

function layerClose(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('SENDPERSON').value=cell.childNodes[0].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}

function getDocumentno(documentno,Checkdate){
document.form1.document_number.value=documentno;
document.form1.RECTIFICATIONCOMPLETIONDATE.value=Checkdate;
}
</SCRIPT>

<body>
<%Date d = new Date();
SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd");
String today=timeFormatter.format(d);
%>
<form name="form1" method="post" action="">
	
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
		<td valign="TOP" align="CENTER">
		<!-- display basic info -->
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				整改计划通报
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1" ><tr align="left"><td width="15%">
		<%if(IsNotExitsCorrectiveplanList!=null && IsNotExitsCorrectiveplanList.size()!=0){ %>
		<table width="40%" align="center" border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
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
				详情查看</td>
		</tr>
		<%for(int i=0;i<IsNotExitsCorrectiveplanList.size();i++){
		 dataMap =(SimpleMap)IsNotExitsCorrectiveplanList.get(i);%>
		<tr>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("DOCUMENTNO"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("CHINESENAME"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("SECURITYCHECKSDATE"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><%=StringUtil.checkNull(dataMap.get("LOCATION"),"&nbsp;")%></td>
		<td nowrap="nowrap" align="center"  style="color: #666666;"><a href="/safeControlServlet?operation=securityChecks&content=ShowDetailsForHIDDENDANGERSCOMMUNICATIONS&menu_code=se0301&securityChecksNo=<%=dataMap.get("DOCUMENTNO")%>" style="color:red;" onclick="getDocumentno('<%=dataMap.get("DOCUMENTNO")%>','<%=dataMap.get("RECTIFICATION_DATE")%>')">查看详情</a></td>	
		</tr>
		<%} %>
		</table>
		<%}%>
		</td>
		<td colspan="400">&nbsp;</td>
		</tr>		
		</table>
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		       <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01">
				申请人</td>
		      <td nowrap="nowrap" class="info_title_01">
				部门</td>
		      <td nowrap="nowrap" class="info_title_01">
				要求完成日期</td>
		      <td nowrap="nowrap" class="info_title_01">
				计划完成日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				整改计划（500个汉字以内）</td>			 
			 <td nowrap="nowrap" class="info_title_01">
				决裁者</td>		
		    </tr>			
		    <tr>
		      <td nowrap="nowrap" class="info_content_01" ><input type="text" name="document_number" style="width:85px" readonly></td>
		      <td nowrap="nowrap" class="info_content_01">
		      <input id="person_id" name="person_id" type="hidden" value="">
		      <input id="SENDPERSON" name="SENDPERSON" type="text" size="6" value="${key}" onKeyUp="SearchEmp(this.value,this.id)"><span id="name"></span></td>
		      <td nowrap="nowrap" class="info_content_01" ><%=admin.getDepartment()%></td>
              <td nowrap="nowrap" class="info_content_01"><input name="RECTIFICATIONCOMPLETIONDATE" type="text" value="" style="width:70px"  readonly></td>
              <td nowrap="nowrap" class="info_content_01"><input name="PLANCOMPLETIONDATE" type="text" value="<%=today%>" style="width:70px" readonly onClick="setday(this);"></td>
		      <td nowrap="nowrap" class="info_content_01"><textarea name="Correctiveplan" style=" height: 20px;width:500px " type="_moz"  onfocus="this.style.height='40px'" onblur="this.style.height='20px';"></textarea></td>
		      <td nowrap="nowrap" >
		      <% GaAffirm  ga = new GaAffirm();
		      List list =ga.getAffirmor1(admin.getAdminID(),"SecurityComplateApply" ,essSysparam.isContainsOwner());%>
		      <%if(!list.isEmpty()){%>
			      <input type="hidden" value="<%=list.size()%>" name="affirmor">
			      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

				      <%int affirmLevel=10;
				      for(int i=0;i<list.size();i++){
				      gaAffirmList=(GaAffirmList)list.get(i);%> 
				    <tr>
				      <td>

				      <font color="#990099"><%=gaAffirmList.getAffirmLevel()%></font>
				      <input type="hidden" value="<%=gaAffirmList.getAffirmorId()%>" name="affirmorId"><font color="#990099"><%=gaAffirmList.getAffirmorName()%></font>
					<%
					 	if (gaAffirmList.getAffirmorDuty()!=null && (gaAffirmList.getAffirmorDuty().equals("C_12005_93775") || 
					 			gaAffirmList.getAffirmorDuty().equals("C_12005_43") || gaAffirmList.getAffirmorDuty().equals("C_12005_1330060"))) {
					 		affirmLevel=gaAffirmList.getAffirmLevel();
					 	}
					
						if (gaAffirmList.getAffirmLevel()<affirmLevel){
					 %>
				      <img src="../images/btn/Delete_little.gif" title="删除" onclick='affirmorlist.deleteRow(this.parentNode.parentNode.rowIndex);'  align="absmiddle"><br />    
					<%
						}
					%>	
				      </td>
			      </tr>
			      <%
				      }
					%>	
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
