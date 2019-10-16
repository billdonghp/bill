<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="codeMap" class="java.util.HashMap" scope="page" />
<jsp:useBean id="affirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essCardApplyBean" class="com.ait.ess.bean.EssCardApplyBean" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<script language="javascript" src="../js/meizzDate.js"></script>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function SearchEmp(empid){
	var inputBox = document.ApplyForm.empId;
	var iBtop  = inputBox.offsetTop;     //文本框的定位点高
	var iBheight  = inputBox.clientHeight;  //文本框本身的高
	var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
	while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
	showsearch.style.top = iBtop+iBheight+6;
	showsearch.style.left = iBleft;
	showsearch.style.visibility='visible';
	document.emp_list.location.href = "/inc/EssSearchEmployee.jsp?condition="+empid;
}


function SearchEmp(condition,id){
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
		$('empId').value=cell.childNodes[0].firstChild.nodeValue;		
		layerClose();
		document.ApplyForm.operation.value="view";
		document.ApplyForm.submit();
}

function Save(){
	if (showsearch.style.visibility=='visible') {
		alert ('请正确选择休假人');
	} else {
		document.ApplyForm.operation.value="add";
		document.ApplyForm.submit();
	}
}
</SCRIPT>
<body>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/esstoolbar_apply.jsp"%> 
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
<form name="ApplyForm" action="/essControlServlet">
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="cardapply" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<div align="left"><span class="title1">考勤申请&gt;漏卡申请</span></div>
<%if (!errorMsg.equals("")) {%>
  <table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
  </table>
<%}%>
<table width="90%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="15%" height="30"  align="center" class="info_title_01">申请人选择</td>
    <td width="35%" align="left">
    <input type="text" id="empId" name="empId" class="content" style="width:90px " value="<%=essCardApplyBean.getApplyEmp() %>" onKeyUp="SearchEmp(this.value,this.id)" />&nbsp;
    [&nbsp;<%=essCardApplyBean.getChineseName() %>&nbsp;]</td>
    <td width="15%" align="center" class="info_title_01">申请日期</td>
    <td width="35%" align="left"><%=essCardApplyBean.getCreateDate() %></td>
  </tr>
  <tr align="center">
    <td height="30"  align="center" class="info_title_01">打卡时间</td>
    <td align="left">
    <input type="text" name="cardDate" class="content" readonly style="width:90px " value="<%=essCardApplyBean.getCardTime().substring(0, 10) %>" onClick="setday(this);">&nbsp;
      <select name="cardTime" style="width: 90px ">
        <%for (int i=0;i<timeList.size();i++) {%>
        <option value="<%=(String) timeList.get(i) %>"<%=((String) timeList.get(i)).equals(essCardApplyBean.getCardTime().substring(11, 16))?" selected":""%>><%=(String) timeList.get(i) %></option>
        <%}%>
      </select>
    </td>
    <td align="center" class="info_title_01">ID卡号</td>
    <td align="left">
    <input type="text" name="cardNo" class="content"  style="width:90px " value="<%=essCardApplyBean.getCardNo()%>" > &nbsp;
    </td>
  </tr>
  <tr align="center">
    <td height="30"  align="center" class="info_title_01">打卡类型</td>
    <td align="left" colspan="3">
      <select name="doorType"  style="width:85px ">
          <option value="in"<%=essCardApplyBean.getDoorType().equals("in")?" selected":""%>>In</option>
          <option value="out"<%=essCardApplyBean.getDoorType().equals("out")?" selected":""%>>Out</option>
      </select>
     </td>
  </tr>
  <%if (essCardApplyBean.getAffirmorList().size() > 0) {
	for (int i=0;i<essCardApplyBean.getAffirmorList().size();i++) {
	  affirmor = (EssAffirmor) essCardApplyBean.getAffirmorList().get(i);%>
	  <tr>
		<td align="center" class="info_title_01" height="30"><%=affirmor.getAffirmLevel()%>&nbsp;级决裁者</td>
		<td align="left"><%=affirmor.getAffirmorName()%></td>
		<%i++;
		if (i<essCardApplyBean.getAffirmorList().size()) {
		  affirmor = (EssAffirmor) essCardApplyBean.getAffirmorList().get(i);%>
		  <td align="center" class="info_title_01"><%=affirmor.getAffirmLevel()%>&nbsp;级决裁者</td>
		  <td align="left"><%=affirmor.getAffirmorName()%></td>
		<%} else {%>
		  <td align="center" class="info_title_01">&nbsp;</td>
		  <td align="left">&nbsp;</td>
		<%}%>
	  </tr>
	<%}
  } else {%>
	<tr>
	  <td align="center" height="30" colspan="4" style="color:red;">当前员工未设置决裁者,请速与人事部门相关人员联系</td>
	</tr>
  <%}%>
  <tr>
	<td align="center" class="info_title_01" height="90px">申请原因</td>
	<td colspan="3"><textarea name="applyReason" style=" height:200px;width:600px "><%=essCardApplyBean.getApplyReason() %></textarea></td>
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
</form>

	<iframe id='iemp' style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;"> 
	</iframe>
	<div id="emp_list"  style="position:absolute;overflow:auto; top:100;width:370; height:210; z-index:2;">   
	</div>

</body>
</html>
