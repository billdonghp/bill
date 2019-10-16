<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String menu_code ="";
String rodeLevel ="";
MenuBiz menubiz = new MenuBiz(); %>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">&nbsp;
          <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle"
          	onclick="javascript:search();" style="cursor:hand" />
          	
          <ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" 
          	onclick="javascript:Update1();" style="cursor:hand" title="保存" enTitle="update" />
          </td>    
        </tr>  
      </table>
      </td>
  </tr>
</table>
