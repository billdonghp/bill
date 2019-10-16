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

boolean lockStatus = true ;
%>
<script language="JavaScript" type="" src="">
    function Evs1(url)
    {
        if (ID=='')
        {
            alert("请在列表中选择要评价的人员.");
        }
        else
        {	
			url = url + "?menu_code=evs0205&operation=retrieveEvaluate&EmpID="+ ID ;
            location.href=url;
        }
    }
function importExcel(configureNo)
{	
	if (configureNo == '')
	{
		alert("请选择输入项目!!!") ;
		return ;
	}
	
	url="/evsControlServlet?operation=importsetupcodeReport$configureNo=" + configureNo;
	window.open('/inc/commonImport.jsp?url='+url, "detail", 'toolbar=no,location=no,directories=no,status=no, menubar=no, scrollbars=no, resizable=no, width=600, height=150, top=150, left=170');	
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
                        <td align="right">
		   					<% if (lockStatus){ %>
					 		<ait:image src="/images/btn/Excel_Imp.gif"  border="0" align="absmiddle" onclick="javascript:importExcel('1');" style="cursor:hand"/> | 
					 		<% } %>
					 		<a target="_blank" href="../reports/template/Jnpjcj.xls">
								<ait:image src="/images/btn/Template.gif"  border="0" align="absmiddle" /></a>
						</td>
						
						
                            <td align="right" valign="middle" style="padding:3 0 3 0 ">
                                <a href="javascript:Evs();" >
                                <img src="/images/btn/evaluate.gif" border="0" alt="评价"/>
                                </a>
                            </td>
                        </tr>
                    </table></td>
                </tr>
            </table>
