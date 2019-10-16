<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ include file="../inc/taglibs.jsp"%>  
 <%@ include file="../inc/meta.jsp" %>
 <jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<title>工资邮件</title>
<ait:processBarJs />
<script src="../js/prototype.js"></script>
<script language=JavaScript> 
function Search(){
	document.form1.action="/paControlServlet?operation=viewPaEmailcmd&menu_code=${param.menu_code}&paMonth=" + document.form1.year.value + document.form1.month.value ;
	document.form1.submit();
}

function Save(){

}

function sendMailAll(){//按条件发送
	document.form1.action="/paControlServlet?operation=paEmaiSendCmd&menu_code=${param.menu_code}&sendType=all&paMonth=" + document.form1.year.value + document.form1.month.value ;

	if (document.form1.mailSize.value == 0)
	{
		alert("请先进行查询，再发送邮件!!!") ;
		return ;
	}

	if (confirm("是否按当前的选择条件，发送邮件!!!")) {
		beforeSubmit();
		document.form1.fireSubmit();
		afterSubmit();
	}
	
	
}

function sendMailPart(){//发送
    document.form1.action="/paControlServlet?operation=paEmaiSendCmd&menu_code=${param.menu_code}&sendType=part&paMonth=" + document.form1.year.value + document.form1.month.value ;
   
    var selectC = document.getElementsByName("selectC") ;
	var size = selectC.length ;
	var selectCnt = 0 ;
	
	for(var i = 0 ; i < size ; i ++ )
	{
		if(selectC[i].checked)
		{
			selectCnt++ ;
			break ;
		}
	}

	if(selectCnt == 0)
	{
		alert("请选择要修改的数据!!!") ;
		return ;	
	}
   
    if (confirm("是否按当前选择的人，发送邮件!!!")) {
		beforeSubmit();
		document.form1.fireSubmit();
		afterSubmit();
	}
}

function checkAll()
{
	var selected = document.form1.ck_all.value == "0" ? true : false;
	var selectarg = document.getElementsByName("selectC");
	for(var i=0;i<selectarg.length;i++)
	{    
	    selectarg[i].checked = selected;     
	 
	}
	document.form1.ck_all.value = selected ? "1" : "0";
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
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		$('name').innerHTML = " : (" + cell.childNodes[1].firstChild.nodeValue + ")" ;
		layerClose();
}

</script>             
</head>                                                                                                                                                
<body>     
<%
 HttpSession session2=request.getSession(true);
 AdminBean admin2=(AdminBean)session2.getAttribute("admin");
 request.setAttribute("cpnyDiff",admin2.getCpnyId());

%>                                
<form name="form1" action="/paControlServlet" method="post">
<input name="ck_all" value="0" type="hidden"/> 
<input type="hidden" name="mailSize" value="${fn:length(paMailList)}"/>    	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
		<%@ include file="../inc/toolbar_send_mail.jsp"%>
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
			<td align="left" class="title1" ><!--查询条件--><ait:message  messageID="display.mutual.search_criteria"/></td>
		</tr>
	    <tr>
	      <td >
	      	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="1" class="dr_d">
		      	 <tr>
		      	 	<td class="info_title_01">邮件区分</td>
		      		<td class="info_content_00" nowrap="nowrap">
			        	<select name="tableName">
			        		<option value="PA_HISTORY" <c:if test="${tableName == 'PA_HISTORY'}">selected</c:if> >工资</option>
			        	<!--<option value="PA_REPLENISHMENT_HISTORY" <c:if test="${tableName == 'PA_REPLENISHMENT_HISTORY'}">selected</c:if> >工资(补)</option> -->
			        		<option value="PA_BONUS_HISTORY" <c:if test="${tableName == 'PA_BONUS_HISTORY'}">selected</c:if>>奖金</option>
			        	</select>	        
			        </td>
			        <td class="info_title_01">员工区分</td>
			        <td class="info_content_00" nowrap="nowrap">
			      		<ait:empDiff  name="statTypeCode"  cpnyDiff="<%= admin.getCpnyId() %>" selected="${statTypeCode}"/> 
			        </td>
			        <td class="info_title_01"><!-- 工资月 --><ait:message messageID="display.mutual.pay_month"/>:</td>
			        <td class="info_content_00" nowrap="nowrap">
	        		<ait:date yearName="year" monthName="month" yearSelected="${year}" monthSelected="${month}" yearPlus="10" />
	        		</td>
	        		<td class="info_title_01">发送状态</td>
	        		<td class="info_content_00" nowrap="nowrap">
	        			<select name="activity">
							<option value="">全部</option>
							<option value="1" <c:if test="${activity == 1}">selected</c:if> >是</option>
							<option value="0" <c:if test="${activity == 0}">selected</c:if> >否</option>
						</select>
	        		</td>
		        </tr>
	      		<tr>
	      			<td class="info_title_01"><!--部门-->
						<ait:message  messageID="display.mutual.department"/></td>
					<td class="info_content_00" >
					    <ait:selDept name="deptId" selected="${deptId}" supervisorType="pa" all=" "/>
					</td>
					<td class="info_title_01"><!-- 员工--><ait:message  messageID="display.mutual.emp_no_name"/>:</td>
			        <td class="info_content_00" nowrap="nowrap">
			        	<input type="hidden" name="person_id" id="person_id" value="${person_id }">
					    <input id="key" name="key" size="10" value="${key}" onkeyup="SearchEmp(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_emp_id" module="hrm"/>'/>
					    <span id="name"></span>
					</td>
					<td class="info_title_01">奖金名称</td>
					<td class="info_content_00" >
				   		<ait:codeClass codeClass="BonusType" name="bonusTypeCode" selected="${bonusTypeCode}" all=" "/>
						<select name="bonusNo">
							<c:forEach var="i" begin="1" end="9" step="1">
								<option value="${i}" <c:if test="${i == bonusNo}">selected</c:if> >${i}</option>
							</c:forEach>
						</select>
					</td>
					<td class="info_title_01">补发工资</td>
					<td class="info_content_00" >
				   		<select name="supplyFlag">
							<option value="N" <c:if test="${supplyFlag == 'N'}">selected</c:if> >否</option>	
							<option value="Y" <c:if test="${supplyFlag == 'Y'}">selected</c:if> >是</option>
					    </select>
					</td>
					
										 
				 </tr>
			</table>
	      </td>
		</tr>
		</table>
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp" %>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" >工资邮件</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
			<tr>
				<td><font color="red" align="center"> <ait:processBar /> </font></td>
			</tr>
		</table>
		                                             
		 <table width="100%" border="0" cellpadding="1" cellspacing="1" class="dr_d">
			<tr align="center">
		<td nowrap="nowrap" class="info_title_01"><a href="#" onclick="checkAll();" style="color:red">全选</a></td>
		<td nowrap="nowrap" class="info_title_01">工资月</td>
		<td nowrap="nowrap" class="info_title_01">职号</td>
		<td nowrap="nowrap" class="info_title_01">姓名</td>
		<td nowrap="nowrap" class="info_title_01">部门</td>
		<td nowrap="nowrap" class="info_title_01">考勤区间</td>
		<td nowrap="nowrap" class="info_title_01">奖金类型</td>
		<td nowrap="nowrap" class="info_title_01">奖金序号</td>
		<td nowrap="nowrap" class="info_title_01">邮件</td>
		<td nowrap="nowrap" class="info_title_01">发送状态</td>
		</tr> 
			<c:forEach items="${paMailList}" var="email" varStatus="status"> 
			<tr bgcolor="#FFFFFF" >
			    <td class="info_content_01">
			    <input type="checkbox" name="selectC" value="${email.ID}"/></td>
			    <td class="info_content_01">${email.PA_MONTH}</td>
			    <td class="info_content_01">${email.EMPID}</td>
			    <td class="info_content_01"">${email.CHINESENAME}</td>
			    <td class="info_content_01">${email.DEPARTMENT}</td>
			    <td class="info_content_01">${email.ATTENDANCE_PERIOD}</td>
			    <td class="info_content_01">${email.BONUS_TYPE}</td>
			    <td class="info_content_01">${email.BONUS_NO}</td>
			    <td class="info_content_01">${email.EMAIL}</td>  
			    <td class="info_content_01">${email.ACTIVITY}</td>    
			</tr>      
			</c:forEach>
		</table> 
		</form>
		<c:if test="${paMailList eq null }">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
			<tr>
				<td><font color="red" align="center"> 请按条件进行搜索!!! </font></td>
			</tr>
		</table>
		</c:if>
		
		
		<!-- Page Navigation Start-->
					<ait:page       
		               link="/paControlServlet"
		               parameters="operation=viewPaEmailcmd&menu_code=${param.menu_code}&paMonth=${paMonth}&key=${key}&year=${year}&month=${month}&tableName=${tableName}&bonusTypeCode=${bonusTypeCode}&bonusNo=${bonusNo}&statTypeCode=${statTypeCode}&activity=${activity}&deptId=${deptId}&supplyFlag=${supplyFlag}" 
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/> 
		               
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
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

<ait:xjos />
</body>
</html>