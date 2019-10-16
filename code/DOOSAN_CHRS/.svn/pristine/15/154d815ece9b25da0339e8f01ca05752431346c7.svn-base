<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory,
                 com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2,
                 com.crystaldecisions.reports.reportengineinterface.JPEReportSource,
                 com.crystaldecisions.report.web.viewer.CrystalReportViewer,
                 com.crystaldecisions.report.web.viewer.CrPrintMode,
                 com.crystaldecisions.sdk.occa.report.data.*,com.ait.util.StringUtil"%>
<jsp:directive.page import="com.ait.sy.common.PaHelper"/>


<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->

<%
String PAMonth=StringUtil.checkNull(request.getParameter("year"))+StringUtil.checkNull(request.getParameter("month"));
if(PAMonth!=null&&PAMonth.length()!=0) {
	session.setAttribute("PAMonth",PAMonth);
}else if(session.getAttribute("PAMonth")!=null){
	PAMonth=session.getAttribute("PAMonth").toString().trim();
}
  String EmpID  =admin.getAdminID();
  Integer lockFlag = PaHelper.getProcessLockFlag(PAMonth);
%>
<html>
<head>
<title>
月工资明细表
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body bgcolor="#ffffff">
<%
  if (lockFlag == null || lockFlag.intValue() == PaHelper.UNLOCK_FLAG) {
  String report = "/EssEmpPA.rpt";
  IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
  JPEReportSource reportSource = (JPEReportSource) rptSrcFactory.createReportSource(report, request.getLocale());

  ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
  pfieldDV1.setValue(PAMonth);
  pfieldDV1.setDescription("PAMonth");
  Values vals1 = new Values();
  vals1.add(pfieldDV1);  
  ParameterField pfield1 = new ParameterField();
  pfield1.setReportName("");
  pfield1.setName("PAMonth");
  pfield1.setCurrentValues(vals1);
  
  ParameterFieldDiscreteValue pfieldDV2 = new ParameterFieldDiscreteValue();
  pfieldDV2.setValue(EmpID);
  pfieldDV2.setDescription("EmpID");
  Values vals2 = new Values();
  vals2.add(pfieldDV2);  
  ParameterField pfield2 = new ParameterField();
  pfield2.setReportName("");
  pfield2.setName("EmpID");
  pfield2.setCurrentValues(vals2); 
   
  Fields fields = new Fields();
  fields.add(pfield1);
  fields.add(pfield2);  

  CrystalReportViewer viewer = new CrystalReportViewer();

  viewer.setReportSource(reportSource);

  viewer.setParameterFields(fields);
  viewer.setEnableParameterPrompt(false);
  viewer.setOwnForm(true);
  viewer.setDisplayToolbar(true);
  viewer.setBestFitPage(true);
  viewer.setHasViewList(false);
  viewer.setHasLogo(false);
  viewer.setOwnPage(true);
  viewer.setPrintMode(CrPrintMode.ACTIVEX);
  viewer.setHasPrintButton(true);
  viewer.setDisplayGroupTree (false);
  viewer.refresh();
  viewer.processHttpRequest(request, response, getServletConfig().getServletContext(), out);

  viewer.dispose();
 } else
     out.print(PAMonth + "工资还没有开放，请稍后再查看！");
%>
</body>
</html>
