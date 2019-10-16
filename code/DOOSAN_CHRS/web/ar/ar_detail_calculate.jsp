<%@ page contentType="text/html; charset=UTF-8" import="com.ait.hrm.bean.Department,org.apache.log4j.Logger,com.ait.hrm.bean.BasicInfo,com.ait.sqlmap.util.SimpleMap,com.ait.ar.bean.Supervisor,com.ait.ar.dao.SupervisorDAO,com.ait.ar.dao.ArDAOFactory,com.ait.ar.ArCalc,java.util.*,com.ait.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="services" class="com.ait.hrm.dao.common.HrmCommonDAO" />
<jsp:useBean id="employee" class="com.ait.hrm.bean.BasicInfo" />

<html>
<head>

<ait:processBarJs />
<script src="../js/prototype.js"></script>
<script language="javascript" type="text/javascript">
function Calc() {
    
    
	caltype = document.getElementById("caltype").value;
	
	var dFrom =($('leaveStartDate').value).split("-");	
    var dateFrom=new Date(dFrom[1]+'-'+dFrom[2]+'-'+dFrom[0]);
    var dTo =($('leaveEndDate').value).split("-"); 
    var dateTo=new Date(dTo[1]+'-'+dTo[2]+'-'+dTo[0]);  
	var days =parseInt(Math.abs(dateTo-dateFrom)/1000/60/60/24);
	

	if (document.Em2Form.leaveStartDate.value == ""){
	//"请填写开始日期"
		alert('<ait:message  messageID="alert.ess.start_time" module="ar"/>');
	}
	else if (document.Em2Form.leaveEndDate.value == ""){
	//"请填写结束日期"
		alert('<ait:message  messageID="alert.ess.end_time" module="ar"/>');
		//您确定要计算考勤员的所有部门考勤?
	
	}
	
    else if ( days > 17){
        alert('明细计算日期间隔不可以大于16天');
    }
	else if (caltype=="SUPERVISOR" && confirm("<ait:message  messageID='display.mutual.attendance_keeper'/>：[" + document.Em2Form.arSupervisorId.options[document.Em2Form.arSupervisorId.selectedIndex].innerText + "]\n <ait:message  messageID='alert.att.definable.calculate_record_keeper' module='ar'/>" )) {
			
				beforeSubmit();
				document.Em2Form.fireSubmit();
				afterSubmit();        
			}
	else if (caltype=="DEPT" && confirm("<ait:message  messageID='display.mutual.department'/>：[" + document.Em2Form.deptId.options[document.Em2Form.deptId.selectedIndex].innerText + "]\n <ait:message  messageID='alert.att.definable.calculate_record_dept' module='ar'/>" )) {
	
				beforeSubmit();
				document.Em2Form.fireSubmit();
				afterSubmit();   
			}
	else if (caltype=="EMP" && document.Em2Form.empId.value != '' && confirm(" <ait:message  messageID='display.mutual.emp_no_name'/>： [" + document.Em2Form.empId.value + "/" + document.getElementById("name").innerText + "] \n <ait:message  messageID='alert.att.definable.calculate_record_employee' module='ar'/>" )) {
			
				beforeSubmit();
				document.Em2Form.fireSubmit();
				afterSubmit();   
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
		$('personId').value = "" ;
		
		var deptid = document.Em2Form.deptId.value ;
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmSearchEmployee&condition=" + encodeURIComponent(condition);
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft-80;    //文本框的定位点宽
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
		$('empId').value = cell.childNodes[0].firstChild.nodeValue;
		$('personId').value = cell.childNodes[2].firstChild.nodeValue;
		$('name').innerHTML= cell.childNodes[1].firstChild.nodeValue;
		layerClose();
}

function toggle(id){
	var div1=document.getElementById("id1");
	var div2=document.getElementById("id2");
	var div3=document.getElementById("id3");
	
	if(id=="SUPERVISOR"){
		div1.style.display = "block";
		div2.style.display = "none";
		div3.style.display = "none";
	}else if(id=="DEPT"){ 
		div1.style.display = "none";
		div2.style.display = "block";
		div3.style.display = "none";
	}else{
		div1.style.display = "none";
		div2.style.display = "none";
		div3.style.display = "block";
	}
}

</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/ar_toolbar_detail_calc.jsp"%>
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
		<%@ include file="../inc/common_toolbar.jsp"%><br>
		
		<!-- display content -->
		<form action="/ar/ar_detail_calculate.jsp" method="post" name="Em2Form">
			<input type="hidden" name="menu_code" value="<%=StringUtil.checkNull(request.getParameter("menu_code"))%>">
		<%
			ArrayList<String> messageList = new ArrayList<String>();
			if (request.getParameter("caltype") != null) {
				ArCalc arCalc = new ArCalc();
				messageList = arCalc.CalcDetail(request.getParameter("leaveStartDate"), request.getParameter("leaveEndDate"), request.getParameter("caltype"), request.getParameter("arSupervisorId"), request.getParameter("deptId"), request.getParameter("personId"),admin);
			}
		
		   String supervisorId = StringUtil.checkNull(request.getParameter("arSupervisorId"));
		   String deptId = StringUtil.checkNull(request.getParameter("deptId"));
		   request.setAttribute("deptId",deptId);
		   String empId = StringUtil.checkNull(request.getParameter("empId"));
		   String personId = StringUtil.checkNull(request.getParameter("personId"));
		   request.setAttribute("empId",empId);
		   request.setAttribute("personId",personId);
		   if (supervisorId.equals(""))
			  supervisorId = admin.getAdminID();
		   request.setAttribute("supervisorId",supervisorId);
		   Logger.getLogger(getClass()).debug("supervisorId : " + supervisorId);
		   Logger.getLogger(getClass()).debug("leaveStartDate : " + request.getParameter("leaveStartDate"));
		   
		   SimpleMap parameterObject1 = new SimpleMap();
		   parameterObject1.setString("SUPERVISOR_ID", supervisorId);
		   parameterObject1.setString("cpnyId", admin.getCpnyId());
		   
		   List supervisedDeptidList = services.retrieveDeptTree(parameterObject1);
		   request.setAttribute("supervisedDeptidList",supervisedDeptidList);
		   
		   
		   SupervisorDAO supervisorDAO = (SupervisorDAO) ArDAOFactory.getInstance().getSupervisorDAO();
		   String[] screenNo = admin.getScreenGrantNo().split(",") ;
		   Arrays.sort(screenNo) ;
		   List supervisorlist = new ArrayList() ;
		   if (Arrays.binarySearch(screenNo,"3") > 0 )
		   {
			   supervisorlist = supervisorDAO.getAllSupervisor(admin.getCpnyId());
		   }
		   else
		   {
			   supervisorlist = supervisorDAO.getAllSupervisorByEmpId(admin.getAdminID(),admin.getCpnyId());
		   }
		   
		   request.setAttribute("supervisorlist",supervisorlist);
		   
		   /*
		   List employeelist = new ArrayList();
		   SimpleMap parameterObject = new SimpleMap();
		   parameterObject.setString("DEPTID", deptId);
		   parameterObject.setString("SUPERVISOR_ID", supervisorId);
		   employeelist =(List) services.retrieveEmpList(parameterObject);
		   request.setAttribute("employeelist",employeelist);
		   */
		   String leaveStartDate = StringUtil.checkNull(request.getParameter("leaveStartDate"));
		   String leaveEndDate = StringUtil.checkNull(request.getParameter("leaveEndDate"));
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><!--明细计算-->
					<ait:message  messageID="display.att.maintenance.definable.calculation" module="ar"/></td>
			</tr>
		</table>
		 <table width="100%" height="33"  border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"  style="padding: 2px 2px 2px 2px;">
		  <tr>
		    <tr align="center">
		    <td height="30"  width="18%" class="info_title_01"><!--开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
		    <td height="30"  width="18%" align="left">
		    <input onClick="setday(this);" readonly type="text" name="leaveStartDate" class="content"  style="width: 100px " value="<%=leaveStartDate%>"></td>
		    <td height="30"  width="20%" class="info_title_01"><!--结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
		    <td height="30"  width="20%" align="left"><input onClick="setday(this);" readonly type="text" name="leaveEndDate" class="content"  style="width: 100px " value="<%=leaveEndDate%>"></td>
		    <td height="30"  width="15%" class="info_title_01"><!--计算方式-->
					<ait:message  messageID="display.att.maintenance.definable.type" module="ar"/></td>
					<%	String caltype= StringUtil.checkNull(request.getParameter("caltype"),"SUPERVISOR");
						request.setAttribute("caltype",caltype);%>
		    <td height="30"  width="15%" align="left"><ait:codeClass name="caltype" codeClass="CalcType"
		     selected="${caltype}"  onChange="toggle(this.value);" /></td>
		  </tr>		 
		  <tr>
		    <td height="30"  width="18%" class="info_title_01"><!--按考勤员计算-->
			选择计算条件
			</td>
		    <td width="75%"  colspan="5">
		     <div id="id1" style="display">
		     <select name="arSupervisorId" style="width:180px; position: static; visibility: inherit;"  title="按考勤员计算">
				<!-- onChange="location.href='ar_detail_calculate.jsp?menu_code=<%=menu_code%>&arSupervisorId='+this.value+'&leaveStartDate='+document.Em2Form.leaveStartDate.value+'&leaveEndDate='+document.Em2Form.leaveEndDate.value;" -->
				<c:forEach items="${supervisorlist}" var="supervisorlist">
					<c:choose>
						<c:when test="${supervisorId==supervisorlist.supervisorId}">
							<option value="${supervisorlist.supervisorId}" selected="selected">
								${supervisorlist.empId }-
								<ait:content enContent="${supervisorlist.pinyin}" zhContent="${supervisorlist.chineseName}" koContent="${supervisorlist.korName}"/>
							</option>
						</c:when>
						<c:otherwise>
							<option value="${supervisorlist.supervisorId}">
								${supervisorlist.empId }-
								<ait:content enContent="${supervisorlist.pinyin}" zhContent="${supervisorlist.chineseName}" koContent="${supervisorlist.korName}"/>
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select>		     
		 </div>
		 <div id="id2" style="display:none">
               <select name="deptId" style="width:180px; position: static; visibility: inherit;" >
				<c:forEach items="${supervisedDeptidList}" var="supervisedDeptidList">
				            
					<c:choose>
						<c:when test="${deptId==supervisedDeptidList.deptID}">   
							<option value="${supervisedDeptidList.deptID}" selected="selected">
							  <c:forEach begin="0" end="${supervisedDeptidList.deptLevel}" step="1" >
							&nbsp;
				           </c:forEach>  
								<ait:content enContent="${supervisedDeptidList.deptEnName}" zhContent="${supervisedDeptidList.deptName}" koContent="${supervisedDeptidList.korDept}"/>
							</option>
						</c:when>
						<c:otherwise>    
							<option value="${supervisedDeptidList.deptID}">
							 <c:forEach begin="0" end="${supervisedDeptidList.deptLevel}" step="1" >
							&nbsp;
				            </c:forEach> 
								<ait:content enContent="${supervisedDeptidList.deptEnName}" zhContent="${supervisedDeptidList.deptName}" koContent="${supervisedDeptidList.korDept}"/>
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select>		   
		 </div>
		<div id="id3" style="display:none">
		     <input id="empId" name="empId" type="text" size="10" value="${param.empId}" onKeyUp="SearchEmp(this.value,this.id)"  title="请输入员工职号或者姓名">&nbsp;(<span id="name"></span>)
		     <input id="personId" name="personId" type="hidden" size="10" value="${param.personId}"/>
		     </td>   
		 </div>     
		    </td>
		 </tr>

		 <tr>
		    <td colspan="6" align="center" height="30" valign="middle">

			<ait:processBar/>
			&nbsp;<br><br>
			<%
				for(int i = 0 ; i < messageList.size() ; ++ i )
				{
			%>
				<%= messageList.get(i) %><br>
			<%		
				}
			%>
		    </td>
		  </tr>
		</table>
		<br></form>
		<br><br><br><br><br><br><br><br>

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
	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2; visibility: hidden;">   
	</div>

<ait:xjos />
</body>
</html>

