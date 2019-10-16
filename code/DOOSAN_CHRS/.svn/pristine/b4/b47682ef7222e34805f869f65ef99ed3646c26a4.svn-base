<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="./inc/taglibs.jsp"%>
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<head>
<!-- main.jsp -->
<SCRIPT type=text/javascript>
/*
function openwin(){


	var date=new Date();
	var day=date.getDate();
	var month=date.getMonth();  
	var time=fnW(day);
	document.getElementById("div3").value = time;

	var s2 ="27";
	var s = document.getElementById("div3").value;
	if(s == s2){
	window.open('hint.jsp', 'newwindow', 'height=500, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
}
*/
//补位 当某个字段不是两位数时补0
function fnW(str){
var num;
str>=10?num=str:num="0"+str;
return num;
}

function cssChange(node) {
       var ul = document.getElementById("nav_menuul");
       var children = ul.childNodes;
       for (var i=0;i<children.length;i++) {
           children[i].className = "lioff";
       }
       while (!node.className || node.className != "lioff") {
           node = node.parentNode;
       }
       node.className = "lion";
}
function cssChange1(node) {
       var ul = document.getElementById("main_nav");
       var children = ul.childNodes;
       for (var i=0;i<children.length;i++) {
           children[i].className = "lioff";
       }
       while (!node.className || node.className != "lioff") {
           node = node.parentNode;
       }
       node.className = "lion";
 }
function showtype(id,obj) 
{
	
	var div = document.getElementById(id);
    var children = div.childNodes;
    for (var i=0;i<children.length;i++) {
        children[i].style.display = "none";
    } 
	document.getElementById(obj).style.display = "";
}

function exit(){

 var msg='<ait:message messageID="alert.mutual.are_you_sure_you_want_to_logout" ></ait:message>';
	if(confirm(msg)){
	    
		parent.location="/controlServlet?operation=logout";
		window.close();
		
		
	}	
}

function jumpURL(url) {

	document.form1.url.value = url;
	document.form1.submit();
}

var httpRequest;


		
function changeLang(language) {
	
	if (window.ActiveXObject) { // IE
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		httpRequest = new XMLHttpRequest();
		httpRequest.overrideMimeType('text/xml');
	}
	
	httpRequest.onreadystatechange = function(){
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				document.location.reload(); 
			} else {
				alert('An error occurred!');
			}
		}
	};
	
	httpRequest.open('POST', '/controlServlet?operation=changeLang', false);
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

	if (language == "zh") {
		httpRequest.send('lang=en');
	} else {
		httpRequest.send('lang=zh');
	}
}
function help(){
   window.open("/main_help_info.jsp","","resizable,scrollbars,dependent,width=700,height=400,left=250,top=300");
 }
function changeDivision() {
	document.form1.action="/controlServlet";
	document.form1.b.value = document.getElementById("cpnyId").value;
	document.form1.submit();
}
</SCRIPT>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>DOOSAN 主页</title>
</head>
<body onload=openwin()>
<form name="form1" action="/home.jsp" method="post">
<input type="hidden" id="div3" />
<input type="hidden" name="url" value=""/>
<input type="hidden" name="operation"value="login"/>
<input type="hidden" name="a" value="${admin.ad_User_Id}"/>
<input type="hidden" name="b" value=""/>
<div class="main_top">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" valign="top" class="logo"><a href="#"><img
			src="images/main/logo.jpg" border="0" /></a></td>
		<td align="left" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="main_top_up">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><img src="images/main/top_img1.gif" /></td>
						<td>欢迎：<ait:content enContent="${admin.englishdept}"
							zhContent="${admin.department}" koContent="${admin.kordept}" /> <ait:content
							enContent="${admin.pinyin}" zhContent="${admin.chineseName}"
							koContent="${admin.koreanname}" /></td>
						<td>&nbsp;</td>
						<td><img src="images/main/top_img2.gif" /></td>
						<td><c:if test="${diffCnt==1}">
								${empDiff[0].NAME}
							</c:if> <c:if test="${diffCnt>1}">
								<ait:select name="cpnyId" dataListName="empDiff" style="login_select" selected="${cpnyId}" onChange="changeDivision();"/>
							</c:if>
						</td>
						<td>&nbsp;</td>
						<td><a href="#" onClick="help();">help</a></td>
						<td>&nbsp;</td>
						<td><img src="images/main/top_img4.gif" /></td>
						<td><a href="#" onClick="exit();">退出系统</a></td>
						<td>&nbsp;</td>
					  </tr>
					</table>
	
				</td>
			  </tr>
			  <tr>
				<td align="left" valign="top" class="main_top_down">
	            <div id="nav_menu">
	            	<ul id="nav_menuul">
                       <!-- 按照系统配置文件的顺序，显示一级菜单 -->
						<c:forEach items="${moduleNames}" var="module" varStatus="status">
							<c:forEach items="${topMenuList}" var="result">
								<c:if test="${module == result.menuCode}">
									<li class='lioff'>
										<a href="#" onClick="cssChange(this);showtype('menua','menua0${status.count}')">
											<ait:content enContent="${result.menuEnIntro}" zhContent="${result.menuIntro}" koContent="${result.menuKorIntro}"/>
										</a>
									</li>
								</c:if>
							</c:forEach>
							<c:forEach items="${modules}" var="result">
								<c:if test="${module == result.menuCode}">
									<li>
										<ait:content enContent="${result.menuEnIntro}" zhContent="${result.menuIntro}" koContent="${result.menuKorIntro}"/>
									</li>
								</c:if>
							</c:forEach>
						</c:forEach>
	                </ul>
	            </div>
	            
	        	<div id="menua"> 
	      			<c:forEach items="${moduleNames}" var="module" varStatus="status">
						<div id="menua0${status.count}" style="display:none">
							<c:forEach items="${topMenuList}" var="result1" varStatus="status1">
								<c:if test="${module == result1.menuCode }">
									<c:forEach items="${secondMenuList}" var="result2" varStatus="status2">
										<c:if test="${status1.count == status2.count}">
											<c:forEach items="${result2}" var="result3" varStatus="status3">
												<a href="#" onClick="jumpURL('${result3.menuUrl}');">
													<ait:content enContent="${result3.menuEnIntro}" zhContent="${result3.menuIntro}" koContent="${result3.menuKorIntro}"/>
												</a>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</div>
					  </c:forEach>
				  </div>
				  
				</td>
			  </tr>
			</table>

		</td>
	  </tr>
	</table>
	</div>
	<div class="main">
		<div class="main_left">
		<ul id="main_nav">
			<li class="lion"><a href="#" onClick="cssChange1(this);showtype('main_type','main_type1')">待决裁</a></li>
			<li class="lioff"><a href="#" onClick="cssChange1(this);showtype('main_type','main_type2')">待确认</a></li>
			<li class="lioff"><a href="#" onClick="cssChange1(this);showtype('main_type','main_type3')">个人查看</a></li>
		</ul>
			<div id="main_type">
				<!-- 待决裁信息表 -->
				<ul id="main_type1">
					<c:choose>
						<c:when test="${affirmFlag}">
							<c:if test="${attAffirmFlag}">
								<li><span>[ <c:out value="${affirmCountOT}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=otaffirm&menu_code=ess0801');">条待决裁加班信息</a></li>
								<li><span>[ <c:out value="${affirmCountLeave}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=leaveaffirm&menu_code=ess0802');">条待决裁休假信息</a></li>
								<li><span>[ <c:out value="${armodifyaffirmNo}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=armodifyaffirm&menu_code=ess0809');">条待决裁考勤修改信息</a></li>
							</c:if>
							<c:if test="${gaAffirmFlag}">
							    <c:if test="${visiterFlag}">
									<li><span>[ <c:out value="${affirmCountVisiter}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0210&operation=visiterApplications&content=visiterApproval');">条待决裁参观者信息</a></li>
								</c:if>
								<c:if test="${eateryFlag}">
									<li><span>[ <c:out value="${affirmCountEatery}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0202&operation=gaAffirm&content=cardApplicationAffirm');">条待决裁就餐信息</a></li>
								</c:if>
								<c:if test="${presentFlag}">
									<li><span>[ <c:out value="${affirmCountPresent}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0207&operation=retrievePresentAffirmList&affirmType=AffirmType001');">条待决裁礼品信息</a></li>
								</c:if>
								<c:if test="${expensesFlag}">
									<li><span>[ <c:out value="${affirmCountExpenses}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0204&operation=expressApplyAndAffirm&content=expressAffirm');">条待决裁快件信息</a></li>
								</c:if>
								<c:if test="${sealFlag}">
									<li><span>[ <c:out value="${affirmCountSeal}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0209&operation=sealManger&content=sealAffirm');">条待决裁公章信息</a></li>
								<li><span>[ <c:out value="${affirmCountVisa}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0223&operation=visaManger&content=visaAffirm');">条待决裁签证信息</a></li>
								</c:if>
								<c:if test="${voitureFlag}">
									<li><span>[ <c:out value="${voitureInt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0201&operation=gaAffirm&content=viewVoitureAffirm');">条待决裁派车信息</a></li>
								</c:if>
								<c:if test="${productFlag}">
									<li><span>[ <c:out value="${affirmCountProducts}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0203&operation=wpAffirm&content=sealproductionAffirm');">条待决裁制品信息</a></li>
								</c:if>
								<c:if test="${securityFlag}">
									<li><span>[ <c:out value="${affirmCountFacility}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0216&operation=faEnvironment&content=facilitiesMaintenanceAAffirm');">条待决裁设施维修信息</a></li>
								</c:if>
								<c:if test="${securityFlag}">
									<li><span>[ <c:out value="${affirmCountForkli}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0216&operation=faEnvironment&content=facilitiesMaintenanceAAffirm&applyType=1');">条待决裁叉车维修信息</a></li>
								</c:if>
								<c:if test="${checkFlag}">
									<li><span>[ <c:out value="${affirmCountCheck}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0215&operation=gaAffirm&content=checkApplicationAffirm');">条待决裁支票信息</a></li>
								</c:if>
								
							</c:if>
							<c:if test="${affirmjnpjFlag}">
								<c:if test="${jnpjFlag}">
									<li><span>[ <c:out value="${affirmCountGxxg}" /> ]</span><a href="#" onclick="jumpURL('/evs/evs0128_modify_affirm.jsp?menu_code=evs0401');">工序修改决裁信息</a></li>
								    <li><span>[ <c:out value="${affirmCountGxxg}" /> ]</span><a href="#" onclick="jumpURL('/evs/evs0135_modify_affirm.jsp?menu_code=evs0402');">工序技能等级修改决裁信息</a></li>
									<li><span>[ <c:out value="${affirmCountGxxg}" /> ]</span><a href="#" onclick="jumpURL('/evs/evs0134_modify_affirm.jsp?menu_code=evs0403');">职业资格等级修改决裁信息</a></li>
									<li><span>[ <c:out value="${affirmCountGxxg}" /> ]</span><a href="#" onclick="jumpURL('/evs/evs0307_modify_affirm.jsp?menu_code=evs0404');">评价成绩决裁信息</a></li>
									<li><span>[ <c:out value="${affirmCountGxxg}" /> ]</span><a href="#" onclick="jumpURL('/evs/evs0137_modify_affirm.jsp?menu_code=evs0405');">工匠技师决裁信息</a></li>				
								</c:if>
							</c:if>
						</c:when>
						<c:otherwise>
							<li><span>&nbsp;</span>对不起,您没有权限查看此提示信息</li>
						</c:otherwise>
					</c:choose>
				</ul>
				
				<!-- 待确认信息表 -->
				<ul id="main_type2" style="display:none">
					<c:choose>
						<c:when test="${confirmFlag}">
							<c:if test="${attConfirmFlag}">
								<li><span>[ <c:out value="${confirmOtCount}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=otconfirm&menu_code=ess0906');">条待确认加班信息</a></li>
								<li><span>[ <c:out value="${confirmLeaveCount}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=leaveconfirm&menu_code=ess0907');">条待确认休假信息</a></li>
							</c:if>
							<c:if test="${visiterConfirmFlag}">
								<li><span>[ <c:out value="${confirmVisiterCnt}" /> ]</span><a href="#" onclick="jumpURL('/puControlServlet?menu_code=ga0402&operation=visiterManagement&content=visiterManagementView&flag=1');">条待确认参观者信息</a></li>
								<li><span>[ <c:out value="${confirmVisaCnt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0414&operation=visaManger&content=visaManagementView');">条待确认签证信息</a></li>
							</c:if>
							<c:if test="${meetingConfirmFlag}">
								<li><span>[ <c:out value="${confirmMeetingCnt}" /> ]</span><a href="#" onclick="jumpURL('/puControlServlet?menu_code=ga0401&operation=conferenceRoom&content=conferenceRoomConfirm');">条待确认会议室信息</a></li>
							</c:if>
							<c:if test="${eateryConfirmFlag}">
								<li><span>[ <c:out value="${countEateryConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0407&operation=view&content=cardapplicationview');">条待确认就餐信息</a></li>
							</c:if>
							<c:if test="${presentConfirmFlag}">
								<li><span>[ <c:out value="${presentConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('gaControlServlet?menu_code=ga0410&operation=retrievePresentConfirmList&flag=ConfirmType001');">条待确认礼品信息</a></li>
							</c:if>
							<c:if test="${ExpensesConfirmFlag}">
								<li><span>[ <c:out value="${confirmExpensesCnt}" /> ]</span><a href="#" onclick="jumpURL('/gmControlServlet?menu_code=ga0404&operation=expressManger&content=expressConfirm');">条待确认快件信息</a></li>
							</c:if>
							<c:if test="${washConfirmFlag}">
								<li><span>[ <c:out value="${washConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0411&operation=retrieveWashConfirmList&flag=ConfirmType001');">条待确认洗衣信息</a></li>
							</c:if>
							<c:if test="${SealConfirmFlag}">
								<li><span>[ <c:out value="${confirmSealCnt}" /> ]</span><a href="#" onclick="jumpURL('/gmControlServlet?menu_code=ga0406&operation=sealManagement&content=sealConfirm&empID=');">条待确认公章信息</a></li>
							</c:if>
							<c:if test="${voitureConfirmFlag}">
								<li><span>[ <c:out value="${confirmvoitureCnt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0409&operation=bookingVoiture&content=bookVoiture');">条待确认派车信息</a></li>
							</c:if>
							<c:if test="${productsConfirmFlag}">
								<li><span>[ <c:out value="${productsConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('/gmControlServlet?menu_code=ga0403&operation=wpCommand&content=wpApplyConfirm');">条待确认制品信息</a></li>
							</c:if>
							<c:if test="${festivalPresentConfirmFlag}">
								<li><span>[ <c:out value="${festivalPresentConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0412&operation=retrieveFestivalPresentConfirm&flag=ConfirmType001');">条待确认节日礼品信息</a></li>
							</c:if>
							<c:if test="${SecurityConfirmFlag}">
								<li><span>[ <c:out value="${SecurityConfirmCnt}" /> ]</span><a href="#" onclick="jumpURL('/safeControlServlet?operation=securityChecks&content=securityChecksView&menu_code=ga0604');">条待确认安全检查信息</a></li>
							</c:if>
						</c:when>
						
                        <c:otherwise>
							<li><span>&nbsp;</span>对不起,您没有权限查看此提示信息</li>
						</c:otherwise>
					</c:choose>
				</ul>
				<ul id="main_type3"  style="display:none">
					<li><span>[ <c:out value="${personalApplyOtCount}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=otview&menu_code=ess0607');">条待决裁加班申请</a></li>
					<li><span>[ <c:out value="${personalApplyLeavelCount}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=leaveview&menu_code=ess0608');">条待决裁休假申请</a></li>
					<li><span>[ <c:out value="${personalApplyLeavelModifyCount}" /> ]</span><a href="#" onclick="jumpURL('/essControlServlet?operation=view&content=armodifyview&menu_code=ess0614');">条待决裁考勤修改申请</a></li>
					<c:if test="${visiterFlag}">
						<li><span>[ <c:out value="${applyVisiterCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0310&operation=visiterApplications&content=visiterApprovalInformation');">条待决裁参观者申请</a></li>
					</c:if>
					<c:if test="${meetingFlag}">
						<li><span>[ <c:out value="${applyMeetingCount}" /> ]</span><a href="#" onclick="jumpURL('/puControlServlet?menu_code=ga0401&operation=conferenceRoom&content=conferenceRoomConfirm1&fal=1');">条待决裁会议室申请</a></li>
					</c:if>
					<c:if test="${eateryFlag}">
						<li><span>[ <c:out value="${applyEateryCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0302&operation=gaAffirm&content=viewCardAffirmInfo');">条待决裁就餐申请</a></li>
					</c:if>
					<c:if test="${presentFlag}">
						<li><span>[ <c:out value="${applyPresentCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?operation=retrievePresentApplyInfo&menu_code=ga0307&flag=ConfirmType001');">条待决裁礼品申请</a></li>
					</c:if>
					<c:if test="${expensesFlag}">
						<li><span>[ <c:out value="${applyExpensesCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0304&operation=expressApplyAndAffirm&content=expressAffirmInfo');">条待决裁快件申请</a></li>
					</c:if>
					<c:if test="${washFlag}">
						<li><span>[ <c:out value="${applyWashCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0301&operation=retrieveWashApplyInfo&flag=ConfirmType001');">条待决裁洗衣申请</a></li>
					</c:if>
					<c:if test="${sealFlag}">
					
						<li><span>[ <c:out value="${applySealCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0309&operation=sealManger&content=sealAffirmInfo');">条待决裁公章申请</a></li>
					    <li><span>[ <c:out value="${applyVisaCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0319&operation=visaManger&content=sealAffirmInfo');">条待决裁签证申请</a></li>
					</c:if>
					<c:if test="${voitureFlag}">
						<li><span>[ <c:out value="${applyvoitureCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0311&operation=gaAffirm&content=viewAffirmInfo');">条待决裁派车申请</a></li>
					</c:if>
					<c:if test="${productFlag}">
						<li><span>[ <c:out value="${applyProductsCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0303&operation=wpAffirm&content=sealproductionAffirmInfo');">条待决裁制品申请</a></li>
				    </c:if>
				    <c:if test="${securityFlag}">
				    	<li><span>[ <c:out value="${applySecurityCount}" /> ]</span><a href="#" onclick="jumpURL('/gaControlServlet?menu_code=ga0308&operation=securityEnvironment&content=completedRectificationAffirmInfo');">条待决裁安全检查申请</a></li>
					</c:if>
					<c:if test="${festivalFlag}">
				    	<li><span>[ <c:out value="${applyFestivalPresentCount}" /> ]</span><a href="#" onclick="jumpURL('gaControlServlet?menu_code=ga0312&operation=retrieveFestivalPresentConfrimView&flag=ConfirmType001');">条待决裁节日礼品申请</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="copyright"></div>
		<div class="emanager"></div>
	</div>
</form>
</body>
</html>
