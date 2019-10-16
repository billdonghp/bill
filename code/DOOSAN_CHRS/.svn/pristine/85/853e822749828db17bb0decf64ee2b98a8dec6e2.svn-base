<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory,
                 com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2,
                 com.crystaldecisions.reports.reportengineinterface.JPEReportSource,
                 com.crystaldecisions.report.web.viewer.CrystalReportViewer,
                 com.crystaldecisions.report.web.viewer.CrPrintMode,
                 com.crystaldecisions.sdk.occa.report.data.*,com.ait.util.StringUtil"%>

<%
String ARMonth=StringUtil.checkNull(request.getParameter("ARMonth"));
if(ARMonth!=null&&ARMonth.length()!=0) { 
	session.setAttribute("ARMonth",ARMonth);
}else if(session.getAttribute("ARMonth")!=null){
    ARMonth=session.getAttribute("ARMonth").toString().trim();
}

String Company=StringUtil.checkNull(request.getParameter("Company"));
if(Company!=null&&Company.length()!=0) {
	session.setAttribute("Company",Company);
}else if(session.getAttribute("Company")!=null){
    Company=session.getAttribute("Company").toString().trim();
}

String Title=StringUtil.toCN(StringUtil.checkNull(request.getParameter("Title")));
if(Title!=null&&Title.length()!=0) {
	session.setAttribute("Title",Title);
}else if(session.getAttribute("Title")!=null){
    Title=StringUtil.toCN(session.getAttribute("Title").toString().trim());
}

String Department=StringUtil.toCN(StringUtil.checkNull(request.getParameter("Department")));
if(Department!=null&&Department.length()!=0) {
	session.setAttribute("Department",Department);
}else if(session.getAttribute("Department")!=null){
    Department=StringUtil.toCN(session.getAttribute("Department").toString().trim());
}

%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body bgcolor="#ffffff">
<%
  String report = "ar_history_detail_report.rpt";
  IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
  JPEReportSource reportSource = (JPEReportSource) rptSrcFactory.createReportSource(report, request.getLocale());

  ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
  pfieldDV1.setValue(ARMonth);
  pfieldDV1.setDescription("ARMonth");
  Values vals1 = new Values();
  vals1.add(pfieldDV1);  
  ParameterField pfield1 = new ParameterField();
  pfield1.setReportName("");
  pfield1.setName("ARMonth");
  pfield1.setCurrentValues(vals1);
   
  ParameterFieldDiscreteValue pfieldDV2 = new ParameterFieldDiscreteValue();
  pfieldDV2.setValue(Company);
  pfieldDV2.setDescription("Company");
  Values vals2 = new Values();
  vals2.add(pfieldDV2);  
  ParameterField pfield2 = new ParameterField();
  pfield2.setReportName("");
  pfield2.setName("Company");
  pfield2.setCurrentValues(vals2);
  
  ParameterFieldDiscreteValue pfieldDV3 = new ParameterFieldDiscreteValue();
  pfieldDV3.setValue(Title);
  pfieldDV3.setDescription("Title");
  Values vals3 = new Values();
  vals3.add(pfieldDV3);  
  ParameterField pfield3 = new ParameterField();
  pfield3.setReportName("");
  pfield3.setName("Title");
  pfield3.setCurrentValues(vals3);
  
  ParameterFieldDiscreteValue pfieldDV4 = new ParameterFieldDiscreteValue();
  pfieldDV4.setValue(Department);
  pfieldDV4.setDescription("Department");
  Values vals4 = new Values();
  vals4.add(pfieldDV4);  
  ParameterField pfield4 = new ParameterField();
  pfield4.setReportName("");
  pfield4.setName("Department");
  pfield4.setCurrentValues(vals4);
  
  Fields fields = new Fields();
  fields.add(pfield1);
  fields.add(pfield2);
  fields.add(pfield3);
  fields.add(pfield4);
  
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


%>
</body>
</html>
