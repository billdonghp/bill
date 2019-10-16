<%@ page contentType="text/html; application/vnd.ms-Excel; application/vnd.ms-PowerPoint; application/vnd.ms-DOC; charset=iso-8859-1"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />

<html>
<head>
<%@ include file="../inc/meta.jsp"%>
<title>制度显示页面</title>
</head>
<script language="JavaScript">
<!--
function key(){ 
if(event.shiftKey){
window.close();}
//禁止shift
if(event.altKey){
window.close();}
//禁止alt
if(event.ctrlKey){
window.close();}
//禁止ctrl
return false;}
document.onkeydown=key;
if (window.Event)
document.captureEvents(Event.MOUSEUP);
//swordmaple javascript article.
//from www.jx165.com
function nocontextmenu(){
event.cancelBubble = true
event.returnValue = false;
return false;}
function norightclick(e){
if (window.Event){
if (e.which == 2 || e.which == 3)
return false;}
else
if (event.button == 2 || event.button == 3){
event.cancelBubble = true
event.returnValue = false;
return false;}
}
//禁止右键
document.oncontextmenu = nocontextmenu;  // for IE5+
document.onmousedown = norightclick;  // for all others
//-->
</script>
<body onselectstart="return false"; onpaste="return false";>
<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
<%}%>
<iframe src="E:\work\DICC\web\upload\files\111.txt" width="500" height="150"></iframe>

</body>
</html>