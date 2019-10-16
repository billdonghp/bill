<%@ include file="../inc/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ait.sy.bean.*,com.ait.sy.service.SysService"%>
<html>
	<head>
		<title>页面样式设置</title>
		<link rel="stylesheet" type="text/css" href="../css/default.css">
		<SCRIPT language="JavaScript" src="../js/prototype.js"></SCRIPT>
		<script type="text/javascript">
		var count = 0 ;
	function addTable(id,value1,value2,value3,value4){
		if($('table').style.visibility == 'hidden'){
			var inputBox = document.getElementById(id);
			var iBtop  = inputBox.offsetTop;     //文本框的定位点高
			var iBheight  = inputBox.clientHeight;  //文本框本身的高
			var iBwidth  = inputBox.clientWidth; 
			var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
			while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
			layer=document.getElementById("table");
			layer.style.top = iBtop+iBheight+22;
			layer.style.left = iBleft;
			layer.style.visibility='visible';
			count++ ;
			document.createViewTable.location.href = "/sy/inc/syAddViewTable.jsp?temp="+count;
		}
	}
	function delDetail(param,rt_no){
		var tempTable = $('showTable') ;
		if(rt_no != 0){
	    	if($('del_rt_no').value == '')
	    		$('del_rt_no').value = rt_no ;
	    	else{
	    		$('del_rt_no').value += ',' + rt_no ;
	    	}
	    	tempTable = $('dataTable');
	    }
		if(confirm("是否确定要删除?"))
	    	tempTable.deleteRow(param);
	}
	function operateColumn(id,row_temp,rt_no,cpnyId){
		if($('colTable').style.visibility == 'hidden'){
			var inputBox = document.getElementById(id);
			var iBtop  = inputBox.offsetTop;     //文本框的定位点高
			var iBheight  = inputBox.clientHeight;  //文本框本身的高
			var iBwidth  = inputBox.clientWidth; 
			var iBleft = inputBox.offsetLeft;    //文本框的定位点宽
			while (inputBox = inputBox.offsetParent){iBtop+=inputBox.offsetTop;iBleft+=inputBox.offsetLeft;}
			layer=document.getElementById("colTable");
			layer.style.top = iBtop+iBheight+22;
			layer.style.left = iBleft - layer.clientWidth;
			layer.style.visibility='visible';
			var type ;
			var showModel ;
			if(rt_no == 0){
				type = $('report_type'+row_temp).value ;
				showModel = $('show_model'+row_temp).value ;
				document.createTableColumn.location.href = "/sy/inc/syAddTableColumn.jsp?type="+type+"&row_temp="+row_temp+"&showModel="+showModel+"&cpnyId="+cpnyId;
			}else{
				type = $('report_type_'+rt_no).value ;
				showModel = $('show_model_'+rt_no).value ;
				document.createTableColumn.location.href = "/sy/inc/syAddTableColumn2.jsp?rt_no="+rt_no+"&type="+type+"&row_temp="+row_temp+"&showModel="+showModel+"&cpnyId="+cpnyId;
			}
		}
	}
	
	function modifyColumn(rt_no,report_type){
		window.open("/sy/inc/syModifyTableColumn.jsp?rt_no="+rt_no+"&report_type="+report_type) ;
	}
	
	var xmlHttp;
	
	function startRequest(){
		if(window.ActiveXObject){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}
		xmlHttp.onreadystatechange = handleStateChange;
		var menu = $('menu').value ;
		var cpnyId=document.viewOptionForm.cpnyId.value;
		var url="/sy/syAJAX.jsp?menu_code=sy0210&temp=row&menu="+menu+"&cpnyId="+cpnyId;

		xmlHttp.open("GET",url,true);
		xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		xmlHttp.send(null);
	}
	function handleStateChange(){
		if(xmlHttp.readyState ==4){
			if(xmlHttp.status ==200){
				var temp = xmlHttp.responseText;
				if(temp.indexOf('中文名称') != -1){
					var tempTable = $('tempTable');
					if($('tempTR'))
						tempTable.deleteRow($('tempTR').rowIndex);
					var row = tempTable.insertRow(tempTable.rows.length-1);
					row.id = 'tempTR' ;
					var cols1 = row.insertCell() ;
					cols1.colSpan = 4 ;
					cols1.innerHTML = temp;		
				}else{
					if($('tempTR'))
						$('tempTable').deleteRow($('tempTR').rowIndex);
				}
			}
		}
	}
	function Save(){
		if($('menu').value == ''){
			alert('请选择一个指定要构建的页面');
			return;
		}
		$('operation').value = 'save';
		$('content').value = 'viewoption' ;
		$('viewOptionForm').submit();
	}
	
	function recordUpdate(rt_no){
		///alert(123);
		if($('update_rt_no').value == '')
    		$('update_rt_no').value = rt_no ;
    	else{
    		$('update_rt_no').value += ',' + rt_no ;
    	}
	}
	
	function changeModel(param,count,flag){
		var obj ;
		if(flag)
			obj = document.getElementById('show_model_'+count);
		else
			obj = document.getElementById('show_model'+count);
		if(param == 2){
			if(obj.length > 1)
				obj.length = 1 ;
		}else{
			if(obj.length == 1)
				var temp = document.createElement('option');
				temp.text = '日期样式';
				temp.value = 2;
				obj.add(temp) ;
		}
	}
	
	function changeCpny(cpnyId){
	    document.viewOptionForm.content.value="searchOption"		
	    document.viewOptionForm.submit();
	}
</script>
	</head>
	<body>
		<%
		String cpnyId=request.getParameter("cpnyId");
		List menuList = SysService.getInstance().retrieveCanBeBuildMenu();
		request.setAttribute("menuList", menuList); 
		%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		
		<tr>
			<td width="15"></td>
			<td width="11" height="33" valign="TOP" align="RIGHT"><img
				src="images/tablbk01_r1_c1.gif"></td>
				
			<!-- display toolbar -->
			<td background="images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_a.jsp"%>
			
			<td width="10" height="33" align="LEFT" valign="TOP"><img
				src="images/tablbk01_r1_c26.gif"></td>
			<td width="10"></td>
		</tr>

			<tr>
				<td width="15"></td>
				<td background="images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
				<td valign="TOP" align="CENTER">
					<!-- display basic info -->

					<!-- display 3 level menu -->
					<%@ include file="../sy/inc/sy_toolbar.jsp"%>

					<!-- display content -->

					<table width="100%" border="0" cellpadding="0">
						<form id="viewOptionForm" name="viewOptionForm" method="post"
							action="/syControlServlet">
							<input type="hidden" id="operation" name="operation" value="view" />
							<input type="hidden" id="content" name="content" value="viewoption" />
							<input type="hidden" name="menu_code" value="sy0210" />
							<input type="hidden" name="del_rt_no" id="del_rt_no" value="" />
							<input type="hidden" name="update_rt_no" id="update_rt_no" value="" />
							<input type="hidden" name="temp_for_add_item" id="temp_for_add_item" value="" />
							<input type="hidden" name="add_item_nos" id="add_item_nos" value="" />
							<input type="hidden" name="add_order_nos" id="add_order_nos" value="" />
							<input type="hidden" name="add_item_ids" id="add_item_ids" value="" />
							<input type="hidden" name="add_ref_tn" id="add_ref_tn" value="" />
							<input type="hidden" name="add_item_names" id="add_item_names" value="" />
							<input type="hidden" name="add_item_kor_names" id="add_item_kor_names" value="" />
							<input type="hidden" name="add_item_en_names" id="add_item_en_names" value="" />
						<tr>
							<td class="line" nowrap="nowrap">
								<table id='tempTable' width="100%" border="0" cellpadding="0" cellspacing="1"
					class="dr_d">
									<tr>
									   <td class="info_title_01" nowrap="nowrap">
											 法人区分&nbsp;&nbsp; 
									 </td>
									   <td align="center" class="info_content_01" nowrap="nowrap">									
									    <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="changeCpny(this.value)"/>									    
			                           </td>
										<td class="info_title_01" nowrap="nowrap">
											<!--构建页面 -->
											<ait:message
												messageID="display.sys.parameter_setting.view_option.select_menu"
												module="sys"></ait:message>
										</td>
										<td class="info_content_01" width="30%">
											<ait:select onChange="startRequest();" dataListName="menuList" name="menu" all="all"/>
										</td>
									</tr>
									<tr>
										<td colspan="4" class="info_title_01">
											<span id='addTable' style="cursor:pointer;" onclick="addTable(this.id)">添加表</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="line" nowrap="nowrap">
							<table id='showTable' width="100%" border="0" cellpadding="0" cellspacing="1" class="dr_d">
								<tr>
									<td class="info_title_01" nowrap="nowrap">中文名称</td>
									<td class="info_title_01" nowrap="nowrap">韩文名称</td>
									<td class="info_title_01" nowrap="nowrap">英文名称</td>
									<td class="info_title_01" nowrap="nowrap">显示模式</td>
									<td class="info_title_01" nowrap="nowrap">数据归属</td>
									<td class="info_title_01" nowrap="nowrap">&nbsp;</td>
									<td class="info_title_01" nowrap="nowrap">&nbsp;</td>
								</tr>
								<tr></tr>
							</table>
							</td>
						</tr>
						</form>
					</table>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="15">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
				<td background="../images/tablbk01_r4_c26.gif" width="10">
					&nbsp;
				</td>
				<td width="11"></td>
			</tr>
			<tr>
		<td width="15"></td>
		<td width="11" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c1.gif"></td>
		<td bgcolor="#71A9DA" height="5"></td>
		<td width="10" height="5" align="LEFT" valign="TOP"><img
			src="images/tablbk01_r14_c26.gif"></td>
		<td width="10"></td>
	</tr>
		</table>
		<div id='table'
			style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
			<iframe id="createViewTable" name="createViewTable" width="370"
				height="200" frameborder="0" marginwidth="0" marginheight="0"
				hspace="0" vspace="0"></iframe>
		</div>
		<div id='colTable'
			style="position:absolute; top:100; width:370; height:200; z-index:1; layer-background-color: #FFFFCC; background: #99ddcc; border: 1px none #000000; visibility: hidden;">
			<iframe id="createTableColumn" name="createTableColumn" width="370"
				height="500" frameborder="0" marginwidth="0" marginheight="0"
				hspace="0" vspace="0"></iframe>
		</div>
	</body>
</html>
