<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.kpa.KpaParamItem"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script src="../js/prototype.js"></script>
<body>

<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_all.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目-->
					<ait:message  messageID="display.pay.maintenance.setting.item_setting" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		  <tr align="center">
		    <%--<td style="width:200px " height="30"  align="center" class="info_content_01">
					<ait:codeClass codeClass="EmpDiffType" name="statTypeCode"  selected="${param.statTypeCode}" onChange="changeStatType()"/>
			</td>
			--%><% 
				KpaParamItem paParamItem = new KpaParamItem();
				Vector vlist = new Vector();
				vlist = paParamItem.getPaParamList();
				request.setAttribute("vlist",vlist);
			%>
		    <td colspan="4" align="center" class="info_content_01" nowrap="nowrap">
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <!-- 员工--><ait:message messageID="display.mutual.emp_no_name" module="pay" />&nbsp;:&nbsp; 
			    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
			    <span id="name"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
			    <input type="hidden" name="searchUrl" value="param_data" />
		    </td>
		  </tr>
		  <tr align="center">
		    <td style="width:200px " height="30" class="info_title_01"><!--工资参数列表-->
					<ait:message  messageID="display.pay.maintenance.setting.pay_list" module="pay"/></td>
		    <td colspan="3" class="info_title_01"><!--工资参数数据-->
					<ait:message  messageID="display.pay.maintenance.setting.data" module="pay"/></td>
		  </tr>
		
		<tr align="center" >
		    <td  align="center" valign="top" class="info_content_01">
		    <%if (vlist.size()!=0){	%>
			    <select name="select" id="param_no" size="20" style="width:200px " onChange="changedata(this.value)">
			      <c:forEach items="${vlist}" var="vlist">
					<option value="${vlist.param_no}">
						<ait:content enContent="${vlist.param_en_name}" zhContent="${vlist.param_name}" koContent="${vlist.param_kor_name}"/>
					</option>
				  </c:forEach>
			   </select>
			<%}%>
			</td>
		    <td colspan="3"  class="info_content_01">
		    <iframe width="100%" height="300" marginwidth="0" marginheight="0" frameborder="0" name="param_date"></iframe>
		    </td>
		  </tr>
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
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#D0D0FF" height="5"></td>
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
<script language="JavaScript" type="text/JavaScript">
<!--
function Add(){
location.href="<%=menu_code%>_a.jsp?menu_code=<%=menu_code%>";
}
function Update(){
	if (document.all("param_no").value!=""){
	location.href="<%=menu_code%>_m.jsp?menu_code=<%=menu_code%>&param_no="+document.all("param_no").value;
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}
function Delete(){
	if (document.all("param_no").value!=""){
	//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
		if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
		location.href="<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&flag=del&param_no="+document.all("param_no").value;
		}
	}
	else{
	//"请选择参数"
		alert('<ait:message  messageID="alert.pay.select_parameter" module="pay"/>');
	}
}

function changedata(para){
    document.all("searchUrl").value = "param_data" ;
	param_date.location.href = "param_data.jsp?param_no="+para;
	}
	
function changeStatType(){
	location.href = "pa0201.jsp?menu_code=<%=menu_code%>" + "&statTypeCode="+document.all("statTypeCode").value ;
	}
	
function Search() {
  var param_no = document.all("param_no").value ;
  var searchUrl = document.all("searchUrl").value ;
  if(param_no == null || param_no.length == 0)
  {
  	//alert("请选择工资参数列表!!!") ;
  	alert('<ait:message messageID="alert.pay.select_wages_parameters" module="pay"/>') ;
  	document.all("param_no").focus() ;
  	return ;
  }
  
  if (searchUrl == "param_data")
  {
  	param_date.location.href = "param_data.jsp?param_no="+ param_no + "&deptid=" + document.all("deptID").value + "&key=" + encodeURI(document.all("key").value) ;
  }	
  if (searchUrl == "param_data_a")
  {
  	param_date.location.href = "param_data_a.jsp?actionType=search&param_no="+ param_no + "&deptid=" + document.all("deptID").value + "&key=" + encodeURI(document.all("key").value) ;
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
//-->
</SCRIPT>