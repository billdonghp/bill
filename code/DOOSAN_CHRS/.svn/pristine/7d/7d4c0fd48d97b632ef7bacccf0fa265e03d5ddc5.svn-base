<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="org.apache.log4j.Logger,com.ait.utils.*,java.util.Vector,com.ait.util.StringUtil,com.ait.ga.cmd.visit.VoitureManger,java.util.*,com.ait.ga.bean.VoitureBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%
ActivityReport pareport = new ActivityReport();
String startdate= request.getParameter("startdate");
String enddate  = request.getParameter("enddate");

VoitureManger vm = new VoitureManger();
List AllVoitureList = new ArrayList();
List getAllMaintenanceCostsTypeList = new ArrayList();
VoitureBean  voitureBean = null;
VoitureBean  vb = null;
AllVoitureList=vm.getAllVoitureConts(admin.getCpnyId());//得到所有的车辆
getAllMaintenanceCostsTypeList=vm.getAllVoitureResume();//得到车辆履历原因
response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=AreaSummaryCosts.xls");
response.setHeader("Pragma", "public");
response.setHeader("Cache-Control", "max-age=0");
response.setCharacterEncoding("UTF-8");
%>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<tr><td colspan="<%=(getAllMaintenanceCostsTypeList.size()+2)%>" align="center" rowspan="2" style="font-size:12pt;Font-FAMILY:宋体;font-weight:900">
<%=startdate.substring(0,4)%>年<%=startdate.substring(5,7)%>月<%=startdate.substring(8,10)%>
日--<%=enddate.substring(0,4)%>年<%=enddate.substring(5,7)%>月<%=enddate.substring(8,10)%>车辆履历费用统计表</td></tr>
</table>
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<!-- 第一行：表头 -->
<tr style="font-size:12pt;Font-FAMILY:宋体">
<td colspan="1" align="center">
车辆编号
</td>
<%for(int s=0;s<getAllMaintenanceCostsTypeList.size();s++){ 
 vb=(VoitureBean)getAllMaintenanceCostsTypeList.get(s);%>
<td colspan="1" align="center">
<%=vb.getCode_name()%>
</td>
<%} %>
<td colspan="1" align="center">合计</td>
</tr>
<%for(int i=0;i<AllVoitureList.size();i++){ 
	voitureBean=(VoitureBean)AllVoitureList.get(i);%>
<tr style="font-size:12pt;Font-FAMILY:宋体">
<td colspan="1"><%=voitureBean.getVoiture_Number()%></td>
<%for(int s=0;s<getAllMaintenanceCostsTypeList.size();s++){
 vb=(VoitureBean)getAllMaintenanceCostsTypeList.get(s);%>
<td colspan="1" align="center">
<%=vm.getAreaSummaryCosts(voitureBean.getVOITURE_ID().toString(),vb.getCode_id(),startdate,enddate)%>
</td>
<%} %>
<td colspan="1" align="center"><%=vm.getAreaSummaryCostsSumFromVoiture(voitureBean.getVOITURE_ID().toString(),startdate,enddate)%></td>
</tr>
<%} %>
<!-- 第一行：合计 -->
<tr style="font-size:12pt;Font-FAMILY:宋体">
<td colspan="1" align="center">费用共计</td>
<% for(int s=0;s<getAllMaintenanceCostsTypeList.size();s++){
vb=(VoitureBean)getAllMaintenanceCostsTypeList.get(s);%>
<td colspan="1" align="center"><%=vm.getAreaSummaryCostsSumFromCodeid(vb.getCode_id(),startdate,enddate)%></td>
<%} %>
<td colspan="1" align="center"><%=vm.getAreaSummaryCostsSum(startdate,enddate)%></td>
</tr>
</table>