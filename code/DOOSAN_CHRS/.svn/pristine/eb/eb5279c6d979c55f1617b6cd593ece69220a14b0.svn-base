<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil,com.ait.util.ViewOptionUtil,com.ait.util.DateUtil"/>
<jsp:useBean id="itemSetList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="itemtList" class="java.util.ArrayList" scope="request" />
<%@ page import="com.ait.ev.bean.EvaluateItem"%>
<%@ page import="com.ait.ev.bean.EvaluateSetItem"%>
<jsp:useBean id="evaluateSet" class="com.ait.ev.bean.EvaluateSetItem" scope="page" />
<jsp:useBean id="evaluateItem" class="com.ait.ev.bean.EvaluateItem" scope="page" />
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="simpleMap" class="com.ait.sqlmap.util.SimpleMap"/>
<jsp:useBean id="itemList1" class="java.util.ArrayList" scope="request" />
<html>
<head>
<script language="javascript">

function Save()
{	
	j = Number(document.form1.temp1.value);
	
	
	var arrayObj = new Array();
	　
	for(var z=0 ; z<=j ; z++){  
		if(document.getElementById('selectItem'+z) != null){
		   arrayObj[z] =document.getElementById('selectItem'+z).value;
		
		}	
	}
	
	var nary=arrayObj.sort();
	 for(var i=0;i<arrayObj.length-1;i++){
	 if (nary[i]==nary[i+1]){
	 alert("评价项目不能重复");
	 return false;
	 }
	}
	
    document.form1.action="/evControlServlet?operation=evaluateSet&content=evaluateItemSetModify&menu_code=${param.menu_code}&flag="+j;
	document.form1.submit();
}


function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell) {
	$('empID').value=cell.childNodes[0].firstChild.nodeValue;
	layerClose();
}
var time=null;
function SearchContent(condition,id){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	
	time=setTimeout(function(){
					///	alert(condition);
						SearchE(condition,id);
					},500);  
}


function SearchE(condition,id){
     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=paSearchEmployeeAll&condition=" + encodeURIComponent(condition)+"&id="+id;
		var inputBox = $(id);
		var iBtop  = inputBox.offsetTop;     //文本框的定位点高
		var iBheight  = inputBox.clientHeight;  //文本框本身的高
		var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
		while (inputBox = inputBox.offsetParent){
			iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;
		}
		
		layer = $('emp_list');
		layer.style.top = iBtop+iBheight+6;
		layer.style.left = iBleft;  
		new Ajax.Request(
            url,{method: 'post', parameters: pars, onComplete: showResponse}
        );
}
function showDept() {
          
          theUrl="/hrmControlServlet?menu_code=pa0102&operation=searchPaEmpByDept&empID=empID";
          var name="searchEmp";
          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
          window.open(theUrl,name,features);

}
function showResponse(XmlHttpRequest){
	var size = XmlHttpRequest.responseXML.getElementsByTagName("table")[0].getElementsByTagName("tr")[0].childNodes[4].firstChild.nodeValue;
    size = size*25+30;
    if(size<40){
		layer.style.height = 48; 
    }else if(size<210){
		layer.style.height = size;  
    }else{
		layer.style.height = 210; 
    }
    
	layer.innerHTML=XmlHttpRequest.responseText.replace("*","&");
    layer.style.visibility = 'visible';
}


    var type = null;
	var tableUtil = new Object();
	var i=0;
	
tableUtil.appendRow = function(){	

		i = Number(document.form1.temp1.value);
		document.form1.temp1.value = i+1;
		
		var nTr = document.getElementById('operateTable').insertRow();
		
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select name='selectItem"+i+"'> <c:forEach items='${itemList1}' var='itemList' varStatus='i'><option value='${itemList.ITEM_ID}'>${ itemList.ITEM_NAME }</option></c:forEach></select>" ;
		

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
        td.innerHTML = "<input id='proportion"+i+"' name='proportion"+i+"' size='8' />&nbsp;%";	     
		    
		
		var td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>";			
	}
	
	tableUtil.deleteRow = function(){
		document.form1.temp1.value = Number(document.form1.temp1.value)- 1;
		var radioArr = document.getElementsByName('rowNum');
		var tbody = document.getElementById('operateTable').tBodies[0];
		var flag = false;
		for(var i=0;i<radioArr.length;i++)
			if(radioArr[i].checked){
				tbody.removeChild(radioArr[i].parentNode.parentNode);
				flag = true;
			}
		if(flag)
			return;
		else
			alert('请先选择要删除的行');
	}
</script>
</head>
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif"><!-- display toolbar -->
		<%@ include file="../inc/common_toolbar_a.jsp"%>

		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER"><!-- display basic info --> <!-- display 3 level menu -->

		<!-- display content --> <br>
		<form name="form1" method="post" action=""><input type="hidden"
			name="wasteTypeId" value="" />
		<input type="hidden" name="applyLimit" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">评价项目设置</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			  <%for(int i=0;i<itemSetList.size();i++){                       
			evaluateSet = (EvaluateSetItem) itemSetList.get(i);
		    itemtList = evaluateSet.getItemList();
		    %>
			 <input type="hidden" name="temp1" value="<%=itemtList.size() %>">
            <tr>
				<td width="10%" class="info_title_01">评价月份</td>
				<td class="info_content_00">
					<%= evaluateSet.getMonth() %>
					<input type="hidden" name="MONTH_SEQ" value="<%=evaluateSet.getMonth()%>">
				</td>
			</tr>
			<tr>
			    
				<td width="10%" class="info_title_01">部门</td>
				<td width="10%" class="info_content_00"><%=evaluateSet.getDeptName() %>&nbsp; (<%=evaluateSet.getFourthDept() %>)
				<input type="hidden" name="DEPTID_SEQ" value="<%=evaluateSet.getDeptId()%>">
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">职位</td>
				<td class="info_content_00">
					<%=evaluateSet.getPositionName() %>
					<input type="hidden" name="POSITION_SEQ" value="<%=evaluateSet.getPosition()%>" />
				</td>
			</tr>
			<!-- 
			<tr>
				<td width="10%" class="info_title_01">评价项目</td>
				<td class="info_content_00">
					 <select name="selectItem">
						    <option value="">请选择</option>
							<c:forEach items="${itemList}" var="itemList" varStatus="i">
								
								<option value="${itemList.ITEM_ID}" <c:if test="${itemList.ITEM_ID==result.EVALUATE_ITEM_ID}">selected</c:if>>
									${itemList.ITEM_NAME}
								</option>
							</c:forEach>
						</select>
				</td>
			</tr>
			-->
			<tr>
				<td width="10%" class="info_title_01">评价项目</td>
				<td width="10%" class="info_title_01">比例</td>
				<td width="10%" class="info_title_01"></td>
			</tr>
		    <%	for(int j=0;j<itemtList.size();j++){            
				    	evaluateItem = (EvaluateItem) itemtList.get(j);%>
				    	
				<tr>
				
				
				
			
				<td class="info_content_01">			
				     <select name="selectItem<%=j%>">
					<option value=''>select</option> 
		             <%for(int k=0;k<itemList1.size();k++){ simpleMap=(SimpleMap)itemList1.get(k); %>
		             
		             <option value='<%=simpleMap.get("ITEM_ID")%>' <% if( evaluateItem.getItemId().equals(simpleMap.get("ITEM_ID") )){ %> selected <%}%> >
		             <%=simpleMap.get("ITEM_NAME")%>
		             </option> 
		             <% } %>
		             </select>
				</td>
				
				<td class="info_content_01">
					<input id="proportion<%=j%>" name="proportion<%=j%>" size="8"  value="<%=evaluateItem.getItemProportion() %>" />&nbsp;%
	
				</td>
				
				<td nowrap="nowrap" class="info_content_01">
				
					<% if(j == 0){ %>
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow(<%=j%>);" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow(<%=j%>);" name="a1" id="a1">
					<% }else {%>
					<input type='radio' name='rowNum' title='取消该行请选择后点击删除'/>
					<% } %>
				</td>
				
			    </tr>
				    
				 <%}%>
		
		 <%}%>
		</table>
		

					
		</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="15">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td background="../images/tablbk01_r4_c26.gif" width="10">&nbsp;</td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#569DD1" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r14_c26.gif"></td>
		<td width="11"></td>
	</tr>
</table>
<div id="emp_list"  style="position:absolute;overflow:auto; top:500;width:370; height:210; z-index:1;"></div>

</body>
<ait:xjos />
</html>
