<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<%@ include file="../inc/taglibs.jsp"%>
<html>
<head>
<title>Insert title here</title>
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<script language="javascript">
function save() {
	document.form1.action="/hrmControlServlet?operation=template";
	document.form1.fireSubmit();
}
</script>
</head>
<body>
<form name="form1" method="post" action="1">

	POST_ID: <input type="text"name="POST_ID" value="" ><br> 
	POST_NAME: <input type="text"name="POST_NAME" value="" ><br>
	POST_EN_NAME: <input type="text"name="POST_EN_NAME" value="" ><br> 
	POST_GROUP: <input type="text"name="POST_GROUP" value="" ><br> 
	<br>
	<br>
	<br>
	<br>
	<br>
	Item No      <input type="text"name="ITEM_NO" value="" > 
	Item Name    <input type="text" name="ITEM_NAME" value="" >
	<br>
	person.id1:  <input type="text" name="1_id"  required><br> 
	person.name2:<input type="text" name="1_name"  required><br>
	person.id2:  <input type="text" name="2_id"  ><br> 
	person.name2:<input type="text" name="2_name"  required>
	<input type="button" name="submit1"
	value="Submit" onClick="save();">
	<br>person.id: <c:out value='${person.id}'/>
	<br>person.name: <c:out value='${person.name}'/>
	<br>Transation result: <c:out value='${result}'/>
	<br>bean1.id: <c:out value='${beanList[0].id}'/>
	<br>bean1.name: <c:out value='${beanList[0].name}'/>
	<br>bean2.id: <c:out value='${beanList[1].id}'/>
	<br>bean2.name: <c:out value='${beanList[1].name}'/>
	<br>USE_YN<input type="checkbox" value="Y" name="USE_YN" <ait:checkbox isChecked='${USE_YN}'/> >
	<br><ait:date yearName="YEAR" monthName="MONTH" yearSelected="${year}" monthSelected="${month}" yearPlus="10"  /> 
	<br><ait:codeClass codeClass="ApplyTypeCode" name="applyType" selected="${applyType}" all="all" disabled="false"/> 
	<br>
	<table>
		<tr class="table_padding">
			<td>工号</td>
			<td>卡号</td>
			<td>序列号</td>
		</tr>
	<c:forEach items="${postList2}" var="oneResult">
		<tr class="table_padding">
			<td>${oneResult.EMPLYID}</td>
			<td>${oneResult.CARDNO}</td>
			<td>${oneResult.SERIAL}</td>
		</tr>
		<tr>
			<td colspan="9" class="table_line_simple"></td>
		</tr>
	</c:forEach>
	</table>
	<br>
<table>
	<tr>
		<td width="10%" align="center">ITEM_NO</td>
		<td width="10%" align="center">ITEM_NAME<br>
		</td>
		<td width="10%" align="center">DESCRIPTION<br>
		</td>
		<td width="10%" align="center">ACTIVITY<br>
		</td>

		<td width="10%" align="center">CREATE_DATE</td>

		<td width="10%" align="center">CREATED_BY</td>

	</tr>
	<c:forEach items="${postList}" var="oneResult">
		<tr class="table_padding">
			<c:out value="${oneResult.POST_ID}"/>
			<c:out value="${oneResult.POST_NAME}"/>
			<c:out value="${oneResult.POST_EN_NAME}"/>
			<c:out value="${oneResult.POST_GROUP}"/>
		</tr>
		<tr>
			<td colspan="9" class="table_line_simple"></td>
		</tr>
	</c:forEach> 
	<c:forEach items="${ArItemList}" var="oneResult">
		<tr class="table_padding">
			<ait:listing entityName="oneResult" value="ITEM_NO" link="/hrControlServlet?operation=template&ITEM_NO=${ITEM_NO}&ITEM_NAME=${ITEM_NAME}"/>
			<ait:listing entityName="oneResult" value="ITEM_NAME"/>
			<ait:listing entityName="oneResult" value="DESCRIPTION"/>
			<ait:listing entityName="oneResult" value="ACTIVITY"/>
			<ait:listing entityName="oneResult" value="CREATE_DATE"/>
			<ait:listing entityName="oneResult" value="CREATED_BY"/>
		</tr>
		<tr>
			<td colspan="9" class="table_line_simple"></td>
		</tr>
	</c:forEach>
	<c:forEach items="${beanList}" var="oneResult">
		<tr class="table_padding">
			<ait:listing entityName="oneResult" value="id"/>
			<ait:listing entityName="oneResult" value="name"/>
		</tr>
		<tr>
			<td colspan="9" class="table_line_simple"></td>
		</tr>
	</c:forEach>
	
	      <!-- Page Navigation Start-->
	        <ait:page       
	               link="/hrControlServlet"
	               parameters="&operation=template&ITEM_NO=${ITEM_NO}&ITEM_NAME=${ITEM_NAME}" 
	               total="${resultCount}"
	               currentpage="${currentPage}"
	               pagesize= "${pageSize}"
	               beginlabel="paging_prv10"
	               endlabel="paging_next10"
	               prevlabel="paging_prv"
	               nextlabel="paging_next"
	               pagegroupsize="${pageGroupsize}"
	               useJS="false"/>             
	      <!-- Page Navigation End -->
</table>
<br>
 <input type="hidden" name="hidden1" value="1"/>
<input type="hidden" name="hidden2" value="2"/>
</form>
</body>
<ait:xjos />
</html>
