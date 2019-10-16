<%@page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@page import="java.sql.*,
                com.ait.util.StringUtil,
                com.ait.util.SysCodeParser,
                com.ait.evs.EvsColumn,
                com.ait.evs.EvsPeriod,
                com.ait.evs.EvsScore,
                java.util.*"%>

<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价&gt;评价进行&gt;目标设定</title>
<style type="text/css">
  <!--
    .style1 {color: #FF00FF}
  -->
</style>
</head>
<body >
<link href="/css/default.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="/js/evtable.js"></SCRIPT>

<%
  float prop=0f;
  String empID = request.getParameter("EmpID");
  if (empID == null){
	if (admin != null)
		empID = admin.getAdminID();
	else
		throw new JspException("无效访问");
  }
  Calendar c = Calendar.getInstance();
  String year = new Integer(c.get(Calendar.YEAR)).toString();

  EvsPeriod evsPeriod = new EvsPeriod();
  String period = evsPeriod.getFirstEvPeriodByEvYear(year);
  EvsScore scoreOp = new EvsScore();
  String operate = scoreOp.getEmpOpoerateByMaster(period,empID,admin.getAdminID());
%>
<%@include file="inc/evstoolbar_0201.jsp"%>
<%@include file="inc/evs_nav.jsp"%>
<%

  EvsColumn evsColumn = new EvsColumn();
%>
<script language="JavaScript">
<!--
document.write ('<div style=\"overflow:auto\; width:98%; height:' + (document.body.clientHeight-154) + ';\">')
//-->
</script>
<table width="100%" border="1" align="right" cellpadding="1" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  
  <tr bgcolor="#F5F5F5">
    <!--<td rowspan="2" align="center" valign="middle" width="80">目标类别</td>
    --><td rowspan="2" align="center" valign="middle"  width="30">序号</td>
    <td rowspan="2" align="center" valign="middle"  width="100">评价项目</td>
    <td rowspan="2" align="center" valign="middle" width="100">细分评价内容</td>
    <td rowspan="2" align="center" valign="middle"  width="30">比重%</td>
    <td colspan="3" align="right"  width="200" >达成目标水准</td>
    <td colspan="3" align="center" valign="middle" width="200">评价尺度(年度目标）</td>
  </tr>
  <tr bgcolor="#F5F5F5">
    <td align="center">上半年目标</td>
    <td align="center">下半年目标</td>
    <td align="center">全年度目标</td>
    <td align="center">上</td>
    <td align="center">中</td>
    <td align="center">下</td>
  </tr>
  <tr>
<%
  String itemCode1 = "EVITEM001";
  Vector detail_v1 = evsColumn.getDetailIDByItemID(period,empID,itemCode1);
  
  if(detail_v1.size()>0){
%>
    <!--<td align="center" valign="middle" rowspan="<%//=detail_v1.size()+1%>">业务目标</td>
--><%
}
  for (int i = 0;i<detail_v1.size();i++) {
    int detial_seq = ((Integer)detail_v1.get(i)).intValue();
    Hashtable column_h = evsColumn.getYearEvsColumnByDetailID(detial_seq);
    if (column_h!=null) {
    	prop=prop+Float.parseFloat((String)column_h.get("detailProp"));
%>
  <tr onClick="HighLightTR('#99CCFF','black','<%=detial_seq%>','<%//=menu_code%>')">
    <td align="center"><%=i+1%></td>
    <td align="left"><%=column_h.get("column001")%></td>
    <td align="left"><%=column_h.get("column002")%></td>
    <td align="center"><a id="ColumnDetailProp"><%=column_h.get("detailProp")%></a></td>
    <td align="left"><%=column_h.get("column003")%></td>
    <td align="left"><%=column_h.get("column004")%></td>
    <td align="left"><%=column_h.get("column005")%></td>
    <td align="left"><%=column_h.get("column006")%></td>
    <td align="left"><%=column_h.get("column007")%></td>
    <td align="left"><%=column_h.get("column008")%></td>
  </tr>
<%
    }
  }
%>
<tr>
<td colspan="11">
<%if(prop<100){
	
	out.println("<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp; 未完成: "+(new Float(100-prop).intValue())+"%</font>");
}else if(prop>100){
	out.println("<font color='red'>&nbsp;&nbsp;&nbsp;&nbsp; 比重溢出: "+(new Float(prop-100).intValue())+"%</font>");
}%>
</td></tr>
</table>
</div>
<script language="JavaScript">
<!--
function CheckProp()
{
	if (document.all.ColumnDetailProp[0])
	{
		j=0;
		for (i=0;i<document.all.ColumnDetailProp.length;i++)
			if (document.all.ColumnDetailProp[i].innerHTML!='')
				j+=document.all.ColumnDetailProp[i].innerHTML*1.0;
		return j;
	}
	else
		return document.all.ColumnDetailProp.innerHTML*1.0;
}
//-->
</script>
