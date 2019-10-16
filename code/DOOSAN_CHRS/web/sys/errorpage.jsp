<%--
 *************************************************************************
 * 閫氱敤error澶勭悊椤甸潰
 * Copyright(c) 2006 AIT,  All rights reserved.
 *************************************************************************
--%>

<%@ page language ="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
  isErrorPage="true"session="false"
  import="java.util.Date"
  import="java.io.StringWriter"
  import="java.io.PrintWriter"
  import="com.ait.core.SysConstants"
%>
<%
  Throwable throwable = (Throwable)request.getAttribute(SysConstants.ExceptionParamName);
  if (throwable == null) throwable = exception;
  StringWriter sw = null;
%>


<html>
<head>
<title>Common Error/Exception Page - AIT</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<script language="javascript">

function cbOnBeforeLoad() {}

function flipInfoDiv() {
	var oDiv = document.getElementById("idDiv");
	oDiv.style.display = oDiv.style.display == "none" ? "block" : "none";
}
</script>
</head>

<style type="text/css">
<!--
div.layout_page {
  width:98%;
  margin: 10px 0px 30px 25px;
}

.table_line_complex {
	background-color: #D0D0D0;
}

.table_padding {
	height: 22px;
	padding: 2px 2px 1px 3px;
  background-color: #FFF
}

.table_header_complex_d{
	background-color: #ECE8EE;
	text-align: right;
	font-family: "Tahoma";
	font-size: 11px;
	color:#555;
	font-weight:bold;
	padding: 1px 6px 1px 2px;
}

.table_line_complex {
	background-color: #D0D0D0;
}

.subtitle_red{	font-weight:bold; 	font-size:14px; 	color:#D26A62;	text-decoration: none;	line-height:16px;}

.bg_gray_a {
 background-color: #A6A6A6
}

.bg_gray_b {
 background-color: #EFEFEF
}

.3depth_title{
	color : #555;
	font-weight:bold; 
	font-size:12px; 
	text-decoration: none;
}

-->
</style>

<body>

<% 
	if (throwable != null) {
 		java.text.SimpleDateFormat df=new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String host    = request.getServerName() + ":" + request.getServerPort();
 		String client  = request.getRemoteAddr();
		String date    = df.format(new Date());
		String url     = request.getRequestURI();
		Object status  = "500 (unexpected error)";
		Object type    = throwable.getClass().getName();
		String message = throwable.getMessage();
%>


<div class="layout_page">
	<!-- Content Start -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	 <tr>
	  <td >&nbsp;
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr valign="top">
	     <td width="140" rowspan="6"><img src="/images/error.gif" width="139" height="94"></td>
	     <td > Sorry. there is unexpected error occurred. </td>
	    </tr>
	    <tr valign="top">
	     <td >&nbsp;</td>
	    </tr>
	    <tr valign="top">
	     <td class="subtitle_red"><%= message!=null && !message.equals("")?message:"unexpected error occurred" %> </td>
	    </tr>
	    <tr valign="top">
	     <td >&nbsp;</td>
	    </tr>
	    <tr valign="top">
	     <td class="3depth_title">Contact Information or Troubleshooting Tips</td>
	    </tr>
	    <tr valign="top">
	     <td>E-mail us at AIT<br>
	      Call us at AIT</td>
	    </tr>
	   </table></td>
	 </tr>
	 <tr>
	  <td width="98%" height="14" ></td> <!-- 832 -->
	 </tr>
	 <tr>
	  <td height="1" class="bg_gray_a"></td>
	 </tr>
	 <tr>
	  <td height="2" class="bg_gray_b"></td>
	 </tr>
	 <tr>
	  <td height="7"></td>
	 </tr>
	 <tr align="right">
	  <td height="7">
	  	<input name="submit2322" type="button" value="&lt;  Back" onclick="history.back()">
	  	<% if (throwable != null ) { %>
	  	<input name="submit2322" type="button" value="debug" onclick="flipInfoDiv()">
	  	<input name="submit2322" type="button" value="reload" onclick="location.reload()">
	  	<% } %>
	  	</td>
	 </tr>
	</table>
	<BR>

  <div id="idDiv" style="display:none;" width="100%" class="table_layout">
    <table width="100%" border="0" cellspacing="1" cellpadding="0"  class="table_line_complex">
      <colgroup>
      <col width="100"/>
      <col width="295"/>
      <col width="100"/>
      <col width="295"/> <!-- 295 -->
      </colgroup>
      <tr>
        <td class="table_header_complex_d">Server</td>
        <td class="table_padding"><%=host%></td>
        <td class="table_header_complex_d">Client</td>
        <td class="table_padding"><%=client%></td>
      </tr>
      <tr>
        <td class="table_header_complex_d">Status Code</td>
        <td class="table_padding"><%=status%></td>
        <td class="table_header_complex_d">Date & Time</td>
        <td class="table_padding"><%=date%></td>
      </tr>
      <tr>
        <td class="table_header_complex_d">Access URL</td>
        <td class="table_padding" colspan="3"><%=url%></td>
      </tr>
      <tr>
        <td class="table_header_complex_d">Exception Type</td>
        <td class="table_padding" colspan="3"><%=type%></td>
      </tr>
      <tr>
        <td class="table_header_complex_d">Message</td>
        <td class="table_padding" colspan="3"><%=message%></td>
      </tr>
      <tr>
        <td class="table_padding" colspan="4">
          <%
            if (throwable!=null){
            	sw = new StringWriter();
      			PrintWriter pw = new PrintWriter(sw);
      			throwable.printStackTrace(pw);	
            }
          %>
		  <pre ID=oStackTrace STYLE="background-color:#EAEAEA;width: 900px;height: 230px; overflow: scroll;"><%= throwable!=null?sw.getBuffer().toString():"" %></pre>
        </td>
      </tr>
    </table>
  </div>
<% } %>
</div>
</body>
</html>
