<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.Vector,com.ait.pa.Paformular,com.ait.utils.Func,com.ait.util.StringUtil"%>
<%@ include file="../inc/taglibs.jsp"%>
<% 	Paformular paformular = new Paformular();
	Vector vlist = new Vector();
	Func func = new Func();
	int pa_item_no = new Integer(request.getParameter("pa_item_no")).intValue();
	vlist = paformular.List(pa_item_no);
%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">
function Delete(id){
//"删除后,相关信息也将被清空!\n\n      确定要删除吗?"
	if (confirm('<ait:message  messageID="alert.pay.associated_deleting" module="pay"/>') )	{
		location.href="formular_data_t.jsp?flag=del&pa_item_no=<%=pa_item_no%>&formular_no="+id;
		}
}
</script>
<body>
<div align="right"></div>
<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
	<form name="data" method="post" action="formular_data_t.jsp?pa_item_no=<%=pa_item_no%>">
    	<tr align="center"> 
    		<td class="info_title_01"><!--顺序-->
					<ait:message  messageID="display.mutual.no"/></td>
			<td height="24" class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/></td>
			<td bgcolor="F7F7F7" class="info_title_01"><!--公式-->
					<ait:message  messageID="display.mutual.formula"/></td>
			<td class="info_title_01"><!--操作 -->
					<ait:message  messageID="display.mutual.operate"/>
				<a href="formular_data_a.jsp?pa_item_no=<%=pa_item_no%>" >
					<img src="/images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt='<ait:message  messageID="display.mutual.add"/>'>
				</a>
			</td>
		</tr>
		<% 
			if (vlist.size()!=0){
			int listNo = 1 ;
			for ( int i = 0 ; i < vlist.size() ; i++ ){ 		  
				paformular=(Paformular)vlist.elementAt(i);
			%>             
			<tr align="center"> 
				<td width="35" class="ir"><%=paformular.getcondition_seq()%></td>
                <td width="300" height="26" class="ir"><%=StringUtil.editNbsp(paformular.getCondition_cn())%></td>
                <td class="ir" width="600"><%=StringUtil.editNbsp(paformular.getFormular_cn())%></td>
                <td width="60" class="ir">
	                <a href="formular_data_a.jsp?pa_item_no=<%=pa_item_no%>" ><!-- 添加  -->
 						<img src="/images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt='<ait:message  messageID="display.mutual.add"/>'>
	                </a>
	                <a href="formular_data_m.jsp?pa_item_no=<%=pa_item_no%>&formular_no=<%=paformular.getformular_no()%>"><!-- 察看详细  -->
	                	<img src="/images/left_menubullet_main_d.gif" width="14" height="22" border="0" alt='<ait:message  messageID="display.pay.formula.datail" module="pay"/>' align="absmiddle">
	                </a> 
	                <a href="javascript:Delete('<%=paformular.getformular_no()%>')"><!-- 删除  -->
	                	<img src="/images/left_menubullet_sub_m.gif" width="14" height="22" alt='<ait:message  messageID="display.mutual.delete"/>' align="absmiddle">
	                </a>
                </td>
			</tr>
			<%}}%>
	</form>
</table>
</body>
</html>