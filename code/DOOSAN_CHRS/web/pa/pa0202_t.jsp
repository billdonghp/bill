<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.util.*,java.lang.*,com.ait.utils.*,com.ait.util.*,java.lang.Integer"%>
<%@ page import="com.ait.pa.Paitem,com.ait.sy.bean.AdminBean,com.ait.i18n.MessageSource"%>
<%	
	HttpSession session1 = request.getSession(true);
	AdminBean admin1 = (AdminBean) session1.getAttribute("admin");
	MessageSource messageSource1 = new MessageSource("pay",admin1.getLocale(), "UTF-8");
	String add = messageSource1.getMessage("alert.pay.add");
	String edit = messageSource1.getMessage("alert.pay.edit");
	String delete = messageSource1.getMessage("alert.pay.delete");
	Func func = new Func();
	String flag = request.getParameter("flag");
	String menu_code = request.getParameter("menu_code");
	String strPage = request.getParameter("strPage");
	String bigpage = request.getParameter("bigpage");
	String cpnyId=request.getParameter("cpnyId");
	int retroactiveRaxCalcMark = NumberUtil.parseInt(request.getParameter("retroactiveRaxCalcMark"), 0);
	int sumMainTainFlag = NumberUtil.parseInt(request.getParameter("sumMainTainFlag"), 0);
	try{
		if (flag.equals("insert")){
			Paitem paitem = new Paitem();
			paitem.setItem_id(func.isoStr(request.getParameter("item_id")));
			paitem.setItem_name(func.isoStr(request.getParameter("item_name")));
			paitem.setItem_en_name(func.isoStr(request.getParameter("item_en_name")));
			paitem.setItem_kor_name(func.isoStr(request.getParameter("item_kor_name")));
			paitem.setDescr(func.isoStr(request.getParameter("descr")));
			paitem.setDatatype(func.isoStr(request.getParameter("datatype")));
			paitem.setItem_type(func.isoStr(request.getParameter("item_type")));
			paitem.setPricision(Integer.parseInt(request.getParameter("pricision")));
			paitem.setCarry_bit(request.getParameter("carry_bit"));
			paitem.setRetroactiveRaxCalcMark(retroactiveRaxCalcMark) ;
			paitem.setSumMainTainFlag(sumMainTainFlag) ;
			paitem.setCpnyId(cpnyId);
			paitem.Insert();
			  //"添加已完成"
			%>
			<script lang="javascript">
			alert('<%=add%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
		if (flag.equals("update")){
			Paitem paitem = new Paitem();
			paitem.setItem_id(func.isoStr(request.getParameter("item_id")));
			paitem.setItem_name(func.isoStr(request.getParameter("item_name")));
			paitem.setItem_en_name(func.isoStr(request.getParameter("item_en_name")));
			paitem.setItem_kor_name(func.isoStr(request.getParameter("item_kor_name")));
			paitem.setDescr(func.isoStr(request.getParameter("descr")));
			paitem.setDatatype(func.isoStr(request.getParameter("datatype")));
			paitem.setItem_type(func.isoStr(request.getParameter("item_type")));
			paitem.setPricision(Integer.parseInt(request.getParameter("pricision")));
			paitem.setCarry_bit(request.getParameter("carry_bit"));
			paitem.setPa_item_no(Integer.parseInt(request.getParameter("no")));
			paitem.setRetroactiveRaxCalcMark(retroactiveRaxCalcMark) ;
			paitem.setSumMainTainFlag(sumMainTainFlag) ;
			paitem.Update();
			  //"修改已完成"
			%>
			<script lang="javascript">
			alert('<%=edit%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
		if (flag.equals("del")) {
			Paitem paitem = new Paitem();
			paitem.Delete(Integer.parseInt(request.getParameter("no")));
			//PaParam paparam = new PaParam();
			//paparam.removePaparam(param_no);
			  //"删除已完成"
			%>
			<script lang="javascript">
			alert('<%=delete%>');
			location.href="<%=menu_code%>.jsp?menu_code=<%=menu_code%>&bigpage=<%=bigpage%>&strPage=<%=strPage%>";
			</script>
			<%
		}
	}catch (Exception ex) {
		System.out.println(ex);
	}
%>