<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.ait.util.StringUtil"%>
<%@ page import="com.ait.utils.MenuEnt"%>
<jsp:useBean id="menuBiz" class="com.ait.utils.MenuBiz" scope="page" />
<jsp:useBean id="menuent" class="com.ait.utils.MenuEnt" scope="page" />
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<%
	String menu_code = StringUtil.checkNull(request.getParameter("menu_code"));
	String imgname = menu_code.length()>=2?menu_code.substring(0,2):"";
	String roleLevel = StringUtil.checkNull(admin.getScreenGrantNo());
	ArrayList menu_vector = menuBiz.thirdmenulist(menu_code,roleLevel);
	String onoroff = "";
	int insertR=0;
	int updateR=0;
	int deleteR=0;
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="kuang_zuo"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="18"><img src="/images/top_zuo.jpg" width="18" height="28"></td>
          <td background="/images/top_zuo_d2.jpg">
          <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <%
										for (int i = 0; i < menu_vector.size(); i++) {
										menuent = (MenuEnt) menu_vector.get(i);
								%>
                <%
								if (menu_code.equals(menuent.getMenuCode())) {
								insertR = menuent.getInsertr();
							  	updateR = menuent.getUpdater();
							  	deleteR = menuent.getDeleter();
								%>
                <td class="td_shen"><a href="<%=menuent.getMenuUrl()%>" class="shen"><%=menuent.getMenuIntro()%></a></td>
                <%
								} else {
								%>
                <td class="td_qian"><a href="<%=menuent.getMenuUrl()%>" class="qian"><%=menuent.getMenuIntro()%></a></td>
                <%
									}
									}
								%>
              </tr>
            </table></td>
          <td width="14" align="right"><img src="/images/top_you.jpg" width="14" height="28"></td>
        </tr>
      </table></td>
  </tr>
</table>
 <table width="98%"  border="0" cellpadding="0" cellspacing="0"  >
        <tr>
        <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
		 <%--<%if(insertR==1){%>
          <img src="/images/btn/add1.gif" align="absmiddle" style="cursor:pointer;" onClick="javascript:Add();">
          <%}%>
          <%if(deleteR==1){%>
         <img src="/images/btn/delete1.gif" align="absmiddle" style="cursor:pointer;" onClick="javascript:Delete();">
          <%}%>
          --%><%if(updateR==1){%>
          <img src="/images/btn/save1.gif" align="absmiddle" style="cursor:pointer;" onClick="javascript:Save();">
          <%}%>
          </td>
          
        </tr>
</table>
