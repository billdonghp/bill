<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<script language="javascript">
function Search(){ 

    var key=document.sySearch.key.value;   
	document.sySearch.action="sy0430.jsp?menu_code=sy0430&key="+encodeURIComponent(key);    
	document.sySearch.submit();
}                             
</script>
<table border="0" align="center" cellspacing="0" width="100%">                         
<tr>
<td align="right" valign="middle" width="10%">
<%if(pc.isforward(bigpage)){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage-1%>&menu_code=<%=menu_code%>&key=<%=key%>">
  <img src="/images/btn/p_last101.gif" width="18" height="12" border="0">
  </a> 
<%}%>      
</td>                                        
<td align="left" width="*">
<table border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
<%
	for(int i=1;i<11;i++){
	if(pc.getNowSmall(i,bigpage)){
     if(strPage == i){
%>
	<td><a href="/E<%=menu_code%>Control?key=<%=key != null?java.net.URLEncoder.encode(key,"UTF-8"):""%>&flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color="blue" ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	<%}else{%>
    <td><a href="/E<%=menu_code%>Control?key=<%=key != null?java.net.URLEncoder.encode(key,"UTF-8"):""%>&flag=list&bigpage=<%=bigpage%>&strPage=<%=i%>&menu_code=<%=menu_code%>"><font color="#666666" ><b><%=pc.getListSmall(i,bigpage)%></b></font></a>&nbsp;</td>
	<%}}}%>
  </tr>
</table>  
</td>
<td align="left" width="10%">
<%if(pc.isNextBig(bigpage)){%>
  <a href="/E<%=menu_code%>Control?flag=list&bigpage=<%=bigpage+1%>&menu_code=<%=menu_code%>&key=<%=key%>">
  <img src="/images/btn/p_next101.gif" width="18" height="12" border="0" align="absmiddle">
</a>
<%}%>  
</td>
<td width="40%" align="right">                                    
<form name="sySearch" method="post"><!-- 部门ID/部门名称  -->      
<ait:message messageID="display.sys.basic_maintenance.dept_maintenance.id_name" module="sys"></ait:message>

<input type="text" name="key"  value="<%=key %>">
<ait:image src="/images/btn/Search.gif" align="absmiddle" onclick="javascript:Search();" style="cursor:hand" /> 
</form>
</td>
</tr>
</table>

