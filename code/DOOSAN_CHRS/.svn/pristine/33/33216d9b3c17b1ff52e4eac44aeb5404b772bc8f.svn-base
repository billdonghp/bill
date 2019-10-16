<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArStaFormular"%>
<%@ page import="java.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="formular" scope="page" class="com.ait.ar.dao.ArStaFormularBean">
</jsp:useBean>
<%
int itemNo=Integer.parseInt(request.getParameter("item_no"));
ArrayList items=formular.getrollFormula(itemNo);
ArStaFormular ar=null;
%>
<html>
<head>
 

<link href="../css/default.css" rel="stylesheet" type="text/css">
  <script language="javascript">
  function Delete(formularID)
  {//"确定要删除吗?"
    if(!confirm('<ait:message  messageID="alert.att.delete_item" module="ar"/>'))
    return;
    document.form1.action="/arControlServlet?operation=formulardel&formularNo="+formularID;
    document.form1.submit();
    //"删除成功"
    alert('<ait:message  messageID="alert.att.delete_successfully" module="ar"/>');
    location.href="/ar/formular_date.jsp?item_no=<%=itemNo%>";
  }
  </script>
</head>
<body>
  <form name="form1" method="POST">

<table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
              <tr align="center">
                <td  nowrap="nowrap" class="info_title_01"><!--顺序-->
					<ait:message  messageID="display.mutual.no"/></td>
                <td  nowrap="nowrap"  class="info_title_01"><!--条件-->
					<ait:message  messageID="display.mutual.condition"/></td>
                <td  nowrap="nowrap"  class="info_title_01"><!--公式-->
					<ait:message  messageID="display.att.setting.formula" module="ar"/></td>
                <td  nowrap="nowrap"  class="info_title_01"><!--操作-->
					<ait:message  messageID="display.att.setting.formula.operate" module="ar"/>
					<span>
						<a href="formular_date_a.jsp?itemNo=<%=itemNo%>" >
							<img src="../images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt="添加">
						</a>
					</span> 
				</td>
              </tr>
               <%for(int i=0;i<items.size();i++){
    				ar=(ArStaFormular)items.get(i);%>
              <tr align="center">
                <td nowrap="nowrap"><%=i%></td>
                <td width='400' style='word-break:break-all' align="left"><%=ar.getCondition_cn()==null?"&nbsp;":ar.getCondition_cn()%></td>
                <td width='400' style='word-break:break-all' align="center"><%=ar.getFormular_cn()%></td>
                <td align="center" nowrap="nowrap">
                <a href="/ar/formular_date_a.jsp?itemNo=<%=itemNo%>" >
                	<img src="../images/left_menubullet_main_p.gif" width="14" height="22" border="0" align="absmiddle" alt="添加">
                </a>
                <a href="formular_date_m.jsp?itemNo=<%=ar.getItem_no()%>&formularNo=<%=ar.getFormular_no()%>">
                	<img src="../images/left_menubullet_main_d.gif" width="14" height="22" border="0" alt="察看详细" align="absmiddle">
                </a> 
                <a href="javascript:Delete('<%=ar.getFormular_no()%>')">
                	<img src="../images/left_menubullet_sub_m.gif" width="14" height="22" alt="删除" border="0" align="absmiddle">
                </a>
                </td>
              </tr>
              <%}%>
</table>
  </form>
</body>
</html>
