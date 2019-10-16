<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*" errorPage="" %>
<%@ page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/js/evtable.js"></SCRIPT>
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

%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30" valign="middle">
            <table width="99%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="100%" align="right" valign="middle" style="padding:3 0 3 0 ">
                        
                       <img name="returnbtn" id="returnbtn" src="/images/ev/return.gif" border="0" onClick="javaScript:Return();" style="cursor:hand;visibility:"  alt="否决"/>                       
                       <img name="savebtn" id="savebtn" src="/images/ev/save.gif" border="0" onClick="javaScript:Save();" style="cursor:hand;visibility:"  alt="保存"/>
                       <img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="javaScript:Submit();" style="cursor:hand;visibility:"  alt="同意/提交" />
                        
                    </td>
                </tr>
            </table>
         </td>
        </tr>
</table>
