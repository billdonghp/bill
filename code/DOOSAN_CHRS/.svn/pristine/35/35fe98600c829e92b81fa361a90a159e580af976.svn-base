
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory,
                 com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2,
                 com.crystaldecisions.reports.reportengineinterface.JPEReportSource,
                 com.crystaldecisions.report.web.viewer.CrystalReportViewer,
				 com.crystaldecisions.report.web.viewer.CrPrintMode,
                 com.crystaldecisions.sdk.occa.report.data.*"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>  <!-- the admin entity keeped in current session -->
<html>
<head>
<title>
报表显示
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body bgcolor="#ffffff">
<%
	  String EmpID  =admin.getAdminID();
out.print(EmpID);
  String report = "empInfo.rpt";
 IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
  JPEReportSource reportSource = (JPEReportSource) rptSrcFactory.createReportSource(report, request.getLocale());


  
  ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
  pfieldDV1.setValue(EmpID);
  pfieldDV1.setDescription("EmpID");
  Values vals1 = new Values();
  vals1.add(pfieldDV1);  
  ParameterField pfield1 = new ParameterField();
  pfield1.setReportName("");
  pfield1.setName("EmpID");
  pfield1.setCurrentValues(vals1); 
  
   
  Fields fields = new Fields();
  fields.add(pfield1);
  
  CrystalReportViewer viewer = new CrystalReportViewer();
  viewer.setReportSource(reportSource);  

  viewer.setParameterFields(fields);
  viewer.setEnableParameterPrompt(false);
  viewer.setDisplayGroupTree (false);
  viewer.setOwnForm(true);
  viewer.setDisplayToolbar(true);
  viewer.setBestFitPage(true);
  viewer.setHasViewList(false);
  viewer.setHasLogo(false);
  viewer.setOwnPage(true);
  viewer.setPrintMode(CrPrintMode.ACTIVEX);
  viewer.setHasPrintButton(true);
  viewer.refresh();
  viewer.processHttpRequest(request, response, getServletConfig().getServletContext(), out);

  viewer.dispose(); 
%>
</body>
</html>
