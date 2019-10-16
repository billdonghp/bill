
<%@ LANGUAGE="VBSCRIPT" %>

<%
'==============================================================================
' WORKING WITH THE REPORT DESIGNER COMPONENT AND ASP TO LOG IN TO A MAIN REPORT 
'==============================================================================
'
' CONCEPT                                                             
'                                                                     
'  ALWAYS REQUIRED STEPS (contained in AlwaysRequiredSteps.asp)
'   - create the application object                                
'   - create the report object                                     
'   - open the report                                              
'
'  WORKING WITH ConnectionProperty Object
'	The ConnectionProperty Object is a property bag that stores connection 
'	information for the report. Information is dependent on the Crystal Decisions 
'	database DLL used by the report and the database drivers that support the DLL. 
'	The collection of ConnectionProperty objects can hold common properties defined 
'	in our database DLLs, connection properties from the supporting drivers, or 
'	both. For example a report based off an OLEDB (ADO) connection to Microsoft SQL 
'	Server will contain properties supplied by the database DLL (crdb_ado.dll) such 
'	as Provider, Data Source, Initial Catalog, User ID, and Password, as well as the 
'	properties supplied by the supporting drivers such as Local Identifier, Connect 
'	Timeout and so on. The values for these properties can be set to connect to the 
'	current data source for the report or to change the data source for the report
'
'  MORE ALWAYS REQUIRED STEPS (contained in MoreRequiredSteps.asp)
'   - retreive the records                                         
'   - create the page engine 
'
'  DISPLAY THE REPORT
'   - display the report using a smart viewer
' = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
%>

<%
' This line creates a string variable called reportname that we will use to pass
' the Crystal Report filename (.rpt file) to the OpenReport method contained in 
' the AlwaysRequiredSteps.asp.

reportname = "60000000_pa_detail_filiale.rpt"
%>

<%
'==================================================================
' ALWAYS REQUIRED STEPS
'
' Include the file AlwaysRequiredSteps.asp which contains the code    
' for steps:
'   - create the application object
'   - create the report object
'   - open the report
'==================================================================
%>                                                                     
<!-- #include file="AlwaysRequiredSteps.asp" --> 

<!-- #include file="CHRS_DB.asp" --> 

<%

paMonth = Request.QueryString("paMonth")
deptid = Request.QueryString("deptid")
distinction = Request.QueryString("distinction")

Session("oRpt").ParameterFields.GetItemByName("paMonth").AddCurrentValue(CStr(paMonth))

'Session("oRpt").ParameterFields.GetItemByName("deptid").AddCurrentValue(CStr(deptid))

'This section demonstrates the enabling of multiple values for a parameter and then setting 
'a number of different values for that single parameter.

'==================================================================
%>

<%
'==================================================================
'
'  MORE ALWAYS REQUIRED STEPS
'
'  Include the file MoreRequiredSteps.asp which contains the code
'  for the steps:
'   - retreive the records                                         
'   - create the page engine                                       
'
'==================================================================
%>
<!-- #include file="MoreRequiredSteps.asp" -->

<%
' INSTANTIATE THE REPORT VIEWER
'
'When using the Crystal Reports in an ASP environment, we use
'the same page-on-demand Report Viewers used with the Crystal Web Component Server.
'There are six Report Viewers:
'
'1.  Report Viewer for ActiveX
'2.  Report Viewer for Java using Browser JVM
'3.  Report Viewer for Java Using Java Plugin
'
'The Report Viewer that you use will based on the browser's display capablities.
'For Example, you would not want to instantiate one of the Java viewers if the browser
'did not support Java applets.  For purposes on this demo, we have chosen to
'define a viewer.  You can through code determine the support capabilities of
'the requesting browser.  However that functionality is inherent in the Crystal
'Reports RDC and is beyond the scope of this demonstration application.
'
'We have chosen to leverage the server side include functionality of ASP
'for simplicity sake.  So you can use the SmartViewer*.asp files to instantiate
'the smart viewer that you wish to send to the browser.  Simply replace the line
'below with the Smart Viewer asp file you wish to use.
'
'The choices are SmartViewerActiveX.asp, SmartViewerJava.asp and JavaPluginViewer.asp. 
'Note that to use this include you must have the appropriate .asp file in the 
'same virtual directory as the main ASP page.
'

'=============================================================================
'  DISPLAY THE REPORT
'   - display the report using a smart viewer
' 
' Include one of the Smart Viewers.
'  - Report Viewer for ActiveX			        =   SmartViewerActiveX.asp
'  - Report Viewer for Java Using Java Plugin	=   JavaPluginViewer.asp
'=============================================================================
%>                 
<!-- #include file="SmartViewerActiveX.asp" --> 