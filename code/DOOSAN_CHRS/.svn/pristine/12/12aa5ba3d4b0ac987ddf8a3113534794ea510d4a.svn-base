<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.ess.bean.EssCardApplyBean"%>
<%@ page import="com.ait.ess.bean.EssAffirmor"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="colorMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="confirmMap" class="java.util.HashMap" scope="request" />
<jsp:useBean id="pageControl" class="com.ait.ess.bean.PageControl" scope="request" />
<jsp:useBean id="applyList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="essCardApplyBean" class="com.ait.ess.bean.EssCardApplyBean" scope="page" />
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
function Confirm(applyNo, flag, batchTag) {
	var msg = "通过";
	if (flag == "2") {
		msg = "否决";
	}
	var tags = document.getElementsByName("ck_selectedTag");
	if (tags == null || tags.length == null){
		alert("没有需要"+msg+"的申请。");
		return false;
	}
	var c = 0;
	for (var i=0; i<tags.length; i++){
		if(tags(i).checked == true){
			c++;
		}
	}
	if (c==0 && applyNo == ''){
		alert("请选择要[" + msg + "]的申请。");
		return false;
	}	
	if (confirm("您确定要"+msg+"这条申请?")) {
		document.ApplyForm.operation.value="modify";
		document.ApplyForm.applyNo.value=applyNo;
		document.ApplyForm.flag.value=flag;
		document.ApplyForm.batchTag.value=batchTag;
		document.ApplyForm.submit();
	}
}
function checkAll()
{
  var selected = document.ApplyForm.ck_all.value == "0" ? true : false;
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
    {
      document.ApplyForm.elements[i].checked = selected;
    }
  }
  document.ApplyForm.ck_all.value = selected ? "1" : "0";
}

</script>
<div align="left">
<span class="title1">人事确认&gt;漏卡申请确认</span></div>
<form name="ApplyForm" action="/essControlServlet">
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="cardconfirm" />
	<input type="hidden" name="applyNo" value="" />
	<input type="hidden" name="flag" value="" />
	<input type="hidden" name="batchTag" value="" />
	<input type="hidden" name="ck_all" value="0" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr>
			<td align="right" width="15%">开始日期</td>
			<td align="left" width="15%"><input type="text" name="sDate" size="10" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
			<td align="right" width="15%">结束日期</td>
			<td align="left" width="15%"><input type="text" name="eDate" size="10" maxlength="10" value="<%=eDate%>" readonly onClick="setday(this);" /></td>
						<td align="right" width="11%">
	<%
	   String qryType = request.getParameter("qryType"); 
	%>
	<%! String selected(String valueSel, String value){
	      return valueSel.equals(value) ? "selected" : "";
	    }
	 %>
			     <select name="qryType">
			         <option value="0" <%= selected("0", qryType) %> ></option>   
			         <option value="1" <%= selected("1", qryType) %>>全部决裁</option>  
			         <option value="2" <%= selected("2", qryType) %>>部分决裁</option> 
			         <option value="3" <%= selected("3", qryType) %>>未决裁</option>                
			     </select>
			</td>
			
			<td align="right" width="25%"><input type="text" name="key" value="<%=StringUtil.toCN(request.getParameter("key")) %>" /></td>
			<td align="left" width="15%"><img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:Search();"></td>
			<td align="left" width="15%"><input type="button" value="批量确认" onclick="Confirm('',1,true)"/> </td>
			<td align="left" width="15%"><input type="button" value="批量否决" onclick="Confirm('',2,true)"/></td>
		</tr>
	</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td align="center" bgcolor="#F5F5F5" nowrap><a href="#" onclick="checkAll();">全选</a></td>
    <td align="center" bgcolor="#F5F5F5" nowrap>工号</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>休假人</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>部门</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>申请日期</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>打卡时间</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>进出门标志</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>申请原因</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>决裁情况</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>人事确认</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>备注</td>
  </tr>
<%for(int i=0;i<applyList.size();i++){
	essCardApplyBean = (EssCardApplyBean) applyList.get(i);%>
  <tr align="center">
    <td><%if (essCardApplyBean.getOpFlag()>=0) {%>
    	<input type="checkbox" name="ck_selectedTag" value="<%=essCardApplyBean.getApplyID()%>"/>
    	<input type="hidden" name="hidden_applyNo" value="<%=essCardApplyBean.getApplyID() %>" ><%} else {%>&nbsp;<%}%>
    </td>
    <td align="center" nowrap><%=essCardApplyBean.getApplyID() %></td>
    <td align="center" nowrap><%=essCardApplyBean.getChineseName() %></td>
    <td align="center" nowrap><%=essCardApplyBean.getDeptName() %></td>
    <td align="center" nowrap><%=essCardApplyBean.getCreateDate() %></td>
    <td align="center" nowrap><%=essCardApplyBean.getCardTime() %></td>
    <td align="center" nowrap><%=essCardApplyBean.getDoorType() %></td>
    <td align="left"><%=StringUtil.checkNull(essCardApplyBean.getApplyReason(), "无")%></td>
    <td align="center" nowrap>
	    <%essAffirmorList = essCardApplyBean.getAffirmorList();
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
		<font color="<%=(String) colorMap.get(String.valueOf(essCardApplyBean.getActivity()))%>">
			<%=(String) confirmMap.get(String.valueOf(essCardApplyBean.getActivity()))%>
    		<%if (essCardApplyBean.getOpFlag() == 0) {%>
    			<br><span style="color:red; cursor:pointer;" onClick="Confirm(<%=essCardApplyBean.getApplyID() %>, 1, false);">通过</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" onClick="Confirm(<%=essCardApplyBean.getApplyID()%>, 2, false);">否决</span>
    		<%} else if (essCardApplyBean.getOpFlag() == 1) {%>
    			<br><span style="color:red; cursor:pointer;" onClick="Confirm(<%=essCardApplyBean.getApplyID() %>, 1, false);">通过</span>
    		<%} else if (essCardApplyBean.getOpFlag() == 2) {%>
    			<br><span style="color:red; cursor:pointer;" onClick="Confirm(<%=essCardApplyBean.getApplyID() %>, 2, false);">否决</span>
			<%}%>
		</font>
	</td>
	<td align="left" style="width: 80px "><textarea name="text_remark" rows="2"><%=StringUtil.checkNull(essCardApplyBean.getRemark(), "")%></textarea></td>
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