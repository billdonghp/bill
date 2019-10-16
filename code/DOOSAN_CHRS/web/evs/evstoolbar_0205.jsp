<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*" errorPage="" %>
<%@ page import="com.ait.sy.bean.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/js/evtable.js"></SCRIPT>
<%
String menu_code ="";
String rodeLevel ="";
menu_code = request.getParameter("menu_code");
admin = (AdminBean)session.getAttribute("admin");
rodeLevel=admin.getScreenGrantNo()!=null?admin.getScreenGrantNo():"";
MenuBiz menubiz = new MenuBiz();
ArrayList toolmenu =menubiz.toolMenuList(menu_code,rodeLevel);
String imgname = menu_code.substring(0,2);
%>

    <table width="98%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td height="30" valign="middle">
                <table width="761" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="7">
                            <form name="search" method="POST" action="">
                                <td width="168" height="45" align="center">
                                </td>
                                <td width="56" align="center"></td>
                            </form>
                            <td width="3">
                                <td width="527" align="right" valign="middle" style="padding:3 0 3 0 ">
                                    <img name="addbtn" id="addbtn" src="/images/ev/add.gif" border="0"  style="cursor:hand;visibility:"  alt="添加"/>

                                    <img name="moditybtn" id="moditybtn" src="/images/ev/modify.gif" border="0" onClick="" style="cursor:hand;visibility:"  alt="修改"/>

                                    <img name="deletebtn" id="deletebtn" src="/images/ev/delete.gif" border="0" onClick="" style="cursor:hand;visibility:"  alt="删除"/>

                                    <img name="savebtn" id="savebtn" src="/images/ev/save.gif" border="0" onClick="" style="cursor:hand;visibility:"  alt="保存"/>

                                    <img name="submitbtn" id="submitbtn" src="/images/ev/submit.gif" border="0" onClick="" style="cursor:hand;visibility:"  alt="同意/提交" />

                                    <img name="returnbtn" id="returnbtn" src="/images/ev/return.gif" border="0" onClick="" style="cursor:hand;visibility:"   alt="否决"/>
                                </td>
                            </tr>
                        </table></td>
                    </tr>
                    <tr>
                        <td height="2"></td>
                    </tr>
                </table>
