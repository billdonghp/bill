<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@ include file="./inc/taglibs.jsp"%>
<link href="./css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/prototype.js"></script>
<html>
<title>DICC</title>
<style type="text/css">
<!--
html,body {
background-image:url(../images/page_bg.jpg);
}
.logo {
	background-image:url(../images/top_bg.jpg);
}
-->
</style>
<head>

<script type="text/JavaScript">
<!--

function index() {
    var a = '${admin.username}'
	var b ="";
	if(document.getElementById("empDiffSize").value==1){
	   b=document.getElementById("b").value;
	}else{
	   b=document.getElementById("cpnyId").value;
	}	
	window.parent.document.all("a").value=a;	
	window.parent.document.all("b").value=b;
	parent.document.homelogin.submit();
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
		parent.window.close();
	}	
}

function jumpURL(url) {

	window.parent.mainFrame.location=url;
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
function changeDivision() {
    var a = '${admin.username}'
	var b = document.getElementById("cpnyId").value;
	window.parent.document.all("a").value=a;	
	window.parent.document.all("b").value=b;
	parent.document.homelogin.submit();
}
function help(){
   window.open("/main_help_info.jsp","","resizable,scrollbars,dependent,width=700,height=400,left=250,top=300");
}
function getEmpDivision()
{		
       var url = "/ajaxControlServlet" ;
		new Ajax.Request(url, {
			parameters : new Hash({
				'operation' : 'getEmpDivision'
			}),
			onSuccess : function(transport) {
				
			   var hash = $H(transport.responseJSON);
			   var empDiffs = hash.get("empDiff");
			   var param = hash.get("param");
			   document.getElementById("empDiffSize").value=param.diffCnt; 
			   if(param.diffCnt == 1) {
			   		var obj = $H(empDiffs[0]);
			   		document.getElementById("empDiff").innerHTML=obj.get("NAME");
			   		document.getElementById("b").value=obj.get("ID");			   		
			   } else {
			   		var content = "<select id='cpnyId' name='cpnyId' style='login_select' onChange='changeDivision();'>"
			   		empDiffs.each(
				   		function(empDiff){
				   			var obj = $H(empDiff);
				   			if (obj.get("ID") == param.cpnyId)
				   				content += "<option value='"+ obj.get("ID") +"' selected>";
				   		 	else
				   		 		content += "<option value='"+ obj.get("ID") +"'>";
				   		 	content += obj.get("NAME") + "</option>";
				   		}
			   		);
			   		content += "</select>"
			   		document.getElementById("empDiff").innerHTML=content;
			   }
			}
		});
	  
}

//-->
</script>
</head>
<body onload="getEmpDivision();">
<form name="form1" action="/controlServlet" >
<input name="b"  id="b" type="hidden" value=""/>
<input name="empDiffSize" id="empDiffSize" type="hidden" value=""/>
	<div class="main_top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td align="left" valign="top" class="logo"><a href="#" onclick="index();" title="home page"><img src="images/logo.jpg" border="0"/></a></td>
	    <td align="left" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td class="main_top_up">
					<table border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td><img src="images/main/top_img1.gif" /></td>
						<td>欢迎：<ait:content enContent="${admin.englishdept}" zhContent="${admin.department}" koContent="${admin.kordept}"/>
								 <ait:content enContent="${admin.pinyin}" zhContent="${admin.chineseName}" koContent="${admin.koreanname}"/>
						</td>
						<td>&nbsp;</td>
						<td><img src="images/main/top_img2.gif" /></td>
						<td>
							<div id="empDiff"></div>
							<c:if test="${diffCnt==1}">
								${empDiff[0].NAME}								
							</c:if>
							<c:if test="${diffCnt>1}">
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
</form>
</body>
</html>
