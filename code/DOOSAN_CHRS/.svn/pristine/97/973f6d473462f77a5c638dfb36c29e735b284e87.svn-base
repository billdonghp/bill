<%@ page contentType="text/html; charset=UTF-8" %>
<script language="javascript">
function search()
{
	if(document.sySearch.key.value=="")
	{
		alert("请输入搜索关键字");
		return false;
	}
	if(document.sySearch.where.value==0)
	{
		alert("请选择搜索类型");
		return false;
	}
	//document.sySearch.action="/sy/sy0101.jsp?menu_code=sy0101&key="+document.sySearch.key.value+"&where="+document.sySearch.where.value;
	document.sySearch.action="/Esy0104Control?flag=list&menu_code=sy0104&key="+document.sySearch.key.value+"&where="+document.sySearch.where.value;
	return true;
	
}
</script>
<table width="70%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td width="13%" align="left" valign="middle"><%if(pc.isforward(bigpage)){%>
<%if(!isSearch){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0"></a></td>
<%}%>
<%if(isSearch){%>
<a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>&key=<%=key%>&where=<%=where%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0"></a><td width="1%"></td>
<%}%>
<%}%>
<td width="10%" align="left" ><table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
	<%
	for(int i=1;i<11;i++){
	if(pc.getNowSmall(i,bigpage)){%>
	<%if(!isSearch){%>
	<td><a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color="#666666" ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	<%}%>
	<%if(isSearch){%>
	<td><a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>&key=<%=key%>&where=<%=where%>"><font color="#666666" ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	<%}%>
	<%}}%>
  </tr>
</table></td>
<td width="11%" align="left"><%if(pc.isNextBig(bigpage)){%>
<%if(!isSearch){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage+1%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a> </td>
<%}%>
<%if(isSearch){%>
<a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage+1%>&menu_code=<%=menu_code%>&key=<%=key%>&where=<%=where%>"><img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle"></a> <td width="17%"></td>
<%}%>
<%}%>
<td width="48%" align="right">
<form name="sySearch" method="post">
<input type="text" name="key"><select name="where">
<option value="0">搜索条件</option>
<option value="adminid">员工号</option>
<option value="username">登录名</option>
</select>
<img src="/images/btn/search.gif" width="52" height="19" align="absmiddle" style="cursor:hand" onclick="return search()">
</form></td>

</tr>
</table>
