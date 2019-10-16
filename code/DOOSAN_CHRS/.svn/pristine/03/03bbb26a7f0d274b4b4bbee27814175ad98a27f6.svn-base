<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript"> 
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
    
	layer.innerHTML=XmlHttpRequest.responseText;
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
function Add(){
 document.form1.action="/paControlServlet?operation=probationcmd&content=probationAdd&menu_code=pa0507"
 document.form1.submit();

}
function Search(){
 document.form1.action="/paControlServlet?operation=probationcmd&content=probationSearch&menu_code=pa0507"
 document.form1.submit();

}
function Delete(){
 document.form1.action="/paControlServlet?operation=probationcmd&content=probationDelete&menu_code=pa0507"
 document.form1.submit();
}
function updateARHistoryData(){
 document.form1.action="/paControlServlet?operation=probationcmd&content=updateARHistoryData&menu_code=pa0507"
 document.form1.submit();
}

</SCRIPT>
</head>
<body>

<%
    String pamonth = request.getParameter("pamonth");
	
    java.util.Calendar now = java.util.Calendar.getInstance();
	int year = now.get(now.YEAR);
	int month = now.get(now.MONTH)+1;
	if (pamonth ==null) {
		pamonth = year+("0"+month).substring(("0"+month).length()-2,("0"+month).length());
	}else{
		year = Integer.parseInt(pamonth.substring(0,4));
		month = Integer.parseInt(pamonth.substring(4,6));
	}


%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../pa/inc/pa_toolbar_balance.jsp"%>
			
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
		<%@ include file="../pa/inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<form name="form1" method="post" action="">
	   <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					试用期考勤修改</td>
			</tr>
		 </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">	
		<tr><td width="*" height="30"  align="center" valign="middle" class="info_title_01">	
		考勤月:&nbsp;<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red; cursor:pointer;" onClick="updateARHistoryData()">更新考勤数据</span>
		</td></tr>
		</table>
	   <br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
					试用期不扣除人员信息</td>
			</tr>
		 </table>	
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
        <tr align="center">
		<td nowrap="nowrap" class="info_title_01">
        <!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    </td>
		<td nowrap="nowrap" class="info_title_01">
		<ait:image src="/images/btn/Add.gif"  border="0" align="absmiddle"	onclick="javascript:Add();" style="cursor:hand" title="添加" enTitle="add" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<ait:image src="/images/btn/Search.gif" align="absmiddle" 	onclick="javascript:Search();" style="cursor:hand" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<ait:image src="/images/btn/Delete.gif"  border="0" align="absmiddle" 	onclick="javascript:Delete();" style="cursor:hand" title="删除" enTitle="delete" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		</tr>	
		</table> 
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01">选择</td>
		<td nowrap="nowrap" class="info_title_01">职号</td>
		<td nowrap="nowrap" class="info_title_01">姓名</td>
		<td nowrap="nowrap" class="info_title_01">部门</td>
		</tr>
         <c:forEach items="${EmloyeeList}" var="test" varStatus="i">
		<tr align="center">
		<td nowrap="nowrap" class="info_title_01"><input type="checkbox" name="empid" value="${test.EMPID}"></td>
		<td nowrap="nowrap" class="info_title_01">${test.EMPID}</td>
		<td nowrap="nowrap" class="info_title_01">${test.CHINESENAME}</td>
		<td nowrap="nowrap" class="info_title_01">${test.DEPTNAME}</td>
		</tr>		
		</c:forEach>	
		</table>     
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	</form>
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
