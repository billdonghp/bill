<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function document.onselectstart()
{
    var tmpObj= event.srcElement.tagName;
    if ((tmpObj=="INPUT" || tmpObj=="TEXTAREA") && event.srcElement.readOnly==false)
            return true;
    if (tmpObj.toUpperCase()=="DATEPICKER")
            return true;
    return false;
}

var preEl ;
var orgBColor;
var orgTColor;
var ID='';
var MENU_CODE='';

function HighLightTR(backColor,textColor,i,name,dept,post,position){
var t;
if(typeof(preEl)!='undefined') {
preEl.bgColor=orgBColor;

	try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
}
		var el = event.srcElement;
		el = el.parentElement;
		orgBColor = el.bgColor;
		orgTColor = el.style.color;
		el.bgColor=backColor;
		try{ChangeTextColor(el,textColor);}catch(e){;}
		preEl = el;
		ID=i;
		
		parent.document.getElementById("<%=request.getParameter("index")%>").value=i;
		if(parent.document.getElementById("name_"+"<%=request.getParameter("index")%>")){
			parent.document.getElementById("name_"+"<%=request.getParameter("index")%>").innerHTML=name;
		}
		
		if(parent.document.getElementById("dept_"+"<%=request.getParameter("index")%>")){
			parent.document.getElementById("dept_"+"<%=request.getParameter("index")%>").innerHTML=dept;
		}
		if(parent.document.getElementById("post_"+"<%=request.getParameter("index")%>")){
			parent.document.getElementById("post_"+"<%=request.getParameter("index")%>").innerHTML=post;
		}
		if(parent.document.getElementById("position_"+"<%=request.getParameter("index")%>")){
			parent.document.getElementById("position_"+"<%=request.getParameter("index")%>").innerHTML=position;
		}
			
		parent.document.getElementById("layername").style.visibility='hidden';
}

function back(){
	parent.document.getElementById("layername").style.visibility='hidden';
}
</script>
<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
<form action="" method="post" name="dd" target="_parent">
  <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d">
    <tr onclick="javascript:back();"> 
      <td width="57" height="25" class="info_title_01">
				    <!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
      <td width="63" height="25" class="info_title_01">
					<!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
      <td width="57" height="25" class="info_title_01">
      <!-- 拼音 --><ait:message  messageID="display.mutual.pin_yin"/></td>
      <td width="163" height="25" class="info_title_01">
				    <!-- 部门 --><ait:message  messageID="display.mutual.dept"/>n</td>
    </tr>
    <c:choose>
         <c:when test="${empListCnt > 0}">
				<c:forEach items="${empList}" var="oneResult">
					
					<c:if test="${admin.languagePreference == 'zh'}">
						<tr onClick="HighLightTR('#99CCFF','black','<c:out value="${oneResult.empID}"/>','<c:out value="${oneResult.chineseName}"/>','<c:out value="${oneResult.department}"/>','<c:out value="${oneResult.post}"/>','<c:out value="${oneResult.position}"/>')" > 
					</c:if>
					<c:if test="${admin.languagePreference == 'en'}">
						<tr onClick="HighLightTR('#99CCFF','black','<c:out value="${oneResult.empID}"/>','<c:out value="${oneResult.pinyinName}"/>','<c:out value="${oneResult.englishDept}"/>','<c:out value="${oneResult.englishPost}"/>','<c:out value="${oneResult.englishPosition}"/>')" > 
				    </c:if>  
				      <td  class="info_search_02" height="25" align="center">
				      <c:out value="${oneResult.empID}"/></td>
				      <td  class="info_search_02" height="25" align="center">
						<ait:content enContent='${oneResult.pinyinName}' zhContent='${oneResult.chineseName}' koContent='${oneResult.koreanName}'/>
						</td>
				      <td  class="info_search_02" height="25" align="center">
				      <c:out value="${oneResult.pinyinName}"/></td>
				      <td  class="info_search_02" height="25" align="center">
						<ait:content enContent='${oneResult.englishDept}' zhContent='${oneResult.department}' koContent='${oneResult.korDept}'/>
						</td>
				    </tr>
				</c:forEach>
         </c:when>
         <c:otherwise>
				   <tr align="center"><td colspan="4"　class="info_content_01"> <!-- 没有您要查的人员！ -->
				   <ait:message  messageID="display.mutual.the_employee_you_searched_is_not_available"/>
				   &nbsp;</td></tr>
         </c:otherwise>     
    </c:choose>
  </table>
</form>
</body>
</html>