<table border="0" align="center" cellspacing="0">
<tr>
<td align="left" valign="middle"><%if(pc.isforward(bigpage)){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>">
  <img src="/images/btn/p_last101.gif" width="18" height="12" border="0"></a></td>
<%}%>
<td align="left" ><table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
	<%
	if (x==null) x = "1";
	String color="#666666";
	for(int i=1;i<11;i++){
	if(pc.getNowSmall(i,bigpage)){%>

	<td><a href="/E<%=menu_code%>Control?flag=list&key=<%=java.net.URLEncoder.encode(key,"UTF-8")%>&where=<%=where%>&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color=<%if (x.equals(i+"")) out.print("red");%> ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	
	<%}}%>
  </tr>
</table></td>
<td align="left">
<%if(pc.isNextBig(bigpage)){%> 
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage+1%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a></td>
<%}%>
</tr>
</table>
