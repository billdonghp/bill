<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@page import="java.util.*,com.ait.evs.EvsColumn"%>

<html>
<head>
<title>评价&gt;评价进行&gt;细则添加 
</title>
</head>
<body>
<%
  String action = request.getParameter("Action");
  String empID = request.getParameter("EmpID");
  String period = request.getParameter("Period");

  String detailID1 = "";
  String column001 = "";
  String column002 = "";
  String column003 = "";
  String column004 = "";
  String column005 = "";
  String column006 = "";
  String column016 = "";
  String detailProp = "";

  int seqDetail = 0;
  if(request.getProtocol().equals("HTTP/1.0")) { 
    response.setHeader("Pragma", "no-cache");
  } else if(request.getProtocol().equals("HTTP/1.1")) {
    response.setHeader("Cache-Control", "no-cache");
  }
  response.setDateHeader("Expires", 0);
  if (action!=null && action.equals("modify")) {
    EvsColumn evsColumn = new EvsColumn();
    seqDetail = new Integer(request.getParameter("SeqDetail")).intValue();
    Hashtable h = evsColumn.getEvsColumnHByDetailID(seqDetail);
    column001 = (h.get("EVCOLUMN001") != null) ? h.get("EVCOLUMN001").toString() : "";
    column002 = (h.get("EVCOLUMN002") != null) ? h.get("EVCOLUMN002").toString() : "";
    column003 = (h.get("EVCOLUMN003") != null) ? h.get("EVCOLUMN003").toString() : "";
    column004 = (h.get("EVCOLUMN004") != null) ? h.get("EVCOLUMN004").toString() : "";
    column005 = (h.get("EVCOLUMN005") != null) ? h.get("EVCOLUMN005").toString() : "";
    column006 = (h.get("EVCOLUMN006") != null) ? h.get("EVCOLUMN006").toString() : "";
    column016 = (h.get("EVCOLUMN016") != null) ? h.get("EVCOLUMN016").toString() : "";
    detailProp = (h.get("detailProp") != null) ? h.get("detailProp").toString() : "";

  }
  
%>
<br />
<link href="/css/default.css" rel="stylesheet" type="text/css">
<form name="ColumnDetail" method="post" target="self" action="evs0205_t.jsp">
  <input type="hidden" name="Action" value="${param.Action}"/>
  <input type="hidden" name="EmpID" value="${param.EmpID}"/>
  <input type="hidden" name="Period" value="${param.Period}"/>
  <input type="hidden" name="type" value="${param.type}"/>
<%
  if (seqDetail != 0) {
%>
  <input type="hidden" name="SeqDetail" value="<%=seqDetail%>"/>
<%
  }
%>
<table width="90%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center" bgcolor="#E5E5E5">
    <td height="30" colspan="6">业绩目标设定</td>
  </tr>
  <tr>
  	<c:if test="${param.type == 'EVTYPE003' or  param.type == 'EVTYPE004'}">
	    <td bgcolor="#F5F5F5" align="center">评价项目</td>
	    <td colspan="3" align="left">
	      <input name="column001" size="25" type="text" value="<%=column001%>"/>
	    </td>
    </c:if>
    <c:if test="${param.type == 'EVTYPE005' or  param.type == 'EVTYPE006'}">
	    <td bgcolor="#F5F5F5" align="center">产品名称</td>
	    <td align="left">
	      <input name="column002" size="15" type="text" value="<%=column002%>"/>
	    </td>
	    <td bgcolor="#F5F5F5" align="center">评价指标</td>
    	<td align="left">
    		<input name="column003" size="25" type="text" value="<%=column003%>"/>
    	</td>
    </c:if>
    <td bgcolor="#F5F5F5" align="center">
      目标类别
    </td>
    <td>
      &nbsp;
      <select name="itemCode">
      	<c:if test="${param.type == 'EVTYPE003' or  param.type == 'EVTYPE004'}">
      		<option value="EVITEM002">业绩</option>
      	</c:if>
        <c:if test="${param.type == 'EVTYPE005' or  param.type == 'EVTYPE006'}">
        	<option value="EVITEM007">定量评价</option>
        </c:if>
      </select>
    </td>
  </tr>
  <tr>
  <c:if test="${param.type == 'EVTYPE003' or  param.type == 'EVTYPE004'}">
	  </tr>
	    <td bgcolor="#F5F5F5" align="center">推进业绩</td>
	    <td colspan="5" align="left">
	      <TEXTAREA name="column016" cols="65" rows="5"><%=column016%></TEXTAREA>
	    </td>
	  </tr>
	  <tr align="center">
	    <td bgcolor="#F5F5F5">比重</td>
	    <td colspan="5" align="left">
	      <input name="DetailProp" type="text" size="3" value="<%=detailProp%>"/> %
	    </td>
	  </tr>
  </c:if>
  <c:if test="${param.type == 'EVTYPE005' or  param.type == 'EVTYPE006'}">
  	  </tr>
	    <td bgcolor="#F5F5F5" align="center">目标</td>
	    <td colspan="5" align="left">
	      <input name="column004" type="text" size="25" value="<%=column004%>"/>
	    </td>
	  </tr>
	  <tr align="center">
	    <td bgcolor="#F5F5F5">实绩</td>
	    <td colspan="5" align="left">
	      <input name="column005" type="text" size="25" value="<%=column005%>"/> 
	    </td>
	  </tr>
	  <tr align="center">
	    <td bgcolor="#F5F5F5">达成率</td>
	    <td colspan="5" align="left">
	      <input name="column006" type="text" size="25" value="<%=column006%>"/> 
	    </td>
	  </tr>
	  <input name="DetailProp" type="hidden" value="0"/>
  </c:if>
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
		//href();
	}
}
function href(){
	<%if (action!=null &&!action.equals("modify")) {%>
	if(confirm(" 继续添加 ? ")){
      document.ColumnDetail.reset();
    }else{
      //window.close();
    }
    <%
    }else{
    //out.print(" window.close(); ");
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
