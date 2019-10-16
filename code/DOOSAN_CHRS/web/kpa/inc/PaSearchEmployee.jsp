<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.hrm.bean.BasicInfo,com.ait.sqlmap.util.SimpleMap,java.util.*" errorPage="" %>
<jsp:useBean id="services" class="com.ait.hrm.dao.common.HrmCommonDAO" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="employeebean" class="com.ait.hrm.bean.BasicInfo" />
<%@ include file="../../inc/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">

</head>
<body style="margin-left:0px; margin-top:0px;">
<%
String content = StringUtil.checkNull(request.getParameter("content"));
String id=StringUtil.checkNull(request.getParameter("id"));
String name=StringUtil.checkNull(request.getParameter("name"));
SimpleMap parameterObject = new SimpleMap();
parameterObject.setString("CONDITION", StringUtil.toCN(content));
parameterObject.setString("ADMINID", admin.getAdminID());
List employee_list =(List) services.retrieveEmpList(parameterObject);
request.setAttribute("employeelist",employee_list);
%>
<script language="javascript" type="text/javascript">
function FillEmpID (empid, name) {
	if(parent.document.getElementById('<%=id%>')){
		parent.document.getElementById('<%=id%>').value = empid;
	}
	
	if(parent.document.getElementById('<%=name%>')){
		parent.document.getElementById('<%=name%>').value=name;
	}
	
	parent.document.all.showsearch.style.visibility='hidden';
}
function back(){
	parent.document.all.showsearch.style.visibility='hidden';
}
</script>
 <table width="370" border="0" cellpadding="0" cellspacing="1" class="dr_d"><!-- 点击表行关闭窗口  -->
      <tr onclick="back();" style="position: relative; top: expression(this.offsetParent.scrollTop);" title="<ait:message  messageID="alert.mutual.click_close"/>"> 
      <td height="25" class="info_title_01"><!--工号--><ait:message  messageID="display.mutual.emp_id"/></td>
      <td height="25" class="info_title_01"><!-- 姓名 --><ait:message  messageID="display.mutual.name"/></td>
      <td height="25" class="info_title_01"><!-- 拼音 --><ait:message  messageID="display.mutual.pin_yin"/></td>
      <td height="25" class="info_title_01"><!-- 部门名称 --><ait:message  messageID="display.mutual.department_2"/></td>
    </tr>
   <c:forEach items="${employeelist}" var="oneResult">  		
    <tr onClick="javascript:FillEmpID('${oneResult.empID}','${oneResult.chineseName}')" style="cursor:pointer;"> 
       <td  class="info_search_02" height="25" align="center">${oneResult.empID}&nbsp;</td>
       <td  class="info_search_02" height="25" align="center">
			<ait:content enContent='${oneResult.pinyinName}' zhContent='${oneResult.chineseName}' koContent='${oneResult.koreanName}'/>
			&nbsp;</td>
       <td  class="info_search_02" height="25" align="center">${oneResult.pinyinName}&nbsp;</td>
       <td  class="info_search_02" height="25" align="center">
			<ait:content enContent='${oneResult.englishDept}' zhContent='${oneResult.department}' koContent='${oneResult.korDept}'/>
			&nbsp;</td>
    </tr>
   </c:forEach>
   <c:if test="${fn:length(employeelist) == 0}">
   
    <tr align="center"> 
      <td colspan="4"　class="info_content_01" height="25" ><br>
     <!--没有符合条件的员工或您的考勤权限不足<br><br>请检查输入或联系管理员！-->
		<ait:message  messageID="alert.ess.not_match_authority" module="ar"/></td>
    </tr>
   </c:if> 
  </table>
</body>
</html>