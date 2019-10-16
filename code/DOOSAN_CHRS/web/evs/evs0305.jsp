<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""%>
<%@ page import="com.ait.evs.EvsEmp"%>
<%@ page import="com.ait.evs.EvsMaster"%>
<%@ page import="com.ait.evs.EvsPeriod"%>
<%@ page import="com.ait.evs.EvsProcess"%>
<%@ page import="com.ait.evs.EvsType"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ait.util.SysCodeParser"%>
<%@ page import="com.ait.util.StringUtil"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session" />
	<%@ include file="/inc/taglibs.jsp"%>
	<%@ include file="../inc/meta.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价 > 统计查看 > 评价报表</title> 
<script src="js/echarts.min.js"></script>
		
</head>

<!-- EXT 所需部分 -->
<link rel="stylesheet" type="text/css" href="../css/ext-all.css" />
<!-- LIBS -->
<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../js/ext/ext-all.js"></script>
<body>
<a name="top"></a>

<%	
String adminId="";
if(admin!=null){
	adminId=admin.getAdminID();
}
String evDeptId="";
String evPeriodId="";
String evTypeId="";
String evProcessId="";

evDeptId=request.getParameter("evDeptId")!=null?request.getParameter("evDeptId"):evDeptId;
evTypeId=request.getParameter("evTypeId")!=null?request.getParameter("evTypeId"):evTypeId;
evPeriodId=request.getParameter("evPeriodId")!=null?request.getParameter("evPeriodId"):evPeriodId;
evProcessId=request.getParameter("evProcessId")!=null?request.getParameter("evProcessId"):evProcessId;


EvsPeriod evsP=new EvsPeriod();
if(evPeriodId.equals("")){
	try{
		evPeriodId=evsP.getCurrentEvPeriod();
	}catch(Exception e){}	
}
//List lEvsDept=null;
List lEvsPeriod=null;
List lEvsType=null;
List lEvsEmp=null;
EvsEmp evsEmp=new EvsEmp();
EvsMaster evsMaster=new EvsMaster();
try{
	//lEvsDept=EvsEmp.getEvEmpDeptList(evPeriodId);
	lEvsPeriod=EvsEmp.getEvEmpPeriodList();
	lEvsType=EvsEmp.getEvEmpTypeList();
	
	lEvsEmp=evsEmp.getEvEmpsByDeptPeriodType(evDeptId,evPeriodId,evTypeId,evProcessId);
	//lEvsEmp=evsMaster.getEvEmpsByMasterPeriod(evPeriodId,adminId,evDeptId,evTypeId);

	
}catch(Exception e){}

//评价流程列表
Vector vEvProcess=new Vector();
try{
vEvProcess=SysCodeParser.getCode("EVSPROCESS");
}catch(Exception e){}
int vEvProcessSize=vEvProcess.size();
%>
<form action="/evs/evs0305.jsp?menu_code=evs0305" method="POST" name="evs0305">
		<input type="hidden" name="ck_all" value="0" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="15"></td>
		<td width="11" height="33" valign="TOP" align="RIGHT"><img
			src="../images/tablbk01_r1_c1.gif"></td>
		<td background="../images/tablbk01_r1_c2.gif">
			<!-- display toolbar -->
		<%@ include file="../evs/inc/evs0305toolbar_v.jsp"%>
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
		<%@ include file="inc/evs_nav.jsp"%>
		
		<!-- display content -->	
		<table width="100%" border="1" align="center" cellpadding="0"	cellspacing="0" bordercolorlight="#E7E7E7" bordercolordark="#FFFFFF"
			style="padding: 2px 2px 2px 2px;">
			
			<%	
				if(lEvsEmp!=null){
					int lEvsEmpSize=lEvsEmp.size();
	    			for(int i=0;i<lEvsEmpSize;i++){
	        			EvsEmp evsEmp_tr=(EvsEmp)lEvsEmp.get(i);
	        			}
	    			}
	        %>
		</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"			>
						
			
			<tr>
			<td  height ="400" colspan=5 align="center" id="main">&nbsp;</td>
			</tr>
	
			<tr>
				<td width ="20%" height ="300" align="left" id="main1"></td>
				<td width ="20%" height ="300" align="left" id="main2"></td>
				<td width ="20%" height ="300"  align="left" id="main3"></td>
				<td width ="20%" height ="300" align="left" id="main4"></td>
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
		</form>
</body>
</html>
<script language="JavaScript" type="text/javascript" src="">
	//查看个人分数明细
	function showMarkDetail(evEmpId , evPeriodId)
	{
		var url = '/ajaxControlServlet';
		var pars = 'operation=getEvItemMarkCmd&evEmpId='+ evEmpId + "&evPeriodId=" + evPeriodId;
		
		new Ajax.Request(url, {
					method: 'post',
					parameters : pars,
					onSuccess : function(XmlHttpRequest) {
						//alert(XmlHttpRequest.responseText);
							var html = "<div>";
								html += XmlHttpRequest.responseText;
								html +="</div>";
							var	editDlg = new Ext.Window({
										modal: true
										 , width: 300
										  , height: 'auto'
										 , shadow: true
										 , closable: true
										  , layout : 'fit'
										 , html : html
										 , title : '评价分数信息'
									});
							editDlg.show();
					}
				} );
	}


	function showEvItemProcessMark(evEmpID,evPeriodId,evTypeId) {
		var theUrl = "../evs/evsItemProcessMark.jsp?"+"evEmpID="+evEmpID+"&evPeriodId="+evPeriodId+"&evTypeId="+evTypeId;
	    var name = "newWin";
	    var features = "status=no,menubar=no,resizable=yes,scrollbars=yes,width=500,height=350";
    	window.open(theUrl,name,features);
	}

	
	
	
</script>

<script>

var v_maindata =[];
var v_maindata1 =[];
var v_maindata2 =[];
var v_maindata3 =[];
var v_maindata4 =[];

function loadData0(option) {
	var url = "/ajaxControlServlet" ;
	
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupcount',
			'craft' : ""
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   
		   v_maindata[0] = hash.get("count0");
		   v_maindata[1] = hash.get("count1");
		   v_maindata[2] = hash.get("count2");
		   v_maindata[3] = hash.get("count3");
		   v_maindata[4] = hash.get("count4");


		   //alert(v_maindata[4]);
		   //option.legend.data = [];
           option.series[0].data = [];
           
               
           //option.legend.data.push(result[i].name);
           option.series[0].data.push(
                    {"name":"技师","value":v_maindata[4]},
		            {"name":"高级工","value":v_maindata[3]},
		            {"name":"中级工","value":v_maindata[2]},
		            {"name":"初级工","value":v_maindata[1]},
		            {"name":"实习工","value":v_maindata[0]});		   
		   myChart.setOption(option);
		}
	});
}
function loadData1(option1) {
	var url = "/ajaxControlServlet" ;
	
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupcount1',
			'craft' : "焊接" 
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   
		   v_maindata1[0] = hash.get("count0");
		   v_maindata1[1] = hash.get("count1");
		   v_maindata1[2] = hash.get("count2");
		   v_maindata1[3] = hash.get("count3");
		   v_maindata1[4] = hash.get("count4");


		   //alert(v_maindata[4]);
		   //option.legend.data = [];
           option1.series[0].data = [];
           
               
           //option.legend.data.push(result[i].name);
           option1.series[0].data.push(
        		   {"name":"技师","value":v_maindata1[4]},
		            {"name":"高级工","value":v_maindata1[3]},
		            {"name":"中级工","value":v_maindata1[2]},
		            {"name":"初级工","value":v_maindata1[1]},
		            {"name":"实习工","value":v_maindata1[0]});		   
		   myChart1.setOption(option1);
		}
	});
}
function loadData2(option2) {
	var url = "/ajaxControlServlet" ;
	
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupcount1',
			'craft' : "机加工"  
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   
		   v_maindata2[0] = hash.get("count0");
		   v_maindata2[1] = hash.get("count1");
		   v_maindata2[2] = hash.get("count2");
		   v_maindata2[3] = hash.get("count3");
		   v_maindata2[4] = hash.get("count4");


		   //alert(v_maindata[4]);
		   //option.legend.data = [];
           option2.series[0].data = [];
           
               
           //option.legend.data.push(result[i].name);
           option2.series[0].data.push(
        		   {"name":"技师","value":v_maindata2[4]},
		            {"name":"高级工","value":v_maindata2[3]},
		            {"name":"中级工","value":v_maindata2[2]},
		            {"name":"初级工","value":v_maindata2[1]},
		            {"name":"实习工","value":v_maindata2[0]});		   
		   myChart2.setOption(option2);
		}
	});
}
function loadData3(option3) {
	var url = "/ajaxControlServlet" ;
	
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupcount1',
			'craft' : "涂装" 
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   
		   v_maindata3[0] = hash.get("count0");
		   v_maindata3[1] = hash.get("count1");
		   v_maindata3[2] = hash.get("count2");
		   v_maindata3[3] = hash.get("count3");
		   v_maindata3[4] = hash.get("count4");


		   //alert(v_maindata[4]);
		   //option.legend.data = [];
           option3.series[0].data = [];
           
               
           //option.legend.data.push(result[i].name);
           option3.series[0].data.push(
        		   {"name":"技师","value":v_maindata3[4]},
		            {"name":"高级工","value":v_maindata3[3]},
		            {"name":"中级工","value":v_maindata3[2]},
		            {"name":"初级工","value":v_maindata3[1]},
		            {"name":"实习工","value":v_maindata3[0]});		   
		   myChart3.setOption(option3);
		}
	});
}
function loadData4(option4) {
	var url = "/ajaxControlServlet" ;
	
	new Ajax.Request(url, {
		parameters : new Hash({
			'operation' : 'getevsSetupcount1',
			'craft' : "组装" 
		}),
		onSuccess : function(transport) {
			
		   var hash = $H(transport.responseJSON);
		   
		   v_maindata4[0] = hash.get("count0");
		   v_maindata4[1] = hash.get("count1");
		   v_maindata4[2] = hash.get("count2");
		   v_maindata4[3] = hash.get("count3");
		   v_maindata4[4] = hash.get("count4");


		   //alert(v_maindata[4]);
		   //option.legend.data = [];
           option4.series[0].data = [];
           
               
           //option.legend.data.push(result[i].name);
           option4.series[0].data.push(
        		   {"name":"技师","value":v_maindata4[4]},
		            {"name":"高级工","value":v_maindata4[3]},
		            {"name":"中级工","value":v_maindata4[2]},
		            {"name":"初级工","value":v_maindata4[1]},
		            {"name":"实习工","value":v_maindata4[0]});	   
		   myChart4.setOption(option4);
		}
	});
}
var myChart = echarts.init(document.getElementById('main'));
		    
			option = {
			    title : {
			        text: '生产部人员技能现状',			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        //formatter: "{a} <br/>{b} : {c} ({d}%)" 
			        formatter: "{a} <br/>{b} : {c} " 
			    },
			    legend: {
			    	type: 'plain',
			        orient: 'vertical',
			        left: 'left',
			        data:  ['技师','高级工','中级工','初级工','实习工']
			    },
			    series : [
			        {
			            name: '比例',
			            type: 'pie',
			            radius : '45%',
			            center: ['40%', '50%'],
			            
			            data: [{"name":"技师","value":v_maindata[0]},
					            {"name":"高级工","value":v_maindata[1]},
					            {"name":"中级工","value":v_maindata[2]},
					            {"name":"初级工","value":v_maindata[3]},
					            {"name":"实习工","value":v_maindata[4]}],
					            itemStyle:{ 
					                normal:{ 
					                      label:{ 
					                        show: true, 
					                        //formatter: '{b} : {c} ({d}%)' 
					                        formatter: '{b} : {c} ' 
					                      }, 
					                      labelLine :{show:true} 
					                    } 
					                }
		
			        }
			    ]
			};
			 
			 loadData0(option);
			myChart.on('click', function (params) {
				//alert(params.name);
				//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
				 //document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
			 	// document.evs0302.submit();

			 	 var theUrl="/evsControlServlet?operation=retrieveEvaluate0305&zyzgdj="+encodeURIComponent(params.name);
		          var name="searchEmp";
		          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
		          window.open(theUrl,name,features);
			});
			
			var myChart1 = echarts.init(document.getElementById('main1'));
			option1 = {
			    title : {
			        text: '焊接',
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			      //formatter: "{a} <br/>{b} : {c} ({d}%)" 
			        formatter: "{a} <br/>{b} : {c} "
			    },
			    series : [
			        {
			            name: '比例',
			            type: 'pie',
			            radius : '45%',
			            center: ['40%', '50%'],
			            data:  [{"name":"技师","value":parseInt("3")},
					            {"name":"高级工","value":parseInt("3")},
					            {"name":"中级工","value":parseInt("3")},
					            {"name":"初级工","value":parseInt("3")},
					            {"name":"实习工","value":parseInt("3")}],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        //formatter: '{b} : {c} ({d}%)' 
			                        formatter: '{b} : {c} '
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                }
			        }
			    ]
			};
			//myChart1.setOption(option1);
			loadData1(option1);
			myChart1.on('click', function (params) {
				//alert(params.name);
				//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
				 //document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
			 	// document.evs0302.submit();

			 	 var theUrl="/evsControlServlet?operation=retrieveEvaluate0305&zyzgdj="+encodeURIComponent(params.name)+"&craft="+encodeURIComponent("焊接"); 
		          var name="searchEmp";
		          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
		          window.open(theUrl,name,features);
			});
			
			var myChart2 = echarts.init(document.getElementById('main2'));
			option2 = {
			    title : {
			        text: '机加工',
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			      //formatter: "{a} <br/>{b} : {c} ({d}%)" 
			        formatter: "{a} <br/>{b} : {c} "
			    },
			    series : [
			        {
			            name: '比例',
			            type: 'pie',
			            radius : '45%',
			            center: ['40%', '50%'],
			            data: [{"name":"技师","value":3},
					            {"name":"高级工","value":23},
					            {"name":"中级工","value":47},
					            {"name":"初级工","value":24},
					            {"name":"实习工","value":3}],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        //formatter: '{b} : {c} ({d}%)' 
			                        formatter: '{b} : {c} '
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                }
			        }
			    ]
			};
			//myChart2.setOption(option2);
			loadData2(option2);
			myChart2.on('click', function (params) {
				//alert(params.name);
				//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
				 //document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
			 	// document.evs0302.submit();

			 	 var theUrl="/evsControlServlet?operation=retrieveEvaluate0305&zyzgdj="+encodeURIComponent(params.name)+"&craft="+encodeURIComponent("机加工"); 
		          var name="searchEmp";
		          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
		          window.open(theUrl,name,features);
			});
			
			var myChart3 = echarts.init(document.getElementById('main3'));
			option3 = {
			    title : {
			        text: '涂装',
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			      //formatter: "{a} <br/>{b} : {c} ({d}%)" 
			        formatter: "{a} <br/>{b} : {c} "
			    },
			    series : [
			        {
			            name: '比例',
			            type: 'pie',
			            radius : '45%',
			            center: ['40%', '50%'],
			            data: [{"name":"技师","value":3},
					            {"name":"高级工","value":23},
					            {"name":"中级工","value":47},
					            {"name":"初级工","value":24},
					            {"name":"实习工","value":3}],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        //formatter: '{b} : {c} ({d}%)' 
			                        formatter: '{b} : {c} ' 
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                }
			        }
			    ]
			};
			//myChart3.setOption(option3);
			loadData3(option3);
			myChart3.on('click', function (params) {
				//alert(params.name);
				//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
				 //document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
			 	// document.evs0302.submit();

			 	 var theUrl="/evsControlServlet?operation=retrieveEvaluate0305&zyzgdj="+encodeURIComponent(params.name)+"&craft="+encodeURIComponent("涂装"); 
		          var name="searchEmp";
		          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
		          window.open(theUrl,name,features);
			});
			
			var myChart4 = echarts.init(document.getElementById('main4'));
			option4 = {
			    title : {
			        text: '组装',
			        
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			      //formatter: "{a} <br/>{b} : {c} ({d}%)" 
			        formatter: "{a} <br/>{b} : {c} "
			    },
			    series : [
			        {
			            name: '比例',
			            type: 'pie',
			            radius : '45%',
			            center: ['40%', '50%'],
			            data: [{"name":"技师","value":3},
					            {"name":"高级工","value":23},
					            {"name":"中级工","value":47},
					            {"name":"初级工","value":24},
					            {"name":"实习工","value":3}],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        //formatter: '{b} : {c} ({d}%)' 
			                        formatter: '{b} : {c} '
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                }
			        }
			    ]
			};
			//myChart4.setOption(option4);
			loadData4(option4);
			myChart4.on('click', function (params) {
				//alert(params.name);
				//window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
				 //document.evs0302.action="/evsControlServlet?operation=retrieveEvaluate0302&EmpID="+ empid;
			 	// document.evs0302.submit();

			 	 var theUrl="/evsControlServlet?operation=retrieveEvaluate0305&zyzgdj="+encodeURIComponent(params.name)+"&craft="+encodeURIComponent("组装"); 
		          var name="searchEmp";
		          var features = "toolbar=no,location=no,directory=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,left=540,top=0,resizable=yes,scrollbars=yes,width=600,height=500";
		          window.open(theUrl,name,features);
			});
</script>