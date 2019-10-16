<%@page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@page import="java.util.*,
                com.ait.util.*,
                com.ait.evs.EvsColumn"%>
<html>
<head>
<title>评价
&gt;
评价进行
&gt;
细则添加
</title>
<style type="text/css">
  <!--
    .style1 {color: #FF00FF}
  -->
</style>
</head>
<body>
<%
  String action = request.getParameter("Action");
  String empID = request.getParameter("EmpID");
  String year = request.getParameter("year");

  String detailID1 = "";
  String column001 = "";
  String column002 = "";
  String detailProp = "";
  String column003 = "";
  String column004 = "";
  String column005 = "";
  String column006 = "";
  String column007 = "";
  String column008 = "";
  int seqDetail = 0;
  if(request.getProtocol().equals("HTTP/1.0")) { // HTTP/1.0
    response.setHeader("Pragma", "no-cache");
  } else if(request.getProtocol().equals("HTTP/1.1")) {
    response.setHeader("Cache-Control", "no-cache");
  }
  response.setDateHeader("Expires", 0);
  if (action!=null && action.equals("modify")) {
    EvsColumn evsColumn = new EvsColumn();
    seqDetail = new Integer(request.getParameter("SeqDetail")).intValue();
    Hashtable h = evsColumn.getYearEvsColumnByDetailID(seqDetail);
    detailID1 = (h.get("detailID1") != null) ? h.get("detailID1").toString() : "";
    column001 = (h.get("column001") != null) ? h.get("column001").toString() : "";
    column002 = (h.get("column002") != null) ? h.get("column002").toString() : "";
    detailProp = (h.get("detailProp") != null) ? h.get("detailProp").toString() : "";
    column003 = (h.get("column003") != null) ? h.get("column003").toString() : "";
    column004 = (h.get("column004") != null) ? h.get("column004").toString() : "";
    column005 = (h.get("column005") != null) ? h.get("column005").toString() : "";
    column006 = (h.get("column006") != null) ? h.get("column006").toString() : "";
    column007 = (h.get("column007") != null) ? h.get("column007").toString() : "";
    column008 = (h.get("column008") != null) ? h.get("column008").toString() : "";
  }
  
%>
<br />
<link href="/css/default.css" rel="stylesheet" type="text/css">
<form name="ColumnDetail" method="post"  target="self" action="evs0201_t.jsp">
  <input type="hidden" name="Action" value="<%=action%>"/>
  <input type="hidden" name="EmpID" value="<%=empID%>"/>
  <input type="hidden" name="year" value="<%=year%>"/>
<%
  if (seqDetail != 0) {
%>
  <input type="hidden" name="SeqDetail" value="<%=seqDetail%>"/>
<%
  }
%>
<table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center" bgcolor="#E5E5E5">
    <td height="30" colspan="6">业务目标设定</td>
  </tr>
  <tr>
    <td bgcolor="#F5F5F5" align="center">评价项目</td>
    <td colspan="3" align="left">
      <input name="column001" size="25" type="text" value="<%=column001%>"/>
    </td>
    <td bgcolor="#F5F5F5" align="center">
      目标类别
    </td>
    <td>
      <select name="itemCode">
        <option value="EVITEM001">业务目标</option>
      </select>
    </td>
  </tr>
  <tr>
  </tr>
    <td bgcolor="#F5F5F5" align="center">细分内容</td>
    <td colspan="5" align="left">
      <input name="column002" size="40" type="text" value="<%=column002%>"/>
    </td>
  </tr>
  <tr align="center">
    <td bgcolor="#F5F5F5">比重</td>
    <td colspan="5" align="left">
      <input name="DetailProp" type="text" size="3" value="<%=detailProp%>"/> %
    </td>
  </tr>
  <tr>
    <td colspan="6" align="center">详细目标</td>
  </tr>
  <tr align="center">
    <td bgcolor="#F5F5F5" width="60">上半年</td>
    <td>
      <input name="column003" type="text" value="<%=column003%>"/>
    </td>
    <td bgcolor="#F5F5F5" width="60">下半年</td>
    <td>
      <input name="column004" type="text" value="<%=column004%>"/>
    </td>
    <td bgcolor="#F5F5F5" width="60">全年度</td>
    <td>
      <input name="column005" type="text" value="<%=column005%>"/>
    </td>
  </tr>
  <tr>
    <td colspan="6" align="center">评价尺度(年度目标)</td>
  </tr>
  <tr align="center">
    <td bgcolor="#F5F5F5" width="60">上</td>
    <td>
      <input name="column006" type="text" value="<%=column006%>"/>
    </td>
    <td bgcolor="#F5F5F5" width="60">中</td>
    <td>
      <input name="column007" type="text" value="<%=column007%>"/>
    </td>
    <td bgcolor="#F5F5F5" width="60">下</td>
    <td>
      <input name="column008" type="text" value="<%=column008%>"/>
    </td>
  </tr>
  <tr align="center">
    <td colspan="6" height="60" valign="bottom">
      <input type="button" value="保存" onclick="subm();"/>
      &nbsp;&nbsp;
      <input type="reset" value="重填"/>
      &nbsp;&nbsp;
      <input type="button" value="关闭" onClick="window.close();" />
    </td>
  </tr>
</table>
</form>
<p>
</body>
</html>
<script language="JavaScript">
<!--
function subm(){
	if(checkProp()){
		document.ColumnDetail.submit();
		href();
	}
}
function href(){
	<%if (action!=null &&! action.equals("modify")) {%>
		if(confirm(" 继续添加 ? ")){
	      document.ColumnDetail.reset();
	    }else{
	      window.close();
	    }
    <%
    }
    %>
}
function checkProp(){
	var prop;
	prop=document.ColumnDetail.DetailProp.value;
	if(isNaN(prop)){
		alert("项目比重只能为数字！");
		document.ColumnDetail.DetailProp.value="";
		document.ColumnDetail.DetailProp.focus();
		return false;
	}
	return true;
}
//-->
</script>
