<%@ page contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="33" bgcolor="#F5F5F5"><table height="30"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=basic&operation=view" class="off">基本信息</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=upgrade&operation=view" class="off">经历/晋升</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=reward&operation=view" class="off">褒奖/惩戒</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=suspension&operation=view" class="off">休职/复职</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=contract&operation=view" class="off">合同/档案</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=relative&operation=view" class="off">社会关系</a></td>
        <td class="info_bg_off"><a href="essControlServlet?empID=<%=basic.getEmpID()%>&content=health&operation=view" class="off">健康</a></td>
        <!--<td class="info_bg_off"><a href="hrControlServlet?empID=<%=basic.getEmpID()%>&info=other&operation=hr_view" class="off">其它信息</a></td>-->
        </tr>
    </table>
  </tr>
</table>