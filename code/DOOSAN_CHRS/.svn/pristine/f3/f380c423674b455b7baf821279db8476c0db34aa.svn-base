<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../inc/taglibs.jsp"%>
<jsp:useBean id="gm_type" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="gmtypeSize" class="java.lang.String" scope="request" />
<jsp:useBean id="empNameSize" class="java.lang.String" scope="request" />
<jsp:useBean id="deptid" class="java.lang.String" scope="request" />
<%@ page import="com.ait.gm.bean.*"%>
<jsp:useBean id="gmBean" class="com.ait.gm.bean.GmBean"/>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<html>
<head>
<%@ include file="../inc/meta.jsp" %>
<title>人数申报页面</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
<%  
if(request.getAttribute("alert") != null) {%>
  alert("<%=request.getAttribute("alert")%>");
<%}%>
function hrefAddsubmit(){

	BeSelectValue();
 	document.form1.action="/gmControlServlet?operation=addEatApply&menu_code=${param.menu_code}&flag=1&empnames="+document.form1.empids.value+"&gmids="+document.form1.gmids.value;
 	document.form1.submit();
}

function deptchange(){
 	document.form1.action="/gmControlServlet?menu_code=${param.menu_code}&operation=eatApply";
 	document.form1.submit();
}

function changeNum(num1,num2){

	if(document.form1["empName"+num1+num2].checked==true){
	
		document.form1["gm_num"+num1].value=parseInt(document.form1["gm_num"+num1].value)+1;
		
		document.form1.heji.value=parseInt(document.form1.heji.value)+1;
		
	}
	else{
		document.form1["gm_num"+num1].value=parseInt(document.form1["gm_num"+num1].value)-1;
		document.form1.heji.value=parseInt(document.form1.heji.value)-1;
	}
}


function BeSelectValue(){
		var gmtypeSize = Number(document.form1.gmtypeSize.value);
		var empNameSize = Number(document.form1.empNameSize.value);
		
		for(var i=0 ; i<gmtypeSize ; i++){
			var gmid = document.form1["gm_id"+i].value;
			var checkFlag = false ;
			var gmids = '';
			var empNames = '';
			for(var j=0 ; j<empNameSize ; j++){
				if(document.form1["empName"+i+j].checked==true){
					checkFlag = true ;
					
					empNames += document.form1["empName"+i+j].value+",";
				}
			}
			
			if(checkFlag == true){
				gmids += document.form1["gm_id"+i].value;
			}
			
			if(gmid!=null && checkFlag == true){
				document.form1['empId'+i].value = empNames;
				document.form1['gmid'+i].value = gmids;
				
				document.form1.empids.value = document.form1['empId'+i].value;
				document.form1.gmids.value = document.form1['gmid'+i].value;
			}
		}
}

function allChecked(iindex){
	for(var i=0 ; i<Number(document.form1.gmtypeSize.value) ; i++){
		for(var j=0 ; j<Number(document.form1.empNameSize.value) ; j++){
			if(document.form1['allSelect_'+i].checked == true){
				document.form1['empName'+i+j].checked = true;
			}
			if(document.form1['allSelect_'+i].checked == false){
				document.form1['empName'+i+j].checked = false;
			}
		}	
	}
	var a = 0;
	for(var j=0 ; j<Number(document.form1.empNameSize.value) ; j++){
		if(document.form1['empName'+iindex+j].checked){
			a++;
		}
	}
	document.form1['gm_num'+iindex].value = a;
	document.form1.heji.value = "";
	for(var i=0 ; i<Number(document.form1.gmtypeSize.value) ; i++){
		document.form1.heji.value = Number(document.form1.heji.value) + Number(document.form1['gm_num'+i].value);
	}
}

</SCRIPT>
<body>

<form name="form1" method="post">
<input type="hidden" name="empids" value=""/>
<input type="hidden" name="gmids" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="../inc/common_toolbar_none.jsp"%>
		</td>
		<td width="10" height="33" align="LEFT" valign="TOP"><img
			src="../images/tablbk01_r1_c26.gif"></td>
		<td width="11"></td>
	</tr>
	<tr>
		<td width="15"></td>
		<td background="../images/tablbk01_r4_c1.gif" width="11">&nbsp;</td>
		<td valign="TOP" align="CENTER">
		
		<!-- display basic info -->
		<br>
		
		<!-- display 3 level menu -->
		<%@ include file="../inc/common_toolbar.jsp"%>
		
		<!-- display content -->
		<br>

		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10"><ait:message messageID="display.gm.eatery.plan.apply" module="gm"></ait:message></td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" colspan="10">&nbsp;&nbsp;
				<!--<ait:selDept name="deptlist" all="请选择部门" selected="${deptid}" onChange="deptchange();" supervisorType="hr"/>-->
					<input type="hidden" name="deptlist" value="<%=admin.getDeptID() %>">
				</td>
			</tr>
		</table>
		
		<table width="100%"  border="1"cellspacing="0" cellpadding="2" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
		    
		 		<tr align="left" bgcolor="#F5F5F5">
		 		<input type="hidden" name="gmtypeSize" value="${gmtypeSize}"/>
		    	<input type="hidden" name="empNameSize" value="${empNameSize}"/>
			 		<c:forEach items="${gm_type}" var="gmtype" varStatus="i">
				          	<input type="hidden" name="gm_type${i.index}" value="${gmtype.GM_TYPE}"/>
				          	<input type="hidden" name="gm_id${i.index}" value="${gmtype.GM_ID}"/>
				          	<input type="hidden" name="empId${i.index}" value=""/>
				          	<input type="hidden" name="gmid${i.index}" value=""/>
			 				<input type="hidden" name="gmtypeID${i.index}" value="${gmtype.GM_ID}"/>
					      <td width="10%" class="info_title_01">
				          	${gmtype.GM_TYPE}实际人数:
				          	<input type="text" name="gm_num${i.index}" value="${gmtype.numChushihua}" readonly="readonly" style="width:50px"/>
				         	<br>
				         	${gmtype.GM_TYPE}预测人数:
				         	<input type="text" name="Forecast_gm_num${i.index}" value="${gmtype.numChushihua}" readonly="readonly" style="width:50px"/> 
				         	 <br>
				         	 实际就餐时间&nbsp;&nbsp;${gmtype.eatDate}
				         	 <br>
				         	 <input type="checkbox" name="allSelect_${i.index}" value="" onclick="allChecked(${i.index})"/>全选
				          </td>
			          </c:forEach>
			    </tr>
			    <tr align="center" style="color: #666666;">
			    	<c:forEach items="${gm_type}" var="gmtype" varStatus="i">
			    	<td width="10%">
			    		<table>
				    		<tr>
				    		<c:forEach items="${gmtype.empNameList}" var="empName" varStatus="j">
				    		<td align="center" style="color: #666666;">
					    			<input type="checkbox" name="empName${i.index}${j.index}"
					    				onclick=changeNum(${i.index},${j.index}); value="${empName.EMPID}"
					    					 ${empName.CHECKED}
					    			/>
					    	</td>
					    	<td align="center" style="color: #666666;">
					    		${empName.CHINESENAME}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	</td>
					    		<c:if test="${(j.index+1)%2==0}">
										<tr></tr>
							    </c:if>
					    	</c:forEach>
		    				</tr>
			    		</table>
			    	</td>
			    	</c:forEach>
			     </tr>
			     <tr align="left" bgcolor="#F5F5F5">
			    <td width="10%" class="info_title_01" colspan="12">
		          	就餐人数合计:
		          	<input type="text" name="heji" value="${total_peoples}" readonly="readonly" style="width:50px"/>
		        </td>
	        </tr>
		    <tr align="center">
		    <td width="10%" class="info_title_01" colspan="12">
		          	<c:if test="${ifApplyFlag==0 }"><img src="../images/submit.gif" style="cursor: pointer;" onClick="hrefAddsubmit()"/></c:if>
		          	<c:if test="${ifApplyFlag!=0 }"><font color=red>本部门相关人员今日的就餐计划已经提交完毕。</font></c:if>
		          	<input type="hidden" name="Eatery_num" value="<%=gm_type.size() %>">
		        </td></tr>
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
</html>