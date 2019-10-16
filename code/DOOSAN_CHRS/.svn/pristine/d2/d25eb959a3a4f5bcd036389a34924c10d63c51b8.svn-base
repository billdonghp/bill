<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.ev.bean.EvEmp" %>
<%@ page import="com.ait.ev.bean.EvProcess" %>
<%@ page import="com.ait.ev.bean.EvType" %>
<%@ page import="com.ait.ev.bean.EvMaster" %>
<%@ page import="com.ait.ev.dao.EvSeasonDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="com.ait.util.StringUtil" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="dept_h" class="java.util.Hashtable" scope="page" />
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="evEmp_list" class="java.util.Vector" scope="request" />  <!--被评价者列表-->
<jsp:useBean id="evEmp" class="com.ait.ev.com.ait.gm.DateBean.bean.EvEmp" scope="request" /> <!--被评价者-->
<jsp:useBean id="deptId" class="java.lang.String" scope="request" />
<jsp:useBean id="evseason" class="java.lang.String" scope="request" />

<script language="JavaScript" type="text/javascript">
  function selectChange(sel){
    var ev_type_code;
    ev_type_code =sel.options[sel.selectedIndex].value;
    location.href='/EvControlServlet?operation=ev0107&flag=refresh&menu_code=ev0107&ev_type_code='+ev_type_code;
    sel.selectedIndex=0;
  }

  function showEvMaster(empID,season,seq) {
    var theUrl = "ev/showEvMaster.jsp?"+"empID="+empID+"&season="+season+"&seq="+seq;
    var name = "newWin";
    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=390,height=400";
    window.open(theUrl,name,features);
  }
  </script>
  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>评价 > 基本设置 > 评价者设置</title>
      </head>
      <body><a name="top"></a>
        <link href="/css/default.css" rel="stylesheet" type="text/css">
          
            <%@ include file="/inc/evtoolbar_v.jsp"%>
            <%@ include file="/inc/ev_nav.jsp"%>
            <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
              <tr>
                <td height="2" class="title_line_01"></td>
              </tr>
              <tr>
                <td height="2" class="title_line_02"></td>
              </tr>
              <form action="/EvControlServlet?operation=ev0107&flag=view&menu_code=ev0107" method="Post" name="ev0107">
                <tr>
                  <td height="2" align="right">
                    <input type="text" name="GoEmp" id="GoEmp" size="6">
                    &nbsp;<a onClick="location.href='#'+document.ev0107.GoEmp.value;" style="cursor:hand">定位</a>

                      &nbsp;<select name="deptid" onChange="ev0107.submit();">
                        <option value="All">请选择部门</option>
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
                          &nbsp;<select name="ev_season_code" onChange="ev0107.submit();">
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
                          </td>
                        </tr>
                      </form>
                      <tr>
                        <td class="line">
                          <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
                            <tr align="center" bgcolor="#F5F5F5">
                              <td width="5%" height="30"><div align="center">序号</div></td>
                              <td width="6%"><div align="center">员工号</div></td>
                              <td width="8%"><div align="center">员工姓名</div></td>
                              <td width="15%"><div align="center">部门名称</div></td>
                              <td width="11%"><div align="center">评价类型</div></td>
                              <td width="10%"><div align="center">流程操作者</div></td>
                              <td width="9%"><div align="center">活跃</div></td>
                            </tr>
                            <%
                            for(i=0;i<evEmp_list.size();i++){
                              evEmp=(EvEmp)evEmp_list.get(i);

                              %>
                              <a name="<%=evEmp.getEmpid()%>">
                              <tr onClick="HighLightTR('#99CCFF','black','<%=evEmp.getSeq_ev_emp()%>','<%=menu_code%>')">
                                <td height="30" class="tablecontent"><div align="center"  class="info_content_01"><%=i+1%></td>
                                  <td><div align="center" class="info_content_01">
                                    <%=evEmp.getEmpid()%>&nbsp;
                                    <a href="#top">&#X0018;
                                    </a>
                                  </div></td>
                                  <td><div align="center" class="info_content_01"><%=StringUtil.toUnicode(StringUtil.toISO(evEmp.getEmpname()),"UTF-8")%>&nbsp;</div></td>
                                  <td><div align="center"  class="info_content_01"><%=evEmp.getEv_dept_name()%>&nbsp;</div></td>
                                  <td><div align="center"  class="info_content_01"><%=evEmp.getEv_type_name()%>&nbsp;</div></td>
                                  <td id="sevnt<%=i%>" style="cursor:pointer" onclick="showEvMaster('<%=evEmp.getEmpid()%>','<%=evEmp.getEv_season_code()%>','<%=evEmp.getSeq_ev_emp()%>')" align="center">
                                    点击查看
                                    &nbsp;</td>
                                    <td><div align="center"  class="info_content_01"><img src="/images/a_<%=evEmp.getActivity()%>.gif">&nbsp;</div></td>
                                    </tr>
                                    </a>
                                    <%}%>
                                  </table></td>
                                </tr>
                              </table>

                            </body>
                          </html>


