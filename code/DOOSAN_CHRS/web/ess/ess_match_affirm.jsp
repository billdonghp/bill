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
    function findObject(object , tag){
      if(object == null || typeof(object) != "object") return null ;
      var node = object.parentElement ;
      if(node == null) return null ;
      if(node.tagName == tag)
        return node ;
      else
       return findObject(node , tag) ;
    }
    function findRow(object){
       return findObject(object , "TR") ;
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

function Search() {
	//重新搜索时应重置当前页数
	document.ApplyForm.currentPage.value='1';
	document.ApplyForm.submit();
}
function JumpPage(currentPage) {
	document.ApplyForm.currentPage.value=currentPage;
	document.ApplyForm.submit();
}
//单个决裁
function doAffirm_single(element, flag) {
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" && !document.ApplyForm.elements[i].disabled)
       document.ApplyForm.elements[i].checked = false;
  }  
  var row = findRow(element);  
  var jumpCyc = false;  
  for(k = 0 ; k < row.cells.length; k++){
      var objects = row.cells[k].all;
      for (j = 0; j < objects.length; j++){
        if(objects[j].type=="checkbox"){
           objects[j].checked = !objects[j].disabled;
           jumpCyc = true;
           break;
        }
      }
      if (jumpCyc) break;
  }
  doAffirm(flag);
}
//批量决裁
// flag 1 通过, 2 否决
function doAffirm(flag){
	var msg = "通过";
	if (flag == "2") {
		msg = "否决";
	}
	var existSelected = false;//是否有选择的记录
  for(var i=0;i<document.ApplyForm.elements.length;i++)
  {
    if(document.ApplyForm.elements[i].type=="checkbox" 
        && document.ApplyForm.elements[i].checked && !document.ApplyForm.elements[i].disabled)
    {
      var affirmNo = document.ApplyForm.elements[i].value;
      var currenFlag = document.getElementById("opFlag" + affirmNo).value;
      if (currenFlag != "0" && currenFlag != flag) {//当前记录只能通过或否决，与操作不一致，忽略
          document.ApplyForm.elements[i].checked = false;
      } else
        existSelected = true;
    }
  }
  if (!existSelected){
      alert("请选择要[" + msg + "]的记录！");
      return false;
  }
	if (confirm("您确定要["+msg+"]当前已选择的申请吗?")) {
		document.ApplyForm.operation.value="modify";
		document.ApplyForm.flag.value=flag;
		document.ApplyForm.submit();
	}
  
}
</script>
<div align="left">
<span class="title1">决裁信息&gt;值班决裁</span></div>
<form name="ApplyForm" action="/essControlServlet">
	<input type="hidden" name="operation" value="view" />
	<input type="hidden" name="content" value="matchaffirm" />
<!-- 	<input type="hidden" name="affirmno" value="" /> -->
	<input type="hidden" name="flag" value="" />
	<input type="hidden" name="menu_code" value="<%=menu_code%>" />
	<input type="hidden" name="currentPage" value="<%=pageControl.getCurrentPage()%>" />	
	<input type="hidden" name="ck_all" value="0" />
	<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		<tr>
			<td align="right" width="11%">开始日期</td>
			<td align="left" width="15%"><input type="text" name="sDate" size="10" maxlength="10" value="<%=sDate%>" readonly onClick="setday(this);" /></td>
			<td align="right" width="11%">结束日期</td>
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
			
			<td align="right" width="15%"><input type="text" name="key" value="<%=StringUtil.toCN(request.getParameter("key")) %>" /></td>
			<td align="left" width="13%"><img align="absmiddle" src="../images/btn/search.gif" onClick="javascript:Search();"></td>
            <td align="left" width="10%"><input type="button" name="btnAffirm_t" value="批量通过" onclick="doAffirm(1)"></td>
            <td align="left" width="10%"><input type="button" name="btnAffirm_f" value="批量否决" onclick="doAffirm(2)"></td>
		</tr>
	</table>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr>
    <td align="center" bgcolor="#F5F5F5" nowrap><a href="#" onclick="checkAll();">全选</a></td>
    <td align="center" bgcolor="#F5F5F5" nowrap>工号</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班人</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>部门</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>申请日期</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班类型</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>值班时段</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>决裁情况</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>人事确认</td>
    <td align="center" bgcolor="#F5F5F5" nowrap>备注</td>
  </tr>
<%for(int i=0;i<matchList.size();i++){
	essMatchBean = (EssMatchBean) matchList.get(i);
    String remark = StringUtil.checkNull(essMatchBean.getRemark(),"");
    essAffirmorList = essMatchBean.getAffirmorList();
    int applyNo = essMatchBean.getMatchNo();
    int affirmNo = 0;
    int opFlag = essMatchBean.getOpFlag();
    if (opFlag >= 0) {
      for(int j=0;j<essAffirmorList.size();j++){
	    essAffirmor = (EssAffirmor) essAffirmorList.get(j);
        if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
            affirmNo = essAffirmor.getAffirmNo();            
	    } 
	  }  		
	}  
	String checkState = affirmNo > 0 ? "" : "disabled";
%>
  <tr align="center">
    <td align="center" nowrap><%if (essMatchBean.getOpFlag()>=0) {%><input type="checkbox" name="affirmNo" value="<%=affirmNo%>" <%=checkState%>>
    <input type="hidden" name="applyNo<%=affirmNo%>" value="<%=applyNo%>">
    <input type="hidden" id="opFlag<%=affirmNo%>" name="opFlag<%=affirmNo%>" value="<%=opFlag%>"><%} else {%>&nbsp;<%}%>
    </td>
    <td align="center" nowrap><%=essMatchBean.getEmpID() %></td>
    <td align="center" nowrap><%=essMatchBean.getChineseName() %></td>
    <td align="center" nowrap><%=essMatchBean.getDeptName() %></td>
    <td align="center" nowrap><%=essMatchBean.getCreateDate() %></td>
    <td align="center" nowrap><%=essMatchBean.getMatchTypeName() %></td>
    <td align="center" nowrap><%=essMatchBean.getMatchFromTime() %><br><%=essMatchBean.getMatchToTime() %></td>
    <td align="center" nowrap>
	    <%
	    for(int j=0;j<essAffirmorList.size();j++){
	    	essAffirmor = (EssAffirmor) essAffirmorList.get(j);%>
	    	<font color="<%=(String) colorMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>">
    		<%=essAffirmor.getAffirmorName() + " " + (String) statusMap.get(String.valueOf(essAffirmor.getAffirmFlag()))%>
	    	<%if (essAffirmor.getAffirmorId().equals(admin.getAdminID())) {
	    		if (essMatchBean.getOpFlag() == 0) {%>
	    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">通过</span>&nbsp;|&nbsp;<span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 2);">否决</span>
	    		<%} else if (essMatchBean.getOpFlag() == 1) {%>
	    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 1);">通过</span>
	    		<%} else if (essMatchBean.getOpFlag() == 2) {%>
	    			<br><span style="color:red; cursor:pointer;" onClick="doAffirm_single(this, 2);">否决</span>
				<%}
			}%>
	    	</font><br>
	    <%}%>
    </td>
    <td align="center" nowrap>
		<font color="<%=(String) colorMap.get(String.valueOf(essMatchBean.getActivity()))%>">
			<%=(String) confirmMap.get(String.valueOf(essMatchBean.getActivity()))%>
		</font>
	</td>
	<td align="center" nowrap><textarea name="remark<%=affirmNo%>"><%=remark%></textarea></td>
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
