<table border="0" align="center" cellspacing="0">
<tr>
<td align="left" valign="middle"><%if(pc.isforward(bigpage)){%>
  <a href="<%=menu_code%>.jsp?flag=list&bigpage=<%=bigpage-1%>&search=<%=search%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_last10.gif" width="18" height="12" border="0"></a></td>
<%}%>
<td align="left" ><table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
	<%
	if (x==null) x = "1";
	String color="#666666";
	String content = request.getParameter("selectContent")==null?"":StringUtil.toCN(request.getParameter("selectContent"));
	String condition = request.getParameter("selectCondition")==null?"":request.getParameter("selectCondition");
	
	for(int i=1;i<11;i++){
	   // color="";
	if(pc.getNowSmall(i,bigpage)){	
	%>
		<td><a href="<%=menu_code%>.jsp?flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&search=<%=search%>&menu_code=<%=menu_code%>&selectContent=<%= selectContent%>&selectCondition=<%=selectCondition %>&selectContent=<%= selectContent%>&selectCondition=<%=selectCondition %>">
		<font color=<%if (x.equals(i+"")) out.print("red");%>><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
<%}}%>
  </tr>
</table></td>
<td align="left"><%if(pc.isNextBig(bigpage)){%>
  <a href="<%=menu_code%>.jsp?flag=list&bigpage=<%=bigpage+1%>&search=<%=search%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_next10.gif" width="18" height="12" border="0" align="absmiddle"></a> </td>
<%}%>
</tr>
</table>       