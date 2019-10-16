<%@ page contentType="text/html; charset=UTF-8" %>
<script language="javascript">
/*
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
	document.sySearch.action="/Esy0101Control?flag=list&menu_code=sy0101&key="+document.sySearch.key.value+"&where="+document.sySearch.where.value;
	return true;	
}
*/  
function Search()       
{              
    var key=encodeURIComponent(document.sySearch.key.value);   
  	document.sySearch.action="/Esy0410Control?flag=list&menu_code=sy0410&key="+key+"&where="+document.sySearch.where.value;
  	document.sySearch.submit();
}                  
</script>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">              
<tr>
<td width="13%" align="left" valign="middle"><%if(pc.isforward(bigpage)){%>
<%if(!isSearch){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0"></a></td>
<%}%>   
<%if(isSearch){%>
<a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>&key=<%=key%>&where=<%=where%>"><img src="/images/btn/p_last101.gif" width="18" height="12" border="0"></a><td width="1%"></td>
<%}%>
<%}%>
<td width="10%" align="left" >
<table border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
  <tr>
<%
	for(int i=1;i<11;i++){  
	if(pc.getNowSmall(i,bigpage)){
     if(strPage % 10 == i || (strPage % 10 == 0 && i == 10)){
%>
	<td><a href="/E<%=menu_code%>Control?key=<%=key != null?java.net.URLEncoder.encode(key,"UTF-8"):""%>&flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color="blue" ><b><%= pc.getListSmall(i,bigpage) %></b></font></a>&nbsp;</td>
	<%}else{%>
      <td><a href="/E<%=menu_code%>Control?key=<%=key != null?java.net.URLEncoder.encode(key,"UTF-8"):""%>&where=<%=where != null?where:""%>&flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color="#666666" ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	<%}}}%>
  </tr>                      
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
<td width="48%" align="right" >        
<form name="sySearch" method="post">
<input type="text" name="key" >   
<% 
 admin = (AdminBean)session.getAttribute("admin");     
 String langu=admin.getLanguagePreference();   
if(langu.equals("zh"))   
{   %>                                          
<select name="where">                                
<option value="menu_code">屏幕编号</option>
<option value="menu_intro">屏幕解释</option>
</select>     
<%}else{%>                      
<select name="where">
<option value="menu_code">ScreenNO</option>
<option value="MENU_EN_INTRO">ScreenIntro</option>  
</select>
<%} %>            
<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" />                         
</form>
</td>
             
</tr>
</table>
