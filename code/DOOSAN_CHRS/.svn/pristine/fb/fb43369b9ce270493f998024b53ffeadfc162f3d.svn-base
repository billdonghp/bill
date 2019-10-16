<%@ page import="com.ait.api.dao.ApiDAO" %>
<%@ page import="com.ait.sqlmap.util.SimpleMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="oracle.sql.CLOB" %>
<%@ page import="com.ait.api.resultApi.KdGoldAPI" %>
<%@ page import="com.google.gson.internal.LinkedTreeMap" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="Generator" content="EditPlus®">
	<meta name="Author" content="">
	<meta name="Keywords" content="">
	<meta name="Description" content="">
	<title>Document</title>
</head>
<STYLE>
	#htmlExp{
		float: left;
	}
	#traces{
		float: left;
		padding-left: 50px;
	}
	.title {
		width: 100%;
		height: 40px;
		line-height: 40px;
		padding-bottom: 15px;
	}
	.title div{
		float: left;
		background: #ccc;
		padding: 0px 8px;
	}
	.title-left {
		float: left;
		width: 360px;
	}
	.title button{
		float: right;
		height: 30px;
		line-height: 30px;
		margin-top: 5px;
	}
	.title span{
		 float: left;
	 }
	.title-right{
		margin-left: 50px;
		width: 880px;
	}

</STYLE>
<style>
	body{font-size: 12px;}
	ul li{list-style: none;}
	.track-rcol{width: 900px; border: 1px solid #eee;}
	.track-list{margin: 20px; padding-left: 5px; position: relative;}
	.track-list li{position: relative; padding: 9px 0 0 25px; line-height: 18px; border-left: 1px solid #d9d9d9; color: #999;}
	.track-list li.first{color: red; padding-top: 0; border-left-color: #fff;}
	.track-list li .node-icon{position: absolute; left: -6px; top: 50%; width: 11px; height: 11px; background: url(/images/order-icons.png)  -21px -72px no-repeat;}
	.track-list li.first .node-icon{background-position:0 -72px;}
	.track-list li .time{margin-right: 20px; position: relative; top: 4px; display: inline-block; vertical-align: middle;}
	.track-list li .txt{max-width: 600px; position: relative; top: 4px; display: inline-block; vertical-align: middle;}
	.track-list li.first .time{margin-right: 20px; }
	.track-list li.first .txt{max-width: 600px; }
</style>
<%
	String applyNo = request.getParameter("APPLY_NO");
	String POSTNUMBER = request.getParameter("POSTNUMBER");
	String EXPRESSTYPE = request.getParameter("EXPRESSTYPE");
	//获取快递信息
	SimpleMap batisMap = new SimpleMap();
	batisMap.put("APPLY_NO", applyNo);
	ApiDAO apiDAO = new ApiDAO();
	List resultList = apiDAO.getInfoList(batisMap, "getExpInfo");

	String printTemplate = null;
	/*String tracesJson = null;
	String content = "";
	String LogisticCode = "";*/
	if (resultList != null && resultList.size() > 0) {
		SimpleMap map = (SimpleMap)resultList.get(0);
		CLOB c = (CLOB) map.get("MSG");
		if (c != null) {
			String jsonResult = c.getSubString(1, (int)c.length());
			SimpleMap upMap = new Gson().fromJson(jsonResult, SimpleMap.class);
			if (upMap != null && "100".equals(upMap.getString("ResultCode"))) {
				printTemplate = upMap.getString("PrintTemplate");
			}
		}
	}

	//查询快递信息
	String tracesResult = KdGoldAPI.getOrderTracesByJson(EXPRESSTYPE,POSTNUMBER);
	SimpleMap tracesMap = new Gson().fromJson(tracesResult, SimpleMap.class);
	List list = (List) tracesMap.get("Traces");
	StringBuffer tracesStr = new StringBuffer();
	if (list != null && list.size() > 0) {
		for (int i = list.size() - 1; i >= 0; i--) {
			LinkedTreeMap trackMap = (LinkedTreeMap)list.get(i);
			if (i == list.size() - 1) {
				tracesStr.append("<li class=\"first\">" +
						"<i class=\"node-icon\"></i>" +
						"<span class=\"time\">" + trackMap.get("AcceptTime") + "</span>" +
						"<span class=\"txt\">" + trackMap.get("AcceptStation") + "</span>" +
						"</li>");
			}else{
				tracesStr.append("<li>" +
						"<i class=\"node-icon\"></i>" +
						"<span class=\"time\">" + trackMap.get("AcceptTime") + "</span>" +
						"<span class=\"txt\">" + trackMap.get("AcceptStation") + "</span>" +
						"</li>");
			}
		}
	}else{
		tracesStr.append("" +
				"<li class=\"first\">" +
				"<i class=\"node-icon\"></i>" +
				"<span class=\"time\"></span>" +
				"<span class=\"txt\">" + tracesMap.getString("Reason") + "</span>" +
				"</li>");
	}
%>
<body>
	<div class="title" id="title">
		<%
			if(printTemplate != null){
		%>
			<div class="title-left">
			<span>电子面单</span>
			<button type="button" onclick="printpage(this)">打印</button>
			</div>
		<%
			}
		%>
		<div class="title-right">
			<span>物流信息</span>
		</div>
	</div>
	<%
		if(printTemplate != null){
	%>
	<div id="htmlExp">
		<%= printTemplate%>
	</div>
	<%
		}
	%>
	<div id="traces">
		<div class="track-rcol">
			<div class="track-list">
				<ul>
					<%= tracesStr.toString()%>
				</ul>
			</div>
		</div>
	</div>
</body>
<script>
	function printpage(obj){
		var titleObj = document.getElementById('title');
		var tracesObj = document.getElementById('traces');
		obj.style.display = "none";
		titleObj.style.display = "none";
		tracesObj.style.display = "none";
		window.print();
		obj.style.display = "";
		titleObj.style.display = "";
		tracesObj.style.display = "";
		return false;
	}
</script>
</html>
