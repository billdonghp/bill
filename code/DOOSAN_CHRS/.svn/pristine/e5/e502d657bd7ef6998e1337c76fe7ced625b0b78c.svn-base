<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.crystaldecisions.report.web.viewer.CrPrintMode"%>
<%@page import="com.crystaldecisions.report.web.viewer.CrystalReportViewer"%>
<%@page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSource"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.Fields"%>
<%
	//Get the IReportSource object from sesion and pass it to the viewer
	IReportSource reportSource = (IReportSource) session.getAttribute("reportSource");

	//create the CrystalReportViewer object
	CrystalReportViewer crystalReportViewer = new CrystalReportViewer();

	//set the reportsource property of the viewer
	crystalReportViewer.setReportSource(reportSource);

	//set the parameterfileds of the viewer
	crystalReportViewer.setParameterFields((Fields) session.getAttribute("parameterFields"));

	//set viewer attributes
	crystalReportViewer.setOwnPage(true);
	crystalReportViewer.setOwnForm(true);
	crystalReportViewer.setDisplayToolbar(true);
	crystalReportViewer.setZoomFactor(100);
	crystalReportViewer.setHasExportButton(false) ;
	crystalReportViewer.setHasToggleGroupTreeButton(false) ;
	//crystalReportViewer.setSeparatePages(false);
	
	crystalReportViewer.setDisplayGroupTree(false);
	//set the CrystalReportViewer print mode
	crystalReportViewer.setPrintMode(CrPrintMode.ACTIVEX);
	
	//refresh the CrystalReportViewer if necessary (only required once)
	if (session.getAttribute("refreshed") == null) {
		crystalReportViewer.refresh();
		session.setAttribute("refreshed", "true");
	}

	crystalReportViewer.processHttpRequest(request, response, getServletConfig().getServletContext(), null);

	//to prevent the "getOutputStream()" error_jspxFactory = null;  
%>