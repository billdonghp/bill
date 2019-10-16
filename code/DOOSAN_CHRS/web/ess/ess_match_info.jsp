<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssMatchBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="confirmMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="matchList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essMatchBean" class="com.ait.ess.bean.EssMatchBean" scope="page" />
<jsp:useBean id="essAffirmorList" class="java.util.ArrayList" scope="page" />
<jsp:useBean id="essAffirmor" class="com.ait.ess.bean.EssAffirmor" scope="page" />
<jsp:useBean id="sDate" class="java.lang.String" scope="request" />
<jsp:useBean id="eDate" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/meizzDate.js"></script>
</head>
<body>
<%@ include file="/inc/hrtoolbar_null.jsp"%>
<%@ include file="/inc/thirdToolBar.jsp"%>
<script language="javascript1.5" type="text/javascript">
function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='1';
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}
function Delete(matchno) {
	if (confirm("您确定要删除这条申请?")) {
		document.ApplyForm.operation.value="delete";
		document.ApplyForm.matchNo.value=matchno;
		document.ApplyForm.submit();
	}
}
</script>
<div align="left">
<span class="title1">决裁情况&gt;值班信息</span></div>
<form name="ApplyForm" action="/essControlServlet">
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="matchview" />
	<input type="hidden" name="matchNo" value="" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr>
			<td align="right" width="15%">开始日期</td>
			<td align="left" width="15%"><input type="text" name="sDate" size="10" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
			<td align="right" width="15%">结束日期</td>
			<td align="left" width="15%"><input type="text" name="eDate" size="10" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>
			<td align="right" width="25%"><input type="text" name="key" value="<%=StringUtil.toCN(request.getParameter("key")) %>" /></td>
			<td align="left" width="15%"><img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:Search();"></td>
		</tr>
	</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td align="center" bgcolor="#F5F5F5" nowrap>工号</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班人</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>部门</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>申请日期</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班类型</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班时段</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>备注</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>决裁情况</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>人事确认</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>删除</td>
  </tr>
<%for(int i=0;i<matchList.size();i++){
	essMatchBean = (EssMatchBean) matchList.get(i);%>
  <tr align="center">
    <td align="center" nowrap><%=essMatchBean.getEmpID() %></td>
    <td align="center" nowrap><%=essMatchBean.getChineseName() %></td>
    <td align="center" nowrap><%=essMatchBean.getDeptName() %></td>
    <td align="center" nowrap><%=essMatchBean.getCreateDate() %></td>
    <td align="center" nowrap><%=essMatchBean.getMatchTypeName() %></td>
    <td align="center" nowrap><%=essMatchBean.getMatchFromTime() %><br><%=essMatchBean.getMatchToTime() %></td>
    <td align="center" width="70"><%=StringUtil.checkNull(essMatchBean.getRemark(), "&nbsp;")%></td>
    <td align="center" nowrap>
	    <%essAffirmorList = essMatchBean.getAffirmorList();
	    if (essAffirmorList.size() > 0) {
		    for(int j=0;j<essAffirmorList.size();j++){
		    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
		    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
	    		<%=essAffirmor.getAffirmorName() + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
		    	</font><br>
		    <%}
		} else {%>
			&nbsp;
		<%}%>
    </td>
    <td align="center" nowrap>
		<font color="<%=(String) colorMap.get(String.valueOf(essMatchBean.getActivity()))%>">
			<%=(String) confirmMap.get(String.valueOf(essMatchBean.getActivity()))%>
		</font>
	</td>
    <td align="center" nowrap>
   		<%if (essMatchBean.getOpFlag() == 3) {%>
    		<span style="color:red; cursor:pointer;" onClick="Delete(<%=essMatchBean.getMatchNo()%>);">删除</span>
		<%} else out.print("&nbsp;");%>
	</td>
  </tr>
<%}%>
</table>

<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
	<%if(pageControl.getPageCount() > 10 && pageControl.getCurrentPage() > 10){%>
	  <td width="11%"><img src="/images/btn/p_last101.gif" align="absmiddle" style="cursor:pointer;" onClick="JumpPage(<%=((pageControl.getCurrentPage()-1)/10)*10%>)"></td>
	<%}%>
	<td>&nbsp;</td>
	<%int count = 0;
	for(int i=((pageControl.getCurrentPage()-1)/10)*10+1;count<10 && i<=pageControl.getPageCount();i++){
	  count++;%>
	  <td><b>
	  <%if (i==pageControl.getCurrentPage()) {%>
	  	<span style="color:#6600CC;"><%=i%></span>
	  <%} else {%>
	    <span style="color:#666666; cursor:pointer;" onClick="JumpPage(<%=i%>);"><%=i%></span>
	  <%}%>
	  </b></td><td>&nbsp;</td>
	<%}%>
	<%if(pageControl.getPageCount() > 10 && ((pageControl.getCurrentPage()-1)/10)*10+11 <= pageControl.getPageCount()){%>
	  <td width="11%"><img src="/images/btn/p_next101.gif" align="absmiddle" style="cursor:pointer;" onClick="JumpPage(<%=((pageControl.getCurrentPage()-1)/10)*10+11%>)"></td>
	<%}%>
  </tr>
</table>
</form>
</body>
</html>
