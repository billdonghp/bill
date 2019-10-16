<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*,com.ait.utils.*" errorPage="" %>
<%@page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<%
String menu_code ="";
String rodeLevel ="";

ArrayList tool_menu=null;
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
ToolMenu toolmenu = new ToolMenu();
MenuBiz menubiz = new MenuBiz();
tool_menu=menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);

int insertMenu=0;
int updateMenu=0;
int deleteMenu=0;
for(int i=0;i<tool_menu.size();i++){
	toolmenu=(ToolMenu)tool_menu.get(i);
	if(toolmenu.getInsertr()==1){
		insertMenu=1;

	}
	if(toolmenu.getUpdate()==1){
		updateMenu=1;

	}
	if(toolmenu.getDelect()==1){
		deleteMenu=1;

	}
}
%>
<script language="JavaScript" type="" src="">

    function AddSave()
    {
//        if (!CheckForm()){
//        }
//        else {
            try{
              document.LGFROM.action="/EvControlServlet";

              document.LGFROM.submit();
            }catch(e){alert(e);}
//        }
    }
    </script>
    <form name="LGFROM" action="" method="POST">
      <input type="hidden" name="flag" value="append">
      <input type="hidden" name="operation" value="<%=menu_code%>">
      <input type="hidden" name="menu_code" value="<%=menu_code%>">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td height="30" valign="middle">
                  <table width="99%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                          <td  align="right" valign="middle" style="padding:3 0 3 0 ">
                            <a href="javascript:AddSave();">
                              <img src="/images/btn/master.gif" width="80" height="24" border="0" align="absmiddle">
                            </a>
                            <a href="javascript:history.back();">
                              <img src="/images/btn/return1.gif" width="67" height="24" align="absmiddle">
                            </a>
                          </td>
                        </tr>
                      </table>
                    </td>
                    </tr>
                  </table>
