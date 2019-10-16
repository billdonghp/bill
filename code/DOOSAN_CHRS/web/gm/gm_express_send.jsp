<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>快件寄送</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
var flag=0;
 function IngSend(){ 
  var appno=document.getElementsByName("selectApplyno");  
  for(var i=0;i<appno.length;i++){  
   if(appno[i].checked){
   flag=1;
   break;
   }   
  } 
	 if(flag==0){
	 alert("请选择要寄送的快件！");
	 }else{
	   document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=expressManger&content=ingSend";
	   document.form1.submit();
	 }
}

function checkAll()
{
    var selected = document.form1.ck_all.value == "0" ? true : false;
    var selectApplyno = document.getElementsByName("selectApplyno") ;
  	for (var i = 0; i< selectApplyno.length ; i++){
		selectApplyno[i].checked = selected ;
	
	}
    document.form1.ck_all.value = selected ? "1" : "0";
}
function Search() {
	//重新搜索时应重置当前页数
	document.form1.action = "/gmControlServlet?menu_code=${param.menu_code}&operation=expressManger&content=expressSend" ;
	document.form1.submit();
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
     	var pars = "operation=hrmSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
		$('sendPerson').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}
</SCRIPT>

<body>
<form name="form1" method="post" action="">
<input type="hidden" name="menu_code" value="gm0401">	
<input type="hidden" name="ck_all" value="0" />	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gm_toolbar_ems.jsp"%>
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
		<br>
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!-- 查询条件 -->
			<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>
			</td>
		</tr>
	    <tr>
	      <td >
	      <table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
	        <tr>
	          <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00"><ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="Search();"/></td> 
	          <td class="info_title_01" nowrap="nowrap"><!-- 申请者部门 -->申请部门</td>
	          <td class="info_content_00"  nowrap="nowrap"><ait:selDeptByCpnyId name="deptID" selected="${deptID}" all="all" cpnyId ="${cpnyId}" supervisorType="pa"/></td>
	          <td class="info_title_01" nowrap="nowrap"><!-- 邮件号 -->快递单号</td>
	          <td class="info_content_00"  nowrap="nowrap"><input type="text" name="postNumber" value="${postNumber}" /></td>
	          <td class="info_title_01" nowrap="nowrap"><!-- 发件人 -->发件人	</td>
	          <td class="info_content_00"  nowrap="nowrap">
	          	<input type="text" id = "sendPerson" name="sendPerson" value="${sendPerson}" onKeyUp="SearchEmp(this.value,this.id)">&nbsp;&nbsp;<span id="name"></span></td>
	          <td class="info_content_00"  nowrap="nowrap">
	          	<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />
	          </td>
	        </tr></table>
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
				快件寄送
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
		       	<ait:message messageID="display.mutual.select_2" module="ess" /></a>
		       </td>  
		      <!--<td nowrap="nowrap" class="info_title_01">
				发件人职号</td>
			   -->
			   <td nowrap="nowrap" class="info_title_01">
				发件人</td>    
		      <td nowrap="nowrap" class="info_title_01">
				申请人部门</td>
			 <!--<td nowrap="nowrap" class="info_title_01">
				申请人职号</td>
			   -->
			   <td nowrap="nowrap" class="info_title_01">
				申请人</td>
			<td nowrap="nowrap" class="info_title_01">
				发件城市</td>	 
			  <td nowrap="nowrap" class="info_title_01">
				寄达城市</td>
		      <td nowrap="nowrap" class="info_title_01">
				收件单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				快件种类</td>
		      <td nowrap="nowrap" class="info_title_01">
				快递单号</td>
		      <td nowrap="nowrap" class="info_title_01">
				邮件内容 </td>
				<td nowrap="nowrap" class="info_title_01">
				发件原因</td>	
			 <td nowrap="nowrap" class="info_title_01">
				收件人 </td>		 	     
		    </tr>
		 <c:forEach items="${expressConfirmInfoList}" var="varTest" varStatus="i">
		    <tr align="center">
		      <td nowrap="nowrap" align="center" class="info_content_01" ><input type="checkbox" name="selectApplyno" value="${varTest.APPLY_NO}" ></td>
		      <!--<td nowrap="nowrap"  align="center" class="info_content_01">${varTest.SENDPERSONEMPID}</td>
		      -->
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.SENDPERSONNAME}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${varTest.DEPTNAME}</td>
		      <!--<td nowrap="nowrap"  align="center" class="info_content_01">${varTest.APPLYEMPEMPID}</td>
		      -->
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.APPLYEMPNAME}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.CITYSENDTO}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CITYARRIVE}</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${varTest.POSTADDRESS}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CODE_NAME} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTNUMBER} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.POSTDESCRIPTION} </td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.CAUSE}</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${varTest.SENDTOPERSON} </td>			      
			 </tr>	
		</c:forEach>	
		<input type="hidden" name="currentPage" value="${currentPage}">	
		 </table>
		 		
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gmControlServlet"
		               parameters="&operation=expressManger&content=expressSend&menu_code=${param.menu_code}&deptID=${deptID}&postNumber=${postNumber}&sendPerson=${sendPerson}" 
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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;visibility: hidden;">   
	</div>
</body>

</html>
