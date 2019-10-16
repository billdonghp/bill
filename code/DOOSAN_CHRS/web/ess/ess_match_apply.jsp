<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="codeMap" class="java.util.HashMap" scope="page" />
<jsp:useBean id="affirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="timeList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="applyTypeVector" class="java.util.Vector" scope="request" />
<jsp:useBean id="essMatchBean" class="com.ait.ess.bean.EssMatchBean" scope="request" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<title>申请信息</title>
</head>
<%@ include file="inc/esstoolbar_apply.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<script language="javascript" src="../js/meizzDate.js"></script>
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
function Save(){
	if (showsearch.style.visibility=='visible') {
		alert ('请正确选择值班人');
	} else {
		document.ApplyForm.operation.value="add";
		document.ApplyForm.submit();
	}
}
</SCRIPT>
<body>
<div id="showsearch" style="position:absolute; top:0px; left: 0px; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
	<iframe name="emp_list" width="370" height="200"  frameborder="0" marginwidth="0" marginheight="0" hspace="0" vspace="0"></iframe>
</div>
<form name="ApplyForm" action="/essControlServlet">
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="matchapply" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>"/>
<div align="left"><span class="title1">考勤申请&gt;值班申请</span></div>
<%if (!errorMsg.equals("")) {%>
  <table width="80%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
  </table>
<%}%>
<table width="90%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center">
    <td width="15%" height="30"  align="center" bgcolor="#F5F5F5">值班人选择</td>
    <td width="35%" align="left"><input type="text" name="empId" class="content" style="width:90px " value="<%=essMatchBean.getEmpID() %>" onKeyUp="SearchEmp(this.value)" /></td>
    <td width="15%" align="center" bgcolor="#F5F5F5">值班人姓名</td>
    <td width="35%" align="left"><%=essMatchBean.getChineseName() %></td>
  </tr>
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">开始日期</td>
    <td align="left"><input type="text" name="matchFromDate" class="content" readonly style="width:90px " value="<%=essMatchBean.getMatchFromTime().substring(0, 10) %>" onClick="setday(this);">&nbsp;
      <select name="matchFromTime" style="width: 90px ">
        <%for (int i=0;i<timeList.size();i++) {%>
        <option value="<%=(String) timeList.get(i) %>"<%=((String) timeList.get(i)).equals(essMatchBean.getMatchFromTime().substring(11, 16))?" selected":""%>><%=(String) timeList.get(i) %></option>
        <%}%>
      </select></td>
    <td align="center" bgcolor="#F5F5F5">结束日期</td>
    <td align="left"><input type="text" name="matchToDate" class="content" readonly style="width:90px " value="<%=essMatchBean.getMatchToTime().substring(0, 10) %>" onClick="setday(this);">&nbsp;
      <select name="matchToTime" style="width: 90px ">
        <%for (int i=0;i<timeList.size();i++) {%>
        <option value="<%=(String) timeList.get(i) %>"<%=((String) timeList.get(i)).equals(essMatchBean.getMatchToTime().substring(11, 16))?" selected":""%>><%=(String) timeList.get(i) %></option>
        <%}%>
      </select></td>
  </tr>
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5">值班类型</td>
    <td align="left">
      <select name="matchTypeCode"  style="width:85px ">
        <%for (int i=0;i<applyTypeVector.size();i++) {
          codeMap = (HashMap) applyTypeVector.elementAt(i);%>
          <option value="<%=codeMap.get("code")%>"<%=essMatchBean.getMatchTypeCode().equals(codeMap.get("code"))?" selected":""%>><%=codeMap.get("name")%></option>
        <%}%>
      </select></td>
	<td align="center" bgcolor="#F5F5F5">申请日期</td>
	<td align="left"><%=essMatchBean.getCreateDate() %></td>
  </tr>
  <%if (essMatchBean.getAffirmorList().size() > 0) {
	for (int i=0;i<essMatchBean.getAffirmorList().size();i++) {
	  affirmor = (EssAffirmor) essMatchBean.getAffirmorList().get(i);%>
	  <tr>
		<td align="center" bgcolor="#F5F5F5" height="30"><%=affirmor.getAffirmLevel()%>&nbsp;级决裁者</td>
		<td align="left"><%=affirmor.getAffirmorName()%></td>
		<%i++;
		if (i<essMatchBean.getAffirmorList().size()) {
		  affirmor = (EssAffirmor) essMatchBean.getAffirmorList().get(i);%>
		  <td align="center" bgcolor="#F5F5F5"><%=affirmor.getAffirmLevel()%>&nbsp;级决裁者</td>
		  <td align="left"><%=affirmor.getAffirmorName()%></td>
		<%} else {%>
		  <td align="center" bgcolor="#F5F5F5">&nbsp;</td>
		  <td align="left">&nbsp;</td>
		<%}%>
	  </tr>
	<%}
  } else {%>
	<tr>
	  <td align="center" height="30" colspan="4" style="color:red;">当前员工未设置决裁者,请速与人事部门相关人员联系</td>
	</tr>
  <%}%>
</table>
</form>
</body>
</html>
