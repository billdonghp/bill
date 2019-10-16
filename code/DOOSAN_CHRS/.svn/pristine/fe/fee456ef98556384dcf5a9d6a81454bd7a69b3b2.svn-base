<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<%@ page import="com.ait.ar.bean.*" %>
<jsp:useBean id="supervisorList" class="java.util.ArrayList" scope="request" /> <!-- after using the search engine,a vector contains all fit employees will be return -->
<jsp:useBean id="supervisorInfoList" class="java.util.ArrayList" scope="request" /> <!-- after using the search engine,a vector contains all fit employees will be return -->
<jsp:useBean id="supervisor" class="com.ait.ar.bean.Supervisor" scope="page"/>
<jsp:useBean id="selectSupervisor" class="com.ait.ar.bean.Supervisor" scope="request"/>
<jsp:useBean id="supervisorInfo" class="com.ait.ar.bean.SupervisorInfo" scope="page"/>
<jsp:useBean id="deptListWithObject" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" /> <!-- the list shows all departments -->
<jsp:useBean id="department" class="com.ait.hrm.bean.Department"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<title>考勤员</title>
</head>
<SCRIPT type="text/javascript">
function Search(){
    document.form1.action="/arControlServlet?operation=ar_pagecontrol&page=ms_d&menu_code=ar0202";
    document.form1.submit();
 }
function selectAll()
{

	for(var i=0;i<document.save.elements.length;i++)
	{
	
		if(save.elements[i].type=="checkbox")
		{
			save.elements[i].checked=true;
		}
	}
}

function Save()
{
var str="";
for(var i=0;i<document.save.elements.length;i++)
{
	if(save.elements[i].type=="checkbox" && save.elements[i].name!="all")
	{
		if(save.elements[i].checked==true)
		{
			str+=save.elements[i].value+"&&";
		}
	}
}

if(str.length<1)
{//"请先选择需要删除的考勤员"
	alert('<ait:message  messageID="alert.att.setting.keeper.delete.delete_keeper" module="ar"/>');
	return;
}
document.save.str.value=str;
document.save.submit();

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
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition);
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
		$('key').value=cell.childNodes[0].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}
</SCRIPT>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

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
		<!-- display basic info -->

		<!-- display 3 level menu -->
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!-- 考勤员-->
					<ait:message  messageID="display.mutual.attendance_keeper"/></td>
			</tr>
		</table>
				<form action="" name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >		
	    <tr>
	      <td colspan="9">
	      <table width="100%" height="30" border="0" cellpadding="0"
				cellspacing="1" class="dr_d">
	      <tr>
		    <td align="center" class="info_content_01" nowrap="nowrap">
			    <!-- 部门 --><ait:message messageID="display.mutual.dept" />&nbsp;:&nbsp; 
			    <ait:selDept name="deptID" selected="${deptID}" all="all" supervisorType="ar"/>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
	        <td align="center" class="info_content_01" nowrap="nowrap">
				 <!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		    <td align="center" class="info_content_01" nowrap="nowrap">
				<ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
		    </td>
	        </tr>
	      </table>	      
	      </td>
		</tr>
		</table>
		</form>
		<form action="/arControlServlet" method="post" name="save">
			<input type="hidden" name="operation" value="ar_delete">
			<input type="hidden" name="content" value="ar_supervisor">
			<input name="empID" value="<%=request.getParameter("empID")%>" type="hidden">
			<input type="hidden" name="menu_code" value="<%=request.getParameter("menu_code")%>">
			<input type="hidden" name="size" value="<%=supervisorList.size()%>">
			<input type="hidden" name="str" value="0">
		    <table width="100%" height="66" border="1" align="default" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		      <tr align="center">
		      	<td>
			  		<input type="checkbox" class="check" name="all" onClick="selectAll()">
			  	</td>
			    <td height="30" class="info_title_01" nowrap="nowrap"><!-- 考勤员号-->
						<ait:message  messageID="display.mutual.emp_id_2"/></td>
			    <td class="info_title_01" nowrap="nowrap"><!-- 中文名-->
						<ait:message  messageID="display.mutual.name"/></td>
			    <td class="info_title_01" nowrap="nowrap"><!-- 课组 -->课组</td>
						<td class="info_title_01" nowrap="nowrap"><!-- 所属部门-->
						<ait:message  messageID="display.mutual.dept"/></td>				
			    <td class="info_title_01" nowrap="nowrap"><!-- 创建日期-->
						<ait:message  messageID="display.mutual.creation_date"/></td>
		      </tr>
		
			<c:forEach items="${supervisorList}" var="supervisorList">
		      <a href='arControlServlet?operation=ar_view&superVisorID=${supervisorList.supervisorId}&content=supervisorInfo&menu_code=<%=request.getParameter("menu_code")%>'>
			      <tr style="cursor:pointer " align="center" onmouseover="this.bgColor='#aaffee';" onmouseout="this.bgColor='#ffffff';">
			        <td>
			        	<input type="checkbox" class="check" name="xz${supervisorList.supervisorId}" value="${supervisorList.supervisorId}">
			       	</td>
							<td align="center" height="30" class="info_content_01">${supervisorList.empId}&nbsp;</td>
			        <td align="center" height="30" class="info_content_01">
				     	<ait:content enContent="${supervisorList.pinyin}" zhContent="${supervisorList.chineseName}" koContent="${supervisorList.korName}"/>
				     &nbsp;</td>
				   <td align="center" class="info_content_01" nowrap="nowrap">${supervisorList.department}&nbsp;</td>
				   <td align="center" class="info_content_01" nowrap="nowrap">${supervisorList.fourthDepartment}&nbsp;</td>				   
				   <td align="center" class="info_content_01" nowrap="nowrap">${supervisorList.createDate}&nbsp;</td>
			      </tr>
		      </a> 
			</c:forEach>
		    </table>
		</form>
		 <!-- Page Navigation Start-->
					<ait:page       
		               link="/arControlServlet"
		               parameters="&operation=ar_pagecontrol&page=ms_d&menu_code=${param.menu_code}&deptID=${deptID}&key=${key}" 
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
<iframe id='iemp' style="position:absolute; top:200; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
</iframe>
<div id="emp_list"  style="position:absolute;overflow:auto; top:200;width:370; height:210; z-index:2;visibility: hidden;">   
</div>
</body>
</html>
