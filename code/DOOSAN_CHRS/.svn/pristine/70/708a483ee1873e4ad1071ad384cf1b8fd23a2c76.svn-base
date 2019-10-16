
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
年薪报表 
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body bgcolor="#ffffff">
<%
	  String EmpID  =admin.getAdminID();
		out.print(EmpID);
		String year=request.getParameter("year");

  String report = "yearSalary.rpt";
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
  
  ParameterFieldDiscreteValue pfieldDV2 = new ParameterFieldDiscreteValue();
  pfieldDV2.setValue(year);
  pfieldDV2.setDescription("year");
  Values vals2 = new Values();
  vals2.add(pfieldDV2);  
  ParameterField pfield2 = new ParameterField();
  pfield2.setReportName("");
  pfield2.setName("year");
  pfield2.setCurrentValues(vals2); 
  
   
  Fields fields = new Fields();
  fields.add(pfield1);
  fields.add(pfield2);
  
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
