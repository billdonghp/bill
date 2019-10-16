<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.utils.FormUtil"%>
<jsp:useBean id="sysparam" class="com.ait.sysparam.HrSysparam" scope="request" />
<html>
<head>
<title>人事参数设置</title>
<link rel="stylesheet" type="text/css" href="/css/default.css">
<link rel="stylesheet" type="text/css" href="/css/zpss.css">
<script type="text/javascript">
function Save() {
	document.SysparamForm.operation.value="modify";
	document.SysparamForm.submit();
}
</script>
</head>
<body>
<jsp:include flush="true" page="/inc/toolbar_ads.jsp"></jsp:include>
<form name="SysparamForm" method="post" action="/syControlServlet">
  <input type="hidden" name="operation" value="view" />
  <input type="hidden" name="content" value="hrparam" />
  <input type="hidden" name="menu_code" value="sy0201" />
  <table width="100%" >
    <tr>
      <td>
      <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
          <tr>
            <td bgcolor="#F5F5F5" height="30" width="50%">试用期月数</td>
            <td class="tablecontent"><%=FormUtil.IntInput("probationalMonths", sysparam.getProbationalMonths())%></td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
</body>
</html>
