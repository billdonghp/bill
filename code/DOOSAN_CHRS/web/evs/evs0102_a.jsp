<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" errorPage=""%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean"
	scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 基本设置 > 项目流程设置</title>
</head>
<body>
<link href="/css/default.css" rel="stylesheet" type="text/css">
<%@ include file="inc/evstoolbar_v.jsp"%>
<%@ include file="inc/evs_nav.jsp"%>
<form name="evs0101" method="post" action="">

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="1">
	<tr>
		<td align="right" height="20"><img src="/images/btn/addevprocess.gif"
			width="67" height="21" border="0" align="absmiddle" alt=""> <img
			src="/images/btn/addevitem.gif" width="67" height="21" border="0"
			align="absmiddle" alt=""> <select name="select">
			<option value="" selected>评价期间</option>
		</select> <select name="select">
			<option value="" selected>评价类型</option>
		</select></td>
	</tr>
	<tr>
		<td class="line">
		<table width="100%" height="149" border="1" align="center"
			cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7"
			bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center">
				<td width="15%" height="30" bgcolor="#F5F5F5">评价流程 | 评价项目</td>
				<td width="27%" bgcolor="#F5F5F5">基本目标</td>
				<td width="28%" bgcolor="#F5F5F5">难易贡献度</td>
				<td width="25%" bgcolor="#F5F5F5">-----------</td>


			</tr>
			<tr>
				<td height="30" class="tablecontent">
				<table width="99%" border="1" align="center" cellpadding="0"
					cellspacing="0" bordercolorlight="#E7E7E7"
					bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
					<tr>
						<td colspan="2" bgcolor="#F5F5F5" align="center">年初设定</td>
					</tr>
					<tr>
						
						<td width="46%">
						<input type="text" name="textfield"
							style="width:70; " title="开始时间" >
						</td>
						
						<td width="18%"><img src="../images/calendar/calender.gif"
							align="absMiddle" border="0" height="18" width="18"
							onClick="showCalendar('evs0101','ev_season_sdate');"
							style="cursor:hand"></td>
					</tr>
					<tr>

						<td><input type="text" name="textfield" style="width:70; " title="结束时间"></td>
						<td><img src="../images/calendar/calender.gif" align="absMiddle"
							border="0" height="18" width="18"
							onClick="showCalendar('evs0101','ev_season_sdate');"
							style="cursor:hand"></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3" title="可选分数值"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3" title="可选分数值"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5" title="流程百分比"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6" title="项目百分比"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7" title="默认分数"></td>
						<td><a title="操作方式">
						<select name="select2"></select></td>
					</tr>
				</table>
				</td>

				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7"></td>
						<td><select name="select2"></select></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7"></td>
						<td><select name="select2"></select></td>
					</tr>
				</table>
				</td>

			</tr>

			<tr>
				<td height="30" class="tablecontent">
				<table width="99%" border="1" align="center" cellpadding="0"
					cellspacing="0" bordercolorlight="#E7E7E7"
					bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
					<tr>
						<td colspan="2" bgcolor="#F5F5F5" align="center">一次打分</td>
					</tr>
					<tr>

						<td width="46%"><input type="text" name="textfield"
							style="width:70; "></td>
						<td width="18%"><img src="../images/calendar/calender.gif"
							align="absMiddle" border="0" height="18" width="18"
							onClick="showCalendar('evs0101','ev_season_sdate');"
							style="cursor:hand"></td>
					</tr>
					<tr>

						<td><input type="text" name="textfield" style="width:70; "></td>
						<td><img src="../images/calendar/calender.gif" align="absMiddle"
							border="0" height="18" width="18"
							onClick="showCalendar('evs0101','ev_season_sdate');"
							style="cursor:hand"></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7"></td>
						<td><select name="select2"></select></td>
					</tr>
				</table>
				</td>

				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7"></td>
						<td><select name="select2"></select></td>
					</tr>
				</table>
				</td>
				<td>
				<table border="1" align="center" cellpadding="0" cellspacing="0"
					bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
					style="padding: 2px 2px 2px 2px;">
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>

						<td colspan="2"><input type="text" style="width:120;"
							name="textfield3"></td>
					</tr>
					<tr>
						<td width="30"><input type="text" style="width:30;"
							name="textfield5"></td>

						<td width="40"><input type="text" style="width:30;"
							name="textfield6"></td>
					</tr>
					<tr>

						<td><input type="text" style="width:30;" name="textfield7"></td>
						<td><select name="select2"></select></td>
					</tr>
				</table>
				</td>

			</tr>
		</table>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>
