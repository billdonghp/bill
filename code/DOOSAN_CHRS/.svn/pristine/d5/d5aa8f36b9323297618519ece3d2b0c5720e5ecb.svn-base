<% 
'This section demonstrates the enabling of multiple values for a parameter and then setting 
'a number of different values for that single parameter.


session("oRpt").ParameterFields.GetItemByName("distinction").EnableMultipleValues = 1
tmpStr = "" 
If Len(distinction) <> 0 then
	While (Len(distinction) <> 0 And InStr(distinction, "," ) > 0)
		charIndexVal = InStr(distinction, ",")
		
		tmpStr = Left(distinction,charIndexVal - 1)
		
		'Response.Write tmpStr & "<br>"

		session("oRpt").ParameterFields.GetItemByName("distinction").AddCurrentValue(CStr(tmpStr))
		
		distinction = Mid(distinction,charIndexVal + 1)
	Wend

	If Len(distinction) <> 0 Then
		
		'Response.Write distinction & "<br>"

		session("oRpt").ParameterFields.GetItemByName("distinction").AddCurrentValue(CStr(distinction))
	End If 
Else
	session("oRpt").ParameterFields.GetItemByName("distinction").AddCurrentValue(CStr(tmpStr))
End If 
%>