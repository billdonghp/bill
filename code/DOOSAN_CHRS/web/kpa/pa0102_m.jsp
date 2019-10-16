<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*,com.ait.util.SysCodeParser,com.ait.util.StringUtil,com.ait.pa.Paitem,com.ait.pa.PaExp"%>
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
<%
String no = StringUtil.checkNull(request.getParameter("no"));
PaExp paexp =PaExp.Detail(no);
%>
<br>
<table width="100%" height="158" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
<form method="post" name="sf" action="<%=menu_code%>_t.jsp?flag=update&bigpage=<%=bigpage%>&strPage=<%=strPage%>&menu_code=<%=menu_code%>" onSubmit="return CheckForm()"><input name="no" type="hidden" value="<%=no%>">
	<tr>
		<td width="140" height="26" bgcolor="#F7F7F7"style="padding-left:40px">汇总部门 <font color="red">*</font></td>
		<td width="389" style="padding-left:40px"><input name="expense_type" class="box" size="20" value="<%=paexp.getExpense_type()%>"></td>
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
		<td style="padding-left:40px"><input name="expense_formular" class="box" size="20" readonly="true"  value="<%=paexp.getExpense_formular()%>"></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7" height="26"style="padding-left:40px">标记</td>
		<td style="padding-left:40px"><select name="tag">
          <option value="1" <%if (paexp.getTag()==1){out.print("selected");}%>>正</option>
          <option value="-1" <%if (paexp.getTag()==-1){out.print("selected");}%>>负</option>
        </select></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7" height="26"style="padding-left:40px">借贷方 <font color="red">*</font></td>
		<td style="padding-left:40px"><select name="debitcredit">
		  <option value="H" <%if (paexp.getDebitcredit().equals("H")){out.print("selected");}%>>H</option>
		  <option value="S" <%if (paexp.getDebitcredit().equals("S")){out.print("selected");}%>>S</option>
	    </select></td>
    </tr>
	<tr>
		<td bgcolor="#F7F7F7"style="padding-left:40px">描述</td>
		<td style="padding-left:40px"><textarea name="descr" cols="50" rows="8" class="box"><%=paexp.getDescr()%></textarea></td>
    </tr>

</form>
</table>
</body>
</html>
