<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">

function back(){
	parent.document.all.layername.style.visibility='hidden';
}
</script>

<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">

<span class="title1">通过搜索引擎查找员工</span></p>
<form action="/hrControlServlet" method="post" name="searchForm" >
	<input type="hidden" name="operation" value="hr_search">
	<input type="hidden" name="destination" value="">
	<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td class="search_title_01">
				<%@ include file="../hr/engine_department.jsp"%>
				<input type="text" name="key" value="<%=StringUtil.toCN(request.getParameter("key"))%>" style="width:90px;" />
				<%for(int i=0;i<statusVector.size();i++){
					statusMap = (HashMap) statusVector.get(i);%>
					<input type="checkbox" name="status" value="<%=statusMap.get("code")%>" /><%=statusMap.get("name")%>&nbsp;
				<%}%>
				<input name="submit" type="submit" value="查找" width="52" height="19" align="absmiddle">
			</td>
		</tr>	
	</table>
</form>

<form action="" method="post" name="dd" target="_parent">

  <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
    <tr onclick="back();" style="position: relative; top: expression(this.offsetParent.scrollTop);" title="点击表行关闭窗口"> 
      <td height="25" align="center" bgcolor="#F5F5F5" >工号</td>
      <td height="25" align="center" bgcolor="#F5F5F5" >姓名</td>
      <td height="25" align="center" bgcolor="#F5F5F5" >拼音</td>
      <td height="25" align="center" bgcolor="#F5F5F5" >部门名称</td>
    </tr>
    <c:choose>
         <c:when test="${empListCnt > 0}">
				<c:forEach items="${empList}" var="oneResult">
					<tr onClick="HighLightTR('#99CCFF','black','<c:out value="${oneResult.empID}"/>','<c:out value="${oneResult.chineseName}"/>')" > 
				      <td  class="info_search_02" height="25" align="center"><c:out value="${oneResult.empID}"/>&nbsp;</td>
				      <td  class="info_search_02" height="25" align="center"><c:out value="${oneResult.chineseName}"/>&nbsp;</td>
				      <td  class="info_search_02" height="25" align="center"><c:out value="${oneResult.pinyinName}"/>&nbsp;</td>
				      <td  class="info_search_02" height="25" align="center"><c:out value="${oneResult.department}"/>&nbsp;</td>
				    </tr>
				</c:forEach>
         </c:when>
         <c:otherwise>
				   <tr align="center"><td colspan="4"　class="info_search_02"> 没有您要查的人员！&nbsp;</td></tr>
         </c:otherwise>     
    </c:choose>
  </table>
</form>
</body>
</html>
<script language="JavaScript" type="text/JavaScript">

function HighLightTR(backColor,textColor,i,name){
	var t;
	if(typeof(preEl)!='undefined') {
	preEl.bgColor=orgBColor;
	
	try{
		ChangeTextColor(preEl,orgTColor);}catch(e){;}
	}
	var el = event.srcElement;
	el = el.parentElement;
	orgBColor = el.bgColor;
	orgTColor = el.style.color;
	el.bgColor=backColor;
	try{ChangeTextColor(el,textColor);}catch(e){;}
	preEl = el;
	ID=i;
	parent.document.all.empID.value=name;
	parent.document.all.layername.style.visibility='hidden';
	parent.location.href='/hrmControlServlet?operation=${nextOperation}&empID=' + ID + '&menu_code=<c:out value="${menu_code}"/>';

}

</script>