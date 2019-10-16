<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.*,com.ait.pa.PaParam,com.ait.pa.PaParamItem,com.ait.util.StringUtil"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function send(target){
document.param_data.flag.value=target;
document.param_data.submit();
}
</script>
<body>
<%
	PaParam paParam = new PaParam();
	Vector vlist = new Vector();
	String return_string =request.getParameter("return_string");
	Hashtable hash = new Hashtable();
	if(return_string!=null && !return_string.equals("")){
        hash = StringUtil.toHast(return_string);
		}
       String param_no = StringUtil.checkNull(request.getParameter("param_no"));
	PaParamItem paramitem = PaParamItem.getPaParamItemDetail(param_no);
	vlist = paParam.AddPaParamList(param_no);
%>
<div align="right"><a href="upload.jsp?param_no=<%=param_no%>" target="_blank">上传</a> <a href="javascript:send('insert');" ><img src="/images/btn/save1.gif" width="67" height="24" border="0" align="absmiddle"></a></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;"><form name="param_data" method="post" action="param_data_t.jsp?param_no=<%=param_no%>">
  <tr align="center">
    <td height="30"  align="center" bgcolor="#F5F5F5"><%=paramitem.getDistinct_field()!=null?paramitem.getDistinct_field():"无"%></td>
    <td align="center" bgcolor="#F5F5F5"><%=paramitem.getDistinct_field_2nd()!=null?paramitem.getDistinct_field_2nd():"无"%></td>
    <td align="center" bgcolor="#F5F5F5">返回值</td>
  </tr><input type="hidden" name="flag" value="">
  <%
if (vlist.size()!=0)
	{
	for (int i=0;i<vlist.size();i++)  {
  paParam=(PaParam)vlist.elementAt(i); %>
 <tr align="center" ><input type="hidden" name="field1" value="<%=StringUtil.checkNull(paParam.getField1_value())%>"><input type="hidden" name="field2" value="<%=StringUtil.checkNull(paParam.getField2_value())%>">
   <td  align="center"><%=paParam.getField1_value()!=null?paParam.getField1_value():"无"%></td>
    <td  align="center"><%=paParam.getField2_value()!=null?paParam.getField2_value():"无"%></td>
    <td  align="center"><input type="text" name="return_value" value="<%=StringUtil.checkNull(hash.get(paParam.getField1_value()))%>" ></td>
 </tr><%}}
else {%>
<SCRIPT LANGUAGE="JavaScript">
<!--
alert("所有项目已全部添加");
history.back();
//-->
</SCRIPT>
<%}%></form>
</table>
</body>
</html>
