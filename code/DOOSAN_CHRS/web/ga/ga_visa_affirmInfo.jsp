<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="../inc/taglibs.jsp"%>
<%@ include file="../inc/meta.jsp" %>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
<jsp:useBean id="errorMsg" class="java.lang.String" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/default.css" rel="stylesheet" type="text/css">
<link href="../css/xjos.css" rel="stylesheet" type="text/css">
<link href="../css/paging.css" rel="stylesheet" type="text/css">
<title>签证情况</title>
</head>
<script src="../js/prototype.js"></script>
<SCRIPT type="text/javascript">
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

 function search(){
 	document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visaManger&content=sealAffirmInfo";
    document.form1.submit(); 
 }  
 function ExportExcel() {
		document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visaManger&content=excelSealInfo";
		document.form1.submit();
	}
 
function viewFile(fileAddress,documentno){
window.open(fileAddress,"","menubar=no, toolbar=no, scrollbars=no, status=no, resizable=yes, location=no, directories=no, copyhistory=no" );
} 
//满意度
function Update2(){
	  var selectFlag=false;
	   var countsArr=document.getElementsByName("counts");
	   for(var i=0;i<countsArr.length;i++){
	      if(countsArr[i].checked){
			
	    	  if(document.form1["MANYZH_"+countsArr[i].value].value==null || document.form1["MANYZH_"+countsArr[i].value].value==""){
			      alert("请填写内容！！！");
			      document.form1["MANYZH_"+countsArr[i].value].onfocus();
			      return;
			    }
	       selectFlag=true;
	      }   
	   }
	   if(!selectFlag){
	          alert("请选择要保存的信息！");
			   return;
	   }else{
		   document.form1.action="/gaControlServlet?menu_code=${param.menu_code}&operation=visaManger&content=updateVisaManYiDu";
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
</SCRIPT>

<body>
<form action="" name="form1" method="post">
	
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
		            <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		    		签证类型
		          </td> 
		          <td class="info_content_00" nowrap="nowrap"> 
		           <select name="qryType2">
				         <option value="" <c:if test="${qryType2==''}">selected</c:if>>请选择</option>
				         <option value="0" <c:if test="${qryType2=='0'}">selected</c:if>>单次</option>				     
				         <option value="1" <c:if test="${qryType2=='1'}">selected</c:if>>多次</option>   
				         </select></td>
		       	  <td class="info_title_01" nowrap="nowrap">进度     	  
		       	  </td>
		          <td class="info_content_00" nowrap="nowrap">
				     <select name="qryType">
				         <option value="3" <c:if test="${qryType==3}">selected</c:if>>全部</option>
				         <option value="0" <c:if test="${qryType==0}">selected</c:if> >未完成</option>   
				         <option value="1" <c:if test="${qryType==1}">selected</c:if>>已完成</option>				         
				         <option value="2" <c:if test="${qryType==2}">selected</c:if>>拒签</option>
				     </select>
		         <ait:image src="/images/btn/Search.gif" align="absmiddle"	onclick="javascript:search();" style="cursor:hand" />
				  </td>                
		        </tr> 
		        <tr>
		         <td  class="info_title_01">法人区分</td>
		         <td  class="info_content_00">
		          <ait:codeClass codeClass="EMP_DIFF" name="cpnyId" selected="${cpnyId}" />
		        </td>     
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		    		紧急度         
		          </td> 
		         <td class="info_content_00" nowrap="nowrap"> 
		           <select name="qryType3">
				         <option value="" <c:if test="${qryType3==''}">selected</c:if>>请选择</option>
				         <option value="0" <c:if test="${qryType3=='0'}">selected</c:if>>普通（10个工作日）</option>				     
				         <option value="1" <c:if test="${qryType3=='1'}">selected</c:if>>加急（2个工作日）</option>   
				         </select>
				  </td>
		         <td  class="info_title_01" nowrap="nowrap"><!-- 部门 -->
		        <ait:message messageID="display.mutual.department" module="ess"></ait:message>               
		          </td>                                                                                                                
		          <td class="info_content_00" nowrap="nowrap">
		          <ait:selDeptByCpnyId name="deptid" supervisorType="pa" all="all" cpnyId ="${cpnyId}" selected="${deptid}"/>
		          </td>
		        
		          <td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		    		申请人                
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key" value="${key}"  title="输入姓名或者职号"/></td>                  
		        </tr>    
		        <tr>
		        
		        	<td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		    		出差国家        
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key2" value="${key2}"  title="输入出差国家"/></td>
		       		
		       
		       		<td  class="info_title_01" nowrap="nowrap"><!-- 关键字 -->
		    		出差内容
		          </td> 
		          <td class="info_content_00" nowrap="nowrap">  <input type="text" name="key3" value="${key3}"  title="输入出差内容"/></td>
		        <td class="info_title_01">&nbsp;签证情况</td>
						<td class="info_content_00"><img
							src="../images/btn/Excel_Exp.gif" style="cursor: pointer;"
							onclick="ExportExcel();" />						
						</td>	
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
				签证情况
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
			  <td nowrap="nowrap" class="info_title_01">
				姓名</td>
			  <td nowrap="nowrap" class="info_title_01">
				部门</td>
			   <td nowrap="nowrap" class="info_title_01">
				申请日期</td>
		       <td nowrap="nowrap" class="info_title_01">
				出差日期(From)</td>
			
				 <td nowrap="nowrap" class="info_title_01">
				完成日期</td>
				 <td nowrap="nowrap" class="info_title_01">
				提前日数</td>
		      <td nowrap="nowrap" class="info_title_01">
				出差国家</td>
		      <td nowrap="nowrap" class="info_title_01">
				签证类型</td>
			  <td nowrap="nowrap" class="info_title_01">
				出差内容</td>	
		      <td nowrap="nowrap" class="info_title_01">
				紧急度 </td>			 	    
			  <td nowrap="nowrap" align="center" class="info_title_01">
				备注</td>
			  <td nowrap="nowrap" class="info_title_01">
				决裁</td>
			  <td nowrap="nowrap" class="info_title_01">意见</td>
			  <td nowrap="nowrap" class="info_title_01">进度</td>
			 <td nowrap="nowrap" class="info_title_01">
			综合满意度</td>	
			<td nowrap="nowrap" class="info_title_01">
			服务态度满意度</td>
			<td nowrap="nowrap" class="info_title_01">
			纳期遵守度</td>		  
			  <td nowrap="nowrap" class="info_title_01">
				操作</td>
		    </tr>
		    <c:forEach items="${allsealAffrimInfoList}" var="all"  varStatus="i">
		    <tr align="center">
		    
			<td nowrap="nowrap" align="center" class="info_content_01">
						<input type="checkbox" name="counts" value="${i.index}">
						 <input type="hidden" name="APPLYNO_${i.index}"	value="${all.DOCUMENTNO}">
					&nbsp;</td>	
		      <td nowrap="nowrap" align="center" class="info_content_01" >${all.DOCUMENTNO }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.CHINESENAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.APPLYDEPTNAME }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.APPLYDATE }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.USEFDATE }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.RETURNDATE }</td>
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.USETDATE }</td>      
		      <td nowrap="nowrap" align="center" class="info_content_01" >${all.SELECTDEPTID }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.USEVISATYPE }</td>		      
		      <td nowrap="nowrap"  align="center" class="info_content_01">${all.USEINFORMATION }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${all.ISLEND }</td>
		      <td nowrap="nowrap" align="center" class="info_content_01">${all.NOTE }</td>	     
				
			  <td nowrap="nowrap" align="center" class="info_content_00">
		       <table align="left">
		    
		      <c:forEach items="${all.allSealApproval}" var="view">
		      <tr>
		      <td nowrap="nowrap"><font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME }</font></td>
		      <td nowrap="nowrap">
		      <c:if test="${view.AFFIRM_FLAG == '0'}">
		      <font color="#990099">未决裁</font></c:if>
		      <c:if test="${view.AFFIRM_FLAG == '1'}">
		      <font color="#00CC00">通过</font>
		      
		      <font color="#00CC00">${view.UPDATE_DATE}</font>
		      
		      </c:if>
		      <c:if test="${view.AFFIRM_FLAG == '2'}">
		      <font color="#00CC00">已否决</font></c:if>
		      </td>		     
		      </tr>	            
		      </c:forEach>
		      </table>     
		      </td>	
		      
		      <td nowrap="nowrap" align="center" class="info_content_00">
		       <table align="left">
		    
		      <c:forEach items="${all.allSealApproval}" var="view">
		      <tr>
		      <td nowrap="nowrap"><font color="#990099">${view.AFFIRM_LEVEL}${view.CHINESENAME }</font>
		      &nbsp;${view.AFFIRMORIDEA}
		      </td>
		      </tr>	            
		      </c:forEach>
		      </table>     
		      </td>
		      <td nowrap="nowrap" align="center" class="info_content_01" >${all.ISCOMPLISE }     </td>	
		         <td nowrap="nowrap" align="center" class="info_content_01" >
		         <select	name="MANYZH_${i.index}"  type="_moz" >
							<option value="" <c:if test="${all.MANYZH==NULL}">selected</c:if>>请选择</option>
							<option value="5" <c:if test="${all.MANYZH==5}">selected</c:if>>5</option>
							<option value="4" <c:if test="${all.MANYZH==4}">selected</c:if>>4</option>
							<option value="3" <c:if test="${all.MANYZH==3}">selected</c:if>>3</option>
							<option value="2" <c:if test="${all.MANYZH==2}">selected</c:if>>2</option>
							<option value="1" <c:if test="${all.MANYZH==1}">selected</c:if>>1</option>
						</select></td>	
		         <td nowrap="nowrap" align="center" class="info_content_01" > 
		         <select	name="MANYFW_${i.index}"  type="_moz" >
							<option value="" <c:if test="${all.MANYFW==NULL}">selected</c:if>>请选择</option>
							<option value="5" <c:if test="${all.MANYFW==5}">selected</c:if>>5</option>
							<option value="4" <c:if test="${all.MANYFW==4}">selected</c:if>>4</option>
							<option value="3" <c:if test="${all.MANYFW==3}">selected</c:if>>3</option>
							<option value="2" <c:if test="${all.MANYFW==2}">selected</c:if>>2</option>
							<option value="1" <c:if test="${all.MANYFW==1}">selected</c:if>>1</option>
						</select></td>
		       <td nowrap="nowrap" align="center" class="info_content_01" >
					<select	name="MANYNQ_${i.index}"  type="_moz" >
							<option value="" <c:if test="${all.MANYNQ==NULL}">selected</c:if>>请选择</option>
							<option value="5" <c:if test="${all.MANYNQ==5}">selected</c:if>>5</option>
							<option value="4" <c:if test="${all.MANYNQ==4}">selected</c:if>>4</option>
							<option value="3" <c:if test="${all.MANYNQ==3}">selected</c:if>>3</option>
							<option value="2" <c:if test="${all.MANYNQ==2}">selected</c:if>>2</option>
							<option value="1" <c:if test="${all.MANYNQ==1}">selected</c:if>>1</option>
						</select></td>
		     <td nowrap="nowrap" align="center" class="info_content_01">&nbsp;
		     <c:if test="${all.ISCONFIRM == '0' && all.isfalg == '0' && all.APPLYORID eq adminID}">
		     <a href="/gaControlServlet?menu_code=${param.menu_code}&operation=visaManger&content=deleteApply&applyno=${all.DOCUMENTNO }" onclick="return(confirm('确认删除吗？这将清空所有的相关信息！'))"><font color="red">删除</font></a>
		     	</c:if>
		     	</td>
		     	
			 </tr>
			 </c:forEach>
		 </table>
		 		<input type="hidden" name="currentPage" value="${currentPage}">
					 <!-- Page Navigation Start-->
					<ait:page       
		               link="/gaControlServlet"
		               parameters="&operation=visaManger&content=sealAffirmInfo&menu_code=${param.menu_code}&qryType=${qryType}&key=${key}&startDate=${startDate}&endDate=${endDate}&cpnyId=${cpnyId}&codeName=${codeName}"
		               total="${resultCount}"
		               currentpage="${currentPage}"
		               pagesize= "${pageSize}"
		               beginlabel="paging_prv10"
		               endlabel="paging_next10"
		               prevlabel="paging_prv"
		               nextlabel="paging_next"
		               pagegroupsize="${pageGroupsize}"
		               useJS="false"/>      
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
</form>

</body>

</html>
