<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.Vector,com.ait.pa.Paitem,com.ait.util.StringUtil"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" src="/js/slide_menu.js"></script>
<SCRIPT language="JavaScript" src="/js/patable.js"></SCRIPT>
<body>
<%@ include file="/inc/common_toolbar.jsp"%>
<script language="JavaScript" type="text/JavaScript">
var ID;
ID='';
function Add(){
location.href="pa_item_a.jsp?menu_code=<%=menu_code%>";
}
function Update(){
	if (ID!=''){
	location.href="pa_item_m.jsp?menu_code=<%=menu_code%>&item_no="+ID;
	}
	else{
		alert("请选择参数");
	}
}
function Delete(){
	if (ID!=""){
		if (confirm("删除后,相关信息也将被清空!\n\n      确定要删除吗?") )	{
		location.href="pa_item_t.jsp?menu_code=<%=menu_code%>&flag=del&param_no="+ID;
		}
	}
	else{
		alert("请选择参数");
	}
}
</script>
<br>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
  <tr align="center"> 
	<td width="40" height="24" bgcolor="F7F7F7">编号</td>
	<td width="150" height="24" bgcolor="F7F7F7">工资项目名称</td>
	<td height="24" bgcolor="F7F7F7">描述</td>
	<td width="80" height="24" bgcolor="F7F7F7">数据类型</td>
	<td width="50" height="24" bgcolor="F7F7F7">计算顺序</td>
	<td width="60" height="24" bgcolor="F7F7F7">项目类型</td>
	<td width="40" height="24" bgcolor="F7F7F7">精度</td>
	<td width="40" height="24" bgcolor="F7F7F7">进位</td>
  </tr>
<%	Paitem paitem =new Paitem();
	Vector vlist = new Vector();
	String searchsql="";
	if (!search.equals("")) {
	    searchsql = " where item_name like '%"+func.isoStr(search)+"%' ";
	}
	pc.setRowCount(" pa_item " + searchsql);
	vlist = paitem.List(searchsql,pc);
	if (vlist.size()!=0){
		int listNo = 1 ;
		for ( int i = 0 ; i < vlist.size() ; i++ )	{ 		  
		paitem=(Paitem)vlist.elementAt(i);
	%>             
  <tr align="center" onClick="HighLightTR('#99CCFF','black','<%=paitem.getpa_item_no()%>','<%=menu_code%>')"> 
	<td height="26"><%=paitem.getpa_item_no()%></td>
		<td height="26"><%=paitem.getitem_name()%></td>
		<td height="26"><%=StringUtil.editNbsp(paitem.getdescr())%></td>
		<td height="26"><%=StringUtil.editNbsp(paitem.getdatatype())%></td>
		<td height="26"><%=paitem.getcalcu_order()%></td>
		<td height="26"><%=StringUtil.editNbsp(paitem.getitem_type())%></td>
		<td height="26"><%=paitem.getpricision()%></td>
		<td height="26"><%=paitem.getcarry_bit()%></td>
	  </tr><% }}%> 
  <tr align="center">
    <td height="26" colspan="8"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center"><%@ include file="/inc/page.jsp"%></td>
        <form action="<%=menu_code%>.jsp" name='search' method="post">
          <td width="200"><span class="info_content_01">
            <input type="text" name="search" class="content" style="width:94px " value="<%=func.isoStr(search)%>">
          </span><img src="/images/btn/search.gif" width="52" height="19" align="absmiddle" style="cursor:hand"  onclick="location.href='<%=menu_code%>.jsp?flag=list&bigpage=<%=bigpage%>&strPage=<%=strPage%>&search='+document.search.search.value;"></td>
        </form>
      </tr>
    </table></td>
  </tr>
 
</table>
<br>
</body>
</html>