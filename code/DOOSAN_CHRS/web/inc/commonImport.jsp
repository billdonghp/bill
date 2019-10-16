<%@page session="false" contentType="text/html; charset=UTF-8" %>
<%@ include file="../inc/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- commonImport.jsp -->
<title>数据导入</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<script language="javascript">
function sub(url)
{
var action = url.replace(/\\$/g,"&");

document.form1.action=action;
document.form1.fireSubmit();

}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="form1"  method="post" enctype="multipart/form-data" > 

<table width="600" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="7" colspan="3"></td>
  </tr>
	<tr>
    <td height="3" colspan="3"></td>
  </tr>
	<tr>
    <td height="11" colspan="3"></td>
  </tr>
  <tr> 
    <td width="25">&nbsp;</td>
	<td width="550" height="15"><span class="title1"><!--数据导入-->
					<ait:message  messageID="display.att.view.dining.import_data" module="ar"/></td>
    <td width="25">&nbsp;</td>
  </tr>
  <tr> 
    <td width="25"></td>
  	<td width="550" align="right" valign="top">
   		<table width="550" cellpadding="0" cellspacing="0" class="dr_d">
        <tr> 
          <td height="14" class="info_content_01"></td>
        </tr>
        <tr> 
          <td height="2" class="info_content_01"></td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="1" cellpadding="0"  >
        	<tr> 
          	<td  width="120" class="info_title_01"><!--附件: -->
					<ait:message  messageID="display.att.view.dining.attachment" module="ar"/></td>
          	<td width="450" class="info_content_01"><input type="file" size="30" name="importFile"  required></td>
        	
        	</tr>
           </table>
			</td>
  		</tr>
		</table>
	<td></td>
	</td>
   </tr>
   <tr>
	<td height="11"></td>
	<td height="11"></td>
	<td height="11"></td>
   </tr> 
   	<tr>
	  <td></td>
		<td align="right">
		  <!-- button Start-->
	      <table cellspacing="0" cellpadding="0">
	        <tr> <!-- 导入-->
	          <td> <input name="submit2" type="button" value='<ait:message  messageID="display.att.view.dining.import" module="ar"/>' onMouseOver="this.style.color='#650000'" onMouseOut="this.style.color='#202020'" onClick="javascript:sub('${param.url}')"> 
	          </td>
	          <td width="4"></td><!-- 取消-->
	          <td> <input name="reset4" type="button"  value='<ait:message  messageID="display.att.view.dining.cancel" module="ar"/>' onclick="javascript:window.close()"  onMouseOver="this.style.color='#650000'" onMouseOut="this.style.color='#202020'"> 
	          </td>
	        </tr>
	      </table>
	   <!-- button End-->
		 </td>
		 <td></td>
	  </tr>
   <tr>
	<td height="24"></td>
	<td height="24"></td>
	<td height="24"></td>
   </tr>
</table>
</form>
</body> 
<ait:xjos />

</html>

