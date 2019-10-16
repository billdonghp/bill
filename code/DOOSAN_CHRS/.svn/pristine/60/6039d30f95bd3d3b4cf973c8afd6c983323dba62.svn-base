<%@ page pageEncoding="UTF-8"  import="java.util.*,com.ait.util.StringUtil,com.ait.ga.bean.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="wpConfirm" scope="page" class="com.ait.gm.cmd.WpConfirm"/>
<jsp:useBean id="wpb" class="com.ait.ga.bean.WoodProductsBean"/>
 <%
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=WoodProductsOrders.xls");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
 %>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体;font-weight:900">
<tr align="center"><td align="center" colspan="12" rowspan="2"><font style="font-size:14pt;FONT-FAMILY:宋体;">木制品清单：</font><%=request.getParameter("Outdoucemntno") %></td></tr>
</table>
<%
List applerList=wpConfirm.getApplerList(request.getParameter("Outdoucemntno"));	
if(applerList!=null && applerList.size()!=0){ %>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体">
<tr><td colspan="12">职号：<%=StringUtil.checkNull(applerList.get(1))%></td></tr>
<tr><td colspan="12">申请人：<%=StringUtil.checkNull(applerList.get(2))%></td></tr>
<tr><td colspan="12">申请部门：<%=StringUtil.checkNull(applerList.get(3))%></td></tr>
<tr><td colspan="12">希望完工日期：<%=StringUtil.checkNull(applerList.get(4))%></td></tr>
<tr><td colspan="12" rowspan="2">&nbsp;</td></tr>
</table>
<%}%>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体">
<tr><td align="center" colspan="2">制品名称</td><td align="center" colspan="2">规格</td><td align="center" colspan="2">数量</td><td align="center" colspan="2">单价</td><td align="center" colspan="2">面积</td><td align="center" colspan="2">小计</td></tr>
<%List wpListForExcelList=wpConfirm.getWpListForExcel(request.getParameter("Outdoucemntno"));
if(wpListForExcelList!=null){%>
<%for(int i=0;i<wpListForExcelList.size();i++){
wpb=(WoodProductsBean)wpListForExcelList.get(i);%>
<tr><td align="center" colspan="2" ><%=wpb.getWPNAME()%></td><td align="center" colspan="2" ><%if(!wpb.getMEASUREMENT_UNITS().equals("EachNumber")){%>长:<%=wpb.getWP_L()%><%}%><%if(!wpb.getMEASUREMENT_UNITS().equals("EachNumber")){%>宽:<%=wpb.getWP_W()%><%}%><%if((!wpb.getMEASUREMENT_UNITS().equals("EachNumber")) &&( !wpb.getMEASUREMENT_UNITS().equals("EachCubicMeters"))){%>高:<%=wpb.getWP_H()%><%}%></td><td align="center" colspan="2" ><%=wpb.getWPNUMBER() %>个</td><td align="center" colspan="2" ><%=wpb.getMEASUREMENT_UNIT_PRICE()%>元</td><td align="center" colspan="2" ><%if(!wpb.getMEASUREMENT_UNITS().equals("EachNumber")){%><%=wpb.getWP_SUMAREA()%>平方米<%} %></td><td align="center" colspan="2" ><%=wpb.getConfirm_confirmprice()%>元</td></tr>
<%}%>
<%} %>
<tr><td align="center" colspan="12" rowspan="2">&nbsp;</td></tr>
</table>

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="font-size:9pt;FONT-FAMILY:宋体">
<tr><td colspan="2" rowspan="3">管理部签字:</td><td colspan="10" rowspan="3">&nbsp;</td></tr>
</table>
