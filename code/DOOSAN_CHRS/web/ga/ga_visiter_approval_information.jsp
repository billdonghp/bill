<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<jsp:useBean id="allvisiterApplyInformation" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="flag" class="java.lang.String" scope="request"/>
<jsp:useBean id="chinesename" class="java.lang.String" scope="request"/>
<jsp:useBean id="level" class="java.lang.String" scope="request"/>
<jsp:useBean id="affirmorId" class="java.lang.String" scope="request"/>
<jsp:useBean id="affirmId" class="java.lang.String" scope="request"/>
<jsp:useBean id="applyId" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>参观者情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
function ImpExcel(applyno){
document.form1.action="/xlsReportServlet?operation=crystal&xlsKey=ga_visiter_excel&applyno="+applyno;
document.form1.submit();
}

 function search(){
 document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=visiterApprovalInformation";
 document.form1.submit(); 
 }
 
 function showMemo(val) {
     var memo = $('opition_'+val).innerHTML;
	var html = "<div style='background-color:#FFFFFF;height: 100%;text-align: left;'>";
	html +="<br>";
	html +=memo;
	html +="</div>";
	var	editDlg = new Ext.Window({
				modal: true
				 , width: 450
				  , height: 200
				 , shadow: true
				 , closable: true
				  , layout : 'fit'
				 , html : html
				 ,resizable : true
				 ,autoScroll:true
				 ,plain : true	
				 ,title : '决裁意见'
			});
		editDlg.setPosition(400,200);
		editDlg.show();	
} 
 //满意度
 function Update2(){
	  var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
			
	    	  if(document.form1["MANY_"+countsArr[i].value].value==null || document.form1["MANY_"+countsArr[i].value].value==""){
			      alert("请填写内容！！！");
			      document.form1["MANY_"+countsArr[i].value].onfocus();
			      return;
			    }
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要保存的信息！");
			   return;
	   }else{
		   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesEnvironment&content=updateVisiterManYiDu";
		   document.form1.submit();
		   }
}
 function checkAll()
 {
     var selected = document.form1.ck_all.value == "0" ? true : false;
     var countsArr = document.getElementsByName("counts") ;
   	for (var i = 0; i< countsArr.length ; i++){
 		countsArr[i].checked = selected ;
 	
 	}
     document.form1.ck_all.value = selected ? "1" : "0";
 }
 function ExportExcel() {
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=facilitiesMaEnvironment&content=excelvisiterview";
		document.form1.submit();
	}
</SCRIPT>

<body>
<form name="form1" method="post" action="">
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"><input type="hidden" name="ck_all" value="0" /></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
		
			<!-- display toolbar -->
			<%@ include file="inc/gatoolbar_none.jsp"%>
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
	<table width="100%" height="30" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="title1"><!-- 查询条件 -->
		<ait:message messageID="display.mutual.search_criteria" module="ess"></ait:message>		
				</td>
			</tr>
		    <tr>        
		      <td colspan="9">
		      <table width="100%" height="30" border="0" cellpadding="0"
					cellspacing="1" class="dr_d">
		        <tr>
		          <td  class="info_title_01" nowrap="nowrap"><!-- 开始日期 -->
		    <ait:message messageID="display.mutual.start_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00" nowrap="nowrap"><input type="text" name="startDate" style="width:70px" value="${startDate}" onClick="setday(this);" /></td>
		          <td  class="info_title_01"><!--  结束日期-->
		  <ait:message messageID="display.mutual.end_date" module="ess"></ait:message> 
		          </td>
		          <td  class="info_content_00">
		          <input type="text" name="endDate" style="width:70px" value="${endDate}" onClick="setday(this);" /></td>                                                                                                                      
		           
		       	  <td class="info_title_01" nowrap="nowrap"><!--  状态-->
		   <ait:message messageID="display.mutual.status" module="ess"></ait:message>            	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3">全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未确认</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已确认</option>
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>已否决</option>                
				     </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
				  <td class="info_content_00"><img	src="../images/btn/Excel_Exp.gif" style="cursor: pointer;"
				  	onclick="ExportExcel();" />						
						</td>
		        </tr> 
		        <tr>
		         <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" onChange="search();"/>
		         </td> 
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		     <ait:message messageID="display.mutual.key_word" module="ess"></ait:message>                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>                    
		        <td class="info_content_00">
							<font color="red">满意度</font>
						<ait:image src="/images/btn/Save.gif"  border="0" align="absmiddle" 
          				onclick="javascript:Update2();" style="cursor:hand" title="保存" enTitle="update" />				
						</td>	
		        </tr>      
		        </table>
		      </td>
			</tr>
			</table>
	
		<!-- display 3 level menu -->
		<%@ include file="../inc/thirdToolBar.jsp"%>
		
		<!-- display content -->
		<br>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td align="left" class="title1" colspan="10">
				参观者情况
				</td>
			</tr>     
		</table>
		<%if (!errorMsg.equals("")) {%>
		  <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF" style="padding: 2px 2px 2px 2px;">
			<tr align="center"><td align="center"><font color="red"><%=errorMsg%></font></td></tr>
		  </table>
		<%}%>
		
		<table width="100%"  border="1" cellspacing="0" cellpadding="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF">
		    <tr align="center" bgcolor="#F5F5F5">
		      <td nowrap="nowrap" class="info_title_01"><a href="#"
					onclick="checkAll();" style="color: red;"><ait:message
					messageID="display.mutual.select_2" module="ess" /></a></td>
		      <td nowrap="nowrap" class="info_title_01">
				编号</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				申请人</td>
<!--		      <td nowrap="nowrap" class="info_title_01"  >
				职务</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				工作单位</td>
			  <td nowrap="nowrap" class="info_title_01"  >
				区分</td>			 
			  <td nowrap="nowrap" class="info_title_01"  >
				来访日期</td>-->
			  <!--<td nowrap="nowrap" class="info_title_01" style="width: 130px">
				截止时间</td>
			  -->
			  <td nowrap="nowrap" class="info_title_01">
				来访日期</td>
			  <td nowrap="nowrap" class="info_title_01">
				到达时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				离开时间</td>
			  <td nowrap="nowrap" class="info_title_01">
				来访单位</td>
			  <td nowrap="nowrap" class="info_title_01">
				访问地点</td>
		      <td nowrap="nowrap" class="info_title_01">
				来访目的</td>
			  <td nowrap="nowrap" class="info_title_01">
				来访人数</td>
			  <td nowrap="nowrap" class="info_title_01" >
				查看详情</td>			 
			  <td nowrap="nowrap" class="info_title_01">
				决裁情况</td>
			  <td nowrap="nowrap" class="info_title_01">
				确认情况</td>
				<td nowrap="nowrap" class="info_title_01">意见</td>
				<td nowrap="nowrap" class="info_title_01">满意度</td>
			  <td nowrap="nowrap" class="info_title_01" >
				操作</td>
			  <td nowrap="nowrap" class="info_title_01" >
				导出申请书</td>
			  
		    </tr>
			    <c:forEach items="${allvisiterApplyInformation}" var="all" varStatus="i">
				    <tr align="center">
				    <td nowrap="nowrap" align="center" class="info_content_01">
						<input type="checkbox" name="counts" value="${i.index}">
					&nbsp;</td>				    
				      <td nowrap="nowrap" class="info_content_01">
			    			&nbsp;${all.GA_VISITER_APPLY_ID}
				      </td>
				     <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.PLAY_APPLYCHINESENAME}
				      </td>
				 <!--      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DUTY}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DISTINICTION}
				      </td>				      -->
				      <td nowrap="nowrap" class="info_content_01">
				      		&nbsp;${all.VISITER_DATE}
				      </td>
				      <!--<td align="center" class="info_content_01">
				      		&nbsp;${all.VISITER_OUT_DATE}
				      </td>
				      --><td nowrap="nowrap" >
				      		&nbsp;${all.VISITER_COME_TIME}
				      		<input type="hidden" name="VISITER_COME_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_COME_TIME}">
				    		 <input type="hidden" name="APPLYNO_${i.index}"	value="${all.GA_VISITER_APPLY_ID}">
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISITER_END_TIME}<input type="hidden" name="VISITER_END_TIME_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_END_TIME}">
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISITER_COMPANY}
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISIT_PLACE}
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISITER_OBJECTIVE}
				      </td>
				      <td nowrap="nowrap" >
				      		&nbsp;${all.VISIT_COUNT}<input type="hidden" name="VISITER_PEOPLE_NUM_${all.GA_VISITER_APPLY_ID}" value="${all.VISITER_PEOPLE_NUM}">
				      </td>
				      <td nowrap="nowrap" >
				      		<a href="/gaControlServlet?menu_code=${param.menu_code}&operation=visiterApplications&content=viewDetail&applyno=${all.GA_VISITER_APPLY_ID}" style="color:red">查看详情</a>
				      </td>	      
					      <td nowrap="nowrap" class="info_content_01">
						     <c:forEach items="${all.allVisiterApproval}" var="view" varStatus="j">
						      	${view.AFFIRM_LEVEL}${view.CHINESENAME}
						      	<c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
						      	<c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
						      	<br>
					      	 </c:forEach>
					      </td>
					      <td nowrap="nowrap" class="info_content_01">
					      	<c:if test="${all.MODIFY_FLAG == 0}"><font color="red">未确认 </font></c:if>
					      	<c:if test="${all.MODIFY_FLAG == 1}"><font color="#00CC00">已确认</font></c:if>
					      	<c:if test="${all.MODIFY_FLAG == 2}"><font color="red">以否决</font></c:if>
					      </td>
					      <td class="info_content_01">
					      
						<a href="#" onClick="showMemo('${i.index}')">详情</a></td>
						<td nowrap="nowrap" align="center" class="info_content_06">
							<c:if test="${ all.PLAY_APPLY eq adminID}">
						      <select	name="MANY_${i.index}"  type="_moz" >
								<option value="" <c:if test="${all.MANY==NULL}">selected</c:if>>请选择</option>
								<option value="5" <c:if test="${all.MANY==5}">selected</c:if>>5</option>
								<option value="4" <c:if test="${all.MANY==4}">selected</c:if>>4</option>
								<option value="3" <c:if test="${all.MANY==3}">selected</c:if>>3</option>
								<option value="2" <c:if test="${all.MANY==2}">selected</c:if>>2</option>
								<option value="1" <c:if test="${all.MANY==1}">selected</c:if>>1</option>
							     </select>
						     </c:if>
						     <c:if test="${ all.PLAY_APPLY ne adminID }">
						     ${all.MANY}
 							</c:if>
						</td>
						<div id="opition_${i.index}" style="display: none;">
							<c:forEach items="${all.allVisiterApproval}" var="view">
									  <p>
									  <font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME}</font>
							          <c:if test="${view.AFFIRM_FLAG==0}"><font color="#00CC00">未决裁</font></c:if>
								      <c:if test="${view.AFFIRM_FLAG==1}"><font color="#00CC00">通过</font></c:if>
								      <c:if test="${view.AFFIRM_FLAG==2}"><font color="#00CC00">已否决</font></c:if>
							          &nbsp;${view.AFFIRMORIDEA}
							          </p>
									
							</c:forEach>
							<p>
							<c:if test="${all.CHINESENAME1!=null}"><font color="#990099">${all.CHINESENAME1}</font></c:if>
							<c:if test="${all.CHINESENAME1==null}"><font color="#990099">&nbsp;&nbsp;&nbsp;担当</font> </c:if>							
							<c:if test="${all.MODIFY_FLAG == 0}"><font color="red">未确认</font></c:if>
					      	<c:if test="${all.MODIFY_FLAG == '1' }"><font color="#00CC00">已确认</font></c:if>
					      	<c:if test="${all.MODIFY_FLAG == '2'}"><font color="red">以否决</font></c:if>
				          	&nbsp;${all.CONFIRMIDEA}				          	
				          	</p>
						
					</div>
						
						
					      <td nowrap="nowrap" class="info_content_01">
						      	<c:if test="${all.AFFIRM_FLAG == 0 && all.PLAY_APPLY eq adminID}">
						      		<a href="/gaControlServlet?menu_code=ga0510&operation=visiterApplications&content=deleteVisiterApproval&ga_visiter_apply_id=${all.GA_VISITER_APPLY_ID}" onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font color="red">删除</font></a>
						      	</c:if>
						      	<c:if test="${all.AFFIRM_FLAG!=0}">&nbsp;</c:if>
						      	&nbsp;
					      </td>
					  <td nowrap="nowrap" class="info_content_01">
				      		<img src="../images/btn/Excel_Exp.gif" style="cursor: pointer;" onclick="ImpExcel('${all.GA_VISITER_APPLY_ID}');"/>
				      </td>
				    </tr>
			    </c:forEach>
		    <tr align="center"> 
			 </tr>
		 </table>
		   <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=visiterApplications&content=visiterApprovalInformation&menu_code=${param.menu_code}&startDate=${startDate}&endDate=${endDate}&qryType=${qryType}&deptid=${deptid}&key=${key}"
		               total="${recordCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
	<!-- Page Navigation End -->
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
<input type="hidden" name="currentPage" value="${currentPage}">
</form>
<table>
 <tr>  
  </tr>
  <input type="hidden" name="menu_code" value="<%=menu_code%>" />
</table>
</body>

</html>