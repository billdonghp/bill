<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.ait.ar.bean.ArItem"%>
<%@ page import="com.ait.ar.dao.item.ItemAdd"%>
<%@ page import="com.ait.util.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<jsp:useBean id="admin" class="com.ait.sy.bean.AdminBean" scope="session"></jsp:useBean>
<%

  String operation =StringUtil.checkNull(request.getParameter("operation"));
  String menu_code = StringUtil.checkNull(request.getParameter("menu_code"));
  String itemNo = StringUtil.checkNull(request.getParameter("itemNo"));
  String ar_group_no = StringUtil.checkNull(request.getParameter("ar_group_No"));
  String paramNo = StringUtil.checkNull(request.getParameter("paramNo"));
  String statTypeCode = StringUtil.checkNull(request.getParameter("statTypeCode"));
  String wagesTypeCode = StringUtil.checkNull(request.getParameter("wagesTypeCode"));
  String shiftNo = StringUtil.checkNull(request.getParameter("shiftNo"));
  
  String unit = StringUtil.checkNull(request.getParameter("unit"));
  String unit_value = StringUtil.checkNull(request.getParameter("unit_value"));
  String min_value = StringUtil.checkNull(request.getParameter("min_value"));
  String max_value = StringUtil.checkNull(request.getParameter("max_value"));
  String depend_item = StringUtil.checkNull(request.getParameter("depend_item")) ;
  String replace_item = StringUtil.checkNull(request.getParameter("replace_item"));
  String card_flag =StringUtil.checkNull(request.getParameter("card_flag"));
  String card_from_flag =StringUtil.checkNull(request.getParameter("card_from_flag"));
  String card_from_offset=StringUtil.checkNull(request.getParameter("card_from_offset"));
  String card_from_relation=StringUtil.toCN(StringUtil.checkNull(request.getParameter("card_from_relation")));
  String card_to_flag=StringUtil.checkNull(request.getParameter("card_to_flag"));
  String card_to_offset=StringUtil.checkNull(request.getParameter("card_to_offset"));
  String card_to_relation=StringUtil.toCN(StringUtil.checkNull(request.getParameter("card_to_relation")));
  String apply_flag =StringUtil.checkNull(request.getParameter("apply_flag"));
  String apply_type =StringUtil.checkNull(request.getParameter("apply_type"));
  String apply_fullday_value =StringUtil.checkNull(request.getParameter("apply_fullday_value"));
  String apply_card_proior =StringUtil.checkNull(request.getParameter("apply_card_proior"));
  String activity =StringUtil.checkNull(request.getParameter("activity"));
  String date_type = StringUtil.checkNull(request.getParameter("date_type"));
  String cpnyId=admin.getCpnyId();
  
  ItemAdd itemAdd = new ItemAdd();
  ArItem arItem = new ArItem();
  arItem.setArParamNo(NumberUtil.parseInt(paramNo)) ;
  arItem.setItemNo(NumberUtil.parseInt(itemNo));
  arItem.setAr_group_no(ar_group_no);
  arItem.setUnit(unit);
  arItem.setUnit_value(unit_value);
  arItem.setMin_value(min_value);
  arItem.setMax_value(max_value);
  arItem.setDepend_item(depend_item);
  arItem.setReplace_item(replace_item);
  arItem.setCard_flag(card_flag);
  arItem.setCard_from_flag(card_from_flag);
  arItem.setCard_from_offset(card_from_offset); 
  arItem.setCard_from_relation(card_from_relation);
  arItem.setCard_to_flag(card_to_flag);
  arItem.setCard_to_offset(card_to_offset);
  arItem.setCard_to_relation(card_to_relation);
  arItem.setApply_flag(apply_flag);
  arItem.setApply_type(apply_type);
  arItem.setApply_fullday_value(apply_fullday_value);
  arItem.setApply_card_proior(apply_card_proior);
  arItem.setStat_type_code(statTypeCode) ;
  arItem.setWages_type_code(wagesTypeCode) ;
  arItem.setCpnyId(cpnyId);
  arItem.setShiftNo(shiftNo) ;
  if(!activity.equals("")){
 	 arItem.setActivity(Integer.parseInt(activity));
  }
  arItem.setDate_type(date_type);
  
  
  
 
if(operation.equals("ADD")){
	try{
		itemAdd.addItem(arItem);
	} catch (Exception e) {
		Logger.getLogger(getClass()).debug(e.toString());
	}
	response.sendRedirect("/ar/arbcdalist.jsp?menu_code="+menu_code+"&itemNo_="+itemNo);
}else if(operation.toUpperCase().equals("MODIFY")){
	try{
		itemAdd.modifyItem(arItem);
	} catch (Exception e) {
		Logger.getLogger(getClass()).debug(e.toString());
	}
	response.sendRedirect("/ar/arbcdalist.jsp?menu_code="+menu_code+"&itemNo_="+itemNo);
}else if(operation.toUpperCase().equals("DELETE")){
	itemAdd.deleteArItem(itemNo,ar_group_no,paramNo);
	response.sendRedirect("/ar/arbcdalist.jsp?menu_code="+menu_code+"&itemNo_="+itemNo);
}

%>
<html>
<head>
<link href="../css/default.css" rel="stylesheet" type="text/css">

</head>

<body>


</body>
</html>
