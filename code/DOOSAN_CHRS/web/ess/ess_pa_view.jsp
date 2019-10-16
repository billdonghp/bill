<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%@ page import="com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory,
                 com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2,
                 com.crystaldecisions.reports.reportengineinterface.JPEReportSource,
                 com.crystaldecisions.report.web.viewer.CrystalReportViewer,
				 com.crystaldecisions.report.web.viewer.CrPrintMode,
                 com.crystaldecisions.sdk.occa.report.data.*"%>
<html>
<head>
<title>
报表显示
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></head>
<body bgcolor="#ffffff">
<%
	  String pamonth  ="";
	 
	  if(request.getParameter("PAMonth")!=null&&request.getParameter("PAMonth").length()!=0){
		  
	      pamonth=request.getParameter("PAMonth");
	     
		  session.setAttribute("PAMonth",pamonth);
		
		}else if(session.getAttribute("PAMonth")!=null){
		    pamonth=session.getAttribute("PAMonth").toString().trim();
		}

  String report = "pa_emp_view.rpt";
 IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
  JPEReportSource reportSource = (JPEReportSource) rptSrcFactory.createReportSource(report, request.getLocale());

  ParameterFieldDiscreteValue pfieldDV1 = new ParameterFieldDiscreteValue();
 ParameterFieldDiscreteValue pfieldDV2 = new ParameterFieldDiscreteValue();
  pfieldDV1.setValue(pamonth);
  pfieldDV1.setDescription("PAMonth");
  pfieldDV2.setValue(admin.getAdminID());
  pfieldDV2.setDescription("empid");
  Values vals1 = new Values();
  vals1.add(pfieldDV1);
  ParameterField pfield1 = new ParameterField();
  pfield1.setReportName("");
  pfield1.setName("PAMonth");

  Values vals2 = new Values();
  vals2.add(pfieldDV2);  
  ParameterField pfield2 = new ParameterField();
  pfield2.setReportName("");
  pfield2.setName("empid");
  pfield2.setCurrentValues(vals2);   
  
  pfield1.setCurrentValues(vals1);
  pfield1.setReportName("");
  pfield1.setName("empID");
  pfield1.setCurrentValues(vals1);
  
   
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

