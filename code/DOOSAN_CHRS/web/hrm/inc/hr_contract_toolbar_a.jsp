<%@ page contentType="text/html; charset=UTF-8" language="java"  errorPage="" %>
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
          	<ait:image src="/images/btn/Search.gif" align="absmiddle"
          	onclick="javascript:Search();" style="cursor:hand" />
          	<c:if test="${toolbarInfo.insertMenu == 1}">
	          	<ait:image src="/images/btn/Add_Contract.gif" align="absmiddle"
	          	onclick="javascript:AddContract();" style="cursor:hand" />
          	</c:if>
          	<c:if test="${toolbarInfo.deleteMenu == 1}">
	          	<ait:image src="/images/btn/Excel_Exp.gif" align="absmiddle"
	          	onclick="javascript:ImportExcel();" style="cursor:hand" />
          	</c:if>
          </td>
        </tr>  
      </table>
      </td>
  </tr>
</table>
