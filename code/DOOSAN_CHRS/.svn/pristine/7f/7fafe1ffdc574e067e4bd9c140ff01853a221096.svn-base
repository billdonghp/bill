<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="java.util.Vector,com.ait.pa.PaProgress,com.ait.util.StringUtil"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/pa/inc/common_toolbar_n.jsp"%>
<script language="JavaScript" type="text/JavaScript">
function change(id,column,cflag){
location.href="<%=menu_code%>_t.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>&flag=update&no="+id+"&column="+column+"&cflag="+cflag+"&user=<%=admin.getAdminID()%>";
}
</script>
<br>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
  <tr align="center"> 
	<td height="30" class="info_title_01">工资月</td>
	<td height="30" class="info_title_01">考勤汇总状态</td>
	<td height="30" class="info_title_01">工资参数初始化</td>
	<td height="30" class="info_title_01">工资计算标记</td>
	<td height="30" class="info_title_01">考勤锁定</td>
	<td height="30" class="info_title_01">工资结算锁定</td>
	<td height="30" class="info_title_01">结算者</td>
	<td height="30" class="info_title_01">结算日期</td>
  </tr>
<%	
	PaProgress progress =new PaProgress();
	Vector vlist = new Vector();
	pc.setRowCount(" Pa_Progress ");
	vlist = progress.List(pc);
	if (vlist.size()!=0){
		int listNo = 1 ;
		for ( int i = 0 ; i < vlist.size() ; i++ )	{ 		  
		progress=(PaProgress)vlist.elementAt(i);
	%>             
  <tr align="center" > 
	<td height="30" class="info_content_01"><%=progress.getpa_month_str()%></td>
	<td height="30" class="info_content_01"><img src="/images/<%=progress.getatt_mo_flag()%>.gif" border="0"></td>
	<td height="30" class="info_content_01"><img src="/images/<%=progress.getparam_initial_flag()%>.gif" border="0"></td>
	<td height="30" class="info_content_01"><img src="/images/<%=progress.getcalc_flag()%>.gif" border="0"></td>
	<td height="30" class="info_content_01"><%if (progress.getpa_lock_flag()==0){%><a href="javascript:change('<%=progress.getpa_month_str()%>','att_mo_lock_flag','<%=progress.flag(progress.getatt_mo_lock_flag())%>')"><img src="/images/<%=progress.getatt_mo_lock_flag()%>.gif" border="0"></a><%}else{%><img src="/images/<%=progress.getatt_mo_lock_flag()%>.gif" border="0"><%}%></td>
	<td height="30" class="info_content_01"><a href="javascript:change('<%=progress.getpa_month_str()%>','pa_lock_flag','<%=progress.flag(progress.getpa_lock_flag())%>')"><img src="/images/<%=progress.getpa_lock_flag()%>.gif" border="0"></a></td>
	<td height="30" class="info_content_01"><%=StringUtil.editNbsp(progress.getlock_user_id())%></td>
	<td height="30" class="info_content_01"><%=progress.getlock_date()%></td>
  </tr><% }%> 
  <tr align="center">
    <td height="30" colspan="8" class="info_content_01">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="center" class="info_content_01"><%@ include file="/inc/page.jsp"%></td>
      </tr>
    </table>
    </td>
  </tr>
<%}%>
</table>
</body>
</html>