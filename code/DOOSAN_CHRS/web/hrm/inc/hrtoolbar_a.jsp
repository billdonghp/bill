<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="/inc/taglibs.jsp"%>       
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	  <table width="99%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
         <td align="left" valign="middle" style="padding:3 0 3 0 " nowrap="nowrap">
          	<ait:history />
          </td>  
          <td  align="right" valign="middle" style="padding:3 0 3 0 ">
		   <ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle"
          	onclick="javascript:Save();" style="cursor:hand" />
          	<ait:image src="/images/btn/Back.gif"  border="0" align="absmiddle"
          	onclick="javascript:history.back();" style="cursor:hand" title="后退" enTitle="return" />
          </td>
        </tr>
      </table></td>      
  </tr>    
</table>
