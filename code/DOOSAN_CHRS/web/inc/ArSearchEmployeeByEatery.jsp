<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.util.*,com.ait.hrm.bean.BasicInfo,com.ait.sqlmap.util.SimpleMap,java.util.*" errorPage="" %>
<jsp:useBean id="services" class="com.ait.ar.business.ArServices" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="simpleMap" class="com.ait.sqlmap.util.SimpleMap" />
<%@ include file="taglibs.jsp"%>
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
parameterObject.setString("SUPERVISOR_ID", admin.getAdminID());
List employee_list = services.retrieveEmpListByEatery(parameterObject);
request.setAttribute("employee_list",employee_list);
%>
<script language="javascript" type="text/javascript">
function FillCardNo (cardNo, name) {
	if(parent.document.getElementById('<%=id%>')){
		parent.document.getElementById('<%=id%>').value = cardNo;
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
      <td height="25" class="info_title_01"><!--卡号-->
					<ait:message  messageID="display.mutual.card_no"/></td>
      <td height="25" class="info_title_01"><!--姓名-->
					<ait:message  messageID="display.mutual.name"/></td>
      <td height="25" class="info_title_01"><!--开始日期-->
					<ait:message  messageID="display.mutual.start_date"/></td>
      <td height="25" class="info_title_01"><!--结束日期-->
					<ait:message  messageID="display.mutual.end_date"/></td>
    </tr>
   <%if( employee_list.size()!=0){%>
	   <c:forEach items="${employee_list}" var="employee_list">
    <% // for(int i=0;i<employee_list.size();i++){
    	//   simpleMap = (SimpleMap)employee_list.get(i);%>
    <tr onClick="javascript:FillCardNo('${employee_list.CARD_NO}','${employee_list.CHINESENAME}');" style="cursor:pointer;"> 
       <td  class="info_search_02" height="25" align="center">${employee_list.CARD_NO}&nbsp;</td>
       <td  class="info_search_02" height="25" align="center">
       <ait:content enContent="${employee_list.CHINESE_PINYIN}" zhContent="${employee_list.CHINESENAME}" koContent="${employee_list.KOREANNAME}"/>
       &nbsp;</td>
       <td  class="info_search_02" height="25" align="center">${employee_list.FROM_DATE}&nbsp;</td>
       <td  class="info_search_02" height="25" align="center">${employee_list.TO_DATE}&nbsp;</td>
    </tr>
   <%//}%></c:forEach>
   <%}else{%>
    <tr align="center"> 
      <td colspan="4"　class="info_search_02" height="25" align="center"><br>
      <!-- 没有符合条件的员工或您的考勤权限不足,请检查输入或联系管理员！ -->
       <ait:message messageID="alert.ess.overtime.no_appropriate_and_authority" module="ess"></ait:message></td>
    </tr>
    <%}%>
  </table>
</body>
</html>