<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>   
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="prompt" class="java.lang.String" scope="request" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="applyId" class="java.lang.String" scope="request" />
<jsp:useBean id="apiLanguage" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listMM" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="listHH" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="sysDate" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptNAME" class="java.lang.String" scope="request" />
<jsp:useBean id="DeptID" class="java.lang.String" scope="request" />
<jsp:useBean id="allVisiterCountry" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gaAffirmList" class="com.ait.ga.bean.GaAffirmList" scope="page" />
<jsp:useBean id="dept" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allDevice" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="allVisiterDistiniction" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.ga.cmd.visit.*,java.util.*,com.ait.ga.bean.*,java.util.Date,com.ait.sysparam.*" %>
<html>

<head>
<script src="../js/prototype.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>访问申请页面</title>
</head>

<SCRIPT type="text/javascript">

var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	time=setTimeout(function(){
			SearchC(condition,id);
		},500);  
}

function SearchC(condition,id){

	var url = "/ajaxControlServlet" ;
    	var pars = "operation=GaSearchEmployee&condition=" + encodeURIComponent(condition);
    	
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
	
	document.form1.empName.value = "";
	document.form1.person_id.value = "";
	
}

function layerClose1(){
	$('emp_list').innerHTML = "" ;
	layeri.style.visibility = 'hidden';
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
		$('empName').value=cell.childNodes[1].firstChild.nodeValue;
		$('person_id').value=cell.childNodes[2].firstChild.nodeValue;
		///$('deptName').innerHTML=cell.childNodes[3].firstChild.nodeValue;
		///$('frontdeptID').value=cell.childNodes[4].firstChild.nodeValue;
		
		layerClose1();
}

function showEmployeePartInformation(XmlHttpRequest){
    
    var dept = $('dept');
    var joinDate = $('joinDate');
    var info = XmlHttpRequest.responseText.split(",") ;
	dept.innerHTML= info[0];
    joinDate.innerHTML= info[1];
}

	var type = null;
	var tableUtil = new Object();
	var i=0;
tableUtil.appendRow = function(){	
		i = Number(document.form1.temp1.value)+1;
		document.form1.temp1.value = i;
		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterName"+i+"' style='width:70px' value='' />";
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='visiterCompany"+i+"'  style='width:170px' value='' />";
		
		
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='text' name='ContactTel"+i+"'  style='width:150px' value='' />";
	    
	   
	    
	    td = nTr.insertCell() ;
		td.className = "info_content_01" ;
	    td.innerHTML = "<textarea name='memo"+i+"' style='height: 25px;width:150px' type='_moz' onfocus=this.style.height='50px'; onblur=this.style.height='20px'; ></textarea>";
	    
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
		document.form1.temp1.value = Number(document.form1.temp1.value)- 1;
		var radioArr = document.getElementsByName('rowNum');
		var tbody = document.getElementById('operateTable').tBodies[0];
		var flag = false;
		for(var i=0;i<radioArr.length;i++)
			if(radioArr[i].checked){
				tbody.removeChild(radioArr[i].parentNode.parentNode);
				flag = true;
			}
		if(flag)
			return;
		else
			alert('请先选择要删除的行');
	}

	
function Save() {
//alert();

	if(document.form1.visiterTime.value == ""){
		alert("请填写来访日期！");
		return;
	}
	
	
	if(document.form1.visiterType.value == ""){
		alert("请填写人员性质！");
		return;
	}
	
	////if(document.form1.hour.value == "" || document.form1.min.value == "" || document.form1.Outhour.value == "" || document.form1.Outmin.value == ""){
	////	alert("请选择到达或者离开的具体时间！");
	////	return;
	///}
	
    if((document.form1.hour.value != "" && document.form1.min.value == "") || (document.form1.hour.value == "" && document.form1.min.value != "")){
		alert("请选择到达的具体时间！");
		return;
	}
	
	if((document.form1.Outhour.value != "" && document.form1.Outmin.value == "") || (document.form1.Outhour.value == "" && document.form1.Outmin.value != "")){
		alert("请选择到离开的具体时间！");
		return;
	}

	var selectDate = document.form1.visiterTime.value;
	var selectComeTime = document.form1.hour.value + document.form1.min.value;
	var selectOutTime = document.form1.Outhour.value + document.form1.Outmin.value ;
	if(document.form1.hour.value != "" && document.form1.Outhour.value != "" ){		
	if(selectComeTime > selectOutTime ){
		alert("到达时间不能小于离开时间!");
		return;
	}
	}
	//if(document.form1.visiterCountry.value == ""){
	//	alert("请选择国别！");
	//	return;
	//}
	
	if(document.form1.peopleNum.value.trim() == ""){
		alert("请填写来访人数！");
		return;
	}
	

	if(document.form1.empName.value.trim() == ""){
		alert("请填写接待人员！");
		return;	
	}
	
	if(document.form1.person_id.value.trim() == ""){
		alert("请在姓名下拉列表中选择接待人员！");
		return;	
	}
	
	if(document.form1.frontdeptID.value == ""){
		alert("请选择接待部门！");
		return;	
	}
	
	if(document.form1.Objective.value.trim() == ""){
		alert("请填写来访目的！");
		return;
	}
	
	j = Number(document.form1.temp1.value);
	
	for(var z=0 ; z<=j ; z++){
		if(document.form1('visiterName'+z) != null && document.form1["visiterName"+z].value.trim() == ""){
			alert("请填写姓名！");
			return;
		}
		
		if(document.form1('visiterCompany'+z) != null && document.form1["visiterCompany"+z].value.trim() == ""){
			alert("请填写单位！");
			return;
		}		
	}
	
	///if(document.form1.affirmor.value == '' || document.form1.affirmor == null){
	///	 alert("没有设置决裁者！请设置");
	///	 return;
   	///}
	document.form1.action="/gaControlServlet?operation=accessApplications&content=accessApplicationsSave&menu_code=${param.menu_code}&flag="+j;
	document.form1.submit();
}

function ifSelect(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['checkbox_'+i].checked){
			aa += document.form1['checkbox_'+i].value+",";
		}
	}
	document.form1.Device.value = aa;
	return;
}
function ifSelect1(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visit_pro_'+i].checked){
			aa += document.form1['visit_pro_'+i].value+",";
		}
	}
	document.form1.visit_pro.value = aa;
	return;
}
function ifSelect2(tempSize){
var aa = '';
	for(var i=0 ; i<=tempSize ; i++){
		if(document.form1['visit_pian_'+i].checked){
			aa += document.form1['visit_pian_'+i].value+",";
		}
	}
	document.form1.visit_pian.value = aa;
	return;
}

         function paseValueToAmount(value){   
                if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
                    if(decimalIndex=='-1'){   
                        return false;   
                    }else{   
                        var decimalPart=value.substring(decimalIndex+1,value.length);   
                        if(decimalPart.length>2){   
                            return true;   
                        }else{   
                            return false;   
                        }   
                    }   
                }   
                return false;   
 } 
 
function jumpHuiYiShi(){
	window.open ('/gaControlServlet?menu_code=ga0105&operation=conferenceRoom&content=conferenceRoomApplyPage&visiterNo='+document.form1.applyId.value+'&temp=visiter',
	 '会议室申请', 'height=500, width=1015, top=40, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=yes') 
}

function uploadImp(photosid){
window.open("/ga/ga_visiter_upload.jsp?documentno="+photosid,"","resizable=no,scrollbars,dependent,width=480,height=200,left=450,top=450");
}
</SCRIPT>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="Device" value="">
<input type="hidden" name="visit_pro" value="">
<input type="hidden" name="visit_pian" value="">
<input type="hidden" name="temp" value="0">
<input type="hidden" name="temp1" value="0">
<input type="hidden" name="sysDate" value="${sysDate}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/toolbar_apply.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%> <!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
			
				<td align="left" class="title1" colspan="10">
					访问申请  <font color="red" size="2">(该申请功能是被访问者使用)</font>
				</td>
<!--				<td align="right" colspan="10">
					<span onclick="jumpHuiYiShi()" style="cursor: pointer;"><font color="red">申请会议室</font></span>
				</td>-->
			</tr>
		</table>
		<%
		if (!errorMsg.equals("")) {
		%>
		<table width="100%" border="1" cellpadding="0" cellspacing="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td align="center"><font color="red"><%=errorMsg%></font></td>
			</tr>
		</table>
		<%
		}
		%>
		<%
		if (!prompt.equals("")) {
		%>
		<script lang="javascript">
			alert('<%= prompt%>');		
	    </script>
		<%
		}
		%>
		<table width="100%" border="1" cellspacing="0" cellpadding="0"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01">编号</td>
				<td nowrap="nowrap" class="info_content_00">${applyId}</td>
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>来访日期</td>
				<td nowrap="nowrap"" class="info_content_00">
					<input type="text" name="visiterTime" value="" class="content" readonly onClick="setday(this);" style="width:70px">
				</td>
				<td nowrap="nowrap" class="info_title_01">到达时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<select name="hour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="min">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				<td nowrap="nowrap" class="info_title_01">离开时间</td>
				<td nowrap="nowrap" class="info_content_00">
					<!--<input type="text" name="visiterOutTime" value="" class="content" readonly onClick="setday(this);" style="width:70px">-->
					<select name="Outhour">
			          	<option value="">小时</option>
			          	<c:forEach items="${listHH}" var="lhh" varStatus="i"> 
			          		<option value="${lhh}">${lhh}</option>
			          	</c:forEach>
			          </select>
			          :
			        <select name="Outmin">
			          	<option value="">分钟</option>
			          	<c:forEach items="${listMM}" var="lmm" varStatus="i">
			          		<option value="${lmm}">${lmm}</option>
			          	</c:forEach>
		            </select>
				</td>
				
			</tr>
			<tr align="center" bgcolor="#F5F5F5">
				
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>人员性质</td>
				<td nowrap="nowrap" class="info_content_00">
				 	<select name="visiterType">
				 			<option value="">
								请选择
							</option>
						<c:forEach items="${visiterType}" var="allType" varStatus="i">
							<option value="${allType.CODE_ID}">
								${allType.CODE_NAME }
							</option>
						</c:forEach>
					</select>
			 	</td>
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>来访人数</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="peopleNum" style="width:150px" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')" value="" /></td><!-- 车牌号码 -->
				<td nowrap="nowrap" class="info_title_01">车牌号码</td>
				<td nowrap="nowrap" class="info_content_00"><input type="text"
					name="visiterCarNum" style="width:150px" value="" /></td>	<!-- 车辆数 -->
				<td nowrap="nowrap" class="info_title_01">车辆数</td>
				<td nowrap="nowrap" class="info_content_00">
					<input type="text" name="visiterCarNumber" style="width:150px"  onkeyup='this.value=this.value.replace(/\D/gi,"")' numeric/>
				</td>	
				</tr>
				<tr>
				<td nowrap="nowrap" class="info_title_01">
				访问法人 </td>
			<td nowrap="nowrap" class="info_content_00" align="left">
			<ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" />
      		</td>
				
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>接待人员</td>
				<td nowrap="nowrap" class="info_content_00">
		          	<input id="empName" name="empName" style="width: 150px" value="<ait:content enContent='${chinesename}' zhContent='${chinesename}' koContent='${chinesename}'/>" 
		          		onkeyup="SearchContent(this.value,this.id)" title='<ait:message messageID="alert.emp.staff_info.basic_info.search_name" module="hrm"/>' />
          				
          			<input type="hidden" id="person_id" name="person_id" value="<c:out value='${person_id}'/>"/>
          			<!--<input type="hidden" id="frontdeptID" name="frontdeptID"  value=""/>-->
		        </td>	
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>接待部门</td>
				<td nowrap="nowrap" class="info_content_00">
				
				<!--<div id="deptName" name="deptName">&nbsp;</div>-->
				<ait:selDept name="frontdeptID" supervisorType="pa" all="all" selected="${DeptID}" />
				</td>
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>来访目的</td>
				<td nowrap="nowrap" class="info_content_00">
					<textarea name="Objective" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
				</td>
				</tr>
				
			<tr>
			    
			    </tr>	
			
		</table>
		<br>
		<table width="100%">
		<tr align="left">
		<td align="left"  colspan="18">
		<font color="red" size="2">${tiShi}</font> 
		</td>
		</tr>	
		
		</table>	
		<br>
		<table id = 'operateTable' width="100%" border="1" cellspacing="0" cellpadding="0"
				bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">	
				<tr>
				<td align="left" class="title1" colspan="18">
					访问者信息
				</td>
			</tr>	
			<tr align="center" bgcolor="#F5F5F5">
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>姓名</td>
				<!-- <td nowrap="nowrap" class="info_title_01">职务</td>-->
				<td nowrap="nowrap" class="info_title_01"><font color="red"> * </font>工作单位</td>
				<!-- <td nowrap="nowrap" class="info_title_01">区分</td> -->
				<td nowrap="nowrap" class="info_title_01">联系电话</td>
				<!-- <td nowrap="nowrap" class="info_title_01">礼品名称</td>-->
				<!-- <td nowrap="nowrap" class="info_title_01">礼品数量</td>-->
				<td nowrap="nowrap" class="info_title_01">备注</td>
				<td nowrap="nowrap" class="info_title_01">操作</td>
			</tr>
			<tr>			
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterName0" style="width:70px" value="" /></td>
				
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="visiterCompany0" style="width:170px" value="" /></td>
				<!-- 	
				<td nowrap="nowrap" class="info_content_01">
					<select name="Distinction0" id="Distinction0">
						<c:forEach items="${allVisiterDistiniction}" var="all" varStatus="i">
				    		<option value="${all.CODE_ID}">${all.CODE_NAME}</option>
			    		</c:forEach>
			    	</select>
				</td>
				 -->
				<td nowrap="nowrap" class="info_content_01"><input type="text"
					name="ContactTel0" style="width:150px" value="" /></td>
					
				
				<td nowrap="nowrap" class="info_content_01">
				<textarea name="memo0" style=" height: 25px;width:150px " type="_moz"  onfocus="this.style.height='50px'" onblur="this.style.height='20px';"></textarea>
					</td>
					
				
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
			</tr>
			
		</table>
		<br>
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
</form>
</body>
</html>
