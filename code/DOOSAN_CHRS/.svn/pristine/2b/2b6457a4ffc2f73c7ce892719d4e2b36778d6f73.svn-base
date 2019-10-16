<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<script language="javascript">

function Save()
{	
	if(document.form1.OCCUR_AMOUNT.value == null || document.form1.OCCUR_AMOUNT.value == ""){
		alert('请填写公积金数额');
		return;
	}
    document.form1.action="paControlServlet?operation=HousingFundRatioCmd&content=save&menu_code=${param.menu_code}";
	document.form1.submit();
}

function paseValueToAmount(value){   
                if(value!=null&&value!=''){   
                    var decimalIndex=value.indexOf('.');   
                    if(decimalIndex=='-1'){   
                        return false;   
                    }else{   
                        var decimalPart=value.substring(decimalIndex+1,value.length);   
                        if(decimalPart.length>2){   
                            return true;   
                        }else{   
                            return false;   
                        }   
                    }   
                }   
                return false;   
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
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td align="left" class="title1" colspan="10">公积金支出清单</td>
			</tr>
		</table>
		<table id="tableObj" width="100%" border="1" cellspacing="0" cellpadding="5"
			bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">

			<tr>
				<td width="15%" class="info_title_01">职号</td>
				<td class="info_content_00">
				<input name="person_id" type="hidden" value="${person_id }">
					${EMPID }
				</td>
				<td width="15%" class="info_title_01">姓名</td>
				<td class="info_content_00">
					${CHINESENAME }
				</td>
				<td width="15%" class="info_title_01">部门</td>
				<td class="info_content_00">
					${DEPTNAME }
				</td>
				<td width="15%" class="info_title_01">年月</td>
				<td class="info_content_00">
					<ait:date yearName="YEAR" monthName="MONTH" yearSelected="${YEAR}" monthSelected="${MONTH}" yearPlus="10" />
					<input name="date" type="hidden" value="${date }">
					<input name="falg" type="hidden" value="${falg }">
					<input name="seqno" type="hidden" value="${seqno }">
					<input name="deptID" type="hidden" value="${deptID }">
					<input name="YEAR1" type="hidden" value="${YEAR1 }">
					<input name="YEAR2" type="hidden" value="${YEAR2 }">
					<input name="MONTH1" type="hidden" value="${MONTH1 }">
					<input name="MONTH2" type="hidden" value="${MONTH2 }">
					<input name="key" type="hidden" value="${key }">
				</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">支给总额</td>
				<td class="info_content_00">
					${ZG}&nbsp;
				</td>
				<td width="15%" class="info_title_01">支出总额</td>
				<td class="info_content_00">
					${ZC }</td>
				<td width="15%" class="info_title_01">剩余总额</td>
				<td class="info_content_00">
					${SY }</td>
				<c:if test="${falg == '2'}">
				<td width="15%" class="info_title_01">支出公积金</td>
				</c:if>
				<c:if test="${falg == '1'}">
				<td width="15%" class="info_title_01">存入公积金</td>
				</c:if>
				<td class="info_content_00">
					<input name="OCCUR_AMOUNT" type="text" value="${OCCUR_AMOUNT }" onkeyup="if(isNaN(value)||paseValueToAmount(value))execCommand('undo')">
					</td>
			</tr>
			<tr>
				<td width="15%" class="info_title_01">备注</td>
				<td class="info_content_00" colspan="7">
					<textarea name="DESCRIPTION" rows="3" cols="50"></textarea>&nbsp;
			</tr>
			<c:forEach var="i" begin="1" end="6"
				step="1">
				<tr>
					<td class="info_content_01" height="30"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
					<td class="info_content_01"></td>
				</tr>
			</c:forEach>
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
</body>
<ait:xjos />
</html>
