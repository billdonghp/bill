<% 
'==================================================================
'==================================================================
' WORKING WITH SETLOGONINFO
'
' The datasource here is called "Pubs Sample Database".  It is a System 
' Datasource (DSN), and points to the "pubs" database, which is installed
' with SQL Server. You will also need to change your user id and
' password.

'Create a reference to the tables collection of the main report
Set mainReportTableCollection = Session("oRpt").Database.Tables

For Each mnTable in mainReportTableCollection
  With mnTable.ConnectionProperties
    .Item("user ID") = "chrs"
    .Item("Password") = "chrs"
	.Item("DSN") = "CHRS_DB"
  End With
Next

'==================================================================
%>