<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="java.util.*"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="dao" scope="page" class="com.ait.ar.dao.ArItemBean">
</jsp:useBean>
<%
ArrayList list=null;
ArItem info=null;
list=dao.getItemList();
request.setAttribute("list",list);
int no=0;
int type=0;
if(request.getParameter("no")!=null){
  no=Integer.parseInt(request.getParameter("no"));
}
if(request.getParameter("type")!=null){
  type=Integer.parseInt(request.getParameter("type"));
}
%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<script language="javascript">
function add()
{
	var length=document.form1.joinitem.length;//得到列表长度
	for(var i=0;i<document.form1.aritem.length;i++)
	{
		if(document.form1.aritem.options[i].selected)
		{
			document.form1.joinitem.options[length]=new Option(document.form1.aritem.options[i].text,document.form1.aritem.options[i].value);
			document.form1.aritem.options[i]=null;
		}
	}
}
function del()
{
	var length=document.form1.aritem.length;
	for(var i=0;i<document.form1.joinitem.length;i++)
	{
		if(document.form1.joinitem.options[i].selected)
		{
			document.form1.aritem.options[length]=new Option(document.form1.joinitem.options[i].text,document.form1.joinitem.options[i].value);
			document.form1.joinitem.options[i]=null;
		}
	}

}
function ok()
{
	var length=document.form1.joinitem.length;
	var inputName = document.form1.inputName.value;
	
	var temp="";
	for(var i=0;i<length;i++)
	{
		temp+=document.form1.joinitem.options[i].value+",";
	}
		temp=temp.substring(0,temp.length-1);
		if(<%=type%>==1){
			opener.document.form1.replace_item.value=temp;
        }else if(<%=type%>==2){
          	opener.document.form1.depend_item.value=temp;
        }
        
	self.close();
}
</script>
</head>

<body>
<form name="form1" method="post" action="">
  <table width="254" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td width="91" rowspan="2"><select name="aritem" size="10">
      <c:if test="${list != null}">
      	<c:forEach items="${list}" var="list">
      		<option value="${list.itemNo }">
      			<ait:content enContent="${list.itemEnName}" zhContent="${list.itemName}" koContent="${list.itemKorName}"/>
      		</option>
      	</c:forEach>
      </c:if>
      </select></td>
      <td width="34" height="50%" valign="bottom"><input type="button" name="Submit" value="--&gt;" onClick="add()"></td>
      <td width="91" rowspan="2"><select name="joinitem" size="10">
      </select></td>
    </tr>
    <tr>
      <td valign="top"><input type="button" name="Submit" value="&lt;--" onClick="del()"></td>
    </tr>
  </table>
  <table width="254" border="1"cellspacing="0" cellpadding="5" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
    <tr>
      <td align="right"><input type="button" name="Submit" value='<ait:message  messageID="display.emp.statistics.enter" module="hrm"/>' onClick="ok()"></td>
    </tr>
  </table>
  <input type="hidden" name="temp" value="">
   <input type="hidden" name="inputName" value="<%=request.getParameter("inputName") %>">
</form>
</body>
</html>
