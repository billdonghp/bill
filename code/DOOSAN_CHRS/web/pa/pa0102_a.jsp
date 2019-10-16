<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.SysCodeParser,com.ait.pa.Paitem"%>
<jsp:useBean id="codemap" class="java.util.HashMap"/>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Save(){
	if ( CheckForm()){
	document.sf.submit();
	}
}
</script>
<script language="JavaScript" src="/pa/js/pa_expense_type.js"></script>
<body>
<%@ include file="/pa/inc/common_toolbar_a.jsp"%>
<br>
<table width="100%" height="158" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form method="post" name="sf" action="<%=menu_code%>_t.jsp?flag=insert&bigpage=<%=bigpage%>&strPage=<%=strPage%>&menu_code=<%=menu_code%>" onSubmit="return CheckForm()">
	<tr>
		<td width="140" height="26" bgcolor="#F7F7F7"style="padding-left:40px">汇总部门 <font color="red">*</font></td>
		<td width="389" style="padding-left:40px"><input name="expense_type" class="box" size="20"></td>
	    <td width="217" rowspan="5" ><% 	Paitem paitem = new Paitem();
	Vector vlist = new Vector();
	vlist = paitem.List();
	if (vlist.size()!=0)
	{	%><select name="select" id="param_no" size="18" style="width:170px " onClick="document.sf.expense_formular.value=this.value">
	<option value="">无</option>
    <% for (int i=0;i<vlist.size();i++)  {
	  paitem=(Paitem)vlist.elementAt(i); 
	%><option value="<%=paitem.getitem_name()%>"><%=paitem.getitem_name()%></option>
    <%}%></select><%}%></td>
	</tr>
	<tr>
		<td bgcolor="#F7F7F7" height="26"style="padding-left:40px">对应项目</td>
		<td style="padding-left:40px"><input name="expense_formular" class="box" size="20" readonly="true"></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7" height="26"style="padding-left:40px">标记</td>
		<td style="padding-left:40px"><select name="tag">
          <option value="1">正</option>
          <option value="-1">负</option>
        </select></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7" height="26"style="padding-left:40px">借贷方 <font color="red">*</font></td>
		<td style="padding-left:40px"><select name="debitcredit">
		  <option value="H">H</option>
		  <option value="S">S</option>
	    </select></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7"style="padding-left:40px">描述</td>
		<td style="padding-left:40px"><textarea name="descr" cols="30" rows="8" class="box"></textarea></td>
    </tr>

</form>
</table>
</body>
</html>
