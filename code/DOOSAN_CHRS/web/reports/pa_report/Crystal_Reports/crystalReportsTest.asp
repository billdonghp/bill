
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

reportname = "crystalReportsTest.rpt"
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

'The following section shows setting single valued parameters of various data types.
'Session("oRpt").ParameterFields.GetItemByName("ExampleStringParameter").AddCurrentValue(CStr("I am a string"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleNumberParameter").AddCurrentValue(CDbl("12345"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleBooleanParameter").AddCurrentValue(CBool("True"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleCurrencyParameter").AddCurrentValue(CDbl("10.3273"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleDateParameter").AddCurrentValue(CDate("2001/Jan/02"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleTimeParameter").AddCurrentValue(CDate("3:45:00 PM"))
'Session("oRpt").ParameterFields.GetItemByName("ExampleDateTimeParameter").AddCurrentValue(CDate("2001/Jan/02 3:45:00 PM"))


'This section demonstrates the enabling of multiple values for a parameter and then setting 
'a number of different values for that single parameter.
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").EnableMultipleValues = 1
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Anne"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Nancy"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Laura"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Justin"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Margaret"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Steven"))
'session("oRpt").ParameterFields.GetItemByName("ExampleMultiValuedStringParameter").AddCurrentValue(CStr("Albert"))


'This section shows enabling a parameter to accept a ranged parameter.
'The first line sets the DiscreetOrRangeKind to 1. For Ranged parameters the constant is 1.
'The second line uses the AddCurrentRange method which takes three arguments.
' AddCurrentRange LowerBoundValue, UpperBoundValue, CRRangeInfoConstant
'The CRRangeInfoConstant of 3 indicates that the range should include values greater than or equal to
'the lower bound and less than or equal to the upper bound.

'session("oRpt").ParameterFields.GetItemByName("ExampleRangedNumberParameter").DiscreteOrRangeKind = 1
'session("oRpt").ParameterFields.GetItemByName("ExampleRangedNumberParameter").AddCurrentRange CDbl("5"),CDbl("10"),CDbl("3")

Session("oRpt").ParameterFields.GetItemByName("paMonth").AddCurrentValue(CStr("200906"))

'paMonth = "200906"
'set session("ParamCollection") = Session("oRpt").Parameterfields     

'set Param1 =  session("ParamCollection").Item(1)
'Call Param1.SetCurrentValue (CStr(paMonth))

'set Param2 =  session("ParamCollection").Item(2)
'Call Param2.SetCurrentValue (CStr("ic0700012"))
 
'set Param3 =  session("ParamCollection").Item(3)
'Call Param3.SetCurrentValue (CStr(ENDDATE), 12)

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