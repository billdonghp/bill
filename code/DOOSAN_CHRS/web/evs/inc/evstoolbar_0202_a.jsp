<%@ page contentType="text/html; charset=UTF-8" language="java" import="com.ait.utils.*" errorPage="" %>
<%@ page import="com.ait.sy.bean.*"%>
<%@page import="java.util.*"%>
<SCRIPT LANGUAGE="JavaScript" src="/js/evtable0202.js"></SCRIPT>
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
<script language="JavaScript" type="" src="">
    function Submit()
    {
          if(CheckForm()){
           document.form1.action='/evs/<%=menu_code%>_t1.jsp';
          document.form1.submit();
	   }
          
    }

   function Modify()
    {
          if(CheckForm()){
           document.form1.action='/evs/<%=menu_code%>_t1.jsp';
          document.form1.submit();
	   }
          
    }


    
    
    </script>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
            <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	   <ait:history />
          	</td>
                <td height="30" valign="middle">
                    <table width="99%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="100%" align="right" valign="middle" style="padding:3 0 3 0 ">
                                
                                <ait:image src="/images/submit.gif"  border="0" align="absmiddle"
          								onclick="javascript:Submit();" style="cursor:hand" title="提交" enTitle="add" />
                               
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td width="100%" align="right" valign="middle" style="padding:3 0 3 0 ">
                                <img src="/images/btn/evaluate.gif" border="0" alt="评价" border="0" 
                                   align="absmiddle" onclick="javascript:Evs();" style="cursor:hand;" />
                            </td>
                            <td>
                            &nbsp;
                            </td>
                            <td width="100%" align="right" valign="middle" style="padding:3 0 3 0 ">
                                <img src="/images/btn/Modify.gif"
					               border="0" align="absmiddle" onclick="javascript:Modify();" style="cursor:hand;" title="修改" >
                            </td>
                        </tr>
                    </table></td>
                </tr>
            </table>
