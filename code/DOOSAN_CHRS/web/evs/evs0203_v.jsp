<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ page import="com.ait.ev.bean.EvEmp" %>
<%@ page import="com.ait.ev.dao.EvSeasonDAO" %>
<%@ page import="com.ait.ev.bean.EvGrade" %>
<%@ page import="com.ait.ev.bean.EvProcess" %>
<%@ page import="com.ait.ev.IEvProcess" %>
<%@ page import="com.ait.ev.IEvEmpMark" %>
<%@ page import="com.ait.ev.bean.EvEmpMark" %>
<%@ page import="com.ait.ev.IEvMaster" %>
<%@ page import="com.ait.ev.bean.EvMaster" %>
<%@ page import="com.ait.ev.EvServices" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ait.util.StringUtil" %>
<%@ page import="com.ait.ev.processing.Constants" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"/>
<jsp:useBean id="evemp_list" class="java.util.Vector" scope="request" />
<jsp:useBean id="evemp" class="com.ait.ev.com.ait.gm.DateBean.bean.EvEmp" scope="request" />
<jsp:useBean id="evgrade_list" class="java.util.Vector" scope="request" />
<jsp:useBean id="evgrade" class="com.ait.ev.com.ait.gm.DateBean.bean.EvGrade" scope="request" />
<jsp:useBean id="evemplist" class="java.util.Hashtable" scope="request" />
<jsp:useBean id="dept_list" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="dept_h" class="java.util.Hashtable" scope="page" />
<jsp:useBean id="deptId" class="java.lang.String" scope="request" />
<jsp:useBean id="evseason" class="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 &gt; 评价进行 &gt; 评价打分</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">

<%@ include file="/evs/inc/evstoolbar_m.jsp"%>
<%@ include file="/evs/inc/evs_nav.jsp"%>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="1">
	  <tr>
		<td height="2" class="title_line_01"></td>
	  </tr>
	  <tr>
		<td height="2" class="title_line_02"></td>
	  </tr>
  <tr align="right">
  <!--form action="/EvControlServlet?operation=ev0203&flag=view&menu_code=ev0203" method="POST"  name="ev0203"-->
  <TD align="right"> &nbsp;

    <a href="/EvControlServlet?operation=ev0203&flag=defaultGrade&menu_code=ev0203&deptid=<%=deptId%>&evseason=<%=evseason%>"> 部门相对化
    </a>
    <select name="deptid" onChange="location.href='/EvControlServlet?operation=ev0203&flag=view&menu_code=ev0203&deptid='+this.value+'&evseason='+document.LGFORM.evseason.value;">
      <option value="All" <%if ("All".equals(deptId)){%>selected<%}%>>请选择部门</option>
      <%
      for ( int d = 0 ; d < dept_list.size() ; d++ )
      {
        dept_h = (Hashtable) dept_list.get(d);
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

      &nbsp;<select name="evseason" onChange="location.href='/EvControlServlet?operation=ev0203&flag=view&menu_code=ev0203&deptid='+document.LGFORM.deptid.value+'&evseason='+this.value;">
    <option value="">评价季度</option>
<%
    EvSeasonDAO dao = new EvSeasonDAO();
    List list = dao.getEvSeasonList();
    int s = 0;
    while (list.size()>s) {
      String seasonCode = (String)list.get(s);
%>
         <option value="<%=seasonCode%>" <%if (seasonCode.equals(evseason)) {%> selected="selected"<%}%>><%=seasonCode%></option>
<%
      s = s + 1;
    }
%>
                    </select>
                     <a href="/EvControlServlet?operation=ev0203&flag=submit&menu_code=ev0203&&deptid=<%=deptId%>&evseason=<%=evseason%>" style="hand;">
      提交
    </a>
		</td>
              </tr>
	  <tr>
		<td class="line">
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
           <tr align="center" bgcolor="#F5F5F5">
            <td height="30">序号</td>
            <td>工号</td>
            <td>姓名</td>
            <td>部门</td>
            <td>当前状态</td>
            <td>当前得分</td>
            <td>当前等级</td>
            <td>相对化/评分</td>
          </tr>
          <%
          String ev_process_code="";
          String ev_operate_type="";

          EvServices evServices=new EvServices();
          IEvProcess evProcess=new EvProcess();
          IEvMaster evMaster=new EvMaster();
          IEvEmpMark evEmpMark=new EvEmpMark();
          int k=1;
          for(int i=0;i<evemp_list.size();i++){
              evemp=(EvEmp)evemp_list.get(i);
              if (evemplist.get(evemp.getEmpid())!=null){
              %>
              <tr align="center">
                  <td height="30" class="tablecontent"><%=k%></td>
                  <td><%=evemp.getEmpid()%>&nbsp;</td>
                  <input type="hidden" name="seq_ev_emp" value="<%=evemp.getSeq_ev_emp()%>">

                  <input type="hidden" name="ev_empid" value="<%=evemp.getEmpid()%>">
                  <input type="hidden" name="ev_process_code" value="<%=evemp.getEv_process_code()%>">
                      <td><%=StringUtil.toUnicode(StringUtil.toISO(evemp.getEmpname()),"UTF-8")%>&nbsp;</td>
                      <td><%=evemp.getEv_dept_name()%>&nbsp;</td>
                      <td><%=evemp.getEv_process_name()%>&nbsp;</td>
                      <td><%=evemp.getMark()%>&nbsp;</td>
                      <td><%=evemp.getEv_grade_code()%>&nbsp;</td>

                      <td>
                          <%
                          //取得各评价流程操作方式
                          ev_process_code=evemp.getEv_process_code();
                          //登录者是否是相应的评价者
                           boolean isMaster=false;
                          try{
                              evMaster=evServices.getEvMaster(evemp.getSeq_ev_emp(),ev_process_code);
                              if(evMaster.getEv_master().equals(admin.getAdminID())){
                                isMaster=true;
                              }
                              if(!ev_process_code.equals("")){
                                  evProcess=evServices.getEvProcessByCode(ev_process_code);
                                  ev_operate_type=evProcess.getEv_operate_type();
                              }
                          }catch(Exception e){System.out.print("evServices.getEvMaster(evemp.getSeq_ev_emp(),ev_process_code) 取值失败!");}
                          %>
                          <input type="hidden" name="ev_operate_type" value="<%=ev_operate_type%>">
                              <%
                              if(ev_operate_type.equals(Constants.EVOPType05)&&isMaster==true&&ev_process_code.equals(Constants.EVPROCESS1006)){//操作方式为人事相对化
                                  %>
                                  <select name="ev_final<%=evemp.getSeq_ev_emp()%>" style="width:40pt">
                                      <%
                                      for(int j=0;j<evgrade_list.size();j++){
                                          evgrade=(EvGrade)evgrade_list.get(j);
                                          if(evgrade.getEv_grade_code().equals(evemp.getEv_final())){
                                              out.print("<option value="+evgrade.getEv_grade_code()+" selected >"+evgrade.getEv_grade_code()+"</option>");
                                          }else{
                                              out.print("<option value="+evgrade.getEv_grade_code()+" >"+evgrade.getEv_grade_code()+"</option>");
                                          }
                                      }
                                      %>
                                      </select>
                                      <%}else if(ev_process_code.equals(Constants.EVPROCESS1005)&&isMaster==true){//操作方式部门相对化%>

                                      <%
                                      out.println(StringUtil.checkNull(evemp.getEv_final()));

//                                      //取得相应流程的评价分数
//                                      int seq_ev_master=0;
//                                      int ev_mark=0;
//                                      try{
//
//                                          seq_ev_master=evMaster.getSeq_ev_master();
//
//                                          if(seq_ev_master!=0){
//                                              evEmpMark=evServices.getEvEmpMark(seq_ev_master);
//                                              ev_mark=evEmpMark.getEv_mark();
//                                          }
//                                      }catch(Exception e){}
                                       %>
                                      <!--input type="hidden" name="ev_process_code<%//=i%>" value="<%//=ev_process_code%>">
                                      <input name="mark<%//=i%>" value="<%//if(ev_mark!=0){out.print(ev_mark);}%>" style="width:40pt"-->
                                          <%}else{//操作方式为其它
                                              out.print("&nbsp;");
                                          }%>
                                      </td>
              </tr>
              <%
              k++;
          }
        }
          %>
        </table></td>
	  </tr>
</table>
</body>

</html>
