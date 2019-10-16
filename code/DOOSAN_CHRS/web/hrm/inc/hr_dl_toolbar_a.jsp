<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="/inc/taglibs.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
         <td align="left" valign="middle" style="padding:3 0 3 0 ">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
           <c:if test="${toolbarInfo.selectMenu == 1}">
	           <ait:image src="/images/btn/Search.gif"  border="0" align="absmiddle"
	          	onclick="javascript:Search();" style="cursor:hand" />
            </c:if>
		   <ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle"
          	onclick="javascript:Save();" style="cursor:hand" />
          </td>
        </tr>
      </table>
      </td>  
  </tr>
</table>