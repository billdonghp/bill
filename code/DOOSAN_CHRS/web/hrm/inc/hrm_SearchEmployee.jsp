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
<form action="" method="post" name="dd" target="_parent">

  <table width="380" border="0" cellpadding="0" cellspacing="1" class="dr_d"><!-- 点击表行关闭窗口  -->
    <tr onclick="back();" style="position: relative; top: expression(this.offsetParent.scrollTop);" title="<ait:message  messageID="alert.mutual.click_close"/>"> 
      <td height="25" class="info_title_01"><ait:message  messageID="display.mutual.emp_id"/></td>
      <td height="25" class="info_title_01"><ait:message  messageID="display.mutual.name"/></td>
      <td height="25" class="info_title_01"><ait:message  messageID="display.mutual.pin_yin"/></td>
      <td height="25" class="info_title_01"><ait:message  messageID="display.mutual.dept"/></td>
    </tr>
    <c:choose>
         <c:when test="${empListCnt > 0}">
				<c:forEach items="${empList}" var="oneResult">
					<tr onClick="HighLightTR('#99CCFF','black','<c:out value="${oneResult.empID}"/>','<c:out value="${oneResult.chineseName}"/>')" > 
				      <td  class="info_search_02" height="25" align="center" nowrap="nowrap"><c:out value="${oneResult.empID}"/>&nbsp;</td>
				      <td  class="info_search_02" height="25" align="center" nowrap="nowrap">
						<ait:content enContent="${oneResult.pinyinName}" zhContent="${oneResult.chineseName}" koContent="${oneResult.koreanName}"/>&nbsp;
				      </td>
				      <td  class="info_search_02" height="25" align="center" nowrap="nowrap"><c:out value="${oneResult.pinyinName}"/>&nbsp;</td>
				      <td  class="info_search_02" height="25" align="center" nowrap="nowrap">
						<ait:content enContent="${oneResult.englishDept}" zhContent="${oneResult.department}" koContent="${oneResult.korDept}"/>&nbsp;
			
				      </td>
				    </tr>
				</c:forEach>
         </c:when>
         <c:otherwise>
				   <tr align="center"><td colspan="4"　class="info_content_01"> 
				   <ait:message  messageID="display.mutual.the_employee_you_searched_is_not_available"/>&nbsp;
				   </td>
				  </tr>
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
	
	if("${id}".toUpperCase().indexOf("ID") != -1)
		parent.document.getElementById('${id}').value=ID;
	else 
		parent.document.getElementById('${id}').value=name;
		
	parent.document.all.layername.style.visibility='hidden';
	
	parent.location.href='/hrmControlServlet?operation=${nextOperation}&empID=' + ID + '&menu_code=<c:out value="${menu_code}"/>';

}

</script>