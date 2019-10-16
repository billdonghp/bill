<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
          	<ait:history />
          </td>
          <td align="right" valign="middle" style="padding:3 0 3 0 ">
          <c:if test="${toolbarInfo.insertMenu == 1}">
          	<ait:image src="/images/btn/Add.gif"  border="0" align="absmiddle"
          	onclick="javascript:Add();" style="cursor:hand" title="添加" enTitle="add" />
          </c:if>
          <c:if test="${toolbarInfo.updateMenu == 1}">
            <ait:image src="/images/btn/Modify.gif"  border="0" align="absmiddle"
          	onclick="javascript:Update();" style="cursor:hand" title="修改" enTitle="update" />
          </c:if>
          <c:if test="${toolbarInfo.deleteMenu == 1}">
          <ait:image src="/images/btn/Delete.gif"  border="0" align="absmiddle"
          	onclick="javascript:Delete();" style="cursor:hand" title="删除" enTitle="delete" />
          </c:if>
          </td>    
        </tr>  
      </table>
      </td>
  </tr>
</table>
