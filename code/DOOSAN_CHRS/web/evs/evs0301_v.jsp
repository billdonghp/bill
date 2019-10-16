<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.ev.dao.EvSeasonDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="com.ait.ev.bean.EvSeason" %>
<%@ page import="com.ait.ev.IEvSeason" %>
<%@ page import="com.ait.ev.bean.EvEmp" %>
<%@ page import="java.util.Vector" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="evemp_list" class="java.util.Vector" scope="request" /><!-评价项目列表  ->
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evemp" class="com.ait.ev.com.ait.gm.DateBean.bean.EvEmp" scope="page" />
<jsp:useBean id="dept_h" class="java.util.Hashtable" scope="page" />
<jsp:useBean id="deptId" class="java.lang.String" scope="request" />
<jsp:useBean id="evseason" class="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 员工状态 </title>
</head>
<body><a name="top"></a>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="/inc/evtoolbar_v.jsp"%>
<%@ include file="/inc/ev_nav.jsp"%>
<BR>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
          <tr>

		<td height="2" align="right" width="74%">
<input type="text" name="GoEmp" id="GoEmp" size="6">
  &nbsp;<a onClick="location.href='#'+document.all.GoEmp.value;" style="cursor:hand">定位</a>
</TD>
<form action="/EvControlServlet?operation=ev0301&flag=view&menu_code=ev0301" method="POST" name="ev0301">
  <TD> &nbsp;
    <select name="deptid" onChange="ev0301.submit();">
      <option value="All" <%if ("All".equals(deptId)){%>selected<%}%>>请选择部门</option>
      <%
      for ( int i = 0 ; i < dept_list.size() ; i++ )
      {
        dept_h = (Hashtable) dept_list.get(i);
        %>
        <option value="<%=(String)dept_h.get("deptId")%>" <%if (((String)dept_h.get("deptId")).equals(deptId)){%>selected<%}%>>

          <%
          int level=Integer.parseInt((String)dept_h.get("deptLevel"));
          String deptname = "";
          for(int j=0;j<level;j++){
            deptname +="　";
          }
          out.print(deptname+(String)dept_h.get("deptName"));
          %>
        </option>
        <%}%>
      </select>
      </td>
      <td>
      &nbsp;<select name="ev_season_code" onChange="ev0301.submit();">
    <option value="">评价季度</option>
<%
    EvSeasonDAO dao = new EvSeasonDAO();
    List list = dao.getEvSeasonList();
    int i = 0;
    while (list.size()>i) {
      String seasonCode = (String)list.get(i);
%>
         <option value="<%=seasonCode%>" <%if (seasonCode.equals(evseason)) {%> selected="selected"<%}%>><%=seasonCode%></option>
<%
      i = i + 1;
    }
%>
                    </select>
		</td> </form>
          </tr>

	  <tr>
		<td class="line" colspan="3">
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center" bgcolor="#F5F5F5">
            <td height="30">序号</td>
            <td>员工号</td>
            <td>姓名</td>
            <td>部门</td>
            <td>当前状态</td>
            <td>步骤期间</td>
          </tr>
          <%
          for(i=0;i<evemp_list.size();i++){
              evemp=(EvEmp)evemp_list.get(i);
              %>
              <tr align="center" onClick="HighLightTR('#99CCFF','black','<%=evemp.getSeq_ev_emp()%>','<%=menu_code%>')">
                  <td height="30" class="tablecontent"><%=i+1%></td>
                  <td><%=evemp.getEmpid()%>&nbsp;<a href="#top">&#X0018;</a><a name="<%=evemp.getEmpid()%>"></a></td>
                  <td><%=StringUtil.toUnicode(StringUtil.toISO(evemp.getEmpname()),"UTF-8")%></td>
                  <td><%=evemp.getEv_dept_name()%></td>
                  <td><%=evemp.getEv_process_name()%></td>
                  <%
                  IEvSeason ievS=new EvSeason();
                  ievS=evemp.getEvseason();
                  %>
                  <td><%=ievS.getEv_season_sdate()%>
                      ～
                         <%=ievS.getEv_season_edate()%></td>
                      </tr>
                     <% }%>
        </table></td>
	  </tr>
</table>

</body>
</html>
