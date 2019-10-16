<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<jsp:directive.page import="com.ait.pa.PaReport"/>
<jsp:directive.page import="com.ait.sqlmap.util.SimpleMap"/>
<jsp:directive.page import="com.ait.sqlmap.util.ObjectBindUtil"/>
<jsp:directive.page import="com.ait.util.StringUtil,com.ait.util.ViewOptionUtil,com.ait.util.DateUtil"/>

<html>
<head>
<script language="javascript">

function Save()
{	
	if($('DeptId').value == "")
	{
		alert("请选择部门!");
		return false;
	}
	if($('selectPosition').value == "")
	{
		alert("请选择职位!");
		return false;
	}
	j = Number(document.form1.temp1.value);
	
	
	if(j==0){
		alert("请添加决裁者信息！");
		return;	
	}
	var arrayObj = new Array();　
	
	for(var z=0 ; z<=j ; z++){
      
	
		if(document.getElementById('level'+z) != null){
		   arrayObj[z] =document.getElementById('level'+z).value;
		  /// alert(arrayObj[z]+z);
		}
		if(document.form1('empID'+z) == "" ){
			alert("请输入决裁人!");
			return false;
		}
		
		if(document.form1('level'+z) == "" ){
			alert("请选择决裁等级!");
			return false;
		}		
	}
	
	
	var nary=arrayObj.sort();
	 for(var i=0;i<arrayObj.length-1;i++){
	 if (nary[i]==nary[i+1]){
	 alert("决裁等级不能重复");
	 return false;
	 }
	}

	
	///var group = document.getElementsByName('level[]');
    ///var len=group.length;
    
    ///alert(len);
	
	
    document.form1.action="/evControlServlet?operation=evaluateSet&content=evaluateAffirmSetAdd&menu_code=${param.menu_code}&flag="+j;
	document.form1.submit();
}


function layerClose()
{
	$('emp_list').innerHTML = "" ;
	layer.style.visibility = 'hidden';
	
}

function updateValue(cell,no) {

	$('empID'+no).value=cell.childNodes[0].firstChild.nodeValue;
	layerClose();
}

var time=null;
function SearchContent(condition,id,no){		
	if(time!=null){
		clearTimeout(time);
		time=null;  
	}
	
	time=setTimeout(function(){
					///	alert(condition);
						SearchE(condition,id,no);
					},500);  
}


function SearchE(condition,id,no){

     	var url = "/ajaxControlServlet" ;
     	var pars = "operation=evSearchEmployeeAll&condition=" + encodeURIComponent(condition)+"&id="+id+"&no="+no;
		///alert(pars);
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

		i = Number(document.form1.temp1.value)+1;
		document.form1.temp1.value = i;
		
		var nTr = document.getElementById('operateTable').insertRow();
		

		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<input id='empID"+i+"' name='empID"+i+"' size='8' onKeyUp='SearchContent(this.value,this.id,"+i+")'/>&nbsp;";	     
		     
		      
		td = nTr.insertCell() ;
		td.className = "info_content_01" ;
		td.innerHTML = "<select id='level"+i+"' name='level"+i+"'><option value='1'>1级</option><option value='2'>2级</option><option value='3'>3级</option><option value='4'>4级</option></select>";	     		
		
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
		<form name="form1" method="post" action="">
		<input type="hidden" name="isVarNum" value="0">	
		<input type="hidden" name="temp1" value="0">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">决裁者设置</td>
			</tr>
		</table>
		<table id="operateTable" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			<input type="hidden" name="gmIds" value="" />

			<tr>
				<td width="10%" class="info_title_01">部门</td>
				<td width="10%" class="info_content_00"><ait:selDept  name="DeptId" selected="${DeptId}" all="all" supervisorType="pa"/></td>
						
			</tr>
			<tr>
				<td width="10%" class="info_title_01">职位</td>
				<td class="info_content_00">
					 <select name="selectPosition">
						    <option value="">请选择</option>
							<c:forEach items="${positionList}" var="positionList" varStatus="i">
								
								<option value="${positionList.CODE_ID}" <c:if test="${positionList.CODE_ID==selectPosition}">selected</c:if>>
									${positionList.CODE_NAME}
								</option>
							</c:forEach>
						</select>
				</td>	
				<td nowrap="nowrap" class="info_content_01">
					<img src="../images/btn/Add.gif" style="cursor: pointer;" onclick="tableUtil.appendRow();" name="a1" id="a1">
					<img src="../images/btn/Delete.gif" style="cursor: pointer;" onclick="tableUtil.deleteRow();" name="a1" id="a1">
				</td>
			</tr>
			<tr>
				<td width="10%" class="info_title_01">决裁人</td>
				<td width="10%" class="info_title_01">决裁等级</td>
				<td width="10%" class="info_title_01"></td>
			</tr>
			<!--  <tr>
				
				<td width="10%" class="info_title_01">决裁人</td>
				<td class="info_content_00">
					<input id="empID111" name="empID111" size="8" onKeyUp="SearchContent(this.value,this.id)"  />
					<ait:image src="/images/btn/Dep.gif" align="absmiddle" onclick="showDept();" style="cursor:hand" />
				</td>
				<td width="10%" class="info_title_01">决裁等级</td>
				<td class="info_content_00">
					<select name="level">
						    <option value="">请选择</option>
						    <option value="1">1</option>
						    <option value="2">2</option>
						    <option value="3">3</option>
						    <option value="4">4</option>
						
						</select>
				</td>
			</tr>
				-->
				
			</tr>
		
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
