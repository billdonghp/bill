<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ page import="java.util.Vector,com.ait.pa.PaParamItem"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.pa.PaCalc"%>
<%@ page import="com.ait.sy.bean.*"%>
<%@ page import="java.util.*,com.ait.sqlmap.util.SimpleMap,com.ait.utils.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
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
	
	

	String statTypeCode = StringUtil.checkNull(request.getParameter("statTypeCode")) ;
	
	if(statTypeCode.equals("") && admin1.getCpnyId().equals("59000000")){
	 	statTypeCode = "C_12067_1354182";//DICI
	}
	
	String pamonth = request.getParameter("pamonth") ;
	
    java.util.Calendar now = java.util.Calendar.getInstance();
	String year = String.valueOf(now.get(now.YEAR));
	String month = String.valueOf(now.get(now.MONTH)+1);
	month = (month.length() > 1 ? month : ("0" + month) ) ;
	if (pamonth == null || pamonth.length() == 0) {
		pamonth = year + month ;
	}else{
		year = pamonth.substring(0,4);
		month = pamonth.substring(4,6);
	}
	
	PaCalc pacalc = new PaCalc();
	boolean lockStatus = pacalc.getPayLockStatus(pamonth,statTypeCode,admin.getCpnyId()) ;
	
	PaParamItem paParamItem = new PaParamItem();
	Vector vlist = new Vector();
	vlist = paParamItem.getPaParamList(statTypeCode,pamonth);
	request.setAttribute("vlist",vlist);
	
	request.setAttribute("year",year);
	request.setAttribute("month",month);
	
	
	String menu_code = "";
	String rodeLevel = "";
	SimpleMap map = new SimpleMap();
	ArrayList tool_menu = null;
	if (session.getAttribute("admin") == null) {
		response.sendRedirect("/error.jsp");
	}
	menu_code = request.getParameter("menu_code");
	admin = (AdminBean) session.getAttribute("admin");
	rodeLevel = admin.getScreenGrantNo() != null ? admin.getScreenGrantNo() : "";
	ToolMenu toolmenu = new ToolMenu();
	MenuBiz menubiz = new MenuBiz();
	tool_menu = menubiz.toolMenuList(menu_code, rodeLevel);

	int insertMenu = 0;
	int updateMenu = 0;
	int deleteMenu = 0;
	for (int i = 0; i < tool_menu.size(); i++) {
		toolmenu = (ToolMenu) tool_menu.get(i);
		if (toolmenu.getInsertr() == 1) {
			insertMenu = 1;

		}
		if (toolmenu.getUpdate() == 1) {
			updateMenu = 1;

		}
		if (toolmenu.getDelect() == 1) {
			deleteMenu = 1;

		}
	}
	
	map.setInt("insertMenu", insertMenu);
	map.setInt("updateMenu", updateMenu);
	map.setInt("deleteMenu", deleteMenu); 
	request.setAttribute("toolbarInfo", map);
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<ait:history/>
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
		<form name="form1" method="post">
		<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
		<tr>
			<td class="title1"><!--查询条件-->
					<ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td colspan="9">
		      	<table width="100%"  border="0" cellpadding="0" cellspacing="1" class="dr_d">
			       	<tr align="center">
			       		<td width="10%" class="info_title_01"> <!-- 部门 --><ait:message messageID="display.mutual.dept"/></td>
					    <td width="20% align="center" class="info_content_00" nowrap="nowrap">
						    <ait:selDept name="deptID" selected="${deptID}" all="all" supervisorType="pa" />
						</td>
				       	<td width="10%" class="info_title_01"><!-- 员工区分类型 -->员工区分类型</td>
					    <td width="20% height="30"  align="center" class="info_content_00">
								<ait:empDiff name="statTypeCode"  cpnyDiff="<%=admin.getCpnyId()%>" selected="${param.statTypeCode}" onChange="changeStatType()"/>
						</td>
						<td class="info_content_00" colspan="2">   
						    <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle" onclick="Search();" style="cursor:hand" title="查询" enTitle="Search" />
						    <input type="hidden" name="searchUrl" value="param_data" />
					    </td>
				    </tr>
				    <tr>
				    	<td width="10%" class="info_title_01"><!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/></td>
					    <td width="20%" align="center" class="info_content_00" nowrap="nowrap">
						    <input id="key" name="key" size="15" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
						    <span id="name"></span>
						 </td>
					    <td width="10%" class="info_title_01"><!--考勤月-->
								<ait:message  messageID="display.pay.maintenance.setting.month" module="pay"/></td>
					    <td width="20%" align="center" class="info_content_00">
					    <ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" onChange="changemonth()"/>
					    </td>
						<td width="12%" align="center" class="info_content_00" nowrap="nowrap">
					    	<a href="#" onClick="para_init() ;"><!-- 输入项目初始化 --><ait:message  messageID="display.pay.initialization" module="pay"/></a>
					    
						</td>
					    <td width="12%" align="center" class="info_content_00" nowrap="nowrap">
					    <!--<a href="pa_personal.jsp?menu_code=<%=menu_code%>&pamonth=<%=pamonth%>">个人别录入 </a>-->
					    <a onclick=" return checkLockStatus();" href="/hrmControlServlet?operation=destinationCmd&menu_code=<%=menu_code%>&destination=/pa_personal&pamonth=<%=pamonth%>"  >
					    <!-- 个人别录入--><ait:message  messageID="display.pay.maintenance.setting.import" module="pay" /></a>					
						</td>
				  </tr>
			</table>
	      </td>
		</tr>
		</table>
		</form>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		<!-- display content -->
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--输入项目-->
					<ait:message  messageID="display.pay.maintenance.setting.item_setting" module="pay"/></td>
			</tr>
		 </table>
		<table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">		    
		  <tr align="center">
		    <td style="width:200px " height="30" class="info_title_01"><!--工资参数列表-->
					<ait:message  messageID="display.pay.maintenance.setting.pay_list" module="pay"/></td>
		    <td colspan="3" class="info_title_01"><!--工资参数数据-->
					<ait:message  messageID="display.pay.maintenance.setting.data" module="pay"/></td>
		  </tr>
		
		<tr align="center" >
		    <td  align="center" valign="top" class="info_content_01">
		    <%if (vlist.size()!=0){	%>
			    <select name="select" id="configureNO" size="20" style="width:200px " onChange="changedata(this.value)">
			      <c:forEach items="${vlist}" var="vlist">
					<option value="${vlist.configureNO}">
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
<script language="JavaScript" type="text/JavaScript">
/*
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
*/
<!--
function checkLockStatus(){
	var lockStatus = <%= lockStatus %> ;
	
	if (lockStatus){
		alert("工资已锁定!!!") ;
		return false ;
	}
	else{
		return true ;
	}
}

function para_init(){
	// 您是否进行工资月的输入项目初始化
	if (confirm('<ait:message  messageID="display.mutual.pay_month"/>：'+<%=pamonth%>+'\n<ait:message  messageID="alert.pay.initialize_payroll" module="pay"/>')){
				 location.href='<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&flag=inital&pamonth=<%=pamonth%>' + "&statTypeCode="+document.all("statTypeCode").value ;
				 }
	return false ;
}

function changemonth(para){
	location.href = "pa0201.jsp?menu_code=<%=menu_code%>&pamonth="+document.all("year").value+document.all("month").value + "&statTypeCode="+document.all("statTypeCode").value ;
	}

function changedata(para){
    document.all("searchUrl").value = "param_data" ;
	param_date.location.href = "param_data.jsp?configureNo="+para + "&pamonth="+document.all("year").value+document.all("month").value + "&statTypeCode="+document.all("statTypeCode").value ;
	}
	
function changeStatType(){
	location.href = "pa0201.jsp?menu_code=<%=menu_code%>" + "&pamonth="+document.all("year").value+document.all("month").value + "&statTypeCode="+document.all("statTypeCode").value ;
	}
	
function Search() {
  
  if (document.all("configureNO") == null){
  	alert("请进行输入项目初始化!!!") ;
  	return ;
  }
  
  var configureNo = document.all("configureNO").value ;
  var searchUrl = document.all("searchUrl").value ;
  if(configureNo == null || configureNo.length == 0)
  {
  	alert("请选择工资参数列表!!!") ;
  	document.all("configureNO").focus() ;
  	return ;
  }
  
  if (searchUrl == "param_data")
  {
  	param_date.location.href = "param_data.jsp?configureNo="+ configureNo + "&deptid=" + document.all("deptID").value + "&key=" + encodeURI(document.all("key").value) + "&pamonth="+document.all("year").value+document.all("month").value  + "&statTypeCode="+document.all("statTypeCode").value  ;
  }	
  if (searchUrl == "param_data_a")
  {
  	param_date.location.href = "param_data_a.jsp?actionType=search&configureNo="+ configureNo + "&deptid=" + document.all("deptID").value + "&key=" + encodeURI(document.all("key").value) + "&pamonth="+document.all("year").value+document.all("month").value + "&statTypeCode="+document.all("statTypeCode").value ;
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
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
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