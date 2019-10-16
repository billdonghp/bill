<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../inc/taglibs.jsp"%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../../css/default.css">
		<title>添加分类表格</title>
		<script type="text/javascript">
			function fillData(temp){
				value1 = document.getElementById('name').value ;
				value2 = document.getElementById('kor_name').value ;
				value3 = document.getElementById('en_name').value ;
				value4 = document.getElementById('show_model').value ;
				value5 = document.getElementById('report_type').value ;
				
				if(value1 == ''){
					alert('中文名字不能为空');
					document.getElementById('name').focus();
					return;
				}
				var showTable = parent.document.getElementById("showTable");
				var row = showTable.insertRow(showTable.rows.length);
				
				var cols1 = row.insertCell() ;
				var cols2 = row.insertCell() ;
				var cols3 = row.insertCell() ;
				var cols4 = row.insertCell() ;
				var cols5 = row.insertCell() ;
				var cols6 = row.insertCell() ;
				var cols7 = row.insertCell() ;
				
				cols1.className = 'info_content_01';
				cols1.innerHTML = '<input type="hidden" name="temp" value='+temp+'><input name="name'+temp+'" value='+value1+'>' ;
				cols2.className = 'info_content_01';
				cols2.innerHTML = '<input name="kor_name'+temp+'" value='+value2+'>' ;
				cols3.className = 'info_content_01';
				cols3.innerHTML = '<input name="en_name'+temp+'" value='+value3+'>' ;
				cols4.className = 'info_content_01';
				var tempVar = '<select name="show_model'+temp+'" id="show_model'+temp+'">';
				if(value4 == 1)
					tempVar += '<option value="1">竖表样式</option><option value="2">纵列样式</option><option value="3">日期样式</option><option value="4">横表样式</option>'
				else
					tempVar += '<option value="1">竖表样式</option><option value="2">纵列样式</option><option value="3" selected>日期样式</option><option value="4">横表样式</option>'
				tempVar += '</select>';
				cols4.innerHTML = tempVar ;
				cols5.className = 'info_content_01';
				var tempVar = '<select name="report_type'+temp+'" onchange="changeModel(this.value,'+temp+',0)">';
				if(value5 == 1)
					tempVar += '<option value="1">考勤信息</option><option value="2">工资信息</option>'
				else
					tempVar += '<option value="1">考勤信息</option><option value="2" selected>工资信息</option>'
				tempVar += '</select>';
				cols5.innerHTML = tempVar ;
				cols6.className = 'info_content_01';
    			cols6.innerHTML = '<span id="operateColumn" onclick="operateColumn(this.id,'+temp+',0)" style="cursor:hand">操作列</span>';
    			cols7.className = 'info_content_01';
    			cols7.innerHTML = '<span onclick="delDetail(this.parentNode.parentNode.rowIndex,0)" style="cursor:hand">删除</span>';
				parent.document.getElementById('table').style.visibility='hidden';
			}
			
			function changeModel(param){
				var obj = document.getElementById('show_model');
				if(param == 2){
					if(obj.length > 1)
						obj.length = 1 ;
				}else{
					if(obj.length == 1)
						var temp = document.createElement('option');
						temp.text = '日期样式';
						temp.value = 2;
						obj.add(temp);
				}
			}
		</script>
	</head>
	<body Style="margin-left:0px;margin-right: 0px;margin-bottom: 0px;margin-top: 0px;">
		<table width="370" class="dr_d" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td class="info_title_01" nowrap="nowrap">
					<!-- 请输入新建表的中文 -->
				
						请输入新建表的中文
				</td>
				<td class="info_content_01" width="30%">
					<input name="name" id="name">
				</td>
			</tr>
			<tr>
				<td class="info_title_01" nowrap="nowrap">
					<!-- 请输入新建表的韩文 -->
					请输入新建表的韩文
				</td>
				<td class="info_content_01" width="30%">
					<input name="kor_name" id="kor_name">
				</td>
			</tr>
			<tr>
				<td class="info_title_01" nowrap="nowrap">
					<!-- 请输入新建表的英文 -->
				请输入新建表的英文
				</td>
				<td class="info_content_01" width="30%">
					<input name="en_name" id="en_name">
				</td>
			</tr>
			<tr>
				<td class="info_title_01" nowrap="nowrap">
					请选择该表要显示的模式
				</td>
				<td class="info_content_01" width="30%">
					<select name="show_model" id="show_model">
							<option value="1">
								竖表样式
							</option>
							<option value="2">
								纵列样式
							</option>
							<option value="3">
								日期样式
							</option>
							<option value="4">
								横表样式
							</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="info_title_01" nowrap="nowrap">
					<!-- 请选择该表要显示的模式 -->
					请选择该表要显示的模式
				</td>
				<td class="info_content_01" width="30%">
					<select name="report_type" id="report_type" onchange="changeModel(this.value)">
							<option value="1">
								考勤信息
							</option>
							<option value="2">
								工资信息
							</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="info_content_01" nowrap="nowrap">
					<input type="button" value="保存" onclick="fillData(${param.temp })">
				</td>
				<td class="info_content_01" width="30%">
					<input type="button" value="关闭" onclick="javascript:parent.document.getElementById('table').style.visibility='hidden';">
				</td>
			</tr>
		</table>
	</body>
</html>
