<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<script src="../js/prototype.js"></script>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<jsp:useBean id="permissionsType" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="eatTypeSize" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptID" class="java.lang.String" scope="request" />
<jsp:useBean id="affirmList"  class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList"
	scope="page" />
<%@ page
	import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date"%>
<html>
	<head>
	<!-- ga_card_application.jsp -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/css/default.css" rel="stylesheet" type="text/css">
		<title>临时卡申请</title>
	</head>
	<script src="../js/prototype.js"></script>
	<SCRIPT type="text/javascript">
	
var time=null;
function SearchEmp(condition,id){	
			var employeeType =document.all("EmployeeType").value;	
			if(employeeType=="external"){
			 alert("外来人员不需要输入职号");
			 return;
			}else{
				if(time!=null){
					clearTimeout(time);
					time=null;  
			}
				time=setTimeout(function(){
								//	alert(condition);
									SearchE(condition,id);
								},500);  
			}
}

function SearchE(condition,id){
		var url = "/ajaxControlServlet" ;
     	var pars = "operation=hrmEmployee&condition=" + encodeURIComponent(condition);
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
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[6].firstChild.nodeValue;
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
		$('used').value=cell.childNodes[1].firstChild.nodeValue;		
		document.form1.jobtitle.value=cell.childNodes[2].firstChild.nodeValue.replace("无","");		
		$('personId').value = cell.childNodes[3].firstChild.nodeValue;		
		$('company').value = cell.childNodes[5].firstChild.nodeValue;	
		$('peoples').value = 1;	
		
		layerClose();
}
function Save() {
   var eatery = document.getElementsByName("permissionsType");   
   var eaterynum=0;
   var permissionsName="";
   for(var i=0; i<eatery.length;i++){
    if(eatery[i].checked==true){
     eaterynum=eaterynum+1;
     permissionsName += document.getElementById(eatery[i].value).value+",";
    }
   }
   document.form1.permissionsName.value = permissionsName;
        if(document.form1.starttime.value==""||document.form1.starttime.value==null||document.form1.endtime.value==""||document.form1.endtime.value==null){
			 alert("日期不能为空！");
			 return;
		}else if(stardateCheck()){
		   alert("结束时间必须在开始时间之后");
		    return;
		}else if(document.form1.username.value==""||document.form1.username.value==null){
			 alert("使用者姓名不能为空");	
			 return;	
		}else if(document.form1.userbusiness.value==""||document.form1.userbusiness.value==null){
		     alert("使用者业务不能为空");
				return;
		}else if(document.form1.affirmor.value=="" || document.form1.affirmor.value==null){
		 alert("没有设置决裁者！请先设置决裁者");
		 return;
		
		}else{
		
			if(confirm("是否提交申请!!!")){
				//alert(123) ;
				document.form1.action="/gaControlServlet?operation=gaApply&content=tempCardApplicationAdd&menu_code=${param.menu_code}";
				document.form1.submit();
			}
	  }
	
}

function stardateCheck(){
	var startime=document.form1.starttime.value.replace("-","");
	var endtime=document.form1.endtime.value.replace("-","");
	if(startime<endtime || startime==endtime){
	  return false;
	}else{
	  return true;
	}
}

</SCRIPT>
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="admin" value="<%=admin.getAdminID()%>" />
			<input type="hidden" name="temp" value="" />
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
				<td align="left" class="title1" id = 'checkRoomInfo' colspan="10">
				临时卡申请<font color="red" size="2">${declaration}</font>
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
						<table width="100%" border="1" cellspacing="0" cellpadding="0"
							bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
							<tr>
								<td nowrap="nowrap" class="info_title_01">
									类别
								</td>
								<td nowrap="nowrap" class="info_content_00">
									<select name="EmployeeType">
										<option value="external">
											外来人员
										</option>
										<option value="Local">
											本社员工
										</option>
									</select>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									申请部门
								</td>
								<td nowrap="nowrap" class="info_content_01">
									${DeptID }
								</td>
								<td nowrap="nowrap" class="info_title_01">
									申请人
								</td>
								<td nowrap="nowrap" class="info_content_01">
									${name}
								</td>
								<td nowrap="nowrap" class="info_title_01">
									决裁者
								</td>
								 <td nowrap="nowrap">
							      <%if(!affirmList.isEmpty()){%>
							      <input type="hidden" value="<%=affirmList.size()%>" name="affirmor">
							      <table id="affirmorlist" width="100%" border="0" cellspacing="0" cellpadding="0">

							      <%int affirmLevel=10;
							      for(int i=0;i<affirmList.size();i++){
							      gaAffirmList=(GaAffirmList)affirmList.get(i);%> 
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
							      <table>
							      <tr><td nowrap="nowrap"><font color="red">没有决裁者</font></td></tr>		      
							      </table>
							      <% }%>		      
							      </td>	
    							</tr>
							<tr>
								<td nowrap="nowrap" class="info_title_01">
									开始日期
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="starttime" value="${startDate }"
										style="width:70px " onClick="setday(this);" readonly>
								</td>
								<td nowrap="nowrap" class="info_title_01">
									结束日期
								</td>


								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" name="endtime" value="${endDate }" style="width:70px "
										onClick="setday(this);" readonly>
								</td>

								<td nowrap="nowrap" class="info_title_01">
									使用者姓名
								</td>
								<td nowrap="nowrap" class="info_content_00">
									<input type="text" style="width: 130px" name="username"  value="" />
								</td>
								<td nowrap="nowrap" class="info_title_01">
									使用者业务
								</td>
								<td nowrap="nowrap"  class="info_content_00" >
									<input type="text" style="width: 130px" name="userbusiness"
										id="userbusiness" value="" />
								</td>							

							</tr>

							<tr >
								
								<td nowrap="nowrap" class="info_title_01">
									申请权限
								</td>
								<td nowrap="nowrap"  class="info_content_01"  colspan="7" >
								<input type="hidden" name="permissionsName" value="" >
									<c:forEach items="${tempCardType}" var="all" varStatus="i">
	      				${all.GM_TYPE}<input type="checkbox"
											name="permissionsType" value="${all.GM_ID}"/>
									<input type="hidden" name="${all.GM_ID}" id="${all.GM_ID}" value="${all.GM_TYPE}" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>
									&nbsp;

								</td>				

							</tr>

						</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="10">		
				<c:forEach var="i" begin="1" end="9">
					<tr>
						<td class="info_content_01">&nbsp;</td>					
					</tr>
				</c:forEach>			
		
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
	</body>
<iframe id='iemp' style="position:absolute; top:100; width:570; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:570; height:210; z-index:2;visibility: hidden;">   
	</div>
	<ait:xjos />
</html>
